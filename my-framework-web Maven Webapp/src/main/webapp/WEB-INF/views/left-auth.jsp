<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.cesgroup.common.global.Constants" %>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../include/global.jsp"%>

<div class="F-left" style="width:100%;">
	
	<div class="PanelLeft F-left">
    	<div class="BoxLeft">
        	<img src="${ctx}/res/resource/style/images/icon05Hit.png" width="40" height="38" alt="" />
            <h4><b>系统管理平台</b></h4>
        </div>
        <div class="clear"></div>
		<div class="menuLeftFirst">
			<ul>
				<sec:hasPermission name="/auth/user">
				<li class="hasTree" no="0" onclick="userMenuClick();refreshBlank();" >
					<img src="${ctx}/res/resource/style/css/images/iconsmall/iconyh.png">
					<span>用户管理</span>
				</li>
				</sec:hasPermission>

				<sec:hasPermission name="/auth/org">
				<li class="hasTree" no="1" onclick="orgMenuClick();refreshBlank();" >
					<img src="${ctx}/res/resource/style/css/images/iconsmall/iconzz.png">
					<span>组织管理</span>
				</li>
				</sec:hasPermission>

				<sec:hasPermission name="/auth/role">
				<li class="hasTree" no="2" onclick="roleMenuClick();refreshBlank();" >
					<img src="${ctx}/res/resource/style/css/images/iconsmall/iconjs.png">
					<span>角色管理</span>
				</li>
				</sec:hasPermission>

				<sec:hasPermission name="/auth/log">
				<li class="hasTree" no="3" onclick="refreshBlank();">
					<img src="${ctx}/res/resource/style/css/images/iconsmall/iconrz.png">
					<span>日志管理</span>
				</li>
				</sec:hasPermission>

				<sec:hasPermission name="/auth/roleclassification">
				<li class="hasTree" no="4" onclick="roleClassificationMenuClick();refreshBlank();" >
					<img src="${ctx}/res/resource/style/css/images/iconsmall/iconpd.png">
					<span>角色分类管理</span>
				</li>
				</sec:hasPermission>

				<sec:hasPermission name="/auth/resource">
				<li class="hasTree" no="7" onclick="resourceMenuClick();refreshBlank();" >
					<img src="${ctx}/res/resource/style/css/images/iconsmall/iconzy.png">
					<span>资源管理</span>
				</li>
				</sec:hasPermission>

				<sec:hasPermission name="/auth/unitChange">
				<li class="hasTree" no="8" onclick="unitChangeMenuClick();refreshBlank();" >
					<img src="${ctx}/res/resource/style/css/images/iconsmall/icongzl.png">
					<span>单位调度</span>
				</li>
				</sec:hasPermission>

				<sec:hasPermission name="/auth/code">
				<li class="hasTree" no="9" onclick="codeMenuClick();refreshBlank();" >
					<img src="${ctx}/res/resource/style/css/images/iconsmall/iconbm.png">
					<span>编码管理</span>
				</li>
				</sec:hasPermission>
			</ul>
		</div>
		
		<div class="menuLeftSecond">
			<div class="menuLeftSecondHead clearfix">
				<div class="menuLeftSecondHead2">
					<div class="BackFirst">返回</div>
					<div class="firstName"></div>
				</div>
			</div>
		<!-- 二级树beigin -->
			<div class="treebox">
				<div class="leftTree">
					<div no="0">
						<div class="margin5 se-searchall">
							<cui:input placeholder="搜索" type="text" onEnter="userTreeSearch" id="userTree_searchText"></cui:input>
							<span class="icon icon-search3 search-Click" onclick="userTreeSearch()"> </span>
						</div>
						<cui:tree id="userTree" asyncEnable="true" asyncType="get"
							asyncUrl="" asyncAutoParam="id,name" onClick="userTreeClick" onLoad="treeAsyncExpandnode" simpleDataEnable="true">					
						</cui:tree>
					</div>
					<div no="1">
						<div class="margin5 se-searchall">
							<cui:input placeholder="搜索" type="text" onEnter="orgTreeSearch" id="orgTree_searchText"></cui:input>
							<span class="icon icon-search3 search-Click" onclick="orgTreeSearch()"> </span>
						</div>
						<cui:tree id="orgTree" asyncEnable="true" asyncType="get"
							asyncUrl="" asyncAutoParam="id,name" onClick="orgTreeClick" onLoad="treeAsyncExpandnode" simpleDataEnable="true">					
						</cui:tree>
					</div>
					<div no="2">
						<div class="margin5 se-searchall">
							<cui:input placeholder="搜索" type="text" onEnter="roleTreeSearch" id="roleTree_searchText"></cui:input>
							<span class="icon icon-search3 search-Click" onclick="roleTreeSearch();"></span>
						</div>
						<cui:tree id="roleTree" simpleDataEnable="true"  asyncEnable="true" asyncType="get" asyncUrl="" asyncAutoParam="id,name" onClick="roleTreeClick" onLoad="treeAsyncExpandnode"></cui:tree>
					</div>
					<div no="3">
						<div class="menuLeftFirst2">
							<ul>
								<sec:hasPermission name="/auth/log/sysLog">
								<li><a href="#" onclick="sysLogClick()"><strong>登录日志</strong></a></li>
								</sec:hasPermission>
								<sec:hasPermission name="/auth/log/operateLog">
								<li><a href="#" onclick="operateLogClick()"><strong>操作日志</strong></a></li>
								</sec:hasPermission>
							</ul>
						</div>
					</div>
					<div no="4">
						<div class="margin5 se-searchall">
							<cui:input placeholder="搜索" type="text" onEnter="roleClassificationTreeSearch" id="roleClassificationTree_searchText"></cui:input>
							<span class="icon icon-search3 search-Click" onclick="roleClassificationTreeSearch();"></span>
						</div>
						<cui:tree id="roleClassificationTree" simpleDataEnable="true"  asyncEnable="true" asyncType="get" asyncUrl="" asyncAutoParam="id,name" onClick="roleClassificationTreeClick" onLoad="treeAsyncExpandnode"></cui:tree>
					</div>
					
					<div no="7">
						<div class="margin5 se-searchall">
							<cui:input placeholder="搜索" type="text" onEnter="resourceTreeSearch" id="resourceTree_searchText"></cui:input>
							<span class="icon icon-search3 search-Click" onclick="resourceTreeSearch()"> </span>
						</div>
						<cui:tree id="resourceTree"  asyncEnable="true" asyncType="get"
							asyncUrl="" asyncAutoParam="id,name" onClick="resourceTreeClick" onLoad="treeAsyncExpandnode" simpleDataEnable="true" >					
						</cui:tree>
					</div>
					<div no="8">
						<div class="margin5 se-searchall">
							<cui:input placeholder="搜索" type="text" onEnter="unitChangeTreeSearch" id="unitChangeTree_searchText"></cui:input>
							<span class="icon icon-search3 search-Click" onclick="unitChangeTreeSearch()"> </span>
						</div>
						<cui:tree id="unitChangeTree"  asyncEnable="true" asyncType="get"
							asyncUrl="" asyncAutoParam="id,name" onClick="unitChangeTreeClick" onLoad="treeAsyncExpandnode" simpleDataEnable="true">					
						</cui:tree>
					</div>
					<div no="9">
						<cui:tree id="codeTree"  asyncEnable="true" asyncType="get"
								  asyncUrl="" asyncAutoParam="id,name" onClick="codeTreeClick" onLoad="treeAsyncExpandnode"  simpleDataEnable="true">
						</cui:tree>
					</div>

				</div>	
			</div>
			<!-- 二级树end -->
    </div>
