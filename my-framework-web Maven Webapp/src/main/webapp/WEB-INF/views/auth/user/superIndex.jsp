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
<title>系统管理</title>
</head>
<body>
    <div class="PanelCon">
       <div class="PanelList">
       		<div class="clearfix">
	       		<div class="floatLeft2" id="userToolBarArea"><cui:toolbar id="userToolbar"></cui:toolbar></div>
	       		<div class="floatRight ">
		       			<span class="se-searchall">
		    				<cui:input id="superUser_search_text" type="text" width="220" placeholder="可检索登录名" onEnter="superUser_searchChange"></cui:input>
		    				<span class=" icon icon-search3 search-Click" onClick="superUser_searchChange()"></span>
	    				</span>
		       	</div>
       		</div>

		   <sec:hasPermission name="/auth/user/getUserPage">
       		<%@include file="./userGrid.jsp" %>
		   </sec:hasPermission>
       </div>
    </div>


	<!-- 超级系统管理员的新增和修改的对话框, 采用include的方式加载进来 -->
	<%@include file="./superDialog.jsp" %>  

	 <!-- 修改密码的对话框  , 采用include的方式加载进来--> 
	<%@include file="./changePasswordDialog.jsp" %>  
<script>
	
	//工具栏
	var userToolBarData = [
		<sec:hasPermission name="/auth/user/create">
	{
		"id"		: "new",
		"label"		: "新增",
		"disabled"	: "false",
		"onClick"		: "addOrUpdateUser()",
		"type"		: "button",
		"cls":"greenbtn",
		"icon":"icon-plus-circle"

	},
		</sec:hasPermission>
		<sec:hasPermission name="/auth/user/update">
	{
		"id"		: "update",
		"label"		: "修改",
		"disabled"	: "disable",
		"onClick"		: "updateUser()",
		"type"		: "button",
		"cls":"cyanbtn",
		"icon":"icon-pencil6"	
	},
		</sec:hasPermission>
		<sec:hasPermission name="/auth/user/delete">
	{
		"id"		: "delete",
		"label"		: "删除",
		"onClick"		: "deleteUser()",
		"disabled"	: "false",
		"type"		: "button",
		"cls":"deleteBtn",
		"icon":"icon-bin"	
	},
		</sec:hasPermission>
		<sec:hasPermission name="/auth/user/initPassword">
	{
		"id"		: "initPassword",
		"label"		: "初始化密码",
		"onClick"		: "initPassword()",
		"disabled"	: "false",
		"type"		: "button",
		"cls":"greenlight",
		"icon":"icon-key5"	
	},
		</sec:hasPermission>
		<sec:hasPermission name="/auth/user/changePassword">
	{
		"id"		: "changePassword",
		"label"		: "修改密码",
		"onClick"		: "changePassword()",
		"disabled"	: "false",
		"type"		: "button",
		"cls":"greenlight",
		"icon":"icon-unlocked"	
	},
		</sec:hasPermission>
		{}
	];

	
	//新增或修改
	function addOrUpdateUser(id) {
		var userGrid = $("#userGrid");
		var userForm = $("#userForm${idSuffix}");
		
		userForm.form("clear");
		
		var orgId = "${orgId }";
		var url = null;
		var title = null;
		if(id){
			url= ctx + '/auth/user/update';
			userForm.form("load", ctx + '/auth/user/load?id=' + id);
			$("#passwordArea${idSuffix}").hide();
			$("#userForm${idSuffix} input[name='loginName']").textbox("option","disabled",true);
			title="修改单位系统管理员";
		}else{
			url= ctx + '/auth/user/create';
			$("#userForm${idSuffix} input[name='orgId']").textbox("setValue",orgId);
			$("#userForm${idSuffix} input[name='roleId']").textbox("setValue",'<%=Constants.Role.UNITSYS_ROLE%>');
			$("#userForm${idSuffix} input[name='status']").textbox("setValue",'<%=Constants.User.ONJOB%>');
			$("#userForm${idSuffix} input[name='isSystem']").textbox("setValue",'<%=Constants.Common.NO%>');

			// 根据用户选择的左侧组织, 判断是省局的组织还是监狱的组织. 如果是省局的组织则角色分类是省局, 否则是监狱
			$("#passwordArea${idSuffix}").show();
			$("#userForm${idSuffix} input[name='loginName']").textbox("option","disabled",false);

			title="新增单位系统管理员";
			// 超级管理员, 那么新增的用户是单位系统管理员, 则该用户的单位id即所选择的组织id
			$("#userForm${idSuffix} input[name='unitId']").textbox("setValue",orgId);
			$("#userForm${idSuffix} input[name='isAdmin']").textbox("setValue",'<%=Constants.User.IS_ADMIN%>');
		}
		dialog(
				"#userDialog${idSuffix}",
				{
					width : 380,
					height : 260,
					title : title
				},
				[
				 	{
				 		text	:	"保存",
				 		id		:	"userSaveButton",
				 		click	:	function(){
						 			var _this = this;
									if (userForm.form("valid")) {
										loading("保存中...");
										
										$.ajax({
											type		: 'post',
											url			: url,
											data		: userForm.form("formData", false),
											dataType 	: 'json',
											success		: function(data) {
			 												hide();
															message("操作成功！");
															$(_this).dialog("close");
															userGrid.grid('reload'); 
											},
											error		: function(e) {
															hide();
															error(e);
											}
										});
					 				}
					 			}
				 	},
				 	{
				 		text	:	"关闭",
				 		id		:	"userCloseButton",
				 		click	:	function(){
				 					$(this).dialog("close");
				 		}
				 	}
				]).dialog("open");
	}

	//查询
	function superUser_searchChange(){
		var loginNameText = $("#superUser_search_text").textbox("getValue");
		
		var userGrid = $("#userGrid");
		var params = {};
		var filter = "";
		if(loginNameText == ""){
			 userGrid.grid("option","postData",params); 
		}else{
			if(loginNameText){
				filter = "loginName_LIKE_"+loginNameText + ";";
			}
			params["filter"] = filter;
			userGrid.grid("option","postData",params);
		}
		userGrid.grid("reload");
	}
</script>
<%@include file="./commonJs.jsp" %>

</body>
</html>