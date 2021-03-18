package com.project.recoder.notice.model.service;


import static com.project.recoder.common.JDBCTemplate.*;



import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.project.recoder.notice.model.dao.NoticeDAO;
import com.project.recoder.notice.model.vo.Notice;
import com.project.recoder.room.model.vo.PageInfo;

public class NoticeService {
	
	private NoticeDAO dao = new NoticeDAO();

	public PageInfo getPageInfo(String cp) throws Exception {
		
		Connection conn = getConnection();
		
		int currentPage = cp == null ? 1 : Integer.parseInt(cp);
		
		// DB에서 전체 게시글 수를 조회하여 반환 받기
		// DB에서 전체 게시글 수를 조회하여 반환 받기
		int listCount = dao.getListCount(conn);	
		
		close(conn);
		
		// 얻어온 현재 페이지와, DB에서 조회한 전체 게시글 수를 이용하여
		// PageInfo 객체를 생성함
		return new PageInfo(currentPage, listCount);
	}

	public List<Notice> selectNoticeList(PageInfo pInfo) throws Exception {
		
		Connection conn = getConnection();
		
		List<Notice> nList = dao.selectNoticeList(conn, pInfo);
		
		close(conn);
		
		return nList;
	}

	
	
	/** 공지사항 입력 Service
	 * @param map
	 * @return 
	 * @throws Exception
	 */
	public int insertNotice(Map<String, Object> map) throws Exception{
		
		Connection conn = getConnection();
		
		// 작성될 글 번호를 먼저 얻어오는 DAO 수행
		int noticeNo = dao.selectNextNo(conn);
		
		int result = 0;
		
		if(noticeNo > 0) {
			
			map.put("noticeNo", noticeNo);
			
			// 크로스 사이트 스크립팅 방지 처리
			map.put("noticeTitle", replaceParameter((String)map.get("noticeTitle")));
			map.put("noticeContent", replaceParameter((String)map.get("noticeContent")));
		
			// 개행문자 변경 처리
			map.put("noticeContent", ((String)map.get("noticeContent")).replaceAll("\r\n", "<br>"));
			
			result = dao.insertNotice(conn, map);
			
			if(result>0) {
				commit(conn);
				
				result = noticeNo; // 글번호가 최종적 반환
			}else {
				rollback(conn);
			}
		}
		close(conn);
	
		
		return result;
	}
	
	
	// 크로스 사이트 스크립팅 방지
	private Object replaceParameter(String param) {
		
		String result = param;
		
		if(result != null) {
			result = result.replaceAll("&", "&amp;");
			result = result.replaceAll("<", "&lt;");
			result = result.replaceAll(">", "&gt;");
			result = result.replaceAll("\"", "&quot;");
		}
		
		return result;
	}
	
	
	
	/** 공지사항 삭제 Service
	 * @param numberList
	 * @return result
	 * @throws Exception
	 */
	public int updateNoticeDelete(String numberList) throws Exception {
		
		Connection conn = getConnection();
		
		int result = dao.updateNoticeDelete(conn, numberList);
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		close(conn);
		
		
		return result;
	}

}
