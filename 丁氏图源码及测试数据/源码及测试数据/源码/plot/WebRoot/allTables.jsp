<%@ page language="java" pageEncoding="GBK"
	contentType="text/html;charset=gbk" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>��ͼϵͳ</title>
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
				ѡ��Ҫ��ͼ�ı�
			</p>
			<form method="POST" action="getTableMessage">
				<input type="hidden" name="objStr" value="${objStr }">
				<table width="200" border="1">
					<tr>
						<td width="38">
							ѡ��
						</td>
						<td width="146">
							<div align="center">
								����
							</div>
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
				<p>
				<center>
					<input type="submit" value="�ύ" name="B1">
					<input type="reset" value="����" name="B2">			<hr>
			<input type="button" value="����" onClick="javascript:history.back(-1);">
				</center>
				</p>
			</form>
			<p>
			</p>

		</div>

	</body>
</html>