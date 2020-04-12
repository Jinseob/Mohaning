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
			scoreAjax(this);
		});
	})
	function scoreAjax(that){
		$(".media").removeClass("active");
		$(that).addClass("active");
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
 				var id = "myChart1";
 				var items = results.mediaScore;
 				drawChart(items, prop, id);
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
		        <div class="tabcontent-title">
		        	<h4>Paris</h4>
		        </div>
	        	<div class="tabcontent-wrap">
		          	<div class="tab-list-wrap">
			            <div class="contents_type">
			            	<h5>기자 분석</h5>
			            </div>
	            		<ul class="tab-list">
	              			<li class="news-list">
	                			<p class="press-name">국민일보</p>
	                			<p class="news-title">신천지 에브리웨어 싯파샛키들아!!!!!</p>
	              			</li>
	              			<li class="news-list">
	                			<p class="press-name">좃선일보</p>
	                			<p class="news-title">대구경북지역 신천지 때문에 초전박살났다.대구경북지역 신천지 때문에 초전박살났다.대구경북지역 신천지 때문에 초전박살났다.</p>
	              			</li>
	              			<li class="news-list">
	                			<p class="press-name">오마이뉴읏스</p>
	                			<p class="news-title">우리회사도 재택근무로 전면 바꿔줘라!</p>
	              			</li>
	              			<li class="news-list">
	                			<p class="press-name">중앙일보</p>
	                			<p class="news-title">코로나 종식돼야 운동도하고 쇼핑도하지 놀러가고 싶따고요</p>
			              	</li>
			              	<li class="news-list">
	                			<p class="press-name">연합뉴스TV</p>
				                <p class="news-title">돈많이많이 벌고시포요 막 이것저것 지르고 시포요!</p>
	              			</li>
	            		</ul>
	          		</div>
	          		<div class="spacer-vertical" ></div>
	          		<div class="tab-list-wrap">
			            <div class="contents_type">
			            	<h5>기자 분석</h5>
			            </div>
	            		<ul class="tab-list">
	              			<li class="news-list">
	                			<p class="press-name">국민일보</p>
	                			<p class="news-title">신천지 에브리웨어 싯파샛키들아!!!!!</p>
	              			</li>
	              			<li class="news-list">
	                			<p class="press-name">좃선일보</p>
	                			<p class="news-title">대구경북지역 신천지 때문에 초전박살났다.대구경북지역 신천지 때문에 초전박살났다.대구경북지역 신천지 때문에 초전박살났다.</p>
	              			</li>
	              			<li class="news-list">
	                			<p class="press-name">오마이뉴읏스</p>
	                			<p class="news-title">우리회사도 재택근무로 전면 바꿔줘라!</p>
	              			</li>
	              			<li class="news-list">
	                			<p class="press-name">중앙일보</p>
	                			<p class="news-title">코로나 종식돼야 운동도하고 쇼핑도하지 놀러가고 싶따고요</p>
			              	</li>
			              	<li class="news-list">
	                			<p class="press-name">연합뉴스TV</p>
				                <p class="news-title">돈많이많이 벌고시포요 막 이것저것 지르고 시포요!</p>
	              			</li>
	            		</ul>
	          		</div>
	          		<div class="spacer-vertical" ></div>
	          		<div class="tab-list-wrap">
			            <div class="contents_type">
			            	<h5>기자 분석</h5>
			            </div>
	            		<ul class="tab-list">
	              			<li class="news-list">
	                			<p class="press-name">국민일보</p>
	                			<p class="news-title">신천지 에브리웨어 싯파샛키들아!!!!!</p>
	              			</li>
	              			<li class="news-list">
	                			<p class="press-name">좃선일보</p>
	                			<p class="news-title">대구경북지역 신천지 때문에 초전박살났다.대구경북지역 신천지 때문에 초전박살났다.대구경북지역 신천지 때문에 초전박살났다.</p>
	              			</li>
	              			<li class="news-list">
	                			<p class="press-name">오마이뉴읏스</p>
	                			<p class="news-title">우리회사도 재택근무로 전면 바꿔줘라!</p>
	              			</li>
	              			<li class="news-list">
	                			<p class="press-name">중앙일보</p>
	                			<p class="news-title">코로나 종식돼야 운동도하고 쇼핑도하지 놀러가고 싶따고요</p>
			              	</li>
			              	<li class="news-list">
	                			<p class="press-name">연합뉴스TV</p>
				                <p class="news-title">돈많이많이 벌고시포요 막 이것저것 지르고 시포요!</p>
	              			</li>
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
	    <div class="chart_wrap">
	    	<div class="chart_area">
	        	<span>기자명</span>
	        	<div class="chart"></div>
	      	</div>
	      	<div class="chart_area">
	        	<span>기자명</span>
	        	<div class="chart"></div>
	      	</div>
	      	<div class="chart_area">
	        	<span>기자명</span>
	        	<div class="chart"></div>
	      	</div>
	    </div>
	</div>
  
  	<!-- 언론사 화면 -->
  	<div class="container mt-20">
	    <div class="contents_type">
	    	<h5>언론사 분석</h5>
	    </div>
	    <div class="media_wrap">
	    	<div class="chart_area">
	        	<div class="chart">
	        		<canvas id="myChart1" width="238" height="238"></canvas>
	        	</div>
	      	</div>
	      	<div class="media_area">
	        	<c:choose>
	        		<c:when test="${fn:length(mediaList) > 0 }">
	        			<table class="media_table">
	        			<c:forEach items="${mediaList }" var="result" varStatus="status">
	        				<c:if test="${status.count % 6 eq 1}">
								<tr>
				   			</c:if>
				   				<c:choose>
				   				<c:when test="${fn:length(mediaList) eq status.count }">
				   					<td><a href="/Media/main.do" >더보기</a></td>
				   				</c:when>
				   				<c:otherwise>
				   					<c:if test="${searchOptionVO.media_id eq result.media_id}">active</c:if>
					   				<td class="media" data-index="${result.media_id }"><a href="/Media/c010d${result.media_id }.do" >${result.media_nm}</a></td>
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

