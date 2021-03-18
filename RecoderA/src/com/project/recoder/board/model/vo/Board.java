package com.project.recoder.board.model.vo;

import java.sql.Timestamp;

public class Board {

	private int boardNo; // 게시글 번호
	private Timestamp createDate; // 작성일
	private String boardTitle; // 게시글 제목
	private String boardContent; // 게시글 내용
	private int readCount; // 조회수
	private String deleteFl; // 삭제 여부
	private int boardCategory; // 게시판 코드
	private int MemberNo; // 회원번호
	private String MemberNick; // 회원 닉네임
	
	public Board() {}

	
	
	public Board(int boardNo, Timestamp createDate, String boardTitle, String boardContent, int readCount,
			String deleteFl, int boardCategory, int memberNo, String memberNick) {
		super();
		this.boardNo = boardNo;
		this.createDate = createDate;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.readCount = readCount;
		this.deleteFl = deleteFl;
		this.boardCategory = boardCategory;
		MemberNo = memberNo;
		MemberNick = memberNick;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public String getBoardTitle() {
		return boardTitle;
	}

	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}

	public String getBoardContent() {
		return boardContent;
	}

	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
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

	public String getMemberNick() {
		return MemberNick;
	}

	public void setMemberNick(String memberNick) {
		MemberNick = memberNick;
	}

	@Override
	public String toString() {
		return "Board [boardNo=" + boardNo + ", createDate=" + createDate + ", boardTitle=" + boardTitle
				+ ", boardContent=" + boardContent + ", readCount=" + readCount + ", deleteFl=" + deleteFl
				+ ", boardCategory=" + boardCategory + ", MemberNo=" + MemberNo + ", MemberNick=" + MemberNick + "]";
	}
	
	
}
