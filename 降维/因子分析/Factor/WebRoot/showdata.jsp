<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@ page import="my_class.Parameters"%>

<html>
 <head>
    <title>因子分析Web软件(Web soft for factor analysis)</title>
</head> 
  <body bgcolor="#DDDDDD">
  <div align="center">  
    <form method="post" action="factor.jsp" name="myform">
      <p class="STYLE1">以下是您选择后的数据：</p>
      <p class="STYLE1">The following are the data you've chosen:</p>
      <hr>
	  <table border="1">
		
		<%
		for(int i=0;i<Parameters.x_new.length;i++){
		%>
		<tr>
		<%for(int j=0;j<Parameters.x_new[0].length;j++){ %>
		<td>
		<%=Parameters.x_new[i][j]%>
		</td>
		<%} %>
		</tr>
		<%} %>
		
		</table>
	<br>
	<p>请选择以下参数进行因子分析：</p>
	<p>Please choose the following parameters for factor analysis:</p>
	<table border="1" cellspacing="0">
        <tr>
          <td>雅可比迭代精度(Jacobian iterative precision)</td>         
          <td><Input type=text value=0.0001 name="Jacobian"></td>
        </tr>
        <tr>
          <td>方差最大正交旋转精度(The biggest orthogonal rotating precision of variance)</td>
          <td><Input type=text value=0.0001 name="Variance"></td>
        </tr>
    </table>
    <br><br>
	<input type="submit" value="下一步(Next)" name="button1" >
	<input type="button" value="后退(Back)" onClick="javascript:history.back(-1);">
    </form>
</div>
</body>
</html>