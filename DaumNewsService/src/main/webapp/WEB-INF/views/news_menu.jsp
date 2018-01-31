<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML>
<HTML>
    <HEAD>
        <META CHARSET="UTF-8">
        <link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/news_menu.css" />" >
    </HEAD>

    <BODY>
        <div class="Wrapper">
            <div class="titleWrapper">
                <div>
                    <span>더블</span><span>뉴스</span>
                </div>
            </div>

            <hr>
            <div class="menuWrapper">
                <div class="menuBar">
                    <ul>
                        <li><a class="menuLink" id="homeLink" href="./home">홈</a></li>
                        <li class="gapBar"></li>
                        <li><a class="menuLink" id="politicsLink" href="./politics">정치 · 시사</a></li>
                        <li class="gapBar"></li>
                        <li><a class="menuLink" id="sociatyLink" href="./sociaty">사회 · 경제</a></li>
                        <li class="gapBar"></li>
                        <li><a class="menuLink" id="globalLink" href="./global">세계</a></li>
                        <li class="gapBar"></li>
                        <li><a class="menuLink" id="cultureLink" href="./culture">문화 · 생활</a></li>
                        <li class="gapBar"></li>
                        <li><a class="menuLink" id="ITLink" href="./IT">IT</a></li>
                    </ul>
                </div>
            </div>
            <hr>
        </div>
    </BODY>
</HTML>