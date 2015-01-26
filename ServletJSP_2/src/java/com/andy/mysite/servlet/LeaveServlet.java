package com.andy.mysite.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.andy.mysite.entity.User;
import com.andy.mysite.service.UserService;

/**
 * Servlet implementation class LeaveServlet
 */
@WebServlet("/user/LeaveServlet")
public class LeaveServlet extends HttpServlet {
	private static final Logger logger = LoggerFactory.getLogger(LeaveServlet.class);
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LeaveServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserService service = UserService.getInstance();
		User user = (User) request.getSession().getAttribute("loginUser");
		try {
			service.leave(user.getId());
		} catch (Exception e) {
			logger.error("회원 탈퇴 실패", e);
			throw new ServletException(e);
		}
		request.getSession().invalidate();

		String path = "/main.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(path);
		dispatcher.forward(request, response);
	}

}
