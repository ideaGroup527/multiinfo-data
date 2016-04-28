<%@ page language="java" import="java.util.*" pageEncoding="GB2312"
	contentType="text/html;charset=gb2312" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
<head>
	<html:base />

	<title>choseExcelSheet.jsp</title>

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
			该Excel文件所有表如下，请选取需要读取哪张表，并提交：
		</h2>

		<form method="POST" action="readExcel">
			<input type="hidden" name="excelPath" value="${excelPath }">
			<c:set var="tFlag" value="0"></c:set>
			<c:forEach var="i" items="${sheetName}">
				<td>
					<input type="radio" name="sheetNO" value="${tFlag+1}">
					${i }
				</td>
				<c:set var="tFlag" value="${tFlag+1}"></c:set>
			</c:forEach>


			<p>
				<input type="submit" value="提交" name="B1">
			</p>

		</form>
	</center>
</body>
</html:html>
