package com.mohaning.app;

import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.mohaning.app.Model.MHNA010VO;
import com.mohaning.app.Model.MHNC99902VO;

public class GetDataController {

	// 0001 : 경향신문
	public MHNA010VO Media_0001(Document document, List<MHNC99902VO> dataCheckList) {
    	MHNA010VO result = new MHNA010VO();
    	
    	MHNC99902VO dataCheck = null;
    	Element dataElement = null;
    	for(int i = 0; i < dataCheckList.size(); i++) {
        	dataCheck = new MHNC99902VO();
        	dataCheck = dataCheckList.get(i);
        	
        	// Title, Content 는 거의 공통.
        	if(dataCheck.getType().equals("TITLE")) {
        		dataElement = document.select(dataCheck.getSelection()).first();
				result.setTitle(dataElement.attr(dataCheck.getData()));
				System.out.println("TitleHtml : " + dataElement.outerHtml());
        	}else if(dataCheck.getType().equals("CONTENTS")) {
        		dataElement = document.select(dataCheck.getSelection()).first();
				result.setContent(dataElement.attr(dataCheck.getData()));
				System.out.println("ContentHtml : " + dataElement.outerHtml());
        	}else if(dataCheck.getType().equals("AUTHOR")) {
        		dataElement = document.select(dataCheck.getSelection()).first();
        		String author = dataElement.attr(dataCheck.getData()).split("/")[0];
				result.setAuthor_nm(author);
        	}else if(dataCheck.getType().equals("EMAIL")) {
        		dataElement = document.select(dataCheck.getSelection()).first();
        		String email = dataElement.attr(dataCheck.getData()).split("/")[1];
				result.setAuthor_email(email);
        	}
        }
    	
    	return result;
    }
	
	// 0002 : 국민일보
	public MHNA010VO Media_0002(Document document, List<MHNC99902VO> dataCheckList) {
    	MHNA010VO result = new MHNA010VO();
    	
    	MHNC99902VO dataCheck = null;
    	Element dataElement = null;
    	for(int i = 0; i < dataCheckList.size(); i++) {
        	dataCheck = new MHNC99902VO();
        	dataCheck = dataCheckList.get(i);
        	
        	// Title, Content 는 거의 공통.
        	if(dataCheck.getType().equals("TITLE")) {
        		dataElement = document.select(dataCheck.getSelection()).first();
				result.setTitle(dataElement.attr(dataCheck.getData()));
				System.out.println("TitleHtml : " + dataElement.outerHtml());
        	}else if(dataCheck.getType().equals("CONTENTS")) {
        		dataElement = document.select(dataCheck.getSelection()).first();
				result.setContent(dataElement.attr(dataCheck.getData()));
				System.out.println("ContentHtml : " + dataElement.outerHtml());
        	}else if(dataCheck.getType().equals("AUTHOR")) {
        		dataElement = document.select(dataCheck.getSelection()).first();
        		String author = dataElement.attr(dataCheck.getData()).split("\\s+")[0];
				result.setAuthor_nm(author);
				System.out.println("AUTHOR : " + dataElement.outerHtml());
        	}else if(dataCheck.getType().equals("EMAIL")) {
        		dataElement = document.select(dataCheck.getSelection()).first();
        		String[] textArray = dataElement.text().split("\\s+");
        		String text = "";
        		for(int j = textArray.length - 1; j >= 0; j--) {
        			text = textArray[j];
        			if(text.indexOf(dataCheck.getPattern()) > -1) {
        				break;
        			}
        		}
        		result.setAuthor_email(text);
        	}
        }
    	
    	return result;
    }
	
	// 0003 : 네이버뉴스 -> 기사 원문 링크를 사용하여 정보 가져오는 로직 사용?
	public MHNA010VO Media_0003(Document document, List<MHNC99902VO> dataCheckList) {
    	MHNA010VO result = new MHNA010VO();
    	
    	MHNC99902VO dataCheck = null;
    	Element dataElement = null;
    	for(int i = 0; i < dataCheckList.size(); i++) {
        	dataCheck = new MHNC99902VO();
        	dataCheck = dataCheckList.get(i);
        	
        	// Title, Content 는 거의 공통.
        	if(dataCheck.getType().equals("TITLE")) {
        		dataElement = document.select(dataCheck.getSelection()).first();
				result.setTitle(dataElement.attr(dataCheck.getData()));
				System.out.println("TitleHtml : " + dataElement.outerHtml());
        	}else if(dataCheck.getType().equals("CONTENTS")) {
        		dataElement = document.select(dataCheck.getSelection()).first();
				result.setContent(dataElement.attr(dataCheck.getData()));
				System.out.println("ContentHtml : " + dataElement.outerHtml());
        	}else if(dataCheck.getType().equals("AUTHOR")) {
        		
        	}else if(dataCheck.getType().equals("EMAIL")) {
        		
        	}
        }
		
		
		// 7. 제목 및 요약 내용 가져오기.
		
		// 8. 기자명 가져오기.
		
		// 9. email 정보 가져오기.
    	
    	return result;
    }

	// 0004 : 노컷뉴스
	public MHNA010VO Media_0004(Document document, List<MHNC99902VO> dataCheckList) {
    	MHNA010VO result = new MHNA010VO();
    	
    	MHNC99902VO dataCheck = null;
    	Element dataElement = null;
    	for(int i = 0; i < dataCheckList.size(); i++) {
        	dataCheck = new MHNC99902VO();
        	dataCheck = dataCheckList.get(i);
        	
        	// Title, Content 는 거의 공통.
        	if(dataCheck.getType().equals("TITLE")) {
        		dataElement = document.select(dataCheck.getSelection()).first();
				result.setTitle(dataElement.attr(dataCheck.getData()));
				System.out.println("TitleHtml : " + dataElement.outerHtml());
        	}else if(dataCheck.getType().equals("CONTENTS")) {
        		dataElement = document.select(dataCheck.getSelection()).first();
				result.setContent(dataElement.attr(dataCheck.getData()));
				System.out.println("ContentHtml : " + dataElement.outerHtml());
        	}else if(dataCheck.getType().equals("AUTHOR")) {
        		dataElement = document.select(dataCheck.getSelection()).first();
        		String author = dataElement.attr(dataCheck.getData()).split("\\s+")[1];
				result.setAuthor_nm(author);
        	}else if(dataCheck.getType().equals("EMAIL")) {
        		dataElement = document.select(dataCheck.getSelection()).first();
				result.setAuthor_email(dataElement.attr(dataCheck.getData()));
        	}
        }
		
    	return result;
    }

