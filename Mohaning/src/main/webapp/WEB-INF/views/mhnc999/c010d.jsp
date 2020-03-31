<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!doctype html>
<html>
<head>
	<title>기사</title>
	<jsp:directive.include file="/WEB-INF/views/common/taglib.jsp" />
	
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
	
	<section>
	<div class="container padt-30">
    	<div class="contents_wrap">
      		<div class="contents_head">
        		<div class="contents_type">
          			<h5>언론사 정보</h5>
        		</div>
      		</div>
      		<div class="info_wrap">
		        <div class="left_wrap">
		        	<div class="title">
		            	<h3>${media.media_nm }</h3>
		          	</div>
		          	<div class="media_info">
		            	<div class="row">
		              		<div class="info">
		                		<label>설립일</label>
		                		<p>2020.01.01</p>
		              		</div>
		            	</div>
		            	<div class="row">
		              		<div class="info">
		                		<label>홈페이지</label>
		                		<a href="${media.media_url }" target="_blank">${media.media_url }</a>
		              		</div>
		            	</div>
		            	<div class="row">
		              		<div class="info">
		                		<label>위치</label>
		                		<a href="#">서울특별시 종로구 종로동 종로로 102-01</a>
		              		</div>
			            </div>
			            <div class="row">
		              		<div class="info width50">
		                		<label>등록된 기자</label>
		                		<a href="#">${fn:length(mediaNewsList) } 명</a>
		              		</div>
		              		<div class="info width50">
				                <label>등록된 기사</label>
				                <a href="#">${fn:length(mediaNewsList) } 건</a>
		              		</div>
		            	</div>
		            	<div class="row">
		              		<div class="info">
		                		<label>정보</label>
		                		<a href="https://ko.wikipedia.org/wiki/%EC%A4%91%EC%95%99%EC%9D%BC%EB%B3%B4" target="_blank">위키백과</a>
		              		</div>
		            	</div>
		          	</div>
		        </div>
		        <div class="spacer-vertical"></div>
		        <div class="right_wrap">
		          	<div class="chart_wrap">
		            	<div class="chart_area">
		              		<span>언론사</span>
		              		<div class="chart">
		              			<!-- Chart.js -->
		              			<canvas id="myChart1" width="300" height="300"></canvas>
		              			<script type="text/javascript">
								var scoreList = JSON.parse('${mediaScore}');
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
								        	label: '언론사 특성',
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
			</div>
		</div>
	</div>
	<div class="container">
	    <div class="info_wrap">
	      	<div class="left_wrap">
	        	<div class="contents_type">
	          		<h5>최근 등록된 기사</h5>
	          		<a href="#">전체보기 ></a>
	        	</div>
	        	<ul class="news-list">
	        		<c:choose>
		        		<c:when test="${fn:length(mediaNewsList) > 0 }">
		        		<c:forEach items="${mediaNewsList }" var="result">
		        			<li><label>298</label><a href="/n010d${result.news_id }.do">${result.news_title }</a></li>
		        		</c:forEach>
		        		</c:when>
		        		<c:otherwise>
		        			<li>등록된 기사가 없습니다.</li>
		        		</c:otherwise>
		        	</c:choose>
	        	</ul>
	      	</div>
	      	<div class="spacer-vertical"></div>
	      	<div class="right_wrap">
	        	<div class="contents_type">
	          		<h5>최근 등록된 기자</h5>
	          		<a href="#">전체보기 ></a>
	        	</div>
	        	<ul class="author-list">
	        		<c:choose>
		        		<c:when test="${fn:length(mediaAuthorList) > 0 }">
		        		<c:forEach items="${mediaAuthorList }" var="result">
		        			<li><label>${result.author_nm }</label><a href="/a010d${result.author_id }.do">${result.author_email }</a></li>
		        		</c:forEach>
		        		</c:when>
		        		<c:otherwise>
		        			<li>등록된 기자가 없습니다.</li>
		        		</c:otherwise>
		        	</c:choose>
	        	</ul>
	      	</div>
	  	</div>
  	</div>
  	<div class="container">
  		<div class="buttons_wrap">
	    	<div class="left_group">
	        	<button type="button" class="btn1" onclick="javascript: onPageMove('Board');">목록</button>
	        </div>
	        <div class="right_group">
	
	        </div>
		</div>
	</div>
	</section>
	
	<footer>
		<jsp:include page="/WEB-INF/views/common/footer.jsp" />	    
  	</footer>
	
	<h1>언론사 상세</h1>
	
	<div class="uk-section">
    <div class="uk-container" uk-grid>
      <div class="uk-width-2-3@s" uk-grid>
        <div class="uk-width-1-1">
          <h1 class="m-border-b">
            ${media.media_nm }
          </h1>
        </div>
        <div class="uk-width-1-1">
          <h4 class="m-border-b"><a href="${media.media_url }" target="_blank">${media.media_url }</a></h4>
        </div>
        <div class="uk-width-1-2@s">
          <h4 class="m-border-b"><b>모하닝에 등록된 기사수</b>&nbsp; &nbsp;${fn:length(mediaNewsList) }</h4>
        </div>
        <div class="uk-width-1-2@s">
          <h4 class="m-border-b"><b>모하닝에 등록된 기자수</b>&nbsp; &nbsp;${fn:length(mediaNewsList) }</h4>
        </div>
        <div class="uk-width-1-2@s">
          <h4 class="m-border-b"><b>평가된 기사수</b>&nbsp; &nbsp;${fn:length(mediaAuthorList) }</h4>
        </div>
        <div class="uk-width-1-2@s">
          <h4 class="m-border-b"><b>평가된 기자수</b>&nbsp; &nbsp;${fn:length(mediaAuthorList) }</h4>
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
			var scoreList = JSON.parse('${mediaScore}');
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
			        	label: '언론사 특성',
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
    	<div class="uk-width-2-3@s">
	      <div class="m-border-t ">
	        <ul class="uk-list uk-link-text uk-margin-small-top">
	        	<c:choose>
	        		<c:when test="${fn:length(mediaNewsList) > 0 }">
	        		<c:forEach items="${mediaNewsList }" var="result">
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
	    <div class="uk-width-1-3@s">
	      <div class="m-border-t ">
	        <ul class="uk-list uk-link-text uk-margin-small-top">
	        	<c:choose>
	        		<c:when test="${fn:length(mediaAuthorList) > 0 }">
	        		<c:forEach items="${mediaAuthorList }" var="result">
	        			<li><a href="/a010d${result.author_id }.do">${result.author_nm }</a></li>
	        		</c:forEach>
	        		</c:when>
	        		<c:otherwise>
	        			<li>등록된 기자가 없습니다.</li>
	        		</c:otherwise>
	        	</c:choose>
	        </ul>
	      </div>
          <div>
          	<button class="uk-button uk-button-primary uk-align-center">전체 기자 보기</button>
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
