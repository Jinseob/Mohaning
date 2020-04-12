<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!doctype html>
<html>
<head>
	<title>모하닝</title>
	<jsp:directive.include file="/WEB-INF/views/common/taglib.jsp" />
<!-- 	<link rel="stylesheet" href="/resources/css/jquery-ui.css" /> -->
<!-- 	<script src="/resources/js/jquery-ui.js"></script> -->

	<!-- Google Charts -->
	<!-- <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
	<script type="text/javascript">
		google.charts.load('current', {packages: ['corechart']});
		google.charts.setOnLoadCallback(drawChart);
		
		function drawChart(){
			// Define the chart to be drawn.
			var data = new google.visualization.DataTable();
			data.addColumn('string', 'Element');
			data.addColumn('number', 'Percentage');
			data.addRows([
			  ['Nitrogen', 0.78],
			  ['Oxygen', 0.21],
			  ['Other', 0.01]
			]);
			
			// Instantiate and draw the chart.
			var chart = new google.visualization.PieChart(document.getElementById('myPieChart'));
			chart.draw(data, null);
		}
	</script> -->
	<!-- Google Charts End -->
	
	<!-- Chart.js -->
	<script src="/resources/js/Chart.bundle.js"></script>
	<!-- Chart.js End -->
	
	<script src="/resources/js/common.js"></script>
	<script type="text/javascript">
	$(function(){
		// Chart 넣는 부분.
		var newsScore = JSON.parse('${newsScore}');
		var authorScore = JSON.parse('${authorScore}');
		var mediaScore = JSON.parse('${mediaScore}');
		var prop = new Object();
		drawChart(newsScore, prop, "myChart1");
		drawChart(authorScore, prop, "myChart2");
		drawChart(mediaScore, prop, "myChart3");
		// Chart 넣는 부분. 여기까지.
		
		$("#saveBtn").on("click", function(){
			onSaveBtn();
		});
	})
	
	function onSaveBtn(){
		if(confirm("평가 하시겠습니까?")){
			var frm = $("#frm").serialize();
			$.ajax({
				method: "POST",
				url : "/scoreUpdate.json",
				dataType: "JSON",
				data : frm,
				success: function(results){
					var result = results.result;
					if(results.type == "NEW"){
						$("#title").val(result.title);
						$("#author_id").val(result.author_id);
						$("#author_nm").val(result.author_nm);
						$("#author_email").val(result.author_email);
						$("#media_id").val(result.media_id);
						$("#media_nm").val(result.media_nm);
						$("#media_url").val(result.media_url);
					}else if(results.type == "OCC"){
						if(confirm(result.news_id + " 번으로 등록된 뉴스가 있습니다.\n이동하시겠습니까?")){
							onPageMove("/Author/a010d" + result.news_id);					
						}
					}
				},
				error: function(data){
					alert("E" + data);
				}
			})
			
// 			$("#frm").attr({"action" : "/scoreUpdate_d010.do", "method" : "POST"}).submit();
		}
	}
	
	function onPageMove(url){
		if(url = "main") url = "/News/main";
		$("#frm").attr({"action" : url + ".do", "method" : "POST"}).submit();
	}
	</script>
	
	<style>
/* 	#propensity > div{ */
/* 		width: 700px; */
/* 		height: 45px; */
/* 	} */
/* 	#propensity > div > input{ */
/* 		width: 50px; */
/* 		margin:10px */
/* 	} */
/* 	#propensity > div > span{ */
/* 		float:left; margin:10px; */
/* 	} */
/* 	#propensity > div > span[data-type=slide]{ */
/* 		width:500px; float:left; margin:15px; */
/* 	} */
	</style>
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
          			<h5>기사 상세</h5>
        		</div>
      		</div>
      		<div class="contents_title">
        		<div class="title">
          			<h3>${result.news_title }</h3>
        		</div>
      		</div>
      		<div class="news_info">
        		<div class="news_url">
	          		<label>원문 URL</label>
	          		<a href="${result.news_url }" target="_blank">&nbsp; &nbsp;${result.news_url }</a>
	        	</div>
        		<c:choose>
		        	<c:when test="${fn:length(portalList) > 0}">
		        		<c:forEach items="${portalList }" var="pList">
				        <div class="news_url">
			          		<label><c:out value="${pList.portal_nm }" /> URL</label>
			          		<a href="${pList.portal_url }" target="_blank">&nbsp; &nbsp;${pList.portal_url }</a>
			        	</div>
		        		</c:forEach>
		        	</c:when>
		        </c:choose>
	        	<div class="author_info">
	          		<div class="author_name">
	            		<p>${result.author_nm }</p>
	          		</div>
	          		<div class="author_email">
	            		<p>${result.author_email }</p>
	          		</div>
	          		<div class="author_media">
	            		<p><a href="${result.media_url }" target="_blank">${result.media_nm }</a></p>
	          		</div>
	        	</div>
