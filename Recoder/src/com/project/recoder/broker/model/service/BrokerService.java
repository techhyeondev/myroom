package com.project.recoder.broker.model.service;

import static com.project.recoder.common.JDBCTemplate.close;
import static com.project.recoder.common.JDBCTemplate.commit;
import static com.project.recoder.common.JDBCTemplate.getConnection;
import static com.project.recoder.common.JDBCTemplate.rollback;

import java.io.File;
import java.sql.Connection;
import java.util.List;

import com.project.recoder.broker.model.dao.BrokerDAO;
import com.project.recoder.broker.model.vo.Broker;
import com.project.recoder.room.model.service.FileInsertFailedException;
import com.project.recoder.room.model.vo.Room;
import com.project.recoder.room.model.vo.RoomImg;

public class BrokerService {
	
	BrokerDAO dao = new BrokerDAO();
	/** 공인중개사 회원 로그인 Service
	 * @param broker
	 * @return LoginBroker
	 * @throws Exception
	 */
	public Broker loginBroker(Broker broker) throws Exception{
		Connection conn = getConnection();
		
		Broker loginBroker = dao.loginBroker(broker, conn);
		
		close(conn);
		
		return loginBroker;
	}
	/** 공인중개사 회원가입 service
	 * @param broker
	 * @return result
	 * @throws Exception
	 */
	public int signUp(Broker broker) throws Exception{
		Connection conn = getConnection();
		int result = 0;
		
			
		//가입할 번호 얻어오기
		int memNo = dao.memNo(conn);
		
		if(memNo>0) {
			
			try {
				result = dao.signUp(broker, conn, memNo);
			
			if(result > 0 && !broker.getBrokerCreti().isEmpty()) { //회원가입 성공시
				result = 0;
				broker.setMemNo2(memNo);
				
				result = dao.insertBrokerInfo(conn, broker);
				
				if(result == 0) { //파일 정보 삽입 실패
					throw new FileInsertFailedException("파일 정보 삽입 실패");
				}
			}
				
			}catch (FileInsertFailedException e) {
				if(!broker.getBrokerCreti().isEmpty()) {
					File deleteFile = new File( broker.getBrokerCreti() + broker.getBrokerFileName());
					
					if(deleteFile.exists()) {
						//해당 경로에 해당 파일이 존재하면
						deleteFile.delete(); //해당 파일 삭제
					}
				
				}
				throw e;
			}
			
			if(result > 0) {
				commit(conn);
				
			}else {
				rollback(conn);
			}
		} 
		close(conn);
		
		return result;
	}
	
	/**
	 * @param memNo 
	 * @return
	 * @throws Exception
	 */
	public String selectPw(int memNo) throws Exception{
		Connection conn = getConnection();
		String pw = null;
		
		pw = dao.selectPw(conn, memNo);
		close(conn);
		return pw;
	}
	public int brokerStatusDl(int memNo) throws Exception{
		Connection conn = getConnection();
		int result = 0;
		
		result = dao.brokerStatusDl(conn, memNo);
		
		if(result >0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	
	public List<Room> selectRoom(int brokerNo) throws Exception{
		Connection conn = getConnection();
		List<Room> room = null;
		
		room = dao.selectRoom(conn, brokerNo);
		close(conn);
		return room;
	}
	
	
	public int chkPwd(int memNo, String password) throws Exception{
		Connection conn = getConnection();	 
		int result = dao.chkPwd(memNo, password, conn);
		close(conn);
		return result;
	}
	public List<Room> selectRoomList(int memNo) throws Exception{
		Connection conn = getConnection();
		
		List<Room> roomList = dao.selectRoomList(conn, memNo);
		close (conn);
		
		return roomList;
	}
	
	
	
	public List<RoomImg> selectimgList(int memNo) throws Exception{
		Connection conn = getConnection();
		
		List<RoomImg> imgList = dao.selectimgList(conn, memNo);
		close (conn);
		
		return imgList;
	}
	public int updateMember(Broker member, String memberPw) throws Exception{
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
	
	

	

}
