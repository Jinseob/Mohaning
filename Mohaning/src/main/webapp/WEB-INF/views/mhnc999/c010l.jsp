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
	   							<c:if test="${status.count % 4 eq 1}">
								<div class="uk-grid-divider uk-child-width-expand@s" uk-grid>
	   							</c:if>
	   							<div>
		      						<h4><a href="/c010d${result.media_id }.do"><b>${result.media_nm }</b></a></h4>
		      						<div style="width: 250px; height: 250px;">
										<canvas id="myChart${status.count }" width="250" height="250"></canvas>
									</div>
									<script type="text/javascript">
									var scoreList = JSON.parse('${result.scoreList}');
									var ctx = document.getElementById('myChart${status.count }').getContext('2d');
										
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
								</div>
								<c:if test="${status.count % 4 eq 0}">
								</div>
								</c:if>
							</c:forEach>
	      				</c:when>
	      				<c:otherwise>
	      					데이터가 없습니다.
	      				</c:otherwise>
      				</c:choose>
      			</div>
      		</div>
    	</div>
	</div>
</body>
</html>
