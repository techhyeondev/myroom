package com.project.recoder.message.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.project.recoder.broker.model.vo.Broker;
import com.project.recoder.member.model.vo.Member;
import com.project.recoder.message.model.service.MessageService;
import com.project.recoder.message.model.vo.Message;

@WebServlet("/message/*")
public class MessageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String uri = request.getRequestURI(); // 
		String contextPath = request.getContextPath(); // 
		String command = uri.substring((contextPath + "/message").length()); 
		
		String path = null;
		RequestDispatcher view = null;
		
		String errorMsg = null;
		try {
			
			MessageService service = new MessageService();
			
			if(command.equals("/message.do")) {

				int memNo = 0;
				try {
					Broker loginMember = (Broker)request.getSession().getAttribute("loginMember");
					memNo = loginMember.getMemNo();
				} catch (Exception e) {
					Member login = (Member)request.getSession().getAttribute("loginMember");
					memNo = login.getMemNo();
					
				}
				
				System.out.println(memNo);
				
				List<Message> message = new ArrayList<Message>();
				message = service.messageList(memNo);
	
				System.out.println(message);
				request.setAttribute("memNo", memNo);
				request.setAttribute("message", message);
				
				path = "/WEB-INF/views/message/message.jsp";
			    view = request.getRequestDispatcher(path);
			    view.forward(request, response);
			}
			
			// 쪽지 보내기 ======================================================================================
			else if (command.equals("/messageSend.do")) {
				
				String msgContext = request.getParameter("msgContext");
				int brokerNo = Integer.parseInt(request.getParameter("brokerNo"));
				int myNo = Integer.parseInt(request.getParameter("myNo"));
				
				int result = service.messageSend(msgContext, brokerNo, myNo);
				
				if(result > 0) {
					
					path = "/WEB-INF/views/message/message.jsp";
				    view = request.getRequestDispatcher(path);
				    view.forward(request, response);
				}

			}
			// 쪽지 목록 ------------------------------------------------------------------------------------
			else if(command.equals("/messageUnI.do")) {
				

				int memNo = 0;
				try {
					Broker loginMember = (Broker)request.getSession().getAttribute("loginMember");
					memNo = loginMember.getMemNo();
				} catch (Exception e) {
					Member login = (Member)request.getSession().getAttribute("loginMember");
					memNo = login.getMemNo();
					
				}
				
				String you = request.getParameter("you");
				String i = request.getParameter("i");
				
				List<Message> mChat = service.messageUnI(you, i);
				
				Map<String, Object> map = new HashMap<String, Object>();
				//map.put("memNo", memNo);
				map.put("mChat", mChat);
				
				Gson gson = new GsonBuilder().setDateFormat("HH:mm").create();
				gson.toJson(map, response.getWriter());
				
			}
			
			// 쪽지보내기 ------------------------------------------------------------------------------------------
			else if(command.equals("/messageISend.do")) {
				int memNo = 0;
				try {
					Broker loginMember = (Broker)request.getSession().getAttribute("loginMember");
					memNo = loginMember.getMemNo();
				} catch (Exception e) {
					Member login = (Member)request.getSession().getAttribute("loginMember");
					memNo = login.getMemNo();
					
				}
				
				
				String you = request.getParameter("you");
				String i = request.getParameter("i");
				String myText = request.getParameter("myText");
				
				
				int numI = Integer.parseInt(i);
				int numYou = Integer.parseInt(you);
				
				int result = service.messageSend(myText, numYou, numI);
				
				List<Message> mChat = service.messageUnI(you, i);
				
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("mChat", mChat);
				
				Gson gson = new GsonBuilder().setDateFormat("HH:mm").create();
				gson.toJson(map, response.getWriter());
				
			}
			
			// 쪽지 삭제 =====================================================================
			else if(command.equals("/messageDelete.do")) {
				
				int memNo = 0;
				
				try {
					Broker loginMember = (Broker)request.getSession().getAttribute("loginMember");
					memNo = loginMember.getMemNo();
				} catch (Exception e) {
					Member login = (Member)request.getSession().getAttribute("loginMember");
					memNo = login.getMemNo();
					
				}
				
				String you = request.getParameter("you");
				String i = request.getParameter("i");
				String myText = request.getParameter("myText");

				
				int result = service.messageDelete(myText, you, i);
				
				
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
		doGet(request, response);
	}

}
