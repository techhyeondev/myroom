package com.project.recoder.searchNotice.model.dao;

import static com.project.recoder.common.JDBCTemplate.*;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import com.project.recoder.notice.model.vo.Notice;
import com.project.recoder.notice.model.vo.PageInfo;

public class SearchNoticeDAO {

	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rset = null;
	
	
	/** 조건을 만족하는 게시글 수 조회 DAO
	 * @param conn
	 * @param condition
	 * @return listCount
	 * @throws Exception
	 */
	public int getListCount(Connection conn, String condition) throws Exception {
		
		int listCount = 0;
		
		String query = "SELECT COUNT(*) FROM AV_NOTICE WHERE " + condition;
		
		try {
			stmt = conn.createStatement();
			
			rset=stmt.executeQuery(query);
			
			if(rset.next()) {
				listCount = rset.getInt(1);
			}
		}finally {
			close(rset);
			close(stmt);
		}
			// 조건에 맞는 전체 게시글 수 반환
		return listCount;
	}
	
	
	
	
	
	
	
	/** 검색 공지사항 목록 조회 DAO
	 * @param conn
	 * @param pInfo
	 * @param condition
	 * @return nList
	 * @throws Exception
	 */
	public List<Notice> searchNoticeList(Connection conn, PageInfo pInfo, String condition) throws Exception{

		List<Notice> nList = null;
		
		String query = 
				"SELECT * FROM" +
				"(SELECT ROWNUM NNUM, V.* " +
				"FROM " +
				"(SELECT * FROM AV_NOTICE WHERE " + condition + 
				"ORDER BY BOARD_NO DESC) V) " +
				"WHERE NNUM BETWEEN ? AND ? ";
		
		try {
			int startRow = (pInfo.getCurrentPage() - 1) * pInfo.getLimit() + 1;
			
			int endRow = startRow + pInfo.getLimit() -1;
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rset = pstmt.executeQuery();
			
			nList = new ArrayList<Notice>();
			
			while(rset.next()) {
				Notice notice = new Notice
						(rset.getInt("BOARD_NO"), 
						rset.getTimestamp("CREATE_DT"), 
						rset.getString("TITLE"),
						rset.getString("CONTENT"),
						rset.getInt("READ_COUNT"),
						rset.getString("DELETE_FL"));
				
				nList.add(notice);
			}
			
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return nList;
	}


}
