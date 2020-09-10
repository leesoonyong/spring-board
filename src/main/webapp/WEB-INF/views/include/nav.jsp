<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<style type="text/css">
.navlist {
	list-style: none;
	display: inline;
	padding: 10px;
	margin: auto;
	font-size: 16px;
}

.navMain {
	margin-left: 35px;
}

navUl {
	padding: 10px;
}
</style>
<header>
<h1 class="navMain">
	게시판
</h1>
<hr/>
</header>
<ul class="navUl">
	<li class="navlist"><a href="/board/listSearch">목록</a></li>
	<li class="navlist"><a href="/board/write">글 작성</a></li>
	<li class="navlist"><a href="/">로그인</a></li>
</ul>
