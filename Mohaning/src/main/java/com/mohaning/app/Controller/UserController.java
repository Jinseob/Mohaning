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
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private CmmnDao dao;
	
	@RequestMapping(value = "/signIn.do")
	public String signInForm(@ModelAttribute("searchOptionVO") SearchOptionVO searchOptionVO, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception{
		
		return "mhnu010/u010sif";
	}
	
	@RequestMapping(value = "/signInProcess.do")
	public String signInProcess(@ModelAttribute("searchOptionVO") SearchOptionVO searchOptionVO, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception{
		
		// 로그인 로직 넣기
		
		// 로그인 후 세션에 정보 넣는 로직 넣기
		
		// 메인화면으로 이동.
		
		return "home";
	}
	
	@RequestMapping(value = "/signUp.do")
	public String signUpForm(@ModelAttribute("searchOptionVO") SearchOptionVO searchOptionVO, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception{
		
		return "mhnu010/u010suf";
	}
	
	@RequestMapping(value = "/signUpProcess.do")
	public String signUpProcess(@ModelAttribute("searchOptionVO") SearchOptionVO searchOptionVO, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception{

		// 중복 확인. 이미 등록된 e-mail 이 있으면 리턴.
		
		// E-mail 인증이 되었는지 확인. 안되어있으면 인증하라고 알림과 함께 리턴.
		
		// 회원 정보 저장 로직
		
		// 로그인 화면으로 이동.
		
		return "mhnu010/u010suf";
	}
}

