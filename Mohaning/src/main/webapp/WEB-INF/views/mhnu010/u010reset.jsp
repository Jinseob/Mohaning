<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!doctype html>
<html>
<head>
	<title>기사</title>
	<jsp:include page="/WEB-INF/views/common/taglib.jsp" />
	<script type="text/javascript">
	function onLoginBtn(){
		$("#frm").attr({"action" : "/signInProcess.do", "method" : "POST"}).submit();
	}
	
	$(function(){
		$(".submitBtn").on("click", function(){
			if($("#email").val() == ""){
				alert("E-mail 을 입력해주세요.");
				return;
			}
			
			var frm = $("#frm").serialize();
			$.ajax({
				type: "POST",
				async: false,
				url : "/loginChk.json",
				dataType: "json",
				data : frm,
				success: function(results){
					if(results.Status == "S"){
						$("#frm").attr({"action" : "/signInProcess.do", "method" : "POST"}).submit();
					}else{
						alert("E-mail 또는 비밀번호를 잘못 입력하였습니다.");
					}
				},
				error: function(data){
					alert("E" + data);
				},
			})
			
			
		});
	});
	</script>
</head>
<body>
	<!-- Header -->
	<header>
		<jsp:include page="/WEB-INF/views/common/header.jsp" />
	</header>
	<h1>비밀번호 찾기</h1>

	<div class="uk-section">
  	<div class="uk-container">
  	<form id="frm" name="frm" method="post" >
    	<fieldset class="uk-fieldset">
        <div class="uk-grid-small" uk-grid>
        	<p>E-mail 을 입력하면 비밀 번호 초기화 URL을 보내 드립니다.</p>
            <input class="uk-input uk-width-1-1" type="text" name="email" id="email" placeholder="E-mail" autocomplete="off"/>
            <button type="button" class="uk-button uk-button-primary uk-width-1-1 submitBtn">제출</button>
        </div>
      	</fieldset>
    </form>
  	</div>
  	</div>
</body>
</html>
