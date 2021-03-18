package com.project.recoder.board.model.vo;

import java.sql.Timestamp;

public class Board {
	private int boardNo;
	private String title;
	private String content;
	private String memId;
	private String memNick;
	private int readCount;
	private String boardNm;
	private Timestamp createDate;
	private String deleteFl;
	private int memNo;

	public Board() {
	}

	

	public Board(int boardNo, String title, String content, String memId, String memNick, int readCount, String boardNm,
			Timestamp createDate, String deleteFl, int memNo) {
		super();
		this.boardNo = boardNo;
		this.title = title;
		this.content = content;
		this.memId = memId;
		this.memNick = memNick;
		this.readCount = readCount;
		this.boardNm = boardNm;
		this.createDate = createDate;
		this.deleteFl = deleteFl;
		this.memNo = memNo;
	}



	public Board(int boardNo, String title, String memNick, int readCount, String boardNm, Timestamp createDate,
			int memNo) {
		super();
		this.boardNo = boardNo;
		this.title = title;
		this.memNick = memNick;
		this.readCount = readCount;
		this.boardNm = boardNm;
		this.createDate = createDate;
		this.memNo = memNo;
	}



	public Board(int boardNo, String title, String content, String memId, String memNick, int readCount,
			Timestamp createDate, int memNo) {
		super();
		this.boardNo = boardNo;
		this.title = title;
		this.content = content;
		this.memId = memId;
		this.memNick = memNick;
		this.readCount = readCount;
		this.createDate = createDate;
		this.memNo = memNo;
	}



	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public String getMemNick() {
		return memNick;
	}

	public void setMemNick(String memNick) {
		this.memNick = memNick;
	}

	public int getReadCount() {
		return readCount;
	}

	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}

	public String getBoardNm() {
		return boardNm;
	}

	public void setBoardNm(String boardNm) {
		this.boardNm = boardNm;
	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public String getDeleteFl() {
		return deleteFl;
	}

	public void setDeleteFl(String deleteFl) {
		this.deleteFl = deleteFl;
	}

	public int getMemNo() {
		return memNo;
	}



	public void setMemNo(int memNo) {
		this.memNo = memNo;
	}



	@Override
	public String toString() {
		return "Board [boardNo=" + boardNo + ", title=" + title + ", content=" + content + ", memId=" + memId
				+ ", memNick=" + memNick + ", readCount=" + readCount + ", boardNm=" + boardNm + ", createDate="
				+ createDate + ", deleteFl=" + deleteFl + ", memNo=" + memNo + "]";
	}



	

	

	
	
	
	
}
