package com.mohaning.app.Model;

import com.mohaning.app.Const;

public class PagingVO {
	private Integer length;			// 목록 출력 길이.
	private Integer offset;			// paging 용 위치.
	private Integer pageoffset;		// 한 화면에 총 건수 기준 offset.
	private Integer page;			// 현재 Page.
	private Integer idx;			// Index.
	private Integer unitpage;			// Index. 
	
	public Integer getLength() {
		return length == null ? Const.length : length;
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
	public Integer getPage() {
		return page == null ? 1 : page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getPageoffset() {
		return pageoffset == null ? 0 : pageoffset;
	}
	public void setPageoffset(Integer pageoffset) {
		this.pageoffset = pageoffset;
	}
	public Integer getIdx() {
		return idx == null ? 1 : idx;
	}
	public void setIdx(Integer idx) {
		this.idx = idx;
	}
	public Integer getUnitpage() {
		return unitpage == null ? 0 : unitpage;
	}
	public void setUnitpage(Integer unitpage) {
		this.unitpage = unitpage;
	}
}
