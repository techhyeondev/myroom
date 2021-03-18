package com.project.recoder.board.model.vo;

public class BoardImg {

	private int boardImgNo;
	private String boardImgPath;
	private String boardImgName;
	private int boardImgLevel;
	private int boardNo;
	
	public BoardImg() {
		// TODO Auto-generated constructor stub
	}

	public BoardImg(String boardImgPath, String boardImgName, int boardImgLevel, int boardNo) {
		super();
		this.boardImgPath = boardImgPath;
		this.boardImgName = boardImgName;
		this.boardImgLevel = boardImgLevel;
		this.boardNo = boardNo;
	}

	public BoardImg(int boardImgNo, String boardImgName, int boardImgLevel) {
		super();
		boardImgNo = boardImgNo;
		boardImgName = boardImgName;
		boardImgLevel = boardImgLevel;
	}

	public BoardImg(int boardImgNo, String boardImgPath, String boardImgName, int boardImgLevel, int boardNo) {
		super();
		boardImgNo = boardImgNo;
		boardImgPath = boardImgPath;
		boardImgName = boardImgName;
		boardImgLevel = boardImgLevel;
		this.boardNo = boardNo;
	}

	public int getboardImgNo() {
		return boardImgNo;
	}

	public void setboardImgNo(int boardImgNo) {
		boardImgNo = boardImgNo;
	}

	public String getboardImgPath() {
		return boardImgPath;
	}

	public void setboardImgPath(String boardImgPath) {
		boardImgPath = boardImgPath;
	}

	public String getboardImgName() {
		return boardImgName;
	}

	public void setboardImgName(String boardImgName) {
		boardImgName = boardImgName;
	}

	public int getboardImgLevel() {
		return boardImgLevel;
	}

	public void setboardImgLevel(int boardImgLevel) {
		boardImgLevel = boardImgLevel;
	}

	public int getboardNo() {
		return boardNo;
	}

	public void setboardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	@Override
	public String toString() {
		return "boardImg [boardImgNo=" + boardImgNo + ", boardImgPath=" + boardImgPath + ", boardImgName="
				+ boardImgName + ", boardImgLevel=" + boardImgLevel + ", boardNo=" + boardNo + "]";
	}
	
	
	
}

