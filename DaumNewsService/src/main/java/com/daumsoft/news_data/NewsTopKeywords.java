package com.daumsoft.news_data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * http://www.baeldung.com/jackson-exception
 * **/

public class NewsTopKeywords {
	private String keyword = null;
	private String frequency = null;

	
	@JsonCreator
	public NewsTopKeywords(@JsonProperty("keyword") String keyword, @JsonProperty("frequency") String frequency){
		this.keyword = keyword;
		this.frequency = frequency;
	}
	
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getFrequency() {
		return frequency;
	}
	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}
	
}
