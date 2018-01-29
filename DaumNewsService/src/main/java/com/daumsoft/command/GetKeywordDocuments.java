package com.daumsoft.command;

import java.net.HttpURLConnection;
import java.util.Map;

public class GetKeywordDocuments implements HttpCommand{
	
	/***
	 * 
	 * [ 문서 목록 ]
	 * - 입력한 키워드가 발현된 문서
	 * 
	 ***/
	// -- 해당 API 쿼리에 대한 기본 명령어
	private static final String Command = "GetKeywordDocuments";
	
 	@Override
	public HttpURLConnection getCommandConnection(HttpConnection httpConnection, Map<String, Object> parameterMap) {
 		StringBuilder url = new StringBuilder(HttpCommand.BASE_URL);

		// 커넥션 객체 설정
		httpConnection.setConnection(url, GetKeywordDocuments.Command, parameterMap);
		
		// 커넥션 객체 반환
		return httpConnection.getHttpURLConnection();
	}
}
