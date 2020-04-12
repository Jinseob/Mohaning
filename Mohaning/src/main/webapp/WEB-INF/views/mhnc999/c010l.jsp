<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!doctype html>
<html>
<head>
	<title>모하닝</title>
	<jsp:directive.include file="/WEB-INF/views/common/taglib.jsp" />
	
	<!-- Chart.js -->
	<script src="/resources/js/Chart.bundle.js"></script>
	<!-- Chart.js End -->
	<script type="text/javascript">
	$(function(){
// 		$("#dialogOpenBtn").button().on("click", function(){
// 			dialog.dialog("open");
// 		});
		
// 		$("#saveBtn").button().on("click", function(){
// 			onSaveBtn();
// 		});
		
// 		$("#replyBtn").button().on("click", function(){
// 			onReplyBtn("1");
// 		});
	})
	
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
        		<jsp:include page="/WEB-INF/views/common/search.jsp" />
      		</div>
          	</form>
    	</div>
  	</div>
	
	<div class="container">
		<div class="contents_type">
	   		<h5>언론사 목록</h5>
	     	<div class="filters_wrap">
	       		<div class="custom-select">
	         		<select id="sortType">
           				<option value="01" <c:if test="${searchOptionVO.sort eq '01' }">selected</c:if>>등록일최신순</option>
           				<option value="02" <c:if test="${searchOptionVO.sort eq '02' }">selected</c:if>>등록일과거순</option>
           				<option value="03" <c:if test="${searchOptionVO.sort eq '03' }">selected</c:if>>조회수높은순</option>
           				<option value="04" <c:if test="${searchOptionVO.sort eq '04' }">selected</c:if>>조회수낮은순</option>
<!-- 	           			<option value="S13">댓글순</option> -->
<!-- 	           			<option value="S14">평가순</option> -->
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
					<h4><a href="/Media/c010d${result.media_id }.do"><b>${result.media_nm }</b></a></h4>
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
	
	<script src="/resources/js/dropDown.js"></script>
  	<footer>
		<jsp:include page="/WEB-INF/views/common/footer.jsp" />	    
  	</footer>   
</body>
</html>
