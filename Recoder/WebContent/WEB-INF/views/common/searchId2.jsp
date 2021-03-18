<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.servletContext.contextPath }" scope="application"></c:set>   
 
 
<!-- Bootstrap core CSS -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">

<!-- Bootstrap core JS -->
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>
 
 
 <!DOCTYPE html>
<html lang="en">
<head>
   <meta charset="UTF-8">
   <meta name="viewport" content="width=device-width, initial-scale=1.0">
   <title>아이디|비밀번호 찾기</title>
   <link rel="stylesheet" href="${contextPath}/resources/css/searchId.css">
   <!-- jQuery -->
   <script src="http://code.jquery.com/jquery-3.5.1.min.js" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>
    
</head>
<body>
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
                 <h3>
                     본인확인 이메일로 인증 <br>
                 </h3>
          가입하신 이메일로 <br>
                  인증번호를 받을 수 있습니다. <br>
            </div>
             <input name="username" type="text" placeholder="닉네임" class="userInfo"/><br>
             <input name="email" type="email" placeholder="이메일 주소" class="userInfo emailAdr"/>
             <a class="btn small"data-toggle="modal" href="#modal-container-1">인증번호 받기</a>
             <input name="code" type="number" placeholder="인증번호 6자리 숫자입력"  class="userInfo">
             <button class="btn" id="nextid" type="submit">확인</button>
            </form>

      </div>
      <div class="footer"></div>
   </div>
   
   	<%-- Modal창에 해당하는 html 코드는 페이지 마지막에 작성하는게 좋다 --%>
	<div class="modal fade" id="modal-container-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">

			<div class="modal-content">

				<div class="modal-header">
					<h5 class="modal-title" id="myModalLabel">인증번호 이메일 전송</h5>
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true"></span>
					</button>
				</div>

				<div class="modal-body">
					<form class="form-signin" method="POST" action="${contextPath }/member/login.do">


						<input type="text" class="form-control" id="memberId" name="memberId" placeholder="아이디" value="${cookie.saveId.value }">
						<br>
						<input type="password" class="form-control" id="memberPwd" name="memberPwd" placeholder="비밀번호">
						<br>

						<div class="checkbox mb-3">
							<label> 
							<%-- cookie에 저장된 아이디가 있을 경우 checked 속성 추가 --%>
								<input type="checkbox" name="save" id="save" 
									<c:if test="${!empty cookie.saveId.value }">
										checked 
									</c:if>
								> 아이디 저장
							</label>
						</div>

						<button class="btn btn-lg btn-primary btn-block" type="submit">로그인</button>
						<a class="btn btn-lg btn-secondary btn-block" href="${contextPath}/member/signUpForm.do">회원가입</a>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
   
    <script>
      $(document).ready(function(){
      $(".container").fadeIn(1000);
});
      
   </script>
    <script>
      $(document).ready(function(){
      $(".container").fadeIn(1000);
});
   </script>
</body>
</html>