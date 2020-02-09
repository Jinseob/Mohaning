<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en" dir="ltr">
  <head>
    <title>모하닝</title>
    <jsp:directive.include file="/WEB-INF/views/common/taglib.jsp" />
  </head>
  <body>
	<!-- Header -->
	<header>
		<jsp:include page="/WEB-INF/views/common/header.jsp" />
	</header>
<!-- 슬라이더 -->
  <div class="uk-section">
    <div class="uk-container">
      <div uk-grid>
          <div class="uk-width-1-5@m">

              <ul class="uk-subnav uk-subnav-pill subnav" uk-switcher="connect: #component-nav; animation: uk-animation-fade">
                  <li><a href="#">분양가 상한제</a></li>
                  <li><a href="#">검찰개혁</a></li>
                  <li><a href="#">조국 조국 조국</a></li>
                  <li><a href="#">돼지 열병</a></li>
                  <li><a href="#">디플레이션</a></li>
                  <li><a href="#">올림픽 불매</a></li>
                  <li><a href="#">No Japan</a></li>
              </ul>

          </div>
          <div class="uk-width-4-5@m">

              <ul id="component-nav" class="uk-switcher">
                  <li>
                    <div class="uk-container">
                      <div class="uk-grid-match uk-child-width-1-3@m" uk-grid>
                        <div>
                          <h5>TOP 기사</h5>
                          <ul class="uk-list">
                            <li><p class="uk-text-meta"><span class="subFontList">연합뉴스 | 2019.10.10</span></p><a class="uk-link-text" href="#"><span class="smallFontList">이중 그물망에도 인양 중 '실종자 유실'…해경 "인근에 있을 것"</span></a></li>
                            <li><p class="uk-text-meta"><span class="subFontList">연합뉴스 | 2019.10.10</span></p><a class="uk-link-text" href="#"><span class="smallFontList">해경 "추락헬기 인양 완료…내부수색했으나 실종자 발견 못 해"</span></a></li>
                            <li><p class="uk-text-meta"><span class="subFontList">연합뉴스 | 2019.10.10</span></p><a class="uk-link-text" href="#"><span class="smallFontList">독도해역서 수습한 시신 동산병원에 안치…'침울'</span></a></li>
                          </ul>
                        </div>
                        <div>
                          <h5><span uk-icon="arrow-up"></span>긍정</h5>
                          <ul class="uk-list">
                            <li><p class="uk-text-meta"><span class="subFontList">연합뉴스 | 2019.10.10</span></p><a class="uk-link-text" href="#"><span class="smallFontList">이중 그물망에도 인양 중 '실종자 유실'…해경 "인근에 있을 것"</span></a></li>
                            <li><p class="uk-text-meta"><span class="subFontList">연합뉴스 | 2019.10.10</span></p><a class="uk-link-text" href="#"><span class="smallFontList">해경 "추락헬기 인양 완료…내부수색했으나 실종자 발견 못 해"</span></a></li>
                            <li><p class="uk-text-meta"><span class="subFontList">연합뉴스 | 2019.10.10</span></p><a class="uk-link-text" href="#"><span class="smallFontList">독도해역서 수습한 시신 동산병원에 안치…'침울'</span></a></li>
                          </ul>
                        </div>
                        <div>
                          <h5><span uk-icon="arrow-down"></span>부정</h5>
                          <ul class="uk-list">
                            <li><p class="uk-text-meta"><span class="subFontList">연합뉴스 | 2019.10.10</span></p><a class="uk-link-text" href="#"><span class="smallFontList">이중 그물망에도 인양 중 '실종자 유실'…해경 "인근에 있을 것"</span></a></li>
                            <li><p class="uk-text-meta"><span class="subFontList">연합뉴스 | 2019.10.10</span></p><a class="uk-link-text" href="#"><span class="smallFontList">해경 "추락헬기 인양 완료…내부수색했으나 실종자 발견 못 해"</span></a></li>
                            <li><p class="uk-text-meta"><span class="subFontList">연합뉴스 | 2019.10.10</span></p><a class="uk-link-text" href="#"><span class="smallFontList">독도해역서 수습한 시신 동산병원에 안치…'침울'</span></a></li>
                          </ul>
                        </div>
                      </div>
                    </div>
                  </li>
                  <li>Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</li>
                  <li>Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur, sed do eiusmod.</li>
              </ul>

          </div>
      </div>
    </div>
  </div>
  <!-- 기자 리스트 -->
  	<!-- Chart.js -->
	<script src="/resources/js/Chart.bundle.js"></script>
	<!-- Chart.js End -->
    <div class="uk-section">
      <div class="uk-container">
        <h3>이슈 기자</h3>
        <div class="uk-grid-match uk-child-width-1-3@m" uk-grid>
            <div>
              <div class="uk-width-1-2">
				<canvas id="myChart1" width="100%" height="100%"></canvas>
				<script type="text/javascript">
