package com.project.recoder.message.model.vo;

import java.sql.Timestamp;

public class Message {
	private int msgNo;
	private String msgContent;
	private Timestamp createDate;
	private int memReceive;
	private int memSend;
	private int msgCnt;
	
	private String memNick;
	
	public Message() {

	}

	public Message(int msgNo, String msgContent, Timestamp createDate, int memReceive, int memSend, int msgCnt) {
		super();
		this.msgNo = msgNo;
		this.msgContent = msgContent;
		this.createDate = createDate;
		this.memReceive = memReceive;
		this.memSend = memSend;
		this.msgCnt = msgCnt;
		
	}

	

	public Message( int memReceive, String memNick, String msgContent, Timestamp createDate, int msgCnt, int memSend ) {
		super();
		this.memReceive = memReceive;
		this.memNick = memNick;
		this.msgContent = msgContent;
		this.createDate = createDate;
		this.msgCnt = msgCnt;
		this.memSend = memSend;
	}
	
	
	
	

	public Message(String msgContent, Timestamp createDate, int memReceive, int memSend, int msgCnt, String memNick) {
		super();
		this.msgContent = msgContent;
		this.createDate = createDate;
		this.memReceive = memReceive;
		this.memSend = memSend;
		this.msgCnt = msgCnt;
		this.memNick = memNick;
	}



	public int getMsgNo() {
		return msgNo;
	}

	public void setMsgNo(int msgNo) {
		this.msgNo = msgNo;
	}

	public String getMsgContent() {
		return msgContent;
	}

	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public int getMemReceive() {
		return memReceive;
	}

	public void setMemReceive(int memReceive) {
		this.memReceive = memReceive;
	}

	public int getMemSend() {
		return memSend;
	}

	public void setMemSend(int memSend) {
		this.memSend = memSend;
	}

	public int getMsgCnt() {
		return msgCnt;
	}

	public void setMsgCnt(int msgCnt) {
		this.msgCnt = msgCnt;
	}
	
	public String getMemNick() {
		return memNick;
	}

	public void setMemNick(String memNick) {
		this.memNick = memNick;
	}

	@Override
	public String toString() {
		return "Message [msgNo=" + msgNo + ", msgContent=" + msgContent + ", createDate=" + createDate + ", memReceive="
				+ memReceive + ", memSend=" + memSend + "]";
	}
	
}
