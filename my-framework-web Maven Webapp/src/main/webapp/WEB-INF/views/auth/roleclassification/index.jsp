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
<title>角色分类管理</title>
</head>
<body>
    <div class="PanelCon">
       <div class="PanelList">
       		<div class="clearfix">
	       		<div class="floatLeft2"><cui:toolbar id="roleClassificationToolbar"></cui:toolbar></div>
	       		<div class="floatRight ">
	       			<span class="se-searchall">
	    				<cui:input id="roleclassification_search_text" type="text" width="220" placeholder="可检索角色分类名称" onEnter="roleclassificationSearchChange"></cui:input>
	    				<span class=" icon icon-search3 search-Click" onClick="roleclassificationSearchChange()"></span>
    				</span>
	       		</div>
       		</div>

		   <sec:hasPermission name="/auth/roleclassification/page">
			<cui:grid id="roleClassificationGrid" url="${ctx}/auth/roleclassification/page?filter=pId_EQ_${pId};unitId_IN_-1,${CURRENTUSER.unitId}" asyncType="post" loadonce="false" rownumbers="true" fitStyle="fill" multiselect="true" altRows="true" afterSortableRows="sortRoleClassificationGrid" onDblClickRow="onDblClickRoleClassificationGrid">
				<cui:gridCols>
					<cui:gridCol name="id" hidden="true">id</cui:gridCol>
					<cui:gridCol name="unitId" hidden="true" >单位id</cui:gridCol>
					<cui:gridCol name="isSystem" hidden="true">是否系统内置</cui:gridCol>
					<cui:gridCol name="name" width="200" formatter="roleClassificationNameFormatter">名称</cui:gridCol>
					<cui:gridCol name="roleClassKey" width="200" >角色分类值</cui:gridCol>
					<cui:gridCol name="comments" width="200" >备注</cui:gridCol>
				</cui:gridCols>
				<cui:gridPager gridId="roleClassificationGrid"></cui:gridPager>
			</cui:grid>
		   </sec:hasPermission>
       </div>
    </div>	
    <%@include file="./dialog.jsp"%> 
<script>

//工具栏
var roleClassificationToolBarData = [
	<sec:hasPermission name="/auth/roleclassification/create">
{
	"id"		: "new",
	"label"		: "新增",
	"disabled"	: "false",
	"onClick"		: "addOrUpdateRoleClassification()",
	"type"		: "button",
	"cls":"greenbtn",
	"icon":"icon-plus-circle"
},
	</sec:hasPermission>
	<sec:hasPermission name="/auth/roleclassification/update">
{
	"id"		: "update",
	"label"		: "修改",
	"disabled"	: "disable",
	"onClick"		: "updateRoleClassification()",
	"type"		: "button",
	"cls":"cyanbtn",
	"icon":"icon-pencil6"	
},
	</sec:hasPermission>
	<sec:hasPermission name="/auth/roleclassification/delete">
{
	"id"		: "delete",
	"label"		: "删除",
	"onClick"		: "deleteRoleClassificationBatch()",
	"disabled"	: "false",
	"type"		: "button",
	"cls":"deleteBtn",
	"icon":"icon-bin"	
},
	</sec:hasPermission>
	{}
];

$(function(){
	$("#roleClassificationToolbar").toolbar({
		data : roleClassificationToolBarData
	});
})

//格式化角色分类显示名称
function roleClassificationNameFormatter(cellValue, options, rowObject){
	if(rowObject.isSystem == '<%=Constants.Common.YES%>'){
		cellValue = "<span style='color:#A3B0C9;'>" + cellValue + "</span>";
	}
	else if(rowObject.unitId == '-1'){
		cellValue = "<span style='color:#6EA8EF;'>" + cellValue + "</span>";
	}
	return cellValue;
}

<sec:hasPermission name="/auth/roleclassification/update">
//双击列表行
function onDblClickRoleClassificationGrid(e,ui){
	addOrUpdateRoleClassification(ui.rowId);
}
</sec:hasPermission>

