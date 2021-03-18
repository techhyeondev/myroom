package com.project.recoder.board.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.JsonObject;
import com.oreilly.servlet.MultipartRequest;
import com.project.recoder.board.model.service.BoardService;
import com.project.recoder.board.model.vo.Board;
import com.project.recoder.board.model.vo.BoardImg;
import com.project.recoder.board.model.vo.PageInfo;
import com.project.recoder.broker.model.vo.Broker;
import com.project.recoder.common.MyFileRenamePolicy;
import com.project.recoder.member.model.vo.Member;

@WebServlet("/board/*")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public BoardController() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uri = request.getRequestURI(); //
		String contextPath = request.getContextPath(); //
		String command = uri.substring((contextPath + "/board").length());

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

			BoardService service = new BoardService();

			// 자유게시판 목록 조회 Controller------------------------------
			if (command.equals("/list.do")) {

				errorMsg = "게시판 목록 조회 과정에서 오류 발생";

				// 1. 페이징 처리를 위한 값 계산 Service
				PageInfo pInfo = service.getPageInfo(cp);
				// 2. 게시글 목록 조회 비즈니스 로직 수행
				List<Board> bList = service.selectBoardList(pInfo);

				path = "/WEB-INF/views/board/boardList.jsp";

				request.setAttribute("bList", bList);
				request.setAttribute("pInfo", pInfo);

				view = request.getRequestDispatcher(path);
				view.forward(request, response);
			}

			// 게시글 상세조회 Controller -----------------------------
			else if (command.equals("/view.do")) {
				errorMsg = "게시글 상세조회 과정에서 오류 발생";

				int boardNo = Integer.parseInt(request.getParameter("no"));
				// 상세조회 비즈니스 로직 수행 후 결과 반환받기
				Board board = service.selectBoard(boardNo);

				if (board != null) { // 상세조회 성공
					

					path = "/WEB-INF/views/board/boardView.jsp";
					request.setAttribute("board", board);
					view = request.getRequestDispatcher(path);
					view.forward(request, response);

				} else {
					request.getSession().setAttribute("swalIcon", "error");
					request.getSession().setAttribute("swalTitle", "게시글 상세조회 실패");
					response.sendRedirect("list.do?cp=1");
				}

			}

			// 게시글 작성폼 연결 ----------------------------------------------
			else if (command.equals("/insertForm.do")) {
				// 썸머노트로 연결
				path = "/WEB-INF/views/board/boardInsert_summer.jsp";
				view = request.getRequestDispatcher(path);
				view.forward(request, response);
			}

			// ajax로 이미지 이름 바꾸기..
			else if (command.equals("/summernoteImgUpload.do")) {
				errorMsg = "썸머노트로 게시글 작성 중 오류 발생";

				int maxSize = 20 * 1024 * 1024;

				String root = request.getSession().getServletContext().getRealPath("/");
				String filePath = root + "resources/images/boardImg/";

				MultipartRequest multiRequest = new MultipartRequest(request, filePath, maxSize, "UTF-8",
						new MyFileRenamePolicy());

				Enumeration<String> files = multiRequest.getFileNames();

				String file = (String) files.nextElement();

				String fileName = multiRequest.getFilesystemName(file);

				response.getWriter().print(fileName);
			}
			
			

			// summer노트로 게시글 작성 controller ---------------------
			else if (command.equals("/insertBoardSummer.do")) {
				errorMsg = "게시글 작성 중 오류 발생";
				String title = request.getParameter("boardTitle");
				String content = request.getParameter("Contents");
				
				int boardWriter = 0; // 회원번호

				if (request.getSession().getAttribute("BrokerNo") != null) {

					boardWriter = (int) request.getSession().getAttribute("BrokerNo");

				} else {
					boardWriter = (int) request.getSession().getAttribute("MemNo");
				}

				
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("boardTitle", title);
				map.put("boardContent", content);
				map.put("boardWriter", boardWriter);

				int result = service.insertBoard(map);

				if (result > 0) { // DB삽입 성공 시 result에는 삽입한 글 번호가 저장되어있다.
					swalIcon = "success";
					swalTitle = "게시글 등록 성공";
					path = "view.do?cp=1&no=" + result;
				} else {
					swalIcon = "error";
					swalTitle = "게시글 등록 실패";
					path = "list.do"; // 게시글 목록
				}
				request.getSession().setAttribute("swalIcon", swalIcon);
				request.getSession().setAttribute("swalTitle", swalTitle);
				response.sendRedirect(path);

			}

			

			// 게시글 수정 연결----------------------------------------------------
			else if (command.equals("/updateForm.do")) {
				errorMsg = "게시글 수정 화면 전환 과정에서 오류 발생";
				
				int boardNo = Integer.parseInt(request.getParameter("no"));
				
				Board board = service.updateView(boardNo);
				
				if(board != null) {
					request.setAttribute("board", board);
					path = "/WEB-INF/views/board/boardUpdate_summer2.jsp";
					view = request.getRequestDispatcher(path);
					view.forward(request, response);
				}else {
					request.getSession().setAttribute("swalIcon", "error");
					request.getSession().setAttribute("swalTitle", "오류");
					response.sendRedirect(request.getHeader("referer"));
				}
			}
			
			//게시글 수정 service-----------------------------------
			else if(command.equals("/update.do")) {
				errorMsg = "게시글 수정 중 오류 발생";

				String title = request.getParameter("boardTitle");
				String content = request.getParameter("Contents");
				int boardNo = Integer.parseInt(request.getParameter("no"));
				
				
				int boardWriter = 0; // 회원번호

				if (request.getSession().getAttribute("BrokerNo") != null) {

					boardWriter = (int) request.getSession().getAttribute("BrokerNo");

				} else {
					boardWriter = (int) request.getSession().getAttribute("MemNo");
				}

				Map<String, Object> map = new HashMap<String, Object>();
				map.put("boardTitle", title);
				map.put("boardContent", content);
				map.put("boardWriter", boardWriter);
				map.put("boardNo", boardNo);

				//map에 있는 회원정보 서비스로 넘기기
				int result = service.updateBoard(map);
				path = "view.do?cp="+cp+"&no=" +boardNo;
				
				String sk = request.getParameter("sk");
				String sv = request.getParameter("sv");
				
				if(sk != null && sv != null) {
					path += "&sk=" +sk + "&sv=" + sv;
				}
				
				if (result > 0) { 
					swalIcon = "success";
					swalTitle = "게시글 수정 성공";
				} else {
					swalIcon = "error";
					swalTitle = "게시글 수정 실패";
				}
				request.getSession().setAttribute("swalIcon", swalIcon);
				request.getSession().setAttribute("swalTitle", swalTitle);
				response.sendRedirect(path);
			}

			// 게시글 삭제
			else if (command.equals("/delete.do")) {
				errorMsg = "게시글 삭제 과정에서 오류 발생";
				int boardNo = Integer.parseInt(request.getParameter("no"));

				int result = service.deleteBoard(boardNo);

				if (result > 0) {// 삭제성공
					swalIcon = "success";
					swalTitle = "게시글 삭제 성공";
					path = "list.do";
				} else { // 삭제 실패
					swalIcon = "error";
					swalTitle = "게시글 삭제 실패";
					path = request.getHeader("referer");
				}
				request.getSession().setAttribute("swalIcon", swalIcon);
				request.getSession().setAttribute("swalTitle", swalTitle);
				response.sendRedirect(path);
			}

		} catch (Exception e) {
			e.printStackTrace();
			path = "/WEB-INF/views/common/errorPage.jsp";
			request.setAttribute("errorMsg", errorMsg);
			view = request.getRequestDispatcher(path);
			view.forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
