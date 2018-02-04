package com.daumsoft.news;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.daumsoft.news_data.NewsAssocSentiment;
import com.daumsoft.news_data.NewsDataImpl;
import com.daumsoft.news_data.NewsDocumentList;
import com.daumsoft.news_data.NewsTopKeywords;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private NewsDataImpl newsData = new NewsDataImpl();
	private Map[] keywordDocumentsMap = null;
	private Map[] topAssociateSentimentMap = null;
	
	private String topKeyword = null;
	private NewsDocumentList newsDocumentList = new NewsDocumentList();
	
	private List<NewsAssocSentiment> sentimentList = new ArrayList<NewsAssocSentiment>();
	private SideController sideController = new SideController();
	// -- Home 메뉴 요청
	@RequestMapping(value="/home")
	public String newsMenuCallHome(Model model){
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
		
		// -- GetTopKeywords 로 탑 키워드 10개 추출
		String menu = request.getParameter("menu");
		String[] menuAndCateogryAndCode = sideController.getClassification(menu);
		
		model = WorkAboutSideMenuBarProcess(model, menuAndCateogryAndCode);
		
		return "news_table";
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
	public Model WorkAboutSideMenuBarProcess(Model model, String[]menuAndCateogryAndCode){
//		System.out.println(Arrays.toString(menuAndCateogryAndCode));
		
		newsData.setCommand(menuAndCateogryAndCode, 1, null);
		@SuppressWarnings("unchecked")
		List<NewsTopKeywords> list = (List<NewsTopKeywords>) newsData.getResponseData(10)[0].get("topKeywords");
		
		// 한번 완전히 클리어하고 새롭게 시작
		// 그렇게 하지 않으면 계속 값이 추가된다.
		sentimentList.clear();
		
		NewsAssocSentiment newsAssocSentiment = new NewsAssocSentiment();
		newsAssocSentiment.setLabel(null);
		newsAssocSentiment.setFrequency(0);
		newsAssocSentiment.setScore(0);
		newsAssocSentiment.setPolarity(null);
		sentimentList.add(newsAssocSentiment);
		
		// -- 추출된 키워드로 GetTopAssocSentimentByPeriod 실시 10개 추출 => 100개 감성블럭 생성 가능
		for (int i = 0; i < list.size(); i++) {
			newsData.setCommand(menuAndCateogryAndCode, 2, list.get(i).getKeyword());
			topAssociateSentimentMap = newsData.getResponseData();
			
//			System.out.println(list.get(i).getKeyword());
			
			// 10번 반복
			for (int j = 1; j <= 10; j++) {
				@SuppressWarnings("unchecked")
				Map<String, Object> rankMap = (Map<String, Object>) topAssociateSentimentMap[0].get("rank" + j);
				
				if(rankMap == null)
					break;
				
				newsAssocSentiment = new NewsAssocSentiment();
				newsAssocSentiment.setLabel((String) rankMap.get("label"));
				newsAssocSentiment.setFrequency((Integer) rankMap.get("frequency"));
				newsAssocSentiment.setScore((Double) rankMap.get("score"));
				newsAssocSentiment.setPolarity((String) rankMap.get("polarity"));
				
				sentimentList.add(newsAssocSentiment);
			}
		}
		
//		for(NewsAssocSentiment n : sentimentList)
//			System.out.println(n.getFrequency() + ", " + n.getPolarity());
		
		model.addAttribute("sentiment", sentimentList);
		return model;
	}
}