	// 0005 : 뉴스1
	public MHNA010VO Media_0005(Document document, List<MHNC99902VO> dataCheckList) {
    	MHNA010VO result = new MHNA010VO();
    	
    	MHNC99902VO dataCheck = null;
    	Element dataElement = null;
    	for(int i = 0; i < dataCheckList.size(); i++) {
        	dataCheck = new MHNC99902VO();
        	dataCheck = dataCheckList.get(i);
        	
        	// Title, Content 는 거의 공통.
        	if(dataCheck.getType().equals("TITLE")) {
        		dataElement = document.select(dataCheck.getSelection()).first();
				result.setTitle(dataElement.attr(dataCheck.getData()));
				System.out.println("TitleHtml : " + dataElement.outerHtml());
        	}else if(dataCheck.getType().equals("CONTENTS")) {
        		dataElement = document.select(dataCheck.getSelection()).first();
				result.setContent(dataElement.attr(dataCheck.getData()));
				System.out.println("ContentHtml : " + dataElement.outerHtml());
        	}else if(dataCheck.getType().equals("AUTHOR")) {
        		dataElement = document.select(dataCheck.getSelection()).first();
        		String[] textArray = dataElement.outerHtml().split("\\n");
        		String text = "";
        		for(int j = 0; j < textArray.length; j++) {
        			text = textArray[j];
        			if(text.indexOf(dataCheck.getPattern()) > -1) {
        				break;
        			}
        		}
        		String[] authorArr = text.split("\\s+");
        		String author = authorArr[authorArr.length - 2];
        		result.setAuthor_nm(author);
        	}else if(dataCheck.getType().equals("EMAIL")) {
        		dataElement = document.select(dataCheck.getSelection()).first();
        		String[] textArray = dataElement.text().split("\\s+");
        		String text = "";
        		for(int j = textArray.length - 1; j >= 0; j--) {
        			text = textArray[j];
        			if(text.indexOf(dataCheck.getPattern()) > -1) {
        				break;
        			}
        		}
        		result.setAuthor_email(text);
        	}
        }
		
    	return result;
    }

	// 0006 : 뉴시스
	public MHNA010VO Media_0006(Document document, List<MHNC99902VO> dataCheckList) {
    	MHNA010VO result = new MHNA010VO();
    	
    	MHNC99902VO dataCheck = null;
    	Element dataElement = null;
    	for(int i = 0; i < dataCheckList.size(); i++) {
        	dataCheck = new MHNC99902VO();
        	dataCheck = dataCheckList.get(i);
        	
        	// Title, Content 는 거의 공통.
        	if(dataCheck.getType().equals("TITLE")) {
        		dataElement = document.select(dataCheck.getSelection()).first();
				result.setTitle(dataElement.attr(dataCheck.getData()));
				System.out.println("TitleHtml : " + dataElement.outerHtml());
        	}else if(dataCheck.getType().equals("CONTENTS")) {
        		dataElement = document.select(dataCheck.getSelection()).first();
				result.setContent(dataElement.attr(dataCheck.getData()));
				System.out.println("ContentHtml : " + dataElement.outerHtml());
        	}else if(dataCheck.getType().equals("AUTHOR")) {
        		
        	}else if(dataCheck.getType().equals("EMAIL")) {
        		if(dataCheck.getUse_yn().equals("Y")) {
        			System.out.println("aaaaaaaa" + document.text());
        			System.out.println("aaaaaaaa" + document.text("@kyunghyang.com"));
        			System.out.println("aaaaaaaa" + document.data());
        			System.out.println("aaaaaaaa" + document.ownText());
        			System.out.println("aaaaaaaa" + document.body());
        		}
        	}
        }
		
		
		// 7. 제목 및 요약 내용 가져오기.
		
		// 8. 기자명 가져오기.
		
		// 9. email 정보 가져오기.
    	
    	return result;
    }

	// 0007 : 다음뉴스
	public MHNA010VO Media_0007(Document document, List<MHNC99902VO> dataCheckList) {
    	MHNA010VO result = new MHNA010VO();
    	
    	MHNC99902VO dataCheck = null;
    	Element dataElement = null;
    	for(int i = 0; i < dataCheckList.size(); i++) {
        	dataCheck = new MHNC99902VO();
        	dataCheck = dataCheckList.get(i);
        	
        	// Title, Content 는 거의 공통.
        	if(dataCheck.getType().equals("TITLE")) {
        		dataElement = document.select(dataCheck.getSelection()).first();
				result.setTitle(dataElement.attr(dataCheck.getData()));
				System.out.println("TitleHtml : " + dataElement.outerHtml());
        	}else if(dataCheck.getType().equals("CONTENTS")) {
        		dataElement = document.select(dataCheck.getSelection()).first();
				result.setContent(dataElement.attr(dataCheck.getData()));
				System.out.println("ContentHtml : " + dataElement.outerHtml());
        	}else if(dataCheck.getType().equals("AUTHOR")) {
        		
        	}else if(dataCheck.getType().equals("EMAIL")) {
        		if(dataCheck.getUse_yn().equals("Y")) {
        			System.out.println("aaaaaaaa" + document.text());
        			System.out.println("aaaaaaaa" + document.text("@kyunghyang.com"));
        			System.out.println("aaaaaaaa" + document.data());
        			System.out.println("aaaaaaaa" + document.ownText());
        			System.out.println("aaaaaaaa" + document.body());
        		}
        	}
        }
		
		
		// 7. 제목 및 요약 내용 가져오기.
		
		// 8. 기자명 가져오기.
		
		// 9. email 정보 가져오기.
    	
    	return result;
    }

