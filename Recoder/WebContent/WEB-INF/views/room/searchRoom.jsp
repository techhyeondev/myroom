<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>


	<!-- reset.css -->
    <link rel="stylesheet" href="resources/css/reset.css">

    <!-- searchRoom.css -->
    <link rel="stylesheet" href="resources/css/searchRoom.css">


    <script src="https://kit.fontawesome.com/70c929d7d4.js" crossorigin="anonymous"></script>
     <!-- 부트스트랩 사용을 위한 라이브러리 추가 -->
     <!-- 제이쿼리가 항상 먼저여야함 -->
     <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>



</head>
<body>
<jsp:include page="../common/header.jsp"></jsp:include>
    <div class="wrapper">
        <div class="row">
                <div class="row2 subHeader">

                <!-- 지역 검색 기능 -->
                    <div class="search_area"><!-- ${contextPath}/searchKeyword.do -->
                        <form action="${contextPath}/searchKeyword.do" method="get" class="text-center" id="searchForm">
                            <input type="text" name="keyword" class="search_Keyword" id="keyword2" placeholder="종로구" autocomplete="off" value="다산">
                            <button id="searchBtn"><i class="fas fa-search"></i></button>

                        </form>
                    </div>
                </div>
        </div>
        <div class="content">
            <div class="mapArea">
            	<div class="map_wrap">
				    <div id="map" style="width:100%;height:100%;position:relative;overflow:hidden;"></div>
				</div>
            </div>
        </div>
    </div>

    <%-- searchRoom.js --%>
    <script src="resources/js/searchRoom.js"></script>

	<script src="//dapi.kakao.com/v2/maps/sdk.js?appkey=8035352f3860f77b021b6c64824a3b93&libraries=services"></script>

    <script>
        var layer = document.getElementsByClassName("filter_btn");

        $(".filter_btn").on("click", function(event){
            var id = $(this).siblings(".layer");
            var id2 = $(this).parent().siblings().children(".layer");
            var color1 = $(this).children("p");
            var color2 = $(this).parent().siblings().children().children("p");

            $(id2).css("display", "none");
            $(color2).css("color", "rgb(34, 34, 34)");

            if($(id).css("display") == "none"){
                $(id).css("display", "block");
                $(color1).css("color", "rgb(174, 160, 236)");
            } else {
                $(id).css("display", "none");
                $(color1).css("color", "rgb(34, 34, 34)");
            }
        });

        $(".close").on("click", function(){
            $(this).parent().siblings().children("p").css("color", "rgb(34, 34, 34)");
        });



        function ShowSliderValue(result, rangeId)
        {
            var obValueView1 = rangeId.siblings("span");
            obValueView1.html(result);
        }

        var RangeSlider = function(rangeId){
            var range = $(rangeId);

            range.on('input', function(){
                var roomVal = this.value;
                var result = "";

                if(roomVal < 10000){
                    result = roomVal + "만원 이하"
                    ShowSliderValue(result, rangeId);
                }
                else{
                    var val1 = parseInt(roomVal / 10000);
                    var val2 = parseInt(roomVal % 10000);
                    if(val2 == 0) {
                        result = val1 + "억 ";
                    }else{
                        result = val1 + "억 " + val2 + "만원"
                    }
                    ShowSliderValue(result, rangeId);
                }

            });
        };
        RangeSlider($("#customRange1"));
        RangeSlider($("#customRange2"));
        RangeSlider($("#customRange3"));


    	// 게시글 상세보기 기능 (jquery를 통해 작업)

/* 		$(".infoLink")
				.on(
						"click",
						function() {

							// 게시글 번호 얻어오기
							var roomNo = $(this).attr('id');
							// 클릭이 되는지 테스트
							console.log(roomNo);
							var url = "${contextPath}/room/view.do?cp=${pInfo.currentPage}&no="
									+ roomNo;

							location.href = url;

						}); */

		//검색내용이 잇을경우 검색착에 해당 내용을 작성해두는 기능
		(function() {
			/* var searchKey = "${param.sk}";
			//파라미터 중 sk가 있을 경우 ex)"49"
			//파라미터 중 sk가 없을 경우 ex) "" */

			var searchValue = "${param.keyword}";

			//검색창 select의 option을 반복 접근
/* 			$("select[name=sk] > option").each(function(index, item) {
				//index : 현재 접근중인 요소의 인덱스
				//item : 현재 접근중인 요소

				if ($(item).val() == searchKey) {
					$(item).prop("selected", true);
				}
			}); */

			//검색어 입력창에 searchValue값 출력
			$("input[name=keyword]").val(searchValue);

/* 			var para2=[];
			$("input[name='roomType']:checked").each(function(i){
				para2.push(($this).val());
			});

			var postData = {"para1":"para1", "chklist" : para2};

			$.ajax({
				url : "${contextPath}/RoomFilterController.do",
				type : 'post',
				data : postData,
				traditional : true,
				error: function(){
					alert('오류발생');
				},
				success : function(result){
					if(result > 0){
						swal.fire("성공!", 'success');
		            }else{
						swal.fire("실패", 'error');
		            }
				}
			}); */

		})();


