package com.project.recoder.visit.model.vo;

import java.sql.Timestamp;

public class Visit {
	private int memNo;
	private int roomNo;
	private Timestamp visitDt;
	private int visitCd;
	
	private String memName;
	
	
	public Visit() {
	}

	public Visit(int memNo, int roomNo, Timestamp visitDt, int visitCd, String memName) {
		super();
		this.memNo = memNo;
		this.roomNo = roomNo;
		this.visitDt = visitDt;
		this.visitCd = visitCd;
		this.memName= memName;
	}


	public Visit(String memName, Timestamp visitDt, int roomNo) {
		super();
		this.memName= memName;
		this.visitDt = visitDt;
		this.roomNo = roomNo;
	}

	public int getMemNo() {
		return memNo;
	}

	public void setMemNo(int memNo) {
		this.memNo = memNo;
	}

	public int getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(int roomNo) {
		this.roomNo = roomNo;
	}

	public Timestamp getVisitDt() {
		return visitDt;
	}

	public void setVisitDt(Timestamp visitDt) {
		this.visitDt = visitDt;
	}

	public int getVisitCd() {
		return visitCd;
	}

	public void setVisitCd(int visitCd) {
		this.visitCd = visitCd;
	}
	
	public String getMemName() {
		return memName;
	}

	public void setMemName(String memName) {
		this.memName = memName;
	}

	@Override
	public String toString() {
		return "Visit [memNo=" + memNo + ", roomNo=" + roomNo + ", visitDt=" + visitDt + ", visitCd=" + visitCd
				+ ", memName=" + memName + "]";
	}

	
}
