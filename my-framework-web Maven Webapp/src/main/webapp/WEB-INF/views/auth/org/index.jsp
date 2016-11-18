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
	<title>组织管理</title>
</head>
<body>
    <div class="PanelCon">
       <div class="PanelList">
       		<div class="clearfix">
	       		<div class="floatLeft2"><cui:toolbar id="orgToolbar"></cui:toolbar></div>
	       		<div class="floatRight ">
	       			<span class="se-searchall">
	    				<cui:input id="org_search_text" type="text" width="220" placeholder="可检索组织名称" onEnter="orgSearchChange"></cui:input>
	    				<span class=" icon icon-search3 search-Click" onClick="orgSearchChange()"></span>
    				</span>
	       		</div>
       		</div>

		   <sec:hasPermission name="/auth/org/page">
       		<cui:grid id="orgGrid" url="${ctx }/auth/org/page?filter=pId_EQ_${orgId };status_EQ_0" loadonce="false" asyncType="post" rownumbers="true"  fitStyle="fill" multiselect="true" altRows="true" afterSortableRows="sortGrid" onDblClickRow="onDblClickOrgGrid">
		    	<cui:gridCols>
		    		<cui:gridCol name="id" hidden="true">id</cui:gridCol>
		    		<cui:gridCol name="unitId" hidden="true">unitId</cui:gridCol>
		    		<cui:gridCol name="name" width="80">组织名称</cui:gridCol>
		    		<cui:gridCol name="organizeCode" width="80">组织编码</cui:gridCol>
		    		<cui:gridCol name="organizeTypeId" width="80" formatter="formaterOrgTypeId">组织类型</cui:gridCol>
		    		<cui:gridCol name="comments" width="80">备注</cui:gridCol>
		    	</cui:gridCols>
		    	<cui:gridPager gridId="orgGrid" />
		    </cui:grid>
		   </sec:hasPermission>
       </div>
    </div>

	<!-- 新增和修改的对话框, 采用include的方式加载进来 -->
	<%@include file="./dialog.jsp" %>  
	
		<!-- 移动组织对话框, 采用请求后台的方式加载 -->
	<cui:dialog id="moveOrgDialog" title="移动组织" autoOpen="false" reLoadOnOpen="true">
	</cui:dialog>
	
	
