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
	<h1>기사 상세</h1>
	
	<div class="uk-section">
    <div class="uk-container">
      <div class="uk-grid-small" uk-grid>
        <div class="uk-width-1-1">
          <h1 class="m-border-b">
            ${result.title }
          </h1>
          <h4 class="m-border-b"><b>원문제목</b>&nbsp; &nbsp;${result.news_title }</h4>
        </div>
        <div class="uk-width-1-1">
          <h4 class="m-border-b"><b>기사 URL</b><a href="${result.news_url }" target="_blank">&nbsp; &nbsp;${result.news_url }</a></h4>
        </div>
        <div class="uk-width-1-3@s">
          <h4 class="m-border-b"><b>기자</b>&nbsp; &nbsp;${result.author_nm }</h4>
        </div>
        <div class="uk-width-1-3@s">
          <h4 class="m-border-b"><b>이메일</b>&nbsp; &nbsp;${result.author_email }</h4>
        </div>
        <div class="uk-width-1-3@s">
          <h4 class="m-border-b"><b>언론사</b>&nbsp; &nbsp;<a href="${result.media_url }" target="_blank">${result.media_nm }</a></h4>
        </div>
        <div class="uk-width-1-1 uk-margin-large">
          <p>${result.news_contents }</p>
        </div>
        <div class="uk-width-1-1 uk-margin-large">
          <p>${result.contents }</p>
        </div>
        <div class="uk-width-1-1">
          <a>#해쉬태그</a><a>#해쉬태그</a><a>#해쉬태그</a><a>#해쉬태그</a>
        </div>
        <div class="uk-grid-divider uk-child-width-expand@s" uk-grid>
          <div>
          	<h4><b>기사</b></h4>
			<!-- Chart.js -->
			<div style="width: 300px; height: 300px;">
				<canvas id="myChart1" width="300" height="300"></canvas>
			</div>
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
          </div>
          <div>
          	<h4><b>기자</b></h4>
          	<!-- Chart.js -->
			<div style="width: 300px; height: 300px;">
				<canvas id="myChart2" width="300" height="300"></canvas>
			</div>
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
          </div>
          <div>
          	<h4><b>언론사</b></h4>
          	<!-- Chart.js -->
			<div style="width: 300px; height: 300px;">
				<canvas id="myChart3" width="300" height="300"></canvas>
			</div>
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
          </div>
        </div>
        <div class="uk-width-1-1 uk-margin-large-top">
          <h4><b>기사의 매력포인트 선택해주세요!</b></h4>
          <!-- Score Button -->
			<form id="frm" name="frm">
				<input type="hidden" value="${result.news_id }" name="news_id" id="news_id" />
				<input type="hidden" value="${result.author_id }" name="author_id" id="author_id" />
				<input type="hidden" value="${result.media_id }" name="media_id" id="media_id" />
				<input type="hidden" value="${result.origin_media_id }" name="origin_media_id" id="origin_media_id" />
		        <div class="uk-grid-divider uk-child-width-expand@s uk-text-center" uk-grid>
				<c:choose>
					<c:when test="${fn:length(scoreListByUser) > 0 }">
					<c:forEach items="${scoreListByUser }" var="scoreList">
						<div>
							<label>
								<input class="uk-checkbox" 
									   type="checkbox" 
									   name="scoreList" 
									   value="${scoreList.type_cd }" 
									   <c:if test="${scoreList.score eq '1'}">checked</c:if>
								/>	   
								<c:out value="${scoreList.type_nm }" />
							</label>
						</div>
					</c:forEach>
					</c:when>
				</c:choose>
	          	</div>
			</form>
