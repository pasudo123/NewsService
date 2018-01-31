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
	private NewsDocumentList newsDocumentList = new NewsDocumentList();
	
	// -- Home 메뉴 요청
	@RequestMapping(value="/home")
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
		
		// 도메인 객체
		model.addAttribute("newsDocumentList", newsDocumentList);
		
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
	@RequestMapping(value="/news_content", method=RequestMethod.POST)
	public String showNewsContent(@ModelAttribute("NewsDocumentList") NewsDocumentList newsDocumentList, Model model){
		
		model.addAttribute("title", newsDocumentList.getTitle());
		model.addAttribute("content", newsDocumentList.getContent());
		model.addAttribute("imageUrl", newsDocumentList.getImageUrl());
		model.addAttribute("parseDate", newsDocumentList.getParseDate());
		model.addAttribute("writerRealName", newsDocumentList.getWriterRealName());
		
		System.out.println(">>>>>>>>\n" + newsDocumentList.getParseDate()[0]);
		System.out.println(">>>>>>>>\n" + newsDocumentList.getParseDate()[1]);
		System.out.println(">>>>>>>>\n" + newsDocumentList.getParseDate()[2]);
		System.out.println(">>>>>>>>\n" + newsDocumentList.getParseDate()[3]);
		return "news_content";
	}
}