	// 0008 : 동아
	public MHNA010VO Media_0008(Document document, List<MHNC99902VO> dataCheckList) {
    	MHNA010VO result = new MHNA010VO();
    	
    	MHNC99902VO dataCheck = null;
    	Element dataElement = null;
    	for(int i = 0; i < dataCheckList.size(); i++) {
        	dataCheck = new MHNC99902VO();
        	dataCheck = dataCheckList.get(i);

        	// Title, Content 는 거의 공통.
        	if(dataCheck.getType().equals("TITLE")) {
        		dataElement = document.select(dataCheck.getSelection()).first();
				result.setTitle(dataElement.attr(dataCheck.getData()));
				System.out.println("TitleHtml : " + dataElement.outerHtml());
        	}else if(dataCheck.getType().equals("CONTENTS")) {
        		dataElement = document.select(dataCheck.getSelection()).first();
				result.setContent(dataElement.attr(dataCheck.getData()));
				System.out.println("ContentHtml : " + dataElement.outerHtml());
        	}else if(dataCheck.getType().equals("AUTHOR")) {
        		
        	}else if(dataCheck.getType().equals("EMAIL")) {
        		if(dataCheck.getUse_yn().equals("Y")) {
        			System.out.println("aaaaaaaa" + document.text());
        			System.out.println("aaaaaaaa" + document.text("@kyunghyang.com"));
        			System.out.println("aaaaaaaa" + document.data());
        			System.out.println("aaaaaaaa" + document.ownText());
        			System.out.println("aaaaaaaa" + document.body());
        		}
        	}
        }
		
		
		// 7. 제목 및 요약 내용 가져오기.
		
		// 8. 기자명 가져오기.
		
		// 9. email 정보 가져오기.
    	
    	return result;
    }

	// 0009 : 디지털타임스
	public MHNA010VO Media_0009(Document document, List<MHNC99902VO> dataCheckList) {
    	MHNA010VO result = new MHNA010VO();
    	
    	MHNC99902VO dataCheck = null;
    	Element dataElement = null;
    	for(int i = 0; i < dataCheckList.size(); i++) {
        	dataCheck = new MHNC99902VO();
        	dataCheck = dataCheckList.get(i);
        	
        	// Title, Content 는 거의 공통.
        	if(dataCheck.getType().equals("TITLE")) {
        		dataElement = document.select(dataCheck.getSelection()).first();
				result.setTitle(dataElement.attr(dataCheck.getData()));
				System.out.println("TitleHtml : " + dataElement.outerHtml());
        	}else if(dataCheck.getType().equals("CONTENTS")) {
        		dataElement = document.select(dataCheck.getSelection()).first();
				result.setContent(dataElement.attr(dataCheck.getData()));
				System.out.println("ContentHtml : " + dataElement.outerHtml());
        	}else if(dataCheck.getType().equals("AUTHOR")) {
        		
        	}else if(dataCheck.getType().equals("EMAIL")) {
        		if(dataCheck.getUse_yn().equals("Y")) {
        			System.out.println("aaaaaaaa" + document.text());
        			System.out.println("aaaaaaaa" + document.text("@kyunghyang.com"));
        			System.out.println("aaaaaaaa" + document.data());
        			System.out.println("aaaaaaaa" + document.ownText());
        			System.out.println("aaaaaaaa" + document.body());
        		}
        	}
        }
		
		
		// 7. 제목 및 요약 내용 가져오기.
		
		// 8. 기자명 가져오기.
		
		// 9. email 정보 가져오기.
    	
    	return result;
    }

	// 0010 : 머니투데이
	public MHNA010VO Media_0010(Document document, List<MHNC99902VO> dataCheckList) {
    	MHNA010VO result = new MHNA010VO();
    	
    	MHNC99902VO dataCheck = null;
    	Element dataElement = null;
    	for(int i = 0; i < dataCheckList.size(); i++) {
        	dataCheck = new MHNC99902VO();
        	dataCheck = dataCheckList.get(i);
        	
        	// Title, Content 는 거의 공통.
        	if(dataCheck.getType().equals("TITLE")) {
        		dataElement = document.select(dataCheck.getSelection()).first();
				result.setTitle(dataElement.attr(dataCheck.getData()));
				System.out.println("TitleHtml : " + dataElement.outerHtml());
        	}else if(dataCheck.getType().equals("CONTENTS")) {
        		dataElement = document.select(dataCheck.getSelection()).first();
				result.setContent(dataElement.attr(dataCheck.getData()));
				System.out.println("ContentHtml : " + dataElement.outerHtml());
        	}else if(dataCheck.getType().equals("AUTHOR")) {
        		
        	}else if(dataCheck.getType().equals("EMAIL")) {
        		if(dataCheck.getUse_yn().equals("Y")) {
        			System.out.println("aaaaaaaa" + document.text());
        			System.out.println("aaaaaaaa" + document.text("@kyunghyang.com"));
        			System.out.println("aaaaaaaa" + document.data());
        			System.out.println("aaaaaaaa" + document.ownText());
        			System.out.println("aaaaaaaa" + document.body());
        		}
        	}
        }
		
		
		// 7. 제목 및 요약 내용 가져오기.
		
		// 8. 기자명 가져오기.
		
		// 9. email 정보 가져오기.
    	
    	return result;
    }

