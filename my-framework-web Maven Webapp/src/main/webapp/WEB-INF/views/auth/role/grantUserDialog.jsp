 <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ include file="../../../include/global.jsp"%>



 <div class="tableBorder">
	 <div class="trBorder" >
		 <table cellspacing="0" cellpadding="0">
			 <tr>
				 <td><cui:checkbox id="role_isTempAccreditCheckbox" label="临时授权"></cui:checkbox></td>
			 </tr>
			 <tr class="tempAccreditDateArea">
				 <td>开始时间：&nbsp;&nbsp;<cui:datepicker id="role_tempAccreditDateStart" width="200" minDate="${currentDate}" value="${currentDate}" required="true" showOn="both" dateFormat="yyyy-MM-dd"></cui:datepicker></td>
			 </tr>
			 <tr class="tempAccreditDateArea">
				 <td>结束时间：&nbsp;&nbsp;<cui:datepicker id="role_tempAccreditDateEnd" width="200" minDate="${currentDate}" startDateId="role_tempAccreditDateStart" showOn="both" required="true" dateFormat="yyyy-MM-dd"></cui:datepicker></td>
			 </tr>
			 <tr>
				 <td>
					 <span class="se-searchall" style="float:left;">
						<cui:input id="grantUserTree_search_text" type="text" width="220" placeholder="可检索名称" onEnter="grantUserTreeSearchChange"></cui:input>
						<span class=" icon icon-search3 search-Click" onClick="grantUserTreeSearchChange()"></span>
					</span>
					 <div class="clearfix"></div>
					 <div class="leftTree">
						 <cui:tree id="grantUserTree" data="${orgUserData }" checkable="true" chkStyle="checkbox" onLoad="grantUserTreeOnload" simpleDataEnable="true">
						 </cui:tree>
					 </div>
				 </td>
			 </tr>
		 </table>
 	</div>
 </div>


	
	<script type="text/javascript">
		//搜索系统树节点
		function grantUserTreeSearchChange(){
			var tree = $("#grantUserTree");
			var value = $("#grantUserTree_search_text").textbox("getValue");
			tree.tree("filterNodesByParam", {name: value});    
		}


		$(function(){
			var $tempAccreditDateArea = $(".tempAccreditDateArea");
			$tempAccreditDateArea.hide();

			$("#role_isTempAccreditCheckbox").change(function(e){
				if(this.checked){
					$tempAccreditDateArea.show();
				}else{
					$tempAccreditDateArea.hide();
				}
			});
		})


		var grantUserDialog = {
			oldUserIds : null
		}

		function grantUserTreeOnload(e, treeId, treeNode){
			//展开所有节点
			var grantUserTree = $("#"+treeId);
			grantUserTree.tree("expandAll",true);

			//记录用户树加载完后勾选的id
			var selNode = grantUserTree.tree("getCheckedNodes");
			var oldUserIdsArray = [];
			for(var i=0;i<selNode.length;i++){
				oldUserIdsArray.push(selNode[i].id);
			}
			grantUserDialog.oldUserIds = oldUserIdsArray;
		}
	</script>