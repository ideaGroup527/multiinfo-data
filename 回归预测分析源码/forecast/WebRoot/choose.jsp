<%@ page language="java" import="java.util.*" pageEncoding="GB2312"
	contentType="text/html;charset=gb2312" isELIgnored="false"%>



<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
<head>
	<html:base />

	<title>Choose forecasting method</title>

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
		��ѡ��Ԥ�ⷽ��(Please choose forecasting method):</h2>
	<form action="calculate" method="post">
		<input type="hidden" name="objStr" value="${objStr }">
		<input type="radio" value="1" name="method">&nbsp;&nbsp;
		��ͨ�𲽻ع����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
		Ordinary stepwise regression<p>
		<input type="radio" value="2" name="method">
		������ǰ�ƻع�Ԥ��
		Multi-factor Forward Shifting Regression</p>
		<p>
		<input type="radio" value="3" name="method">
		���������ƻع�Ԥ��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
		Multi-factor Trend Regressin</p>
		<p>
		��ָ��F�ٽ�ֵ(Please designate F critical value):<br />
		���������F<html:html lang="true">1</html:html>ֵ(F1, for entering)
		<input type="text" name="f1">
		<br />
		�޳�<html:html lang="true">������F</html:html><html:html lang="true">2</html:html><html:html lang="true">ֵ(F2, 
		for eliminating)</html:html>
		<input type="text" name="f2">
		<br />
		<input type="submit" value="�ύ" name="B1">
	</p>
	</form>
	</center>
<html:html lang="true">
<p><font color="#FF0000">ע��</font><b><font color="#000080">ֻ��ʱ�����в���Ԥ�⡣</font></b>Ԥ��δ����ֵ��Ӧ����ԭʼ���ݼ�¼���϶������ϵ������У�Ԥ���ȥ��ֵ����׷������ļ�¼֮ǰһʱ���ĳ�������ֵ�����Խ����ݼ�¼���϶������µ������С�<font color="#000080"><b><html:html lang="true">��ʱ�����п���ͨ����ͨ�𲽻ع�����ҳ���������Ա��������Թ�ϵ��</html:html></b></font></p>
<html:html lang="true">
<p><font color="#FF0000">Notice: </font><b><font color="#000080">Only time 
series can be predicted. To predict the future value, the original data record 
shoud be in sequence form old to new. To </font></b><font color="#000080"><b>
backward the past value, </b></font><font color="#000080"><b>
<html:html lang="true">
the original data re</html:html></b></font><b><font color="#000080">cord shoud 
be in sequence form new to old, which is to predict the value before the oldest 
record. If the data is not time series, ordinary regression could be applied to 
get the relationship of independents to dependent.</font></b></p>
</html:html>
</html:html>
</body>
</html:html>
