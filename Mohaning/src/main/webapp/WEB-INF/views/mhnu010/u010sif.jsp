<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!doctype html>
<html>
<head>
	<title>모하닝</title>
	<jsp:include page="/WEB-INF/views/common/taglib.jsp" />
	<script type="text/javascript">
	function onLoginBtn(){
		$("#frm").attr({"action" : "/signInProcess.do", "method" : "POST"}).submit();
	}
	
	$(function(){
		$(".loginBtn").on("click", function(){
			if($("#email").val() == ""){
				alert("E-mail 을 입력해주세요.");
				return;
			}
			
			if($("#psw").val() == ""){
				alert("Password 를 입력해주세요.");
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
	
	<section>
	<div class="container padt-40">
		<div class="contents_type">
  			<h5>로그인</h5>
		</div>
		<form id="frm" name="frm" method="post" >
   			<div class="login_wrap">
   				<div class="loginForm">
   					<div class="row">
	        			<input type="text" class="in_id" name="email" id="email" placeholder="E-mail" autocomplete="off"/>
	      			</div>
	      			<div class="row">
	        			<input type="password" class="in_passwd" name="psw" id="psw" placeholder="Password" autocomplete="off"/>
	      			</div>
	      			<div class="row">
	        			<button type="button" class="login_btn">로그인</button>
	      			</div>
	      			<div class="row">
		        		<input type="checkbox" id="chkItem1">
           				<label for="chkItem1">자동 로그인</label>
	        		</div>
	        		<div class="spacer-horizontal-line"></div>
		            <div class="row">
						<a href="/resetPsw.do" >비밀번호 찾기</a>
						<div class="spacer-vertical-line"></div>
						<a href="/signUp.do" >회원가입</a>
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
