package com.daumsoft.news_data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

public class NewsParser<T> {
	private JsonParser jsonParser = null;
	private List<T> elementList = null;
	private NewsDocumentList newsDocumentList = null;
	
	public NewsParser(){
		elementList = new ArrayList<T>();
		newsDocumentList = new NewsDocumentList();
	}
	
	// -- Json 파일을 Object 로 변경
	public void parseJson2Object(String line){
		try {
			jsonParser = new JsonFactory().createParser(line);
			
			// END_OBJECT 만나기 전까지 토큰 순회
			while(jsonParser.nextToken() != JsonToken.END_OBJECT){
				
			}
		} 
		
		catch (JsonParseException e) {
			e.printStackTrace();
		} 
		
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// -- Json 파일 내에서 추출 네임 값 체크
	public boolean checkParser(String currentName){
		if(currentName.equals("date"))
			return false;
		if(currentName.equals("sequence"))
			return false;
		if(currentName.equals("title"))
			return false;
		if(currentName.equals("content"))
			return false;
		if(currentName.equals("url"))
			return false;
		if(currentName.equals("writeName"))
			return false;
		if(currentName.equals("writeRealName"))
			return false;
		
		return true;
	}
}
