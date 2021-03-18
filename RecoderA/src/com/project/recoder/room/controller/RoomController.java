package com.project.recoder.room.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.recoder.room.model.service.RoomService;
import com.project.recoder.room.model.vo.FakeRoom;
import com.project.recoder.room.model.vo.PageInfo;
import com.project.recoder.room.model.vo.ReportComment;
import com.project.recoder.room.model.vo.Room;


@WebServlet("/room/*")
public class RoomController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String uri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = uri.substring((contextPath + "/room").length());

		String path = null;
		RequestDispatcher view = null;

		String swalIcon = null;
		String swalTitle = null;
		String swalText = null;

		String errorMsg = null;
	
		try {
			RoomService service = new RoomService();
			
			String cp = request.getParameter("cp");
			
			// 매물 목록 조회 Controller
			if(command.equals("/roomStatus.do")) {
				errorMsg = "매물 목록 조회 과정에서 오류 발생";
				
				PageInfo pInfo = service.getPageInfo(cp);
				//System.out.println(pInfo);
				List<Room> rList = service.selectRoomList(pInfo);
				
				//for(Room r : rList) {
				//	System.out.println(r);
				//}
				
				
				path = "/WEB-INF/views/room/roomManage.jsp";
				
				request.setAttribute("rList", rList);
				request.setAttribute("pInfo", pInfo);
				
				view=request.getRequestDispatcher(path);
				view.forward(request, response);
			}
			
			
			else if(command.equals("/deleteRoom.do")){
				errorMsg = "매물 삭제 과정에서 오류 발생";
				
				
				String numberList = request.getParameter("numberList");
				
				//System.out.println(numberList);
				
				int result = service.updateRoomDelete(numberList);
				//System.out.println(result);
				response.getWriter().print(result);
			}
			
			
			
			else if(command.equals("/recoverRoom.do")) {
				errorMsg = "매물 복구 과정에서 오류 발생";
				
				String numberList = request.getParameter("numberList");
				int result = service.updateRoomRecover(numberList);
				response.getWriter().print(result);
				
			}
			
			
			// 신고 매물 조회
			else if(command.equals("/fakeRoom.do")){
				errorMsg = "허위 매물 조회 과정에서 오류 발생";
				
				PageInfo pInfo = service.getPageInfo1(cp);
				
				List<FakeRoom> fList = service.selectFakeList(pInfo);
				
				List<ReportComment> rList = service.selectReportList();
				
				/*
				 * System.out.println(pInfo);
				 * 
				 * for(FakeRoom f : fList) { System.out.println(f); }
				 * 
				 * for(ReportComment r : rList) { System.out.println(r); }
				 */

				
				path = "/WEB-INF/views/room/fakeRoom.jsp";
				
				request.setAttribute("pInfo", pInfo);
				request.setAttribute("fList", fList);
				request.setAttribute("rList", rList);
				
				
				
				view=request.getRequestDispatcher(path);
				view.forward(request, response);
				
				
			}
			
			
			// 신고 매물 삭제
			else if(command.equals("/deleteFakeRoom.do")){
				
				String roomNo = request.getParameter("RoomNo");
				
				System.out.println(roomNo); //확인 ok
				
				int result = service.updateFakeRoomDelete(roomNo);
				
				System.out.println(result);
				
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
