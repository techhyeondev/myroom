package com.project.recoder.review.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.project.recoder.member.model.vo.Member;
import com.project.recoder.review.model.service.ReviewService;
import com.project.recoder.review.model.vo.review;

/**
 * Servlet implementation class ReviewController
 */
@WebServlet("/review/*")
public class ReviewController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
	      String contextPath = request.getContextPath();
	      String command = uri.substring((contextPath+"/review").length());
	      
	      try {
	         ReviewService service = new ReviewService();
	         
	         // 댓글 목록 조회 Controller *************************************
	         if(command.equals("/selectList.do")) {
	        	
	            int parentBoardNo = Integer.parseInt(request.getParameter("parentRoomNo"));
	            
	            List<review> rList = service.selectList(parentBoardNo);
	            
	            Gson gson = new GsonBuilder().setDateFormat("yyyy년 MM월 dd일 HH:mm").create();
	            gson.toJson(rList, response.getWriter()); // rList가 자동으로 gson 형태로 변환된다.
	         }
	         
	         else if(command.equals("/chkVisit.do")) {
	        	 System.out.println("kdansghkrdls");
	        	 int parentBoardNo = Integer.parseInt(request.getParameter("parentRoomNo"));
	        	 
	        	 
	        	 int memNo = Integer.parseInt(request.getParameter("memNo"));
	        	 
	        	 int result = service.chkVisit(parentBoardNo,memNo);
	        	 response.getWriter().print(result);
	         }
	         
	         
	         else if(command.equals("/insertReply.do")) {
	        	// 오라클에서 숫자로 이루어진 문자열은 자동으로 숫자로 변환되는 특징을 사용할 예정 
	        	int replyWriter = Integer.parseInt(request.getParameter("replyWriter"));
				int parentRoomNo = Integer.parseInt(request.getParameter("parentRoomNo"));
				String replyContent = request.getParameter("replyContent");
				int rating = Integer.parseInt(request.getParameter("rating"));
				

				review reply = new review();
	        	reply.setMemNo(replyWriter); // 회원번호 저장됨 
	        	reply.setContent(replyContent);
	        	reply.setRoomNo(parentRoomNo);
	        	reply.setRating(rating);
				
	        	int result = service.insertReply(reply);
	        	
	        	
	        	response.getWriter().print(result);
	        	
	         }
	         
	         else if(command.equals("/reviewChk.do")) {
		        	// 오라클에서 숫자로 이루어진 문자열은 자동으로 숫자로 변환되는 특징을 사용할 예정 
		        	int memNo = Integer.parseInt(request.getParameter("memNo"));
					int roomNo = Integer.parseInt(request.getParameter("parentRoomNo"));
					
				
		        	int result = service.reviewChk(memNo, roomNo);

		        	response.getWriter().print(result);
		        	
		         }
	         

	      }	catch(Exception e) {
	    	  e.printStackTrace();
	         }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
