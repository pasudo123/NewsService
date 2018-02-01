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
	
	private Map[] topAssiciateSentimentMap = null;
	
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
		model = WorkAboutMainMenuProcess(model);
		
		return "news_politics";
	}
	
	
	// -- 사회 경제 메뉴 요청
	@RequestMapping(value="/sociaty")
	public String newsMenuCallSociaty(Model model){
		
		// -- 사회 경제에 대한 상위 키워드 얻기 ( 1개 )
		newsData.setCommand("GetTopKeywords", "sociaty", null);
		model = WorkAboutMainMenuProcess(model);
				
		return "news_sociaty";
	}
	
	
	// -- 세계 메뉴 요청
	@RequestMapping(value="/global")
	public String newsMenuCallGlobal(Model model){
		
		// -- 세계에 대한 상위 키워드 얻기 ( 1개 )
		newsData.setCommand("GetTopKeywords", "global", null);
		model = WorkAboutMainMenuProcess(model);
				
		return "news_global";
	}
	
	
	// -- 문화 생활 메뉴 요청
	@RequestMapping(value="/culture")
	public String newsMenuCallCulture(Model model){
		
		// -- 문화 생활에 대한 상위 키워드 얻기 ( 1개 )
		newsData.setCommand("GetTopKeywords", "culture", null);
		model = WorkAboutMainMenuProcess(model);
		
		return "news_culture";
	}
	
	
	// -- IT 메뉴 요청
	@RequestMapping(value="/IT")
	public String newsMenuCallIT(Model model){
		
		// -- IT에 대한 상위 키워드 얻기 ( 1개 )
		newsData.setCommand("GetTopKeywords", "IT", null);
		model = WorkAboutMainMenuProcess(model);
		
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
	
	
	// -- 사이드 메뉴 감성 이슈 선택
	@RequestMapping(value="/viewForSentiment", method=RequestMethod.POST)
	public String showSentimentTable(Model model, HttpServletRequest request){
		
		String menu = request.getParameter("menu");
		
		return WorkAboutSideMenuBarProcess(model, menu);
	}
	
	
	// -- 모델 객체에 대한 반복된 프로세스 일괄 수행
	public Model WorkAboutMainMenuProcess(Model model){
		
		topKeyword = (String) newsData.getResponseData()[0].get("keyword");

		// -- 상위 키워드에 대한 뉴스 내용 조회하기
		newsData.setCommand("GetKeywordDocuments", null, topKeyword);

		// map[] 반환
		keywordDocumentsMap = newsData.getResponseData();
		model.addAttribute("documents", keywordDocumentsMap);
		model.addAttribute("size", keywordDocumentsMap.length);

		// 도메인 객체
		model.addAttribute("newsDocumentList", newsDocumentList);
		
		return model;
	}
	
	
	// -- 사이드 메뉴에 대한 프로세스 수행
	public String WorkAboutSideMenuBarProcess(Model model, String choiceMenu){

		// 정치||시사
		if(choiceMenu.equals("sidePolitics"))
			newsData.setCommand("GetTopAssocSentimentByPeriod", "politics", "정치||시사");
		
		// 사회||경제
		if(choiceMenu.equals("sideSociaty"))
			newsData.setCommand("GetTopAssocSentimentByPeriod", "sociaty", "사회||경제");
		
		// 세계||국제
		if(choiceMenu.equals("sideGlobal"))
			newsData.setCommand("GetTopAssocSentimentByPeriod", "global", "세계||국제");
		
		// 문화||생활
		if(choiceMenu.equals("sideCulture"))
			newsData.setCommand("GetTopAssocSentimentByPeriod", "culture", "문화||생활");
		
		// IT
		if(choiceMenu.equals("sideIT"))
			newsData.setCommand("GetTopAssocSentimentByPeriod", "IT", "컴퓨터");
		
		// 해당 사이드 메뉴에 대한 감성 키워드
		topAssiciateSentimentMap = newsData.getResponseData();
		
		for(int i = 1; i <= 15; i++){
			Map<String, Object> rankMap = (Map<String, Object>) topAssiciateSentimentMap[0].get("rank" + i);
			model.addAttribute("rankMap" + i, rankMap);
		}
		
//		{label=깨끗하다, frequency=1906, score=362.03235, polarity=other}
//		{label=도덕적, frequency=953, score=57.994762, polarity=other}
//		{label=원활하다, frequency=480, score=75.04859, polarity=other}
//		{label=역겨운, frequency=339, score=55.16494, polarity=other}
//		{label=떠나다, frequency=305, score=53.31615, polarity=neutral}
//		{label=대표적, frequency=297, score=11.512738, polarity=neutral}
//		{label=똑같다, frequency=261, score=40.59972, polarity=neutral}
//		{label=범죄, frequency=212, score=1.8735881, polarity=negative}
//		{label=의혹, frequency=192, score=3.967096, polarity=negative}
//		{label=좋은, frequency=168, score=21.975758, polarity=other}
//		{label=정확한, frequency=164, score=24.677788, polarity=neutral}
//		{label=의문, frequency=162, score=2.8649797, polarity=neutral}
//		{label=안전하다, frequency=160, score=24.885527, polarity=other}
//		{label=의문 있다, frequency=160, score=24.220907, polarity=neutral}
//		{label=시도하다, frequency=148, score=23.390179, polarity=neutral}
		
		// API 조회 결과와 함께 페이지 뷰 리턴
		return "news_table";
	}
}
