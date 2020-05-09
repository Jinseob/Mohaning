<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en" dir="ltr">

<head>
  <meta charset="utf-8">
  <title>모하닝</title>
  <jsp:directive.include file="/WEB-INF/views/common/taglib.jsp" />
<!--   <link rel="stylesheet" href="reset.css"> -->
<!--   <link rel="stylesheet" href="style.css"> -->
	<!-- Chart.js -->
	<script src="/resources/js/Chart.bundle.js"></script>
	<!-- Chart.js End -->
	<script type="text/javascript">
	$(function(){
		// 초기값.
		scoreAjax($(".media")[0]);
		
		$(".media").click(function(){
			if($(this).hasClass("active")){
				location.href = "/Media/c010d" + this.dataset.index + ".do";
			}else{
				$(".media").removeClass("active");
				$(this).addClass("active");
				scoreAjax(this);
			}
		});
	})
	function scoreAjax(that){
		$("#media_id").val(that.dataset.index);
		var frm = $("#frm").serialize();
		var url = "/getScoreM.json";
		$.ajax({
 			type: "POST",
 			url : url,
 			dataType: "json",
 			data : frm,
 			success: function(results){
 				var prop = new Object();
 				var id = "myChart2";
 				var items = results.mediaScore;
 				drawChart(items, prop, id);
 				drawChart(items, prop, "myChart3");
 				drawChart(items, prop, "myChart4");
 				drawChart(items, prop, "myChart5");
 			},
 			error: function(data){
 				alert("E" + data);
 			},
 		})
	}
	</script>
</head>

