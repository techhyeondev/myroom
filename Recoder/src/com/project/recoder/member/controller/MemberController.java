package com.project.recoder.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project.recoder.member.model.service.MemberService;
import com.project.recoder.member.model.vo.Member;
import com.project.recoder.room.model.vo.Room;
import com.project.recoder.room.model.vo.RoomImg;

@WebServlet("/member/*")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI(); // 
		String contextPath = request.getContextPath(); // 
		String command = uri.substring((contextPath + "/member").length());  
		
		
		String url = null;
		String path = null;
		RequestDispatcher view = null;
		
		String errorMsg = null;
		
		HttpSession session = request.getSession();
		// 현재 페이지를 얻어옴
		String cp = request.getParameter("cp");
		
		MemberService service = new MemberService();
		
		
		String chkPw = request.getParameter("chkPw");
		Member member = null;
		Member loginMember = null;

		
		try {
			
			
			//일반회원 로그인 controller
			if (command.equals("/login.do")) {
				errorMsg = "로그인 과정에서 오류 발생";
				String memId = request.getParameter("userId"); //아이디
				String memPw = request.getParameter("userPw"); //비번
				String remember = request.getParameter("remember"); //아이디 저장
				
				member = new Member();
				member.setMemId(memId);
				member.setMemPw(memPw);
				
					loginMember = service.loginMember(member);
					
					session = request.getSession();
					
					url = null;
					
					if(loginMember != null) { //로그인 성공
						session.setMaxInactiveInterval(60 * 30);
					
						session.setAttribute("loginMember", loginMember);
						session.setAttribute("MemNo", loginMember.getMemNo());		
						System.out.println("일반회원 로그인 확인");
						Cookie cookie = new Cookie("saveIdG", memId);

						if(remember != null) {
							cookie.setMaxAge(60*60*24*7);
						}else {
							cookie.setMaxAge(0);
						}
						cookie.setPath(request.getContextPath());
			
						response.addCookie(cookie);
						url = (String)session.getAttribute("beforeUrl");
						
						
					}else { //로그인 실패
						session.setAttribute("swalIcon", "error");
						session.setAttribute("swalTitle", "로그인 실패");
						session.setAttribute("swalText", "아이디 또는 비밀번호를 확인해주세요");
						
						
						url = request.getHeader("referer"); 
						
						String beforeUrl = (String)session.getAttribute("beforeUrl");
						session.setAttribute("beforeUrl",  beforeUrl);
					}
					
					response.sendRedirect(url);
					
			}
			
			
			//회원가입 contolloer -------------------------------------------------------------------------------
			else if(command.equals("/msignUp.do")) {
				errorMsg = "회원가입 과정에서 오류 발생";
				//전달받은 파라미터를 모두 변수에 저장
				String memId = request.getParameter("userid");
				String memPw = request.getParameter("password");
				String memEmail = request.getParameter("email");
				String memNick = request.getParameter("nickname");
				String memTel = request.getParameter("usertel");
				
				//Member객체를 생성하여 파라미터를 모두 저장
				member = new Member(memId, memPw, memNick, memTel, memEmail);
					int result = service.signUp(member);
					
					
					//swalIcon
					String swalIcon = null;
					String swalTitle = null;
					String swalText = null;
					
					if(result>0) { //성공
						swalIcon = "success";
						swalTitle = "회원가입 성공!";
						swalText = memNick + "님의 회원가입을 환영합니다.";
						
					}else { //실패
						swalIcon = "error";
						swalTitle = "회원가입 실패!";
						swalText = "문제가 지속될 경우 고객센터로 문의 바랍니다.";
					}
					
					 session = request.getSession();
					
					session.setAttribute("swalIcon", swalIcon);
					session.setAttribute("swalTitle", swalTitle);
					session.setAttribute("swalText", swalText);
					
					//회원가입 진행 후 메인 페이지로 이동(메인 화면 재요청)
					response.sendRedirect(request.getContextPath());
					
					
			}
			
			//마이페이지폼 컨트롤러------------------
			else if(command.equals("/memberMyPage.do")) {
				loginMember = (Member)session.getAttribute("loginMember");
				int memNo = loginMember.getMemNo();
				List<Room> roomList = service.selectRoomList(memNo); //찜한매물 리스트
				
				if(roomList != null) {
					List<RoomImg> imgList = service.selectimgList(memNo); //찜한 매물 이미지		
					
					if(!imgList.isEmpty()) {
						request.setAttribute("imgList", imgList);
					}
				}
				
				
				List<Room> reviewRoomList = service.selectReviewList(memNo);
				if(reviewRoomList != null) {
					List<RoomImg> reviewImgList = service.selectReviewimg(memNo); //후기 매물 이미지		
					
					if(!reviewImgList.isEmpty()) {
						request.setAttribute("reviewImgList", reviewImgList);
					}
				}
		            
				path = "/WEB-INF/views/member/memberMyPage.jsp";
				request.setAttribute("roomList", roomList);
				request.setAttribute("reviewRoomList", reviewRoomList);
				
				view = request.getRequestDispatcher(path);
				view.forward(request, response);
	    	}
			
			//정보수정폼 컨트롤러---------------------
			else if(command.equals("/updateMember.do")) {
	    		path = "/WEB-INF/views/member/updateMember2.jsp";
	    		view = request.getRequestDispatcher(path);
	    		view.forward(request, response);
	    	}
			
			//비밀번호 체크 컨트롤러-------------------------------------
			else if (command.equals("/checkMember.do")) {
				loginMember = (Member)session.getAttribute("loginMember");
				int memNo = loginMember.getMemNo();
				
				
				String password = request.getParameter("userPw");

		         // 비즈니스 로직 처리 후 결과 반환 받기
		         int result = new MemberService().chkPwd(memNo, password);

		         
		         System.out.println(result);
		         

		         response.getWriter().print(result);
		         
		    	}
			
			//탈퇴 컨트롤러----------------------------
			else if(command.equals("/secessionMember.do")){
				loginMember = (Member)session.getAttribute("loginMember");
				int memNo = loginMember.getMemNo();
				String password = request.getParameter("userPw");
		         int result = new MemberService().updateStatus(memNo, password);
		         
		         if(result > 0) { // 비밀번호 변경 성공

			            // 로그아웃 == 세션 무효화
			            session.invalidate();
			            // 세션 무효화 시 현재 얻어온 세션을 사용할 수 없는 문제점이 있다!
			            // -> 새로운 세션을 얻어와야한다.
			            session = request.getSession();
			         
			         }

		         response.getWriter().print(result);
	    	}
			
	    	
			//정보수정 컨츠롤러---------------------------
	    	else if(command.equals("/updateMemberServlet.do")){
	    		
	    		String pw1 = request.getParameter("pw1");
	    		
	    		
	    		//전송된 파라미터를 변수에 저장
	    		String memberNickname = request.getParameter("nickname");
	    		String memberPw = request.getParameter("password");
	    		String memberEmail = request.getParameter("email");
	    		
	    		String tel = request.getParameter("usertel");
	    		
	    		session = request.getSession();
	    		loginMember = (Member)session.getAttribute("loginMember");
	    		
	    		System.out.println(memberPw);
	    		//얻어온 수정ㅇ 정보와 회원번호를 하나의 Member객체에 저장
	    		member = new Member();
	    		member.setMemNo( loginMember.getMemNo() );
	    		member.setMemNick(memberNickname);
	    		member.setMemEmail(memberEmail);
	    		member.setMemTel(tel);
	    		
	    		
	    		
	    		//swalIcon
				String swalIcon = null;
				String swalTitle = null;
				String swalText = null;
				
	    			//비즈니스 로직 수행 후 결과 반환
	    			int result = new MemberService().updateMember(member, memberPw); 	 
	    			
	    	         if(result > 0) { // 성공
	    	        	 
	    	            
	    	            //DB데이터가 갱신된 경우 Session에 있는 회원 정보도 갱신되어야함
	    	            //기존 로그인 정보에서 id를 얻어와 갱신에 사용된 member 객체에 저장
	    	            member.setMemId(loginMember.getMemId());
	    	            member.setMemGrade(loginMember.getMemGrade());
	    	            
	    	            //Session에 있는 loginmember 정보를 mmember로 갱신
	    	            session.setAttribute("loginMember", member);
	    	            
	    	            swalIcon = "success";
						swalTitle = "정보수정 성공!";
						swalText = "정보 수정에 성공하였습니다.";
	    	            
	    	         }else {
	    	        	 swalIcon = "error";
	 					swalTitle = "정보수정 실패!";
	 					swalText = "문제가 지속될 경우 고객센터로 문의 바랍니다.";
	    	         }
						
						session.setAttribute("swalIcon", swalIcon);
						session.setAttribute("swalTitle", swalTitle);
						session.setAttribute("swalText", swalText);
	    	        //수정 완료 후 다시 내정보 페이지로 재요청
	    	        response.sendRedirect("memberMyPage.do");
	    	}
			
			
			//찜하기----------------------------------
	    	else if(command.equals("/heart.do")){
	    		String room_no = request.getParameter("roomNo");
				String mem_no = request.getParameter("memNo");

				System.out.println(room_no);
				
				int result = service.heartcheck(room_no,mem_no);
				int result2 = 0;
				
				System.out.println(result);
				if(result>0) {
					result = service.heartDelete(room_no,mem_no);
					if(result>0) result2=-1; //삭제성공
					else result2 = 0;
				}else {
					result = service.heartInsert(room_no,mem_no);
					if(result>0) result2=1; //삽입성공
					else result2 = 0;
				}
				
				response.getWriter().print(result2);
				
	    	}
			
	    	else if(command.equals("/heartChk.do")){
	    		String room_no = request.getParameter("roomNo");
				String mem_no = request.getParameter("memNo");

				System.out.println(room_no);
				
				int result = service.heartcheck(room_no,mem_no);
				
				response.getWriter().print(result);
				
	    	}
			
			
	    	else if(command.equals("/nickDupCheck.do")) {
				System.out.println("졸려");
				errorMsg = "닉네임 중복검사 과정에서 오류 발생";
				String nickname = request.getParameter("nickname");
				
				int result = service.nickDupCheck(nickname);
				//0이면 사용해도 되는거
				
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
