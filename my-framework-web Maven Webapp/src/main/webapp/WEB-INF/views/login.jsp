<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%@taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta http-equiv="cache-control" content="no-store, no-cache, must-revalidate"> 
<meta http-equiv="pragma" content="no-cache"> 
<meta http-equiv="expires" content="0">
<title>复用统计平台-登录</title>
<link href="${ctx}/res/resource/style/css/inforGlobal.css" type="text/css" rel="stylesheet" />
<link href="${ctx}/res/resource/style/css/login.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="${ctx }/res/cui/jquery-1.9.1.min.js"></script>
<script src="${ctx}/res/thirdParty/jqfloat.min.js"></script>
<script type="text/javascript">
	var ctx = "${ctx}";
</script>
<script src="${ctx}/res/resource/style/js/login.js"></script>
<script src="${ctx}/res/resource/style/js/sessionCheck.js"></script>
<style type="text/css">
	body{
	overflow: hidden;
	}
</style>
</head>
<body style="text-align:center;">
	<script type="text/javascript">
		$(function(){
			$("#cloud1").jqFloat({
				width: 800,
				height: 300,
				speed: 10000
			});
			$("#cloud2").jqFloat({
				width: 300,
				height: 100,
				speed: 7000
			});
			$("#cloud3").jqFloat({
				width: 800,
				height: 300,
				speed: 10000
			});
			$("#cloud4").jqFloat({
				width: 800,
				height: 300,
				speed: 10000
			});
			if("${shiroLoginFailure}"){
				$("#mesBox").show();
			}

			$(".jcaptcha-btn").click(
					function() {
						$("#jcaptcha-img").attr("src",
								'${ctx}/jcaptcha.jpg?' + new Date().getTime());
					});
		});

	</script>

<div id="cloud1" class="cloud1"></div>
<div id="cloud2" class="cloud"></div>
<div id="cloud3" class="cloud2"></div>
<div id="cloud4" class="cloud"></div>
<div class="loginbg">
  <div class="loginbg2"></div>
  <div class="loginbg3">
    <div class="logo-login"></div>
    <%--判断用户名、密码、验证码是否为空，以及根据服务器返回的error码来反馈相应的错误信息 --%>
    <div class="loginbox">
    	<form id="loginForm" style="position:relative" action="${ctx}/login" method="POST">
    		<div class="exitmessage">
				<div id="mesBox">
					<span>
					    <span><img src='${ctx}/res/resource/style/images/login/fail.png'/></span>
						<c:if test="${shiroLoginFailure eq 'org.apache.shiro.authc.IncorrectCredentialsException'}"><span class="error">${msg} </span></c:if>
						<c:if test="${shiroLoginFailure eq 'org.apache.shiro.authc.UnknownAccountException'}"><span class="error">用户名与密码不匹配</span></c:if>
						<c:if test="${shiroLoginFailure eq 'org.apache.shiro.authc.ExcessiveAttemptsException'}"><span class="error">${msg}</span></c:if>
						<c:if test="${shiroLoginFailure eq 'org.apache.shiro.authc.AuthenticationException'}"><span class="error">用户名或者密码不能为空</span></c:if>
						<c:if test="${shiroLoginFailure eq 'org.apache.shiro.authc.DisabledAccountException'}"><span class="error">用户被锁定，无法登录</span></c:if>
						<c:if test="${shiroLoginFailure eq 'jCaptcha.error' }"><span class="error">验证码错误</span></c:if>
					</span>
				</div>
    	  	</div>
	      <div class="paddingt55">
			  <div class="labelbox user">
				  <label>用户名：</label>
				  <input type="text" id="loginName" name="loginName">
			  </div>
			  <div class="labelbox pwd">
				  <label>密&emsp;码：</label>
				  <input type="password" autocomplete="off" id="password" name="password">
			  </div>
			  <div class="labelbox identify">
				  <c:if test="${jcaptchaEbabled}">
					  <label>验证码：</label>
					  <input type="text" name="jcaptchaCode" placeholder="请输入右侧验证码" autocomplete="off">
					  <span class="identifyIMg"><img id="jcaptcha-img" src="${ctx}/jcaptcha.jpg" title="点击更换验证码" alt="验证码" /></span>
				  </c:if>
			  </div>
			  <div class="rember clearfix">
				  <span>
					<input id="rememberMe" name="rememberMe" type="checkbox" value="true"/>
					<label>下次自动登录</label>
				  </span>
			  </div>

	      </div>
	      <div class="loginbtnteam">
	        <span class="loginBtnOk"><a id="loginBtn" href="javascript:login();">登录系统</a></span>
	      </div>
      </form>
    </div>
    <div class="copyright-login">信息发展©2016版权所有,版本: 1.0</div>
  </div>
</div>
</body>
</html>
<script type="text/javascript">
	$(function(){

		//绑定回车登陆
		$("body").keydown(function(event) {
		    if (event.keyCode == "13") {//keyCode=13是回车键
		    	$("#loginBtn").text("登录中...");
		    	if(!login()){
		    		$("#loginBtn").text("登录系统");
		    	};
		    }
		});
	})
</script>