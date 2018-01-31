<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:url var="contentUrl" value="./news_content" />

<!DOCTYPE HTML>
<HTML>
    <HEAD>
        <META CHARSET="UTF-8">
        <link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/news_list.css" />" >
        
        <script>
        	function submit(){
        		document.getElementById("documentform").submit();
        	}
        </script>
    </HEAD>

    <BODY>
        <div class="Wrapper">
            <div class="listWrapper">
                <ul>
                	<c:forEach items="${documents}" var="keyworDocMap" varStatus="status">
	               		<li>
	                        <div class="contentWrapper">
	                            <div class="imageWrapper">
	                                <img src="${keyworDocMap['imageUrl']}" />
	                            </div>
	                            <div class="textWrapper">	
	                            	<!-- 30줄 넘어가면 글자 자동으로 생략 -->
	                                <div>
	                                	<form:form id="docform_${status.index}" modelAttribute="newsDocumentList" action="${contentUrl}" method="POST" acceptCharset="UTF-8">
	                                		
	                                		<!-- 도메인 객체에 바인딩 시키기 위함. -->
	                                    	<form:hidden path="title" value="${keyworDocMap['title']}" />
	                                    	<form:hidden path="content" value="${keyworDocMap['content']}" />
	                                    	<form:hidden path="parseDate" value="${keyworDocMap['parseDate']}" />
	                                    	<form:hidden path="sequence" value="${keyworDocMap['sequence']}" />
	                                    	<form:hidden path="url" value="${keyworDocMap['url']}" />
	                                    	<form:hidden path="imageUrl" value="${keyworDocMap['imageUrl']}" />
	                                    	<form:hidden path="writerRealName" value="${keyworDocMap['writerRealName']}" />
	                                    	<form:hidden path="documentDate" value="${keyworDocMap['documentDate']}" /> 
<%-- 											<form:hidden path="index" value="${status.index}" /> --%>
											
											<!-- a 태그에서 해당 form 태그 url 서브밋 여기-->
	                                    	<p><a class="newsTitleLink" onclick="document.getElementById('docform_${status.index}').submit();">${keyworDocMap['title']}</a></p>
	                                    	<span>${keyworDocMap['writerRealName']}</span>
	                                    </form:form>
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