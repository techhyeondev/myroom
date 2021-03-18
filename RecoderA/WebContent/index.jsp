<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<link rel="shortcut icon" sizes="16x16 32x32 64x64" href="resources/images/logo.png"/>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!-- jquery  -->
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>

<script src="https://kit.fontawesome.com/5a7a3b1a34.js"
   crossorigin="anonymous"></script>

<!-- bootStrap -->
<link rel="stylesheet"
   href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
   integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2"
   crossorigin="anonymous">
<script
   src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"
   integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx"
   crossorigin="anonymous"></script>


<link rel="stylesheet" href="resources/css/reset.css">
<link rel="stylesheet" href="resources/css/admin_main.css">
<link rel="stylesheet" href="resources/css/slick.css">

<script src="resources/js/slick.min.js"></script>
</head>
<body>

	<jsp:include page="WEB-INF/views/common/header.jsp"></jsp:include>
	<!-- visual -->
	<section class="visual">

		<ul class="slide">
			<li><img src="resources/images/bg1.jpg" alt="배경1">
				<div class="mainTitle">
					<strong> 어떤 방에서<br> 살고 싶으신가요?
					</strong>
				</div></li>
			<li><img src="resources/images/bg2.jpg" alt="배경2">
				<div class="mainTitle">
					<strong> 어떤 동네에서<br> 살고 싶으신가요?
					</strong>
				</div></li>
			<li><img src="resources/images/bg3.jpg" alt="배경3">
				<div class="mainTitle">
					<strong> 어떤 이웃과<br> 살고 싶으신가요?
					</strong>
				</div></li>
			<li><img src="resources/images/bg4.jpg" alt="배경4">
				<div class="mainTitle">
					<strong> 어떤 집에서<br> 살고 싶으신가요?
					</strong>
				</div></li>
		</ul>
	</section>


	<!-- Modal -->
	<div class="modal fade" id="exampleModal" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">

				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel loginBtn">관리자 로그인</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<form method="POST" action="#">
					<div class="modal-body">
						<input type="text" id="inputId" placeholder="아이디"><br>
						<input type="password" id="inputPw" placeholder="비밀번호"><br>
						<button type="button" class="btn btn-primary loginBtn">로그인</button>
					</div>
				</form>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary loginBtn"
						data-dismiss="modal">닫기</button>

				</div>
			</div>
		</div>
	</div>
	<script src="resources/js/index.js"></script>
	
</body>
</html>