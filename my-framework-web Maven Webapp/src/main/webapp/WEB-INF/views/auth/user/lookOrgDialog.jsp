 <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
 <%@ include file="../../../include/global.jsp"%>
 <%@ page import="com.cesgroup.common.global.Constants" %>

<cui:grid id="lookOrgGrid" data="${orgData }" datatype="local" loadonce="false" asyncType="get" rownumbers="true"
	fitStyle="fill" altRows="true" >
	<cui:gridCols>
		<cui:gridCol name="id" hidden="true">id</cui:gridCol>
		<cui:gridCol name="pId" hidden="true">pId</cui:gridCol>
		<cui:gridCol name="unitName" width="100">单位名称</cui:gridCol>
		<cui:gridCol name="name" width="100">部门名称</cui:gridCol>
		<cui:gridCol name="userType" width="50" formatter="userTypeFormatter" align="center" sortable="false">专职/兼职</cui:gridCol>
		<cui:gridCol name="unitCode" width="40">单位代码</cui:gridCol>
		<cui:gridCol name="organizeCode" width="40">部门代码</cui:gridCol>
	</cui:gridCols>
</cui:grid>
	
