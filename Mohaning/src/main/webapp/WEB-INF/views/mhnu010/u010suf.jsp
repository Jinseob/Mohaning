<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!doctype html>
<html>
<head>
	<title>모하닝</title>
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
	
	<section>
	<div class="container padt-40">
		<div class="contents_type">
   			<h5>회원 가입</h5>
		</div>
		<form id="frm" name="frm" method="post" >
			<input type="hidden" name="type" id="type" />
   			<div class="signup_wrap">
   				<div class="signupForm">
   					<div class="row">
	        			<input class="in_type1" type="text" name="email" id="email" placeholder="E-mail" autocomplete="off">
	        			<button type="button" class="btn1" data-Property="email">인증</button>
	      			</div>
	      			<div class="row">
	        			<input class="in_type1" type="text" name="emailAct" id="emailAct" placeholder="인증번호" autocomplete="off">
	        			<button type="button" class="btn1" data-Property="emailAct">활성</button>
	      			</div>
	      			<div class="row">
	        			<input class="in_type1" name="nickName" id="nickName" type="text" placeholder="닉네임" autocomplete="off">
	        			<button type="button" class="btn1" data-Property="nickName">확인</button>
	      			</div>
	      			<div class="row">
	        			<input class="in_type2" name="psw" id="psw" type="password" placeholder="비밀번호">
	      			</div>
	      			<div class="row">
	        			<input class="in_type2" name=pswConfirm id="pswConfirm" type="password" placeholder="비밀번호 확인">
	      			</div>
	      			<div class="row">
		      			<button type="button" class="btn1" onclick="javascript: onPageMove();">취소</button>
			            <button type="button" class="btn1" onclick="javascript: onRegisterBtn();">가입하기</button>
	      			</div>
	            </div>
   			</div>
   		</form>
	</div>
  	</section>
	
	<footer>
		<jsp:include page="/WEB-INF/views/common/footer.jsp" />	    
  	</footer>
</body>
</html>
