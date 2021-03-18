package com.project.recoder.searchFakeRoom.controller;

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

import com.project.recoder.notice.model.vo.PageInfo;
import com.project.recoder.room.model.vo.FakeRoom;
import com.project.recoder.room.model.vo.ReportComment;
import com.project.recoder.searchFakeRoom.service.SearchFakeRoomService;


@WebServlet("/searchReportRoom.do")
public class SearchFakeRoomController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String searchValue = request.getParameter("sv");
		
		String cp = request.getParameter("cp");
		
		try {
			SearchFakeRoomService service = new SearchFakeRoomService();
			
			Map<String, Object> map = new HashMap<String, Object>();
			
			map.put("searchValue", searchValue);
			
			map.put("currentPage", cp);
			
			PageInfo pInfo = service.getPageInfo(map);
			
			List<FakeRoom> fList = service.searchFakeRoomList(map, pInfo);
			
			List<ReportComment> rList = service.searchReportList();
			
			
			//결과확인
				//System.out.println(pInfo);

				//for(FakeRoom f : fList) { System.out.println(f); }

				//for(ReportComment r : rList) { System.out.println(r); }

		
			String path = "/WEB-INF/views/room/fakeRoom.jsp";

			request.setAttribute("fList", fList);
			request.setAttribute("rList", rList);
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
