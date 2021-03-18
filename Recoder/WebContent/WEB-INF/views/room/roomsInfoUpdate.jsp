<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="contextPath" value="${pageContext.servletContext.contextPath }" scope="application"></c:set>

<!DOCTYPE html>
<html lang="kr">
	
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Document</title>
        
		<!-- reset.css -->
		<link rel="stylesheet" href="${contextPath}/resources/css/reset.css">
		<!-- bootstrap -->
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
		<!-- slick.css -->
		<link rel="stylesheet" href="${contextPath}/resources/css/slick.css">
		<!-- roomsInfoUpdate.css -->
		<link rel="stylesheet" href="${contextPath}/resources/css/roomsInfoUpdate.css">
		
		
		
		<!-- jQuery -->
		<script src="http://code.jquery.com/jquery-3.5.1.min.js" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>
		
		<!-- slick.js -->
		<script src="${contextPath}/resources/js/slick.min.js"></script>
		
		<!-- font awesome -->
		<script src="${contextPath}/resources/js/fontawesome.js"></script>
    
</head>
<body>

    <div class="wrapper">
        <!-- WEB-INF/views/common/header.jsp 여기에 삽입(포함) -->
		<jsp:include page="../common/header.jsp"></jsp:include>

		
        <h2>매물 수정</h2>
        <!-- ${contextPath}/room/roomUpdate.do -->
        <form action="${contextPath}/room/roomUpdate.do?no=${room.roomNo}" id="updateForm" method="POST"  enctype="multipart/form-data" role="form"onsubmit="return updateValidate();">
        
            <!-- update image -->
            <section class="images">
                <h3>사진 올리기</h3>
                <span>빈 공간을 클릭해주세요</span>
                <div class="images_wrapper">
                    <div class="img_list">
                        <img alt="" id="img0">
                    </div>
                    <div class="img_list">
                        <img alt="" id="img1">
                    </div>
                    <div class="img_list">
                        <img alt="" id="img2">
                    </div>
                    <div class="img_list">
                        <img alt="" id="img3">
                    </div>
                    <div class="img_list">
                        <img alt="" id="img4">
                    </div>
                    <div class="img_list">
                        <img alt="" id="img5">
                    </div>
                    <div class="img_list">
                        <img alt="" id="img6">
                    </div>
                    <div class="img_list">
                        <img alt="" id="img7">
                    </div>
                    <div class="img_list">
                        <img alt="" id="img8">
                    </div>
                </div>

                <div id="fileArea">
                    <input type="file" id="img_0" onchange="LoadImg(this,0)" name="img0">
                    <input type="file" id="img_1" onchange="LoadImg(this,1)" name="img1">
                    <input type="file" id="img_2" onchange="LoadImg(this,2)" name="img2">
                    <input type="file" id="img_3" onchange="LoadImg(this,3)" name="img3">
                    <input type="file" id="img_4" onchange="LoadImg(this,4)" name="img4">
                    <input type="file" id="img_5" onchange="LoadImg(this,5)" name="img5">
                    <input type="file" id="img_6" onchange="LoadImg(this,6)" name="img6">
                    <input type="file" id="img_7" onchange="LoadImg(this,7)" name="img7">
                    <input type="file" id="img_8" onchange="LoadImg(this,8)" name="img8">
                </div>
                
            </section>

            <hr>

            <!-- update roomInfo -->
            <section class="room_info">
                <div class="room_content">
                    제목을 수정해주세요 <input type="text" class="title" id="roomTitle" name="roomTitle" placeholder="제목" value="${room.roomTitle }">
                    내용을 수정해주세요 <textarea class="about" id="roomInfo" name="roomInfo">${room.roomInfo }</textarea>
                </div>

                <div class="room_detail">
                    <table class="table caption-top">
                        <thead> 
                        <tr>
                            <th class="more_info" colspan="4">추가 정보</th>
                        </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <th scope="row" >지역</th>
                                <td colspan="3" id="area"><input type="text" id="roomAddr" name="roomAddr" value="${room.roomAddr }"></td>
                            </tr>
                            <tr>
                                <th scope="row" >관리비</th>
                                <td><input type="text" id="careFee" name="careFee" value="${room.careFee }"></td>
                                <th scope="row">월세/전세</th>
                                <td>
                                    <select name="typeOfRent" id="typeOfRent" value="${room.typeOfRent }">
                                        <option value="1">월세</option>
                                        <option value="2">전세</option>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <th scope="row">보증금</th>
                                <td><input type="text" id="deposit" name="deposit" value="${room.deposit }"></td>
                                <th scope="row">월세</th>
                                <td><input type="text" id="monthRent" name="monthRent" value="${room.monthRent }"></td>
                            </tr>
                            <tr>
                                <th scope="row">구조</th>
                                <td>
                                    <select name="roomStruc" id="roomStruc" value="${room.roomStruc }">
                                        <option value="분리형원룸">분리형원룸</option>
                                        <option value="복층">복층</option>
                                        <option value="단층">단층</option>
                                    </select>
                                </td>
                                <th scope="row">층수</th>
                                <td><input type="text" id="roomFloor" name="roomFloor" value="${room.roomFloor }"></td>
                            </tr>
                            <tr>
                                <th scope="row">공급면적</th>
                                <td><input type="text" id="pubSize" name="pubSize" value="${room.pubSize }"></td>
                                <th scope="row">전용면적</th>
                                <td><input type="text" id="realSize" name="realSize" value="${room.realSize }"></td>
                            </tr>
                            <tr>
                                <th scope="row">방갯수</th>
                                <td>
                                    <select name="roomCount" id="roomCount" value="${room.roomCount }">
                                        <option value="1">원룸</option>
                                        <option value="2">투룸</option>
                                    </select>
                                </td>
                                <th scope="row">근쳐 지하철역</th>
                                <td><input type="text" id="stationAddr" name="stationAddr"  value="${room.stationAddr }"></td>
                            </tr>
                        </tbody>
                    </table>
                </div>

                <div class="option">
                    <h2>옵션</h2>
                    <div class="title">
                        <h3>
                            있어요 <i class="fas fa-grin-hearts"></i>
                            <br>
                            <span>여기로 드래그 하세요! <i class="far fa-hand-point-down"></i></span>
                        </h3>
                        <h3>
                            없어요 <i class="fas fa-dizzy"></i>
                            <br>
                            <span>여기로 드래그 하세요! <i class="far fa-hand-point-down"></i></span>
                        </h3>
                    </div>
                    <div class="option_info">
                        <ul id="having" class="having" ondrop="drop(event)" ondragover="allowDrop(event)">
                            
                        </ul>
                        
                        <ul id="none" class="none" ondrop="drop(event)" ondragover="allowDrop(event)" draggable="true" ondragstart="drag(event)" class="items">
                            
                        </ul>
                        
                        
                    </div>
                    <div class="option_list" ondrop="drop(event)" ondragover="allowDrop(event)" >
                        <ul id="optionList">
                            <li id="airCon" draggable="true" ondragstart="drag(event)" class="items"><span><i class="fad fa-air-conditioner"></i>에어컨</span></li>
                            <li id="washing" draggable="true" ondragstart="drag(event)" class="items"><span><i class="fas fa-washer"></i>세탁기</span></li>
                            <li id="bed" draggable="true" ondragstart="drag(event)" class="items"><span><i class="fad fa-bed-empty"></i>침대</span></li>
                            <li id="parking" draggable="true" ondragstart="drag(event)" class="items"><span><i class="fad fa-parking"></i>주차</span></li>
                            <li id="closet" draggable="true" ondragstart="drag(event)" class="items"><span><i class="fas fa-tshirt"></i>옷장</span></li>
                            <li id="tv" draggable="true" ondragstart="drag(event)" class="items"><span><i class="fad fa-tv-retro"></i>텔레비전</span></li>
                            <li id="internet" draggable="true" ondragstart="drag(event)" class="items"><span><i class="fas fa-wifi"></i>인터넷</span></li>
                            <li id="fridge" draggable="true" ondragstart="drag(event)" class="items"><span><i class="fas fa-refrigerator"></i>냉장고</span></li>
                            <li id="womanOnly" draggable="true" ondragstart="drag(event)" class="items"><span><i class="fas fa-female"></i>여성전용</span></li>
                            <li id="pet" draggable="true" ondragstart="drag(event)" class="items"><span><i class="fad fa-dog"></i>반려동물</span></li>
                        </ul>

                    </div>
                </div>

                
            </section>
            
            <!-- section map -->
            <section class="map">
	            <div class="map_wrapper">
	                <h3>위치</h3>
	                <div id="map" class="map_area"></div>
	            </div>
	        </section>
            
            <div class="form_btn">
                <button type="submit" class="btn btn-primary" id="update">수정</button>
                <button type="button" class="btn btn-secondary">이전으로</button>
            </div>
        </form>

