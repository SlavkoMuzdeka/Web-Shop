package org.unibl.etf.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.unibl.etf.beans.AdminBean;
import org.unibl.etf.beans.CategoryBean;
import org.unibl.etf.dao.AttributeDAO;
import org.unibl.etf.dto.Attribute;
import org.unibl.etf.dto.Category;

import org.unibl.etf.enums.Type;

/**
 * Servlet implementation class CategoryController
 */
@WebServlet("/CategoryController")
public class CategoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CategoryController() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String address = "";
		String action = request.getParameter("action");
		HttpSession session = request.getSession();
		if (action == null || action.equals("")) {
			address = "/WEB-INF/pages/login.jsp";
		} else if (action.equals("logout")) {
			session.invalidate();
			address = "/WEB-INF/pages/login.jsp";
		} else {
			AdminBean adminBean = (AdminBean) session.getAttribute("adminBean");
			if (adminBean == null || !adminBean.isLoggedIn()) {
				address = "/WEB-INF/pages/login.jsp";
			} else {
				if (action.equals("categories")) {
					address = "/WEB-INF/pages/categories.jsp";
				} else if (action.equals("deleteCategory")) {
					address = deleteCategory(session, request, address);
				} else if (action.equals("createCategory")) {
					address = createCategory(session, request, address);
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

	private String deleteCategory(HttpSession session, HttpServletRequest request, String address) {
		try {
			CategoryBean categoryBean = (CategoryBean) session.getAttribute("categoryBean");
			Integer userId = Integer.parseInt(request.getParameter("id"));
			Category category = new Category(userId);
			categoryBean.setCategory(category);
			if (categoryBean.delete())
				address = "/WEB-INF/pages/categories.jsp";
		} catch (Exception ex) {
			address = "/WEB-INF/pages/404.jsp";
		}
		return address;
	}

	private String createCategory(HttpSession session, HttpServletRequest request, String address) {
		String name = request.getParameter("categoryName");
		String superCategoryId = request.getParameter("superCategory");
		CategoryBean categoryBean = (CategoryBean) session.getAttribute("categoryBean");
		if (superCategoryId != null && superCategoryId != "") {
			Category category = new Category(name, new Category(Integer.parseInt(superCategoryId)));
			categoryBean.setCategory(category);
		} else {
			categoryBean.setCategory(new Category(name));
		}
		if (categoryBean.add()) {
			Integer numAttributes = Integer.parseInt(request.getParameter("numAttributes"));
			if (numAttributes != null && String.valueOf(numAttributes) != "")
				addAttributesToCategory(numAttributes, request, categoryBean.getCategory().getId());
			address = "/WEB-INF/pages/categories.jsp";
		} else
			address = "/WEB-INF/pages/404.jsp";
		return address;
	}

	private void addAttributesToCategory(Integer numAttributes, HttpServletRequest request, Integer categoryId) {
		Category category = new Category(categoryId);
		for (int i = 0; i < numAttributes; i++) {
			AttributeDAO.create(new Attribute(category, request.getParameter("attribute" + (i + 1)),
					request.getParameter("select" + (i + 1)).equals("text") ? Type.STRING : Type.NUMBER));
		}
	}
}
