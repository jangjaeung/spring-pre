<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지</title>
</head>
<body>
<jsp:include page="../common/menubar.jsp"></jsp:include>
	<h1 align="center">마이페이지 - 정보수정</h1>
	<div class="centerText">
		<form action="memberModify.kh" method="post" >
			<table align="center" width="650" cellspacing="5">
				<tr>
					<td>* 아이디 : </td>
					<td><input type="text" name="memberId"  value="${loginUser.memberId }" readonly></td>
				</tr>
				<tr>
					<td>* 비밀번호 : </td>
					<td><input type="password" name="memberPwd" value="${loginUser.memberPwd }"></td>
				</tr>
				<tr>
					<td>* 이름 : </td>
					<td><input type="text" name="memberName" value="${loginUser.memberName }" readonly></td>
				</tr>
				<tr>
					<td>* 이메일 : </td>
					<td><input type="text" name="memberEmail" value="${loginUser.memberEmail }"></td>
				</tr>
				<tr>
					<td>* 전화번호 : </td>
					<td><input type="text" name="memberPhone" value="${loginUser.memberPhone }"></td>
				</tr>
				<!-- 주소는 회원가입시 주소api를 이용하여 ,기준 3값을 받았으니 split -->
				<!-- delims -> 무엇을 기준으로 var->어떤 변수명으로 -->
				<c:forTokens items="${loginUser.memberAddr }" delims="," var="addr" varStatus="status">
				<c:if test="${status.index eq 0 }"><!-- 자른 문자열이 3개 0번인덱스면 우편번호 1은 도로명 2는 상세 -->
				<tr>
					<td>우편번호 : </td>
					<td>
						<input type="text" name="post" class="postcodify_postcode5" size="6" value="${addr }">
						<button type="button" id="postcodify_search_button">검색</button>
						<!-- 버튼 타입="버튼" 하면 이벤트 전달 안됨 버튼 타입 안적으면 이벤트 타입 전달되어 섭밋이 눌림 -->
					</td>
				</tr>
				</c:if>
				<c:if test="${status.index eq 1 }">
				<tr>
					<td>도로명 주소 : </td>
					<td>
						<input type="text" name="address1" class="postcodify_address" value="${addr }">
					</td>
				</tr>
				</c:if>
				<c:if test="${status.index eq 2 }">
				<tr>
					<td>상세 주소 : </td>
					<td>
						<input type="text" name="address2" class="postcodify_extra_info" value="${addr }">
					</td>
				</tr>
				</c:if>
				</c:forTokens>
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="수정하기">
						<button type="button" onclick="location.href='memberDelete.kh?memberId=${loginUser.memberId }';">탈퇴하기</button>
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