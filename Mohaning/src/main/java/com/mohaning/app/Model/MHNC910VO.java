package com.mohaning.app.Model;

public class MHNC910VO {	
	private String type_cd;
	private String type_nm;
	private String color;
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
	public String getColor() {
		return color == null ? "" : color;
	}
	public void setColor(String color) {
		this.color = color;
	}
}
