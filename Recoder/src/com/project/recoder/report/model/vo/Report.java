package com.project.recoder.report.model.vo;

import java.sql.Date;

public class Report {
	private int rpNo;
	private String rpTitle;
	private String rpContent;
	private Date rpCreateDate;
	private int roomNo;
	private int memNo;
	private int categoryNo;
	
	public Report() {
		// TODO Auto-generated constructor stub
	}

	public Report(int rpNo, String rpTitle, String rpContent, Date rpCreateDate, int roomNo, int memNo,
			int categoryNo) {
		super();
		this.rpNo = rpNo;
		this.rpTitle = rpTitle;
		this.rpContent = rpContent;
		this.rpCreateDate = rpCreateDate;
		this.roomNo = roomNo;
		this.memNo = memNo;
		this.categoryNo = categoryNo;
	}

	public int getRpNo() {
		return rpNo;
	}

	public void setRpNo(int rpNo) {
		this.rpNo = rpNo;
	}

	public String getRpTitle() {
		return rpTitle;
	}

	public void setRpTitle(String rpTitle) {
		this.rpTitle = rpTitle;
	}

	public String getRpContent() {
		return rpContent;
	}

	public void setRpContent(String rpContent) {
		this.rpContent = rpContent;
	}

	public Date getRpCreateDate() {
		return rpCreateDate;
	}

	public void setRpCreateDate(Date rpCreateDate) {
		this.rpCreateDate = rpCreateDate;
	}

	public int getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(int roomNo) {
		this.roomNo = roomNo;
	}

	public int getMemNo() {
		return memNo;
	}

	public void setMemNo(int memNo) {
		this.memNo = memNo;
	}

	public int getCategoryNo() {
		return categoryNo;
	}

	public void setCategoryNo(int categoryNo) {
		this.categoryNo = categoryNo;
	}

	@Override
	public String toString() {
		return "Report [rpNo=" + rpNo + ", rpTitle=" + rpTitle + ", rpContent=" + rpContent + ", rpCreateDate="
				+ rpCreateDate + ", roomNo=" + roomNo + ", memNo=" + memNo + ", categoryNo=" + categoryNo + "]";
	}
	
	
	
}
