 <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ include file="../../../include/global.jsp"%>

	<div class="leftTree">
		<cui:tree id="moveCodeTree" asyncEnable="true" asyncType="get"
				  asyncUrl="${ctx}/auth/code/getAsyncCodeTree" asyncAutoParam="id,name" checkable="true" chkStyle="radio"  radioType="all" onLoad="moveCodeTreeOnload" simpleDataEnable="true">
		</cui:tree>
		
	</div>

<script type="text/javascript">

	function moveCodeTreeOnload(e, treeId, treeNode){
	 	var tree = $("#"+treeId);
	    var nodes = tree.tree("getNodes");
	   // nodes[0].nocheck = true;
	   // tree.tree("updateNode",nodes[0]); //去除根结点的checkbox

		//禁选的编码ids, 所有子节点包括自身都是禁选的
		var codeIds = "${disabledCodeIds}".split(",");
		for(var i=0;i<codeIds.length;i++){
			var codeNode = tree.tree("getNodeByParam","id",codeIds[i]);
			tree.tree("setChkDisabled",codeNode,true);
		}
		tree.tree("expandNode",nodes[0], true);

	}
</script>