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
 * Servlet implementation class IdCheck
 */
@WebServlet("/user/IdCheck")
public class IdCheck extends HttpServlet {
	private static final Logger logger = LoggerFactory.getLogger(IdCheck.class);
	UserService service = UserService.getInstance();
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public IdCheck() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		logger.trace(id);
		User result = null;
		try {
			result = service.selectUser(id);
		} catch (Exception e) {
			logger.error("idCheck", e);
			throw new ServletException(e);
		}
		request.setAttribute("id", id);
		request.setAttribute("result", result);
		String path = "/user/idConfirm.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(path);
		dispatcher.forward(request, response);
	}

}
