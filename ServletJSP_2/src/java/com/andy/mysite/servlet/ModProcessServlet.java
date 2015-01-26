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
 * Servlet implementation class ModProcessServlet
 */
@WebServlet("/user/ModProcessServlet")
public class ModProcessServlet extends HttpServlet {
	private static final Logger logger = LoggerFactory
			.getLogger(ModProcessServlet.class);
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModProcessServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String pass = request.getParameter("pass");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		User user = new User(id, name, pass, email, phone);
		UserService service = UserService.getInstance();
		HttpSession session = request.getSession();
		try {
			service.updateUser(user);
			session.setAttribute("loginUser", user);
		} catch (Exception e) {
			logger.error("회원 수정 실패", e);
			throw new ServletException(e);
		}
		String path = "/main.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(path);
		dispatcher.forward(request, response);
	}

}
