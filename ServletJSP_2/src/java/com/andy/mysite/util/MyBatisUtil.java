package com.andy.mysite.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyBatisUtil {
	private static final Logger logger = LoggerFactory.getLogger(MyBatisUtil.class);
	private static MyBatisUtil instance = new MyBatisUtil();
	private SqlSessionFactory sessionFactory;

	private MyBatisUtil() {
		initFactory();
		logger.trace("MyBatis 사용 준비 완료");
	}

	public static MyBatisUtil getInstance() {
		return instance;
	}

	public SqlSession getSqlSession() {
		return sessionFactory.openSession(false);
	}

	public void initFactory() {
		SqlSessionFactory sqlSessionFactory = null;
		String resource = "mybatis_config.xml";
		try {
			InputStream inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
		this.sessionFactory = sqlSessionFactory;
	}
}
