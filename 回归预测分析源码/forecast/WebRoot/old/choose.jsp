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
		��ѡ��Ԥ�ⷽ��:
	</h2>
	<form action="calculate" method="post">
		<input type="hidden" name="objStr" value="${objStr }">
		<input type="radio" value="1" name="method">
		��ͨ�𲽻ع����
		<input type="radio" value="2" name="method">
		������ǰ�ƻع�Ԥ��
		<input type="radio" value="3" name="method">
		���������ƻع�Ԥ��<p>
		��ָ��F�ٽ�ֵ��<br />
		���������F<html:html lang="true">1</html:html>ֵ
		<input type="text" name="f1">
		<br />
		�޳�<html:html lang="true">������F</html:html><html:html lang="true">2</html:html><html:html lang="true">ֵ</html:html>
		<input type="text" name="f2">
		<br />
		<input type="submit" value="�ύ" name="B1">
	</p>
	</form>
	</center>
<html:html lang="true">
<p>(<font color="#FF0000">ע��</font><b><font color="#000080">ֻ��ʱ�����в���Ԥ�⡣</font></b>Ԥ��δ����ֵ��Ӧ����ԭʼ���ݼ�¼���϶������ϵ������У�Ԥ���ȥ��ֵ����׷������ļ�¼֮ǰһʱ���ĳ�������ֵ�����Խ����ݼ�¼���϶������µ������С�<font color="#000080"><b><html:html lang="true">��ʱ�����п���ͨ����ͨ�𲽻ع�����ҳ���������Ա��������Թ�ϵ��</html:html></b></font>)</p>
</html:html>
</body>
</html:html>
