package com.mohaning.app.Model;

import java.sql.Date;

public class MHNR010VO {
	private String board_id;
	private String reply_id;
	private String group_id;
	private String level;
	private String comment;
	private String reg_id;
	private Date reg_dt;
	public String getBoard_id() {
		return board_id == null ? "" : board_id;
	}
	public void setBoard_id(String board_id) {
		this.board_id = board_id;
	}
	public String getReply_id() {
		return reply_id == null ? "" : reply_id;
	}
	public void setReply_id(String reply_id) {
		this.reply_id = reply_id;
	}
	public String getGroup_id() {
		return group_id == null ? "" : group_id;
	}
	public void setGroup_id(String group_id) {
		this.group_id = group_id;
	}
	public String getLevel() {
		return level == null ? "" : level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getComment() {
		return comment == null ? "" : comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getReg_id() {
		return reg_id == null ? "" : reg_id;
	}
	public void setReg_id(String reg_id) {
		this.reg_id = reg_id;
	}
	public Date getReg_dt() {
		return reg_dt;
	}
	public void setReg_dt(Date reg_dt) {
		this.reg_dt = reg_dt;
	}
}
