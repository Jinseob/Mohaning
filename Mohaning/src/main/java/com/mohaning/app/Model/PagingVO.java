package com.mohaning.app.Model;

public class PagingVO {
	private Integer length;			// 목록 출력 길이.
	private Integer offset;			// paging 용 위치.
	
	public Integer getLength() {
		return length == null ? 20 : length;
	}
	public void setLength(Integer length) {
		this.length = length;
	}
	public void setOffset(Integer offset) {
		this.offset = offset;
	}
	public int getOffset() {
		return offset == null ? 0 : offset;
	}
}
