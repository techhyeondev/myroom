package com.project.recoder.member.model.vo;

public class Member {
	private int memNo;
	private String memId;
	private String memPw;
	private String memNick;
	private String memTel;
	private String memEmail;
	private String memStateFl;
	private String memGrade;
	
	public Member() {
		// TODO Auto-generated constructor stub
	}

	public Member(int memNo, String memId, String memPw, String memNick, String memTel, String memEmail,
			String memStateFl, String memGrade) {
		super();
		this.memNo = memNo;
		this.memId = memId;
		this.memPw = memPw;
		this.memNick = memNick;
		this.memTel = memTel;
		this.memEmail = memEmail;
		this.memStateFl = memStateFl;
		this.memGrade = memGrade;
	}

	//로그인용 생성자
	public Member(int memNo, String memId, String memNick, String memTel, String memEmail, String memGrade) {
		super();
		this.memNo = memNo;
		this.memId = memId;
		this.memNick = memNick;
		this.memTel = memTel;
		this.memEmail = memEmail;
		this.memGrade = memGrade;
	}

	
	
	//회원가입용 생성자
	public Member(String memId, String memPw, String memNick, String memTel, String memEmail) {
		super();
		this.memId = memId;
		this.memPw = memPw;
		this.memNick = memNick;
		this.memTel = memTel;
		this.memEmail = memEmail;
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

	public String getMemPw() {
		return memPw;
	}

	public void setMemPw(String memPw) {
		this.memPw = memPw;
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
		return "Member [memNo=" + memNo + ", memId=" + memId + ", memPw=" + memPw + ", memNick=" + memNick + ", memTel="
				+ memTel + ", memEmail=" + memEmail + ", memStateFl=" + memStateFl + ", memGrade=" + memGrade + "]";
	}
	
	
}
