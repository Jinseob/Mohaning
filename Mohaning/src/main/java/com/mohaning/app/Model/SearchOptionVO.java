package com.mohaning.app.Model;

public class SearchOptionVO extends PagingVO{
	// ID 용
	private String aid;			// 모든 ID 용
	private String news_id;		// 기사
	private String board_id;	// 토른
	private String media_id;	// 언론사
	private String author_id;	// 기자
	
	// 검색용
	private String type;	// 제목 = TIT, 내용 = CON, 제목+내용 = MIX, 기자 = ATH, 언론사 = MED 
	private String val;		// 검색값
	
	// 정렬용
	private String sort;	// 등록일 높은순/낮은순 = 01/02, 조회수 높은순/낮은순 = 03/04, 평가수 높은순/낮은순 = 05/06
	
	// 라인수
	private int line;	// 원하는 라인 수 만큼 출력 
	
	// IP Address.
	private String ip;
	
	// Keyword
	private String keyword;
	
	public String getAid() {
		return aid == null ? "" : aid;
	}

	public void setAid(String aid) {
		this.aid = aid;
	}

	public String getNews_id() {
		return news_id == null ? "" : news_id;
	}

	public void setNews_id(String news_id) {
		this.news_id = news_id;
	}

	public String getBoard_id() {
		return board_id == null ? "" : board_id;
	}

	public void setBoard_id(String board_id) {
		this.board_id = board_id;
	}

	public String getMedia_id() {
		return media_id == null ? "" : media_id;
	}

	public void setMedia_id(String media_id) {
		this.media_id = media_id;
	}

	public String getAuthor_id() {
		return author_id == null ? "" : author_id;
	}

	public void setAuthor_id(String author_id) {
		this.author_id = author_id;
	}

	public String getType() {
		return type == null ? "" : type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getVal() {
		return val == null ? "" : val;
	}

	public void setVal(String val) {
		this.val = val;
	}

	public String getSort() {
		return sort == null ? "" : sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public int getLine() {
		return line;
	}

	public void setLine(int line) {
		this.line = line;
	}

	public String getIp() {
		return ip == null ? "" : ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getKeyword() {
		return keyword == null ? "" : keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
}
