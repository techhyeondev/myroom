package com.project.recoder.admin.model.dao;

import static com.project.recoder.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;


import com.project.recoder.admin.model.vo.Admin;


public class AdminDAO {
	
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rset = null;
	
	private Properties prop = null;
	
	public AdminDAO() {
		try {
			String filePath = AdminDAO.class.getResource("/com/project/recoder/sql/admin/admin-query.xml").getPath();

			prop = new Properties();

			prop.loadFromXML(new FileInputStream(filePath));
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/** 관리자 로그인 DAO
	 * @param conn
	 * @param admin
	 * @return loginAdmin
	 * @throws Exception
	 */
	public Admin loginAdmin(Connection conn, Admin admin) throws Exception{
		
		Admin loginAdmin = null;
		
		String query = prop.getProperty("loginAdmin");
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, admin.getAdminId());
			pstmt.setString(2, admin.getAdminPw());
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				loginAdmin = new Admin(rset.getInt("MEM_NO"),
						rset.getString("MEM_ID"), 
						rset.getString("MEM_NICK"),
						rset.getString("MEM_TEL"),
						rset.getString("MEM_EMAIL"),
						rset.getString("MEM_GRADE"));
				//MEM_NO, MEM_ID, MEM_NICK, MEM_TEL, MEM_EMAIL, MEM_GRADE
			}
			
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return loginAdmin;
	}
	
	
	
	
	

}
