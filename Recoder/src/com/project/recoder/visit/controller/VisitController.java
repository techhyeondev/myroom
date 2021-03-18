package com.project.recoder.visit.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project.recoder.broker.model.vo.Broker;
import com.project.recoder.member.model.vo.Member;
import com.project.recoder.room.model.vo.Room;
import com.project.recoder.visit.model.service.VisitService;
import com.project.recoder.visit.model.vo.Visit;



@WebServlet("/visit/*")
public class VisitController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String uri = request.getRequestURI(); // 
		String contextPath = request.getContextPath(); // 
		String command = uri.substring((contextPath + "/visit").length()); 
		
		String path = null;
		RequestDispatcher view = null;
		
		String errorMsg = null;
		
		HttpSession session = request.getSession();
		
		// 현재 페이지를 얻어옴
		String cp = request.getParameter("cp");
		
		try {
			
			VisitService service = new VisitService();
			
			if (command.equals("/visit.do")) {
				
				Broker loginMember = (Broker)request.getSession().getAttribute("loginMember");
				int brokerNo =  loginMember.getMemNo();
				
				List<Room> room = service.selectRoom(brokerNo);
				List<Visit> visit = service.selectVisit();
				List<Room> rImg = service.selectRoomImg(brokerNo);
				
				request.setAttribute("rImg", rImg);
				request.setAttribute("room", room);
				request.setAttribute("visit", visit);
				
				path = "/WEB-INF/views/broker/visitCheck.jsp";
			    view = request.getRequestDispatcher(path);
			    view.forward(request, response);
			
			}
			
			else if (command.equals("/visitSend.do")) {
				
				int result = 0;
				int roomNo = Integer.parseInt(request.getParameter("no"));
				Member loginMember = (Member)request.getSession().getAttribute("loginMember");
				
				int memNo = loginMember.getMemNo();
				int visitCd = Integer.parseInt(request.getParameter("result"));
				result = service.visitSend(roomNo, memNo, visitCd);
				response.getWriter().print(result);
				
			}
			else if(command.equals("/visitAccept.do")) {
				int result = 0;
				int roomNo = Integer.parseInt(request.getParameter("roomNo"));
				int memNo = Integer.parseInt(request.getParameter("memNo"));
				
				result = service.visitRoomCheck(roomNo, memNo);
				response.getWriter().print(result);
			}
			
			
		} catch (Exception e) {
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
