<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%@taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ include file="../../../include/global.jsp"%>
<%@ page import="com.cesgroup.common.global.Constants" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>流程部署</title>
</head>
<body>
    <div class="PanelCon">
       <div class="PanelList">
       		<div class="clearfix">
	       		<div class="floatLeft2"><cui:toolbar id="processdefToolbar"></cui:toolbar></div>
	       		<div class="floatRight ">
		       			<span class="se-searchall">
		    				<cui:input id="processdef_search_text" type="text" width="220" placeholder="可检索key名称" onEnter="processdefSearchChange"></cui:input>
		    				<span class=" icon icon-search3 search-Click" onClick="processdefSearchChange()"></span>
	    				</span>
		       	</div>
       		</div>

		   <cui:grid id="processdefGrid" url="${ctx}/activity/repository-api/findLastVersionProcessDefinition" loadonce="false" asyncType="get" rownumbers="true" fitStyle="fill" multiselect="true" altRows="true">
			   <cui:gridCols>
				   <cui:gridCol name="key" width="40" align="center">key</cui:gridCol>
				   <cui:gridCol name="version" width="40" align="center">version</cui:gridCol>
				   <cui:gridCol name="deploymentId" width="40" align="center">deploymentId</cui:gridCol>
				   <cui:gridCol name="resourceName" width="40" align="center">resourceName</cui:gridCol>
				   <cui:gridCol name="diagramResourceName" width="40" align="center" >diagramResourceName</cui:gridCol>
			   </cui:gridCols>
		   </cui:grid>
       </div>
    </div>

	<!-- 上传部署文件的对话框 -->
	<%@include file="../deployment/uploadDeploymentDialog.jsp"%>
<script>


//工具栏
var processdefToolbarData = [
	{
		"id"		: "openUploadDeploymentDialogButton",
		"label"		: "上传部署文件",
		"disabled"	: "false",
		"onClick"		: "openUploadDeploymentDialog()",
		"type"		: "button",
		"cls":"greenbtn",
		"icon":"icon-plus-circle"


	},
	{}
];

$(function(){
	$("#processdefToolbar").toolbar({
		data : processdefToolbarData
	});
})


//查询
function processdefSearchChange(){
	var searchText = $("#processdef_search_text").textbox("getValue");

	var url = "${ctx}/activity/repository-api/findLastVersionProcessDefinition?1=1";
	if(searchText){
		url += "&searchText="+searchText ;
	}
	var grid = $("#processdefGrid");
	grid.grid("option","url",url);
	grid.grid("reload");
}
</script>
</body>
</html>