<!-- WEB-INF/views/common/header.jsp 여기에 삽입(포함) -->
		<jsp:include page="../common/footer.jsp"></jsp:include>
        
    </div>
    
    <script src="${contextPath}/resources/js/roomsInfoUpdate.js"></script>

    <script src="//dapi.kakao.com/v2/maps/sdk.js?appkey=8035352f3860f77b021b6c64824a3b93&libraries=services"></script>
	<script>

	$("#roomAddr").blur(function(){
		
		// 마커를 클릭하면 장소명을 표출할 인포윈도우 입니다
		var infowindow = new kakao.maps.InfoWindow({zIndex:1});
		
		var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
		    mapOption = {
		        center: new kakao.maps.LatLng(37.566826, 126.9786567), // 지도의 중심좌표
		        level: 3 // 지도의 확대 레벨
		    };  
		
		// 지도를 생성합니다    
		var map = new kakao.maps.Map(mapContainer, mapOption); 
		
		// 장소 검색 객체를 생성합니다
		var ps = new kakao.maps.services.Places(); 
		
		// 키워드로 장소를 검색합니다
		ps.keywordSearch($("#roomAddr").val(), placesSearchCB); 
		
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
		
		// 마커를 생성합니다
		var marker = new kakao.maps.Marker({
		    position: markerPosition
		});
		marker.setMap(map);
		    } 
		}
	})

	$(function () {
       $("#fileArea").hide();

      $(".img_list").on("click",function(){
        var index = $(".img_list").index(this);
        $("#img_" + index).click();
        console.log(111);
      });
    });
	
	

	// 각각의 영역에 파일을 첨부 했을 경우 미리 보기가 가능하도록 하는 함수
	function LoadImg(value, num) {
	  if (value.files && value.files[0]) {
	    var reader = new FileReader();
	    // 자바스크립트 FileReader
	     // 웹 애플리케이션이 비동기적으로 데이터를 읽기 위하여 읽을 파일을 가리키는 File 혹은 Blob객체를 이용해 파일의 내용을 읽고 사용자의 컴퓨터에 저장하는 것을 가능하게 해주는 객체
	    
	    reader.readAsDataURL(value.files[0]);
	    // FileReader.readAsDataURL()
	    // 지정된의 내용을 읽기 시작합니다. Blob완료되면 result속성 data:에 파일 데이터를 나타내는 URL이 포함 됩니다.
	    
	     // FileReader.onload
	    // load 이벤트의 핸들러. 이 이벤트는 읽기 동작이 성공적으로 완료 되었을 때마다 발생합니다.
	    reader.onload = function (e) {
	      //console.log(e.target.result);
	      // e.target.result
	      // -> 파일 읽기 동작을 성공한 객체에(fileTag) 올라간 결과(이미지 또는 파일)
	      
	      $(".img_list").eq(num).children("img").attr("src", e.target.result);
	    }

	  }
	}

		 
	
	
	<c:forEach var="mFile" items="${mList}">
 			$(".img_list").eq(${mFile.roomImgLevel}).children("img").attr("src", "${contextPath}/resources/images/rooms/${mFile.roomImgName}"); 
	</c:forEach>
	</script>
</body>
</html>