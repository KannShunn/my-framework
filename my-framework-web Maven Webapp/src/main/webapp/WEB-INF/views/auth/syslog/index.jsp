<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%@taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ include file="../../../include/global.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>系统管理</title>
</head>
<body>
  <div class="PanelCon">
	<form id="sysLogForm" method="post" >
		<div class="PanelSelect showmenubar">
			<div class="search-block clearfix">
	    		<label class="floatL-1">登录日期：</label>
	    		<cui:radiolist id="system_search_sysLog_date" onChange="sysLog_searchChange" column="7" value="1" data="[{text:'全部',value:' '},{text:'今日',value:'1'},{text:'最近7天',value:'2'},{text:'本周',value:'3'},{text:'最近30天',value:'4'},{text:'本月',value:'5'},{text:'自定义',value:'6'}]">
        		</cui:radiolist>
      			<span id="selfDateSearch" class="selfBtnDatepicker setSelf">
      				<cui:datepicker id="sysLog_custom_search_startDate" showOn="both" value="${currentDateTime }" dateFormat="yyyy-MM-dd" onChange="sysLog_searchChange" width="95"></cui:datepicker>
      				<label>到</label>
      				<cui:datepicker id="sysLog_custom_search_endDate" showOn="both" value="${currentDateTime }"  dateFormat="yyyy-MM-dd" onChange="sysLog_searchChange" width="95"></cui:datepicker>
        		</span>
	    	</div>
		</div>
       <div class="PanelList">
       		<div class="clearfix">
	       		<div class="floatLeft2"><cui:toolbar id="sysLogToolbar"></cui:toolbar></div>
	       		<div class="floatRight ">
	       			<span class="se-searchall">
	    				<cui:input id="sysLog_search_text" name="userName" type="text" width="220" placeholder="可检索用户名" onEnter="sysLog_searchChange"></cui:input>
	    				<span class=" icon icon-search3 search-Click" onClick="sysLog_searchChange()"></span>
    				</span>
	       		</div>
       		</div>

		   <sec:hasPermission name="/auth/log/sysLog/page">
       		<cui:grid id="sysLogGrid" url="${ctx}/auth/log/sysLog/page?P_orders=logDate&filter=unitId_EQ_${CURRENTUSER.unitId }" datatype="local" sortorder="desc" loadonce="false" rownumbers="true"  fitStyle="fill" multiselect="true" altRows="true" >
		    	<cui:gridCols>
		    		<cui:gridCol name="id" hidden="true">id</cui:gridCol>
		    		<cui:gridCol name="userId" hidden="true">登录用户id</cui:gridCol>
		    		<cui:gridCol name="unitId" hidden="true">单位id</cui:gridCol>
		    		<cui:gridCol name="userName" width="80">用户名</cui:gridCol>
		    		<cui:gridCol name="operate" width="80" >操作</cui:gridCol>
		    		<cui:gridCol name="message" width="80">操作内容</cui:gridCol>
		    		<cui:gridCol name="ip" width="80">登录用户ip</cui:gridCol>
		    		<cui:gridCol name="logDate" width="80" formatter="dateFormatter">操作时间</cui:gridCol>
		    	</cui:gridCols>
		    	<cui:gridPager gridId="sysLogGrid" />
		    </cui:grid>
		   </sec:hasPermission>
       </div>
      </form>
   </div>	
<script>
var sysLog_sysLogToolBarData = [
	<sec:hasPermission name="/auth/log/operateLog/export">
{
	"id": "export",
	"label": "导出日志",
	"disabled": "false",
	"onClick":"sysLog_exportSysLog()",
	"type": "button",
	"cls":"greenbtn",
	"icon":"icon-upload2"
},
	</sec:hasPermission>
	{}
];

$(function() {
	$("#sysLogToolbar").toolbar({
		data : sysLog_sysLogToolBarData
	});	
});

//dom解析完后将查询时间段定格为今日
$.parseDone(function(){

	var baseSearch = "${ctx}/auth/log/sysLog/page?P_orders=logDate&filter=unitId_EQ_${CURRENTUSER.unitId }";
	var searchDate = getDateToday();
	if(searchDate != "") param_search_sysLog_date = searchDate;
	if(param_search_sysLog_date != "") {
		baseSearch += ";logDate_GTE_" + param_search_sysLog_date[0];
		baseSearch += ";logDate_LTE_" + param_search_sysLog_date[1];
	}
	var sysLogGrid = $("#sysLogGrid");
	sysLogGrid.grid("option","datatype","json");
	sysLogGrid.grid("option","url",baseSearch);
	sysLogGrid.grid("reload");

	changeHighLight();
});

