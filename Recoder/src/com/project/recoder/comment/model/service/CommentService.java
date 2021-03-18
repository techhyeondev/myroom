package com.project.recoder.comment.model.service;

import java.sql.Connection;
import java.util.List;

import com.project.recoder.comment.model.dao.CommentDAO;
import com.project.recoder.comment.model.vo.Comment;
import static com.project.recoder.common.JDBCTemplate.*;

public class CommentService {

	CommentDAO dao = new CommentDAO();
	
	/** 댓글 목록 조회 service
	 * @param boardNo
	 * @return cList
	 * @throws Exception
	 */
	public List<Comment> selectList(int boardNo) throws Exception {
		Connection conn = getConnection();
		
		List<Comment> cList = dao.selectList(conn, boardNo);
		
		close(conn);
		
		return cList;
	}

	/** 댓글 삽입 service
	 * @param comment
	 * @return result
	 * @throws Exception
	 */
	public int insertComment(Comment comment) throws Exception{
		Connection conn = getConnection();
		
		//크로스 사이트 스크립팅 방지 처리
		String commentContent = comment.getContent();
		commentContent = replaceParameter(commentContent);
		
		//개행문자 변환처리
		// ajax 통신 시 textarea의 개행문자가 \n 하나만 넘어옴(\r\n아님) 
		// \n --> <br>
		commentContent = commentContent.replaceAll("\n", "<br>");
		comment.setContent(commentContent);  //변경된 commentContent를 다시 comment에 세팅
		
		int result = dao.insertComment(conn, comment);
				
		//트랜잭션 처리
		if(result >0) commit(conn);
		else			rollback(conn);
		
		close(conn);
		
		return result;
	}
	
	// 크로스 사이트 스크립트 방지 메소드
	private String replaceParameter(String param) {
		String result = param;
		if(param != null) {
			result = result.replaceAll("&", "&amp;");
			result = result.replaceAll("<", "&lt;");
			result = result.replaceAll(">", "&gt;");
			result = result.replaceAll("\"", "&quot;");
		}
		
		return result;
	}

	/** 댓글 수정 service
	 * @param comment
	 * @return result
	 * @throws Exception
	 */
	public int updateComment(Comment comment) throws Exception{
		Connection conn = getConnection();
		
		//크로스 사이트 스크립팅 방지 처리
		String commentContent = comment.getContent();
		commentContent = replaceParameter(commentContent);
		
		//개행문자 변환처리
		// ajax 통신 시 textarea의 개행문자가 \n 하나만 넘어옴(\r\n아님) 
		// \n --> <br>
		commentContent = commentContent.replaceAll("\n", "<br>");
		
		comment.setContent(commentContent);  //변경된 commentContent를 다시 comment에 세팅
		
		int result = dao.updateComment(conn, comment);
		
		if(result>0) commit(conn);
		else		rollback(conn);
		
		close(conn);
		
		return result;
	}


	/** 댓글 삭제 service
	 * @param commentNo
	 * @return result
	 * @throws Exception
	 */
	public int updateDeleteFl(int commentNo) throws Exception{
		Connection conn = getConnection();
		
		int result = dao.updateDeleteFl(conn, commentNo);
		
		if(result>0) commit(conn);
		else		rollback(conn);
		
		close(conn);
		
		return result;
	}

}
