package com.project.recoder.reply.model.vo;

import java.sql.Timestamp;

public class Reply {
	
	private int replyNo; 		 		// 댓글 번호
	private String replyContent; 		// 댓글 내용
	private Timestamp replyCreateDate;	// 댓글 작성일
	private int parentBoardNo;			// 댓글이 작성된 게시글 번호
	private String memberNick;			// 댓글 작성 회원
	private String deleteFl;			// 삭제 여부



public Reply() {}



public Reply(int replyNo, String replyContent, Timestamp replyCreateDate, int parentBoardNo, String memberNick,
		String deleteFl) {
	super();
	this.replyNo = replyNo;
	this.replyContent = replyContent;
	this.replyCreateDate = replyCreateDate;
	this.parentBoardNo = parentBoardNo;
	this.memberNick = memberNick;
	this.deleteFl = deleteFl;
}



public int getReplyNo() {
	return replyNo;
}



public void setReplyNo(int replyNo) {
	this.replyNo = replyNo;
}



public String getReplyContent() {
	return replyContent;
}



public void setReplyContent(String replyContent) {
	this.replyContent = replyContent;
}



public Timestamp getReplyCreateDate() {
	return replyCreateDate;
}



public void setReplyCreateDate(Timestamp replyCreateDate) {
	this.replyCreateDate = replyCreateDate;
}



public int getParentBoardNo() {
	return parentBoardNo;
}



public void setParentBoardNo(int parentBoardNo) {
	this.parentBoardNo = parentBoardNo;
}



public String getMemberNick() {
	return memberNick;
}



public void setMemberNick(String memberNick) {
	this.memberNick = memberNick;
}



public String getDeleteFl() {
	return deleteFl;
}



public void setDeleteFl(String deleteFl) {
	this.deleteFl = deleteFl;
}



@Override
public String toString() {
	return "Reply [replyNo=" + replyNo + ", replyContent=" + replyContent + ", replyCreateDate=" + replyCreateDate
			+ ", parentBoardNo=" + parentBoardNo + ", memberNick=" + memberNick + ", deleteFl=" + deleteFl + "]";
}




}
