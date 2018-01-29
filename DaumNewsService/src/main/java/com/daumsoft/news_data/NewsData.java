package com.daumsoft.news_data;

public interface NewsData {
	
	// -- 명령어 설정
	public void setCommand(String command);
	
	// -- API 로 값을 요청하고 반환된 내용을 획득하기 위함
	public String getResponseData();
}