<body>
	<header id="header">
		<jsp:include page="/WEB-INF/views/common/header.jsp" />
  	</header>
  	<section>
  	<!-- Search -->
	<div class="container">
    	<div class="row">
    		<form id="frm" name="frm">
        		<input type="hidden" name="author_id" id="author_id" />
        		<input type="hidden" name="media_id" id="media_id" />
      		<div class="search-wrapper">
        		<jsp:include page="/WEB-INF/views/common/search.jsp" />
      		</div>
      		</form>
    	</div>
  	</div>
	
	<!-- Main Top -->
	<div class="container">
		<div class="tab-wrapper">
	    	<div class="tab">
		        <button class="tablinks active" onclick="openCity(event, 'London')">London</button>
		        <button class="tablinks" onclick="openCity(event, 'Seoul')">Paris</button>
		        <button class="tablinks" onclick="openCity(event, 'Seoul')">Seoul</button>
		        <button class="tablinks" onclick="openCity(event, 'Seoul')">기사이름</button>
		        <button class="tablinks" onclick="openCity(event, 'Seoul')">기사기사제목</button>
		        <button class="tablinks" onclick="openCity(event, 'Seoul')">가나다라마바auto</button>
		    </div>
	      	<div id="tabcontent" class="tabcontent">
	        	<div class="tabcontent-wrap">
		          	<div class="tab-list-wrap">
			            <div class="top-chart-wrap">
			            	<canvas id="myChart1" width="366" height="180"></canvas>
			            	<script type="text/javascript">
			            	var ctx = document.getElementById('myChart1').getContext('2d');

			        		var config = {
			        			type: 'line',
			        			data: {
			        				labels: [ // Date Objects
			        					"2020.01.01",
			        					"2020.01.02",
			        					"2020.01.03",
			        					"2020.01.04",
			        					"2020.01.05",
			        					"2020.01.06",
			        					"2020.01.07"
			        				],
			        				datasets: [{
			        					label: 'My First dataset',
			        					backgroundColor: "rgba(200,0,0,0.1)",
			        					borderColor: "rgba(200,0,0,0.1)",
			        					fill: false,
			        					data: [
			        						100,
			        						120,
			        						130,
			        						140,
			        						110,
			        						120,
			        						140
			        					],
			        				}]
			        			},
			        			options: {
			        				title: {
			        					text: 'Chart.js Time Scale'
			        				},
			        				scales: {
			        					xAxes: [{
			        						scaleLabel: {
			        							display: true,
			        						}
			        					}],
			        					yAxes: [{
			        						scaleLabel: {
			        							display: true,
// 			        							labelString: 'value'
			        						}
			        					}]
			        				},
			        			}
			        		};
			        		var myChart = new Chart(ctx, config);
			            	</script>
			            </div>
			            <div class="top-wrap">
			            	<div class="top-header">
			            		<p class="top-title">신천지히히히히신천지히히히히신천지히히히히신천지히히히히신천신천신천신천신천신천신천</p>
			            	</div>
			            	<div class="top-section">
			            		<p class="top-contents">신천지 에브리웨어 싯파샛키들아!!!!!신천지 에브리웨어 싯파샛키들아!!!!!신천지 에브리웨어 싯파샛키들아!!!!!신천지 에브리웨어 싯파샛키들아!!!!!ㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁ</p>
			            	</div>
			            	<div class="top-bottom">
			            		<p class="top-media">국민일보</p>
			            		<p class="top-name">홍길동</p>
			            		<p class="top-date">2020.05.03</p>
			            		<p class="top-view">조회수 100만</p>
			            	</div>
			            </div>
	          		</div>
	          		<div class="spacer-vertical" ></div>
	          		<div class="tab-list-wrap">
	            		<ul class="tab-list">
	              			<li class="news-list">
	              				<div class="top-header">
	                				<p class="top-title">신천지 에브리웨어 싯파샛키들아!!!!!</p>
                				</div>
	                			<div class="top-bottom">
				            		<p class="top-media">국민일보</p>
				            		<p class="top-name">홍길동</p>
				            		<p class="top-date">2020.05.03</p>
				            		<p class="top-view">조회수 100만</p>
				            	</div>
	              			</li>
	              			<li class="news-list">
	                			<div class="top-header">
	                				<p class="top-title">대구경북지역 신천지 때문에 초전박살났다.대구경북지역 신천지 때문에 초전박살났다.대구경북지역 신천지 때문에 초전박살났다.</p>
                				</div>
	                			<div class="top-bottom">
				            		<p class="top-media">국민일보</p>
				            		<p class="top-name">홍길동</p>
				            		<p class="top-date">2020.05.03</p>
				            		<p class="top-view">조회수 100만</p>
				            	</div>
	              			</li>
	              			<li class="news-list">
	              				<div class="top-header">
	                				<p class="top-title">우리회사도 재택근무로 전면 바꿔줘라!</p>
                				</div>
	                			<div class="top-bottom">
				            		<p class="top-media">국민일보</p>
				            		<p class="top-name">홍길동</p>
				            		<p class="top-date">2020.05.03</p>
				            		<p class="top-view">조회수 100만</p>
				            	</div>
	              			</li>
	              			<li class="news-list">
	              				<div class="top-header">
	                				<p class="top-title">코로나 종식돼야 운동도하고 쇼핑도하지 놀러가고 싶따고요</p>
                				</div>
	                			<div class="top-bottom">
				            		<p class="top-media">국민일보</p>
				            		<p class="top-name">홍길동</p>
				            		<p class="top-date">2020.05.03</p>
				            		<p class="top-view">조회수 100만</p>
				            	</div>
			              	</li>
			              	<li class="news-list">
			              		<div class="top-header">
				                	<p class="top-title">돈많이많이 벌고시포요 막 이것저것 지르고 시포요!</p>
			                	</div>
	                			<div class="top-bottom">
				            		<p class="top-media">국민일보</p>
				            		<p class="top-name">홍길동</p>
				            		<p class="top-date">2020.05.03</p>
				            		<p class="top-view">조회수 100만</p>
				            	</div>
	              			</li>
	              			<li><a href="/News/main.do" class="more right">기사 더보기 ></a></li>
	            		</ul>
	          		</div>
	        	</div>
	      	</div>
		</div>
	</div>

	<!-- 기자 화면 -->
	<div class="container mt-20">
	    <div class="contents_type">
	    	<h5>기자 분석</h5>
	    </div>
	    <div class="chart_wrap3">
	    	<div class="chart_with_info">
	        	<div class="chart">
	        		<canvas id="myChart3" width="140" height="140"></canvas>
	        	</div>
	        	<div class="spacer-vertical"></div>
	        	<div class="chart_info">
	        		<div class="spacer-horizontal"></div>
	        		<h5>홍길동</h5>
	        		<div class="spacer-horizontal"></div>
	        		<span>MBC</span><br/>
	        		<span>기사 수</span><a>185 건</a><br/>
	        		<div class="spacer-horizontal"></div>
	        		<a>hong@imbc.com</a>
	        	</div>
	      	</div>
<!--       	</div> -->
<!--       	<div class="chart_wrap"> -->
	      	<div class="chart_with_info">
	        	<div class="chart">
	        		<canvas id="myChart4" width="140" height="140"></canvas>
	        	</div>
	        	<div class="spacer-vertical"></div>
	        	<div class="chart_info">
	        		<div class="spacer-horizontal"></div>
	        		<h5>홍길동</h5>
	        		<div class="spacer-horizontal"></div>
	        		<span>MBC</span><br/>
	        		<span>기사 수</span><a>185 건</a><br/>
	        		<div class="spacer-horizontal"></div>
	        		<a>hong@imbc.com</a>
	        	</div>
	      	</div>
