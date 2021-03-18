<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="contextPath" value="${pageContext.servletContext.contextPath }" scope="application"></c:set>

<!DOCTYPE html>
<html lang="kr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">

    <!-- reset -->
    <link rel="stylesheet" href="${contextPath}/resources/css/reset.css">
    
    <!-- slick.css -->
    <link rel="stylesheet" href="${contextPath}/resources/css/slick.css">
    
    <!-- visitCheck.css -->
    <link rel="stylesheet" href="${contextPath}/resources/css/visitCheck.css">
    
    
    <!-- jQuery -->
    <script src="http://code.jquery.com/jquery-3.5.1.min.js" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>
    
    <!-- slick.js -->
    <script src="${contextPath}/resources/js/slick.min.js"></script>

    <!-- font awesome -->
    <script src="https://kit.fontawesome.com/70c929d7d4.js" crossorigin="anonymous"></script>

    
    <title>내 방</title>
</head>
<body>
    <div class="wrapper">
        <!-- header -->
		<jsp:include page="../common/header.jsp"></jsp:include>

        
        <section class="visit_check">
            <h3>방문자 체크</h3>
            <div class="chk_wrapper">
                <ul>
                    <c:forEach var="room" items="${room}">
                    <li>
                        <div class="list_top">
                            <p class="check_img">
                            	<c:forEach var="rImg" items="${rImg }">
                            		<c:if test="${rImg.roomNo == room.roomNo}" var="nameHong" scope="session">
	                               		<a href=""><img src="${contextPath}/resources/images/rooms/${rImg.pet}" alt=""></a>
	                               	</c:if>
                            	</c:forEach>
                            </p>
                            <div class="check_info">
                                <h3>${room.roomTitle}</h3>
	                                <p class="text">
	                                    <span>
	                                        ${room.roomInfo }
	                                    </span>
	                                </p>
                                <a href="${contextPath}/room/view.do?no=${room.roomNo}" class="more">View more</a>
                            </div>
                        </div>
                        <div class="list_bottom">
                            <table class="table">
                                <thead>
	                                <tr>
	                                  <th scope="col">신청 날짜</th>
	                                  <th scope="col">회원 닉네임</th>
	                                  <th scope="col">승낙 여부</th>
	                                </tr>
                                  </thead>
                                  <tbody>
                                    <c:forEach var="visit" items="${visit}" varStatus="vst">
			                            <c:if test="${visit.roomNo == room.roomNo}" var="nameHong" scope="session">
                                    <tr>
                                        <td>
                                        	<fmt:formatDate value="${visit.visitDt }" pattern="yyyy년 MM월 dd일 "/>
                                        </td>
                                        <td>${visit.memName }</td>
                                        <td>
                                        	<c:if test="${visit.visitCd == 1}" var="nameHong" scope="session">
                                            	<button class="visitok" value="${room.roomNo }" name="${visit.memNo}">방문 승낙</button>
                                            	<input type="hidden" value="${room.roomNo }">
                                            </c:if>
                                            
                                        </td>
                                    </tr>
                                        </c:if>
	                                 </c:forEach>
                                  </tbody>
                              </table>
                        </div>
                    </li>
                    </c:forEach>
                    <c:if test="${empty room }">
                    <li>
                       <h1 style="text-align: center; font-size: 50px; margin: 300px 0 800px;">방문신청이 없습니다.</h1>
                    </li>
                    </c:if>
                </ul>
            </div>

        </section>
        
        <!-- footer -->
        <jsp:include page="../common/footer.jsp"></jsp:include>
    </div>
    
    <!-- index.js -->
    <script src="${contextPath}/resources/js/visitCheck.js"></script>
    <script>
    	$(".visitok").on('click', function(e){
    		var visitRoomNo = e.target.value
    		var visitMemNo = e.target.name
    		console.log(visitRoomNo);
    		console.log(visitMemNo);
    		
    		$.ajax({
    	 		url : "${contextPath}/visit/visitAccept.do",
    			type : "post",
    			data : {
    				"roomNo" : visitRoomNo,
    				"memNo": visitMemNo 
    				},
    			success : function(result){
    				
	    		location.reload();
    				
    			}, error : function(){
    				
    			}		
    		});
    		
    	});
    </script>
</body>
</html>