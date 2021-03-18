package com.project.recoder.review.model.vo;

import java.sql.Timestamp;

public class review {
	private int reviewNo;
	private Timestamp createDt;
	private String content;
	private int rating;
	private int memNo;
	private int roomNo;
	private String memNick;
	
	public review() {}

	public review(int reviewNo, Timestamp createDt, String content, int rating, int memNo, int roomNo, String memNick) {
		super();
		this.reviewNo = reviewNo;
		this.createDt = createDt;
		this.content = content;
		this.rating = rating;
		this.memNo = memNo;
		this.roomNo = roomNo;
		this.memNick = memNick;
	}

	public int getReviewNo() {
		return reviewNo;
	}

	public void setReviewNo(int reviewNo) {
		this.reviewNo = reviewNo;
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

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
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

	public String getMemNick() {
		return memNick;
	}

	public void setMemNick(String memNick) {
		this.memNick = memNick;
	}

	@Override
	public String toString() {
		return "review [reviewNo=" + reviewNo + ", createDt=" + createDt + ", content=" + content + ", rating=" + rating
				+ ", memNo=" + memNo + ", roomNo=" + roomNo + ", memNick=" + memNick + "]";
	}

	
	
	
}
