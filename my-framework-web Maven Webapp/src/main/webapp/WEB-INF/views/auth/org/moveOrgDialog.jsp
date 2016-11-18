<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ include file="../../../include/global.jsp"%>

	<div class="leftTree">
		<cui:tree id="moveOrgTree" asyncEnable="true" asyncType="post" 
		asyncUrl="${ctx}/auth/org/getLeftOrgSyncTree"  asyncAutoParam="id,name" onLoad="moveOrgTreeOnload" checkable="true" radioType="all" chkStyle="radio" simpleDataEnable="true">
		</cui:tree>
	</div>
<script>
	function moveOrgTreeOnload(e, treeId, treeNode){
		//移动组织树
		var orgTree = $("#"+treeId);
		//根节点不带选择框,注意nocheck与chkdisabled是有区别的，一个是不带选择框，一个是不能选择
		var rootNode = orgTree.tree("getNodes");
		if(rootNode[0].id == "-1"){
			rootNode[0].nocheck=true;
			orgTree.tree("updateNode",rootNode[0]);
		}

		
		//当前部门和父组织禁选, 因为移动组织却选择当前部门会出现逻辑相悖, 而选择父组织则没有意义
		var orgIds = "${orgIds}".split(",");
		for(var i=0;i<orgIds.length;i++){
			var orgNode = orgTree.tree("getNodeByParam","id",orgIds[i]);
			orgTree.tree("setChkDisabled",orgNode,true);
		}
		treeAsyncExpandnode(e, treeId, treeNode);
	}
</script>