<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 수정</title>
</head>
<body>
<jsp:include page="../common/menubar.jsp"></jsp:include>
	<br style="clear:both">
	<h1 align="center">공지글 수정하기</h1>
	<br><br>
	<form action="noticeUpdate.kh" method="POST" encType="multipart/form-data">
		<input type="hidden" name="noticeNo" value="${notice.noticeNo }">
		<input type="hidden" name="noticeFilePath" value="${notice.noticeFilePath }">
		<table align="center" border="1">
			<tr>
				<td>제목</td>
				<td><input type="text" size="50" name="noticeTitle" value="${notice.noticeTitle }"></td>
			</tr>
			<tr>
				<td>작성자</td>
				<td><input type="text" size="50" name="noticeWriter" value="${notice.noticeWriter }" readonly></td>
			</tr>
			<tr>
				<td>내용</td>
				<td><textarea rows="7" cols="50" name="noticeContents">${notice.noticeContents }</textarea></td>
			</tr>
			<tr>
				<td>첨부파일</td>
				<td><input type="file" name="reloadFile">${notice.noticeFilePath }</td>
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