package com.mohaning.app.Controller.Ajax;

import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.mohaning.app.GetDataController;
import com.mohaning.app.Dao.CmmnDao;
import com.mohaning.app.Model.MHNA01001VO;
import com.mohaning.app.Model.MHNA010VO;
import com.mohaning.app.Model.MHNC99901VO;
import com.mohaning.app.Model.MHNC99902VO;
import com.mohaning.app.Model.MHND010VO;

@Controller
public class NewsJsonController {
	private static final Logger logger = LoggerFactory.getLogger(NewsJsonController.class);

	@Autowired
	private CmmnDao dao;
	
	// News 등록 Backup
//	@RequestMapping(value = "/NewsRegister.json")
//	public ModelAndView NewsRegister(@ModelAttribute("MHNA010VO") MHNA01001VO MHNA010VO, ModelMap model, HttpServletRequest request, 
//			HttpServletResponse response, HttpSession session) throws Exception{
//		
//		ModelAndView modelAndView = new ModelAndView();
//		MappingJackson2JsonView jsonView = new MappingJackson2JsonView();
//		modelAndView.setView(jsonView);
//		
//		String url = MHNA010VO.getNews_url();
//		
//		// 기 등록된 뉴스인지 확인하는 부분.
//		MHNA01001VO checkRegNews = (MHNA01001VO) dao.select("a010.checkRegisteredNews", MHNA010VO);
//		if(checkRegNews != null) {
//			model.addAttribute("type", "OCC");
//			model.addAttribute("result", checkRegNews);
//		}else {
//			System.out.println("동일한 URL 이 없다.");
//			// 기사 내용중 많이 사용된 단어들 정리해서 태그하기.
//			
//			// 언론사 정보 가지고 오는 부분.
//			@SuppressWarnings("unchecked")
//			List<MHNC99901VO> mediaList = (List<MHNC99901VO>) dao.selectList("c999.selectMediaList", MHNA010VO);
//			
//			// 입력된 URL 에 해당하는 언론사 선택.
//			MHNC99901VO selectedMedia = new MHNC99901VO();
//			MHNC99901VO media = new MHNC99901VO();
//			for(int i = 0; i < mediaList.size(); i++) {
//				media = mediaList.get(i);
//				if(url.indexOf(media.getMedia_url()) > -1) {
//					selectedMedia = media;
//					break;
//				}
//			}
//			
//			// 선택된 언론사의 타이틀 및 정보 가져오는 부분.
//			if(!selectedMedia.getMedia_id().equals("")) {
//				@SuppressWarnings("unchecked")
//				List<MHNC99902VO> dataCheckList = (List<MHNC99902VO>) dao.selectList("c999.selectDataCheckList", selectedMedia);
//				
//				Document document = Jsoup.connect(url).get();
//				
//				// Title, Content, Author, Email 등을 추출하는 부분.
//				MHNC99902VO dataCheck = new MHNC99902VO();
//				Element dataElement = null;
//				MHNA010VO result = new MHNA010VO();
//				result.setMedia_id(selectedMedia.getMedia_id());
//				result.setMedia_nm(selectedMedia.getMedia_nm());
//				result.setMedia_url(selectedMedia.getMedia_url());
//				for(int i = 0; i < dataCheckList.size(); i++) {
//					dataCheck = dataCheckList.get(i);
//					dataElement = document.select(dataCheck.getSelect_tag()).first();
//					
//					String data = new String();
//					// Tag 를 사용할 경우 Y. 사용하지 않을 경우는 text() 를 사용.
//					if(dataCheck.getUse_tag().equals("Y")) {
//						data = dataElement.attr(dataCheck.getData_tag());
//					}else {
//						data = dataElement.text();
//					}
//					
//					// Name, Email 일 경우 패턴 파악 후 분리.
//					if(dataCheck.getType().equals("Name")) {
//						// 처음 데이터에 기자명 있음.
//						String[] strList = data.split("\\s");	// 빈칸 마다 자르기.
//						String str = strList[0];	// 무조건 첫번째 데이터가 기자 이름을 가지고 있음. 영어 이름일 경우는 추가 로직을 넣어야함.
//						
//						// 베이징=홍길동 형식만 우선 처리. 다른 형태가 있는 경우 추후 처리.
//						if(str.indexOf("=") > -1) {
//							str = str.substring(str.indexOf("=") + 1);
//						}
//						result.setAuthor_nm(str);
//					}else if(dataCheck.getType().equals("Email")) {
//						String[] strList = data.split("\\s");	// 빈칸 마다 자르기.
//						String str = new String();
//						for(int j = 0; j < strList.length; j++) {
//							str = strList[j];
//							if(str.indexOf(dataCheck.getData_pattern()) > -1) {
//								result.setAuthor_email(str);
//								break;
//							}
//						}
//					}else if(dataCheck.getType().equals("Title")) {
//						result.setTitle(data);
//					}else if(dataCheck.getType().equals("Contents")) {
//						result.setContent(data);
//					}
//				}
//				
//				// 저자 등록여부 확인 및 ID 가져오기.
//				MHNB01001VO authorParam = new MHNB01001VO();
//				authorParam.setAuthor_email(result.getAuthor_email());
//				authorParam.setAuthor_nm(result.getAuthor_nm());
//				authorParam.setMedia_id(result.getMedia_id());
//				
//				int authorCnt = (Integer) dao.select("b010.selectAuthorCnt", authorParam);
//				String author_id = "";
//				if(authorCnt == 1) {
//					MHNB01001VO authorResult = (MHNB01001VO) dao.select("b010.selectAuthor", authorParam);
//					author_id = authorResult.getAuthor_id();
//				}
//				result.setAuthor_id(author_id);
//				
//				model.addAttribute("type", "NEW");
//				model.addAttribute("result", result);
//			}else {
//				System.out.println("맞는 URL이 없습니다.");
//			}
//		}
//		
//		return modelAndView;
//	}
	
