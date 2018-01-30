<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE HTML>
<HTML>
    <HEAD>
        <META CHARSET="UTF-8">
        <link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/news_list.css" />" >
    </HEAD>

    <BODY>
        <div class="Wrapper">
            <!-- 
               	뉴스의 타이틀 같은 경우는 글자제한 걸어두기.
               	글자가 길어지면 div 태그가 넘어감 CSS 깨지기 때문
             -->
            <div class="listWrapper">
                <ul>
                	<c:forEach items="${documents}" var="keyworDocMap">
	               		<li>
	                        <div class="contentWrapper">
	                            <div class="imageWrapper">
	
	                            </div>
	                            <div class="textWrapper">
	                            
	                            	<!-- 30줄 넘어가면 글자 자동으로 e -->
	                                <div>
	                                    <p><a class="newsTitleLink" href="./news_content">${keyworDocMap['title']}</a></p>
	                                    <span>${keyworDocMap['writerRealName']}</span>
	                                </div>
	                            </div>
	                        </div>
	                    </li>
                	</c:forEach>
               	</ul>
            </div>
        </div>
    </BODY>
</HTML>