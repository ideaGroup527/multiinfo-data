<%@ page language="java" pageEncoding="GBK"
	contentType="text/html;charset=gbk" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>Select the table in the database</title>
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
				ѡ�����ݿ��еı�<br>
				Select the table in the database</p>
			<form method="POST" action="getTableMessage">
				<input type="hidden" name="objStr" value="${objStr }">
				<table width="370" border="1">
					<tr>
						<td width="101">
							ѡ��
							Select
						</td>
						<td width="253">
							<div align="center">
								����
								Table name</div>
						</td>
					</tr>
					<c:forEach var="i" items="${allTableName}">
						<tr>
							<td>
								<label>
									<input type="radio" name="tableName" value="${i }">
								</label>
							</td>
							<td>
								${i }
							</td>
						</tr>
					</c:forEach>
				</table>
				<p>
				</p>
				
				<center>
					<input type="submit" value="�ύ(Submit)" name="B1">
					<input type="reset" value="����(Reset)" name="B2">			<hr>
			<input type="button" value="����(Back)" onClick="javascript:history.back(-1);">
				</center>
				
			</form>
			

		</div>

	</body>
</html>