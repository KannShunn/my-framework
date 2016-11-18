 <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>

<cui:dialog id="userDialog${idSuffix}" autoOpen="false" width="380" height="260" >
	<cui:form id="userForm${idSuffix}" action="${ctx }/auth/user">
		<cui:input name="id" width="200" type="hidden"></cui:input>
		<cui:input name="orgId" type="hidden"></cui:input>
		<cui:input name="showOrder" type="hidden"></cui:input>
		<cui:input name="unitId" type="hidden"></cui:input>
		<cui:input name="isAdmin" type="hidden"></cui:input>
		<cui:input name="status" type="hidden" value="0"></cui:input>
		<cui:input name="flagAction" type="hidden" value="0"></cui:input> 
		<cui:input name="roleId" type="hidden" ></cui:input>
	    <cui:input name="createUserId" type="hidden"></cui:input> 
	    <cui:input name="createUserName" type="hidden"></cui:input> 
	    <cui:input name="createTime" type="hidden"></cui:input> 
	    <cui:input name="isSystem" type="hidden"></cui:input>

		   <div class="tableBorder">
		   		<div class="trBorder">
		   			<table cellspacing="0" cellpadding="0">
		   				<tr>
		   					<td class="tdLabel">用户登录名：</td>
		   					<td class="tdElement"><cui:input name="loginName" width="200" required="true" valid="checkUnique" uniqueScope="status" pattern="//^[A-Za-z0-9\u4E00-\u9FA5]{1,150}$//" errMsg="长度在1-150之间的字母数字汉字" errMsgPosition="right"></cui:input></td>
		   				</tr>
		   			</table>
		   		</div>
		   		<div class="trBorder">
		   			<table cellspacing="0" cellpadding="0">
		   				<tr>
		   					<td class="tdLabel">用户姓名：</td>
		   					<td class="tdElement"><cui:input name="name" width="200" required="true" maxlength="150" ></cui:input></td>
		   				</tr>
		   			</table>
		   		</div>
		   		
	   			<!-- 密码和确认密码区域, 为了多处调用而抽取出来 -->
	   			<div id="passwordArea${idSuffix}" style="display: none;">
					<%@include file="./passwordArea.jsp" %>
		   		</div>
		    </div>
	</cui:form>
</cui:dialog>
