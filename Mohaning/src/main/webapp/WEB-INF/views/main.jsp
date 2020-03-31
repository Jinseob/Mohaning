<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en" dir="ltr">

<head>
  <meta charset="utf-8">
  <title></title>
<!--   <link rel="stylesheet" href="reset.css"> -->
<!--   <link rel="stylesheet" href="style.css"> -->
</head>

<body>
	<header id="header">
		<jsp:include page="/WEB-INF/views/common/header.jsp" />
  	</header>
  	<section>
  	<!-- Search -->
	<div class="container">
    	<div class="row">
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
		        	<form action="/action_page.php">
		            	<input type="text" placeholder="코로나19" name="search">
		            	<button type="submit">search</button>
		          	</form>
        		</div>
      		</div>
    	</div>
  	</div>
	
	<!-- Main Top -->
	<div class="container">
		<div class="tab-wrapper">
	    	<div class="tab">
		        <button class="tablinks active" onclick="openCity(event, 'London')">London</button>
		        <button class="tablinks" onclick="openCity(event, 'Paris')">Paris</button>
		        <button class="tablinks" onclick="openCity(event, 'Seoul')">Seoul</button>
		        <button class="tablinks" onclick="openCity(event, 'Seoul')">기사이름</button>
		        <button class="tablinks" onclick="openCity(event, 'Seoul')">기사기사제목</button>
		        <button class="tablinks" onclick="openCity(event, 'Seoul')">가나다라마바auto</button>
		    </div>
	      	<div id="tabcontent" class="tabcontent">
		        <div class="tabcontent-title">
		        	<h4>Paris</h4>
		        </div>
	        	<div class="tabcontent-wrap">
		          	<div class="tab-list-wrap">
			            <div class="contents_type">
			            	<h5>기자 분석</h5>
			            </div>
	            		<ul class="tab-list">
	              			<li class="news-list">
	                			<p class="press-name">국민일보</p>
	                			<p class="news-title">신천지 에브리웨어 싯파샛키들아!!!!!</p>
	              			</li>
	              			<li class="news-list">
	                			<p class="press-name">좃선일보</p>
	                			<p class="news-title">대구경북지역 신천지 때문에 초전박살났다.대구경북지역 신천지 때문에 초전박살났다.대구경북지역 신천지 때문에 초전박살났다.</p>
	              			</li>
	              			<li class="news-list">
	                			<p class="press-name">오마이뉴읏스</p>
	                			<p class="news-title">우리회사도 재택근무로 전면 바꿔줘라!</p>
	              			</li>
	              			<li class="news-list">
	                			<p class="press-name">중앙일보</p>
	                			<p class="news-title">코로나 종식돼야 운동도하고 쇼핑도하지 놀러가고 싶따고요</p>
			              	</li>
			              	<li class="news-list">
	                			<p class="press-name">연합뉴스TV</p>
				                <p class="news-title">돈많이많이 벌고시포요 막 이것저것 지르고 시포요!</p>
	              			</li>
	            		</ul>
	          		</div>
	          		<div class="spacer-vertical" ></div>
	          		<div class="tab-list-wrap">
			            <div class="contents_type">
			            	<h5>기자 분석</h5>
			            </div>
	            		<ul class="tab-list">
	              			<li class="news-list">
	                			<p class="press-name">국민일보</p>
	                			<p class="news-title">신천지 에브리웨어 싯파샛키들아!!!!!</p>
	              			</li>
	              			<li class="news-list">
	                			<p class="press-name">좃선일보</p>
	                			<p class="news-title">대구경북지역 신천지 때문에 초전박살났다.대구경북지역 신천지 때문에 초전박살났다.대구경북지역 신천지 때문에 초전박살났다.</p>
	              			</li>
	              			<li class="news-list">
	                			<p class="press-name">오마이뉴읏스</p>
	                			<p class="news-title">우리회사도 재택근무로 전면 바꿔줘라!</p>
	              			</li>
	              			<li class="news-list">
	                			<p class="press-name">중앙일보</p>
	                			<p class="news-title">코로나 종식돼야 운동도하고 쇼핑도하지 놀러가고 싶따고요</p>
			              	</li>
			              	<li class="news-list">
	                			<p class="press-name">연합뉴스TV</p>
				                <p class="news-title">돈많이많이 벌고시포요 막 이것저것 지르고 시포요!</p>
	              			</li>
	            		</ul>
	          		</div>
	          		<div class="spacer-vertical" ></div>
	          		<div class="tab-list-wrap">
			            <div class="contents_type">
			            	<h5>기자 분석</h5>
			            </div>
	            		<ul class="tab-list">
	              			<li class="news-list">
	                			<p class="press-name">국민일보</p>
	                			<p class="news-title">신천지 에브리웨어 싯파샛키들아!!!!!</p>
	              			</li>
	              			<li class="news-list">
	                			<p class="press-name">좃선일보</p>
	                			<p class="news-title">대구경북지역 신천지 때문에 초전박살났다.대구경북지역 신천지 때문에 초전박살났다.대구경북지역 신천지 때문에 초전박살났다.</p>
	              			</li>
	              			<li class="news-list">
	                			<p class="press-name">오마이뉴읏스</p>
	                			<p class="news-title">우리회사도 재택근무로 전면 바꿔줘라!</p>
	              			</li>
	              			<li class="news-list">
	                			<p class="press-name">중앙일보</p>
	                			<p class="news-title">코로나 종식돼야 운동도하고 쇼핑도하지 놀러가고 싶따고요</p>
			              	</li>
			              	<li class="news-list">
	                			<p class="press-name">연합뉴스TV</p>
				                <p class="news-title">돈많이많이 벌고시포요 막 이것저것 지르고 시포요!</p>
	              			</li>
	            		</ul>
	          		</div>
	        	</div>
	      	</div>
		</div>
	</div>

	<!-- 기자 화면 -->
	<div class="container mt-20">
	    <div class="contents_type">
	    	<h5>기자 분석</h5>
	    </div>
	    <div class="chart_wrap">
	    	<div class="chart_area">
	        	<span>기자명</span>
	        	<div class="chart"></div>
	      	</div>
	      	<div class="chart_area">
	        	<span>기자명</span>
	        	<div class="chart"></div>
	      	</div>
	      	<div class="chart_area">
	        	<span>기자명</span>
	        	<div class="chart"></div>
	      	</div>
	    </div>
	</div>
  
  	<!-- 언론사 화면 -->
  	<div class="container mt-20">
	    <div class="contents_type">
	    	<h5>언론사 분석</h5>
	    </div>
	    <div class="media_wrap">
	    	<div class="chart_area">
	        	<span>땡땡일보</span>
	        	<div class="chart"></div>
	      	</div>
	      	<div class="media_area">
	        	<div class="media_list">
	          		<div class="media_row">
			            <a href="#" >모한일보</a>
			            <a href="#" >국가일보</a>
			            <a href="#" >신문일자</a>
			            <a href="#" >ABC</a>
			            <a href="#" >EFG</a>
			            <a href="#" >EF News</a>
	          		</div>
	          		<div class="media_row">
			           	<a href="#" >모한일보</a>
			            <a href="#" >국가일보</a>
			            <a href="#" >신문일자</a>
			            <a href="#" >ABC</a>
			            <a href="#" >EFG</a>
			            <a href="#" >EF News</a>
	          		</div>
	          		<div class="media_row">
			            <a href="#" >모한일보</a>
			            <a href="#" >국가일보</a>
			            <a href="#" >신문일자</a>
			            <a href="#" >ABC</a>
			            <a href="#" >EFG</a>
			            <a href="#" >EF News</a>
	          		</div>
	          		<div class="media_row">
			            <a href="#" >모한일보</a>
			            <a href="#" >국가일보</a>
			            <a href="#" >신문일자</a>
			            <a href="#" >ABC</a>
			            <a href="#" >EFG</a>
			            <a href="#" >EF News</a>
	          		</div>
	          		<div class="media_row">
			            <a href="#" >모한일보</a>
			            <a href="#" >국가일보</a>
			            <a href="#" >신문일자</a>
			            <a href="#" >ABC</a>
			            <a href="#" >EFG</a>
			            <a href="#" >EF News</a>
	          		</div>
	          		<div class="media_row">
			            <a href="#" >모한일보</a>
			            <a href="#" >국가일보</a>
			            <a href="#" >신문일자</a>
			            <a href="#" >ABC</a>
			            <a href="#" >EFG</a>
			            <a href="#" >EF News</a>
	          		</div>
	          		<div class="media_row">
			            <a href="#" >모한일보</a>
			            <a href="#" >국가일보</a>
			            <a href="#" >신문일자</a>
			            <a href="#" >ABC</a>
			            <a href="#" >EFG</a>
			            <a href="#" >EF News</a>
	          		</div>
	        	</div>
	      	</div>
		</div>
	</div>
  
  	<!-- 기사 화면 -->
  	<div class="container mt-20">
    	<div class="main-list-wrap">
      		<div class="main-news-list">
        		<div class="contents_type">
          			<h5>평가 많은 기사</h5>
        		</div>
        		<ul class="popular-news-list">
		        	<li class="news-list-title"><label>298</label><a>어제 기획재정부와 국토교통부, 금융위원회가 ‘최근 부동산 시장상황 점검 결과 정부는 일단 각종 규제에도 불구하고 부동산 가격이 다시 불안해지고 있다고</a></li>
		          	<li class="news-list-title"><label>109</label><a>어제 기획재정부와 국토교통부, 금융위원회가 ‘최근 부동산 시장상황 점검 결과 정부는 일단 각종 규제에도 불구하고 부동산 가격이 다시 불안해지고 있다고</a></li>
		          	<li class="news-list-title"><label>98</label><a>어제 기획재정부와 국토교통부, 금융위원회가 ‘최근 부동산 시장상황 점검 결과 정부는 일단 각종 규제에도 불구하고 부동산 가격이 다시 불안해지고 있다고</a></li>
		          	<li class="news-list-title"><label>45</label><a>어제 기획재정부와 국토교통부, 금융위원회가 ‘최근 부동산 시장상황 점검 결과 정부는 일단 각종 규제에도 불구하고 부동산 가격이 다시 불안해지고 있다고</a></li>
		          	<li class="news-list-title"><label>32</label><a>어제 기획재정부와 국토교통부, 금융위원회가 ‘최근 부동산 시장상황 점검 결과 정부는 일단 각종 규제에도 불구하고 부동산 가격이 다시 불안해지고 있다고</a></li>
		          	<li class="news-list-title"><label>14</label><a>어제 기획재정부와 국토교통부, 금융위원회가 ‘최근 부동산 시장상황 점검 결과 정부는 일단 각종 규제에도 불구하고 부동산 가격이 다시 불안해지고 있다고</a></li>
		          	<li class="news-list-title"><label>1</label><a>어제 기획재정부와 국토교통부, 금융위원회가 ‘최근 부동산 시장상황 점검 결과 정부는 일단 각종 규제에도 불구하고 부동산 가격이 다시 불안해지고 있다고</a></li>
        		</ul>
      		</div>
      		<div class="spacer-vertical"></div>
      		<div class="main-news-list">
        		<div class="contents_type">
          			<h5>최신 기사</h5>
        		</div>
        		<ul class="popular-news-list">
          			<li class="news-list-title"><label>298</label><a>어제 기획재정부와 국토교통부, 금융위원회가 ‘최근 부동산 시장상황 점검 결과 정부는 일단 각종 규제에도 불구하고 부동산 가격이 다시 불안해지고 있다고</a></li>
          			<li class="news-list-title"><label>109</label><a>어제 기획재정부와 국토교통부, 금융위원회가 ‘최근 부동산 시장상황 점검 결과 정부는 일단 각종 규제에도 불구하고 부동산 가격이 다시 불안해지고 있다고</a></li>
          			<li class="news-list-title"><label>98</label><a>어제 기획재정부와 국토교통부, 금융위원회가 ‘최근 부동산 시장상황 점검 결과 정부는 일단 각종 규제에도 불구하고 부동산 가격이 다시 불안해지고 있다고</a></li>
          			<li class="news-list-title"><label>45</label><a>어제 기획재정부와 국토교통부, 금융위원회가 ‘최근 부동산 시장상황 점검 결과 정부는 일단 각종 규제에도 불구하고 부동산 가격이 다시 불안해지고 있다고</a></li>
          			<li class="news-list-title"><label>32</label><a>어제 기획재정부와 국토교통부, 금융위원회가 ‘최근 부동산 시장상황 점검 결과 정부는 일단 각종 규제에도 불구하고 부동산 가격이 다시 불안해지고 있다고</a></li>
          			<li class="news-list-title"><label>14</label><a>어제 기획재정부와 국토교통부, 금융위원회가 ‘최근 부동산 시장상황 점검 결과 정부는 일단 각종 규제에도 불구하고 부동산 가격이 다시 불안해지고 있다고</a></li>
          			<li class="news-list-title"><label>1</label><a>어제 기획재정부와 국토교통부, 금융위원회가 ‘최근 부동산 시장상황 점검 결과 정부는 일단 각종 규제에도 불구하고 부동산 가격이 다시 불안해지고 있다고</a></li>
        		</ul>
      		</div>
    	</div>
  	</div>
	</section>
	
  	<script type="text/javascript">
    function openCity(evt, cityName) {
      // Declare all variables
      var i, tabcontent, tablinks;

      // Get all elements with class="tabcontent" and hide them
//       tabcontent = document.getElementsByClassName("tabcontent");
//       for (i = 0; i < tabcontent.length; i++) {
//         tabcontent[i].style.display = "none";
//       }

      // Get all elements with class="tablinks" and remove the class "active"
      tablinks = document.getElementsByClassName("tablinks");
      for (i = 0; i < tablinks.length; i++) {
        tablinks[i].className = tablinks[i].className.replace(" active", "");
      }

      // Show the current tab, and add an "active" class to the link that opened the tab
//       document.getElementById(cityName).style.display = "block";
//       if(evt){
        evt.currentTarget.className += " active";
//       }
    }
//     window.onload = function(){
//     	openCity(null, "London");
//     }
  	</script>
  	<footer>
		<jsp:include page="/WEB-INF/views/common/footer.jsp" />	    
  	</footer>
</body>

</html>

