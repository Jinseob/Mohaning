package com.mohaning.app.Controller;

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

import org.json.JSONArray;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mohaning.app.GetDataController;
import com.mohaning.app.Dao.CmmnDao;
import com.mohaning.app.Model.MHNN01001VO;
import com.mohaning.app.Model.MHNB010VO;
import com.mohaning.app.Model.MHNB01001VO;
import com.mohaning.app.Model.MHNA01001VO;
import com.mohaning.app.Model.MHNC99901VO;
import com.mohaning.app.Model.MHNC99902VO;
import com.mohaning.app.Model.MHND010VO;
import com.mohaning.app.Model.SearchOptionVO;

@Controller
public class NewsController {
	private static final Logger logger = LoggerFactory.getLogger(NewsController.class);

	@Autowired
	private CmmnDao dao;
	
	// 기사
	@RequestMapping(value = "/News.do")
	public String newsMain(@ModelAttribute("searchOptionVO") SearchOptionVO searchOptionVO, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception{
		
		@SuppressWarnings("unchecked")
		List<MHNB010VO> list = (List<MHNB010VO>) dao.selectList("n010.selectNewsList", searchOptionVO);
		model.addAttribute("resultList", list);
		
		return "mhnn010/n010l";
	}
	
	@RequestMapping(value = "/n010i.do")
	public String newsInsert(@ModelAttribute("searchOptionVO") SearchOptionVO searchOptionVO, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception{
		
		return "mhnn010/n010i";
	}
	
	@RequestMapping(value = "/NewsRegister.do", method=RequestMethod.POST)
	public String NewsRegister(@ModelAttribute("mhnn01001VO") MHNN01001VO mhnn01001VO, ModelMap model, HttpServletRequest request, 
			HttpServletResponse response, HttpSession session) throws Exception{
		
		MHNB010VO result = Register(mhnn01001VO);
		
		System.out.println(result.getMedia_nm());
		System.out.println(result.getMedia_id() + " : " + result.getOrigin_media_id());
		System.out.println(result.getNews_title());
		model.addAttribute("type", "NEW");
		model.addAttribute("result", result);
		
		return "mhnn010/n010i";
	}
	
	// SSL 확인 및 언론사 정보 가져오기.
	public MHNB010VO Register(MHNN01001VO mhnn01001VO) throws Exception {
		String url = mhnn01001VO.getNews_url();
		
		// 1. SSL 있는지 확인 및 우회 처리
        if(url.indexOf("https://") > -1){
        	NewsController.setSSL();
        }
		
        // 2. 언론사 정보 가지고 오기. (네이버, 다음은 포탈이지만 기사를 포탈용으로 제공받으므로 포함됨)
		@SuppressWarnings("unchecked")
		List<MHNC99901VO> mediaList = (List<MHNC99901VO>) dao.selectList("c999.selectMediaList", mhnn01001VO);
		
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
		
		MHNB010VO result = new MHNB010VO();
		if(chk) {
			// 4. Doc Id, Title, Content, Author, Email 등을 가져오는 클래스 실행.
			result = DataRegister(mhnn01001VO, selectedMedia);
			result.setNews_url(mhnn01001VO.getNews_url());
			result.setMedia_id(selectedMedia.getMedia_id());
			result.setMedia_nm(selectedMedia.getMedia_nm());
		}else {
			System.out.println("아직 해당 언론사는 지원하지 않습니다.");
		}
		
		return result;
	}
	
	// 확인된 언론사의 데이터 가져오기
    public MHNB010VO DataRegister(MHNN01001VO mhnn01001VO, MHNC99901VO selectedMedia) throws Exception{
		MHNB010VO result = new MHNB010VO();
		
		// 4. 찾은 언론사의 정보 추출 방법 가지고 오기.
		@SuppressWarnings("unchecked")
		List<MHNC99902VO> dataCheckList = (List<MHNC99902VO>) dao.selectList("c999.selectDataCheckList", selectedMedia);
		
		// 5. 크롤링
        Document document = Jsoup.connect(mhnn01001VO.getNews_url()).get();
		
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
				URL doc_url = new URL(mhnn01001VO.getNews_url());
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
//							data = dataElement.attr(dataCheck.getData_tag());
		}
		document.attr("docid", result.getDoc_id());	// getData 에서 doc id 를 사용하기 위해 설정.
		
		// 6-1 Docid 와 언론사 ID 를 사용하여 기등록된 기사가 있는지 확인하는 부분.
		@SuppressWarnings("unchecked")
		List<MHNN01001VO> checkRegisteredNews = (List<MHNN01001VO>) dao.selectList("n010.checkRegisteredNews", result);
		if(checkRegisteredNews.size() > 0) {
			System.out.println("기 등록된 기사가 있습니다.");
		}else {
			MHNB010VO tempData = new MHNB010VO();
			GetDataController getData = new GetDataController();
			if(selectedMedia.getMedia_id().equals("0001")) {
				tempData = getData.Media_0001(document, dataCheckList);
				result.setOrigin_media_id(selectedMedia.getMedia_id());
			}else if(selectedMedia.getMedia_id().equals("0002")) {
				tempData = getData.Media_0002(document, dataCheckList);
				result.setOrigin_media_id(selectedMedia.getMedia_id());
			}else if(selectedMedia.getMedia_id().equals("0003")) {	// 네이버
				tempData = PortalCheck(document, dataCheckList, selectedMedia);
				result.setOrigin_id(tempData.getOrigin_id());
				result.setOrigin_media_id(tempData.getOrigin_media_id());
			}else if(selectedMedia.getMedia_id().equals("0004")) {
				tempData = getData.Media_0004(document, dataCheckList);
				result.setOrigin_media_id(selectedMedia.getMedia_id());
			}else if(selectedMedia.getMedia_id().equals("0005")) {
				tempData = getData.Media_0005(document, dataCheckList);
				result.setOrigin_media_id(selectedMedia.getMedia_id());
			}else if(selectedMedia.getMedia_id().equals("0006")) {
				tempData = getData.Media_0006(document, dataCheckList);
				result.setOrigin_media_id(selectedMedia.getMedia_id());
			}else if(selectedMedia.getMedia_id().equals("0007")) {	// 다음
				tempData = PortalCheck(document, dataCheckList,selectedMedia);
				result.setOrigin_id(tempData.getOrigin_id());
				result.setOrigin_media_id(tempData.getOrigin_media_id());
			}else if(selectedMedia.getMedia_id().equals("0008")) {
				tempData = getData.Media_0008(document, dataCheckList);
				result.setOrigin_media_id(selectedMedia.getMedia_id());
			}else if(selectedMedia.getMedia_id().equals("0009")) {
				tempData = getData.Media_0009(document, dataCheckList);
				result.setOrigin_media_id(selectedMedia.getMedia_id());
			}else if(selectedMedia.getMedia_id().equals("0010")) {
				tempData = getData.Media_0010(document, dataCheckList);
				result.setOrigin_media_id(selectedMedia.getMedia_id());
			}else if(selectedMedia.getMedia_id().equals("0011")) {
				tempData = getData.Media_0011(document, dataCheckList);
				result.setOrigin_media_id(selectedMedia.getMedia_id());
			}else if(selectedMedia.getMedia_id().equals("0012")) {
				tempData = getData.Media_0012(document, dataCheckList);
				result.setOrigin_media_id(selectedMedia.getMedia_id());
			}else if(selectedMedia.getMedia_id().equals("0013")) {
				tempData = getData.Media_0013(document, dataCheckList);
				result.setOrigin_media_id(selectedMedia.getMedia_id());
			}else if(selectedMedia.getMedia_id().equals("0014")) {
				tempData = getData.Media_0014(document, dataCheckList);
				result.setOrigin_media_id(selectedMedia.getMedia_id());
			}else if(selectedMedia.getMedia_id().equals("0015")) {
				tempData = getData.Media_0015(document, dataCheckList);
				result.setOrigin_media_id(selectedMedia.getMedia_id());
			}else if(selectedMedia.getMedia_id().equals("0016")) {
				tempData = getData.Media_0016(document, dataCheckList);
				result.setOrigin_media_id(selectedMedia.getMedia_id());
			}else if(selectedMedia.getMedia_id().equals("0017")) {
				tempData = getData.Media_0017(document, dataCheckList);
				result.setOrigin_media_id(selectedMedia.getMedia_id());
			}else if(selectedMedia.getMedia_id().equals("0018")) {
				tempData = getData.Media_0018(document, dataCheckList);
				result.setOrigin_media_id(selectedMedia.getMedia_id());
			}else if(selectedMedia.getMedia_id().equals("0019")) {
				tempData = getData.Media_0019(document, dataCheckList);
				result.setOrigin_media_id(selectedMedia.getMedia_id());
			}else if(selectedMedia.getMedia_id().equals("0020")) {
				tempData = getData.Media_0020(document, dataCheckList);
				result.setOrigin_media_id(selectedMedia.getMedia_id());
			}else if(selectedMedia.getMedia_id().equals("0021")) {
				tempData = getData.Media_0021(document, dataCheckList);
				result.setOrigin_media_id(selectedMedia.getMedia_id());
			}else if(selectedMedia.getMedia_id().equals("0022")) {
				tempData = getData.Media_0022(document, dataCheckList);
				result.setOrigin_media_id(selectedMedia.getMedia_id());
			}else if(selectedMedia.getMedia_id().equals("0023")) {
				tempData = getData.Media_0023(document, dataCheckList);
				result.setOrigin_media_id(selectedMedia.getMedia_id());
			}else if(selectedMedia.getMedia_id().equals("0024")) {
				tempData = getData.Media_0024(document, dataCheckList);
				result.setOrigin_media_id(selectedMedia.getMedia_id());
			}else if(selectedMedia.getMedia_id().equals("0025")) {
				tempData = getData.Media_0025(document, dataCheckList);
				result.setOrigin_media_id(selectedMedia.getMedia_id());
			}else if(selectedMedia.getMedia_id().equals("0026")) {
				tempData = getData.Media_0026(document, dataCheckList);
				result.setOrigin_media_id(selectedMedia.getMedia_id());
			}else if(selectedMedia.getMedia_id().equals("0027")) {
				tempData = getData.Media_0027(document, dataCheckList);
				result.setOrigin_media_id(selectedMedia.getMedia_id());
			}
			
			result.setNews_title(tempData.getNews_title());
			result.setNews_contents(tempData.getNews_contents());
			result.setAuthor_nm(tempData.getAuthor_nm());
			result.setAuthor_email(tempData.getAuthor_email());

			// 기자 id 생성 또는 가져오기.
			MHNA01001VO authorInfo = new MHNA01001VO();
			authorInfo.setAuthor_nm(result.getAuthor_nm());
			authorInfo.setAuthor_email(result.getAuthor_email());
			authorInfo.setMedia_id(result.getOrigin_media_id());
			
			int authorCnt = dao.selectCnt("a010.selectAuthorCnt", authorInfo);
			if(authorCnt > 0) {	// 기 등록된 기자가 있으면 ID 가지고 와서 넣기
				authorInfo = (MHNA01001VO) dao.select("a010.selectAuthor", authorInfo);
			}else {	// 등록된 기자가 없으면 등록하기.
				dao.insert_return("a010.insertAuthor", authorInfo);
			}
			result.setAuthor_id(authorInfo.getAuthor_id());
		}
		
		return result;
	}
    
    public MHNB010VO PortalCheck(Document document, List<MHNC99902VO> dataCheckList, MHNC99901VO selectedMedia) {
    	MHNB010VO result = new MHNB010VO();
    	MHNB010VO temp = new MHNB010VO();
    	
    	MHNC99902VO dataCheck = null;
    	Element dataElement = null;
    	for(int i = 0; i < dataCheckList.size(); i++) {
        	dataCheck = new MHNC99902VO();
        	dataCheck = dataCheckList.get(i);
        	
        	// Title, Content 는 거의 공통.
        	if(dataCheck.getType().equals("TITLE")) {
        		dataElement = document.select(dataCheck.getSelection()).first();
				result.setNews_title(dataElement.attr(dataCheck.getData()));
        	}else if(dataCheck.getType().equals("CONTENTS")) {
        		dataElement = document.select(dataCheck.getSelection()).first();
				result.setNews_contents(dataElement.attr(dataCheck.getData()));
        	}else if(dataCheck.getType().equals("ORIGINAL")) {
        		try {
        			dataElement = document.select(dataCheck.getSelection()).first();
        			String subUrl = dataElement.attr(dataCheck.getData());
        			document = Jsoup.connect(subUrl).get();
        			subUrl = document.baseUri();
        			MHNN01001VO mhnn01001VO = new MHNN01001VO();
        			mhnn01001VO.setNews_url(subUrl);
        			temp = Register(mhnn01001VO);
				} catch (Exception e) { 					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}
        }
    	
    	result.setAuthor_email(temp.getAuthor_email());
    	result.setAuthor_nm(temp.getAuthor_nm());
    	result.setMedia_id(selectedMedia.getMedia_id());
    	result.setOrigin_media_id(temp.getMedia_id());
    	result.setOrigin_id(temp.getDoc_id());
		
    	return result;
    }
    
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
	
	@RequestMapping(value = "/processUpdate_n010.do")
	public String processUpdate(@ModelAttribute("mhnb010VO") MHNB010VO mhnb010VO, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception{
		
		// 저자가 등록되어 있지 않으면 저장하기.
		if(mhnb010VO.getAuthor_id().isEmpty()) {
			dao.insert("a010.insertAuthor", mhnb010VO);	// 우선 기자를 등록하고 등록된 기자의 ID 를 가지고 온다.
		}
		dao.insert_return("n010.insertNews", mhnb010VO);	// 기자 ID 를 news 에 넣는다.
		
		// 저장 성공시 Detail 화면으로 간다.
		String rediredUrl = "redirect:/n010d" + mhnb010VO.getNews_id() + ".do";
		System.out.println(rediredUrl);
		return rediredUrl;
	}
	
	@RequestMapping(value = "/n010d{news_id}.do")
	public String boardDetail(@PathVariable String news_id, @ModelAttribute("mhnb010VO") MHNB010VO mhnb010VO, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception{
		
		mhnb010VO.setNews_id(news_id);

		// 조회수 올리는 로직 넣기
		dao.update("n010.updateCnt", mhnb010VO);
		
		// 뉴스 기사와 기자, 언론사 정보 가지고 오는 부분.
		MHNB010VO result = (MHNB010VO) dao.select("n010.selectNews", mhnb010VO);
		System.out.println(result.getContents());
		model.addAttribute("result", result);
		
		// 개인별 반영 점수 가지고 오는 부분
		MHND010VO news_info = new MHND010VO();
		news_info.setNews_id(news_id);
		news_info.setReg_id("admin");
		@SuppressWarnings("unchecked")
		List<MHND010VO> scoreListByUser = (List<MHND010VO>) dao.selectList("d010.selectScoreListByUser", news_info);
		model.addAttribute("scoreListByUser", scoreListByUser);
		
		// 평균 점수 가지고 오는 부분
		@SuppressWarnings("unchecked")
		List<MHND010VO> score = (List<MHND010VO>) dao.selectList("d010.selectScoreListByNews", news_info);
		JSONArray jsonArray = new JSONArray(score);
		model.addAttribute("score", jsonArray);
		
		// 뉴스 기사를 사용한 토론 불러오기.
		SearchOptionVO searchOptionVO = new SearchOptionVO();
		@SuppressWarnings("unchecked")
		List<MHNB01001VO> boardList = (List<MHNB01001VO>) dao.selectList("b010.selectBoardList", searchOptionVO);
		model.addAttribute("boardList", boardList);
		
		return "mhnn010/n010d";
	}
//	
//	@RequestMapping(value = "/b0010/b0010D.do")
//	public String boardDetail(@ModelAttribute("b0010VO") B0010VO b0010VO, ModelMap model, 
//			HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception{
//		
//		B0010VO detail = new B0010VO();
//		detail = (B0010VO) dao.select("b0010.select_b0010", b0010VO);
//		model.addAttribute("detail", detail);
//		
//		return "b0010/b0010D";
//	}
//	
//	@RequestMapping(value = "/b0010/b0010{procType}.do")
//	public String boardUpdate(@PathVariable String procType, @ModelAttribute("b0010VO") B0010VO b0010VO,
//			ModelMap model, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception{
//		
////		여기 들어가기전에 session 정보가 있는지 확인 필요.
//		
//		String url = "";
//		
//		if(procType.equals("INS")) {
//			url = "I";
//			
//			dao.insert("b0010.insert_b0010", b0010VO);	// dao 에 null 값이 들어가있음. 왜일까.
//		}else if(procType.equals("UPD")) {
//			url = "U";
//			
//			dao.update("b0010.update_b0010", b0010VO);
//		}else if(procType.equals("DEL")) {
//			url = "L";
//			
//			dao.delete("b0010.delete_b0010", b0010VO);
//		}
//		
//		return "b0010/b0010" + url;
//	}
}
