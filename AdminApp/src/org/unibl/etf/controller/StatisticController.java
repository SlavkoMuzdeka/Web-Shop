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

/**
 * Servlet implementation class LogController
 */
@WebServlet("/StatisticController")
public class StatisticController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StatisticController() {
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
			} else if (action.equals("statistics")) {
				address = "/WEB-INF/pages/statistics.jsp";
			} else
				address = "/WEB-INF/pages/404.jsp";
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

}
