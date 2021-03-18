<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<link rel="shortcut icon" sizes="16x16 32x32 64x64"
	href="${contextPath}/resources/images/logo.png" />
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- jquery  -->
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>


<!-- bootStrap -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
	integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx"
	crossorigin="anonymous"></script>

<link rel="stylesheet" href="${contextPath}/resources/css/header.css">
<link rel="stylesheet"
	href="${contextPath}/resources/css/room_manage.css">


<title>매물 삭제/복구</title>
<style>
	input[type="checkbox"]{
		width:30px;
		height:30px;
	}
	
	#trList td:last-child p{
		color : red;
		font-weight : bold;
	}
	input[type="checkbox"]:hover{
	cursor: pointer;
	}
	
</style>


</head>
<body>

	<jsp:include page="../common/header.jsp"></jsp:include>


	<div class="section">

		<div class="top-section mainFont">
			<h6>매물관리</h6>
			<h2>매물 삭제/복구</h2>
		</div>



		<div class="search-area ft">

			<form action="${contextPath}/searchRoom.do" method="GET"
				class="search-form" id="searchForm" >

				<select name="sk" class="form-control"
					style="width: 140px; display: inline-block;">
					<option value="totalRoom">전체 매물</option>
					<option value="enrollRoom">등록된 매물</option>
					<option value="deleteRoom">삭제된 매물</option>

				</select>

				<button class="form-control btn btn-primary search-btn"
					style="width: 60px; display: inline-block;">검색</button>
			</form>


		</div>




		<div class="content-area ft">

			<table class="table table-striped table-hover ft" id="list-table">
				<thead>
					<tr>
						<th><input type="checkbox" name="allRoom"
							onclick='selectAll(this)' ></th>
						<th>매물 번호</th>
						<th>매물 제목</th>
						<th>공인중개사</th>
						<th>상태</th>
					</tr>
				</thead>

				<tbody>
					<%--Room 목록 출력 --%>
					<c:choose>
						<%--rList가 비어있을 때 --%>
						<c:when test="${empty rList}">
							<tr>
								<td colspan="5">존재하는 매물이 없습니다.</td>
							</tr>
						</c:when>

						<c:otherwise>
							<%--rList가 있을 때 --%>
							<c:forEach var="room" items="${rList}">
								<tr id="trList">
									<td><input type="checkbox" name="ck" class="selectRoom" value="${room.roomNo}">
									<input type ="hidden" value="${room.roomNo}" class="roomNo">
									</td>
									<td>${room.roomNo}</td>
									<td>${room.roomTitle}</td>
									<td>${room.gMemNick}</td>
									<td>
									
										<c:if test="${room.deleteFl == 'Y'}">
										<p>삭제</p>
										</c:if>
										<c:if test="${room.deleteFl == 'N'}">
										등록
										</c:if>
									
									</td>
								</tr>
							</c:forEach>
						</c:otherwise>
					</c:choose>

				</tbody>

			</table>
			<br> <br>
			<div class="button-area">



			<c:if test="${loginAdmin.adminGrade =='A' }">
				<c:choose>
					
				
					<c:when test="${param.sk == 'enrollRoom'}">
						<button class="btn btn-primary float-right delete-btn"
							id="deleteBtn">삭제</button>
					</c:when>
				
					<c:when test="${param.sk == 'deleteRoom'}"> 
						<button class="btn btn-primary float-right recover-btn"
							id="recoverBtn">복구</button>
					</c:when>
					
					<c:otherwise>
						<button class="btn btn-primary float-right delete-btn"
							id="deleteBtn">삭제</button>
						<button class="btn btn-primary float-right recover-btn"
						id="recoverBtn">복구</button>
					</c:otherwise>
					
					
					
				</c:choose>
			</c:if>

			</div>


		</div>



		<%----------------- Pagination -------------------%>
		<c:choose>

			<c:when test="${!empty param.sk}">
				<c:url var="pageUrl" value="/searchRoom.do" />

				<c:set var="searchStr" value="&sk=${param.sk}" />
			</c:when>

			<c:otherwise>
				<c:url var="pageUrl" value="/room/roomStatus.do" />
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
       
        function selectAll(selectAll) {
            const selectReply = document.getElementsByName('ck');
            selectReply.forEach((checkbox) => {
            checkbox.checked = selectAll.checked;
            })
        }
        
        
        // tr 클릭 시 체크
 		
        /*
         
		$("#list-table tr").click(function(){
			//.find('td:first-child : checkbox');
			// .attr('checked', "true")
			//var checkbox = $(this).find('input').prop("checked")
			var checkbox = $(this).find('input')
			if($(this).find('input').is(":checked") == false){
				$(this).find('input').attr('checked', true)
			}else {
				$(this).find('input').attr('checked', false)
				
			}
			console.log(checkbox)
			//checkbox.attr('checked', !checkbox.is(':checked'));
		});
        	
       */
        
        
        $("#deleteBtn").on("click", function(){
        	
        	var list = [];
        	
        	$("input:checkbox[name='ck']:checked").length
            
            $('input[type="checkbox"]:checked').each(function (index) {
            		if($(this).val() != "on"){
	  					list.push($(this).val());
            		}	
            });
                //console.log(list);
        	
	       $.ajax({
				url : "${contextPath}/room/deleteRoom.do",
				data : {"numberList" : list.join()},
				
				type : "post",
				
				success : function(result){
					
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
			$("#recoverBtn").on("click", function(){
					
				
        	var list = [];
        	
        	$("input:checkbox[name='ck']:checked").length
            
            $('input[type="checkbox"]:checked').each(function (index) {
            		if($(this).val() != "on"){
	  					list.push($(this).val());
            		}	
            });
                $.ajax({
    				url : "${contextPath}/room/recoverRoom.do",
    				data : {"numberList" : list.join()},
    				
    				type : "post",
    				
    				success : function(result){
    					 if(result > 0){ 
    						swal({icon : "success" , 
    				        	title : "복구 성공", 
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
    					console.log("복구 실패");
    				}
    			}); 
	        	
        });
			
		
		(function(){
			
			var searchKey = "${param.sk}";
			
			$("select[name=sk] > option").each(function(index, item){
				
				if( $(item).val() == searchKey){
					$(item).prop("selected", true);
				}
			});
			
			
		})();	
       
         
        
	
		
		
    </script>


	

</body>
</html>