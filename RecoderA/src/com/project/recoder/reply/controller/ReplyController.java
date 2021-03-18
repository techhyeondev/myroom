package com.project.recoder.reply.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.project.recoder.reply.model.service.ReplyService;
import com.project.recoder.reply.model.vo.Reply;


@WebServlet("/reply/*")
public class ReplyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); 
		String uri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = uri.substring((contextPath+"/reply").length());
		
		try {
			ReplyService service = new ReplyService();
		
			// 댓글 목록 조회
			if(command.equals("/selectList.do")) {
				int parentBoardNo = Integer.parseInt(request.getParameter("parentBoardNo"));
				
				List<Reply> rList = service.selectList(parentBoardNo);
				//System.out.println(rList);
				Gson gson = new GsonBuilder().setDateFormat("yyyy년 MM월 dd일 HH:mm").create();
				response.setCharacterEncoding("UTF-8");
				gson.toJson(rList, response.getWriter());
			}
			
			
			// 댓글 삭제
			else if(command.equals("/deleteReply.do")) {
				
				int replyNo= Integer.parseInt(request.getParameter("replyNo"));
				
				int result = service.updateReplyDelete(replyNo);
				
				response.getWriter().print(result);
			}
			
			
			// 댓글 복구
			else if(command.equals("/recoverReply.do")) {
				
				int replyNo= Integer.parseInt(request.getParameter("replyNo"));
				
				int result = service.updateReplyRecover(replyNo);
				
				response.getWriter().print(result);
			}
			
			
			
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
	
	
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
