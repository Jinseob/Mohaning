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
	
	<section>
	<!-- Search -->
	<div class="container">
    	<div class="row">
        	<form id="frm" name="frm">
      		<div class="search-wrapper">
        		<div class="dropdown">
          			<h6 style="display:inline-block;">인기검색어</h6>
          			<a class="hot-keyword"><b>1</b> 코로나19</a>
          			<button class="dropbtn">Q</button>
          			<div class="dropdown-content">
			            <a href="#">Link 1</a>
			            <a href="#">Link 2</a>
			            <a href="#">Link 3</a>
          			</div>
        		</div>
        		<div class="search-container">
		        		<input type="hidden" name="news_id" id="news_id" />
		            	<input type="text" placeholder="코로나19" name="search">
		            	<button type="submit">search</button>
        		</div>
      		</div>
          	</form>
    	</div>
  	</div>
	
	<div class="container">
		<div class="contents_type">
	   		<h5>언론사 목록</h5>
	     	<div class="filters_wrap">
	       		<div class="custom-select">
	         		<select>
	           			<option value="S11">등록일순</option>
	           			<option value="S12">조회순</option>
	           			<option value="S13">댓글순</option>
	           			<option value="S14">평가순</option>
	         		</select>
	       		</div>
	     	</div>
	   	</div>
	   	<c:choose>
	    <c:when test="${fn:length(resultList) > 0 }">
	    	<c:forEach items="${resultList }" var="result" varStatus="status">
	    	<%-- ${status.count} = ${status.index} = ${status.count % 4} --%>
	   			<c:if test="${status.count % 4 eq 1}">
					<div class="chart_wrap4">
	   			</c:if>
	   			<div class="chart_area">
					<h4><a href="/c010d${result.media_id }.do"><b>${result.media_nm }</b></a></h4>
					<div class="chart">
						<canvas id="myChart${status.count }" width="225" height="225"></canvas>
					</div>
				</div>
				<c:if test="${status.count % 4 eq 0}">
					</div>
					<div class="spacer-horizontal-line"></div>
				</c:if>
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
			</c:forEach>
		</c:when>
		<c:otherwise>
			데이터가 없습니다.
		</c:otherwise>
	</c:choose>
	</div>
	</section>
	
	<script src="./resources/js/dropDown.js"></script>
  	<footer>
		<jsp:include page="/WEB-INF/views/common/footer.jsp" />	    
  	</footer>   
</body>
</html>
