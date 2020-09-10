<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>댓글 수정</title>
</head>
<body>
<div id="nav">
<%@ include file="../include/nav.jsp" %>
</div>
	<form method="post">
	
	<label>내용</label>
	<textarea cols="50" rows="5" name="content">${readReply.content}</textarea><br />
	
	<button type="submit">수정</button>
	
	</form>
</body>
</html>