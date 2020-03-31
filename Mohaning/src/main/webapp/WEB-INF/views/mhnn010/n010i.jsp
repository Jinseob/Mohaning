<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!doctype html>
<html>
<head>
	<title>기사</title>
	<jsp:include page="/WEB-INF/views/common/taglib.jsp" />
	<script type="text/javascript">
// 	function onNewsRegisterAjax(){
// 		var frm = $("#frm").serialize();
// 		$.ajax({
// 			type: "POST",
// 			async: false,
// 			url : "/NewsRegister.json",
// 			dataType: "json",
// 			data : frm,
// 			success: function(results){
// 				var result = results.result;
// 				if(results.type == "NEW"){
// 					$("#title").val(result.title);
// 					$("#content").val(result.content);
// 					$("#author_id").val(result.author_id);
// 					$("#author_nm").val(result.author_nm);
// 					$("#author_email").val(result.author_email);
// 					$("#media_id").val(result.media_id);
// 					$("#media_nm").val(result.media_nm);
// 					$("#media_url").val(result.media_url);
// 				}else if(results.type == "OCC"){
// 					if(confirm(result.news_id + " 번으로 등록된 뉴스가 있습니다.\n이동하시겠습니까?")){
// 						onPageMove("a010d" + result.news_id);					
// 					}
// 				}
// 			},
// 			error: function(data){
// 				alert("E" + data);
// 			},
// 		})
// 	}
	
	function onRegisterBtn(){
		var frm = $("#frm").serialize();
		if(confirm("등록 하시겠습니까?")){
			$.ajax({
	 			type: "POST",
	 			async: false,
	 			url : "/NewsRegister.json",
	 			dataType: "json",
	 			data : frm,
	 			success: function(results){
	 				var result = results.result;
	 				if(result.status == "N"){
	 					$("#doc_id").val(result.doc_id);
	 					$("#media_id").val(result.media_id);
	 					$("#media_nm").val(result.media_nm);
	 					$("#media_url").val(result.media_url);
	 					$("#author_id").val(result.author_id);
	 					$("#author_nm").val(result.author_nm);
	 					$("#author_email").val(result.author_email);
	 					$("#portal_id").val(result.portal_id);
	 					$("#portal_url").val(result.portal_url);
	 					$("#news_title").val(result.news_title);
	 					$("#news_contents").val(result.news_contents);
	 					$("#news_url").val(result.news_url);
	 				}else if(result.status == "AL"){
	 					if(confirm(result.news_id + " 번으로 등록된 뉴스가 있습니다.\n이동하시겠습니까?")){
	 						onPageMove("n010d" + result.news_id);					
	 					}
	 				}else if(result.status == "E1"){
	 					alert("아직 지원하지 않는 언론사입니다.");
	 				}
	 			},
	 			error: function(data){
	 				alert("E" + data);
	 			},
	 		})
		}
	}
	
	function onSaveBtn(){
		var frm = $("#frm").attr({"action" : "/processUpdate_n010.do", "method" : "POST"});
		if(confirm("저장 하시겠습니까?")){
			frm.submit();
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

<!-- <form id="frm" name="frm"> -->
<!-- 	<input type="text" name="media_id" id="media_id" /> -->
<!-- 	<input type="text" name="media_url" id="media_url"> -->
<!-- </form> -->
	
	<div class="uk-section">
  	<div class="uk-container">
  	<form id="frm" name="frm">
		<input type="hidden" name="doc_id" id="doc_id" value="${result.doc_id}"/>
		<input type="hidden" name="media_id" id="media_id" value="${result.media_id}"/>
		<input type="hidden" name="author_id" id="author_id" value="${result.author_id}"/>  		
		<input type="hidden" name="portal_id" id="portal_id" value="${result.portal_id}"/>
		
    	<fieldset class="uk-fieldset">
        <div class="uk-grid-small" uk-grid>
          	<p uk-margin>
	            <button type="button" class="uk-button uk-button-default" onclick="onRegisterBtn();">등록</button>
	            <button type="button" class="uk-button uk-button-default" onclick="onPageMove('News');">취소</button>
<!-- 	            <button class="uk-button uk-button-primary">발행</button> -->
          	</p>
            <div class="uk-width-1-1">
                <input class="uk-input" type="text" name="news_url" id="news_url" placeholder="https://" value="${result.news_url }"/>
            </div>
            <div class="uk-width-1-1">
                <input class="uk-input" type="text" name="portal_url" id="portal_url" placeholder="https://" value="${result.portal_url }" readonly="readonly"/>
            </div>
            <div class="uk-width-1-1">
                <input class="uk-input" type="text" name="news_title" id="news_title" placeholder="기사 제목" value="${result.news_title }"/>
            </div>
            <div class="uk-width-1-3@s">
                <input class="uk-input" type="text" name="author_nm" id="author_nm" placeholder="모하닝 기자" value="${result.author_nm }"/>
            </div>
            <div class="uk-width-1-3@s">
                <input class="uk-input" type="text" name="author_email" id="author_email" placeholder="mohaning@mohaning.com" value="${result.author_email }"/>
            </div>
            <div class="uk-width-1-3@s">
                <input class="uk-input" type="text" name="media_nm" id="media_nm" placeholder="모하닝 신문" value="${result.media_nm }"/>
            </div>
            <div class="uk-width-1-1">
              <textarea class="uk-textarea" rows="5" name="news_contents" id="news_contents" placeholder="Textarea">${result.news_contents }</textarea>
            </div>
            <div class="uk-width-1-1">
              <span class="uk-badge">태그태그</span>
              <span class="uk-badge">태그태그태그</span>
            </div>
            <p uk-margin>
	            <button type="button" class="uk-button uk-button-default" onclick="javascript: onSaveBtn();">저장</button>
	            <button type="button" class="uk-button uk-button-primary" onclick="javascript: onPageMove('News');">목록</button>
          	</p>
        </div>
      	</fieldset>
    </form>
  	</div>
  	</div>
</body>
</html>
