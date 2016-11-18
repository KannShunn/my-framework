<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%@taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ include file="../../../include/global.jsp"%>
<%@ page import="com.cesgroup.common.global.Constants" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>编码管理</title>
</head>
<body>
    <div class="PanelCon">
       <div class="PanelList">
       		<div class="clearfix">
	       		<div class="floatLeft2"><cui:toolbar id="codeToolbar"></cui:toolbar></div>
	       		<div class="floatRight ">
		       			<span class="se-searchall">
		    				<cui:input id="code_search_text" type="text" width="220" placeholder="可检索编码名称" onEnter="codeSearchChange"></cui:input>
		    				<span class=" icon icon-search3 search-Click" onClick="codeSearchChange()"></span>
	    				</span>
		       	</div>
       		</div>

		   <sec:hasPermission name="/auth/code/page">
		   <cui:grid id="codeGrid" url="${ctx}/auth/code/page?filter=pId_EQ_${pId};unitId_IN_-1,${CURRENTUSER.unitId};" loadonce="false" asyncType="post" rownumbers="true" fitStyle="fill" multiselect="true" altRows="true" afterSortableRows="sortGrid" onDblClickRow="onDblClickCodeGrid">
			   <cui:gridCols>
				   <cui:gridCol name="id" hidden="true">id</cui:gridCol>
				   <cui:gridCol name="unitId" hidden="true">单位id</cui:gridCol>
				   <cui:gridCol name="isSystem" hidden="true">是否系统内置</cui:gridCol>
				   <cui:gridCol name="name" width="40" formatter="codeNameFormatter">编码名称</cui:gridCol>
				   <cui:gridCol name="code" width="40" >编码code</cui:gridCol>
				   <cui:gridCol name="comments" width="40" >备注</cui:gridCol>
				   <cui:gridCol name="status" width="40" formatter="codeStatusFormatter">状态</cui:gridCol>
				   <cui:gridCol  name="op" fixed="true" width="140" align="center" formatter="code_operateFormatter">操作选项</cui:gridCol>
			   </cui:gridCols>
			   <cui:gridPager gridId="codeGrid"></cui:gridPager>
		   </cui:grid>
	   </sec:hasPermission>
       </div>
    </div>


	<%-- 新增或修改的对话框 --%>
	  <%@include file="./dialog.jsp" %>

	<!-- 移动编码的对话框 -->
	<cui:dialog id="moveCodeDialog" onLoad="dialogLoad" autoOpen="false" reLoadOnOpen="true">
	</cui:dialog>
	 
<script>

//工具栏
var codeToolBarData = [
	<sec:hasPermission name="/auth/code/create">
{
	"id"		: "newCode",
	"label"		: "新增",
	"disabled"	: "false",
	"onClick"		: "addOrUpdateCode()",
	"type"		: "button",
	"cls":"greenbtn",
	"icon":"icon-plus-circle"
	
	
},
	</sec:hasPermission>
	<sec:hasPermission name="/auth/code/update">
{
	"id"		: "updateCode",
	"label"		: "修改",
	"disabled"	: "disable",
	"onClick"		: "updateCode()",
	"type"		: "button",
	"cls":"cyanbtn",
	"icon":"icon-pencil6"	
},
	</sec:hasPermission>
	<sec:hasPermission name="/auth/code/delete">
{
	"id"		: "deleteCode",
	"label"		: "删除",
	"onClick"		: "deleteCodeBatch()",
	"disabled"	: "false",
	"type"		: "button",
	"cls":"deleteBtn",
	"icon":"icon-bin"	
},
	</sec:hasPermission>
	<sec:hasPermission name="/auth/code/moveCode">
{
		"id"		: "moveCode",
		"label"		: "移动",
		"onClick"		: "moveCode()",
		"disabled"	: "false",
		"type"		: "button",
		"cls":"greenlight",
		"icon":"icon-move"
},
	</sec:hasPermission>
	{}
];

$(function(){
	$("#codeToolbar").toolbar({
		data : codeToolBarData
	});
})

//格式化角色分类显示名称
function codeNameFormatter(cellValue, options, rowObject){
	if(rowObject.isSystem == '<%=Constants.Common.YES%>'){
		cellValue = "<span style='color:#A3B0C9;'>" + cellValue + "</span>";
	}
	else if(rowObject.unitId == '-1'){
		cellValue = "<span style='color:#6EA8EF;'>" + cellValue + "</span>";
	}
	return cellValue;
}

