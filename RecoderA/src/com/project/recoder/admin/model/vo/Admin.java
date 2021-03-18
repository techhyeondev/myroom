package com.project.recoder.admin.model.vo;

public class Admin {
	private int adminNo; //번호
	private String adminId; // 관리자 아이디  
	private String adminPw; // 관리자 비밀번호
	private String adminNick; // 관리자 닉네임
	private String adminPhone; //관리자 전화번호
	private String adminEmail; // 관리자 이메일
	private String adminStatus; // 관리자 상태
	private String adminGrade; // 관리자 등급
	
	public Admin() {
	}
	
	
	
	// 로그인용 생성자
	public Admin(int adminNo, String adminId, String adminNick, String adminPhone, String adminEmail,
			String adminGrade) {
		super();
		this.adminNo = adminNo;
		this.adminId = adminId;
		this.adminNick = adminNick;
		this.adminPhone = adminPhone;
		this.adminEmail = adminEmail;
		this.adminGrade = adminGrade;
	}




	public Admin(int adminNo, String adminId, String adminPw, String adminNick, String adminPhone, String adminEmail,
			String adminStatus, String adminGrade) {
		super();
		this.adminNo = adminNo;
		this.adminId = adminId;
		this.adminPw = adminPw;
		this.adminNick = adminNick;
		this.adminPhone = adminPhone;
		this.adminEmail = adminEmail;
		this.adminStatus = adminStatus;
		this.adminGrade = adminGrade;
	}

	public int getAdminNo() {
		return adminNo;
	}

	public void setAdminNo(int adminNo) {
		this.adminNo = adminNo;
	}

	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	public String getAdminPw() {
		return adminPw;
	}

	public void setAdminPw(String adminPw) {
		this.adminPw = adminPw;
	}

	public String getAdminNick() {
		return adminNick;
	}

	public void setAdminNick(String adminNick) {
		this.adminNick = adminNick;
	}

	public String getAdminPhone() {
		return adminPhone;
	}

	public void setAdminPhone(String adminPhone) {
		this.adminPhone = adminPhone;
	}

	public String getAdminEmail() {
		return adminEmail;
	}

	public void setAdminEmail(String adminEmail) {
		this.adminEmail = adminEmail;
	}

	public String getAdminStatus() {
		return adminStatus;
	}

	public void setAdminStatus(String adminStatus) {
		this.adminStatus = adminStatus;
	}

	public String getAdminGrade() {
		return adminGrade;
	}

	public void setAdminGrade(String adminGrade) {
		this.adminGrade = adminGrade;
	}

	@Override
	public String toString() {
		return "Admin [adminNo=" + adminNo + ", adminId=" + adminId + ", adminPw=" + adminPw + ", adminNick="
				+ adminNick + ", adminPhone=" + adminPhone + ", adminEmail=" + adminEmail + ", adminStatus="
				+ adminStatus + ", adminGrade=" + adminGrade + "]";
	}
	
}
