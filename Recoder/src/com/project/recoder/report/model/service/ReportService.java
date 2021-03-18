package com.project.recoder.report.model.service;

import static com.project.recoder.common.JDBCTemplate.*;

import java.sql.Connection;

import com.project.recoder.report.model.dao.ReportDAO;
public class ReportService {

	private ReportDAO dao = new ReportDAO();
	
	public int reportSend(String reportTitle, String reportInfo, int roomNo, int categoryCD, int memNo) throws Exception{
		Connection conn = getConnection();
		int result = 0;
		
		result = dao.reportSend(conn, reportTitle, reportInfo, roomNo, categoryCD, memNo);
		
		if(result > 0) {
			
			result = dao.increaseRoomRportCount(conn, roomNo);
			
			if (result > 0) {
				commit(conn);
			}else {
				rollback(conn);
			}
		}else {
			rollback(conn);
		}
		return result;
	}
	
	public int reportChk(int roomNo, int memNo) throws Exception{
		Connection conn = getConnection();
		int result = dao.reportChk(conn, roomNo, memNo);
		close(conn);
		return result;
	}

}
