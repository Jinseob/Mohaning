<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="/resources/uikit-3.2.2/css/uikit.min.css" />
<link rel="stylesheet" href="/resources/css/style.css" />
<script src="/resources/uikit-3.2.2/js/uikit.min.js"></script>
<script src="/resources/uikit-3.2.2/js/uikit-icons.min.js"></script>
<nav class="uk-navbar-container uk-margin" uk-navbar>
	<div class="uk-navbar-left">
		<a class="uk-navbar-item uk-logo" href="/">모하닝</a>
	</div>
	<div class="uk-navbar-center">
	    <div>
	        <ul class="uk-navbar-nav">
	            <li class="uk-active"><a href="/Board.do">기사</a></li>
	            <li>
	                <a href="/Media.do">언론사</a>
<!-- 	                <div class="uk-navbar-dropdown"> -->
<!-- 	                    <ul class="uk-nav uk-navbar-dropdown-nav"> -->
<!-- 	                        <li class="uk-active"><a href="#">Active</a></li> -->
<!-- 	                        <li><a href="#">Item</a></li> -->
<!-- 	                        <li><a href="#">Item</a></li> -->
<!-- 	                    </ul> -->
<!-- 	                </div> -->
	            </li>
	            <li class="uk-active"><a href="/Author.do">기자</a></li>
	        </ul>
	    </div>
	</div>
	<div class="uk-navbar-right">
	    <div>
			<ul class="uk-navbar-nav">
	    	<c:choose>
				<c:when test="${userNick ne null}">
					<li><a href="/signIn.do">${userNick}</a></li>
					<li><a href="/logout.do">로그아웃</a></li>
				</c:when>
				<c:otherwise>
					<li><a href="/signIn.do">Login</a></li>
		        	<li><a href="/signUp.do">Join</a></li>
				</c:otherwise>
			</c:choose>
	      	</ul>
	    	
	    </div>
	</div>
</nav>