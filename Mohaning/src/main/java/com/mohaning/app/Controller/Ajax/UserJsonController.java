package com.mohaning.app.Controller.Ajax;

import java.util.Random;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.mohaning.app.Dao.CmmnDao;
import com.mohaning.app.Model.MHNU010VO;

@Controller
public class UserJsonController {
	private static final Logger logger = LoggerFactory.getLogger(UserJsonController.class);

	@Inject
	private JavaMailSender mailSender;
	
	@Autowired
	private CmmnDao dao;
	
	@RequestMapping(value = "/userValidationChk.json")
	public ModelAndView userValidationChk(@ModelAttribute("mhnu010VO") MHNU010VO mhnu010VO, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception{
		
		ModelAndView modelAndView = new ModelAndView();
		MappingJackson2JsonView jsonView = new MappingJackson2JsonView();
		modelAndView.setView(jsonView);
		
		if(mhnu010VO.getType().equals("email")) {
			int cnt = dao.selectCnt("u010.checkEmail", mhnu010VO);
			if(cnt == 0)model.addAttribute("Status", "S");
			
			int ran = new Random().nextInt(900000) + 100000;
			session.setAttribute("random", String.valueOf(ran));
			System.out.println("ran : " + String.valueOf(ran));
			
//			공식 메일 생성 후 이곳에 적용하기.
//			MailHandler sendMail = new MailHandler(mailSender);
//	        sendMail.setSubject("[이메일 인증]");
//	        sendMail.setText(new StringBuffer().append("<h1>인증 코드</h1>")
//	                .append("<h1>")
//	                .append(String.valueOf(ran))
//	                .append("</h1>")
//	                .toString());
//	        sendMail.setFrom("보낸이메일", "ljinseob@gmail.com");
//	        sendMail.setTo(mhnu010VO.getEmail());
//	        sendMail.send();
		}else if(mhnu010VO.getType().equals("emailAct")) {
			String ran = (String) session.getAttribute("random");
			if(ran.equals(mhnu010VO.getEmailAct())){
				model.addAttribute("Status", "S");
			}
		}else if(mhnu010VO.getType().equals("nickName")) {
			int cnt = dao.selectCnt("u010.checkNickName", mhnu010VO);
			if(cnt == 0)model.addAttribute("Status", "S");
		}
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/loginChk.json")
	public ModelAndView loginChk(@ModelAttribute("mhnu010VO") MHNU010VO mhnu010VO, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception{
		
		ModelAndView modelAndView = new ModelAndView();
		MappingJackson2JsonView jsonView = new MappingJackson2JsonView();
		modelAndView.setView(jsonView);
		
		int cnt = dao.selectCnt("u010.loginChk", mhnu010VO);
		if(cnt == 1)model.addAttribute("Status", "S");
		
		return modelAndView;
	}
}

