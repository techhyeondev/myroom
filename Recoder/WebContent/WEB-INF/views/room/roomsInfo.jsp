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
		
		<!-- roomsInfo.css -->
		<link rel="stylesheet" href="${contextPath}/resources/css/roomsInfo.css">
		
		
		
		<!-- jQuery -->
		<script src="http://code.jquery.com/jquery-3.5.1.min.js" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>
		
		<!-- slick.js -->
		<script src="${contextPath}/resources/js/slick.min.js"></script>
		
		<!-- font awesome -->
		<script src="${contextPath}/resources/js/fontawesome.js"></script>
		
		<!-- star rating -->
		<script src="${contextPath}/resources/js/rating.js"></script>
    
</head>
<body>
    <div class="wrapper">
        <!-- header -->
		<jsp:include page="../common/header.jsp"></jsp:include>

         
        
        <!-- section menu -->
        <section class="menu">
	        <c:if test="${!empty loginMember && (loginMember.memGrade == 'G')}">
	            <div class="info_wrapper">
	                <div class="left_btn">
	                    <a id="report"><span class="clearfix reportspan"><i class="fas fa-siren-on"></i>신고</span></a>
	                    <a id="msg"><span class="clearfix"><i class="fas fa-envelope"></i>쪽지</span></a>
	                    <a id="heart"><span class="clearfix heartspan"><i class="far fa-heart"></i>찜하기</span></a>
	                </div>
	                <div class="broker_info">
	                    <div class="visit"><button class="btn btn-primary" id="visit">방문신청</button></div>
	                    <div class="broker"><a href="${contextPath }/broker/brokerInfo.do?no=${memNo}">공인중개사</a></div>
	                </div>
	            </div>
			</c:if>
        </section>
        
        <!-- section image -->
        <section class="images">
            <div class="images_wrapper">
                <div class="main_image slider-for">
                <c:if test="${!empty mList }">
	                <c:forEach var="file" items="${mList}" varStatus="vs" >
	                    <img src="${contextPath}/resources/images/rooms/${file.roomImgName}" alt="" id="${file.roomImgNo}">
                    </c:forEach>
                </c:if>
                    
                </div>
                <div class="mini_image slider-nav">
                	<c:if test="${!empty mList }">
		                <c:forEach var="file" items="${mList}" varStatus="vs" >
		                    <img src="${contextPath}/resources/images/rooms/${file.roomImgName}" alt="" id="${file.roomImgNo}">
	                    </c:forEach>
	                </c:if>
                </div>
            </div>
            
        </section>

        <hr>

        <!-- section roomInfo -->
        <section class="room_info">
            <div class="room_content">
                <h3 class="title">${room.roomTitle }</h3>
                <p class="about">
                    ${room.roomInfo}
                </p>
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
                            <th scope="row">지역</th>
                            <td colspan="3" id="area">${room.roomAddr }</td>
                        </tr>
                        <tr>
                            <th scope="row">관리비</th>
                            <td>${room.careFee }만원</td>
                            <th scope="row">근쳐 지하철역</th>
                            <td>${room.stationAddr }</td>
                        </tr>
                        <tr>
                            <th scope="row">구조</th>
                            <td>${room.roomStruc }</td>
                            <th scope="row">층수</th>
                            <td>1</td>
                        </tr>
                        <tr>
                            <th scope="row">공급면적</th>
                            <td>${room.pubSize }</td>
                            <th scope="row">전용면적</th>
                            <td>${room.realSize }</td>
                        </tr>
                    </tbody>
                </table>
            </div>

            <div class="room_cost">
                <ul>
                    <li>
                        <span class="title">원룸</span>
                        <span class="info">월세 ${room.monthRent}만원</span>
                    </li>
                    <li>
                        <span class="title">전용면적</span>
                        <span class="info">${room.realSize }평</span>
                    </li>
                    <li>
                        <span class="title">보증금</span>
                        <span class="info">${room.deposit}만원</span>
                    </li>
                </ul>
            </div>

            <div class="option">
                <h2>옵션</h2>
                <div class="title">
                    <h3>있어요 <i class="fas fa-grin-hearts"></i></h3>
                    <h3>없어요 <i class="fas fa-dizzy"></i></h3>
                </div>
                <div class="option_info">
                    <ul class="having">
                    	<c:forEach var="font" items="${font }">
                    		<c:forEach var="item" items="${options}">
                    			<c:choose>
                    				<c:when test="${item.value == 'Y' && font.key == item.key }">
			                        	<li><span><i class="${font.value }"></i><c:out value="${item.key }"></c:out></span></li>
                    				</c:when>
                    			</c:choose>
                    		</c:forEach>
                    	</c:forEach>
                    </ul>
                    
                    <ul class="none">
                        <c:forEach var="font" items="${font }">
                    		<c:forEach var="item" items="${options}">
                    			<c:choose>
                    				<c:when test="${item.value == 'N' && font.key == item.key }">
			                        	<li><span><i class="${font.value }"></i><c:out value="${item.key }"></c:out></span></li>
                    				</c:when>
                    			</c:choose>
                    		</c:forEach>
                    	</c:forEach>
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
 
      
        
        <!-- section reviw -->
        <section class="review">
            <div class="review_wrapper">
                <h3>후기</h3>
                <div class="visit_review">
                    <div class="score">
                        <div class="rank">
                            <h3>방문 만족도</h3>
                            <span>${reviewScore }</span>
                            <div class="graph">
                                <span style="width: ${percent}%">${percent}%</span>
                            </div>
                        </div>
                        <div class="count">
                            <h3>방문자 수</h3>
                            <span>${visitCnt }</span>
                        </div>
                    </div>
                </div>



                <div class="total_review">
                    <ul id="replyListArea">
                    <!-- 후기 작성자가 아닌 사람이 보는 부분 -->
                        <li class="reply-row">
                            <div class="reply_info">
                                <span class="score">후기</span>
                                <span class="rWriter">내방 회원님</span><span class="rDate"></span>
                            </div>
                            <p class="comment rContent">
                                	내 방 후기는 회원님들만 이용 가능합니다.
                                	로그인 후 이용해주세요.
                            </p>
                        </li>
                        
                        
                        <%-- <!-- 후기 작성자가 보는 부분 -->
                        <li class="reply-row">
                            <div class="reply_info">
                                <span class="score">방문 만족도: 5</span>
                                <span class="rWriter">닉네임</span><span class="rDate">2021.01.01</span>
                            </div>
                            <p class="comment rContent">
                                	${room.roomNo }
                            </p>
                            
                            <div class="replyBtnArea">
								<button class="btn btn-primary btn-sm ml-1" id="updateReply" onclick="showUpdateReply(2, this)">수정</button>
								<button class="btn btn-primary btn-sm ml-1" id="deleteReply" onclick="deleteReply(2)">삭제</button>
							</div>
                        </li> --%>
  
                    </ul>
                </div>

		<c:if test="${!empty loginMember && (loginMember.memGrade == 'G')}">
                <div class="review_write">
                    <form action="" method="POST">
                        <div class="form-floating">
                        	<div id="review"></div>
                            <textarea class="form-control " placeholder="" id="replyContent"></textarea>
                            <input class="btn btn-primary" type="button" id="addReply" value="등록">
                          </div>
                    </form>
                </div>
                
        </c:if>
            </div>
        </section>
        
        
        
        
        
        <c:if test="${!empty loginMember && (loginMember.memGrade == 'B')}">
			<div class="update_wrapper">
				<div class="update"><a href="${contextPath }/room/roomUpdateForm.do?no=${room.roomNo }" class="btn btn-primary">수정</a></div>
				<div class="update"><a class="btn btn-danger" id="delete">삭제</a></div>
				
			</div>
        </c:if>
		<!-- footer -->
        <jsp:include page="../common/footer.jsp"></jsp:include>

    </div>
    
    <!-- sweetalert -->
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>

    <script src="${contextPath}/resources/js/roomsInfo.js"></script>

      <script src="//dapi.kakao.com/v2/maps/sdk.js?appkey=8035352f3860f77b021b6c64824a3b93&libraries=services"></script>
