<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
form {
	display: inline;
}

#header_info {
	text-align: right;
}

#wrapper {
	text-align:center;
	margin: 10px 20px;
}
</style>
<div id="wrapper">
	<h1>길동이네 집에 오신것을 환영합니다.</h1>
	<div id="header_info">
		<c:if test="${!empty loginUser }">
		${loginUser.id }님 반갑습니다. <a href="<%=request.getContextPath() %>/user/LogoutServlet"><button>로그아웃</button></a><a href="<%=request.getContextPath() %>/user/ModUserServlet"><button>개인정보</button></a>
		</c:if>
		<c:if test="${empty loginUser }">
			<form method="post" action="<%=request.getContextPath() %>/user/LoginServlet">
				<label for="id">ID</label> <input type="text" id="id" name="id">
				<label for="pass">PASS</label> <input type="password" id="pass"
					name="pass"> <input type="submit" value="login" />
			</form>
			<a href="<%=request.getContextPath() %>/user/JoinServlet"><input type="button" value="가입" /></a>
		</c:if>
	</div>
</div>
<hr>