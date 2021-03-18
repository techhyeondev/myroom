package com.project.recoder.member.model.vo;

public class Member {
	private int memNo; // 회원번호
	private String memId; // id
	private String memNick; // 닉네임
	private String memTel; // 전화번호
	private String memEmail; // 이메일
	private String memStateFl; // 탈퇴여부
	private String memGrade; //회원등급 (G,B,A)
	
	public Member() {}
	
	
	
	public Member(int memNo, String memId, String memNick, String memTel, String memEmail, String memStateFl,
			String memGrade) {
		super();
		this.memNo = memNo;
		this.memId = memId;
		this.memNick = memNick;
		this.memTel = memTel;
		this.memEmail = memEmail;
		this.memStateFl = memStateFl;
		this.memGrade = memGrade;
	}



	public int getMemNo() {
		return memNo;
	}

	public void setMemNo(int memNo) {
		this.memNo = memNo;
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

	public String getMemTel() {
		return memTel;
	}

	public void setMemTel(String memTel) {
		this.memTel = memTel;
	}

	public String getMemEmail() {
		return memEmail;
	}

	public void setMemEmail(String memEmail) {
		this.memEmail = memEmail;
	}

	public String getMemStateFl() {
		return memStateFl;
	}

	public void setMemStateFl(String memStateFl) {
		this.memStateFl = memStateFl;
	}

	public String getMemGrade() {
		return memGrade;
	}

	public void setMemGrade(String memGrade) {
		this.memGrade = memGrade;
	}

	@Override
	public String toString() {
		return "Member [memNo=" + memNo + ", memId=" + memId + ", memNick=" + memNick + ", memTel=" + memTel
				+ ", memEmail=" + memEmail + ", memStateFl=" + memStateFl + ", memGrade=" + memGrade + "]";
	}
	
	

}