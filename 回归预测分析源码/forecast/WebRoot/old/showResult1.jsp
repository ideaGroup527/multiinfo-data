<%@ page language="java" import="java.util.*" pageEncoding="GB2312"
	contentType="text/html;charset=gb2312" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>




<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
<head>
	<html:base />

	<title>showResult.jsp</title>

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

		最终回归方程F检验值：${sdu.f }
		<br />
		显著性为0.05的F临界值:${sdu.f005 }
		<br />
		显著性为0.01的F临界值:${sdu.f001 }
		<br />
		<h2>
			最终回归方程F检验结论：
		</h2>

		<c:if test="${sdu.f<=sdu.f005}">总的来看，自变量对因变量的影响不显著</c:if>
		<c:if test="${(sdu.f-sdu.f005)*(sdu.f-sdu.f001)<0}">总的来看，自变量对因变量的影响显著</c:if>
		<c:if test="${sdu.f>sdu.f001}">总的来看，自变量对因变量的影响特别显著</c:if>
		<hr>
		<b> 根据逐步回归选入回归方程的自变量，最终列出回归方程为：<p>Y = <%=request.getAttribute("equation")%> <br/>

	<!-- <c:set var="tFlag" value="0"></c:set>
		<c:forEach var="i" items="${b}">
					+(${i }*X${tFlag })
					<c:set var="tFlag" value="${tFlag+1}"></c:set>
		</c:forEach>
		-->
		<br />
		</p>
		<h2>t(0.05)的值为：	${sdu.t005 }</h2>
		<h2>t(0.01)的值为：	${sdu.t001 }</h2>
		<h2>各变量的T检验值及与因变量的关系为：	</h2>
		<table border="1" align="center">
		<tr><td>变量</td><td>t检验值</td><td>显著性</td></tr>

			<c:set var="tFlag" value="0"></c:set>
			<%int j=0;%>
			<c:forEach var="i" items="${t}" begin="1">
			<%j++; %>
				<tr>
				<td><%="X"+j%></td>
					<td>
						${i }&nbsp;&nbsp;&nbsp;&nbsp;

					</td>
					<td>
						<c:if test="${i<=sdu.t005}">不显著</c:if>
						<c:if test="${(i-sdu.t005)*(i-sdu.t001)<0}">显著</c:if>
						<c:if test="${i>sdu.t001}">特别显著</c:if>
					</td>
				</tr>
				<c:set var="tFlag" value="${tFlag+1}"></c:set>
			</c:forEach>

		</table>
		<h2>
			预测结果为：${sdu.result }
		</h2>


	</center>
</body>
</html:html>
