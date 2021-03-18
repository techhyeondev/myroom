<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="contextPath" value="${pageContext.servletContext.contextPath }" scope="application"></c:set>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!-- reset -->
    <link rel="stylesheet" href="${contextPath}/resources/css/reset.css">
    <link rel="stylesheet" href="${contextPath}/resources/css/message.css">


    <!-- jQuery -->
    <script src="http://code.jquery.com/jquery-3.5.1.min.js" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>
    
    <!-- font awesome -->
    <script src="${contextPath}/resources/js/fontawesome.js" crossorigin="anonymous"></script>

    <title>-</title>
</head>
<body>
    <div class="wrapper">
        <!-- header -->
		<!-- WEB-INF/views/common/header.jsp 여기에 삽입(포함) -->
		<jsp:include page="../common/header.jsp"></jsp:include>


        <!-- message -->
        <section class="message clearfix">
            <h1>쪽지 함</h1>
            <div class="msg_box">
                <ul>
                	<c:forEach var="message" items="${message}">
                    <li class="member_list clearfix">
                        <div class="msg_left">
                            <img src="${contextPath}/resources/images/homepage/mail_open.png" alt="">
                            <!-- <div class="circle"></div> -->
                            <c:if test="${message.msgCnt == 0}">
	                            <div class="mcnt" id="mcnt"><i class="fas fa-comment-plus"></i></div>
                            </c:if>
                            <span class="name">${message.memNick}</span>
                            <input type="hidden" value="${message.memSend }" name="memNo" class="aa">
                        </div>

                        <p class="msg_text" id="msgText">
                        	${message.msgContent} 
                        </p>

                        <div class="msg_right">
                            <span class="msg_date">
                            	<fmt:formatDate value="${message.createDate}" pattern="hh:mm"/>
                            </span>
                            <div class="msg_button_check" id="${message.memSend}" onclick="getId(this)"><span>확인</span></div>
                            <div class="msg_button_delete" ><span id="${message.memSend}" onclick="deleteMsg(this)">삭제</span></div>
                        </div>
                    </li>
                    
                    </c:forEach>
                    
                    
                </ul>
            </div>
            <!-- <div class="message_his">
                <ul>
                    <li class="you clearfix">
                        <span class="name">상대방</span>
                        <p>
                            네 안녕하세요네 안녕하세요네 안녕하세요네 안녕하세요네 안녕하세요네 안녕하세요네 안녕하세요네 안녕하세요네 안녕하세요네 안녕하세요네 안녕하세요네 안녕하세요네 안녕하세요네 안녕하세요네 안녕하세요네 안녕하세요네 안녕하세요네 안녕하세요네 안녕하세요네 안녕하세요네 안녕하세요네 안녕하세요네 안녕하세요네 안녕하세요네 안녕하세요네 안녕하세요네 안녕하세요네 안녕하세요네 안녕하세요네 안녕하세요네 안녕하세요네 안녕하세요네 안녕하세요네 안녕하세요네 안녕하세요네 안녕하세요네 안녕하세요네 안녕하세요네 안녕하세요

                            <span class="msg_date">11:54</span>  
                        </p>
                    </li>
                    <li class="me clearfix">
                        <span class="name">나</span>
                        <p>
                            네 안녕하세요
                            <span class="msg_date">11:54</span>  
                        </p>
                    </li>
                    <li class="me clearfix">
                        <span class="name">나</span>
                        <p>
                            네 안녕하세요네 안녕하세요네 안녕하세요네 안녕하세요네 안녕하세요네 안녕하세요네 안녕하세요네 안녕하세요네 안녕하세요네 안녕하세요네 안녕하세요네 안녕하세요네 안녕하세요네 안녕하세요네 안녕하세요네 안녕하세요네 안녕하세요네 안녕하세요네 안녕하세요네 안녕하세요네 안녕하세요네 안녕하세요네 안녕하세요네 안녕하세요네 안녕하세요네 안녕하세요네 안녕하세요네 안녕하세요네 안녕하세요네 안녕하세요네 안녕하세요네 안녕하세요네 안녕하세요네 안녕하세요네 안녕하세요네 안녕하세요네 안녕하세요네 안녕하세요네 안녕하세요네 안녕하세요네 안녕하세요네 안녕하세요네 안녕하세요네 안녕하세요네 안녕하세요네 안녕하세요네 안녕하세요네 안녕하세요네 안녕하세요네 안녕하세요네 안녕하세요네 안녕하세요네 안녕하세요네 안녕하세요네 안녕하세요네 안녕하세요네 안녕하세요네 안녕하세요네 안녕하세요네 안녕하세요네 안녕하세요네 안녕하세요네 안녕하세요네 안녕하세요네 안녕하세요
                            <span class="msg_date">11:54</span>  
                        </p>
                    </li>
                </ul>
                <div class="message_send">
                    <form action="" class="input_form clearfix">
                        <input type="textarea" placeholder="내용">
                        <textarea name="" id="" cols="30" rows="10" placeholder="내용" ></textarea>
                        <button id="send">전송</button>
                    </form>
                </div>
            </div> -->
            
            
            <jsp:include page="messageUnI.jsp"></jsp:include>
            
            
        </section>
        
        <jsp:include page="../common/footer.jsp"></jsp:include>
    </div>

    <script>
    	
    
    	function getId(e){
    		
    		var you = Number(e.id)
    		
    		if (isNaN(you)) {
				you = e;				
			}
    		console.log(you);
    		var i = ${memNo}

    		$.ajax({
    	 		url : "${contextPath}/message/messageUnI.do",
    			type : "post",
    			data : {
    				"you" : you,
    				"i" : i
    				},
   				dataType : "json",
    			success : function(result){
    				
    				$("#mChat").html("");
    				
    					for(let j = 0; j <result.mChat.length; j++){
    						
    						if (result.mChat[j].memReceive == you) {
    							var cnt = 0;
		    					var li = $("<li>").addClass("you clearfix");
		    					var p = $("<p>").text(result.mChat[j].msgContent)
		    					var span = $("<span>").addClass("name").text(result.mChat[j].memNick);
		    					var span2 = $("<span>").addClass("msg_date").text(result.mChat[j].createDate)
		    					var input = $("<input>").prop("type", "hidden").prop("value", "result.mChat[j].memSend");
		    					
		    					li.append(span).append(p).append(input)
		    					p.append(span2);
	    						
							}else{
								
		    					var cnt = 0;
		    					var li = $("<li>").addClass("me clearfix");
		    					var p = $("<p>").text(result.mChat[j].msgContent)
		    					var span = $("<span>").addClass("name").text("나");
		    					var span2 = $("<span>").addClass("msg_date").text(result.mChat[j].createDate);
		    					li.append(span).append(p);
		    					p.append(span2);
							}

	    					$("#mChat").append(li);
    					}    				
    				
    			}, error : function(){
    				console.log("실패");
    			}		
    		});
    		
    		
    		
    	}
    	
    	
    	// 삭제
		function deleteMsg(e){
    		
			var you = Number(e.id)
    		var i = ${memNo}

    		
    		
    		$.ajax({
    	 		url : "${contextPath}/message/messageDelete.do",
    			type : "post",
    			data : {
    				"you" : you,
    				"i" : i
    				},
    			success : function(result){
    				console.log("성공");
    				
    				
    				location.reload();
    				
    			}, error : function(){
    				console.log("실패");
    			}		
    		});
    		
    		

    	}
    	
    	
    	
    </script>
    <script src="${contextPath}/resources/js/message.js"></script>
</body>
</html>