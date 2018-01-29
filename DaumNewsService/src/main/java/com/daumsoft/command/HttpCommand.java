package com.daumsoft.command;

import java.net.HttpURLConnection;
import java.util.Map;

public interface HttpCommand {
	/**
	 * http://qt0.some.co.kr/TrendMap/JSON/ServiceHandler?command=XXXXXXXXXXXXXX
	 * **/
	
	static final String DOMAIN = "qt0.some.co.kr";
	static final String BASE_URL = "http://" + DOMAIN + "/TrendMap/JSON/ServiceHandler?";
	
	// 해당 쿼리에 대한 url 설정
	public HttpURLConnection getCommandConnection(HttpConnection httpConnection, Map<String, Object> parameterMap);
}
