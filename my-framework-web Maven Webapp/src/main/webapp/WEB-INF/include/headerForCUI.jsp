<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% 
	String headerForCUI = request.getContextPath()+"/res";
	//String headerForCUI = "http://192.10.33.120:8080/Coral4.0/";
	String version = "4.1.6";
	//String min="min.";
	String min="";
%>
<%--
<link href="<%=headerForCUI %>/cui/<%=version%>/dev/themes/<%=themeFolder %>/cui.all.css" rel="stylesheet"/>
<script src="<%=headerForCUI %>/cui/<%=version %>/js/jquery.coral.js" type="text/javascript"></script>
<script src="<%=headerForCUI %>/cui/<%=version %>/dev/ui/jquery.coral.layout.js"/></script>
--%>
<link href="<%=headerForCUI %>/icon-font/style.css" rel="stylesheet"/>
<link href="<%=headerForCUI %>/cui/cui.<%=min %>css" rel="stylesheet"/>
<!-- common resources -->
<script type="text/javascript" src="<%=headerForCUI %>/cui/jquery-1.9.1.<%=min %>js"></script>
<script type="text/javascript" src="<%=headerForCUI %>/cui/cui.<%=min %>js"></script>


<script type="text/javascript">
	var ctx = "${ctx}";
</script>