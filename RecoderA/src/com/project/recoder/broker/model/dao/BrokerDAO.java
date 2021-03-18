package com.project.recoder.broker.model.dao;

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
import com.project.recoder.broker.model.vo.Broker;
import com.project.recoder.room.model.vo.PageInfo;

public class BrokerDAO {
	
	private PreparedStatement pstmt = null;
	private Statement stmt = null;
	private ResultSet rset = null;
	
	private Properties prop = null;
	
	public BrokerDAO() {
		try {
			String filePath = AdminDAO.class.getResource("/com/project/recoder/sql/broker/broker-query.xml").getPath();

			prop = new Properties();

			prop.loadFromXML(new FileInputStream(filePath));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	/** 승인 되지 않은 공인중개사 수 반환 DAO
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

			if (rset.next()) {
				listCount = rset.getInt(1);
			}
			
		} finally {
			close(rset);
			close(stmt);
		}

		
		return listCount;
	}
	
	
	
	
	
	/** 승인 요청한 공인중개사 목록 조회 DAO
	 * @param conn
	 * @param pInfo
	 * @return bList
	 * @throws Exception
	 */
	public List<Broker> selectRequestBrokerList(Connection conn, PageInfo pInfo) throws Exception{
		
		List<Broker> bList = null;
		
		String query = prop.getProperty("selectRequestBrokerList");
		
		try {
			int startRow = (pInfo.getCurrentPage() - 1) * pInfo.getLimit() + 1;

			int endRow = startRow + pInfo.getLimit() - 1;

			pstmt = conn.prepareStatement(query);

			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);

			rset = pstmt.executeQuery();
			
			bList = new ArrayList<Broker>();
			
			while(rset.next()) {
				Broker broker = new Broker(rset.getInt("MEM_NO2"),
						rset.getString("MEM_NICK"),
						rset.getString("FILE_NAME"),
						rset.getString("BROKER_CRETI"),
						rset.getString("BROKER_ADDR"),
						rset.getString("APPROVE_FL"));
			
				bList.add(broker);
			}
			
		}finally {
			
			close(rset);
			close(pstmt);
		}
		return bList;
	}




	/** 중개사 회원 가입 승인 DAO
	 * @param conn
	 * @param numberList
	 * @return result
	 * @throws Exception
	 */
	public int updateApproveEnroll(Connection conn, String numberList) throws Exception {
		
		int result = 0;
		
		String query = "UPDATE BROKER SET "
				 + " APPROVE_FL = 'Y' "
				 + " WHERE MEM_NO2 IN( " + numberList + ")";
		
		try {
			stmt = conn.createStatement();
			
			result = stmt.executeUpdate(query);
			//System.out.println(result);
		}finally {
			
			close(stmt);
		}
		
		return result;
	}




	/**승인 반려 후 공인중개사 회원 정보 삭제.(broker에서 삭제)
	 * @param conn
	 * @param numberList
	 * @return result
	 * @throws Exception
	 */
	public int deleteEnroll(Connection conn, String numberList) throws Exception {
		
		int result = 0;
		
		String query = "DELETE FROM BROKER "
				 + " WHERE MEM_NO2 IN( " + numberList + ")";
		
		try {
			stmt = conn.createStatement();
			
			result = stmt.executeUpdate(query);
		
		}finally {
			
			close(stmt);
		}
		
		return result;
	}




	/** 승인 반려 후 공인중개사 회원 정보 삭제.(member에서 삭제)
	 * @param conn
	 * @param numberList
	 * @return result
	 * @throws Exception
	 */
	public int deleteEnroll2(Connection conn, String numberList) throws Exception {
		
		int result = 0;
		
		String query = "DELETE FROM MEMBER "
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
