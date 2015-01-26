<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 가입</title>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
	window.$("#use").click(function(){
			opener.$("#joinid").val("${id}");
			opener.$("#checked").val("checked");
			self.close();
		});
	});
</script>
</head>
<body>
	<h2>아이디 중복 확인</h2>
	<c:if test="${!empty (result) }">
		${id }는 이미 사용중인 아이디입니다.<br>
	</c:if>
	<c:if test="${empty(result) }">
		${id }는 사용가능한 아이디입니다.<br>
		<input type="button" value="사용" id="use"><br>
	</c:if>
	<c:url value="/user/IdCheck" var="idchk"></c:url>
	다른 ID로 시도하려면 새로 중복 체크를 하세요.
	<form action="${idchk}">
		<label for="id">아이디</label>
		<input type="text" id="id" name="id">
		<input type="submit" value="전송"/>
	</form>
</body>
</html>