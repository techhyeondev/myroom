package com.project.recoder.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.project.recoder.board.model.service.BoardService;
import com.project.recoder.board.model.vo.Board;
import com.project.recoder.room.model.vo.PageInfo;


@WebServlet("/board/*")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String uri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = uri.substring((contextPath + "/board").length());

		String path = null;
		RequestDispatcher view = null;

		String swalIcon = null;
		String swalTitle = null;
		String swalText = null;

		String errorMsg = null;
	
		try {
			BoardService service = new BoardService();
		
			String cp = request.getParameter("cp");
			
			if(command.equals("/list.do")) {
				errorMsg = "자유게시판 조회 과정에서 오류 발생";
				
				PageInfo pInfo = service.getPageInfo(cp);
				//System.out.println(pInfo);
				List<Board> bList = service.selectBoardList(pInfo);
				
//				for(Board b : bList) {
//					System.out.println(b);
//				}
				
				path="/WEB-INF/views/board/boardList.jsp";
				
				request.setAttribute("bList", bList);
				request.setAttribute("pInfo", pInfo);
				
				view=request.getRequestDispatcher(path);
				view.forward(request, response);
			}
		
			else if(command.equals("/deleteBoard.do")){
				errorMsg = "게시글 삭제 과정에서 오류 발생";
				
				String numberList = request.getParameter("numberList");
				//System.out.println(numberList);
				int result = service.updateBoardDelete(numberList);
				//System.out.println(result);
				response.getWriter().print(result);
			}
			
			else if(command.equals("/recoverBoard.do")){
				errorMsg = "게시글 삭제 과정에서 오류 발생";
				
				String numberList = request.getParameter("numberList");
				
				//System.out.println(numberList);
				int result = service.updateBoardRecover(numberList);
				
				//System.out.println(result);
				response.getWriter().print(result);
			}
			
			

			
			// 게시글 상세 조회
			else if(command.equals("/view.do")) {
				errorMsg = "게시글 상세 조회 과정에서 오류 발생";
				
				int boardNo = Integer.parseInt(request.getParameter("no"));
				
				Board board = service.selectBoard(boardNo);
				
				//System.out.println(board);
				
				/*
				 * Gson gson = new GsonBuilder().setDateFormat("yyyy년 MM월 dd일 HH:mm").create();
				 * gson.toJson(board, response.getWriter()); // Json 형태로 자동 변환
				 */ 
				path = "/WEB-INF/views/board/boardView.jsp";
				request.setAttribute("board", board);
				view=request.getRequestDispatcher(path);
				view.forward(request, response);
			
			}else {
				request.getSession().setAttribute("swalIcon", "error");
				request.getSession().setAttribute("swalTitle", "게시글 상세조회 실패");
				response.sendRedirect("list.do");
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
			path = "/WEB-INF/views/common/errorpage.jsp";
			
			request.setAttribute("errorMsg", errorMsg);
			
			view = request.getRequestDispatcher(path);
			view.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
