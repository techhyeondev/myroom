<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.servletContext.contextPath }" scope="application"></c:set>
<!DOCTYPE html>
<html lang="ko">
<head>
   <meta charset="UTF-8">
   <meta name="viewport" content="width=device-width, initial-scale=1.0">
   <title>Document</title>
   <link rel="stylesheet" href="${contextPath}/resources/css/updateMember.css">
   <!-- jQuery -->
   <script src="http://code.jquery.com/jquery-3.5.1.min.js" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>
   <!-- swalicon --> 
   <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
</head>
<body>
    <div class="container">
    
    <div class="c1">
          
          <!-- div class="c11">
             <h1 class="mainhead">내 방 찾기</h1>
          </div> -->
          <div id="left"><h3 class="s1class" >
          </h3></div>
          <div id="right"><h3 class="s2class">
       </div>
       <div class="c2">
          <!-- 일반회원 -->
          
          <c:if test="${sessionScope.loginMember.memGrade =='G'}">
          <form class="membersignup" method="post" action="${contextPath}/member/updateMemberServlet.do" onsubmit="return validateMember();">  <!-- onsubmit="return validateMember();"> -->
          	
             <h1 class="signup1">내 정보 수정</h1>
             <br><br><br><br><br><br><br>
             <input name="password" id="password" type="password" placeholder="비밀번호 " class="username"/>
             <div class="validate-area checkPw" id="checkPw"></div>
             <input name="confirmpassword" id="confirmpassword" type="password" placeholder="비밀번호 확인 *" class="username"/>
             <div class="validate-area checkPw2" id="checkPw2" ></div>
             <input name="email" id="email" type="email"  value="${loginMember.memEmail }" class="username"/>
             <div class="validate-area checkEmail" id="checkEmail"></div>
             <input name="nickname" id="nickname" type="text" class="username" value="${loginMember.memNick }"/>
             <div class="validate-area checkNick" id="checkNick"></div>
             <input name="usertel" id="usertel" type="tel"  value="${loginMember.memTel }" class="username"/>
             <div class="validate-area checkPhone" id="checkPhone"></div>

            
             <button type="submit" class="btn">수정하기</button>
                        
            
          </form>
          </c:if>
          
       
       </div>
       <div class="footer"></div>
    </div>
</div>

<script> $(function() { $("#postcodify_search_button").postcodifyPopUp(); }); </script>
	<script src="//d1p7wdleee1q2z.cloudfront.net/post/search.min.js"></script>
	<script src="${contextPath}/resources/js/memberUpdate2.js">
   </script>
</body>
</html>