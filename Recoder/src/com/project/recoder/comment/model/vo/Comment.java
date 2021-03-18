package com.project.recoder.comment.model.vo;

import java.sql.Timestamp;

public class Comment {

	private int commentNo ;
	private String memNick;
	private Timestamp createDt ;
	private String content;
	private String deleteFl ;
	private int boardNo;
	
	public Comment() {
	}

	public Comment(int commentNo, String memNick, Timestamp createDt, String content, String deleteFl, int boardNo) {
		super();
		this.commentNo = commentNo;
		this.memNick = memNick;
		this.createDt = createDt;
		this.content = content;
		this.deleteFl = deleteFl;
		this.boardNo = boardNo;
	}

	
	public Comment(int commentNo, String memNick, Timestamp createDt, String content) {
		super();
		this.commentNo = commentNo;
		this.memNick = memNick;
		this.createDt = createDt;
		this.content = content;
	}

	public int getCommentNo() {
		return commentNo;
	}

	public void setCommentNo(int commentNo) {
		this.commentNo = commentNo;
	}

	public String getMemNick() {
		return memNick;
	}

	public void setMemNick(String memNick) {
		this.memNick = memNick;
	}

	public Timestamp getCreateDt() {
		return createDt;
	}

	public void setCreateDt(Timestamp createDt) {
		this.createDt = createDt;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDeleteFl() {
		return deleteFl;
	}

	public void setDeleteFl(String deleteFl) {
		this.deleteFl = deleteFl;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	@Override
	public String toString() {
		return "Comment [commentNo=" + commentNo + ", memNick=" + memNick + ", createDt=" + createDt + ", content="
				+ content + ", deleteFl=" + deleteFl + ", boardNo=" + boardNo + "]";
	}

	
}
