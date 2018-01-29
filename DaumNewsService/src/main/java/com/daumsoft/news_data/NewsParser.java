package com.daumsoft.news_data;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonParser;

public class NewsParser<T> {
	private JsonParser jsonParser = null;
	private List<T> elementList = null;
	private NewsDocumentList newsDocumentList = null;
	
	public NewsParser(){
		elementList = new ArrayList<T>();
		newsDocumentList = new NewsDocumentList();
	}
	
	// -- Json 파일을 Text 로 변경
	public void parseJson2Text(String line){
		
	}
}
