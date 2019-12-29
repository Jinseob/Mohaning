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
  	<form id="frm" name="frm" method="post" >
    	<fieldset class="uk-fieldset">
        <div class="uk-grid-small" uk-grid>
            <div class="uk-width-1-3@s">
                <input class="uk-input" type="text" name="email" id="email" placeholder="E-mail" />
            </div>
            <p uk-margin>
	            <button type="button" class="uk-button uk-button-primary" onclick="javascript: onActivation();">인증</button>
          	</p>
            <div class="uk-width-1-3@s">
              <input class="uk-input" type="text" name="nickname" id="nickname" placeholder="인증번호"/>
            </div><!-- 인증하기 클릭 후 화면에서 보이도록 -->
            <p uk-margin>
	            <button type="button" class="uk-button uk-button-primary" onclick="javascript: onPageMove();">활성</button>
          	</p>
            <div class="uk-width-1-3@s">
              <input class="uk-input" type="text" name="nickname" id="nickname" placeholder="닉네임"/>
            </div>
            <p uk-margin>
	            <button type="button" class="uk-button uk-button-primary" onclick="javascript: onPageMove();">중복확인</button>
          	</p>
            <div class="uk-width-1-1">
              <input class="uk-input" type="password" name="psw" id="psw" placeholder="Password" />
            </div>
            <div class="uk-width-1-1">
              <input class="uk-input" type="password" name="pswConfirm" id="pswConfirm" placeholder="Password 확인" />
            </div>
            <p uk-margin>
	            <button type="button" class="uk-button uk-button-primary" onclick="javascript: onRegisterBtn();">가입하기</button>
	            <button type="button" class="uk-button uk-button-default" onclick="javascript: onPageMove();">취소</button>
          	</p>
        </div>
      	</fieldset>
    </form>
  	</div>
  	</div>
</body>
</html>
