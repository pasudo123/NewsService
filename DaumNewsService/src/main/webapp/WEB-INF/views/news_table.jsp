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
    		$('td').each(function(index){
    			var sentiment = $(this).find('img').attr('id');
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
   				if(frequency > 400 && frequency < 600)
   					$(this).find('img').attr("src","<c:url value='/resources/image/" + polarity + "_400.png' />");
   				else
					$(this).find('img').attr("src","<c:url value='/resources/image/" + polarity + "_200.png' />");
    		});
    	});
    	</SCRIPT>
    </HEAD>

    <BODY>
		<div class="tableWrapper politicsContainer">
			<table>
				<tr>
					<td id="1" class="issueText">
						<img id="${rankMap13.get('frequency')}&${rankMap13.get('polarity')}" class="blockImage" src=""  />
						<div class="container">
							<div class="text">${rankMap13.get('label')}</div>
						</div>
					</td>
		
					<td id="2" class="issueText">
						<img id="${rankMap7.get('frequency')}&${rankMap7.get('polarity')}" class="blockImage" src="" />
						<div class="container">
							<div class="text">${rankMap7.get('label')}</div>
						</div>
					</td>
		
					<td id="3" class="issueText">
						<img id="${rankMap5.get('frequency')}&${rankMap5.get('polarity')}" class="blockImage" src="" />
						<div class="container">
							<div class="text">${rankMap5.get('label')}</div>
						</div>
					</td>
			
					<td id="4" class="issueText">
						<img id="${rankMap9.get('frequency')}&${rankMap9.get('polarity')}" class="blockImage" src="" />
						<div class="container">
							<div class="text">${rankMap9.get('label')}</div>
						</div>
					</td>
			
					<td id="5" class="issueText">
						<img id="${rankMap15.get('frequency')}&${rankMap15.get('polarity')}" class="blockImage" src="" />
						<div class="container">
							<div class="text">${rankMap15.get('label')}</div>
						</div>
					</td>
				</tr>
		
				<tr>
					<td id="6" class="issueText">
						<img id="${rankMap11.get('frequency')}&${rankMap11.get('polarity')}" class="blockImage" src="" />
						<div class="container">
							<div class="text">${rankMap11.get('label')}</div>
						</div>
					</td>
		
					<td id="7" class="issueText">
						<img id="${rankMap2.get('frequency')}&${rankMap2.get('polarity')}" class="blockImage" src="" />
						<div class="container">
							<div class="text">${rankMap2.get('label')}</div>
						</div>
					</td>
		
					<td id="8" class="issueText">
						<img id="${rankMap1.get('frequency')}&${rankMap1.get('polarity')}" class="blockImage" src="" /> 
						 <div class="container">
							<div class="text">${rankMap1.get('label')}</div>
						</div>
					</td>
		
					<td id="9" class="issueText">
						<img id="${rankMap3.get('frequency')}&${rankMap3.get('polarity')}" class="blockImage" src="" />
						<div class="container">
							<div class="text">${rankMap3.get('label')}</div>
						</div>
					</td>
		
					<td id="10" class="issueText">
						<img id="${rankMap10.get('frequency')}&${rankMap10.get('polarity')}" class="blockImage" src="" />
						<div class="container">
							<div class="text">${rankMap10.get('label')}</div>
						</div>
					</td>
				</tr>
		
				<tr>
					<td id="11" class="issueText">
						<img id="${rankMap14.get('frequency')}&${rankMap14.get('polarity')}" class="blockImage" src="" />
						<div class="container">
							<div class="text">${rankMap14.get('label')}</div>
						</div>
					</td>
		
					<td id="12" class="issueText">
						<img id="${rankMap6.get('frequency')}&${rankMap6.get('polarity')}" class="blockImage" src="" />
						<div class="container">
							<div class="text">${rankMap6.get('label')}</div>
						</div>
					</td>
			
					<td id="13" class="issueText">
						<img id="${rankMap4.get('frequency')}&${rankMap4.get('polarity')}" class="blockImage" src="" />
						<div class="container">
							<div class="text">${rankMap4.get('label')}</div>
						</div>
					</td>
		
					<td id="14" class="issueText">
						<img id="${rankMap8.get('frequency')}&${rankMap8.get('polarity')}" class="blockImage" src="" />
						<div class="container">
							<div class="text">${rankMap8.get('label')}</div>
						</div>
					</td>
		
					<td id="15" class="issueText">
						<img id="${rankMap12.get('frequency')}&${rankMap12.get('polarity')}" class="blockImage" src="" />
						<div class="container">
							<div class="text">${rankMap12.get('label')}</div>
						</div>
					</td>
				</tr>
			</table>
		</div>
	</BODY>
</HTML>