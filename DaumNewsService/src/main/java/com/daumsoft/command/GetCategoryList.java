package com.daumsoft.command;

import java.net.HttpURLConnection;
import java.util.Map;

public class GetCategoryList implements HttpCommand{
	
	/***
	 * 
	 * [ 분류 코드 목록 조회 ]
	 * - 특정 분류 체계의 모든 분류 조회
	 * 
	 ***/
	
	// -- 해당 API 쿼리에 대한 기본 명령어
	private static final String Command = "GetCategoryList";

	@Override
	public HttpURLConnection getCommandConnection(HttpConnection httpConnection, Map<String, Object> parameterMap) {
		StringBuilder url = new StringBuilder(HttpCommand.BASE_URL);

		// 커넥션 객체 설정
		httpConnection.setConnection(url, GetCategoryList.Command, parameterMap);
		
		// 커넥션 객체 반환
		return httpConnection.getHttpURLConnection();
	}
}
