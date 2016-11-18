 <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
 <%@ include file="../../../include/global.jsp"%>
 <%@ page import="com.cesgroup.common.global.Constants" %>
 
	      		<div class="clearfix">
	       		<div class="floatLeft2"></div>
	       		<div class="floatRight ">
	       			<span class="se-searchall">
	    				<cui:input id="renewOldMan_search_text" type="text" width="220" placeholder="可搜索用户名" onEnter="searchOldMan"></cui:input>
	    				<span class=" icon icon-search3 search-Click" onclick="searchOldMan()"></span>
	   				</span>
	       		</div>
	      		</div>
	    	<cui:grid id="renewOldManGrid" url="${ctx}/auth/user/page?filter=status_EQ_1;unitId_EQ_${CURRENTUSER.unitId};isAdmin_EQ_1" loadonce="false" asyncType="post" rownumbers="true" fitStyle="fill" altRows="true" onDblClickRow="onDblClickRenewOldManGrid">
				<cui:gridCols>
					<cui:gridCol name="id" hidden="true">id</cui:gridCol>
					<cui:gridCol name="name" width="200" >用户名称</cui:gridCol>
					<cui:gridCol name="loginName" width="200" >登录名</cui:gridCol>
					<cui:gridCol name="jobNo" width="200" >工号</cui:gridCol>
					<cui:gridCol name="op" width="100" align="center" formatter="operationRenewFormatter" sortable="false">操作选项</cui:gridCol>
				</cui:gridCols>
				<cui:gridPager gridId="renewOldManGrid"></cui:gridPager>
			</cui:grid>
	
			<cui:dialog id="updateOldUserDialog" title="恢复用户" autoOpen="false" reLoadOnOpen="true">
				<cui:form id="updateOldUserForm" action="${ctx }/auth/user">
					<cui:input name="id" type="hidden"></cui:input>
					<cui:input name="name" type="hidden"></cui:input>
					<cui:input name="status" type="hidden"></cui:input>
				
					<div class="tableBorder">
						<div class="trBorder">
							<table cellspacing="0" cellpadding="0">
				   				<tr>
				   					<td class="tdLabel">用户登录名：</td>
				   					<td class="tdElement"><cui:input name="loginName" width="200" required="true" pattern="//^[A-Za-z0-9\u4E00-\u9FA5]{1,50}$//" errMsg="长度在1-50之间的字母数字汉字" errMsgPosition="right" valid="checkUnique"></cui:input></td>
				   				</tr>	
				   			</table>
						</div>
						<div class="trBorder">
				   			<table cellspacing="0" cellpadding="0">
				   				<tr>
				   					<td class="tdLabel">工号：</td>
				   					<td class="tdElement"><cui:input name="jobNo" width="200"  required="true" maxlength="150" pattern="//^[0-9]*$//" valid="checkUnique" uniqueScope="status"></cui:input></td>
				   				</tr>
				   			</table>
				   		</div>
					</div>
				</cui:form>
			</cui:dialog>
<script type="text/javascript">

	//双击列表行
	function onDblClickRenewOldManGrid(e,ui){
		updateOldUser(ui.rowId);
	}


	//老用户列表搜索
	function searchOldMan(){
		
		var url = "${ctx}/auth/user/page?filter=status_EQ_1;unitId_EQ_${CURRENTUSER.unitId};isAdmin_EQ_1";
		var searchText = $("#renewOldMan_search_text").textbox("getValue");
		if(searchText){
			url += ";name_LIKE_"+searchText ;
		}
		var grid = $("#renewOldManGrid");
		grid.grid("option","url",url);
		grid.grid("reload");
	}
	
	
	//操作老用户格式化
	function operationRenewFormatter(cellValue, options, rowObject){
		var result = "";
			result += "<a href='javascript:updateOldUser(\""+ rowObject.id +"\");' title='修改' class='grideditbtn'></a> "
			result += "<a href='javascript:renewOldToNew(\""+ rowObject.id+ "\",\""+rowObject.name+"\");' class='gridchangebtn' title='恢复'></a> ";
		return result;
	}
	
	//启用老用户
	function renewOldToNew(id,userName){
		$.confirm("确认要恢复吗?",function(r){
			if(r){
				loading("恢复中...");
				var grid = $("#renewOldManGrid");
				$.ajax({
			        type        :    'POST',
			        url         :    '${ctx}/auth/user/renewOldToNew',
			        data        :    {id : id.toString() , orgId : '${orgId}', userName : userName, orgName : '${orgName}'}, 
			        dataType    :    'json',
			        success     :    function(data){
			        					message("恢复成功");
			        					grid.grid("reload");
			                            hide();
			        },
			        error        :    function(e){
			                            error(e);
			                            grid.grid("reload");
			                            hide();
			        }    
			    }) 
			}
		})
	  	
	} 
	
	//修改老用户
	function updateOldUser(id){
		
		var grid = $("#renewOldManGrid");
		var form = $("#updateOldUserForm");
		var url= ctx + '/auth/user/updateOldUser';
		form.form("load", ctx + '/auth/user/load?id=' + id);
		
		$("#updateOldUserForm input[name='status']").textbox("setValue",'<%=Constants.User.ONJOB%>');
		dialog(
				"#updateOldUserDialog",
				{
					width : 400,
					height : 300,
					title : "修改老用户"
				},
				[{
				    text	:	"保存",
				    id		:	"oldUserSaveButton",
				    click	:	function () {
				    	var _this = this;
						if (form.form("valid")) {
							loading("保存中...");
							
							$.ajax({
								type		: 'post',
								url			: url,
								data		: form.form("formData", false),
								dataType 	: 'json',
								success		: function(data) {
 												hide();
												message("操作成功！");
												$(_this).dialog("close");
												grid.grid('reload'); 
								},
								error		: function(e) {
												hide();
												error(e);
								}
							});
						} else {
							message("未通过页面校验！");
						}
				    }        
				},
				{
				    text	:	"关闭",
				    id		:	"oldUserCloseButton",
				    click	:	function () {
				    				$(this).dialog("close");
				    }            
				}
			]).dialog("open");
	}
</script>