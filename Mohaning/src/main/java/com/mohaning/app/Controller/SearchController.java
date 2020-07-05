package com.mohaning.app.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mohaning.app.Const;
import com.mohaning.app.NewsAPI;
import com.mohaning.app.Dao.CmmnDao;
import com.mohaning.app.Model.MHNN01001VO;
import com.mohaning.app.Model.SearchOptionVO;

/**
 * Handles requests for the application home page.
 */
@Controller
public class SearchController {
	
	private static final Logger logger = LoggerFactory.getLogger(SearchController.class);
	
	@Autowired
	private CmmnDao dao;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 * Main Page.
	 */
	@SuppressWarnings({ "unchecked", "static-access" })
	@RequestMapping(value = "/Search.do")
	public String search(@ModelAttribute("searchOptionVO") SearchOptionVO searchOptionVO, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response, HttpSession session, BindingResult bindingResult) throws Exception{

		/* Keyword Save */
		dao.insert("k010.insertKeyword", searchOptionVO);
		
		/* Board List */
		searchOptionVO.setType(Const.MIX);
		searchOptionVO.setLength(10);
		int boardResultCnt = dao.selectCnt("b010.selectBoardCount", searchOptionVO);
		model.addAttribute("boardResultCnt", boardResultCnt);
		
		List<MHNN01001VO> boardResultList = (List<MHNN01001VO>) dao.selectList("b010.selectBoardList", searchOptionVO);
		model.addAttribute("boardResultList", boardResultList);
		
		/* News List */
		searchOptionVO.setType(Const.MIX);
		searchOptionVO.setLength(10);
		int newsResultCnt = dao.selectCnt("n010.selectNewsCount", searchOptionVO);
		model.addAttribute("newsResultCnt", newsResultCnt);
		
		List<MHNN01001VO> newsResultList = (List<MHNN01001VO>) dao.selectList("n010.selectNewsList", searchOptionVO);
		model.addAttribute("newsResultList", newsResultList);
		
		if(newsResultCnt < 10) {
			NewsAPI newsAPI = new NewsAPI();
			String newsResults = newsAPI.getNews(searchOptionVO.getVal());
			JSONParser jsonParser = new JSONParser();
			Object obj = jsonParser.parse(newsResults);
			JSONObject jsonObj = (JSONObject) obj;
			List<Map<String, Object>> items = null;
			items = (List<Map<String, Object>>) jsonObj.get("items");
			
			MHNN01001VO item = null;
			for(int i = 0;  i < items.size(); i++) {
				System.out.println(items.get(i).get("originallink"));
				item = new MHNN01001VO();
				item.setNews_url(items.get(i).get("originallink").toString());
				NewsController newsCon = new NewsController();
				newsCon.RegisterProcess(item, bindingResult);
			}
			
//			JSONObject items = (JSONObject) jsonObj.get("items");
			
//			Map<String, String> items = new HashMap<String, String>();
//			items = (Map<String, String>) jsonObj.get("items");
			
			
//			JSONArray newsJsonArray = new JSONArray(jsonParser);
			System.out.println("AAAA");
		}
		
		return "searchResult";
	}
	
}
