 <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>

<cui:dialog id="roleClassificationDialog" autoOpen="false">
	 	<cui:form id="roleClassificationForm" action="${ctx}/roleclassification">
	 		<cui:input name="id" type="hidden"></cui:input>
	 		<cui:input name="pId" type="hidden"></cui:input>
	 		<cui:input name="showOrder" type="hidden"></cui:input>
	 		<cui:input name="unitId" type="hidden"></cui:input>
	 		<cui:input name="createUserId" type="hidden"></cui:input> 
			<cui:input name="createUserName" type="hidden"></cui:input> 
			<cui:input name="createTime" type="hidden"></cui:input>
			<cui:input name="isSystem" type="hidden"></cui:input>

	 		<div class="tableBorder">
		   		<div class="trBorder">
		   			<table cellspacing="0" cellpadding="0">
		   				<tr>
		   					<td class="tdLabel">角色分类名称：</td>
		   					<td class="tdElement"><cui:input name="name" width="200" maxlength="150" required="true" valid="checkUnique" uniqueScope="pId,unitId" onBlur="onBlurRoleClassificationName"></cui:input></td>
		   				</tr>
		   			</table>
		   		</div>
		   		<div class="trBorder">
		   			<table cellspacing="0" cellpadding="0">
		   				<tr>
		   					<td class="tdLabel">角色分类值：</td>
		   					<td class="tdElement"><cui:input name="roleClassKey" width="200" maxlength="150" required="true" pattern="//^[A-Za-z0-9.-]+$//" errMsgPosition="right" errMsg="请输入字母数字" valid="checkUnique"></cui:input></td>
		   				</tr>
		   			</table>
		   		</div>
		   		<div class="trBorder">
		   			<table cellspacing="0" cellpadding="0">
		   				<tr>
		   					<td class="tdLabel">备注：</td>
		   					<td class="tdElement"><cui:textarea name="comments" maxlength="500" width="200"></cui:textarea></td>
		   				</tr>
		   			</table>
		   		</div>
		    </div>
	 	</cui:form>
 </cui:dialog>
 
 
 <script type="text/javascript">
	//角色姓名光标失焦时, 自动生成code
	function onBlurRoleClassificationName(e){
		var pinYin = makePy(this.value);
		
		if(!this.form.id.value){
			var $roleClassKey = $("#roleClassificationForm input[name='roleClassKey']");
			var roleClassKey = $roleClassKey.textbox("getValue");
			$roleClassKey.textbox("setValue",roleClassKey + pinYin);
		}
	}
 
 </script>