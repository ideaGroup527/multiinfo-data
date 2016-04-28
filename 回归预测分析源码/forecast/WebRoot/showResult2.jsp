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
		<b>前移一位最终回归方程F检验值 F-test value of last regression equation for moving 1 
		period forward: </b>${sdu1.f }
		<br />
		<b>前移二位最终回归方程F检验值 </b>
		<b>F-test value of last regression equation for moving 2 periods 
		forward: </b>
		${sdu2.f }
		<br />
		<b>前移三位最终回归方程F检验值 </b>
		<b>F-test value of last regression equation for moving 3 periods 
		forward: </b>
		${sdu3.f }
		<hr>
		<br />
		<b>前移一位显著性为0.05的F临界值 </b>Critical F value of moving 1 dependent period forward at 
		significance 0.05<b>:</b>${sdu1.f005 }
		<br />
		<b>前移二位显著性为0.05的F临界值 </b>
		Critical F value of moving dependent 2 periods forward at significance 0.05<b>:</b>${sdu2.f005 }
		<br />
		<b>前移三位显著性为0.05的F临界值 </b>
		Critical F value of moving dependent 3 periods forward at significance 0.05<b>:</b>${sdu3.f005 }
		<hr>
		<br />
		<b>前移一位显著性为0.01的F临界值 </b>
		Critical F value of moving dependent 1 period forward at significance 0.01<b>:</b>${sdu1.f001 }
		<br />
		<b>前移二位显著性为0.01的F临界值 </b>
		Critical F value of moving dependent 2 periods forward at significance 0.01<b>:</b>${sdu2.f001 }
		<br />
		<b>前移三位显著性为0.01的F临界值 </b>
		Critical F value of moving dependent 3 periods forward at significance 0.01<b>:</b>${sdu3.f001 }
		<hr>
	<p align="left">
		<br />
		<b> 最终前移一位回归方程F检验结论 Last F-test Conclusion of regression equation for moving dependent 1 period forward:</b>
		<c:if test="${sdu1.f<=sdu1.f005}">总的来看，自变量对因变量的影响不显著</c:if>
		<c:if test="${(sdu1.f-sdu1.f005)*(sdu1.f-sdu1.f001)<0}">总的来看，自变量对因变量的影响显著</c:if>
		<c:if test="${sdu1.f>sdu1.f001}">总的来看，自变量对因变量的影响特别显著</c:if>
		<c:if test="${sdu1.f<=sdu1.f005}">In general, the influence of independent variables to dependent is not significant.</c:if>
		<c:if test="${(sdu1.f-sdu1.f005)*(sdu1.f-sdu1.f001)<0}">In general, the influence of independent. variables to dependent is significan.</c:if>
		<c:if test="${sdu1.f>sdu1.f001}">In general, the influence of independent variables to dependent is very significant.</c:if>
		<br />
		<b> 最终前移二位回归方程F检验结论 Last F-test Conclusion of regression equation for moving dependent 2 periods forward</b>
		<c:if test="${sdu2.f<=sdu2.f005}">总的来看，自变量对因变量的影响不显著</c:if>
		<c:if test="${(sdu2.f-sdu2.f005)*(sdu2.f-sdu2.f001)<0}">总的来看，自变量对因变量的影响显著</c:if>
		<c:if test="${sdu2.f>sdu2.f001}">总的来看，自变量对因变量的影响特别显著</c:if>
		<c:if test="${sdu2.f<=sdu2.f005}">In general, the influence of independent variables to dependent is not significant.</c:if>
		<c:if test="${(sdu2.f-sdu2.f005)*(sdu2.f-sdu2.f001)<0}">In general, the influence of independent. variables to dependent is significan.</c:if>
		<c:if test="${sdu2.f>sdu2.f001}">In general, the influence of independent variables to dependent is very significant.</c:if>
		<br />
		<b> 最终前移三位回归方程F检验结论  Last F-test Conclusion of regression equation for moving dependent 3 periods forward</b>
		<c:if test="${sdu3.f<=sdu3.f005}">总的来看，自变量对因变量的影响不显著</c:if>
		<c:if test="${(sdu3.f-sdu3.f005)*(sdu3.f-sdu3.f001)<0}">总的来看，自变量对因变量的影响显著</c:if>
		<c:if test="${sdu3.f>sdu3.f001}">总的来看，自变量对因变量的影响特别显著</c:if>
		<c:if test="${sdu3.f<=sdu3.f005}">In general, the influence of independent variables to dependent is not significant.</c:if>
		<c:if test="${(sdu3.f-sdu3.f005)*(sdu3.f-sdu3.f001)<0}">In general, the influence of independent. variables to dependent is significan.</c:if>
		<c:if test="${sdu3.f>sdu3.f001}">In general, the influence of independent variables to dependent is very significant.</c:if>
		</p>
		<hr>
	<p align="left"><b>根据逐步回归选入回归方程的自变量，可以列出回归方程：</b></p>
		<p>According to stepwise regression to select independents, the last regression equation is :</p>
	<p align="left">
		<b> 前移一位最终回归方程为 The last regression equation for moving dependent 1 period forward: Y = </b><%=request.getAttribute("equation1")%><br/>
		<br>
		<b> 前移二位最终回归方程为 The last regression equation for moving dependent 2 periods forwards: Y = </b><%=request.getAttribute("equation2")%><br/>
		<br>
		<b> 前移三位最终回归方程为 The last regression equation for moving dependent 3 periods forwards: Y = </b><%=request.getAttribute("equation3")%><br/>
		</p>
	<center>

		<hr>
		<b>前移一位t(0.05)的值为 t(0.05) for moving 1 dependent period forward:</b>${sdu1.t005 }
		<br />
		<b>前移两位t(0.05)的值为 t(0.05) for moving 2 dependent periods forward:</b>${sdu2.t005 }
		<br />
		<b>前移三位t(0.05)的值为 t(0.05) for moving 3 dependent periods forward:</b>${sdu3.t005 }
		<br />
		<hr>

		<b>前移一位t(0.01)的值为 t(0.01) for moving 1 dependent period forward:</b>${sdu1.t001 }
		<br />
		<b>前移两位t(0.01)的值为 t(0.01) for moving 2 dependent periods forward:</b>${sdu2.t001 }
		<br />
		<b>前移三位t(0.01)的值为 t(0.01) for moving 3 dependent periods forward:</b>${sdu3.t001 }
		<br />
		<hr>


		<b>前移1位各变量的T检验值及与因变量的关系为</b>
		<b>T-test values of independents and the relationship with dependent for moving dependent 1 period forward:</b>


		<table border="1" align="center">
				<tr><td>变量 Variable</td><td>t检验值 t_test value</td><td>显著性 Significance</td></tr>

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
						<c:if test="${i<=sdu1.t005}">不显著 Not significant</c:if>
						<c:if test="${(i-sdu1.t005)*(i-sdu1.t001)<0}">显著 Significant</c:if>
						<c:if test="${i>sdu1.t001}">特别显著 Very significant</c:if>
					</td>
				</tr>
				<c:set var="tFlag" value="${tFlag+1}"></c:set>
			</c:forEach>

		</table>
		<b>前移2位各变量的T检验值及与因变量的关系为</b>

	<b>T-test values of independents and the relationship with dependent for moving dependent 2 periods forward:</b>
		<table border="1" align="center">
		<tr><td>变量 Variable</td><td>t检验值 t_test value</td><td>显著性 Significance</td></tr>

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
						<c:if test="${i<=sdu1.t005}">不显著Not significant</c:if>
						<c:if test="${(i-sdu1.t005)*(i-sdu1.t001)<0}">显著 Significant</c:if>
						<c:if test="${i>sdu1.t001}">特别显著 Very significant</c:if>
					</td>
				</tr>
				<c:set var="tFlag" value="${tFlag+1}"></c:set>
			</c:forEach>

		</table>
		<b>前移3位各变量的T检验值及与因变量的关系为</b>
		<b>T-test values of independents and the relationship with dependent for moving dependent 1 period forward:</b>

		<table border="1" align="center">
	<tr><td>变量 Variable</td><td>t检验值 t_test value</td><td>显著性 Significance</td></tr>
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
						<c:if test="${i<=sdu1.t005}">不显著</c:if>
						<c:if test="${(i-sdu1.t005)*(i-sdu1.t001)<0}">显著</c:if>
						<c:if test="${i>sdu1.t001}">特别显著</c:if>
					</td>
				</tr>
				<c:set var="tFlag" value="${tFlag+1}"></c:set>
			</c:forEach>
		</table>
		<hr>

		<b>前移一位预测结果为 Forecast result by moving dependent 1 period forward:</b>${sdu1.result }
		<br />
		<b>前移两位预测结果为Forecast result by moving dependent 2 periods forward:</b>${sdu2.result }
		<br />
		<b>前移三位预测结果为Forecast result by moving dependent 3 periods forward:</b>${sdu3.result }
		<br />
		<hr>
		<b>最终预测结果为 The last forecast result:</b>
		<br>前移1、2、3位预测结果按5:3:2加权平均 5:3:2 weighted average of moving dependent 1,2 and 3 periods forward:${sdu1.result*0.5+sdu2.result*0.3+sdu3.result*0.2}
		<br>前移1、2、3位预测结果按普通平均General average of moving dependent 1,2 and 3 periods forward:${(sdu1.result+sdu2.result+sdu3.result)/3}
	</center>
</body>
</html:html>
