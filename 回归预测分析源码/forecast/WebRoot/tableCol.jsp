<%@ page language="java" pageEncoding="GBK"
	contentType="text/html;charset=gbk" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>Ԥ��ϵͳ</title>
		<link href="css/css.css" rel="stylesheet" type="text/css" media="all" />
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
			<p> 
				<font color="#0000ff">����ѡ��<br>
				Data Selection</font></p>
			
			<center> 
				<font size="3">�������ı���(Your Operating Table Is):${tableName } 
			</font></center>
			<form method="POST" action="tableData">
				<input type="hidden" name="objStr" value="${objStr }">
				<input type="hidden" name="tableName" value="${tableName }">
				<table width="271" border="1">
                  <tr>
                    <td width="194">ѡ���ʶ�� Mark Column</td>
                    <c:forEach var="i" items="${tableCol}">
                    	<td width="61">
                    	<input type="radio" name="xScaleName" value="${i }"/>
                   	  </td>
                    </c:forEach>
                  </tr>
                  <tr>
                    <td>ѡ�������� Select Data Column</td>
                    <c:forEach var="i" items="${tableCol}">
                    	<td width="61">
                   	    <input type="checkbox" name="chooseCol" value="${i }">
					  </td>
                    </c:forEach>
                  </tr>
				  <tr>
                    <td>���� Column Name</td>
                    <c:forEach var="i" items="${tableCol}">
                    	<td width="61">${i }</td>
                    </c:forEach>
                  </tr>
                </table>
				<center>
					<font size="3"><font color="#ff0000">ע��</font>��ѡΪ�б�ʶ(һ���Ƿ��ڵ�һ�е�������)�ı���</font><font size="3">Ҳ����ѡ�������С�</font><p><font size="3">
					<font color="#FF0000">Notice:</font>The selected variable 
					for column mark should be selected into data columns, which 
					is generally the first </font><br>
					<input type="submit" value="�ύ(Submit)" name="B1">
					<input type="reset" value="����(Reset)" name="B2">
				<input type="button" value="����(Back)" onClick="javascript:history.back(-1);"></p>
				</center>
			</form>

		</div>

	</body>
</html>