<script>


	
//마커를 클릭하면 장소명을 표출할 인포윈도우 입니다
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
ps.keywordSearch('${room.roomAddr }', placesSearchCB); 

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


/* <!-- ----------------------댓글영역 --> */
var loginMemberNick = "${loginMember.memNick}";
var parentRoomNo = ${room.roomNo};
var memNo = "${loginMember.memNo}";

// 페이지 로딩 완료 시 댓글 목록 호출
selectReplyList();


// 해당 게시글 댓글 목록 조회 함수(ajax)
function selectReplyList(){
	$.ajax({
		url : "${contextPath}/review/selectList.do",
		data : {"parentRoomNo" : parentRoomNo},
		type : "post",
		dataType : "JSON", // 응답 형태가 JSON이다. 
		success : function(rList){ // 통신이 성공했을 때
			//console.log(rList);
		
			$("#replyListArea").html("");
		
			$.each(rList, function(index, item){
				
				var li = $("<li>").addClass("reply-row");
				var div = $("<div>").addClass("reply_info");
				var rating = $("<span>").addClass("score").text("방문 만족도 : " + item.rating)
				var rWriter = $("<span>").addClass("rWriter").text("닉네임 : " + item.memNick);
				var rDate = $("<span>").addClass("rDate").text("작성일 : " + item.createDt);
				
				
				div.append(rating).append(rWriter).append("<br>").append(rDate);
				
				var rContent = $("<p>").addClass("comment rContent").html(item.content);
				
				li.append(div).append(rContent);
	
				$("#replyListArea").append(li); 
			});
	
		},
		error : function(){
			console.log("댓글 목록 조회 실패");
		}
	});
}



