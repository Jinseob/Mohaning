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
				var mediaScore = JSON.parse('${result.scoreList}');
				var prop = new Object();
				drawChart(mediaScore, prop, "myChart${status.count }");
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
	
<!-- 	<script src="/resources/js/dropDown.js"></script> -->
  	<footer>
		<jsp:include page="/WEB-INF/views/common/footer.jsp" />	    
  	</footer>   
</body>
</html>
