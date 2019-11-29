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
	       		<div class="floatLeft2"><cui:toolbar id="repositoryToolbar"></cui:toolbar></div>
	       		<div class="floatRight ">
		       			<span class="se-searchall">
		    				<cui:input id="repository_search_text" type="text" width="220" placeholder="可检索编码名称" onEnter="repositorySearchChange"></cui:input>
		    				<span class=" icon icon-search3 search-Click" onClick="repositorySearchChange()"></span>
	    				</span>
		       	</div>
       		</div>

		   <cui:grid id="repositoryGrid" url="${ctx}/activity/repository/findLastVersionProcessDefinition" loadonce="false" asyncType="get" rownumbers="true" fitStyle="fill" multiselect="true" altRows="true">
			   <cui:gridCols>
				   <cui:gridCol name="key">key</cui:gridCol>
				   <cui:gridCol name="version">version</cui:gridCol>
				   <cui:gridCol name="deploymentId" width="40">deploymentId</cui:gridCol>
				   <cui:gridCol name="resourceName" width="40" >resourceName</cui:gridCol>
				   <cui:gridCol name="diagramResourceName" width="40" >diagramResourceName</cui:gridCol>
				   <cui:gridCol  name="op" fixed="true" width="140" align="center" formatter="repository_operateFormatter">操作选项</cui:gridCol>
			   </cui:gridCols>
		   </cui:grid>
       </div>
    </div>

	 
<script>

//工具栏
var repositoryToolBarData = [
{
	"id"		: "newCode",
	"label"		: "新增",
	"disabled"	: "false",
	"onClick"		: "addOrUpdateCode()",
	"type"		: "button",
	"cls":"greenbtn",
	"icon":"icon-plus-circle"
	
	
},
{
	"id"		: "updateCode",
	"label"		: "修改",
	"disabled"	: "disable",
	"onClick"		: "updateCode()",
	"type"		: "button",
	"cls":"cyanbtn",
	"icon":"icon-pencil6"	
},
{
	"id"		: "deleteCode",
	"label"		: "删除",
	"onClick"		: "deleteCodeBatch()",
	"disabled"	: "false",
	"type"		: "button",
	"cls":"deleteBtn",
	"icon":"icon-bin"	
},
{
		"id"		: "moveCode",
		"label"		: "移动",
		"onClick"		: "moveCode()",
		"disabled"	: "false",
		"type"		: "button",
		"cls":"greenlight",
		"icon":"icon-move"
},
	{}
];

$(function(){
	$("#repositoryToolbar").toolbar({
		data : repositoryToolBarData
	});
})


//查询
function codeSearchChange(){
	var searchText = $("#code_search_text").textbox("getValue");

	var url = "${ctx}/auth/code/page?filter=pId_EQ_${pId};unitId_IN_-1,${CURRENTUSER.unitId};";
	if(searchText){
		url += ";name_LIKE_"+searchText ;
	}
	var grid = $("#codeGrid");
	grid.grid("option","url",url);
	grid.grid("reload");
}
</script>
</body>
</html>