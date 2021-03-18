package com.project.recoder.search.service;
import static com.project.recoder.common.JDBCTemplate.close;
import static com.project.recoder.common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.List;

import com.project.recoder.room.model.vo.Room;
import com.project.recoder.search.dao.searchDAO;



public class SearchService {
	private searchDAO dao = new searchDAO();

	public List<Room> searchRoomList(String searchValue) throws Exception{
		Connection conn = getConnection();
		List<Room> roomList = dao.searchRoomList(conn, searchValue);
		close(conn);
		
		return roomList;
	}
	public List<Room> searchSubwayList(String searchValue) throws Exception{
		searchValue = searchValue.substring(1);
		Connection conn = getConnection();
		List<Room> roomList = dao.searchSubwayList(conn, searchValue);
		close(conn);
		
		return roomList;
	}
	

	
}
