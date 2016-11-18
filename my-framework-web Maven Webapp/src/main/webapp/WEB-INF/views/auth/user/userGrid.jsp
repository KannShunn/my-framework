
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>

<!-- 用户列表 -->
<cui:grid id="userGrid" url="${ctx }/auth/user/getUserPage?orgId=${orgId }" asyncType="post" loadonce="false" onDblClickRow="onDblClickUserGrid"
	rownumbers="true" rowNum="25" fitStyle="fill" multiselect="true" altRows="true" afterSortableRows="sortGrid">
	<cui:gridCols>
		<cui:gridCol name="id" hidden="true">id</cui:gridCol>
		<cui:gridCol name="orgId" hidden="true">部门id</cui:gridCol>
		<cui:gridCol name="unitId" hidden="true">单位id</cui:gridCol>
		<cui:gridCol name="urlPath" hidden="true">urlPath</cui:gridCol>
		<cui:gridCol name="loginName" width="40">登录名</cui:gridCol>
		<cui:gridCol name="name" width="40">用户姓名</cui:gridCol>
		<cui:gridCol name="email" width="40">邮箱</cui:gridCol>
		<cui:gridCol name="jobNo" width="40">工号</cui:gridCol>
		<cui:gridCol name="flagAction" width="20" revertCode="true" formatter="flagActionFormatter">锁定状态</cui:gridCol>
		<cui:gridCol name="orgName" width="40" sortable="false">所属部门</cui:gridCol>
		<cui:gridCol name="roleNames" width="40" sortable="false">角色</cui:gridCol>
		<cui:gridCol name="userType" width="20" formatter="userTypeFormatter" sortable="false" revertCode="true">专职/兼职</cui:gridCol>
		<cui:gridCol name="a_org" width="20" formatter="orgFormatter" sortable="false" align="center">部门列表</cui:gridCol>
		<cui:gridCol name="a_role" width="20" formatter="roleFormatter" sortable="false" align="center">角色列表</cui:gridCol>
	</cui:gridCols>
	<cui:gridPager gridId="userGrid" />
</cui:grid>

	<!-- 查看部门的对话框, 采用请求后台的方式加载 -->
	<cui:dialog id="lookOrgDialog" autoOpen="false" title="部门 " reLoadOnOpen="true">
	</cui:dialog>

	<!-- 查看角色的对话框, 采用请求后台的方式加载 -->
	<cui:dialog id="lookRoleDialog" autoOpen="false" title="角色" reLoadOnOpen="true">
	</cui:dialog>

<script type="text/javascript">


<sec:hasPermission name="/auth/user/update">
//双击列表行
function onDblClickUserGrid(e,ui){
	addOrUpdateUser(ui.rowId);
}
</sec:hasPermission>

function flagActionFormatter(cellValue, options, rowObject){
	var result="";
	if(rowObject.flagAction==<%=Constants.User.LOCKED%>){
		result="<font color='red'>锁定</font>";
	}else{
		result="正常";
	}
	return result;
}

/**
 * 用户grid 显示部门的超链接
 * @param cellValue
 * @param options
 * @param rowObject
 */
function orgFormatter(cellValue,options,rowObject){
	var result = "";

	<sec:hasPermission name="/auth/user/openLookOrgDialog">
		result += "<a href=\"javascript:openLookOrgDialog(\'" + rowObject.id + "\')\" >部门列表</a>";
	</sec:hasPermission>

	return result;
}

/**
 * 用户显示角色
 */
function roleFormatter(cellValue,options,rowObject){
	var result = "";

	<sec:hasPermission name="/auth/user/openLookRoleDialog">
		result += "<a href=\"javascript:openLookRoleDialog(\'" + rowObject.id + "\')\" >角色列表</a>";
	</sec:hasPermission>
	return result;
}

function openLookRoleDialog(id){
	dialog(
			"#lookRoleDialog",
			{
				resizable:false,
				width : 700,
				height : 400,
				url : "${ctx}/auth/user/openLookRoleDialog?id="+id
			},
			[
			 	{
			 		text	:	"关闭",
			 		id		:	"lookRoleCloseButton",
			 		click	:	function(){
			 						$(this).dialog("close");
			 		}
			 	}
			]).dialog("open");
}

function openLookOrgDialog(id){
	dialog(
			"#lookOrgDialog",
			{
				resizable: false,
				width : 700,
				height : 400,
				url : "${ctx}/auth/user/openLookOrgDialog?id="+id
			},
			[
			 	{
			 		text	:	"关闭",
			 		id		:	"lookOrgCloseButton",
			 		click	:	function(){
			 						$(this).dialog("close");
			 		}
			 	}
			]).dialog("open");
}

function userTypeFormatter(cellValue, options, rowObject) {
	if (cellValue == <%=Constants.User.ONJOB%>) {
		return "专职";
	} else if (cellValue == <%=Constants.User.OFFJOB%>) {
		return "兼职";
	}
	return "未知";
}

</script>