<!--             <div><button class="uk-button uk-button-text">공감</button></div> -->
<!--             <div><button class="uk-button uk-button-text">전문성</button></div> -->
<!--             <div><button class="uk-button uk-button-text">추정</button></div> -->
<!--             <div><button class="uk-button uk-button-text">간접</button></div> -->
<!--             <div><button class="uk-button uk-button-text">발품</button></div> -->
<!--             <div><button class="uk-button uk-button-text">검증</button></div> -->
        </div>
        <div>
          <button class="uk-button uk-button-primary uk-align-center" type="button" id="saveBtn">평가하기</button>
        </div>

        <!-- 댓글 -->
        <div class="uk-width-1-1 m-border-t uk-margin-large-top">
          <div class="uk-margin-top">
            <h4><b>댓글</b></h4>
          </div>
          <ul class="uk-comment-list">
            <li class="uk-margin-small">
              <article class="uk-comment uk-visible-toggle" tabindex="-1">
                <header class="uk-comment-header uk-position-relative">
                  <div class="uk-grid-medium uk-flex-middle" uk-grid>
                    <div class="uk-width-auto">
                      <h5 class="uk-comment-title uk-margin-remove"><a class="uk-link-reset" href="#">Author</a></h5>
                      <p class="uk-comment-meta uk-margin-remove-top"><a class="uk-link-reset" href="#">12 days ago</a></p>
                    </div>
                  </div>
                  <div class="uk-position-top-right uk-position-small uk-hidden-hover"><a class="uk-link-muted" href="#">답글</a><a class="uk-link-muted" href="#">삭제</a></div>
                </header>
                <div class="uk-comment-body">
                  <p>Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd
                    gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.</p>
                </div>
              </article>
              <!-- 대댓글 -->
              <ul class="uk-margin">
                <li class="uk-margin-small">
                  <article class="uk-comment uk-comment-primary uk-visible-toggle" tabindex="-1">
                    <header class="uk-comment-header uk-position-relative">
                      <div class="uk-grid-medium uk-flex-middle" uk-grid>
                        <div class="uk-width-auto">
                          <h5 class="uk-comment-title uk-margin-remove"><a class="uk-link-reset" href="#">Author</a></h5>
                          <p class="uk-comment-meta uk-margin-remove-top"><a class="uk-link-reset" href="#">12 days ago</a></p>
                        </div>
                      </div>
                      <div class="uk-position-top-right uk-position-small uk-hidden-hover"><a class="uk-link-muted" href="#">답글</a></div>
                    </header>
                    <div class="uk-comment-body">
                      <p>Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita
                        kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.</p>
                    </div>
                  </article>
                </li>
                <li class="uk-margin-small">
                  <article class="uk-comment uk-comment-primary uk-visible-toggle" tabindex="-1">
                    <header class="uk-comment-header uk-position-relative">
                      <div class="uk-grid-medium uk-flex-middle" uk-grid>
                        <div class="uk-width-auto">
                          <h5 class="uk-comment-title uk-margin-remove"><a class="uk-link-reset" href="#">Author</a></h5>
                          <p class="uk-comment-meta uk-margin-remove-top"><a class="uk-link-reset" href="#">12 days ago</a></p>
                        </div>
                      </div>
                      <div class="uk-position-top-right uk-position-small uk-hidden-hover"><a class="uk-link-muted" href="#">답글</a></div>
                    </header>
                    <div class="uk-comment-body">
                      <p>Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita
                        kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.</p>
                    </div>
                  </article>
                </li>
              </ul>
            </li>
          </ul>
          <!-- 페이징 -->
          <div uk-grid>
            <div class="uk-align-center uk-margin-small-top">
              <ul class="uk-pagination">
                <li><a href="#"><span uk-pagination-previous></span></a></li>
                <li><a href="#">1</a></li>
                <li class="uk-disabled"><span>...</span></li>
                <li><a href="#">4</a></li>
                <li><a href="#">5</a></li>
                <li><a href="#">6</a></li>
                <li class="uk-active"><span>7</span></li>
                <li><a href="#">8</a></li>
                <li><a href="#">9</a></li>
                <li><a href="#">10</a></li>
                <li class="uk-disabled"><span>...</span></li>
                <li><a href="#">20</a></li>
                <li><a href="#"><span uk-pagination-next></span></a></li>
              </ul>
            </div>
          </div>
          <!-- 댓글달기 -->
          <textarea class="uk-textarea" rows="5" placeholder="Textarea"></textarea>
          <p class="uk-align-right uk-margin-small-top">
            <button type="button" class="uk-button uk-button-primary" onclick="javascript: onRegisterBtn();">댓글달기</button>
          </p>
        </div>

      </div>
    </div>
    <div class="uk-container uk-margin-small-top">
      <div class="uk-width-1-1 m-border-t uk-margin-top">
        <h5 class="uk-margin-top"><b>이전글</b>&nbsp; &nbsp;이것이 원래 기사 제목이로다</h5>
        <h5 class="uk-margin-remove-top"><b>다음글</b>&nbsp; &nbsp;이것이 원래 기사 제목이로다</h5>
      </div>
      <div class="m-border-t ">
        <ul class="uk-list uk-link-text uk-margin-small-top">
          <li><a href="#">[12/21개강]스케치와 프로토파이로 UX/UI 실무끝내기-프스투파 [3기]</a></li>
          <li><a href="#">퍼안감 통굽 여성로퍼 (3cm)</a></li>
          <li><a href="#">'4+1' 합의 난항에 본회의 불투명…與 "선거법 더 조정 않겠다"</a></li>
          <li><a href="#">'유재수 비위' 靑반박에 檢 "사실관계 모르면서 일방적 주장"</a></li>
          <li><a href="#">'유재수 비위' 靑반박에 檢 "사실관계 모르면서 일방적 주장"</a></li>
          <li><a href="#">인천 1호선 선로서 연기…전동차 운행 1시간여 중단</a></li>
          <li><a href="#">'유재수 비위' 靑반박에 檢 "사실관계 모르면서 일방적 주장"</a></li>
          <li><a href="#">[12/21개강]스케치와 프로토파이로 UX/UI 실무끝내기-프스투파 [3기]</a></li>
          <li><a href="#">퍼안감 통굽 여성로퍼 (3cm)</a></li>
          <li><a href="#">'4+1' 합의 난항에 본회의 불투명…與 "선거법 더 조정 않겠다"</a></li>
          <li><a href="#">'유재수 비위' 靑반박에 檢 "사실관계 모르면서 일방적 주장"</a></li>
          <li><a href="#">'유재수 비위' 靑반박에 檢 "사실관계 모르면서 일방적 주장"</a></li>
          <li><a href="#">인천 1호선 선로서 연기…전동차 운행 1시간여 중단</a></li>
          <li><a href="#">'유재수 비위' 靑반박에 檢 "사실관계 모르면서 일방적 주장"</a></li>
        </ul>
      </div>
      <div class="uk-container" uk-grid>
        <div class="uk-align-center uk-margin-small-top">
          <ul class="uk-pagination">
            <li><a href="#"><span uk-pagination-previous></span></a></li>
            <li><a href="#">1</a></li>
            <li class="uk-disabled"><span>...</span></li>
            <li><a href="#">4</a></li>
            <li><a href="#">5</a></li>
            <li><a href="#">6</a></li>
            <li class="uk-active"><span>7</span></li>
            <li><a href="#">8</a></li>
            <li><a href="#">9</a></li>
            <li><a href="#">10</a></li>
            <li class="uk-disabled"><span>...</span></li>
            <li><a href="#">20</a></li>
            <li><a href="#"><span uk-pagination-next></span></a></li>
          </ul>
        </div>
        <div>
          <button class="uk-button uk-button-primary uk-align-center">제보하기</button>
        </div>
      </div>
    </div>
  </div>
	
	
<%-- 	<div>${result.news_title }</div> --%>
<%-- 	<div><a href="${result.news_url }" target="_blank">${result.news_url }</a></div> --%>
<%-- 	<div>${result.view_cnt }</div> --%>
<%-- 	<div>${result.reg_dt }</div> --%>
<%-- 	<div>${result.author_nm }</div> --%>
<%-- 	<div>${result.author_email }</div> --%>
<%-- 	<div>${result.media_nm }</div> --%>
<%-- 	<div>${result.media_url }</div> --%>
	
	<input type="button" value="목록" onclick="javascript: onPageMove('Board');" />
	
	<!-- Google Charts -->
<!-- 	<div id="myPieChart"></div> -->
	<!-- Google Charts End -->
<!-- 	<input type="button" value="성향등록" id="dialogOpenBtn" /> -->
</body>
</html>
