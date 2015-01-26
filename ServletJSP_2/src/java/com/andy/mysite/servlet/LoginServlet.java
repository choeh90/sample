package com.andy.mysite.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.andy.mysite.entity.User;
import com.andy.mysite.service.UserService;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/user/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final Logger logger = LoggerFactory
			.getLogger(LoginServlet.class);
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		UserService service = UserService.getInstance();

		HttpSession session = request.getSession();
		User user = null;
		try {
			user = service.login(id, pass);
		} catch (Exception e) {
			logger.error("로그인 실패", e);
			throw new ServletException(e);
		}
		session.setAttribute("loginUser", user);
		String path = "/main.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(path);
		dispatcher.forward(request, response);
	}
}
