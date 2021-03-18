package com.project.recoder.main.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.recoder.board.model.vo.Board;
import com.project.recoder.main.model.service.MainService;
import com.project.recoder.room.model.vo.Room;

@WebServlet("/main")
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// String uri = request.getRequestURI(); // 
		// String contextPath = request.getContextPath(); // 
		// String command = uri.substring((contextPath + "/message").length()); 
		
		String path = null;
		RequestDispatcher view = null;
		
		String errorMsg = null;
	
		try {
			MainService service = new MainService();
			
			List<Board> board = service.boardList();
			List<Room> room = service.roomList();
			
			
			
			request.setAttribute("room", room);
			request.setAttribute("board", board);
			
			path = "/index.jsp"; 
			view = request.getRequestDispatcher(path);
			view.forward(request, response);
		}catch (Exception e) {
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
