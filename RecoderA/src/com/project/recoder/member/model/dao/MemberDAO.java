package com.project.recoder.member.model.dao;
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
import com.project.recoder.member.model.vo.Member;
import com.project.recoder.member.model.vo.PageInfo;

public class MemberDAO {
	
	private PreparedStatement pstmt = null;
	private Statement stmt = null;
	private ResultSet rset = null;
	
	private Properties prop = null;
	
	public MemberDAO() {
		try {
			String filePath = AdminDAO.class.getResource("/com/project/recoder/sql/member/member-query.xml").getPath();

			prop = new Properties();

			prop.loadFromXML(new FileInputStream(filePath));

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	
	
	/** 전체 회원(탈퇴회원까지 포함)수 조회 dao
	 * @param conn
	 * @return
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

	
	
	
	/**
	 * @param conn
	 * @param pInfo
	 * @return mList
	 * @throws Exception
	 */
	public List<Member> selectMemberList(Connection conn, PageInfo pInfo) throws Exception{
		
		List<Member> mList = null;
		
		String query = prop.getProperty("selectMemberList");
		
		try {
			int startRow = (pInfo.getCurrentPage() - 1) * pInfo.getLimit() + 1;

			int endRow = startRow + pInfo.getLimit() - 1;

			pstmt = conn.prepareStatement(query);

			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);

			rset = pstmt.executeQuery();
			
			mList = new ArrayList<Member>();
			
			while(rset.next()) {
				
				Member member = new Member(
						rset.getInt("MEM_NO"),
						rset.getString("MEM_ID"),
						rset.getString("MEM_NICK"),
						rset.getString("MEM_TEL"),
						rset.getString("MEM_EMAIL"),
						rset.getString("SCSN_FL"),
						rset.getString("MEM_GRADE"));
				
				mList.add(member);
			}
			//System.out.println(mList);
			
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return mList;
	}



	/**Member 정지 service
	 * @param conn
	 * @param numberList
	 * @return
	 * @throws Exception
	 */
	public int updateMemberStop(Connection conn, String numberList) throws Exception{
		
		int result = 0;
		
		
		String query = "UPDATE MEMBER SET "
				 + " SCSN_FL = 'Y' "
				 + " WHERE MEM_NO IN( " + numberList + ")";
		
		try {
			stmt = conn.createStatement();
			
			result = stmt.executeUpdate(query);
			
		}finally {
			
			close(stmt);
		}
		
		return result;
	}



	/** Member 복구 Service
	 * @param conn
	 * @param numberList
	 * @return
	 * @throws Exception
	 */
	public int updateMemberRecover(Connection conn, String numberList) throws Exception {
		
		int result = 0;
		
		
		String query = "UPDATE MEMBER SET "
				 + " SCSN_FL = 'N' "
				 + " WHERE MEM_NO IN( " + numberList + ")";
		
		try {
			stmt = conn.createStatement();
			
			result = stmt.executeUpdate(query);
			
		}finally {
			
			close(stmt);
		}
		
		return result;
		
	}
	
}
