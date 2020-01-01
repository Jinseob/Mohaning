package com.mohaning.app.Controller;

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

import com.mohaning.app.Dao.CmmnDao;
import com.mohaning.app.Model.SearchOptionVO;

@Controller
public class AuthorController {
	private static final Logger logger = LoggerFactory.getLogger(AuthorController.class);

	@Autowired
	private CmmnDao dao;
	
	@RequestMapping(value = "/Author.do")
	public String authorMain(@ModelAttribute("searchOptionVO") SearchOptionVO searchOptionVO, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception{
		
//		@SuppressWarnings("unchecked")
//		List<MHNA010VO> list = (List<MHNA010VO>) dao.selectList("a010.selectNewsList", searchOptionVO);
//		model.addAttribute("resultList", list);
		
		return "mhnb010/b010l";
	}
}
