package com.project.recoder.broker.Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.recoder.broker.model.service.BrokerService;
import com.project.recoder.broker.model.vo.Broker;
import com.project.recoder.room.model.vo.PageInfo;


@WebServlet("/broker/*")
public class brokerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String uri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = uri.substring((contextPath + "/broker").length());

		String path = null;
		RequestDispatcher view = null;

		String swalIcon = null;
		String swalTitle = null;
		String swalText = null;

		String errorMsg = null;
		
		
		try {
			BrokerService service = new BrokerService();
			
			String cp = request.getParameter("cp");
			
			if(command.equals("/list.do")) {
				errorMsg = "공인중개사 가입승인 리스트 조회 과정에서 오류 발생";
				PageInfo pInfo = service.getPageInfo(cp);
				
				List<Broker> bList = service.selectRequestBrokerList(pInfo);
				
						System.out.println(pInfo);
						for(Broker b : bList) {
							System.out.println(b);
						}
				
				path = "/WEB-INF/views/broker/brokerList.jsp";
				
				request.setAttribute("bList", bList);
				request.setAttribute("pInfo", pInfo);
				
				view=request.getRequestDispatcher(path);
				view.forward(request, response);
			}
			
			
			
			// 공인중개사 회원 가입 승인
			else if(command.equals("/approveEnroll.do")){
				errorMsg = "승인 과정에서 오류 발생";
				String numberList = request.getParameter("numberList");
					//System.out.println(numberList);
				
				int result = service.updateApproveEnroll(numberList);
					//System.out.println(result);
				
				response.getWriter().print(result);
			}
			
			// 공인중개사 승인요청 반려 시 회원 정보 삭제
			else if(command.equals("/rejectEnroll.do")){
				errorMsg = "삭제 과정에서 오류 발생";
				
				String numberList = request.getParameter("numberList");
					System.out.println(numberList);
					
				int result = service.deleteEnroll(numberList);
				
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
