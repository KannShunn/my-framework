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
	       		<div class="floatLeft2"><cui:toolbar id="roleToolbar"></cui:toolbar></div>
	       		<div class="floatRight ">
	       			<span class="se-searchall">
	    				<cui:input id="role_search_text" type="text" width="220" placeholder="可检索角色名称" onEnter="roleSearchChange"></cui:input>
	    				<span class=" icon icon-search3 search-Click" onClick="roleSearchChange()"></span>
    				</span>
	       		</div>
       		</div>

		   <sec:hasPermission name="/auth/role/page">
			<cui:grid id="roleGrid" url="${ctx}/auth/role/page?filter=roleClassificationId_EQ_${roleClassificationId};unitId_IN_-1,${CURRENTUSER.unitId}" asyncType="post" loadonce="false" rownumbers="true" fitStyle="fill" multiselect="true" altRows="true" afterSortableRows="sortRoleGrid" onDblClickRow="onDblClickRoleGrid">
				<cui:gridCols>
					<cui:gridCol name="id" hidden="true">id</cui:gridCol>
					<cui:gridCol name="unitId" hidden="true" >单位id</cui:gridCol>
					<cui:gridCol name="isSystem" hidden="true">是否系统内置</cui:gridCol>
					<cui:gridCol name="name" width="200" formatter="roleNameFormatter">名称</cui:gridCol>
					<cui:gridCol name="roleKey" width="200" >角色值</cui:gridCol>
					<cui:gridCol name="comments" width="200" >备注</cui:gridCol>
				</cui:gridCols>
				<cui:gridPager gridId="roleGrid"></cui:gridPager>
			</cui:grid>
		   </sec:hasPermission>
       </div>
    </div>
	 
	 <!-- 新增和修改的对话框 -->
	 <%@include file="./dialog.jsp" %>
	 
	 <!-- 授予资源的对话框 -->
	 <cui:dialog id="grantResourceDialog" onLoad="dialogLoad" autoOpen="false" reLoadOnOpen="true">
	 </cui:dialog>
	 
	 <!-- 授予用户的对话框 -->
	 <cui:dialog id="grantUserDialog" onLoad="dialogLoad" autoOpen="false" reLoadOnOpen="true">
	 </cui:dialog>
<script>

//工具栏
var roleToolBarData = [
	<sec:hasPermission name="/auth/role/create">
{
	"id"		: "new",
	"label"		: "新增",
	"disabled"	: "false",
	"onClick"		: "addOrUpdateRole()",
	"type"		: "button",
	"cls":"greenbtn",
	"icon":"icon-plus-circle"
},
	</sec:hasPermission>
	<sec:hasPermission name="/auth/role/update">
{
	"id"		: "update",
	"label"		: "修改",
	"disabled"	: "disable",
	"onClick"		: "updateRole()",
	"type"		: "button",
	"cls":"cyanbtn",
	"icon":"icon-pencil6"	
},
	</sec:hasPermission>
	<sec:hasPermission name="/auth/role/delete">
{
	"id"		: "delete",
	"label"		: "删除",
	"onClick"		: "deleteRoleBatch()",
	"disabled"	: "false",
	"type"		: "button",
	"cls":"deleteBtn",
	"icon":"icon-bin"	
},
	</sec:hasPermission>
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
	<sec:hasPermission name="/auth/role/grantUser">
{
	"id"		: "grantUser",
	"label"		: "授予用户",
	"onClick"		: "grantUser()",
	"disabled"	: "false",
	"type"		: "button",
	"cls":"greenlight",
	"icon":"icon-user-check"	
},
	</sec:hasPermission>
	{}
];

$(function(){
	
	var roleToolbar = $("#roleToolbar");
	roleToolbar.toolbar({
		data : roleToolBarData
	});
	
	if(isSuperAdmin("${CURRENTUSER.loginName}")){
		roleToolbar.toolbar("disableItem","grantUser");
	}
})

