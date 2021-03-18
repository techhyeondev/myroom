package com.project.recoder.searchBoard.model.dao;

import static com.project.recoder.common.JDBCTemplate.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.project.recoder.board.model.vo.Board;
import com.project.recoder.room.model.vo.PageInfo;

public class SearchBoardDAO {

	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rset = null;
	
	
	
	public int getListCount(Connection conn, String condition) throws Exception {
		
		int listCount = 0;
		
		String query = "SELECT COUNT(*) FROM AV_BOARD " + condition;
		
		try {
			stmt = conn.createStatement();
			
			rset = stmt.executeQuery(query);
			
			if(rset.next()) {
				listCount = rset.getInt(1);
			}
		
		}finally {
			close(rset);
			close(stmt);
		}
		
		return listCount;
	}

	
	
	/** 검색 Board 목록 조회 DAO
	 * @param conn
	 * @param pInfo
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	public List<Board> searchBoardList(Connection conn, PageInfo pInfo, String condition) throws Exception {
		
		List<Board> bList = null;
		
		String query =
				"SELECT * FROM " + 		
				"(SELECT ROWNUM BNUM, V.* " +
				"FROM " + 
				"(SELECT * FROM AV_BOARD " + condition +
				"ORDER BY BOARD_NO DESC) V) " +
				"WHERE BNUM BETWEEN ? AND ? ";
		
		try {
			int startRow = (pInfo.getCurrentPage() - 1) * pInfo.getLimit() + 1;
			
			int endRow = startRow + pInfo.getLimit() -1;
			
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rset = pstmt.executeQuery();
			
			bList = new ArrayList<Board>();
			
			while(rset.next()) {
				Board board = new Board(rset.getInt("BOARD_NO"),
						rset.getTimestamp("CREATE_DT"),
						rset.getString("TITLE"),
						rset.getString("CONTENT"),
						rset.getInt("READ_COUNT"),
						rset.getString("DELETE_FL"),
						rset.getInt("BOARD_CD"),
						rset.getInt("MEM_NO"),
						rset.getString("MEM_NICK"));
				
				bList.add(board);
			}
		}finally {
			close(rset);
			close(pstmt);
		}
		
		
		return bList;
	}

}
