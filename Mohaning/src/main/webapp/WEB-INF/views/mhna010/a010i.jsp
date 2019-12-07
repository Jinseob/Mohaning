<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!doctype html>
<html>
<head>
	<title>기사</title>
	<jsp:directive.include file="/WEB-INF/views/common/taglib.jsp" />
	<script type="text/javascript">
	function onNewsRegisterAjax(){
		var frm = $("#frm").serialize();
		$.ajax({
			method: "POST",
			url : "/NewsRegister.json",
			dataType: "JSON",
			data : frm,
			success: function(results){
				var result = results.result;
				if(results.type == "NEW"){
					$("#title").val(result.title);
					$("#content").val(result.content);
					$("#author_id").val(result.author_id);
					$("#author_nm").val(result.author_nm);
					$("#author_email").val(result.author_email);
					$("#media_id").val(result.media_id);
					$("#media_nm").val(result.media_nm);
					$("#media_url").val(result.media_url);
				}else if(results.type == "OCC"){
					if(confirm(result.news_id + " 번으로 등록된 뉴스가 있습니다.\n이동하시겠습니까?")){
						onPageMove("a010d" + result.news_id);					
					}
				}
			},
			error: function(data){
				alert("E" + data);
			}
		})
	}
	
	function onSaveBtn(){
		if(confirm("저장 하시겠습니까?")){
			$("#frm").attr({"action" : "/processUpdate_a010.do", "method" : "POST"}).submit();
		}
	}
	
	function onPageMove(type){
		$("#frm").attr({"action" : "/" + type + ".do", "method" : "POST"}).submit();
	}
	</script>
</head>
<body>
	<!-- Header -->
	<header>
		<jsp:include page="/WEB-INF/views/common/header.jsp" />
	</header>
	<h1>기사 등록</h1>

<form id="frm" name="frm">
	<input type="url" name="news_url" id="news_url" />
	<input type="button" value="등록" onclick="javascript: onNewsRegisterAjax();" />
	<input type="button" value="취소" onclick="javascript: onPageMove('Board');" />
	
	<input type="text" name="title" id="title" />
	<input type="text" name="content" id="content" />
	<input type="text" name="author_nm" id="author_nm" />
	<input type="text" name="author_email" id="author_email">
	
	<input type="text" name="media_id" id="media_id" />
	<input type="text" name="media_nm" id="media_nm" />
	<input type="text" name="media_url" id="media_url">
</form>
	<input type="button" value="저장" onclick="javascript: onSaveBtn();" />
	<input type="button" value="목록" onclick="javascript: onPageMove('Board');" />
</body>
</html>
