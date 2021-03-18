package com.project.recoder.member.model.service;

import static com.project.recoder.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import com.project.recoder.member.model.dao.MemberDAO;
import com.project.recoder.member.model.vo.Member;
import com.project.recoder.member.model.vo.PageInfo;

public class MemberService {
	
	private MemberDAO dao = new MemberDAO();
	
	
	/** 전체회원 수 반환 Service
	 * @param cp
	 * @return
	 * @throws Exception
	 */
	public PageInfo getPageInfo(String cp) throws Exception {
		
		Connection conn = getConnection();
		
		int currentPage = cp == null ? 1 : Integer.parseInt(cp);
		
		int listCount = dao.getListCount(conn);	
		
		close(conn);
		
		return new PageInfo(currentPage, listCount);
	}

	public List<Member> selectMemberList(PageInfo pInfo) throws Exception{
		
		Connection conn = getConnection();
		
		List<Member> mList = dao.selectMemberList(conn, pInfo);
		
		close(conn);
		
		return mList;
	}

	/**Member 정지 service
	 * @param numberList
	 * @return
	 * @throws Exception
	 */
	public int updateMemberStop(String numberList) throws Exception {
		
		Connection conn = getConnection();
		
		int result = dao.updateMemberStop(conn, numberList);
		
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		close(conn);
		
		
		return result;
	}

	
	
	/** Member 복구 Service
	 * @param numberList
	 * @return
	 */
	public int updateMemberRecover(String numberList) throws Exception {
		
		Connection conn = getConnection();
		
		int result = dao.updateMemberRecover(conn, numberList);
		
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		close(conn);
		
		
		return result;
	}

	

}
