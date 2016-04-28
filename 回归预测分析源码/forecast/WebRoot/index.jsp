<%@ page language="java" pageEncoding="gb2312"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>Forecast system</title>
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
	  <p align="left"><font size="3">
		滑移回归分析法预测程序(2010年4月推出)，包括多因素前移回归分析和多因素趋势回归分析。</font></p>
		<p align="left"><font size="3">
		The Program for Shifting Regression, Including Multi-factor Forward 
		Shifting Regression and Multi-factor Trend Regressin. Copyright 
		Apr.,2010</font></p>
		<p style="margin-top: 0; margin-bottom: 0">请选择数据来源</p>
		<p style="margin-top: 0; margin-bottom: 0">Please select data source</p>
	  <p><span style="font-weight: 400"><font size="4">(用户事先应准备好自己需要做图的数据，数据库可以用Excel、SQL 
		Server、Oracle和MySQL。</font></span></p>
		<p><span style="font-weight: 400"><font size="4">如果用Excel，数据样例见<a href="http://210.34.136.253:8488/Sample_data.xls">实例Excel表</a>；采用其他数据库需要在下一步填入数据库的URL、用户名、密码。)</font></span></p>
		<p><font size="4"><span style="font-weight: 400">(User must prepare the 
		data for drawing the chart. The dadabase could be excel, SQL Server,
		</span></font><span style="font-weight: 400"><font size="4">
		Oracle and MySQL</font></span><font size="4"><span style="font-weight: 400">.</span></font></p>
		<p><font size="4"><span style="font-weight: 400">If excel is selected, 
		the sample data is like &quot;<a href="http://210.34.136.253:8488/Sample_data.xls">sample excel file</a>&quot;. </span></font></p>
		<p><font size="4"><span style="font-weight: 400">If you choose other 
		database, you may fill in the URL, user name and passwrod. )</span></font></p>
	  <p>
	  	<input type="button" value="Excel" onClick="location.href='readExcel.jsp'">
	 <br>
	  	<input type="button" value="数据库(Other database)" onClick="location.href='connParam.jsp'">
	  </p>
	</div>
	
	</body>
</html>