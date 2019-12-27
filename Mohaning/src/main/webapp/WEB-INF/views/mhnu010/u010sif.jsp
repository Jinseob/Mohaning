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
	</script>
</head>
<body>
	<!-- Header -->
	<header>
		<jsp:include page="/WEB-INF/views/common/header.jsp" />
	</header>
	<h1>로그인</h1>

	<div class="uk-section">
  	<div class="uk-container">
  	<form id="frm" name="frm" method="post" >
    	<fieldset class="uk-fieldset">
        <div class="uk-grid-small" uk-grid>
            <div class="uk-width-1-1">
                <input class="uk-input" type="text" name="email" id="email" placeholder="E-mail"/>
            </div>
            <div class="uk-width-1-1">
                <input class="uk-input" type="password" name="psw" id="psw" placeholder="password"/>
            </div>
            <p uk-margin>
	            <button type="button" class="uk-button uk-button-primary" onclick="javascript: onLoginBtn();">로그인</button>
          	</p>
        </div>
        	자동 로그인<br/>
          	아이디 찾기 | 비밀번호 찾기 | 회원가입
      	</fieldset>
    </form>
  	</div>
  	</div>
</body>
</html>
