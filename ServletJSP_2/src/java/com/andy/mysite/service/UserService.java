package com.andy.mysite.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.andy.mysite.dao.UserDao;
import com.andy.mysite.dao.UserDaoImpl;
import com.andy.mysite.entity.User;
import com.andy.mysite.exception.ServiceFailException;
import com.andy.mysite.util.MyBatisUtil;

public class UserService {
	private static final Logger logger = LoggerFactory.getLogger(UserService.class);

	private static UserService instance = new UserService();

	private UserService() {}

	public static UserService getInstance() {
		return instance;
	}

	UserDao dao = UserDaoImpl.getInstance();

	public int join(User user) {
		SqlSession session = MyBatisUtil.getInstance().getSqlSession();
		try {
			int result = dao.createUser(session, user);
			if (result > 0) {
				session.commit();
				logger.trace("사용자 추가 결과 : commit " + result);
				return result;
			} else {
				session.rollback();
				logger.trace("사용자 추가 실패 : rollback " + result);
				throw new ServiceFailException("사용자 추가 실패");
			}
		}catch(RuntimeException e){
			logger.trace("join fail", e);
			throw e;
		} finally {
			session.close();
		}
	}

	public int leave(String id) {
		SqlSession session = MyBatisUtil.getInstance().getSqlSession();
		try {
			int result = dao.deleteUser(session, id);
			if (result > 0) {
				session.commit();
				logger.trace("사용자 삭제 결과 : commit " + result);
				return result;
			} else {
				session.rollback();
				logger.trace("사용자 삭제 실패 : rollback " + result);
				throw new ServiceFailException(id + "사용자를 삭제할 수 없습니다.");
			}
		} catch (RuntimeException e) {
			logger.error("leave fail", e);
			throw e;
		} finally {
			session.close();
		}
	}

	public int updateUser(User user) {
		SqlSession session = MyBatisUtil.getInstance().getSqlSession();
		try {
			int result = dao.updateUser(session, user);
			if (result > 0) {
				session.commit();
				logger.trace("사용자 수정 결과 : commit " + result);
				return result;
			} else {
				session.rollback();
				logger.trace("사용자 수정 실패 : rollback " + result);
				throw new ServiceFailException(user.getId() + " 사용자의 정보를 수정할 수 없습니다.");
			}
		} catch (RuntimeException e) {
			logger.error("update fail", e);
			throw e;
		} finally {
			session.close();
		}
	}

	public User selectUser(String id) {
		SqlSession session = MyBatisUtil.getInstance().getSqlSession();
		try {
			User result = dao.getUserById(session, id);
			logger.trace("사용자 조회 결과 : " + result);
			return result;
		} catch (RuntimeException e) {
			logger.error("select fail", e);
			throw e;
		} finally {
			session.close();
		}
	}

	public User login(String id, String pass) {
		try {
			User result = selectUser(id);
			if (result == null) {
				throw new ServiceFailException(id + "로 등록된 사용자가 없습니다.");
			} else if (!result.getPass().equals(pass)) {
				throw new ServiceFailException("패스워드를 확인하세요.");
			} else {
				return result;
			}
		} catch (RuntimeException e) {
			logger.error("select fail", e);
			throw e;
		}
	}
	public List<User> selectUserByPage(int page) {
		SqlSession session = MyBatisUtil.getInstance().getSqlSession();
		try {
			List<User> result = dao.getUsersByPage(session, page);
			logger.trace("사용자 조회 결과 : " + result);
			return result;
		} catch (RuntimeException e) {
			logger.error("selectUserByPage fail", e);
			throw e;
		} finally {
			session.close();
		}
	}
}
