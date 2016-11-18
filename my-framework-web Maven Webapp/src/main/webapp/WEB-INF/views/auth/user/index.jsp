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
       			<div class="floatLeft2">
       				<table>
       					<tr>
       						<td style="vertical-align: middle;"><label>登录名:</label></td><td><cui:input id="s_loginName" type="text" width="200" onEnter="searchUserGrid" placeholder="查询登录名"></cui:input></td>
       						<td style="vertical-align: middle;"><label>用户姓名:</label></td><td><cui:input id="s_name" type="text" width="200" onEnter="searchUserGrid" placeholder="查询用户姓名"></cui:input></td>
							<td style="vertical-align: middle;"><label>工号:</label></td><td><cui:input id="s_jobNo" type="text" width="200" onEnter="searchUserGrid" placeholder="查询工号"></cui:input></td>
       						<td><cui:button cls="cyanbtn" id="s_userSearchButton" label="查询" icons="icon-search"  name="se"  onClick="searchUserGrid" /></td>
       					</tr>
       				</table>
       			</div>
       		</div>
       		<div class="clearfix" id="userToolBarArea">
	       		<div class="floatLeft2"><cui:toolbar id="userToolbar"></cui:toolbar></div>
       		</div>
       		
       		<!-- 用户列表, 为了多次复用而抽取出来 -->
		   <sec:hasPermission name="/auth/user/getUserPage">
       		<%@include file="./userGrid.jsp" %>
		   </sec:hasPermission>
       </div>
    </div>

	<!-- 单位系统管理员的新增和修改的对话框, 采用include的方式加载进来 -->
	<%@include file="./dialog.jsp" %>  

	
	<!-- 改变组织的对话框, 采用请求后台的方式加载 -->
	<cui:dialog id="changeOrgDialog" title="改变组织" autoOpen="false" reLoadOnOpen="true">
	</cui:dialog>
	
	<!-- 兼职的对话框, 采用请求后台的方式加载 -->
	<cui:dialog id="partTimeJobDialog" title="兼职" autoOpen="false" reLoadOnOpen="true">
	</cui:dialog>
	
	<!-- 修改密码的对话框  , 采用include的方式加载进来--> 
	<%@include file="./changePasswordDialog.jsp" %>  
	
	<!-- 启用老用户的对话框, 采用请求后台的方式加载 -->
	<cui:dialog id="renewOldManDialog" title="恢复用户" autoOpen="false" reLoadOnOpen="true">
	</cui:dialog>
	
	<!-- 授予角色的对话框, 采用请求后台的方式加载 -->
	<cui:dialog id="accreditRoleDialog" title="授予角色" autoOpen="false" reLoadOnOpen="true">
	</cui:dialog>
	 
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
		<sec:hasPermission name="/auth/user/accreditRole">
	{
		"id"		: "changeOrg",
		"label"		: "授予角色",
		"onClick"		: "accreditUserRole()",
		"disabled"	: "false",
		"type"		: "button",
		"cls":"greenlight",
		"icon":"icon-users"	
	},
		</sec:hasPermission>
		<sec:hasPermission name="/auth/user/changeOrg">
	{
		"id"		: "changeOrg",
		"label"		: "改变组织",
		"onClick"		: "changeUserOrg()",
		"disabled"	: "false",
		"type"		: "button",
		"cls":"greenlight",
		"icon":"icon-move"	
	},
		</sec:hasPermission>
		<sec:hasPermission name="/auth/user/partTimeJob">
	{
		"id"		: "partTimeJob",
		"label"		: "兼职",
		"onClick"		: "partTimeJob()",
		"disabled"	: "false",
		"type"		: "button",
		"cls":"greenlight",
		"icon":"icon-user-plus"	
	},
		</sec:hasPermission>
		<sec:hasPermission name="/auth/user/removePartTimeJob">
	{
		"id"		: "removePartTimeJob",
		"label"		: "撤销兼职",
		"onClick"		: "removePartTimeJob()",
		"disabled"	: "false",
		"type"		: "button",
		"cls":"deleteBtn",
		"icon":"icon-user-minus"	
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
		"icon":"icon-wrench"	
	},
		</sec:hasPermission>
		<sec:hasPermission name="/auth/user/renewOldToNew">
	{
		"id"		: "renewOldMan",
		"label"		: "恢复用户",
		"onClick"		: "openRenewOldManDialog()",
		"disabled"	: "false",
		"type"		: "button",
		"cls":"greenlight",
		"icon":"icon-accessibility"	
	},
		</sec:hasPermission>
		<sec:hasPermission name="/auth/user/lockUser">
		{
			"id"		: "lockUser",
			"label"		: "锁定",
			"onClick"		: "lockUser()",
			"disabled"	: "false",
			"type"		: "button",
			"cls":"greenlight",
			"icon":"icon-lock5"
		},
		</sec:hasPermission>
		<sec:hasPermission name="/auth/user/unlockUser">
	{
			"id"		: "unlockUser",
			"label"		: "解锁",
			"onClick"		: "unlockUser()",
			"disabled"	: "false",
			"type"		: "button",
			"cls":"greenlight",
			"icon":"icon-unlocked2"
	},
		</sec:hasPermission>
		{}
	
	];
	
	
	//新增或修改
	function addOrUpdateUser(id) {
		var userGrid = $("#userGrid");
		var userForm = $("#userForm${idSuffix}");
		
		userForm.form("reset");
		var rowData = userGrid.grid("getRowData",id);
		
		
		var orgId = "${orgId }";
		var url = null;
		var title = null;
		if(id){
			
			url= ctx + '/auth/user/update';
			userForm.form("load", ctx + '/auth/user/load?id=' + id);
			
			$("#passwordArea${idSuffix}").hide();
			$("#userForm${idSuffix} input[name='loginName']").textbox("option","disabled",true);
			if(rowData.urlPath){
				$("#userImg${idSuffix}").attr("src","${ctx}/upload/images/origin/" + rowData.urlPath);
			}else{
				$("#userImg${idSuffix}").attr("src","${ctx}/res/resource/style/images/addpicture.png");
			}
			title="修改用户";
		}else{
			
			url= ctx + '/auth/user/create';
			$("#userForm${idSuffix} input[name='orgId']").textbox("setValue",orgId);
			$("#userForm${idSuffix} input[name='status']").textbox("setValue",'<%=Constants.User.ONJOB%>');
			$("#userForm${idSuffix} input[name='isSystem']").textbox("setValue",'<%=Constants.Common.NO%>');

			$("#passwordArea${idSuffix}").show();
			$("#userForm${idSuffix} input[name='loginName']").textbox("option","disabled",false);
			$("#userImg${idSuffix}").attr("src","${ctx}/res/resource/style/images/addpicture.png");
			title="新增用户";
			
			//新增的就是业务用户, 业务用户的单位id是当前登录人(单位管理员)的单位id
			$("#userForm${idSuffix} input[name='unitId']").textbox("setValue",'${CURRENTUSER.unitId}');
			$("#userForm${idSuffix} input[name='isAdmin']").textbox("setValue",'<%=Constants.User.NOT_ADMIN%>');
		}
		dialog(
				"#userDialog${idSuffix}",
				{
					width : 700,
					height : 730,
					title : title
				},
				[{
				    text	:	"保存",
				    id		:	"userSaveButton",
				    click	:	function () {
				    	var _this = this;
						if (userForm.form("valid")) {
							//新增校验是否和离职登录名相同, 如果和本单位的离职登录名相同, 则提示是否恢复. 如果和其他单位的离职登录名相同, 则提示其修改.
							if(!id){
								var isThisUnitOffJobUser = false;
								var isOtherUnitOffJobUser = false;
								var oldUserId = "";
								var showMessage = "";
								var loginName = $("#userForm${idSuffix} input[name='loginName']").textbox("getValue");
								$.ajax({
									type: 'post',
									url	:  ctx + '/auth/user/isOffJobUser',
									data: {loginName : loginName},
									dataType: 'json',
									async: false,
									success	: function(data){
										//查询到有离职用户和登录名相同
										for(var key in data){
											if(key == "1"){//和本单位的离职用户登录名重复
												isThisUnitOffJobUser = true;
												showMessage = data[key];
											}else if(key == "2"){//和其他单位的离职用户登录名重复
												isOtherUnitOffJobUser = true;
												showMessage = data[key];
											}
										}
										if(isThisUnitOffJobUser){
											oldUserId = data["oldUserId"];
										}
										
									},
									error: function(e){
										error(e);
									}
								});
								if(isOtherUnitOffJobUser){
									warning(showMessage);
									return;
								}
								
								//如果和本单位的离职登录名相同, 则提示是否恢复
								if(isThisUnitOffJobUser){
									$.confirm(showMessage, function(r){
										if(r){
											loading("恢复中...");
											var dataJson = userForm.form("formData", false);
											dataJson["id"] = oldUserId;
											$.ajax({
										        type        :    'POST',
										        url         :    '${ctx}/auth/user/resumeUser',
										        data        :    dataJson, 
										        dataType    :    'json',
										        success     :    function(data){
										        					message("恢复成功");
										        					$(_this).dialog("close");
																	userGrid.grid('reload'); 
										                            hide();
										        },
										        error        :    function(e){
										                            error(e);
										                            $(_this).dialog("close");
																	userGrid.grid('reload'); 
										                            hide();
										        }    
										    }); 
										}
									});
									return;
								}
							}
							
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
						} else {
							message("未通过页面校验！");
						}
				    }        
				},
				{
				    text	:	"关闭",
				    id		:	"userCloseButton",
				    click	:	function () {
				    				$(this).dialog("close");
				    }            
				}
				]).dialog("open");
	}

	//用户列表搜索
	function searchUserGrid(e,ui){
		var loginNameText = $("#s_loginName").textbox("getValue");
		var nameText  = $("#s_name").textbox("getValue");
		var jobNoText  = $("#s_jobNo").textbox("getValue");

		var userGrid = $("#userGrid");
		var params = {};
		var filter = "";
		if(loginNameText == "" && nameText == "" && jobNoText == ""){
			 userGrid.grid("option","postData",params); 
		}else{
			if(loginNameText){
				filter = "loginName_LIKE_"+loginNameText + ";";
			}
			if(nameText){
				filter += "name_LIKE_"+nameText + ";";
			}
			if(jobNoText){
				filter += "jobNo_LIKE_"+jobNoText + ";";
			}
			params["filter"] = filter;
			userGrid.grid("option","postData",params);
		}
		userGrid.grid("reload");
	}

	//授予角色
	function accreditUserRole(){
		var userGrid = $("#userGrid");
		var id = userGrid.grid("option", "selarrrow");
		var userName =  userGrid.grid("getRowData",id[0]).name;
		//不支持批量
	    if(id=="" || id.length>1){
	    	warning("请选择一条记录！");
			return;
		}
		
	  	//打开授予角色对话框
		dialog(
				"#accreditRoleDialog",
				{
					width : 300,
					height : 600,
					title : "授予角色",
					url : ctx + '/auth/user/openAccreditRoleDialog?userId='+id[0]
				},
				[
					{
						text	:	"保存",
						id		:	"grantRoleSaveButton",
						click	:	function() {
							var _this = $(this);
							var accreditUserTree = $("#accreditRoleTree");
							var selNode = accreditUserTree.tree("getCheckedNodes");
							
							loading("授予角色中...");
							//获得所有选中的角色ID
							var roleIds = [];
							for(var i=0;i<selNode.length;i++){
								var objectType = selNode[i].type+'';
								if(objectType=='role'){
									roleIds.push(selNode[i].id);
								}
							}
							var addRoleIds = arrayCompared(roleIds,accreditRoleDialog.oldRoleIds);
							var addRoleNames = "";
							if(addRoleIds!=''){
								var addRoleArray= new Array();
								addRoleArray = addRoleIds.split(",");
								for(var i=0;i<addRoleArray.length;i++){
									var addRoleId=addRoleArray[i];
									var node = accreditUserTree.tree("getNodesByParam",'id',addRoleId,null)[0];
									addRoleNames += ","+node.name;
								}
								addRoleNames = addRoleIds==''?addRoleNames : addRoleNames.substring(1,addRoleNames.length);
							}
							var deleteRoleIds = arrayCompared(accreditRoleDialog.oldRoleIds,roleIds);
							var deleteRoleNames = "";
							if(deleteRoleIds!=''){
								var deleteRoleArray = deleteRoleIds.split(",");
								for(var i=0;i<deleteRoleArray.length;i++){
									var deleteRoleId=deleteRoleArray[i];
									var node = accreditUserTree.tree("getNodesByParam",'id',deleteRoleId,null)[0];
									deleteRoleNames += ","+node.name;
								}
								deleteRoleNames = deleteRoleIds==''?deleteRoleNames : deleteRoleNames.substring(1,deleteRoleNames.length);
							}
							//未做任何修改
							if(addRoleIds==''&&deleteRoleIds==''){
								hide();
								_this.dialog("close");
								message("授权成功！");
								return;
							}
							var isTempAccredit = $("#user_isTempAccreditCheckbox").checkbox("getValue") == "" ? false : true;
							var tempAccreditDateStart = $("#user_tempAccreditDateStart").datepicker("getValue");
							var tempAccreditDateEnd = $("#user_tempAccreditDateEnd").datepicker("getValue");

							$.ajax({
								type		: 'post',
								url			: ctx + '/auth/user/accreditRole',
								data		: {
													deleteRoleIds : deleteRoleIds,
													addRoleIds : addRoleIds,
													userId : id[0] ,
													addRoleNames : addRoleNames,
													deleteRoleNames : deleteRoleNames,
													userName : userName,
													isTempAccredit : isTempAccredit,
													tempAccreditDateStart : tempAccreditDateStart,
													tempAccreditDateEnd : tempAccreditDateEnd
												},
								dataType	: "json",
								success		: function(data) {
												hide();
												message("授权成功！");
												userGrid.grid('reload');
												_this.dialog("close");
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
						id		:	"grantUserCloseButton",
						click	:	function() {
							$(this).dialog("close");
						}
					}
				]).dialog("open");
	}
	
	//改变用户的组织
	function changeUserOrg(){
		var userGrid = $("#userGrid");
		var id = userGrid.grid("option", "selarrrow");
		
		//不支持批量
	    if(id=="" || id.length>1){
	    	warning("请选择一条记录！");
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
					url : ctx + '/auth/user/openChangeOrgDialog?userId='+id.toString()
				},
				[
				 {
					text	:	"保存",
					id		:	"changeOrgSaveButton",
					click	:	function() {
						var _this = this;
						var orgChangeTree = $("#orgChangeTree");
						var selNode = orgChangeTree.tree("getCheckedNodes");
						if(selNode.length<1){
							warning("请选择一个部门！");
							return;
						}
						$.ajax({
							type		: 'post',
							url			: ctx + '/auth/user/changeOrg',
							data		: { userId : id.toString(), newOrgId : selNode[0].id, oldOrgId : rowData.orgId, userName : rowData.name, newOrgName : selNode[0].name, oldOrgName : rowData.orgName },
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
	
	//用户兼职
	function partTimeJob(){
		var userGrid = $("#userGrid");
		var id = userGrid.grid("option", "selarrrow");
		
		//不支持批量
	    if(id=="" || id.length>1){
	    	warning("请选择一条记录！");
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
					url : ctx + '/auth/user/openPartTimeJobDialog?userId='+id.toString()
				},
				[
				 	{
				 		text	:	"保存",
				 		id		:	"partTimeJobSaveButton",
				 		click	:	function(){
							 			var _this = $(this);
										var partTimeJobTree = $("#partTimeJobTree");
										var selNode = partTimeJobTree.tree("getCheckedNodes");
										if(selNode.length<1){
											message("请选择一个部门！");
											return;
										}
										//获得所有选中的部门ID
										var orgIds = "";
										var orgNames = "";
										for(var i=0;i<selNode.length;i++){
											orgIds += orgIds==""?selNode[i].id:","+selNode[i].id;
											orgNames += orgNames==""?selNode[i].name:","+selNode[i].name;
										}
										$.ajax({
											type		: 'post',
											url			: ctx + '/auth/user/partTimeJob',
											data		: { userId : id.toString(), orgIds : orgIds, userName : rowData.name,  orgName : orgNames},
											dataType	: "json",
											success		: function(data) {
															hide();
															message("兼职成功");
															_this.dialog("close");
											},
											error		: function(e) {
															hide();
															error(e);
											}
										});
				 		}
				 	}
				 	,
				 	{
				 		text	:	"关闭",
				 		id		:	"partTimeJobCloseButton",
				 		click	:	function(){
				 						$(this).dialog("close");
				 		}
				 		
				 	}
				]).dialog("open");
	}
	
	//撤销兼职
	function removePartTimeJob(){
		var userGrid = $("#userGrid");
		var id = userGrid.grid("option", "selarrrow");
	    if(id==""){
	    	warning("请至少选择一条记录！");
			return;
		}
		
		var url = ctx + '/auth/user/removePartTimeJob';
		
		var userIds = [];
		var userNames = [];
		var orgIds = [];
		var orgNames = [];
		var unitIds = [];
		for(var i=0; i<id.length; i++){
			var rowData = userGrid.grid("getRowData",id[i]);
			
			if(rowData.userType == '<%=Constants.User.PARTTIME%>'){
				userIds.push(id[i]);
				userNames.push(rowData.name);
				orgIds.push(rowData.orgId);
				orgNames.push(rowData.orgName);
				unitIds.push(rowData.unitId)
			}
		}
		
		if(userIds.length == 0){
	    	warning("专职用户不用撤销兼职！");
			return;
		}
		
		$.confirm("确认要撤销兼职吗?", function(r){
			if(r){
				loading("撤销兼职中...")
				$.ajax({
					type		: 'post',
					url			: url,
					data		: {userIds : userIds.toString(), userNames : userNames.toString(), orgIds : orgIds.toString(), orgNames: orgNames.toString(), unitIds : unitIds.toString()},
					success		: function(data) {
									hide();
									message("撤销成功");
									userGrid.grid('reload');
					},
					error		: function(e) {
									hide();
									error(e);
					}
				});
			}
		});

	}
	
	//打开启用老用户的对话框
	function openRenewOldManDialog(){
		dialog(
				"#renewOldManDialog",
				{
					width : 800,
					height : 400,
					title : "启用老用户",
					url : "${ctx}/auth/user/openRenewOldManDialog?orgId=${orgId}&orgName=${orgName}",
					onClose : function(e,ui){
								$("#userGrid").grid("reload");
					}
				},
				[
				 	{
				 		text	:	"关闭",
				 		id		:	"renewOldManCloseButton",
				 		click	:	function(){
				 						$(this).dialog("close");
				 		}
				 	}
				]).dialog("open");
	}


	//锁定用户
	function lockUser(){
		var userGrid = $("#userGrid");
		var id = userGrid.grid("option", "selarrrow");
		if(id.length < 1){
			warning("请至少选择一条记录");
			return;
		}
		var names = "";
		for(var i=0; i<id.length; i++){
			var rowData = userGrid.grid("getRowData",id[i]);

			names += "," + rowData.name;
		}
		names = names.substring(1,names.length);

		loading("解锁中...");
		$.ajax({
			type		:	'post',
			url			:	'${ctx}/auth/user/lockUser',
			data		:	{ 	"id" : id.toString(), "name" : names },
			dataType	:	'json',
			success		:	function(data) {
				hide();
				message("锁定成功！");
				userGrid.grid('reload');
			},
			error		:	function(e) {
				hide();
				error(e);
			}
		});
	}

	//解锁用户
	function unlockUser(){
		var userGrid = $("#userGrid");
		var id = userGrid.grid("option", "selarrrow");
		if(id.length < 1){
			warning("请至少选择一条记录");
			return;
		}
		var names = "";
		for(var i=0; i<id.length; i++){
			var rowData = userGrid.grid("getRowData",id[i]);

			names += "," + rowData.name;
		}
		names = names.substring(1,names.length);

		loading("解锁中...");
		$.ajax({
			type		:	'post',
			url			:	'${ctx}/auth/user/unlockUser',
			data		:	{ 	"id" : id.toString(), "name" : names },
			dataType	:	'json',
			success		:	function(data) {
				hide();
				message("解锁成功！");
				userGrid.grid('reload');
			},
			error		:	function(e) {
				hide();
				error(e);
			}
		});
	}
</script>
<%@include file="./commonJs.jsp" %>
</body>
</html>