package com.project.recoder.boardSearch.controller;

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
import com.project.recoder.board.model.vo.PageInfo;
import com.project.recoder.boardSearch.service.BoardSearchService;

@WebServlet("/boardSearch.do")
public class BoardSearchController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    public BoardSearchController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String searchkey = request.getParameter("sk");
		String searchValue = request.getParameter("sv");
		String cp = request.getParameter("cp");
		
		
		try {
			BoardSearchService service = new BoardSearchService();
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("sk", searchkey);
			map.put("sv", searchValue);
			map.put("currentPage", cp);
			
			PageInfo pInfo = service.getPageInfo(map);
			
			List<Board> bList = service.boardSearch(map, pInfo);
			
			String path = "WEB-INF/views/board/boardList.jsp";
			
			request.setAttribute("bList", bList);
			request.setAttribute("pInfo", pInfo);
			
			RequestDispatcher view = request.getRequestDispatcher(path);
			view.forward(request, response);
		
		} catch (Exception e) {
			e.printStackTrace();
			String path = "/WEB-INF/views/common/errorPage.jsp";
			request.setAttribute("errorMsg", "검색 과정에서 오류 발생");
			RequestDispatcher view = request.getRequestDispatcher(path);
			view.forward(request, response);
			
		}
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
