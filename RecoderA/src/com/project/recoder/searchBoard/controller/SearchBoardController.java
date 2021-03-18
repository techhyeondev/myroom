package com.project.recoder.searchBoard.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.recoder.board.model.vo.Board;
import com.project.recoder.room.model.vo.PageInfo;
import com.project.recoder.searchBoard.model.service.SearchBoardService;


@WebServlet("/searchBoard.do")
public class SearchBoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String searchKey = request.getParameter("sk");
		
		String cp = request.getParameter("cp");
		
		try {
			
			SearchBoardService service = new SearchBoardService();
			
			Map<String, Object> map = new HashMap<String, Object>();
			
			map.put("searchKey", searchKey);
			
			map.put("currentPage", cp);
			
			PageInfo pInfo = service.getPageInfo(map);
			
			List<Board> bList = service.searchBoardList(map, pInfo);
			
			//System.out.println(pInfo);
			//for(Board b : bList) {
			//	System.out.println(b);
			//}
			
			
			String path = "/WEB-INF/views/board/boardList.jsp";
			request.setAttribute("bList", bList);
			request.setAttribute("pInfo", pInfo);
			
			RequestDispatcher view = request.getRequestDispatcher(path);
			view.forward(request, response);
			
		
		}catch(Exception e) {
			e.printStackTrace();
			String path = "/WEB-INF/views/common/errorPage.jsp";
			request.setAttribute("errormsg", "검색과정에서 오류 발생");
			RequestDispatcher view = request.getRequestDispatcher(path);
			view.forward(request, response);
		}
	
	}

	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
