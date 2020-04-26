<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!doctype html>
<html>
<head>
	<title>모하닝</title>
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
		var url = $("#modal_url_in").val();
		if(url == ""){
			alert("URL 을 입력해주세요.");
			return;
		}else{
			$("#news_url").val(url);
		}
		
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
	 					
	 					// 팝업 숨기는 용.
	 					$("div[class='news_info']").show();
 						$("div[class='news_button']").hide();
 						$("#news_modal").hide();
 						
 						// 화면 출력용.
 						$("[data-index='news_title']").text(result.news_title);
	 					$("[data-index='news_url']").text(result.news_url);
	 					$("[data-index='portal_url']").text(result.portal_url);
	 					$("[data-index='author_nm']").text(result.author_nm);
	 					$("[data-index='author_email']").text(result.author_email);
	 					$("[data-index='media_nm']").text(result.media_nm);
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
		var frm = $("#frm").attr({"action" : "/News/processUpdate.do", "method" : "POST"});
		if(confirm("저장 하시겠습니까?")){
			frm.submit();
		}
	}
	
	function onPageMove(url){
		if(url = "main") url = "/News/main";
		$("#frm").attr({"action" : "/" + type + ".do", "method" : "POST"}).submit();
	}
	
	window.onload = function(){
		var btn = document.getElementById("news_insert_btn");
	    var modal = document.getElementById("news_modal");
	    var close = document.getElementById("news_modal_close");
	    btn.onclick = function() {
	    	modal.style.display = "block";
	    }

	    // When the user clicks on <span> (x), close the modal
	    close.onclick = function() {
	    	modal.style.display = "none";
	    }
	}
	</script>
</head>
<body>
	<!-- Header -->
	<header>
		<jsp:include page="/WEB-INF/views/common/header.jsp" />
	</header>
	<section>
	<div class="container padt-30">
	    <div class="contents_wrap">
    	<form id="frm" name="frm" method="post" >
			<input type="hidden" name="doc_id" id="doc_id" value="${result.doc_id}"/>
			<input type="hidden" name="media_id" id="media_id" value="${result.media_id}"/>
			<input type="hidden" name="author_id" id="author_id" value="${result.author_id}"/>  		
			<input type="hidden" name="portal_id" id="portal_id" value="${result.portal_id}"/>
	    	<div class="contents_head">
	        	<div class="contents_type">
	          		<h5>기사 등록</h5>
	        	</div>
	      	</div>
	      	<div class="contents_title">
	        	<div class="title">
	          		<input type="text" class="in_title" name="news_title" id="news_title" placeholder="URL 등록 후 자동으로 제목을 가져옵니다." readonly/>
	        	</div>
	      	</div>
	      	<div class="news_button">
	        	<div class="insert_div">
	          		<button type="button" id="news_insert_btn">기사를 등록하실려면 눌러주세요.</button>
	        	</div>
	      	</div>
	      	<div class="news_info" hidden>
	        	<div class="news_url">
	          		<label>원문 URL</label>
	          		<a href="#" data-index="news_url">${result.news_url }</a>
	          		<input type="hidden" name="news_url" id="news_url" value="${result.news_url}"/>
	        	</div>
	        	<div class="news_url">
	          		<label>포털 URL</label>
	          		<a href="#" data-index="portal_url">${result.portal_url }</a>
	          		<input type="hidden" name="portal_url" id="portal_url" value="${result.portal_url}"/>
	        	</div>
	        	<div class="author_info">
	          		<div class="author_name">
	            		<p data-index="author_nm">${result.author_nm }</p>
	            		<input type="hidden" name="author_nm" id="author_nm" value="${result.author_nm}"/>
	          		</div>
	          		<div class="author_email">
	            		<p data-index="author_email">${result.author_email }</p>
	            		<input type="hidden" name="author_email" id="author_email" value="${result.author_email}"/>
	          		</div>
	          		<div class="author_media">
	            		<p data-index="media_nm">${result.media_nm }</p>
	            		<input type="hidden" name="media_nm" id="media_nm" value="${result.media_nm}"/>
	          		</div>
	        	</div>
	      	</div>
	      	<div class="contents_view mt-10">
	        	<article>
	          		<div class="contents_article">
	            		<textarea cols="50" rows="10" name="news_contents" id="news_contents" placeholder="URL 등록 후 자동으로 요약 내용을 가져옵니다." readonly="readonly"></textarea>
	          		</div>
	        	</article>
	      	</div>
	      	<div class="contents_tags">
	        	<div class="tags">
	          		<p>#태그</p>
	          		<p>#태그1</p>
	          		<p>#태그2</p>
	          		<p>#태그3</p>
	        	</div>
	      	</div>
	      	<div class="buttons_wrap">
	        	<div class="left_group">
	
	        	</div>
	        	<div class="right_group">
	          		<button type="button" class="btn1" onclick="javascript: onPageMove('main');">취소</button>
	          		<button type="button" class="btn1" onclick="javascript: onSaveBtn();">저장</button>
	        	</div>
	      	</div>
      	</form>
		</div>
	</div>

  	<!-- Modal -->
  	<div id="news_modal" class="modal">
    	<div class="modal_contents">
      		<input type="text" class="modal_url_in" name="modal_url_in" id="modal_url_in" placeholder="기사의 URL을 입력해주세요."/>
      		<button type="button" class="btn1" onclick="onRegisterBtn();">등록</button>
      		<span class="close" id="news_modal_close">&times;</span>
    	</div>
  	</div>
	<!-- Modal -->
	</section>
	
	<footer>
		<jsp:include page="/WEB-INF/views/common/footer.jsp" />	    
  	</footer>
</body>
</html>
