package com.project.recoder.search.dao;
import static com.project.recoder.common.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.project.recoder.room.model.vo.Room;

public class searchDAO {
	
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rset = null;



	public List<Room> searchRoomList(Connection conn, String searchValue) throws Exception{
		List<Room> roomList = null;
		String query = 
				" SELECT ROOM_ADDR, ROOM_TITLE, ROOM_NO, ROOM_IMG_NAME, ROOM_IMG_PATH " + 
				" FROM ROOM " + 
				" JOIN ROOM_IMG USING(ROOM_NO) " + 
				" WHERE DELETE_FL = 'N' AND SELL_FL = 'N' AND ROOM_ADDR LIKE '%' ||'" + searchValue + "'|| '%'  "+
				" AND ROOM_IMG_LEVEL = 0";
		
		try {
	        
	         pstmt = conn.prepareStatement(query);
	         // pstmt.setString(1, searchValue);
	         rset = pstmt.executeQuery();
	         
	         roomList = new ArrayList<Room>();
	         
	         while(rset.next()) {
	            
	        	 Room at = new Room();
	        	 at.setRoomAddr(rset.getString("ROOM_ADDR"));
	        	 at.setRoomTitle(rset.getString("ROOM_TITLE"));
	             at.setRoomNo(rset.getInt("ROOM_NO"));
	             at.setPet(rset.getString("ROOM_IMG_NAME"));
	             at.setBed(rset.getString("ROOM_IMG_PATH"));
	            
	            roomList.add(at);
	         }
	         System.out.println(roomList);
		}finally {
			close(rset);
			close(pstmt);
		}
		return roomList;
	}
	
	public List<Room> searchSubwayList(Connection conn, String searchValue) throws Exception{
		List<Room> roomList = null;
		String query = 
				" SELECT ROOM_ADDR, ROOM_TITLE, ROOM_NO, ROOM_IMG_NAME, ROOM_IMG_PATH " + 
				" FROM ROOM " + 
				" JOIN ROOM_IMG USING(ROOM_NO) " + 
				" WHERE DELETE_FL = 'N' AND SELL_FL = 'N' AND STATION_ADDR LIKE '%' ||'" + searchValue + "'|| '%'  "+
				" AND ROOM_IMG_LEVEL = 0";
		try {
	        
	         pstmt = conn.prepareStatement(query);
	         rset = pstmt.executeQuery();
	         
	         roomList = new ArrayList<Room>();
	         
	         while(rset.next()) {
	            
	        	 Room at = new Room();
	        	 at.setRoomAddr(rset.getString("ROOM_ADDR"));
	        	 at.setRoomTitle(rset.getString("ROOM_TITLE"));
	             at.setRoomNo(rset.getInt("ROOM_NO"));
	             at.setPet(rset.getString("ROOM_IMG_NAME"));
	             at.setBed(rset.getString("ROOM_IMG_PATH"));
	            
	            roomList.add(at);
	         }
	         System.out.println(roomList);
		}finally {
			close(rset);
			close(pstmt);
		}
		return roomList;
	}
	
}
