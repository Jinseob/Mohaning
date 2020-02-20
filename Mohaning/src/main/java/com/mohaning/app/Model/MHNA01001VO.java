package com.mohaning.app.Model;

import java.sql.Date;

public class MHNA01001VO {
	private String author_id;
	private String media_id;
	private String author_nm;
	private String author_email;
	private String reg_id;
	private Date reg_dt;
	public String getAuthor_id() {
		return author_id == null ? "" : author_id;
	}
	public void setAuthor_id(String author_id) {
		this.author_id = author_id;
	}
	public String getMedia_id() {
		return media_id == null ? "" : media_id;
	}
	public void setMedia_id(String media_id) {
		this.media_id = media_id;
	}
	public String getAuthor_nm() {
		return author_nm == null ? "" : author_nm;
	}
	public void setAuthor_nm(String author_nm) {
		this.author_nm = author_nm;
	}
	public String getAuthor_email() {
		return author_email == null ? "" : author_email;
	}
	public void setAuthor_email(String author_email) {
		this.author_email = author_email;
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
