package com.andy.mysite.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/DBConnectionTest")
public class DBConnectionTest extends HttpServlet {
	private static final Logger logger = LoggerFactory.getLogger(DBConnectionTest.class);
	private static final long serialVersionUID = 1L;

	public DBConnectionTest() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		jndiTest(out);
		myBatisTest(out);
		testMyBatisUtil(out);
	}

	private void myBatisTest(PrintWriter out) {
		String resource = "mybatis_config.xml";
		InputStream inputStream = null;
		try {
			inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			out.println("Session Factory 확인 : " + sqlSessionFactory + "<br>");
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}

	private void jndiTest(PrintWriter out) {
		Context initContext = null;
		try {
			initContext = new InitialContext();
			DataSource ds = (DataSource) initContext.lookup("java:/comp/env/jdbc/myOracle");
			Connection conn = ds.getConnection();
			out.println("connection 획득 확인 : " + conn + "<br>");
		} catch (NamingException | SQLException e) {
			logger.error(e.getMessage(), e);
		}
	}

	private void testMyBatisUtil(PrintWriter out) {
		MyBatisUtil util = MyBatisUtil.getInstance();
		logger.trace("session 확인 : " + util.getSqlSession());
		out.println("session 확인 : " + util.getSqlSession() + "<br>");
	}
}
