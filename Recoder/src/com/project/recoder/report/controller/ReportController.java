package com.project.recoder.report.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.recoder.member.model.vo.Member;
import com.project.recoder.report.model.service.ReportService;

@WebServlet("/report/*")
public class ReportController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String uri = request.getRequestURI(); // 
		String contextPath = request.getContextPath(); // 
		String command = uri.substring((contextPath + "/report").length()); 
		
		String path = null;
		RequestDispatcher view = null;
		
		String errorMsg = null;
		
		// 현재 페이지를 얻어옴
		String cp = request.getParameter("cp");
		
		try {
			ReportService service = new ReportService();
			
			if(command.equals("/reportSend.do")) {
				String reportTitle = request.getParameter("reportTitle");
				String reportInfo = request.getParameter("reportInfo");
				int roomNo = Integer.parseInt(request.getParameter("roomNo"));
				int categoryCD = Integer.parseInt(request.getParameter("category"));
				
				int memNo = ((Member)request.getSession().getAttribute("loginMember")).getMemNo();
				int result = service.reportSend(reportTitle, reportInfo, roomNo, categoryCD, memNo);
				
				if (result > 0) {
					System.out.println("성공");
				}else {
					System.out.println("실패");
				}
				
			}
			
			if(command.equals("/reportChk.do")) {
				
				int roomNo = Integer.parseInt(request.getParameter("roomNo"));
				int memNo = Integer.parseInt(request.getParameter("memNo"));
				
				int result = service.reportChk(roomNo, memNo);
				
				response.getWriter().print(result);
				
			}
			
		}catch (Exception e) {
			e.printStackTrace();
			path = "/WEB-INF/views/common/errorPage.jsp"; // 수정
			request.setAttribute("errorMsg", errorMsg);
			view = request.getRequestDispatcher(path);
			view.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
