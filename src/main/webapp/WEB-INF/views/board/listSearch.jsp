<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script src='https://code.jquery.com/jquery-3.3.1.min.js'></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<meta charset="UTF-8">
<title>insert title here</title>
</head>
<body>
<div id="nav">
 <%@ include file="../include/nav.jsp" %>
</div>
<div class="container">
	<table class="table table-hover">
		 <thead>
		 	 <tr>
			   <th>번호</th>
			   <th>제목</th>
			   <th>작성일</th>
			   <th>작성자</th>
			   <th>조회수</th>
		  	</tr>
		 </thead>
		 <tbody>
			 <c:forEach items="${list}" var="list">
				 <tr>
				  <td>${list.bno}</td>
				  <td>
				 	 <a href="/board/view?bno=${list.bno}
				 	 		             &page=${search.page}
				 	 		             &perPageNum=${search.perPageNum}
				 	 		             &searchType=${search.searchType}
				 	 		             &keyword=${search.keyword}">${list.title}</a>
				  </td>
				  <td><fmt:formatDate value="${list.regDate}" pattern="yyyy-MM-dd"/></td>
				  <td>${list.writer}</td>
				  <td><c:out value="${list.hit}"/></td>
				 </tr>
			 </c:forEach>
		 </tbody>
	</table>
	<div class="search row">
		<div class="col-xs-2 col-sm-2">
			<select name="searchType" class="form-control">
				<option value="n"<c:out value="${search.searchType == null ? 'selected' : '' }"/>>-------</option>
				<option value="t"<c:out value="${search.searchType eq 't' ? 'selected' : '' }"/>>제목</option>
				<option value="c"<c:out value="${search.searchType eq 'c' ? 'selected' : '' }"/>>내용</option>
				<option value="w"<c:out value="${search.searchType eq 'w' ? 'selected' : '' }"/>>작성자</option>
				<option value="tc"<c:out value="${search.searchType eq 'tc' ? 'selected' : '' }"/>>제목+내용</option>
			</select>
		</div>
		<div class="col-xs-10 col-sm-10">
			<div class="input-group">	
				<input type="text" class="form-control" name="keyword" id="keywordInput" value="${search.keyword}"/>
				<span class="input-group-btn">
					<button id="searchBtn" class="btn btn-default">검색</button>
				</span>
			</div>
		</div>
		<script>
		$(function(){
			$('#searchBtn').click(function(){
				self.location = "listSearch"
				+ '${pageMaker.makeQuery(1)}'
				+ '&searchType='
				+ $('select option:selected').val()
				+ '&keyword='
				+ encodeURIComponent($('#keywordInput').val());
			});
		});
		</script> 
	</div>
	<div class="col-md-offset-3">
		<ul class="pagination">
			<c:if test = "${pageMaker.prev}">
				<li><a href="listSearch${pageMaker.makeSearch(pageMaker.startPage -1)}">이전</a></li>
			</c:if>	
			<c:forEach begin = "${pageMaker.startPage}" end="${pageMaker.endPage}" var="idx">
				<li <c:out value="${pageMaker.pg.page == idx ? 'class=active' : ''}" />>
				<a href="listSearch${pageMaker.makeSearch(idx)}">${idx}</a></li>
			</c:forEach>	
			<c:if test = "${pageMaker.next && pageMaker.endPage > 0}">
				<li><a href="listSearch${pageMaker.makeSearch(pageMaker.endPage + 1)}">다음</a></li>
			</c:if>	
		</ul>
	</div>
</div>	
</body>
</html>