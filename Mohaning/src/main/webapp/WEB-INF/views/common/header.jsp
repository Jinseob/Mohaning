<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	    <div class="topnav-centered">
	    	<a href="/Board.do" class="active">토론방</a>
	        <a href="/News.do" class="">기사</a>
	        <a href="/Author.do" class="">기자</a>
	        <a href="/Media.do" class="">언론사</a>
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