package com.project.recoder.notice.model.dao;

import static com.project.recoder.common.JDBCTemplate.close;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.project.recoder.board.model.dao.BoardDAO;
import com.project.recoder.board.model.vo.Board;
import com.project.recoder.board.model.vo.PageInfo;

public class NoticeDAO {
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rset = null;
	
	private Properties prop = null;
	
	public NoticeDAO() {
		String fileName = BoardDAO.class.getResource("/com/project/recoder/sql/notice/notice-query.xml").getPath();
		try {
			prop = new Properties();
			prop.loadFromXML(new FileInputStream(fileName)); 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/** 공지사항 전체 게시글 수 반환 DAO
	 * @param conn
	 * @return listCount
	 * @throws Exception
	 */
	public int getListCount(Connection conn) throws Exception{
		int listCount = 0;
	      
	      String query = prop.getProperty("getListCount");
	      
	      try {
	         stmt = conn.createStatement();
	         
	         rset = stmt.executeQuery(query);
	      
	         if(rset.next()) {
	            listCount = rset.getInt(1);
	         }
	         
	      } finally {
	         close(stmt);
	         close(rset);
	      }
	      
	      return listCount;
	}

	/** 게시글 목록 조회 DAO
	 * @param conn
	 * @param pInfo
	 * @return bList
	 * @throws Exception
	 */
	public List<Board> selectBoardList(Connection conn, PageInfo pInfo) throws Exception {
		List<Board> bList = null;
		
		String query = prop.getProperty("selectBoardList");
		
		try {
			//SQL 구분 조건절에 대입할 변수 생성
			int startRow = (pInfo.getCurrentPage()-1) * pInfo.getLimit() + 1;
			int endRow = startRow + pInfo.getLimit() -1;
			
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			//System.out.println(startRow+"/"+endRow);
			
			rset = pstmt.executeQuery();
			
			bList = new ArrayList();
			
			while(rset.next()) {
				Board board = new Board(
						rset.getInt("BOARD_NO"),
						rset.getString("TITLE"),
						rset.getString("MEM_NICK"),
						rset.getInt("READ_COUNT"),
						rset.getString("BOARD_NM"),
						rset.getTimestamp("CREATE_DT"),
						rset.getInt("MEM_NO"));
				
				bList.add(board);
			}
			
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return bList;
	}


	/** 게시글 상세조회 DAO
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
				board.setTitle(rset.getString("TITLE"));
				board.setContent(rset.getString("CONTENT"));
				board.setMemNick(rset.getString("MEM_NICK"));
				board.setReadCount(rset.getInt("READ_COUNT"));
				board.setCreateDate(rset.getTimestamp("CREATE_DT"));
				board.setBoardNm(rset.getString("BOARD_NM"));
				board.setMemNo(rset.getInt("MEM_NO"));
			}
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return board;
	}


	/** 게시글 상세 조회 - 조회수 증가 DAO
	 * @param conn
	 * @param boardNo
	 * @return result
	 * @throws Exception
	 */
	public int increaseReadCount(Connection conn, int boardNo) throws Exception{
		int result = 0;
		String query = prop.getProperty("increaseReadCount");
			
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, boardNo);
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		return result;
	}
}