//格式化角色显示名称
function roleNameFormatter(cellValue, options, rowObject){
	if(rowObject.isSystem == '<%=Constants.Common.YES%>'){
		cellValue = "<span style='color:#A3B0C9;'>" + cellValue + "</span>";
	}
	else if(rowObject.unitId == -1){
		cellValue = "<span style='color:#6EA8EF;'>" + cellValue + "</span>";
	}
	return cellValue;
}

<sec:hasPermission name="/auth/role/update">
//双击列表行
function onDblClickRoleGrid(e,ui){
	addOrUpdateRole(ui.rowId);
}
</sec:hasPermission>

//角色拖动排序, 角色拖动排序较为特殊, 需自行处理
function sortRoleGrid(e, ui){
	
	var grid = $("#"+e.target.id);
	
	if(!isSuperAdmin("${CURRENTUSER.loginName}")){
		var rowObj = grid.grid("getRowData",ui.itemId[0]);
		if(rowObj.unitId == -1){
			warning("不能够对公共角色进行操作");
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
function addOrUpdateRole(id){
	var form = $("#roleForm");
	var grid = $("#roleGrid");
	var leftTree = $("#"+"roleTree");
	var selNode = leftTree.tree("getSelectedNodes");
	if(!selNode){
		warning("请选择左侧节点");
	}
	form.form("clear");
	var url = null;
	
	var addFlag = true;
	if(id){//修改  在dialog中 如果有id传入的话 那么就是修改 否则的话就是新增
		var rowData = grid.grid("getRowData",id);
		if(rowData.isSystem == '<%=Constants.Code.IS_SYSTEM%>'){
			warning("不能删除系统内置数据");
			return;
		}
		if(rowData.unitId == -1 && !isSuperAdmin("${CURRENTUSER.loginName}")){
			warning("不能修改公共的角色")
			return;
		}
		$("#roleForm input[name='roleKey']").textbox("option","readonly",true);
		url = "${ctx}/auth/role/update";
		form.form("load","${ctx}/auth/role/load?id="+id);
		addFlag = false;

	}else{//新增
		url = "${ctx}/auth/role/create";
		$("#roleForm input[name='roleKey']").textbox("setValue", selNode[0].roleClassKey + ".");
		$("#roleForm input[name='roleKey']").textbox("option","readonly",false);
		$("#roleForm input[name='unitId']").textbox("setValue",'${CURRENTUSER.unitId}');
		$("#roleForm input[name='roleClassificationId']").textbox("setValue",selNode[0].id);
		$("#roleForm input[name='isSystem']").textbox("setValue",'<%=Constants.Common.NO%>');

		addFlag = true;
	}
	
	dialog(
		"#roleDialog",
		{
			width : 400,
			title : addFlag ? "新增角色" : "修改角色"
		},
		[
		 	{
		 		text	:	"保存",
		 		id		:	"roleSaveButton",
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
		 		id		:	"roleCloseButton",
		 		click	:	function(){
		 					$(this).dialog("close");
		 		}
		 	}
		]
	).dialog("open");
	
}

function updateRole(){
	var grid = $("#roleGrid");
	
	var sel = grid.grid("option","selarrrow");  //只读属性。当multiselect 为true时，包含当前选定的行。此为一维数组，值为表格中选定行的ID。
	
	if(sel.length != 1){
		warning("请选择一条记录");
		return;
	}
	
	addOrUpdateRole(sel[0]);
}

function deleteRoleBatch(){
	var grid = $("#roleGrid");
	var leftTree = $("#"+"roleTree");
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
			 warning("不能删除公共角色");
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
				url			:	'${ctx}/auth/role/delete',
				data		:	{id : ids.toString() , name : names },
				dataType	:	'json',
				success		:	function(data){
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


//授予用户
function grantUser(){
	var grid = $("#roleGrid");
	
	var roleId = grid.grid("option","selarrrow"); 
	
	if(roleId.length != 1){
		warning("请选择一条记录");
		return;
	}
	
	var rowData = grid.grid("getRowData",roleId[0]);
	
	dialog(
			"#grantUserDialog",
			{
				width : 400,
				height : 700,
				title : "授予用户",
				url : "${ctx}/auth/role/loadOrgUserTree?roleId="+roleId
			},
			[
			 	{
			 		text	:	"保存",
			 		id		:	"grantUserSaveButton",
			 		click	:	function(){
					 			var _this = $(this);							
								var tree = $("#grantUserTree");
								var selNode = tree.tree("getCheckedNodes",true);//获取全部勾选中的节点

								//获得所有选中的用户ID
								var userIds = [];
								for(var i = 0 ; i<selNode.length ; i++){
									userIds.push(selNode[i].id);
								}
								var addUserIds = arrayCompared(userIds,grantUserDialog.oldUserIds);
								var addUserNames = "";
								if(addUserIds!=''){
									var addUserArray= new Array();
									addUserArray = addUserIds.split(",");
									for(var i=0;i<addUserArray.length;i++){
										var addUserId=addUserArray[i];
										var node = tree.tree("getNodesByParam",'id',addUserId,null)[0];
										addUserNames += ","+node.name;
									}
									addUserNames = addUserIds==''? addUserNames : addUserNames.substring(1,addUserNames.length);
								}

								var deleteUserIds = arrayCompared(grantUserDialog.oldUserIds,userIds);
								var deleteUserNames = "";
								if(deleteUserIds!=''){
									var deleteUserArray = deleteUserIds.split(",");
									for(var i=0;i<deleteUserArray.length;i++){
										var deleteUserId=deleteUserArray[i];
										var node = tree.tree("getNodesByParam",'id',deleteUserId,null)[0];
										deleteUserNames += ","+node.name;
									}
									deleteUserNames = deleteUserIds==''?deleteUserNames : deleteUserNames.substring(1,deleteUserNames.length);
								}

								//未做任何修改
								if(addUserIds==''&&deleteUserIds==''){
									hide();
									_this.dialog("close");
									message("授权成功！");
									return;
								}
								var isTempAccredit = $("#role_isTempAccreditCheckbox").checkbox("getValue") == "" ? false : true;
								var tempAccreditDateStart = $("#role_tempAccreditDateStart").datepicker("getValue");
								var tempAccreditDateEnd = $("#role_tempAccreditDateEnd").datepicker("getValue");
								
								loading("正在保存...");
								$.ajax({
									type		:	'POST',
									url			:	'${ctx}/auth/role/grantUser',
									data		:	{
														roleId : roleId.toString(),
														roleName : rowData.name ,
														addUserIds : addUserIds,
														deleteUserIds : deleteUserIds,
														addUserNames : addUserNames,
														deleteUserNames : deleteUserNames,
														isTempAccredit : isTempAccredit,
														tempAccreditDateStart : tempAccreditDateStart,
														tempAccreditDateEnd : tempAccreditDateEnd
													},
									dataType	:	'json',
									success		:	function(data){
														message("授权成功");
														_this.dialog("close");
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
			 		text	:	"关闭",
			 		id		:	"grantUserCloseButton",
			 		click	:	function(){
			 					$(this).dialog("close");
			 		}
			 	}
			]
		).dialog("open");
}

//搜索
function roleSearchChange(){
	var url = "${ctx}/auth/role/page?filter=roleClassificationId_EQ_${roleClassificationId};unitId_IN_-1,${CURRENTUSER.unitId}";
	var searchText = $("#role_search_text").textbox("getValue");
	if(searchText){
		url += ";name_LIKE_"+searchText ;
	}
	var grid = $("#roleGrid");
	grid.grid("option","url",url);
	grid.grid("reload");
}

</script>

<%@include file="commonJs.jsp"%>
</body>
</html>