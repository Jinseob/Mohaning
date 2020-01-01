<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!doctype html>
<html>
<head>
	<title>기사</title>
	<jsp:include page="/WEB-INF/views/common/taglib.jsp" />
	<script type="text/javascript">
	
	</script>
</head>
<body>
	<!-- Header -->
	<header>
		<jsp:include page="/WEB-INF/views/common/header.jsp" />
	</header>
	<h1>Error Page</h1>

	<div class="uk-section">
  	<div class="uk-container">
    	<fieldset class="uk-fieldset">
        <div class="uk-grid-small" uk-grid>
        	<c:if test="${requestScope['javax.servlet.error.status_code'] == 400 }" >
        		<p>잘못된 요청입니다.</p>
        	</c:if>
        	<c:if test="${requestScope['javax.servlet.error.status_code'] == 404 }" >
        		<p>요청하신 페이지를 찾을 수 없습니다.</p>
        	</c:if>
        	<c:if test="${requestScope['javax.servlet.error.status_code'] == 405 }" >
        		<p>요청된 메소드가 허용되지 않습니다.</p>
        	</c:if>
        	<c:if test="${requestScope['javax.servlet.error.status_code'] == 500 }" >
        		<p>서버에 오류가 발생하여 요청을 수행할 수 없습니다.</p>
        	</c:if>
        	<c:if test="${requestScope['javax.servlet.error.status_code'] == 503 }" >
        		<p>서비스를 사용할 수 없습니다.</p>
        	</c:if>
            <a href="/" >홈으로</a>
        </div>
      	</fieldset>
  	</div>
  	</div>
</body>
</html>
