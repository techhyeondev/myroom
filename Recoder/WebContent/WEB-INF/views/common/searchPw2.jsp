<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.servletContext.contextPath }" scope="application"></c:set>   
 
 <!DOCTYPE html>
<html lang="en">
<head>
   <meta charset="UTF-8">
   <meta name="viewport" content="width=device-width, initial-scale=1.0">
   <title>비밀번호 찾기</title>
   <link rel="stylesheet" href="${contextPath}/resources/css/searchId.css">
   
<!-- sweetalert : alert창을 꾸밀 수 있게 해주는 라이브러리 https://sweetalert.js.org/ -->
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
   
   <!-- jQuery -->
   <script src="http://code.jquery.com/jquery-3.5.1.min.js" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>
    <style type="text/css">
   #sendEmail:hover{
   cursor: pointer;}
    </style>
</head>
<body>
<c:if test="${!empty sessionScope.swalTitle }">
		<script>
			
			swal({
				icon:"${swalIcon}",
				title:"${swalTitle}",
				text: "${swalText}"
			});
		</script>
		
		<%-- 2) 한번 출력한 메세지를 Session에서 삭제 --%>
		<c:remove var="swalIcon"/>
		<c:remove var="swalTitle"/>
		<c:remove var="swalText"/>
	
	</c:if>
    <div class="container">
       <div class="c1">
        
          <div id="left" class="left_hover"><h3 class="s1class" ><span>비밀번호</span><span class="su">찾기</span>
          </h3></div>
          <div id="right"><a id="searchIdForm" href="${contextPath}/common/searchIdForm.do"><h3 class="s2class"><span>아이디</span><span class="su">찾기</span></h3></a></div>
       </div>
       <div class="c2">
        <form class="searchPw" method="post" action="${contextPath}/common/searchPw.do"  onsubmit="return PwValidate();">
                <h1 class="search1">비밀번호 찾기</h1>
                <div class="txt">
                   <h3>내정보에 등록된 이메일로 찾기
                     </h3>
                      가입하신 이메일을 입력해주세요 <br>
                   <br>
                  </div>
                  <input id="username"name="username" type="text" placeholder="닉네임" class="userInfo"/> <br>
                  <input id="userid"name="userid" type="text" placeholder="아이디" class="userInfo">
                  <button id="button">Open Modal</button>
  
					<!-- 모달창 -->
					<div id="modal">
					    <h3>인증번호 입력</h3>
					    <input name="email" id="email" type="email" placeholder="이메일 주소" class="userInfo emailAdr" />
                 		<button type="button" class="btn small" id="sendEmail" >인증번호 받기</button>
                  		<input name="code" type="text" placeholder="인증번호 6자리 숫자입력"  class="userInfo" >
                 
					    <button id="confirm_button">확인</button>
					    <button class="js_close">닫기</button>
					</div>
					
                  <input name="email" id="email" type="email" placeholder="이메일 주소" class="userInfo emailAdr" />
                 <button type="button" class="btn small" id="sendEmail" >인증번호 받기</button>
                  <input name="code" type="text" placeholder="인증번호 6자리 숫자입력"  class="userInfo" >
                  <button class="btn" id="nextPw" type="submit">확인</button>
               </form>

      </div>
      <div class="footer"></div>
   </div>
   
   <script>
     $(document).ready(function(){
     $(".container").fadeIn(1000);
	});
   
       //이메일 인증 보내기
      $("#sendEmail").on("click", function(){
    	  
    	    var value = $("#email").val();
    	    
    	  	$.ajax({
    		 url: "sendEmail.do",
    		 data: {"email": value},
 			type: "post",
 			success: function(num){
 				if(num == 1){ // 이메일 보내기 성공한 경우
 					swal("입력하신 주소로 이메일을 보냈습니다.");
 					colsole.log("랜덤번호:"+num);
                }
 			},
 			error: function(){
 				console.log("이메일 전송 실패");
 			}
   	    
    	  }); 
      });  
    	  
</script> 
     
      
  
</body>
</html>