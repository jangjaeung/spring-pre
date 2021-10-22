<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="resources/css/member-style.css" rel="stylesheet">
</head>
<body>
   <h1 align="center">Welcome to our WebSite!</h1>
   <div class="login-area">
		<c:if test="${empty sessionScope.loginUser }">
      <form action="/login.kh" method="post">
         <table align="right">
            <tr>
               <td>아이디 : </td>
               <td><input type="text" name="user-id"></td>
               <td rowspan="2">
               		<input type="submit" value="로그인">
               </td>
            </tr>
            <tr>
               <td>비밀번호 : </td>
               <td><input type="password" name="user-pwd"></td>
            </tr>
            <tr>
               <td colspan="3">
                  <a href="#">회원가입</a> <a href="#">아이디/비밀번호 찾기</a>
               </td>
            </tr>
         </table>
      </form>
      </c:if>
      <c:if test="${!empty loginUser }">
      	<table align="right">
      		<tr>
      			<td colspan="2">${loginUser.memberName }님 환영합니다.</td>
      		</tr>
      		<tr>
      			<td><button>정보수정</button></td>
      			<td><button onclick="location.href='/logout.kh';">로그아웃</button></td>
      		</tr>
      	</table>
      </c:if>
   </div>
   <div class="nav-area">
      <div class="menu">Home</div>
      <div class="menu">공지사항</div>
      <div class="menu">자유게시판</div>
      <div class="menu">ETC</div>
   </div>
</body>
</html>