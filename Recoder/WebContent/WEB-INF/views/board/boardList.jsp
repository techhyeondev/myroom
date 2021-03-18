<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.servletContext.contextPath }" scope="application"></c:set>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>자유게시판</title>
    
<!-- Bootstrap core CSS -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">

<!-- Bootstrap core JS -->
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>

    <!-- jQuery -->
   <script src="http://code.jquery.com/jquery-3.5.1.min.js" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>
   
  <link rel="stylesheet" href="${contextPath}/resources/css/boardList.css">
  
</head>
<body> 
<!-- WEB-INF/views/common/header.jsp 여기에 삽입(포함) -->
		<jsp:include page="../common/header.jsp"></jsp:include>
    <div class="container my-5">
    <br>
    <img alt="자유게시판" src="${contextPath}/resources/images/homepage/boardBanner.jpg" width="1100px" class="mt-5">
    <!-- <h1 class="title">자유게시판</h1> -->

        <div class="list-wrapper">
            <table class="table table-hover my-5" id="board-table">
            <thead>
                <tr>
                    <th>글번호</th>
                    <th class="board-title">제목</th>
                    <th class="board-title-small">작성자</th>
                    <th class="board-title-small">조회수</th>
                    <th class="board-title-small">작성일</th>
                </tr>
            </thead>

            <%-- 공지사항 목록--%>
            <tbody>
            	<c:choose>
            		<c:when test="${empty bList}">
            			<tr>
            				<td colspan="5" class="notex">존재하는 게시글이 없습니다.</td>
            			</tr>
            		</c:when>
            		
            		 <%-- 조회된 게시글 목록이 있을 때  --%>
            		<c:otherwise>
            			<c:forEach var="board" items="${bList}">
		                <tr>
		                    <td>${board.boardNo}</td>
		                    <td class="boardTitle">
		                    	<%-- <c:forEach var="thumbnail" items="${fList}">
		                    	현재 출력하려는 게시글 번호와 썸네일 목록 중 부모게시글번호가 일치하는 썸네일 정보가 있다면
                              		<c:if test="${board.boardNo == thumbnail.parentBoardNo}">
                              			<img src="${contextPath}/resources/uploadImages/${thumbnail.fileName}">
                              		</c:if>
                              	</c:forEach> --%>
                              	 ${board.title}
		                     </td>
		                    <td>${board.memNick}</td>
		                    <td>${board.readCount}</td>
		                    <td>
		                    	<%-- 날짜 출력 모양 지정 --%>
                                 <fmt:formatDate var="createDate" value="${board.createDate}"  pattern="yyyy-MM-dd"/>
                                 <fmt:formatDate var="today" value="<%= new java.util.Date() %>"  pattern="yyyy-MM-dd"/>
                                 
                                 <c:choose>
                                    <%-- 글 작성일이 오늘이 아닐 경우 --%>
                                    <c:when test="${createDate != today}">
                                       ${createDate}
                                    </c:when>
                                    
                                    <%-- 글 작성일이 오늘일 경우 --%>
                                    <c:otherwise>
                                       <fmt:formatDate value="${board.createDate}"  pattern="HH:mm"/>
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
    
     <%-- 로그인이 되어있는 경우 글쓰기 버튼 --%>
         <c:if test="${!empty loginMember}">
         <button type="button" class="btn float-right" id="insertBtn"
          onclick="location.href = '${contextPath}/board/insertForm.do'">글쓰기</button>
         </c:if>
         
         
     <%---------------------- Pagination ----------------------%>
         <%-- 페이징 처리 주소를 쉽게 사용할 수 있도록 미리 변수에 저장 --%>
         
          <c:choose>
         	<%-- 검색 내용이 파라미터에 존재할 때 == 검색을 통해 만들어진 페이지인가? --%>
         	<c:when test="${!empty param.sk && !empty param.sv }">
		         <c:url var="pageUrl" value="/boardSearch.do"/>
		         
		        <%--쿼리 스트링으로 사용할 내용을 변수에 저장--%>
		         <c:set var="searchStr" value="&sk=${param.sk}&sv=${param.sv}"/>
         	</c:when>
         	
         	<c:otherwise>
		         <c:url var="pageUrl" value="/board/list.do"/>
         	</c:otherwise>
         </c:choose>
         
         
         
         
         <!-- 화살표에 들어갈 주소를 변수로 생성----------- -->
         
         <!-- 
         	검색 안했을 때 : /board/list.do?cp=1  (el은 null Pointer Exception 발생안함)
         	검색 했을 때 : /search.do?cp=1&sk=title&sv=49
          -->
         <c:set var="firstPage" value="${pageUrl}?cp=1${searchStr}"/>
         <c:set var="lastPage" value="${pageUrl}?cp=${pInfo.maxPage}${searchStr}"/>
          
         <%-- EL을 이용한 숫자 연산의 단점 : 연산이 자료형에 영향을 받지 않는다--%>
         <%-- 
            <fmt:parseNumber>   : 숫자 형태를 지정하여 변수 선언
            integerOnly="true": 정수로만 숫자 표현 (소수점 버림)
          --%>
         
         <!-- (<) 이전 페이지 누르면 무조건  10, 20, 30,..등으로 감  -->
         <fmt:parseNumber var="c1" value="${(pInfo.currentPage - 1) / 5}" integerOnly="true" />
         <fmt:parseNumber var="prev" value="${c1 * 5}" integerOnly="true" />
         <c:set var="prevPage" value="${pageUrl}?cp=${prev}${searchStr}"/>
         
         <!-- (>) 다음 페이지 누르면 무조건 11, 21, 31, ..등으로 이동  -->
         <fmt:parseNumber var="c2" value="${(pInfo.currentPage + 4) / 5 }" integerOnly="true" />
         <fmt:parseNumber var="next" value="${c2*5+1 }" integerOnly="true" />
         <c:set var="nextPage" value="${pageUrl}?cp=${next}${searchStr}" />
             
         
    <div class="my-5">
        <ul class="pagination">
        <!-- 페이지 목록 -->
         <%-- 현재 페이지가 5페이지 초과인 경우 --%>
           <c:if test="${pInfo.currentPage > 5}">
              <li> <!-- 이전 페이지로 이동 (<) -->
                 <a class="page-link" href="${prevPage}">&lt;</a>
              </li>
           </c:if>
        
            <c:forEach var="page" begin="${pInfo.startPage}"   end="${pInfo.endPage}">
               <c:choose>
                 <c:when test="${pInfo.currentPage == page }">
      			  <li>
                  	 <a class="page-link">${page}</a> <!-- 현재페이지는 누를수 없게 링크없이 그냥 숫자만있음!! -->
                  </li>
         		</c:when>
               <c:otherwise>
            	 <li>
                    <a class="page-link" href="${pageUrl}?cp=${page}${searchStr}">${page}</a> <!-- 여기서 cp 파라미터 생성됨! -->
                 </li>
 				 </c:otherwise>
              </c:choose>
           </c:forEach>
           
           <%-- 다음 페이지가 마지막 페이지 이하인 경우 --%>
               <c:if test="${next <= pInfo.maxPage}">
                  <li> <!-- 다음 페이지로 이동 (>) -->
                     <a class="page-link" href="${nextPage}">&gt;</a>
                  </li>
               </c:if>

            </ul>
         </div>
           
     <!-- 검색창 -->
    <div class="mb-5">
        <form action="${contextPath}/boardSearch.do" method="GET" class="text-center" id="searchForm">
            <select name="sk" class="form-control" style="width: 100px; display: inline-block;">
                <option value="title">글제목</option>
                <option value="content">내용</option>
                <option value="titcont">제목+내용</option>
                <option value="writer">작성자</option>
            </select>
            <input type="text" name="sv" class="form-control"  style="width: 25%; display: inline-block;">
            <button class="form-control btn" id="searchBtn" style="width: 100px; display: inline-block;" >검색</button>
        </form>
    </div>
    
    
    
