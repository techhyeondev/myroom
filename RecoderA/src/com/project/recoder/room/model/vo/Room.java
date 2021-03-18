package com.project.recoder.room.model.vo;

public class Room {
	private int roomNo; //매물 번호
	private String roomAddr; // 주소
	private String typeOfRent; // 월세,전세
	private int deposit; // 보증금
	private int monthRent; // 월세
	private int careFee; // 관리비
	private int pubSize; //공급면적
	private int realSize; //전용면적
	private String roomFloor; //층
	private String roomStruc; // 구조
	private int roomCount; // 방 개수
	private int reportCount; //신고회수
	private String deleteFl; // 삭제여부
	private String roomTitle; // 매물제목
	private String roomInfo; // 매물 정보
	private String sellFl; // 판매여부
	private String stationAddr; //지하철 주소
	private int gMemNo; //등록한 공인중개사 회원번호
	private String gMemNick; // 등록한 공인중개사 닉네임
	
	public Room() {}

	
	// 매물 관리 (매물 삭제/복구)용 생성자
	



	public Room(int roomNo, String roomAddr, String typeOfRent, int deposit, int monthRent, int careFee, int pubSize,
			int realSize, String roomFloor, String roomStruc, int roomCount, int reportCount, String deleteFl,
			String roomTitle, String roomInfo, String sellFl, String stationAddr, int gMemNo, String gMemNick) {
		super();
		this.roomNo = roomNo;
		this.roomAddr = roomAddr;
		this.typeOfRent = typeOfRent;
		this.deposit = deposit;
		this.monthRent = monthRent;
		this.careFee = careFee;
		this.pubSize = pubSize;
		this.realSize = realSize;
		this.roomFloor = roomFloor;
		this.roomStruc = roomStruc;
		this.roomCount = roomCount;
		this.reportCount = reportCount;
		this.deleteFl = deleteFl;
		this.roomTitle = roomTitle;
		this.roomInfo = roomInfo;
		this.sellFl = sellFl;
		this.stationAddr = stationAddr;
		this.gMemNo = gMemNo;
		this.gMemNick = gMemNick;
	}

	// 매물 관리 (매물 삭제/복구)용 생성자
	public Room(int roomNo, String deleteFl, String roomTitle, String gMemNick) {
		super();
		this.roomNo = roomNo;
		this.deleteFl = deleteFl;
		this.roomTitle = roomTitle;
		this.gMemNick = gMemNick;
	}


	public int getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(int roomNo) {
		this.roomNo = roomNo;
	}

	public String getRoomAddr() {
		return roomAddr;
	}

	public void setRoomAddr(String roomAddr) {
		this.roomAddr = roomAddr;
	}

	public String getTypeOfRent() {
		return typeOfRent;
	}

	public void setTypeOfRent(String typeOfRent) {
		this.typeOfRent = typeOfRent;
	}

	public int getDeposit() {
		return deposit;
	}

	public void setDeposit(int deposit) {
		this.deposit = deposit;
	}

	public int getMonthRent() {
		return monthRent;
	}

	public void setMonthRent(int monthRent) {
		this.monthRent = monthRent;
	}

	public int getCareFee() {
		return careFee;
	}

	public void setCareFee(int careFee) {
		this.careFee = careFee;
	}

	public int getPubSize() {
		return pubSize;
	}

	public void setPubSize(int pubSize) {
		this.pubSize = pubSize;
	}

	public int getRealSize() {
		return realSize;
	}

	public void setRealSize(int realSize) {
		this.realSize = realSize;
	}

	public String getRoomFloor() {
		return roomFloor;
	}

	public void setRoomFloor(String roomFloor) {
		this.roomFloor = roomFloor;
	}

	public String getRoomStruc() {
		return roomStruc;
	}

	public void setRoomStruc(String roomStruc) {
		this.roomStruc = roomStruc;
	}

	public int getRoomCount() {
		return roomCount;
	}

	public void setRoomCount(int roomCount) {
		this.roomCount = roomCount;
	}

	public int getReportCount() {
		return reportCount;
	}

	public void setReportCount(int reportCount) {
		this.reportCount = reportCount;
	}

	public String getDeleteFl() {
		return deleteFl;
	}

	public void setDeleteFl(String deleteFl) {
		this.deleteFl = deleteFl;
	}

	public String getRoomTitle() {
		return roomTitle;
	}

	public void setRoomTitle(String roomTitle) {
		this.roomTitle = roomTitle;
	}

	public String getRoomInfo() {
		return roomInfo;
	}

	public void setRoomInfo(String roomInfo) {
		this.roomInfo = roomInfo;
	}

	public String getSellFl() {
		return sellFl;
	}

	public void setSellFl(String sellFl) {
		this.sellFl = sellFl;
	}

	public String getStationAddr() {
		return stationAddr;
	}

	public void setStationAddr(String stationAddr) {
		this.stationAddr = stationAddr;
	}

	public int getgMemNo() {
		return gMemNo;
	}

	public void setgMemNo(int gMemNo) {
		this.gMemNo = gMemNo;
	}

	public String getgMemNick() {
		return gMemNick;
	}

	public void setgMemNick(String gMemNick) {
		this.gMemNick = gMemNick;
	}

	@Override
	public String toString() {
		return "Room [roomNo=" + roomNo + ", roomAddr=" + roomAddr + ", typeOfRent=" + typeOfRent + ", deposit="
				+ deposit + ", monthRent=" + monthRent + ", careFee=" + careFee + ", pubSize=" + pubSize + ", realSize="
				+ realSize + ", roomFloor=" + roomFloor + ", roomStruc=" + roomStruc + ", roomCount=" + roomCount
				+ ", reportCount=" + reportCount + ", deleteFl=" + deleteFl + ", roomTitle=" + roomTitle + ", roomInfo="
				+ roomInfo + ", sellFl=" + sellFl + ", stationAddr=" + stationAddr + ", gMemNo=" + gMemNo
				+ ", gMemNick=" + gMemNick + "]";
	}
	
	
}