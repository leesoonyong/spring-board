<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script src='https://code.jquery.com/jquery-3.3.1.min.js'></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<meta charset="UTF-8">
<title>회원 가입</title>
</head>
<script>
	$(function(){
		$(".cancel").on("click", function(){
			location.href="/";
		})
		$("#submit").on("click", function(){
			if($("#userPw").val()==""){
				alert("비밀번호를 입력해주세요.");
				$("#userPw").focus();
				return false;	
			}	
			
			if($("#userName").val()==""){
				alert("이름을 입력해주세요.");
				$("#userName").focus();
				return false;	
			}
			$.ajax({
				url : "/member/passChk",
				type : "POST",
				dataType : "json",
				data : $("#modifyForm").serializeArray(),
				success: function(data){
					
					if(data==true){
						if(confirm("회원수정하시겠습니까?")){
							$("#modifyForm").submit();
						}
					}else{
						alert("비밀번호가 틀렸습니다")
						return;
					}
				}
			})
		});	
	})
</script>
<body>
	<section id="container">
		<form action="/member/memberModify" method="post" id="modifyForm">
			<div class="form-group has-feedback">
				<label class="control-label" for="userId">아이디</label>
				<input class="form-control" type="text" id="userId" name="userId" value="${member.userId}" readonly="readonly"/>
			</div>
			<div class="form-group has-feedback">
				<label class="control-label" for="userPw">패스워드</label>
				<input class="form-control" type="password" id="userPw" name="userPw"/>
			</div>
			<div class="form-group has-feedback">
				<label class="control-label" for="userName">이름</label>
				<input class="form-control" type="text" id="userName" name="userName"/>
			</div>
		</form>
			<div class="form-group has-feedback">
				<button class="btn btn-success" type="button" id="submit">회원 정보수정</button>
				<button class="cancel btn btn-danger" type="button">취소</button>
			</div>
	</section>
</body>
</html>