function addOrUpdateCode(id){
	var form = $("#codeForm");
	var grid = $("#codeGrid");
	var tree = $("#codeTree");

	var obj=grid.grid("getRowData",id);

	form.form("clear");
	var url = null;

	$("#codeForm input[name='pId']").textbox("setValue","${pId}");
	$("#codeForm input[name='parentCode']").textbox("setValue","${parentCode}");
	var addFlag = true;
	if(id){//修改
		if(obj.isSystem == '<%=Constants.Code.IS_SYSTEM%>'){
			warning("不能修改系统内置编码");
			return;
		}
		if(obj.unitId == -1 && !isSuperAdmin("${CURRENTUSER.loginName}")){
			warning("不能修改公用的编码")
			return;
		}

		url = "${ctx}/auth/code/update";
		form.form("load","${ctx}/auth/code/load?id="+id);
		$("#codeForm input[name='code']").textbox("option", "readonly", true);
		addFlag = false;
	}else{//新增
		$("#codeForm input[name='isSystem']").textbox("setValue",'<%=Constants.Common.NO%>');
		$("#codeForm input[name='status']").textbox("setValue",'<%=Constants.Code.IN_USE%>');
		$("#codeForm input[name='unitId']").textbox("setValue",'${CURRENTUSER.unitId}');
		$("#codeForm input[name='code']").textbox("option", "readonly", false);
		url = "${ctx}/auth/code/create";
		addFlag = true;
	}
	
	dialog(
		"#codeDialog",
		{
			width : 400,
			title : addFlag?"新增编码":"修改编码"
		},
		[
		 	{
		 		text	:	"保存",
		 		id		:	"codeSaveButton",
		 		click	:	function(){
				 			if(form.form("valid")){
								var _this = this;
								loading("保存中...");
								$.ajax({
									type		:	'POST',
									url			:	url,
									data		:	form.form("formData",false),
									dataType	:	'json',
									success		:	function(data){
														message("保存成功");
														grid.grid("reload");
														$(_this).dialog("close");
														addOrUpdateLeftTreeNode(tree,data,addFlag,false);
														hide();
									},
									error		:	function(e){
														error(e);
														hide();
									}
								})
							}
			 	}
		 	},
			{
		 		text	:	"关闭",
		 		id		:	"codeCloseButton",
		 		click	:	function(){
		 						$(this).dialog("close");
		 		}
		 	}
		]
	).dialog("open");
	
}

<sec:hasPermission name="/auth/code/update">
//双击列表行修改
function onDblClickCodeGrid(e,ui){
	addOrUpdateCode(ui.rowId);
}
</sec:hasPermission>

//修改编码
function updateCode(){
	var grid = $("#codeGrid");
	
	var sel = grid.grid("option","selarrrow");
	
	if(sel.length != 1){
		warning("请选择一条记录");
		return;
	}

	addOrUpdateCode(sel[0]);
}

//删除编码
function deleteCodeBatch(){
	var grid = $("#codeGrid");
	var ids = grid.grid("option","selarrrow");
	
	if(ids.length < 1){
		warning("请至少选择一条记录");
		return;
	}

	deleteCode(ids.toString());

}

//删除编码
function deleteCode(ids){
	var grid = $("#codeGrid");
	var tree = $("#codeTree")

	var idsArray = ids.split(",");
	//获得code名称，用于日志记录
	var names = "";
	for(var i=0;i<idsArray.length; i++){
		var obj=grid.grid("getRowData",idsArray[i]);
		if(obj.isSystem == '<%=Constants.Code.IS_SYSTEM%>'){
			warning("不能删除系统内置编码");
			return;
		}
		if(obj.unitId == '-1' && !isSuperAdmin("${CURRENTUSER.loginName}")){
			warning("不能删除公共的编码");
			return;
		}
		names+=obj.name+",";
	}
	names=names.substring(0,names.length-1);

	$.confirm("确认要删除吗?",function(r){
		if(r){
			loading("删除中...");
			$.ajax({
				type		:	'POST',
				url			:	'${ctx}/auth/code/delete',
				data		:	{id : ids.toString(),name:names},
				dataType	:	'json',
				success		:	function(data){
									message("删除成功");
									grid.grid("reload");
									hide();
									deleteLeftTreeNode(tree,ids);
				},
				error		:	function(e){
									error(e);
									hide();
				}	
			})
		}
	})	
}

//格式化code状态
function codeStatusFormatter(cellValue,option,rowObject){
	if(cellValue == '<%=Constants.Code.IN_USE%>'){
		return "启用";
	}
	else if(cellValue == '<%=Constants.Code.NO_USE%>'){
		return "停用";
	}
	else{
		return "未知";
	}
}

