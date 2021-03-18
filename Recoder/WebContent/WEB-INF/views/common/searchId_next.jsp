<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.servletContext.contextPath }" scope="application"></c:set>   
 
 <!DOCTYPE html>
<html lang="en">
<head>
   <meta charset="UTF-8">
   <meta name="viewport" content="width=device-width, initial-scale=1.0">
   <title>아이디|비밀번호 찾기</title>
   <link rel="stylesheet" href="${contextPath}/resources/css/searchId.css">
   
<!-- sweetalert : alert창을 꾸밀 수 있게 해주는 라이브러리 https://sweetalert.js.org/ -->
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
   
   <!-- jQuery -->
   <script src="http://code.jquery.com/jquery-3.5.1.min.js" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>
    
</head>
<body>

    <div class="container">
       <div class="c1">
        
           <div id="left"><a id="searchPwForm" href="${contextPath}/common/searchPwForm.do"><h3 class="s1class" ><span>비밀번호</span><span class="su">찾기</span>
          </h3></a></div>
          <div id="right"  class="right_hover"><h3 class="s2class"><span>아이디</span><span class="su">찾기</span></h3></div>
       </div>
       <div class="c2">
       
 		
          <div class="idSet">
            <h1 class="search2">아이디 찾기</h1>
            <br><br>

            <div class="txt">
               <h3>
                  고객님의 정보와 일치하는 아이디 입니다.
               </h3>
               <div class="idArea">
                  <span>${memId}</span>
               </div>
               <button class="btn"> <a href="${contextPath}/common/loginForm.do"> 로그인하기</a></button>
            </div>
          </div>

      </div>
      <div class="footer"></div>
   </div>
   <script>

     $(document).ready(function(){
     $(".container").fadeIn(1000);
	});
     
  </script>
</body>
</html>