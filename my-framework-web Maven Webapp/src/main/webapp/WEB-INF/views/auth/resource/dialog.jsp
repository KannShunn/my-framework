 <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>

<cui:dialog id="resourceDialog" autoOpen="false">
	 	<cui:form id="resourceForm" action="${ctx}/auth/resource">
	 		<cui:input name="id" type="hidden"></cui:input>
	 		<cui:input name="pId" type="hidden"></cui:input>
	 		<cui:input name="showOrder" type="hidden"></cui:input>
	 		<cui:input name="navigateUrl" type="hidden"></cui:input>
	 		<cui:input name="businessUrl" type="hidden"></cui:input>
	 		<cui:input name="otherUrl" type="hidden"></cui:input>
	 		<cui:input name="useFunction" type="hidden"></cui:input>
	 		<cui:input name="resourceImg" type="hidden"></cui:input>
	 		<cui:input name="resourceKey" type="hidden"></cui:input>
	 		<cui:input name="moduleId" type="hidden"></cui:input>
	 		<cui:input name="sourceFile" type="hidden"></cui:input>
	 		<cui:input name="createUserId" type="hidden"></cui:input> 
			<cui:input name="createUserName" type="hidden"></cui:input> 
			<cui:input name="createTime" type="hidden"></cui:input> 
			<cui:input name="isSystem" type="hidden"></cui:input>

	 		<div class="tableBorder">
		   		<div class="trBorder">
		   			<table cellspacing="0" cellpadding="0">
		   				<tr>
		   					<td class="tdLabel">资源名称：</td>
		   					<td class="tdElement"><cui:input name="name" width="200" maxlength="150" required="true" valid="checkUnique" uniqueScope="pId"></cui:input></td>
		   				</tr>
		   			</table>
		   		</div>
		   		<div class="trBorder">
		   			<table cellspacing="0" cellpadding="0">
		   				<tr>
		   					<td class="tdLabel">资源类型：</td>
		   					<td class="tdElement"><cui:combobox name="resType" url="${ctx}/auth/code/getComboboxDataByCode?code=resType"  width="200" required="true"></cui:combobox></td>
		   				</tr>
		   			</table>
		   		</div>
				<div class="trBorder">
					<table cellspacing="0" cellpadding="0">
						<tr>
							<td class="tdLabel">是否默认：</td>
							<td class="tdElement"><cui:combobox name="isDefault" url="${ctx}/auth/code/getComboboxDataByCode?code=yesOrNo"  width="200" required="true"></cui:combobox></td>
						</tr>
					</table>
				</div>
		   		<div class="trBorder">
		   			<table cellspacing="0" cellpadding="0">
		   				<tr>
		   					<td class="tdLabel">资源URL：</td>
		   					<td class="tdElement"><cui:input name="resUrl" width="200" maxlength="200" required="true" valid="checkUnique" ></cui:input></td>
		   				</tr>
		   			</table>
		   		</div>
		   		
		   		<div class="trBorder">
		   			<table cellspacing="0" cellpadding="0">
		   				<tr>
		   					<td class="tdLabel">资源备注：</td>
		   					<td class="tdElement"><cui:textarea name="comments" maxlength="500" width="200"></cui:textarea></td>
		   				</tr>
		   			</table>
		   		</div>
		    </div>
	 	</cui:form>
 </cui:dialog>
 
 <script type="text/javascript">

	
</script>