package com.project.recoder.searchMember.controller;

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

import com.project.recoder.member.model.vo.Member;
import com.project.recoder.room.model.vo.PageInfo;
import com.project.recoder.searchMember.model.service.SearchMemberService;


@WebServlet("/searchMember.do")
public class SearchMemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 배열의 첫번째값 얻어오기
		String searchKey1 = request.getParameter("sk1");
		
		// 배열의 두번째값 얻어오기
		String searchKey2 = request.getParameter("sk2");
				
				
		//System.out.println(searchKey1); //확인 ok gMem, 
		//System.out.println(searchKey2); //확인 ok stop 출력
		
		String cp = request.getParameter("cp");
		
		try {
			
			SearchMemberService service = new SearchMemberService();
			
			Map<String, Object> map = new HashMap<String, Object>();
			
			map.put("searchKey1", searchKey1);
			
			map.put("searchKey2", searchKey2);
			
			map.put("currentPage", cp);
			
			PageInfo pInfo = service.getPageInfo(map);
			
				//System.out.println(pInfo);
			
			
			List<Member> mList = service.searchMemberList(map, pInfo);
			
				//for(Member m : mList) {
				//	System.out.println(m);
				//}

			
			String path = "/WEB-INF/views/member/memberManage.jsp";
			
			request.setAttribute("mList", mList);
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
