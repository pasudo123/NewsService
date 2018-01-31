package com.daumsoft.news;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.daumsoft.news_data.NewsDataImpl;
import com.daumsoft.news_data.NewsDocumentList;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private NewsDataImpl newsData = new NewsDataImpl();
	private Map[] keywordDocumentsMap = null;
	private String topKeyword = null;
	private NewsDocumentList newsDocumentList = new NewsDocumentList();
	
	
	// -- Home 메뉴 요청
	@RequestMapping(value="/home")
	public String newsMenuCallHome(Model model){
//		newsData.setCommand("GetKeywordDocuments");
//		
//		// map[] 반환
//		keywordDocumentsMap = newsData.getResponseData();
//		model.addAttribute("documents", keywordDocumentsMap);
//		model.addAttribute("menu", "home");
		
		return "news_home";
	}
	
	
	// -- 정치 시사 메뉴 요청
	@RequestMapping(value="/politics")
	public String newsMenuCallPolitics(Model model){
		
		// -- 정치에 대한 상위 키워드 얻기 ( 1개 )
		newsData.setCommand("GetTopKeywords", "politics", null);
		model = modelWorkAboutMenuProcess(model);
		
		return "news_politics";
	}
	
	
	// -- 사회 경제 메뉴 요청
	@RequestMapping(value="/sociaty")
	public String newsMenuCallSociaty(Model model){
		
		// -- 사회 경제에 대한 상위 키워드 얻기 ( 1개 )
		newsData.setCommand("GetTopKeywords", "sociaty", null);
		model = modelWorkAboutMenuProcess(model);
				
		return "news_sociaty";
	}
	
	
	// -- 세계 메뉴 요청
	@RequestMapping(value="/global")
	public String newsMenuCallGlobal(Model model){
		
		// -- 세계에 대한 상위 키워드 얻기 ( 1개 )
		newsData.setCommand("GetTopKeywords", "global", null);
		model = modelWorkAboutMenuProcess(model);
				
		return "news_global";
	}
	
	
	// -- 문화 생활 메뉴 요청
	@RequestMapping(value="/culture")
	public String newsMenuCallCulture(Model model){
		
		// -- 문화 생활에 대한 상위 키워드 얻기 ( 1개 )
		newsData.setCommand("GetTopKeywords", "culture", null);
		model = modelWorkAboutMenuProcess(model);
		
		return "news_culture";
	}
	
	
	// -- IT 메뉴 요청
	@RequestMapping(value="/IT")
	public String newsMenuCallIT(Model model){
		
		// -- IT에 대한 상위 키워드 얻기 ( 1개 )
		newsData.setCommand("GetTopKeywords", "IT", null);
		model = modelWorkAboutMenuProcess(model);
		
		return "news_IT";
	}
	
	
	// -- 뉴스 본문 요청
	@RequestMapping(value="/news_content", method=RequestMethod.POST)
	public String showNewsContent(@ModelAttribute("NewsDocumentList") NewsDocumentList newsDocumentList, Model model){
		
		// 제목, 내용, 이미지, 언론사
		model.addAttribute("title", newsDocumentList.getTitle());
		model.addAttribute("content", newsDocumentList.getContent());
		model.addAttribute("imageUrl", newsDocumentList.getImageUrl());
		model.addAttribute("writerRealName", newsDocumentList.getWriterRealName());
		
		// 년, 월, 일, 요일, 시, 분
		model.addAttribute("parseYear", newsDocumentList.getParseYear());
		model.addAttribute("parseMonth", newsDocumentList.getParseMonth());
		model.addAttribute("parseDay", newsDocumentList.getParseDay());
		model.addAttribute("parseWeekDay", newsDocumentList.getParseWeekDay());
		model.addAttribute("parseHour", newsDocumentList.getParseHour());
		model.addAttribute("parseMinute", newsDocumentList.getParseMinute());
		
		return "news_content";
	}
	
	
	// -- 모델 객체에 대한 반복된 일처리 일괄 수행
	public Model modelWorkAboutMenuProcess(Model model){
		
		topKeyword = (String) newsData.getResponseData()[0].get("keyword");

		// -- 상위 키워드에 대한 뉴스 내용 조회하기
		newsData.setCommand("GetKeywordDocuments", null, topKeyword);

		// map[] 반환
		keywordDocumentsMap = newsData.getResponseData();
		model.addAttribute("documents", keywordDocumentsMap);
		model.addAttribute("size", keywordDocumentsMap.length);
		model.addAttribute("menu", "politics");

		// 도메인 객체
		model.addAttribute("newsDocumentList", newsDocumentList);
		
		return model;
	}
}
