package com.project.recoder.reply.model.service;


import static com.project.recoder.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import com.project.recoder.reply.model.dao.ReplyDAO;
import com.project.recoder.reply.model.vo.Reply;

public class ReplyService {

	private ReplyDAO dao = new ReplyDAO();
	
	
	/** 댓글 목록 조회 Service
	 * @param parentBoardNo
	 * @return rList
	 * @throws Exception
	 */
	public List<Reply> selectList(int parentBoardNo) throws Exception {
		
		Connection conn = getConnection();
		
		List<Reply> rList = dao.selectList(conn, parentBoardNo);
		
		close(conn);
		
		return rList;
	}


	
	/** 댓글 삭제 Service
	 * @param replyNo
	 * @return result
	 * @throws Exception
	 */
	public int updateReplyDelete(int replyNo) throws Exception {
		
		Connection conn = getConnection();
		
		int result = dao.updateReplyDelete(conn, replyNo);
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}

		close(conn);
		
		return result;
	}



	/** 댓글 복구 Service
	 * @param replyNo
	 * @return result
	 * @throws Exception
	 */
	public int updateReplyRecover(int replyNo) throws Exception{
		
		Connection conn = getConnection();
		
		int result = dao.updateReplyRecover(conn, replyNo);
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}

		close(conn);
		
		return result;
	}

}
