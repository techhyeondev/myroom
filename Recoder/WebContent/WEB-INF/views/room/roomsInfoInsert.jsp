<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    
    <!-- roomsInfo.css -->
    <link rel="stylesheet" href="${contextPath}/resources/css/roomsInfoInsert.css">
    
    

    <!-- jQuery -->
    <script src="http://code.jquery.com/jquery-3.5.1.min.js" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>
    
    <!-- slick.js -->
    <script src="${contextPath}/resources/js/slick.min.js"></script>
    
    <!-- font awesome -->
    <script src="${contextPath}/resources/js/fontawesome.js"></script>
    
</head>
<body>
    <div class="wrapper">
        <!-- header -->
        <!-- WEB-INF/views/common/header.jsp 여기에 삽입(포함) -->
		<jsp:include page="../common/header.jsp"></jsp:include>

        <h2>매물 올리기</h2>
        
        <form action="${contextPath}/room/roomInsert.do" id="insertForm" method="POST" enctype="multipart/form-data" role="form"  onsubmit="return insertteValidate();">
        
            <!-- update image -->
            <section class="images">
                <h3>사진 올리기</h3>
                <span>빈 공간을 클릭해주세요</span>
                <div class="images_wrapper">
                    <div class="img_list">
                        <img alt="">
                    </div>
                    <div class="img_list">
                        <img alt="">
                    </div>
                    <div class="img_list">
                        <img alt="">
                    </div>
                    <div class="img_list">
                        <img alt="">
                    </div>
                    <div class="img_list">
                        <img alt="">
                    </div>
                    <div class="img_list">
                        <img alt="">
                    </div>
                    <div class="img_list">
                        <img alt="">
                    </div>
                    <div class="img_list">
                        <img alt="">
                    </div>
                    <div class="img_list">
                        <img alt="">
                    </div>
                </div>

                <div id="fileArea">
                    <input type="file" id="img0" onchange="LoadImg(this,0)" name="img0">
                    <input type="file" id="img1" onchange="LoadImg(this,1)" name="img1">
                    <input type="file" id="img2" onchange="LoadImg(this,2)" name="img2">
                    <input type="file" id="img3" onchange="LoadImg(this,3)" name="img3">
                    <input type="file" id="img4" onchange="LoadImg(this,4)" name="img4">
                    <input type="file" id="img5" onchange="LoadImg(this,5)" name="img5">
                    <input type="file" id="img6" onchange="LoadImg(this,6)" name="img6">
                    <input type="file" id="img7" onchange="LoadImg(this,7)" name="img7">
                    <input type="file" id="img8" onchange="LoadImg(this,8)" name="img8">
                </div>
                
            </section>

            <hr>

            <!-- update roomInfo -->
            <section class="room_info">
                <div class="room_content">
                    제목을 작성해주세요 <input type="text" class="title" id="roomTitle" name="roomTitle" placeholder="제목">
                    내용을 작성해주세요 <textarea class="about" id="roomInfo" name="roomInfo"></textarea>
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
                                <td colspan="3"><input type="text" id="roomAddr" name="roomAddr"></td>
                            </tr>
                            <tr>
                                <th scope="row" >관리비</th>
                                <td><input type="text" id="careFee" name="careFee"></td>
                                <th scope="row">월세/전세</th>
                                <td>
                                    <select name="typeOfRent" id="typeOfRent">
                                        <option value="1">월세</option>
                                        <option value="2">전세</option>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <th scope="row">보증금</th>
                                <td><input type="text" id="deposit" name="deposit"></td>
                                <th scope="row">월세</th>
                                <td><input type="text" id="monthRent" name="monthRent"></td>
                            </tr>
                            <tr>
                                <th scope="row">구조</th>
                                <td>
                                    <select name="roomStruc" id="roomStruc">
                                        <option value="분리형원룸">분리형원룸</option>
                                        <option value="복층">복층</option>
                                        <option value="단층">단층</option>
                                    </select>
                                </td>
                                <th scope="row">층수</th>
                                <td><input type="text" id="roomFloor" name="roomFloor"></td>
                            </tr>
                            <tr>
                                <th scope="row">공급면적</th>
                                <td><input type="text" id="pubSize" name="pubSize"></td>
                                <th scope="row">전용면적</th>
                                <td><input type="text" id="realSize" name="realSize"></td>
                            </tr>
                            <tr>
                                <th scope="row">방갯수</th>
                                <td>
                                    <select name="roomCount" id="roomCount">
                                        <option value="1">원룸</option>
                                        <option value="2">투룸</option>
                                    </select>
                                </td>
                                <th scope="row">근처 지하철역</th>
                                <td><input type="text" id="stationAddr" name="stationAddr"></td>
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
                <button type="submit" class="btn btn-primary" id="addRoom">완료</button>
                <button type="button" class="btn btn-primary">취소</button>
            </div>
        </form>
        
        <!-- footer -->
        <!-- WEB-INF/views/common/header.jsp 여기에 삽입(포함) -->
		<jsp:include page="../common/footer.jsp"></jsp:include>
        
        
    </div>
    
    <script src="${contextPath}/resources/js/roomsInfoInsert.js"></script>
    <script src="//dapi.kakao.com/v2/maps/sdk.js?appkey=8035352f3860f77b021b6c64824a3b93&libraries=services"></script>
	<script>
	
	console.log($("#area"))
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
	</script>
</body>
</html>