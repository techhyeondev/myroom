package com.project.recoder.reply.model.dao;
import static com.project.recoder.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.project.recoder.admin.model.dao.AdminDAO;
import com.project.recoder.reply.model.vo.Reply;

public class ReplyDAO {
	
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rset = null;
	
	private Properties prop;

	public ReplyDAO() {
		try {
			String filePath = AdminDAO.class.getResource("/com/project/recoder/sql/reply/reply-query.xml").getPath();

			prop = new Properties();

			prop.loadFromXML(new FileInputStream(filePath));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public List<Reply> selectList(Connection conn, int parentBoardNo) throws Exception{
		
		List<Reply> rList = null;
		
		String query = prop.getProperty("selectList");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, parentBoardNo);
			
			rset = pstmt.executeQuery();
			
			rList = new ArrayList<Reply>();
		
			while(rset.next()) {
				Reply reply = new Reply();
				
				reply.setReplyNo(rset.getInt("COMMENT_NO"));
				reply.setReplyContent(rset.getString("CONTENT"));
				reply.setReplyCreateDate(rset.getTimestamp("CREATE_DT"));
				reply.setParentBoardNo(rset.getInt("BOARD_NO"));
				reply.setMemberNick(rset.getString("MEM_NICK"));
				reply.setDeleteFl(rset.getString("DELETE_FL"));
				
				rList.add(reply);
			}
		
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return rList;
	}


	/** 댓글 삭제용 DAO
	 * @param conn
	 * @param replyNo
	 * @return result
	 * @throws Exception
	 */
	public int updateReplyDelete(Connection conn, int replyNo) throws Exception {
		int result = 0;
		
		String query = prop.getProperty("updateReplyDelete");
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, replyNo);
			
			result = pstmt.executeUpdate();
		}finally {
			
			close(pstmt);
		}
		return result;
	}


	
	/** 댓글 복구용 DAO
	 * @param conn
	 * @param replyNo
	 * @return result
	 * @throws Exception
	 */
	public int updateReplyRecover(Connection conn, int replyNo) throws Exception{
		
		int result = 0;
		
		String query = prop.getProperty("updateReplyRecover");
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, replyNo);
			
			result = pstmt.executeUpdate();
		}finally {
			
			close(pstmt);
		}
		return result;
	}

}
