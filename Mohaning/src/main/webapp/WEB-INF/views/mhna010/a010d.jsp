<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

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
		let dialog, form;
		
		$("#propensity > div > span[data-type=slide]").each(function(e){
			var value = parseInt( $( this ).text(), 10 );
			$(this).empty().slider({
				value : value,
				range: "min",
				animate: true,
				slide: function(event, ui){
					var index = $(this).data("index");
					var input = $("#propensity > div > input[name=" + index + "]");
					input.val(ui.value);
				}
			})
		});
		
		$("#propensity > div > input").change(function(e){
			var value = parseInt($(this).val(), 10);
			var name = $(this).attr("name");
			$("#propensity > div > span[data-index=" + name + "]").slider({
				value : value
			})
		})
		
		dialog = $("#dialog-confirm").dialog({
			autoOpen: false,
			resizable: false,
			height: "auto",
			width: 720,
			modal: true,
			buttons:{
				"등록" : function(){
					$(this).dialog("close");
				},
				Cancel: function(){
					$(this).dialog("close");
				}
			}
		});
		
		$("#dialogOpenBtn").button().on("click", function(){
			dialog.dialog("open");
		});
		
		$("#saveBtn").button().on("click", function(){
			onSaveBtn();
		});
	})
	
	function onSaveBtn(){
		if(confirm("저장 하시겠습니까?")){
			$("#frm").attr({"action" : "/processUpdate_a010.do", "method" : "POST"}).submit();
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
	<h1>기사 상세 소스를 수정하고 그러면 어케 되려나</h1>
	
	<div id="dialog-confirm" title="새로운 성향을 등록하시겠습니까?">
		<div id="propensity">
		<div>
			<span>성향1</span>
			<span data-index="${result.author_nm }" data-type="slide">10</span>
			<input name="${result.author_nm }" value="10" onchange="javascript: onChangeCharacter();"/>
		</div>
		</div>
	</div>

<form id="frm" name="frm">
	<input type="hidden" value="${result.news_id }" name="news_id" id="news_id" />
</form>

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
	var ctx = document.getElementById('myChart').getContext('2d');
	var myChart = new Chart(ctx, {
	    type: 'radar',
	    data: {
	        labels: ['Running', 'Swimming', 'Eating', 'Cycling', 'A', 'B', 'C'],
	        datasets: [{
	        	label: '성향1',
	            data: [20, 10, 4, 2, 30, 23, 10],
	            borderColor : 'rgba(200, 0, 0, 0.1)',
	            backgroundColor: 'rgba(200, 0, 0, 0.2)'
	        },
	        {
	        	label: '성향2',
	            data: [30, 20, 14, 12, 40, 33, 20],
	            borderColor : 'rgba(0, 200, 0, 0.1)',
	            backgroundColor: 'rgba(0, 200, 0, 0.2)'
	        }
	        ]
	    },
	    options: {
	    	scale:{
	    		ticks:{
	    			beginAtZero: true,
	    			max :100
	    		}
	    	}
	    }
	});
	</script>
	<!-- Chart.js End -->
	
	<!-- Score Button -->
	<div id="propensity">
		<c:choose>
			<c:when test="${fn:length(scoreListByUser) > 0 }">
			<c:forEach items="${scoreListByUser }" var="scoreList">
				<div>
					<c:out value="${scoreList.type_nm }" />
					<input type="checkbox" name="${scoreList.type_cd }" title="${scoreList.type_nm }" 
						   <c:if test="${scoreList.score eq '0'} ">checked</c:if>
					></input>
<%-- 					<input type="button" name="${scoreList.type_cd }" value="${scoreList.type_nm }" checked="checked"/> --%>
				</div>
			</c:forEach>
			</c:when>
		</c:choose>
	</div>
	
	<input type="button" value="성향등록" id="dialogOpenBtn" />
	<input type="button" value="평가" id="saveBtn" />
</body>
</html>
