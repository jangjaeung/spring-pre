<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 작성</title>
</head>
<body>
	<jsp:include page="../common/menubar.jsp"></jsp:include>
	<br style="clear:both">
	<h1 align="center">공지글 등록페이지</h1>
	<br><br>
	<form action="noticeRegister.kh" method="post" enctype="multipart/form-data"><!-- enctype은 첨부파일용 -->
		<table align="center" border="1" cellspacing="0">
			<tr>
				<td>제목</td>
				<td><input type="text" size="50" name="noticeTitle"></td>
			</tr>
			<tr>
				<td>작성자</td>
				<td><input type="text" size="50" name="noticeWriter" value="${loginUser.memberId }" readOnly></td>
			</tr>
			<tr>
				<td>내용</td>
				<td><textarea rows="7" cols="50" name="noticeContents"></textarea></td>
			</tr>
			<tr>
				<td>파일</td>
				<td><input type="file" size="50" name="uploadFile"></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="등록">
					<input type="reset" value="취소">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>