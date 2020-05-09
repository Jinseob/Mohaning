package com.mohaning.app.Model;

import java.sql.Date;
import java.util.List;

public class MHND010VO extends MHNC910VO{
	private String news_id;
	private String author_id;
	private String media_id;
	private Integer score;
	private Integer total;
	private Float ratio;
	private List<String> scoreList;
	private String status;
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
	public Integer getScore() {
		return score == null ? 0 : score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public Float getRatio() {
		return ratio;
	}
	public void setRatio(Float ratio) {
		this.ratio = ratio;
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
	public String getStatus() {
		return status == null ? "" : status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
