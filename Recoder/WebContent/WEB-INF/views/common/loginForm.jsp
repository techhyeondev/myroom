<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
   <meta charset="UTF-8">
   <meta name="viewport" content="width=device-width, initial-scale=1.0">
   <meta http-equiv="X-UA-Compatible" content="ie=edge">
   
   <!-- login.css -->
    <link rel="stylesheet" href="${contextPath}/resources/css/login.css">
    
   <!-- jQuery -->
   <script src="http://code.jquery.com/jquery-3.5.1.min.js" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>
   <!-- sweetalert : alert창을 꾸밀 수 있게 해주는 라이브러리 https://sweetalert.js.org/ -->
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
   <title>로그인</title>
</head>
<body>

	

   <div class="container">
      <div class="c1">
         
         <div class="c11">
            <h1 class="mainhead">내 방 찾기</h1>
         </div>
         <div id="left"><h3 class="s1class"><span>공인중개사</span><span class="su">회원</span>
         </h3></div>
         <div id="right"><h3 class="s2class"><span>일반</span><span class="su">회원</span></h3></div>
      </div>
      <div class="c2">
         <form class="signup" method="POST" action="${contextPath}/member/login.do" >
            <h1 class="signup1">일반회원 로그인</h1>
            <br><br><br><br>
            <input name="userId" type="text" placeholder="User ID*" class="username" value="${cookie.saveIdG.value}"/>
            
            <input name="userPw" type="password" placeholder="Password*" class="username"/>
            <button class="btn" type="submit">Login</button>
            
            <div id="rememberId">
               <input type="checkbox" <c:if test="${!empty cookie.saveIdG.value}">checked</c:if> name="remember" id="remember"><label for="remember">아이디 저장</label> <br>
            </div>
            
            <div id="else">
               <a href="${contextPath}/common/signUpForm.do" id="signin">회원가입</a> <br>
               <a href="${contextPath}/common/searchIdForm.do" id="search">아이디 | 비밀번호 찾기</a> 
            </div>
         </form>
         

         <form class="signin"  method="POST" action="${contextPath}/broker/login.do">
            
            <h1 class="signup1">공인중개사 로그인</h1>
            <br><br><br><br>
            <input name="userId" type="text" placeholder="User ID*" class="username" value="${cookie.saveIdB.value}"/>
            
            <input name="userPw" type="password" placeholder="Password*" class="username"/>
            <button class="btn" type="submit" id="l">Login</button>

            <div id="rememberId">
               <input type="checkbox" <c:if test="${!empty cookie.saveIdB.value}">checked</c:if> name="remember" id="remember"><label for="remember">아이디 저장</label>
            </div>
            
            <div id="else">
               <a href="${contextPath}/common/signUpForm.do" id="signin">회원가입</a> <br>
               <a href="${contextPath}/common/searchIdForm.do" id="search">아이디 | 비밀번호 찾기</a> 
            </div>
         </form>
         
      </div>
   </div>
   <script src="${contextPath}/resources/js/login.js"></script>
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
</body>
</html>