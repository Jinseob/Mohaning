package com.mohaning.app.Controller.Ajax;

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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.mohaning.app.Dao.CmmnDao;
import com.mohaning.app.Model.MHND010VO;
import com.mohaning.app.Model.SearchOptionVO;

@Controller
public class ScoreJsonController {
	private static final Logger logger = LoggerFactory.getLogger(ScoreJsonController.class);

	@Autowired
	private CmmnDao dao;
	
	@RequestMapping(value = "/getScore{type}.json")
	public ModelAndView getScore(@PathVariable String type, @ModelAttribute("searchOptionVO") SearchOptionVO searchOptionVO, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception{
		
		ModelAndView modelAndView = new ModelAndView();
		MappingJackson2JsonView jsonView = new MappingJackson2JsonView();
		modelAndView.setView(jsonView);
		
		// 첫번째 언론사 점수
		if(type.equals("M")) {
			@SuppressWarnings("unchecked")
			List<MHND010VO> mediaScoreTemp = (List<MHND010VO>) dao.selectList("d010.selectMediaScore", searchOptionVO);
			model.addAttribute("mediaScore", mediaScoreTemp);
		}else if(type.equals("N")) {
			
		}else if(type.equals("A")) {
			
		}
		
		return modelAndView;
	}
}

