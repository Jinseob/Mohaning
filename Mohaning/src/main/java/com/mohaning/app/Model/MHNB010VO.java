package com.mohaning.app.Model;

public class MHNB010VO extends MHNN01001VO{	
	private String board_id;
	private String title;
	private String contents;
//	private String news_id;
//	private String status;	// S : Success, E : Error, N : New, AL : Already
//	private int view_cnt;
//	private int del_fl;
//	private String reg_id;
//	private Date reg_dt;
//	private String upd_id;
//	private Date upd_dt;
	public String getBoard_id() {
		return board_id == null ? "" : board_id;
	}
	public void setBoard_id(String board_id) {
		this.board_id = board_id;
	}
	public String getTitle() {
		return title == null ? "" : title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContents() {
		return contents == null ? "" : contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
}
