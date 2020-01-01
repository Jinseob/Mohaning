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
import org.springframework.web.bind.annotation.RequestMapping;

import com.mohaning.app.Dao.CmmnDao;
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
	
	@RequestMapping(value = "/Media.do")
	public String mediaMain(@ModelAttribute("searchOptionVO") SearchOptionVO searchOptionVO, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception{
		
		@SuppressWarnings("unchecked")
		List<MHNC99901VO> mediaList = (List<MHNC99901VO>) dao.selectList("c999.selectMediaList", searchOptionVO);
		
		@SuppressWarnings("unchecked")
		List<MHNC910VO> scoreList = (List<MHNC910VO>) dao.selectList("c910.selectScoreList", searchOptionVO);
		
		@SuppressWarnings("unchecked")
		List<MHND010VO> scoreListByMedia = (List<MHND010VO>) dao.selectList("d010.selectScoreListByMedia", searchOptionVO);
		
		int mediaListSize = mediaList.size();
		int scoreListSize = scoreList.size();
		int scoreListByMediaSize = scoreListByMedia.size();
		MHNC999VO media = null;
		MHND010VO score = null;
		List<MHND010VO> mediaScoreList = null;
		List<MHNC999VO> resultList = new ArrayList<MHNC999VO>();
		for(int i = 0; i < mediaListSize; i++) {
			media = new MHNC999VO();
			media.setMedia_id(mediaList.get(i).getMedia_id());
			media.setMedia_nm(mediaList.get(i).getMedia_nm());
			media.setMedia_url(mediaList.get(i).getMedia_url());
//			media.setScoreList(new ArrayList<MHND010VO>());
			
			// 특성 넣는 부분. 현재 6가지
			for(int j = 0; j < scoreListSize; j++) {
				score = new MHND010VO();
				score.setType_cd(scoreList.get(j).getType_cd());
				score.setType_nm(scoreList.get(j).getType_nm());
				media.getScoreList().add(score);
			}
			
			// 특성에 따른 점수 넣는 부분.
			for(int j = 0; j < scoreListByMediaSize; j++) {
				score = new MHND010VO();
				score = scoreListByMedia.get(j);
				if(media.getMedia_id().equals(score.getMedia_id())) {
					mediaScoreList = new ArrayList<MHND010VO>();
					mediaScoreList = media.getScoreList();

					for(int x = 0; x < mediaScoreList.size(); x++) {
						if(mediaScoreList.get(x).getType_cd().equals(score.getType_cd())) {
							mediaScoreList.get(x).setScore(score.getScore());
						}
					}
					
					JSONArray jsonArray = new JSONArray(media.getScoreList());
//					media.setScoreList(jsonArray);
				}
			}
			
			resultList.add(media);
		}
		model.addAttribute("resultList", resultList);
		
		return "mhnc999/c010l";
	}
}
