<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!doctype html>
<html>
<head>
	<title>기사</title>
	<jsp:directive.include file="/WEB-INF/views/common/taglib.jsp" />
	<script type="text/javascript">

	</script>
</head>
<body>
	<!-- Header -->
	<header>
		<jsp:include page="/WEB-INF/views/common/header.jsp" />
	</header>
	<h1>기자 메인</h1>
	<div class="uk-section">
	  	<div class="uk-container">
	    	<div class="uk-align-left">
	        	<div uk-form-custom="target: > * > span:first-child">
		            <select>
		                <option value="">제목</option>
		                <option value="1">제목+내용</option>
		                <option value="2">기사</option>
		            </select>
		            <button class="uk-button uk-button-default" type="button" tabindex="-1">
		                <span></span>
		                <span uk-icon="icon: chevron-down"></span>
		            </button>
	        	</div>
		        <form id="frm" name="frm" class="uk-search uk-search-default ">
		        	<input type="hidden" name="news_id" id="news_id" />
		            <a href="" class="uk-search-icon-flip" uk-search-icon></a>
		            <input class="uk-search-input" type="search" placeholder="Search...">
		        </form>
	      	</div>
	      	<div class="uk-align-right m-border" style="margin-top:-3rem; padding:1rem">
	      		<div class="uk-text-center">홍길동</div>
	        	<div class="uk-margin-small-top">
		        	<button class="uk-button uk-button-default">내 소식</button>
		          	<button class="uk-button uk-button-default">내가 남긴 글</button>
		            <button class="uk-button uk-button-default">내가 남긴 댓글</button>
		        </div>
			</div>
	  	</div>
	  	<div class="uk-container uk-margin-small-top">
		    <div class="" uk-grid>
		      	<ul class="uk-subnav uk-subnav-divider" style="margin-left:20px;" uk-margin>
			        <li class="uk-active"><a href="#">제보일순</a></li>
			        <li><a href="#">작성일순</a></li>
			        <li><a href="#">평가순</a></li>
			        <li><a href="#">조회순</a></li>
		      	</ul>
		      	<div class="uk-navbar-right" uk-form-custom="target: > * > span:first-child">
			        <select>
			        	<option value="">성향별</option>
			            <option value="1">000</option>
			            <option value="2">000</option>
			        </select>
			        <button class="uk-button uk-button-default" type="button" tabindex="-1">
			            <span></span>
			            <span uk-icon="icon: chevron-down"></span>
			        </button>
			    </div>
		    </div>
		    <div class="m-border-t ">
		    	<ul class="uk-list uk-link-text uk-margin-small-top uk-margin-small-left">
		    		<c:choose>
						<c:when test="${fn:length(resultList) > 0 }">
						<c:forEach items="${resultList }" var="result">
							<li>
								<a href="/b010d${result.author_id }.do">${result.author_nm }</a>
								<a href="/c010d${result.media_id }.do"><b>${result.media_nm }</b></a>
							</li>
						</c:forEach>
						</c:when>
						<c:otherwise>
							<li>데이터가 없습니다.</li>
						</c:otherwise>
					</c:choose>
		    	</ul>
		    </div>
		    <div class="uk-container"uk-grid>
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
			    	<button class="uk-button uk-button-primary uk-align-center" type="button" onclick="javascript: onPageMove('I')">제보하기</button>
			    </div>
			</div>
		</div>
	</div>
</body>
</html>
