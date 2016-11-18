 <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
 
<script type="text/javascript">
	//初始化工具栏
	$(function() {
		$("#userToolbar").toolbar({
			data : userToolBarData
		});
	});
	
	$.parseDone(function(){
		
		var userToolbar = $("#userToolbar");
		if("${orgId }" == "${CURRENTUSER.unitId}"){
			//$("#userToolBarArea").hide();
			userToolbar.toolbar("disableItem","new");
			userToolbar.toolbar("disableItem","renewOldMan");
			userToolbar.toolbar("disableItem","removePartTimeJob");
		} else {
			//$("#userToolBarArea").show();
			userToolbar.toolbar("enableItem","new");
			userToolbar.toolbar("enableItem","renewOldMan");
			userToolbar.toolbar("enableItem","removePartTimeJob");
		}
	});
	
	//从工具栏的修改按钮点进来
	function updateUser(){
		var userGrid = $("#userGrid");
		var id = userGrid.grid("option", "selarrrow");
		if(id.length != 1){
			warning("请选择一条记录");
			return;
		}
		addOrUpdateUser(id);
	}
	
	//删除用户
	function deleteUser(){
		var userGrid = $("#userGrid");
		var id = userGrid.grid("option", "selarrrow");
	    if(id==""){
	    	warning("请至少选择一条记录！");
			return;
		}
		
		var url = ctx + '/auth/user/delete';
		
		var names = "";
		for(var i=0; i<id.length; i++){
			var rowData = userGrid.grid("getRowData",id[i]);
			
			names += "," + rowData.name;
		}
		names = names.substring(1,names.length);
		
		$.confirm("确认要删除吗?", function(r){
			if(r){
				loading("删除中...")
				$.ajax({
					type		: 'post',
					url			: url,
					data		: {id : id.toString(), name : names},
					success		: function(data) {
									hide();
									message("删除成功");
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

	//初始化密码
	function initPassword(){
		var userGrid = $("#userGrid");
		var ids = userGrid.grid("option", "selarrrow");
		/* 可以接受多条记录 */
	    if(ids==""){
	    	warning("请至少选择一条记录！");
			return;
		} 
		var names = "";
		for(var i=0; i<ids.length; i++){
			var rowData = userGrid.grid("getRowData",ids[i]);
			
			names += "," + rowData.name;
		}
		names = names.substring(1,names.length);
		
		
	    $.confirm("确认初始化吗?",function(r){
			if(r){
				loading("初始化中...");
				$.ajax({
					type		:	'POST',
					url			:	'${ctx}/auth/user/initPassword',
					data		:	{userIds : ids.toString(), name : names},
					dataType	:	'json',
					success		:	function(data){
										message("初始化成功, 初始化密码为："+'<%=Constants.User.DEFAULT_PASSWORD%>');
										userGrid.grid("reload");
										hide();
										
					},
					error		:	function(e){
										error(e);
										userGrid.grid("reload");
										hide();
					}	
				})
			}
		})
	}
	
	//修改密码
	function changePassword(){
		var form = $("#changePasswordForm${idSuffix}");
		var userGrid = $("#userGrid");
		var id = userGrid.grid("option", "selarrrow");
		var url = "${ctx}/auth/user/changePassword";
		if(id.length != 1){
			warning("请选择一条记录");
			return;
		}
		
		
		// 从一个页面中跳出的对话框 和 之前的父页面是属于同一个页面中的 ， 所以可以通过选择器 进行选择
		var rowData = userGrid.grid("getRowData",id);     //选择器 进行选择 
        form.form("clear");
        $("#changePasswordArea${idSuffix}").show();
		$("#oldPasswordArea${idSuffix}").hide();
		$("#changePasswordForm${idSuffix} input[name='id']").textbox("setValue",rowData.id);//将选择出来的数据塞到想要塞的表单中去
		$("#changePasswordForm${idSuffix} input[name='name']").textbox("setValue",rowData.name); //将选择出来的数据塞到想要塞的表单中去
		dialog(
				"#changePasswordDialog${idSuffix}",
				{
					width : 380,
					title : "修改" + rowData.name + "的密码"
				},
				[
				 	{
				 		text	:	"保存",
				 		id		:	"changePasswordSaveButton",
				 		click	:	function(){
							 			var _this = this;
										if (form.form("valid")) {
				                            loading("保存中...");
											
											$.ajax({
												type		:'post',
												url			: url,
												data		: form.form("formData", false),
												dataType	: 'json',
												success		:	function(data){
																message("修改成功");
																userGrid.grid("reload");
																$(_this).dialog("close");
																hide();
												},
												error		:	function(e){
																	error(e);
																	hide();
												}
											});
										} else {
											message("未通过页面校验！");
										} 
				 		}
				 	},
				 	{
				 		text	:	"关闭",
				 		id		:	"changePasswordCloseButton",
				 		click	:	function(){
				 						$(this).dialog("close");
				 		}
				 	}
				]).dialog("open");
	}
	
</script>