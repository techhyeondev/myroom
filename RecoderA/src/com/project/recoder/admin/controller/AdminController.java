package com.project.recoder.admin.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project.recoder.admin.model.service.AdminService;
import com.project.recoder.admin.model.vo.Admin;

@WebServlet("/admin/*")
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String uri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = uri.substring((contextPath + "/admin").length());

		String path = null;
		RequestDispatcher view = null;

		String swalIcon = null;
		String swalTitle = null;
		String swalText = null;

		String errorMsg = null;
		
		try {
			AdminService service = new AdminService(); 
				

			// 관리자 로그인 Controller------------------------------
			if(command.equals("/login.do")) {
				errorMsg = "로그인 과정에서 오류 발생";
				
				request.setCharacterEncoding("UTF-8");
				
				String adminId = request.getParameter("adminId");
				String adminPw = request.getParameter("adminPw");
				
				Admin admin = new Admin();
				admin.setAdminId(adminId);
				admin.setAdminPw(adminPw);
				
				Admin loginAdmin = new AdminService().loginAdmin(admin);
				//System.out.println(loginAdmin);
				response.setContentType("text/html; charset=UTF-8");
				
				HttpSession session = request.getSession();
				
				if(loginAdmin !=null) {
					session.setMaxInactiveInterval(60*30);
					
					session.setAttribute("loginAdmin", loginAdmin);
				}else {
					session.setAttribute("swalIcon", "error");
					session.setAttribute("swalTitle", "로그인 실패");
					session.setAttribute("swalText", "아이디 또는 비밀번호를 확인해주세요.");
				}
				response.sendRedirect(request.getHeader("referer"));
			}
			
			
			// 관리자 로그아웃 컨트롤러
			else if(command.equals("/logout.do")) {
				errorMsg = "로그아웃 과정에서 오류 발생";
				
				request.getSession().invalidate();
				
				response.sendRedirect(request.getContextPath());
			}
			
			
			
			
		}catch(Exception e) {
			e.printStackTrace();
			path = "/WEB-INF/views/common/errorpage.jsp";
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
