<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 가입</title>
<style>
	body{
		text-align:center;
	}
	span.need{
		color:red;
	}
	label{
		display:inline-block;
		width:130px;
	}
#wrapper {
	margin: 10px 20px;
}
#formWrapper{
	margin:0px auto;
	text-align:left;
	width:400px;
}
</style>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript">
	
	$(document).ready(function(){
		$("#leaveB").click(function(){
			var confirm = window.confirm("탈퇴하시겠습니까?");
			if(confirm){
				location.href="<%=request.getContextPath()%>/user/LeaveServlet";
			}
		});
		
		  var password1 = document.getElementById('pass');
		    var password2 = document.getElementById('pass2');

		    var checkPasswordValidity = function() {
		        if (password1.value != password2.value) {
		            password1.setCustomValidity('비밀번호와 확인 비밀번호가 일치하지 않습니다.');
		        } else {
		            password1.setCustomValidity('');
		        }        
		    };

		    var updateErrorMessage = function() {
		        document.getElementById('error').innerHTML = password1.validationMessage;
		    };
		    
		    
		    password1.addEventListener('change', checkPasswordValidity, false);
		    password2.addEventListener('change', checkPasswordValidity, false);
	 
		    
		    var form = document.getElementById('form');
		    form.addEventListener('submit', function() {

		        checkPasswordValidity();
		        if (!this.checkValidity()) {
		            event.preventDefault();
		            updateErrorMessage();
		            password1.focus();
		        }else{
		        	 password1.setCustomValidity('');
		        }
		    }, false);


	});
</script>
</head>
<body>
	<jsp:include page="/header.jsp"/>
	<div id="wrapper">
	<c:url value="/user/ModProcessServlet" var="action"></c:url>
	<h2>정보 수정</h2>
	<hr>
	<span class="need">*</span> 표시 항목은 필수 입력 항목입니다.<br>
	<div id="formWrapper">
	<form action="${action }" method="post" id="form">
		<label for="name">이름</label>
		<input type="text" id="name" name="name"  value="${loginUser.name }" required/><span class="need">*</span><br>
		<label for="joinid">아이디</label>
		<input type="text" id="joinid" name="id" value="${loginUser.id }" readonly="readonly"/><br>
		<label for="pass">비밀번호</label>
		<input type="password" id="pass" name="pass"  required/><span class="need">*</span><br>
		<label for="pass2">비밀번호 확인</label>
		<input type="password" id="pass2" name="pass2" required/><span class="need">*</span><br>
		<label for="email">이메일</label>
		<input type="email" id="email" name="email" value="${loginUser.email }"/><br>
		<label for="tel">전화번호</label>
		<input type="tel" id="tel" name="phone" value="${loginUser.phone }"/><br>
		<input type="submit" value="확인" id="submit"/>
		<input type="reset" value="새로"/>
	</form>
	</div>
	</div>
	<div id="error"></div>
	<input type="button" id="leaveB" value="탈퇴하기">
	<jsp:include page="/footer.jsp"/>
</body>
</html>