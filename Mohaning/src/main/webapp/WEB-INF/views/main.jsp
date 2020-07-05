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
		// Keyword 초기값.
		onSearchListByKey();
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
	function onSearchListByKey(){
		var frm = $("#frm").serialize();
		var url = "/keywordSearch.json";
		$.ajax({
 			type: "POST",
 			url : url,
 			dataType: "json",
 			data : frm,
 			success: function(results){
 				var result = results.listByKeyword;
 				for(var i = 0; i < result.length; i++){
 					var data = result[i];
 					var keyList = $("[data-name='keyList']");
 					var item = $(keyList[i]);
 					item.children().children(".top-title").text(data.news_title);
 					item.children().children(".top-title").attr("href", "/News/n010d" + data.news_id + ".do");
 					item.children().children(".top-media").text(data.media_nm);
 					item.children().children(".top-name").text(data.author_nm);
 					
 					var date = new Date(data.reg_dt);
 					var yyyy = date.getFullYear();
 					var mm = (date.getMonth() + 1 > 9) ? (date.getMonth() + 1) : "0" + (date.getMonth() + 1);
 					var dd = date.getDate() > 9 ? date.getDate() : "0" + date.getDate();
 					item.children().children(".top-date").text(yyyy+"-"+mm+"-"+dd);
 					item.children().children(".top-view").text("조회수 " + data.view_cnt);
 					if(i == 0){
	 					item.children().children(".top-contents").text(data.news_contents);
 					}
 				}
 			},
 			error: function(data){
 				alert("E" + data);
 			},
 		})
	}
	function selectKeyword(evt, keyword) {
      	var i, tabcontent, tablinks;

      	tablinks = document.getElementsByClassName("tablinks");
      	for (i = 0; i < tablinks.length; i++) {
        	tablinks[i].className = tablinks[i].className.replace(" active", "");
      	}
		evt.currentTarget.className += " active";
		
		// 리스트 초기화
		resetList();
		
		// 검색 단어로 값 가져오기.
		$("#keyword").val(keyword);
		onSearchListByKey();
    }
	function resetList(){
		var keyList = $("[data-name='keyList']");
		for(var i = 0; i < keyList.length; i++){
			var item = $(keyList[i]);
			item.children().children(".top-title").text("Title");
			item.children().children(".top-title").removeAttr("href");
			item.children().children(".top-media").text("Media");
			item.children().children(".top-name").text("Name");
			item.children().children(".top-date").text("Date");
			item.children().children(".top-view").text("조회수");
			if(i == 0){
				item.children().children(".top-contents").text("Contents");
			}
		}
	}
// 	Image down 시 사용함.
// 	function downimage(){
// 		$.ajax({
//  			type: "POST",
//  			url : "/downImage.json",
//  			dataType: "json",
//  			success: function(results){
//  				console.log(results);
//  			},
//  			error: function(data){
//  				alert("E" + data);
//  			},
//  		})
// 	}
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
    			<input type="hidden" name="keyword" id="keyword" value="${searchOptionVO.keyword }"/>
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
	    		<c:forEach items="${keyList }" var="keyList" end="6" varStatus="status">
	    			<button class="tablinks <c:if test='${status.first }'>active</c:if>" onclick="selectKeyword(event, '${keyList.keyword}')">${keyList.keyword }</button>
	    		</c:forEach>
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
			            <div class="top-wrap" data-name="keyList">
			            	<div class="top-header">
			            		<a class="top-title">Title</a>
			            	</div>
			            	<div class="top-section">
			            		<p class="top-contents">Contents</p>
			            	</div>
			            	<div class="top-bottom">
			            		<p class="top-media">Media</p>
			            		<p class="top-name">Name</p>
			            		<p class="top-date">Date</p>
			            		<p class="top-view">조회수</p>
			            	</div>
			            </div>
	          		</div>
	          		<div class="spacer-vertical" ></div>
	          		<div class="tab-list-wrap">
	            		<ul class="tab-list">
	              			<li class="news-list" data-name="keyList">
	              				<div class="top-header">
	                				<a class="top-title">Title</a>
                				</div>
	                			<div class="top-bottom">
				            		<p class="top-media">Media</p>
				            		<p class="top-name">Name</p>
				            		<p class="top-date">Date</p>
				            		<p class="top-view">조회수</p>
				            	</div>
	              			</li>
	              			<li class="news-list" data-name="keyList">
	                			<div class="top-header">
	                				<a class="top-title">Title</a>
                				</div>
	                			<div class="top-bottom">
				            		<p class="top-media">Media</p>
				            		<p class="top-name">Name</p>
				            		<p class="top-date">Date</p>
				            		<p class="top-view">조회수</p>
				            	</div>
	              			</li>
	              			<li class="news-list" data-name="keyList">
	              				<div class="top-header">
	                				<a class="top-title">Title</a>
                				</div>
	                			<div class="top-bottom">
				            		<p class="top-media">Media</p>
				            		<p class="top-name">Name</p>
				            		<p class="top-date">Date</p>
				            		<p class="top-view">조회수</p>
				            	</div>
	              			</li>
	              			<li class="news-list" data-name="keyList">
	              				<div class="top-header">
	                				<a class="top-title">Title</a>
                				</div>
	                			<div class="top-bottom">
				            		<p class="top-media">Media</p>
				            		<p class="top-name">Name</p>
				            		<p class="top-date">Date</p>
				            		<p class="top-view">조회수</p>
				            	</div>
			              	</li>
			              	<li class="news-list" data-name="keyList">
			              		<div class="top-header">
	                				<a class="top-title">Title</a>
                				</div>
	                			<div class="top-bottom">
				            		<p class="top-media">Media</p>
				            		<p class="top-name">Name</p>
				            		<p class="top-date">Date</p>
				            		<p class="top-view">조회수</p>
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
  	<script src="/resources/js/common.js"></script>
  	<footer>
		<jsp:include page="/WEB-INF/views/common/footer.jsp" />	    
  	</footer>
</body>

</html>