<%-- 	        	${result.news_contents } --%>
	      	</div>
      		<div class="contents_info">
        		<div class="contents_author">
          			<span>작성자명</span>
        		</div>
        		<div class="contents_regdt">
          			<span>2020-03-18</span>
        		</div>
      		</div>
      		<div class="contents_view">
        		<article>
          			<div class="contents_article">
            			<p>${result.news_contents }</p>
            			<p>
              				<br/>
            			</p>
          			</div>
        		</article>
      		</div>
      		<div class="contents_tags">
        		<div class="tags">
          			<p>#태그</p>
          			<p>#태그1</p>
          			<p>#태그2</p>
          			<p>#태그3</p>
        		</div>
      		</div>
      		<div class="chart_wrap mt-30">
        		<div class="chart_area">
          			<span>기사</span>
          			<div class="chart">
          				<canvas id="myChart1" width="300" height="300"></canvas>
          			</div>
        		</div>
	        	<div class="chart_area">
	          		<span>기자</span>
	          		<div class="chart">
	          			<canvas id="myChart2" width="300" height="300"></canvas>
	          		</div>
	        	</div>
	        	<div class="chart_area">
	          		<span>언론사</span>
	          		<div class="chart">
	          			<canvas id="myChart3" width="300" height="300"></canvas>
	          		</div>
	        	</div>
	      	</div>
	      	<div class="property_wrap mt-40">
	        	<div class="property_head">
	          		<h6>기사의 매력포인트를 선택해주세요!</h6>
	        	</div>
	        	<!-- Score Button -->
				<form id="frm" name="frm">
					<input type="hidden" value="${result.news_id }" name="news_id" id="news_id" />
					<input type="hidden" value="${result.author_id }" name="author_id" id="author_id" />
					<input type="hidden" value="${result.media_id }" name="media_id" id="media_id" />
					<input type="hidden" value="${result.portal_id }" name="portal_id" id="portal_id" />
			        <div class="uk-grid-divider uk-child-width-expand@s uk-text-center" uk-grid>
					<c:choose>
						<c:when test="${fn:length(scoreListByUser) > 0 }">
						<div class="property_items">
						<c:forEach items="${scoreListByUser }" var="scoreList" varStatus="status">
							<div class="item">
								<input type="checkbox" id="chkItem${status.index }" name="scoreList" 
									   value="${scoreList.type_cd }" <c:if test="${scoreList.score eq '1'}">checked</c:if>>
            					<label for="chkItem${status.index }"><c:out value="${scoreList.type_nm }" /></label>
<!-- 								<label> -->
<!-- 									<input class="" -->
<!-- 										   type="checkbox"  -->
<!-- 										   name="scoreList"  -->
<%-- 										   value="${scoreList.type_cd }"  --%>
<%-- 										   <c:if test="${scoreList.score eq '1'}">checked</c:if> --%>
<!-- 									/>	    -->
<%-- 									<c:out value="${scoreList.type_nm }" /> --%>
<!-- 								</label> -->
							</div>
						</c:forEach>
						</div>
						</c:when>
					</c:choose>
		          	</div>
				</form>
        	</div>
		    <div class="buttons_wrap">
		    	<div class="left_group">
		        	<button type="button" class="btn1" onclick="javascript: onPageMove('main');">목록</button>
		        </div>
		        <div class="right_group">
					<button type="button" class="btn1" id="saveBtn">평가</button>
		        </div>
			</div>
		</div>
	</div>
	
	<!-- List -->
  	<div class="container">
		<div class="column-wrapper">
      		<article class="column">
      			<div class="contents_type">
          			<h5>해당 기사를 사용한 토론방 목록</h5>
        		</div>
        		<ul class="popular-news-list">
        			<c:choose>
						<c:when test="${fn:length(boardList) > 0 }">
						<c:forEach items="${boardList }" var="result">
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
        		</ul>
      		</article>
    	</div>
   	</div>
	</section>
	
	
	<footer>
		<jsp:include page="/WEB-INF/views/common/footer.jsp" />	    
  	</footer>
</body>
</html>
