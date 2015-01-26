package com.andy.mysite.test;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.andy.mysite.dao.UserDao;
import com.andy.mysite.dao.UserDaoImpl;
import com.andy.mysite.entity.User;
import com.andy.mysite.util.MyBatisUtil;

@WebServlet("/UserDaoImplTest")
public class UserDaoImplTest extends HttpServlet {
	private static final Logger logger = LoggerFactory.getLogger(UserDaoImplTest.class);
	UserDao dao = UserDaoImpl.getInstance();

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

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		createUserTest();
		selectUserTest();
		updateUserTest();
		selectUserTest();
		deleteUserTest();
		selectUserTest();
	}

	private void deleteUserTest() {
		User user = makeTempUser();

		SqlSession session = MyBatisUtil.getInstance().getSqlSession();
		try {
			int result = dao.deleteUser(session, user.getId());
			if (result > 0) {
				session.commit();
				logger.trace("사용자 삭제 결과 : commit " + result);
			} else {
				session.rollback();
				logger.trace("사용자 삭제 실패 : rollback " + result);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	private void updateUserTest() {
		User user = makeTempUser();
		user.setName("임꺽정");
		user.setPass("lim1234");
		user.setEmail("ghi@jkl.com");
		user.setPhone("010-111-1111");
		SqlSession session = MyBatisUtil.getInstance().getSqlSession();
		try {
			int result = dao.updateUser(session, user);
			if (result > 0) {
				session.commit();
				logger.trace("사용자 수정 결과 : commit " + result);
			} else {
				session.rollback();
				logger.trace("사용자 수정 실패 : rollback " + result);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	private void createUserTest() {
		User user = makeTempUser();
		SqlSession session = MyBatisUtil.getInstance().getSqlSession();
		try {
			int result = dao.createUser(session, user);
			if (result > 0) {
				session.commit();
				logger.trace("사용자 추가 결과 : commit " + result);
			} else {
				session.rollback();
				logger.trace("사용자 추가 성공 : rollback " + result);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	private void selectUserTest() {
		String id = makeTempUser().getId();
		SqlSession session = MyBatisUtil.getInstance().getSqlSession();
		try {
			User result = dao.getUserById(session, id);
			logger.trace("사용자 조회 결과 : " + result);
		} finally {
			session.close();
		}

	}

}
