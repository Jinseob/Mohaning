package com.mohaning.app;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.mohaning.app.Model.MHNN01001VO;

public class NewsValidator implements Validator{
	
	@Override
	public boolean supports(Class<?> arg0) {
		return MHNN01001VO.class.isAssignableFrom(arg0);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		// TODO Auto-generated method stub
		MHNN01001VO news = (MHNN01001VO) obj;
		
		// News Url 체크
		String news_url = news.getNews_url();
		if(news_url == null || news_url.trim().isEmpty()) {
			errors.rejectValue("news_url", Const.ERROR10);
		}
		
		// 기사 제목 필수.
		String news_title = news.getNews_title();
		if(news_title == null || news_title.trim().isEmpty()) {
			errors.rejectValue("news_title", Const.ERROR10);
		}
		
		/* Doc_id 체크 */
		String doc_id = news.getDoc_id();
		if(doc_id == null || doc_id.trim().isEmpty()) {
			errors.rejectValue("doc_id", Const.ERROR20);
		}
		
		// 기사 내용 필수.
		String news_contents = news.getNews_contents();
		if(news_contents == null || news_contents.trim().isEmpty()) {
			errors.rejectValue("news_contents", Const.ERROR30);
		}
		
		// 언론사명 필수.
		String media_nm = news.getMedia_nm();
		if(media_nm == null || media_nm.trim().isEmpty()) {
			errors.rejectValue("media_nm", Const.ERROR30);
		}
		
		// 기자명 필수.
		String author_nm = news.getAuthor_nm();
		if(author_nm == null || author_nm.trim().isEmpty()) {
			errors.rejectValue("author_nm", Const.ERROR30);
		}
		
		// 기자 email 필수.
		String author_email = news.getAuthor_email();
		if(author_email == null || author_email.trim().isEmpty()) {
			errors.rejectValue("author_email", Const.ERROR30);
		}
		
		/* Etc Check */
		String media_id = news.getMedia_id();
		if(media_id == null || media_id.trim().isEmpty()) {
			errors.rejectValue("media_id", Const.ERROR30);
		}
		
		String media_url = news.getMedia_url();
		if(media_url == null || media_url.trim().isEmpty()) {
			errors.rejectValue("media_url", Const.ERROR30);
		}
		
		String author_id = news.getAuthor_id();
		if(author_id == null || author_id.trim().isEmpty()) {
			errors.rejectValue("author_id", Const.ERROR30);
		}
		
		/* Portal Check */
		String portal_id = news.getPortal_id();
		if(portal_id == null || portal_id.trim().isEmpty()) {
			errors.rejectValue("portal_id", Const.ERROR40);
		}
		
		String portal_url = news.getPortal_url();
		if(portal_url == null || portal_url.trim().isEmpty()) {
			errors.rejectValue("portal_url", Const.ERROR40);
		}
	}
}
