<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!doctype html>
<html>
<head>
	<title>기사</title>
	<jsp:directive.include file="/WEB-INF/views/common/taglib.jsp" />
	<script type="text/javascript">
	function onPageMove(type){
		var url = "";
		switch(type){
		case "I":
			url = "/n010i.do";
			break;
		case "D":
			url = "/n010d.do";
			break;
		}
		
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
  	
  	<!-- List -->
  	<div class="container">
		<div class="column-wrapper">
      		<article class="column">
      			<div class="contents_type">
          			<h5>토론방 목록</h5>
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
        		<ul class="popular-news-list">
        			<c:choose>
						<c:when test="${fn:length(resultList) > 0 }">
						<c:forEach items="${resultList }" var="result">
							<li class="news-list-title">
								<label>298</label>
								<a href="/n010d${result.news_id }.do">${result.news_title }</a>
							</li>
						</c:forEach>
						</c:when>
						<c:otherwise>
							<li>데이터가 없습니다.</li>
						</c:otherwise>
					</c:choose>
        		</ul>
        		<div class="news-list-bottom">
          			<button class="btn1" type="submit">처음으로</button>
          			<div class="nav-paging-wrap">
            			<div class="nav-paging-area">
              				<a href="#" class="nav-paging-prev">이전</a>
              				<div class="nav-paging">
				                <a href="#" >1</a>
				                <a href="#" >2</a>
				                <a href="#" >3</a>
				                <a href="#" >4</a>
				                <a href="#" >5</a>
				                <a href="#" >6</a>
				                <a href="#" >7</a>
				                <a href="#" >8</a>
				                <a href="#" >9</a>
				                <a href="#" >10</a>
              				</div>
              				<a href="#" class="nav-paging-next">다음</a>
            			</div>
          			</div>
          			<button class="btn1" type="button" onclick="javascript: onPageMove('I');">제보하기</button>
        		</div>
      		</article>
    	</div>
  	</div>
  	</section>
  	
  	<script src="./resources/js/dropDown.js"></script>
  	<footer>
		<jsp:include page="/WEB-INF/views/common/footer.jsp" />	    
  	</footer>
</body>
</html>
