<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!doctype html>
<html>
<head>
	<title>기사</title>
	<jsp:directive.include file="/WEB-INF/views/common/taglib.jsp" />
	<link rel="stylesheet" href="/resources/css/jquery-ui.css" />
	<script src="/resources/js/jquery-ui.js"></script>

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
	
	<script type="text/javascript">
	
	$(function(){
		$("#dialogOpenBtn").button().on("click", function(){
			dialog.dialog("open");
		});
		
		$("#saveBtn").button().on("click", function(){
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
							onPageMove("a010d" + result.news_id);					
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
	
	function onPageMove(type){
		$("#frm").attr({"action" : "/" + type + ".do", "method" : "POST"}).submit();
	}
	</script>
	<style>
	#propensity > div{
		width: 700px;
		height: 45px;
	}
	#propensity > div > input{
		width: 50px;
		margin:10px
	}
	#propensity > div > span{
		float:left; margin:10px;
	}
	#propensity > div > span[data-type=slide]{
		width:500px; float:left; margin:15px;
	}
	</style>
</head>
<body>
	<!-- Header -->
	<header>
		<jsp:include page="/WEB-INF/views/common/header.jsp" />
	</header>
	<h1>기사 상세</h1>
	
	<div id="dialog-confirm" title="새로운 성향을 등록하시겠습니까?">
		<div id="propensity">
		<div>
			<span>성향1</span>
			<span data-index="${result.author_nm }" data-type="slide">10</span>
			<input name="${result.author_nm }" value="10" onchange="javascript: onChangeCharacter();"/>
		</div>
		</div>
	</div>
	<div>${result.title }</div>
	<div><a href="${result.news_url }" target="_blank">${result.news_url }</a></div>
	<div>${result.view_cnt }</div>
	<div>${result.reg_dt }</div>
	<div>${result.author_nm }</div>
	<div>${result.author_email }</div>
	<div>${result.media_nm }</div>
	<div>${result.media_url }</div>
	
	<input type="button" value="목록" onclick="javascript: onPageMove('Board');" />
	
	<!-- Google Charts -->
<!-- 	<div id="myPieChart"></div> -->
	<!-- Google Charts End -->
	
	<!-- Chart.js -->
	<div style="width: 300px; height: 300px;">
		<canvas id="myChart" width="300" height="300"></canvas>
	</div>
	<script type="text/javascript">
	var scoreList = JSON.parse('${score}');
	var ctx = document.getElementById('myChart').getContext('2d');
	
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
	
	<!-- Score Button -->
	<form id="frm" name="frm">
		<input type="hidden" value="${result.news_id }" name="news_id" id="news_id" />
		<div id="propensity">
			<c:choose>
				<c:when test="${fn:length(scoreListByUser) > 0 }">
				<c:forEach items="${scoreListByUser }" var="scoreList">
					<div>
						<c:out value="${scoreList.type_nm }" />
						<input type="checkbox" 
							   name="scoreList" 
							   title="${scoreList.type_nm }"
							   value="${scoreList.type_cd }" 
							   
							   <c:if test="${scoreList.score eq '1'}">checked</c:if>
						/>${scoreList.score eq '1'}
	<%-- 					<input type="button" name="${scoreList.type_cd }" value="${scoreList.type_nm }" checked="checked"/> --%>
					</div>
				</c:forEach>
				</c:when>
			</c:choose>
		</div>
	</form>
	
<!-- 	<input type="button" value="성향등록" id="dialogOpenBtn" /> -->
	<input type="button" value="평가" id="saveBtn" />
</body>
</html>
