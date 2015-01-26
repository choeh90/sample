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
	text-align:left;
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
		$("#dupCheck").click(function(){
			if($("#joinid").val()==""){
				alert("아이디를 입력해주세요.");
				$("#joinid").focus();		
			}else{
				<c:url value="/user/IdCheck" var="idchk"></c:url>
				var url = "${idchk}?id="+$("#joinid").val();
				window.open(url, "_blank", "width=450, height=200, toolbar=no, menubar=no, resizable=no")
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

/* 	    var updateErrorMessage = function() {
	        document.querySelector("#error").innerHTML = password1.validationMessage;
	    };
	     */
	    
	    password1.addEventListener('change', checkPasswordValidity, false);
	    password2.addEventListener('change', checkPasswordValidity, false);
 
	    
	    var form = document.getElementById('form');
	    form.addEventListener('submit', function() {
	    	if($("#checked").val()==""){
				alert("중복체크 해주세요.");
				event.preventDefault();
			}
	        checkPasswordValidity();
	        if (!this.checkValidity()) {
	            event.preventDefault();
	           // updateErrorMessage();
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
	<c:url value="/user/JoinProcess" var="action"></c:url>
	<h2>회원 가입</h2>
	<hr>
	<span class="need">*</span> 표시 항목은 필수 입력 항목입니다.<br>
	<div id="formWrapper">
	<form action="${action }" method="post" id="form">
		<label for="name">이름</label>
		<input type="text" id="name" name="name"  required/><span class="need">*</span><br>
		<label for="joinid">아이디</label>
		<input type="text" id="joinid" name="id" required/><span class="need">*</span>
		<input type="button" value="중복확인" id="dupCheck"/>
		<input type="hidden" name="checked" id="checked"/><br>
		<label for="pass">비밀번호</label>
		<input type="password" id="pass" name="pass" required/><span class="need">*</span><br>
		<label for="pass2">비밀번호 확인</label>
		<input type="password" id="pass2" name="pass2" required/><span class="need">*</span><br>
		<label for="email">이메일</label>
		<input type="email" id="email" name="email" /><br>
		<label for="tel">전화번호</label>
		<input type="tel" id="tel" name="phone"/><br>
		<input type="submit" value="확인" id="submit"/>
		<input type="reset" value="새로"/>
	</form>
	<p id="error"></p>
	</div>
	</div>
	<jsp:include page="/footer.jsp"/>
</body>
</html>