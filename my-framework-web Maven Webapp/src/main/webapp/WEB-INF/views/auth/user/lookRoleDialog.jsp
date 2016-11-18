
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ include file="../../../include/global.jsp"%>

<cui:grid id="lookRoleGrid" data="${roleData }" datatype="local" loadonce="false" asyncType="get" rownumbers="true"
	fitStyle="fill" altRows="true">
	<cui:gridCols>
		<cui:gridCol name="id" hidden="true">id</cui:gridCol>
		<cui:gridCol name="roleId" hidden="true">roleId</cui:gridCol>
		<cui:gridCol name="userId" hidden="true">userId</cui:gridCol>
		<cui:gridCol name="unitId" hidden="true">unitId</cui:gridCol>
		<cui:gridCol name="roleClassificationId" hidden="true">roleClassificationId</cui:gridCol>
		<cui:gridCol name="roleName" width="100">角色名称</cui:gridCol>
		<cui:gridCol name="roleKey" width="80">角色值</cui:gridCol>
		<cui:gridCol name="roleClassificationName" width="80" >角色分类</cui:gridCol>
		<cui:gridCol name="isTempAccredit" width="40" formatter="isTempAccreditFormatter">临时授权</cui:gridCol>
		<cui:gridCol name="tempAccreditDateStart" width="80">开始时间</cui:gridCol>
		<cui:gridCol name="tempAccreditDateEnd" width="80">结束时间</cui:gridCol>
	</cui:gridCols>
</cui:grid>

<script type="text/javascript">

function isTempAccreditFormatter(cellValue,option,rowObject){
	if(cellValue == <%=Constants.User.IS_TEMP_ACCREDIT_YES%>){
		return "是";
	}else{
		return "否";
	}
}

</script>