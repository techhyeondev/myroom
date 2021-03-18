package com.project.recoder.comment.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.project.recoder.comment.model.service.CommentService;
import com.project.recoder.comment.model.vo.Comment;

@WebServlet("/comment/*")
public class CommentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CommentController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI(); // 
		String contextPath = request.getContextPath(); // 
		String command = uri.substring((contextPath + "/comment").length()); 
		
		String path = null;
		RequestDispatcher view = null;

		String swalIcon = null;
		String swalTitle = null;
		String swalText = null;
		
		String errorMsg = null;
		
		CommentService service = new CommentService();
		
		try {
			HttpSession session = request.getSession();
			
			String cp = request.getParameter("cp");
			
			
			
			//댓글 목록 조회------------------------------
			if(command.equals("/selectList.do")) {
				errorMsg = "댓글 조회 과정 중 오류 발생";
				
				int boardNo =Integer.parseInt(request.getParameter("boardNo"));
				
				//System.out.println(boardNo);
				
				List<Comment> cList = service.selectList(boardNo);
				
				Gson gson = new GsonBuilder().setDateFormat("yyyy년 MM월 dd일 HH:mm").create();
				
				gson.toJson(cList, response.getWriter());
				
			}
			
			//댓글 삽입 ==========-------------------------
			else if(command.equals("/insertComment.do")) {
				String commentWriter = request.getParameter("commentWriter");
				int boardNo = Integer.parseInt(request.getParameter("boardNo"));
				String commentContent = request.getParameter("commentContent");
				
			    Comment comment = new Comment();
				comment.setMemNick(commentWriter); //회원번호 저장
				comment.setContent(commentContent);
				comment.setBoardNo(boardNo);
				
				int result = service.insertComment(comment);
				
				response.getWriter().print(result);
			}
			
			//댓글 수정 Controller ****************************************************
			else if(command.equals("/updateComment.do")) {
				
				//파라미터 (댓글번호, 댓글내용) 얻어오기
				int commentNo = Integer.parseInt(request.getParameter("commentNo"));
				String commentContent = request.getParameter("commentContent");
				
				Comment comment = new Comment();
				comment.setCommentNo(commentNo);
				comment.setContent(commentContent);
				
				int result = service.updateComment(comment);
				
				response.getWriter().print(result);
				
			}
			
			//댓글 삭제 Controller ********************************************
			else if(command.equals("/deleteComment.do")) {
				int commentNo = Integer.parseInt(request.getParameter("commentNo"));
				
				int result = service.updateDeleteFl(commentNo);
				
				response.getWriter().print(result);
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
			path = "/WEB-INF/views/common/errorPage.jsp"; // 수정
			request.setAttribute("errorMsg", errorMsg);
			view = request.getRequestDispatcher(path);
			view.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
