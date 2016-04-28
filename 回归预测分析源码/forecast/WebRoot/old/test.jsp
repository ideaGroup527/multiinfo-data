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
	    <style type="text/css">
<!--
.STYLE1 {color: #FF0000}
-->
        </style>
</head>

	<body>
		<br>
		<center>
			<h2>
				　</h2>
			<p>
				<font size="4">Please select the data needed.</font></p>
			<br>
			<h3>所选的工作表是(Your selected table is):<span class="STYLE1">${picName }</span></h3>
		</center>
		<br>
		<br>
		<form method="post" action="chooseData">
			<table width="441" border="1" align="center">
				<tr>
					<td>
						全选
						All<input type="checkbox" name="isAll" value="true">
					</td>
					<td>
						列全选
						All Columns<input type="checkbox" name="isCol" value="true">
					</td>
					<td>
						行全选
						All Rows<input type="checkbox" name="isRow" value="true">
					</td>
				</tr>
			</table>
<h2>
<hr>
</h2>
			<table width="228" border="1" align="center">
				<tr>

					<td width="105">
				  </td>
					<c:set var="tFlag" value="0"></c:set>
					<c:forEach var="i" items="${title}">
						<td width="79">
							<input type="checkbox" name="col" value="${tFlag }">
					  </td>
						<c:set var="tFlag" value="${tFlag+1}"></c:set>
					</c:forEach>
				</tr>
				<tr>

					<td>
						
					</td>
					<c:forEach var="i" items="${title}">
						<td>
							${i }
						</td>
					</c:forEach>
				</tr>
				<c:set var="flag" value="0"></c:set>
				<c:forEach var="j" items="${showList}">
					<tr>
						<td>
							<input type="checkbox" name="row" value="${flag }">
							<br>
							
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
				<input type="submit" value="下一步(Next)">
			</center>
		</form>
	</body>
</html>