</div>
</div>
<script>

/***************************************************用户模块开始******************************************/
//点击用户管理菜单
function userMenuClick(){
	var tree = $("#userTree");
		
	tree.tree("option","asyncUrl","${ctx}/auth/user/getLeftOrgSyncTree");
	tree.tree("option","asyncType","get");
	tree.tree("reload");
}

//点击用户树节点
function userTreeClick(e, treeId, treeNode) {
	refreshCenter('${ctx}/auth/user/index?orgId='+treeNode.id+'&orgName='+treeNode.name);
}

//搜索用户树节点
function userTreeSearch(){
	var value = $("#userTree_searchText").textbox("getValue");
	$("#userTree").tree("filterNodesByParam", {name: value});    
}
/***************************************************用户模块结束******************************************/



/***************************************************组织模块开始******************************************/
//点击组织管理菜单
function orgMenuClick(){
	var tree = $("#orgTree");
	tree.tree("option","asyncUrl","${ctx}/auth/org/getLeftOrgSyncTree");
	tree.tree("reload");
}

//点击组织树节点
function orgTreeClick(e, treeId, treeNode) {
	refreshCenter('${ctx}/auth/org/index?orgId='+treeNode.id);
}

//搜索组织树节点
function orgTreeSearch(){
	var value = $("#orgTree_searchText").textbox("getValue");
	$("#orgTree").tree("filterNodesByParam", {name: value});    
}
/***************************************************组织模块结束******************************************/



