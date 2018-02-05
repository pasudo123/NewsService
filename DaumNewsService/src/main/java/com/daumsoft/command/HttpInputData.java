package com.daumsoft.command;

import java.util.HashMap;
import java.util.Map;

public class HttpInputData {
	private Map<String, Object> map = null;
	private GetParameterDate getParameterDate = new GetParameterDate();
	private String source = null;

	// -- 생성자
	public HttpInputData(){
		this.map = new HashMap<String, Object>();
	}
	
	// -- 파라미터 맵 반환
	public Map<String, Object> getMap(){
		return this.map;
	}
	
	/**ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
	 * 파라미터 데이터 삽입 메소드 나열
	 * ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ**/
	
	// --매체관련 
	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}
	
	// 언어 선택
	public void setLanguage(){
		map.put("lang", "ko");
	}
	
	// 매체 선택
	public void setSource(){
		setSource("news");
		map.put("source", "news");
	}
	
	// 연관어 감성 키워드 : 매체 선택
	public void setColorIssueSoruce(){
		setSource("twitter");
		map.put("source", "twitter");
	}
	
	// 날짜 설정
	public void setDate(){
		getParameterDate.setWeeksAgoDate();
		setStartDate(getParameterDate.getStartDate());
		setEndDate(getParameterDate.getEndDate());
	}
	
	// 연관어 감성 키워드 : 시작 및 종료 날짜 설정 (최근날짜, 즉 끝나는 날짜로 설정)
	public void setColorIssueDate(){
		getParameterDate.setWeeksAgoDate();
		setStartDate(getParameterDate.getColorIssueGetEndDate());
		setEndDate(getParameterDate.getColorIssueGetEndDate());
	}
	
	// 시작 날짜
	public void setStartDate(String startDate){
		map.put("startDate", startDate);
	}
	
	// 종료 날짜
	public void setEndDate(String endDate){
		map.put("endDate", endDate);
	}
	
	// 조회하고자 하는 주제어
	public void setKeyword(Object value){
		map.put("keyword", value);
	}
	
	// 문서 정렬 방식 (0:최신순, 1:정확도)
	public void setOrderType(Object value){
		if(((Integer) value) == 1){
			map.put("orderType", "1");
			return;
		}
		
		map.put("orderType", "0");
	}
	
	// 페이지당 문서수 (setPageNum 갯수당 나타나는 글의 수이다)
	public void setRowPerPage(Object value){
		int documentNumber = (Integer) value;
		
		map.put("rowPerPage", String.valueOf(documentNumber));
	}
	
	// 페이지 번호
	public void setPageNum(Object value){
		int pageNumber = (Integer) value;
		
		map.put("pageNum", String.valueOf(pageNumber));
	}
	
	// 분류 체계 명 설정
	public void setCategorySetName(Object value){
		map.put("categorySetName", value);
	}
	
	// 출력 옵션 설정
	public void setOutputOption(){
		map.put("outputOption", "tree");
	}
	
	// 분류 체계 코드 설정
	public void setCategoryCode(Object value){
		map.put("categoryCode", value);
	}
	
	// 분류 체계 코드 설정 (정치 시사, 사회 경제, 세계, 문화 생활, IT)
	public void setCategoryList(Object value){
		map.put("categoryList", value);
	}
	
	// 출력 개수 설정
	public void setTopN(Object value){
		int topN = (Integer)value;
		
		map.put("topN", topN);
	}
	
	// 출력 주기 설정
	public void setPeriod(){
		// 일별로만 보여주기 때문에 0
		map.put("period", 0);
	}
	
	// 맵 데이터 삭제
	public void clear(){
		map.clear();
	}
}
