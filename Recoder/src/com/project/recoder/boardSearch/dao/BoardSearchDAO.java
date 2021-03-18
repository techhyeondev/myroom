package com.project.recoder.boardSearch.dao;

import static com.project.recoder.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.project.recoder.board.model.vo.Board;
import com.project.recoder.board.model.vo.PageInfo;


public class BoardSearchDAO {

	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rset = null;
	
	/** 검색 페이징 처리 dao
	 * @param conn
	 * @param condition
	 * @return listCount
	 * @throws Exception
	 */
	public int getListCount(Connection conn, String condition) throws Exception {
		int listCount = 0;
		
		String query = "SELECT COUNT(*) FROM V_BOARD WHERE DELETE_FL = 'N'" + 
				" AND BOARD_NM ='자유게시판' AND " + condition;
		
		try {
			stmt = conn.createStatement();
			
			rset = stmt.executeQuery(query);
			
			if(rset.next()) {
				listCount = rset.getInt(1);
			}
			
		} finally {
			close(rset);
			close(stmt);
		}
		
		return listCount;
	}

	/** 검색 목록 조회 dao
	 * @param conn
	 * @param condition
	 * @param pInfo
	 * @return bList
	 * @throws Exception
	 */
	public List<Board> boardSearch(Connection conn, String condition, PageInfo pInfo) throws Exception{
		
		List<Board> bList = null;
		
		try {
			String query = 			
			"SELECT * FROM " + 
			" (SELECT ROWNUM RNUM, V.* " + 
			" FROM" + 
			" (SELECT * FROM V_BOARD " + 
			" WHERE " + condition+ 
			" AND DELETE_FL = 'N' ORDER BY BOARD_NO DESC )V ) " + 
			" WHERE RNUM BETWEEN ? AND ?";
			
			//System.out.println(query);
			
			int startRow = (pInfo.getCurrentPage()-1) * pInfo.getLimit() + 1;
			int endRow = startRow + pInfo.getLimit() -1;
			
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rset = pstmt.executeQuery();
			
			bList = new ArrayList<Board>();
			
			while(rset.next()) {
				
				Board board = new Board(
								rset.getInt("BOARD_NO"), //boardNo, 
								rset.getString("TITLE"), //title,
								rset.getString("MEM_NICK"),//memNick,
								rset.getInt("READ_COUNT"), //readCount,
								rset.getString("BOARD_NO"), // boardNm,
								rset.getTimestamp("CREATE_DT"), //createDate);
								rset.getInt("MEM_NO")
						);
				
				bList.add(board);
			}
			
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return bList;
	}

}
