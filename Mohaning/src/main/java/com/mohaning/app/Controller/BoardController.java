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
import com.mohaning.app.Model.MHNA010VO;
import com.mohaning.app.Model.MHND010VO;
import com.mohaning.app.Model.SearchOptionVO;

@Controller
public class BoardController {
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

	@Autowired
	private CmmnDao dao;
	
	@RequestMapping(value = "/Board.do")
	public String boardMain(@ModelAttribute("searchOptionVO") SearchOptionVO searchOptionVO, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception{
		
		@SuppressWarnings("unchecked")
		List<MHNA010VO> list = (List<MHNA010VO>) dao.selectList("a010.selectNewsList", searchOptionVO);
		model.addAttribute("resultList", list);
		
		return "mhna010/a010l";
	}
	
	@RequestMapping(value = "/a010i.do")
	public String boardInsert(@ModelAttribute("searchOptionVO") SearchOptionVO searchOptionVO, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception{
		
		return "mhna010/a010i";
	}
	
	@RequestMapping(value = "/processUpdate_a010.do")
	public String processUpdate(@ModelAttribute("mhna010VO") MHNA010VO mhna010VO, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception{
		
		// 저자가 등록되어 있지 않으면 저장하기.
		if(mhna010VO.getAuthor_id().isEmpty()) {
			dao.insert("b010.insertAuthor", mhna010VO);	// 우선 기자를 등록하고 등록된 기자의 ID 를 가지고 온다.
		}
		dao.insert("a010.insertNews", mhna010VO);	// 기자 ID 를 news 에 넣는다.
		
		// 저장 실패시 Insert or Update 화면으로 그대로 둔다.
		
		// 저장 성공시 Detail 화면으로 간다.
		String rediredUrl = "redirect:/a010d" + mhna010VO.getNews_id() + ".do";
		System.out.println(rediredUrl);
		return rediredUrl;
	}
	
	@SuppressWarnings("static-access")
	@RequestMapping(value = "/a010d{news_id}.do")
	public String boardDetail(@PathVariable String news_id, @ModelAttribute("mhna010VO") MHNA010VO mhna010VO, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception{
		
		// 뉴스 기사와 기자, 언론사 정보 가지고 오는 부분.
		mhna010VO.setNews_id(news_id);
		MHNA010VO result = (MHNA010VO) dao.select("a010.selectNews", mhna010VO);
		model.addAttribute("result", result);
		
		// 개인별 반영 점수 가지고 오는 부분
		MHND010VO news_info = new MHND010VO();
		news_info.setNews_id(news_id);
		news_info.setReg_id("admin");
		@SuppressWarnings("unchecked")
		List<MHND010VO> scoreListByUser = (List<MHND010VO>) dao.selectList("d010.selectScoreListByUser", news_info);
		model.addAttribute("scoreListByUser", scoreListByUser);
		
		JSONArray jsonArray = new JSONArray(scoreListByUser);
		model.addAttribute("scoreListByUserJSON", jsonArray);
		
		System.out.println(scoreListByUser.get(1).getScore());
		
		// 평균 점수 가지고 오는 부분
		
		
		return "mhna010/a010d";
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