	// 0011 : 서울신문
	public MHNA010VO Media_0011(Document document, List<MHNC99902VO> dataCheckList) {
    	MHNA010VO result = new MHNA010VO();
    	
    	MHNC99902VO dataCheck = null;
    	Element dataElement = null;
    	for(int i = 0; i < dataCheckList.size(); i++) {
        	dataCheck = new MHNC99902VO();
        	dataCheck = dataCheckList.get(i);
        	
        	// Title, Content 는 거의 공통.
        	if(dataCheck.getType().equals("TITLE")) {
        		dataElement = document.select(dataCheck.getSelection()).first();
				result.setTitle(dataElement.attr(dataCheck.getData()));
				System.out.println("TitleHtml : " + dataElement.outerHtml());
        	}else if(dataCheck.getType().equals("CONTENTS")) {
        		dataElement = document.select(dataCheck.getSelection()).first();
				result.setContent(dataElement.attr(dataCheck.getData()));
				System.out.println("ContentHtml : " + dataElement.outerHtml());
        	}else if(dataCheck.getType().equals("AUTHOR")) {
        		
        	}else if(dataCheck.getType().equals("EMAIL")) {
        		if(dataCheck.getUse_yn().equals("Y")) {
        			System.out.println("aaaaaaaa" + document.text());
        			System.out.println("aaaaaaaa" + document.text("@kyunghyang.com"));
        			System.out.println("aaaaaaaa" + document.data());
        			System.out.println("aaaaaaaa" + document.ownText());
        			System.out.println("aaaaaaaa" + document.body());
        		}
        	}
        }
		
		
		// 7. 제목 및 요약 내용 가져오기.
		
		// 8. 기자명 가져오기.
		
		// 9. email 정보 가져오기.
    	
    	return result;
    }

	// 0012 : 세계일보
	public MHNA010VO Media_0012(Document document, List<MHNC99902VO> dataCheckList) {
    	MHNA010VO result = new MHNA010VO();
    	
    	MHNC99902VO dataCheck = null;
    	Element dataElement = null;
    	for(int i = 0; i < dataCheckList.size(); i++) {
        	dataCheck = new MHNC99902VO();
        	dataCheck = dataCheckList.get(i);
        	
        	// Title, Content 는 거의 공통.
        	if(dataCheck.getType().equals("TITLE")) {
        		dataElement = document.select(dataCheck.getSelection()).first();
				result.setTitle(dataElement.attr(dataCheck.getData()));
				System.out.println("TitleHtml : " + dataElement.outerHtml());
        	}else if(dataCheck.getType().equals("CONTENTS")) {
        		dataElement = document.select(dataCheck.getSelection()).first();
				result.setContent(dataElement.attr(dataCheck.getData()));
				System.out.println("ContentHtml : " + dataElement.outerHtml());
        	}else if(dataCheck.getType().equals("AUTHOR")) {
        		
        	}else if(dataCheck.getType().equals("EMAIL")) {
        		if(dataCheck.getUse_yn().equals("Y")) {
        			System.out.println("aaaaaaaa" + document.text());
        			System.out.println("aaaaaaaa" + document.text("@kyunghyang.com"));
        			System.out.println("aaaaaaaa" + document.data());
        			System.out.println("aaaaaaaa" + document.ownText());
        			System.out.println("aaaaaaaa" + document.body());
        		}
        	}
        }
		
		
		// 7. 제목 및 요약 내용 가져오기.
		
		// 8. 기자명 가져오기.
		
		// 9. email 정보 가져오기.
    	
    	return result;
    }

	// 0013 : 아시아경제
	public MHNA010VO Media_0013(Document document, List<MHNC99902VO> dataCheckList) {
    	MHNA010VO result = new MHNA010VO();
    	
    	MHNC99902VO dataCheck = null;
    	Element dataElement = null;
    	for(int i = 0; i < dataCheckList.size(); i++) {
        	dataCheck = new MHNC99902VO();
        	dataCheck = dataCheckList.get(i);
        	
        	// Title, Content 는 거의 공통.
        	if(dataCheck.getType().equals("TITLE")) {
        		dataElement = document.select(dataCheck.getSelection()).first();
				result.setTitle(dataElement.attr(dataCheck.getData()));
				System.out.println("TitleHtml : " + dataElement.outerHtml());
        	}else if(dataCheck.getType().equals("CONTENTS")) {
        		dataElement = document.select(dataCheck.getSelection()).first();
				result.setContent(dataElement.attr(dataCheck.getData()));
				System.out.println("ContentHtml : " + dataElement.outerHtml());
        	}else if(dataCheck.getType().equals("AUTHOR")) {
        		
        	}else if(dataCheck.getType().equals("EMAIL")) {
        		if(dataCheck.getUse_yn().equals("Y")) {
        			System.out.println("aaaaaaaa" + document.text());
        			System.out.println("aaaaaaaa" + document.text("@kyunghyang.com"));
        			System.out.println("aaaaaaaa" + document.data());
        			System.out.println("aaaaaaaa" + document.ownText());
        			System.out.println("aaaaaaaa" + document.body());
        		}
        	}
        }
		
		
		// 7. 제목 및 요약 내용 가져오기.
		
		// 8. 기자명 가져오기.
		
		// 9. email 정보 가져오기.
    	
    	return result;
    }

	// 0014 : 연합뉴스
	public MHNA010VO Media_0014(Document document, List<MHNC99902VO> dataCheckList) {
    	MHNA010VO result = new MHNA010VO();
    	
    	MHNC99902VO dataCheck = null;
    	Element dataElement = null;
    	for(int i = 0; i < dataCheckList.size(); i++) {
        	dataCheck = new MHNC99902VO();
        	dataCheck = dataCheckList.get(i);
        	
        	// Title, Content 는 거의 공통.
        	if(dataCheck.getType().equals("TITLE")) {
        		dataElement = document.select(dataCheck.getSelection()).first();
				result.setTitle(dataElement.attr(dataCheck.getData()));
				System.out.println("TitleHtml : " + dataElement.outerHtml());
        	}else if(dataCheck.getType().equals("CONTENTS")) {
        		dataElement = document.select(dataCheck.getSelection()).first();
				result.setContent(dataElement.attr(dataCheck.getData()));
				System.out.println("ContentHtml : " + dataElement.outerHtml());
        	}else if(dataCheck.getType().equals("AUTHOR")) {
        		
        	}else if(dataCheck.getType().equals("EMAIL")) {
        		if(dataCheck.getUse_yn().equals("Y")) {
        			System.out.println("aaaaaaaa" + document.text());
        			System.out.println("aaaaaaaa" + document.text("@kyunghyang.com"));
        			System.out.println("aaaaaaaa" + document.data());
        			System.out.println("aaaaaaaa" + document.ownText());
        			System.out.println("aaaaaaaa" + document.body());
        		}
        	}
        }
		
		
		// 7. 제목 및 요약 내용 가져오기.
		
		// 8. 기자명 가져오기.
		
		// 9. email 정보 가져오기.
    	
    	return result;
    }

