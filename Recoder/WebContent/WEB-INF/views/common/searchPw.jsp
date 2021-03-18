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
                  <input id="email"name="email" type="email" placeholder="이메일 주소" class="userInfo emailAdr" />
                 <button id="sendEmail" type="button" class="btn small" >인증번호 받기</button>
                  <input id="code" name="code" type="text" placeholder="인증번호 6자리 숫자입력"  class="userInfo" >
                  <button id="nextPw" class="btn" type="submit">확인</button>
               </form>

      </div>
      <div class="footer"></div>
   </div>
   
   <script>
     $(document).ready(function(){
     $(".container").fadeIn(1000);
	});
  	function emailVal(){
   		 var regExp = /^[\w]{4,}@[\w]+(\.[\w]+){1,3}$/; // 4글자 아무단어 @ 아무단어 . * 3
   		    
   		    var value = $("#email").val();
   		    if(!regExp.test(value)){
   		        return false;
   		    }else{
   		        return true;
   		    }
    	 }
   	
       //이메일 인증 보내기
      $("#sendEmail").on("click", function(){
    	  if(emailVal()){
    		  
	    	  swal("이메일 보내는중...");
	    	    var value = $("#email").val();
	    	    
	    	  	$.ajax({
	    		 url: "sendEmail.do",
	    		 data: {"email": value},
	 			type: "post",
	 			success: function(randomNum){
	 				if(randomNum != null){ // 이메일 보내기 성공한 경우
	 					swal({
	 						icon:"success",
	 						title:"성공",
	 						text: "입력하신 주소로 이메일을 보냈습니다."
	 					});
	 					sessionStorage.setItem("randomNum", randomNum); 
	                }
	 			},
	 			error: function(){
	 				console.log("이메일 전송 실패");
	 			}
	   	    
	    	  }); 
    	  }else{
    		  swal("유효하지 않은 이메일 형식입니다.");
    	  }
    	  
      });
       
     //검사
     var validateCheck = {
    "username" : false,
    "userid" : false,
    "email" : false,
    "code" : false,
    "confirm" : false
	}
     
     
     
    function PwValidate(){
    	 
    	 var username = $("#username").val();
    	 var userid = $("#userid").val();
    	 var email = $("#email").val();
    	 var code = $("#code").val();
    	 var randomNum = sessionStorage.getItem("randomNum");
    	 
    	 
    	 //닉네임 미입력시
    	if($.trim(username)==""){
    		
    		validateCheck.username = false;
    	}else{
    		validateCheck.username = true;

    	}
    	 
    	 //아이디 미입력
    	if($.trim(userid)==""){
    		validateCheck.userid = false;
    	}else{
    		validateCheck.userid = true;
    	}
    	
    	 //이메일 미입력
    	if($.trim(email)==""){
    		validateCheck.email = false;
    	}else{
    		validateCheck.email = true;
    	}
    	
    	//코드 미입력
    	if($.trim(code)==""){
    		validateCheck.code = false;
    	}
    	
    	//인증번호 확인
    	
    	if(code == randomNum){
    		
    		validateCheck.confirm = true;
    		validateCheck.code = true;
    		sessionStorage.removeItem("randomNum");
    	} 
	 	
	 	
    	
    	for(var key in validateCheck){
    		
            if(!validateCheck[key]){
                var msg;
                switch(key){
                    case "username" : msg = "닉네임을 입력해주세요"; break;
                    case "userid": msg = "아이디을 입력해주세요"; break;
                    case "email": msg = "이메일을 입력해주세요 "; break;
                    case "code": msg = "코드을 입력해주세요"; break;
                    case "confirm": msg = "인증번호가 맞지 않습니다 "; break;
                }
                swal(msg);

                $("#"+key).focus();

                return false; 
            }
        }
    	 
    }	  
</script> 
     
      
  
</body>
</html>