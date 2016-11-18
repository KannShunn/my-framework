<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%@taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@page import="com.cesgroup.common.global.Constants" %>
<%@ include file="../../../include/global.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>单位调度</title>
</head>
<body>
    <div class="PanelCon">
       <div class="PanelList">
       		<div class="clearfix">
	       		<div class="floatLeft2"><cui:toolbar id="unitChangeToolbar"></cui:toolbar></div>
	       		<div class="floatRight ">
	       			<span class="se-searchall">
	    				<cui:input id="unitChange_search_text" type="text" width="220" placeholder="可检索用户名称" onEnter="unitChangeSearchChange"></cui:input>
	    				<span class=" icon icon-search3 search-Click" onClick="unitChangeSearchChange()"></span>
    				</span>
	       		</div>
       		</div>

       		<!-- 用户列表 -->
		   <sec:hasPermission name="/auth/unitChange/getUserPage">
			<cui:grid id="userGrid" url="${ctx }/auth/unitChange/getUserPage?orgId=${orgId }" asyncType="post" loadonce="false"
				rownumbers="true" rowNum="25" fitStyle="fill" multiselect="true" altRows="true">
				<cui:gridCols>
					<cui:gridCol name="id" hidden="true">id</cui:gridCol>
					<cui:gridCol name="orgId" hidden="true">部门id</cui:gridCol>
					<cui:gridCol name="unitId" hidden="true">单位id</cui:gridCol>
					<cui:gridCol name="urlPath" hidden="true">urlPath</cui:gridCol>
					<cui:gridCol name="loginName" width="40">登录名</cui:gridCol>
					<cui:gridCol name="name" width="40">用户姓名</cui:gridCol>
					<cui:gridCol name="email" width="40">邮箱</cui:gridCol>
					<cui:gridCol name="jobNo" width="40">工号</cui:gridCol>
					<cui:gridCol name="orgName" width="40" sortable="false">所属部门</cui:gridCol>
					<cui:gridCol name="userType" width="40" formatter="userTypeFormatter" sortable="false" revertCode="true">专职/兼职</cui:gridCol>
				</cui:gridCols>
				<cui:gridPager gridId="userGrid" />
			</cui:grid>
		   </sec:hasPermission>
       </div>
    </div>
	
	<!-- 改变组织的对话框, 采用请求后台的方式加载 -->
	<cui:dialog id="changeOrgDialog" title="改变组织" autoOpen="false" reLoadOnOpen="true">
	</cui:dialog>
	
	<!-- 兼职的对话框, 采用请求后台的方式加载 -->
	<cui:dialog id="partTimeJobDialog" title="兼职" autoOpen="false" reLoadOnOpen="true">
	</cui:dialog>
	
