package com.project.recoder.searchRoom.model.service;

import static com.project.recoder.common.JDBCTemplate.*;


import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.project.recoder.room.model.vo.PageInfo;
import com.project.recoder.room.model.vo.Room;
import com.project.recoder.searchRoom.model.dao.SearchRoomDAO;

public class SearchRoomService {

	
	private SearchRoomDAO dao = new SearchRoomDAO();
	
	/** 검색 내용이 포함된 페이징 정보 생성 Service
	 * @param map
	 * @return PageInfo
	 * @throws Exception
	 */
	public PageInfo getPageInfo(Map<String, Object> map) throws Exception {
		Connection conn = getConnection();
		
		map.put("currentPage",
				(map.get("currentPage") == null)? 1 
						: Integer.parseInt((String)map.get("currentPage"))   );
		
		String condition = createCondition(map);
		
		int listCount = dao.getListCount(conn, condition);
		
		close(conn);
		
		return new PageInfo((int)map.get("currentPage"), listCount);
	}

	
	
	/** 검색 조건에 따라 SQL
	 * @param map
	 * @return
	 */
	private String createCondition(Map<String, Object> map) {
		
		String condition = null;
		
		String searchKey = (String)map.get("searchKey");
		
		switch(searchKey) {
		case "totalRoom" : condition = "";
							break;
		case "enrollRoom" : condition =" WHERE DELETE_FL = 'N' "; //등록된매물
							break; 
		case "deleteRoom" : condition =" WHERE DELETE_FL = 'Y' ";
							break;
		}
		
		
		return condition;
	}



	
	/** 검색 Room 목록 리스트 조회 Service
	 * @param map
	 * @param pInfo
	 * @return
	 * @throws Exception
	 */
	public List<Room> searchRoomList(Map<String, Object> map, PageInfo pInfo) throws Exception{
		Connection conn = getConnection();
		
		String condition = createCondition(map);
		
		List<Room> rList = dao.searchRoomList(conn, pInfo, condition);
		
		close(conn);
		
		return rList;
		

	}
}

