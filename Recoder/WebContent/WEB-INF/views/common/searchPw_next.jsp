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
   <!-- jQuery -->
   <script src="http://code.jquery.com/jquery-3.5.1.min.js" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>
    
<!-- sweetalert : alert창을 꾸밀 수 있게 해주는 라이브러리 https://sweetalert.js.org/ -->
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
   <style type="text/css">
   #checkPw, #checkPw2{
   width:100%;
   height: 20px;
   }
   
   .txt > span {
   font-size:30px;
   font-weight: bord;
   }
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
          <div id="right" ><a id="searchIdForm" href="${contextPath}/common/searchIdForm.do"><h3 class="s2class"><span>아이디</span><span class="su">찾기</span></h3></a></div>
       </div>
       <div class="c2">
        <form class="pwSet" method="post" action="${contextPath}/common/setPw.do"  onsubmit="return PwSetValidate();">
            <h1 class="search2">비밀번호 재설정</h1>
            <br>
            <div class="txt">
                  <span>ID : ${memId}</span> <br>
               <h3>
                  비밀번호를 변경해주세요
               </h3>
               <div class="infoArea">
                  <input name="password1" id="password" type="password" placeholder="새 비밀번호" class="userInfo"> <br>
                  <div id="checkPw"></div>
                  <input name="password2" id="confirmpassword" type="password" placeholder="새 비밀번호 확인" class="userInfo">
               	<div id="checkPw2"></div>
               </div>
               <button class="btn" type="submit">확인</button>
            </div>
         </form>

      </div>
      <div class="footer"></div>
   </div>
   
    <script>
      $(document).ready(function(){
      $(".container").fadeIn(1000);
});
      
      var validateCheck = {
    			"password" : false
    			}


    		//비밀번호 검사
    		$("#password, #confirmpassword").on("input",function(){
    			var regExp =/^[a-zA-Z\d]{6,20}$/;
    		    var value1= $("#password").val(); 
    			var value2 = $("#confirmpassword").val(); 
    			
    			if(!regExp.test(value1)){
    		        $("#checkPw").text("비밀번호 형식이 유효하지 않습니다.").css("color","red");
    		        validateCheck.password = false;
    		    }else{
    				 $("#checkPw").text("유효한 비밀번호 형식입니다.").css("color","green");
    		        validateCheck.password = true;
    			}
    			//비밀번호가 유효하지 않은 상태에서 비밀번호 확인 작성 시
    		    if(!validateCheck.password && value2.length > 0){
    		        swal("유효한 비밀번호를 먼저 작성해주세요");
    		        $("#confirmpassword").val(""); 
    		        $("#password").focus(); 
    		    }else{
    		        // + 비밀번호, 비밀번호 확인의 일치여부 
    		        if(value1.length == 0 || value2.length == 0){
    		            $("#checkPw2").html("&nbsp;");
    		        }else if(value1 == value2){
    		            $("#checkPw2").text("비밀번호 일치").css("color","green");
    		            validateCheck.confirmpassword = true;
    		        }else{
    		            $("#checkPw2").text("비밀번호 불일치").css("color","red");
    		            validateCheck.confirmpassword = false;
    		        }
    		    }
    		});

    		function PwSetValidate(){
    			if(validateCheck.confirmpassword == false){
    				return false;
    			}
    		}
   </script>
</body>
</html>