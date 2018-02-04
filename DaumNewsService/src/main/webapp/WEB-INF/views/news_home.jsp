<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<HTML>
<HEAD>
<META CHARSET="UTF-8">

<link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/news_home.css" />">
<link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/news_home_side.css" />" >
<script type="text/javascript" src="<c:url value="/resources/js/jquery-3.3.1.min.js" />"></script>
<SCRIPT>
	$(document).ready(function() {
		
		// -- 애니메이션 효과 밀기 당기기 -- 
		$('div.p_sideButton').click(function() {
			var highLevel = $(this).attr('id');
			
			if(highLevel == 'sidePolitics'){
				$('div#sideOne').text('정부');
				$('div#sideTwo').text('단체');
				$('div#sideThree').text('법 / 제도')
			}
			
			if(highLevel == 'sideSociaty'){
				$('div#sideOne').text('경제');
				$('div#sideTwo').text('세금');
				$('div#sideThree').text('사건 / 사고')
			}
			
			if(highLevel == 'sideGlobal'){
				$('div#sideOne').text('대륙');
				$('div#sideTwo').text('기후 / 기상');
				$('div#sideThree').text('천체')
			}
			
			if(highLevel == 'sideCulture'){
				$('div#sideOne').text('축제 / 행사');
				$('div#sideTwo').text('연극');
				$('div#sideThree').text('티비')
			}
			
			if(highLevel == 'sideIT'){
				$('div#sideOne').text('SW - 품목');
				$('div#sideTwo').text('SW - 제품');
				$('div#sideThree').text('SW - 브랜드');
			}
			
			$('ul#sideParentMenuId').animate({
				opacity : 0
			}, 350);

			$('ul#sideChildMenuId').animate({
				left : -2,
				opacity : 1
			}, 350);
		});
		$('div#sideBack').click(function() {
			$('ul#sideChildMenuId').animate({
				left : 198,
				opacity : 0
			}, 350);

			$('ul#sideParentMenuId').animate({
				opacity : 1
			}, 350)
		});
		
		// ajax 로 화면 나타내기.
		$('div.c_sideButton').click(function() {
			var lowLevel = $(this).text();
			$.ajax({
				type : 'post',
				url : './viewForSentiment',
				data : {
					"menu" : lowLevel
				},

				success : function(data) {
					$("div.menuBox-imageWrapper").html(data)
				}
			});
		});
	});
</SCRIPT>
</HEAD>

<BODY>
	<c:import url="news_menu.jsp" charEncoding="UTF-8"></c:import>
	<c:import url="news_list.jsp" charEncoding="UTF-8"></c:import>

	<!-- 날씨 보는 공간 -->
<!-- 	<div class="weatherWrapper"> -->

<!-- 	</div> -->

	<div class="sentimentWrapper">
		<div class="homeTitle">
			<em>색깔로 보는 오늘의 이슈</em>
		</div>

		<div class="menuBoxWrapper">
			<div class="menuBox-menuWrapper">
				<ul id="sideParentMenuId" class="sideParentMenu">
                     <li><div class="p_sideButton" id="sidePolitics">정치 · 시사</div></li>
                     <li><div class="p_sideButton" id="sideSociaty">사회 · 경제</div></li>
                     <li><div class="p_sideButton" id="sideGlobal">세계</div></li>
                     <li><div class="p_sideButton" id="sideCulture">문화 · 생활</div></li>
                     <li><div class="p_sideButton" id="sideIT">IT</div></li>
                 </ul>

                 <ul id="sideChildMenuId" class="sideChildMenu">
                     <li><div class="c_sideButton" id="sideOne">분류 1</div></li>
                     <li><div class="c_sideButton" id="sideTwo">분류 2</div></li>
                     <li><div class="c_sideButton" id="sideThree">분류 3</div></li>
                     <li><div id="sideBack">뒤로가기</div></li>
                 </ul>
			</div>

			<div class="menuBox-imageWrapper">
			</div>
		</div>
	</div>
</BODY>
</HTML>