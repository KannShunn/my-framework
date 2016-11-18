<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%@taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ include file="../../../include/global.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>角色管理</title>
</head>
<body>
    <div class="PanelCon">
       <div class="PanelList">
       		<div class="clearfix">
	       		<div class="floatLeft2"><cui:toolbar id="rootRoleToolbar"></cui:toolbar></div>
       		</div>

		   <sec:hasPermission name="/auth/role/page">
			<cui:grid id="roleGrid" url="${ctx}/auth/role/page?filter=id_IN_3;" asyncType="post" loadonce="false" rownumbers="true" fitStyle="fill" multiselect="true" altRows="true">
				<cui:gridCols>
					<cui:gridCol name="id" hidden="true">id</cui:gridCol>
					<cui:gridCol name="unitId" hidden="true" >单位id</cui:gridCol>
					<cui:gridCol name="name" width="200">名称</cui:gridCol>
					<cui:gridCol name="roleKey" width="200" >角色值</cui:gridCol>
					<cui:gridCol name="comments" width="200" >备注</cui:gridCol>
				</cui:gridCols>
				<cui:gridPager gridId="roleGrid"></cui:gridPager>
			</cui:grid>
		   </sec:hasPermission>
       </div>
    </div>

	 <!-- 授予资源的对话框 -->
	 <cui:dialog id="grantResourceDialog" onLoad="dialogLoad" autoOpen="false" reLoadOnOpen="true">
	 </cui:dialog>
<script>

//工具栏
var rootRoleToolBarData = [
	<sec:hasPermission name="/auth/role/grantResource">
{
	"id"		: "grantResource",
	"label"		: "授予资源",
	"onClick"		: "grantResource()",
	"disabled"	: "false",
	"type"		: "button",
	"cls":"greenlight",
	"icon":"icon-file-plus"	
},
	</sec:hasPermission>
	{}
];

$(function(){
	
	var rootRoleToolbar = $("#rootRoleToolbar");
	rootRoleToolbar.toolbar({
		data : rootRoleToolBarData
	});
})

</script>

	<%@include file="commonJs.jsp"%>
</body>
</html>