<%@ page language="java" import="java.util.*" pageEncoding="GBK"
	contentType="text/html;charset=gbk" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>

		<title>������ʾ</title>
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
				��ѡ����Ҫ������
			</h2>
			<br>
			<h3>
				��ѡ�Ĺ������ǣ�
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

				<input type="button" value="����"
					onClick="javascript:history.back(-1);">
				<input type="submit" value="��һ��">
				<br><br><br>
			</center>
			
			<table width="200" border="1" align="center">
				<tr>
					<td><input type=button value="��ȫѡ" onClick="checkAll('row')"></td>
					<td><input type=button value="��ȫ��ѡ" onClick="uncheckAll('row')"></td>
					<td><input type=button value="�з�ѡ" onClick="switchAll('row')"></td>
				</tr>
				<tr>
					<td><input type=button value="��ȫѡ" onClick="checkAll('col')"></td>
					<td><input type=button value="��ȫ��ѡ" onClick="uncheckAll('col')"></td>
					<td><input type=button value="�з�ѡ" onClick="switchAll('col')"></td>
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
						X��					</td>
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

				<input type="button" value="����"
					onClick="javascript:history.back(-1);">
				<input type="submit" value="��һ��">
			</center>
		</form>
	</body>
</html>