<script>
	
	//工具栏
	var orgToolBarData = [
		<sec:hasPermission name="/auth/org/create">
	{
		"id"		: "newOrg",
		"label"		: "新增",
		"disabled"	: "false",
		"onClick"		: "addOrUpdateOrg()",
		"type"		: "button",
		"cls":"greenbtn",
		"icon":"icon-plus-circle"
		
		
	},
		</sec:hasPermission>
		<sec:hasPermission name="/auth/org/update">
	{
		"id"		: "updateOrg",
		"label"		: "修改",
		"disabled"	: "disable",
		"onClick"		: "updateOrg()",
		"type"		: "button",
		"cls":"cyanbtn",
		"icon":"icon-pencil6"	
	},
		</sec:hasPermission>
		<sec:hasPermission name="/auth/org/delete">
	{
		"id"		: "deleteOrg",
		"label"		: "删除",
		"onClick"		: "deleteOrgBatch()",
		"disabled"	: "false",
		"type"		: "button",
		"cls":"deleteBtn",
		"icon":"icon-bin"	
	},
		</sec:hasPermission>
		<sec:hasPermission name="/auth/org/moveOrg">
	{
		"id"		: "moveOrg",
		"label"		: "移动组织",
		"onClick"		: "moveOrg()",
		"disabled"	: "false",
		"type"		: "button",
		"cls":"greenlight",
		"icon":"icon-move"	
	},
		</sec:hasPermission>
		<sec:hasPermission name="/auth/org/mergeOrg">
	{
		"id"		: "mergeOrg",
		"label"		: "组织合并",
		"onClick"		: "mergeOrg()",
		"disabled"	: "false",
		"type"		: "button",
		"cls":"greenlight",
		"icon":"icon-merge"	
	},
		</sec:hasPermission>
		<sec:hasPermission name="/auth/imp/importWotalkData">
		{
			"id"		: "mergeOrg",
			"label"		: "同步组织用户",
			"onClick"		: "importOrgUserData()",
			"disabled"	: "false",
			"type"		: "button",
			"cls":"greenlight",
			"icon":"icon-spinner9"
		},
		</sec:hasPermission>
		{}
	];

	//初始化工具栏
	$(function() {
		$("#orgToolbar").toolbar({
			data : orgToolBarData
		});
	});
	
	$.parseDone(function(){
		
		if("${orgId }" == '<%=Constants.Org.TOP%>'){
			//$("#userToolBarArea").hide();
			$("#orgToolbar").toolbar("disableItem","moveOrg");
			$("#orgToolbar").toolbar("disableItem","mergeOrg");
		} else {
			//$("#userToolBarArea").show();
			$("#orgToolbar").toolbar("enableItem","moveOrg");
			$("#orgToolbar").toolbar("enableItem","mergeOrg");
		}
	});

	<sec:hasPermission name="/auth/org/update">
	//双击列表行
	function onDblClickOrgGrid(e,ui){
		addOrUpdateOrg(ui.rowId);
	}
	</sec:hasPermission>
	
	//新增或修改
	function addOrUpdateOrg(id){
		var form = $("#orgForm");
		var grid = $("#orgGrid");
		var leftTree = $("#"+"orgTree");
		var selNode = leftTree.tree("getSelectedNodes");
		var title = null;
		if(!selNode){
			warning("请选择左侧节点");
			return;
		}
		form.form("clear");
		var url = null;
		
		var addFlag = true;
		if(id){//修改  在dialog中 如果有id传入的话 那么就是修改 否则的话就是新增
			url = "${ctx}/auth/org/update";
			form.form("load","${ctx}/auth/org/load?id="+id);
			title = "修改组织";
			addFlag = false;
			$("#orgForm input[name='organizeCode']").textbox("option","readonly",true);
		}else{//新增
			url = "${ctx}/auth/org/create";
			$("#orgForm input[name='pId']").textbox("setValue","${orgId}");
			$("#orgForm input[name='status']").textbox("setValue",'<%=Constants.Org.IN_USE%>');
			$("#orgForm input[name='isSystem']").textbox("setValue",'<%=Constants.Common.NO%>');

			title = "新增组织";
			addFlag = true;
			$("#orgForm input[name='organizeCode']").textbox("setValue",selNode[0].organizeCode + ".");
			$("#orgForm input[name='organizeCode']").textbox("option","readonly",false);
		}
		
		dialog(
			"#orgDialog",
			{
				width : 400,
				title : title
			},
			[{
				   text      :    "保存",
                   id        :    "orgSaveButton",
                   click     :    function () {
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
				 text      :    "关闭",
                 id        :    "orgCloseButton",
                 click     :    function () {
                				 $(this).dialog("close");
                 }
			 }
		]).dialog("open");
		
	}
	
	function updateOrg(){
		var grid = $("#orgGrid");
		
		var sel = grid.grid("option","selarrrow");  //只读属性。当multiselect 为true时，包含当前选定的行。此为一维数组，值为表格中选定行的ID。
		
		if(sel.length != 1){
			warning("请选择一条记录");
			return;
		}
		addOrUpdateOrg(sel[0]);
	}
	
	function deleteOrgBatch(){
		var grid = $("#orgGrid");
		var leftTree = $("#"+"orgTree");
		var selNode = leftTree.tree("getSelectedNodes");
		if(!selNode){
			warning("请选择左侧节点");
		}
		var ids = grid.grid("option","selarrrow");
		
		if(ids.length < 1){
			warning("请至少选择一条记录");
			return;
		}
		
		//获得组织名称，用于日志记录
		var names = "";
		$.each(ids,function(i){
			var obj=grid.grid("getRowData",ids[i]);
			names+=obj.name+",";
		});
		names=names.substring(0,names.length-1);
		
		$.confirm("确认要删除吗?",function(r){
			if(r){
				loading("删除中...");
				$.ajax({
					type		:	'POST',
					url			:	'${ctx}/auth/org/delete',
					data		:	{id : ids.toString(),name:names.toString()},
					dataType	:	'json',
					success		:	function(data){
										deleteLeftTreeNode(leftTree,ids);
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
	
	function formaterOrgTypeId(cellValue,options,rowObject){
		if(cellValue == <%=Constants.Org.DEPARTMENT_TYPE%>){
			return "部门";
		}
		if(cellValue == <%=Constants.Org.UNIT_TYPE%>){
			return "单位";
		}
		return "未知"
	}
	
	//移动组织
	function moveOrg(){
		var grid = $("#orgGrid");
		var id = grid.grid("option", "selarrrow");
		var leftTree = $("#orgTree");

		//不支持批量
	    if(id=="" || id.length>1){
	    	warning("请选择一条记录！");
			return;
		}
		
		var rowData = grid.grid("getRowData",id);
		
		//打开移动组织对话框
		dialog(
				"#moveOrgDialog",
				{
					width : 350,
					height : 600,
					title : "移动组织",
					url : ctx + '/auth/org/openMoveOrgDialog?moveOrgId='+rowData.id+'&parentOrgId=${orgId}'
				},
				[
				 {
					text	:	"保存",
					id		:	"moveOrgSaveButton",
					click	:	function() {
						var _this = this;
						var moveOrgTree = $("#moveOrgTree");
						var selNode = moveOrgTree.tree("getCheckedNodes");
						if(selNode.length<1){
							warning("请选择一个部门！");
							return;
						}
						$.ajax({
							type		: 'post',
							url			: ctx + '/auth/org/moveOrg',
							data		: { orgId : rowData.id, unitId : rowData.unitId, targetOrgId : selNode[0].id, targetUnitId : selNode[0].unitId, orgName : rowData.name, targetOrgName : selNode[0].name },
							dataType	: "json",
							success		: function(data) {
											hide();
											message("移动组织成功！");
											var targetNode = leftTree.tree("getNodeByParam","id",selNode[0].id);
											var treeNode = leftTree.tree("getNodeByParam","id",rowData.id);
											leftTree.tree("moveNode",targetNode,treeNode,"inner",false);

											grid.grid('reload');
											$(_this).dialog("close");
							},
							error		: function(e) {
											hide();
											error(e);
							}
						});
					}
				 },
				 
				 {
					 text	:	"关闭",
					 id		:	"moveOrgCloseButton",
					 click	:	function(){
						 			$(this).dialog("close");
					 }
				 }
				]
			).dialog("open");
	}
	
	//合并组织
	function mergeOrg(){
		var form = $("#orgForm");
		var grid = $("#orgGrid");
		var leftTree = $("#"+"orgTree");
		var selNode = leftTree.tree("getSelectedNodes");
		var title = null;
		if(!selNode){
			warning("请选择左侧节点");
			return;
		}
		
		var id = grid.grid("option", "selarrrow");
		
	    if(id=="" || id.length<2){
	    	warning("请选择至少两条记录！");
			return;
		}
		
	    var mergeOrgIds = [];
	    var mergeOrgNames = [];
		for(var i=0; i<id.length; i++){
			var rowData = grid.grid("getRowData", id[i]);
			mergeOrgIds.push(rowData.id);
			mergeOrgNames.push(rowData.name);
		}	    
		
		form.form("clear");
		
		
		var url = "${ctx}/auth/org/mergeOrg";

		$("#orgForm input[name='pId']").textbox("setValue","${orgId}");
		$("#orgForm input[name='status']").textbox("setValue",<%=Constants.Org.IN_USE%>);
		
		title = "填写合并后组织信息";
		$("#orgForm input[name='organizeCode']").textbox("setValue",selNode[0].organizeCode + ".");
		$("#orgForm input[name='organizeCode']").textbox("option","readonly",false);
		
		dialog(
			"#orgDialog",
			{
				width : 400,
				title : title
			},
			[{
				   text      :    "保存",
                   id        :    "mergeOrgSaveButton",
                   click     :    function () {
			                	   if(form.form("valid")){
										var _this = this;
										loading("保存中...");
										var formData = form.form("formData",false);
										formData["mergeOrgIds"] = mergeOrgIds.toString();
										formData["mergeOrgNames"] = mergeOrgNames.toString();
										
										$.ajax({
											type		:	'POST',
											url			:	url,
											data		:	formData,
											dataType	:	'json',
											success		:	function(data){
																deleteLeftTreeNode(leftTree,mergeOrgIds);
																addOrUpdateLeftTreeNode(leftTree,data,true)
																message("合并成功");
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
				 text      :    "关闭",
                 id        :    "mergeOrgCloseButton",
                 click     :    function () {
                				 $(this).dialog("close");
                 }
			 }
		]).dialog("open");
	}

	//同步组织用户
	function importOrgUserData(){
		var grid = $("#orgGrid");
		var leftTree = $("#"+"orgTree");


		$.confirm("确认要同步吗?",function(r){
			if(r){
				loading("同步中...");
				$.ajax({
					type		:	'POST',
					url			:	'${ctx}/auth/imp/importWotalkData',
					dataType	:	'json',
					success		:	function(data){
						message("同步成功");
						grid.grid("reload");
						leftTree.tree("reload");
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

//搜索
function orgSearchChange(){
	var url = "${ctx }/auth/org/page?filter=pId_EQ_${orgId };status_EQ_0";
	var searchText = $("#org_search_text").textbox("getValue");
	if(searchText){
		url += ";name_LIKE_"+searchText ;
	}
	var grid = $("#orgGrid");
	grid.grid("option","url",url);
	grid.grid("reload");
}
</script>
</body>
</html>