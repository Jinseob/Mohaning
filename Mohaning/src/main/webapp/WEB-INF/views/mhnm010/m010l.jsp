<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!doctype html>
<html>
<head>
	<title>모하닝</title>
	<jsp:directive.include file="/WEB-INF/views/common/taglib.jsp" />
	<script type="text/javascript">
	function onPageMove(type){
		var url = "";
		switch(type){
		case "I":
			url = "/Board/b010i.do";
			break;
		case "M":
			url = "/Board/main.do";
			break;
		}
		
		$("#frm").attr({"action":url, "method":"POST"}).submit();
	}
	$(document).on('change', '#sortType', function(){
		$("#sort").val($("#sortType option:selected").val());
		onPageMove("M");
	});
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
        		<input type="hidden" name="sort" id="sort" />
        		<input type="hidden" name="news_id" id="news_id" />
      		<div class="search-wrapper">
        		<jsp:include page="/WEB-INF/views/common/search.jsp" />
      		</div>
          	</form>
    	</div>
  	</div>
  	
  	<!-- List -->
  	<div class="container">
		<div class="column-wrapper">
      		<article class="column">
      			<div class="contents_type">
          			<h5>관리 기사 목록</h5>
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
        		<ul class="popular-news-list">
        			<c:choose>
						<c:when test="${fn:length(resultList) > 0 }">
						<c:forEach items="${resultList }" var="result">
							<li class="news-list-title">
								<label>${result.news_id }</label>
								<a href="/News/n010d${result.news_id }.do">${result.news_title }</a>
								<span>${result.news_contents }</span>
								<span>${result.doc_id }</span>
								<span>${result.author_nm }</span>
<%-- 								<span>${result.author_id }</span> --%>
<%-- 								<span>${result.author_email }</span> --%>
								<span>${result.media_nm }</span>
								<span>${result.log }</span>
<%-- 								<span>${result.media_id }</span> --%>
								<span>${result.view_cnt }</span>
								<span><c:if test="${result.news_url ne ''}">OA</c:if></span>
								<span>${result.portal_cnt}</span>
							</li>
						</c:forEach>
						</c:when>
						<c:otherwise>
							<li>데이터가 없습니다.</li>
						</c:otherwise>
					</c:choose>
        		</ul>
        		<div class="news-list-bottom">
<!--           			<button class="btn1" type="submit">처음으로</button> -->
          			<div class="nav-paging-wrap">
            			<div class="nav-paging-area">
              				<a href="#" class="nav-paging-prev">이전</a>
              				<div class="nav-paging">
				                <a href="#" class="active">1</a>
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
          			<button class="btn1" type="button" onclick="javascript: onPageMove('I');">수정</button>
          			<button class="btn1" type="button" onclick="javascript: onPageMove('I');">저장</button>
          			<button class="btn1" type="button" onclick="javascript: onPageMove('I');">취소</button>
        		</div>
      		</article>
    	</div>
  	</div>
  	</section>
  	
<!--   	<script src="/resources/js/dropDown.js"></script> -->
  	<footer>
		<jsp:include page="/WEB-INF/views/common/footer.jsp" />	    
  	</footer>
</body>
</html>
