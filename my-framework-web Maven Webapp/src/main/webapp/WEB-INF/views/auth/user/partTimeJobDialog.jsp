<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ include file="../../../include/global.jsp"%>

	<div class="leftTree">
		<cui:tree id="partTimeJobTree" asyncEnable="true" asyncType="post" 
		asyncUrl="${ctx}/auth/org/getLeftOrgSyncTree"  asyncAutoParam="id,name" onLoad="changePartTimeJobTreeOnload" checkable="true" chkStyle="checkbox" chkboxType="{ 'Y' : '', 'N' : '' }" simpleDataEnable="true">
		</cui:tree>
	</div>
<script>
	function changePartTimeJobTreeOnload(e, treeId, treeNode){
		//改变组织树
		var orgTree = $("#"+treeId);
		//根节点不带选择框,注意nocheck与chkdisabled是有区别的，一个是不带选择框，一个是不能选择
		var rootNode = orgTree.tree("getNodes");
		rootNode[0].nocheck=true;
		orgTree.tree("updateNode",rootNode[0]);
		//设置该用户当前部门及已兼职部门为不可选
	    var userTree = $("#userTree");
		var selNode = userTree.tree("getSelectedNodes");
		
		//当前用户的部门IDS通过后台传入
		var orgIds = "${orgIds}".split(",");
		for(var i=0;i<orgIds.length;i++){
			var orgNode = orgTree.tree("getNodeByParam","id",orgIds[i]);
			orgTree.tree("setChkDisabled",orgNode,true);
		}
		treeAsyncExpandnode(e, treeId, treeNode);
	}
</script>