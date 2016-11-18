<%@page import="com.cesgroup.common.global.Constants"%>
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
<title>资源管理</title>
</head>
<body>
    <div class="PanelCon">
       <div class="PanelList">
       		<div class="clearfix">
				<div class="floatLeft2"><cui:toolbar id="resourceToolbar"></cui:toolbar></div>
	       		<div class="floatRight ">
	       			<span class="se-searchall">
	    				<cui:input id="resource_search_text" type="text" width="220" placeholder="可检索资源名称" onEnter="resourceSearchChange"></cui:input>
	    				<span class=" icon icon-search3 search-Click" onClick="resourceSearchChange()"></span>
    				</span>
	       		</div>
       		</div>

		   <%--${ctx}/auth/resource/page?filter=pId_EQ_${pId};id_GT_100;--%>
		   <sec:hasPermission name="/auth/resource/page">
			<cui:grid id="resourceGrid" url="${ctx}/auth/resource/page?filter=pId_EQ_${pId};isSystem_EQ_1;" asyncType="get" loadonce="false" rownumbers="true" fitStyle="fill" multiselect="true" altRows="true" afterSortableRows="sortGrid" onDblClickRow="onDblClickResourceGrid">
				<cui:gridCols>
					<cui:gridCol name="id" hidden="true">id</cui:gridCol>
					<cui:gridCol name="isSystem" hidden="true">是否系统内置</cui:gridCol>
					<cui:gridCol name="name" width="200">名称</cui:gridCol>
					<cui:gridCol name="resUrl" width="200">资源url</cui:gridCol>
					<cui:gridCol name="resType" width="200" formatter="resTypeFormatter">资源类型</cui:gridCol>
					<cui:gridCol name="comments" width="200" >备注</cui:gridCol>
				</cui:gridCols>
				<cui:gridPager gridId="resourceGrid"></cui:gridPager>
			</cui:grid>
		   </sec:hasPermission>
       </div>
    </div>


	<%-- 新增或修改的对话框 --%>
	 <%@include file="./dialog.jsp" %>
	<!-- 移动资源的对话框 -->
	 <cui:dialog id="moveResourceDialog" onLoad="dialogLoad" autoOpen="false" reLoadOnOpen="true">
	 </cui:dialog>
	 
<script>

var resourceToolBarData = [
	<sec:hasPermission name="/auth/resource/create">
{
	"id"		: "newResource",
	"label"		: "新增",
	"disabled"	: "false",
	"onClick"		: "addOrUpdateResource()",
	"type"		: "button",
	"cls":"greenbtn",
	"icon":"icon-plus-circle"
},
	</sec:hasPermission>
	<sec:hasPermission name="/auth/resource/update">
{
	"id"		: "updateResource",
	"label"		: "修改",
	"disabled"	: "disable",
	"onClick"		: "updateResource()",
	"type"		: "button",
	"cls":"cyanbtn",
	"icon":"icon-pencil6"	
},
	</sec:hasPermission>
	<sec:hasPermission name="/auth/resource/delete">
{
	"id"		: "deleteResource",
	"label"		: "删除",
	"onClick"		: "deleteResource()",
	"disabled"	: "false",
	"type"		: "button",
	"cls":"deleteBtn",
	"icon":"icon-bin"	
},
	</sec:hasPermission>
	<sec:hasPermission name="/auth/resource/moveResource">
{
	"id"		: "moveResource",
	"label"		: "移动",
	"onClick"		: "moveResource()",
	"disabled"	: "false",
	"type"		: "button",
	"cls":"greenlight",
	"icon":"icon-move"	
},
	</sec:hasPermission>
	{}
];

$(function(){
	$("#resourceToolbar").toolbar({
		data : resourceToolBarData
	});
})

//资源类型格式化
function resTypeFormatter(cellValue,options,rowObject){
	if(cellValue == '<%=Constants.Resource.BUTTON_TYPE %>'){
		return "按钮";
	}
	if(cellValue == '<%=Constants.Resource.MENU_TYPE %>'){
		return "菜单";
	}
	if(cellValue == '<%=Constants.Resource.SYS_TYPE %>'){
		return "系统";
	}
	if(cellValue == '<%=Constants.Resource.URL_TYPE %>'){
		return "url";
	}
	return "未知";
	
}

<sec:hasPermission name="/auth/resource/update">
//双击列表行
function onDblClickResourceGrid(e,ui){
	addOrUpdateResource(ui.rowId);
}
</sec:hasPermission>

function addOrUpdateResource(id){
	var form = $("#resourceForm");
	var grid = $("#resourceGrid");
	var leftTree = $("#"+"resourceTree");
	var selNode = leftTree.tree("getSelectedNodes");
	if(!selNode){
		warning("请选择左侧节点");
		return;
	}
	form.form("reset");
	var url = null;
	debugger;
	var addFlag = true;
	if(id){//修改  在dialog中 如果有id传入的话 那么就是修改 否则的话就是新增
		url = "${ctx}/auth/resource/update";
		form.form("load","${ctx}/auth/resource/load?id="+id);
		addFlag = false;
	}else{//新增
 		url = "${ctx}/auth/resource/create";
 		$("#resourceForm input[name='pId']").textbox("setValue",'${pId}');
 		$("#resourceForm input[name='isSystem']").textbox("setValue",'<%=Constants.Common.NO%>');
 		if(selNode[0].resUrl){
			$("#resourceForm input[name='resUrl']").textbox("setValue",selNode[0].resUrl + "/");
 		}else{
			$("#resourceForm input[name='resUrl']").textbox("setValue","/");
 		}
 		addFlag = true;
	}
	
	dialog(
		"#resourceDialog",
		{
			width : 400,
			title : addFlag ? "新增资源" : "修改资源"
		},
		
		[{
			 text    :    "保存",
             id      :    "resourceSaveButton",
             click   :    function () {
			            	 if(form.form("valid")){
									var _this = this;
									loading("保存中...");
									var formData = form.form("formData",false);
									$.ajax({
										type		:	'POST',
										url			:	url,
										data		:	formData,
										dataType	:	'json',
										success		:	function(data){
															addOrUpdateLeftTreeNode(leftTree,data,addFlag)
															message("保存成功");
															grid.grid("reload");
															// leftTree.tree("reload"); 
															$(_this).dialog("close");
															hide();
										},
										error		:	function(e){
															error(e);
															hide();
										}
									})
								}
             			}
		 },
		 {
			 text    :    "关闭",
             id      :    "resourceSaveCloseButton",
             click   :    function () {
            				 $(this).dialog("close");
             }
			 
		 }
	
	]).dialog("open");
	
}

