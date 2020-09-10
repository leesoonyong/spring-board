<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<title>게시물 수정</title>
</head>
<script>
$(function(){
	var fileIndex = 1;
	$(".fileAdd_btn").on("click", function(){
		$("#fileIndex").append("<div><input type='file' style='float:left;' name='file_"+(fileIndex++)+"'>"+"</button>"+"<button type='button'id='fileDelBtn'>"+"삭제"+"</button></div>");
	});
	$(document).on("click","#fileDelBtn", function(){
		$(this).parent().remove();
		
	});
	


});

var fileNoArry = new Array();
var fileNameArry = new Array();


function fn_del(){
	alert($(".fileNo").data('fileNo'));
	fileNoArry.push(value);
	fileNameArry.push(name);
	$("#fileNoDel").attr("value", fileNoArry);
	$("#fileNameDel").attr("value", fileNameArry);
	
}

</script>
<body>
<div id="nav">
<%@ include file="../include/nav.jsp" %>
</div>
<form name="modifyForm" method="post" action="/board/modify" enctype="multipart/form-data">
	<input type="hidden" name="bno" value="${view.bno}">
	<input type="hidden" id="fileNoDel" name="fileNoDel[]" value=""> 
	<input type="hidden" id="fileNameDel" name="fileNameDel[]" value=""> 
	<label>제목</label>
	<input type="text" name="title" value="${view.title }"/><br />
	
	<label>작성자</label>
	<input type="text" name="writer" value="${view.writer}"/><br />
	
	<label>내용</label>
	<textarea cols="50" rows="5" name="content">${view.content}</textarea><br />
	<div id="fileIndex">
		<c:forEach var="file" items="${file}" varStatus="var">
		<div>
			<input type="hidden" id="FILE_NO" name="FILE_NO_${var.index}" value="${file.FILE_NO}">
			<input type="hidden" id="FILE_NAME" name="FILE_NAME" value="FILE_NO_${var.index}">
			<a href="#" id="fileName" onclick="return false;">${file.ORG_FILE_NAME}</a>(${file.FILE_SIZE}kb)
			<button id="fileDel" class="fileNo" data-fileNo="${file.FILE_NO}" data-fileIndex="FILE_NO_${var.index}" onclick="fn_del($(this));" type="button">삭제</button><br>
		</div>
		</c:forEach>
	</div>
	<button type="submit">완료</button>
	<button type="button" class="fileAdd_btn">파일추가</button>
</form>

</body>
</html>