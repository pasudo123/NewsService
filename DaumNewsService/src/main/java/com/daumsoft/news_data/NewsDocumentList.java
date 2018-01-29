package com.daumsoft.news_data;

public class NewsDocumentList {
	
	/**
	 *
	 * [ GetKeywordDocuments ] Json 파싱 객체 
	 * 
	 * **/
	
	String date = null;				// 날짜	
	String sequence = null;			// 순서
	String projectId = null;		// 프로젝트 아이디
	String title = null;			// 제목
	String content = null;			// 내용
	String url = null;				// 기사 url
	String writeName = null;		// 작성자
	String writeRealName = null;	// 실제 작성자
	
	
	// -- getter() & setter()
	public String getDate() {
		return date;
	}
	public String getSequence() {
		return sequence;
	}
	public String getProjectId() {
		return projectId;
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
	public String getWriteName() {
		return writeName;
	}
	public String getWriteRealName() {
		return writeRealName;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public void setSequence(String sequence) {
		this.sequence = sequence;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
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
	public void setWriteName(String writeName) {
		this.writeName = writeName;
	}
	public void setWriteRealName(String writeRealName) {
		this.writeRealName = writeRealName;
	}
}
