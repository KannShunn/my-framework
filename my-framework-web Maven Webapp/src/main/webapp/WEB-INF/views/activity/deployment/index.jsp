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
	       		<div class="floatLeft2"><cui:toolbar id="deploymentToolbar"></cui:toolbar></div>
	       		<div class="floatRight ">
		       			<span class="se-searchall">
		    				<cui:input id="deployment_search_text" type="text" width="220" placeholder="可检索name名称" onEnter="deploymentSearchChange"></cui:input>
		    				<span class=" icon icon-search3 search-Click" onClick="deploymentSearchChange()"></span>
	    				</span>
		       	</div>
       		</div>

		   <cui:grid id="deploymentGrid" url="${ctx}/activity/repository-api/findDeploymentList" loadonce="false" asyncType="get" rownumbers="true" fitStyle="fill" multiselect="true" altRows="true">
			   <cui:gridCols>
				   <cui:gridCol name="id" align="center">id</cui:gridCol>
				   <cui:gridCol name="name" align="center">name</cui:gridCol>
				   <cui:gridCol name="deploymentTime" width="40" align="center" >deploymentTime</cui:gridCol>
				   <cui:gridCol  name="op" fixed="true" width="140" align="center" formatter="deployment_operateFormatter">操作选项</cui:gridCol>
			   </cui:gridCols>
		   </cui:grid>
       </div>
    </div>

	<!-- 上传部署文件的对话框 -->
	<%@include file="uploadDeploymentDialog.jsp"%>
<script>

//工具栏
var deploymentToolBarData = [
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
	$("#deploymentToolbar").toolbar({
		data : deploymentToolBarData
	});
})


//查询
function deploymentSearchChange(){
	var searchText = $("#deployment_search_text").textbox("getValue");

	var url = "${ctx}/activity/repository-api/findDeploymentList?1=1";
	if(searchText){
		url += "&searchText="+searchText ;
	}
	var grid = $("#deploymentGrid");
	grid.grid("option","url",url);
	grid.grid("reload");
}


//格式化操作栏
function deployment_operateFormatter(cellValue, options, rowObject){
    var result ="";

    result+= " <a href='javascript:deleteDeployment(\""+ rowObject.id+ "\");' title='删除' class='griddeletebtn'></a> ";

    return result;
}


//删除编码
function deleteDeployment(ids){
    var grid = $("#deploymentGrid");

    $.confirm("确认要删除吗?",function(r){
        if(r){
            loading("删除中...");
            $.ajax({
                type		:	'POST',
                url			:	'${ctx}/activity/repository-api/deleteDeployment',
                data		:	{id : ids.toString()},
                dataType	:	'json',
                success		:	function(data){
                    message("删除成功");
                    grid.grid("reload");
                    hide();
                },
                error		:	function(e){
                    error(e);
                    hide();
                }
            })
        }
    })
}
</script>
</body>
</html>