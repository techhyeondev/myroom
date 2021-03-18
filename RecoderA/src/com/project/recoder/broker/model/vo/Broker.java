package com.project.recoder.broker.model.vo;

public class Broker {
	private int brokerNo; //중개사 회원번호
	private String brokerId; // 중개사 아이디
	private String brokerNick; // 중개사 닉네임
	private String brokerStateFl; // 탈퇴여부
	private String brokerGrade; //중개사 등급 == B
	private String brokerFileName; //  파일 이름
	private String brokerCreti; // 파일 경로
	private String brokerAddr; // 사무소 주소
	private String approveFl; // 승인여부
	
	
	public Broker() {}
	
	
	// 가입 승인 요청한 중개사 목록 조회용 생성자
	public Broker(int brokerNo, String brokerNick, String brokerFileName, String brokerCreti, String brokerAddr,
			String approveFl) {
		super();
		this.brokerNo = brokerNo;
		this.brokerNick = brokerNick;
		this.brokerFileName = brokerFileName;
		this.brokerCreti = brokerCreti;
		this.brokerAddr = brokerAddr;
		this.approveFl = approveFl;
	}



	public Broker(int brokerNo, String brokerId, String brokerNick, String brokerStateFl, String brokerGrade,
			String brokerFileName, String brokerCreti, String brokerAddr, String approveFl) {
		super();
		this.brokerNo = brokerNo;
		this.brokerId = brokerId;
		this.brokerNick = brokerNick;
		this.brokerStateFl = brokerStateFl;
		this.brokerGrade = brokerGrade;
		this.brokerFileName = brokerFileName;
		this.brokerCreti = brokerCreti;
		this.brokerAddr = brokerAddr;
		this.approveFl = approveFl;
	}


	public int getBrokerNo() {
		return brokerNo;
	}


	public void setBrokerNo(int brokerNo) {
		this.brokerNo = brokerNo;
	}


	public String getBrokerId() {
		return brokerId;
	}


	public void setBrokerId(String brokerId) {
		this.brokerId = brokerId;
	}


	public String getBrokerNick() {
		return brokerNick;
	}


	public void setBrokerNick(String brokerNick) {
		this.brokerNick = brokerNick;
	}


	public String getBrokerStateFl() {
		return brokerStateFl;
	}


	public void setBrokerStateFl(String brokerStateFl) {
		this.brokerStateFl = brokerStateFl;
	}


	public String getBrokerGrade() {
		return brokerGrade;
	}


	public void setBrokerGrade(String brokerGrade) {
		this.brokerGrade = brokerGrade;
	}


	public String getBrokerFileName() {
		return brokerFileName;
	}


	public void setBrokerFileName(String brokerFileName) {
		this.brokerFileName = brokerFileName;
	}


	public String getBrokerCreti() {
		return brokerCreti;
	}


	public void setBrokerCreti(String brokerCreti) {
		this.brokerCreti = brokerCreti;
	}


	public String getBrokerAddr() {
		return brokerAddr;
	}


	public void setBrokerAddr(String brokerAddr) {
		this.brokerAddr = brokerAddr;
	}


	public String getApproveFl() {
		return approveFl;
	}


	public void setApproveFl(String approveFl) {
		this.approveFl = approveFl;
	}


	@Override
	public String toString() {
		return "Broker [brokerNo=" + brokerNo + ", brokerId=" + brokerId + ", brokerNick=" + brokerNick
				+ ", brokerStateFl=" + brokerStateFl + ", brokerGrade=" + brokerGrade + ", brokerFileName="
				+ brokerFileName + ", brokerCreti=" + brokerCreti + ", brokerAddr=" + brokerAddr + ", approveFl="
				+ approveFl + "]";
	}
	
	
	
}
