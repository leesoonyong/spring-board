<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
	 <script src='https://code.jquery.com/jquery-3.3.1.min.js'></script>
	<title>Home</title>
</head>
<script>
$(function(){
	$("#logoutBtn").on("click", function(){
		location.href="member/logout";
	});
	
	$("#register").on("click", function(){
		location.href="member/register";
	});
	
	$("#modifyBtn").on("click", function(){
		location.href="member/memberModifyView";
	});
	
	$("#deleteBtn").on("click", function(){
		location.href="member/memberDeleteView";
	});
});

</script>
<body>
<h1>
	게시판에 오신것을 환영합니다!  
</h1>

<P>  먼저 회원가입을 진행해주세요  </P>
<p><a href="/board/write">게시물 쓰기</a></p>
<p><a href="/board/listSearch">게시물 검색</a></p>

<form action ="/member/login" method="post">
	<c:if test="${member == null}">
		<div>
			<label for="userId">아이디</label>
			<input type="text" id="userId" name="userId"/>
		</div>
		<div>
			<label for="userPw">비밀번호</label>
			<input type="text" id="userPw" name="userPw"/>
		</div>
		<div>
			<button type="submit" id="login">로그인</button>
			<button type="button" id="register">회원가입</button>
		</div>
	</c:if>
	<c:if test="${member != null}">
		<div>
			<p>${member.userId}님 환영합니다.</p>
			<button id="modifyBtn" type="button">회원 정보수정</button>
			<button id="deleteBtn" type="button">회원 탈퇴</button>
			<button id="logoutBtn" type="button">로그아웃</button>
		</div>
	</c:if>
	<c:if test="${msg == false}">
		<p style="color : red">로그인 실패! 아이디와 비밀번호를 확인해주세요</p>
	</c:if>
</form>

</body>
</html>
