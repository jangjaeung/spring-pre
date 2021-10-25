<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="resources/css/menubar-style.css" rel="stylesheet">
</head>
<body>
	<h1 align="center">회원가입</h1>
	<form action="memberRegister.kh" method="post" >
		<table align="center">
			<tr>
				<td>* 아이디 : </td>
				<td><input type="text" name="user-id"></td>
			</tr>
			<tr>
				<td>* 비밀번호 : </td>
				<td><input type="password" name="user-pwd"></td>
			</tr>
			<tr>
				<td>* 이름 : </td>
				<td><input type="text" name="user-name"></td>
			</tr>
			<tr>
				<td>* 이메일 : </td>
				<td><input type="text" name="user-email"></td>
			</tr>
			<tr>
				<td>* 전화번호 : </td>
				<td><input type="text" name="user-phone"></td>
			</tr>
			<tr>
				<td>* 주소 : </td>
				<td><input type="text" name="user-addr"></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="가입하기">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>