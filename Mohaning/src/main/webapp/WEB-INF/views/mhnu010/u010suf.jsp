<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!doctype html>
<html>
<head>
	<title>기사</title>
	<jsp:include page="/WEB-INF/views/common/taglib.jsp" />
	<script type="text/javascript">
	function onRegisterBtn(){
		var frm = $("#frm").attr({"action" : "/signUpProcess.do", "method" : "POST"});
		if(confirm("가입 하시겠습니까?")){
			frm.submit();
		}
	}
	
	function onPageMove(){
		$("#frm").attr({"action" : "/", "method" : "POST"}).submit();
	}
	
	$(function(){
		$("#email").on("keyup", function(){
			var val = $(this).val();
			var regExp = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
			$(this).removeClass("uk-form-danger");
			$(this).removeClass("uk-form-success");
			if (val.match(regExp) != null) {
				$(this).addClass("uk-form-success");
			}else {
				$(this).addClass("uk-form-danger");
			}
		});
		
		$("#psw").on("change", function(){
			$("#pswConfirm").val("");
			$("#pswConfirm").removeClass("uk-form-danger");
			$("#pswConfirm").removeClass("uk-form-success");
		});
		
		$("#pswConfirm").on("keyup", function(){
			var val = $(this).val();
			$(this).removeClass("uk-form-danger");
			$(this).removeClass("uk-form-success");
			if(val == $("#psw").val()){
				$(this).addClass("uk-form-success");
			}else{
				$(this).addClass("uk-form-danger");
			}
		});
		
		$(".chkBtn").on("click", function(){
			var type = $(this).attr("data-Property");
			$("#type").val(type);
			var frm = $("#frm").serialize();
			$("#" + type).removeClass("uk-form-danger");
			$("#" + type).removeClass("uk-form-success");
			$.ajax({
				type: "POST",
				async: false,
				url : "/userValidationChk.json",
				dataType: "json",
				data : frm,
				success: function(results){
					if(results.Status == "S"){
						$("#" + type).addClass("uk-form-success");
					}else{
						$("#" + type).addClass("uk-form-danger");
					}
				},
				error: function(data){
					alert("E" + data);
				},
			})
		})
	})
	</script>
</head>
<body>
	<!-- Header -->
	<header>
		<jsp:include page="/WEB-INF/views/common/header.jsp" />
	</header>
	<h1>회원 가입</h1>

	<div class="uk-section">
	  	<div class="uk-container">
		  	<form class="uk-form-horizontal" id="frm" name="frm" method="post" >
		  		<input type="hidden" name="type" id="type" />
		    	<fieldset class="uk-fieldset">
			        <div class="uk-flex uk-flex-center uk-margin-small">
			        	<div class="uk-width-1-4">
			            	<label class="uk-form-label" for="email">메일주소</label>
			        	</div>
			            <div class="uk-width-expand">
			               	<input class="uk-input" type="text" name="email" id="email" placeholder="E-mail" autocomplete="off">
			            </div>
			            <div class="uk-width-auto uk-child-width-expand@s">
			               	<button type="button" class="uk-button uk-button-primary chkBtn" data-Property="email">인증</button>
			            </div>
					</div>
			        <div class="uk-flex uk-flex-center uk-margin-small">
			            <div class="uk-width-1-4">
			              	<label class="uk-form-label" for="emailAct">인증번호</label>
			            </div>
			            <div class="uk-width-expand">
			            	<input class="uk-input" type="text" name="emailAct" id="emailAct" placeholder="인증번호" autocomplete="off">
			            </div>
			            <div class="uk-width-auto uk-child-width-expand@s">
			              	<button type="button" class="uk-button uk-button-primary chkBtn" data-Property="emailAct">활성</button>
			            </div>
					</div>
			        <div class="uk-flex uk-flex-center uk-margin-small">
			            <div class="uk-width-1-4">
			            	<label class="uk-form-label" for="nickName">닉네임</label>
			            </div>
			            <div class="uk-width-expand">
			               	<input class="uk-input" name="nickName" id="nickName" type="text" placeholder="닉네임" autocomplete="off">
			            </div>
			            <div class="uk-width-auto uk-child-width-expand@s">
			               	<button type="button" class="uk-button uk-button-primary chkBtn" data-Property="nickName">중복확인</button>
			            </div>
					</div>
			        <div class="uk-flex uk-flex-center uk-margin-small">
			        	<div class="uk-width-1-4">
			                <label class="uk-form-label" for="psw">비밀번호</label>
			            </div>
			            <div class="uk-width-expand">
			                <input class="uk-input" name="psw" id="psw" type="password" placeholder="비밀번호">
			            </div>
					</div>
			        <div class="uk-flex uk-flex-center uk-margin-small">
			        	<div class="uk-width-1-4">
			            	<label class="uk-form-label" for="pswConfirm">비밀번호 확인</label>
			            </div>
			            <div class="uk-width-expand">
			                <input class="uk-input" name=pswConfirm id="pswConfirm" type="password" placeholder="비밀번호 확인">
			            </div>
			    	</div>
			        <p class="uk-align-right" uk-margin>
			        	<button type="button" class="uk-button uk-button-default" onclick="javascript: onPageMove();">취소</button>
			            <button type="button" class="uk-button uk-button-primary" onclick="javascript: onRegisterBtn();">가입하기</button>
			        </p>
		    	</fieldset>
		    </form>
	  	</div>
  	</div>
</body>
</html>
