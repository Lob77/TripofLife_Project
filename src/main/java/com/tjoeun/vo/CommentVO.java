package com.tjoeun.vo;

import java.sql.Date;

public class CommentVO {

	private int idx;
	private String userID;
	private String userComment;
	private Date writedate;
	
	public CommentVO(int idx, String userID, String userComment) {
		super();
		this.idx = idx;
		this.userID = userID;
		this.userComment = userComment;
	}

	public CommentVO() {
		// TODO Auto-generated constructor stub
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getUserComment() {
		return userComment;
	}

	public void setUserComment(String userComment) {
		this.userComment = userComment;
	}

	public Date getWritedate() {
		return writedate;
	}

	public void setWritedate(Date writedate) {
		this.writedate = writedate;
	}

	@Override
	public String toString() {
		return "CommentVO [idx=" + idx + ", userID=" + userID + ", userComment=" + userComment + ", writedate="
				+ writedate + "]";
	}
	
	
}
