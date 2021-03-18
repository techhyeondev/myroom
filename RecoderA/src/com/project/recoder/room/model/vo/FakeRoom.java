package com.project.recoder.room.model.vo;

public class FakeRoom {
	private int roomNo; // 방번호
	private String roomTitle; //방제목
	private String roomInfo; //방 정보
	private String RoomImgName; //방 이름
	private int reportCount; // 신고회수
	private String brokerNick; // 공인중개사 닉네임
	
	public FakeRoom() {}

	public FakeRoom(int roomNo, String roomTitle, String roomInfo, String roomImgName, int reportCount,
			String brokerNick) {
		super();
		this.roomNo = roomNo;
		this.roomTitle = roomTitle;
		this.roomInfo = roomInfo;
		RoomImgName = roomImgName;
		this.reportCount = reportCount;
		this.brokerNick = brokerNick;
	}

	public int getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(int roomNo) {
		this.roomNo = roomNo;
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

	public String getRoomImgName() {
		return RoomImgName;
	}

	public void setRoomImgName(String roomImgName) {
		RoomImgName = roomImgName;
	}

	public int getReportCount() {
		return reportCount;
	}

	public void setReportCount(int reportCount) {
		this.reportCount = reportCount;
	}

	public String getBrokerNick() {
		return brokerNick;
	}

	public void setBrokerNick(String brokerNick) {
		this.brokerNick = brokerNick;
	}

	@Override
	public String toString() {
		return "fakeRoom [roomNo=" + roomNo + ", roomTitle=" + roomTitle + ", roomInfo=" + roomInfo + ", RoomImgName="
				+ RoomImgName + ", reportCount=" + reportCount + ", brokerNick=" + brokerNick + "]";
	}
	
	
}
