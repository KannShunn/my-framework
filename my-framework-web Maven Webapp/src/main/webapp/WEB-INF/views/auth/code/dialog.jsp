 <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>

<cui:dialog id="codeDialog" autoOpen="false">
	 	<cui:form id="codeForm" action="${ctx}/auth/code">
	 		<cui:input name="id" type="hidden"></cui:input>
	 		<cui:input name="pId" type="hidden"></cui:input>
	 		<cui:input name="parentCode" type="hidden"></cui:input>
	 		<cui:input name="isSystem" type="hidden"></cui:input>
	 		<cui:input name="status" type="hidden"></cui:input>
	 		<cui:input name="unitId" type="hidden"></cui:input>
	 		<cui:input name="showOrder" type="hidden"></cui:input>
	 		<cui:input name="createUserId" type="hidden"></cui:input> 
			<cui:input name="createUserName" type="hidden"></cui:input> 
			<cui:input name="createTime" type="hidden"></cui:input> 
			
			
	 		<div class="tableBorder">
		   		<div class="trBorder">
		   			<table cellspacing="0" cellpadding="0">
		   				<tr>
		   					<td class="tdLabel">编码名称：</td>
		   					<td class="tdElement"><cui:input valid="checkUnique" name="name" width="200" maxlength="150" required="true" ></cui:input></td>
		   				</tr>
		   			</table>
		   		</div>
		   		<div class="trBorder">
		   			<table cellspacing="0" cellpadding="0">
		   				<tr>
		   					<td class="tdLabel">编码KEY：</td>
		   					<td class="tdElement"><cui:input valid="checkUnique" uniqueScope="pId" name="code" width="200" maxlength="150" required="true" ></cui:input></td>
		   				</tr>
		   			</table>
		   		</div>
		   		<div class="trBorder">
		   			<table cellspacing="0" cellpadding="0">
		   				<tr>
		   					<td class="tdLabel">备注：</td>
		   					<td class="tdElement"><cui:textarea name="comments" width="200" height="80" maxlength="500"></cui:textarea> </td>
		   				</tr>
		   			</table>
		   		</div>
		    </div>
	 	</cui:form>
 </cui:dialog>
 
 <script type="text/javascript">

</script>