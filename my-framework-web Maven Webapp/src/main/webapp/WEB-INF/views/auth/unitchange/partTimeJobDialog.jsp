<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ page import="com.cesgroup.common.global.Constants" %>
<%@ include file="../../../include/global.jsp"%>

	<div class="leftTree">
		<cui:tree id="unitPartTimeJobTree" asyncEnable="true" asyncType="post" 
		asyncUrl="${ctx}/auth/unitChange/getAllOrgTree"  asyncAutoParam="id,name" onLoad="unitPartTimeJobTreeOnload" checkable="true" chkStyle="checkbox" simpleDataEnable="true">
		</cui:tree>
	</div>
<script>
	function unitPartTimeJobTreeOnload(e, treeId, treeNode){
		//改变组织树
		var orgTree = $("#"+treeId);
		//根节点不带选择框
		var rootNode = orgTree.tree("getNodes");
		rootNode[0].nocheck=true;
		orgTree.tree("updateNode",rootNode[0]);
		//遍历子节点，单位类型节点取消选择框,注意nocheck与chkdisabled是有区别的，一个是不带选择框，一个是不能选择
		$(rootNode[0].children).each(function(){
			if(this.organizeTypeId == '<%=Constants.Org.UNIT_TYPE%>'){
				this.nocheck=true;
				orgTree.tree("updateNode",this);
			}
		})

		//当前用户的部门IDS通过后台传入
		var orgIds = "${orgIds}".split(",");
		for(var i=0;i<orgIds.length;i++){
			var orgNode = orgTree.tree("getNodeByParam","id",orgIds[i]);
			orgTree.tree("setChkDisabled",orgNode,true);
		}
		treeAsyncExpandnode(e, treeId, treeNode);
	}
</script>