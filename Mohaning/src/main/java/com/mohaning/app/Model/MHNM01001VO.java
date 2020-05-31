package com.mohaning.app.Model;

public class MHNM01001VO {
	private String id;
	private String type;
	private String status;
	private String log;
	public String getId() {
		return id == null ? "" : id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getType() {
		return type == null ? "" : type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getStatus() {
		return status == null ? "" : status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getLog() {
		return log == null ? "" : log;
	}
	public void setLog(String log) {
		this.log = log;
	}
}
