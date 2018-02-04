<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML>
<HTML>
    <HEAD>
        <META CHARSET="UTF-8">
    	<link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/news_home_sentiment.css" />" >
    	
    	<SCRIPT>
    	$(document).ready(function(){
    		$('td.issueText').each(function(index){
//     			${rankMap13.get('frequency')}&${rankMap13.get('polarity')}

    			var sentiment = $(this).find('img').attr('id');
    			
    			if(sentiment == "&"){
    				$(this).find('img').attr("src","<c:url value='/resources/image/none_200.png' />");
    				$(this).find('input').attr("value","null");
    				return;
    			}
    			
    			var frequency = Number(sentiment.split("&")[0]);
    			var polarity = sentiment.split("&")[1];

    			if(polarity == "negative")
    				polarity = "ne";
    			if(polarity == "neutral")
    				polarity = "neu";
    			if(polarity == "other")
    				polarity = "ot";
    			if(polarity == "positive")
    				polarity = "pos";
    			
   				if(frequency >= 1000)
   					$(this).find('img').attr("src","<c:url value='/resources/image/" + polarity + "_1000.png' />");
   				if(frequency >= 800 && frequency < 1000)
   					$(this).find('img').attr("src","<c:url value='/resources/image/" + polarity + "_800.png' />");
   				if(frequency >= 600 && frequency < 800)
   					$(this).find('img').attr("src","<c:url value='/resources/image/" + polarity + "_600.png' />");
   				if(frequency >= 400 && frequency < 600)
   					$(this).find('img').attr("src","<c:url value='/resources/image/" + polarity + "_400.png' />");
   				if(frequency >= 0 && frequency < 400)
   					$(this).find('img').attr("src","<c:url value='/resources/image/" + polarity + "_200.png' />");
    		});
    		
    		$('td.issueText').hover(function(){
   				var label = $(this).find('input').val();

   				if(label == "null"){
   					$('div.textContainer').find('div').text("N O N E");
   					return;
   				}
   				
   				$('div.textContainer').find('div').text(label);
    		});
    	});
    	</SCRIPT>
    </HEAD>

    <BODY>
		<div class="tableWrapper">
			<!-- 가로 15 / 세로 7 = 15 * 7 = 105블럭 -->
			<table>
				<c:forEach varStatus="status" begin="1" end="6" step="1">
				<tr>
					<c:forEach varStatus="innerStatus" begin="${(status.index-1)*15 + 1}" end="${status.index*15}" step="1">
					<td class="issueText">
						<img id="${sentiment[innerStatus.index].getFrequency()}&${sentiment[innerStatus.index].getPolarity()}" class="blockImage" src="" />
						<input type="hidden" value="${sentiment[innerStatus.index].getLabel()}" />
						<div class="container">
<!-- 							<div class="text"></div> -->
						</div>	
					</td>
					</c:forEach>
				</tr>
				</c:forEach>
				
				<tr>
					<c:forEach varStatus="innerStatus" begin="91" end="95" step="1">
					<td class="issueText">
						<img id="${sentiment[innerStatus.index].getFrequency()}&${sentiment[innerStatus.index].getPolarity()}" class="blockImage" src="" />
						<input type="hidden" value="" />
						<div class="container">
<!-- 							<div class="text"></div> -->
						</div>	
					</td>
					</c:forEach>
					
					<td colspan="5">
                        <div class="textContainer">
                            <div class="text"></div>
                        </div>
                    </td>
                    
                    <c:forEach varStatus="innerStatus" begin="96" end="100" step="1">
					<td class="issueText">
						<img id="${sentiment[innerStatus.index].getFrequency()}&${sentiment[innerStatus.index].getPolarity()}" class="blockImage" src="" />
						<input type="hidden" value="" />
						<div class="container">
<!-- 							<div class="text"></div> -->
						</div>	
					</td>
					</c:forEach>
				</tr>
			</table>
		</div>
	</BODY>
</HTML>