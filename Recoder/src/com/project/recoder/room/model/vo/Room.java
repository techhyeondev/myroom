package com.project.recoder.room.model.vo;

public class Room {
	
	private int roomNo;
	private String roomAddr;
	private String typeOfRent;
	private int deposit;
	private int monthRent;
	private int careFee;
	private int pubSize;
	private int realSize;
	private String roomFloor;
	private String roomStruc;
	private int roomCount;
	private String tv;
	private String internet; 
	private String airCon;
	private String washing;
	private String fridge;
	private String bed;
	private String closet;
	private String womanOnly;
	private String pet;
	private String parking;
	private int reportCount;
	private String deleteFl;
	private String roomTitle;
	private String roomInfo;
	private String sellFl;
	private String stationAddr;
	private int gMemNo;

	public Room() {
		// TODO Auto-generated constructor stub
	}
	
	public Room(int roomNo, String roomAddr, String typeOfRent, int deposit, int monthRent, int careFee, int pubSize,
			int realSize, String roomFloor, String roomStruc, int roomCount, String tv, String internet, String airCon,
			String washing, String fridge, String bed, String closet, String womanOnly, String pet, String parking,
			int reportCount, String deleteFl, String roomTitle, String roomInfo, String sellFl, String stationAddr,
			int gMemNo) {
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
		this.tv = tv;
		this.internet = internet;
		this.airCon = airCon;
		this.washing = washing;
		this.fridge = fridge;
		this.bed = bed;
		this.closet = closet;
		this.womanOnly = womanOnly;
		this.pet = pet;
		this.parking = parking;
		this.reportCount = reportCount;
		this.deleteFl = deleteFl;
		this.roomTitle = roomTitle;
		this.roomInfo = roomInfo;
		this.sellFl = sellFl;
		this.stationAddr = stationAddr;
		this.gMemNo = gMemNo;
	}
	
	

	public Room(String tv, String internet, String airCon, String washing, String fridge, String bed, String closet,
			String womanOnly, String pet, String parking) {
		super();
		this.tv = tv;
		this.internet = internet;
		this.airCon = airCon;
		this.washing = washing;
		this.fridge = fridge;
		this.bed = bed;
		this.closet = closet;
		this.womanOnly = womanOnly;
		this.pet = pet;
		this.parking = parking;
	}

	

	public Room(int roomNo, String roomTitle, String roomInfo, int gMemNo) {
		super();
		this.roomNo = roomNo;
		this.roomTitle = roomTitle;
		this.roomInfo = roomInfo;
		this.gMemNo = gMemNo;
	}
	
	public Room(int roomNo, String typeOfRent, int deposit, int monthRent, String roomTitle) {
		super();
		this.roomNo = roomNo;
		this.typeOfRent = typeOfRent;
		this.deposit = deposit;
		this.monthRent = monthRent;
		this.roomTitle = roomTitle;
	}





	public Room(int roomNo, String roomTitle, String roomInfo, int gMemNo, String pet) {
		super();
		this.roomNo = roomNo;
		this.roomTitle = roomTitle;
		this.roomInfo = roomInfo;
		this.gMemNo = gMemNo;
		this.pet = pet;
	}

	public Room(int roomNo, String pet) {
		super();
		this.roomNo = roomNo;
		this.pet = pet;
	}

	
	
	

	public Room(int roomNo, String roomTitle, String roomInfo) {
		super();
		this.roomNo = roomNo;
		this.roomTitle = roomTitle;
		this.roomInfo = roomInfo;
	}

	public Room(String bed, String pet, String roomTitle, String roomInfo) {
		super();
		this.bed = bed;
		this.pet = pet;
		this.roomTitle = roomTitle;
		this.roomInfo = roomInfo;
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

	public String getTv() {
		return tv;
	}

	public void setTv(String tv) {
		this.tv = tv;
	}

	public String getInternet() {
		return internet;
	}

	public void setInternet(String internet) {
		this.internet = internet;
	}

	public String getAirCon() {
		return airCon;
	}

	public void setAirCon(String airCon) {
		this.airCon = airCon;
	}

	public String getWashing() {
		return washing;
	}

	public void setWashing(String washing) {
		this.washing = washing;
	}

	public String getFridge() {
		return fridge;
	}

	public void setFridge(String fridge) {
		this.fridge = fridge;
	}

	public String getBed() {
		return bed;
	}

	public void setBed(String bed) {
		this.bed = bed;
	}

	public String getCloset() {
		return closet;
	}

	public void setCloset(String closet) {
		this.closet = closet;
	}

	public String getWomanOnly() {
		return womanOnly;
	}

	public void setWomanOnly(String womanOnly) {
		this.womanOnly = womanOnly;
	}

	public String getPet() {
		return pet;
	}

	public void setPet(String pet) {
		this.pet = pet;
	}

	public String getParking() {
		return parking;
	}

	public void setParking(String parking) {
		this.parking = parking;
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

	@Override
	public String toString() {
		return "Room [roomNo=" + roomNo + ", roomAddr=" + roomAddr + ", typeOfRent=" + typeOfRent + ", deposit="
				+ deposit + ", monthRent=" + monthRent + ", careFee=" + careFee + ", pubSize=" + pubSize + ", realSize="
				+ realSize + ", roomFloor=" + roomFloor + ", roomStruc=" + roomStruc + ", roomCount=" + roomCount
				+ ", tv=" + tv + ", internet=" + internet + ", airCon=" + airCon + ", washing=" + washing + ", fridge="
				+ fridge + ", bed=" + bed + ", closet=" + closet + ", womanOnly=" + womanOnly + ", pet=" + pet
				+ ", parking=" + parking + ", reportCount=" + reportCount + ", deleteFl=" + deleteFl + ", roomTitle="
				+ roomTitle + ", roomInfo=" + roomInfo + ", sellFl=" + sellFl + ", stationAddr=" + stationAddr
				+ ", gMemNo=" + gMemNo + "]";
	}
	
	
}