//查询
var param_search_sysLog_date = "";
var param_search_sysLog_text = "";
function sysLog_searchChange(event,options){

	changeHighLight();

	var baseSearch = "${ctx}/auth/log/sysLog/page?P_orders=logDate&filter=unitId_EQ_${CURRENTUSER.unitId }";
	if(this.id == "system_search_sysLog_date"){
		$("#selfDateSearch").hide();
		var searchDate = "";
		switch(options.value){
			case '1':
				searchDate = getDateToday();
				break;
			case '2':
				searchDate = getDateRecentDay(7);
				break;
			case '3':
				searchDate = getDateWeek();
				break;
			case '4':
				searchDate = getDateRecentDay(30);
				break;
			case '5':
				searchDate = getDateMonth();
				break;
			case '6':
				searchDate = "";
				//param_search_publish_date = "";
				$("#selfDateSearch").show();
				break;
			default:
				searchDate = "";
				param_search_sysLog_date = "";
				break;
			
		}
		if(searchDate != "") param_search_sysLog_date = searchDate;
	}
	var loginDateValue = $("#system_search_sysLog_date").radiolist( "getValue" );
	if(loginDateValue == 6){
		var datePre = $("#sysLog_custom_search_startDate").datepicker( "option","value" );
		var dateNext = $("#sysLog_custom_search_endDate").datepicker( "option","value" );
		if(datePre == "") {
			alert("开始日期不能为空");
			return;
		}else{
			datePre = datePre.substring(0,10);
			datePre += " 00:00:00";
		}
		if(dateNext == "") {
			alert("结束日期不能为空");
			return;
		}else{
			dateNext = dateNext.substring(0,10);
			dateNext += " 23:59:59"; 
		}
		if(dateNext < datePre) {alert("开始日期不能大于结束日期");return;}
		param_search_sysLog_date = [datePre,dateNext];
	}
	
	param_search_sysLog_text = $("#sysLog_search_text").textbox("getValue");
	//var params = {};
	if(param_search_sysLog_date != "") {
		baseSearch += ";logDate_GTE_" + param_search_sysLog_date[0];
		baseSearch += ";logDate_LTE_" + param_search_sysLog_date[1];
	}
	if(typeof(param_search_sysLog_text) != "undefined" && param_search_sysLog_text != ""){
		baseSearch += ";userName_LIKE_" + param_search_sysLog_text;
	}

	var sysLogGrid = $("#sysLogGrid");
	//sysLogGrid.grid("option","postData",params);
	sysLogGrid.grid("option","url",baseSearch);
	sysLogGrid.grid("reload");
}

//导出日志
function sysLog_exportSysLog(){
	debugger;
 	var loginDateText = $("#system_search_sysLog_date").radiolist( "getText" ) + " ";
	var userName = $("#sysLog_search_text").textbox("getValue") + " ";
	var grid = $("#sysLogGrid");
	var selIds = grid.grid("option","selarrrow");
	var showMessage = "要导出 <span style='color:green;'>"+ loginDateText + userName + "</span>的记录吗?";
	if(selIds.length != 0){
		showMessage = "要导出选中的记录吗?";
	}
 	$.confirm(showMessage,function(r){
 		if(r){
 			var pageNumber = grid.grid("option","page");
 			//var pageSize = grid.grid("option","rowNum");
 			var pageSize = grid.grid("option","records");
 			var filter = "unitId_EQ_${CURRENTUSER.unitId }";
 			
 			var ids = grid.grid("option","selarrrow");
 			if(ids.length != 0){
 				filter += ";id_IN_"+ids.toString();
 			}else{
	 			var logDateStart = param_search_sysLog_date[0];
	 			var logDateEnd = param_search_sysLog_date[1];
	 			if(typeof(logDateStart) != "undefined" && logDateStart != ""){
	 				filter += ";logDate_GTE_" + logDateStart;
	 			}
	 			if(typeof(logDateEnd) != "undefined" && logDateEnd != ""){
	 				filter += ";logDate_LTE_" + logDateEnd;
	 			}
	 			var userName = $("#sysLog_search_text").textbox("getValue");
	 			if(userName != ""){
	 				filter += ";userName_LIKE_" + userName;
	 			}
 			}
 			
 			var logForm = $("#sysLogForm");
 			logForm.attr("action","${ctx}/auth/log/sysLog/export?P_pageNumber="+ pageNumber +"&P_pagesize="+ pageSize +"&P_orders=logDate&filter=" + filter);
 			//logForm.attr("action","${ctx}/backstage/system/sys-log!export?logDateStart="+logDateStart+"&logDateEnd="+logDateEnd+"&userName="+userName.trim());
 			logForm.attr("target","_blank");
 			logForm.submit();
 		}
 	});
 }

</script>
</body>
</html>