/* 		$("#layer1 #filter_btn_submit").on("click", function(){
			console.log("아악");

			var isSeasonChk = false;



			var para2=[];

	        var arr_Season = document.getElementsByName("roomType");
	        for(var i=0;i<arr_Season.length;i++){
	            if(arr_Season[i].checked == true) {
	            	para2.push(arr_Season[i].value);
	            }
	        }

	            	console.log(para2);
			var postData = {"para1":"para1", "chklist" : para2};

			$.ajax({
				url : "${contextPath}/RoomFilterController.do",
				type : 'post',
				data : postData,
				traditional : true,
				error: function(){
					alert('오류발생');
				},
				success : function(result){
					if(result > 0){
						alert('성공');
		            }else{
		            	alert('헉');
		            }
				}
			});
		});*/



		// ----------------------------------------------------------------------------------


	var mapContainer = document.getElementById('map'), // 지도를 표시할 div
    mapOption = {
        center: new kakao.maps.LatLng(37.5437096971089, 126.90423126379329), // 지도의 중심좌표
        level: 4 // 지도의 확대 레벨
    };

	var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

	var listData =  new Array();

	<c:forEach var="roomList" items="${roomList }">

		listData.push({
			groupAddress : '${roomList.roomAddr}',
			title : '${roomList.roomTitle}',
			img: '${roomList.pet}',
			roomNo:'${roomList.roomNo}',
			path: '${roomList.bed}' });

	</c:forEach>
	console.log(listData)
		var mapContainer = document.getElementById('map'), // 지도를 표시할 div
	    mapOption = {
	        center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
	        level: 5 // 지도의 확대 레벨
	    };
/*
	var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
 */
	console.log(listData)


	// 장소 검색 객체를 생성합니다
	var ps = new kakao.maps.services.Places();

	// 키워드로 장소를 검색합니다
	// listData[0].groupAddress
	// $("input[name=keyword]").val()
	ps.keywordSearch(keyword2, placesSearchCB);

	// 키워드 검색 완료 시 호출되는 콜백함수 입니다
	function placesSearchCB (data, status, pagination) {
	    if (status === kakao.maps.services.Status.OK) {

	        // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
	        // LatLngBounds 객체에 좌표를 추가합니다
	        var bounds = new kakao.maps.LatLngBounds();

	            bounds.extend(new kakao.maps.LatLng(data[0].y, data[0].x));

	        // 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
	        map.setBounds(bounds);

	        var markerPosition  = new kakao.maps.LatLng(map.getCenter().getLat(), map.getCenter().getLng());


	    }
	}




	// 주소-좌표 변환 객체를 생성합니다
	var geocoder = new daum.maps.services.Geocoder();






	// 마커 이미지의 이미지 주소입니다
	var imageSrc = "${contextPath}/resources/images/homepage/mark.png";

	var overlayList = [];


	for (let i=0; i < listData.length ; i++) {
		// 주소로 좌표를 검색합니다
		geocoder.addressSearch(listData[i].groupAddress, function(result, status) {

	    // 정상적으로 검색이 완료됐으면
	     if (status === daum.maps.services.Status.OK) {

	        var coords = new daum.maps.LatLng(result[0].y, result[0].x);


	     // 마커 이미지의 이미지 크기 입니다
	        var imageSize = new kakao.maps.Size(24, 35);

	        // 마커 이미지를 생성합니다
	        var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);


	        // 결과값으로 받은 위치를 마커로 표시합니다
	        var marker = new kakao.maps.Marker({
	            map: map,
	            position: coords,
	            image : markerImage, // 마커 이미지
	        });




	        var content = '<div class="wrap rooonlist" id="lst'+i+'">' +
            '    <div class="info">' +
            '        <div class="title" id="'+  marker.getPosition() +'">' +  listData[i].title +
            '        </div>' +
            '        <div class="body">' +
            '            <div class="img">' +
            '                <img src="${contextPath}/resources/images/rooms/'+listData[i].img+'" width="73" height="70" id="' + listData[i].roomNo + '">' +
            '           </div>' +
            '            <div class="desc">' +
            '                <div class="ellipsis">'+listData[i].groupAddress+'</div>' +
            '                <div><a class="link infoLink" href="${contextPath}/room/view.do?no='
									+ listData[i].roomNo+'">매물 상세보기</a></div>' +
            '            </div>' +
            '        </div>' +
            '    </div>' +
            '</div>';

         // 마커 위에 커스텀오버레이를 표시합니다
         // 마커를 중심으로 커스텀 오버레이를 표시하기위해 CSS를 이용해 위치를 설정했습니다
         var overlay = new kakao.maps.CustomOverlay({
             content: content,
             map: map,
             position: marker.getPosition()
         });

         overlayList.push(overlay);

         // 마커를 클릭했을 때 커스텀 오버레이를 표시합니다
         kakao.maps.event.addListener(marker, 'click', function() {
             	// overlay.setMap(map);
             	$("#lst"+i).toggle(300);
         });


	        map.setCenter(coords);
	    }

	    console.log(map)
	})



	};

    </script>
</body>
</html>
