<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!doctype html>
<html>
<head>
	<title>기사</title>
	<jsp:directive.include file="/WEB-INF/views/common/taglib.jsp" />
	<link rel="stylesheet" href="/resources/css/jquery-ui.css" />
	<script src="/resources/js/jquery-ui.js"></script>
	
	<!-- Chart.js -->
	<script src="/resources/js/Chart.bundle.js"></script>
	<!-- Chart.js End -->
	
	<script type="text/javascript">
	function onPageMove(type){
		$("#frm").attr({"action" : "/" + type + ".do", "method" : "POST"}).submit();
	}
	</script>
</head>
<body>
	<!-- Header -->
	<header>
		<jsp:include page="/WEB-INF/views/common/header.jsp" />
	</header>
	<h1>기자 상세</h1>
	
	<div class="uk-section">
    <div class="uk-container" uk-grid>
      <div class="uk-width-2-3@s" uk-grid>
        <div class="uk-width-1-1">
          <h1 class="m-border-b">
            ${author.author_nm }
          </h1>
        </div>
        <div class="uk-width-1-2@s">
          <h4 class="m-border-b"><a href="${author.media_url }" target="_blank">${author.media_nm }</a></h4>
        </div>
        <div class="uk-width-1-2@s">
          <h4 class="m-border-b"><b>${author.author_email }</b></h4>
        </div>
        <div class="uk-width-1-2@s">
          <h4 class="m-border-b"><b>모하닝에 등록된 기사수</b>&nbsp; &nbsp;${fn:length(authorNewsList) }</h4>
        </div>
        <div class="uk-width-1-2@s">
          <h4 class="m-border-b"><b>평가된 기사수</b>&nbsp; &nbsp;${fn:length(authorNewsList) }</h4>
        </div>
      </div>
      <div class="uk-width-1-3@s">
      	<div class="uk-grid-divider" uk-grid>
          <div>
			<!-- Chart.js -->
			<div style="width: 300px; height: 300px;">
				<canvas id="myChart1" width="300" height="300"></canvas>
			</div>
			<script type="text/javascript">
			var scoreList = JSON.parse('${authorScore}');
			var ctx = document.getElementById('myChart1').getContext('2d');
			
			var labels = new Array();
			var data = new Array();
			for(var i = 0; i < scoreList.length; i++){
				var item = scoreList[i];
				labels.push(item.type_nm);
				data.push(item.score);
			}
			var myChart = new Chart(ctx, {
			    type: 'radar',
			    data: {
			        labels: labels,
			        datasets: [{
			        	label: '기자 특성',
			            data: data,
			            borderColor : 'rgba(200, 0, 0, 0.1)',
			            backgroundColor: 'rgba(200, 0, 0, 0.2)'
			        }
			        ]
			    },
			    options: {
			    	scale:{
			    		ticks:{
			    			beginAtZero: true,
		// 	    			max :100
			    		}
			    	}
			    }
			});
			</script>
			<!-- Chart.js End -->          
          </div>
        </div>
      </div>
      
    </div>
    <div class="uk-container" uk-grid>
    	<div class="uk-width-1-1">
	      <div class="m-border-t ">
	        <ul class="uk-list uk-link-text uk-margin-small-top">
	        	<c:choose>
	        		<c:when test="${fn:length(authorNewsList) > 0 }">
	        		<c:forEach items="${authorNewsList }" var="result">
	        			<li><a href="/n010d${result.news_id }.do">${result.news_title }</a></li>
	        		</c:forEach>
	        		</c:when>
	        		<c:otherwise>
	        			<li>등록된 기사가 없습니다.</li>
	        		</c:otherwise>
	        	</c:choose>
	        </ul>
	      </div>
          <div>
          	<button class="uk-button uk-button-primary uk-align-center">전체 기사 보기</button>
          </div>
	    </div>
    </div>
  </div>
	
	<input type="button" value="목록" onclick="javascript: onPageMove('Board');" />
	
	<!-- Google Charts -->
<!-- 	<div id="myPieChart"></div> -->
	<!-- Google Charts End -->
<!-- 	<input type="button" value="성향등록" id="dialogOpenBtn" /> -->
</body>
</html>