	// 0015 : 이데일리
	public MHNA010VO Media_0015(Document document, List<MHNC99902VO> dataCheckList) {
    	MHNA010VO result = new MHNA010VO();
    	
    	MHNC99902VO dataCheck = null;
    	Element dataElement = null;
    	for(int i = 0; i < dataCheckList.size(); i++) {
        	dataCheck = new MHNC99902VO();
        	dataCheck = dataCheckList.get(i);
        	
        	// Title, Content 는 거의 공통.
        	if(dataCheck.getType().equals("TITLE")) {
        		dataElement = document.select(dataCheck.getSelection()).first();
				result.setTitle(dataElement.attr(dataCheck.getData()));
				System.out.println("TitleHtml : " + dataElement.outerHtml());
        	}else if(dataCheck.getType().equals("CONTENTS")) {
        		dataElement = document.select(dataCheck.getSelection()).first();
				result.setContent(dataElement.attr(dataCheck.getData()));
				System.out.println("ContentHtml : " + dataElement.outerHtml());
        	}else if(dataCheck.getType().equals("AUTHOR")) {
        		
        	}else if(dataCheck.getType().equals("EMAIL")) {
        		if(dataCheck.getUse_yn().equals("Y")) {
        			System.out.println("aaaaaaaa" + document.text());
        			System.out.println("aaaaaaaa" + document.text("@kyunghyang.com"));
        			System.out.println("aaaaaaaa" + document.data());
        			System.out.println("aaaaaaaa" + document.ownText());
        			System.out.println("aaaaaaaa" + document.body());
        		}
        	}
        }
		
		
		// 7. 제목 및 요약 내용 가져오기.
		
		// 8. 기자명 가져오기.
		
		// 9. email 정보 가져오기.
    	
    	return result;
    }

	// 0016 : 조선일보
	public MHNA010VO Media_0016(Document document, List<MHNC99902VO> dataCheckList) {
    	MHNA010VO result = new MHNA010VO();
    	
    	MHNC99902VO dataCheck = null;
    	Element dataElement = null;
    	for(int i = 0; i < dataCheckList.size(); i++) {
        	dataCheck = new MHNC99902VO();
        	dataCheck = dataCheckList.get(i);
        	
        	// Title, Content 는 거의 공통.
        	if(dataCheck.getType().equals("TITLE")) {
        		dataElement = document.select(dataCheck.getSelection()).first();
				result.setTitle(dataElement.attr(dataCheck.getData()));
				System.out.println("TitleHtml : " + dataElement.outerHtml());
        	}else if(dataCheck.getType().equals("CONTENTS")) {
        		dataElement = document.select(dataCheck.getSelection()).first();
				result.setContent(dataElement.attr(dataCheck.getData()));
				System.out.println("ContentHtml : " + dataElement.outerHtml());
        	}else if(dataCheck.getType().equals("AUTHOR")) {
        		
        	}else if(dataCheck.getType().equals("EMAIL")) {
        		if(dataCheck.getUse_yn().equals("Y")) {
        			System.out.println("aaaaaaaa" + document.text());
        			System.out.println("aaaaaaaa" + document.text("@kyunghyang.com"));
        			System.out.println("aaaaaaaa" + document.data());
        			System.out.println("aaaaaaaa" + document.ownText());
        			System.out.println("aaaaaaaa" + document.body());
        		}
        	}
        }
		
		
		// 7. 제목 및 요약 내용 가져오기.
		
		// 8. 기자명 가져오기.
		
		// 9. email 정보 가져오기.
    	
    	return result;
    }

	// 0017 : 중앙일보
	public MHNA010VO Media_0017(Document document, List<MHNC99902VO> dataCheckList) {
    	MHNA010VO result = new MHNA010VO();
    	
    	MHNC99902VO dataCheck = null;
    	Element dataElement = null;
    	for(int i = 0; i < dataCheckList.size(); i++) {
        	dataCheck = new MHNC99902VO();
        	dataCheck = dataCheckList.get(i);
        	
        	// Title, Content 는 거의 공통.
        	if(dataCheck.getType().equals("TITLE")) {
        		dataElement = document.select(dataCheck.getSelection()).first();
				result.setTitle(dataElement.attr(dataCheck.getData()));
				System.out.println("TitleHtml : " + dataElement.outerHtml());
        	}else if(dataCheck.getType().equals("CONTENTS")) {
        		dataElement = document.select(dataCheck.getSelection()).first();
				result.setContent(dataElement.attr(dataCheck.getData()));
				System.out.println("ContentHtml : " + dataElement.outerHtml());
        	}else if(dataCheck.getType().equals("AUTHOR")) {
        		
        	}else if(dataCheck.getType().equals("EMAIL")) {
        		if(dataCheck.getUse_yn().equals("Y")) {
        			System.out.println("aaaaaaaa" + document.text());
        			System.out.println("aaaaaaaa" + document.text("@kyunghyang.com"));
        			System.out.println("aaaaaaaa" + document.data());
        			System.out.println("aaaaaaaa" + document.ownText());
        			System.out.println("aaaaaaaa" + document.body());
        		}
        	}
        }
		
		
		// 7. 제목 및 요약 내용 가져오기.
		
		// 8. 기자명 가져오기.
		
		// 9. email 정보 가져오기.
    	
    	return result;
    }

