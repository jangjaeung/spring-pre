<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 상세</title>
</head>
<body>
	<jsp:include page="../common/menubar.jsp"></jsp:include>
	<br style="clear:both">
	<h1 align="center">${notice.noticeNo }번 글 상세보기</h1>
	<br><br>
	<table align="center" width="800" border="1">
		<tr>
			<td width="100">번호</td>
			<td>${notice.noticeNo }</td>
		</tr>
		<tr>
			<td>제목</td>
			<td>${notice.noticeTitle }</td>
		</tr>
		<tr>
			<td>작성날짜</td>
			<td>${notice.nCreateDate }</td>
		</tr>
		<tr>
			<td>작성자</td>
			<td>${notice.noticeWriter }</td>
		</tr>
		<tr height="300">
			<td>내용</td>
			<td>${notice.noticeContents }</td>
		</tr>
		<tr>
			<td>첨부파일</td>
			<td>${notice.noticeFilePath }</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<c:url var="nModify" value="noticeModifyView.kh">
					<c:param name="noticeNo" value="${notice.noticeNo }"></c:param>
				</c:url>
				<c:url var="nDelete" value="noticeDelete.kh">
					<c:param name="noticeNo" value="${notice.noticeNo }"></c:param>
				</c:url>
				<a href="${nModify }">수정페이지로 이동</a>
				<a href="${nDelete }">삭제</a>
			</td>
		</tr>
	</table>
</body>
</html>