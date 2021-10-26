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
	<jsp:include page="../common/menubar.jsp"></jsp:include>
	<h1 align="center">회원가입</h1>
	<div class="centerText">
		<form action="memberRegister.kh" method="post" >
			<table align="center" width="650" cellspacing="5">
				<tr>
					<td>* 아이디 : </td>
					<td><input type="text" name="memberId"></td>
				</tr>
				<tr>
					<td>* 비밀번호 : </td>
					<td><input type="password" name="memberPwd"></td>
				</tr>
				<tr>
					<td>* 이름 : </td>
					<td><input type="text" name="memberName"></td>
				</tr>
				<tr>
					<td>* 이메일 : </td>
					<td><input type="text" name="memberEmail"></td>
				</tr>
				<tr>
					<td>* 전화번호 : </td>
					<td><input type="text" name="memberPhone"></td>
				</tr>
				<tr>
					<td>우편번호 : </td>
					<td>
						<input type="text" name="post" class="postcodify_postcode5" size="6">
						<button type="button" id="postcodify_search_button">검색</button>
						<!-- 버튼 타입="버튼" 하면 이벤트 전달 안됨 버튼 타입 안적으면 이벤트 타입 전달되어 섭밋이 눌림 -->
					</td>
				</tr>
				<tr>
					<td>도로명 주소 : </td>
					<td>
						<input type="text" name="address1" class="postcodify_address">
					</td>
				</tr>
				<tr>
					<td>상세 주소 : </td>
					<td>
						<input type="text" name="address2" class="postcodify_extra_info">
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="가입하기">
					</td>
				</tr>
			</table>
		</form>
	</div>
	<script src="//d1p7wdleee1q2z.cloudfront.net/post/search.min.js"></script>
	<script>
	//window.onload = function(){}; 윈도우 객체 사용 
	//$(document).ready(function(){}); 제이쿼리 이용
		$(function() {
			$("#postcodify_search_button").postcodifyPopUp(); 
		});
	</script>
</body>
</html>