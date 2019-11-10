package com.mohaning.app.Controller.Ajax;

import java.util.List;

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

import com.mohaning.app.Dao.CmmnDao;
import com.mohaning.app.Model.MHNA01001VO;
import com.mohaning.app.Model.MHNA010VO;
import com.mohaning.app.Model.MHNB01001VO;
import com.mohaning.app.Model.MHNC99901VO;
import com.mohaning.app.Model.MHNC99902VO;

@Controller
public class NewsJsonController {
	private static final Logger logger = LoggerFactory.getLogger(NewsJsonController.class);

	@Autowired
	private CmmnDao dao;
		
	@RequestMapping(value = "/NewsRegister.json")
	public ModelAndView NewsRegister(@ModelAttribute("MHNA010VO") MHNA01001VO MHNA010VO, ModelMap model, HttpServletRequest request, 
			HttpServletResponse response, HttpSession session) throws Exception{
		
//		final String btcusdt 	= "BTCUSDT";
//		final String ethusdt 	= "ETHUSDT";
//		final String ltcusdt 	= "LTCUSDT";
//		final String xlmbtc 	= "XLMBTC";
//		final String adabtc 	= "ADABTC";
//		final String qtumbtc 	= "QTUMBTC";
		
		ModelAndView modelAndView = new ModelAndView();
		MappingJackson2JsonView jsonView = new MappingJackson2JsonView();
		modelAndView.setView(jsonView);
		
		String url = MHNA010VO.getNews_url();
		
		// 기 등록된 뉴스인지 확인하는 부분.
		MHNA01001VO checkRegNews = (MHNA01001VO) dao.select("a010.checkRegisteredNews", MHNA010VO);
		if(checkRegNews != null) {
			model.addAttribute("type", "OCC");
			model.addAttribute("result", checkRegNews);
		}else {
			System.out.println("동일한 URL 이 없다.");
			// 기사 내용중 많이 사용된 단어들 정리해서 태그하기.
			
			// 언론사 정보 가지고 오는 부분.
			@SuppressWarnings("unchecked")
			List<MHNC99901VO> mediaList = (List<MHNC99901VO>) dao.selectList("c999.selectMediaList", MHNA010VO);
			
			// 입력된 URL 에 해당하는 언론사 선택.
			MHNC99901VO selectedMedia = new MHNC99901VO();
			MHNC99901VO media = new MHNC99901VO();
			for(int i = 0; i < mediaList.size(); i++) {
				media = mediaList.get(i);
				if(url.indexOf(media.getMedia_url()) > -1) {
					selectedMedia = media;
					break;
				}
			}
			
			// 선택된 언론사의 타이틀 및 정보 가져오는 부분.
			if(!selectedMedia.getMedia_id().equals("")) {
				@SuppressWarnings("unchecked")
				List<MHNC99902VO> dataCheckList = (List<MHNC99902VO>) dao.selectList("c999.selectDataCheckList", selectedMedia);
				
				Document document = Jsoup.connect(url).get();
				
				// Title, Content, Author, Email 등을 추출하는 부분.
				MHNC99902VO dataCheck = new MHNC99902VO();
				Element dataElement = null;
				MHNA010VO result = new MHNA010VO();
				result.setMedia_id(selectedMedia.getMedia_id());
				result.setMedia_nm(selectedMedia.getMedia_nm());
				result.setMedia_url(selectedMedia.getMedia_url());
				for(int i = 0; i < dataCheckList.size(); i++) {
					dataCheck = dataCheckList.get(i);
					dataElement = document.select(dataCheck.getSelect_tag()).first();
					
					String data = new String();
					// Tag 를 사용할 경우 Y. 사용하지 않을 경우는 text() 를 사용.
					if(dataCheck.getUse_tag().equals("Y")) {
						data = dataElement.attr(dataCheck.getData_tag());
					}else {
						data = dataElement.text();
					}
					
					// Name, Email 일 경우 패턴 파악 후 분리.
					if(dataCheck.getType().equals("Name")) {
						// 처음 데이터에 기자명 있음.
						String[] strList = data.split("\\s");	// 빈칸 마다 자르기.
						String str = strList[0];	// 무조건 첫번째 데이터가 기자 이름을 가지고 있음. 영어 이름일 경우는 추가 로직을 넣어야함.
						
						// 베이징=홍길동 형식만 우선 처리. 다른 형태가 있는 경우 추후 처리.
						if(str.indexOf("=") > -1) {
							str = str.substring(str.indexOf("=") + 1);
						}
						result.setAuthor_nm(str);
					}else if(dataCheck.getType().equals("Email")) {
						String[] strList = data.split("\\s");	// 빈칸 마다 자르기.
						String str = new String();
						for(int j = 0; j < strList.length; j++) {
							str = strList[j];
							if(str.indexOf(dataCheck.getData_pattern()) > -1) {
								result.setAuthor_email(str);
								break;
							}
						}
					}else if(dataCheck.getType().equals("Title")) {
						result.setTitle(data);
					}else if(dataCheck.getType().equals("Contents")) {
						result.setContent(data);
					}
				}
				
				// 저자 등록여부 확인 및 ID 가져오기.
				MHNB01001VO authorParam = new MHNB01001VO();
				authorParam.setAuthor_email(result.getAuthor_email());
				authorParam.setAuthor_nm(result.getAuthor_nm());
				authorParam.setMedia_id(result.getMedia_id());
				
				int authorCnt = (Integer) dao.select("b010.selectAuthorCnt", authorParam);
				String author_id = "";
				if(authorCnt == 1) {
					MHNB01001VO authorResult = (MHNB01001VO) dao.select("b010.selectAuthor", authorParam);
					author_id = authorResult.getAuthor_id();
				}
				result.setAuthor_id(author_id);
				
				model.addAttribute("type", "NEW");
				model.addAttribute("result", result);
			}else {
				System.out.println("맞는 URL이 없습니다.");
			}
		}
		
		return modelAndView;
	}
	
}

