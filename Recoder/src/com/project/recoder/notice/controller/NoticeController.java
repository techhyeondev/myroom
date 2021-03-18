package com.project.recoder.notice.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project.recoder.board.model.vo.Board;
import com.project.recoder.board.model.vo.BoardImg;
import com.project.recoder.board.model.vo.PageInfo;
import com.project.recoder.notice.model.service.NoticeService;

@WebServlet("/notice/*")
public class NoticeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public NoticeController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI(); // 
		String contextPath = request.getContextPath(); // 
		String command = uri.substring((contextPath + "/notice").length());
	
		String path = null;
		RequestDispatcher view = null;

		String swalIcon = null;
		String swalTitle = null;
		String swalText = null;
		
		String errorMsg = null;
		
		try {
			HttpSession session = request.getSession();
			
			// 현재 페이지를 얻어옴
			String cp = request.getParameter("cp");
			
			NoticeService service = new NoticeService();
			
			//공지사항 목록 조회 Controller------------------------------
			if (command.equals("/list.do")) {
				
				errorMsg = "게시판 목록 조회 과정에서 오류 발생";
				
				PageInfo pInfo = service.getPageInfo(cp);
				
				List<Board> bList = service.selectBoardList(pInfo);
				
			
				path = "/WEB-INF/views/notice/noticeList.jsp";
				
				request.setAttribute("bList", bList);
				request.setAttribute("pInfo", pInfo);
				
				view = request.getRequestDispatcher(path);
				view.forward(request, response);
				
				}
			
			//게시글 상세조회 Controller -----------------------------
			else if(command.equals("/view.do")) {
				errorMsg = "게시글 상세조회 과정에서 오류 발생";
				
				int boardNo = Integer.parseInt(request.getParameter("no"));
				//System.out.println(boardNo);
				//상세조회 비즈니스 로직 수행 후 결과 반환받기 
				Board board = service.selectBoard(boardNo);
				
				if(board != null) { //상세조회 성공
					
					path = "/WEB-INF/views/notice/noticeView.jsp";
					request.setAttribute("board", board);
					view = request.getRequestDispatcher(path);
					view.forward(request, response);
					
				}else {
					request.getSession().setAttribute("swalIcon", "error");
					request.getSession().setAttribute("swalTitle", "게시글 상세조회 실패");
					response.sendRedirect("list.do?cp=1");
				}
					
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			path = "/WEB-INF/views/common/errorPage.jsp"; 
			request.setAttribute("errorMsg", errorMsg);
			view = request.getRequestDispatcher(path);
			view.forward(request, response);
		}
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
