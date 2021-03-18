<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.servletContext.contextPath }" scope="application"></c:set>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>댓글</title>
    <!-- jQuery -->
    <script src="http://code.jquery.com/jquery-3.5.1.min.js" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>
    
    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">

    <!-- Bootstrap core JS -->
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>
  <link rel="stylesheet" href="${contextPath}/resources/css/comment.css">
   <style>
   #updateComment, #deleteComment, #addComment , .alert-danger {
   display: inline-block;
    background-color: #EE9BA3;
    color: white;
    border: none;
   }
  
   </style>
</head>



<div id="comment-area ">
	<!-- 댓글 작성 부분 -->
	<div class="commentWrite">
		<table align="center">
			<tr>
				<td id="commentContentArea">
					<textArea rows="3" id="commentContent"></textArea>
				</td>
				<td id="commentBtnArea">
					<button class="btn" id="addComment">
						댓글<br>등록
					</button>
				</td>
			</tr>
		</table>
	</div>


	<!-- 댓글 출력 부분 -->
	<div class="commentList mt-5 pt-2">
		<ul id="commentListArea">
			
			<!-- 로그인 x 또는 댓글 작성자가 아닌 회원의 화면 -->
			<li class="comment-row">
				<div>
					<p class="cWriter">작성자</p>
					<p class="cDate">작성일 : 2021.01.11 10:30</p>
				</div>
				
				<p class="cContent">댓글 내용1</p>
			</li>

			
			<!-- 로그인한 회원이 댓글 작성자인 경우 -->
			<li class="comment-row">
				<div>
					<p class="cWriter">작성자</p>
					<p class="cDate">작성일 : 2021.01.11 10:30</p>
				</div>

				<p class="cContent">댓글 내용2</p>
				
				<div class="commentBtnArea">
					<button class="btn btn-sm ml-1 alert-danger" id="updateComment" onclick="showUpdateComment(2, this)">수정</button>
					<button class="btn btn-sm ml-1 alert-danger" id="deleteComment" onclick="deleteComment(2)">삭제</button>
				</div>
			</li>
	
		</ul>
	</div>


</div>

<script type="text/javascript">
var loginMemberNick = "${loginMember.memNick}";
var loginMemberId = "${loginMember.memId}";
var boardNo = ${board.boardNo};

//페이지 로딩 완료 시 댓글 목록 호출
$(function(){
selectCommentList();
});


//해당 게시글 댓글 목록 조회 함수(ajax)
function selectCommentList(){
$.ajax({
 url : "${contextPath}/comment/selectList.do", 
 data : {"boardNo" : boardNo },
 type : "post",
 dataType : "JSON",
 success : function(cList){
	 console.log(cList);
	 
	 $("#commentListArea").html("");
	 
	 $.each(cList, function(index, item){
		 var li = $("<li>").addClass("comment-row");
		 var cWriter = $("<p>").addClass("cWriter").text(item.memNick); 
		 var cDate = $("<p>").addClass("cDate").text(item.createDt);
		 
		 var div = $("<div>");
		 div.append(cWriter).append(cDate);
		 var cContent = $("<p>").addClass("cContent").html(item.content);
		 li.append(div).append(cContent);
		 
			
			// 현재 댓글의 작성자와 로그인한 멤버의 닉네임이 같을 때 버튼 추가
			if(item.memNick == loginMemberNick){
				
				// 수정, 삭제 버튼 영역
					var commentBtnArea = $("<div>").addClass("commentBtnArea");
				
				// ** 추가되는 댓글에 onclick 이벤트를 부여하여 버튼 클릭 시 수정, 삭제를 수행할 수 있는 함수를 이벤트 핸들러로 추가함. 
				var showUpdate = $("<button>").addClass("btn alert-danger btn-sm ml-1").text("수정").attr("onclick", "showUpdateComment("+item.commentNo+", this)");
				var deleteComment = $("<button>").addClass("btn alert-danger btn-sm ml-1").text("삭제").attr("onclick", "deleteComment("+item.commentNo+")");
				
				commentBtnArea.append(showUpdate).append(deleteComment);
				
				li.append(commentBtnArea);
			}

		 
		 $("#commentListArea").append(li);
	 });
 },
 error : function(){
	 console.log("댓글 목록 조회 실패");
 }
 });
}

