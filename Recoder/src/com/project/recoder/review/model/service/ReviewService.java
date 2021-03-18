package com.project.recoder.review.model.service;


import static com.project.recoder.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import com.project.recoder.review.model.dao.ReviewDAO;
import com.project.recoder.review.model.vo.review;

public class ReviewService {
	private ReviewDAO dao = new ReviewDAO();

	public String selectMemNick(int replyWriter) throws Exception{
		Connection conn = getConnection();
		String writer = dao.selectMemNick(conn, replyWriter);
		close(conn);
		return writer;
	}

	public List<review> selectList(int parentBoardNo) throws Exception{
		Connection conn = getConnection();

		List<review> rList = dao.selectList(conn, parentBoardNo);

		close(conn);
		return rList;
	}

	public int insertReply(review reply) throws Exception{
		Connection conn = getConnection(); 
		
		String replyContent = reply.getContent();
		
		// 크로스 사이트 스크립팅 방지 처리
		replyContent = replaceParameter(replyContent);
		
		// 개행문자 변환 처리
		// ajax 통신 시 textarea의 개행문자가 \n 하나만 넘어옴.
		// \n -> <br> 
		replyContent = replyContent.replace("\n", "<br>");
				
		// 변경된 replyContent를 다시 reply에 세팅
		reply.setContent(replyContent);
		
		int result = dao.insertReply(conn, reply);
		
		// 트랜잭션 처리
		if(result > 0 ) commit(conn);
		else 			rollback(conn);

		return result;
	}

	
	// 크로스 사이트 스크립트 방지 메소드
	private String replaceParameter(String param) {
		String result = param;
		if (param != null) {
			result = result.replaceAll("&", "&amp;");
			result = result.replaceAll("<", "&lt;");
			result = result.replaceAll(">", "&gt;");
			result = result.replaceAll("\"", "&quot;");
		}

		return result;
	}

	public int chkVisit(int parentBoardNo, int memNo) throws Exception{
		Connection conn = getConnection();
		int result = dao.chkVisit(conn, parentBoardNo, memNo);
		close(conn);
		return result;
	}

	public int reviewChk(int memNo, int roomNo) throws Exception{
		Connection conn = getConnection();
		int result = dao.reviewChk(conn, roomNo, memNo);
		close(conn);
		return result;
	}

}
