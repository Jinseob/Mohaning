package com.mohaning.app.Controller.Ajax;

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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.mohaning.app.Dao.CmmnDao;
import com.mohaning.app.Model.MHND010VO;

@Controller
public class NewsJsonController {
	private static final Logger logger = LoggerFactory.getLogger(NewsJsonController.class);

	@Autowired
	private CmmnDao dao;
	
	@RequestMapping(value = "/scoreUpdate.json")
	public ModelAndView scoreUpdate(@ModelAttribute("mhnd010VO") MHND010VO mhnd010VO, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception{
		
		ModelAndView modelAndView = new ModelAndView();
		MappingJackson2JsonView jsonView = new MappingJackson2JsonView();
		modelAndView.setView(jsonView);
		
		// 점수 등록.
		List<String> scoreList = mhnd010VO.getScoreList();
		for(int i = 0; i < scoreList.size(); i++) {
			mhnd010VO.setType_cd(scoreList.get(i));
			mhnd010VO.setScore(1);
			dao.insert("d010.insertScore", mhnd010VO);
		}
		String rediredUrl = "redirect:/a010d" + mhnd010VO.getNews_id() + ".do";
		System.out.println(rediredUrl);
		return modelAndView;
	}
}