//格式化操作栏
function code_operateFormatter(cellValue, options, rowObject){
	var result ="";

	if(rowObject.isSystem == '<%=Constants.Code.NOT_SYSTEM%>'){//非系统内置的编码才可以增删改

		//只有超级管理员可以改编码, 或者各自的单位系统管理员改各自单位的编码
		if((rowObject.unitId == '-1' && isSuperAdmin("${CURRENTUSER.loginName}")) || (rowObject.unitId != '-1' && !isSuperAdmin("${CURRENTUSER.loginName}"))){
			<sec:hasPermission name="/auth/code/update">
			result+= " <a href='javascript:addOrUpdateCode(\""+ rowObject.id +"\");' title='修改' class='grideditbtn'></a> ";
			</sec:hasPermission>

			<sec:hasPermission name="/auth/code/delete">
			result+= " <a href='javascript:deleteCode(\""+ rowObject.id+ "\");' title='删除' class='griddeletebtn'></a> ";
			</sec:hasPermission>

			<sec:hasPermission name="/auth/code/updateStatus">
			if(rowObject.status == '<%=Constants.Code.IN_USE%>'){
				result += " <a href='javascript:updateCodeStatus(\""+ rowObject.id+ "\",\"stop\");' title='停用' class='gridstopbtn'></a> ";
			} else {
				result += " <a href='javascript:updateCodeStatus(\""+ rowObject.id+ "\",\"start\");' title='启用' class='gridstartbtn'></a> ";
			}
			</sec:hasPermission>
		}
	}

	return result;
}


//启用编码
function updateCodeStatus(id,op){
	var grid = $("#codeGrid");
	$.ajax({
		type : 'post',
		url : ctx + '/auth/code/updateStatus',
		data : {
			id : id,
			op : op
		},
		dataType : "json",
		success : function(data) {
			if("start" == op){
				message("启用成功");
			}else{
				message("停用成功");
			}
			grid.grid('reload');
		},
         error: function(XMLHttpRequest, textStatus, errorThrown) {
             error(errorThrown);
         },
	});
}

//移动编码
function moveCode(){
	var grid = $("#codeGrid");
	var ids = grid.grid("option", "selarrrow");
	if(ids.length < 1){
		warning("请至少选择一条记录");
		return;
	}
	var leftTree = $("#codeTree");

	var names = "";
	for (var i = 0; i < ids.length; i++) {
		var rowData = grid.grid("getRowData", ids[i]);
		if(rowData.isSystem == '<%=Constants.Code.IS_SYSTEM%>'){
			warning("不能移动系统内置编码");
			return;
		}
		names += "," + rowData.name;
	}
	names = names.substring(1, names.length);

	var url = "${ctx}/auth/code/openMoveCodeDialog?codeIds="+ids;

	dialog(
			"#moveCodeDialog",
			{
				width : 400,
				height : 700,
				title : "移动编码",
				url : url
			},
			[
				{
					text    :    "保存",
					id      :    "moveCodeSaveButton",
					click   :    function () {
						var _this = $(this);

						var tree = $("#moveCodeTree");

						var checkedCode = tree.tree("getCheckedNodes",true);//获取全部勾选中的节点
						var newPCodeId = checkedCode[0].id; //目标结点 (新的父编码的id)
						var newPCodeName = checkedCode[0].name; //目标结点 (新的父编码的名称)
						var newParentCode = checkedCode[0].code; //目标结点 (新的父编码的code)

						loading("正在保存...");

						$.ajax({
							type		:	'POST',
							url			:	'${ctx}/auth/code/moveCode',
							data		:	{codeIds : ids.toString(), newPId : newPCodeId , newParentCode: newParentCode, name:names.toString(), newPCodeName : newPCodeName},
							dataType	:	'json',
							success		:	function(data){
												message("保存成功");
												var targetNode = leftTree.tree("getNodeByParam","id",newPCodeId);
												for (var i = 0; i < ids.length; i++) {
													var treeNode = leftTree.tree("getNodeByParam","id",ids[i]);
													leftTree.tree("moveNode",targetNode,treeNode,"inner",false);
												}
												grid.grid("reload");
												_this.dialog("close");
							},
							error		:	function(e){
												error(e);
							},
							complete	:	function(){
												hide();
							}
						})
					}
				},
				{

					text    :    "关闭",
					id      :    "moveCodeSaveCloseButton",
					click   :    function () {
						$(this).dialog("close");
					}
				}

			]).dialog("open");
}


//查询
function codeSearchChange(){
	var searchText = $("#code_search_text").textbox("getValue");

	var url = "${ctx}/auth/code/page?filter=pId_EQ_${pId};unitId_IN_-1,${CURRENTUSER.unitId};";
	if(searchText){
		url += ";name_LIKE_"+searchText ;
	}
	var grid = $("#codeGrid");
	grid.grid("option","url",url);
	grid.grid("reload");
}
</script>
</body>
</html>