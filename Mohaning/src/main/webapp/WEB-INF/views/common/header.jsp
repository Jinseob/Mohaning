<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- <script src="/resources/uikit-3.2.2/js/uikit.min.js"></script> -->
<!-- <script src="/resources/uikit-3.2.2/js/uikit-icons.min.js"></script> -->
<!-- <link rel="stylesheet" href="/resources/uikit-3.2.2/css/uikit.min.css" /> -->
<link rel="stylesheet" href="/resources/css/reset.css" />
<link rel="stylesheet" href="/resources/css/style.css" />
<div class="container">
	<div class="topnav">
		<!-- Left-aligned links (default) -->
    	<div class="topnav-left">
      		<a href="/">모하닝</a>
    	</div>

	    <!-- Centered link -->
	    <c:set var="path" value="${requestScope['javax.servlet.forward.servlet_path']}" />
	    <div class="topnav-centered">
	    	<a href="/Board/main.do" class=<c:if test="${fn:indexOf(path, 'Board') > -1 }">active</c:if>>토론방</a>
	        <a href="/News/main.do" class=<c:if test="${fn:indexOf(path, 'News') > -1 }">active</c:if>>기사</a>
	        <a href="/Author/main.do" class=<c:if test="${fn:indexOf(path, 'Author') > -1 }">active</c:if>>기자</a>
	        <a href="/Media/main.do" class=<c:if test="${fn:indexOf(path, 'Media') > -1 }">active</c:if>>언론사</a>
	    </div>
	
	    <!-- Right-aligned links -->
	    <div class="topnav-right">
    	<c:choose>
    	<c:when test="${userNick ne null}">
			<a href="#">${userNick}</a>
			<a href="/logout.do">로그아웃</a>
		</c:when>
		<c:otherwise>
			<a href="/signIn.do">로그인</a>
      		<a href="/signUp.do">회원가입</a>
		</c:otherwise>
		</c:choose>
	    </div>
  	</div>
</div>