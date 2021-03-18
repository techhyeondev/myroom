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
   
<!-- sweetalert : alert창을 꾸밀 수 있게 해주는 라이브러리 https://sweetalert.js.org/ -->
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
   
   
   <link rel="stylesheet" href="${contextPath}/resources/css/searchId.css">
   <!-- jQuery -->
   <script src="http://code.jquery.com/jquery-3.5.1.min.js" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>
    
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
        
          <div id="left"><a id="searchPwForm" href="${contextPath}/common/searchPwForm.do"><h3 class="s1class" ><span>비밀번호</span><span class="su">찾기</span>
          </h3></a></div>
          <div id="right" class="right_hover"><h3 class="s2class"><span>아이디</span><span class="su">찾기</span></h3></div>
       </div>
       <div class="c2">
       
 		<form class="searchId" method="post" action="${contextPath}/common/searchId.do"  onsubmit="return idValidate();">
            <h1 class="search2">아이디 찾기</h1>
             <div class="txt">
             
                   <h3>내정보에 등록된 이메일로 찾기
                     </h3>
                      가입하신 이메일을 입력해주세요 <br>
                   <br>
             <br>
            </div>
             <input id="nickname"name="username" type="text" placeholder="닉네임" class="userInfo"/><br>
             <input id="email" name="email" type="email" placeholder="이메일 주소" class="userInfo emailAdr"/>
             <button class="btn" id="nextid" type="submit">확인</button>
            </form>

      </div>
      <div class="footer"></div>
   </div>
   <script type="text/javascript">
   $(document).ready(function(){
	      $(".container").fadeIn(1000);
	});</script>
   
      
   </script>
</body>
</html>