package com.project.recoder.searchNotice.controller;

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

import com.project.recoder.notice.model.vo.Notice;
import com.project.recoder.notice.model.vo.PageInfo;
import com.project.recoder.searchNotice.model.service.SearchNoticeService;

@WebServlet("/searchNotice.do")
public class SearchNoticeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String searchValue = request.getParameter("sv");
	
		String cp = request.getParameter("cp");
		
		try {
			SearchNoticeService service = new SearchNoticeService();
			
			Map<String, Object> map = new HashMap<String, Object>();
			
			map.put("searchValue", searchValue);
			
			map.put("currentPage", cp);
			
			PageInfo pInfo = service.getPageInfo(map);
			
			// 결과확인
			System.out.println(pInfo);
			
			List<Notice> nList = service.searchNoticeList(map, pInfo);
			
			//결과확인
//			for(Notice n : nList) {
//				System.out.println(n);
//			}
			
			String path = "/WEB-INF/views/notice/noticeList.jsp";
			request.setAttribute("nList", nList);
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
