package com.project.recoder.message.model.dao;

import static com.project.recoder.common.JDBCTemplate.close;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.project.recoder.message.model.vo.Message;
import com.project.recoder.room.model.dao.RoomDAO;

public class MessageDAO {

	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rset = null;
	
	private Properties prop = null;
	
	public MessageDAO() {
		String fileName = RoomDAO.class.getResource("/com/project/recoder/sql/message/message-query.xml").getPath();
		try {
			prop = new Properties();
			prop.loadFromXML(new FileInputStream(fileName)); 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/** 매물에서 메시지 전송 DAO
	 * @param conn
	 * @param msgContext
	 * @param brokerNo
	 * @param myNo
	 * @return
	 * @throws Exception
	 */
	public int messageSend(Connection conn, String msgContext, int brokerNo, int myNo) throws Exception{
		int result = 0;
		String query = prop.getProperty("messageSend");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, msgContext);
			pstmt.setInt(2, myNo);
			pstmt.setInt(3, brokerNo);
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		
				
		
		return result;
	}

	public List<Message> messageList(Connection conn, int brokerNo) throws Exception {
		List<Message> message = null;
		String query = prop.getProperty("messageList");
		
		try {
			
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, brokerNo);
			
			rset = pstmt.executeQuery();
			
			message = new ArrayList<Message>();
			while(rset.next()) {
				Message msg = new Message(
					rset.getInt("ME"),
					rset.getString("YOU"),
					rset.getString("MSG_CONTENT"),
					rset.getTimestamp("CREATE_DT"),
					rset.getInt("MSG_CNT"),
					rset.getInt("MEM_NO"));
				
				message.add(msg);
			}
			
		} finally {
			close(rset);
			close(pstmt);
		}
		
		
		return message;
	}

	public List<Message> messageUnI(Connection conn, String you, String i) throws Exception{
		List<Message> message = null;
		String query = prop.getProperty("messageUnI");
		
		try {
			
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, you);
			pstmt.setString(2, i);
			pstmt.setString(3, i);
			pstmt.setString(4, you);
			
			rset = pstmt.executeQuery();
			
			message = new ArrayList<Message>();
			while(rset.next()) {
				Message msg = new Message(
					rset.getString("MSG_CONTENT"),
					rset.getTimestamp("CREATE_DT"),
					rset.getInt("I"),
					rset.getInt("YOU"),
					rset.getInt("MSG_CNT"),
					rset.getString("MEM_NICK"));
				message.add(msg);
			}
			
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return message;

	}

	public int messageDelete(Connection conn, String myText, String you, String i) throws Exception{
		int result = 0;
		String query = prop.getProperty("messageDelete");
		
		try {
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, you);
			pstmt.setString(2, i);
			pstmt.setString(3, i);
			pstmt.setString(4, you);
			
			result = pstmt.executeUpdate();
		} finally {
			close(pstmt);
			
		}
		
		
		return result;
	}
}
