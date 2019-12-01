package com.mohaning.app.Model;

import java.sql.Date;

public class MHNC99902VO {	
	private String media_id;
	private String type;
	private String selection;
	private String data;
	private String method;
	private String use_yn;
	private String use_url;
	private String pattern;
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
	public String getSelection() {
		return selection == null ? "" : selection;
	}
	public void setSelection(String selection) {
		this.selection = selection;
	}
	public String getData() {
		return data == null ? "" : data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getMethod() {
		return method == null ? "" : method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getUse_yn() {
		return use_yn == null ? "" : use_yn;
	}
	public void setUse_yn(String use_yn) {
		this.use_yn = use_yn;
	}
	public String getUse_url() {
		return use_url == null ? "" : use_url;
	}
	public void setUse_url(String use_url) {
		this.use_url = use_url;
	}
	public String getPattern() {
		return pattern == null ? "" : pattern;
	}
	public void setPattern(String pattern) {
		this.pattern = pattern;
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
