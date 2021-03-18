package com.project.recoder.room.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.project.recoder.broker.model.vo.Broker;
import com.project.recoder.common.MyFileRenamePolicy;
import com.project.recoder.room.model.service.RoomService;
import com.project.recoder.room.model.vo.PageInfo;
import com.project.recoder.room.model.vo.Room;
import com.project.recoder.room.model.vo.RoomImg;

@WebServlet("/room/*")
public class RoomController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String uri = request.getRequestURI(); // 
		String contextPath = request.getContextPath(); // 
		String command = uri.substring((contextPath + "/room").length()); 
		
		String path = null;
		RequestDispatcher view = null;
		
		String errorMsg = null;
		
		// 현재 페이지를 얻어옴
		String cp = request.getParameter("cp");
		
		try {
			
			
			RoomService service = new RoomService();
			
			// 매물 등록 창 =================================================================================================
			if(command.equals("/roomInsertForm.do")) {

				path = "/WEB-INF/views/room/roomsInfoInsert.jsp";
			    view = request.getRequestDispatcher(path);
			    view.forward(request, response);
		    }
			
			
			// 매물 등록 시작 =================================================================================================
			else if(command.equals("/roomInsert.do")) {

				// 파일
	        	int maxSize = 20 * 1024 * 1024; // 20MB == 20 * 1024KB == 20 * 1024 * 1024Byte
	        	String root = request.getSession().getServletContext().getRealPath("/");
	        	String filePath = root + "resources/images/rooms/";
	        	
	        	
	        	MultipartRequest multiRequest = new MultipartRequest(request, filePath, maxSize, "UTF-8", new MyFileRenamePolicy());
	        	//														요청		파일위치		크기						이름변경			
	        	
	        	List<RoomImg> mList = new ArrayList<RoomImg>();
	        	
	        	Enumeration<String> files = multiRequest.getFileNames();
	        	
	        	while (files.hasMoreElements()) { // 다음 요소가 있다면
	        		String name = files.nextElement(); // img0
	        		
	        		if(multiRequest.getFilesystemName(name) != null) {
	        			
	        			RoomImg temp = new RoomImg();
	        			temp.setRoomImgName(multiRequest.getFilesystemName(name));
	        			temp.setRoomImgPath(filePath);
	        			
	        			int fileLevel = 0;
	        			switch(name) {
		        			case "img0": fileLevel = 0; break;
		        			case "img1": fileLevel = 1;	break;
		        			case "img2": fileLevel = 2;	break;
		        			case "img3": fileLevel = 3;	break;
		        			case "img4": fileLevel = 4;	break;
		        			case "img5": fileLevel = 5;	break;
		        			case "img6": fileLevel = 6;	break;
		        			case "img7": fileLevel = 7;	break;
		        			case "img8": fileLevel = 8;	break;
		        			
	        			}
	        			
	        			temp.setRoomImgLevel(fileLevel);
	        			
	        			// fList에 추가
	        			mList.add(temp);
	        		}
				} // end while
	        	
	        	
	        	String roomAddr = multiRequest.getParameter("roomAddr");
	        	String typeOfRent = multiRequest.getParameter("typeOfRent");
	        	int deposit = Integer.parseInt(multiRequest.getParameter("deposit"));
	        	int monthRent = Integer.parseInt(multiRequest.getParameter("monthRent"));
	        	int careFee = Integer.parseInt(multiRequest.getParameter("careFee"));
	        	int pubSize = Integer.parseInt(multiRequest.getParameter("pubSize"));
	        	int realSize = Integer.parseInt(multiRequest.getParameter("realSize"));
	        	String roomCount = multiRequest.getParameter("roomCount");
	        	String roomFloor = multiRequest.getParameter("roomFloor");
	        	String roomStruc = multiRequest.getParameter("roomStruc");
				
	        	String tv = multiRequest.getParameter("tv");
	        	String washing = multiRequest.getParameter("washing");
	        	String internet = multiRequest.getParameter("internet");
				String airCon = multiRequest.getParameter("airCon");
				String fridge = multiRequest.getParameter("fridge");
				String bed = multiRequest.getParameter("bed");
				String closet = multiRequest.getParameter("closet");
				String womanOnly = multiRequest.getParameter("womanOnly");
				String pet = multiRequest.getParameter("pet");
				String parking = multiRequest.getParameter("parking");
	        	
				String roomTitle = multiRequest.getParameter("roomTitle");
				String roomInfo = multiRequest.getParameter("roomInfo");
				String stationAddr = multiRequest.getParameter("stationAddr");
				
				// 로그인 얻어오기
				Broker loginMember = (Broker)request.getSession().getAttribute("loginMember");
				
//				int roomBroker = loginMember.getMemNo2();
				int roomBroker = loginMember.getMemNo();
				/*
				 
				
				*/
				
				Map<String, Object> map = new HashMap<String, Object>();
				
				map.put("roomTitle", roomTitle);
				map.put("roomInfo", roomInfo);
				map.put("roomAddr", roomAddr);
				map.put("careFee", careFee);
				map.put("typeOfRent", typeOfRent);
				map.put("deposit", deposit);
				map.put("monthRent", monthRent);
				map.put("roomStruc", roomStruc);
				map.put("roomFloor", roomFloor);
				map.put("pubSize", pubSize);
				map.put("realSize", realSize);
				map.put("roomCount", roomCount);
				map.put("stationAddr", stationAddr);
				
				map.put("washing", washing);
				map.put("airCon", airCon);
				map.put("bed", bed);
				map.put("closet", closet);
				map.put("parking", parking);
				map.put("tv", tv);
				map.put("internet", internet);
				map.put("fridge", fridge);
				map.put("womanOnly", womanOnly);
				map.put("pet", pet);
				
				map.put("roomBroker", roomBroker);
				map.put("mList" ,mList);
				
				// 서비스 실행 
				int result = service.roomInsert(map);
				
				System.out.println(map);
				request.setAttribute("map", map);
				


				path = "view.do?cp=" + cp + "&no=" + result ;
	        	
	        	
	        	response.sendRedirect(path);
				
				
	
				
			}
			
			// 매물 수정 창 =================================================================================================
			else if(command.equals("/roomUpdateForm.do")) {
				
				// 매물 번호
				int roomNo = Integer.parseInt(request.getParameter("no"));
				Room room = service.updateView(roomNo);
				if(room !=null) {
					List<RoomImg> mList = service.selectRoomImg(roomNo);
					if(!mList.isEmpty()) {
						request.setAttribute("mList", mList);
					}
					
					request.setAttribute("room", room);
	        		path = "/WEB-INF/views/room/roomsInfoUpdate.jsp";
				    view = request.getRequestDispatcher(path);
				    view.forward(request, response);
				    
	        		
				}else {
	        		response.sendRedirect(request.getHeader("referer"));
	        	}
				
			}
			
			// 매물 수정 시작 =================================================================================================
			
			else if(command.equals("/roomUpdate.do")) {
				int maxSize = 20 * 1024 * 1024; // 20MB == 20 * 1024KB == 20 * 1024 * 1024Byte
	        	String root = request.getSession().getServletContext().getRealPath("/");
	        	String filePath = root + "resources/images/rooms/";
	        	
	        	MultipartRequest mRequest = new MultipartRequest(request, filePath, maxSize, "UTF-8", new MyFileRenamePolicy());
	        	//														요청		파일위치		크기						이름변경			
	        	String roomAddr = mRequest.getParameter("roomAddr");
	        	String typeOfRent = mRequest.getParameter("typeOfRent");
	        	int deposit = Integer.parseInt(mRequest.getParameter("deposit"));
	        	int monthRent = Integer.parseInt(mRequest.getParameter("monthRent"));
	        	int careFee = Integer.parseInt(mRequest.getParameter("careFee"));
	        	int pubSize = Integer.parseInt(mRequest.getParameter("pubSize"));
	        	int realSize = Integer.parseInt(mRequest.getParameter("realSize"));
	        	int roomCount = Integer.parseInt(mRequest.getParameter("roomCount"));
	        	String roomFloor = mRequest.getParameter("roomFloor");
	        	String roomStruc = mRequest.getParameter("roomStruc");
	        	
	        	String tv = mRequest.getParameter("tv");
	        	String washing = mRequest.getParameter("washing");
	        	String internet = mRequest.getParameter("internet");
	        	String airCon = mRequest.getParameter("airCon");
	        	String fridge = mRequest.getParameter("fridge");
	        	String bed = mRequest.getParameter("bed");
	        	String closet = mRequest.getParameter("closet");
	        	String womanOnly = mRequest.getParameter("womanOnly");
	        	String pet = mRequest.getParameter("pet");
	        	String parking = mRequest.getParameter("parking");
	        	
	        	String roomTitle = mRequest.getParameter("roomTitle");
	        	String roomInfo = mRequest.getParameter("roomInfo");
	        	String stationAddr = mRequest.getParameter("stationAddr");
	        	
	        	// 매물 번호 받아오기
	        	int roomNo = Integer.parseInt(mRequest.getParameter("no"));
	        	
	        	
	        	List<RoomImg> mList = new ArrayList<RoomImg>();
	        	
	        	Enumeration<String> files = mRequest.getFileNames();
	        	
	        	while (files.hasMoreElements()) { // 다음 요소가 있다면
	        		String name = files.nextElement(); // img0
	        		
	        		if(mRequest.getFilesystemName(name) != null) {
	        			
	        			RoomImg temp = new RoomImg();
	        			temp.setRoomImgName(mRequest.getFilesystemName(name));
	        			temp.setRoomImgPath(filePath);
	        			
	        			int fileLevel = 0;
	        			switch(name) {
		        			case "img0": fileLevel = 0; break;
		        			case "img1": fileLevel = 1;	break;
		        			case "img2": fileLevel = 2;	break;
		        			case "img3": fileLevel = 3;	break;
		        			case "img4": fileLevel = 4;	break;
		        			case "img5": fileLevel = 5;	break;
		        			case "img6": fileLevel = 6;	break;
		        			case "img7": fileLevel = 7;	break;
		        			case "img8": fileLevel = 8;	break;
		        			
	        			}
	        			
	        			temp.setRoomImgLevel(fileLevel);
	        			
	        			// fList에 추가
	        			mList.add(temp);
	        		}
				} // end while
	        	
	        	int memNo = ((Broker)request.getSession().getAttribute("loginMember")).getMemNo();
	        	
	        	Map<String, Object> map = new HashMap<String, Object>();
				
				map.put("roomTitle", roomTitle);
				map.put("roomInfo", roomInfo);
				map.put("roomAddr", roomAddr);
				map.put("careFee", careFee);
				map.put("typeOfRent", typeOfRent);
				map.put("deposit", deposit);
				map.put("monthRent", monthRent);
				map.put("roomStruc", roomStruc);
				map.put("roomFloor", roomFloor);
				map.put("pubSize", pubSize);
				map.put("realSize", realSize);
				map.put("roomCount", roomCount);
				map.put("stationAddr", stationAddr);
				
				map.put("washing", washing);
				map.put("airCon", airCon);
				map.put("bed", bed);
				map.put("closet", closet);
				map.put("parking", parking);
				map.put("tv", tv);
				map.put("internet", internet);
				map.put("fridge", fridge);
				map.put("womanOnly", womanOnly);
				map.put("pet", pet);
				map.put("roomNo", roomNo);
				
				map.put("mList" ,mList);
				map.put("memNo", memNo);
				
				int result = service.roomUpdate(map);
				// 8. result 값에 따라 View 연결 처리 
	        	path = "view.do?cp=" + cp + "&no=" + roomNo ;
	        	
	        	String sk = mRequest.getParameter("sk");
	        	String sv = mRequest.getParameter("sv");
				
	        	if (sk != null && sv != null) {
	        		path += "&sk=" + sk + "&sv=" + sv;
	        	}
	        	
	        	if(result > 0){
	        		System.out.println("수정 성공");
	        	}else {
	        		System.out.println("실패");
	        	}
	        	
	        	response.sendRedirect(path);
			}
			
			// 매물 삭제 =================================================================================================
			else if(command.equals("/delete.do")) {
				
				int roomNo = Integer.parseInt(request.getParameter("no")); // 매물 번호 받아오기
				
				
				int result = service.updateRoomStatus(roomNo);
				

	        	if (result > 0) {
	        		System.out.println("성공");
	        		path = request.getHeader("referer");
				} else {
					System.out.println("실패");
	        		path = request.getHeader("referer");
				}
	        	
	        	response.sendRedirect(path);
			}
			
			// 매물 상세 =================================================================================================
			else if(command.equals("/view.do")) {
				HttpSession session = request.getSession();
				
				int roomNo = Integer.parseInt(request.getParameter("no")); // 임시
				
				if(session.getAttribute("loginMember") == null) {
					session.setAttribute("swalIcon", "error");
					session.setAttribute("swalTitle", "로그인 필요");
					session.setAttribute("swalText", "매물 상세보기를 원하신다면 로그인 해주세요");
					response.sendRedirect(request.getHeader("referer"));
				}
				else {
				

				Room room = service.selectRoom(roomNo);
				room.setRoomNo(roomNo);
				
				Map<String, String> a = new HashMap<String, String>();
				a.put("tv",room.getTv());
				a.put("인터넷",room.getInternet());
				a.put("에어컨",room.getAirCon());
				a.put("세탁기",room.getWashing());
				a.put("냉장고",room.getFridge());
				a.put("침대",room.getBed());
				a.put("옷장",room.getCloset());
				a.put("여성전용",room.getWomanOnly());
				a.put("반려동물",room.getPet());
				a.put("주차",room.getParking());
				
				Map<String, String> b = new HashMap<String, String>();
				b.put("tv", "fad fa-tv-retro");
				b.put("인터넷", "fas fa-wifi");
				b.put("에어컨", "fad fa-air-conditioner");
				b.put("세탁기", "fas fa-washer");
				b.put("냉장고", "fas fa-refrigerator");
				b.put("침대", "fad fa-bed-empty");
				b.put("옷장", "fas fa-tshirt");
				b.put("여성전용", "fas fa-female");
				b.put("반려동물", "fad fa-dog");
				b.put("주차", "fad fa-parking");
				
//				option.setTv(room.getTv());
//				option.setInternet(room.getInternet());
//				option.setAirCon(room.getAirCon());
//				option.setWashing(room.getWashing());
//				option.setFridge(room.getFridge());
//				option.setBed(room.getBed());
//				option.setCloset(room.getCloset());
//				option.setWomanOnly(room.getWomanOnly());
//				option.setPet(room.getPet());
//				option.setParking(room.getParking());
				
				
				if(room != null) {
					List<RoomImg> mList = service.selectRoomImg(roomNo);
					
					if(!mList.isEmpty()) {
						request.setAttribute("mList", mList);
					}
					
					double reviewScore = service.calReview(roomNo);
					double visitCnt = service.visitCount(roomNo);
					System.out.println(reviewScore/visitCnt);
					double resultScore = Math.round((reviewScore / visitCnt)*100)/100.0;
					
					int visitCnt2 = (int)visitCnt;
					
					double percent = ((resultScore * 20)*100)/100.0;
					
					path = "/WEB-INF/views/room/roomsInfo.jsp";
					request.setAttribute("room", room);
					request.setAttribute("options", a);
					request.setAttribute("font", b);
					request.setAttribute("reviewScore", resultScore);
					request.setAttribute("visitCnt", visitCnt2);
					request.setAttribute("percent", percent);
				    view = request.getRequestDispatcher(path);
				    view.forward(request, response);
					
				} else {
					response.sendRedirect(request.getHeader("referer"));	
				}
				
				}
			}
			
			//-------------------------------------------------------------------------------------------------------------
			//매물 검색폼 컨트롤러
			else if(command.equals("/searchRoom.do")){
				PageInfo pInfo = service.getPageInfo(cp);
				List<Room> rList = service.selectList(pInfo);

				if(rList != null) {
		               
		               // 썸네일 이미지 목록 조회 서비스 호출
		               List<RoomImg> fList = service.selectThumbnailList(pInfo);

		               
		               // 썸네일 이미지 목록이 비어있지 않은 경우
		               if(!fList.isEmpty()) {
		                  request.setAttribute("fList", fList);
		               }
		            }
		            
		            path = "/WEB-INF/views/room/searchRoom.jsp";
		            
		            request.setAttribute("rList", rList);
		            request.setAttribute("pInfo", pInfo);
		            
		            view = request.getRequestDispatcher(path);
		            view.forward(request, response);
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
