<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자유게시판</title>
</head>
<body>
	<jsp:include page="../common/menubar.jsp"></jsp:include>
	<br style="clear:both">
	<h1 align="center">게시글 등록페이지</h1>
	<br><br>
	<table align="center" border="1">
		<tr>
			<th>번호</th>
			<th width="300">제목</th>
			<th>작성자</th>
			<th>날짜</th>
			<th>조회수</th>
			<th>첨부파일</th>
		</tr>
		<c:forEach items="${bList }" var="board">
			<tr>
				<td>${board.boardNo }</td>
				<td>${board.boardTitle }</td>
				<td>${board.boardWriter }</td>
				<td>${board.bCreateDate }</td>
				<td>${board.boardCount }</td>
				<td>O</td>
			</tr>
		</c:forEach>
		<tr align="center" height="20">
			<td colspan="6">
				<c:if test="${pi.currentPage <= 1 }">
					[이전]
				</c:if>
				<c:if test="${pi.currentPage > 1 }">
					<a href="#">[이전]</a>
				</c:if>
				
				<c:forEach var="p" begin="${pi.startNavi }" end="${pi.endNavi }">
					<c:if test="${p eq pi.currentPage }">
						<font color="red" size="4">[${p }]</font>
					</c:if>
					<c:if test="${p ne pi.currentPage }">
						<a href="#">[${p }]</a>&nbsp;
					</c:if>
				</c:forEach>
				
				<c:if test="${pi.currentPage >= pi.maxPage }">
					[다음]
				</c:if>
				<c:if test="${pi.currentPage < pi.maxPage }">
					<a href="#">[다음]</a>
				</c:if>
			</td>
		</tr>
	</table>
</body>
</html>