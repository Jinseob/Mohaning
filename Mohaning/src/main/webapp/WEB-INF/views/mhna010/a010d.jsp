<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!doctype html>
<html>
<head>
	<title>모하닝</title>
	<jsp:directive.include file="/WEB-INF/views/common/taglib.jsp" />
	
	<!-- Chart.js -->
	<script src="/resources/js/Chart.bundle.js"></script>
	<!-- Chart.js End -->
	
	<script type="text/javascript">
	function onPageMove(url){
		if(url == "main") url = "/Author/main";
		$("#frm").attr({"action" : url + ".do", "method" : "POST"}).submit();
	}
	</script>
</head>
<body>
	<!-- Header -->
	<header>
		<jsp:include page="/WEB-INF/views/common/header.jsp" />
	</header>
	
	<form id="frm" name="frm" method="post" >
		<input type="hidden" name="author_id" id="author_id" value="${result.author_id}"/>
		<input type="hidden" name="status" id="status" value="${result.status}"/>
	</form>
	
	<section>
	<div class="container padt-30">
    	<div class="contents_wrap">
      		<div class="contents_head">
        		<div class="contents_type">
          			<h5>기자 정보</h5>
        		</div>
      		</div>
      		<div class="info_wrap">
		        <div class="left_wrap">
		        	<div class="title">
		            	<h3>${author.author_nm }</h3>
		          	</div>
		          	<div class="media_info">
		          		<div class="row">
		              		<div class="info">
		                		<label>등록일</label>
		                		<p>2020.01.01</p>
		              		</div>
		            	</div>
		            	<div class="row">
		              		<div class="info">
		                		<label>E-mail</label>
		                		<p>${author.author_email }</p>
		              		</div>
		            	</div>
		            	<div class="row">
		              		<div class="info">
		                		<label>소속</label>
		                		<a href="${author.media_url }" target="_blank">${author.media_nm }</a>
		              		</div>
		            	</div>
			            <div class="row">
		              		<div class="info width50">
				                <label>등록된 기사</label>
				                <a href="#">${fn:length(mediaNewsList) } 건</a>
		              		</div>
		              		<div class="info width50">
		                		<label>평가된 기사</label>
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
<!-- 		              		<span>언론사</span> -->
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
	        	<ul class="info-listL">
	        		<c:choose>
		        		<c:when test="${fn:length(authorNewsList) > 0 }">
		        		<c:forEach items="${authorNewsList }" var="result">
		        			<li><label>298</label><a href="/News/n010d${result.news_id }.do">${result.news_title }</a></li>
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
	          		<h5>같은 소속 기자</h5>
	          		<a href="#">전체보기 ></a>
	        	</div>
	        	<ul class="info-listR">
	        		<c:choose>
		        		<c:when test="${fn:length(mediaAuthorList) > 0 }">
		        		<c:forEach items="${mediaAuthorList }" var="result">
		        			<li><a href="/Author/a010d${result.author_id }.do"><label>${result.author_nm }</label>${result.author_email }</a></li>
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
	        	<button type="button" class="btn1" onclick="javascript: onPageMove('main');">목록</button>
	        </div>
	        <div class="right_group">
	
	        </div>
		</div>
	</div>
	</section>
	
	<footer>
		<jsp:include page="/WEB-INF/views/common/footer.jsp" />	    
  	</footer>
	
	
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
	        			<li><a href="/News/n010d${result.news_id }.do">${result.news_title }</a></li>
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
	
	<input type="button" value="목록" onclick="javascript: onPageMove('main');" />
	
	<!-- Google Charts -->
<!-- 	<div id="myPieChart"></div> -->
	<!-- Google Charts End -->
<!-- 	<input type="button" value="성향등록" id="dialogOpenBtn" /> -->
</body>
</html>
