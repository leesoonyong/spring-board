<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
 <script src='https://code.jquery.com/jquery-3.3.1.min.js'></script>
<meta charset="UTF-8">
<title>댓글 삭제</title>
</head>
<body>
<div id="nav">
<%@ include file="../include/nav.jsp" %>
</div>
	<form method="post">
		<input type="hidden" id="bno" name="bno" value="${readReply.bno}" readonly="readonly" />
		<input type="hidden" id="rno" name="rno" value="${readReply.rno}" readonly="readonly" /> 
		<input type="hidden" id="page" name="page" value="${search.page}" readonly="readonly" />
	    <input type="hidden" id="perPageNum" name="perPageNum" value="${search.perPageNum}" readonly="readonly" />
		<input type="hidden" id="searchType" name="searchType" value="${search.searchType}" readonly="readonly" />
		<input type="hidden" id="keyword" name="keyword" value="${search.keyword}" readonly="readonly" />
		
		<p>정말로 삭제하시겠습니까?</p>
	  	<p>
	    <button type="submit">예, 삭제합니다.</button><br />
	    <button type="button" id="cancelBtn">아니오, 삭제하지 않습니다.</button>
	    
	    <script>
	    	$("#cancelBtn").click(function(){
	    		self.location ="/board/view?bno=${readReply.bno}"
    			+ "&page=${search.page}"
    		    + "&perPageNum=${search.perPageNum}"
    		    + "&searchType=${search.searchType}"
    		    + "&keyword=${search.keyword}"; 
	    	});
	    
	    </script>
		</p>
	</form>
</body>
</html>