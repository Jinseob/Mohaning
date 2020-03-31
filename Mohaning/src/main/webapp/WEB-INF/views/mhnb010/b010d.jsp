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
		
		$("#replyBtn").button().on("click", function(){
			onReplyBtn("1");
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
	
	function onReplyBtn(){
		if(confirm("답글을 등록 하시겠습니까?")){
			$("#frm").append($("#group_id"));
			$("#frm").append($("#comment"));
			var frm = $("#frm").serialize();
			
			$.ajax({
				method: "POST",
				url : "/replyUpdate.json",
				dataType: "JSON",
				data : frm,
				success: function(results){
					alert("성공 저장 완료.");
					onPageMove("b010d" + results.mhnr010VO.board_id);
				},
				error: function(data){
					alert("E" + data);
				}
			})
		}
	}
	
	function onPageMove(type){
		$("#frm").attr({"action" : "/" + type + ".do", "method" : "POST"}).submit();
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
          			<h5>토론방</h5>
        		</div>
      		</div>
      		<div class="contents_title">
        		<div class="title">
          			<h3>${result.title }</h3>
        		</div>
      		</div>
      		<div class="news_info">
        		<div class="news_title">
          			<h4>${result.news_title }</h4>
        		</div>
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
            			<p>${result.contents }</p>
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
					<input type="hidden" value="${result.board_id }" name="board_id" id="board_id" />
					<input type="hidden" value="${result.news_id }" name="news_id" id="news_id" />
					<input type="hidden" value="${result.author_id }" name="author_id" id="author_id" />
					<input type="hidden" value="${result.media_id }" name="media_id" id="media_id" />
					<input type="hidden" value="${result.portal_id }" name="portal_id" id="portal_id" />
			        <div class="uk-grid-divider uk-child-width-expand@s uk-text-center" uk-grid>
					<c:choose>
						<c:when test="${fn:length(scoreListByUser) > 0 }">
						<div class="property_items">
						<c:forEach items="${scoreListByUser }" var="scoreList">
							<div class="item">
								<label>
									<input class=""
										   type="checkbox" 
										   name="scoreList" 
										   value="${scoreList.type_cd }" 
										   <c:if test="${scoreList.score eq '1'}">checked</c:if>
									/>	   
									<c:out value="${scoreList.type_nm }" />
								</label>
							</div>
						</c:forEach>
						</div>
						</c:when>
					</c:choose>
		          	</div>
				</form>
				<div>
	          		<button class="uk-button uk-button-primary uk-align-center" type="button" id="saveBtn">평가하기</button>
	        	</div>
<!-- 				<div class="property_items"> -->
<!-- 	          		<div class="item"> -->
<!-- 	            		<button type="button">공감</button> -->
<!-- 	          		</div> -->
<!-- 	          		<div class="item"> -->
<!-- 	            		<button type="button">전문성</button> -->
<!-- 	          		</div> -->
<!-- 	          		<div class="item"> -->
<!-- 	            		<button type="button">추정</button> -->
<!-- 	          		</div> -->
<!-- 	          		<div class="item"> -->
<!-- 	            		<button type="button">간접</button> -->
<!-- 	          		</div> -->
<!-- 	          		<div class="item"> -->
<!-- 	            		<button type="button">발품</button> -->
<!-- 	          		</div> -->
<!-- 	          		<div class="item"> -->
<!-- 	            		<button type="button">검증</button> -->
<!-- 	          		</div> -->
<!-- 	        	</div> -->
				
        	</div>
	      	<div class="comment_wrap">
	        	<div class="comment_head">
	          		<span>댓글(<c:out value="${fn:length(replyList) }" />)</span>
	        	</div>
	        	<c:choose>
	          	<c:when test="${fn:length(replyList) > 0 }">
	          		<div class="comment">
	          		<c:forEach items="${replyList }" var="rList" varStatus="rStatus">
	          			<div class="comment_row">
		            		<div class="comment_info">
		              			<div class="comment_author">
		                			<span><c:out value="${rList.reg_id }" /></span>
		                			<span><c:out value="${rList.reg_dt }" /></span>
		              			</div>
		              			<div class="comment_buttons">
					                <button type="button">답글</button>
					                <button type="button">수정</button>
					                <button type="button">신고</button>
					                <button type="button">좋아요</button>
		              			</div>
		            		</div>
		            		<div class="comment_contents">
		              			<div class="comment_view">
		                			<p><c:out value="${rList.comment }" /></p>
		              			</div>
		            		</div>
		          		</div>
          			</c:forEach>
          			</div>
          		</c:when>
          		</c:choose>
	          	<div class="comment_write">
		        	<div class="comment_input">
		            	<textarea cols="50" rows="2" maxlength="6000"></textarea>
		          	</div>
		          	<div class="comment_btn">
		            	<button type="button">답글등록</button>
		          	</div>
				</div>
			</div>
		    <div class="buttons_wrap">
		    	<div class="left_group">
		        	<button type="button" class="btn1" onclick="javascript: onPageMove('Board');">목록</button>
		        </div>
		        <div class="right_group">
		
		        </div>
			</div>
		</div>
	</div>
	
			<!-- Chart.js -->
<!-- 			<div style="width: 300px; height: 300px;"> -->
<!-- 				<canvas id="myChart1" width="300" height="300"></canvas> -->
<!-- 			</div> -->
			<script type="text/javascript">
			var scoreList = JSON.parse('${score}');
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
			<script type="text/javascript">
			var scoreList = JSON.parse('${score}');
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
			<!-- Chart.js End -->
			<script type="text/javascript">
			var scoreList = JSON.parse('${score}');
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
			<!-- Chart.js End -->
<!--     <div class="uk-container uk-margin-small-top"> -->
<!--       <div class="uk-width-1-1 m-border-t uk-margin-top"> -->
<!--         <h5 class="uk-margin-top"><b>이전글</b>&nbsp; &nbsp;이것이 원래 기사 제목이로다</h5> -->
<!--         <h5 class="uk-margin-remove-top"><b>다음글</b>&nbsp; &nbsp;이것이 원래 기사 제목이로다</h5> -->
<!--       </div> -->
<!--       <div class="m-border-t "> -->
<!--         <ul class="uk-list uk-link-text uk-margin-small-top"> -->
<!--           <li><a href="#">[12/21개강]스케치와 프로토파이로 UX/UI 실무끝내기-프스투파 [3기]</a></li> -->
<!--           <li><a href="#">퍼안감 통굽 여성로퍼 (3cm)</a></li> -->
<!--           <li><a href="#">'4+1' 합의 난항에 본회의 불투명…與 "선거법 더 조정 않겠다"</a></li> -->
<!--           <li><a href="#">'유재수 비위' 靑반박에 檢 "사실관계 모르면서 일방적 주장"</a></li> -->
<!--           <li><a href="#">'유재수 비위' 靑반박에 檢 "사실관계 모르면서 일방적 주장"</a></li> -->
<!--           <li><a href="#">인천 1호선 선로서 연기…전동차 운행 1시간여 중단</a></li> -->
<!--           <li><a href="#">'유재수 비위' 靑반박에 檢 "사실관계 모르면서 일방적 주장"</a></li> -->
<!--           <li><a href="#">[12/21개강]스케치와 프로토파이로 UX/UI 실무끝내기-프스투파 [3기]</a></li> -->
<!--           <li><a href="#">퍼안감 통굽 여성로퍼 (3cm)</a></li> -->
<!--           <li><a href="#">'4+1' 합의 난항에 본회의 불투명…與 "선거법 더 조정 않겠다"</a></li> -->
<!--           <li><a href="#">'유재수 비위' 靑반박에 檢 "사실관계 모르면서 일방적 주장"</a></li> -->
<!--           <li><a href="#">'유재수 비위' 靑반박에 檢 "사실관계 모르면서 일방적 주장"</a></li> -->
<!--           <li><a href="#">인천 1호선 선로서 연기…전동차 운행 1시간여 중단</a></li> -->
<!--           <li><a href="#">'유재수 비위' 靑반박에 檢 "사실관계 모르면서 일방적 주장"</a></li> -->
<!--         </ul> -->
<!--       </div> -->
<!--       <div class="uk-container" uk-grid> -->
<!--         <div class="uk-align-center uk-margin-small-top"> -->
<!--           <ul class="uk-pagination"> -->
<!--             <li><a href="#"><span uk-pagination-previous></span></a></li> -->
<!--             <li><a href="#">1</a></li> -->
<!--             <li class="uk-disabled"><span>...</span></li> -->
<!--             <li><a href="#">4</a></li> -->
<!--             <li><a href="#">5</a></li> -->
<!--             <li><a href="#">6</a></li> -->
<!--             <li class="uk-active"><span>7</span></li> -->
<!--             <li><a href="#">8</a></li> -->
<!--             <li><a href="#">9</a></li> -->
<!--             <li><a href="#">10</a></li> -->
<!--             <li class="uk-disabled"><span>...</span></li> -->
<!--             <li><a href="#">20</a></li> -->
<!--             <li><a href="#"><span uk-pagination-next></span></a></li> -->
<!--           </ul> -->
<!--         </div> -->
<!--         <div> -->
<!--           <button class="uk-button uk-button-primary uk-align-center">제보하기</button> -->
<!--         </div> -->
<!--       </div> -->
<!--     </div> -->
	<!-- Google Charts -->
<!-- 	<div id="myPieChart"></div> -->
	<!-- Google Charts End -->
<!-- 	<input type="button" value="성향등록" id="dialogOpenBtn" /> -->

	</section>
	<footer>
		<jsp:include page="/WEB-INF/views/common/footer.jsp" />	    
  	</footer>
</body>
</html>
