package com.daumsoft.news;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.daumsoft.news_data.NewsDataImpl;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	private NewsDataImpl newsData = new NewsDataImpl();
	private Map[] keywordDocumentsMap = null;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	// -- Home 메뉴 요청
	@RequestMapping(value="/home", method=RequestMethod.GET)
	public String newsMenuCallHome(Model model){
		newsData.setCommand("GetKeywordDocuments");
		
		// map[] 반환
		keywordDocumentsMap = newsData.getResponseData();
		model.addAttribute("documents", keywordDocumentsMap);
		model.addAttribute("menu", "home");
		
		return "news_home";
	}
	
	// -- 정치 시사 메뉴 요청
	@RequestMapping(value="/politics")
	public String newsMenuCallPolitics(Model model){
		
		// 키워드 기반 뉴스 내용 얻기
		newsData.setCommand("GetKeywordDocuments");
		
		// map[] 반환
		keywordDocumentsMap = newsData.getResponseData();
		model.addAttribute("documents", keywordDocumentsMap);
		model.addAttribute("size", keywordDocumentsMap.length);
		model.addAttribute("menu", "politics");
		
		return "news_politics";
	}
	
	// -- 사회 경제 메뉴 요청
	@RequestMapping(value="/sociaty")
	public String newsMenuCallSociaty(Model model){
		return "news_sociaty";
	}
	
	// -- 세계 메뉴 요청
	@RequestMapping(value="/global")
	public String newsMenuCallGlobal(Model model){
		return "news_global";
	}
	
	// -- 문화 생활 메뉴 요청
	@RequestMapping(value="/culture")
	public String newsMenuCallCulture(Model model){
		return "news_culture";
	}
	
	// -- IT 메뉴 요청
	@RequestMapping(value="/IT")
	public String newsMenuCallIT(Model model){
		return "news_IT";
	}
	
	// -- 뉴스 본문 요청
	@RequestMapping(value="/news_content")
	public String showNewsContent(Model model){
		
		
		return "news_content";
	}
}
