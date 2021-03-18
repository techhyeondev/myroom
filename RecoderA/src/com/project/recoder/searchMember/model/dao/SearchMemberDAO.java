package com.project.recoder.searchMember.model.dao;
import static com.project.recoder.common.JDBCTemplate.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.project.recoder.member.model.vo.Member;
import com.project.recoder.room.model.vo.PageInfo;

public class SearchMemberDAO {

	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rset = null;
	
	
	
	/** 조건을 만족하는 Member 검색 조회 DAO
	 * @param conn
	 * @param condition1
	 * @param condition2
	 * @return
	 * @throws Exception
	 */
	public int getListCount(Connection conn, String condition1, String condition2) throws Exception {
		
		int listCount = 0;
		
		String query = "SELECT COUNT(*) FROM AV_MEMBER WHERE " + condition1 + " AND " + condition2;
		
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

	
	
	/** 검색 Member 목록 조회 DAO
	 * @param conn
	 * @param pInfo
	 * @param condition1
	 * @param condition2
	 * @return
	 * @throws Exception
	 */
	public List<Member> searchMemberList(Connection conn, PageInfo pInfo, String condition1, String condition2) throws Exception {
		
		List<Member> mList = null;
		
		
		System.out.println(condition1);
		System.out.println(condition2);
		String query = 
				"SELECT * FROM " + 		
				"(SELECT ROWNUM MMNUM, V.* " +
				"FROM " + 
				"(SELECT * FROM AV_MEMBER WHERE " + condition1 + " AND " + condition2 +
				" ORDER BY MEM_NO DESC) V) " +
				"WHERE MMNUM BETWEEN ? AND ? ";
				
		
			try {
				int startRow = (pInfo.getCurrentPage() - 1) * pInfo.getLimit() + 1;
				
				int endRow = startRow + pInfo.getLimit() -1;
				
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
				
				for(Member m : mList) {
					System.out.println(m);
				}
				
				
		}finally {
			close(rset);
			close(pstmt);
		}
		return mList;
	}

}
