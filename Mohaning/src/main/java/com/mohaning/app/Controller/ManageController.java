package com.mohaning.app.Controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.mohaning.app.NewsAPI;
import com.mohaning.app.Dao.CmmnDao;
import com.mohaning.app.Model.MHNK02001VO;
import com.mohaning.app.Model.MHNM01001VO;
import com.mohaning.app.Model.MHNN01001VO;
import com.mohaning.app.Model.SearchOptionVO;

@Controller
public class ManageController {
	private static final Logger logger = LoggerFactory.getLogger(ManageController.class);

	@Autowired
	private CmmnDao dao;
	
	// 뉴스 관리
	@RequestMapping(value = "/Manage/main.do")
	public String manageMain(@ModelAttribute("searchOptionVO") SearchOptionVO searchOptionVO, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception{
		
		@SuppressWarnings("unchecked")
		List<MHNM01001VO> list = (List<MHNM01001VO>) dao.selectList("m010.selectManageNewsList", searchOptionVO);
		model.addAttribute("resultList", list);
		
		return "mhnm010/m010l";
	}
	
	// 검색어 관리
	@RequestMapping(value = "/Manage/keyword.do")
	public String manageKeyword(@ModelAttribute("searchOptionVO") SearchOptionVO searchOptionVO, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception{
		
		@SuppressWarnings("unchecked")
		List<MHNK02001VO> list = (List<MHNK02001VO>) dao.selectList("m020.selectKeywordCheckList", searchOptionVO);
		model.addAttribute("resultList", list);
		
		return "mhnm020/m020l";
	}
	
	// 검색어 관리
	@RequestMapping(value = "/Manage/getNews.json", method=RequestMethod.POST)
	public ModelAndView getNews(@ModelAttribute("mhnn01001VO") MHNN01001VO mhnn01001VO, ModelMap model, HttpServletRequest request, 
			HttpServletResponse response, HttpSession session, BindingResult bindingResult) throws Exception{
		
		ModelAndView modelAndView = new ModelAndView();
		MappingJackson2JsonView jsonView = new MappingJackson2JsonView();
		modelAndView.setView(jsonView);
		
		// 체크해서 최대 10 개의 keyword 를 진행할 수 있도록 로직 만들기.
		
		NewsAPI newsAPI = new NewsAPI();
//		String newsResults = newsAPI.getNews(searchOptionVO.getVal());
//		JSONParser jsonParser = new JSONParser();
//		Object obj = jsonParser.parse(newsResults);
//		JSONObject jsonObj = (JSONObject) obj;
//		List<Map<String, Object>> items = null;
//		items = (List<Map<String, Object>>) jsonObj.get("items");
//		
//		MHNN01001VO item = null;
//		for(int i = 0;  i < items.size(); i++) {
//			System.out.println(items.get(i).get("originallink"));
//			item = new MHNN01001VO();
//			item.setNews_url(items.get(i).get("originallink").toString());
//			NewsController newsCon = new NewsController();
//			newsCon.setDao(dao);	// 현재 Dao 를 넘겨주는 용.
//			newsCon.RegisterProcess(item, bindingResult);	// bindingResult target 에 searchoptionvo 가 되어 있음. 
//		}
		
//		JSONObject items = (JSONObject) jsonObj.get("items");
		
//		Map<String, String> items = new HashMap<String, String>();
//		items = (Map<String, String>) jsonObj.get("items");
		
		
//		JSONArray newsJsonArray = new JSONArray(jsonParser);
		
//		MHNN01001VO result = new MHNN01001VO();
//		
//		model.addAttribute("result", result);
		
		return modelAndView;
	}
}