/***************************************************角色模块开始******************************************/
//点击角色管理菜单
function roleMenuClick(){
	var tree = $("#roleTree");
	tree.tree("option","asyncUrl","${ctx}/auth/role/getLeftRoleClassificationSyncTree");
	tree.tree("reload");
}

//搜索角色树节点
function roleTreeSearch(){
	var tree = $("#roleTree");
	var value = $("#roleTree_searchText").textbox("getValue");

	tree.tree("filterNodesByParam", {name: value});    
}

//点击角色树节点
function roleTreeClick(e, treeId, treeNode){
		refreshCenter('${ctx}/auth/role/index?roleClassificationId='+treeNode.id);
}

/***************************************************角色模块结束******************************************/




/***************************************************角色分类模块开始******************************************/
//点击角色分类管理菜单
function roleClassificationMenuClick(){
	var tree = $("#roleClassificationTree");
	tree.tree("option","asyncUrl","${ctx}/auth/roleclassification/getLeftRoleClassificationSyncTree");
	tree.tree("reload");
}

//搜索角色分类树节点
function roleClassificationTreeSearch(){
	var tree = $("#roleClassificationTree");
	var value = $("#roleClassificationTree_searchText").textbox("getValue");

	tree.tree("filterNodesByParam", {name: value});    
}

//点击角色分类树节点
function roleClassificationTreeClick(e, treeId, treeNode){
	refreshCenter('${ctx}/auth/roleclassification/index?pId='+treeNode.id);
}

/***************************************************角色分类模块结束******************************************/




/***************************************************资源模块开始******************************************/
//点击资源管理菜单
function resourceMenuClick(){
	var tree = $("#resourceTree");
	tree.tree("option","asyncUrl","${ctx}/auth/resource/getLeftSyncResourceTree");
	tree.tree("reload");
}

//点击资源树节点
function resourceTreeClick(e, treeId, treeNode){
	refreshCenter('${ctx}/auth/resource/index?pId='+treeNode.id);
}

//搜索资源树节点
function resourceTreeSearch(){
	var tree = $("#resourceTree");
	var value = $("#resourceTree_searchText").textbox("getValue");
	tree.tree("filterNodesByParam", {name: value});    
}
/***************************************************资源模块结束******************************************/


/***************************************************日志模块开始******************************************/

function sysLogClick(){
	refreshCenter('${ctx}/auth/log/sysLog/index');
}
function operateLogClick(){
	refreshCenter('${ctx}/auth/log/operateLog/index');
}
/***************************************************日志模块结束******************************************/



 
 
 /***************************************************单位调度模块开始******************************************/
 //点击单位调度的菜单
 function unitChangeMenuClick(){
	 var tree=$("#unitChangeTree");
		tree.tree("option","asyncUrl","${ctx}/auth/unitChange/getUnitChangeTree");
		tree.tree("reload");
}
 
 //搜索单位调度树节点
 function unitChangeTreeSearch(){
	 var tree=$("#unitChangeTree");
		var value = $("#unitChangeTree_searchText").textbox("getValue");
		tree.tree("filterNodesByParam", {name: value});    
 }
 
 //点击单位调度的树
 function unitChangeTreeClick(e, treeId, treeNode){
	 refreshCenter('${ctx}/auth/unitChange/index?orgId='+treeNode.id);
 }
 
 
 /***************************************************单位调度模块结束******************************************/
 
 
 
 /***************************************************编码模块开始******************************************/
 //点击编码管理
 function codeMenuClick(){
	 var tree=$("#codeTree");
	 tree.tree("option","asyncUrl","${ctx}/auth/code/getAsyncCodeTree");
	 tree.tree("reload");
 }

//点击编码树
function codeTreeClick(e, treeId, treeNode){
	refreshCenter('${ctx}/auth/code/index?pId='+treeNode.id+"&code="+treeNode.code);
}
 
 /***************************************************编码模块结束******************************************/
</script>
