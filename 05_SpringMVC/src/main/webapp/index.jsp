<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 로그인</title>
</head>
<body>
	<fieldset>
		<legend>로그인</legend>
		<form action="/login.do" method="post">
			ID : <input type = "text" name="user-id">
			PW : <input type = "password" name="user-pw">
			<input type="submit" value="로그인">
		</form>
	</fieldset>
</body>
</html>