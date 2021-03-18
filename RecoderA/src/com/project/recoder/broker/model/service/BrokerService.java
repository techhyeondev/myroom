package com.project.recoder.broker.model.service;

import static com.project.recoder.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import com.project.recoder.broker.model.dao.BrokerDAO;
import com.project.recoder.broker.model.vo.Broker;
import com.project.recoder.room.model.vo.PageInfo;

public class BrokerService {

	private BrokerDAO dao = new BrokerDAO();
	
	/** 승인 요청한 공인중개사  수 반환 Service
	 * @param cp
	 * @return 
	 * @throws Exception
	 */
	public PageInfo getPageInfo(String cp) throws Exception{
		
		Connection conn = getConnection();
		
		int currentPage = cp == null ? 1 : Integer.parseInt(cp);
		
		int listCount = dao.getListCount(conn);	
		
		close(conn);
		
		return new PageInfo(currentPage, listCount);
	}

	
	
	/** 승인 요청한 공인중개사의 목록 반환 Service
	 * @param pInfo
	 * @return bList
	 * @throws Exception
	 */
	public List<Broker> selectRequestBrokerList(PageInfo pInfo) throws Exception {
		
		Connection conn = getConnection();
		
		List<Broker> bList = dao.selectRequestBrokerList(conn, pInfo);
		
		close(conn);
		
		return bList;
	}



	/** 중개사 회원 가입 승인 service
	 * @param numberList
	 * @return result
	 * @throws Exception
	 */
	public int updateApproveEnroll(String numberList) throws Exception{
		
		Connection conn = getConnection();
		
		int result = dao.updateApproveEnroll(conn, numberList);
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		close(conn);
		
		
		return result;
	}



	
	
	/** 승인 반려 후 공인중개사 회원 정보 삭제.
	 * @param numberList
	 * @return result
	 * @throws Exception
	 */
	public int deleteEnroll(String numberList) throws Exception {
		
		Connection conn = getConnection();
		
		int result = dao.deleteEnroll(conn, numberList);
		
		if(result > 0) {
			
			result = dao.deleteEnroll2(conn, numberList);
		}
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		close(conn);
		
		
		return result;
	}

}