<script>
	
	//工具栏
	var unitChangeToolbarData = [
		<sec:hasPermission name="/auth/unitChange/changeOrg">
	{
		"id"		: "unitChangeOrg",
		"label"		: "改变组织",
		"onClick"		: "unitChangeUserOrg()",
		"disabled"	: "false",
		"type"		: "button",
		"cls":"greenbtn",
		"icon":"icon-move"	
	},
		</sec:hasPermission>
		<sec:hasPermission name="/auth/unitChange/partTimeJob">
	{
		"id"		: "unitPartTimeJob",
		"label"		: "兼职",
		"onClick"		: "unitPartTimeJob()",
		"disabled"	: "false",
		"type"		: "button",
		"cls":"greenbtn",
		"icon":"icon-user-plus"	
	},
		</sec:hasPermission>
		{}
	
	];
	
	//初始化工具栏
	$(function() {
		$("#unitChangeToolbar").toolbar({
			data : unitChangeToolbarData
		});
	});
	
	function userTypeFormatter(cellValue, options, rowObject) {
		if (cellValue == '<%=Constants.User.ONJOB%>') {
			return "专职";
		} else if (cellValue == '<%=Constants.User.OFFJOB%>') {
			return "兼职";
		}
		return "未知";
	}
	
	//跨单位改变组织
	function unitChangeUserOrg(){
		var userGrid = $("#userGrid");
		var id = userGrid.grid("option", "selarrrow");
		
		//不支持批量
	    if(id=="" || id.length>1){
			message("请选择一条记录！");
			return;
		}
		
		var rowData = userGrid.grid("getRowData",id);
		if(rowData.userType == '<%=Constants.User.PARTTIME %>'){
			warning("兼职用户不能够改变组织");
			return;
		}
		
		//打开改变组织对话框
		dialog(
				"#changeOrgDialog",
				{
					width : 350,
					height : 600,
					title : "改变组织",
					url : ctx + '/auth/unitChange/openChangeOrgDialog?userId='+id.toString()
				},
				[
				 	{
				 		text	:	"保存",
				 		id		:	"changeOrgSaveButton",
				 		click	:	function(){
						 			var _this = this;
									var orgChangeTree = $("#unitChangeOrgTree");
									var selNode = orgChangeTree.tree("getCheckedNodes");
									if(selNode.length != 1){
										message("请选择一个部门！");
										return;
									}
									
									$.ajax({
										type		: 'post',
										url			: ctx + '/auth/unitChange/changeOrg',
										data		: { userId : id.toString(), newOrgId : selNode[0].id, newUnitId : selNode[0].unitId, oldOrgId : rowData.orgId, oldUnitId : rowData.unitId, userName : rowData.name, newOrgName : selNode[0].name, oldOrgName : rowData.orgName },
										dataType	: "json",
										success		: function(data) {
														hide();
														message("改变组织成功！");
														userGrid.grid('reload');
														$(_this).dialog("close");
										},
										error		: function(e) {
														hide();
														error(e);
														$(_this).dialog("close");
										}
									});
					 	}
				 	},
					{
				 		text	:	"关闭",
				 		id		:	"changeOrgCloseButton",
				 		click	:	function(){
				 					$(this).dialog("close");
				 		}
				 	}
				]).dialog("open");
	}
	
	//跨单位兼职
	function unitPartTimeJob(){
		var userGrid = $("#userGrid");
		var id = userGrid.grid("option", "selarrrow");
		
		//不支持批量
	    if(id=="" || id.length>1){
			message("请选择一条记录！");
			return;
		}
		
		var rowData = userGrid.grid("getRowData",id);
		//打开兼职对话框
		dialog(
				"#partTimeJobDialog",
				{
					width : 350,
					height : 600,
					title : "兼职",
					url : ctx + '/auth/unitChange/openPartTimeJobDialog?userId='+id.toString()
				},
				[
				 	{
				 		text	:	"保存",
				 		id		:	"partTimeJobSaveButton",
				 		click	:	function(){
						 			var _this = $(this);
									var partTimeJobTree = $("#unitPartTimeJobTree");
									var selNode = partTimeJobTree.tree("getCheckedNodes");
									if(selNode.length<1){
										message("请选择一个部门！");
										return;
									}
									
									//获得所有选中的部门ID
									var orgIds = "";
									var orgNames = "";
									var unitIds = "";
									for(var i=0;i<selNode.length;i++){
										orgIds += orgIds==""?selNode[i].id:","+selNode[i].id;
										orgNames += orgNames==""?selNode[i].name:","+selNode[i].name;
										unitIds += unitIds==""?selNode[i].unitId:","+selNode[i].unitId;
									}
									
									$.ajax({
										type		: 'post',
										url			: ctx + '/auth/unitChange/partTimeJob',
										data		: { userId : id.toString(), orgIds : orgIds, unitIds : unitIds, userName : rowData.name , orgNames : orgNames },
										dataType	: "json",
										success		: function(data) {
														hide();
														message("兼职成功！");
														_this.dialog("close");
										},
										error		: function(e) {
														hide();
														error(e);
														$(_this).dialog("close");
										}
									});
					 	}
				 	},
					{
				 		text	:	"关闭",
				 		id		:	"partTimeJobCloseButton",
				 		click	:	function(){
				 					$(this).dialog("close");
				 		}
				 	}
				]).dialog("open");
	}
	
	//搜索
	function unitChangeSearchChange(){
		var url = "${ctx }/auth/unitChange/getUserList?orgId=${orgId }";
		var searchText = $("#unitChange_search_text").textbox("getValue");
		if(searchText){
			url += "&filter=name_LIKE_"+searchText ;
		}
		var grid = $("#userGrid");
		grid.grid("option","url",url);
		grid.grid("reload");
	}
</script>
</body>
</html>