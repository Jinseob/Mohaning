package com.mohaning.app.Controller;

import java.util.ArrayList;
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
import com.mohaning.app.Model.MHNB010VO;
import com.mohaning.app.Model.MHNC910VO;
import com.mohaning.app.Model.MHNC99901VO;
import com.mohaning.app.Model.MHNC999VO;
import com.mohaning.app.Model.MHND010VO;
import com.mohaning.app.Model.SearchOptionVO;

@Controller
public class MediaController {
	private static final Logger logger = LoggerFactory.getLogger(MediaController.class);

	@Autowired
	private CmmnDao dao;
	
	@RequestMapping(value = "/Media/main.do")
	public String mediaMain(@ModelAttribute("searchOptionVO") SearchOptionVO searchOptionVO, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception{
		
		// 언론사 종류
		@SuppressWarnings("unchecked")
		List<MHNC99901VO> mediaList = (List<MHNC99901VO>) dao.selectList("c999.selectMediaList", searchOptionVO);
		
		// 특성 종류
		@SuppressWarnings("unchecked")
		List<MHNC910VO> scoreList = (List<MHNC910VO>) dao.selectList("c910.selectScoreList", searchOptionVO);
		
		// 언론사별 특성 점수
		@SuppressWarnings("unchecked")
		List<MHND010VO> scoreListByMedia = (List<MHND010VO>) dao.selectList("d010.selectScoreListByMedia", searchOptionVO);
		
		int mediaListSize = mediaList.size();	// 언론사 종류수
		int scoreListSize = scoreList.size();	// 특성 종류수
		int scoreListByMediaSize = scoreListByMedia.size();	// 언론사별 특성 점수 갯수
		
		MHNC999VO selectedMedia = null;	// 선택된 언론사
		MHND010VO selectedScore = null;	// 선택된 특성
		List<MHNC999VO> resultList = new ArrayList<MHNC999VO>();
		
		List<MHND010VO> baseScoreList = null;	// 임시 특성 저장 공간.
		MHND010VO baseScore = null;	// 선택된 특성
		int baseScoreListSize = 0;
		// 언론사 목록 + 점수 특성 목록 + 언론사별 점수
		for(int i = 0; i < mediaListSize; i++) {
			selectedMedia = new MHNC999VO();
			selectedMedia.setMedia_id(mediaList.get(i).getMedia_id());
			selectedMedia.setMedia_nm(mediaList.get(i).getMedia_nm());
			selectedMedia.setMedia_url(mediaList.get(i).getMedia_url());
			
			// 1. 기본 특성을 임시 특성 저장 공간에 넣는다.
			//    특성 넣는 부분. 현재 6가지. 기본 목록 만들기.
			baseScoreList = new ArrayList<MHND010VO>();
			for(int y = 0; y < scoreListSize; y++) {
				selectedScore = new MHND010VO();
				selectedScore.setType_cd(scoreList.get(y).getType_cd());
				selectedScore.setType_nm(scoreList.get(y).getType_nm());
				baseScoreList.add(selectedScore);
			}
			baseScoreListSize = baseScoreList.size();	// 기본 특성 갯수
			
			// 2. 임시 저장 공간에 언론사별로 특성 점수를 넣는다.
			for(int j = 0; j < scoreListByMediaSize; j++) {
				selectedScore = new MHND010VO();
				selectedScore = scoreListByMedia.get(j);
				if(selectedMedia.getMedia_id().equals(selectedScore.getMedia_id())) {
					for(int x = 0; x < baseScoreListSize; x++) {
						baseScore = new MHND010VO();
						baseScore = baseScoreList.get(x);
						if(selectedScore.getType_cd().equals(baseScore.getType_cd())) {
							baseScore.setScore(selectedScore.getScore());
							baseScore.setRatio(selectedScore.getRatio());
							baseScore.setColor(selectedScore.getColor());
							break;
						}
					}
				}
			}
			
			// 3. 적용 완료된 언론사별 특성 점수를 Json Array 형태로 변형한다.
			JSONArray jsonArray = new JSONArray(baseScoreList);
			selectedMedia.setScoreList(jsonArray);
			resultList.add(selectedMedia);
		}
		model.addAttribute("resultList", resultList);
		
		return "mhnc999/c010l";
	}
	
	@RequestMapping(value = "/Media/c010d{media_id}.do")
	public String mediaDetail(@PathVariable String media_id, @ModelAttribute("mhnc99901VO") MHNC99901VO mhnc99901VO, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception{
		
		// 1. 언론사 기본 정보 가지고 오는 부분.
		mhnc99901VO.setMedia_id(media_id);
		MHNC99901VO media = (MHNC99901VO) dao.select("c999.selectMedia", mhnc99901VO);
		model.addAttribute("media", media);
		
		// 2. 언론사 점수 가지고 오는 부분
		@SuppressWarnings("unchecked")
		List<MHND010VO> mediaScoreTemp = (List<MHND010VO>) dao.selectList("d010.selectMediaScore", mhnc99901VO);
		JSONArray mediaScore = new JSONArray(mediaScoreTemp);
		model.addAttribute("mediaScore", mediaScore);
		
		// 3. 언론사의 최신 기사 가지고 오는 부분
		@SuppressWarnings("unchecked")
		List<MHNB010VO> mediaNewsList = (List<MHNB010VO>) dao.selectList("n010.selectMediaNewsList", mhnc99901VO);
		model.addAttribute("mediaNewsList", mediaNewsList);
		
		// 4. 언론사의 인기 있는 기자 정보 가지고 오는 부분.
		@SuppressWarnings("unchecked")
		List<MHNA01001VO> mediaAuthorList = (List<MHNA01001VO>) dao.selectList("a010.selectMediaAuthorList", mhnc99901VO);
		model.addAttribute("mediaAuthorList", mediaAuthorList);
		
		return "mhnc999/c010d";
	}
}
