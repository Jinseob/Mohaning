package com.mohaning.app.Controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mohaning.app.Dao.CmmnDao;
import com.mohaning.app.Model.MHNA01001VO;
import com.mohaning.app.Model.MHNA010VO;
import com.mohaning.app.Model.MHNB010VO;
import com.mohaning.app.Model.MHND010VO;
import com.mohaning.app.Model.SearchOptionVO;

@Controller
public class AuthorController {
	private static final Logger logger = LoggerFactory.getLogger(AuthorController.class);

	@Autowired
	private CmmnDao dao;
	
	@RequestMapping(value = "/Author/main.do")
	public String authorMain(@ModelAttribute("searchOptionVO") SearchOptionVO searchOptionVO, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception{
		
		// 특성 종류
//		@SuppressWarnings("unchecked")
//		List<MHNC910VO> scoreList = (List<MHNC910VO>) dao.selectList("c910.selectScoreList", searchOptionVO);
		
		// 특성별 기자 목록 가져오기
		@SuppressWarnings("unchecked")
		List<MHNA010VO> list = (List<MHNA010VO>) dao.selectList("a010.selectAuthorList", searchOptionVO);
		model.addAttribute("resultList", list);
		
		return "mhna010/a010l";
	}
	
	@RequestMapping(value = "/Author/a010d{author_id}.do")
	public String mediaDetail(@PathVariable String author_id, @ModelAttribute("mhna01001VO") MHNA01001VO mhna01001VO, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception{
		
		// 1. 기자 기본 정보 가지고 오는 부분.
		mhna01001VO.setAuthor_email(author_id);
		MHNA010VO author = (MHNA010VO) dao.select("a010.selectAuthorByID", mhna01001VO);
		model.addAttribute("author", author);
		
		// 2. 기자 점수 가지고 오는 부분
		@SuppressWarnings("unchecked")
		List<MHND010VO> authorScoreTemp = (List<MHND010VO>) dao.selectList("d010.selectAuthorScore", mhna01001VO);
		JSONArray authorScore = new JSONArray(authorScoreTemp);
		model.addAttribute("authorScore", authorScore);
		
		// 3. 기자의 최신 기사 가지고 오는 부분
		@SuppressWarnings("unchecked")
		List<MHNB010VO> authorNewsList = (List<MHNB010VO>) dao.selectList("n010.selectAuthorNewsList", mhna01001VO);
		model.addAttribute("authorNewsList", authorNewsList);
		
		return "mhna010/a010d";
	}
}
