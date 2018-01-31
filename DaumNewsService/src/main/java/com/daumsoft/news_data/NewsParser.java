package com.daumsoft.news_data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.HttpClientBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class NewsParser{
	private List<Object> elementList = null;
	
	public NewsParser(){
		elementList = new ArrayList<Object>();
	}
	
	// -- json 데이터를 String 으로 변환
	public Map[] readAndParseData(BufferedReader bufferedReader, String command){
		StringBuilder readLineBuilder = new StringBuilder();
		String line = new String();
		
		try{
			// 결과값 한줄씩 읽어들이기.
			while((line = bufferedReader.readLine()) != null)
				readLineBuilder.append(line);
		}
		catch(IOException e){
			e.printStackTrace();
		}
		
		if(command.equals("GetCategoryList"))
			return convertListAboutGetCategoryList(readLineBuilder.toString());
		if(command.equals("GetKeywordDocuments"))
			return convertListAboutGetKeywordDocuments(readLineBuilder.toString());
		
		// 임시
		return convertListAboutGetKeywordDocuments(readLineBuilder.toString());
	}
	
	
	public Map[] convertListAboutGetCategoryList(String line){
		return null;
	}
	
	// -- string 데이터를 list 컬렉션 객체로 변환
	// -- 미확인 오퍼레이션과 관련된 경고 제거
	@SuppressWarnings("unchecked")
	public Map[] convertListAboutGetKeywordDocuments(String line){
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> map = new HashMap<String, Object>();
		
		try {
			map = mapper.readValue(line, new TypeReference<Map<String, Object>>(){});
		} 
		catch (JsonParseException e) {
			e.printStackTrace();
		} 
		catch (JsonMappingException e) {
			e.printStackTrace();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		
		// [ GetKeywordDocuments ] 명령어
		elementList = (List<Object>) map.get("documentList");
		
		return convertList2Map(elementList);
	}
	
	// -- list 컬렉션 객체를 map[] 로 반환
	@SuppressWarnings("unchecked")
	public Map[] convertList2Map(List<Object> list){
		
		// 맵 데이터, 맵 배열
		Map<String, Object> mapData = new HashMap<String, Object>();
		Map[]mapArray = new HashMap[list.size()];
		int size = mapArray.length;
		
		// 맵 배열 초기화
		for(int index = 0; index < size; index++)
			mapArray[index] = new HashMap<String, Object>();
		
		
		// 맵 데이터로 캐스팅 이후 맵 배열 인덱스별로 키 밸류 초기화
		for(int index = 0; index < size; index++){
			mapData = (Map<String, Object>) list.get(index);
			
			for(Map.Entry<String, Object> map : mapData.entrySet()){
				mapArray[index].put(map.getKey(), map.getValue());
			}
		}
		
		// 해당 맵 데이터에서, documentDate 값 정리
		for(int index = 0; index < size; index++){
			String[]dateList = getFormatDate(mapArray[index].get("documentDate"));
			
			mapArray[index].put("parseYear", dateList[0]);
			mapArray[index].put("parseMonth", dateList[1]);
			mapArray[index].put("parseDay", dateList[2]);
			mapArray[index].put("parseWeekDay", dateList[3]);
		}
		
		// 해당 맵 데이터에서, url 경로 크롤링 >> 이미지 경로 획득
		for(int index = 0; index < size; index++){
			String valueUrl = (String) mapArray[index].get("url");
			mapArray[index].put("imageUrl", crawlingImplementJava(valueUrl));
		}
		
		/**
		 * ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
		 * imageUrl 이 null 인 경우,
		 * 만들어놓은 이미지 URL 삽입
		 * 
		 * [ 보류 ]
		 * ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
		 * **/
		
		return mapArray;
	}
	
	public String[] getFormatDate(Object paramObject){
		// 캐스팅
		String documentDate = (String) paramObject;
		
		String year = documentDate.substring(0, 4);
		String month = documentDate.substring(4, 6);
		String day = documentDate.substring(6, 8);
		String weekDay = null; 
		
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		Date date = null;
		Calendar calendar = null;
		
		try{
			date = dateFormat.parse(year + month + day);
			
			calendar = Calendar.getInstance();
			calendar.setTime(date);
		}
		catch(ParseException e){
			e.printStackTrace();
		}
		
		switch(calendar.get(Calendar.DAY_OF_WEEK)){
	        case 1:
	        	weekDay = "일";
	            break ;
	        case 2:
	        	weekDay = "월";
	            break ;
	        case 3:
	        	weekDay = "화";
	            break ;
	        case 4:
	        	weekDay = "수";
	            break ;
	        case 5:
	        	weekDay = "목";
	            break ;
	        case 6:
	        	weekDay = "금";
	            break ;
	        case 7:
	        	weekDay = "토";
	            break ;
		}
        
		// 앞에 0 제거
		if(month.startsWith("0"))
			month = month.substring(1);
		
		if(day.startsWith("0"))
			day = day.substring(1);
		
		String[]dateList = new String[4];
		dateList[0] = year + "년";
		dateList[1] = month + "월";
		dateList[2] = day + "일";
		dateList[3] = weekDay + "요일";
		
		return dateList;
	}
	
	// -- url 따라 크롤링
	public String crawlingImplementJava(String url){
		
		// 획득할 Http 주소 배열 
		HttpPost httpPost = null;
		
		// 획득 Http 주소 설정
		httpPost = new HttpPost(url);
			
		// 실행 클라이언트 객체 생성
		HttpClient httpClient = HttpClientBuilder.create().build();
		HttpResponse httpResponse = null;
		
		try{
			// 실행 클라이언트 및 데이터를 Response 객체로 초기화
			httpResponse = httpClient.execute(httpPost);
		}
		catch(IOException e){
			e.printStackTrace();
		}
			
		// Response 데이터 중, DOM 데이터를 객체에 저장
		HttpEntity httpEntity = httpResponse.getEntity();
		
		// Charset 을 알아내기 : DOM 컨텐트 타입을 가져와 담고 charset을 가져옴
		ContentType contentType = ContentType.getOrDefault(httpEntity);
		Charset charset = contentType.getCharset();
		
		BufferedReader bufferedReader = null;
		try {
			// 데이터 읽기 위함
			bufferedReader = new BufferedReader(new InputStreamReader(httpEntity.getContent(), charset));
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		
		StringBuffer sb = new StringBuffer();
		String line = new String();
		
		try {
			while((line = bufferedReader.readLine()) != null){
				sb.append(line + "\n");
			}
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		
		return getImageOrVideo(sb.toString());
	}
	
	// -- 크롤링된 데이터 이미지 또는 동영상 추출
	public String getImageOrVideo(String html){
		
		/**
		 * https://jsoup.org/
		 * **/
		
		// (1) Parse a document from a String
		Document doc = (Document) Jsoup.parse(html);
		
		// (2) Use DOM methods to navigate a document
		Elements metaTag = doc.getElementsByTag("meta");
		
		for(Element element : metaTag){
			
			// 메타 태그에서 이미지 추출
			if(element.attr("property").equals("og:image"))
				return element.attr("content");
		}
		
		return null;
	}
}
