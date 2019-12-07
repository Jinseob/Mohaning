package com.mohaning.app.Model;

import java.sql.Date;

public class MHNA010VO {	
	private String news_id;
	private String doc_id;
	private String title;
	private String content;
	private String news_url;
	private String media_id;
	private String media_nm;
	private String media_url;
	private String author_id;
	private String author_nm;
	private String author_email;
	private int view_cnt;
	private int del_fl;
	private String reg_id;
	private Date reg_dt;
	private String upd_id;
	private Date upd_dt;
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
	public String getTitle() {
		return title == null ? "" : title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content == null ? "" : content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getNews_url() {
		return news_url == null ? "" : news_url;
	}
	public void setNews_url(String news_url) {
		this.news_url = news_url;
	}
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
	public String getAuthor_id() {
		return author_id == null ? "" : author_id;
	}
	public void setAuthor_id(String author_id) {
		this.author_id = author_id;
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