// 				var scoreList = JSON.parse('${result.scoreList}');
				var ctx = document.getElementById('myChart1').getContext('2d');
					
// 				var labels = new Array();
// 				var data = new Array();
// 				for(var i = 0; i < scoreList.length; i++){
// 					var item = scoreList[i];
// 					labels.push(item.type_nm);
// 					data.push(item.score);
// 				}
				var myChart = new Chart(ctx, {
				    type: 'radar',
				    data: {
				        labels: ['Running', 'Swimming', 'Eating', 'Cycling', 'A', 'B'],
				        datasets: [{
				        	label: '성향1',
				            data: [20, 10, 4, 2, 30, 23],
				            borderColor : 'rgba(200, 0, 0, 0.1)',
				            backgroundColor: 'rgba(200, 0, 0, 0.2)'
				        },
				        {
				        	label: '성향2',
				            data: [30, 20, 14, 12, 40, 33],
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
              </div>
              <div class="uk-width-1-2">
                <p>기자 이름</p>
                <p>언론사</p>
                <p>메일 주소</p>
              </div>
            </div>
            <div>
              <div class="uk-width-1-2">
                <canvas id="myChart2" width="100%" height="100%"></canvas>
				<script type="text/javascript">
// 				var scoreList = JSON.parse('${result.scoreList}');
				var ctx = document.getElementById('myChart2').getContext('2d');
					
// 				var labels = new Array();
// 				var data = new Array();
// 				for(var i = 0; i < scoreList.length; i++){
// 					var item = scoreList[i];
// 					labels.push(item.type_nm);
// 					data.push(item.score);
// 				}
				var myChart = new Chart(ctx, {
				    type: 'radar',
				    data: {
				        labels: ['Running', 'Swimming', 'Eating', 'Cycling', 'A', 'B'],
				        datasets: [{
				        	label: '성향1',
				            data: [20, 10, 4, 2, 30, 23],
				            borderColor : 'rgba(200, 0, 0, 0.1)',
				            backgroundColor: 'rgba(200, 0, 0, 0.2)'
				        },
				        {
				        	label: '성향2',
				            data: [30, 20, 14, 12, 40, 33],
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
              </div>
              <div class="uk-width-1-2">
	            <p>기자 이름</p>
	            <p>언론사</p>
	          	<p>메일 주소</p>
	          </div>
            </div>
            <div>
              <div class="uk-width-1-2">
                <canvas id="myChart3" width="100%" height="100%"></canvas>
				<script type="text/javascript">
// 				var scoreList = JSON.parse('${result.scoreList}');
				var ctx = document.getElementById('myChart3').getContext('2d');
					
// 				var labels = new Array();
// 				var data = new Array();
// 				for(var i = 0; i < scoreList.length; i++){
// 					var item = scoreList[i];
// 					labels.push(item.type_nm);
// 					data.push(item.score);
// 				}
				var myChart = new Chart(ctx, {
				    type: 'radar',
				    data: {
				        labels: ['Running', 'Swimming', 'Eating', 'Cycling', 'A', 'B'],
				        datasets: [{
				        	label: '성향1',
				            data: [20, 10, 4, 2, 30, 23],
				            borderColor : 'rgba(200, 0, 0, 0.1)',
				            backgroundColor: 'rgba(200, 0, 0, 0.2)'
				        },
				        {
				        	label: '성향2',
				            data: [30, 20, 14, 12, 40, 33],
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
              </div>
              <div class="uk-width-1-2">
                <p>기자 이름</p>
                <p>언론사</p>
                <p>메일 주소</p>
              </div>
            </div>
        </div>
      </div>
    </div>
<!-- 언론사 -->
    <div class="uk-section">
      <div class="uk-container">

        <h3>언론사 분석</h3>

        <div class="uk-grid-match uk-grid-small" uk-grid>

          <div class="uk-width-expand@m uk-flex-last@m">
            <ul class="uk-subnav uk-subnav-pill subnav-small " uk-tab="connect: #component-tab-right; animation: uk-animation-fade" uk-switcher>
                <li><a href="#">MBC</a></li>
                <li><a href="#">SBS</a></li>
                <li><a href="#">KBS</a></li>
                <li><a href="#">EBS</a></li>
                <li><a href="#">조선일보</a></li>
                <li><a href="#">중앙일보</a></li>
                <li><a href="#">동아일보</a></li>
                <li><a href="#">국민일보</a></li>
                <li><a href="#">JTBC</a></li>
                <li><a href="#">YTN</a></li>
                <li><a href="#">한겨레</a></li>
                <li><a href="#">MBC</a></li>
                <li><a href="#">SBS</a></li>
                <li><a href="#">KBS</a></li>
                <li><a href="#">EBS</a></li>
                <li><a href="#">조선일보</a></li>
                <li><a href="#">중앙일보</a></li>
                <li><a href="#">동아일보</a></li>
                <li><a href="#">국민일보</a></li>
                <li><a href="#">JTBC</a></li>
            </ul>
          </div>

          <div class="uk-width-1-3@m">
            <ul id="component-tab-right" class="uk-switcher uk-margin">
                <li>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</li>
                <li>Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</li>
                <li>Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur, sed do eiusmod.</li>
            </ul>
          </div>

        </div>
      </div>
    </div>

<!-- 평가많은 기사 -->
    <div class="uk-section">
        <div class="uk-container">
            <div class="uk-grid-match uk-child-width-1-2@m" uk-grid>
                <div>
                  <h3>평가많은 기사</h3>
                  <ul class="uk-list">
                    <li><p class="uk-text-meta">연합뉴스 | 2019.10.10</p><a class="uk-link-text" href="#">이중 그물망에도 인양 중 '실종자 유실'…해경 "인근에 있을 것"</a></li>
                    <li><p class="uk-text-meta">연합뉴스 | 2019.10.10</p><a class="uk-link-text" href="#">해경 "추락헬기 인양 완료…내부수색했으나 실종자 발견 못 해"</a></li>
                    <li><p class="uk-text-meta">연합뉴스 | 2019.10.10</p><a class="uk-link-text" href="#">독도해역서 수습한 시신 동산병원에 안치…'침울'</a></li>
                  </ul>
                </div>
                <div>
                  <h3>최신 기사</h3>
                  <ul class="uk-list">
                    <li><p class="uk-text-meta">연합뉴스 | 2019.10.10</p><a class="uk-link-text" href="#">이중 그물망에도 인양 중 '실종자 유실'…해경 "인근에 있을 것"</a></li>
                    <li><p class="uk-text-meta">연합뉴스 | 2019.10.10</p><a class="uk-link-text" href="#">해경 "추락헬기 인양 완료…내부수색했으나 실종자 발견 못 해"</a></li>
                    <li><p class="uk-text-meta">연합뉴스 | 2019.10.10</p><a class="uk-link-text" href="#">독도해역서 수습한 시신 동산병원에 안치…'침울'</a></li>
                  </ul>
                </div>
            </div>

        </div>
    </div>
  </body>
</html>

