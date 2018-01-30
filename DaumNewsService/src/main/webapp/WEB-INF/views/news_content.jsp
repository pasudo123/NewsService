<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<HTML>
    <HEAD>
        <META CHARSET="UTF-8">
        <link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/news_content.css" />" >
    </HEAD>

    <BODY>
    	<c:import url="news_menu.jsp" charEncoding="UTF-8"></c:import>
    
        <div class="aboutNewsWrapper">
            <div class="newsInfoWrapper">
                <div class="infoNameWrapper">${writerRealName}</div>
                <div class="infoTitleWrapper">${title}</div>
                <div class="infoDateWrapper">2018년 1월 18일 목요일</div>
            </div>

            <div class="newsFileWrapper">

            </div>

            <div class="newsDetailWrapper">
				${content}
            </div>
        </div>
    </BODY>
</HTML>