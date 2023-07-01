package org.unibl.etf.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.unibl.etf.beans.AdminBean;
import org.unibl.etf.beans.CategoryBean;
import org.unibl.etf.beans.StatisticBean;
import org.unibl.etf.beans.UserBean;
import org.unibl.etf.dto.User;

/**
 * Servlet implementation class Controller
 */
@WebServlet("/UserController")
@MultipartConfig
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserController() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String address = "/WEB-INF/pages/login.jsp";
		String action = request.getParameter("action");
		HttpSession session = request.getSession();
		if (action == null || action.equals("")) {
			address = "/WEB-INF/pages/login.jsp";
		} else if (action.equals("logout")) {
			session.invalidate();
			address = "/WEB-INF/pages/login.jsp";
		} else if (action.equals("login")) {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			if (username != null && password != null) {
				address = logUser(username, password, session, address);
			} else
				session.setAttribute("error", "error");
		} else {
			AdminBean adminBean = (AdminBean) session.getAttribute("adminBean");
			if (adminBean == null || !adminBean.isLoggedIn()) {
				address = "/WEB-INF/pages/login.jsp";
			} else {
				if (action.equals("users")) {
					address = "/WEB-INF/pages/users.jsp";
				} else if (action.equals("updateUser")) {
					address = updateUserData(address, session, request);
				} else if (action.equals("deleteUser")) {
					address = deleteUser(session, request, address);
				} else if (action.equals("createUser")) {
					address = createUser(session, request, address);
				} else
					address = "/WEB-INF/pages/404.jsp";
			}
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	private String createUser(HttpSession session, HttpServletRequest request, String address) {
		try {
			address = "/WEB-INF/pages/createUser.jsp";
			UserBean userBean = (UserBean) session.getAttribute("userBean");
			User newUser = setUserFields(request, userBean, true);
			if(newUser != null) {
				userBean.setUser(newUser);
				if(userBean.add())
					address = "/WEB-INF/pages/users.jsp";
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return address;
	}

	private String deleteUser(HttpSession session, HttpServletRequest request, String address) {
		try {
			UserBean userBean = (UserBean) session.getAttribute("userBean");
			Integer userId = Integer.parseInt(request.getParameter("id"));
			User user = new User(userId);
			userBean.setUser(user);
			if (userBean.delete())
				address = "/WEB-INF/pages/users.jsp";
		} catch (Exception ex) {
			address = "/WEB-INF/pages/404.jsp";
		}
		return address;
	}

	private String updateUserData(String address, HttpSession session, HttpServletRequest request) {
		try {
			address = "/WEB-INF/pages/updateUser.jsp";
			UserBean userBean = (UserBean) session.getAttribute("userBean");
			User updatedUser = setUserFields(request, userBean, false);
			if (updatedUser != null) {
				userBean.setUser(updatedUser);
				if (userBean.update())
					address = "/WEB-INF/pages/users.jsp";
			} else if (request.getParameter("id") != null) {
				User user = new User(Integer.parseInt(request.getParameter("id")));
				userBean.setUser(user);
				if (!userBean.getOne())
					address = "/WEB-INF/pages/users.jsp";
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			address = "/WEB-INF/pages/404.jsp";
		}
		return address;
	}

	private String logUser(String username, String password, HttpSession session, String address) {
		try {
			AdminBean adminBean = new AdminBean();
			adminBean.setAdmin(adminBean.retreive(username, password));
			if (adminBean.isLoggedIn()) {
				session.setAttribute("adminBean", adminBean);
				UserBean userBean = new UserBean();
				CategoryBean categoryBean = new CategoryBean();
				session.setAttribute("categoryBean", categoryBean);
				StatisticBean statisticBean = new StatisticBean();
				session.setAttribute("statisticBean", statisticBean);
				session.setAttribute("userBean", userBean);
				address = "/WEB-INF/pages/users.jsp";
			} else
				session.setAttribute("error", "error");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return address;
	}

	private User setUserFields(HttpServletRequest request, UserBean userBean, boolean t) throws Exception {
		Integer id = null;
		if(!t) {	//Ako je false onda vrsimo azuriranje i treba nam id, ako je true (kreiranje) onda nam ne treba id
			id = Integer.parseInt(request.getParameter("id"));
		}
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String city = request.getParameter("city");
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String mail = request.getParameter("mail");
		if (firstName != null && lastName != null && city != null && userName != null && password != null
				&& mail != null) {
			byte[] avatar = getAvatar(request);
			User user = null;
			if(id == null) {
				user = new User(firstName, lastName, userName, password, city, avatar == null ? userBean.getUser().getAvatar() : avatar, mail);
			}else
				user = new User(id, firstName, lastName, userName, password, city, avatar == null ? userBean.getUser().getAvatar() : avatar, mail);
			return user;
		}
		return null;
	}

	private byte[] getAvatar(HttpServletRequest request) throws Exception {
		byte[] data = null;
		Part filePart = request.getPart("avatar");
		String fileName = filePart.getSubmittedFileName();
		if (fileName != null && !fileName.isEmpty()) {
			InputStream inputStream = filePart.getInputStream();
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int bytesRead;
			while ((bytesRead = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, bytesRead);
			}
			data = outputStream.toByteArray();
		}
		return data;
	}

}
