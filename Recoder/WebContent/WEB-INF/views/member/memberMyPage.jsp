<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath"
	value="${pageContext.servletContext.contextPath }" scope="application"></c:set>


<!DOCTYPE html>
<html lang="kr">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">

<!-- reset -->
<link rel="stylesheet" href="${contextPath}/resources/css/reset.css">

<!-- bootstrap -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
	integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2"
	crossorigin="anonymous">

<!-- brokerInfo.css -->
<link rel="stylesheet"
	href="${contextPath}/resources/css/brokerInfo.css">

<!-- slick.css -->
<link rel="stylesheet" href="${contextPath}/resources/css/slick.css">


<!-- jQuery -->
<script src="http://code.jquery.com/jquery-3.5.1.min.js"
	integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
	crossorigin="anonymous"></script>

<!-- slick.js -->
<script src="${contextPath}/resources/js/slick.min.js"></script>

<!-- font awesome -->
<script src="${contextPath}/resources/js/fontawesome.js"></script>

<title>내 방</title>
</head>
<body>
	<div class="wrapper">
		<!-- header -->
		<!-- WEB-INF/views/common/header.jsp 여기에 삽입(포함) -->
		<jsp:include page="../common/header.jsp"></jsp:include>


		<section class="broker_mypage">
			<h1>일반회원 마이 페이지</h1>
			<div class="broker_info">
				<div class="broker_card clearfix">
					<table class="table table-dark">
						<thead>
							<tr>
								<th colspan="4">${loginMember.memNick }님의정보</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th scope="row"><i class="fas fa-phone-office"></i></th>
								<td colspan="3">${loginMember.memTel }</td>
							</tr>
							<tr>
								<th scope="row"><i class="fas fa-at"></i></th>
								<td colspan="3">${loginMember.memEmail }</td>
							</tr>
						</tbody>
					</table>

					<button class="btn btn-danger" id="delete">회원 탈퇴</button>
					<div class="broker_link clearfix">
						<a id="modify"><span>내 정보 수정</span></a>
					</div>
				</div>
			</div>
		</section>

		<section class="room_manager">
			<h1>찜한 매물</h1>
			<div class="room_wrapper clearfix">
				<div class="room_list autoplay clearfix">
					<c:forEach var="room" items="${roomList}">
						<c:forEach var="thumbnail" items="${imgList}" varStatus="status">
								<c:if test="${room.roomNo == thumbnail.parentRoomNo}">
										<div class="room">
											<p class="img">
												<a href="${contextPath}/room/view.do?no=${room.roomNo }"><img
													src="${contextPath}/resources/images/rooms/${thumbnail.roomImgName}" id="${room.roomNo }" alt=""></a>
											</p>
											<h3>${room.roomTitle}</h3>
											
											
											<a href="${contextPath}/room/view.do?no=${room.roomNo }" class="more">더보기</a>
										</div>
									
								</c:if>
						</c:forEach>
					</c:forEach>
						
						
						

				</div>
				<%-- <div class="add_room">
					<a href="${contextPath }/room/roomInsertForm.do">매물 등록${pwch }</a>
				</div> --%>
			</div>
		</section>
		
		
		<section class="room_manager">
			<h1>후기 남긴 매물</h1>
			<div class="room_wrapper clearfix">
				<div class="room_list autoplay clearfix">
					<c:forEach var="room" items="${reviewRoomList}">
						<c:forEach var="thumbnail" items="${reviewImgList}" varStatus="status">
								<c:if test="${room.roomNo == thumbnail.parentRoomNo}">
										<div class="room">
											<p class="img">
												<a href="${contextPath}/room/view.do?no=${room.roomNo }"><img
													src="${contextPath}/resources/images/rooms/${thumbnail.roomImgName}" id="${room.roomNo }" alt=""></a>
											</p>
											<h3>${room.roomTitle}</h3>
											
											<a href="${contextPath}/room/view.do?no=${room.roomNo }" class="more">더보기</a>
										</div>
									
								</c:if>
						</c:forEach>
					</c:forEach>

				</div>
			</div>
		</section>

		<!-- footer -->
		<jsp:include page="../common/footer.jsp"></jsp:include>

	</div>


	<!-- sweetalert -->
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>

	<!-- index.js -->
	<script src="${contextPath}/resources/js/brokerInfo.js"></script>
	<script type="text/javascript">
    
    
  
        				  


	$('#modify').on('click', function () {

        Swal.fire({
            title: '비밀번호를 입력해주세요',
            input: 'password',
            inputAttributes: {
                autocapitalize: 'off'
            },
            showCancelButton: true,
            confirmButtonText: '확인',
            showLoaderOnConfirm: true,
            allowOutsideClick: () => !Swal.isLoading()
        }).then((result) => {
            if (result.isConfirmed) {
                var inputPw = result.value;


                $.ajax({
                    url: "${contextPath}/member/checkMember.do",
                    type: "post",
                    data: { "userPw": inputPw },
                    success: function (result) {
                    	console.log(result);
                        if (result > 0) {
                        	swal.fire({
                        		title : '성공',
                        		confirmButtonText: '확인',
                                showLoaderOnConfirm: true,
                                allowOutsideClick: () => !Swal.isLoading()
                                
                        	}).then((result) => {
                        		if(result.isConfirmed){
                        			location.href="http://localhost:8080/Recoder/member/updateMember.do";
                        		}
                        	}
                        	)

                        }else{
                        	Swal.fire(
									'비밀번호 오류!',
									'비밀번호가 일치하지 않습니다',
									'error'
								);
                        }

                    }
                });

            } else if (
                /* Read more about handling dismissals below */
                result.dismiss === Swal.DismissReason.cancel
            ) {
                swalWithBootstrapButtons.fire(
                    'Cancelled',
                    'Your imaginary file is safe :)',
                    'error'
                )
            }
        })
    });
        	  
        	  
    $('#delete').on('click', function(){

    Swal.fire({
    	  title: '비밀번호를 입력해주세요',
    	  input: 'password',
    	  inputAttributes: {
    	    autocapitalize: 'off'
    	  },
    	  showCancelButton: true,
    	  confirmButtonText: '확인',
    	  showLoaderOnConfirm: true,
    	  allowOutsideClick: () => !Swal.isLoading()
    	}).then((result) => {
    	  if (result.isConfirmed) {

    		  
    		  var inputPw = result.value;
    		  
    		  const swalWithBootstrapButtons = Swal.mixin({
    			  customClass: {
    			    confirmButton: 'btn btn-success',
    			    cancelButton: 'btn btn-danger'
    			  },
    			  buttonsStyling: false
    			})

    			swalWithBootstrapButtons.fire({
    			  title: '정말 탈퇴하시겠습니까?',
    			  text: "탈퇴하면 계정이 삭제됩니다",
    			  icon: 'question',
    			  showCancelButton: true,
    			  confirmButtonText: '네',
    			  cancelButtonText: '아니요',
    			  reverseButtons: true
    			}).then((result) => {
    			  if (result.isConfirmed) {
    				  
    				  
    				  $.ajax({
    						url : "${contextPath}/member/secessionMember.do",
    						type : "post",
    						data: {"userPw": inputPw},
    						success : function(result){
    							if(result > 0) {
   				    			    swalWithBootstrapButtons.fire(
   				    			      '탈퇴되었습니다',
   				    			      '이용해주셔서 감사합니다.',
   				    			      'success'
   				    			    ).then((result)=>{
   				    			    	
	   				    			    location.href="http://localhost:8080/Recoder/";
   				    			    })
   				    			    
   				    			
   								}else {
   									
   									swalWithBootstrapButtons.fire(
   				    			      '비밀번호가 일치하지 않습니다',
   				    			      '비밀번호를 다시 확인후 입력해주세요',
   				    			      'error'
   				    			    )
   								}
    							
   						}, error : function(){
   							console.log("탈퇴 실패");
   						}		
   					});
    				  
    			  } else if (
    			    /* Read more about handling dismissals below */
    			    result.dismiss === Swal.DismissReason.cancel
    			  ) {
    			    swalWithBootstrapButtons.fire(
    			      'Cancelled',
    			      '내방으로 오신 것을 환영합니다.',
    			      'success'
    			    )
    			  }
    			})
    		  
    	  }
    	})

    	
    });

    </script>
</body>
</html>