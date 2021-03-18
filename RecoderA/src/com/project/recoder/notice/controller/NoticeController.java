package com.project.recoder.notice.controller;

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
import javax.servlet.http.HttpSession;

import com.project.recoder.admin.model.vo.Admin;
import com.project.recoder.notice.model.service.NoticeService;
import com.project.recoder.notice.model.vo.Notice;
import com.project.recoder.room.model.vo.PageInfo;


@WebServlet("/notice/*")
public class NoticeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		request.setCharacterEncoding("UTF-8");
		String uri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = uri.substring((contextPath + "/notice").length());

		String path = null;
		RequestDispatcher view = null;

		String swalIcon = null;
		String swalTitle = null;
		String swalText = null;

		String errorMsg = null;
	
		try {
			NoticeService service = new NoticeService();
			
			String cp = request.getParameter("cp");
			
			if(command.equals("/list.do")) {
				errorMsg = "공지사항 조회 과정에서 오류 발생";
				
				PageInfo pInfo = service.getPageInfo(cp);
				//System.out.println(pInfo);
				
				
				List<Notice> nList = service.selectNoticeList(pInfo);
				
				//for(Notice n : nList) {
				//	System.out.println(n);
				//}
				
				path = "/WEB-INF/views/notice/noticeList.jsp";
				
				request.setAttribute("nList", nList);
				request.setAttribute("pInfo", pInfo);
				
				view=request.getRequestDispatcher(path);
				view.forward(request, response);
			}
			
			
			
			// 공지사항 작성 화면 전환 Controller
			else if(command.equals("/insertForm.do")) {
				path = "/WEB-INF/views/notice/noticeInsert.jsp";
				view = request.getRequestDispatcher(path);
				view.forward(request, response);
			}
			
			
			// 공지사항 등록 Controller
			else if(command.equals("/insert.do")) {
				errorMsg = "공지사항 등록 과정에서 오류 발생";
				
				String noticeTitle = request.getParameter("noticeTitle");
				String noticeContent = request.getParameter("noticeContent");
				
				response.setContentType("text/html; charset=UTF-8");
				
				HttpSession session = request.getSession();
				int noticeWriter =((Admin)session.getAttribute("loginAdmin")).getAdminNo();
				
				Map<String, Object> map = new HashMap<String, Object>();
				
				map.put("noticeTitle", noticeTitle);
				map.put("noticeContent", noticeContent);
				map.put("noticeWriter", noticeWriter);
				
				// 삽입 성공한 공지사항 번호 저장됨.
				int result = service.insertNotice(map);
				
				if(result>0) {
					
					path = "list.do";
					swalIcon = "success";
					swalTitle = "공지사항이 등록되었습니다.";
				}else {
					path = "list.do";
					swalIcon="error";
					swalTitle="공지사항 등록 실패";
				}
				
				session.setAttribute("swalIcon", swalIcon);
				session.setAttribute("swalTitle", swalTitle);
				
				response.sendRedirect(path);
			}
			
			else if(command.equals("/deleteNotice.do")){
				errorMsg = "공지사항 삭제 과정에서 오류 발생";
				
				String numberList = request.getParameter("numberList");
				
				//System.out.println(numberList);
				
				int result = service.updateNoticeDelete(numberList);
				//System.out.println(result);
				response.getWriter().print(result);
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
