package com.project.recoder.search.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.recoder.room.model.vo.Room;
import com.project.recoder.search.service.SearchService;


/**
 * Servlet implementation class SearchController
 */
@WebServlet("/searchKeyword.do")
public class SearchAreaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String searchKey = request.getParameter("sk");
		
		String searchValue = request.getParameter("keyword");
		  
		System.out.println(searchValue);
		  
		
		
		String uri = request.getRequestURI(); // 
		String contextPath = request.getContextPath(); // 
		String command = uri.substring((contextPath + "/room").length()); 
		
		String path = null;
		RequestDispatcher view = null;
		
		String errorMsg = null;

	      
	      try {
	         SearchService service = new SearchService();
	         
	         List<Room> roomList = null;
	         
	         if (searchValue.length() != 0) {
	        	 if(searchValue.substring(0,1).equals("#")) {
	        		 roomList = service.searchSubwayList(searchValue);
	        	 }else {
	        		 roomList = service.searchRoomList(searchValue);
	        	 }
			}
 
	         
	         
	         
	         // 조회된 내용과 PageInfo 객체를 request객체에 담아서 요청 위임
	         path = "/WEB-INF/views/room/searchRoom.jsp";

	         request.setAttribute("roomList", roomList);
	         
	         view = request.getRequestDispatcher(path);
	         view.forward(request, response);
	         
//	         }
	      }catch (Exception e) {
	         e.printStackTrace();
	         path = "/WEB-INF/views/common/errorPage.jsp";
	         request.setAttribute("errorMsg", "검색 과정에서 오류 발생");
	         view = request.getRequestDispatcher(path);
	         view.forward(request, response);
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
