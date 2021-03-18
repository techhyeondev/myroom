package com.project.recoder.searchNotice.model.service;

import static com.project.recoder.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.project.recoder.notice.model.vo.Notice;
import com.project.recoder.notice.model.vo.PageInfo;
import com.project.recoder.searchNotice.model.dao.SearchNoticeDAO;

public class SearchNoticeService {
	
	
	private SearchNoticeDAO dao = new SearchNoticeDAO();
	
	
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

	
	
	private String createCondition(Map<String, Object> map) {
		
		String condition = null;
		
		String searchValue = (String)map.get("searchValue");
		
		if(searchValue != null) {
			
			condition = " TITLE LIKE '%' || '" + searchValue + "' || '%' ";
		}
		return condition;
	}

	
	
	/** 검색 공지사항 목록 리스트 조회 Service
	 * @param map
	 * @param pInfo
	 * @return nList
	 * @throws Exception
	 */
	public List<Notice> searchNoticeList(Map<String, Object> map, PageInfo pInfo) throws Exception {
		
		Connection conn = getConnection();
		
		String condition = createCondition(map);
		
		List<Notice> nList = dao.searchNoticeList(conn, pInfo, condition);
		
		close(conn);
		
		return nList;
	}

}
