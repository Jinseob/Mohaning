package com.mohaning.app.Controller;

import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
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
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.mohaning.app.Const;
import com.mohaning.app.GetDataController;
import com.mohaning.app.NewsValidator;
import com.mohaning.app.Dao.CmmnDao;
import com.mohaning.app.Model.MHNA01001VO;
import com.mohaning.app.Model.MHNB01001VO;
import com.mohaning.app.Model.MHNC99901VO;
import com.mohaning.app.Model.MHNC99902VO;
import com.mohaning.app.Model.MHND010VO;
import com.mohaning.app.Model.MHNN01001VO;
import com.mohaning.app.Model.MHNP01001VO;
import com.mohaning.app.Model.PagingVO;
import com.mohaning.app.Model.SearchOptionVO;

@Controller
public class NewsController {
	private static final Logger logger = LoggerFactory.getLogger(NewsController.class);

	@Autowired
	private CmmnDao dao;
	
	// 기사
	@RequestMapping(value = "/News/main.do")
	public String newsMain(@ModelAttribute("searchOptionVO") SearchOptionVO searchOptionVO, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception{
		
//		int totallength = Const.totalpage * Const.length;	// 한 화면에 Count 할 총 건수
		searchOptionVO.setType(Const.MIX);
		int resultCnt = dao.selectCnt("n010.selectNewsCount", searchOptionVO);
		model.addAttribute("resultCnt", resultCnt);		// 총 건수 표시.
		int totalPage = (int) Math.ceil((double)resultCnt / Const.length );
//		model.addAttribute("totalPage", totalPage);
		
		// Paging 용 정보를 다 만들어서 배열로 넣어준다면? page, currpage, href, 총 페이지는 배열의 수
		int unitPage = ( searchOptionVO.getPage() - 1 ) / Const.totalpage;
		PagingVO pagingVO = null;
		List<PagingVO> pagingList = new ArrayList<PagingVO>();
		for(int i = 1; i <= totalPage; i++) {
			pagingVO = new PagingVO();
			pagingVO.setIdx((unitPage * Const.totalpage) + i);
			pagingVO.setPage(searchOptionVO.getPage());
			pagingList.add(pagingVO);
		}
		model.addAttribute("pagingList", pagingList);
		
		int offset = ( searchOptionVO.getPage() - 1 ) * searchOptionVO.getLength();
		searchOptionVO.setOffset(offset);
		@SuppressWarnings("unchecked")
		List<MHNN01001VO> list = (List<MHNN01001VO>) dao.selectList("n010.selectNewsList", searchOptionVO);
		model.addAttribute("resultList", list);
		
		return "mhnn010/n010l";
	}
	
	@RequestMapping(value = "/News/n010i.do")
	public String newsInsert(@ModelAttribute("searchOptionVO") SearchOptionVO searchOptionVO, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception{
		
		return "mhnn010/n010i";
	}

//	@RequestMapping(value = "/News/processUpdate.do")
//	public String processUpdate(@ModelAttribute("mhnn01001VO") MHNN01001VO mhnn01001VO, ModelMap model, 
//			HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception{
//		
//		// 저자가 등록되어 있지 않으면 저장하기.
//		if(mhnn01001VO.getAuthor_id().isEmpty()) {
//			MHNA01001VO authorInfo = new MHNA01001VO();
//			authorInfo.setAuthor_id(mhnn01001VO.getAuthor_id());
//			authorInfo.setAuthor_email(mhnn01001VO.getAuthor_email());
//			authorInfo.setAuthor_nm(mhnn01001VO.getAuthor_nm());
//			authorInfo.setMedia_id(mhnn01001VO.getMedia_id());
//			
//			dao.insert("a010.insertAuthor", authorInfo);	// 우선 기자를 등록하고 등록된 기자의 ID 를 가지고 온다.
//			mhnn01001VO.setAuthor_id(authorInfo.getAuthor_id());
//		}
//		dao.insert_return("n010.insertNews", mhnn01001VO);		// News 를 저장한다.
//
//		// 포털 URL 이 있는 경우 저장 되었는지 확인하는 부분.
//		int portalCnt = -1;	// -1 이면 포털 ID 가 없는 경우. 0 이상이면 포털 ID 가 있는 경우.
//		if(!mhnn01001VO.getPortal_id().isEmpty()) {	// 포털 URL 이 잇는 경우 포털 ID 가 있다.
//			portalCnt = dao.selectCnt("p010.selectPortalByNewsCnt", mhnn01001VO);
//		}
//		
//		if(portalCnt == 0) {
//			System.out.println("Portal Count : " + portalCnt);
//			dao.insert_return("p010.insertPortal", mhnn01001VO);	// 포탈에서 가져온 경우 포털 정보를 저장한다.
//		}
//		
//		// 저장 성공시 Detail 화면으로 간다.
//		String rediredUrl = "redirect:/News/n010d" + mhnn01001VO.getNews_id() + ".do";
//		System.out.println(rediredUrl);
//		return rediredUrl;
//	}
	
	public MHNN01001VO processUpdate(MHNN01001VO mhnn01001VO){
		
		try {
			// 저자가 등록되어 있지 않으면 저장하기.
			if(mhnn01001VO.getAuthor_id().isEmpty()) {
				int authorCnt = 0;
				MHNA01001VO authorInfo = new MHNA01001VO();
//				authorInfo.setAuthor_id(mhnn01001VO.getAuthor_id());
				authorInfo.setAuthor_email(mhnn01001VO.getAuthor_email());
				authorInfo.setAuthor_nm(mhnn01001VO.getAuthor_nm());
				authorInfo.setMedia_id(mhnn01001VO.getMedia_id());
				
				if(authorInfo.getAuthor_email().isEmpty()) authorCnt++;
				if(authorInfo.getAuthor_nm().isEmpty()) authorCnt++;
				if(authorInfo.getMedia_id().isEmpty()) authorCnt++;
				
				if(authorCnt < 2) {
					dao.insert_return("a010.insertAuthor", authorInfo);
					mhnn01001VO.setAuthor_id(authorInfo.getAuthor_id());
				}
			}
			
			// 기사 저장
			dao.insert_return("n010.insertNews", mhnn01001VO);
			
			// 기사 상태 저장
			mhnn01001VO.setId(mhnn01001VO.getNews_id());
			mhnn01001VO.setType(Const.NEWS);
			dao.insert("m010.insertStatus", mhnn01001VO);

			// 포털 URL 이 있는 경우 저장 되었는지 확인하는 부분.
			int portalCnt = -1;	// -1 이면 포털 ID 가 없는 경우. 0 이상이면 포털 ID 가 있는 경우.
			if(!mhnn01001VO.getPortal_id().isEmpty()) {	// 포털 URL 이 잇는 경우 포털 ID 가 있다.
				portalCnt = dao.selectCnt("p010.selectPortalByNewsCnt", mhnn01001VO);
			}
			
			if(portalCnt == 0) {
				System.out.println("Portal Count : " + portalCnt);
				dao.insert_return("p010.insertPortal", mhnn01001VO);
			}
		}catch(Exception e) {
			mhnn01001VO.setStatus(Const.ERROR50);
			mhnn01001VO.setMsg(Const.MSG50);
			// log 에 남기기. 
			e.printStackTrace();
		}
		
		return mhnn01001VO;
	}
	
	@RequestMapping(value = "/News/n010d{news_id}.do")
	public String boardDetail(@PathVariable String news_id, @ModelAttribute("mhnn01001VO") MHNN01001VO mhnn01001VO, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception{
		
		mhnn01001VO.setNews_id(news_id);

		// 조회수 올리는 로직 넣기
		dao.update("n010.updateCnt", mhnn01001VO);
		
		// 뉴스 기사와 기자, 언론사 정보 가지고 오는 부분.
		MHNN01001VO result = (MHNN01001VO) dao.select("n010.selectNews", mhnn01001VO);
		model.addAttribute("result", result);
		
		// 포털 정보가 있는 경우 가지고 오는 부분.
		@SuppressWarnings("unchecked")
		List<MHNP01001VO> portalList = (List<MHNP01001VO>) dao.selectList("p010.selectPortalByNews", result);
		model.addAttribute("portalList", portalList);
		
		// 개인별 반영 점수 가지고 오는 부분
		MHND010VO news_info = new MHND010VO();
		news_info.setNews_id(news_id);
		news_info.setReg_id("admin");
		@SuppressWarnings("unchecked")
		List<MHND010VO> scoreListByUser = (List<MHND010VO>) dao.selectList("d010.selectScoreListByUser", result);
		model.addAttribute("scoreListByUser", scoreListByUser);
		
		// 평균 점수 가지고 오는 부분
		@SuppressWarnings("unchecked")
		List<MHND010VO> newsScore = (List<MHND010VO>) dao.selectList("d010.selectScoreListByNews", result);
		JSONArray newsJsonArray = new JSONArray(newsScore);
		model.addAttribute("newsScore", newsJsonArray);
		
		// 언론사 점수 가지고 오는 부분
		@SuppressWarnings("unchecked")
		List<MHND010VO> authorScore = (List<MHND010VO>) dao.selectList("d010.selectAuthorScore", result);
		JSONArray authorJsonArray = new JSONArray(authorScore);
		model.addAttribute("authorScore", authorJsonArray);
		
		// 기자 점수 가지고 오는 부분
		@SuppressWarnings("unchecked")
		List<MHND010VO> mediaScore = (List<MHND010VO>) dao.selectList("d010.selectMediaScore", result);
		JSONArray mediaJsonArray = new JSONArray(mediaScore);
		model.addAttribute("mediaScore", mediaJsonArray);
		
		// 뉴스 기사를 사용한 토론 불러오기.
		SearchOptionVO searchOptionVO = new SearchOptionVO();
		searchOptionVO.setNews_id(news_id);
		@SuppressWarnings("unchecked")
		List<MHNB01001VO> boardList = (List<MHNB01001VO>) dao.selectList("b010.selectBoardList", searchOptionVO);
		
		model.addAttribute("boardList", boardList);
		
		return "mhnn010/n010d";
	}
	
	@RequestMapping(value = "/NewsRegister.json", method=RequestMethod.POST)
	public ModelAndView NewsRegister(@ModelAttribute("mhnn01001VO") MHNN01001VO mhnn01001VO, ModelMap model, HttpServletRequest request, 
			HttpServletResponse response, HttpSession session, BindingResult bindingResult) throws Exception{
		
		ModelAndView modelAndView = new ModelAndView();
		MappingJackson2JsonView jsonView = new MappingJackson2JsonView();
		modelAndView.setView(jsonView);
		
		MHNN01001VO result = new MHNN01001VO();
		
		// 1. Portal, SSL, Media Check.
		result = DataCheckForURL(mhnn01001VO);
		
		// 2. Url 확인.
//		result.setStatus(Const.STATUS10);	// 임시 저장.
		if(result.getMedia_id() != null && !result.getMedia_id().isEmpty()) {
			result = DataExtract(result);
		}
		
		// 3. 필수 항목, 선택 항목. 등록이 안된 경우만 진행하기.
		if(!result.getStatus().equals(Const.STATUSAL)){
			NewsValidator newsValidator = new NewsValidator();
			newsValidator.validate(result, bindingResult);
			
			List<FieldError> errors = bindingResult.getFieldErrors();
			FieldError error = null;
			int e10Cnt = 0, e20Cnt = 0, e30Cnt = 0, e40Cnt = 0;
			String eLog = "";
			for(int i = 0; i < errors.size(); i++) {
				error = errors.get(i);
				if(error.getCode().equals(Const.ERROR10)) e10Cnt++;
				if(error.getCode().equals(Const.ERROR20)) e20Cnt++;
				if(error.getCode().equals(Const.ERROR30)) e30Cnt++;
				if(error.getCode().equals(Const.ERROR40)) e40Cnt++;
				eLog += error.getField() + ",";
			}
			
			String eCd = Const.STATUS10;	// URL 확인. 제목 없는 경우.
			
			// 4. 필수 항목 및 기타 항목 확인.
			if(e20Cnt > 0) {	// Doc_id 없는 경우.
				eCd = Const.STATUS20;
			}else if(e30Cnt > 0) {	// 필수 항목외에 없는 경우.
				eCd = Const.STATUS30;
			}else if(e40Cnt > 0) {	// 필수 항목외에 없는 경우.
				eCd = Const.STATUS40;
			}
			result.setStatus(eCd);
			result.setLog(eLog);
			
			// 저장 후 ID 가지고 와서 result 에 넣어주기.
			result = processUpdate(result);
			
			// 성공시 -> 등록되었습니다. 알림창 띄우기 -> 확인 시 기사 상세 화면으로 이동.
			// 실패시 -> 등록에 실패하였습니다. 다시 등록해주세요. -> 확인 시 기사 등록 화면으로 이동.
		}
		
		model.addAttribute("result", result);
		
		return modelAndView;
	}
	
	// 1. Portal, SSL Check.
	public MHNN01001VO DataCheckForURL(MHNN01001VO mhnn01001VO) throws Exception {
		MHNN01001VO retObj = new MHNN01001VO();
		String url = mhnn01001VO.getNews_url();
		
		// 1. SSL 있는지 확인 및 우회 처리
        if(url.indexOf("https://") > -1){
        	NewsController.setSSL();
        }
		
        // 2. 언론사, 포털 정보 가져오기.
        MHNC99901VO selectedMedia = SelectMedia(mhnn01001VO);
		
		// 3. 포탈 여부 확인. 포탈일 경우 원문 URL 추출.
		if(selectedMedia.getMedia_id().equals("0007") || selectedMedia.getMedia_id().equals("0003")) {	// 다음 || 네이버
			// 포털의 ID 와 URL 을 다른 필드에 저장.
			retObj.setPortal_id(selectedMedia.getMedia_id());
			retObj.setPortal_url(url);
			
			// 포탈별 URL 추출 방법 가져오기.
			@SuppressWarnings("unchecked")
			List<MHNC99902VO> dataCheckList = (List<MHNC99902VO>) dao.selectList("c999.selectDataCheckList", selectedMedia);
			
			// 포탈 크롤링
	        Document document = Jsoup.connect(url).get();
	    	
	    	MHNC99902VO dataCheck = null;
	    	Element dataElement = null;
	    	for(int i = 0; i < dataCheckList.size(); i++) {
	        	dataCheck = new MHNC99902VO();
	        	dataCheck = dataCheckList.get(i);
	        	
	        	// Title, Content 는 거의 공통.
	        	if(dataCheck.getType().equals("ORIGINAL")) {
	        		try {
	        			dataElement = document.select(dataCheck.getSelection()).first();
	        			String subUrl = dataElement.attr(dataCheck.getData());
	        			document = Jsoup.connect(subUrl).get();
	        			retObj.setNews_url(document.baseUri());
					} catch (Exception e) { 					// TODO Auto-generated catch block
						e.printStackTrace();
					}
	        	}
	        }
		}else {
			retObj.setNews_url(url);
		}
		
		// 4. 언론사 정보 가져오기.
		selectedMedia = new MHNC99901VO();
		selectedMedia = SelectMedia(retObj);
		retObj.setMedia_id(selectedMedia.getMedia_id());
		retObj.setMedia_nm(selectedMedia.getMedia_nm());
		retObj.setMedia_url(selectedMedia.getMedia_url());
 		
    	return retObj;
    }
	
	public MHNC99901VO SelectMedia(MHNN01001VO mhnn01001VO) throws Exception {
		String url = mhnn01001VO.getNews_url();
		
		// 2. 언론사 및 포털 정보 가져오기.
 		@SuppressWarnings("unchecked")
 		List<MHNC99901VO> mediaList = (List<MHNC99901VO>) dao.selectList("c999.selectMediaList", null);	// Parameter 상관 없음.
        
 		// 3. 포털 ID 찾기.
 		MHNC99901VO retObj = new MHNC99901VO();
		MHNC99901VO media = new MHNC99901VO();
		for(int i = 0; i < mediaList.size(); i++) {
			media = mediaList.get(i);
			if(url.indexOf(media.getMedia_chk_url()) > -1) {
				retObj = media;
				break;
			}
		}
		
		return retObj;
	}
	
	// SSL 확인 및 언론사 정보 가져오기.
//	public MHNN01001VO Register(MHNN01001VO mhnn01001VO) throws Exception {
//        // 2. 언론사 정보 가지고 오기. (네이버, 다음은 포탈이지만 기사를 포탈용으로 제공받으므로 포함됨)
//		@SuppressWarnings("unchecked")
//		List<MHNC99901VO> mediaList = (List<MHNC99901VO>) dao.selectList("c999.selectMediaList", mhnn01001VO);	// Parameter 상관 없음.
//		
//        // 3. 언론사 ID 찾기.
//		Boolean chk = false;	// 언론사 있는 경우 true.
//		MHNC99901VO selectedMedia = new MHNC99901VO();
//		MHNC99901VO media = new MHNC99901VO();
//		for(int i = 0; i < mediaList.size(); i++) {
//			media = mediaList.get(i);
//			if(url.indexOf(media.getMedia_chk_url()) > -1) {
//				chk = true;
//				selectedMedia = media;
//				break;
//			}
//		}
//		
//		// 4. 포탈 여부 확인. 포탈일 경우 원문 URL 추출.
//		if(selectedMedia.getMedia_id().equals("0007")) {	// 다음
//			String subUrl = PortalCheck(selectedMedia);
//			mhnn01001VO.setNews_url(subUrl);
//			
//			// 포털의 ID 와 URL 을 다른 필드에 저장.
//			mhnn01001VO.setPortal_id(selectedMedia.getMedia_id());
//			mhnn01001VO.setPortal_url(url);
//		}else if(selectedMedia.getMedia_id().equals("0003")) {	// 네이버
//			String subUrl = PortalCheck(selectedMedia);
//			mhnn01001VO.setNews_url(subUrl);
//			
//			// 포털의 ID 와 URL 을 다른 필드에 저장.
//			mhnn01001VO.setPortal_id(selectedMedia.getMedia_id());
//			mhnn01001VO.setPortal_url(url);
//		}
//		
//		
//		MHNN01001VO result = new MHNN01001VO();
//		if(chk) {
//			// 4. Doc Id, Title, Content, Author, Email 등을 가져오는 클래스 실행.
//			result = DataRegister(mhnn01001VO, selectedMedia);
//			result.setNews_url(mhnn01001VO.getNews_url());
//			result.setMedia_id(selectedMedia.getMedia_id());
//			result.setMedia_nm(selectedMedia.getMedia_nm());
//		}else {
//			System.out.println("아직 해당 언론사는 지원하지 않습니다.");
//		}
//		
//		return result;
//	}
	
	// 확인된 언론사의 데이터 가져오기
    public MHNN01001VO DataExtract(MHNN01001VO mhnn01001VO) throws Exception{
		// 1. 찾은 언론사의 정보 추출 방법 가지고 오기.
		@SuppressWarnings("unchecked")
		List<MHNC99902VO> dataCheckList = (List<MHNC99902VO>) dao.selectList("c999.selectDataCheckList", mhnn01001VO);
		
		// 2. 크롤링
        Document document = Jsoup.connect(mhnn01001VO.getNews_url()).get();
		
		// 3. 기 등록된 뉴스인지 확인하는 부분. DocID 로 확인.
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
						mhnn01001VO.setDoc_id(param.split(dataCheck.getMethod())[1]);
//						System.out.println("docid : " + param.split(dataCheck.getMethod())[1]);
					}
				}
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {		// 태그에서 DocID 를 가져올 수 있는 경우.
			dataElement = document.select(dataCheck.getSelection()).first();
			if(dataElement != null)	mhnn01001VO.setDoc_id(dataElement.attr(dataCheck.getData()));
//			System.out.println("docid : " + dataElement.attr(dataCheck.getData()));
//			System.out.println("outerHtml : " + dataElement.outerHtml());
//							data = dataElement.attr(dataCheck.getData_tag());
		}
		
		MHNN01001VO checkRegisteredNews = null;
		if(mhnn01001VO.getDoc_id() != null && !mhnn01001VO.getDoc_id().isEmpty()) {
			document.attr("docid", mhnn01001VO.getDoc_id());	// getData 에서 doc id 를 사용하기 위해 설정.
			// 6-1 Docid 와 언론사 ID 를 사용하여 기등록된 기사가 있는지 확인하는 부분.
			checkRegisteredNews = new MHNN01001VO();
			checkRegisteredNews = (MHNN01001VO) dao.select("n010.registeredNews", mhnn01001VO);
		}
		
		if(checkRegisteredNews != null) {
			// mhnn01001VO 여기는 크롤링한 데이터만 있음.
			// 토론에서만 사용.
			mhnn01001VO.setNews_id(checkRegisteredNews.getNews_id());
			String inputURL = mhnn01001VO.getPortal_url();
			String inputPortal = mhnn01001VO.getPortal_id();
			// 기등록된 경우에도 포털의 URL 은 추가된 적이 없을 수 있기 때문에 저장하는 로직 추가. Portal ID 가 있고 doc_id
			// 포털 URL 이 있는 경우 저장 되었는지 확인하는 부분.
			int portalCnt = -1;	// -1 이면 포털 ID 가 없는 경우. 0 이상이면 포털 ID 가 있는 경우.
			if(!mhnn01001VO.getPortal_id().isEmpty()) {	// 포털 URL 이 잇는 경우 포털 ID 가 있다.
				portalCnt = dao.selectCnt("p010.selectPortalByNewsCnt", mhnn01001VO);
			}
			
			if(portalCnt == 0) {
				dao.insert_return("p010.insertPortal", mhnn01001VO);	// 포탈에서 가져온 경우 포털 정보를 저장한다.
			}
			// 토론에서만 사용.
			
			BeanUtils.copyProperties(checkRegisteredNews, mhnn01001VO);
			mhnn01001VO.setStatus(Const.STATUSAL);	// 기사 등록하는 화면에서는 상태값으로 
			
			// 토론에서만 사용.
			mhnn01001VO.setPortal_url(inputURL);	// 토론 등록화면에서 확인하기 위함
			mhnn01001VO.setPortal_id(inputPortal);	// 토론 등록화면에서 확인하기 위함
			// 토론에서만 사용.
		}else {
			mhnn01001VO.setStatus("N");
			MHNN01001VO tempData = new MHNN01001VO();
			GetDataController getData = new GetDataController();
			if(mhnn01001VO.getMedia_id().equals("0001")) {
				tempData = getData.Media_0001(document, dataCheckList);
//				result.setOrigin_media_id(selectedMedia.getMedia_id());
			}else if(mhnn01001VO.getMedia_id().equals("0002")) {
				tempData = getData.Media_0002(document, dataCheckList);
//				result.setOrigin_media_id(selectedMedia.getMedia_id());
//			}else if(selectedMedia.getMedia_id().equals("0003")) {	// 네이버
//				tempData = PortalCheck(document, dataCheckList, selectedMedia);
//				result.setOrigin_id(tempData.getOrigin_id());
//				result.setOrigin_media_id(tempData.getOrigin_media_id());
			}else if(mhnn01001VO.getMedia_id().equals("0004")) {
				tempData = getData.Media_0004(document, dataCheckList);
//				result.setOrigin_media_id(selectedMedia.getMedia_id());
			}else if(mhnn01001VO.getMedia_id().equals("0005")) {
				tempData = getData.Media_0005(document, dataCheckList);
//				result.setOrigin_media_id(selectedMedia.getMedia_id());
			}else if(mhnn01001VO.getMedia_id().equals("0006")) {
				tempData = getData.Media_0006(document, dataCheckList);
//				result.setOrigin_media_id(selectedMedia.getMedia_id());
//			}else if(selectedMedia.getMedia_id().equals("0007")) {	// 다음
//				tempData = PortalCheck(document, dataCheckList,selectedMedia);
//				result.setOrigin_id(tempData.getOrigin_id());
//				result.setOrigin_media_id(tempData.getOrigin_media_id());
			}else if(mhnn01001VO.getMedia_id().equals("0008")) {
				tempData = getData.Media_0008(document, dataCheckList);
//				result.setOrigin_media_id(selectedMedia.getMedia_id());
			}else if(mhnn01001VO.getMedia_id().equals("0009")) {
				tempData = getData.Media_0009(document, dataCheckList);
//				result.setOrigin_media_id(selectedMedia.getMedia_id());
			}else if(mhnn01001VO.getMedia_id().equals("0010")) {
				tempData = getData.Media_0010(document, dataCheckList);
//				result.setOrigin_media_id(selectedMedia.getMedia_id());
			}else if(mhnn01001VO.getMedia_id().equals("0011")) {
				tempData = getData.Media_0011(document, dataCheckList);
//				result.setOrigin_media_id(selectedMedia.getMedia_id());
			}else if(mhnn01001VO.getMedia_id().equals("0012")) {
				tempData = getData.Media_0012(document, dataCheckList);
//				result.setOrigin_media_id(selectedMedia.getMedia_id());
			}else if(mhnn01001VO.getMedia_id().equals("0013")) {
				tempData = getData.Media_0013(document, dataCheckList);
//				result.setOrigin_media_id(selectedMedia.getMedia_id());
			}else if(mhnn01001VO.getMedia_id().equals("0014")) {
				tempData = getData.Media_0014(document, dataCheckList);
//				result.setOrigin_media_id(selectedMedia.getMedia_id());
			}else if(mhnn01001VO.getMedia_id().equals("0015")) {
				tempData = getData.Media_0015(document, dataCheckList);
//				result.setOrigin_media_id(selectedMedia.getMedia_id());
			}else if(mhnn01001VO.getMedia_id().equals("0016")) {
				tempData = getData.Media_0016(document, dataCheckList);
//				result.setOrigin_media_id(selectedMedia.getMedia_id());
			}else if(mhnn01001VO.getMedia_id().equals("0017")) {
				tempData = getData.Media_0017(document, dataCheckList);
//				result.setOrigin_media_id(selectedMedia.getMedia_id());
			}else if(mhnn01001VO.getMedia_id().equals("0018")) {
				tempData = getData.Media_0018(document, dataCheckList);
//				result.setOrigin_media_id(selectedMedia.getMedia_id());
			}else if(mhnn01001VO.getMedia_id().equals("0019")) {
				tempData = getData.Media_0019(document, dataCheckList);
//				result.setOrigin_media_id(selectedMedia.getMedia_id());
			}else if(mhnn01001VO.getMedia_id().equals("0020")) {
				tempData = getData.Media_0020(document, dataCheckList);
//				result.setOrigin_media_id(selectedMedia.getMedia_id());
			}else if(mhnn01001VO.getMedia_id().equals("0021")) {
				tempData = getData.Media_0021(document, dataCheckList);
//				result.setOrigin_media_id(selectedMedia.getMedia_id());
			}else if(mhnn01001VO.getMedia_id().equals("0022")) {
				tempData = getData.Media_0022(document, dataCheckList);
//				result.setOrigin_media_id(selectedMedia.getMedia_id());
			}else if(mhnn01001VO.getMedia_id().equals("0023")) {
				tempData = getData.Media_0023(document, dataCheckList);
//				result.setOrigin_media_id(selectedMedia.getMedia_id());
			}else if(mhnn01001VO.getMedia_id().equals("0024")) {
				tempData = getData.Media_0024(document, dataCheckList);
//				result.setOrigin_media_id(selectedMedia.getMedia_id());
			}else if(mhnn01001VO.getMedia_id().equals("0025")) {
				tempData = getData.Media_0025(document, dataCheckList);
//				result.setOrigin_media_id(selectedMedia.getMedia_id());
			}else if(mhnn01001VO.getMedia_id().equals("0026")) {
				tempData = getData.Media_0026(document, dataCheckList);
//				result.setOrigin_media_id(selectedMedia.getMedia_id());
			}else if(mhnn01001VO.getMedia_id().equals("0027")) {
				tempData = getData.Media_0027(document, dataCheckList);
//				result.setOrigin_media_id(selectedMedia.getMedia_id());
			}
			
			mhnn01001VO.setNews_title(tempData.getNews_title());
			mhnn01001VO.setNews_contents(tempData.getNews_contents());
			mhnn01001VO.setAuthor_nm(tempData.getAuthor_nm());
			mhnn01001VO.setAuthor_email(tempData.getAuthor_email());

			// 기자 id 생성 또는 가져오기.
			MHNA01001VO authorInfo = new MHNA01001VO();
			authorInfo.setAuthor_nm(mhnn01001VO.getAuthor_nm());
			authorInfo.setAuthor_email(mhnn01001VO.getAuthor_email());
			authorInfo.setMedia_id(mhnn01001VO.getMedia_id());
			
			int authorCnt = dao.selectCnt("a010.selectAuthorCnt", authorInfo);
			if(authorCnt > 0) {	// 기 등록된 기자가 있으면 ID 가지고 와서 넣기
				authorInfo = (MHNA01001VO) dao.select("a010.selectAuthor", authorInfo);
//			}else {	// 등록된 기자가 없으면 등록하기.
//				dao.insert_return("a010.insertAuthor", authorInfo);
			}
			mhnn01001VO.setAuthor_id(authorInfo.getAuthor_id());
		}
		
		return mhnn01001VO;
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
