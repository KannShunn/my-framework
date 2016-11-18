 <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ include file="../../../include/global.jsp"%>

	<div class="leftTree">
		<%-- <cui:tree id="moveResourceTree" data="resData" onLoad="moveResourceTreeOnload" checkable="true" rootNode="{ name:'资源信息', id:'-1', isParent:true, open:true, nocheck : true }">					
		</cui:tree> --%>
		<cui:tree id="moveResourceTree" asyncEnable="true" asyncType="get"
				  asyncUrl="${ctx}/auth/resource/loadMoveResourceTree" asyncAutoParam="id,name" checkable="true" chkStyle="radio"  radioType="all" onLoad="moveResourceTreeOnload" simpleDataEnable="true">
		</cui:tree>
		
	</div>

<script type="text/javascript">

	function moveResourceTreeOnload(e, treeId, treeNode){
	 	var tree = $("#"+treeId);
	    var nodes = tree.tree("getNodes");
	    //nodes[0].nocheck = true;
	    //tree.tree("updateNode",nodes[0]); //去除根结点的checkbox

		//禁选的资源ids, 所有子节点包括自身都是禁选的
		var resourceIds = "${disabledResIds}".split(",");
		for(var i=0;i<resourceIds.length;i++){
			var resourceNode = tree.tree("getNodeByParam","id",resourceIds[i]);
			tree.tree("setChkDisabled",resourceNode,true);
		}
	    //因为角色授予资源重点关心的是资源, 所以此处展开两层
		tree.tree("expandNode",nodes[0], true);
		if(nodes[0].children){
			for(var i=0; i< nodes[0].children.length ; i++){
				tree.tree("expandNode",nodes[0].children[i], true);
			}
		}
	    
	}
</script>