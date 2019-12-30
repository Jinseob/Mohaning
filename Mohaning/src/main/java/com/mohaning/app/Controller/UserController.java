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
import com.mohaning.app.Model.MHNU010VO;
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
	public String signInProcess(@ModelAttribute("mhnu010VO") MHNU010VO mhnu010VO, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception{
		
		
		// 로그인 로직 넣기
		mhnu010VO = (MHNU010VO) dao.select("u010.selectUserInfo", mhnu010VO);
		
		// 로그인 후 세션에 정보 넣는 로직 넣기
		System.out.println(mhnu010VO.getEmail());
		session.setAttribute("userEmail", mhnu010VO.getEmail());
		session.setAttribute("userNick", mhnu010VO.getNickName());
		System.out.println(mhnu010VO.getNickName());
		// 메인화면으로 이동.
		
		return "redirect:/";
	}
	
	@RequestMapping(value = "/signUp.do")
	public String signUpForm(@ModelAttribute("searchOptionVO") SearchOptionVO searchOptionVO, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception{
		
		return "mhnu010/u010suf";
	}
	
	@RequestMapping(value = "/signUpProcess.do")
	public String signUpProcess(@ModelAttribute("mhnu010VO") MHNU010VO mhnu010VO, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception{

		// 중복 확인. 이미 등록된 e-mail 이 있으면 리턴.
		int chk = 0;
		chk = chk + dao.selectCnt("u010.checkNickName", mhnu010VO);
		chk = chk + dao.selectCnt("u010.checkEmail", mhnu010VO);
		
		// Email Activation
		String ran = (String) session.getAttribute("random");
		if(!mhnu010VO.getEmailAct().equals(ran)) {
			chk++;
		}
		
		// Password 확인
		if(!mhnu010VO.getPsw().equals(mhnu010VO.getPswConfirm())) {
			chk++;
		}
		
		// 회원 정보 저장 로직
		String url = "mhnu010/u010suf";
		if(chk == 0) {
			dao.insert("u010.insertUser", mhnu010VO);
			// 로그인 화면으로 이동.
			url = "mhnu010/u010sif";
		}
		
		return url;
	}
	
	@RequestMapping(value = "/logout.do")
	public String logout(@ModelAttribute("mhnu010VO") MHNU010VO mhnu010VO, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception{
		
		session.invalidate();
		
		return "redirect:/";
	}
}

