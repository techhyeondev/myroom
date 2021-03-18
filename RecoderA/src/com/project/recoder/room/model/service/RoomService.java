package com.project.recoder.room.model.service;

import static com.project.recoder.common.JDBCTemplate.*;



import java.sql.Connection;
import java.util.List;

import com.project.recoder.room.model.dao.RoomDAO;
import com.project.recoder.room.model.vo.PageInfo;
import com.project.recoder.room.model.vo.ReportComment;
import com.project.recoder.room.model.vo.Room;
import com.project.recoder.room.model.vo.FakeRoom;

public class RoomService {
	
	private RoomDAO dao = new RoomDAO();
	

	/** 페이징 처리를 위한 값 계산 Service
	 * @param cp
	 * @return PageInfo
	 * @throws Exception
	 */
	public PageInfo getPageInfo(String cp) throws Exception {
		Connection conn = getConnection();
		
		int currentPage = cp == null ? 1 : Integer.parseInt(cp);
		
		int listCount = dao.getListCount(conn);
		
		close(conn);
		return new PageInfo(currentPage, listCount);
	}

	
	
	
	/** 전체 room 목록 조회
	 * @param pInfo
	 * @return rList
	 * @throws Exception
	 */
	public List<Room> selectRoomList(PageInfo pInfo) throws Exception {
		Connection conn = getConnection();
		
		List<Room> rList = dao.selectRoomList(conn, pInfo);
		
		close(conn);
		
		return rList;
	}



	
	
	



	
	/** Room 삭제 Service
	 * @param numberList
	 * @return result
	 * @throws Exception
	 */
	public int updateRoomDelete(String numberList) throws Exception {
		Connection conn = getConnection();
		
		int result = dao.updateRoomDelete(conn, numberList);
		
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		close(conn);
		
		
		return result;
	}




	public int updateRoomRecover(String numberList) throws Exception {
		
		Connection conn = getConnection();
		
		int result = dao.updateRoomRecover(conn,numberList);
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}




	/** 신고 매물 수 조회 
	 * @param cp
	 * @return
	 * @throws Exception
	 */
	public PageInfo getPageInfo1(String cp) throws Exception{
		
		Connection conn = getConnection();
		
		int currentPage = cp == null ? 1 : Integer.parseInt(cp);
		
		int listCount = dao.getListCount1(conn);
		
		close(conn);
		return new PageInfo(currentPage, listCount);
		
		
	}
	
	/** 신고 매물의 정보 조회 Service
	 * @param pInfo
	 * @return fList
	 * @throws Exception
	 */
	public List<FakeRoom> selectFakeList(PageInfo pInfo) throws Exception{
		Connection conn = getConnection();
		
		List<FakeRoom> fList = dao.selectFakeList(conn, pInfo);
		
		close(conn);
		
		return fList;
	}




	/** 신고 리스트 조회 Service
	 * @return
	 * @throws Exception
	 */
	public List<ReportComment> selectReportList() throws Exception{
		
		Connection conn = getConnection();
		
		List<ReportComment> rList = dao.selectReportList(conn);
		
		close(conn);
		
		return rList;
	}




	/** 신고 매물 삭제
	 * @param roomNo
	 * @return result
	 * @throws Exception
	 */
	public int updateFakeRoomDelete(String roomNo) throws Exception{
		
		Connection conn = getConnection();
		
		int result = dao.updateFakeRoomDelete(conn, roomNo);
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		close(conn);
		
		
		return result;
	}

}
