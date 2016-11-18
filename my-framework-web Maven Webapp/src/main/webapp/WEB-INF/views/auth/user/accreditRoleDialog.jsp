<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ include file="../../../include/global.jsp"%>

<div class="tableBorder">
	<div class="trBorder" >
		<table cellspacing="0" cellpadding="0">
			<tr>
				<td><cui:checkbox id="user_isTempAccreditCheckbox" label="临时授权"></cui:checkbox></td>
			</tr>
			<tr class="tempAccreditDateArea">
				<td>开始时间：&nbsp;&nbsp;<cui:datepicker id="user_tempAccreditDateStart" width="200" minDate="${currentDate}" value="${currentDate}" required="true" showOn="both" dateFormat="yyyy-MM-dd"></cui:datepicker></td>
			</tr>
			<tr class="tempAccreditDateArea">
				<td>结束时间：&nbsp;&nbsp;<cui:datepicker id="user_tempAccreditDateEnd" width="200" minDate="${currentDate}" startDateId="user_tempAccreditDateStart" showOn="both" required="true" dateFormat="yyyy-MM-dd"></cui:datepicker></td>
			</tr>
			</div>
			<tr>
				<td>
					<div class="leftTree">
						<cui:tree id="accreditRoleTree" asyncEnable="true" asyncType="post"
								  asyncUrl="${ctx}/auth/user/getAccreditRoleTree?userId=${userId}"  asyncAutoParam="id,name" onLoad="accreditRoleTreeOnload"  checkable="true" chkStyle="checkbox" simpleDataEnable="true">
						</cui:tree>
					</div>
				</td>
			</tr>
		</table>
	</div>
</div>

<script>

	$(function(){
		var $tempAccreditDateArea = $(".tempAccreditDateArea");
		$tempAccreditDateArea.hide();

		$("#user_isTempAccreditCheckbox").change(function(e){
			if(this.checked){
				$tempAccreditDateArea.show();
			}else{
				$tempAccreditDateArea.hide();
			}
		});
	})


	var accreditRoleDialog = {
		oldRoleIds : null
	}

	function accreditRoleTreeOnload(e, treeId, treeNode){
		//展开所有节点
		var accreditRoleTree = $("#"+treeId);
		accreditRoleTree.tree("expandAll",true);

		//记录角色树加载完后勾选的id
		var selNode = accreditRoleTree.tree("getCheckedNodes");
		var oldRoleIdsArray = [];
		for(var i=0;i<selNode.length;i++){
			oldRoleIdsArray.push(selNode[i].id);
		}
		accreditRoleDialog.oldRoleIds = oldRoleIdsArray;
	}
</script>