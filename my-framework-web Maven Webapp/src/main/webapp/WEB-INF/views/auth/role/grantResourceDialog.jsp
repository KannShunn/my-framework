 <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ include file="../../../include/global.jsp"%>

	<div class="leftTree">
		<cui:tree id="grantResourceTree" asyncEnable="true" asyncType="get"
				  asyncUrl="${ctx}/auth/role/loadRoleResourceTree?roleId=${roleId}" asyncAutoParam="id,name" onLoad="grantResourceTreeOnload" checkable="true" chkboxType="{'Y':'ps','N':'s'}" simpleDataEnable="true">
		</cui:tree>
	</div>

<script type="text/javascript">

	var grantResourceDialog = {
		oldResourceIds : null
	}

	function grantResourceTreeOnload(e, treeId, treeNode){
	 	var tree = $("#"+treeId);
	    var nodes = tree.tree("getNodes");
	    nodes[0].nocheck = true;
	    tree.tree("updateNode",nodes[0]); //去除根结点的checkbox
	    
	    //因为角色授予资源重点关心的是资源, 所以此处展开两层
	    tree.tree("expandNode",nodes[0], true);
	    if(nodes[0].children){
	    	for(var i=0; i< nodes[0].children.length ; i++){
	    		 tree.tree("expandNode",nodes[0].children[i], true);
	    	}
	    }

		//记录用户树加载完后勾选的id
		var selNode = tree.tree("getCheckedNodes");
		var oldResourceIdsArray = [];
		for(var i=0;i<selNode.length;i++){
			oldResourceIdsArray.push(selNode[i].id);
		}
		grantResourceDialog.oldResourceIds = oldResourceIdsArray;
	}
</script>