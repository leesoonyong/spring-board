<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>insert title here</title>
</head>
<body>
<div id="nav">
 <%@ include file="../include/nav.jsp" %>
</div>
	<table>
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
				 	 <a href="/board/view?bno=${list.bno}">${list.title}</a>
				  </td>
				  <td>${list.regDate}</td>
				  <td>${list.writer}</td>
				  <td>${list.viewCnt}</td>
				 </tr>
			 </c:forEach>
		 </tbody>
	</table>
	<div>
		<span>
			<c:if test = "${pageMaker.prev}">
				<a href="listPage${pageMaker.makeQuery(pageMaker.startPage -1)}">이전</a>
			</c:if>	
		</span>
		<span>
			<c:forEach begin = "${pageMaker.startPage}" end="${pageMaker.endPage}" var="idx">
				<a href="listPage${pageMaker.makeQuery(idx)}">${idx}</a>
			</c:forEach>	
		</span>
		<span>
			<c:if test = "${pageMaker.next && pageMaker.endPage > 0}">
				<a href="listPage${pageMaker.makeQuery(pageMaker.endPage + 1)}">다음</a>
			</c:if>	
		</span>
	</div>
</body>
</html>