package com.daumsoft.command;

import java.net.HttpURLConnection;
import java.util.Map;

public class GetTopKeywords implements HttpCommand{
	
	// -- 해당 분류 체계에 대한 상위 키워드 받는 명령어
	private static final String Command = "GetTopKeywords";
	
	@Override
	public HttpURLConnection getCommandConnection(HttpConnection httpConnection, Map<String, Object> parameterMap) {
		StringBuilder url = new StringBuilder(HttpCommand.BASE_URL);
		
		// 커넥션 객체 설정
		httpConnection.setConnection(url, GetTopKeywords.Command, parameterMap);
		
		// 커넥션 객체 반환
		return httpConnection.getHttpURLConnection();
	}
}
