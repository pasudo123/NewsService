package com.daumsoft.command;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

public class HttpConnection {	
	private URL apiUrl = null;
	private HttpURLConnection connection = null;
	private byte[] dataBytes = null;

	private StringBuilder urlBuilder = null;
	
	// -- HttpURL에 대한 입력변수 값 url에 설정
	public void setConnection(StringBuilder parameterUrl, String command, Map<String, Object> parameterMap){
		StringBuilder url = new StringBuilder(parameterUrl);
		
		try{
			// -- API 쿼리 명령어 입력
			url.append("command");
			url.append("=");
			url.append(URLEncoder.encode(command, "UTF-8"));
			
			// -- API 쿼리 입력 변수 차례로 입력
			for(Map.Entry<String, Object> map : parameterMap.entrySet()){
				url.append("&");
				url.append(URLEncoder.encode(map.getKey(), "UTF-8"));
				url.append("=");
				url.append(URLEncoder.encode(String.valueOf(map.getValue()), "UTF-8"));
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
//		System.out.println("HttpConnection : " + url);
		this.urlBuilder = url;
	}
	
	// -- HttpURL에 대한 커넥터 객체 생성 및 반환
	public HttpURLConnection getHttpURLConnection(){
		try {
			String urlString = urlBuilder.toString();
			
			dataBytes = urlString.getBytes("UTF-8");
			apiUrl = new URL(urlString);
			
			/**
			 * ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
			 * - URL 주소로 GET/POST 로 데이터를 전달할 때 이용
			 * - OpenConnection : 연결
			 * - setDoOutput : 서버 통신에서 출력 가능한 상태로 만듦
			 * - setUseCaches : 캐시에 저장된 값이 아닌 동적으로 값을 얻어옴
			 * - setRequestProperty : Content-type 프로퍼티를 두번째 파라미터로 설정
			 * - getOutputStream : 해당 URL에 대한 출력 스트림 획득 및 파라미터는 인코딩한 문자열
			 * ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
			 * **/
			
			connection = (HttpURLConnection) apiUrl.openConnection();
			connection.setRequestMethod("GET");
			connection.setDoOutput(true);
			connection.setUseCaches(false);
			connection.setRequestProperty("Content-type", "application/x-www-form-urlencoded");
			connection.getOutputStream().write(dataBytes);
		} 
		catch (UnsupportedEncodingException e) {
			// -- 인코딩 관련 catch 문
			e.printStackTrace();
		} 
		catch (MalformedURLException e) {
			// -- 잘못된 URL이 발생한 catch 문
			// -- https or http 다 붙여야 한다.
			e.printStackTrace();
		} 
		catch (IOException e) {
			// -- 입출력 catch 문
			e.printStackTrace();
		}
		
		return connection;
	}
}
