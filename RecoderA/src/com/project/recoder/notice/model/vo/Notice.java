package com.project.recoder.notice.model.vo;

import java.sql.Timestamp;

public class Notice {
	
	private int noticeNo; // 게시글 번호
	private Timestamp createDate; // 작성일
	private String noticeTitle; // 공지사항 제목
	private String noticeContent; // 공지사항 내용
	private int readCount; // 조회수
	private String deleteFl; // 삭제 여부
	private int boardCategory; // 게시판 코드
	private int MemberNo; // 회원번호
	
	
	
	public Notice() {}

	

	public Notice(int noticeNo, Timestamp createDate, String noticeTitle, String noticeContent, int readCount,
			String deleteFl) {
		super();
		this.noticeNo = noticeNo;
		this.createDate = createDate;
		this.noticeTitle = noticeTitle;
		this.noticeContent = noticeContent;
		this.readCount = readCount;
		this.deleteFl = deleteFl;
	}



	public Notice(int noticeNo, Timestamp createDate, String noticeTitle, String noticeContent, int readCount,
			String deleteFl, int boardCategory, int memberNo) {
		super();
		this.noticeNo = noticeNo;
		this.createDate = createDate;
		this.noticeTitle = noticeTitle;
		this.noticeContent = noticeContent;
		this.readCount = readCount;
		this.deleteFl = deleteFl;
		this.boardCategory = boardCategory;
		MemberNo = memberNo;
	}



	public int getNoticeNo() {
		return noticeNo;
	}



	public void setNoticeNo(int noticeNo) {
		this.noticeNo = noticeNo;
	}



	public Timestamp getCreateDate() {
		return createDate;
	}



	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}



	public String getNoticeTitle() {
		return noticeTitle;
	}



	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}



	public String getNoticeContent() {
		return noticeContent;
	}



	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}



	public int getReadCount() {
		return readCount;
	}



	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}



	public String getDeleteFl() {
		return deleteFl;
	}



	public void setDeleteFl(String deleteFl) {
		this.deleteFl = deleteFl;
	}



	public int getBoardCategory() {
		return boardCategory;
	}



	public void setBoardCategory(int boardCategory) {
		this.boardCategory = boardCategory;
	}



	public int getMemberNo() {
		return MemberNo;
	}



	public void setMemberNo(int memberNo) {
		MemberNo = memberNo;
	}



	@Override
	public String toString() {
		return "Notice [noticeNo=" + noticeNo + ", createDate=" + createDate + ", noticeTitle=" + noticeTitle
				+ ", noticeContent=" + noticeContent + ", readCount=" + readCount + ", deleteFl=" + deleteFl
				+ ", boardCategory=" + boardCategory + ", MemberNo=" + MemberNo + "]";
	}
	
	
}
