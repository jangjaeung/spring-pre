<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 수정</title>
</head>
<body>
	<jsp:include page="../common/menubar.jsp"></jsp:include>
	<br style="clear:both">
	<h1 align="center">${board.boardNo }번 글 상세보기</h1>
	<br><br>
	<form action="boardUpdate.kh" method="post" enctype="multipart/form-data">
		<input type="hidden" name="boardNo" value="${board.boardNo }">
		<input type="hidden" name="boardFileName" value="${board.boardFileName }">
		<input type="hidden" name="boardFileRename" value="${board.boardFileRename }">
		<table align="center" width="800" border="1">
			<tr>
				<td width="100">제목</td>
				<td>
					<input type="text" name="boardTitle" size="50" value="${board.boardTitle }">
				</td>
			</tr>
			<tr>
				<td>작성자</td>
				<td><input type="text" name="boardWriter" value="${board.boardWriter }" readonly></td>
			</tr>
			<tr height="300">
				<td>내용</td>
				<td><textarea rows="7" cols="50" name="boardContents">${board.boardContents }</textarea></td>
			</tr>
			<tr>
				<td>첨부파일</td>
				<td>
					<input type="file" name="reloadFile">
					${board.boardFileName }
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="수정하기">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>