package com.iei.spring.board.domain;

import java.sql.Date;

public class Board {
	private int boardNo;
	private String boardTitle;
	private String boardContents;
	private String boardWriter;
	private String boardFileName;
	private String boardFileRename;
	private int boardCount;
	private Date bCreateDate;
	private Date bUpdateDate;
	private String bStatus;
	
	public Board() {}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public String getBoardTitle() {
		return boardTitle;
	}

	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}

	public String getBoardContents() {
		return boardContents;
	}

	public void setBoardContents(String boardContents) {
		this.boardContents = boardContents;
	}

	public String getBoardWriter() {
		return boardWriter;
	}

	public void setBoardWriter(String boardWriter) {
		this.boardWriter = boardWriter;
	}

	public String getBoardFileName() {
		return boardFileName;
	}

	public void setBoardFileName(String boardFileName) {
		this.boardFileName = boardFileName;
	}

	public String getBoardFileRename() {
		return boardFileRename;
	}

	public void setBoardFileRename(String boardFileRename) {
		this.boardFileRename = boardFileRename;
	}

	public int getBoardCount() {
		return boardCount;
	}

	public void setBoardCount(int boardCount) {
		this.boardCount = boardCount;
	}

	public Date getbCreateDate() {
		return bCreateDate;
	}

	public void setbCreateDate(Date bCreateDate) {
		this.bCreateDate = bCreateDate;
	}

	public Date getbUpdateDate() {
		return bUpdateDate;
	}

	public void setbUpdateDate(Date bUpdateDate) {
		this.bUpdateDate = bUpdateDate;
	}

	public String getbStatus() {
		return bStatus;
	}

	public void setbStatus(String bStatus) {
		this.bStatus = bStatus;
	}

	@Override
	public String toString() {
		return "Board [boardNo=" + boardNo + ", boardTitle=" + boardTitle + ", boardContents=" + boardContents
				+ ", boardWriter=" + boardWriter + ", boardFileName=" + boardFileName + ", boardFileRename="
				+ boardFileRename + ", boardCount=" + boardCount + ", bCreateDate=" + bCreateDate + ", bUpdateDate="
				+ bUpdateDate + ", bStatus=" + bStatus + "]";
	}
	
	
}
