package com.project.recoder.message.model.service;

import static com.project.recoder.common.JDBCTemplate.close;
import static com.project.recoder.common.JDBCTemplate.commit;
import static com.project.recoder.common.JDBCTemplate.getConnection;
import static com.project.recoder.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import com.project.recoder.message.model.dao.MessageDAO;
import com.project.recoder.message.model.vo.Message;

public class MessageService {
	
	MessageDAO dao = new MessageDAO();
	

	/**
	 * @param msgContext
	 * @param brokerNo
	 * @param myNo
	 * @return result
	 * @throws Exception
	 */
	public int messageSend(String msgContext, int brokerNo, int myNo) throws Exception{
		Connection conn = getConnection();
		int result = 0;
		
		result = dao.messageSend(conn, msgContext, brokerNo, myNo);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		return result;
	}


	public List<Message> messageList(int brokerNo) throws Exception{
		Connection conn = getConnection();
		List<Message> message = null;
		
		message = dao.messageList(conn, brokerNo);
		
		close(conn);
		
		return message;
	}


	public List<Message> messageUnI(String you, String i) throws Exception{
		Connection conn = getConnection();
		List<Message> mChat = null;
		
		mChat = dao.messageUnI(conn, you, i);
		
		return mChat;
	}
	
	
	public int messageDelete(String myText, String you, String i) throws Exception{
		Connection conn = getConnection();
		int result = 0;
		
		result = dao.messageDelete(conn, myText, you, i);
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		return result;
	}

	
}
