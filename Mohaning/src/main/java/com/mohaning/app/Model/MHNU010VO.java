package com.mohaning.app.Model;

import java.sql.Date;

public class MHNU010VO {
	private String user_id;
	private String email;
	private String emailAct;
	private String nickName;
	private String psw;
	private String pswConfirm;
	private String psw_upt_dt;
	private String status;
	private String type;
	private Date reg_dt;
	public String getUser_id() {
		return user_id == null ? "" : user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getEmail() {
		return email == null ? "" : email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmailAct() {
		return emailAct == null ? "" : emailAct;
	}
	public void setEmailAct(String emailAct) {
		this.emailAct = emailAct;
	}
	public String getNickName() {
		return nickName == null ? "" : nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getPsw() {
		return psw == null ? "" : psw;
	}
	public void setPsw(String psw) {
		this.psw = psw;
	}
	public String getPswConfirm() {
		return pswConfirm == null ? "" : pswConfirm;
	}
	public void setPswConfirm(String pswConfirm) {
		this.pswConfirm = pswConfirm;
	}
	public String getPsw_upt_dt() {
		return psw_upt_dt == null ? "" : psw_upt_dt;
	}
	public void setPsw_upt_dt(String psw_upt_dt) {
		this.psw_upt_dt = psw_upt_dt;
	}
	public String getStatus() {
		return status == null ? "" : status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getType() {
		return type == null ? "" : type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Date getReg_dt() {
		return reg_dt;
	}
	public void setReg_dt(Date reg_dt) {
		this.reg_dt = reg_dt;
	}
}
