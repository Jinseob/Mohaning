package com.mohaning.app.Model;

import java.util.Date;

public class SearchOptionVO {
	private String news_id;
	private String val1;
	private String val2;
	private String val3;
	private Date fDate;
	private Date tDate;
	private String[] list;
	public String getNews_id() {
		return news_id == null ? "" : news_id;
	}
	public void setNews_id(String news_id) {
		this.news_id = news_id;
	}
	public String getVal1() {
		return val1 == null ? "" : val1;
	}
	public void setVal1(String val1) {
		this.val1 = val1;
	}
	public String getVal2() {
		return val2 == null ? "" : val2;
	}
	public void setVal2(String val2) {
		this.val2 = val2;
	}
	public String getVal3() {
		return val3 == null ? "" : val3;
	}
	public void setVal3(String val3) {
		this.val3 = val3;
	}
	public Date getfDate() {
		return fDate;
	}
	public void setfDate(Date fDate) {
		this.fDate = fDate;
	}
	public Date gettDate() {
		return tDate;
	}
	public void settDate(Date tDate) {
		this.tDate = tDate;
	}
	public String[] getList() {
		return list;
	}
	public void setList(String[] list) {
		this.list = list;
	}
}
