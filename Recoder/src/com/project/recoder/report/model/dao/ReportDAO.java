package com.project.recoder.report.model.dao;

import static com.project.recoder.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import com.project.recoder.room.model.dao.RoomDAO;

public class ReportDAO {
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rset = null;
	
	private Properties prop = null;
	
	public ReportDAO() {
		String fileName = ReportDAO.class.getResource("/com/project/recoder/sql/report/report-query.xml").getPath();
		try {
			prop = new Properties();
			prop.loadFromXML(new FileInputStream(fileName)); 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param conn
	 * @param reportTitle
	 * @param reportInfo
	 * @param roomNo
	 * @param categoryCD
	 * @param memNo
	 * @return result
	 * @throws Exception
	 */
	public int reportSend(Connection conn, String reportTitle, String reportInfo, int roomNo, int categoryCD, int memNo) throws Exception{
		int result = 0;
		String query = prop.getProperty("reportSend");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, reportTitle);
			pstmt.setString(2, reportInfo);
			pstmt.setInt(3, roomNo);
			pstmt.setInt(4, memNo);
			pstmt.setInt(5, categoryCD);
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		
		
		return result;
	}

	public int increaseRoomRportCount(Connection conn, int roomNo) throws Exception{
		
		int result = 0;
		String query = prop.getProperty("increaseRoomRportCount");
		
		try {
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, roomNo);
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public int reportChk(Connection conn, int roomNo, int memNo) throws Exception{
		int result = 0;
		String query = prop.getProperty("reportChk");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, roomNo);
			pstmt.setInt(2, memNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				result = rset.getInt(1);
			}
		}finally {
			close(rset);
			close(pstmt);
		}
		return result;
	}
}
