package com.daumsoft.news_data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

import com.daumsoft.command.GetCategoryList;
import com.daumsoft.command.GetKeywordDocuments;
import com.daumsoft.command.HttpCommand;
import com.daumsoft.command.HttpConnection;
import com.daumsoft.command.HttpInputData;

public class NewsDataImpl implements NewsData{
	
	private HttpCommand httpCommand = null;				// API 쿼리 커맨드 설정 객체
	private HttpURLConnection connection = null; 		// API 쿼리 커넥션 획득 객체
	private HttpConnection httpConnection = null;		// API httpConnection 	
	private HttpInputData httpInputData = null;			// API 입력 변수 객체
	private StringBuilder readLineBuilder = null;		// API 응답 결과 획득 객체
	
	private NewsParser newsParser = null;
	
	// -- 디폴트 생성자
	public NewsDataImpl(){
		readLineBuilder = new StringBuilder();
		httpInputData = new HttpInputData();
		newsParser = new NewsParser();
	}
	
	// -- 명령어 찾기 & 명령어 설정
	public void setCommand(String command){
		/**
		 * ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
		 * 
		 * 반복되는 밑의 구문들을 리팩토링 하여야 한다. 
		 * 해당 클래스가 가진 응집도를 높이고, 새로운 오브젝를 통해서 
		 * request 하는 입력변수 파라미터를 재정의 하여아한다. 
		 * 해당 클래스에게 너무 많은 권한 부여 되어 있다.
		 * 
		 * ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
		 **/
		
		// 특정 분류체계의 모든 분류 조회
		if(command.equals("GetCategoryList")){
			httpCommand = new GetCategoryList();
			
		}
		
		// 입력한 키워드가 발현된 문서
		if(command.equals("GetKeywordDocuments")){
			httpCommand = new GetKeywordDocuments();
			
			httpInputData.setLanguage();
			httpInputData.setSource();
			httpInputData.setStartDate();
			httpInputData.setEndDate();
			httpInputData.setKeyword("한파");
			httpInputData.setCategorySetName("SM");
			httpInputData.setOrderType(0);
			httpInputData.setRowPerPage(10);
			httpInputData.setPageNum(3);
		}
		
		setConnection();
	}
	
	// -- 커넥션 설정
	public void setConnection(){
		if(this.httpConnection == null)
			this.httpConnection = new HttpConnection();
		
		this.connection = httpCommand.getCommandConnection(httpConnection, httpInputData.getMap());
	}
	
	// -- 응답 데이터 확인
	@Override
	public String getResponseData() {
		
		try{
			InputStreamReader inputStreamReader = new InputStreamReader(connection.getInputStream(), "UTF-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			String line = new String();
			
			// 결과값 읽어들이기.
			while((line = bufferedReader.readLine()) != null)
				readLineBuilder.append(line);
			
			// json 파싱
//			newsParser.parseJson2Object(readLineBuilder.toString());
		}
		catch(IOException e){
			e.printStackTrace();
		}
		
		return null;
	}
}
