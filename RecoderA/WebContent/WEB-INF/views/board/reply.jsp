<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<style>
       .reply-area {
           width: 60%;
           height:30%;
           margin: auto;
       }

       .recover-btn {
           background-color: rgb(174, 160, 236);
           border: rgb(174, 160, 236);
       }

       .delete-btn {
           background-color: #868e96;
           border: #868e96;
       }

       .pagination {
           justify-content: center;
       }
</style>

		<div class="reply-area">
            <h4>댓글 관리</h4>
            <table class="table table-striped table-hover" id="list-table">
                <thead>
                    <tr>
                        <th>작성자</th>
                        <th>작성된 내용</th>
                        <th>작성 일자</th>
                        <th></th>
                    </tr>
                </thead>

                <tbody id="replyListArea">
                    <!-- 게시글 목록 -->
                    <tr class="reply-row">
                        <td class="rWriter"> horizon </td>
                        <td class="rContent"> 댓글내용1 </td>
                        <td class="rDate"> 2021-01-12</td>
                        <td>
                            <div class="replyBtnArea">
                                <button class="btn btn-primary btn-sm ml-1 recover-btn"
                                    onclick="recoverReply(2)">복구</button>
                               
                                <button class="btn btn-primary btn-sm ml-1 delete-btn"
                                    onclick="deleteReply(2)">삭제</button>
                            </div>
                        </td>
                    </tr>


                </tbody>

            </table>
        </div> 
        
    <script>
    var loginAdmin = "${loginAdmin.adminId}"
    
    var parentBoardNo = ${board.boardNo};
    
    /* console.log("parentBoardNo"); */
    
 	// 레디 함수
    $(function(){
    	selectReplyList();
    });
 	
    function selectReplyList(){
     	$.ajax({
     		url : "${contextPath}/reply/selectList.do",
     		data : {"parentBoardNo" : parentBoardNo},
     		type : "post",
     		dataType : "JSON",
     		success : function(rList){
     			console.log(rList); // 배열 출력 확인 OK.
     			
     			/* replyListArea 영역의 내용을 우선 모두 지운다. */
     			$("#replyListArea").html("");
     			
     			$.each(rList,function(index, item){
     				// <li>태그를 생성해서 reply-row를 추가함.
     				var tr = $("<tr>").addClass("reply-row");
     				var rWriter =$("<td>").addClass("rWriter").text(item.memberNick);
     				var rContent = $("<td>").addClass("rContent").html(item.replyContent);
     				var rDate =$("<td>").addClass("rDate").text(item.replyCreateDate);
     				
     				
     				var td =$("<td>");
     				
     				var replyBtnArea = $("<div>").addClass("replyBtnArea");
     				
     				if(item.deleteFl =='Y'){ //삭제되었다면
	     				var recoverBtn = $("<button>").addClass("btn btn-primary btn-sm ml-1 recover-btn").text("복구").attr("onclick", "recoverReply("+item.replyNo+" )");
     					replyBtnArea.append(recoverBtn);
     				
     				}else{ //삭제되지 않았다면, (정상 등록되어있다면)
     					var deleteBtn = $("<button>").addClass("btn btn-primary btn-sm ml-1 delete-btn").text("삭제").attr("onclick", "deleteReply("+item.replyNo+")");
     					replyBtnArea.append(deleteBtn);
     				}
     				
     				td.append(replyBtnArea);
     				
     				tr.append(rWriter).append(rContent).append(rDate).append(td);
     				
     				$("#replyListArea").append(tr);
     			});
     		},
     		error : function(){
     			console.log("댓글 목록 조회 실패")
     		}
     	});
    }
    
    
    
    // 댓글 삭제
    function deleteReply(replyNo){
    	
    		var url = "${contextPath}/reply/deleteReply.do";
    		
    		$.ajax({
    			url : url,
    			data : {"replyNo" : replyNo},
    			success : function(result){
    				if(result > 0){
    					selectReplyList(parentBoardNo);
    					
    					swal({"icon" : "success" , "title" : "댓글 삭제 성공"});
    					
    					
    				}
    				
    			}, 
    			error : function(){
    				console.log("ajax 통신 실패");
    			}
    		});
    	

    }
    
    
    // 댓글 복구
     function recoverReply(replyNo){
    	
    		var url = "${contextPath}/reply/recoverReply.do";
    		
    		$.ajax({
    			url : url,
    			data : {"replyNo" : replyNo},
    			success : function(result){
    				if(result > 0){
    					selectReplyList(parentBoardNo);
    					
    					swal({"icon" : "success" , "title" : "댓글 복구 성공"});
    					
    				}
    				
    			}, 
    			error : function(){
    				console.log("ajax 통신 실패");
    			}
    		});
    	

    } 
    
    
    
    </script>    
        
        
           