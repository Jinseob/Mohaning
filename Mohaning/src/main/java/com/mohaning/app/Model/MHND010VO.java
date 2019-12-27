package com.mohaning.app.Model;

import java.sql.Date;
import java.util.List;

public class MHND010VO {
	private String news_id;
	private String author_id;
	private String media_id;
	private String origin_media_id;
	private String type_cd;
	private String type_nm;
	private Integer score;
	private List<String> scoreList;
	private String reg_id;
	private Date reg_dt;
	public String getNews_id() {
		return news_id == null ? "" : news_id;
	}
	public void setNews_id(String news_id) {
		this.news_id = news_id;
	}
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
	public String getOrigin_media_id() {
		return origin_media_id == null ? "" : origin_media_id;
	}
	public void setOrigin_media_id(String origin_media_id) {
		this.origin_media_id = origin_media_id;
	}
	public String getType_cd() {
		return type_cd == null ? "" : type_cd;
	}
	public void setType_cd(String type_cd) {
		this.type_cd = type_cd;
	}
	public String getType_nm() {
		return type_nm == null ? "" : type_nm;
	}
	public void setType_nm(String type_nm) {
		this.type_nm = type_nm;
	}
	public Integer getScore() {
		return score == null ? 0 : score;
	}
	public void setScore(Integer score) {
		this.score = score;
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
	public List<String> getScoreList() {
		return scoreList;
	}
	public void setScoreList(List<String> scoreList) {
		this.scoreList = scoreList;
	}
}
