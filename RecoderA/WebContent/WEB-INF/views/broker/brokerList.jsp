<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<link rel="shortcut icon" sizes="16x16 32x32 64x64" href="${contextPath}/resources/images/logo.png" />
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- jquery  -->
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    
    <!-- bootStrap -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
        integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx"
        crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>    
    <link rel="stylesheet" href="${contextPath}/resources/css/header.css">
	<link rel="stylesheet" href="${contextPath}/resources/css/brokerList.css">
	<title>공지사항 게시판</title>
	<style>
	
	input[type="checkbox"]{
		width:30px;
		height:30px;
	}
	#table-body td:nth-child(3) > img:hover {
    cursor: pointer;
	}
	input[type="checkbox"]:hover{
	cursor: pointer;
	}
	
	.swal2-styled.swal2-confirm{
	background-color : rgb(174, 160, 236) !important;
	border : rgb(174, 160, 236) !important; 
	}
	
	</style>
</head>
<body>
	
	<jsp:include page="../common/header.jsp"></jsp:include>
	
	<div class="section-area">

        <div class="top-section mainFont">

            <h2>중개사 회원 승인</h2>
        </div>


        <table class="table table-striped table-hover ft" id="list-table">
            <thead>
                <tr>
                    <th>
                        <input type="checkbox" name="ck" onclick='selectAll(this)'>
                    </th>
                    <th>회원번호</th>
                    <th>자격증 이미지</th>
                    <th>사무실 주소</th>
                    <th>공인중개사</th>

                </tr>
            </thead>

            <tbody id="table-body">
           <!--  <img src="/Recoder/resources/images/rooms/20210115153738_55413.png"> -->
            	<c:choose>
            		<c:when test="${empty bList}">
						<tr>
							<td colspan="5">승인 요청이 없습니다.</td>
						</tr>
					</c:when>
					<c:otherwise>	
						<c:forEach var ="broker" items="${bList}">
                			<tr>
			                   <td id="trList">
			                       <input type="checkbox" name="ck" value="${broker.brokerNo}">
			                       <input type ="hidden" value="${broker.brokerNo}">
			                   </td>
			                    <td> ${broker.brokerNo} </td>
			                    <td> 
			                    	<img class="certi" src="/Recoder/resources/images/brokerInfo/${broker.brokerFileName}"> </td>
			                    <td> ${broker.brokerAddr}</td>
			                    <td> ${broker.brokerNick}</td>
							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>			

            </tbody>

        </table>
		
		
		
        <div class="button-area">
        <c:if test="${loginAdmin.adminGrade =='A' }">
            <button class="btn btn-primary btn-sm ml-1 approve-btn" id="approveBtn">승인</button>
            <button class="btn btn-primary btn-sm ml-1 cancel-btn" id="deleteBtn">삭제</button>
        </c:if>    
        </div>
		
		
		
		
		<%----------------- Pagination -------------------%>
		
		
		<c:url var="pageUrl" value="/broker/list.do" />
		
		
		
       <%-- 첫 페이지로 돌아가는 화살표 주소 --%>
		<c:set var="fistPage" value="${pageUrl}?cp=1"/>
		<%-- 마지막 페이지로 돌아가는 화살표 주소 --%>
		<c:set var="lastPage" value="${pageUrl}?cp=${pInfo.maxPage}"/>
			
		<fmt:parseNumber var="c1" value="${(pInfo.currentPage - 1) / 10 }" integerOnly="true" />
		<fmt:parseNumber var="prev" value="${ c1 * 10 }" integerOnly="true" />
		
		<c:set var="prevPage" value="${pageUrl}?cp=${prev}"/>
		
		<fmt:parseNumber var="c2" value="${(pInfo.currentPage + 9) / 10 }" integerOnly="true"/>
		<fmt:parseNumber var="next" value="${c2 * 10 + 1}" integerOnly="true"/>
		
		<c:set var="nextPage" value="${pageUrl}?cp=${next}"/>
		

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
						<a class="page-link" href="${pageUrl}?cp=${page}">${page}</a>	
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
        
       
		$("#approveBtn").on("click", function(){
        	
        	var list = [];
        	
        	$("input:checkbox[name='ck']:checked").length
            
            $('input[type="checkbox"]:checked').each(function (index) {
            		if($(this).val() != "on"){
	  					list.push($(this).val());
            		}	
            });
                console.log(list);
        	
	       $.ajax({
				url : "${contextPath}/broker/approveEnroll.do",
				data : {"numberList" : list.join()},
				
				type : "post",
				
				success : function(result){
					
					if(result > 0){ 
						swal({icon : "success" , 
				        	title : "승인완료", 
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
					console.log("승인 실패");
				}
			}); 
	        	
        	
        });
        
        
		
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
			url : "${contextPath}/broker/rejectEnroll.do",
			data : {"numberList" : list.join()},
			
			type : "post",
			
			success : function(result){
				
				if(result > 0){ 
					swal({icon : "warning" , 
			        	title : "회원 정보 삭제", 
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
				console.log("승인 실패");
			}
		}); 
        	
    }); 
			
			
		
			
			$("#table-body td:nth-child(3) > img").on("click", function(){
				
				Swal.fire({
					  title: '자격증',
					  text: '공인 중개사 자격증',
					  imageUrl: $(this).attr('src'),
					  imageWidth: 400,
					  imageHeight: 600,
					  imageAlt: '이미지가 표시되지 않습니다.',
					})
				
			});
			
		
		

    </script>
</body>
</html>