//package com.daumsoft.news_data;
//
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import com.fasterxml.jackson.core.JsonFactory;
//import com.fasterxml.jackson.core.JsonParseException;
//import com.fasterxml.jackson.core.JsonParser;
//import com.fasterxml.jackson.core.JsonToken;
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.JsonMappingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//public class Main {
//	public static void main(String[] args) throws IOException{
//		JsonParseMapper();
//	}
//
//	@SuppressWarnings("resource")
//	public static void JsonParseMapper() throws IOException {
//		String text = "";
//		try {
//			File file = new File("src/main/java/com/daumsoft/news_data/test.json");
//			BufferedReader br;
//			br = new BufferedReader(new FileReader(file));
//
//			String buffer = "";
//			while ((buffer = br.readLine()) != null) {
//				text += buffer;
//			}
//			
//		}
//		catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		 ObjectMapper mapper = new ObjectMapper();
//		 Map<String, Object> map = new HashMap<String, Object>();
//		
//		 // convert JSON string to Map
//		 try {
//		 map = mapper.readValue(text, new TypeReference<Map<String, Object>>(){});
//		 } catch (JsonParseException e) {
//		 // TODO Auto-generated catch block
//		 e.printStackTrace();
//		 } catch (JsonMappingException e) {
//		 // TODO Auto-generated catch block
//		 e.printStackTrace();
//		 } catch (IOException e) {
//		 // TODO Auto-generated catch block
//		 e.printStackTrace();
//		 }
//		
//		 System.out.println(map.toString());
//		 System.out.println(map.get("documentList"));
//		 List<Object>list = (List<Object>) map.get("documentList");
//		 
//		 System.out.println(list.size());
//	}
//
//	public static void JsonParseMethod() {
//		try {
//			JsonParser jsonParser = new JsonFactory()
//					.createParser(new File("src/main/java/com/daumsoft/news_data/test.json"));
//
//			while (!jsonParser.isClosed()) {
//				if (jsonParser.nextValue() != null && jsonParser.nextValue().equals(JsonToken.START_ARRAY)) {
//					int check = 0;
//
//					while (!jsonParser.isClosed()) {
//						JsonToken nextValue = jsonParser.nextValue();
//
//						if (nextValue.equals(JsonToken.END_OBJECT))
//							check++;
//
//						if (check == 2)
//							break;
//
//						if (!checkParser(jsonParser.getCurrentName())) {
//							System.out.println(jsonParser.getCurrentName());
//							System.out.println(jsonParser.getValueAsString());
//							System.out.println("---------------------------");
//						}
//					}
//				}
//			}
//		} catch (JsonParseException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//
//	// -- Json 파일 내에서 추출 네임 값 체크
//	public static boolean checkParser(String currentName) {
//		if (currentName == null)
//			return true;
//
//		if (currentName.equals("date"))
//			return false;
//		if (currentName.equals("sequence"))
//			return false;
//		if (currentName.equals("title"))
//			return false;
//		if (currentName.equals("content"))
//			return false;
//		if (currentName.equals("url"))
//			return false;
//		if (currentName.equals("writeName"))
//			return false;
//		if (currentName.equals("writeRealName"))
//			return false;
//
//		return true;
//	}
//}