<!--       	</div> -->
<!--       	<div class="chart_wrap"> -->
	      	<div class="chart_with_info">
	        	<div class="chart">
	        		<canvas id="myChart5" width="140" height="140"></canvas>
	        	</div>
	        	<div class="spacer-vertical"></div>
	        	<div class="chart_info">
	        		<div class="spacer-horizontal"></div>
	        		<h5>홍길동</h5>
	        		<div class="spacer-horizontal"></div>
	        		<span>MBC</span><br/>
	        		<span>기사 수</span><a>185 건</a><br/>
	        		<div class="spacer-horizontal"></div>
	        		<a>hong@imbc.com</a>
	        	</div>
	      	</div>
	    </div>
	</div>
  
  	<!-- 언론사 화면 -->
  	<div class="container mt-20">
	    <div class="contents_type">
	    	<h5>언론사 분석</h5>
	    </div>
	    <div class="media_wrap">
	    	<div class="chart_wrap">
		    	<div class="chart_with_info">
		        	<div class="chart">
		        		<canvas id="myChart2" width="140" height="140"></canvas>
		        	</div>
		        	<div class="spacer-vertical"></div>
		        	<div class="chart_info">
		        		<div class="spacer-horizontal"></div>
		        		<h5>MBC</h5>
		        		<div class="spacer-horizontal"></div>
		        		<span>기자 수</span><a>85 명</a><br/>
		        		<span>기사 수</span><a>185 건</a><br/>
		        		<div class="spacer-horizontal"></div>
		        		<a>www.imbc.com</a>
		        	</div>
		      	</div>
	    	</div>
	      	<div class="media_area">
	        	<c:choose>
	        		<c:when test="${fn:length(mediaList) > 0 }">
	        			<table class="media_table">
	        			<c:forEach items="${mediaList }" var="result" varStatus="status" end="17">
	        				<c:if test="${status.count % 6 eq 1}">
								<tr>
				   			</c:if>
				   				<c:choose>
				   				<c:when test="${status.count eq 18 }">
				   					<td><a href="/Media/main.do" class="more">더보기</a></td>
				   				</c:when>
				   				<c:otherwise>
<%-- 					   				<td class="media" data-index="${result.media_id }"><a href="/Media/c010d${result.media_id }.do" >${result.media_nm}</a></td> --%>
					   				<td class="media <c:if test="${status.first}">active</c:if>" data-index="${result.media_id }"><p>${result.media_nm}</p></td>
				   				</c:otherwise>
				   				</c:choose>
			   				<c:if test="${status.count % 6 eq 0}">
								</tr>	
							</c:if>
	        			</c:forEach>
	        			</table>
	        		</c:when>
	        	</c:choose>
	      	</div>
		</div>
	</div>
  
  	<!-- 기사 화면 -->
  	<div class="container mt-20">
    	<div class="main-list-wrap">
      		<div class="main-news-list">
        		<div class="contents_type">
          			<h5>최신 기사</h5>
        		</div>
        		<ul class="popular-news-list">
        			<c:choose>
        				<c:when test="${fn:length(newNewsList) > 0 }">
        					<c:forEach var="result" items="${newNewsList }" varStatus="status">
        						<li class="news-list-title"><label>${result.view_cnt }</label><a href="/News/n010d${result.news_id }.do">${result.news_title }</a></li>
        					</c:forEach>
        					<li><a href="/News/main.do" class="more right">기사 더보기 ></a></li>
        				</c:when>
        				<c:otherwise>
        					<li class="news-list-title"><div><span>데이터가 없습니다.</span></div></li>
        				</c:otherwise>
        			</c:choose>
        		</ul>
      		</div>
      		<div class="spacer-vertical mv-20"></div>
      		<div class="main-news-list">
        		<div class="contents_type">
          			<h5>조회수 많은 기사</h5>
        		</div>
        		<ul class="popular-news-list">
        			<c:choose>
        				<c:when test="${fn:length(topNewsList) > 0 }">
        					<c:forEach var="result" items="${topNewsList }" varStatus="status">
        						<li class="news-list-title"><label>${result.view_cnt }</label><a href="/News/n010d${result.news_id }.do">${result.news_title }</a></li>
        					</c:forEach>
        					<li><a href="/News/main.do" class="more right">기사 더보기 ></a></li>
        				</c:when>
        				<c:otherwise>
        					<li class="news-list-title"><div><span>데이터가 없습니다.</span></div></li>
        				</c:otherwise>
        			</c:choose>
        		</ul>
      		</div>
    	</div>
  	</div>
	</section>
	
  	<script type="text/javascript">
    function openCity(evt, cityName) {
      	var i, tabcontent, tablinks;

      	tablinks = document.getElementsByClassName("tablinks");
      	for (i = 0; i < tablinks.length; i++) {
        	tablinks[i].className = tablinks[i].className.replace(" active", "");
      	}
		evt.currentTarget.className += " active";
    }
  	</script>
  	<script src="/resources/js/common.js"></script>
  	<footer>
		<jsp:include page="/WEB-INF/views/common/footer.jsp" />	    
  	</footer>
</body>

</html>

