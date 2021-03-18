package com.project.recoder.main.model.service;

import static com.project.recoder.common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.List;

import com.project.recoder.board.model.vo.Board;
import com.project.recoder.main.model.dao.MainDAO;
import com.project.recoder.room.model.vo.Room;

public class MainService {
	
	MainDAO dao = new MainDAO();

	public List<Room> roomList() throws Exception {
		Connection conn = getConnection();
		List<Room> rList= null;
		rList = dao.roomList(conn);
		
		
		return rList;
	}

	public List<Board> boardList() throws Exception{
		Connection conn = getConnection();
		List<Board> bList= null;
		bList = dao.boardList(conn);
		return bList;
	}

}
