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
		var url = "";
		switch(type){
		case "I":
			url = "/a010i.do";
			break;
		case "D":
			url = "/a010d.do";
			break;
		}
		
		$("#frm").attr({"action":url, "method":"POST"}).submit();
	}
	</script>
</head>
<body>
	<!-- Header -->
	<header>
		<jsp:include page="/WEB-INF/views/common/header.jsp" />
	</header>
	<h1>기사 메인</h1>
	<div class="uk-section">
	  	<div class="uk-container uk-margin-small-top">
		    <div class="uk-container">
      			<div class="uk-grid-small" uk-grid>
      				<c:choose>
      					<c:when test="${fn:length(resultList) > 0 }">
      						<c:forEach items="${resultList }" var="result" varStatus="status">
      							<div style="width: 250px; height: 250px;">
									<canvas id="myChart${status.count }" width="250" height="250"></canvas>
								</div>
								<c:forEach items="${result.scoreList }" var="scoreList" varStatus="itemStatus">
<%-- 				      				${scoreList.type_nm }||${scoreList.score } --%>
									<script type="text/javascript">
									var scoreList = JSON.parse('${scoreList}');
	// 								var scoreList = [
	// 									{type_nm : "A", score: 11},
	// 									{type_nm : "B", score: 12},
	// 									{type_nm : "C", score: 13},
	// 									{type_nm : "D", score: 14},
	// 									{type_nm : "E", score: 15},
	// 									{type_nm : "F", score: 16}
	// 								]
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
									        	label: '기사 특성',
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
								</c:forEach>
<%--       							${result.media_nm } - ${result.media_id } <br/> --%>
      							<br/>
      						</c:forEach>
      					</c:when>
      					<c:otherwise>
      						데이터가 없습니다.
      					</c:otherwise>
      				</c:choose>
      			
      				<div class="uk-grid-divider uk-child-width-expand@s" uk-grid>
			        <!-- 	<div>
			          	<h4><b>기사</b></h4>
						Chart.js
						<div style="width: 250px; height: 250px;">
							<canvas id="myChart1" width="250" height="250"></canvas>
						</div>
						<script type="text/javascript">
// 						var scoreList = JSON.parse('${score}');
						var scoreList = [
							{type_nm : "A", score: 11},
							{type_nm : "B", score: 12},
							{type_nm : "C", score: 13},
							{type_nm : "D", score: 14},
							{type_nm : "E", score: 15},
							{type_nm : "F", score: 16}
						]
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
						        	label: '기사 특성',
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
						Chart.js End          
			          	</div>
			          	<div>
			          	<h4><b>기자</b></h4>
			          	Chart.js
						<div style="width: 250px; height: 250px;">
							<canvas id="myChart2" width="250" height="250"></canvas>
						</div>
						<script type="text/javascript">
// 						var scoreList = JSON.parse('${score}');
						var scoreList = [
							{type_nm : "A", score: 11},
							{type_nm : "B", score: 12},
							{type_nm : "C", score: 13},
							{type_nm : "D", score: 14},
							{type_nm : "E", score: 15},
							{type_nm : "F", score: 16}
						]
						var ctx = document.getElementById('myChart2').getContext('2d');
						
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
						Chart.js End
			          	</div>
			          	<div>
			          		<h4><b>언론사</b></h4>
			          	Chart.js
						<div style="width: 250px; height: 250px;">
							<canvas id="myChart3" width="250" height="250"></canvas>
						</div>
						<script type="text/javascript">
// 						var scoreList = JSON.parse('${score}');
						var scoreList = [
							{type_nm : "A", score: 11},
							{type_nm : "B", score: 12},
							{type_nm : "C", score: 13},
							{type_nm : "D", score: 14},
							{type_nm : "E", score: 15},
							{type_nm : "F", score: 16}
						]
						var ctx = document.getElementById('myChart3').getContext('2d');
						
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
						Chart.js End
			          	</div>
			          	<div>
			          		<h4><b>언론사</b></h4>
			          	Chart.js
						<div style="width: 250px; height: 250px;">
							<canvas id="myChart4" width="250" height="250"></canvas>
						</div>
						<script type="text/javascript">
// 						var scoreList = JSON.parse('${score}');
						var scoreList = [
							{type_nm : "A", score: 11},
							{type_nm : "B", score: 12},
							{type_nm : "C", score: 13},
							{type_nm : "D", score: 14},
							{type_nm : "E", score: 15},
							{type_nm : "F", score: 16}
						]
						var ctx = document.getElementById('myChart4').getContext('2d');
						
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
						Chart.js End
			          	</div> -->
			        </div>
      			</div>
      		</div>
		</div>
	</div>
</body>
</html>
