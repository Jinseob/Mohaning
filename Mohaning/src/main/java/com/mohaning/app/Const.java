package com.mohaning.app;

public class Const {
	
	// 처리 상태 코드.
	public final static String STATUS10 = "10";		// 임시 저장
	public final static String STATUS20 = "20";		// 저장 완료
	public final static String STATUS30 = "30";		// 필수 확인
	public final static String STATUS40 = "40";		// 선택 확인
	public final static String STATUS50 = "50";		// 확인중
	public final static String STATUS60 = "60";
	public final static String STATUS70 = "70";
	public final static String STATUS80 = "80";		
	public final static String STATUS90 = "90";		// 완료
	public final static String STATUS99 = "99";		// 반려
	
	// 오류 코드.
	public final static String ERROR10 	= "E10";		// First Check Error. News_url
	public final static String ERROR20 	= "E20";		// Second Check Error. News_title, News_contents, Media_nm, Author_nm, Author_email 
	public final static String ERROR30 	= "E30";		// Etc Check Error. Doc_id, Media_id, Media_url, Author_id
	public final static String ERROR40 	= "E40";		// Portal Check Error. Portal_id, Portal_url
	
	public final static String ERROR50 	= "E50";		// Insert Error.
	public final static String ERROR60 	= "E60";		// Select Error.
	
	
	// 메뉴 타입
	public final static String NEWS 	= "N";			// News 타입
	public final static String BOARD 	= "B";			// Board 타입
	public final static String AUTHOR 	= "A";			// Author 타입
	public final static String MEDIA 	= "M";			// Media 타입
}
