package com.project.recoder.visit.model.dao;

import static com.project.recoder.common.JDBCTemplate.close;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.project.recoder.room.model.vo.Room;
import com.project.recoder.visit.model.vo.Visit;

public class VisitDAO {
	
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rset = null;
	
	private Properties prop = null;
	
	public VisitDAO() {
		String fileName = VisitDAO.class.getResource("/com/project/recoder/sql/visit/visit-query.xml").getPath();
		try {
			prop = new Properties();
			prop.loadFromXML(new FileInputStream(fileName)); 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int visitSend(Connection conn, int roomNo, int memNo, int visitCd) throws Exception{
		int result = 0;
		String query = prop.getProperty("visitSend");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, memNo);
			pstmt.setInt(2, roomNo);
			pstmt.setInt(3, visitCd);
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
			
		}
		return result;
	}

	public List<Room> selectRoom(Connection conn, int brokerNo) throws Exception{
		List<Room> rList = null;
		String query = prop.getProperty("selectRoom");
		try {
			
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, brokerNo);

			rset = pstmt.executeQuery();

			rList = new ArrayList<Room>();

			while (rset.next()) {
				Room room = new Room(
					rset.getInt("ROOM_NO"), 
					rset.getString("ROOM_TITLE"),
					rset.getString("ROOM_INFO"),
					rset.getInt("GMEM_NO"));
				
				rList.add(room);
			}
			
			
		} finally {
			close(rset);
			close(stmt);
		}
		
		return rList;
	}

	public List<Visit> selectVisit(Connection conn) throws Exception{
		List<Visit> vList = null;
		String query = prop.getProperty("selectVisit");
		try {
			
			
			pstmt = conn.prepareStatement(query);

			rset = pstmt.executeQuery();

			vList = new ArrayList<Visit>();

			while (rset.next()) {
				Visit visit = new Visit(
					rset.getInt("MEM_NO"), 
					rset.getInt("ROOM_NO"),
					rset.getTimestamp("VISIT_DT"),
					rset.getInt("VISIT_CODE"),
					rset.getString("MEM_NICK"));
				vList.add(visit);
			}
			
			
		} finally {
			close(rset);
			close(stmt);
		}
		
		return vList;
	}

	public List<Room> selectRoomImg(Connection conn, int brokerNo) throws Exception{	
			List<Room> rImg = null;
			String query = prop.getProperty("selectRoomImg");
			System.out.println(brokerNo);
			try {
				
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, brokerNo);
				
				rset = pstmt.executeQuery();
				
				rImg = new ArrayList<Room>();

				
				while (rset.next()) {
					Room room = new Room(
						rset.getInt("ROOM_NO"), 
						rset.getString("ROOM_IMG_NAME"));
					
					rImg.add(room);
				}
				
			} finally{
				close(rset);
				close(pstmt);
				
			}
		
		return rImg;
	}

	public int visitRoomCheck(Connection conn, int roomNo, int memNo) throws Exception{
		int result = 0;
		String query = prop.getProperty("visitRoomCheck");
		
		try {
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, memNo);
			pstmt.setInt(2, roomNo);
			
			result = pstmt.executeUpdate();
			System.out.println(result);
		} catch (Exception e) {
			close(pstmt);
			e.printStackTrace();
		}
		
		return result;
	}




}
