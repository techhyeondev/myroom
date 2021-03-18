package com.project.recoder.board.model.dao;

import static com.project.recoder.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.project.recoder.admin.model.dao.AdminDAO;
import com.project.recoder.board.model.vo.Board;
import com.project.recoder.room.model.vo.PageInfo;

public class BoardDAO {

	private PreparedStatement pstmt = null;
	private Statement stmt = null;
	private ResultSet rset = null;
	
	private Properties prop = null;
	
	public BoardDAO() {
		try {
			String filePath = AdminDAO.class.getResource("/com/project/recoder/sql/board/board-query.xml").getPath();

			prop = new Properties();

			prop.loadFromXML(new FileInputStream(filePath));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	/** 전체 게시글 수 조회 DAO
	 * @param conn
	 * @return listCount
	 * @throws Exception
	 */
	public int getListCount(Connection conn) throws Exception {
		
		int listCount = 0;
		
		String query = prop.getProperty("getListCount");
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);

			if (rset.next()) {
				listCount = rset.getInt(1);
			}

		} finally {
			close(rset);
			close(stmt);
		}

		return listCount;
	}




	/** 게시글 목록 조회 DAO
	 * @param conn
	 * @param pInfo
	 * @return bList
	 * @throws Exception
	 */
	public List<Board> selectBoardList(Connection conn, PageInfo pInfo) throws Exception{
		
		List<Board> bList = null;
		
		String query = prop.getProperty("selectBoardList");
		
		try {
			int startRow = (pInfo.getCurrentPage() - 1) * pInfo.getLimit() + 1;

			int endRow = startRow + pInfo.getLimit() - 1;

			pstmt = conn.prepareStatement(query);

			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);

			rset = pstmt.executeQuery();
			
			bList = new ArrayList<Board>();
			
			while(rset.next()) {
				Board board = new Board(
										rset.getInt("BOARD_NO"),
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




	/** Board 삭제 DAO
	 * @param conn
	 * @param numberList
	 * @return result
	 * @throws Exception
	 */
	public int updateBoardDelete(Connection conn, String numberList) throws Exception{
		
		int result = 0;
		
		String query = "UPDATE BOARD SET "
				 + " DELETE_FL = 'Y' "
				 + " WHERE BOARD_NO IN( " + numberList + ")";
		
		try {
			stmt = conn.createStatement();
			
			result = stmt.executeUpdate(query);
			System.out.println(result);
		}finally {
			
			close(stmt);
		}
		
		return result;
	}




	/** Board 복구 DAO
	 * @param conn
	 * @param numberList
	 * @return result
	 * @throws Exception
	 */
	public int updateBoardRecover(Connection conn, String numberList) throws Exception{
		
		int result = 0;
		
		String query = "UPDATE BOARD SET "
				 + " DELETE_FL = 'N' "
				 + " WHERE BOARD_NO IN( " + numberList + ")";
		
		try {
			stmt = conn.createStatement();
			
			result = stmt.executeUpdate(query);
			System.out.println(result);
		}finally {
			
			close(stmt);
		}
		
		return result;
	}




	/** 게시글 상세 조회 DAO
	 * @param conn
	 * @param boardNo
	 * @return board
	 * @throws Exception
	 */
	public Board selectBoard(Connection conn, int boardNo) throws Exception{
		
		Board board = null;
		
		String query = prop.getProperty("selectBoard");
		
		try {
			pstmt = conn.prepareStatement(query);
		
			pstmt.setInt(1, boardNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				board = new Board();
				
				board.setBoardNo(rset.getInt("BOARD_NO"));
				board.setBoardTitle(rset.getString("TITLE"));
				board.setBoardContent(rset.getString("CONTENT"));
				board.setCreateDate(rset.getTimestamp("CREATE_DT"));
				board.setReadCount(rset.getInt("READ_COUNT"));
				board.setDeleteFl(rset.getString("DELETE_FL"));
				board.setBoardCategory(rset.getInt("BOARD_CD"));
				board.setMemberNo(rset.getInt("MEM_NO"));
				board.setMemberNick(rset.getString("MEM_NICK"));
				
			}
		
			//System.out.println(board.getCreateDate());
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return board;
	}

}
