package com.daumsoft.news_data;

public class NewsGroup {
	
	/**
	 * ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
	 * 
	 * 주어진 분류체계 코드로는 분류가 제대로 되지 않는다. 
	 * 따라서 주어진 데이터로 얼마나 효율적인 기사내용을 추출할 수 있는지가 중요하다.
	 * 
	 * 따라서 특정 분야에 대한 기사를 검색하는 경우, 해당 분류체계를 단일만
	 * 사용하는 것보다 상위 키워드끼리 서로간의 '&&'연산을 통하여 기사내용을
	 * 추출하면 조금 더 특정분야에 대해서 집중된 기사만을 추출할 수 있다고 판단하였다.
	 * 
	 * [ 일단 보류 ]
	 * 
	 * ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
	 * **/
	// 정치, 시사 분야
	private static final String politics1 = "1/1/1";	// 사회인			(정치인)
	private static final String politics2 = "2/1/9";	// 공공기관		(국회, 청와대)
	private static final String politics3 = "2/2/10";	// 사회기관, 단체	(정당)
	
	
//	
//	private static final String politics1 = "1/1/1";	// 사회		(정치인)
//	private static final String politics2 = "2/1/9";	// 정부 조직	(국회, 청와대)
//	private static final String politics3 = "2/2/0";	// 기관 단체	(정당)
}
