package com.mohaning.app.Model;

import java.sql.Date;

public class MHNP01001VO {	
	private String news_id;
	private String doc_id;
	private String media_id;
	private String portal_id;
	private String portal_nm;
	private String portal_url;
	private String status;
	private int clk_cnt;
	private int del_fl;
	private String reg_id;
	private Date reg_dt;
	public String getNews_id() {
		return news_id == null ? "" : news_id;
	}
	public void setNews_id(String news_id) {
		this.news_id = news_id;
	}
	public String getDoc_id() {
		return doc_id == null ? "" : doc_id;
	}
	public void setDoc_id(String doc_id) {
		this.doc_id = doc_id;
	}
	public String getMedia_id() {
		return media_id == null ? "" : media_id;
	}
	public void setMedia_id(String media_id) {
		this.media_id = media_id;
	}
	public String getPortal_id() {
		return portal_id == null ? "" : portal_id;
	}
	public void setPortal_id(String portal_id) {
		this.portal_id = portal_id;
	}
	public String getPortal_nm() {
		return portal_nm == null ? "" : portal_nm;
	}
	public void setPortal_nm(String portal_nm) {
		this.portal_nm = portal_nm;
	}
	public String getPortal_url() {
		return portal_url == null ? "" : portal_url;
	}
	public void setPortal_url(String portal_url) {
		this.portal_url = portal_url;
	}
	public String getStatus() {
		return status == null ? "" : status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getClk_cnt() {
		return clk_cnt;
	}
	public void setClk_cnt(int clk_cnt) {
		this.clk_cnt = clk_cnt;
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
}
