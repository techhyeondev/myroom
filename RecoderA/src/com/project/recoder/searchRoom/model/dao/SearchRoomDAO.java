package com.project.recoder.searchRoom.model.dao;

import static com.project.recoder.common.JDBCTemplate.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.project.recoder.room.model.vo.PageInfo;
import com.project.recoder.room.model.vo.Room;

public class SearchRoomDAO {

	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rset = null;
	
	/** 조건을 만족하는 Room 검색 조회 DAO
	 * @param conn
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	public int getListCount(Connection conn, String condition) throws Exception{
		
		int listCount = 0;
	
		String query = "SELECT COUNT(*) FROM AV_ROOM " + condition;
		
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

	/** 검색 ROOM 목록 조회 DAO
	 * @param conn
	 * @param pInfo
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	public List<Room> searchRoomList(Connection conn, PageInfo pInfo, String condition) throws Exception{
		
		List<Room> rList = null;
		
			
		String query = 
				"SELECT * FROM " + 		
				"(SELECT ROWNUM RNUM, V.* " +
				"FROM " + 
				"(SELECT * FROM AV_ROOM " + condition +
				"ORDER BY ROOM_NO DESC) V) " +
				"WHERE RNUM BETWEEN ? AND ? ";
		
		try {
			int startRow = (pInfo.getCurrentPage() - 1) * pInfo.getLimit() + 1;
			
			int endRow = startRow + pInfo.getLimit() -1;
			
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rset = pstmt.executeQuery();
			
			rList = new ArrayList<Room>();
			
			while(rset.next()) {
				Room room = new Room(
						rset.getInt("ROOM_NO"),
						rset.getString("DELETE_FL"),
						rset.getString("ROOM_TITLE"),
						rset.getString("MEM_NICK"));
				
				rList.add(room);
			}
			
			
		}finally {
			close(rset);
			close(pstmt);
		}
		
		
		return rList;
	}

}
