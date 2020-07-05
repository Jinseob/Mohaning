package com.mohaning.app;

public class Const {
	
	// Main Keyword 용
	public final static Integer keylistlength 		= 6;	// Page 당 건수.
	
	// Paging 용.
	public final static Integer length 		= 10;	// Page 당 건수.
	public final static Integer totalpage 	= 10;	// 목록 화면에 선택할 수 있는 Page 갯 수.
	
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
	
	// 상태 코드
	public final static String STATUSAL = "AL"; 	// Already Registerd.
	
	// 오류 코드.
	public final static String ERROR10 	= "E10";		// First Check Error. News_url
	public final static String ERROR20 	= "E20";		// Second Check Error. News_title, News_contents, Media_nm, Author_nm, Author_email 
	public final static String ERROR30 	= "E30";		// Etc Check Error. Doc_id, Media_id, Media_url, Author_id
	public final static String ERROR40 	= "E40";		// Portal Check Error. Portal_id, Portal_url
	
	public final static String ERROR50 	= "E50";		// Insert Error.
	public final static String ERROR60 	= "E60";		// Select Error.
	
	
	// 메세지
	public final static String MSG10 = "등록 되었습니다.\n관리자 점검 후 기사 메뉴에서 확인 하실 수 있습니다.";
	public final static String MSG20 = "등록 되었습니다.\n관리자 점검 후 기사 메뉴에서 확인 하실 수 있습니다.";
	public final static String MSG50 = "등록이 실패하였습니다. 잠시 후 다시 등록해주세요.";
	
	// 메뉴 타입
	public final static String NEWS 	= "N";			// News 타입
	public final static String BOARD 	= "B";			// Board 타입
	public final static String AUTHOR 	= "A";			// Author 타입
	public final static String MEDIA 	= "M";			// Media 타입
	
	public final static String MIX		= "MIX";
	public final static String TIT		= "TIT";
	public final static String CON		= "CON";
	public final static String ATH		= "ATH";
	public final static String MED		= "MED";
	public final static String KEY		= "KEY";
}
