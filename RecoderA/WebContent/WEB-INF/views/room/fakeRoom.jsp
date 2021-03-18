<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>

<link rel="shortcut icon" sizes="16x16 32x32 64x64" href="img/logo.png" />
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- jquery  -->
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>

<!-- bootStrap -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>

<link rel="stylesheet" href="${contextPath}/resources/css/header.css">
<link rel="stylesheet" href="${contextPath}/resources/css/fake_room.css">
<title>허위매물관리</title>

<style>
input[type="checkbox"] {
	width: 30px;
	height: 30px;
}
input[type="checkbox"]:hover{
	cursor: pointer;
	}
</style>
</head>

<body>
	<jsp:include page="../common/header.jsp"></jsp:include>

	<div class="section">
		<div class="report-manage mainFont">
			<h2>허위 매물 관리</h2>
		</div>

		<div class="search-area ft">
			<form action="${contextPath}/searchReportRoom.do" method="GET" class="search" name="searchForm" id="searchForm">
				<input type="text" name="sv" class="search-input" placeholder="제목으로 검색">
				<button class="search-button btn btn-primary" id="searchBtn">검색</button>
			</form>
		</div>


		<c:choose>
			<c:when test="${empty fList}">

			
			</c:when>

			<c:otherwise>
				<c:forEach var="room" items="${fList}">
					<div class="total-item-area ft">
						<div class="check-area">
							<input type="checkbox" name="ck" id="check-item" value="${room.roomNo}">
						</div>

						<div class="item-area">
							<div class="item-info-area">
								<div class="image-area">
									<img src="/Recoder/resources/images/rooms/${room.roomImgName}">
								</div>
								<div class="title-info-area">
									<div class="title-area">
										<div class="item-title">
											매물 제목
										</div>

										<div class="item-name">
											<span>${room.roomTitle}</span>
										</div>
									</div>
									<div class="info-area">
										<div class="item-info">
											신고회수 : ${room.reportCount}  |  등록자 : ${room.brokerNick}
										</div>
										<div class="item-information">
											<span> ${room.roomInfo} </span>
										</div>
									</div>
								</div>

							</div>
							<div class="table-area">

								<%-- ${rList} --%>
										<table class="table table-striped table-hover" id="list-table">
											<tr>
												<th>카테코리</th>
												<th>신고 내용</th>
												<th>신고한 회원</th>
											</tr>
								<c:forEach var="report" items="${rList}">
									<c:if test="${room.roomNo == report.roomNo}">

											<tr>
												<td>${report.categoryName}</td>
												<td>${report.reportContent}</td>
												<td>${report.memNick}</td>
											</tr>

									</c:if>
								</c:forEach>
										</table>

							</div>
						</div>

					</div>

					<div class="button-area">
					<c:if test="${loginAdmin.adminGrade =='A' }">
						<button class="btn btn-primary float-right delete-item delete-item" id="deleteBtn">삭제</button>
					</c:if>
					</div>
					<br>
					<br>
				</c:forEach>
			</c:otherwise>
		</c:choose>



				<c:choose>
			
					<%-- 검색 내용이 파라미터에 존재 할 때 == 검색을 통해 만들어진 페이지인가?--%>
				<c:when test="${!empty param.sv }">
					<c:url var="pageUrl" value="/searchReportRoom.do"/>				
					
					
					<%-- 쿼리스트링으로 사용할 내용을 변수에 저장 --%>
					<c:set var="searchStr" value="&sv=${param.sv}" />
				</c:when>
				
				<c:otherwise>
					<c:url var="pageUrl" value="/room/fakeRoom.do"/>
				</c:otherwise>
				
		</c:choose>
		<%-- 첫 페이지로 돌아가는 화살표 주소 --%>
		<c:set var="fistPage" value="${pageUrl}?cp=1${searchStr}"/>
		<%-- 마지막 페이지로 돌아가는 화살표 주소 --%>
		<c:set var="lastPage" value="${pageUrl}?cp=${pInfo.maxPage}${searchStr}"/>
			
		<fmt:parseNumber var="c1" value="${(pInfo.currentPage - 1) / 10 }" integerOnly="true" />
		<fmt:parseNumber var="prev" value="${ c1 * 10 }" integerOnly="true" />
		
		<c:set var="prevPage" value="${pageUrl}?cp=${prev}${searchStr}"/>
		
		<fmt:parseNumber var="c2" value="${(pInfo.currentPage + 9) / 10 }" integerOnly="true"/>
		<fmt:parseNumber var="next" value="${c2 * 10 + 1}" integerOnly="true"/>
		
		<c:set var="nextPage" value="${pageUrl}?cp=${next}${searchStr}"/>
		
		
		<div class="my-5">
			<ul class="pagination">
			
			<c:if test="${pInfo.currentPage > 10}">
				<li>
					<a class="page-link" href="${fistPage}">&lt;&lt;</a>
				</li>
				<li>
					<a class="page-link" href="${prevPage}">&lt;</a>
				</li>
			</c:if>
			
			<c:forEach var="page" begin="${pInfo.startPage}" end="${pInfo.endPage}">
				<c:choose>						<%--page는 1부터 10까지 --%>
					<c:when test="${pInfo.currentPage == page}"> <%--페이지가 현재페이지와 같을 경우 --%>
						<li>		
							<a class="page-link">${page}</a> 
						</li>
					</c:when>
					
				<c:otherwise>
					<li>
						<a class="page-link" href="${pageUrl}?cp=${page}${searchStr}">${page}</a>	
					</li>
				</c:otherwise>
				</c:choose>
					
			</c:forEach>			
				<c:if test="${next <= pInfo.maxPage}">
					<li>
						<!-- 다음 페이지로 이동 (>) -->
						<a class="page-link" href="${nextPage}">&gt;</a>
					</li>
					<li>
						<!-- 마지막 페이지로 이동(>>) -->
						<a class="page-link" href="${lastPage}">&gt;&gt;</a>
					</li>
				</c:if>

			</ul>
		</div>


	</div>
	
	<script>
	
	$(".delete-item").on("click", function(){
    	
    	
    	
    	var RoomNo = $("input:checkbox[name='ck']:checked").val()
        
    	console.log(RoomNo);
       
    	
       $.ajax({
			url : "${contextPath}/room/deleteFakeRoom.do",
			data : {"RoomNo" : RoomNo},
			
			type : "post",
			
			success : function(result){
				
				//console.log(result);
				
				if(result > 0){ 
					swal({icon : "success" , 
			        	title : "삭제 성공", 
			        	buttons : {confirm : true}}
			        ).then((result) => {
				        	if(result) {
								location.reload();
				        	}	
				        }
			        );
					
				}
			},
			error : function(){
				console.log("삭제 실패");
			}
		}); 
        	
    	
    });
		
		(function(){
			
			var searchValue = "${param.sv}";
			
			
			// 검색어 입력창에 searchValue 값 출력
			$("input[name=sv]").val(searchValue);
			
		})();	
    
	
	
	
	
	</script>
	
	

</body>
</html>