//角色分类拖动排序, 角色分类拖动排序较为特殊, 需自行处理
function sortRoleClassificationGrid(e, ui){
	
	var grid = $("#"+e.target.id);
	
	if(!isSuperAdmin("${CURRENTUSER.loginName}")){
		var rowObj = grid.grid("getRowData",ui.itemId[0]);
		if(rowObj.unitId == -1){
			warning("不能够对公共角色分类进行操作");
			return;
		}
	}

	var url = grid.grid("option","url");
	if(!url)return;
	url = url.substring(0,url.lastIndexOf("/"));
	url = url+"/sort";
	var sortAfterIDs = ui.permutation.join();
	var sortBeforeIDs = ui.originalPermutation.join();
	
	var beforeSortPublicFirstId = null;
	for(var i=0 ; i<ui.originalPermutation.length ; i++){
		var rowObj = grid.grid("getRowData",ui.originalPermutation[i]);
		if(rowObj.unitId == -1){
			beforeSortPublicFirstId = rowObj.id;
			break;
		}
	}
	
	 $.ajax({
		type: 'post',
		url	: url,
		data: {
				"sortAfterIDs": sortAfterIDs,
				"sortBeforeIDs":sortBeforeIDs,
				"beforeSortPublicFirstId" : beforeSortPublicFirstId
		},
		dataType: 'json',
		success	: function(data){
			 message("排序成功");
		},
		error: function(e){
			error(e);
		}
	});
}
function addOrUpdateRoleClassification(id){
	var form = $("#roleClassificationForm");
	var grid = $("#roleClassificationGrid");
	var leftTree = $("#"+"roleClassificationTree");
	var selNode = leftTree.tree("getSelectedNodes");
	if(!selNode){
		warning("请选择左侧节点");
		return;
	}
	form.form("clear");
	var url = null;
	
	var addFlag = true;
	if(id){//修改  在dialog中 如果有id传入的话 那么就是修改 否则的话就是新增
		var rowData = grid.grid("getRowData",id);
		if(rowData.isSystem == '<%=Constants.Code.IS_SYSTEM%>'){
			warning("不能修改系统内置数据");
			return;
		}
		if(rowData.unitId == -1 && !isSuperAdmin("${CURRENTUSER.loginName}")){
			warning("不能修改公共的角色分类")
			return;
		}
		$("#roleClassificationForm input[name='roleClassKey']").textbox("option","readonly",true);
		url = "${ctx}/auth/roleclassification/update";
		form.form("load","${ctx}/auth/roleclassification/load?id="+id);
		addFlag = false;

	}else{//新增
		url = "${ctx}/auth/roleclassification/create";
		$("#roleClassificationForm input[name='roleClassKey']").textbox("setValue",selNode[0].roleClassKey + ".");
		$("#roleClassificationForm input[name='roleClassKey']").textbox("option","readonly",false);
		$("#roleClassificationForm input[name='pId']").textbox("setValue","${pId}");
		$("#roleClassificationForm input[name='unitId']").textbox("setValue",'${CURRENTUSER.unitId}');
		$("#roleClassificationForm input[name='isSystem']").textbox("setValue",'<%=Constants.Common.NO%>');
		addFlag = true;
	}
	
	dialog(
		"#roleClassificationDialog",
		{
			width : 400,
			title : addFlag ? "新增角色分类" : "修改角色分类"
		},
		[
		 	{
		 		text	:	"保存",
		 		id		:	"roleClassificationSaveButton",
		 		click	:	function(){
				 			if(form.form("valid")){
								var _this = this;
								loading("保存中...");
								$.ajax({
									type		:	'POST',
									url			:	url,
									data		:	form.form("formData",false),
									dataType	:	'json',
									success		:	function(data){
														addOrUpdateLeftTreeNode(leftTree,data,addFlag)
														message("保存成功");
														grid.grid("reload");
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
		 		text	:	"关闭",
		 		id		:	"roleClassificationCloseButton",
		 		click	:	function(){
		 					$(this).dialog("close");
		 		}
		 	}
		]
	).dialog("open");
	
}

function updateRoleClassification(){
	var grid = $("#roleClassificationGrid");
	
	var sel = grid.grid("option","selarrrow");  //只读属性。当multiselect 为true时，包含当前选定的行。此为一维数组，值为表格中选定行的ID。
	
	if(sel.length != 1){
		warning("请选择一条记录");
		return;
	}
	
	addOrUpdateRoleClassification(sel[0]);
	/*
	 此处可以获取选中行的对象
	 for(var i=0; i<sel.length; i++){
		var rowData = grid.grid("getRowData",sel[i]);
		console.log(rowData);
	} */
}

function deleteRoleClassificationBatch(){
	
	var grid = $("#roleClassificationGrid");
	var leftTree = $("#"+"roleClassificationTree");
	var selNode = leftTree.tree("getSelectedNodes");
	if(!selNode){
		warning("请选择左侧节点");
		return;
	}
	var ids = grid.grid("option","selarrrow");
	
	if(ids.length < 1){
		warning("请至少选择一条记录");
		return;
	}

	var names = "";
	for(var i=0; i<ids.length; i++){
		var rowData = grid.grid("getRowData",ids[i]);

		if(rowData.isSystem == '<%=Constants.Code.IS_SYSTEM%>'){
			warning("不能删除系统内置数据");
			return;
		}
		if(rowData.unitId == '-1' && !isSuperAdmin("${CURRENTUSER.loginName}")){
			warning("不能删除公共角色分类");
			return;
		}
		names += "," + rowData.name;
	}
	names = names.substring(1,names.length);
	
	$.confirm("确认要删除吗?",function(r){
		if(r){
			loading("删除中...");
			$.ajax({
				type		:	'POST',
				url			:	'${ctx}/auth/roleclassification/delete',
				data		:	{id : ids.toString(), name : names},
				dataType	:	'json',
				success		:	function(data){
									deleteLeftTreeNode(leftTree,ids);
									message("删除成功");
									grid.grid("reload");
									hide();
									
				},
				error		:	function(e){
									debugger;
									error(e);
									hide();
				}	
			})
		}
	})

}

//搜索
function roleclassificationSearchChange(){
	var url = "${ctx}/auth/roleclassification/page?filter=pId_EQ_${pId};unitId_IN_-1,${CURRENTUSER.unitId}";
	var searchText = $("#roleclassification_search_text").textbox("getValue");
	if(searchText){
		url += ";name_LIKE_"+searchText ;
	}
	var grid = $("#roleClassificationGrid");
	grid.grid("option","url",url);
	grid.grid("reload");
}
</script>
</body>
</html>