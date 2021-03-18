<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="message_his">
    <ul id="mChat">
    </ul>
    <div class="message_send">
        <div class="input_form clearfix">
            <!-- <input type="textarea" placeholder="내용"> -->
            <textarea name="" id="myText" cols="30" rows="10" placeholder="내용" ></textarea>
            
            <button id="" onclick="send(this)">전송</button>
        </div>
    </div>
</div>
<script>

	function send(e){
		var you = $("input").val()
    		var i = ${memNo}

		$.ajax({
	 		url : "${contextPath}/message/messageISend.do",
			type : "post",
			data : {
				"myText" : $("#myText").val(),
				"you" : you,
				"i" : i
				},
			success : function(result){
				console.log("성공");
				
				//$("#mChat").html("");
				
					

					getId(you);
					
					
				
			}, error : function(){
				console.log("실패");
			}		
		});
		
		
		
}

</script>