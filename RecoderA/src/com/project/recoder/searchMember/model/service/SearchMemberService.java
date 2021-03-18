package com.project.recoder.searchMember.model.service;
import static com.project.recoder.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;
import java.util.Map;


import com.project.recoder.member.model.vo.Member;
import com.project.recoder.room.model.vo.PageInfo;
import com.project.recoder.searchMember.model.dao.SearchMemberDAO;

public class SearchMemberService {
	
	private SearchMemberDAO dao = new SearchMemberDAO();
	
	
	
	
	/**검색 내용이 포함된 페이징 정보 생성 Service
	 * @param map
	 * @return PageInfo
	 * @throws Exception
	 */
	public PageInfo getPageInfo(Map<String, Object> map) throws Exception {
		
		Connection conn = getConnection();
		
		map.put("currentPage",
				(map.get("currentPage") == null)? 1 
						: Integer.parseInt((String)map.get("currentPage"))   );
		
		
		String condition1 = createCondition1(map);
		
		String condition2 = createCondition2(map);
		
		int listCount = dao.getListCount(conn, condition1, condition2);
		//System.out.println("--"+listCount);
		close(conn);
		System.out.println((int)map.get("currentPage"));
		return new PageInfo((int)map.get("currentPage"), listCount);
	}


	private String createCondition1(Map<String, Object> map) {
		
		String condition1 = null;
		
		String searchKey1 = (String)map.get("searchKey1"); // 일반회원 gMem,중개사회원 bMem
		System.out.println(searchKey1);
		
		
		switch(searchKey1) {
		case "gMem" : condition1 = " MEM_GRADE = 'G' "; break;
		case "bMem" : condition1 = " MEM_GRADE = 'B' "; break;
		}
		
		return condition1;
	}

	private String createCondition2(Map<String, Object> map) {
		String condition2 = null;
		
		String searchKey2 = (String)map.get("searchKey2"); // 정지 stop, 활동 active
		
		switch(searchKey2) {
		case "stop" : condition2 = " SCSN_FL = 'Y' "; break;
		case "active" : condition2 = " SCSN_FL = 'N' "; break;
		}
		
		return condition2;
		
	}
	
	
	
	
	/** 검색 회원 목록 리스트 조회 Service
	 * @param map
	 * @param pInfo
	 * @return
	 * @throws Exception
	 */
	public List<Member> searchMemberList(Map<String, Object> map, PageInfo pInfo) throws Exception{
		Connection conn = getConnection();
		
		String condition1 = createCondition1(map);
		
		String condition2 = createCondition2(map);
		
		List<Member> mList = dao.searchMemberList(conn, pInfo, condition1, condition2);
		
		close(conn);
		
		return mList;
	}

}
