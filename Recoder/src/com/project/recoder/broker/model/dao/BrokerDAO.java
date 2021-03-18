package com.project.recoder.broker.model.dao;

import static com.project.recoder.common.JDBCTemplate.close;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.project.recoder.broker.model.vo.Broker;
import com.project.recoder.room.model.vo.Room;
import com.project.recoder.room.model.vo.RoomImg;

public class BrokerDAO {

	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rset = null;

	private Properties prop = null;
	
	public BrokerDAO() {
		try {
			String filePath = 
					BrokerDAO.class.getResource("/com/project/recoder/sql/broker/broker-query.xml").getPath();
			
			prop = new Properties();
			
			prop.loadFromXML(new FileInputStream(filePath));
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 공인중개사 로그인 dao
	 * @param broker
	 * @param conn
	 * @return loginBroker
	 * @throws Exception
	 */
	public Broker loginBroker(Broker broker, Connection conn) throws Exception{
		Broker loginBroker = null;
		
		String query = prop.getProperty("loginBroker");
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, broker.getMemId());
			pstmt.setString(2, broker.getMemPw());
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				//MEM_NO, MEM_ID, MEM_NICK, MEM_TEL, MEM_EMAIL, MEM_GRADE,BROKER_CRETI, BROKER_ADDR, APPROVE_FL
				loginBroker = new Broker(
						rset.getInt("MEM_NO"),
						rset.getString("MEM_ID"), 
						rset.getString("MEM_NICK"), 
						rset.getString("MEM_TEL"), 
						rset.getString("MEM_EMAIL"),
						rset.getString("MEM_GRADE"),
						rset.getString("BROKER_CRETI"),
						rset.getString("BROKER_ADDR"), 
						rset.getString("APPROVE_FL"));
			}
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return loginBroker;
	}

	/** 공인중개사 회원가입 dao
	 * @param broker
	 * @param conn
	 * @param memNo 
	 * @return result
	 * @throws Exception
	 */
	public int signUp(Broker broker, Connection conn, int memNo) throws Exception{
		int result = 0;
		
		String query = prop.getProperty("signUp");
		
		try {
			pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, memNo);
				pstmt.setString(2, broker.getMemId());
				pstmt.setString(3, broker.getMemPw());
				pstmt.setString(4, broker.getMemNick());
				pstmt.setString(5, broker.getMemTel());
				pstmt.setString(6, broker.getMemEmail());
				
				// 5) SQL 구문 수행 후 반환값 저장
				result = pstmt.executeUpdate();
				
			} finally {
				// 6) 사용한 JDBC 자원 반환
				close(pstmt);
			}
		
		return result;
	}

	/** 현재 가입하는 멤버 넘버 받기
	 * @param conn 
	 * @return memNo
	 * @throws Exception
	 */
	public int memNo(Connection conn) throws Exception{
		int memNo = 0;
		
		String query = prop.getProperty("memNo");
		try {
			stmt = conn.createStatement();
			
			rset = stmt.executeQuery(query);
			
			if(rset.next()) {
				memNo= rset.getInt(1);
			}
		} finally {
			close(rset);
			close(stmt);
		}
		
		return memNo;
	}

	/** 공인중개사 추가정보 등록  dao
	 * @param conn
	 * @param broker
	 * @return result 
	 * @throws Exception
	 */
	public int insertBrokerInfo(Connection conn, Broker broker) throws Exception{
		int result = 0;
		
		String query = prop.getProperty("insertBrokerInfo");
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, broker.getMemNo2());
			pstmt.setString(2, broker.getBrokerCreti());
			pstmt.setString(3, broker.getBrokerAddr());
			pstmt.setString(4, broker.getBrokerFileName());
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public String selectPw(Connection conn, int memNo) throws Exception{
		String pw = null;
		String query = prop.getProperty("selectPw");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, memNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				pw = rset.getString("MEM_PW");
			}
		}finally {
			close(rset);
			close(pstmt);
		}
		
		
		return pw;
	}

	public int brokerStatusDl(Connection conn, int memNo) throws Exception{
		int result = 0;
		String query = prop.getProperty("brokerStatusDl");
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, memNo);
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
			
		}
		
		
		
		return result;
	}

	public List<Room> selectRoom(Connection conn, int brokerNo) throws Exception{
		List<Room> rList = null;
		String query = prop.getProperty("selectRoom");
		try {
			
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, brokerNo);

			rset = pstmt.executeQuery();

			rList = new ArrayList<Room>();

			while (rset.next()) {
				Room room = new Room(
					rset.getInt("ROOM_NO"), 
					rset.getString("ROOM_TITLE"),
					rset.getString("ROOM_INFO"),
					rset.getInt("GMEM_NO"),
					rset.getString("ROOM_IMG_NAME"));

				
				rList.add(room);
			}
			
		} finally {
			close(rset);
			close(stmt);
		}
		
		return rList;
	}
	
	
	
	public int chkPwd(int memNo, String password, Connection conn) throws Exception{
		int result = 0;
		String query = prop.getProperty("chkPwd");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, memNo);
			pstmt.setString(2, password);
			
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

	public List<Room> selectRoomList(Connection conn, int memNo) throws Exception{
		List<Room> roomList = null;
		String query = prop.getProperty("selectRoomList");
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, memNo);
			
			rset = pstmt.executeQuery();
			
			roomList = new ArrayList<Room>();
			
			while (rset.next()) {
	            Room room = new Room(rset.getInt(1), rset.getString(2));
	            roomList.add(room);
	         }
			
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return roomList;
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

	public String currentPW(Connection conn, Broker member) throws Exception{
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

	public int updateMember(Connection conn, Broker member) throws Exception{
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
			
			if(result > 0) {
				query = prop.getProperty("updateBroker");
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, member.getBrokerAddr());
				pstmt.setInt(2, member.getMemNo());
				
				result = pstmt.executeUpdate();
			}
			
		}finally {
			close(pstmt);
		}
		return result;
	}


}