</div>
    <!-- footer  -->
 <jsp:include page="../common/footer.jsp"></jsp:include>
 
 <script>
      // 게시글 상세보기 기능 (jquery를 통해 작업)
      $("#board-table td").on("click", function(){
    	  //게시글 번호 얻어오기
    	  var boardNo = $(this).parent().children().eq(0).text();
    	  console.log(boardNo);
    	  
    	  var url = "${contextPath}/board/view.do?cp=${pInfo.currentPage}&no=" + boardNo + "${searchStr}";
    	  console.log(url);
    	  location.href = url;
      });
      
      
      
      //검색 내용이 있을 경우 검색창에 해당 내용을 작성해두는 기능
      (function(){
    	  var searchkey = "${param.sk}"; 
    	  //파라미터중 sk가 있을 경우 ex) "49"
    	  //파라미터중 sk가 없을 경우 ex) ""
    	  
    	  var searchValue = "${param.sv}";

    	  //검색창 select의 option을 반복 접근
    	  $("select[name=sk] > option").each(function(index,item){
    		  //index : 현재 접근중인 요소의 인덱스
    		  //item : 현재 접근중인 요소
    		  
    		  if( $(item).val() == searchkey){ 
    			  $(item).prop("selected", true);
    		  }
    	  });
    		
    	  // 검색어 입력 창에 searchValue값 출력
    	  $("input[name=sv]").val(searchValue);
    		  
      })();
   </script>
 
</body>
</html>