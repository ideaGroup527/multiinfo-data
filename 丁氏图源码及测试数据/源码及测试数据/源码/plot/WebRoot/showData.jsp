<%@ page language="java" import="java.util.*" pageEncoding="GBK"
	contentType="text/html;charset=gbk" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>

		<title>数据显示</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<script type="text/javaScript" src="js/checkBox.js"></script>
		<link href="css/css.css" rel="stylesheet" type="text/css" media="all" />
		<style type="text/css">
<!--
.STYLE1 {
	color: #FF0000
}
-->
</style>
	</head>

	<body>
		<br>
		<center>
			<h2>
				请选择需要的数据
			</h2>
			<br>
			<h3>
				所选的工作表是：
				<span class="STYLE1">${picName }</span>
			</h3>
		</center>
		<br>
		<br>
		<form method="post" action="chooseData">
			<input type="hidden" name="title" value="${title }">
			<input type="hidden" name="xScale" value="${xScale }">
			
			<center>
				<br>

				<input type="button" value="后退"
					onClick="javascript:history.back(-1);">
				<input type="submit" value="下一步">
				<br><br><br>
			</center>
			
			<table width="200" border="1" align="center">
				<tr>
					<td><input type=button value="行全选" onClick="checkAll('row')"></td>
					<td><input type=button value="行全不选" onClick="uncheckAll('row')"></td>
					<td><input type=button value="行反选" onClick="switchAll('row')"></td>
				</tr>
				<tr>
					<td><input type=button value="列全选" onClick="checkAll('col')"></td>
					<td><input type=button value="列全不选" onClick="uncheckAll('col')"></td>
					<td><input type=button value="列反选" onClick="switchAll('col')"></td>
				</tr>
			</table>
			<h2>
				<hr>
			</h2>
			<table width="278" border="1" align="center">
				<tr>

					<td width="93">
						<input type="hidden" name="tran" value="${tran }">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				  </td>
					<c:set var="tFlag" value="0"></c:set>
					<c:forEach var="i" items="${title}">
						<td width="169">
							<input type="checkbox" name="col" value="${tFlag }" checked="checked">
					  </td>
						<c:set var="tFlag" value="${tFlag+1}"></c:set>
					</c:forEach>
				</tr>
				<tr>

					<td width="93">
						X轴					</td>
					<c:forEach var="i" items="${title}">
						<td>
							${i }
						</td>
					</c:forEach>
				</tr>
				<c:set var="flag" value="0"></c:set>
				<c:forEach var="j" items="${xScale}">
					<tr>
						<td  width="93">
							<input type="checkbox" name="row" value="${flag }" checked="checked">
							<br>
							${j }
					  </td>
						<c:forEach var="jj" items="${showList[flag]}">
							<td>
								${jj }
							</td>
						</c:forEach>
						<c:set var="flag" value="${flag+1}"></c:set>
					</tr>
				</c:forEach>
		  </table>
			<center>
				<br>

				<input type="button" value="后退"
					onClick="javascript:history.back(-1);">
				<input type="submit" value="下一步">
			</center>
		</form>
	</body>
</html>