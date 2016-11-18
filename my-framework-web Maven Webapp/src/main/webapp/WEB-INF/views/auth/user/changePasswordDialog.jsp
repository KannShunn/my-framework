 <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>

<cui:dialog id="changePasswordDialog${idSuffix}" autoOpen="false">
	 	<cui:form id="changePasswordForm${idSuffix}" ajaxSubmit="true">
	 		<cui:input name="id" type="hidden" ></cui:input>
	 		<cui:input name="name" type="hidden" ></cui:input>
	 		
	 	
	 		<div class="tableBorder">

				<div id="oldPasswordArea${idSuffix}" style="display: none;">
					<div class="trBorder">
						<table cellspacing="0" cellpadding="0">
							<tr>
								<td class="tdLabel">原密码：</td>
								<td class="tdElement"><cui:input name="oldPassword" width="200" type="password" errMsgPosition="right" ></cui:input></td>
							</tr>
						</table>
					</div>
				</div>
	   			<!-- 密码和确认密码区域, 为了多处调用而抽取出来 -->
	   			<div id="changePasswordArea${idSuffix}" style="display: none;">
	   				<%@include file="./passwordArea.jsp"%>
	   			</div>
		    </div>
		    
	 	</cui:form>
 </cui:dialog>
	
