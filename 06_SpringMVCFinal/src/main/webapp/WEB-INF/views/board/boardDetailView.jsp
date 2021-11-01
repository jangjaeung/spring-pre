<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 상세</title>
</head>
<body>
	<jsp:include page="../common/menubar.jsp"></jsp:include>
	<br style="clear:both">
	<h1 align="center">${board.boardNo }번 글 상세보기</h1>
	<br><br>
	<table align="center" width="800" border="1">
		<tr>
			<td width="100">제목</td>
			<td>${board.boardTitle }</td>
		</tr>
		<tr>
			<td>작성자</td>
			<td>${board.boardWriter }</td>
		</tr>
		<tr>
			<td>작성날짜</td>
			<td>${board.bCreateDate }</td>
		</tr>
		<tr>
			<td>조회수</td>
			<td>${board.boardCount }</td>
		</tr>
		<tr height="300">
			<td>내용</td>
			<td>${board.boardContents }</td>
		</tr>
		<tr>
			<td>첨부파일</td>
			<td>${board.boardFileName }</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<c:url var="bModify" value="boardModifyView.kh">
					<c:param name="boardNo" value="${board.boardNo }"></c:param>
				</c:url>
				<c:url var="bDelete" value="boardDelete.kh">
					<c:param name="boardNo" value="${board.boardNo }"></c:param>
					<c:param name="fileName" value="${board.boardFileRename }"></c:param>
				</c:url>
				<a href="${bModify }">수정페이지로 이동</a>
				<a href="${bDelete }">삭제</a>
			</td>
		</tr>
	</table>
</body>
</html>