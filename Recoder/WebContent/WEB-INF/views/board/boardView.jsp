<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="contextPath" value="${pageContext.servletContext.contextPath }" scope="application"></c:set>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>자유게시판 상세조회</title>
    
     <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">

    <!-- Bootstrap core JS -->
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>

    <!-- jQuery -->
   <script src="http://code.jquery.com/jquery-3.5.1.min.js" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>
    
 <link rel="stylesheet" href="${contextPath}/resources/css/boardView.css">
   
</head>
<body margin-top:80px;>

 <!-- WEB-INF/views/common/header.jsp 여기에 삽입(포함) -->
		<jsp:include page="../common/header.jsp"></jsp:include>
    <div class="container  my-5">
<br><br>
    <img alt="자유게시판" src="${contextPath}/resources/images/homepage/boardBanner.jpg" width="1100px" class="my-5">
    
		<div>
            <!-- <h1 id="board-main">자유게시판</h1> -->
            <!-- 게시판구역 -->
            <div class="wrapper">

                <div id="board-area">
                    
                <table class="table">
                    <thead>
                      <tr id="table-name-large">
                        <th scope="col">제목</th>
                        <th scope="col" colspan="5">${board.title}</th>
                    </tr>
                    </thead>
                    <tbody>
                      <tr>
                        <th scope="row">작성자</th>
                        <td colspan="5">${board.memNick}</td>
                      </tr>
                      <tr>
                          <th scope="row">작성일</th>
                          <td id="date">
                          <span>
                          <fmt:formatDate value="${board.createDate}" pattern="yyyy년 MM월 dd일  HH:mm:ss" />
                          </span>
						</td>
                          <th>조회수</th>
                          <td colspan="2" >${board.readCount}</td>
                        </tr>
                    </tbody>
                </table>
                           <p>
                              ${board.content}
                              </p>
					<br>                
                <!-- 버튼구역 -->
                <div>
               <%-- 로그인된 회원과 해당 글 작성자가 같은 경우--%>
				<c:if test="${!empty loginMember && (board.memNo == loginMember.memNo)}">
                    <button id="deleteBtn" class="btn alert-danger float-right">삭제</button> 
                  	<!-- 상세조회, 검색 할때 cp, no, sk, sv 유지 시켜야 수정후 다시 상세조회로 돌아갈수있다 -->
					<%-- 게시글 수정 후 상세조회 페이지로 돌아오기 위한 url조합 --%>
					<c:if test="${!empty param.sv && !empty param.sk}">
						<%-- 검색을 통해 들어온 상세 조회 페이지인 경우 --%>
						<c:set var="searchStr" value="&sk=${param.sk}&sv=${param.sv}"/>
					</c:if>
				<a href="updateForm.do?cp=${param.cp}&no=${param.no}${searchStr}" class="btn alert-danger float-right ml-1 mr-1">수정</a>
				</c:if>
			
			
                <c:choose>
				<c:when test="${!empty param.sk && !empty param.sv}">
					<c:url var="goToList" value="../boardSearch.do">
						<c:param name="cp">${param.cp}</c:param>
						<c:param name="sk">${param.sk}</c:param>
						<c:param name="sv">${param.sv}</c:param>
					</c:url>
				</c:when>
				<c:otherwise>
					<c:url var="goToList" value="list.do">
						<c:param name="cp">${param.cp}</c:param>
					</c:url>
				</c:otherwise>
			</c:choose>
                    <a href="${goToList}" class="btn float-right alert-danger" id="btn2" >목록으로</a>
                </div>
                <br> <br>
                <hr>
                <!-- 댓글 구역 -->
                <jsp:include page="comment.jsp"></jsp:include>
                    
                    
                  
                   
                </div>
                
            </div>
                
                
            </div>

		</div>
	</div>
	
	<script type="text/javascript">
	$("#deleteBtn").on("click", function(){
		if(confirm("정말 삭제 하시겠습니까?")){
			location.href = "delete.do?no=${board.boardNo}";
		}
	})
	</script>
</body>
</html>