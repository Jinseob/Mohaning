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
		var url = $("#news_url").val();
		if(url == ""){
			alert("URL 을 입력해주세요.");
			return;
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
	 				if(result.status == "AL"){
	 					if(confirm(result.news_id + " 번으로 등록된 뉴스가 있습니다.\n이동하시겠습니까?")){
	 						onPageMove("n010d" + result.news_id);					
	 					}
	 				}else if(result.status == "E50"){
	 					alert(result.msg);
 						location.href = "./n010i.do";
	 				}else{
	 					if(result.status == "10" || result.status == "20"){
	 						alert(result.msg);
	 						location.href = "./main.do";
	 					}else{
	 						alert("등록되었습니다.");
	 						onPageMove("n010d" + result.news_id);
	 					}
	 					
	 					
// 	 					if(result.status == "E20") alert("관리자가 확인 후 내용을 업데이트 하겠습니다.");
	 					
// 	 					$("#doc_id").val(result.doc_id);
// 	 					$("#media_id").val(result.media_id);
// 	 					$("#media_nm").val(result.media_nm);
// 	 					$("#media_url").val(result.media_url);
// 	 					$("#author_id").val(result.author_id);
// 	 					$("#author_nm").val(result.author_nm);
// 	 					$("#author_email").val(result.author_email);
// 	 					$("#portal_id").val(result.portal_id);
// 	 					$("#portal_url").val(result.portal_url);
// 	 					$("#news_title").val(result.news_title);
// 	 					$("#news_contents").val(result.news_contents);
// 	 					$("#news_url").val(result.news_url);
	 					
// 	 					// 팝업 숨기는 용.
// 	 					$("div[class='news_info']").show();
//  						$("div[class='news_button']").hide();
//  						$("#news_modal").hide();
 						
//  						// 화면 출력용.
//  						$("[data-index='news_title']").text(result.news_title);
// 	 					$("[data-index='news_url']").text(result.news_url);
// 	 					$("[data-index='portal_url']").text(result.portal_url);
// 	 					$("[data-index='author_nm']").text(result.author_nm);
// 	 					$("[data-index='author_email']").text(result.author_email);
// 	 					$("[data-index='media_nm']").text(result.media_nm);
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
		$("#frm").attr({"action" : "./" + url + ".do", "method" : "POST"}).submit();
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
	      	<div class="news_input_area">
	      		<h4>기사의 URL을 입력해주세요.</h4>
	      		<span class="mt-10">URL 등록 후 자동으로 요약 내용을 가져옵니다.</span>
	      		<input type="url" class="news_url_input mt-10" name="news_url" id="news_url"></input>
	      		<div class="buttons_wrap mt-10">
		        	<div class="left_group">
			
		        	</div>
		        	<div class="right_group">
		          		<button type="button" class="btn1" onclick="onRegisterBtn();">등록</button>
		        	</div>
		      	</div>
	      	</div>
      	</form>
		</div>
	</div>
	</section>
	
	<footer>
		<jsp:include page="/WEB-INF/views/common/footer.jsp" />	    
  	</footer>
</body>
</html>
