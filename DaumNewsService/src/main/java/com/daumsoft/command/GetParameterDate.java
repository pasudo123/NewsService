package com.daumsoft.command;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class GetParameterDate {
	private String startDate = null; // 시작날짜
	private String endDate = null; // 종료날짜

	// 60초(1분) * 60분(1시간) * 24시간 * 1000(1/1000 이기 때문에) * 7(일주일)
	private static final long WEEK = (60 * 60 * 24 * 1000) * 7;

	// 현재날짜로부터 최근 일주일 날짜 계산
	public void setWeeksAgoDate() {
		/**
		 * SimpleDateFormat - Date 클래스를 원하는 포맷으로 출력 가능하도록 설정
		 **/
		
		SimpleDateFormat simpleDateFormat = null;
		Date date = null;
		
		simpleDateFormat = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);
		date = new Date();
		endDate = simpleDateFormat.format(date);
		
		// 밀리세컨드를 반환(1/1000초)
		date = new Date(date.getTime() - WEEK);
		startDate = simpleDateFormat.format(date);
	}

	// -- 시작날짜 리턴
	public String getStartDate() {
		return startDate;
	}

	// -- 종료날짜 리턴
	public String getEndDate() {
		return endDate;
	}
}
