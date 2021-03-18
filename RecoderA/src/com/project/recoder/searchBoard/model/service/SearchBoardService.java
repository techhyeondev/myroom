package com.project.recoder.searchBoard.model.service;

import static com.project.recoder.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.project.recoder.board.model.vo.Board;
import com.project.recoder.room.model.vo.PageInfo;
import com.project.recoder.searchBoard.model.dao.SearchBoardDAO;

public class SearchBoardService {
	
	private SearchBoardDAO dao = new SearchBoardDAO();
	
	
	
	
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
		case "totalWrite" : condition = ""; // 모든 게시물
							break;
		case "enrollWrire" : condition =" WHERE DELETE_FL = 'N' "; // 등록된 게시물
							break; 
		case "deleteWrite" : condition =" WHERE DELETE_FL = 'Y' "; // 삭제된 게시물
							break;
		}
		
		return condition;
	}

	
	
	/** 게시물 목록 리스트 조회 Service
	 * @param map
	 * @param pInfo
	 * @return bList
	 * @throws Exception
	 */
	public List<Board> searchBoardList(Map<String, Object> map, PageInfo pInfo) throws Exception {
		
		Connection conn = getConnection();
		
		String condition = createCondition(map);
		
		List<Board> bList = dao.searchBoardList(conn, pInfo, condition);
		
		close(conn);
		
		return bList;
	}

}
