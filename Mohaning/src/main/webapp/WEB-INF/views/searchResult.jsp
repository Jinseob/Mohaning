<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!doctype html>
<html>
<head>
	<title>모하닝</title>
	<jsp:directive.include file="/WEB-INF/views/common/taglib.jsp" />
	<script type="text/javascript">
	function onPageMove(type){
		var url = "";
		switch(type){
		case "I":
			url = "/Board/b010i.do";
			break;
		case "M":
			url = "/Board/main.do";
			break;
		}
		
		$("#frm").attr({"action":url, "method":"POST"}).submit();
	}
	$(document).on('change', '#sortType', function(){
		$("#sort").val($("#sortType option:selected").val());
		onPageMove("M");
	});
	</script>
</head>
<body>
	<!-- Header -->
	<header>
		<jsp:include page="/WEB-INF/views/common/header.jsp" />
	</header>
	
	<section>
	<!-- Search -->
	<div class="container">
    	<div class="row">
        	<form id="frm" name="frm">
        		<input type="hidden" name="sort" id="sort" />
        		<input type="hidden" name="news_id" id="news_id" />
	      		<div class="search-wrapper">
	        		<jsp:include page="/WEB-INF/views/common/search.jsp" />
	      		</div>
          	</form>
    	</div>
  	</div>
  	
  	<!-- Search Result Word -->
  	<div class="container">
    	<div class="row">
      		<div class="search-result-wrapper">
        		<c:choose>
        			<c:when test="${val ne '' }">
        				<h3>"${searchOptionVO.val }"</h3>	
        			</c:when>
        			<c:otherwise>
        				<h3>"검색 결과"</h3>
        			</c:otherwise>
        		</c:choose>
        		
        		
      		</div>
    	</div>
  	</div>
  	
  	<!-- 토론방 결과 -->
  	<div class="container mt-30">
		<div class="contents_type">
			<div class="title_wrap">
	   			<h5>토론방 결과</h5><h5 class="highlight">(${boardResultCnt } 건)</h5>
			</div>
   		</div>
   		<ul class="popular-news-list">
			<c:choose>
				<c:when test="${fn:length(boardResultList) > 0 }">
				<c:forEach items="${boardResultList }" var="result">
					<li class="news-list-title">
						<label>298</label>
						<a href="/Board/b010d${result.board_id }.do">${result.title }</a>
					</li>
				</c:forEach>
				</c:when>
				<c:otherwise>
					<li>데이터가 없습니다.</li>
				</c:otherwise>
			</c:choose>
			<li><a href="/Board/main.do?val=${searchOptionVO.val }" class="more right">기사 더보기 ></a></li>
   		</ul>
	</div>

	<!-- 기사 결과 -->
    <div class="container mt-30">
    	<div class="contents_type">
    		<div class="title_wrap">
        		<h5>기사 결과</h5><h5 class="highlight">(${newsResultCnt } 건)</h5>
       		</div>
        </div>
        <ul class="popular-news-list">
			<c:choose>
				<c:when test="${fn:length(newsResultList) > 0 }">
				<c:forEach items="${newsResultList }" var="result">
					<li class="news-list-title">
						<label>298</label>
						<a href="/News/n010d${result.news_id }.do">${result.news_title }</a>
					</li>
				</c:forEach>
				</c:when>
				<c:otherwise>
					<li>데이터가 없습니다.</li>
				</c:otherwise>
			</c:choose>
			<li><a href="/News/main.do?val=${searchOptionVO.val }" class="more right">기사 더보기 ></a></li>
		</ul>
	</div>
  	
  	<!-- 기자 결과 -->
  	<div class="container mt-30">
  		<div class="contents_type">
  			<div class="title_wrap">
   				<h5>기자 결과</h5><h5 class="highlight">(11 건)</h5>
   			</div>
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
		</div>
		<a href="/News/main.do" class="more right">기자 더보기 ></a>
  	</div>
  	
  	<!-- 언론사 결과 -->
  	<div class="container mt-30">
  		<div class="contents_type">
  			<div class="title_wrap">
   				<h5>언론사 결과</h5><h5 class="highlight">(11 건)</h5>
   			</div>
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
		</div>
		<a href="/News/main.do" class="more right">언론사 더보기 ></a>
  	</div>
  	
  	</section>
  	
<!--   	<script src="/resources/js/dropDown.js"></script> -->
  	<footer>
		<jsp:include page="/WEB-INF/views/common/footer.jsp" />	    
  	</footer>
</body>
</html>
