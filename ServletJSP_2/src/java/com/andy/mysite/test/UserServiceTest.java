package com.andy.mysite.test;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.andy.mysite.entity.User;
import com.andy.mysite.service.UserService;

@WebServlet("/UserServiceTest")
public class UserServiceTest extends HttpServlet {

	private static final Logger logger = LoggerFactory.getLogger(UserServiceTest.class);
	UserService service = UserService.getInstance();

	private static final long serialVersionUID = 1L;

	private User makeTempUser() {
		User user = new User();
		user.setId("hong");
		user.setName("홍길동");
		user.setPass("hong1234");
		user.setEmail("abc@def.net");
		user.setPhone("010-000-0000");
		return user;
	}

	private void createUserTest() {
		User user = makeTempUser();
		try {
			int result = service.join(user);
			logger.trace("join  : " + result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void selectUserTest() {
		String id = makeTempUser().getId();
		try {
			User result = service.selectUser(id);
			logger.trace("사용자 조회 결과 : " + result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void updateUserTest() {
		User user = makeTempUser();
		user.setName("임꺽정");
		user.setPass("lim1234");
		user.setEmail("ghi@jkl.com");
		user.setPhone("010-111-1111");
		try {
			int result = service.updateUser(user);
			logger.trace("사용자 수정 결과 : " + result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void deleteUserTest() {
		User user = makeTempUser();
		try {
			int result = service.leave(user.getId());
			logger.trace("사용자 삭제 결과 : commit " + result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		createUserTest();
		selectUserTest();
		updateUserTest();
		selectUserTest();
		deleteUserTest();
		selectUserTest();
	}

}
