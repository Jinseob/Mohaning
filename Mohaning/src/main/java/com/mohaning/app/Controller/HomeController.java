package com.mohaning.app.Controller;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mohaning.app.Dao.CmmnDao;
import com.mohaning.app.Model.MHNA010VO;
import com.mohaning.app.Model.MHNA02001VO;
import com.mohaning.app.Model.MHNB010VO;
import com.mohaning.app.Model.MHNC99901VO;
import com.mohaning.app.Model.SearchOptionVO;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private CmmnDao dao;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 * Main Page.
	 */
	@RequestMapping(value = "/")
	public String home(@ModelAttribute("searchOptionVO") SearchOptionVO searchOptionVO, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception{

		// 0. 현재 이슈 리스트업 10건
		
		// 1. 이슈 관련 조회수 가장 많은 기사 5건
		@SuppressWarnings("unchecked")
		List<MHNA010VO> issueTopNewsList = (List<MHNA010VO>) dao.selectList("a010.issueTopNewsList", searchOptionVO);
		model.addAttribute("issueTopNewsList", issueTopNewsList);
		
		// 2. 이슈 관련 조회수 가장 많은 토론 5건
		@SuppressWarnings("unchecked")
		List<MHNA02001VO> issueTopBoardList = (List<MHNA02001VO>) dao.selectList("a020.issueTopBoardList", searchOptionVO);
		model.addAttribute("issueTopBoardList", issueTopBoardList);
		
		// 3. 이슈 관련 신규 토론 5건
		@SuppressWarnings("unchecked")
		List<MHNA02001VO> issueNewBoardList = (List<MHNA02001VO>) dao.selectList("a020.issueNewBoardList", searchOptionVO);
		model.addAttribute("issueNewBoardList", issueNewBoardList);
		
		// 4. 평가 많은 기자 3명
		@SuppressWarnings("unchecked")
		List<MHNB010VO> topAuthorList = (List<MHNB010VO>) dao.selectList("b010.selectTopAuthorList", searchOptionVO);
		model.addAttribute("topAuthorList", topAuthorList);
		
		// 4-1. 평가 많은 기자 3명 점수
		
		// 5. 신규 등록 기자 3명
		@SuppressWarnings("unchecked")
		List<MHNB010VO> newAuthorList = (List<MHNB010VO>) dao.selectList("b010.selectNewAuthorList", searchOptionVO);
		model.addAttribute("newAuthorList", newAuthorList);
		
		// 5-1. 신규 등록 기자 3명 점수. 이부분은 신규 등록 기자의 기사로 대처할지 고민.
		
		// 6. 언론사 목록
		@SuppressWarnings("unchecked")
		List<MHNC99901VO> mediaList = (List<MHNC99901VO>) dao.selectList("c999.selectMediaList", searchOptionVO);
		model.addAttribute("mediaList", mediaList);
		
		// 7. 조회 수 높은 기사 10건
		@SuppressWarnings("unchecked")
		List<MHNA010VO> topNewsList = (List<MHNA010VO>) dao.selectList("a010.selectTopNewsList", searchOptionVO);
		model.addAttribute("topNewsList", topNewsList);
		
		// 8. 조회 수 높은 토론 10건
		@SuppressWarnings("unchecked")
		List<MHNA02001VO> topBoardList = (List<MHNA02001VO>) dao.selectList("a020.selectTopBoardList", searchOptionVO);
		model.addAttribute("topBoardList", topBoardList);
		
		// 9. 최신 기사 10건
		@SuppressWarnings("unchecked")
		List<MHNA010VO> newsList = (List<MHNA010VO>) dao.selectList("a010.selectNewNewsList", searchOptionVO);
		model.addAttribute("newNewsList", newsList);
		
		// 10. 최신 토론 10건
		@SuppressWarnings("unchecked")
		List<MHNA02001VO> boardList = (List<MHNA02001VO>) dao.selectList("a020.selectNewBoardList", searchOptionVO);
		model.addAttribute("newBoardList", boardList);
		
		return "main";
	}
	
}