	// 0018 : 채널 A
	public MHNA010VO Media_0018(Document document, List<MHNC99902VO> dataCheckList) {
    	MHNA010VO result = new MHNA010VO();
    	
    	MHNC99902VO dataCheck = null;
    	Element dataElement = null;
    	for(int i = 0; i < dataCheckList.size(); i++) {
        	dataCheck = new MHNC99902VO();
        	dataCheck = dataCheckList.get(i);
        	
        	// Title, Content 는 거의 공통.
        	if(dataCheck.getType().equals("TITLE")) {
        		dataElement = document.select(dataCheck.getSelection()).first();
				result.setTitle(dataElement.attr(dataCheck.getData()));
				System.out.println("TitleHtml : " + dataElement.outerHtml());
        	}else if(dataCheck.getType().equals("CONTENTS")) {
        		dataElement = document.select(dataCheck.getSelection()).first();
				result.setContent(dataElement.attr(dataCheck.getData()));
				System.out.println("ContentHtml : " + dataElement.outerHtml());
        	}else if(dataCheck.getType().equals("AUTHOR")) {
        		
        	}else if(dataCheck.getType().equals("EMAIL")) {
        		if(dataCheck.getUse_yn().equals("Y")) {
        			System.out.println("aaaaaaaa" + document.text());
        			System.out.println("aaaaaaaa" + document.text("@kyunghyang.com"));
        			System.out.println("aaaaaaaa" + document.data());
        			System.out.println("aaaaaaaa" + document.ownText());
        			System.out.println("aaaaaaaa" + document.body());
        		}
        	}
        }
		
		
		// 7. 제목 및 요약 내용 가져오기.
		
		// 8. 기자명 가져오기.
		
		// 9. email 정보 가져오기.
    	
    	return result;
    }

	// 0019 : 프레시안
	public MHNA010VO Media_0019(Document document, List<MHNC99902VO> dataCheckList) {
    	MHNA010VO result = new MHNA010VO();
    	
    	MHNC99902VO dataCheck = null;
    	Element dataElement = null;
    	for(int i = 0; i < dataCheckList.size(); i++) {
        	dataCheck = new MHNC99902VO();
        	dataCheck = dataCheckList.get(i);
        	
        	// Title, Content 는 거의 공통.
        	if(dataCheck.getType().equals("TITLE")) {
        		dataElement = document.select(dataCheck.getSelection()).first();
				result.setTitle(dataElement.attr(dataCheck.getData()));
				System.out.println("TitleHtml : " + dataElement.outerHtml());
        	}else if(dataCheck.getType().equals("CONTENTS")) {
        		dataElement = document.select(dataCheck.getSelection()).first();
				result.setContent(dataElement.attr(dataCheck.getData()));
				System.out.println("ContentHtml : " + dataElement.outerHtml());
        	}else if(dataCheck.getType().equals("AUTHOR")) {
        		
        	}else if(dataCheck.getType().equals("EMAIL")) {
        		if(dataCheck.getUse_yn().equals("Y")) {
        			System.out.println("aaaaaaaa" + document.text());
        			System.out.println("aaaaaaaa" + document.text("@kyunghyang.com"));
        			System.out.println("aaaaaaaa" + document.data());
        			System.out.println("aaaaaaaa" + document.ownText());
        			System.out.println("aaaaaaaa" + document.body());
        		}
        	}
        }
		
		
		// 7. 제목 및 요약 내용 가져오기.
		
		// 8. 기자명 가져오기.
		
		// 9. email 정보 가져오기.
    	
    	return result;
    }

	// 0020 : 한겨레
	public MHNA010VO Media_0020(Document document, List<MHNC99902VO> dataCheckList) {
    	MHNA010VO result = new MHNA010VO();
    	
    	MHNC99902VO dataCheck = null;
    	Element dataElement = null;
    	for(int i = 0; i < dataCheckList.size(); i++) {
        	dataCheck = new MHNC99902VO();
        	dataCheck = dataCheckList.get(i);
        	
        	// Title, Content 는 거의 공통.
        	if(dataCheck.getType().equals("TITLE")) {
        		dataElement = document.select(dataCheck.getSelection()).first();
				result.setTitle(dataElement.attr(dataCheck.getData()));
				System.out.println("TitleHtml : " + dataElement.outerHtml());
        	}else if(dataCheck.getType().equals("CONTENTS")) {
        		dataElement = document.select(dataCheck.getSelection()).first();
				result.setContent(dataElement.attr(dataCheck.getData()));
				System.out.println("ContentHtml : " + dataElement.outerHtml());
        	}else if(dataCheck.getType().equals("AUTHOR")) {
        		
        	}else if(dataCheck.getType().equals("EMAIL")) {
        		if(dataCheck.getUse_yn().equals("Y")) {
        			System.out.println("aaaaaaaa" + document.text());
        			System.out.println("aaaaaaaa" + document.text("@kyunghyang.com"));
        			System.out.println("aaaaaaaa" + document.data());
        			System.out.println("aaaaaaaa" + document.ownText());
        			System.out.println("aaaaaaaa" + document.body());
        		}
        	}
        }
		
		
		// 7. 제목 및 요약 내용 가져오기.
		
		// 8. 기자명 가져오기.
		
		// 9. email 정보 가져오기.
    	
    	return result;
    }

