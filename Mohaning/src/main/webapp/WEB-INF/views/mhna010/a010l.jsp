<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!doctype html>
<html>
<head>
	<title>모하닝</title>
	<jsp:directive.include file="/WEB-INF/views/common/taglib.jsp" />
	<script type="text/javascript">

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
	   		<h5>기자 목록</h5>
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
	   	<div class="alist_wrap">
	   	<c:choose>
			<c:when test="${fn:length(resultList) > 0 }">
			<c:forEach items="${resultList }" var="result" varStatus="status">
			<c:if test="${status.count % 9 eq 1 }">
			<div class="alist_row">
			</c:if>
	   			<div class="alist_item">
	   				<div class="top">
	   					<a href="/Author/a010d${result.author_id }.do"><b>${result.author_nm }</b></a>
	   				</div>
	   				<div class="bottom">
						<a href="/Media/c010d${result.media_id }.do">${result.media_nm }</a>	   			
	   				</div>
	   			</div>
	   		<c:if test="${status.count % 9 eq 0 }">
	   		</div>
			</c:if>
	   		</c:forEach>
			</c:when>
			<c:otherwise>
				<div class="alist_empty">
					<span>데이터가 없습니다.</span>
				</div>
			</c:otherwise>
		</c:choose>
	   	</div>
	</div>
	</section>
	
<!-- 	<script src="/resources/js/dropDown.js"></script> -->
  	<footer>
		<jsp:include page="/WEB-INF/views/common/footer.jsp" />	    
  	</footer>
</body>
</html>
