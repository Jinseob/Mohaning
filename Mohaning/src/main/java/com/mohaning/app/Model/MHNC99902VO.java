package com.mohaning.app.Model;

import java.sql.Date;

public class MHNC99902VO {	
	private String media_id;
	private String type;
	private String select_tag;
	private String data_tag;
	private String data_method;
	private String use_tag;
	private String data_pattern;
	private String reg_id;
	private Date reg_dt;
	public String getMedia_id() {
		return media_id == null ? "" : media_id;
	}
	public void setMedia_id(String media_id) {
		this.media_id = media_id;
	}
	public String getType() {
		return type == null ? "" : type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSelect_tag() {
		return select_tag == null ? "" : select_tag;
	}
	public void setSelect_tag(String select_tag) {
		this.select_tag = select_tag;
	}
	public String getData_tag() {
		return data_tag == null ? "" : data_tag;
	}
	public void setData_tag(String data_tag) {
		this.data_tag = data_tag;
	}
	public String getData_method() {
		return data_method == null ? "" : data_method;
	}
	public void setData_method(String data_method) {
		this.data_method = data_method;
	}
	public String getUse_tag() {
		return use_tag == null ? "" : use_tag;
	}
	public void setUse_tag(String use_tag) {
		this.use_tag = use_tag;
	}
	public String getData_pattern() {
		return data_pattern == null ? "" : data_pattern;
	}
	public void setData_pattern(String data_pattern) {
		this.data_pattern = data_pattern;
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
