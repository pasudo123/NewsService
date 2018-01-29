package com.daumsoft.news_data;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	public List<Object> readAndParseData(BufferedReader bufferedReader, String command){
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
	
	public List<Object> convertListAboutGetCategoryList(String line){
		return null;
	}
	
	// -- string 데이터를 list 컬렉션 객체로 변환
	// -- 미확인 오퍼레이션과 관련된 경고 제거
	@SuppressWarnings("unchecked")
	public List<Object> convertListAboutGetKeywordDocuments(String line){
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
		
		return elementList;
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
		
		return mapArray;
	}
}
