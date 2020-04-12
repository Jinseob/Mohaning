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
import com.mohaning.app.Model.MHNB01001VO;
import com.mohaning.app.Model.MHND010VO;
import com.mohaning.app.Model.MHNN01001VO;
import com.mohaning.app.Model.MHNP01001VO;
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

	@RequestMapping(value = "/News/processUpdate_n010.do")
	public String processUpdate(@ModelAttribute("mhnn01001VO") MHNN01001VO mhnn01001VO, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception{
		
		// 저자가 등록되어 있지 않으면 저장하기.
		if(mhnn01001VO.getAuthor_id().isEmpty()) {
			MHNA01001VO authorInfo = new MHNA01001VO();
			authorInfo.setAuthor_id(mhnn01001VO.getAuthor_id());
			authorInfo.setAuthor_email(mhnn01001VO.getAuthor_email());
			authorInfo.setAuthor_nm(mhnn01001VO.getAuthor_nm());
			authorInfo.setMedia_id(mhnn01001VO.getMedia_id());
			
			dao.insert("a010.insertAuthor", authorInfo);	// 우선 기자를 등록하고 등록된 기자의 ID 를 가지고 온다.
			mhnn01001VO.setAuthor_id(authorInfo.getAuthor_id());
		}
		dao.insert_return("n010.insertNews", mhnn01001VO);		// News 를 저장한다.

		// 포털 URL 이 있는 경우 저장 되었는지 확인하는 부분.
		int portalCnt = -1;	// -1 이면 포털 ID 가 없는 경우. 0 이상이면 포털 ID 가 있는 경우.
		if(!mhnn01001VO.getPortal_id().isEmpty()) {	// 포털 URL 이 잇는 경우 포털 ID 가 있다.
			portalCnt = dao.selectCnt("p010.selectPortalByNewsCnt", mhnn01001VO);
		}
		
		if(portalCnt == 0) {
			System.out.println("Portal Count : " + portalCnt);
			dao.insert_return("p010.insertPortal", mhnn01001VO);	// 포탈에서 가져온 경우 포털 정보를 저장한다.
		}
		
		// 저장 성공시 Detail 화면으로 간다.
		String rediredUrl = "redirect:/n010d" + mhnn01001VO.getNews_id() + ".do";
		System.out.println(rediredUrl);
		return rediredUrl;
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
