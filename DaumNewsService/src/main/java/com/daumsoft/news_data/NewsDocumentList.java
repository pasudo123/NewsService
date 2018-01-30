package com.daumsoft.news_data;

public class NewsDocumentList {
	
	/**
	 *
	 * [ GetKeywordDocuments ] Json 파싱 객체 
	 * 
	 * **/
	
	String documentDate = null;		// 날짜	
	String sequence = null;			// 순서
	String title = null;			// 제목
	String content = null;			// 내용
	String url = null;				// 기사 url
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
	public void setWriterRealName(String writerRealName) {
		this.writerRealName = writerRealName;
	}
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	
	@Override
	public String toString() {
		return "NewsDocumentList [documentDate=" + documentDate + ", sequence=" + sequence + ", title=" + title
				+ ", content=" + content + ", url=" + url + ", writerRealName=" + writerRealName + "]";
	}
}
