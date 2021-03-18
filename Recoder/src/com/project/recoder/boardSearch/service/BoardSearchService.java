package com.project.recoder.boardSearch.service;

import static com.project.recoder.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.project.recoder.board.model.vo.Board;
import com.project.recoder.board.model.vo.PageInfo;
import com.project.recoder.boardSearch.dao.BoardSearchDAO;

public class BoardSearchService {

	BoardSearchDAO dao = new BoardSearchDAO();
	
	
	
	/** 페이징 처리 service
	 * @param map
	 * @return pInfo
	 * @throws Exception
	 */
	public PageInfo getPageInfo(Map<String, Object> map) throws Exception{
		Connection conn = getConnection();
		
		map.put("currentPage", map.get("currentPage") == null ? 
				1:Integer.parseInt((String)map.get("currentPage")));
		
		String condition = createCondition(map);
		
		int listCount = dao.getListCount(conn, condition);
		
		close(conn);
		
		return new PageInfo((int)map.get("currentPage"), listCount);
	}
	
	
	/** 검색 게시글 목록 조회 service
	 * @param map
	 * @param pInfo 
	 * @return bList
	 * @throws Exception
	 */
	public List<Board> boardSearch(Map<String, Object> map, PageInfo pInfo) throws Exception {
		Connection conn = getConnection();
		
		String condition = createCondition(map);
		
		List<Board> bList = dao.boardSearch(conn, condition, pInfo);
		
		close(conn);
		
		return bList;
	}

	/** 검색 조건에 따라 SQL에 사용될 조건문을 조합하는 메소드
	 * @param map
	 * @return condition
	 */
	private String createCondition(Map<String, Object> map) {
		
		String condition = null;
		
		String searchKey = (String)map.get("sk");
		String searchValue = (String)map.get("sv");
		
		//검색 조건(searchKey)에 따라 SQL 조합
		switch(searchKey) {
		case "title" : 
			condition = " TITLE LIKE '%' || '" +searchValue + "' || '%' ";
					//  " BOARD_TITLE LIKE '%' || '49' || '%' "
			break;
		case "content" : 
			condition = " CONTENT LIKE '%' || '" +searchValue + "' || '%' ";
			break;
		case "titcont" : 
			condition = " (TITLE LIKE '%' || '" +searchValue + "' || '%' "
						+ " OR CONTENT LIKE '%' || '" + searchValue + "' || '%') ";
			break;
		case "writer" : 
			condition = " MEM_NICK LIKE '%' || '" +searchValue + "' || '%' ";
			break;
		}
		return condition;
	}
}
