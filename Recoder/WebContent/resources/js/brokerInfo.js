$(function(){
    $('.visual .slide').slick({
        arrows: false, // 화살표
        dots: false, // 인디케이스 제거
        fade: true, // 페이드 효과
        autoplay: true, // 자동재생
        autoplaySpeed: 4000, // 재생시간
        pauseOnHover: false, // 마우스 호버시 정지
        pauseOnFocus: false // 포커스시 정지
    })
});

// 헤더 이벤트
var scrollTop = 0;
scrollTop = $(document).scrollTop();

$(window).on('scroll resize', function(){
    scrollTop = $(document).scrollTop();
    fixHeader();
});

function fixHeader(){
    if(scrollTop > 1){
        $('header').addClass('on');
    }else{
        $('header').removeClass('on');
    }
}


$(window).on('scroll resize', function(){
    scrollTop = $(document).scrollTop();
    noticeMotion();
});

function noticeMotion(){
    if(scrollTop > 170 && scrollTop <1500){
        $('.notice .notice_list li').addClass('slide');
        $('.notice .notice_list h2').addClass('slide');
        
        
    }else{
        $('.notice .notice_list li').removeClass('slide');
        $('.notice .notice_list h2').removeClass('slide');
    }
}

$(window).on('scroll resize', function(){
    scrollTop = $(document).scrollTop();
    popularMotion();
});

function popularMotion(){
    if(scrollTop > 750){
        $('.popular .popular_list h2').addClass('slide');
        $('.popular .popular_list > a').addClass('slide');
        $('.popular .popular_list ul li').addClass('slide');
    }else{
        $('.popular .popular_list h2').removeClass('slide');
        $('.popular .popular_list > a').removeClass('slide');
        $('.popular .popular_list ul li').removeClass('slide');
    }
}

// 위로 부드럽게
$("#gotoTop").click(function() {
    $('html').animate({
        scrollTop : 0
    }, 400);
});

$('.autoplay').slick({
    slidesToShow: 3,
    slidesToScroll: 1,
    autoplay: true,
    autoplaySpeed: 2000,
});