function updateResource(){
	var grid = $("#resourceGrid");
	var sel = grid.grid("option","selarrrow");  //只读属性。当multiselect 为true时，包含当前选定的行。此为一维数组，值为表格中选定行的ID。
	if(sel.length != 1){
		warning("请选择一条记录");
		return;
	}
	addOrUpdateResource(sel[0]);
	
}

function deleteResource(){
	var grid = $("#resourceGrid");
	var leftTree = $("#"+"resourceTree");
	var selNode = leftTree.tree("getSelectedNodes");
	if(!selNode){
		warning("请选择左侧节点");
		return;
	}
	var sel = grid.grid("option","selarrrow");  //只读属性。当multiselect 为true时，包含当前选定的行。此为一维数组，值为表格中选定行的ID。
	if(sel.length == 0){
		warning("请选择一条记录");
		return;
	}
	var ids = grid.grid("option","selarrrow");
	var names = "";
	 for(var i=0; i<ids.length; i++){
	 var rowData = grid.grid("getRowData",ids[i]);

	 names += "," + rowData.name;
	 }
	 names = names.substring(1,names.length);
	
	$.confirm("确认要删除吗?",function(r){
		if(r){
			loading("删除中...");
			$.ajax({
				type		:	'POST',
				url			:	'${ctx}/auth/resource/delete',
				data		:	{id : ids.toString(), name:names},
				dataType	:	'json',
				success		:	function(data){
									if(data){
										deleteLeftTreeNode(leftTree,ids);
										message("删除成功!");
										grid.grid("reload");
										hide();
									}else{
										message("要删除的节点中包含子结点,删除失败!");
										grid.grid("reload");
										hide();
									}
				},
				error		:	function(e){
									error(e);
									hide();
				}	
			})
		}
	})

}

//移动资源
function moveResource(){
	var grid = $("#resourceGrid");   //获取资源表格
	var ids = grid.grid("option", "selarrrow");
	var leftTree = $("#resourceTree");
	if(ids.length < 1){
		warning("请至少选择一条记录");
		return;
	}

	var names = "";
	for (var i = 0; i < ids.length; i++) {
		var rowData = grid.grid("getRowData", ids[i]);
		names += "," + rowData.name;
	}
	names = names.substring(1, names.length);

	var url = "${ctx}/auth/resource/openMoveResourceDialog?resourceIds="+ids;

	dialog(
			"#moveResourceDialog",
			{
				width : 400,
				height : 700,
				title : "移动资源",
				url : url
			},
			[
			 {
				 text    :    "保存",
	             id      :    "moveResourceSaveButton",
	             click   :    function () {
				            	 var _this = $(this);			
									
								var tree = $("#moveResourceTree");
								
								var checkedRes = tree.tree("getCheckedNodes",true);//获取全部勾选中的节点
								var newPResourceId = checkedRes[0].id; //newPResource 目标结点 (新的父资源的id)
					 			var newPResourceName = checkedRes[0].name; //newPResource 目标结点 (新的父资源的名称)
								
								loading("正在保存...");
								
								$.ajax({
									type		:	'POST',
									url			:	'${ctx}/auth/resource/moveResource',
									data		:	{resourceIds : ids.toString(), newPResourceId : newPResourceId , name:names.toString(), newPResourceName : newPResourceName},
									dataType	:	'json',
									success		:	function(data){
														message("保存成功");
														var targetNode = leftTree.tree("getNodeByParam","id",newPResourceId);
														for (var i = 0; i < ids.length; i++) {
															var treeNode = leftTree.tree("getNodeByParam","id",ids[i]);
															leftTree.tree("moveNode",targetNode,treeNode,"inner",false);
														}
														grid.grid("reload");
														_this.dialog("close");
														//leftTree.tree("reload");
									},
									error		:	function(e){
														error(e);
									},
									complete	:	function(){
														hide();
									}
								})
				             }
			 },
			 {

				 text    :    "关闭",
	             id      :    "moveResourceSaveCloseButton",
	             click   :    function () {
	            	            $(this).dialog("close");
	             } 
			 }
			
		]).dialog("open");
}

//搜索
function resourceSearchChange(){
	var url = "${ctx}/auth/resource/page?filter=pId_EQ_${pId};isSystem_EQ_1;";
	var searchText = $("#resource_search_text").textbox("getValue");
	if(searchText){
		url += "name_LIKE_"+searchText+";" ;
	}
	var grid = $("#resourceGrid");
	grid.grid("option","url",url);
	grid.grid("reload");
}
</script>
</body>
</html>