<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 상세</title>
</head>
<body>
	<jsp:include page="../common/menubar.jsp"></jsp:include>
	<br style="clear:both">
	<script type="text/javascript">
	$(function(){
        getReplyList();
        $("#rSubmit").on("click", function(){
//            var boardNo = $("#boardNo").val();
           var boardNo = '${board.boardNo}';
           var rContents = $("#rContents").val();
           $.ajax({
              url : "addReply.kh",
              type : "post",
              data : {
                 "refBoardNo" : boardNo,
                 "replyContents" : rContents
                 },
              success : function(data) {
                 if(data == "success"){
                    // 댓글 불러오기
                    getReplyList();
                    // 작성 후 내용 초기화
                    $("#rContents").val("");
                 }else{
                    alert("댓글 등록 실패")
                 }
              },
              error : function(){
                 alert("AJAX 통신 오류..")
              },
              complete : function(){
                 
              }
           });
        });
     });
  
     function getReplyList() {
        var boardNo = '${board.boardNo}';
        $.ajax({
           url : "replyList.kh",
           type : "get",
           data : {"boardNo" : boardNo},
           dataType : "json",
           //[{},{},{}] json배열 형태로 데이터가 넘어올것 그래서 데이터타입이 결과값을 받을때 json니까 써줌
           success : function(data){
              var $tableBody = $("#rtb tbody");
              $tableBody.html("");
              var $tr;
              var $rWriter;
              var $rContents;
              var $rCreateDate;
              var $btnArea;
              $("#rCount").text("댓글 (" + data.length + ")"); // 댓글 갯수
              if(data.length > 0){ // date.length는 댓글의 갯수
                 for(var i in data){
                    $tr = $("<tr id='modifyTr'>");
                    $rWriter = $("<td width='100'>").text(data[i].replyWriter);
                    $rContent = $("<td>").text(data[i].replyContents);
                    $rCreateDate = $("<td width='100'>").text(data[i].rCreateDate);
                    $btnArea = $("<td width='80'>")
                    .append("<a href ='#' onclick='modifyReply(this,"+boardNo+","+data[i].replyNo+",\""+data[i].replyContents+"\");'>수정/<a> ")
                    .append("<a href ='#' onclick='removeReply("+boardNo+","+data[i].replyNo+")'>삭제<a>");
                    // replyContents는 스트링이기 때문에 이스케이프문자인 \ 를 통해 스트링인걸 나타냄
                    $tr.append($rWriter);
                    $tr.append($rContent);
                    $tr.append($rCreateDate);
                    $tr.append($btnArea);
                    $tableBody.append($tr);
                 }
              }
           },
           error : function(){
              alert("AJAX 댓글 통신 오류")
           }
        });
     }
      //위에 onclick모디파이에 this를 추가함 this는 a링크 자체를 가르킴
     function modifyReply(obj, boardNo, replyNo, replyContents){
        $trModify = $("<tr>");
        $trModify
        .append("<td colspan='3'><input type='text' id='modifyReply' size='50' value='"+replyContents+"'></td>");
        $trModify
        .append("<td><button onclick='modifyReplyCommit("+boardNo+","+replyNo+")'>수정완료</button></td>");
        //obj는 넘어온 this이므로 부모로 두번가서 수정창을 띄워야함 this=obj고 obj는 a를 가르키기 때문에
           //a에서 td로 td에서 tr로 가야 내가 누른 수정버튼 다음에 수정창이 뜸
        $(obj).parent().parent().after($trModify);
     }
     
     
  function modifyReplyCommit(boardNo, replyNo){
     var modifiedContent = $("#modifyReply").val();
     $.ajax({
        url : "modifyReply.kh",
        type : "post",
        data : {
           "refBoardNo" : boardNo,
           "replyNo" : replyNo,
           "replyContents" : modifiedContent
        },
        success : function(data){
           if(data == "success"){
              getReplyList();
           }else{
              alert("댓글 수정 실패");
           }
        },
        error : function(){
           alert("Ajax 통신 실패");
        }
     });
  }   
     function removeReply(boardNo, replyNo){
        $.ajax({
           url : "deleteReply.kh",
           type : "get",
           data : {"refBoardNo" : boardNo, "replyNo" : replyNo},
           success : function(data) {
              if(data == "success"){
                 getReplyList();
              }else{
                 alert("댓글 삭제 실패");
              }
           }
        })
     }
	</script>
	<h1 align="center">${board.boardNo }번 글 상세보기</h1>
	<br><br>
	<input type ="hidden" value="${board.boardNo }" id="boardNo">
	<table align="center" width="800" border="1">
		<tr>
			<td width="100">제목</td>
			<td>${board.boardTitle }</td>
		</tr>
		<tr>
			<td>작성자</td>
			<td>${board.boardWriter }</td>
		</tr>
		<tr>
			<td>작성날짜</td>
			<td>${board.bCreateDate }</td>
		</tr>
		<tr>
			<td>조회수</td>
			<td>${board.boardCount }</td>
		</tr>
		<tr height="300">
			<td>내용</td>
			<td>${board.boardContents }</td>
		</tr>
		<tr>
			<td>첨부파일</td>
			<td>${board.boardFileName }</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<c:url var="bModify" value="boardModifyView.kh">
					<c:param name="boardNo" value="${board.boardNo }"></c:param>
				</c:url>
				<c:url var="bDelete" value="boardDelete.kh">
					<c:param name="boardNo" value="${board.boardNo }"></c:param>
					<c:param name="fileName" value="${board.boardFileRename }"></c:param>
				</c:url>
				<a href="${bModify }">수정페이지로 이동</a>
				<a href="${bDelete }">삭제</a>
			</td>
		</tr>
	</table>
	
	<!-- 댓글 등록 -->
	<table align="center" width="800" border="1">
		<tr>
			<td>
				<textarea rows="3" cols="55" id="rContents"></textarea>
			</td>
			<td><button id="rSubmit">등록하기</button>
		</tr>
	</table>
	
	<!--  댓글 목록 -->
	<table align="center" width="800" border="1" id="rtb">
		<thead>
			<tr>
				<!-- 댓글 갯수 -->
				<td colspan="4"><b id="rCount"></b></td>
			</tr>
		</thead>
		<tbody>
		
		</tbody>
	</table>
	

</body>
</html>