	// 0021 : 한국일보
	public MHNA010VO Media_0021(Document document, List<MHNC99902VO> dataCheckList) {
    	MHNA010VO result = new MHNA010VO();
    	
    	MHNC99902VO dataCheck = null;
    	Element dataElement = null;
    	for(int i = 0; i < dataCheckList.size(); i++) {
        	dataCheck = new MHNC99902VO();
        	dataCheck = dataCheckList.get(i);
        	
        	// Title, Content 는 거의 공통.
        	if(dataCheck.getType().equals("TITLE")) {
        		dataElement = document.select(dataCheck.getSelection()).first();
				result.setTitle(dataElement.attr(dataCheck.getData()));
				System.out.println("TitleHtml : " + dataElement.outerHtml());
        	}else if(dataCheck.getType().equals("CONTENTS")) {
        		dataElement = document.select(dataCheck.getSelection()).first();
				result.setContent(dataElement.attr(dataCheck.getData()));
				System.out.println("ContentHtml : " + dataElement.outerHtml());
        	}else if(dataCheck.getType().equals("AUTHOR")) {
        		
        	}else if(dataCheck.getType().equals("EMAIL")) {
        		if(dataCheck.getUse_yn().equals("Y")) {
        			System.out.println("aaaaaaaa" + document.text());
        			System.out.println("aaaaaaaa" + document.text("@kyunghyang.com"));
        			System.out.println("aaaaaaaa" + document.data());
        			System.out.println("aaaaaaaa" + document.ownText());
        			System.out.println("aaaaaaaa" + document.body());
        		}
        	}
        }
		
		
		// 7. 제목 및 요약 내용 가져오기.
		
		// 8. 기자명 가져오기.
		
		// 9. email 정보 가져오기.
    	
    	return result;
    }

	// 0022 : 헤럴드경제
	public MHNA010VO Media_0022(Document document, List<MHNC99902VO> dataCheckList) {
    	MHNA010VO result = new MHNA010VO();
    	
    	MHNC99902VO dataCheck = null;
    	Element dataElement = null;
    	for(int i = 0; i < dataCheckList.size(); i++) {
        	dataCheck = new MHNC99902VO();
        	dataCheck = dataCheckList.get(i);
        	
        	// Title, Content 는 거의 공통.
        	if(dataCheck.getType().equals("TITLE")) {
        		dataElement = document.select(dataCheck.getSelection()).first();
				result.setTitle(dataElement.attr(dataCheck.getData()));
				System.out.println("TitleHtml : " + dataElement.outerHtml());
        	}else if(dataCheck.getType().equals("CONTENTS")) {
        		dataElement = document.select(dataCheck.getSelection()).first();
				result.setContent(dataElement.attr(dataCheck.getData()));
				System.out.println("ContentHtml : " + dataElement.outerHtml());
        	}else if(dataCheck.getType().equals("AUTHOR")) {
        		
        	}else if(dataCheck.getType().equals("EMAIL")) {
        		if(dataCheck.getUse_yn().equals("Y")) {
        			System.out.println("aaaaaaaa" + document.text());
        			System.out.println("aaaaaaaa" + document.text("@kyunghyang.com"));
        			System.out.println("aaaaaaaa" + document.data());
        			System.out.println("aaaaaaaa" + document.ownText());
        			System.out.println("aaaaaaaa" + document.body());
        		}
        	}
        }
		
		
		// 7. 제목 및 요약 내용 가져오기.
		
		// 8. 기자명 가져오기.
		
		// 9. email 정보 가져오기.
    	
    	return result;
    }

	// 0023 : JTBC
	public MHNA010VO Media_0023(Document document, List<MHNC99902VO> dataCheckList) {
    	MHNA010VO result = new MHNA010VO();
    	
    	MHNC99902VO dataCheck = null;
    	Element dataElement = null;
    	for(int i = 0; i < dataCheckList.size(); i++) {
        	dataCheck = new MHNC99902VO();
        	dataCheck = dataCheckList.get(i);
        	
        	// Title, Content 는 거의 공통.
        	if(dataCheck.getType().equals("TITLE")) {
        		dataElement = document.select(dataCheck.getSelection()).first();
				result.setTitle(dataElement.attr(dataCheck.getData()));
				System.out.println("TitleHtml : " + dataElement.outerHtml());
        	}else if(dataCheck.getType().equals("CONTENTS")) {
        		dataElement = document.select(dataCheck.getSelection()).first();
				result.setContent(dataElement.attr(dataCheck.getData()));
				System.out.println("ContentHtml : " + dataElement.outerHtml());
        	}else if(dataCheck.getType().equals("AUTHOR")) {
        		
        	}else if(dataCheck.getType().equals("EMAIL")) {
        		if(dataCheck.getUse_yn().equals("Y")) {
        			System.out.println("aaaaaaaa" + document.text());
        			System.out.println("aaaaaaaa" + document.text("@kyunghyang.com"));
        			System.out.println("aaaaaaaa" + document.data());
        			System.out.println("aaaaaaaa" + document.ownText());
        			System.out.println("aaaaaaaa" + document.body());
        		}
        	}
        }
		
		
		// 7. 제목 및 요약 내용 가져오기.
		
		// 8. 기자명 가져오기.
		
		// 9. email 정보 가져오기.
    	
    	return result;
    }

	// 0024 : KBS
	public MHNA010VO Media_0024(Document document, List<MHNC99902VO> dataCheckList) {
    	MHNA010VO result = new MHNA010VO();
    	
    	MHNC99902VO dataCheck = null;
    	Element dataElement = null;
    	for(int i = 0; i < dataCheckList.size(); i++) {
        	dataCheck = new MHNC99902VO();
        	dataCheck = dataCheckList.get(i);
        	
        	// Title, Content 는 거의 공통.
        	if(dataCheck.getType().equals("TITLE")) {
        		dataElement = document.select(dataCheck.getSelection()).first();
				result.setTitle(dataElement.attr(dataCheck.getData()));
				System.out.println("TitleHtml : " + dataElement.outerHtml());
        	}else if(dataCheck.getType().equals("CONTENTS")) {
        		dataElement = document.select(dataCheck.getSelection()).first();
				result.setContent(dataElement.attr(dataCheck.getData()));
				System.out.println("ContentHtml : " + dataElement.outerHtml());
        	}else if(dataCheck.getType().equals("AUTHOR")) {
        		
        	}else if(dataCheck.getType().equals("EMAIL")) {
        		if(dataCheck.getUse_yn().equals("Y")) {
        			System.out.println("aaaaaaaa" + document.text());
        			System.out.println("aaaaaaaa" + document.text("@kyunghyang.com"));
        			System.out.println("aaaaaaaa" + document.data());
        			System.out.println("aaaaaaaa" + document.ownText());
        			System.out.println("aaaaaaaa" + document.body());
        		}
        	}
        }
		
		
		// 7. 제목 및 요약 내용 가져오기.
		
		// 8. 기자명 가져오기.
		
		// 9. email 정보 가져오기.
    	
    	return result;
    }

