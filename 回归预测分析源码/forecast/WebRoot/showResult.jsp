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

		���ջع鷽��F����ֵ F
<html:html lang="true">
		Test
</html:html>
		value of
<html:html lang="true">
		last regression equation:
</html:html>${sdu.f }
		<br />
		������Ϊ0.05��F�ٽ�ֵ Critical F value for significance 0.05:${sdu.f005 }
		<br />
		������Ϊ0.01��F�ٽ�ֵ<html:html lang="true"> Critical F value for significance 
		0.0</html:html>1:${sdu.f001 }
		<br />
		<h2>
			���ջع鷽��F������ۣ�<br>
			Conclusion for last regression equation:</h2>
		<c:if test="${sdu.f<=sdu.f005}">�ܵ��������Ա������������Ӱ�첻����</c:if>
		<c:if test="${(sdu.f-sdu.f005)*(sdu.f-sdu.f001)<0}">�ܵ��������Ա������������Ӱ������</c:if>
		<c:if test="${sdu.f>sdu.f001}">�ܵ��������Ա������������Ӱ���ر�����</c:if><html:html lang="true"><p>
		<c:if test="${sdu.f<=sdu.f005}">Ingeneral, the influence of independent variables to dependent is not significant.</c:if>
		<c:if test="${(sdu.f-sdu.f005)*(sdu.f-sdu.f001)<0}">Ingeneral, the influence of independent. variables to dependent is significan.</c:if>
		<c:if test="${sdu.f>sdu.f001}">Ingeneral, the influence of independent variables to dependent is very significant.</c:if><html:html lang="true">.<p>
		<hr>
		<b> 
		<html:html lang="true"></html:html>
		�����𲽻ع�ѡ��ع鷽�̵��Ա����������г��ع鷽��Ϊ��<br>
		According to stepwise regression to select independents, the last regression equation is :</b><p>
		<b>Y = </b><%=request.getAttribute("equation")%><br/>

		<br />
		</p>
		<h2>t(0.05)��ֵΪ:	${sdu.t005 }</h2>
		<h2>t(0.01)��ֵΪ:	${sdu.t001 }</h2>
		<h2>���Ա�����T����ֵ����������Ĺ�ϵΪ��<br>
		T-test values of independents and the relationship with dependent</h2>
		<table border="1" align="center">
				<tr><td>����Variable</td><td>t����ֵt value</td><td>������Significant</td></tr>
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
						<c:if test="${i<=sdu.t005}">������Not significant</c:if>
						<c:if test="${(i-sdu.t005)*(i-sdu.t001)<0}">����Significant</c:if>
						<c:if test="${i>sdu.t001}">�ر�����Very significant</c:if>
					</td>
				</tr>
				<c:set var="tFlag" value="${tFlag+1}"></c:set>
			</c:forEach>
		</table>



	</center>
</body>
</html:html>
