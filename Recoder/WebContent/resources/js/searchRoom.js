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


// 위로 부드럽게
$("#gotoTop").click(function() {
  $('html').animate({
      scrollTop : 0
  }, 400);
});