package com.project.recoder.notice.model.dao;


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

import com.project.recoder.admin.model.dao.AdminDAO;
import com.project.recoder.notice.model.vo.Notice;
import com.project.recoder.room.model.vo.PageInfo;

public class NoticeDAO {

	private PreparedStatement pstmt = null;
	private Statement stmt = null;
	private ResultSet rset = null;

	private Properties prop = null;

	public NoticeDAO() {
		try {
			String filePath = AdminDAO.class.getResource("/com/project/recoder/sql/notice/notice-query.xml").getPath();

			prop = new Properties();

			prop.loadFromXML(new FileInputStream(filePath));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

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

	/**
	 * 공지글 목록 조회 DAO
	 * 
	 * @param conn
	 * @param pInfo
	 * @return nList
	 * @throws Exception
	 */
	public List<Notice> selectNoticeList(Connection conn, PageInfo pInfo) throws Exception {

		List<Notice> nList = null;

		String query = prop.getProperty("selectNoticeList");

		try {
			int startRow = (pInfo.getCurrentPage() - 1) * pInfo.getLimit() + 1;

			int endRow = startRow + pInfo.getLimit() - 1;

			pstmt = conn.prepareStatement(query);

			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);

			rset = pstmt.executeQuery();

			nList = new ArrayList<Notice>();

			while (rset.next()) {
				Notice notice = new Notice(rset.getInt("BOARD_NO"), rset.getTimestamp("CREATE_DT"),
						rset.getString("TITLE"), rset.getString("CONTENT"), rset.getInt("READ_COUNT"),
						rset.getString("DELETE_FL"));

				nList.add(notice);
			}

		} finally {
			close(rset);
			close(pstmt);
		}

		return nList;
	}

	/**
	 * 공지사항 등록 시 사용될 번호 반환 DAO
	 * 
	 * @param conn
	 * @return noticeNo
	 * @throws Exception
	 */
	public int selectNextNo(Connection conn) throws Exception {

		int noticeNo = 0;

		String query = prop.getProperty("selectNextNo");

		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			if (rset.next()) {
				
				noticeNo = rset.getInt(1);
			}
		
		} finally {
			close(rset);
			close(stmt);
		}
		
		return noticeNo;

	}
	
	
	
	/** 공지사항 등록 DAO
	 * @param conn
	 * @param map
	 * @return result
	 * @throws Exception
	 */
	public int insertNotice(Connection conn, Map<String, Object> map) throws Exception {
		
		int result = 0;
		
		String query = prop.getProperty("insertNotice");
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, (int)map.get("noticeNo"));
			pstmt.setString(2, (String)map.get("noticeTitle"));
			pstmt.setString(3, (String)map.get("noticeContent"));
			pstmt.setInt(4, (int)map.get("noticeWriter"));
			
			result =  pstmt.executeUpdate();
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	
	
	
	/** 공지사항 삭제 Service
	 * @param conn
	 * @param numberList
	 * @return result
	 * @throws Exception
	 */
	public int updateNoticeDelete(Connection conn, String numberList) throws Exception{
		
		int result = 0;
		
		String query = "UPDATE BOARD SET "
				 + " DELETE_FL = 'Y' "
				 + " WHERE BOARD_NO IN( " + numberList + ")";
		
		try {
			stmt = conn.createStatement();
			
			result = stmt.executeUpdate(query);
			
		}finally {
			
			close(stmt);
		}
		
		return result;
	}
}