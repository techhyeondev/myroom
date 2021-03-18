<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.servletContext.contextPath }" scope="application"></c:set>
<!DOCTYPE html>
<html lang="ko">
<head>
   <meta charset="UTF-8">
   <meta name="viewport" content="width=device-width, initial-scale=1.0">
   <title>Document</title>
   <link rel="stylesheet" href="${contextPath}/resources/css/signUpForm.css">
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
          <div id="left"><h3 class="s1class" ><span>공인중개사</span><span class="su">회원</span>
          </h3></div>
          <div id="right"><h3 class="s2class"><span>일반</span><span class="su">회원</span></h3></div>
       </div>
       <div class="c2">
          <!-- 일반회원 -->
          <form class="membersignup" method="post" action="${contextPath}/member/msignUp.do"  onsubmit="return validateMember();">
          	
             <h1 class="signup1">회원가입</h1>
             <br><br><br><br><br><br><br>
             <input name="userid" id="userid" type="text" placeholder="아이디 *" class="username"/>
             <div class="validate-area checkId" id="checkId"></div>
             <input name="password" id="password" type="password" placeholder="비밀번호 *" class="username"/>
             <div class="validate-area checkPw" id="checkPw"></div>
             <input name="confirmpassword" id="confirmpassword" type="password" placeholder="비밀번호 확인 *" class="username"/>
             <div class="validate-area checkPw2" id="checkPw2" ></div>
             <input name="email" id="email" type="email" placeholder="이메일 *" class="username"/>
             <div class="validate-area checkEmail" id="checkEmail"></div>
             <input name="nickname" id="nickname" type="text" placeholder="닉네임 *" class="username"/>
             <div class="validate-area checkNick" id="checkNick"></div>
             <input name="usertel" id="usertel" type="tel" placeholder="전화번호(-포함)*" class="username"/>
             <div class="validate-area checkPhone" id="checkPhone"></div>

             <div id="agreementDivArea" class="agreement">
               <div>
                   <input type="checkbox" class="n-check" id="checkAll">
                   <label for="checkAll" class="all">약관 전체동의</label>
               </div>
               <div>
                   <input type="checkbox" class="n-check agree-item required-agree-item" id="agreeCheckbox" name="agreeCheckbox">
                   <label for="agreeCheckbox">개인정보 수집 이용동의(필수)</label>
                   <button type="button" class="open">약관보기</button>
                   <div class="terms">약관내용</div>
               </div>

               <div>
                   <input type="checkbox" class="n-check agree-item required-agree-item" id="useTermsCheckbox" name="useTermsCheckbox">
                   <label for="useTermsCheckbox">my room 이용약관(필수)</label>
                    <button type="button" class="open" >약관보기</button>
                   <div class="terms">약관내용</div>
               </div>

               <div>
                   <input type="checkbox" class="n-check agree-item optional-agree-item" id="marketingReceiveAgreeYn" name="marketingReceiveAgreeYn">
                   <label for="marketingReceiveAgreeYn">마케팅 활용 및 광고성 정보 수신 동의(선택)</label>
                   <button type="button" class="open" >약관보기</button>
                   <div class="terms">약관내용</div>
               </div>
               <div>
                   <input type="checkbox" class="n-check agree-item required-agree-item" id="ageAgreeCheckbox" name="ageAgreeCheckbox">
                   <label for="ageAgreeCheckbox">만 14세 미만 가입 제한(필수)</label>
               </div>
           </div>
             <button type="submit" class="btn">가입하기</button>
                        
            
          </form>
          
 
          <form class="Brokersignup" enctype="multipart/form-data" role="form"  method="POST" action="${contextPath}/broker/signUp.do"  onsubmit="return validateBroker();">
             <!-- 공인중개사 -->
            <h1 class="signup1">회원가입</h1>
            <br><br>
           <input name="userid" id="userid2" type="text" placeholder="아이디 *" class="username"/>
             <div class="validate-area checkId" id="checkId2"></div>
             
             <input name="password" id="password2" type="password" placeholder="비밀번호 *" class="username"/>
             <div class="validate-area checkPw" id="checkPwB"></div>
             
             <input name="confirmpassword" id="confirmpassword2" type="password" placeholder="비밀번호 확인 *" class="username"/>
             <div class="validate-area checkPw2" id="2checkPw2" ></div>
             
             <input name="email" id="email2" type="email" placeholder="이메일 *" class="username"/>
             <div class="validate-area checkEmail" id="checkEmail2"></div>
             
             <input name="nickname" id="nickname2" type="text" placeholder="닉네임* " class="username"/>
             <div class="validate-area checkNick" id="checkNick2"></div>
             
             <input name="usertel" id="usertel2" type="tel" placeholder="전화번호(-포함)* " class="username"/>
             <div class="validate-area checkPhone" id="checkPhone2"></div>
             
             <!-- 주소 -->
						<div class="postcodeArea">
							<input type="text" name="post" class="form-control postcodify_postcode5 username addr" id="postcode" placeholder="사무실 우편번호*">
							<button type="button" class="btn btn-primary small" id="postcodify_search_button">검색</button>
						</div>
							<!-- <span class="brokerImg" id="officeAddr">사무실 주소</span> -->
							<input type="text" class="form-control postcodify_address username addr" name="address1" id="address1" placeholder="사무실 도로명주소*">

							<input type="text" class="form-control postcodify_details username addr" name="address2" id="address2" placeholder="사무실 상세주소*">
             
             
             <input type="file" id="attachFile">
            <div class="brokerArea">
               <div class="broker boardImg" id="brokerImgArea">
                  <img id="brokerImg" />
               <span name="fileselect" class="btn small" id="fileselect">파일선택</span>
                  <input type="file" id="img0" name="img0" onchange="LoadImg(this,0)"> 
					</div>
               <span class="brokerImg">공인중개사 자격증 사진 첨부*</span>
            </div>
            <div id="agreementDivArea" class="agreement">
               <div>
                   <input type="checkbox" class="n-check1" id="checkAll1">
                   <label for="checkAll" class="all">약관 전체동의</label>
               </div>
               <div>
                   <input type="checkbox" class="n-check1 agree-item required-agree-item" id="agreeCheckbox1" name="agreeCheckbox">
                   <label for="agreeCheckbox">개인정보 수집 이용동의(필수)</label>
                   <button type="button" class="open">약관보기</button>
                    <div class="terms">약관내용</div>
               </div>

               <div>
                   <input type="checkbox" class="n-check1 agree-item required-agree-item" id="useTermsCheckbox1" name="useTermsCheckbox">
                   <label for="useTermsCheckbox">my room 이용약관(필수)</label>
                    <button type="button" class="open" >약관보기</button>
                   <div class="terms">약관내용</div>
               </div>

               <div>
                   <input type="checkbox" class="n-check1 agree-item optional-agree-item" id="marketingReceiveAgreeYn" name="marketingReceiveAgreeYn">
                   <label for="marketingReceiveAgreeYn">마케팅 활용 및 광고성 정보 수신 동의(선택)</label>
                   <button type="button" class="open" >약관보기</button>
                   <div class="terms">약관내용</div>
               </div>
               <div>
                   <input type="checkbox" class="n-check1 agree-item required-agree-item" id="ageAgreeCheckbox1" name="ageAgreeCheckbox">
                   <label for="ageAgreeCheckbox">만 14세 미만 가입 제한(필수)</label>
               </div>
           </div>
            <button class="btn" type="submit">가입하기</button>
 
            
          </form>
          
       </div>
       <div class="footer"></div>
    </div>
<script src="//d1p7wdleee1q2z.cloudfront.net/post/search.min.js"></script>

<!-- "검색" 단추를 누르면 팝업 레이어가 열리도록 설정한다 -->
<script> $(function() { $("#postcodify_search_button").postcodifyPopUp(); }); </script>
	<script src="//d1p7wdleee1q2z.cloudfront.net/post/search.min.js"></script>
   <script src="${contextPath}/resources/js/signUpForm.js">
   </script>
</body>
</html>