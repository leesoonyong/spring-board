<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<title>게시물 작성</title>
</head>
<script>
$(function(){
	
	var fileIndex = 1;
	//$("#fileIndex").append("<div><input type='file' style='float:left;' name='file_"+(fileIndex++)+"'>"+"<button type='button' style='float:right;' id='fileAddBtn'>"+"추가"+"</button></div>");
	$(".fileAdd_btn").on("click", function(){
		$("#fileIndex").append("<div><input type='file' style='float:left;' name='file_"+(fileIndex++)+"'>"+"</button>"+"<button type='button'  id='fileDelBtn'>"+"삭제"+"</button></div>");
	});
	$(document).on("click","#fileDelBtn", function(){
		$(this).parent().remove();
		
	});
});
</script>
<body>
<div id="nav">
 <%@ include file="../include/nav.jsp" %>
</div>
<c:if test="${msg == null }">
	<form method="post" enctype="multipart/form-data">
		<div class="form-group has-feedback">
			<label class="control-label">제목</label>
			<input class="form-control" type="text" name="title" />
		</div>
		<div class="form-group has-feedback">
			<label class="control-label">작성자</label>
			<input class="form-control"type="text" name="writer" value="${member.userName}" readonly="readonly"/><br />
		</div>
		<div>
			<label class="control-label">내용</label>
			<textarea class="form-control"cols="50" rows="5" name="content"></textarea><br />
		</div>
		<div id="fileIndex"></div>
		<div class="form-group">
			<button class="btn btn-success"type="submit">작성</button>
			<button class="fileAdd_btn btn btn-primary" type="button">파일추가</button>
		</div>
	</form>
</c:if>
<c:if test="${msg == false }">
	<p>로그인을 하셔야 글을 작성하실 수 있습니다</p>
	<p><a href="/">홈으로</a>
</c:if>
</body>
</html>