package com.daumsoft.news_data;

import java.util.Map;

public interface NewsData {
	
	// -- 리팩토링으로 메소드 오버로딩도 생각해볼 수 있다.
	// -- 해당 키워드를 통한 문서 발현 & 분류 체계를 통한 상위 키워드 획득
	public void setCommand(String command, String category, String keyword);
	
	// -- API 로 값을 요청하고 반환된 내용을 획득하기 위함
	public Map[] getResponseData();
}
