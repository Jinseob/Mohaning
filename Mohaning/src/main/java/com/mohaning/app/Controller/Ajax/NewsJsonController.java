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

import com.mohaning.app.Const;
import com.mohaning.app.Dao.CmmnDao;
import com.mohaning.app.Model.MHND010VO;
import com.mohaning.app.Model.MHNN01001VO;
import com.mohaning.app.Model.MHNR010VO;
import com.mohaning.app.Model.SearchOptionVO;

@Controller
public class NewsJsonController {
	private static final Logger logger = LoggerFactory.getLogger(NewsJsonController.class);

	@Autowired
	private CmmnDao dao;
	
	@RequestMapping(value = "/scoreUpdate.json")
	public ModelAndView scoreUpdate(@ModelAttribute("mhnd010VO") MHND010VO mhnd010VO, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		
		ModelAndView modelAndView = new ModelAndView();
		MappingJackson2JsonView jsonView = new MappingJackson2JsonView();
		modelAndView.setView(jsonView);
		
		// 점수 등록.
		List<String> scoreList = mhnd010VO.getScoreList();
		for(int i = 0; i < scoreList.size(); i++) {
			mhnd010VO.setType_cd(scoreList.get(i));
			mhnd010VO.setScore(1);
			try {
				dao.insert("d010.insertScore", mhnd010VO);
				if(i == scoreList.size() - 1)mhnd010VO.setStatus("S");	// 우선은 단순 처리.
			} catch (Exception e) {
				mhnd010VO.setStatus("E");
			}
		}
		return modelAndView;
	}
	
	@RequestMapping(value = "/replyUpdate.json")
	public ModelAndView replyUpdate(@ModelAttribute("mhnr010VO") MHNR010VO mhnr010VO, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception{
		
		ModelAndView modelAndView = new ModelAndView();
		MappingJackson2JsonView jsonView = new MappingJackson2JsonView();
		modelAndView.setView(jsonView);
		
		// 답글 등록.
		String retVal = dao.insert_return("r010.insertReply", mhnr010VO);
		System.out.println("retVal : " + retVal);
		
		return modelAndView;
	}
    
//    @RequestMapping(value = "/downImage.json", method=RequestMethod.POST)
//	public ModelAndView downImage(@ModelAttribute("searchOptionVO") SearchOptionVO SearchOptionVO, ModelMap model, HttpServletRequest request, 
//			HttpServletResponse response, HttpSession session) throws Exception{
//		
//		ModelAndView modelAndView = new ModelAndView();
//		MappingJackson2JsonView jsonView = new MappingJackson2JsonView();
//		modelAndView.setView(jsonView);
//		
//		int cnt = 1400;
//		String imgurl = "https://s.pstatic.net/static/newsstand/2019/logo/";
// 		String png = ".png";
// 		String name = new String();
//		String fileURL = new String();
//		String fileName = new String();
//		String[] strArr = new String[cnt];
//		int size = 0;
//		for(int i = 1000; i < cnt; i++) {
//			name = Integer.toString(i);
//			size = name.length();
//			for(int j = size; j < 3; j++) {
//				name = "0" + name;
//			}
//			fileURL = imgurl + name + png;
//			fileName = name + png;
//			
//			// 1. SSL 있는지 확인 및 우회 처리
//	        if(fileURL.indexOf("https://") > -1){
//	        	NewsJsonController.setSSL();
//	        }
//	        
//	        FileOutputStream fos = null;
//			InputStream is = null;
//			try {
//				fos = new FileOutputStream("/CI/" + fileName);
//
//				URL url = new URL(fileURL);
//				URLConnection urlConnection = url.openConnection();
//				if(urlConnection.getInputStream() != null) {
//					is = urlConnection.getInputStream();
//					byte[] buffer = new byte[1024];
//					int readBytes;
//					while ((readBytes = is.read(buffer)) != -1) {
//						fos.write(buffer, 0, readBytes);
//					}
//				}
//			} catch (FileNotFoundException e) {
//				e.printStackTrace();
//			} catch (MalformedURLException e) {
//				e.printStackTrace();
//			} catch (IOException e) {
//				e.printStackTrace();
//			} finally {
//				try {
//					if (fos != null) {
//						fos.close();
//					}
//					if (is != null) {
//						is.close();
//					}
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//			
////			strArr[i] = fileURL;
////			System.out.println(fileURL + " : " + fileName);
//		}
// 		
////		model.addAttribute("URL", strArr);
//		
//		return modelAndView;
//	}
	
	@RequestMapping(value = "/keywordSearch.json")
	public ModelAndView keywordSearch(@ModelAttribute("searchOptionVO") SearchOptionVO searchOptionVO, ModelMap model, 
			HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception{
		
		ModelAndView modelAndView = new ModelAndView();
		MappingJackson2JsonView jsonView = new MappingJackson2JsonView();
		modelAndView.setView(jsonView);
		
		// 받아온 Keyword 로 검색
		searchOptionVO.setType(Const.KEY);
		@SuppressWarnings("unchecked")
		List<MHNN01001VO> listByKeyword = (List<MHNN01001VO>) dao.selectList("n010.selectNewsList", searchOptionVO);
		model.addAttribute("listByKeyword", listByKeyword);
		
		return modelAndView;
	}
}

