<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>footer</title>
    <link rel="stylesheet" href="${contextPath}/resources/css/footer.css">
</head>
<body>
    
    <div class="footer">
    <div class="footer_wrap ft">
        <div class="footer_top clearfix">
            <ul class="about">
                <li><a href="">회사소개</a></li>
                <li><a href="">이용약관</a></li>
                <li><a href="">개인정보처리방침</a></li>
                <li><a href="">매물관리규정</a></li>
                <li><a href="">자동저장서비스</a></li>
            </ul>
            <div id="gotoTop"><i class="fas fa-arrow-up"></i>TOP</div>
        </div>
        <div class="footer_mid">
            <p>
                (주)스테이션3 <br>
                대표 : 한유순, 유형석 <br>
                사업자 번호: 220-88-59156 &nbsp;통신판매업신고번호 : 제2013-서울 강남-02884호<br>
                주소 : 서울시 서초구 서초대로 301 동익 성봉빌딩 10층 (주)스테이션3 <br>
            </p>
            <p>
                고객센터 : 02-1899-6840(평일 10:00 ~ 18:30 토•일요일, 공휴일 휴무) <br>
                팩스 : 02-554-9774프로모션/사업 제휴문의 : biz@station3.co.kr허위매물 신고 : clean@dabangapp.com
            </p>
        </div>
        <div class="footer_bottom">
            <span>Station3, Inc. All rights reserved.</span>
        </div>
    </div>
</div>
<script>
    // 위로 부드럽게
$("#gotoTop").click(function() {
    $('html').animate({
        scrollTop : 0
    }, 400);
});
</script>
</body>
</html>