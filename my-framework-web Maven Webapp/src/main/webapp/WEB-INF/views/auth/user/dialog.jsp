 <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>

<cui:dialog id="userDialog${idSuffix}" autoOpen="false" width="700" height="730" >
	<cui:form id="userForm${idSuffix}" action="${ctx }/auth/user">
		<cui:input name="id" width="200" type="hidden"></cui:input>
		<cui:input name="orgId" type="hidden"></cui:input>
		<cui:input name="showOrder" type="hidden"></cui:input> 
		<cui:input name="unitId" type="hidden"></cui:input>
		<cui:input name="status" type="hidden" value="0"></cui:input>
		<cui:input name="isAdmin" type="hidden"></cui:input>
		<cui:input name="flagAction" type="hidden" value="0"></cui:input> 
		<cui:input name="createUserId" type="hidden"></cui:input> 
		<cui:input name="createUserName" type="hidden"></cui:input> 
		<cui:input name="createTime" type="hidden"></cui:input> 
		<cui:input name="OffJobDate" type="hidden"></cui:input>
		<cui:input name="title" type="hidden"></cui:input>
		<cui:input name="isSystem" type="hidden"></cui:input>

		   <div class="tableBorder">
			   
		   		<div class="trBorder">
		   			<table cellspacing="0" cellpadding="0" style="float: left">
		   				<tr>
		   					<td class="tdLabel">用户登录名：</td>
		   					<td class="tdElement"><cui:input name="loginName" width="200" required="true" valid="checkUnique" uniqueScope="status" pattern="//^[A-Za-z0-9\u4E00-\u9FA5]{1,150}$//" errMsg="长度在1-150之间的字母数字汉字" errMsgPosition="right"></cui:input></td>
		   				</tr>		   				
		   				<tr>
		   					<td class="tdLabel">用户姓名：</td>
		   					<td class="tdElement"><cui:input name="name" width="200" required="true" maxlength="150" onBlur="onBlurUserName"></cui:input></td>
		   				</tr>
		   				<tr>
		   					<td class="tdLabel">用户简拼：</td>
		   					<td class="tdElement"><cui:input name="abbr" width="200" maxlength="100"></cui:input></td>
		   				</tr>
		   			</table>
		   			<table cellspacing="0" cellpadding="0" >
		   				<tr>	    					
		   					<td class="tdLabel"></td>
	    					<td class="tdElement">
	    						<div class="addpicblock">
	    							<div class="zoomOut">
	    								<div class="zoomIn">
				    						<a href="javascript:;" id="upim${idSuffix}" title="上传头像"><img id="userImg${idSuffix}" alt="" src="${ctx}/res/resource/style/images/addpicture.png"></a>
			    						</div>
		    						</div>
<!-- 		   							<div class="imgeditarea">
		   								<a class="grideditbtn" href="javascript:openUserImageUploadDialog('thumb','image','false','true','waterMarker_')"></a>
		   								<a class="griddeletebtn" id="logo_crop_pic" href="javascript:clearImage('urlPath','userImg')"></a>
		   							</div> -->
	    							<cui:input name="urlPath" type="hidden" ></cui:input>
	   							</div>
	   						</td>
		   				</tr>		   				
		   			</table>
		   			<div class="clearfix"></div>
		   		</div>

				
		   		<div class="trBorder">
		   			<table cellspacing="0" cellpadding="0">
		   				<tr>
		   					<td class="tdLabel">性别：</td>
		   					<td class="tdElement"><cui:combobox name="sex" url="${ctx}/auth/code/getComboboxDataByCode?code=sexData" emptyText="请选择..." width="200" required="true"></cui:combobox> </td>
		   					<td class="tdLabel">身份证号：</td>
		   					<td class="tdElement"><cui:input name="cardNo" width="200" maxlength="50" validType="idno" onBlur="onBlurCardNo" ></cui:input></td>
		   				</tr>
		   			</table>
		   		</div>			
		   			
		   		<!-- 密码和确认密码区域, 为了多处调用而抽取出来 -->
				<div id="passwordArea${idSuffix}" style="display: none;">
					<%@include file="./passwordArea.jsp" %>
				</div>
				
		   		<div class="trBorder">
		   			<table cellspacing="0" cellpadding="0">
		   				<tr>
		   					<td class="tdLabel">出生日期：</td>
		   					<td class="tdElement"><cui:datepicker name="birthday" width="200" required="true" onChange="onChangeBirthday" value="${currentDate}" dateFormat="yyyy-MM-dd"></cui:datepicker></td>
		   					<td class="tdLabel">年龄：</td>
		   					<td class="tdElement"><cui:input name="age" width="200" value="0" required="true" validType="number"></cui:input> </td>
		   				</tr>
		   			</table>
		   		</div>
			   <div class="trBorder">
				   <table cellspacing="0" cellpadding="0">
					   <tr>
						   <td class="tdLabel">入职时间：</td>
						   <td class="tdElement"><cui:datepicker name="onJobDate" width="200" value="${currentDate}" dateFormat="yyyy-MM-dd"></cui:datepicker></td>
						   <td class="tdLabel">是否二次入职：</td>
						   <td class="tdElement"><cui:combobox name="isSecondOnJob" width="200" value="1" url="${ctx}/auth/code/getComboboxDataByCode?code=yesOrNo"></cui:combobox> </td>
					   </tr>
				   </table>
			   </div>
		   		<div class="trBorder">
		   			<table cellspacing="0" cellpadding="0">
		   				<tr>
		   					<td class="tdLabel">工号：</td>
		   					<td class="tdElement"><cui:input name="jobNo" width="200"  required="true" maxlength="150" pattern="//^[A-Za-z0-9]*$//"  valid="checkUnique" buttons="generatorJobNoButtons" ></cui:input></td>
		   					<td class="tdLabel">政治面貌：</td>
		   					<td class="tdElement"><cui:combobox name="political" width="200" url="${ctx}/auth/code/getComboboxDataByCode?code=politicalData"></cui:combobox></td>
		   				</tr>
		   			</table>
		   		</div>
		   		<div class="trBorder">
		   			<table cellspacing="0" cellpadding="0">
		   				<tr>
		   					<td class="tdLabel">手机号码：</td>
		   					<td class="tdElement"><cui:input name="mobile" width="200" maxlength="20" validType="mobile" errMsgPosition="right"></cui:input></td>
		   					<td class="tdLabel">邮件地址：</td>
		   					<td class="tdElement"><cui:input name="email" width="200" maxlength="50" validType="email" errMsgPosition="right"></cui:input></td>
		   				</tr>
		   			</table>
		   		</div>
		   		<div class="trBorder">
		   			<table cellspacing="0" cellpadding="0">
		   				<tr>
		   					<td class="tdLabel">职位：</td>
		   					<td class="tdElement"><cui:input name="station" width="200" maxlength="100"></cui:input></td>
		   					<td class="tdLabel">固定电话：</td>
		   					<td class="tdElement"><cui:input name="telephone" width="200" maxlength="50" ></cui:input></td>
		   				</tr>
		   			</table>
		   		</div>
		   		<div class="trBorder">
		   			<table cellspacing="0" cellpadding="0">
		   				<tr>
		   					<td class="tdLabel">用户备注：</td>
		   					<td class="tdElement" colspan="2"><cui:textarea name="comments" width="550" height="80" maxlength="500"></cui:textarea> </td>
		   				</tr>
		   			</table>
		   		</div>
		    </div>
	</cui:form>
