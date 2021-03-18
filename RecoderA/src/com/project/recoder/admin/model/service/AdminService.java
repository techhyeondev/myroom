package com.project.recoder.admin.model.service;
import static com.project.recoder.common.JDBCTemplate.*;

import com.project.recoder.admin.model.dao.AdminDAO;
import com.project.recoder.admin.model.vo.Admin;


import java.sql.Connection;
public class AdminService {

	private AdminDAO dao = new AdminDAO();
	
	/** 관리자 로그인 service
	 * @param admin
	 * @return 
	 * @throws Exception
	 */
	public Admin loginAdmin(Admin admin) throws Exception {
		
		Connection conn = getConnection();
		
		Admin loginAdmin = dao.loginAdmin(conn, admin);
		
		close(conn);
		
		return loginAdmin;
	}

}
