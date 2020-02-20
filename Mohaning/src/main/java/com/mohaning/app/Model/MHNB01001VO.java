package com.mohaning.app.Model;

import java.sql.Date;

public class MHNB01001VO {	
	private String board_id;
	private String news_id;
	private String title;
	private String contents;
	private int view_cnt;
	private int del_fl;
	private String reg_id;
	private Date reg_dt;
	private String upd_id;
	private Date upd_dt;
	public String getBoard_id() {
		return board_id == null ? "" : board_id;
	}
	public void setBoard_id(String board_id) {
		this.board_id = board_id;
	}
	public String getNews_id() {
		return news_id == null ? "" : news_id;
	}
	public void setNews_id(String news_id) {
		this.news_id = news_id;
	}
	public String getTitle() {
		return title == null ? "" : title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContents() {
		return contents == null ? "" : contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public int getView_cnt() {
		return view_cnt;
	}
	public void setView_cnt(int view_cnt) {
		this.view_cnt = view_cnt;
	}
	public int getDel_fl() {
		return del_fl;
	}
	public void setDel_fl(int del_fl) {
		this.del_fl = del_fl;
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
	public String getUpd_id() {
		return upd_id == null ? "" : upd_id;
	}
	public void setUpd_id(String upd_id) {
		this.upd_id = upd_id;
	}
	public Date getUpd_dt() {
		return upd_dt;
	}
	public void setUpd_dt(Date upd_dt) {
		this.upd_dt = upd_dt;
	}
}
