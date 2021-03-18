package com.project.recoder.member.model.dao;

import com.project.recoder.broker.model.vo.Broker;
import com.project.recoder.member.model.vo.Member;
import com.project.recoder.room.model.vo.Room;
import com.project.recoder.room.model.vo.RoomImg;

import static com.project.recoder.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class MemberDAO {
	
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rset = null;

	private Properties prop = null;
	
	public MemberDAO() {
		//외부에 저장된 XML 파일로  부터 SQL을 얻어옴 (유지보수성 향상)
		try {
			String filePath = 
					MemberDAO.class.getResource("/com/project/recoder/sql/member/member-query.xml").getPath();
			
			prop = new Properties();
			
			prop.loadFromXML(new FileInputStream(filePath));
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	/** 로그인 dao
	 * @param member
	 * @return member
	 * @throws Exception
	 */
	public Member loginMember(Member member, Connection conn) throws Exception{
		Member loginMember = null;
		String query = prop.getProperty("loginMember");
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, member.getMemId());
			pstmt.setString(2, member.getMemPw());
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				loginMember = new Member(rset.getInt(1),
										 rset.getString(2), 
										 rset.getString(3),
										 rset.getString(4), 
										 rset.getString(5), 
										 rset.getString(6));
			}
			
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return loginMember;
	}


	/** 회원가입 dao
	 * @param member
	 * @param conn
	 * @return result
	 * @throws Exception
	 */
	public int signUp(Member member, Connection conn) throws Exception{
		int result = 0;
		String query = prop.getProperty("signUp");
		
		try {
			pstmt = conn.prepareStatement(query);
			
			// 4)위치홀더(?)에 알맞은 값 세팅
			pstmt.setString(1, member.getMemId());
			pstmt.setString(2, member.getMemPw());
			pstmt.setString(3, member.getMemNick());
			pstmt.setString(4, member.getMemTel());
			pstmt.setString(5, member.getMemEmail());
			
			// 5) SQL 구문 수행 후 반환값 저장
			result = pstmt.executeUpdate();
			
		} finally {
			// 6) 사용한 JDBC 자원 반환
			close(pstmt);
		}
		
		// 7) 결과 반환
		return result;
	}

	public int chkPwd(int memNo, String chkPw, Connection conn) throws Exception{
		int result = 0;
		String query = prop.getProperty("chkPwd");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, memNo);
			pstmt.setString(2, chkPw);
			
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
	
	

	public int updateStatus(Connection conn, int memNo) throws Exception{
		int result = 0;
		String query = prop.getProperty("updateStatus");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, memNo);
			
			result = pstmt.executeUpdate();
		}finally {
			close(pstmt);
		}
		
		return result;
	}
	
	

	public String currentPW(Connection conn, Member member) throws Exception{
		String currentPw = null;
		String query = prop.getProperty("currentPW");
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, member.getMemNo());
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				currentPw = rset.getString(1);
			}
			
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return currentPw;
	}

	public int updateMember(Connection conn, Member member) throws Exception{
		int result =0; 
		
		try {
			String query = prop.getProperty("updateMember");

			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, member.getMemEmail());
			pstmt.setString(2, member.getMemTel());
			pstmt.setString(3, member.getMemPw());
			pstmt.setString(4, member.getMemNick());
			pstmt.setInt(5, member.getMemNo());

			
			result = pstmt.executeUpdate();
		}finally {
			close(pstmt);
		}
		return result;
	}
	
	
	public int heartInsert(Connection conn, String room_no, String mem_no) throws Exception{
		int result =0; 
		String query = prop.getProperty("heartInsert");
		
		try {
			// 3) PreparedStatement 객체를 얻어와 SQL구문을 세팅
			pstmt = conn.prepareStatement(query);
			
			// 4)위치홀더(?)에 알맞은 값 세팅
			pstmt.setString(1, room_no);
			pstmt.setString(2, mem_no);
			
			// 5) SQL 구문 수행 후 반환값 저장
			result = pstmt.executeUpdate();
			
		} finally {
			// 6) 사용한 JDBC 자원 반환
			close(pstmt);
		}
		return result;
	}

	public int heartcheck(Connection conn, String room_no, String mem_no) throws Exception{
		int result = 0;
		String query = prop.getProperty("heartcheck");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, room_no);
			pstmt.setString(2, mem_no);
			
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

	public int heartDelete(Connection conn, String room_no, String mem_no) throws Exception{
		int result =0; 
		String query = prop.getProperty("heartDelete");
		
		try {
			// 3) PreparedStatement 객체를 얻어와 SQL구문을 세팅
			pstmt = conn.prepareStatement(query);
			
			// 4)위치홀더(?)에 알맞은 값 세팅
			pstmt.setString(1, room_no);
			pstmt.setString(2, mem_no);
			
			// 5) SQL 구문 수행 후 반환값 저장
			result = pstmt.executeUpdate();
			
		} finally {
			// 6) 사용한 JDBC 자원 반환
			close(pstmt);
		}
		return result;
	}

	public int nickDupCheck(Connection conn, String nickname) throws Exception{
		int result = 0;
		
		String query = prop.getProperty("nickDupCheck");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, nickname);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				result = rset.getInt(1);
			}
			
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return result;
	}
	
	
	public List<RoomImg> selectimgList(Connection conn, int memNo) throws Exception{
		List<RoomImg> imgList = null;
		String query = prop.getProperty("selectimgList");
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, memNo);
			
			rset = pstmt.executeQuery();
			
			imgList = new ArrayList<RoomImg>();
			
			while (rset.next()) {
	            RoomImg roomImg = new RoomImg(rset.getString("ROOM_IMG_NAME"), rset.getInt("ROOM_NO"));
	            imgList.add(roomImg);
	         }
			
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return imgList;
	}

	public List<Room> selectRoomList(Connection conn, int memNo) throws Exception{
		List<Room> roomList = null;
		String query = prop.getProperty("selectRoomList");
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, memNo);
			
			rset = pstmt.executeQuery();
			
			roomList = new ArrayList<Room>();
			
			while (rset.next()) {
				Room room = new Room(rset.getInt(1), rset.getString(2), rset.getString(3));
	            roomList.add(room);
	         }
			
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return roomList;
	}
	
	
	public List<Room> selectReviewList(Connection conn, int memNo) throws Exception{
		List<Room> roomList = null;
		String query = prop.getProperty("selectReviewList");
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, memNo);
			
			rset = pstmt.executeQuery();
			
			roomList = new ArrayList<Room>();
			
			while (rset.next()) {
	            Room room = new Room(rset.getInt(1), rset.getString(2), rset.getString(3));
	            roomList.add(room);
	         }
			
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return roomList;
	}

	public List<RoomImg> selectReviewimg(Connection conn, int memNo) throws Exception{
		List<RoomImg> imgList = null;
		String query = prop.getProperty("selectReviewimg");
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, memNo);
			
			rset = pstmt.executeQuery();
			
			imgList = new ArrayList<RoomImg>();
			
			while (rset.next()) {
	            RoomImg roomImg = new RoomImg(rset.getString("ROOM_IMG_NAME"), rset.getInt("ROOM_NO"));
	            imgList.add(roomImg);
	         }
			
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return imgList;
	}
	

}
