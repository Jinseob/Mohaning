<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- <div class="dropdown"> -->
<!-- 	<h6 style="display:inline-block;">인기검색어</h6> -->
<!-- 	<a class="hot-keyword"><b>1</b> 코로나19</a> -->
<!-- 	<button class="dropbtn">Q</button> -->
<!-- 	<div class="dropdown-content"> -->
<!--     	<a href="#">Link 1</a> -->
<!--     	<a href="#">Link 2</a> -->
<!--     	<a href="#">Link 3</a> -->
<!-- 	</div> -->
<!-- </div> -->
<script type="text/javascript">
$(function(){
	$("#search-btn").click(function(){
		var searchVal = $("#searchVal").val();
		var path = location.pathname;
		if(path == "/")path += "Search.do";
		location.href = path + "?val=" + searchVal;
	});
});
</script>
<div class="search-container">
	<input type="text" placeholder="코로나19" name="searchVal" id="searchVal">
	<button type="button" class="search-btn" id="search-btn"></button>
</div>