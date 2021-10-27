<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<script
	src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<style>
.centerText {
	margin: auto 0;
}

.guide {
	display: none;
	font-size: 12px;
	top: 12px;
	right: 10px;
}

#ok {
	color: blue
}

#no {
	color: red
}
</style>
</head>
<body>
	<jsp:include page="../common/menubar.jsp"></jsp:include>
	<h1 align="center">회원가입</h1>
	<div class="centerText">
		<form action="memberRegister.kh" method="post">
			<table align="center" width="650" cellspacing="5">
				<tr>
					<td>* 아이디</td>
					<td><input type="text" name="memberId" id="memberId">
						<span class="guide" id="ok">이 아이디는 사용 가능합니다</span> <span
						class="guide" id="no">이 아이디는 사용할 수 없습니다.</span></td>
				</tr>
				<tr>
					<td>* 비밀번호</td>
					<td><input type="password" name="memberPwd"></td>
				</tr>
				<tr>
					<td>* 이름</td>
					<td><input type="text" name="memberName"></td>
				</tr>
				<tr>
					<td>* 이메일</td>
					<td><input type="text" name="memberEmail"></td>
				</tr>
				<tr>
					<td>* 전화번호</td>
					<td><input type="text" name="memberPhone"></td>
				</tr>
				<tr>
					<td>우편번호</td>
					<td><input type="text" name="post"
						class="postcodify_postcode5" size="6">
						<button type="button" id="postcodify_search_button">검색</button></td>
				</tr>
				<tr>
					<td>도로명 주소</td>
					<td><input type="text" name="address1"
						class="postcodify_address"></td>
				</tr>
				<tr>
					<td>상세주소</td>
					<td><input type="text" name="address2"
						class="postcodify_extra_inf"></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input type="submit"
						value="가입하기"></td>
				</tr>
			</table>
		</form>
	</div>
	<script src="//d1p7wdleee1q2z.cloudfront.net/post/search.min.js"></script>
	<script>
		$(function() {
			$("#postcodify_search_button").postcodifyPopUp();
		});

		$("#memberId").on("blur", function() {
			var memberId = $("#memberId").val();
			$.ajax({
				url : "checkDupId.kh",
				data : {
					"memberId" : memberId
				},
				success : function(result) {
					//                console.log(result);
					if (result != 0) {
						$("#ok").hide();
						$("#no").show();
					} else {
						$("#ok").show();
						$("#no").hide();
					}
				},
				error : function() {
					alert("ajax 전송 실패! 관리자에게 문의하세요")
				}
			});
		});
	</script>
</body>
</html>