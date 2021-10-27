<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지 리스트</title>
</head>
<body>
	<jsp:include page="../common/menubar.jsp"></jsp:include>
	<br style="clear:both">
	<h1 align="center">공지글 목록보기</h1>
	<div><button onclick="location.href='noticeWriteView.kh'">글쓰기</button></div>
	<br style="clear:both">
	<table>
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>올린날짜</th>
			<th>첨부파일</th>
		</tr>
		<c:forEach items="${nList }" var="nList">
			<tr>
				<td>${nList.noticeNo }</td>
				<td>${nList.noticeTitle }</td>
				<td>${nList.noticeWriter }</td>
				<td>${nList.nCreateDate }</td>
				<td>o</td>
			</tr>
		</c:forEach>
	</table>
	<br><br>
</body>
</html>