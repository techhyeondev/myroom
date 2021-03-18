<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.servletContext.contextPath }" scope="application"></c:set>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>게시글 수정</title>
    
    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">

    <!-- Bootstrap core JS -->
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>

    <!-- jQuery -->
   <script src="http://code.jquery.com/jquery-3.5.1.min.js" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>
   
   <link rel="stylesheet" href="${contextPath}/resources/css/boardInsert.css">
   <style>
   
   #btn1, #btn2, .pinkBtn{
    display: inline-block;
    background-color: #EE9BA3;
    color: white;
    border: none;
    }
    </style>
   
  	 <!-- 썸머노트 include libraries(jQuery, bootstrap) -->
		<link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">
		<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>

 <!-- WEB-INF/views/common/header.jsp 여기에 삽입(포함) -->
		<jsp:include page="../common/header.jsp"></jsp:include>
    <div class="container  my-5">
        <h1 id="board-main">게시글 수정</h1>
            <div id="board-area">
            
            <c:if test="${!empty param.sk && !empty param.sv}">
			<c:set var="searchStr" value="&sk=${param.sk}&sv=${param.sv}"/>
			</c:if>
                <form action="${contextPath}/board/update.do?cp=${param.cp}&no=${param.no}${searchStr}"  method="post" onsubmit="return boardValidate();">
                <div class="mb-2">
                    <hr>
                </div>
                <div class="form-inline mb-2">
					<label class="mr-3 insert-label">제목</label> 
					<input type="text" class="form-control" id="boardTitle" name="boardTitle" size="70" value="${board.title}">
				</div>
				

				<div class="form-inline mb-2">
                    <div class="boardInfo-area">
                        <label class="mr-3 insert-label">작성자</label>
                        <h5 class="my-0" id="writer">${board.memNick}</h5>
                    </div>
                    <div class="boardInfo-area">
                        <label class="mr-3 insert-label">작성일</label>
                        <h5 class="my-0" id="today">${board.createDate}</h5>
                    </div>
				</div>


				<hr>
                
                <!-- 썸머노트  -->
				<textarea id="summernote" name="Contents"></textarea>

				<hr class="mb-4">

				<div class="text-center">
					<button type="submit" class="btn pinkBtn">등록</button>
					<button type="button" class="btn pinkBtn" onclick = "location.href='${header.referer}'">이전으로</button>
				</div>

            
            
                </form>

            </div>
    </div>

    <!-- include summernote css/js -->
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>

    <script>
    //썸머노트 실행
    $(document).ready(function() {
    	  $('#summernote').summernote();
     	$('#summernote').summernote('pasteHTML',  '${board.content}');
 
    	});
    

    $('#summernote').summernote({
   	 toolbar: [
   		    // [groupName, [list of button]]
   		    ['fontname', ['fontname']],
   		    ['fontsize', ['fontsize']],
   		    ['style', ['bold', 'italic', 'underline','strikethrough', 'clear']],
   		    ['color', ['forecolor','color']],
   		    ['table', ['table']],
   		    ['para', ['ul', 'ol', 'paragraph']],
   		    ['height', ['height']],
   		    ['insert',['picture','link','video']],
   		    ['view', ['fullscreen', 'help']]
   		  ],
   		fontNames: ['Arial', 'Arial Black', 'Comic Sans MS', 'Courier New','맑은 고딕','궁서','굴림체','굴림','돋음체','바탕체'],
   		fontSizes: ['8','9','10','11','12','14','16','18','20','22','24','28','30','36','50','72'],
   		height: 450,
   		width : 1000,
   		minHeight: null,
   		maxHeight: null,
   		focus: true,
   		lang: "ko-KR",
      //서머노트 생성시 콜백함수가 이미지를 추가하는 콜백함수
   		callbacks: {
   			onImageUpload : function(files){
   				sendFile(files[0],this);
   			}
   		}
   			
   	});
      
      function sendFile(file, editor) {
          // 파일 전송을 위한 폼생성
	 		data = new FormData();
	 	    data.append("uploadFile", file);
	 	    
	 	    $.ajax({ // ajax를 통해 파일 업로드 처리
	 	        data : data,
	 	        type : "POST",
	 	        url : "summernoteImgUpload.do",
	 	        enctype: 'multipart/form-data',
	 	        cache : false,
	 	        contentType : false,
	 	        processData : false,
	 	        success : function(result) { // 처리가 성공할 경우
	 	          $(editor).summernote('editor.insertImage', "${contextPath}/resources/images/boardImg/"+result);
                  // 에디터에 이미지 출력
	 	        	//$(editor).summernote('editor.insertImage', data.url);
	 	        }
	 	    });
	 	}
    

(function printToday(){
			// 오늘 날짜 출력 
	 		var today = new Date();
			var month = (today.getMonth()+1);
			var date = today.getDate();
	
	  	var str = today.getFullYear() + "-"
	        		+ (month < 10 ? "0"+month : month) + "-"
	        		+ (date < 10 ? "0"+date : date);
			$("#today").html(str);
		})();





		// 유효성 검사 
		function boardValidate() {
			if ($("#boardTitle").val().trim().length == 0) {
				alert("제목을 입력해 주세요.");
				$("#boardTitle").focus();
				return false;
			}

			if ($("#summernote").val().trim().length == 0) {
				alert("내용을 입력해 주세요.");
				$("#summernote").focus();
				return false;
			}
		}
		
		var markupStr = $('#summernote').summernote('code');
		
    </script>
</body>
</html>    