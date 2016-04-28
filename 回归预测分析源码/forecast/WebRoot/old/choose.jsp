<%@ page language="java" import="java.util.*" pageEncoding="GB2312"
	contentType="text/html;charset=gb2312" isELIgnored="false"%>



<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
<head>
	<html:base />

	<title>choose.jsp</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>

<body>
<center>
	<h2>
		请选择预测方法:
	</h2>
	<form action="calculate" method="post">
		<input type="hidden" name="objStr" value="${objStr }">
		<input type="radio" value="1" name="method">
		普通逐步回归分析
		<input type="radio" value="2" name="method">
		多因素前移回归预测
		<input type="radio" value="3" name="method">
		多因素趋势回归预测<p>
		请指定F临界值：<br />
		引入变量的F<html:html lang="true">1</html:html>值
		<input type="text" name="f1">
		<br />
		剔除<html:html lang="true">变量的F</html:html><html:html lang="true">2</html:html><html:html lang="true">值</html:html>
		<input type="text" name="f2">
		<br />
		<input type="submit" value="提交" name="B1">
	</p>
	</form>
	</center>
<html:html lang="true">
<p>(<font color="#FF0000">注：</font><b><font color="#000080">只有时间序列才能预测。</font></b>预测未来的值，应当将原始数据记录自上而下由老到新排列；预测过去的值，即追溯最早的记录之前一时间的某因变量的值，可以将数据记录自上而下有新到老排列。<font color="#000080"><b><html:html lang="true">非时间序列可以通过普通逐步回归分析找出因变量与自变量的线性关系。</html:html></b></font>)</p>
</html:html>
</body>
</html:html>
