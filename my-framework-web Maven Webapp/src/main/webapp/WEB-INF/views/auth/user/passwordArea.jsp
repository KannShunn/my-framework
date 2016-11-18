 <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
 
 		   		
		   		<div class="trBorder">
		   			<table cellspacing="0" cellpadding="0">
		   				<tr>
		   					<td class="tdLabel">密码：</td>
		   					<td class="tdElement"><cui:input maxlength="40" name="password" width="200" pattern="//^[A-Za-z0-9_]{6,18}$//" errMsg="长度在6~18之间，只能包含字母、数字和下划线" type="password" errMsgPosition="right" ></cui:input></td>
		   				</tr>
		   			</table>
		   		</div>
		   		<div class="trBorder">
		   			<table cellspacing="0" cellpadding="0">
		   				<tr>
		   					<td class="tdLabel">确认密码：</td>
		   					<td class="tdElement"><cui:input width="200" maxlength="40" name="confirmPassword" valid="checkPasswordCorrectness" pattern="//^[A-Za-z0-9_]{6,18}$//" errMsg="长度在6~18之间，只能包含字母、数字和下划线" type="password" errMsgPosition="right" ></cui:input></td>
		   				</tr>
		   			</table>
		   		</div>
		   		