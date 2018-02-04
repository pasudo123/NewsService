package com.daumsoft.news;

public class SideController {
	
	/**ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
	 * 
	 * (1) GetTopKeywords 로 탑 키워드 10개 추출
	 * (2) 추출된 10개의 키워드로 GetTopAssocSentimentByPeriod 실시 10개 추출 => 100개 감성블럭 생성 가능
	 * (3) 해당 감성블럭과 탑 키워드 Expression 적용, 트위터 게시글 하나 팝업으로 볼 수 있도록 클릭이벤트 처리
	 * 
	 *ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ*/
	
	// 누른 메뉴, 분야, 코드
	private String[] menuAndCateogryAndCode = new String[3];
	private String code = null;
	
	// -- 메뉴 별로 10개의 탑 키워드를 추출한다.
	public String[] getClassification(String menu){
		menuAndCateogryAndCode[0] = new String(menu);
		
		if(menu.equals("정부") || menu.equals("단체") || menu.equals("법 / 제도")){
			if(menu.equals("정부"))
				code = "2/1/9";
			
			if(menu.equals("단체"))
				code = "2/2/10";
			
			if(menu.equals("법 / 제도"))
				code = "6/2/147";
				
			return sidePolitics(menu);
		}
		
		if (menu.equals("경제") || menu.equals("세금") || menu.equals("사건 / 사고")) {
			if(menu.equals("경제"))
				code = "6/3/152";
			
			if(menu.equals("세금"))
				code = "6/3/150";
			
			if(menu.equals("사건 / 사고"))
				code = "6/7/158";
			
			return sideSociaty(menu);
		}
		
		if (menu.equals("대륙") || menu.equals("기후 / 기상") || menu.equals("천체")) {
			if(menu.equals("대륙"))
				code = "3/15/41";
			
			if(menu.equals("기후 / 기상"))
				code = "4/6/67";
				
			if(menu.equals("천체"))
				code = "4/5/66";
				
			return sideGlobal(menu);
		}

		if (menu.equals("축제 / 행사") || menu.equals("연극") || menu.equals("티비")) {
			if(menu.equals("축제 / 행사"))
				code = "8/6/193";
				
			if(menu.equals("연극"))
				code = "7/1/161";
				
			if(menu.equals("티비"))
				code = "7/2/162";
				
			return sideCulture(menu);
		}

		if (menu.equals("SW - 품목") || menu.equals("SW - 제품") || menu.equals("SW - 브랜드")) {
			if(menu.equals("SW - 품목"))
				code = "5/2/0";
			
			if(menu.equals("SW - 제품"))
				code = "5/19/0";
			
			if(menu.equals("SW - 브랜드"))
				code = "5/36/0";
			
			return sideIT(menu);
		}
		
		System.out.println("-- null");
		return null;
	}
	
	public String[] sidePolitics(String menu){
		menuAndCateogryAndCode[1] = new String("politics");
		menuAndCateogryAndCode[2] = new String(code);
		
		return menuAndCateogryAndCode;
	}
	
	public String[] sideSociaty(String menu){
		menuAndCateogryAndCode[1] = new String("sociaty");
		menuAndCateogryAndCode[2] = new String(code);
		
		return menuAndCateogryAndCode;
	}
	
	public String[] sideGlobal(String menu){
		menuAndCateogryAndCode[1] = new String("global");
		menuAndCateogryAndCode[2] = new String(code);
		
		return menuAndCateogryAndCode;
	}
	
	public String[] sideCulture(String menu){
		menuAndCateogryAndCode[1] = new String("culture");
		menuAndCateogryAndCode[2] = new String(code);
		
		return menuAndCateogryAndCode;
	}
	
	public String[] sideIT(String menu){
		menuAndCateogryAndCode[1] = new String("IT");
		menuAndCateogryAndCode[2] = new String(code);
		
		return menuAndCateogryAndCode;
	}
}
