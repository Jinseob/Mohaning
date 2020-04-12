package com.mohaning.app.Model;

public class PagingVO {
	private Integer legnth;			// 목록 출력 길이.
	private Integer offset;			// paging 용 위치.
	public int getLegnth() {
		return legnth == null ? 20 : legnth;
	}
	public void setLegnth(int legnth) {
		this.legnth = legnth;
	}
	public int getOffset() {
		return offset == null ? 0 : offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	
}
