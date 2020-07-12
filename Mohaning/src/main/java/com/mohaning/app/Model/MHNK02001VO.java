package com.mohaning.app.Model;

import java.sql.Date;

public class MHNK02001VO {
	private String keyword;
	private String status;
	private String reg_id;
	private Date reg_dt;
	private String upt_id;
	private Date upt_dt;
	public String getKeyword() {
		return keyword == null ? "" : keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getStatus() {
		return status == null ? "" : status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	public String getUpt_id() {
		return upt_id == null ? "" : upt_id;
	}
	public void setUpt_id(String upt_id) {
		this.upt_id = upt_id;
	}
	public Date getUpt_dt() {
		return upt_dt;
	}
	public void setUpt_dt(Date upt_dt) {
		this.upt_dt = upt_dt;
	}
}
