<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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

<title>공지사항 게시판</title>
<style>
	.title-area > h2{
        text-align: center;
        height:70px;
      }
      .form-group{
        width: 50%;
        margin : auto;
      }
      .write-btn{
        margin-top:30px;
        margin-right:25%;
        background-color: rgb(174, 160, 236);
        border: rgb(174, 160, 236);
      }
      .prev-btn{
      	margin-top : 30px;
      	margin-right : 10px;
      	background-color: rgb(174, 160, 236);
        border: rgb(174, 160, 236);
      }
</style>
</head>
<body>

	<jsp:include page="../common/header.jsp"></jsp:include>
	
	
	<div class="section ft">
	    <div class="title-area mainFont">
    		  <h2>공지사항</h2>
    	</div>


    <form action="insert.do" method="POST" role="form" onsubmit="return noticeValidate();">
        <div class="form-group ft">
              <label for="exampleFormControlInput1">제목</label>
            <input type="text" class="form-control" id="noticeTitle" name="noticeTitle" placeholder="제목을 작성해주세요.">
          </div>
          <div class="form-group ft">
            <label for="exampleFormControlTextarea1">내용</label>
            <textarea class="form-control" id="noticeContent" name="noticeContent" rows="10"></textarea>
          </div>
        <button type="submit" class="btn btn-primary write-btn float-right">등록</button>
        <a href="list.do" class="btn btn-primary prev-btn float-right">목록으로</a>
    </form>

  </div>
  
  <script>
  
  function noticeValidate(){
	  
	  	
	  
		if( $("#noticeTitle").val().trim().length == 0){
			alert("제목을 입력해 주세요.");
			$("#title").focus();
			return false;
		}
		
		if( $("#noticeContent").val().trim().length == 0){
			alert("내용을 입력해 주세요.");
			$("#content").focus();
			return false;
		}
	}
  </script>

</body>
</html>