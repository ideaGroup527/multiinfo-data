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
		���ƻع������Ԥ�����(2010��4���Ƴ�)������������ǰ�ƻع�����Ͷ��������ƻع������</font></p>
		<p align="left"><font size="3">
		The Program for Shifting Regression, Including Multi-factor Forward 
		Shifting Regression and Multi-factor Trend Regressin. Copyright 
		Apr.,2010</font></p>
		<p style="margin-top: 0; margin-bottom: 0">��ѡ��������Դ</p>
		<p style="margin-top: 0; margin-bottom: 0">Please select data source</p>
	  <p><span style="font-weight: 400"><font size="4">(�û�����Ӧ׼�����Լ���Ҫ��ͼ�����ݣ����ݿ������Excel��SQL 
		Server��Oracle��MySQL��</font></span></p>
		<p><span style="font-weight: 400"><font size="4">�����Excel������������<a href="http://210.34.136.253:8488/Sample_data.xls">ʵ��Excel��</a>�������������ݿ���Ҫ����һ���������ݿ��URL���û��������롣)</font></span></p>
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
	  	<input type="button" value="���ݿ�(Other database)" onClick="location.href='connParam.jsp'">
	  </p>
	</div>
	
	</body>
</html>