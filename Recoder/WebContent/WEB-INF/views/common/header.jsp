<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.servletContext.contextPath }" scope="application"></c:set>

<html lang="kr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>내 방</title>
   <link rel="stylesheet" href="${contextPath}/resources/css/header.css">
   
<!-- sweetalert : alert창을 꾸밀 수 있게 해주는 라이브러리 https://sweetalert.js.org/ -->
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
   
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
		<%--
			프로젝트의 시작주소 (context root)를 얻어와 간단하게 사용할 수 있도록 
			별도의 변수를 생성 
	 	--%>
		
        <!-- header -->
        <header>
            <div class="menu_wrapper">
                <div class="logo">
                    <a href="${contextPath}"><img src="${contextPath}/resources/images/homepage/logo.png" alt="로고"></a>
                </div>
                <div class="menu_left clearfix">
                    <ul class="menu_left_title clearfix">
                        <li><a href="${contextPath}/searchKeyword.do?keyword="><span><b>방찾기</b></span></a></li>
                        <li><a id="insertRoom"><span><b>방 내놓기</b></span></a></li>
                        <c:if test="${!empty sessionScope.loginMember}">
                        	<li><a id="pickRoom"><span><b>찜한 방</b></span></a></li>
                        </c:if>
                        <c:if test="${empty sessionScope.loginMember}">
                        	<li><a id="pickRoom"><span><b>찜한 방</b></span></a></li>
                        </c:if>
                         <li><a href="${contextPath}/notice/list.do""><span><b>공지사항</b></span></a></li>
                        <li><a href="${contextPath}/board/list.do"><span><b>게시판</b></span></a></li>
                         <li><a href="${contextPath}/message/message.do"><span><b>쪽지함</b></span></a></li>
                        
                    </ul>
                </div>
                  
               <%--헤더 오른쪽 영역 --%>
                 <div class="loginInfoArea">
              <c:choose>
            	<c:when test="${ empty sessionScope.loginMember}">      
                    <ul>
                        <li><a href="${contextPath}/common/signUpForm.do">회원가입</a></li>
                        <li><a href="${contextPath}/common/loginForm.do">로그인</a></li>
                    </ul>
                </c:when>
                <c:otherwise>
                <div class="loginInfo">
                  <span id="nickName">${loginMember.memNick}님</span>
                  	<c:if test="${sessionScope.loginMember.memGrade =='G'}">
                     <a href="${contextPath}/member/memberMyPage.do" id="myPage">마이페이지</a>
                    </c:if>
                 	<c:if test="${sessionScope.loginMember.memGrade =='B'}">
                      <a href="${contextPath}/broker/brokerInfo.do" id="myPage">마이페이지</a>
                	</c:if>
                     <a href="${contextPath}/common/logout.do" id="logout">Logout</a>
                 </div>
                </div>
                   
                </c:otherwise>
              </c:choose> 
            </div>
        </header>
  <script src="${contextPath}/resources/js/index.js">
  </script>
  
  <script>
  $("#pickRoom").on("click", function(){
	 if(${ !empty sessionScope.loginMember}){
		 if (${sessionScope.loginMember.memGrade=='G'}){
			 $(this).attr("href", "${contextPath}/member/memberMyPage.do");
		 }else{
			 swal({
				    title: "입장이 불가합니다.",
				    text: "일반 회원들만 이용 가능한 메뉴입니다.",
				    icon: "warning" 
				})
		 }
	 } else{
		 swal({
			    title: "로그인이 필요합니다.",
			    text: "로그인 후 이용 가능한 메뉴입니다. 로그인 해주세요.",
			    icon: "warning" //"info,success,warning,error" 중 택1
			}).then(function() {
			    window.location = "${contextPath}/common/loginForm.do";
			});
		/*  $(this).attr("href", "${contextPath}/common/loginForm.do"); */
	 }
  });
  
  $("#insertRoom").on("click", function(){
	  if(${ !empty sessionScope.loginMember}){ 
			 if (${sessionScope.loginMember.memGrade=='B'}){
				 $(this).attr("href", "${contextPath}/room/roomInsertForm.do");
			 }else{
				 swal({
					    title: "입장이 불가합니다.",
					    text: "중개사 회원들만 이용 가능한 메뉴입니다.",
					    icon: "warning" 
					})
			 }
		 } else{
			 swal({ 
				    title: "로그인이 필요합니다.",
				    text: "로그인 후 이용 가능한 메뉴입니다. 로그인 해주세요.",
				    icon: "warning" //"info,success,warning,error" 중 택1
				}).then(function() {
				    window.location = "${contextPath}/common/loginForm.do";
				});
			/*  $(this).attr("href", "${contextPath}/common/loginForm.do"); */
		 }
  });
  
  
  
  </script>