//-----------------------------------------------------------------------------------------
//댓글 등록 (ajax)
$("#addComment").on("click", function(){

// 댓글 내용을 얻어와서 변수에 저장
var commentContent = $("#commentContent").val().trim();

// 로그인이 되어있지 않은 경우 == loginMemberId 전역변수에 저장된 값이 "" 일 경우
if(loginMemberId == ""){
  alert("로그인 후 이용해 주세요.");
  
}else{ // 로그인이 되어있는 경우
  // 댓글 내용이 작성되어 있는지 확인
  if(commentContent.length == 0){
     alert("댓글 작성 후 클릭해주세요.");      
  }

  else{ // 로그인도 되어있고, 댓글도 작성되어 있는 경우
     //회원번호를 얻어와서 문자열 변수에 저장
     var commentWriter = "${loginMember.memNick}";
     
     $.ajax({
        url : "${contextPath}/comment/insertComment.do",
        data : { "commentWriter" : commentWriter,/* 회원닉네임 */
                     "commentContent" : commentContent, /* 댓글내용 */
                     "boardNo" : boardNo}, /* 글번호 */
        type : "post",
        success : function(result){
           
           if(result>0){   // 댓글 삽입 성공 시
              // 댓글 작성 내용 삭제
              $("#commentContent").val("");
           
              // 성공 메세지 출력 
              swal({"icon" : "success", "title" : "댓글 등록 성공"});
              
              // 댓글 목록을 다시 조회 -> 새로 삽입한 댓글도 조회하여 화면에 출력
              selectCommentList();
           }
           
        }, error: function(){
           console.log("댓글 등록 실패")
        }
        
     });
     
  }

}
});


//-----------------------------------------------------------------------------------------


var beforeCommentRow;

//댓글 수정 폼 출력 함수
function showUpdateComment(commentNo, el){
	
	//console.log($(".commentUpdateContent").length);
	
	// 이미 열려있는 댓글 수정 창이 있을 경우 닫아주기
	if($(".commentUpdateContent").length > 0){
		$(".commentUpdateContent").eq(0).parent().html(beforeCommentRow);
	} //**모르겟음..
		
	
	// 댓글 수정화면 출력 전 요소를 저장해둠.
	beforeCommentRow = $(el).parent().parent().html();
	//console.log(beforecommentRow);
	
	
	// 작성되어있던 내용(수정 전 댓글 내용) 
	var beforeContent = $(el).parent().prev().html();
	
	
	// 이전 댓글 내용의 크로스사이트 스크립트 처리 해제, 개행문자 변경
	// -> 자바스크립트에는 replaceAll() 메소드가 없으므로 정규 표현식을 이용하여 변경
	beforeContent = beforeContent.replace(/&amp;/g, "&");	
	beforeContent = beforeContent.replace(/&lt;/g, "<");	
	beforeContent = beforeContent.replace(/&gt;/g, ">");	
	beforeContent = beforeContent.replace(/&quot;/g, "\"");	
	
	beforeContent = beforeContent.replace(/<br>/g, "\n");	
	//console.log(beforeContent)
	
	
	// 기존 댓글 영역을 삭제하고 textarea를 추가 
	$(el).parent().prev().remove();
	var textarea = $("<textarea>").addClass("commentUpdateContent").attr("rows", "3").val(beforeContent).css("width","97%").css("resize", "none").css("margin","10px");
	$(el).parent().before(textarea);
	
	//console.log(commentBtnArea);
	
	
	// 수정 버튼
	var updateComment = $("<button>").addClass("btn alert-danger btn-sm ml-1 mb-4").text("댓글 수정").attr("onclick", "updateComment(" + commentNo + ", this)");
	
	// 취소 버튼
	var cancelBtn = $("<button>").addClass("btn alert-danger btn-sm ml-1 mb-4").text("취소").attr("onclick", "updateCancel(this)");
	
	var commentBtnArea = $(el).parent();
	
	$(commentBtnArea).empty(); 
	$(commentBtnArea).append(updateComment); 
	$(commentBtnArea).append(cancelBtn); 
	
	
}

//-----------------------------------------------------------------------------------------


//댓글 수정 함수
function updateComment(commentNo, el){
//수정된 댓글 내용
var commentContent = $(el).parent().prev().val();

$.ajax({
		url : "${contextPath}/comment/updateComment.do",
	type : "post",
	data : {"commentNo" : commentNo, "commentContent" : commentContent},
	success : function(result){
		if(result > 0){
			selectCommentList(boardNo);
			
			swal({"icon" : "success" , "title" : "댓글 수정 성공"});
		}
		
	}, error : function(){
		console.log("댓글 수정 실패");
	}		
});



}

//-----------------------------------------------------------------------------------------


//댓글 수정 취소 시 원래대로 돌아가는 함수
function updateCancel(el){
console.log(beforeCommentRow);
$(el).parent().parent().html(beforeCommentRow);

}



//-----------------------------------------------------------------------------------------

//댓글 삭제 함수
function deleteComment(commentNo){
if(confirm("정말로 삭제하시겠습니까?")){
	var url = "${contextPath}/comment/deleteComment.do";
	
	$.ajax({
		url : url,
		data : {"commentNo" : commentNo},
		success : function(result){
			if(result > 0){
				selectCommentList(boardNo);  // 댓글 목록을 다시 읽어옴.
				
				swal({"icon" : "success" , "title" : "댓글 삭제 성공"});
			}
			
			
		}, error : function(){
			console.log("ajax 통신 실패");
		}
	});
}

}


</script>
   
    
</body>
</html>