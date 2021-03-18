<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="shortcut icon" sizes="16x16 32x32 64x64"
	href="${contextPath}/resources/images/logo.png" />
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
	href="${contextPath}/resources/css/member_manage.css">

<title>회원 정지/복구</title>
<style>
input[type="checkbox"]{
		width:30px;
		height:30px;
	}
	
	input[type="checkbox"]:hover{
	cursor: pointer;
	}
.button-area{
	height:70px;
	margin-right : 15%;
}
#trList td:last-child span{
		color : red;
		font-weight : bold;
	}
#membertable{
	text-align : center;
}
		
	
</style>


</head>
<body>
	<jsp:include page="../common/header.jsp"></jsp:include>
	
	


	<div class="section">

		<div class="top-section mainFont">
			<h2>회원 관리</h2>
		</div>

		<div class="search-area ft">

			<form action="${contextPath}/searchMember.do" method="GET" class="search-form" id="searchForm">

				<select name="sk1" class="form-control"
					style="width: 140px; display: inline-block;">
					<option value="gMem">일반회원</option>
					<option value="bMem">중개사 회원</option>
				</select> 
				
				<select name="sk2" class="form-control"
					style="width: 90px; display: inline-block;">
					<option value="active">활동</option>
					<option value="stop">정지</option>
				</select> 
				
				<button class="form-control btn btn-primary search-btn"
					style="width: 60px; display: inline-block;">검색</button>
			</form>


		</div>



		<div class="content-area ft">
				<table class="table table-striped table-hover " id="membertable">
					<thead>
						<tr>
							<th><input type="checkbox" name="ck"
								onclick='selectAll(this)'></th>
							<th>회원 번호</th>
							<th>구분</th>
							<th>아이디 | 닉네임</th>
							<th>상태</th>
						</tr>
					</thead>
					
						<!-- 게시글 목록 -->
					<tbody>
					<c:choose>
						<c:when test="${empty mList}">
							<tr>
								<td colspan = "5">존재하는 회원이 없습니다.</td>
							</tr>
						</c:when>
						<c:otherwise>
						
						
							<c:forEach var="member" items="${mList}">
								<tr id="trList">
									<td>
									<input type="checkbox" name="ck" class="selectReply" value="${member.memNo}">
									<input type ="hidden" value="${member.memNo}" class="boardNo">
									</td>
									<td>${member.memNo}</td>
									<td>
										<c:if test="${member.memGrade == 'G'}">
											일반회원
										</c:if>
										<c:if test="${member.memGrade == 'B'}">
											공인중개사회원
										</c:if>
									</td>
									<td>
									${member.memId } | ${member.memNick}
									</td>
									<td>
										<c:if test="${member.memStateFl == 'Y' }">
										<span>정지</span>
										</c:if>
										<c:if test="${member.memStateFl == 'N' }">
										활동
										</c:if>	
									</td>
								</tr>
								</c:forEach>
							</c:otherwise>
						</c:choose>		

					</tbody>
				</table>
			</div>		
			<br>
				
				
				
		<div class="button-area">
		
			<c:if test="${loginAdmin.adminGrade =='A' }">
				<c:choose>
					<c:when test="${param.sk2 == 'active'}">
						<button class="btn btn-danger float-right stop-btn" 
						id="stopbtn">정지</button>
					</c:when>				
					<c:when test="${param.sk2 == 'stop'}">	
						<button class="btn btn-danger float-right recover-btn"
						id="recoverbtn">복구</button>
					</c:when>
					<c:otherwise>
						<button class="btn btn-primary float-right stop-btn" 
						id="stopbtn">정지</button>
						<button class="btn btn-primary float-right recover-btn"
						id="recoverbtn">복구</button>
					</c:otherwise>
				</c:choose>
			</c:if>	
		</div>
	
	</div>

	
		<%----------------- Pagination -------------------%>
		
		<c:choose>

			<c:when test="${!empty param.sk1}">
				<c:url var="pageUrl" value="/searchMember.do" />
				<c:set var="searchStr" value="&sk1=${param.sk1}&sk2=${param.sk2}" />
			</c:when>

			<c:otherwise>
				<c:url var="pageUrl" value="/member/list.do" />
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






	<script>
        function selectAll(selectAll) {
            const selectReply = document.getElementsByName('ck');
            selectReply.forEach((checkbox) => {
                checkbox.checked = selectAll.checked;
            })
        }
	
        
			$("#stopbtn").on("click", function(){
        	
        	var list = [];
        	
        	$("input:checkbox[name='ck']:checked").length
            
            $('input[type="checkbox"]:checked').each(function (index) {
            		if($(this).val() != "on"){
	  					list.push($(this).val());
            		}	
            });
                //console.log(list);
        	
	       $.ajax({
				url : "${contextPath}/member/stopMember.do",
				data : {"numberList" : list.join()},
				
				type : "post",
				
				success : function(result){
					
					if(result > 0){ 
						swal({icon : "success" , 
				        	title : "회원 정지 성공", 
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
					console.log("회원 정지 실패");
				}
			}); 
	        	
        	
        });
        
        
			
			
			$("#recoverbtn").on("click", function(){
	        	
	        	var list = [];
	        	
	        	$("input:checkbox[name='ck']:checked").length
	            
	            $('input[type="checkbox"]:checked').each(function (index) {
	            		if($(this).val() != "on"){
		  					list.push($(this).val());
	            		}	
	            });
	                //console.log(list);
	        	
		       $.ajax({
					url : "${contextPath}/member/recoverMember.do",
					data : {"numberList" : list.join()},
					
					type : "post",
					
					success : function(result){
						
						if(result > 0){ 
							swal({icon : "success" , 
					        	title : "회원 복구 성공", 
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
						console.log("회원 복구 실패");
					}
				}); 
		        	
	        	
	        });
        
			//검색 조건값 고정하기
		(function(){
						
						var searchKey1 = "${param.sk1}";
						
						$("select[name=sk1] > option").each(function(index, item){
							
							if( $(item).val() == searchKey1){
								$(item).prop("selected", true);
							}
						});
						
						
					})();	
			
		//검색 조건값 고정하기
		(function(){
			
			var searchKey2 = "${param.sk2}";
			
			$("select[name=sk2] > option").each(function(index, item){
				
				if( $(item).val() == searchKey2){
					$(item).prop("selected", true);
				}
			});
			
			
		})();	
    </script>


</body>
</html>