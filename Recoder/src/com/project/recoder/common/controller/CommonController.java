package com.project.recoder.common.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project.recoder.common.SendEmail;
import com.project.recoder.common.service.CommonService;
import com.project.recoder.member.model.service.MemberService;


@WebServlet("/common/*")
public class CommonController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI(); // 
		String contextPath = request.getContextPath(); // 
		String command = uri.substring((contextPath + "/common").length()); 
		
		String path = null;
		RequestDispatcher view = null;
		
		String errorMsg = null;
		HttpSession session = request.getSession();
		// 현재 페이지를 얻어옴
		
		CommonService service = new CommonService();
		
		
		try {
			
			//헤더에서 로그인 버튼 눌렀을 때 로그인 폼으로 이동 Controller------------------------------------------------
			if (command.equals("/loginForm.do")) {
				
				path = "/WEB-INF/views/common/loginForm.jsp";
				
				if(request.getSession().getAttribute("beforeUrl") == null) {
					request.getSession().setAttribute("beforeUrl", request.getHeader("referer"));
				}
				
				view = request.getRequestDispatcher(path);
				
				view.forward(request, response);
			}
			
			//헤더 로그아웃 눌렀을 때 Controller --------------------------------------------------------------------
			else if(command.equals("/logout.do")) {
				request.getSession().invalidate();
				
				response.sendRedirect(request.getContextPath()); //메인으로
				
			}
			
			//회원가입 버튼 눌렀을 때 회원가입 폼으로 이동 Controller------------------------------------------------
			else if(command.equals("/signUpForm.do")) {

				path = "/WEB-INF/views/common/signUpForm.jsp";
				
				view = request.getRequestDispatcher(path);
				
				view.forward(request, response);
			}
			

			//회원가입 id 중복검사 controller-----------------------------
			else if(command.equals("/idDupCheck.do")) {
				errorMsg = "id 중복검사 과정에서 오류 발생";
				String id = request.getParameter("userid");
				
				int result = service.idDupCheck(id);
				//0이면 사용해도 되는거
				
				response.getWriter().print(result);
				
				
			}
			//회원가입 닉네임 중복검사 controller-----------------------------
			else if(command.equals("/nickDupCheck.do")) {
				errorMsg = "닉네임 중복검사 과정에서 오류 발생";
				String nickname = request.getParameter("nickname");
				
				int result = service.nickDupCheck(nickname);
				//0이면 사용해도 되는거
				
				response.getWriter().print(result);
			}
			
			//아이디 찾기 폼 연결 controller -=--------------------
			else if(command.equals("/searchIdForm.do")) {

				path = "/WEB-INF/views/common/searchId.jsp";
				
				view = request.getRequestDispatcher(path);
				
				view.forward(request, response);
			}
			
			//비밀번호 찾기 폼 연결 controller---------------
			else if(command.equals("/searchPwForm.do")) {

				path = "/WEB-INF/views/common/searchPw.jsp";
				
				view = request.getRequestDispatcher(path);
				
				view.forward(request, response);
			}
			
			//아이디 찾기 controller----------------
			else if(command.equals("/searchId.do")) {
				errorMsg = "아이디 찾기 과정 중 오류 발생";
				
				String nickname = request.getParameter("username");
				String email = request.getParameter("email");
				String code = request.getParameter("code");
				
				Map<String, Object> map = new HashMap<String, Object>();
				
				map.put("nickname", nickname);
				map.put("email", email);
				
				String memId = service.searchId(map);
				
				if(memId != null) { //아이디 있을 경우
					session.setAttribute("memId", memId);
					
					path = "/WEB-INF/views/common/searchId_next.jsp";
					
					view = request.getRequestDispatcher(path);
					
					view.forward(request, response);
					
					
				}else { //아이디 없을 경우
					session.setAttribute("swalIcon", "error");
					session.setAttribute("swalTitle", "아이디 찾기 실패");
					session.setAttribute("swalText", "닉네임 또는 이메일을 확인해주세요."); 
					
					response.sendRedirect(request.getHeader("referer"));
					
				}
				
				
			}
			
			
			  //비번찾기 이메일 보내기! 
				else if(command.equals("/sendEmail.do")) {
				  errorMsg = "이메일을 보내는 중 오류 발생";
				  
				  int result = 0;
				  
				  String email = request.getParameter("email");
				  String randomNum = null;
				  randomNum = service.random();
				  
				  //System.out.println("랜덤넘버 : "+randomNum);
				  
				  SendEmail mail = new SendEmail(); 
				  
				   mail.Email(session, email, randomNum); //1이면 잘보낸거
				  
			  
				   //session.setAttribute("randomNum", randomNum);
				   response.getWriter().print(randomNum);
			  
			  }
			 
			
			
			//비밀번호 찾기 controller ------------------------------------------
			else if(command.equals("/searchPw.do")) {
				String nickname = request.getParameter("username");
				String memId = request.getParameter("userid");
				String email = request.getParameter("email");
				String code = request.getParameter("code");
				//System.out.println("회원이 입력한 코드"+code);
				
				
				Map<String, Object> map = new HashMap<String, Object>();
				
				map.put("nickname", nickname);
				map.put("email", email);
				map.put("memId", memId);
				
				int result = service.searchPw(map);
				
				//String memId = service.searchId(map);
				
				if(result > 0) { //회원 있을 경우 비번 변경 하기 service
					
					session.setAttribute("memId", memId);
					
					path = "/WEB-INF/views/common/searchPw_next.jsp";
					
					view = request.getRequestDispatcher(path);
					
					view.forward(request, response);
					
					
				}else { //회원 없을 경우
					session.setAttribute("swalIcon", "error");
					session.setAttribute("swalTitle", "비밀번호 찾기 실패");
					session.setAttribute("swalText", "닉네임 또는 이메일을 확인해주세요."); 
					
					response.sendRedirect(request.getHeader("referer"));
					
				}
			
			}
			//비밀번호 찾기에서 비밀번호 변경하기 controller-------------------------------
			else if(command.equals("/setPw.do")) {
				String password = request.getParameter("password1");
				String memId = (String) session.getAttribute("memId");
				
				Map<String, Object> map = new HashMap<String, Object>();
				
				map.put("memId", memId);
				map.put("password", password);
				int result = service.setPw(map);
				
				String swalIcon = null;
				String swalTitle = null;
				String swalText = null;
				
				String url = null;
				
				if(result > 0) { //회원비번 바뀌었을 때
					// 로그인 페이지로 이동(메인 화면 재요청)
					swalIcon = "success";
					swalTitle = "비밀번호가 변경되었습니다.";
					swalText = "로그인으로 돌아갑니다.";
					url = "loginForm.do";
				}else { //비번 못바꿈
					swalIcon = "error";
					swalTitle = "비밀번호 변경 실패!";
					swalText = "문제가 지속될 경우 고객센터로 문의 바랍니다.";
					 session.removeAttribute("memId");
					url = request.getContextPath();
				}
				
				session.setAttribute("swalIcon", swalIcon);
				session.setAttribute("swalTitle", swalTitle);
				session.setAttribute("swalText", swalText);
				
				response.sendRedirect(url);
				
				
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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
