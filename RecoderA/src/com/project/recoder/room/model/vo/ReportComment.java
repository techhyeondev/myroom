package com.project.recoder.room.model.vo;

public class ReportComment {
	private String categoryName; //카데고리 이름
	private String memNick; // 회원 닉네임
	private String reportContent; //신고 내용
	private int roomNo; // 룸 번호
	
	public ReportComment() {
		// TODO Auto-generated constructor stub
	}

	public ReportComment(String categoryName, String memNick, String reportContent, int roomNo) {
		super();
		this.categoryName = categoryName;
		this.memNick = memNick;
		this.reportContent = reportContent;
		this.roomNo = roomNo;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getMemNick() {
		return memNick;
	}

	public void setMemNick(String memNick) {
		this.memNick = memNick;
	}

	public String getReportContent() {
		return reportContent;
	}

	public void setReportContent(String reportContent) {
		this.reportContent = reportContent;
	}

	public int getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(int roomNo) {
		this.roomNo = roomNo;
	}

	@Override
	public String toString() {
		return "ReportComment [categoryName=" + categoryName + ", memNick=" + memNick + ", reportContent="
				+ reportContent + ", roomNo=" + roomNo + "]";
	}
	
	
}
