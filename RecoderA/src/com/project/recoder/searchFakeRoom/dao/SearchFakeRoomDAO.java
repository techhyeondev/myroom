package com.project.recoder.searchFakeRoom.dao;
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
import com.project.recoder.notice.model.vo.PageInfo;
import com.project.recoder.room.model.vo.FakeRoom;
import com.project.recoder.room.model.vo.ReportComment;

public class SearchFakeRoomDAO {

	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rset = null;
	
	private Properties prop = null;
	
	public SearchFakeRoomDAO() {
		try {
			String filePath = AdminDAO.class.getResource("/com/project/recoder/sql/room/room-query.xml").getPath();

			prop = new Properties();

			prop.loadFromXML(new FileInputStream(filePath));

		} catch (Exception e) {
			e.printStackTrace();
		}
	
	
	}
	
	public int getListCount(Connection conn, String condition) throws Exception {
		
		int listCount = 0;
		
		String query = "SELECT COUNT(*) FROM RV_ROOM WHERE " + condition;
		
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

	
	
	
	
	/** 검색 신고매물 목록 조회 DAO
	 * @param conn
	 * @param pInfo
	 * @param condition
	 * @return fList
	 * @throws Exception
	 */
	public List<FakeRoom> searchFakeRoomList(Connection conn, PageInfo pInfo, String condition) throws Exception {
		
		List<FakeRoom> fList = null;
		
		String query = 
				"SELECT * FROM" +
				"(SELECT ROWNUM RVNUM, V.* " +
				"FROM " +
				"(SELECT * FROM RV_ROOM WHERE " + condition + 
				"ORDER BY REPORT_COUNT DESC) V) " +
				"WHERE RVNUM BETWEEN ? AND ? ";
		
		try {
			int startRow = (pInfo.getCurrentPage() - 1) * pInfo.getLimit() + 1;
			
			int endRow = startRow + pInfo.getLimit() -1;
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rset = pstmt.executeQuery();
			
			fList = new ArrayList<FakeRoom>();
			
			while(rset.next()) {
				FakeRoom fr = new FakeRoom(
						rset.getInt("ROOM_NO"),
						rset.getString("ROOM_TITLE"),
						rset.getString("ROOM_INFO"),
						rset.getString("ROOM_IMG_NAME"),
						rset.getInt("REPORT_COUNT"),
						rset.getString("MEM_NICK"));
				
				fList.add(fr);
			}
			
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return fList;
	}





	public List<ReportComment> searchReportList(Connection conn) throws Exception{
		
		List<ReportComment> rList = null;
		
		String query = prop.getProperty("selectReportList");
		
			try {
			
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			rList = new ArrayList<ReportComment>();
			
			while(rset.next()) {
				ReportComment rc = new ReportComment(
						rset.getString("CATEGORY_NM"),
						rset.getString("MEM_NICK"),
						rset.getString("RP_CONTENT"),
						rset.getInt("ROOM_NO"));
						
				rList.add(rc);
			}
			
		}finally {
			close(rset);
			close(stmt);
			
		}

		
		return rList;
	}

	
	
	
	
	
	
	
	
}
