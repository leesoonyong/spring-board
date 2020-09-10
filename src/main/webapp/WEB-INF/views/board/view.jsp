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
<title>게시물 조회</title>
</head>
<script>
$(function(){
	$(".replyModify").click(function(){
		   self.location = "/board/replyModify?bno=${view.bno}"
		    + "&page=${search.page}"
		    + "&perPageNum=${search.perPageNum}"
		    + "&searchType=${search.searchType}"
		    + "&keyword=${search.keyword}"
		    + "&rno=" + $(this).attr("data-rno");        
		  });
		  
	
	
	$(".replyDelete").click(function(){
		   self.location = "/board/replyDelete?bno=${view.bno}"
		    + "&page=${search.page}"
		    + "&perPageNum=${search.perPageNum}"
		    + "&searchType=${search.searchType}"
		    + "&keyword=${search.keyword}"
		    + "&rno=" + $(this).attr("data-rno"); 
		  });
		  
		  
	var formObj = $(".replyForm form[role='form']");
	$(".repSubmit").click(function(){
		formObj.attr("action", "replyWrite");
		formObj.submit();
	});	  
	
	function fn_fileDown(fileNo){
		var formObj = $("form[name='readForm']");
		$("#FILE_NO").attr("value", fileNo);
		formObj.attr("action", "/board/fileDown");
		formObj.submit();
	}

})

</script>
<body>
<div class="nav">
 <%@ include file="../include/nav.jsp" %>
</div>	
<div class="container">
	<div class="form-group">
		<label for="title" class="control-label">제목</label>
		<input type="text" id="title" name="title" class="form-control" value="${view.title}" readonly="readonly"/>
	</div>
	<div class="form-group">
		<label for="writer" class="control-label">작성자</label>
		<input type="text" id="witer" name="writer" class="form-control" value="${view.writer}" readonly="readonly"/>
	</div>
	<div class="form-group">
		<label for="content" class="control-label">내용</label>
		<textarea  id="content" name="content" class="form-control"  readonly="readonly">${view.content}"</textarea>
	</div>
	<hr>
	<span>파일 목록</span>
	<div class="form-group" style="border: 1px solid #dbdbdb">
		<c:forEach var="file" items="${file}">
			<a href="#" onclick="fn_fileDown('${file.FILE_NO}'); return false;">${file.ORG_FILE_NAME}</a>(${file.FILE_SIZE}kb)<br>
		</c:forEach>
	</div>
	<input type="hidden" id="page" name="page" value="${search.page}" readonly="readonly">
	<input type="hidden" id="perPageNum" name="perPageNum" value="${search.perPageNum}" readonly="readonly">
	<input type="hidden" id="searchType" name="searchType" value="${search.searchType}" readonly="readonly">
	<input type="hidden" id="keyword" name="keyword" value="${search.keyword}" readonly="readonly">
	
	
	<div class="form-group">
		<a href="/board/modify?bno=${view.bno}" class="btn btn-primary">수정</a>
		<a href="/board/delete?bno=${view.bno}" class="btn btn-warning">삭제</a>
		<a href="/board/listSearch?page=${search.page}&perPageNum=${search.perPageNum}&searchType=${search.searchType}&keyword=${search.keyword}" class="btn btn-default">목록
		</a>
	</div>
	<div id="reply">
		<ol class="replyList">
			<c:forEach items="${repList}" var="repList">
			<li>
				<p>
				<span class="glyphicon glyphicon-user"></span>
					${repList.writer}<br/>
					작성 날짜 : <fmt:formatDate value="${repList.regDate}" pattern="yyyy-MM-dd"/>
				</p>
				<p>${repList.content}</p>
				<p>
					<button type="button" data-rno="${repList.rno}" class="replyModify btn btn-primary btn-xs">수정</button>
					<button type="submit" data-rno="${repList.rno}" class="replyDelete btn-warning btn-xs">삭제</button>
				</p>
			</li>
			</c:forEach>
		</ol>
	</div>
	<section class="replyForm">
	<form name="readForm" role="form" method="post" class="form-horizontal">
		<input type="hidden" id="bno" name="bno" value="${view.bno}" readonly="readonly"/>
		<input type="hidden" id="page" name="page" value="${search.page}" readonly="readonly"/>
		<input type="hidden" id="perPageNum" name="perPageNum" value="${search.perPageNum}" readonly="readonly"/>
		<input type="hidden" id="searchType" name="searchType" value="${search.searchType}" readonly="readonly"/>
		<input type="hidden" id="keyword" name="keyword" value="${search.keyword}" readonly="readonly"/>
		<input type="hidden" id="FILE_NO" name="FILE_NO" value="">
		
		<div class="form-group">
			<label for="writer" class="col-sm-2 control-label">작성자</label>
			<div class="col-sm-10">
				<input type="text" id="writer" class="form-contorl" name="writer">
			</div>
		</div>
		<div class="form-group">
			<label for="content" class="col-sm-2 control-label">댓글 내용</label>
			<div class="col-sm-10">
				<textarea id="content" name="content" class="form-control"></textarea>
			</div>
		</div>
		<div class=form-group>
			<div class="col-sm-offset-2 col-sm-10">
				<button type="button" class="repSubmit btn btn-success">작성</button>
			</div>
		</div>
		
	</form>
	</section>
</div>	
</body>
</html>