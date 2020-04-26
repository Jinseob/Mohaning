<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!doctype html>
<html>
<head>
	<title>모하닝</title>
	<jsp:directive.include file="/WEB-INF/views/common/taglib.jsp" />
	
	<!-- Chart.js -->
	<script src="/resources/js/Chart.bundle.js"></script>
	<!-- Chart.js End -->
	<script src="/resources/js/common.js"></script>
	<script type="text/javascript">
	$(function(){
		scoreAjax();
	});
	function scoreAjax(){
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
	function onPageMove(url){
		if(url == "main") url = "/Media/main";
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
		<input type="hidden" name="media_id" id="media_id" value="${media.media_id}"/>
	</form>
	
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
		              		<div class="chart">
		              			<canvas id="myChart1" width="300" height="300"></canvas>
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
		        		<c:when test="${fn:length(mediaNewsList) > 0 }">
		        		<c:forEach items="${mediaNewsList }" var="result">
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
	          		<h5>최근 등록된 기자</h5>
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
</body>
</html>
