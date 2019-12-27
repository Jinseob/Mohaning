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
                <input class="uk-input" type="text" name="email1" id="email1" placeholder="E-mail"/>
            </div>
            <div class="uk-width-1-3@s">
                <input class="uk-input" type="text" name="email2" id="email2" />
            </div>
            <div class="uk-width-1-1">
              <input class="uk-textarea" rows="5" name="emailSelection" id="emailSelection" placeholder="직접입력" />
            </div>
            <div class="uk-width-1-1">
              <input class="uk-input uk-form-large" type="text" name="nickname" id="nickname" placeholder="닉네임"/>
            </div>
            <div class="uk-width-1-1">
              <input class="uk-input uk-form-large" type="password" name="psw" id="psw" placeholder="Password" />
            </div>
            <div class="uk-width-1-1">
              <input class="uk-input uk-form-large" type="password" name="pswConfirm" id="pswConfirm" placeholder="Password 확인" />
            </div>
            <p uk-margin>
	            <button type="button" class="uk-button uk-button-default" onclick="javascript: onRegisterBtn();">가입하기</button>
	            <button type="button" class="uk-button uk-button-primary" onclick="javascript: onPageMove();">취소</button>
          	</p>
        </div>
      	</fieldset>
    </form>
  	</div>
  	</div>
</body>
</html>
