package com.daumsoft.news_data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.Map;

import com.daumsoft.command.GetCategoryList;
import com.daumsoft.command.GetKeywordDocuments;
import com.daumsoft.command.GetTopAssocSentimentByPeriod;
import com.daumsoft.command.GetTopKeywords;
import com.daumsoft.command.HttpCommand;
import com.daumsoft.command.HttpConnection;
import com.daumsoft.command.HttpInputData;

public class NewsDataImpl implements NewsData {

	private String myCommand = new String();
	private HttpCommand httpCommand = null; // API 쿼리 커맨드 설정 객체
	private HttpURLConnection connection = null; // API 쿼리 커넥션 획득 객체
	private HttpConnection httpConnection = null; // API httpConnection
	private HttpInputData httpInputData = null; // API 입력 변수 객체

	private NewsParser newsParser = null;

	// -- 디폴트 생성자
	public NewsDataImpl() {
		httpInputData = new HttpInputData();
		newsParser = new NewsParser();
	}

	// -- 명령어 찾기 & 명령어 설정
	public void setCommand(String command, String category, String keyword) {
		/**
		 * ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
		 * 
		 * 반복되는 아래의 구문들을 리팩토링 하여야 한다. 해당 클래스가 가진 응집도를 높이고, 새로운 오브젝트를 통해서 request
		 * 하는 입력변수 파라미터를 재정의 하여아한다. 해당 클래스에게 너무 많은 권한 부여 되어 있다.
		 * 
		 * ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
		 **/

		// 멤버 필드 설정
		this.myCommand = command;

		// -- 특정 분류체계의 모든 분류 조회
		if (command.equals("GetCategoryList")) {
			httpCommand = new GetCategoryList();
		}

		// -- 입력한 키워드가 발현된 문서
		if (command.equals("GetKeywordDocuments")) {
			httpCommand = new GetKeywordDocuments();

			/**
			 * ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
			 * 
			 * 언어, 매체, 날짜 시간, 카테고리 분류체계는 다 정해져 있는 상태 [ GetTopKeywords ] 에서 객체 생성
			 * 이후에 해당 객체를 이용하기 때문
			 * 
			 ** ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
			 **/

			httpInputData.setOrderType(0);
			httpInputData.setRowPerPage(10);
			httpInputData.setPageNum(3);

			// 해당 분류 체계에 맞는 키워드를 얻어야 한다.
			httpInputData.setKeyword(keyword);
		}

		// -- 분류 체계 코드에 해당하는 상위 탑 키워드 추출
		if (command.equals("GetTopKeywords")) {
			httpCommand = new GetTopKeywords();

			/**
			 * ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 
			 * 
			 * setCategoryList 내부에 기입 내용은 직접 정해주었다.
			 * 
			 ** ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
			 */

			// -- 정치 시사, 관련 상위 키워드 : SM
			if (category.equals("politics")) {
				httpInputData.setCategoryList("1/1/0");
				httpInputData.setCategorySetName("SM");
			}

			// -- 사회 경제, 관련 상위 키워드 : LS
			if (category.equals("sociaty")) {
				httpInputData.setCategoryList("10/1/37");
				httpInputData.setCategorySetName("LS");
			}

			// -- 세계, 관련 상위 키워드 : SM
			if (category.equals("global")) {
				httpInputData.setCategoryList("3/1/20");
				httpInputData.setCategorySetName("SM");
			}

			// -- 문화 생활, 관련 상위 키워드 : LS
			if (category.equals("culture")) {
				httpInputData.setCategoryList("8/0/0");
				httpInputData.setCategorySetName("LS");
			}

			// -- IT, 관련 상위 키워드 : LS
			if (category.equals("IT")) {
				httpInputData.setCategoryList("7/0/0");
				httpInputData.setCategorySetName("LS");
			}

			// 한국어, 뉴스 매체, 한 개 상위 키워드, 시작 종료 날짜,
			httpInputData.setLanguage();
			httpInputData.setSource();
			httpInputData.setTopN(1);
			httpInputData.setDate();
		}

		if (command.equals("GetTopAssocSentimentByPeriod")) {
			httpCommand = new GetTopAssocSentimentByPeriod();

			// stack memory 에서만, 쓰이도록
			HttpInputData stackHttpInputData = new HttpInputData();

			stackHttpInputData.setLanguage();
			stackHttpInputData.setColorIssueSoruce();
			stackHttpInputData.setColorIssueDate();
			stackHttpInputData.setKeyword(keyword);
			stackHttpInputData.setTopN(10);
			stackHttpInputData.setPeriod();

			setConnection(stackHttpInputData);
			return;
		}

		setConnection(null);
	}

	@Override
	public void setCommand(String[] menuAndCategoryAndCode, int flag, String keyword) {
		
		if(flag == 1){
			myCommand = "GetTopKeywords";
			httpCommand = new GetTopKeywords();
	
			httpInputData.clear();
			httpInputData.setCategoryList(menuAndCategoryAndCode[2]);
			httpInputData.setCategorySetName("SM");
			httpInputData.setLanguage();
			httpInputData.setTopN(10);
			httpInputData.setColorIssueDate();
			httpInputData.setColorIssueSoruce();
		}
		else{
			myCommand = "GetTopAssocSentimentByPeriod";
			httpCommand = new GetTopAssocSentimentByPeriod();
			
			httpInputData.clear();
			httpInputData.setLanguage();
			httpInputData.setColorIssueSoruce();
			httpInputData.setColorIssueDate();
			httpInputData.setKeyword(keyword);
			httpInputData.setTopN(10);
			httpInputData.setPeriod();
		}

		setConnection(null);
	}

	// -- 커넥션 설정
	public void setConnection(HttpInputData parameterHttpInputData) {
		if (this.httpConnection == null)
			this.httpConnection = new HttpConnection();

		if (parameterHttpInputData == null) {
			this.connection = httpCommand.getCommandConnection(httpConnection, httpInputData.getMap());
			return;
		}

		this.connection = httpCommand.getCommandConnection(httpConnection, parameterHttpInputData.getMap());
	}

	// -- 응답 데이터 확인
	@Override
	@SuppressWarnings("rawtypes")
	public Map[] getResponseData() {
		BufferedReader bufferedReader = this.getBuffferReader();

		// -- 1개 상위 키워드 추출
		return newsParser.readAndParseData(bufferedReader, myCommand);
	}

	// -- 제네릭 사용 매개변수 불특정 경고 제거
	@Override
	@SuppressWarnings("rawtypes")
	public Map[] getResponseData(int check) {
		BufferedReader bufferedReader = this.getBuffferReader();
		
		// -- 10개 상위 키워드 추출
		return newsParser.readAndParseData(bufferedReader, myCommand + "10");
	}
	
	public BufferedReader getBuffferReader(){
		try {
			InputStreamReader inputStreamReader = new InputStreamReader(connection.getInputStream(), "UTF-8");
			return new BufferedReader(inputStreamReader);
		}

		catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
