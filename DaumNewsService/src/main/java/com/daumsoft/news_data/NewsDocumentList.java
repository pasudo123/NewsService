package com.daumsoft.news_data;

public class NewsDocumentList {
	
	/**
	 *
	 * [ GetKeywordDocuments ] Json 파싱 객체 
	 * 
	 * **/
	
	String documentDate = null;		// 날짜	
	String parseYear = null;		// 년도
	String parseMonth = null;		// 달
	String parseDay = null;			// 일
	String parseWeekDay = null;		// 주
	String parseHour = null;		// 시
	String parseMinute = null;		// 분
	
	
	String sequence = null;			// 순서
	String title = null;			// 제목
	String content = null;			// 내용
	String url = null;				// 기사 url
	String imageUrl = null;			// 기사 이미지 url
	String writerRealName = null;	// 실제 작성자
	String index = null;			// 객체 인덱스
	
	public String getDocumentDate() {
		return documentDate;
	}
	
	public String getSequence() {
		return sequence;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getContent() {
		return content;
	}
	
	public String getUrl() {
		return url;
	}
	
	public String getWriterRealName() {
		return writerRealName;
	}
	
	public void setDocumentDate(String documentDate) {
		this.documentDate = documentDate;
	}
	
	public void setSequence(String sequence) {
		this.sequence = sequence;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getImageUrl() {
		return imageUrl;
	}
	
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
	public void setWriterRealName(String writerRealName) {
		this.writerRealName = writerRealName;
	}
	
	public String getIndex() {
		return index;
	}
	
	public void setIndex(String index) {
		this.index = index;
	}
	
	public String getParseYear() {
		return parseYear;
	}

	public String getParseMonth() {
		return parseMonth;
	}

	public String getParseDay() {
		return parseDay;
	}

	public String getParseWeekDay() {
		return parseWeekDay;
	}

	public void setParseYear(String parseYear) {
		this.parseYear = parseYear;
	}

	public void setParseMonth(String parseMonth) {
		this.parseMonth = parseMonth;
	}

	public void setParseDay(String parseDay) {
		this.parseDay = parseDay;
	}

	public void setParseWeekDay(String parseWeekDay) {
		this.parseWeekDay = parseWeekDay;
	}

	public String getParseHour() {
		return parseHour;
	}

	public String getParseMinute() {
		return parseMinute;
	}

	public void setParseHour(String parseHour) {
		this.parseHour = parseHour;
	}

	public void setParseMinute(String parseMinute) {
		this.parseMinute = parseMinute;
	}

	@Override
	public String toString() {
		return "NewsDocumentList [documentDate=" + documentDate + ", sequence=" + sequence + ", title=" + title
				+ ", content=" + content + ", url=" + url + ", writerRealName=" + writerRealName + "]";
	}
}
