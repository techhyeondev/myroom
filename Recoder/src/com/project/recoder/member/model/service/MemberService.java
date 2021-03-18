package com.project.recoder.member.model.service;

import com.project.recoder.broker.model.vo.Broker;
import com.project.recoder.member.model.dao.MemberDAO;
import com.project.recoder.member.model.vo.Member;
import com.project.recoder.room.model.vo.Room;
import com.project.recoder.room.model.vo.RoomImg;

import static com.project.recoder.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

public class MemberService {

	private MemberDAO dao = new MemberDAO();

	/** 로그인 service
	 * @param member
	 * @return member
	 * @throws Exception
	 */
	public Member loginMember(Member member) throws Exception{
		Connection conn = getConnection();
		
		Member loginMember = dao.loginMember(member, conn);
		
		close(conn);
		
		return loginMember;
	}

	/** 회원가입 service
	 * @param member
	 * @return result
	 * @throws Exception
	 */
	public int signUp(Member member) throws Exception{
		Connection conn = getConnection();
		
		int result = dao.signUp(member, conn);
		
		if(result >0 ) commit(conn);
		else			rollback(conn);
		
		close(conn);
		
		return result;
	}

	public int chkPwd(int memNo, String chkPw) throws Exception{
		Connection conn = getConnection();	 
		int result = dao.chkPwd(memNo, chkPw, conn);
		close(conn);
		return result;
	}

	public int updateStatus(int memNo, String chkPw) throws Exception{
		Connection conn = getConnection();
		int result = dao.chkPwd(memNo, chkPw, conn);
		
		if(result > 0) {
			result = dao.updateStatus(conn, memNo);
			
			if(result > 0) commit(conn);
			else			rollback(conn);
			
		}else {
			result = -1;
			System.out.println(result);
		}
		
		return result;
	}

	public int updateMember(Member member, String memberPw) throws Exception{
		Connection conn = getConnection();
		System.out.println("MemberPw:" + memberPw);
		if(memberPw.equals("z4PhNX7vuL3xVChQ1m2AB9Yg5AULVxXcg/SpIdNs6c5H0NE8XYXysP+DGNKHfuwvY7kxvUdBeoGlODJ6+SfaPg==")) {
			memberPw = dao.currentPW(conn, member);
			System.out.println("MemberPw:" + memberPw);
		}
		member.setMemPw(memberPw);

		int result = dao.updateMember(conn, member);
		//3) 트랜잭션 처리
		if(result >0) {commit(conn);}
		else {rollback(conn);}
		//4) connection 반환
		close(conn);
		//5) retrun
		return result;
	}
	
	
	public int heartInsert(String room_no, String mem_no) throws Exception{
		Connection conn = getConnection();
		int result = dao.heartInsert(conn, room_no, mem_no);
		
		if(result>0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}

	public int heartcheck(String room_no, String mem_no) throws Exception{
		Connection conn = getConnection();
		int result = dao.heartcheck(conn, room_no, mem_no);
		close(conn);
		return result;
	}

	public int heartDelete(String room_no, String mem_no) throws Exception{
		Connection conn = getConnection();
		int result = dao.heartDelete(conn, room_no, mem_no);
		
		if(result>0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}
	
	
	public int nickDupCheck(String nickname) throws Exception{
		Connection conn = getConnection();
		
		int result = dao.nickDupCheck(conn, nickname);
		
		close(conn);
		
		return result;
	}

	
	public List<RoomImg> selectimgList(int memNo) throws Exception{
		Connection conn = getConnection();
		
		List<RoomImg> imgList = dao.selectimgList(conn, memNo);
		close (conn);
		
		return imgList;
	}

	public List<Room> selectRoomList(int memNo) throws Exception{
		Connection conn = getConnection();
		
		List<Room> roomList = dao.selectRoomList(conn, memNo);
		close (conn);
		
		return roomList;
	}
	
	public List<Room> selectReviewList(int memNo) throws Exception{
		Connection conn = getConnection();
		
		List<Room> roomList = dao.selectReviewList(conn, memNo);
		close (conn);
		
		return roomList;
	}

	public List<RoomImg> selectReviewimg(int memNo) throws Exception{
		Connection conn = getConnection();
		
		List<RoomImg> imgList = dao.selectReviewimg(conn, memNo);
		close (conn);
		
		return imgList;
	}
	
	
	
}
