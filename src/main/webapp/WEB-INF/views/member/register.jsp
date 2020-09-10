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
		});
		$("#submit").on("click", function(){
			if($("#userId").val()==""){
				alert("아이디를 입력해주세요.");
				$("#userId").focus();
				return false;
			}
			
			if($("#userPw").val()==""){
				alert("비밀번호를 입력해주세요.");
				$("#userPw").focus();
				return false;	
			}	
			
			if($("#userName").val()==""){
				alert("이름을 입력해주세요.");
				$("#userPw").focus();
				return false;	
			}
			var idChkVal = $("#idChk").val();
			if(idChkVal == "N"){
				alert("중복확인 버튼을 눌러주세요");
				return false;
			}else if(idChkVal == "Y"){
				$("#regForm").submit();
			}
			
				
			});
	});
	
	function fn_idChk(){
		$.ajax({
			url: "/member/idChk",
			type: "post",
			dataType : "json",
			data : {"userId" : $("#userId").val()},
			success : function(data){
				if(data == 1){
					alert("중복된 아이디입니다.");
				}else if(data == 0){
					$("#idChk").attr("value", "Y");
					alert("사용가능한 아이디입니다.");
				}
			}
		})
	}
</script>
<body>
	<section id="container">
		<form action="/member/register" method="post" id="regForm" >
			<div class="form-group has-feedback">
				<label class="control-label" for="userId">아이디</label>
				<input class="form-control" type="text" id="userId" name="userId"/>
				<button class="idChk" type="button" id="idChk" onclick="fn_idChk();" value="N">중복확인</button>
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
				<button class="btn btn-success" type="submit" id="submit">회원가입</button>
				<button class="cancel btn btn-danger" type="button">취소</button>
			</div>
	</section>
</body>
</html>