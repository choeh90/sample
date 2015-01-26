package com.andy.mysite.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.andy.mysite.entity.User;

public class UserDaoImpl implements UserDao {
	private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);
	private static UserDaoImpl instance = new UserDaoImpl();
	private final static int CNT_PER_PAGE = 10;

	private UserDaoImpl() {
		logger.trace("UserDao 사용 준비 완료");
	}

	public static UserDaoImpl getInstance() {
		return instance;
	}

	private final String namespace = "com.andy.mysite.UserMapper.";
	@Override
	public int createUser(SqlSession session, User user) {
		String stmt = namespace + "createUser";
		int result = session.insert(stmt, user);
		return result;
	}

	@Override
	public User getUserById(SqlSession session, String id) {
		String stmt = namespace + "selectUser";
		User user = session.selectOne(stmt, id);
		return user;
	}

	@Override
	public int updateUser(SqlSession session, User user) {
		String stmt = namespace + "updateUser";
		int result = session.update(stmt, user);
		return result;
	}

	@Override
	public int deleteUser(SqlSession session, String user) {
		String stmt = namespace + "deleteUser";
		int result = session.delete(stmt, user);
		return result;
	}

	@Override
	public List<User> getAllUsers(SqlSession session) {
		String stmt = namespace + "selectAllUser";
		List<User> result = session.selectList(stmt);
		return result;
	}

	@Override
	public List<User> getUsersByPage(SqlSession session, int page) {
		String stmt = namespace + "selectUserByPage";
		HashMap<String, Integer> map = new HashMap<>();
		map.put("from", (page - 1) * CNT_PER_PAGE + 1);
		map.put("to", (page) * CNT_PER_PAGE + 1);
		List<User> result = session.selectList(stmt);
		return result;
	}

}
