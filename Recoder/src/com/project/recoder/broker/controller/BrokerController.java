package com.project.recoder.broker.controller;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.project.recoder.broker.model.service.BrokerService;
import com.project.recoder.broker.model.vo.Broker;
import com.project.recoder.common.MyFileRenamePolicy;
import com.project.recoder.room.model.vo.Room;
import com.project.recoder.room.model.vo.RoomImg;
import com.project.recoder.wrapper.EncryptWrapper;

@WebServlet("/broker/*")
public class BrokerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI(); // 
		String contextPath = request.getContextPath(); // 
		String command = uri.substring((contextPath + "/broker").length()); 
		
		BrokerService service = new BrokerService();
		
		String path = null;
		RequestDispatcher view = null;
		
		String errorMsg = null;
		
		HttpSession session = request.getSession();
		
		// 현재 페이지를 얻어옴
		String cp = request.getParameter("cp");
		
		try {
			
if (command.equals("/brokerInfo.do")) {
				
				Broker loginMember = (Broker)request.getSession().getAttribute("loginMember");
				
				Map<String, String> broker = new HashMap<String, String>();
				broker.put("brokerNick", loginMember.getMemNick());
				broker.put("brokerAddr", loginMember.getBrokerAddr());
				broker.put("brokerEmail", loginMember.getMemEmail());
				broker.put("brokerTel", loginMember.getMemTel());
				
				
				
				int memNo = loginMember.getMemNo();
				List<Room> roomList = service.selectRoomList(memNo); //찜한매물 리스트
				if(roomList != null) {
					List<RoomImg> imgList = service.selectimgList(memNo); //찜한 매물 이미지		
					
					if(!imgList.isEmpty()) {
						request.setAttribute("imgList", imgList);
					}
				}
				
				System.out.println(broker);;
				
				request.setAttribute("memNo", memNo);
				request.setAttribute("broker", broker);
				path = "/WEB-INF/views/broker/brokerInfo.jsp";
				request.setAttribute("roomList", roomList);
			    view = request.getRequestDispatcher(path);
			    view.forward(request, response);
			}
			
			else if(command.equals("/brokerPwCheck.do")){
				Broker loginMember = (Broker)request.getSession().getAttribute("loginMember");
				int memNo = loginMember.getMemNo();
				
				
				String password = request.getParameter("userPw");
				
				String pw = service.selectPw(memNo);
				
				int pwch = 0;
				
				if(password.equals(pw)) {
					pwch = 1;
					pwch = service.brokerStatusDl(memNo);
					session.invalidate();
				}
				
				
//				request.setAttribute("pwch", pwch);
				
//				path = request.getContextPath();
				response.getWriter().print(pwch);
//			    response.sendRedirect(path);
				
				
//				path = contextPath;
//			    response.sendRedirect(path);
				
//				path = "/index.jsp";
//			    view = request.getRequestDispatcher(path);
//			    view.forward(request, response);
				
			}
			
			//브로커 로그인 controller------------------------------------------------------------------
			else if(command.equals("/login.do")) {
				
				String memId = request.getParameter("userId"); //아이디
				String memPw = request.getParameter("userPw"); //비번
				String remember = request.getParameter("remember"); //아이디 저장
				
				Broker broker = new Broker();
				broker.setMemId(memId);
				broker.setMemPw(memPw);
				
				
					Broker loginMember = service.loginBroker(broker);
					
					
				// 6. Session 객체를 얻어와 로그인 정보를 추가함
					session = request.getSession();
					
					String url = null;
					
				// 6-1. 로그인이 성공했을 때만 Session에 로그인 정보 추가하기. 
					if(loginMember != null && loginMember.getApproveFl().equals("Y")) {
					//6-2. 30분 동안 동작이 없을 경우 Session을 만료시킴
						session.setMaxInactiveInterval(60 * 30);
					
					//6-3. Session에 로그인 정보 추가
						session.setAttribute("loginMember", loginMember);
						session.setAttribute("BrokerNo", loginMember.getMemNo());
						//System.out.println(loginMember);
						
					
					//6-4 아이디를 Cookie에 저장하기
					
						//1) 쿠키 객체 생성
						Cookie cookie = new Cookie("saveIdB", memId);
					
						//2) 아이디 저장 checkbox가 체크 되었을 때 쿠키 저장
						if(remember != null) {
							// 3) 일주일동안 쿠키가 유효하도록 설정( 쿠키생명주기설정)
							cookie.setMaxAge(60*60*24*7);
						}else {
							// 4) 아이디 저장이 체크가 안된 경우 기존에 있던 쿠키 파일 삭제
							cookie.setMaxAge(0);
						}
						//5) 쿠키 유효 디렉토리 지정
						cookie.setPath(request.getContextPath());
			
					// 6) 생성된 쿠키를 클라이언트로 전달(응답)
						response.addCookie(cookie);
						//System.out.println("성공"+session.getAttribute("beforeUrl"));
						url = (String)session.getAttribute("beforeUrl");
						
						
					}else if(loginMember != null && loginMember.getApproveFl().equals("N")) {
						//회원가입은 하고 인증은 아직 못받은 회원 
						session.setAttribute("swalIcon", "error");
						session.setAttribute("swalTitle", "미인증 회원");
						session.setAttribute("swalText", "관리자의 승인을 받을 때 까지 기다려주세요.");
						
						url = request.getHeader("referer"); 
						
						String beforeUrl = (String)session.getAttribute("beforeUrl");
						session.setAttribute("beforeUrl",  beforeUrl);
					}
					
					
					
					
					else {
						//7. 로그인이 실패했을 때 "아이디 또는 비밀번호를 확인해주세요."라고 경고창 띄우기
						session.setAttribute("swalIcon", "error");
						session.setAttribute("swalTitle", "로그인 실패");
						session.setAttribute("swalText", "아이디 또는 비밀번호를 확인해주세요");
						
						url = request.getHeader("referer"); 
						
						String beforeUrl = (String)session.getAttribute("beforeUrl");
						session.setAttribute("beforeUrl",  beforeUrl);
					}
					
					response.sendRedirect(url);
					
			}
			// 공인중개사 회원가입 ---------------------------------------------------------------------------
			else if(command.equals("/signUp.do")) {
				errorMsg = "회원가입 과정에서 오류 발생";
				
				//자격증 사진 주소
				int maxSize = 20 * 1024 * 1024; // 20MB == 20 * 1024KB == 20 * 1024 * 1024 Byte
				
				String root = request.getSession().getServletContext().getRealPath("/");
				String filePath = root + "resources/images/brokerInfo/";
				
				//System.out.println("filePath : "+filePath);
				
				MultipartRequest multiRequest = new MultipartRequest(request, filePath, maxSize, "UTF-8", new MyFileRenamePolicy());
				
				
				Broker broker = new Broker();
				
				Enumeration<String> files = multiRequest.getFileNames();
				
				if(files.hasMoreElements()) { //다음 요소가 있다면
					
					String name = files.nextElement(); //img0
					//System.out.println("name : " + name);
					//System.out.println("원본 파일명 : " + multiRequest.getOriginalFileName(name));
					//System.out.println("변경된 파일명 : "+ multiRequest.getFilesystemName(name));
					
					if(multiRequest.getFilesystemName(name) != null) {
						
						
						broker.setBrokerFileName(multiRequest.getFilesystemName(name));
						broker.setBrokerCreti(filePath);
						
					}
				
				}
				
				
			//나머지 파라미터 받아오기
				//전달받은 파라미터를 모두 변수에 저장
				String memId = multiRequest.getParameter("userid");
				String memPw = EncryptWrapper.getSha512( multiRequest.getParameter("password"));
				String memEmail = multiRequest.getParameter("email");
				String memNick = multiRequest.getParameter("nickname");
				String memTel = multiRequest.getParameter("usertel");

				//주소 
				String post = multiRequest.getParameter("post"); //우편번호
				String address1 = multiRequest.getParameter("address1"); //도로명주소
				String address2 = multiRequest.getParameter("address2"); //상세주소

				String brokerAddr= post + " , " +address1 +" , "+address2;
				
				broker.setMemId(memId);
				broker.setMemPw(memPw);
				broker.setMemEmail(memEmail);
				broker.setMemNick(memNick);
				broker.setMemTel(memTel);
				broker.setBrokerAddr(brokerAddr);
				
				int result = service.signUp(broker); //회원가입
				

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
				
				
				
				request.getSession().setAttribute("swalIcon", swalIcon);
				request.getSession().setAttribute("swalTitle", swalTitle);
				request.getSession().setAttribute("swalText", swalText);
				
				//회원가입 진행 후 메인 페이지로 이동(메인 화면 재요청)
				response.sendRedirect(request.getContextPath());
				
				
			}

			else if(command.equals("/checkPw.do")){
				Broker loginMember = (Broker)request.getSession().getAttribute("loginMember");
				
				int memNo = loginMember.getMemNo();
				
				
				String password = request.getParameter("userPw");

		         // 비즈니스 로직 처리 후 결과 반환 받기
		         int result = service.chkPwd(memNo, password);         
		         System.out.println(result);

		         response.getWriter().print(result);
				
			}
			
			else if(command.equals("/updateBroker.do")) {
				
				Broker loginMember = (Broker)request.getSession().getAttribute("loginMember");
				
				Map<String, String> broker = new HashMap<String, String>();
				broker.put("brokerNick", loginMember.getMemNick());
				broker.put("brokerAddr", loginMember.getBrokerAddr());
				broker.put("brokerEmail", loginMember.getMemEmail());
				broker.put("brokerTel", loginMember.getMemTel());
				
				request.setAttribute("broker", broker);
				path = "/WEB-INF/views/broker/updateBroker.jsp";
			    view = request.getRequestDispatcher(path);
			    view.forward(request, response);
				
				
			}
			
			//정보수정 컨츠롤러---------------------------
	    	else if(command.equals("/updateBrokerServlet.do")){
	    		String pw1 = request.getParameter("pw1");
	    		
	    		
	    		//전송된 파라미터를 변수에 저장
	    		String memberNickname = request.getParameter("nickname");
	    		String memberPw = request.getParameter("password");
	    		String memberEmail = request.getParameter("email");
	    		
	    		String tel = request.getParameter("usertel");
	    		String post = request.getParameter("post"); //우편번호
				String address1 = request.getParameter("address1"); //도로명주소
				String address2 = request.getParameter("address2"); //상세주소

				String brokerAddr= post + " , " +address1 +" , "+address2;
	    		
	    		session = request.getSession();
	    		Broker loginMember = (Broker)request.getSession().getAttribute("loginMember");
	    		
	    		//얻어온 수정ㅇ 정보와 회원번호를 하나의 Member객체에 저장
	    		Broker member = new Broker();
	    		member.setMemNo( loginMember.getMemNo() );
	    		member.setMemNick(memberNickname);
	    		member.setMemEmail(memberEmail);
	    		member.setMemTel(tel);
	    		member.setBrokerAddr(brokerAddr);
	    		
	    			//비즈니스 로직 수행 후 결과 반환
	    			int result = new BrokerService().updateMember(member, memberPw); 	 
	    			
	    	         if(result > 0) { // 성공

	    	            
	    	            //DB데이터가 갱신된 경우 Session에 있는 회원 정보도 갱신되어야함
	    	            //기존 로그인 정보에서 id를 얻어와 갱신에 사용된 member 객체에 저장
	    	            member.setMemId(loginMember.getMemId());
	    	            member.setMemGrade(loginMember.getMemGrade());
	    	            
	    	            //Session에 있는 loginmember 정보를 mmember로 갱신
	    	            session.setAttribute("loginMember", member);
	    	            System.out.println(loginMember);
	    	            
	    	         }
	    	        
	    	        //수정 완료 후 다시 내정보 페이지로 재요청
	    	        response.sendRedirect("brokerInfo.do");
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
