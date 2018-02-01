package com.daumsoft.command;

import java.net.HttpURLConnection;
import java.util.Map;

public class GetTopAssocSentimentByPeriod implements HttpCommand{
	
	/***
	 * 
	 * [ 기간 별 상위 감성 ]
	 * - 기간단위별로 기간을 나눠서 연관어를 구한 후 감성 기준으로 정리한 결과
	 * 
	 ***/
	
	// -- 기간 별 연관어 획득 이후에 감성 기준을 받는 명령어
	private static final String COMMAND = "GetTopAssocSentimentByPeriod";
	
	@Override
	public HttpURLConnection getCommandConnection(HttpConnection httpConnection, Map<String, Object> parameterMap) {
		StringBuilder url = new StringBuilder(HttpCommand.BASE_URL);
		
		// 커넥션 객체 설정
		httpConnection.setConnection(url, GetTopAssocSentimentByPeriod.COMMAND, parameterMap);
		
		// 커넥션 객체 반환
		return httpConnection.getHttpURLConnection();
	}

}