	// 0025 : MBC
	public MHNA010VO Media_0025(Document document, List<MHNC99902VO> dataCheckList) {
    	MHNA010VO result = new MHNA010VO();
    	
    	MHNC99902VO dataCheck = null;
    	Element dataElement = null;
    	for(int i = 0; i < dataCheckList.size(); i++) {
        	dataCheck = new MHNC99902VO();
        	dataCheck = dataCheckList.get(i);
        	
        	// Title, Content 는 거의 공통.
        	if(dataCheck.getType().equals("TITLE")) {
        		dataElement = document.select(dataCheck.getSelection()).first();
				result.setTitle(dataElement.attr(dataCheck.getData()));
				System.out.println("TitleHtml : " + dataElement.outerHtml());
        	}else if(dataCheck.getType().equals("CONTENTS")) {
        		dataElement = document.select(dataCheck.getSelection()).first();
				result.setContent(dataElement.attr(dataCheck.getData()));
				System.out.println("ContentHtml : " + dataElement.outerHtml());
        	}else if(dataCheck.getType().equals("AUTHOR")) {
        		
        	}else if(dataCheck.getType().equals("EMAIL")) {
        		if(dataCheck.getUse_yn().equals("Y")) {
        			System.out.println("aaaaaaaa" + document.text());
        			System.out.println("aaaaaaaa" + document.text("@kyunghyang.com"));
        			System.out.println("aaaaaaaa" + document.data());
        			System.out.println("aaaaaaaa" + document.ownText());
        			System.out.println("aaaaaaaa" + document.body());
        		}
        	}
        }
		
		
		// 7. 제목 및 요약 내용 가져오기.
		
		// 8. 기자명 가져오기.
		
		// 9. email 정보 가져오기.
    	
    	return result;
    }

	// 0026 : SBS
	public MHNA010VO Media_0026(Document document, List<MHNC99902VO> dataCheckList) {
    	MHNA010VO result = new MHNA010VO();
    	
    	MHNC99902VO dataCheck = null;
    	Element dataElement = null;
    	for(int i = 0; i < dataCheckList.size(); i++) {
        	dataCheck = new MHNC99902VO();
        	dataCheck = dataCheckList.get(i);
        	
        	// Title, Content 는 거의 공통.
        	if(dataCheck.getType().equals("TITLE")) {
        		dataElement = document.select(dataCheck.getSelection()).first();
				result.setTitle(dataElement.attr(dataCheck.getData()));
				System.out.println("TitleHtml : " + dataElement.outerHtml());
        	}else if(dataCheck.getType().equals("CONTENTS")) {
        		dataElement = document.select(dataCheck.getSelection()).first();
				result.setContent(dataElement.attr(dataCheck.getData()));
				System.out.println("ContentHtml : " + dataElement.outerHtml());
        	}else if(dataCheck.getType().equals("AUTHOR")) {
        		
        	}else if(dataCheck.getType().equals("EMAIL")) {
        		if(dataCheck.getUse_yn().equals("Y")) {
        			System.out.println("aaaaaaaa" + document.text());
        			System.out.println("aaaaaaaa" + document.text("@kyunghyang.com"));
        			System.out.println("aaaaaaaa" + document.data());
        			System.out.println("aaaaaaaa" + document.ownText());
        			System.out.println("aaaaaaaa" + document.body());
        		}
        	}
        }
		
		
		// 7. 제목 및 요약 내용 가져오기.
		
		// 8. 기자명 가져오기.
		
		// 9. email 정보 가져오기.
    	
    	return result;
    }

	// 0027 : YTN
	public MHNA010VO Media_0027(Document document, List<MHNC99902VO> dataCheckList) {
    	MHNA010VO result = new MHNA010VO();
    	
    	MHNC99902VO dataCheck = null;
    	Element dataElement = null;
    	for(int i = 0; i < dataCheckList.size(); i++) {
        	dataCheck = new MHNC99902VO();
        	dataCheck = dataCheckList.get(i);
        	
        	// Title, Content 는 거의 공통.
        	if(dataCheck.getType().equals("TITLE")) {
        		dataElement = document.select(dataCheck.getSelection()).first();
				result.setTitle(dataElement.attr(dataCheck.getData()));
				System.out.println("TitleHtml : " + dataElement.outerHtml());
        	}else if(dataCheck.getType().equals("CONTENTS")) {
        		dataElement = document.select(dataCheck.getSelection()).first();
				result.setContent(dataElement.attr(dataCheck.getData()));
				System.out.println("ContentHtml : " + dataElement.outerHtml());
        	}else if(dataCheck.getType().equals("AUTHOR")) {
        		
        	}else if(dataCheck.getType().equals("EMAIL")) {
        		if(dataCheck.getUse_yn().equals("Y")) {
        			System.out.println("aaaaaaaa" + document.text());
        			System.out.println("aaaaaaaa" + document.text("@kyunghyang.com"));
        			System.out.println("aaaaaaaa" + document.data());
        			System.out.println("aaaaaaaa" + document.ownText());
        			System.out.println("aaaaaaaa" + document.body());
        		}
        	}
        }
		
		
		// 7. 제목 및 요약 내용 가져오기.
		
		// 8. 기자명 가져오기.
		
		// 9. email 정보 가져오기.
    	
    	return result;
    }
}