</cui:dialog>

<div style="display: none;">
	<form action ="${ctx }/auth/user/upload" methos = "post" enctype="multipart/form-data" name="userUploadForm" id="userUploadForm${idSuffix}" hidden="true" >
		<input type="file" name="imfile" id="imfile${idSuffix}" accept="image/*" hidden="true" />
	</form>
</div>
	
<script type="text/javascript">

	var generatorJobNoButtons = [{
				label	:	"生成",
				icons:"icon-checkmark-circle",
				click	:	function(e,data){
								autoGeneratorJobNo();
				}
	}]
	//当光标离开身份证号时输入框时, 提取输入的身份证, 并计算出生日期和年龄
	function onBlurCardNo(e){
		var card = this.value;
		
		if(isCardNo(card)){
			var y = card.substring(6,10);
			var m = card.substring(10,12);
			var d = card.substring(12,14);

			$("#userForm${idSuffix} input[name='birthday']").datepicker("option","value",y+"-"+m+"-"+d);
			$("#userForm${idSuffix} input[name='age']").textbox("setValue",new Date().getFullYear()-parseInt(y));
		}
	}
	
	//用户姓名光标失焦时, 自动生成简拼
	function onBlurUserName(e){
		var pinYin = makePy(this.value);

		$("#userForm${idSuffix} input[name='abbr']").textbox("setValue",pinYin);
	}
	
	//当选择生日时, 自动计算年龄
	function onChangeBirthday(e,ui){
		var y = ui.value.substring(0,4);
		if(y){
			$("#userForm${idSuffix} input[name='age']").textbox("setValue",new Date().getFullYear()-parseInt(y));
		} else {
			$("#userForm${idSuffix} input[name='age']").textbox("setValue",0);
		}
	}

	//自动生成工号
	function autoGeneratorJobNo(){
		$.ajax({
			type		:	'POST',
			url			:	'${ctx}/auth/user/autoGeneratorJobNo',
			dataType	:	'json',
			success		:	function(data){
								$("#userForm${idSuffix} input[name='jobNo']").textbox("setValue",data["jobNo"]);
			},
			error		:	function(e){
								error(e);
			}
		})
	}
	
	$("#upim${idSuffix}").click(function(){
		$("#imfile${idSuffix}").click();
	})
	
	$("#imfile${idSuffix}").change(function(){
		loading("正在上传请稍后...")
		var options_v = {
				url: "${ctx}/auth/user/upload" + "?_CSRFToken=" + $('meta[name="_CSRFToken"]').attr('content'),
				success:function(data){
					$("#userImg${idSuffix}").attr("src",data.imageContent);
					hide();
					if(data.success){
 						$("#userForm${idSuffix} input[name='urlPath']").textbox("setValue",data.urlPath);
					}else{
						
					}
				}
		};
		$("#userUploadForm${idSuffix}").ajaxSubmit(options_v);
	});

</script>