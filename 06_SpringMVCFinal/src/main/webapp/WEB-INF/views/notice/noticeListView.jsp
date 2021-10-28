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
	<table align="center" width="600" border="1" cellspacing="0" style="clear:right;">
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>올린날짜</th>
			<th>첨부파일</th>
		</tr>
		<c:if test="${empty nList }">
			<tr><td colspan="5" align="center">조회된 정보가 없습니다.</td></tr>
		</c:if>
		<c:if test="${!empty nList }">
		<c:forEach items="${nList }" var="nList">
			<tr>
				<td align="center">${nList.noticeNo }</td>
				<td align="center">
					<c:url var="nDetail" value="noticeDetail.kh">
						<c:param name="noticeNo" value="${nList.noticeNo }"></c:param>
					</c:url>
					<a href="${nDetail }">${nList.noticeTitle }</a>
				</td>
				<td align="center">${nList.noticeWriter }</td>
				<td align="center">${nList.nCreateDate }</td>
				<td align="center">
					<c:if test="${!empty nList.noticeFilePath }">O</c:if>
					<c:if test="${empty nList.noticeFilePath }">X</c:if>
				</td>
			</tr>
		</c:forEach>
		</c:if>
	</table>
	<div align="center">
		<form action="noticeSearch.kh"method="get">
			<select name="searchCondition">
				<option value="all" <c:if test="${search.searchCondition eq 'all' }">selected</c:if>>전체</option>
				<option value="writer" <c:if test="${search.searchCondition eq 'writer' }">selected</c:if>>작성자</option>
				<option value="title" <c:if test="${search.searchCondition eq 'title' }">selected</c:if>>제목</option>
				<option value="contents" <c:if test="${search.searchCondition eq 'contents' }">selected</c:if>>내용</option>
			</select>
			<input type="text" name="searchValue" value="${search.searchValue }">
			<input type="submit" value="검색">
		</form>
	</div>
</body>
</html>