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

import com.mohaning.app.Dao.CmmnDao;
import com.mohaning.app.Model.MHNM01001VO;
import com.mohaning.app.Model.SearchOptionVO;

@Controller
public class ManageController {
	private static final Logger logger = LoggerFactory.getLogger(ManageController.class);

	@Autowired
	private CmmnDao dao;
	
	// 관리
	@RequestMapping(value = "/Manage/main.do")
	public String manageMain(@ModelAttribute("searchOptionVO") SearchOptionVO searchOptionVO, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception{
		
		@SuppressWarnings("unchecked")
		List<MHNM01001VO> list = (List<MHNM01001VO>) dao.selectList("m010.selectManageNewsList", searchOptionVO);
		model.addAttribute("resultList", list);
		
		return "mhnm010/m010l";
	}
}
