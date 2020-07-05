package com.mohaning.app.Model;

import java.sql.Date;

public class MHNK01001VO {
	private String key_id;
	private String keyword;
	private String reg_ip;
	private String reg_id;
	private Date reg_dt;
	public String getKey_id() {
		return key_id == null ? "" : key_id;
	}
	public void setKey_id(String key_id) {
		this.key_id = key_id;
	}
	public String getKeyword() {
		return keyword == null ? "" : keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getReg_ip() {
		return reg_ip == null ? "" : reg_ip;
	}
	public void setReg_ip(String reg_ip) {
		this.reg_ip = reg_ip;
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
