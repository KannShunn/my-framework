 <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>

<cui:dialog id="orgDialog" autoOpen="false" width="460" height="400" >
	<cui:form id="orgForm" method="post" action="${ctx}/auth/org">
		   <div class="tableBorder">
			   <cui:input name="id" width="200" type="hidden"></cui:input>
			   <cui:input name="pId" type="hidden"></cui:input>
			   <cui:input name="showOrder" type="hidden"></cui:input> 
			   <cui:input name="status" type="hidden"></cui:input>
			   <cui:input name="unitId" type="hidden"></cui:input> 
			   <cui:input name="createUserId" type="hidden"></cui:input> 
			   <cui:input name="createUserName" type="hidden"></cui:input> 
			   <cui:input name="createTime" type="hidden"></cui:input>
			   <cui:input name="isSystem" type="hidden"></cui:input>

		   		<div class="trBorder">
		   			<table cellspacing="0" cellpadding="0">
		   				<tr>
		   					<td class="tdLabel">组织名称：</td>
			   				<td class="tdElement"><cui:input name="name" width="200" valid="checkUnique" uniqueScope="pId,status" maxlength="150" required="true" onChange="onBlurOrgName"></cui:input></td>
		   				</tr>
		   			</table>
		   		</div>
		   		<div class="trBorder">
		   			<table cellspacing="0" cellpadding="0">
		   				<tr>
		   					<td class="tdLabel">组织简称：</td>
		   					<td class="tdElement"><cui:input name="abbreviation" width="200" maxlength="150"></cui:input></td>
		   				</tr>
		   			</table>
		   		</div>
		   		<div class="trBorder">
		   			<table cellspacing="0" cellpadding="0">
		   				<tr>
		   					<td class="tdLabel">组织代码：</td>
		   					<td class="tdElement"><cui:input name="organizeCode" width="200" valid="checkUnique" maxlength="150" required="true"></cui:input></td>
		   				</tr>
		   			</table>
		   		</div>
		   		<div class="trBorder">
		   			<table cellspacing="0" cellpadding="0">
		   				<tr>
		   					<td class="tdLabel">组织类型：</td>
		   					<td class="tdElement"><cui:combobox name="organizeTypeId" data="${organizeTypeIdData }" required="true" emptyText="请选择..." width="200"></cui:combobox> </td>
		   				</tr>
		   			</table>
		   		</div>
		   		<div class="trBorder">
		   			<table cellspacing="0" cellpadding="0">
		   				<tr>
		   					<td class="tdLabel">组织备注：</td>
		   					<td class="tdElement"><cui:textarea name="comments" width="200" height="80" maxlength="500"></cui:textarea> </td>
		   				</tr>
		   			</table>
		   		</div>
			   <%--<div class="trBorder">
				   <table cellspacing="0" cellpadding="0">
					   <tr>
						   <td class="tdLabel">是否开启111：</td>
						   <td class="tdElement"><input type="checkbox" class="js-switch sortswithcheck" id="testSwitchButton"/></td>
					   </tr>
				   </table>
			   </div>--%>
		    </div>
	</cui:form>
</cui:dialog>

	
<script type="text/javascript">
	//var organizeTypeIdData=[{value:"1",text:"部门",selected:true},{value:"2",text:"单位"}];

/*	$(function(){

		debugger;
		switchInit();
		$('span#testSwitchButton').addClass('switchery-blue').click();
	});*/

/* 	$(function() { 
	    $( "#organizeTypeId" ).combobox({
	        data: organizeTypeIdData
	    });
	}); */
	
	//用户姓名光标失焦时, 自动生成简拼
	function onBlurOrgName(e){
		var pinYin = makePy(this.value);

		$("#orgForm input[name='abbreviation']").textbox("setValue",pinYin);
		
		if(!this.form.id.value){
			var organizeCode = $("#orgForm input[name='organizeCode']").textbox("getValue");
			$("#orgForm input[name='organizeCode']").textbox("setValue",organizeCode + pinYin);
		}
	}
</script>