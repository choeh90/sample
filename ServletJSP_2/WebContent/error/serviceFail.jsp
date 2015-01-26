<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ page isErrorPage="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>service 실패</title>
</head>
<body>
	<jsp:include page="/header.jsp" />
	<h1>사용중 불편을 드려 대단히 죄송합니다.</h1>
	오류 내용:
	<%=exception.getMessage()%>
	<jsp:include page="/footer.jsp" />
</body>
</html>