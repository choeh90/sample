<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="/header.jsp"/>
	<h1>회원 가입 결과</h1>
	<c:if test="${empty loginUser }">
		회원 가입에 실패하였습니다.<br>
		실패 사유 : ${loginFailMessage }<br>
		<c:url value="/user/JoinForm.jsp" var="target"/>
		<a href="${target }">다시 하기</a>
	</c:if>
	<c:if test="${!empty loginUser }">
		회원 가입에 성공하였습니다.<br>
		좋은 활동 부탁 드립니다.<br>
		<c:url value="/main.jsp" var="target"/>
		<a href="${target }">메인으로</a>
	</c:if>
	<jsp:include page="/footer.jsp"/>
</body>
</html>