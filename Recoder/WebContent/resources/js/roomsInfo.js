$('.slider-for').slick({
    slidesToShow: 1,
    slidesToScroll: 1,
    arrows: false,
    fade: true,
    asNavFor: '.slider-nav'
  });
  
  $('.slider-nav').slick({
    slidesToShow: 3,
    slidesToScroll: 1,
    asNavFor: '.slider-for',
    dots: false,
    centerMode: true,
    focusOnSelect: true,
    arrows: false
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

  
// 위로 부드럽게
$("#gotoTop").click(function() {
  $('html').animate({
      scrollTop : 0
  }, 400);
});

function initMap(){
  const myLatLng= {
    lat: 37.499878229497895,
    lng: 127.03293045767072
  }
  const map = new google.maps.Map(
    document.getElementById('map'),
    {
      center: myLatLng,
      scrollwheel: false,
      zoom: 18
    }
  );
  const marker = new google.maps.Marker({
    position: myLatLng,
    map: map,
    title: 'KH정보교육원'
  });
}


/*

$('#report').on('click', () => {

  Swal.fire({
    title: '<strong>신고하기</strong>',
    icon: 'warning',
    html:
      '<form action="" method="POST" id="report_form">'+
        '신고제목: <input type="text" id="reportTitle" name="reportTitle"><br>'+
        '작성자 아이디: <br>'+
        '<b id="reason">신고 사유</b>'+
        '<input type="radio" id="fake" name="report" value="1">'+
        '<label for="fake">허위매물</label><br>'+
        '<input type="radio" id="illegal" name="report" value="2">'+
        '<label for="illegal">불법 및 음란 광고</label><br>'+
        '<input type="radio" id="info" name="report" value="3">'+
        '<label for="private">개인정보노출 / 사생활 침해</label><br>'+
        '<div id="other_label">'+
          '<input type="radio"  name="report" value="3">'+
          '<label for="other">기타사유: </label>'+
        '</div>'+
        '<textarea name="other" id="other" cols="30" rows="5"></textarea>'+
      '</form>',
    showCloseButton: true,
    showCancelButton: true,
    focusConfirm: false,
    confirmButtonText:
      '신고',
    confirmButtonAriaLabel: 'Thumbs up, great!',
    cancelButtonText:
      '취소',
    cancelButtonAriaLabel: 'Thumbs down'
  }).then((result) => {
    // 값 보낼 함수 시작 가능

	
	$.ajax({
		 		url : "message/messageSend.do",
				type : "post",
				data : {
					"reportTitle" : reportTitle,
					},
				success : function(result){
					if(result > 0){
						
						console.log("성공");
	
					}
					
				}, error : function(){
					console.log("댓글 수정 실패");
				}		
			});
	


	console.log(result)
	Swal.fire('신고했습니다!', '', 'success')
  });

});



*/


/*

		$.ajax({
				url : "/viewReport.do",
			type : "post",
			data : {
				"reportTitle" : reportTitle,
				"fake" : fake,
				"illegal" : illegal,
				"info" : info,
				"other" : other
				},
			success : function(result){
				console.log("성공!!");
				
			}, error : function(){
				console.log("댓글 수정 실패");
			}		
		}); 

	    */



		

