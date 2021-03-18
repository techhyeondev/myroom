package com.project.recoder.notice.model.service;

import static com.project.recoder.common.JDBCTemplate.close;
import static com.project.recoder.common.JDBCTemplate.commit;
import static com.project.recoder.common.JDBCTemplate.getConnection;
import static com.project.recoder.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import com.project.recoder.board.model.vo.Board;
import com.project.recoder.board.model.vo.BoardImg;
import com.project.recoder.board.model.vo.PageInfo;
import com.project.recoder.notice.model.dao.NoticeDAO;

public class NoticeService {
	
	private NoticeDAO dao = new NoticeDAO();
	
	
	/** 페이징 처리를 위한 값 계산 Service
	 * @param cp
	 * @return pInfo
	 * @throws Exception
	 */
	public PageInfo getPageInfo(String cp) throws Exception{
		Connection conn = getConnection();
		
		int currentPage  = cp == null ? 1 : Integer.parseInt(cp);
		//System.out.println("currentPage : "+currentPage);
		int listCount = dao.getListCount(conn);
		
		close(conn);
		
		return new PageInfo(currentPage, listCount);
	}


	/** 공지사항 목록 조회 service
	 * @param pInfo
	 * @return bList
	 * @throws Exception
	 */
	public List<Board> selectBoardList(PageInfo pInfo) throws Exception{
		Connection conn = getConnection();
		
		List<Board> bList = dao.selectBoardList(conn, pInfo);
		
		close(conn);
		
		return bList;
	}
	
	/** 공지사항 상세조회 service
	 * @param boardNo
	 * @return board
	 * @throws Exception
	 */
	public Board selectBoard(int boardNo) throws Exception{
		Connection conn = getConnection();
		
		Board board = dao.selectBoard(conn, boardNo);
		
		if(board != null) { //DB에서 조회 성공 시
			
			//조회수 증가
			int result = dao.increaseReadCount(conn, boardNo);
			
			if(result > 0) {
				commit(conn);
				
				//반환되는 Board 데이터에는 조회수가 증가되어있지 않기 때문에
				// 조회수 1 증가 시켜주기
				board.setReadCount(board.getReadCount()+1);
			}
			else {
				rollback(conn);
			}
				
			
		}
		
		close(conn);
		
		return board;
	}

	
}
