package com.mohaning.app.Model;

import java.sql.Date;
import java.util.List;

import org.json.simple.JSONArray;

public class MHNC999VO {	
	private String media_id;
	private String media_nm;
	private String media_url;
	private JSONArray scoreList;
	private String reg_id;
	private Date reg_dt;
	public String getMedia_id() {
		return media_id == null ? "" : media_id;
	}
	public void setMedia_id(String media_id) {
		this.media_id = media_id;
	}
	public String getMedia_nm() {
		return media_nm == null ? "" : media_nm;
	}
	public void setMedia_nm(String media_nm) {
		this.media_nm = media_nm;
	}
	public String getMedia_url() {
		return media_url == null ? "" : media_url;
	}
	public void setMedia_url(String media_url) {
		this.media_url = media_url;
	}
	public JSONArray getScoreList() {
		return scoreList;
	}
	public void setScoreList(JSONArray scoreList) {
		this.scoreList = scoreList;
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
