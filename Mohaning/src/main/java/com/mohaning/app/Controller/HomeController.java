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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mohaning.app.Const;
import com.mohaning.app.Dao.CmmnDao;
import com.mohaning.app.Model.MHNA010VO;
import com.mohaning.app.Model.MHNB01001VO;
import com.mohaning.app.Model.MHNB010VO;
import com.mohaning.app.Model.MHNC99901VO;
import com.mohaning.app.Model.MHNK01001VO;
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
		// 0-1. 검색 건수 7건. 기준 - 조회 수 상위, 오늘 날짜 기준.
		@SuppressWarnings("unchecked")
		List<MHNK01001VO> keyList = (List<MHNK01001VO>) dao.selectList("k010.selectKeyword", searchOptionVO);
		// 처음 값 세팅.
		if(keyList.size() > 0) {
			searchOptionVO.setKeyword(keyList.get(0).getKeyword());
		}
		if(keyList.size() < Const.keylistlength) {
			MHNK01001VO emptyKey = null;
			for(int i = keyList.size(); i < Const.keylistlength; i++) {
				emptyKey = new MHNK01001VO();
				keyList.add(emptyKey);
			}
		}
		
		model.addAttribute("keyList", keyList);
		// 0-2. Top 1 검색어를 기반으로 조회수 많은 6건의 기사 추출.
		
		
		// 1. 이슈 관련 조회수 가장 많은 기사 5건
		searchOptionVO.setType("MIX");	// Title, Contents 에 이슈 단어가 있는 경우만 검색. val 에 이슈 단어가 들어가 있음.
		searchOptionVO.setSort("03");
		searchOptionVO.setLength(5);
		@SuppressWarnings("unchecked")
		List<MHNB010VO> issueTopNewsList = (List<MHNB010VO>) dao.selectList("n010.selectNewsList", searchOptionVO);
		model.addAttribute("issueTopNewsList", issueTopNewsList);
		
		// 2. 이슈 관련 조회수 가장 많은 토론 5건
		searchOptionVO.setType("MIX");	// Title, Contents 에 이슈 단어가 있는 경우만 검색.  val 에 이슈 단어가 들어가 있음.
		searchOptionVO.setSort("03");
		searchOptionVO.setLength(5);
		@SuppressWarnings("unchecked")
		List<MHNB01001VO> issueTopBoardList = (List<MHNB01001VO>) dao.selectList("b010.selectBoardList", searchOptionVO);
		model.addAttribute("issueTopBoardList", issueTopBoardList);
		
		// 3. 이슈 관련 신규 토론 5건
		searchOptionVO.setType("MIX");	// Title, Contents 에 이슈 단어가 있는 경우만 검색. val 에 이슈 단어가 들어가 있음.
		searchOptionVO.setSort("01");
		searchOptionVO.setLength(5);
		@SuppressWarnings("unchecked")
		List<MHNB01001VO> issueNewBoardList = (List<MHNB01001VO>) dao.selectList("b010.selectBoardList", searchOptionVO);
		model.addAttribute("issueNewBoardList", issueNewBoardList);
		
		// 4. 평가 많은 기자 3명
		@SuppressWarnings("unchecked")
		List<MHNA010VO> topAuthorList = (List<MHNA010VO>) dao.selectList("a010.selectTopAuthorList", searchOptionVO);
		model.addAttribute("topAuthorList", topAuthorList);
		
		// 4-1. 평가 많은 기자 3명 점수
		
		// 5. 신규 등록 기자 3명
		@SuppressWarnings("unchecked")
		List<MHNA010VO> newAuthorList = (List<MHNA010VO>) dao.selectList("a010.selectNewAuthorList", searchOptionVO);
		model.addAttribute("newAuthorList", newAuthorList);
		
		// 5-1. 신규 등록 기자 3명 점수. 이부분은 신규 등록 기자의 기사로 대처할지 고민.
		
		// 6. 언론사 목록
		@SuppressWarnings("unchecked")
		List<MHNC99901VO> mediaList = (List<MHNC99901VO>) dao.selectList("c999.selectMediaList", searchOptionVO);
		model.addAttribute("mediaList", mediaList);
		
		// 7. 조회 수 높은 기사 10건
		searchOptionVO.setType("");
		searchOptionVO.setSort("03");
		searchOptionVO.setLength(10);
		@SuppressWarnings("unchecked")
		List<MHNB010VO> topNewsList = (List<MHNB010VO>) dao.selectList("n010.selectNewsList", searchOptionVO);
		model.addAttribute("topNewsList", topNewsList);
		
		// 8. 조회 수 높은 토론 10건
//		searchOptionVO.setType("");
//		searchOptionVO.setSort("03");
//		searchOptionVO.setLegnth(10);
//		@SuppressWarnings("unchecked")
//		List<MHNB01001VO> topBoardList = (List<MHNB01001VO>) dao.selectList("b010.selectBoardList", searchOptionVO);
//		model.addAttribute("topBoardList", topBoardList);
		
		// 9. 최신 기사 10건
		searchOptionVO.setType("");
		searchOptionVO.setSort("01");
		searchOptionVO.setLength(10);
		@SuppressWarnings("unchecked")
		List<MHNB010VO> newsList = (List<MHNB010VO>) dao.selectList("n010.selectNewsList", searchOptionVO);
		model.addAttribute("newNewsList", newsList);
		
		// 10. 최신 토론 10건
//		searchOptionVO.setType("");
//		searchOptionVO.setSort("01");
//		searchOptionVO.setLegnth(10);
//		@SuppressWarnings("unchecked")
//		List<MHNB01001VO> boardList = (List<MHNB01001VO>) dao.selectList("b010.selectBoardList", searchOptionVO);
//		model.addAttribute("newBoardList", boardList);
		
		return "main";
	}
	
}
