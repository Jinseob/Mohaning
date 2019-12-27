package com.mohaning.app;

import java.io.IOException;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Controller;

import com.mohaning.app.Model.MHNA010VO;
import com.mohaning.app.Model.MHNC99902VO;

@Controller
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
				result.setNews_title(dataElement.attr(dataCheck.getData()));
				System.out.println("TitleHtml : " + dataElement.outerHtml());
        	}else if(dataCheck.getType().equals("CONTENTS")) {
        		dataElement = document.select(dataCheck.getSelection()).first();
				result.setNews_contents(dataElement.attr(dataCheck.getData()));
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
				result.setNews_title(dataElement.attr(dataCheck.getData()));
				System.out.println("TitleHtml : " + dataElement.outerHtml());
        	}else if(dataCheck.getType().equals("CONTENTS")) {
        		dataElement = document.select(dataCheck.getSelection()).first();
				result.setNews_contents(dataElement.attr(dataCheck.getData()));
				System.out.println("ContentHtml : " + dataElement.outerHtml());
        	}else if(dataCheck.getType().equals("AUTHOR")) {
        		dataElement = document.select(dataCheck.getSelection()).first();
        		String author = dataElement.attr(dataCheck.getData()).split("\\s+")[0];
				result.setAuthor_nm(author);
				System.out.println("AUTHOR : " + dataElement.outerHtml());
        	}else if(dataCheck.getType().equals("EMAIL")) {
        		dataElement = document.select(dataCheck.getSelection()).first();
        		String[] textArray = dataElement.text().split("\\s+");
        		String selectedTxt = "";
        		for(int j = textArray.length - 1; j >= 0; j--) {
        			String text = textArray[j];
        			if(text.indexOf(dataCheck.getPattern()) > -1) {
        				selectedTxt = text;
        				break;
        			}
        		}
        		result.setAuthor_email(selectedTxt);
        	}
        }
    	
    	return result;
    }
	
	// 0003 : 네이버뉴스 -> 기사 원문 링크를 사용하여 정보 가져오는 로직 사용?
	public MHNA010VO Media_0003(Document document, List<MHNC99902VO> dataCheckList) {
    	MHNA010VO result = new MHNA010VO();
    	MHNA010VO temp = new MHNA010VO();
    	
    	MHNC99902VO dataCheck = null;
    	Element dataElement = null;
    	for(int i = 0; i < dataCheckList.size(); i++) {
        	dataCheck = new MHNC99902VO();
        	dataCheck = dataCheckList.get(i);
        	
        	// Title, Content 는 거의 공통.
        	if(dataCheck.getType().equals("TITLE")) {
        		dataElement = document.select(dataCheck.getSelection()).first();
				result.setNews_title(dataElement.attr(dataCheck.getData()));
				System.out.println("TitleHtml : " + dataElement.outerHtml());
        	}else if(dataCheck.getType().equals("CONTENTS")) {
        		dataElement = document.select(dataCheck.getSelection()).first();
				result.setNews_contents(dataElement.attr(dataCheck.getData()));
				System.out.println("ContentHtml : " + dataElement.outerHtml());
        	}else if(dataCheck.getType().equals("ORIGINAL")) {

        	}
        	
        	result.setAuthor_email(temp.getAuthor_email());
        	result.setAuthor_nm(temp.getAuthor_nm());
        }
		
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
				result.setNews_title(dataElement.attr(dataCheck.getData()));
				System.out.println("TitleHtml : " + dataElement.outerHtml());
        	}else if(dataCheck.getType().equals("CONTENTS")) {
        		dataElement = document.select(dataCheck.getSelection()).first();
				result.setNews_contents(dataElement.attr(dataCheck.getData()));
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
				result.setNews_title(dataElement.attr(dataCheck.getData()));
				System.out.println("TitleHtml : " + dataElement.outerHtml());
        	}else if(dataCheck.getType().equals("CONTENTS")) {
        		dataElement = document.select(dataCheck.getSelection()).first();
				result.setNews_contents(dataElement.attr(dataCheck.getData()));
				System.out.println("ContentHtml : " + dataElement.outerHtml());
        	}else if(dataCheck.getType().equals("AUTHOR")) {
        		dataElement = document.select(dataCheck.getSelection()).first();
        		String jsonHtml = dataElement.html();
        		JSONParser jsonParser = new JSONParser();
        		try {
					JSONObject jsonObj = (JSONObject)jsonParser.parse(jsonHtml);
					String text = jsonObj.get("author").toString();
					String[] authorArr = text.split("\\s+");
					String author = authorArr[authorArr.length - 2];
					result.setAuthor_nm(author);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}else if(dataCheck.getType().equals("EMAIL")) {
        		dataElement = document.select(dataCheck.getSelection()).first();
        		String[] textArray = dataElement.text().split("\\s+");
        		String selectedTxt = "";
        		for(int j = textArray.length - 1; j >= 0; j--) {
        			String text = textArray[j];
        			if(text.indexOf(dataCheck.getPattern()) > -1) {
        				selectedTxt = text;
        				break;
        			}
        		}
        		result.setAuthor_email(selectedTxt);
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
				result.setNews_title(dataElement.attr(dataCheck.getData()));
				System.out.println("TitleHtml : " + dataElement.outerHtml());
        	}else if(dataCheck.getType().equals("CONTENTS")) {
        		dataElement = document.select(dataCheck.getSelection()).first();
				result.setNews_contents(dataElement.attr(dataCheck.getData()));
				System.out.println("ContentHtml : " + dataElement.outerHtml());
        	}else if(dataCheck.getType().equals("AUTHOR")) {
        		dataElement = document.select(dataCheck.getSelection()).first();
        		String[] textArray = dataElement.attr(dataCheck.getData()).split("\\s+");
        		String selectedTxt = "";
        		for(int j = 0; j < textArray.length; j++) {
        			String text = textArray[j];
        			if(text.indexOf(dataCheck.getPattern()) > -1) {
        				selectedTxt = text.replace(dataCheck.getPattern(), "");
        				break;
        			}
        		}
        		result.setAuthor_nm(selectedTxt);
        	}else if(dataCheck.getType().equals("EMAIL")) {
        		dataElement = document.select(dataCheck.getSelection()).first();
        		String[] textArray = dataElement.text().split("\\s+");
        		String selectedTxt = "";
        		for(int j = textArray.length - 1; j >= 0; j--) {
        			String text = textArray[j];
        			if(text.indexOf(dataCheck.getPattern()) > -1) {
        				selectedTxt = text;
        				break;
        			}
        		}
        		result.setAuthor_email(selectedTxt);
        	}
        }
		
    	return result;
    }

	// 0007 : 다음뉴스 -> 네이버와 동일. 마지막에 통합으로 처리.
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
				result.setNews_title(dataElement.attr(dataCheck.getData()));
				System.out.println("TitleHtml : " + dataElement.outerHtml());
        	}else if(dataCheck.getType().equals("CONTENTS")) {
        		dataElement = document.select(dataCheck.getSelection()).first();
				result.setNews_contents(dataElement.attr(dataCheck.getData()));
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
				result.setNews_title(dataElement.attr(dataCheck.getData()));
				System.out.println("TitleHtml : " + dataElement.outerHtml());
        	}else if(dataCheck.getType().equals("CONTENTS")) {
        		dataElement = document.select(dataCheck.getSelection()).first();
				result.setNews_contents(dataElement.attr(dataCheck.getData()));
				System.out.println("ContentHtml : " + dataElement.outerHtml());
        	}else if(dataCheck.getType().equals("AUTHOR")) {
        		dataElement = document.select(dataCheck.getSelection()).first();
        		String text = dataElement.attr(dataCheck.getData());
        		String author = text.split("\\s+")[0];
				result.setAuthor_nm(author);
        	}else if(dataCheck.getType().equals("EMAIL")) {
        		dataElement = document.select(dataCheck.getSelection()).first();
        		String[] textArray = dataElement.text().split("\\s+");
        		String selectedTxt = "";
        		for(int j = textArray.length - 1; j >= 0; j--) {
        			String text = textArray[j];
        			if(text.indexOf(dataCheck.getPattern()) > -1) {
        				selectedTxt = text;
        				break;
        			}
        		}
        		result.setAuthor_email(selectedTxt);
        	}
        }
		
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
				result.setNews_title(dataElement.attr(dataCheck.getData()));
				System.out.println("TitleHtml : " + dataElement.outerHtml());
        	}else if(dataCheck.getType().equals("CONTENTS")) {
        		dataElement = document.select(dataCheck.getSelection()).first();
				result.setNews_contents(dataElement.attr(dataCheck.getData()));
				System.out.println("ContentHtml : " + dataElement.outerHtml());
        	}else if(dataCheck.getType().equals("AUTHOR")) {
        		dataElement = document.select(dataCheck.getSelection()).first();
        		String author = dataElement.text().split("\\s+")[0];
        		result.setAuthor_nm(author);
        	}else if(dataCheck.getType().equals("EMAIL")) {
        		dataElement = document.select(dataCheck.getSelection()).first();
        		String[] textArray = dataElement.text().split("\\s+");
        		String selectedTxt = "";
        		for(int j = textArray.length - 1; j >= 0; j--) {
        			String text = textArray[j];
        			if(text.indexOf(dataCheck.getPattern()) > -1) {
        				selectedTxt = text;
        				break;
        			}
        		}
        		result.setAuthor_email(selectedTxt);
        	}
        }
		
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
				result.setNews_title(dataElement.attr(dataCheck.getData()));
				System.out.println("TitleHtml : " + dataElement.outerHtml());
        	}else if(dataCheck.getType().equals("CONTENTS")) {
        		dataElement = document.select(dataCheck.getSelection()).first();
				result.setNews_contents(dataElement.attr(dataCheck.getData()));
				System.out.println("ContentHtml : " + dataElement.outerHtml());
        	}else if(dataCheck.getType().equals("AUTHOR")) {
        		dataElement = document.select(dataCheck.getSelection()).first();
				result.setAuthor_nm(dataElement.attr(dataCheck.getData()));
        	}else if(dataCheck.getType().equals("EMAIL")) {
        		// Email 이 없음.
        	}
        }
		
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
				result.setNews_title(dataElement.attr(dataCheck.getData()));
				System.out.println("TitleHtml : " + dataElement.outerHtml());
        	}else if(dataCheck.getType().equals("CONTENTS")) {
        		dataElement = document.select(dataCheck.getSelection()).first();
				result.setNews_contents(dataElement.attr(dataCheck.getData()));
				System.out.println("ContentHtml : " + dataElement.outerHtml());
        	}else if(dataCheck.getType().equals("AUTHOR")) {
        		dataElement = document.select(dataCheck.getSelection()).first();
				result.setAuthor_nm(dataElement.attr(dataCheck.getData()));
        	}else if(dataCheck.getType().equals("EMAIL")) {
        		dataElement = document.select(dataCheck.getSelection()).first();
        		String[] textArray = dataElement.text().split("\\s+");
        		String selectedTxt = "";
        		for(int j = textArray.length - 1; j >= 0; j--) {
        			String text = textArray[j];
        			if(text.indexOf(dataCheck.getPattern()) > -1) {
        				selectedTxt = text;
        				break;
        			}
        		}
        		result.setAuthor_email(selectedTxt);
        	}
        }
		
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
				result.setNews_title(dataElement.attr(dataCheck.getData()));
				System.out.println("TitleHtml : " + dataElement.outerHtml());
        	}else if(dataCheck.getType().equals("CONTENTS")) {
        		dataElement = document.select(dataCheck.getSelection()).first();
				result.setNews_contents(dataElement.attr(dataCheck.getData()));
				System.out.println("ContentHtml : " + dataElement.outerHtml());
        	}else if(dataCheck.getType().equals("AUTHOR")) {
        		dataElement = document.select(dataCheck.getSelection()).first();
				result.setAuthor_nm(dataElement.attr(dataCheck.getData()));
        	}else if(dataCheck.getType().equals("EMAIL")) {
        		dataElement = document.select(dataCheck.getSelection()).first();
        		String[] textArray = dataElement.text().split("\\s+");
        		String selectedTxt = "";
        		for(int j = textArray.length - 1; j >= 0; j--) {
        			String text = textArray[j];
        			if(text.indexOf(dataCheck.getPattern()) > -1) {
        				selectedTxt = text;
        				break;
        			}
        		}
        		result.setAuthor_email(selectedTxt);
        	}
        }
    	
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
				result.setNews_title(dataElement.attr(dataCheck.getData()));
				System.out.println("TitleHtml : " + dataElement.outerHtml());
        	}else if(dataCheck.getType().equals("CONTENTS")) {
        		dataElement = document.select(dataCheck.getSelection()).first();
				result.setNews_contents(dataElement.attr(dataCheck.getData()));
				System.out.println("ContentHtml : " + dataElement.outerHtml());
        	}else if(dataCheck.getType().equals("AUTHOR")) {
        		dataElement = document.select(dataCheck.getSelection()).first();
        		String[] textArray = dataElement.attr(dataCheck.getData()).split("=");
        		String author = textArray[textArray.length - 1];
				result.setAuthor_nm(author);
        	}else if(dataCheck.getType().equals("EMAIL")) {
        		dataElement = document.select(dataCheck.getSelection()).first();
        		String[] textArray = dataElement.text().split("\\s+");
        		String selectedTxt = "";
        		for(int j = textArray.length - 1; j >= 0; j--) {
        			String text = textArray[j];
        			if(text.indexOf(dataCheck.getPattern()) > -1) {
        				selectedTxt = text;
        				break;
        			}
        		}
        		result.setAuthor_email(selectedTxt);
        	}
        }
		
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
				result.setNews_title(dataElement.attr(dataCheck.getData()));
				System.out.println("TitleHtml : " + dataElement.outerHtml());
        	}else if(dataCheck.getType().equals("CONTENTS")) {
        		dataElement = document.select(dataCheck.getSelection()).first();
				result.setNews_contents(dataElement.attr(dataCheck.getData()));
				System.out.println("ContentHtml : " + dataElement.outerHtml());
        	}else if(dataCheck.getType().equals("AUTHOR")) {
        		dataElement = document.select(dataCheck.getSelection()).first();
				result.setAuthor_nm(dataElement.attr(dataCheck.getData()));
        	}else if(dataCheck.getType().equals("EMAIL")) {
        		dataElement = document.select(dataCheck.getSelection()).first();
        		String[] textArray = dataElement.text().split("\\s+");
        		String selectedTxt = "";
        		for(int j = textArray.length - 1; j >= 0; j--) {
        			String text = textArray[j];
        			if(text.indexOf(dataCheck.getPattern()) > -1) {
        				selectedTxt = text;
        				break;
        			}
        		}
        		result.setAuthor_email(selectedTxt);
        	}
        }
		
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
				result.setNews_title(dataElement.attr(dataCheck.getData()));
				System.out.println("TitleHtml : " + dataElement.outerHtml());
        	}else if(dataCheck.getType().equals("CONTENTS")) {
        		dataElement = document.select(dataCheck.getSelection()).first();
				result.setNews_contents(dataElement.attr(dataCheck.getData()));
				System.out.println("ContentHtml : " + dataElement.outerHtml());
        	}else if(dataCheck.getType().equals("AUTHOR")) {
        		dataElement = document.select(dataCheck.getSelection()).first();
        		String[] textArray = dataElement.text().split("\\s+");
        		String author = textArray[0];
				result.setAuthor_nm(author);
        	}else if(dataCheck.getType().equals("EMAIL")) {
        		// Email 이 없음.
        	}
        }
		
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
				result.setNews_title(dataElement.attr(dataCheck.getData()));
				System.out.println("TitleHtml : " + dataElement.outerHtml());
        	}else if(dataCheck.getType().equals("CONTENTS")) {
        		dataElement = document.select(dataCheck.getSelection()).first();
				result.setNews_contents(dataElement.attr(dataCheck.getData()));
				System.out.println("ContentHtml : " + dataElement.outerHtml());
        	}else if(dataCheck.getType().equals("AUTHOR")) {
        		dataElement = document.select(dataCheck.getSelection()).first();
        		String[] textArray = dataElement.attr(dataCheck.getData()).split("\\s+");
        		String author = textArray[0];
				result.setAuthor_nm(author);
        	}else if(dataCheck.getType().equals("EMAIL")) {
        		// Email 이 없음.
        	}
        }
		
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
				result.setNews_title(dataElement.attr(dataCheck.getData()));
				System.out.println("TitleHtml : " + dataElement.outerHtml());
        	}else if(dataCheck.getType().equals("CONTENTS")) {
        		dataElement = document.select(dataCheck.getSelection()).first();
				result.setNews_contents(dataElement.attr(dataCheck.getData()));
				System.out.println("ContentHtml : " + dataElement.outerHtml());
        	}else if(dataCheck.getType().equals("AUTHOR")) {
        		dataElement = document.select(dataCheck.getSelection()).first();
				result.setAuthor_nm(dataElement.attr(dataCheck.getData()));
        	}else if(dataCheck.getType().equals("EMAIL")) {
        		dataElement = document.select(dataCheck.getSelection()).first();
        		String[] textArray = dataElement.text().split("\\s+");
        		String selectedTxt = "";
        		for(int j = textArray.length - 1; j >= 0; j--) {
        			String text = textArray[j];
        			if(text.indexOf(dataCheck.getPattern()) > -1) {
        				selectedTxt = text;
        				break;
        			}
        		}
        		result.setAuthor_email(selectedTxt);
        	}
        }
		
    	return result;
    }

	// 0018 : 채널 A -> 로직 추가 필요. 다양한 언론사 사용.
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
				result.setNews_title(dataElement.attr(dataCheck.getData()));
				System.out.println("TitleHtml : " + dataElement.outerHtml());
        	}else if(dataCheck.getType().equals("CONTENTS")) {
        		dataElement = document.select(dataCheck.getSelection()).first();
				result.setNews_contents(dataElement.attr(dataCheck.getData()));
				System.out.println("ContentHtml : " + dataElement.outerHtml());
        	}else if(dataCheck.getType().equals("AUTHOR")) {
        		// 추후 로직 추가.
        	}else if(dataCheck.getType().equals("EMAIL")) {
        		// 추후 로직 추가.
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
				result.setNews_title(dataElement.attr(dataCheck.getData()));
				System.out.println("TitleHtml : " + dataElement.attr(dataCheck.getData()));
        	}else if(dataCheck.getType().equals("CONTENTS")) {
        		dataElement = document.select(dataCheck.getSelection()).first();
				result.setNews_contents(dataElement.attr(dataCheck.getData()));
				System.out.println("ContentHtml : " + dataElement.outerHtml());
        	}else if(dataCheck.getType().equals("AUTHOR")) {
        		dataElement = document.select(dataCheck.getSelection()).first();
        		String[] textArray = dataElement.attr(dataCheck.getData()).split("\\s+");
        		String author = textArray[0];
				result.setAuthor_nm(author);
        	}else if(dataCheck.getType().equals("EMAIL")) {
        		dataElement = document.select(dataCheck.getSelection()).first();
        		String[] textArray = dataElement.text().split("\\s+");
        		String selectedTxt = "";
        		for(int j = textArray.length - 1; j >= 0; j--) {
        			String text = textArray[j];
        			if(text.indexOf(dataCheck.getPattern()) > -1) {
        				selectedTxt = text;
        				break;
        			}
        		}
        		result.setAuthor_email(selectedTxt);
        	}
        }
		
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
				result.setNews_title(dataElement.attr(dataCheck.getData()));
				System.out.println("TitleHtml : " + dataElement.outerHtml());
        	}else if(dataCheck.getType().equals("CONTENTS")) {
        		dataElement = document.select(dataCheck.getSelection()).first();
				result.setNews_contents(dataElement.attr(dataCheck.getData()));
				System.out.println("ContentHtml : " + dataElement.outerHtml());
        	}else if(dataCheck.getType().equals("AUTHOR")) {
        		dataElement = document.select(dataCheck.getSelection()).first();
        		String[] textArray = dataElement.attr(dataCheck.getData()).split(",");
        		String author = textArray[0];
				result.setAuthor_nm(author);
        	}else if(dataCheck.getType().equals("EMAIL")) {
        		dataElement = document.select(dataCheck.getSelection()).first();
        		String text = dataElement.text();
				result.setAuthor_email(text);
        	}
        }
		
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
				result.setNews_title(dataElement.attr(dataCheck.getData()));
				System.out.println("TitleHtml : " + dataElement.outerHtml());
        	}else if(dataCheck.getType().equals("CONTENTS")) {
        		dataElement = document.select(dataCheck.getSelection()).first();
				result.setNews_contents(dataElement.attr(dataCheck.getData()));
				System.out.println("ContentHtml : " + dataElement.outerHtml());
        	}else if(dataCheck.getType().equals("AUTHOR")) {
        		dataElement = document.select(dataCheck.getSelection()).first();
				result.setAuthor_nm(dataElement.attr(dataCheck.getData()));
        	}else if(dataCheck.getType().equals("EMAIL")) {
        		dataElement = document.select(dataCheck.getSelection()).first();
				result.setAuthor_email(dataElement.attr(dataCheck.getData()));
        	}
        }
		
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
				result.setNews_title(dataElement.attr(dataCheck.getData()));
				System.out.println("TitleHtml : " + dataElement.outerHtml());
        	}else if(dataCheck.getType().equals("CONTENTS")) {
        		dataElement = document.select(dataCheck.getSelection()).first();
				result.setNews_contents(dataElement.attr(dataCheck.getData()));
				System.out.println("ContentHtml : " + dataElement.outerHtml());
        	}else if(dataCheck.getType().equals("AUTHOR")) {
        		dataElement = document.select(dataCheck.getSelection()).first();
        		String jsonHtml = dataElement.html().substring(0, dataElement.html().lastIndexOf("}"));	// Json Data 부분에 '}' 마지막에 하나 더 들어감. 제거 필요.
        		JSONParser jsonParser = new JSONParser();
        		try {
					JSONObject jsonObj = (JSONObject)jsonParser.parse(jsonHtml);
					JSONObject author = (JSONObject) jsonObj.get("author");
					String name = (String) author.get("name");
					result.setAuthor_nm(name);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}else if(dataCheck.getType().equals("EMAIL")) {
        		dataElement = document.select(dataCheck.getSelection()).first();
        		String[] textArray = dataElement.text().split("\\s+");
        		String selectedTxt = "";
        		for(int j = textArray.length - 1; j >= 0; j--) {
        			String text = textArray[j];
        			if(text.indexOf(dataCheck.getPattern()) > -1) {
        				selectedTxt = text;
        				break;
        			}
        		}
        		result.setAuthor_email(selectedTxt);
        	}
        }
		
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
				result.setNews_title(dataElement.attr(dataCheck.getData()));
				System.out.println("TitleHtml : " + dataElement.outerHtml());
        	}else if(dataCheck.getType().equals("CONTENTS")) {
        		dataElement = document.select(dataCheck.getSelection()).first();
				result.setNews_contents(dataElement.attr(dataCheck.getData()));
				System.out.println("ContentHtml : " + dataElement.outerHtml());
        	}else if(dataCheck.getType().equals("AUTHOR")) {
        		dataElement = document.select(dataCheck.getSelection()).first();
        		String[] textArray = dataElement.attr(dataCheck.getData()).split("\\s+");
        		String author = textArray[0];
				result.setAuthor_nm(author);
        	}else if(dataCheck.getType().equals("EMAIL")) {
        		dataElement = document.select(dataCheck.getSelection()).first();
        		String[] textArray = dataElement.attr(dataCheck.getData()).split(":");
        		String selectedTxt = "";
        		for(int j = 0; j < textArray.length; j++) {
        			String text = textArray[j];
        			if(text.indexOf(dataCheck.getPattern()) > -1) {
        				selectedTxt = text;
        				break;
        			}
        		}
				result.setAuthor_email(selectedTxt);
        	}
        }
		
    	return result;
    }

	// 0024 : KBS -> javascript 에서 기자명, 이메일 받아와서 처리. 단순 jsoup 으로 크롤링이 힘듬. ajax 값 json 으로 받아서 처리하기.
	public MHNA010VO Media_0024(Document document, List<MHNC99902VO> dataCheckList) {
    	MHNA010VO result = new MHNA010VO();
    	String docId = document.attr("docid");
    	
    	MHNC99902VO dataCheck = null;
    	Element dataElement = null;
    	for(int i = 0; i < dataCheckList.size(); i++) {
        	dataCheck = new MHNC99902VO();
        	dataCheck = dataCheckList.get(i);
        	
        	// Title, Content 는 거의 공통.
        	if(dataCheck.getType().equals("TITLE")) {
        		dataElement = document.select(dataCheck.getSelection()).first();
				result.setNews_title(dataElement.attr(dataCheck.getData()));
				System.out.println("TitleHtml : " + dataElement.outerHtml());
        	}else if(dataCheck.getType().equals("CONTENTS")) {
        		dataElement = document.select(dataCheck.getSelection()).first();
				result.setNews_contents(dataElement.attr(dataCheck.getData()));
				System.out.println("ContentHtml : " + dataElement.outerHtml());
        	}else if(dataCheck.getType().equals("AUTHOR")) {
        		try {
        			String url = dataCheck.getSelection();
					document = Jsoup.connect(url).data("SEARCH_NEWS_CODE", docId).post();
					JSONParser jsonParser = new JSONParser();
					JSONObject jsonObj = (JSONObject)jsonParser.parse(document.select("body").html());
					JSONArray reporter = (JSONArray) jsonObj.get("reporter");
					JSONObject reporterInfo = (JSONObject) reporter.get(0);
					String author = reporterInfo.get("REPORTER_NAME").toString();
					result.setAuthor_nm(author.toString());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}else if(dataCheck.getType().equals("EMAIL")) {
        		try {
        			String url = dataCheck.getSelection();
					document = Jsoup.connect(url).data("SEARCH_NEWS_CODE", docId).post();
					JSONParser jsonParser = new JSONParser();
					JSONObject jsonObj = (JSONObject)jsonParser.parse(document.select("body").html());
					JSONArray reporter = (JSONArray) jsonObj.get("reporter");
					JSONObject reporterInfo = (JSONObject) reporter.get(0);
					String email = reporterInfo.get("EMAIL").toString();
					result.setAuthor_email(email.toString());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}
        }
    	
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
				result.setNews_title(dataElement.attr(dataCheck.getData()));
				System.out.println("TitleHtml : " + dataElement.outerHtml());
        	}else if(dataCheck.getType().equals("CONTENTS")) {
        		dataElement = document.select(dataCheck.getSelection()).first();
				result.setNews_contents(dataElement.attr(dataCheck.getData()));
				System.out.println("ContentHtml : " + dataElement.outerHtml());
        	}else if(dataCheck.getType().equals("AUTHOR")) {
        		dataElement = document.select(dataCheck.getSelection()).first();
				result.setAuthor_nm(dataElement.attr(dataCheck.getData()));
        	}else if(dataCheck.getType().equals("EMAIL")) {
        		// Email 이 없음
        	}
        }
		
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
				result.setNews_title(dataElement.attr(dataCheck.getData()));
				System.out.println("TitleHtml : " + dataElement.outerHtml());
        	}else if(dataCheck.getType().equals("CONTENTS")) {
        		dataElement = document.select(dataCheck.getSelection()).first();
				result.setNews_contents(dataElement.attr(dataCheck.getData()));
				System.out.println("ContentHtml : " + dataElement.outerHtml());
        	}else if(dataCheck.getType().equals("AUTHOR")) {
        		dataElement = document.select(dataCheck.getSelection()).first();
				result.setAuthor_nm(dataElement.attr(dataCheck.getData()));
        	}else if(dataCheck.getType().equals("EMAIL")) {
        		dataElement = document.select(dataCheck.getSelection()).first();
				result.setAuthor_email(dataElement.attr(dataCheck.getData()));
        	}
        }
		
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
				result.setNews_title(dataElement.attr(dataCheck.getData()));
				System.out.println("TitleHtml : " + dataElement.outerHtml());
        	}else if(dataCheck.getType().equals("CONTENTS")) {
        		dataElement = document.select(dataCheck.getSelection()).first();
				result.setNews_contents(dataElement.attr(dataCheck.getData()));
				System.out.println("ContentHtml : " + dataElement.outerHtml());
        	}else if(dataCheck.getType().equals("AUTHOR")) {
        		if(result.getAuthor_nm().equals("")) {
        			dataElement = document.select(dataCheck.getSelection()).first();
        			String jsonHtml = dataElement.html();	// Json Data 부분에 '}' 마지막에 하나 더 들어감. 제거 필요.
        			JSONParser jsonParser = new JSONParser();
        			try {
        				JSONObject jsonObj = (JSONObject)jsonParser.parse(jsonHtml);
        				JSONObject author = (JSONObject) jsonObj.get("author");
        				String name = (String) author.get("name");
        				result.setAuthor_nm(name);
        			} catch (ParseException e) {
        				// TODO Auto-generated catch block
        				e.printStackTrace();
        			}
        		}
        	}else if(dataCheck.getType().equals("EMAIL")) {
        		dataElement = document.select(dataCheck.getSelection()).first();
        		String[] textArray = dataElement.text().split("\\s+");
        		String selectedTxt = "";
        		for(int j = textArray.length - 1; j >= 0; j--) {
        			String text = textArray[j];
        			if(text.indexOf(dataCheck.getPattern()) > -1) {
        				selectedTxt = text;
        				break;
        			}
        		}
        		
        		String author = "";
        		String email = "";
        		if(!selectedTxt.equals("")) {
        			selectedTxt = selectedTxt.replaceAll("\\]", "");
        			textArray = selectedTxt.split("\\[");
        			author = textArray[0] != null ? textArray[0] : "";
        			email = textArray[1] != null ? textArray[1] : "";
        		}
        		
        		// 기자명에 YTN 이 있거나 빈값이 있는 경우. 기자명 넣기.
				if(result.getAuthor_nm().equals("YTN") || result.getAuthor_nm().equals("")) {
					result.setAuthor_nm(author);
				}
				
				// email 이 없는 경우도 있음.
				result.setAuthor_email(email);
        	}
        }
    	
    	return result;
    }
}
