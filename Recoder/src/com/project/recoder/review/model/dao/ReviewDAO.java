package com.project.recoder.review.model.dao;

import static com.project.recoder.common.JDBCTemplate.*;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.project.recoder.review.model.vo.review;


public class ReviewDAO {
	
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rset = null;

	private Properties prop;

	public ReviewDAO() {
		String fileName = ReviewDAO.class.getResource("/com/project/recoder/sql/review/review-query.xml").getPath();
		try {
			prop = new Properties();
			prop.loadFromXML(new FileInputStream(fileName));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String selectMemNick(Connection conn, int replyWriter) throws Exception{
		String writer = null;
		
		String query = prop.getProperty("selectMemNick");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, replyWriter);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				writer = rset.getString(1);
			}
			
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return writer;
	}

	public List<review> selectList(Connection conn, int parentBoardNo) throws Exception{
		List<review> rList = null;

		String query = prop.getProperty("selectList");

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, parentBoardNo);

			rset = pstmt.executeQuery();

			rList = new ArrayList<review>();

			while (rset.next()) {
				review reply = new review();
				reply.setReviewNo(rset.getInt("REVIEW_NO"));
				reply.setContent(rset.getString("CONTENT"));
				reply.setCreateDt(rset.getTimestamp("CREATE_DT"));
				reply.setRating(rset.getInt("RATING"));
				reply.setMemNo(rset.getInt("MEM_NO"));
				reply.setRoomNo(rset.getInt("ROOM_NO"));
				reply.setMemNick(rset.getString("MEM_NICK"));

				rList.add(reply);
			}
		} finally {
			close(rset);
			close(pstmt);
		}
		return rList;
	}

	public int insertReply(Connection conn, review reply) throws Exception{
		int result = 0 ;
		
		String query = prop.getProperty("insertReply");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, reply.getContent());
			pstmt.setInt(2, reply.getRating());
			pstmt.setInt(3, reply.getMemNo());
			pstmt.setInt(4, reply.getRoomNo());
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}


	public int chkVisit(Connection conn, int parentBoardNo, int memNo) throws Exception{
		int result=0;
		String query = prop.getProperty("chkVisit");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, parentBoardNo);
			pstmt.setInt(2, memNo);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				result = rset.getInt(1);
			}
			
		}finally {
			close(rset);
			close(pstmt);
		}
		return result;
	}

	public int reviewChk(Connection conn, int roomNo, int memNo) throws Exception{
		int result = 0;
		String query = prop.getProperty("reviewChk");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, roomNo);
			pstmt.setInt(2, memNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				result = rset.getInt(1);
			}
		}finally {
			close(rset);
			close(pstmt);
		}
		return result;
	}

}
