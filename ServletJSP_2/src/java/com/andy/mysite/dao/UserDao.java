package com.andy.mysite.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.andy.mysite.entity.User;

public interface UserDao {
	int createUser(SqlSession session, User user);

	User getUserById(SqlSession session, String id);

	int updateUser(SqlSession session, User user);

	int deleteUser(SqlSession session, String user);

	List<User> getAllUsers(SqlSession session);

	List<User> getUsersByPage(SqlSession session, int page);
}
