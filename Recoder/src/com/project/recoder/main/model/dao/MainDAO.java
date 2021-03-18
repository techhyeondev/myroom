package com.project.recoder.main.model.dao;

import static com.project.recoder.common.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.project.recoder.board.model.vo.Board;
import com.project.recoder.room.model.vo.Room;

public class MainDAO {
	
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rset = null;
	
	private Properties prop = null;

	public List<Room> roomList(Connection conn) throws Exception{
		List<Room> rList = null;
		String query = 
				" SELECT * FROM(" + 
				"     SELECT ROOM_NO, ROOM_TITLE, ROOM_INFO, ROOM_IMG_PATH, ROOM_IMG_NAME " + 
				"     FROM ROOM " + 
				"     JOIN PICK_ROOM USING (ROOM_NO) " + 
				"     JOIN ROOM_IMG USING (ROOM_NO) " + 
				"     WHERE ROOM_IMG_LEVEL = 0 AND DELETE_FL = 'N' AND SELL_FL = 'N' " + 
				"     GROUP BY ROOM_NO, ROOM_TITLE, ROOM_INFO, ROOM_IMG_PATH, ROOM_IMG_NAME " + 
				"     ORDER BY COUNT(ROOM_NO) DESC " + 
				"     ) " + 
				" WHERE ROWNUM <= 4 ";
		
		try {
			
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			rList = new ArrayList<Room>();
			while(rset.next()) {
				Room rl = new Room();
				rl.setRoomNo(rset.getInt("ROOM_NO"));
				rl.setRoomTitle(rset.getString("ROOM_TITLE"));
				rl.setRoomInfo(rset.getString("ROOM_INFO"));
				rl.setBed(rset.getString("ROOM_IMG_PATH"));
				rl.setPet(rset.getString("ROOM_IMG_NAME"));
		
				rList.add(rl);
				
			}
					
		}finally {
			close(rset);
			close(stmt);
		}
		
		return rList;
	}

	public List<Board> boardList(Connection conn) throws Exception{
		List<Board> bList = null;
		String query = 
				" SELECT BOARD_NO, TITLE, CONTENT, CREATE_DT FROM (SELECT ROWNUM RNUM, TABLE_1.*  FROM " + 
				" (SELECT * FROM BOARD WHERE BOARD_CD=20 AND DELETE_FL = 'N' ORDER BY BOARD_NO DESC) TABLE_1) " + 
				" WHERE RNUM BETWEEN 1 AND 3";
		try {
			
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			bList = new ArrayList<Board>();
			while(rset.next()) {
				Board bl = new Board();
					bl.setBoardNo(rset.getInt("BOARD_NO"));
					bl.setTitle(rset.getString("TITLE"));
					bl.setContent(rset.getString("CONTENT"));
					bl.setCreateDate(rset.getTimestamp("CREATE_DT"));
			
					bList.add(bl);
			
			}
		} finally {
			close(rset);
			close(stmt);
		}
		return bList;
	}


}
