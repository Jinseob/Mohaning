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

import com.mohaning.app.Const;
import com.mohaning.app.Dao.CmmnDao;
import com.mohaning.app.Model.MHNB01001VO;
import com.mohaning.app.Model.MHNB010VO;
import com.mohaning.app.Model.MHND010VO;
import com.mohaning.app.Model.MHNP01001VO;
import com.mohaning.app.Model.MHNR010VO;
import com.mohaning.app.Model.SearchOptionVO;

@Controller
public class BoardController {
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

	@Autowired
	private CmmnDao dao;
	
	// 토론
	@RequestMapping(value = "/Board/main.do")
	public String boardMain(@ModelAttribute("searchOptionVO") SearchOptionVO searchOptionVO, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception{
		
		searchOptionVO.setType(Const.MIX); 
		int resultCnt = dao.selectCnt("b010.selectBoardCount", searchOptionVO);
		model.addAttribute("resultCnt", resultCnt);
		
		@SuppressWarnings("unchecked")
		List<MHNB01001VO> list = (List<MHNB01001VO>) dao.selectList("b010.selectBoardList", searchOptionVO);
		model.addAttribute("resultList", list);
		
		return "mhnb010/b010l";
	}
	
	@RequestMapping(value = "/Board/b010i.do")
	public String boardInsert(@ModelAttribute("searchOptionVO") SearchOptionVO searchOptionVO, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception{
		
		return "mhnb010/b010i";
	}
	
	@RequestMapping(value = "/Board/processUpdate.do")
	public String processUpdate(@ModelAttribute("mhnb010VO") MHNB010VO mhnb010VO, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception{
		
		// 저자가 등록되어 있지 않으면 저장하기.
		if(mhnb010VO.getAuthor_id().isEmpty()) {
			dao.insert("a010.insertAuthor", mhnb010VO);	// 우선 기자를 등록하고 등록된 기자의 ID 를 가지고 온다.
		}
		if(mhnb010VO.getNews_id().isEmpty()) {	// 우선은 Java 에서 News 의 ID 기준으로 새롭게 등록하던가 아니면 넘어가도록만 처리. 추후 merge 쿼리를 사용하여 업데이트도 되도록 변경.
			dao.insert_return("n010.insertNews", mhnb010VO);	// 기자 ID 를 news 에 넣는다.
		}
		dao.insert("b010.insertBoard", mhnb010VO);			// 기사 ID 를 board 에 넣는다.
		
		// 저장 성공시 Detail 화면으로 간다.
		String rediredUrl = "redirect:/Board/b010d" + mhnb010VO.getBoard_id() + ".do";
		return rediredUrl;
	}
	
	@RequestMapping(value = "/Board/b010d{board_id}.do")
	public String boardDetail(@PathVariable String board_id, @ModelAttribute("mhnb010VO") MHNB010VO mhnb010VO, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception{

		mhnb010VO.setBoard_id(board_id);
		
		// 조회수 올리는 로직 넣기
		dao.update("b010.updateCnt", mhnb010VO);
		
		// 여기서부터 토론 화면 가져오는 부분 만들기. 쿼리부터 다시 짜기. 2020.02.19
		// 뉴스 기사와 기자, 언론사 정보 가지고 오는 부분.
		MHNB010VO result = (MHNB010VO) dao.select("b010.selectBoard", mhnb010VO);
		model.addAttribute("result", result);
		
		// 포털 정보가 있는 경우 가지고 오는 부분.
		@SuppressWarnings("unchecked")
		List<MHNP01001VO> portalList = (List<MHNP01001VO>) dao.selectList("p010.selectPortalByNews", result);
		model.addAttribute("portalList", portalList);
		
		// 개인별 반영 점수 가지고 오는 부분
		MHND010VO news_info = new MHND010VO();
		news_info.setNews_id(result.getNews_id());
		news_info.setReg_id("admin");
		@SuppressWarnings("unchecked")
		List<MHND010VO> scoreListByUser = (List<MHND010VO>) dao.selectList("d010.selectScoreListByUser", news_info);
		model.addAttribute("scoreListByUser", scoreListByUser);
		
		// 평균 점수 가지고 오는 부분
		@SuppressWarnings("unchecked")
		List<MHND010VO> score = (List<MHND010VO>) dao.selectList("d010.selectScoreListByNews", news_info);
		JSONArray jsonArray = new JSONArray(score);
		model.addAttribute("score", jsonArray);
		
		// 답글 가지고 오는 부분.
		@SuppressWarnings("unchecked")
		List<MHNR010VO> replyList = (List<MHNR010VO>) dao.selectList("r010.replyList", mhnb010VO);
		model.addAttribute("replyList", replyList);
		
		return "mhnb010/b010d";
	}
}
