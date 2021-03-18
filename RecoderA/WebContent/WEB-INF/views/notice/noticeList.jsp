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
	href="${contextPath}/resources/css/noticeList.css">
<title>공지사항 게시판</title>
<style>
	.button-area{
		margin-right: 15%;
	}
	input[type="checkbox"]{
		width:30px;
		height:30px;
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

			<h2>공지사항</h2>
		</div>



		<div class="search-area ft">

			<form action="${contextPath}/searchNotice.do" method="GET" class="search-form" id="searchForm">

				<input type="text" name="sv" class="form-control"
					style="width: 150px; display: inline-block;" placeholder="제목으로 검색">
				<button class="form-control btn btn-primary search-btn"
					style="width: 60px; display: inline-block;">검색</button>
			</form>


		</div>




		<div class="content-area ft">
			<table class="table table-striped table-hover" id="list-table">
				<thead>
					<tr>
						<th><input type="checkbox" name="allNotice"
							onclick='selectAll(this)'></th>
						<th>공지글 번호</th>
						<th>공지글 제목</th>
						<th>작성일</th>
					</tr>
				</thead>

				<tbody>
					<!-- 게시글 목록 -->

					<c:choose>
						<c:when test="${empty nList}">
							<tr>
								<td colspan="4">존재하는 게시글이 없습니다.</td>
							</tr>
						</c:when>

						<c:otherwise>
							<c:forEach var="notice" items="${nList}">
								<tr>
									<td>
									<input type="checkbox" name="ck" class="selectNotice" value="${notice.noticeNo}">
									<input type ="hidden" value="${notice.noticeNo}" class="noticeNo">
									</td>
									<td>${notice.noticeNo}</td>
									<td>${notice.noticeTitle}</td>
									<td>
									
									<fmt:formatDate var="createDate"
											value="${notice.createDate}" pattern="yyyy-MM-dd" /> <fmt:formatDate
											var="today" value="<%=new java.util.Date()%>"
											pattern="yyyy-MM-dd" /> <c:choose>
											<%-- 오늘 작성된 것이 아니라면,--%>
											<c:when test="${createDate != today}">
													${createDate}
												</c:when>

											<%-- 글 작성일이 오늘일 경우 --%>
											<c:otherwise>
												<fmt:formatDate value="${notice.createDate}"
													pattern="HH:mm" />
											</c:otherwise>
										</c:choose>
										
									</td>
								</tr>
							</c:forEach>
						</c:otherwise>
					</c:choose>


				</tbody>

			</table>
		</div>

		
		<div class="button-area">
			
			<c:if test="${loginAdmin.adminGrade =='A' }">
			<button class="btn btn-primary float-right delete-btn" id="deleteBtn">삭제</button>
			
			<a class="btn btn-primary float-right write-btn" id="writeBtn"
				 onclick="location.href = 'insertForm.do';">등록</a>
			</c:if>	

		</div>

		<c:choose>
			
					<%-- 검색 내용이 파라미터에 존재 할 때 == 검색을 통해 만들어진 페이지인가?--%>
				<c:when test="${!empty param.sv }">
					<c:url var="pageUrl" value="/searchNotice.do"/>				
					
					
					<%-- 쿼리스트링으로 사용할 내용을 변수에 저장 --%>
					<c:set var="searchStr" value="&sv=${param.sv}" />
				</c:when>
				
				<c:otherwise>
					<c:url var="pageUrl" value="/notice/list.do"/>
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
        /*  */
        function selectAll(selectAll) {
            const selectReply = document.getElementsByName('ck');
            selectReply.forEach((checkbox) => {
                checkbox.checked = selectAll.checked;
            })
        }
		
        
        
        
			$("#deleteBtn").on("click", function(){
        	
        	var list = [];
        	
        	$("input:checkbox[name='ck']:checked").length
            
            $('input[type="checkbox"]:checked').each(function (index) {
            		if($(this).val() != "on"){
	  					list.push($(this).val());
            		}	
            });
                console.log(list);
        	
	       $.ajax({
				url : "${contextPath}/notice/deleteNotice.do",
				data : {"numberList" : list.join()},
				
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