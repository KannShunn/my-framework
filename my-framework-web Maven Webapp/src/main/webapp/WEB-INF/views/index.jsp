
<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../include/global.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta http-equiv="cache-control" content="no-store, no-cache, must-revalidate"> 
<meta http-equiv="pragma" content="no-cache"> 
<meta http-equiv="expires" content="0">
<meta name="_CSRFToken" content="${_tokenval}">
<title>系统管理平台 - 监狱版</title>
<style>
html, body {
	height: 100%;
	width: 100%;
}
</style>
<%@ include file="../include/headerForCUI.jsp"%>
<link href="${ctx}/res/resource/style/css/common.css" type="text/css" rel="stylesheet" />
<link href="${ctx}/res/resource/style/css/white/forcoraltheme.css" type="text/css" rel="stylesheet" />
<link href="${ctx}/res/resource/style/css/jquery.pagewalkthrough.css" type="text/css" rel="stylesheet" />
<link href="${ctx}/res/resource/style/css/body.css" type="text/css" rel="stylesheet" />
<link href="${ctx}/res/resource/style/css/inforGlobal.css" type="text/css" rel="stylesheet" />

<link href="${ctx}/res/thirdParty/switch/css/switch.css" type="text/css" rel="stylesheet" />

<script type="text/javascript" src="${ctx}/res/resource/style/js/respond.min.js"></script>
<script type="text/javascript" src="${ctx}/res/resource/style/js/function.js"></script>
<script type="text/javascript" src="${ctx}/res/resource/style/js/inforGlobal.js"></script>

<script src="${ctx}/res/resource/style/js/sessionCheck.js"></script>
<script src="${ctx}/res/resource/style/js/jquery.form.js"></script>

<script type="text/javascript" src="${ctx}/res/thirdParty/switch/js/switchery.min.js"></script>

<script type="text/javascript">
	$(function(){
		toDisplay('Auth');
	})
</script>
</head>
<body>
	<cui:layout id="coralui-layout" fit="true">
		<%-- <cui:layoutRegion region="north" split="false" style="height:60px;border:none">
				<%@ include file="head.jsp"%>
			</cui:layoutRegion> --%>
		<cui:layoutRegion region="north" split="false" style="height:60px;border:none">
		</cui:layoutRegion>

		<div style="height: 60px; width: 100%; position: absolute; left: 0; top: 0; z-index: 22;">
			<%@ include file="head.jsp"%>
		</div>
		<cui:layoutRegion region='west' split="true" style="width:220px;" maxWidth="600" onLoad="initTreebox" onResize="initTreebox">
		
		</cui:layoutRegion>
		<cui:layoutRegion region='center' onLoad="layoutCenterResize" onResize="layoutCenterResize">
		
		</cui:layoutRegion>
		<cui:layoutRegion region='south' split="false" style="height:25px">
			<div id="footer" class="PanelFoot">
				<h3 class="F-left">版本：监狱版</h3>
				<h2 class="F-right">信息发展©2016版权所有</h2>
			</div>
		</cui:layoutRegion>
	</cui:layout>
</body>
</html>
