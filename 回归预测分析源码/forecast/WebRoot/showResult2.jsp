<%@ page language="java" import="java.util.*" pageEncoding="GB2312"
	contentType="text/html;charset=gb2312" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>




<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
<head>

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
		<b>ǰ��һλ���ջع鷽��F����ֵ F-test value of last regression equation for moving 1 
		period forward: </b>${sdu1.f }
		<br />
		<b>ǰ�ƶ�λ���ջع鷽��F����ֵ </b>
		<b>F-test value of last regression equation for moving 2 periods 
		forward: </b>
		${sdu2.f }
		<br />
		<b>ǰ����λ���ջع鷽��F����ֵ </b>
		<b>F-test value of last regression equation for moving 3 periods 
		forward: </b>
		${sdu3.f }
		<hr>
		<br />
		<b>ǰ��һλ������Ϊ0.05��F�ٽ�ֵ </b>Critical F value of moving 1 dependent period forward at 
		significance 0.05<b>:</b>${sdu1.f005 }
		<br />
		<b>ǰ�ƶ�λ������Ϊ0.05��F�ٽ�ֵ </b>
		Critical F value of moving dependent 2 periods forward at significance 0.05<b>:</b>${sdu2.f005 }
		<br />
		<b>ǰ����λ������Ϊ0.05��F�ٽ�ֵ </b>
		Critical F value of moving dependent 3 periods forward at significance 0.05<b>:</b>${sdu3.f005 }
		<hr>
		<br />
		<b>ǰ��һλ������Ϊ0.01��F�ٽ�ֵ </b>
		Critical F value of moving dependent 1 period forward at significance 0.01<b>:</b>${sdu1.f001 }
		<br />
		<b>ǰ�ƶ�λ������Ϊ0.01��F�ٽ�ֵ </b>
		Critical F value of moving dependent 2 periods forward at significance 0.01<b>:</b>${sdu2.f001 }
		<br />
		<b>ǰ����λ������Ϊ0.01��F�ٽ�ֵ </b>
		Critical F value of moving dependent 3 periods forward at significance 0.01<b>:</b>${sdu3.f001 }
		<hr>
	<p align="left">
		<br />
		<b> ����ǰ��һλ�ع鷽��F������� Last F-test Conclusion of regression equation for moving dependent 1 period forward:</b>
		<c:if test="${sdu1.f<=sdu1.f005}">�ܵ��������Ա������������Ӱ�첻����</c:if>
		<c:if test="${(sdu1.f-sdu1.f005)*(sdu1.f-sdu1.f001)<0}">�ܵ��������Ա������������Ӱ������</c:if>
		<c:if test="${sdu1.f>sdu1.f001}">�ܵ��������Ա������������Ӱ���ر�����</c:if>
		<c:if test="${sdu1.f<=sdu1.f005}">In general, the influence of independent variables to dependent is not significant.</c:if>
		<c:if test="${(sdu1.f-sdu1.f005)*(sdu1.f-sdu1.f001)<0}">In general, the influence of independent. variables to dependent is significan.</c:if>
		<c:if test="${sdu1.f>sdu1.f001}">In general, the influence of independent variables to dependent is very significant.</c:if>
		<br />
		<b> ����ǰ�ƶ�λ�ع鷽��F������� Last F-test Conclusion of regression equation for moving dependent 2 periods forward</b>
		<c:if test="${sdu2.f<=sdu2.f005}">�ܵ��������Ա������������Ӱ�첻����</c:if>
		<c:if test="${(sdu2.f-sdu2.f005)*(sdu2.f-sdu2.f001)<0}">�ܵ��������Ա������������Ӱ������</c:if>
		<c:if test="${sdu2.f>sdu2.f001}">�ܵ��������Ա������������Ӱ���ر�����</c:if>
		<c:if test="${sdu2.f<=sdu2.f005}">In general, the influence of independent variables to dependent is not significant.</c:if>
		<c:if test="${(sdu2.f-sdu2.f005)*(sdu2.f-sdu2.f001)<0}">In general, the influence of independent. variables to dependent is significan.</c:if>
		<c:if test="${sdu2.f>sdu2.f001}">In general, the influence of independent variables to dependent is very significant.</c:if>
		<br />
		<b> ����ǰ����λ�ع鷽��F�������  Last F-test Conclusion of regression equation for moving dependent 3 periods forward</b>
		<c:if test="${sdu3.f<=sdu3.f005}">�ܵ��������Ա������������Ӱ�첻����</c:if>
		<c:if test="${(sdu3.f-sdu3.f005)*(sdu3.f-sdu3.f001)<0}">�ܵ��������Ա������������Ӱ������</c:if>
		<c:if test="${sdu3.f>sdu3.f001}">�ܵ��������Ա������������Ӱ���ر�����</c:if>
		<c:if test="${sdu3.f<=sdu3.f005}">In general, the influence of independent variables to dependent is not significant.</c:if>
		<c:if test="${(sdu3.f-sdu3.f005)*(sdu3.f-sdu3.f001)<0}">In general, the influence of independent. variables to dependent is significan.</c:if>
		<c:if test="${sdu3.f>sdu3.f001}">In general, the influence of independent variables to dependent is very significant.</c:if>
		</p>
		<hr>
	<p align="left"><b>�����𲽻ع�ѡ��ع鷽�̵��Ա����������г��ع鷽�̣�</b></p>
		<p>According to stepwise regression to select independents, the last regression equation is :</p>
	<p align="left">
		<b> ǰ��һλ���ջع鷽��Ϊ The last regression equation for moving dependent 1 period forward: Y = </b><%=request.getAttribute("equation1")%><br/>
		<br>
		<b> ǰ�ƶ�λ���ջع鷽��Ϊ The last regression equation for moving dependent 2 periods forwards: Y = </b><%=request.getAttribute("equation2")%><br/>
		<br>
		<b> ǰ����λ���ջع鷽��Ϊ The last regression equation for moving dependent 3 periods forwards: Y = </b><%=request.getAttribute("equation3")%><br/>
		</p>
	<center>

		<hr>
		<b>ǰ��һλt(0.05)��ֵΪ t(0.05) for moving 1 dependent period forward:</b>${sdu1.t005 }
		<br />
		<b>ǰ����λt(0.05)��ֵΪ t(0.05) for moving 2 dependent periods forward:</b>${sdu2.t005 }
		<br />
		<b>ǰ����λt(0.05)��ֵΪ t(0.05) for moving 3 dependent periods forward:</b>${sdu3.t005 }
		<br />
		<hr>

		<b>ǰ��һλt(0.01)��ֵΪ t(0.01) for moving 1 dependent period forward:</b>${sdu1.t001 }
		<br />
		<b>ǰ����λt(0.01)��ֵΪ t(0.01) for moving 2 dependent periods forward:</b>${sdu2.t001 }
		<br />
		<b>ǰ����λt(0.01)��ֵΪ t(0.01) for moving 3 dependent periods forward:</b>${sdu3.t001 }
		<br />
		<hr>


		<b>ǰ��1λ��������T����ֵ����������Ĺ�ϵΪ</b>
		<b>T-test values of independents and the relationship with dependent for moving dependent 1 period forward:</b>


		<table border="1" align="center">
				<tr><td>���� Variable</td><td>t����ֵ t_test value</td><td>������ Significance</td></tr>

			<c:set var="tFlag" value="0"></c:set>
						<%int j=0;%>
			<c:forEach var="i" items="${t_1}" begin="1">
						<%j++; %>
				<tr>
								<td><%="X"+j%></td>
					<td>
						${i }&nbsp;&nbsp;&nbsp;&nbsp;

					</td>
					<td>
						<c:if test="${i<=sdu1.t005}">������ Not significant</c:if>
						<c:if test="${(i-sdu1.t005)*(i-sdu1.t001)<0}">���� Significant</c:if>
						<c:if test="${i>sdu1.t001}">�ر����� Very significant</c:if>
					</td>
				</tr>
				<c:set var="tFlag" value="${tFlag+1}"></c:set>
			</c:forEach>

		</table>
		<b>ǰ��2λ��������T����ֵ����������Ĺ�ϵΪ</b>

	<b>T-test values of independents and the relationship with dependent for moving dependent 2 periods forward:</b>
		<table border="1" align="center">
		<tr><td>���� Variable</td><td>t����ֵ t_test value</td><td>������ Significance</td></tr>

			<c:set var="tFlag" value="0"></c:set>
						<% j=0;%>
			<c:forEach var="i" items="${t_2}" begin="1">
						<%j++; %>
				<tr>
								<td><%="X"+j%></td>
					<td>
						${i }&nbsp;&nbsp;&nbsp;&nbsp;

					</td>
					<td>
						<c:if test="${i<=sdu1.t005}">������Not significant</c:if>
						<c:if test="${(i-sdu1.t005)*(i-sdu1.t001)<0}">���� Significant</c:if>
						<c:if test="${i>sdu1.t001}">�ر����� Very significant</c:if>
					</td>
				</tr>
				<c:set var="tFlag" value="${tFlag+1}"></c:set>
			</c:forEach>

		</table>
		<b>ǰ��3λ��������T����ֵ����������Ĺ�ϵΪ</b>
		<b>T-test values of independents and the relationship with dependent for moving dependent 1 period forward:</b>

		<table border="1" align="center">
	<tr><td>���� Variable</td><td>t����ֵ t_test value</td><td>������ Significance</td></tr>
			<c:set var="tFlag" value="0"></c:set>
						<% j=0;%>
			<c:forEach var="i" items="${t_3}" begin="1">
						<%j++; %>
				<tr>
								<td><%="X"+j%></td>
					<td>
						${i }&nbsp;&nbsp;&nbsp;&nbsp;

					</td>
					<td>
						<c:if test="${i<=sdu1.t005}">������</c:if>
						<c:if test="${(i-sdu1.t005)*(i-sdu1.t001)<0}">����</c:if>
						<c:if test="${i>sdu1.t001}">�ر�����</c:if>
					</td>
				</tr>
				<c:set var="tFlag" value="${tFlag+1}"></c:set>
			</c:forEach>
		</table>
		<hr>

		<b>ǰ��һλԤ����Ϊ Forecast result by moving dependent 1 period forward:</b>${sdu1.result }
		<br />
		<b>ǰ����λԤ����ΪForecast result by moving dependent 2 periods forward:</b>${sdu2.result }
		<br />
		<b>ǰ����λԤ����ΪForecast result by moving dependent 3 periods forward:</b>${sdu3.result }
		<br />
		<hr>
		<b>����Ԥ����Ϊ The last forecast result:</b>
		<br>ǰ��1��2��3λԤ������5:3:2��Ȩƽ�� 5:3:2 weighted average of moving dependent 1,2 and 3 periods forward:${sdu1.result*0.5+sdu2.result*0.3+sdu3.result*0.2}
		<br>ǰ��1��2��3λԤ��������ͨƽ��General average of moving dependent 1,2 and 3 periods forward:${(sdu1.result+sdu2.result+sdu3.result)/3}
	</center>
</body>
</html:html>