var rate;
//별점
	$("#review").rating({
	  "color":"#EE9BA3",
	  "emptyStar":"far fa-star",
	  "filledStar":"fas fa-star",
	  "click":function (e) {
	    console.log(e.stars);
	    rate = e.stars;
	  }
	});


	// 댓글 등록 (ajax)
$("#addReply").on("click", function(){

   // 댓글 내용을 얻어와서 변수에 저장
   var replyContent = $("#replyContent").val().trim();
   
   $.ajax({
       url : "${contextPath}/review/chkVisit.do",
       data : {"memNo" : memNo,
             "parentRoomNo" : parentRoomNo},
       type : "post",
       
       success : function(result){
          if(result < 1){ // 승낙받지 않은 경우
        	  Swal.fire({
    			  title: '후기를 작성할 수 없습니다.',
    			  text: "방문 후 후기를 작성할 수 있습니다.",
    			  icon: 'warning',
    			  confirmButtonColor: '#EE9BA3'
    			});
          }else { // 승낙받은경우
              
              // 댓글 내용이 작성되어있는지 확인
              if(replyContent.length == 0){
            	  Swal.fire({
        			  title: '댓글 작성 후 등록해주세요',
        			  text: "",
        			  icon: 'warning',
        			  confirmButtonColor: '#EE9BA3'
        			});
              
              } else { // 승낙도 되어있고, 댓글도  작성되어있는 경우
                 
                 // 회원 번호를 얻어와서 변수에 저장
                 var replyWriter = "${loginMember.memNo}";
                 
                 $.ajax({
                    url : "${contextPath}/review/insertReply.do",
                    data : {"replyWriter" : replyWriter,
                          "replyContent" : replyContent,
                          "parentRoomNo" : parentRoomNo,
                          "rating" : rate},
                    type : "post",
                    
                    success : function(result){
                       if(result >0){ // 댓글 삽입 성공 시
                          // 댓글 작성 내용 삭제
                          $("#replyContent").val("");
                       
                          // 성공 메세지 출력
                          Swal.fire({
		        			  title: '작성 성공!',
		        			  text: "",
		        			  icon: 'success',
		        			  confirmButtonColor: '#EE9BA3'
		        			});
                          
                          // 댓글 목록을 다시 조회 -> 새로 삽입한 댓글도 조회하여 화면에 출력
                          selectReplyList();
                          
                       }
                       
                    },
                    
                    error : function(){
                       console.log("댓글 등록 실패");
                    }
                 });
              }
           }
          
       },
       
       error : function(){
          console.log("조회 실패");
       }
    });

});





	// 쪽지 보내기
	
	$("#msg").on('click', ()=>{
		
		Swal.fire({
		  title: '쪽지 보내기',
		  input: 'text',
		  inputAttributes: {
		    autocapitalize: 'off'
		  },
		  showCancelButton: true,
		  confirmButtonText: '보내기',
		  showLoaderOnConfirm: true,
		  preConfirm: (login) => {
			if(login != ''){
		  		
			  	$.ajax({
			 		url : "${contextPath}/message/messageSend.do",
					type : "post",
					data : {
						"msgContext" : login,
						"brokerNo" : ${room.gMemNo},
			 			"myNo" : ${loginMember.memNo}
						},
					success : function(result){
						if(result.length > 0){
							
							Swal.fire({
								  position: 'center',
								  icon: 'success',
								  title: '쪽지를 전달 했습니다!',
								  showConfirmButton: false,
								  timer: 1500
							})		
						}
						
					}, error : function(){
						console.log("댓글 수정 실패");
					}		
				});
			  	
			}
		}
	})
		
});
	




