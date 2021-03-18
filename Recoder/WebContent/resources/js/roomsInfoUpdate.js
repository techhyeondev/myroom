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

// 드래그
function allowDrop(ev) {
  ev.preventDefault();
}

function drag(ev) {
  ev.dataTransfer.setData("text", ev.target.id);
}

function drop(ev) {
  ev.preventDefault();
  var data = ev.dataTransfer.getData("text");
  ev.target.appendChild(document.getElementById(data));
}




function updateValidate(e){
	
	// 변수들
	var airCon = $('#airCon');
	var washing = $('#washing');
	var bed = $('#bed');
	var parking = $('#parking');
	var closet = $('#closet');
	var tv = $('#tv');
	var internet = $('#internet');
	var fridge = $('#fridge');
	var womanOnly = $('#womanOnly');
	var pet = $('#pet');
	
	var having = $('#having > li')
	var none = $("#none > li")
	
	var optionList = $("#optionList > li");
	
	var options = [];
	var options2 = [];
	var options3 = [];
	var yes = [];
	var no = []
	
	// 있을 때
	$.each(having,  function(index, item){ 
		options[index] = item.id;
	
//		var input = $("<input type='hidden' name='options[index]'>").val(item.id);
//		var input2 = $("<input type='hidden' name='options2'>").val(item.id);
//		var input2 = $("<input type='hidden' name='options2'>").val(item.id);
		
		// var input = $("<input type='hidden' name='"+ yes[index] +"'>").val(item.id);
		//  yes = $("<input type='hidden' name='"+ options[index] +"'>").val("Y");
		
		
		
		var input2 = $("<input type='hidden' name='options2'>").val(item.id);
		//var input = $("<input type='hidden' name='"+ options[index] +"'>").val("Y");
		yes = $("<input type='hidden' name='"+ options[index] +"'>").val("Y");
		$("#updateForm").append(yes);
		$("#updateForm").append(input2);
		
	
		// form태그 요소 선택
		// <input type="hidden" name="options" value="item.value">
		// form.append(input)
	    
	  })

	// 없을 때
	$.each(none,  function(index, item){ 
		options3[index] = item.id;
	
//		var input = $("<input type='hidden' name='options[index]'>").val(item.id);
//		var input2 = $("<input type='hidden' name='options2'>").val(item.id);
//		var input2 = $("<input type='hidden' name='options2'>").val(item.id);
		
		// var input = $("<input type='hidden' name='"+ yes[index] +"'>").val(item.id);
		
		var input3 = $("<input type='hidden' name='options3'>").val(item.id);
		no = $("<input type='hidden' name='"+ options3[index] +"'>").val("N");
		$("#updateForm").append(no);
		$("#updateForm").append(input3);
		
	
		// form태그 요소 선택
		// <input type="hidden" name="options" value="item.value">
		// form.append(input)
	  })

	console.log(no)

	// 옵션 유효성 검사
	if($("#optionList > li").length>0){
		alert("모든 옵션을 옮겨주세요");
		return false;
	}

}


