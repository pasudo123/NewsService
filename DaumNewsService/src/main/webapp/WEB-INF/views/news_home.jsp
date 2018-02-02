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
$(document).ready(function(){
	
	// ajax 로 화면 나타내기.
	$('div.sideButton').click(function(){
		// id값 속성으로 받기.
		var id = $(this).attr('id');
	
		$.ajax({
			type:'post',
			url:'./viewForSentiment',
			data:{"menu":id},
			
			success:function(data){
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
				<ul>
					<li><div class="sideButton" id="sidePolitics">정치 · 시사</div></li>
					<li><div class="sideButton" id="sideSociaty">사회 · 경제</div></li>
					<li><div class="sideButton" id="sideGlobal">세계</div></li>
					<li><div class="sideButton" id="sideCulture">문화 · 생활</div></li>
					<li><div class="sideButton" id="sideIT">IT</div></li>
				</ul>
			</div>

			<div class="menuBox-imageWrapper">
<%-- 				<c:if test="${sideMenu == 'politics'}" > --%>
<%-- 					<c:import url="news_table_politics.jsp" charEncoding="UTF-8"></c:import> --%>
<%-- 				</c:if> --%>
				
<%-- 				<c:if test="${sideMenu == 'sociaty'}" > --%>
<%-- 					<c:import url="news_table_sociaty.jsp" charEncoding="UTF-8"></c:import> --%>
<%-- 				</c:if> --%>
				
<%-- 				<c:if test="${sideMenu == 'global'}" > --%>
<%-- 					<c:import url="news_table_global.jsp" charEncoding="UTF-8"></c:import> --%>
<%-- 				</c:if> --%>
				
<%-- 				<c:if test="${sideMenu == 'culture'}" > --%>
<%-- 					<c:import url="news_table_culture.jsp" charEncoding="UTF-8"></c:import> --%>
<%-- 				</c:if> --%>
				
<%-- 				<c:if test="${sideMenu == 'IT'}" > --%>
<%-- 					<c:import url="news_table_IT.jsp" charEncoding="UTF-8"></c:import> --%>
<%-- 				</c:if> --%>
			</div>
		</div>
	</div>
</BODY>
</HTML>