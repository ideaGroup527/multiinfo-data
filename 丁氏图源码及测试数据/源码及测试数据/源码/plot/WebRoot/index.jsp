<%@ page language="java" pageEncoding="gb2312"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>绘图系统</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
	    <style type="text/css">
<!--
.STYLE1 {
	font-size: x-large;
	font-weight: bold;
}
-->
        </style>
</head>
	<body>
	<div align="center" class="STYLE1">
	  <p>选择数据来源</p>
	  <p>&nbsp;</p>
	  <p>
	  	<input type="button" value="Excel" onClick="location.href='readExcel.jsp'">
	 <br>
	  	<input type="button" value="数据库" onClick="location.href='connParam.jsp'">
	  </p>
	</div>
	
	</body>
</html>