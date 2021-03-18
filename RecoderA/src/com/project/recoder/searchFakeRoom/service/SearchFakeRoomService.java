package com.project.recoder.searchFakeRoom.service;

import static com.project.recoder.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.project.recoder.notice.model.vo.PageInfo;
import com.project.recoder.room.model.vo.FakeRoom;
import com.project.recoder.room.model.vo.ReportComment;
import com.project.recoder.searchFakeRoom.dao.SearchFakeRoomDAO;

public class SearchFakeRoomService {

	SearchFakeRoomDAO dao = new SearchFakeRoomDAO();
	
	
	public PageInfo getPageInfo(Map<String, Object> map) throws Exception{
		Connection conn = getConnection();
		
		map.put("currentPage",
				(map.get("currentPage") == null)? 1 
						: Integer.parseInt((String)map.get("currentPage"))   );
		
		String condition = createCondition(map);
		
		int listCount = dao.getListCount(conn, condition);
		
		close(conn);
		
		return new PageInfo((int)map.get("currentPage"), listCount);
		
		
	}
	
	
	
	private String createCondition(Map<String, Object> map) {
		
		String condition = null;
		
		String searchValue = (String)map.get("searchValue");
		
		if(searchValue != null) {
			
			condition = " ROOM_TITLE LIKE '%' || '" + searchValue + "' || '%' ";
		}
		return condition;
	}

	public List<FakeRoom> searchFakeRoomList(Map<String, Object> map, PageInfo pInfo) throws Exception {
		
		Connection conn = getConnection();
		
		String condition = createCondition(map);
		
		List<FakeRoom> fList = dao.searchFakeRoomList(conn,pInfo,condition);
		
		close(conn);
		
		return fList;
	}



	public List<ReportComment> searchReportList() throws Exception{
		
		Connection conn = getConnection();
		
		List<ReportComment> rList = dao.searchReportList(conn);
		
		close(conn);
		
		return rList;
	}

}
