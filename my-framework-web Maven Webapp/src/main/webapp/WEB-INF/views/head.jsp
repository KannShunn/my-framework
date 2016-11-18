<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%@taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<!--head begin-->
    	<div class="w-head clearfix">
    		<div class="w-h-l">
    			<img src="${ctx}/res/resource/style/css/images/logo.png" class="logo">
    		</div>
    		<div class="w-h-M">
    			<div class="w-h-m-c">
    				<ul id="headMenu" class="headMenu">
						<sec:hasPermission name="/auth">
   						<li class="current" id="head_auth" onclick="toDisplay('Auth')" title="系统管理"><i class="xt"></i><span>系统管理</span></li>
						</sec:hasPermission>

    				</ul>
    			</div>
    		</div>
    		<div class="w-h-r">

    			<div class="userboxShow">
    				<div class="usernameShow">
    					<span class="userIconspan"></span>
    					<span class="userlengh">${CURRENTUSER.name}</span>
    					<span class="dropIconspan down" id="c_drop"></span>
    				</div>
    				<div class="topicBox">
    					<ul>
							<sec:hasPermission name="/auth/user/changeSelfPassword">
							<li id="c_changePassword" onclick="changeSelfPassword();">修改密码</li>
							</sec:hasPermission>
							<sec:hasPermission name="/auth/user/update">
							<li id="c_changeInfo" onclick="changeSelfInfo();">修改资料</li>
							</sec:hasPermission>
    						<li id="c_logout" onclick="logout();">退出系统</li>
    					</ul>
    				</div>
    			</div>

    			<div class="dropsel">
    				
    			</div>

    		</div>
		</div>
		<!-- 修改个人密码对话框 -->
		<%@include file="./auth/user/changePasswordDialog.jsp"%>
		<!-- 修改个人资料对话框 -->
		<c:choose>
			<c:when test="${CURRENTUSER.isAdmin eq '0'}">
				<%@include file="./auth/user/superDialog.jsp"%>
			</c:when>
			<c:when test="${CURRENTUSER.isAdmin eq '1'}">
				<%@include file="./auth/user/dialog.jsp"%>
			</c:when>
		</c:choose>

    	<!--head end-->
	
<script type="text/javascript">

	$.fn['dialog'].defaults = {
			show: {
				effect: "blind",
				duration: 2800
			}
	};
	
	//页面加载完毕后获取下拉框的value值
	$(function(){
		$('.dropsel .coral-combo-arrow').click(function(){
			//选择下拉框样式
			$('#combobox_i1_0').parents('.coral-combo-panel').css('border','0');
		})

		//退出系统
		$('.userboxShow').mouseover(function(e){
			e.stopPropagation();
			$('.dropIconspan').addClass('up').removeClass('down');
			$('.topicBox').show();
		}).mouseleave(function(){
			$('.dropIconspan').addClass('down').removeClass('up');
			$('.topicBox').hide();
		})

		//菜单 多余
		$('.siteMaintenance').mouseover(function(){
			$(this).children('.droparrow').addClass('up2').removeClass('down2');
			$('.moremenudrop').show();
		}).mouseleave(function(){
			$(this).children('.droparrow').addClass('down2').removeClass('up2');
			$('.moremenudrop').hide();
		})

	});

	//修改自身密码
	function changeSelfPassword(){
		var form = $("#changePasswordForm${idSuffix}");
		var url = "${ctx}/auth/user/changeSelfPassword";

		form.form("clear");
		$("#oldPasswordArea${idSuffix}").show();
		$("#changePasswordArea${idSuffix}").show();
		$("#changePasswordForm${idSuffix} input[name='id']").textbox("setValue","${CURRENTUSER.id}");
		$("#changePasswordForm${idSuffix} input[name='name']").textbox("setValue","${CURRENTUSER.name}");
		dialog(
				"#changePasswordDialog${idSuffix}",
				{
					width : 380,
					title : "修改密码"
				},
				[
					{
						text	:	"保存",
						id		:	"changeSelfPasswordSaveButton",
						click	:	function(){
							var _this = this;
							if (form.form("valid")) {
								loading("保存中...");
								var formData = form.form("formData", false);
								debugger;
								$.ajax({
									type		:'post',
									url			: url,
									data		: formData,
									dataType	: 'json',
									success		:	function(data){
										message("修改成功");
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
						id		:	"changeSelfPasswordCloseButton",
						click	:	function(){
							$(this).dialog("close");
						}
					}
				]).dialog("open");
	}

	function changeSelfInfo(){
		var userForm = $("#userForm${idSuffix}");

		userForm.form("clear");

		var url= ctx + '/auth/user/update';
		userForm.form("load", ctx + '/auth/user/load?id=' + "${CURRENTUSER.id}");

		$("#passwordArea${idSuffix}").hide();
		$("#userForm${idSuffix} input[name='loginName']").textbox("option","disabled",true);
		if("${CURRENTUSER.urlPath}"){
			$("#userImg${idSuffix}").attr("src","${ctx}/upload/images/origin/" + "${CURRENTUSER.urlPath}");
		}else{
			$("#userImg${idSuffix}").attr("src","${ctx}/res/resource/style/images/addpicture.png");
		}
		var title="修改个人资料";

		dialog(
				"#userDialog${idSuffix}",
				{
					width : 700,
					height : 600,
					title : title
				},
				[{
					text	:	"保存",
					id		:	"changeSelfUserSaveButton",
					click	:	function () {
						var _this = this;
						if (userForm.form("valid")) {
							loading("修改中...");
							$.ajax({
								type		: 'post',
								url			: url,
								data		: userForm.form("formData", false),
								dataType 	: 'json',
								success		: function(data) {
									hide();
									message("修改成功！");
									$(_this).dialog("close");
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
					id		:	"changeSelUserCloseButton",
					click	:	function () {
						$(this).dialog("close");
					}
				}
				]).dialog("open");
	}


	function logout(){
		$.confirm("确定要退出系统吗？", function(r) {
			if (r) {
				window.location.href="${ctx}/logout";
			}
		});
	}

</script>
