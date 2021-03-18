<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        
    <link rel="stylesheet" href="${contextPath}/resources/css/header.css">
	<title>게시글 상세 조회</title>
	<style>
	
		.top-section{
			text-align : center;
		}
       .board-area {
           height : 60%;
           width: 60%;
           margin: auto;
           margin-top: 100px;
       }



       .prev-btn {
           background-color: rgb(174, 160, 236);
           border: rgb(174, 160, 236);
       }
       .button-area{
           height:100px;
       }
   
    </style>
</head>


<body>
	
	<jsp:include page="../common/header.jsp"></jsp:include>
		
	 <div class="section">
	 	
	 	 <div class="top-section mainFont">
            <h6>게시판 상세조회</h6>
            <h2>댓글 삭제</h2>
        </div>
	 	
        <div class="board-area ft">


            <!--타이틀  -->
            <h4 class="">[${board.boardTitle}]</h4>

            <p class="">작성자 : ${board.memberNick}</p>

            <hr>

            <!-- 정보 -->
            <div class="info-area">
                <p>
                    <span class="board-dateArea">
                   		작성일 : <fmt:formatDate value="${board.createDate}" pattern="yyyy년 MM월 dd일 HH:mm:ss" />     
                        
                        | 조회수 : ${board.readCount}
                      
                    </span>


                </p>
            </div>
            <hr>
            <!-- 내용 -->
            <div class="board-content">

				${board.boardContent}

            </div>

            <hr>
            
            <c:choose>
				<c:when test="${!empty param.sk }">
					<c:url var="goToList" value="../searchBoard.do">
						<c:param name="cp">${param.cp}</c:param>
						<c:param name="sk">${param.sk}</c:param>
						
					</c:url>
				</c:when>
				
				<c:otherwise>
				
					<c:url var="goToList" value="list.do">
						<c:param name="cp">${param.cp}</c:param>
					</c:url>
				
				</c:otherwise>
			</c:choose>
            
            

            <div class="button-area">
                <a href="${goToList}" class="btn btn-danger float-right prev-btn">목록으로</a>
            </div>
        </div>
	
		<jsp:include page="reply.jsp"></jsp:include>
    </div>
	

</body>
</html>