$('#report').on('click', () => {
	if($('.reportspan').html() == '<i class="fas fa-siren-on" aria-hidden="true"></i>신고됨'){
		Swal.fire('이미 신고한 매물입니다!', '', 'warning');
	}else{
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
	        '<input type="radio" name="report" value="4">'+
	        '<label for="other">기타사유</label>'+
	        '<strong id="rpinfo">신고 내용</strong>'+
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
		if(result.isConfirmed){
			
		
		$.ajax({
			 		url : "${contextPath}/report/reportSend.do",
					type : "post",
					data : {
						"reportTitle" : $("#reportTitle").val(),
						"reportInfo" : $("#other").val(),
						"roomNo" :  ${room.roomNo },
						"category" :$("input[name=report]").val()
						
						},
					success : function(result){
						Swal.fire('신고했습니다!', '', 'success')
						$('.reportspan').html('<i class="fas fa-siren-on"></i>신고됨');
					}, error : function(){
						console.log("신고 실패");
					}		
				});
		 


		}
		
	  });
	  
}

	});
	
reportChk();
	
function reportChk(){
	$.ajax({
 		url : "${contextPath}/report/reportChk.do",
		type : "post",
		data : {
			"roomNo" : ${room.roomNo },
			"memNo" : ${loginMember.memNo}
			
			},
		success : function(result){
			
			if(result>0){
				$('.reportspan').html('<i class="fas fa-siren-on"></i>신고됨');
				$('#report').unbind('click', false);
			} else{
				$('.reportspan').html('<i class="fas fa-siren-on"></i>신고');
			}
			
		}, error : function(){
			console.log("실패");
		}		
	});
}
	
	// 방문신청
	$("#visit").on("click",()=>{
		Swal.fire({
			  title: '방문 신청 하시겠습니까?',
			  text: " ",
			  icon: 'question',
			  showCancelButton: true,
			  confirmButtonColor: '#3085d6',
			  cancelButtonColor: '#d33',
			  confirmButtonText: '네'
			}).then((result) => {
			  if (result.isConfirmed) {
				  console.log(result);
				  $.ajax({
				 		url : "${contextPath}/visit/visitSend.do?no=${room.roomNo }",
						type : "post",
						data : {"result":1 },
						success : function(result){
							console.log(result)
							if(result > 0){
								Swal.fire(
									'방문 신청했습니다!',
									'방문시간을 지켜주세요',
									'success'
								);
							}else{
								Swal.fire(
									'이미신청했습니다',
									'',
									'info'
								);
							}
							
						}, error : function(){
							console.log("신청 실패");
						}		
					});
				  
				  
			    
			  }
			})
			
	})
	
	
	
	
	/* 찜하기 */
	$("#heart").on("click", function(){
		$.ajax({
	 		url : "${contextPath}/member/heart.do",
			type : "post",
			data : {
				"roomNo" : ${room.roomNo },
				"memNo" : ${loginMember.memNo}
				
				},
			success : function(result){
				
				if(result>0){
					$('.heartspan').html('<i class="fas fa-heart"></i>찜취소');
				} else if(result < 0){
					$('.heartspan').html('<i class="far fa-heart"></i>찜하기');
				}else{
					swal.fire('실패', '', 'error');
				}
				
			}, error : function(){
				console.log("실패");
			}		
		});
	});
	
	
	function heartChk(){
		$.ajax({
	 		url : "${contextPath}/member/heartChk.do",
			type : "post",
			data : {
				"roomNo" : ${room.roomNo },
				"memNo" : ${loginMember.memNo}
				
				},
			success : function(result){
				
				if(result>0){
					$('.heartspan').html('<i class="fas fa-heart"></i>찜취소');
				} else{
					$('.heartspan').html('<i class="far fa-heart"></i>찜하기');
				}
				
			}, error : function(){
				console.log("실패");
			}		
		});
	}

	heartChk();

	
	// 삭제
	$("#delete").on('click', () => {
		Swal.fire({
			  title: '정말 삭제하시겠습니까??',
			  text: "삭제 하면 끝",
			  icon: 'warning',
			  showCancelButton: true,
			  confirmButtonColor: '#3085d6',
			  cancelButtonColor: '#d33',
			  confirmButtonText: 'Yes, delete it!'
			}).then((result) => {
			  if (result.isConfirmed) {
				  
				  $.ajax({
				 		url : "${contextPath}/room/delete.do?no=${room.roomNo }",
						type : "post",
						success : function(result){
							console.log("성공")
							
						}, error : function(){
							console.log("실패");
						}		
					});
				  
				  window.location.href="${contextPath }/broker/brokerInfo.do?no=${memNo}";

			  }
			})
	}); 



</script>
</body>
</html>