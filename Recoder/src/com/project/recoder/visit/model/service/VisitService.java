package com.project.recoder.visit.model.service;

import static com.project.recoder.common.JDBCTemplate.close;
import static com.project.recoder.common.JDBCTemplate.commit;
import static com.project.recoder.common.JDBCTemplate.getConnection;
import static com.project.recoder.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import com.project.recoder.room.model.vo.Room;
import com.project.recoder.visit.model.dao.VisitDAO;
import com.project.recoder.visit.model.vo.Visit;


public class VisitService {
	
	private VisitDAO dao = new VisitDAO();

	/**
	 * @param visitCd 
	 * @param visitCd2 
	 * @param memNo 
	 * @return
	 * @throws Exception
	 */
	public int visitSend(int roomNo, int memNo, int visitCd) throws Exception{
		
		Connection conn = getConnection();
		int result = 0;
		
		try {
			result = dao.visitSend(conn, roomNo, memNo, visitCd);
			System.out.println("service result"+result);
		} catch (Exception e) {
			result = 0;
			e.printStackTrace();
		}
		
		if (result >0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public List<Room> selectRoom(int brokerNo) throws Exception{
		Connection conn = getConnection();
		List<Room> room = null;
		
		room = dao.selectRoom(conn, brokerNo);
		close(conn);
		return room;
	}

	public List<Visit> selectVisit() throws Exception{
		Connection conn = getConnection();
		List<Visit> visit = null;
		
		visit = dao.selectVisit(conn);
		close(conn);
		
		return visit;
	}

	public List<Room> selectRoomImg(int brokerNo) throws Exception{
		Connection conn = getConnection();
		List<Room> rImg = null;
		rImg = dao.selectRoomImg(conn, brokerNo);
		close(conn);
		
		
		return rImg;
	}

	public int visitRoomCheck(int roomNo, int memNo) throws Exception{
		Connection conn = getConnection();
		int result = 0;
		
		result = dao.visitRoomCheck(conn, roomNo, memNo);
		
		if (result >0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		
		return result;
	}

}
