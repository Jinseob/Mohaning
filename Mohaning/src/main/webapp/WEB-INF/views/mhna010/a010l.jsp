<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

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
			url = "/a010i.do";
			break;
		case "D":
			url = "/a010d.do";
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
	<h1>기사 메인</h1>
	<form id="frm" name="frm">
		<input type="hidden" name="news_id" id="news_id" />
	</form>
	<input type="button" value="기사 등록" onclick="javascript: onPageMove('I')" />
	
	<table>
		<colgroup>
			<col width="10%" />
			<col width="10%" />
			<col width="50%" />
			<col width="15%" />
			<col width="15%" />
		</colgroup>
		<thead>
			<tr>
				<th>No.</th>
				<th>언론사</th>
				<th>제목</th>
				<th>기자명</th>
				<th>등록일</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${fn:length(resultList) > 0 }">
				<c:forEach items="${resultList }" var="result">
					<tr>
						<td>${result.news_id }</td>
						<td>${result.media_nm }</td>
						<td><a href="/a010d${result.news_id }.do">${result.title }</a></td>
						<td>${result.author_nm }</td>
						<td>${result.reg_dt }</td>
					</tr>
				</c:forEach>
				</c:when>
				<c:otherwise>
					<tr>
						<td colspan="5">데이터가 없습니다.</td>
					</tr>
				</c:otherwise>
			</c:choose>
		</tbody>
	</table>

</body>
</html>
