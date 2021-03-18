package com.project.recoder.comment.model.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import static com.project.recoder.common.JDBCTemplate.*;
import com.project.recoder.comment.model.vo.Comment;

public class CommentDAO {

	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rset = null;
	
	private Properties prop = null;
	
	public CommentDAO(){
		String fileName = CommentDAO.class.getResource("/com/project/recoder/sql/comment/comment-query.xml").getPath();
		try {
			prop = new Properties();
			prop.loadFromXML(new FileInputStream(fileName)); 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	/** 댓글 목록 조회 dao
	 * @param conn
	 * @param boardNo
	 * @return cList
	 * @throws Exception
	 */
	public List<Comment> selectList(Connection conn, int boardNo) throws Exception{
		List<Comment> cList = null;
		
		String query = prop.getProperty("selectList");
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, boardNo);
			
			rset = pstmt.executeQuery();
			
			cList = new ArrayList<Comment>();
			
			while(rset.next()) {
				Comment comment = new Comment();
				comment.setCommentNo(rset.getInt("COMMENT_NO"));
				comment.setContent(rset.getString("CONTENT"));
				comment.setCreateDt(rset.getTimestamp("CREATE_DT"));
				comment.setMemNick(rset.getString("MEM_NICK"));
				//OMMENT_NO, MEM_NICK, CREATE_DT, CONTENT
				cList.add(comment);
			}
			
			
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return cList;
	}




	/** 댓글 삽입 dao
	 * @param conn
	 * @param comment
	 * @return result
	 * @throws Exception
	 */
	public int insertComment(Connection conn, Comment comment) throws Exception{
		int result = 0;
		String query = prop.getProperty("insertComment");
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, comment.getMemNick());
			pstmt.setString(2, comment.getContent());
			pstmt.setInt(3, comment.getBoardNo());
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}




	/** 댓글 수정 dao
	 * @param conn
	 * @param comment
	 * @return result
	 * @throws Exception
	 */
	public int updateComment(Connection conn, Comment comment) throws Exception{
		int result = 0;
		
		String query = prop.getProperty("updateComment");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, comment.getContent());
			pstmt.setInt(2, comment.getCommentNo());
			
			result = pstmt.executeUpdate();
		} finally {
			close(pstmt);
		}
		return result;
	}




	/** 댓글 삭제 dao
	 * @param conn
	 * @param commentNo
	 * @return result
	 * @throws Exception
	 */
	public int updateDeleteFl(Connection conn, int commentNo) throws Exception{
		int result = 0;
		
		String query = prop.getProperty("updateDeleteFl");
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, commentNo);
			
			result = pstmt.executeUpdate();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

}
