<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
</head>
<body>
	<div class="tooltipone">请选择左边树中的节点进行操作</div>
	
<script type="text/javascript">
	$(function(){
		if('${showTip}'){
			$(".tooltipone").text('${showTip}');
		}
	})
</script>
</body>
</html>