	// SSL 우회 등록
    public static void setSSL() throws NoSuchAlgorithmException, KeyManagementException {
        TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }
            public void checkClientTrusted(X509Certificate[] certs, String authType) {
            }
            public void checkServerTrusted(X509Certificate[] certs, String authType) {
            }
        }};
        SSLContext sc = SSLContext.getInstance("SSL");
        sc.init(null, trustAllCerts, new SecureRandom());
        HttpsURLConnection.setDefaultHostnameVerifier(
            new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            }
        );
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
    }
	
	@RequestMapping(value = "/NewsRegister.json")
	public ModelAndView NewsRegister(@ModelAttribute("MHNA01001VO") MHNA01001VO mhna01001VO, ModelMap model, HttpServletRequest request, 
			HttpServletResponse response, HttpSession session) throws Exception{
		
		ModelAndView modelAndView = new ModelAndView();
		MappingJackson2JsonView jsonView = new MappingJackson2JsonView();
		modelAndView.setView(jsonView);
		
		MHNA010VO result = Register(mhna01001VO);
		
		System.out.println(result.getMedia_nm());
		model.addAttribute("type", "NEW");
		model.addAttribute("result", result);
		
		return modelAndView;
	}
	
	// SSL 확인 및 언론사 정보 가져오기.
	public MHNA010VO Register(MHNA01001VO mhna01001VO) throws Exception {
		String url = mhna01001VO.getNews_url();
		
		// 1. SSL 있는지 확인 및 우회 처리
        if(url.indexOf("https://") > -1){
        	NewsJsonController.setSSL();
        }
		
        // 2. 언론사 정보 가지고 오기. (네이버, 다음은 포탈이지만 기사를 포탈용으로 제공받으므로 포함됨)
		@SuppressWarnings("unchecked")
		List<MHNC99901VO> mediaList = (List<MHNC99901VO>) dao.selectList("c999.selectMediaList", mhna01001VO);
		
        // 3. 언론사 ID 찾기.
		Boolean chk = false;	// 언론사 있는 경우 true.
		MHNC99901VO selectedMedia = new MHNC99901VO();
		MHNC99901VO media = new MHNC99901VO();
		for(int i = 0; i < mediaList.size(); i++) {
			media = mediaList.get(i);
			if(url.indexOf(media.getMedia_chk_url()) > -1) {
				chk = true;
				selectedMedia = media;
				break;
			}
		}
		
		MHNA010VO result = new MHNA010VO();
		if(chk) {
			// 4. Doc Id, Title, Content, Author, Email 등을 가져오는 클래스 실행.
			result = DataRegister(mhna01001VO, selectedMedia);
			result.setMedia_id(selectedMedia.getMedia_id());
			result.setMedia_nm(selectedMedia.getMedia_nm());
		}else {
			System.out.println("아직 해당 언론사는 지원하지 않습니다.");
		}
		
		return result;
	}
	
	// 확인된 언론사의 데이터 가져오기
    public MHNA010VO DataRegister(MHNA01001VO mhna01001VO, MHNC99901VO selectedMedia) throws Exception{
		MHNA010VO result = new MHNA010VO();
		
		// 4. 찾은 언론사의 정보 추출 방법 가지고 오기.
		@SuppressWarnings("unchecked")
		List<MHNC99902VO> dataCheckList = (List<MHNC99902VO>) dao.selectList("c999.selectDataCheckList", selectedMedia);
		
		// 5. 크롤링
        Document document = Jsoup.connect(mhna01001VO.getNews_url()).get();
		
		// 6. 기 등록된 뉴스인지 확인하는 부분. DocID 로 확인.
		MHNC99902VO dataCheck = null;
		// DOCID 확인하는 Line 가져오기.
		for(int i = 0; i < dataCheckList.size(); i++) {
        	dataCheck = new MHNC99902VO();
        	dataCheck = dataCheckList.get(i);
        	if(dataCheck.getType().equals("DOCID")) {
        		break;
        	}
        }
        
		// 추가 로직 필요. 크롤링하면서 데이터 확인하는 동시에 저장되어 있는 태그의 형태와 동일한지 확인하기. 가능한가?
		Element dataElement = null;
		if(dataCheck.getUse_url().equals("Y")) {	// URL 에서 DocId 를 가져와야 하는 경우
			try {
				URL doc_url = new URL(mhna01001VO.getNews_url());
				String query = doc_url.getQuery();
				String[] params = query.split("&");
				String param = new String();
				for(int i = 0; i < params.length; i++) {
					param = params[i];
					if(param.indexOf(dataCheck.getMethod()) > -1) {
						result.setDoc_id(param.split(dataCheck.getMethod())[1]);
						System.out.println("docid : " + param.split(dataCheck.getMethod())[1]);
					}
				}
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {		// 태그에서 DocID 를 가져올 수 있는 경우.
			dataElement = document.select(dataCheck.getSelection()).first();
			result.setDoc_id(dataElement.attr(dataCheck.getData()));
			System.out.println("docid : " + dataElement.attr(dataCheck.getData()));
			System.out.println("outerHtml : " + dataElement.outerHtml());
//						data = dataElement.attr(dataCheck.getData_tag());
		}
		// 6-1 Docid 와 언론사 ID 를 사용하여 기등록된 기사가 있는지 확인하는 부분.
		@SuppressWarnings("unchecked")
		List<MHNA01001VO> checkRegisteredNews = (List<MHNA01001VO>) dao.selectList("a010.checkRegisteredNews", result);
		if(checkRegisteredNews.size() > 0) {
			System.out.println("기 등록된 기사가 있습니다.");
		}else {
			MHNA010VO tempData = new MHNA010VO();
			GetDataController getData = new GetDataController();
			if(selectedMedia.getMedia_id().equals("0001")) {
				tempData = getData.Media_0001(document, dataCheckList);
			}else if(selectedMedia.getMedia_id().equals("0002")) {
				tempData = getData.Media_0002(document, dataCheckList);
			}else if(selectedMedia.getMedia_id().equals("0003")) {	// 네이버
				tempData = PortalCheck(document, dataCheckList);
			}else if(selectedMedia.getMedia_id().equals("0004")) {
				tempData = getData.Media_0004(document, dataCheckList);
			}else if(selectedMedia.getMedia_id().equals("0005")) {
				tempData = getData.Media_0005(document, dataCheckList);
			}else if(selectedMedia.getMedia_id().equals("0006")) {
				tempData = getData.Media_0006(document, dataCheckList);
			}else if(selectedMedia.getMedia_id().equals("0007")) {	// 다음
				tempData = PortalCheck(document, dataCheckList);
			}else if(selectedMedia.getMedia_id().equals("0008")) {
				tempData = getData.Media_0008(document, dataCheckList);
			}else if(selectedMedia.getMedia_id().equals("0009")) {
				tempData = getData.Media_0009(document, dataCheckList);
			}else if(selectedMedia.getMedia_id().equals("0010")) {
				tempData = getData.Media_0010(document, dataCheckList);
			}else if(selectedMedia.getMedia_id().equals("0011")) {
				tempData = getData.Media_0011(document, dataCheckList);
			}else if(selectedMedia.getMedia_id().equals("0012")) {
				tempData = getData.Media_0012(document, dataCheckList);
			}else if(selectedMedia.getMedia_id().equals("0013")) {
				tempData = getData.Media_0013(document, dataCheckList);
			}else if(selectedMedia.getMedia_id().equals("0014")) {
				tempData = getData.Media_0014(document, dataCheckList);
			}else if(selectedMedia.getMedia_id().equals("0015")) {
				tempData = getData.Media_0015(document, dataCheckList);
			}else if(selectedMedia.getMedia_id().equals("0016")) {
				tempData = getData.Media_0016(document, dataCheckList);
			}else if(selectedMedia.getMedia_id().equals("0017")) {
				tempData = getData.Media_0017(document, dataCheckList);
			}else if(selectedMedia.getMedia_id().equals("0018")) {
				tempData = getData.Media_0018(document, dataCheckList);
			}else if(selectedMedia.getMedia_id().equals("0019")) {
				tempData = getData.Media_0019(document, dataCheckList);
			}else if(selectedMedia.getMedia_id().equals("0020")) {
				tempData = getData.Media_0020(document, dataCheckList);
			}else if(selectedMedia.getMedia_id().equals("0021")) {
				tempData = getData.Media_0021(document, dataCheckList);
			}else if(selectedMedia.getMedia_id().equals("0022")) {
				tempData = getData.Media_0022(document, dataCheckList);
			}else if(selectedMedia.getMedia_id().equals("0023")) {
				tempData = getData.Media_0023(document, dataCheckList);
			}else if(selectedMedia.getMedia_id().equals("0024")) {
				tempData = getData.Media_0024(document, dataCheckList);
			}else if(selectedMedia.getMedia_id().equals("0025")) {
				tempData = getData.Media_0025(document, dataCheckList);
			}else if(selectedMedia.getMedia_id().equals("0026")) {
				tempData = getData.Media_0026(document, dataCheckList);
			}else if(selectedMedia.getMedia_id().equals("0027")) {
				tempData = getData.Media_0027(document, dataCheckList);
			}
			
			result.setTitle(tempData.getTitle());
			result.setContent(tempData.getContent());
			result.setAuthor_nm(tempData.getAuthor_nm());
			result.setAuthor_email(tempData.getAuthor_email());
		}
		
		return result;
	}
    
    public MHNA010VO PortalCheck(Document document, List<MHNC99902VO> dataCheckList) {
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
				result.setTitle(dataElement.attr(dataCheck.getData()));
        	}else if(dataCheck.getType().equals("CONTENTS")) {
        		dataElement = document.select(dataCheck.getSelection()).first();
				result.setContent(dataElement.attr(dataCheck.getData()));
        	}else if(dataCheck.getType().equals("ORIGINAL")) {
        		try {
        			dataElement = document.select(dataCheck.getSelection()).first();
        			String subUrl = dataElement.attr(dataCheck.getData());
        			document = Jsoup.connect(subUrl).get();
        			subUrl = document.baseUri();
        			MHNA01001VO mhna01001VO = new MHNA01001VO();
        			mhna01001VO.setNews_url(subUrl);
        			temp = Register(mhna01001VO);
				} catch (Exception e) { 					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}
        }
    	
    	result.setAuthor_email(temp.getAuthor_email());
    	result.setAuthor_nm(temp.getAuthor_nm());
//    	result.setAuthor_email(temp.getAuthor_email());
		
    	return result;
    }
    
	@RequestMapping(value = "/scoreUpdate.json")
	public ModelAndView scoreUpdate(@ModelAttribute("mhnd010VO") MHND010VO mhnd010VO, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception{
		
		ModelAndView modelAndView = new ModelAndView();
		MappingJackson2JsonView jsonView = new MappingJackson2JsonView();
		modelAndView.setView(jsonView);
		
		// 점수 등록.
		List<String> scoreList = mhnd010VO.getScoreList();
		for(int i = 0; i < scoreList.size(); i++) {
			mhnd010VO.setType_cd(scoreList.get(i));
			mhnd010VO.setScore(1);
			dao.insert("d010.insertScore", mhnd010VO);
		}
		String rediredUrl = "redirect:/a010d" + mhnd010VO.getNews_id() + ".do";
		System.out.println(rediredUrl);
		return modelAndView;
	}
}

