 <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>



 <cui:dialog id="uploadDeploymentDialog" autoOpen="false">
	 <cui:form id="uploadDeploymentForm">

		 <div class="tableBorder">

			 <div class="trBorder">
				 <table cellspacing="0" cellpadding="0">
					 <tr>
						 <td class="tdLabel">流程名称：</td>
						 <td class="tdElement"><cui:input name="filename" width="200" maxlength="50" required="true" ></cui:input></td>
					 </tr>
				 </table>
			 </div>
			 <div class="trBorder">
				 <table cellspacing="0" cellpadding="0">
					 <tr>
						 <td class="tdLabel">文件：</td>
						 <td class="tdElement">
							 <div id="commonLocalFile_select">
								 <cui:uploader id="uploaderDeploymentFile" name="file" auto="false" buttonText="选择文件" fileTypeExts="*.zip" successTimeout="3600"
											   multi="false"
											   onUploadStart="website_sensitive_UploadFileStart"   
											   onUploadComplete="website_sensitive_UploadComplete" 
											   onUploadSuccess="commonUploadFileSuccess"
											   onUploadError="website_sensitive_UploadError"
											   uploader="${ctx}/activity/repository-api/uploadDeployment" 
											   swf="${ctx}/res/cui/external/uploadify.swf"></cui:uploader>
							 </div>
						 </td>
					 </tr>
				 </table>
			 </div>
		 </div>

	 </cui:form>
 </cui:dialog>
<script type="text/javascript">


    function openUploadDeploymentDialog(){
        var grid = $("#deploymentGrid");
        var form = $("#uploadDeploymentForm");

        form.form("clear");

        dialog(
            "#uploadDeploymentDialog",
            {
                width : 400,
                height : 300,
                title : "上传部署文件"
            },
            [
                {
                    text    :    "上传",
                    id      :    "uploadDeploymentButton",
                    click   :    function () {
                        var _this = $(this);

                        debugger;
                        var dataLength = $("#uploaderDeploymentFile").uploader("queueData").queueSize;
                        if(dataLength<=0){
                            warning("文件中没有选择文件");
                            return;
                        }
                        if (form.form("valid")) {
                            loading("处理中，请稍候...");
                            $("#uploaderDeploymentFile").uploader("option", "formData",form.form("formData", false));
                            $("#uploaderDeploymentFile").uploader("upload", "*");
                        } else {
                            warning("未通过页面校验！");
                        }
                    }
                },
                {

                    text    :    "关闭",
                    id      :    "closeButton",
                    click   :    function () {
                        $(this).dialog("close");
                    }
                }

            ]).dialog("open");
    }
    
    function website_sensitive_UploadFileStart(file){
        //console.log(file);
    }

    function website_sensitive_UploadComplete(){

    }

    function commonUploadFileSuccess(file,data,response){
        debugger;
        hide();
        try{
            var dataJson = $.parseJSON(data.data);
            if(dataJson["status"]=="error"){
                error(dataJson['message']);
                $("#uploadDeploymentForm").form("clear");
                return;
            }
        }catch(e){}
        message("上传成功！");
        $("#uploadDeploymentForm").form("clear");
        $("#deploymentGrid").grid("reload");
        $("#processdefGrid").grid("reload");
        $("#uploadDeploymentDialog").dialog("close");
    }

    function website_sensitive_UploadError(file, errorCode){
        debugger;
        hide();
        error("上传失败");
    }
</script>