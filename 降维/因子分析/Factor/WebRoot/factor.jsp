<%@ page language="java" import="java.util.*,my_class.*,java.text.DecimalFormat" pageEncoding="gb2312"%>

<html>
  <head>
    <title>数据处理(Data Processing)</title>
    <style type="text/css">
    </style>
</head>
  <body bgcolor="#DDDDDD">
    <div align="center">
    <form method="POST" name="myform" action="servlet/factoring">
	<br>
	          特征值百分比表(Eigenvalue percentage table)
    <hr>
	<table border="1" cellspacing="0">
	   <tr>
	   <th>N0</th>
	   <th>Eigenvalue</th>
	   <th>Percentage values</th>
	   <th>Cumulative percentage value</th>
	   </tr>
  <%
  	DecimalFormat df = new DecimalFormat( "0.00000 ");  
  	Parameters.Jacobian = Double.valueOf(request.getParameter("Jacobian"));
  	Parameters.Variance = Double.valueOf(request.getParameter("Variance"));
  	factoranalysis fac = new factoranalysis();
  	fac.Datastandard();
  	fac.Related_Coefficient_Matrix();
  	fac.Characteristic_Matrix(Parameters.Jacobian, Parameters.Variance);
  	for(int i=0;i<fac.ch1.length;i++){
  %>
	<tr>
	<td><%=i+1 %></td>
	<td><%=df.format(fac.ch1[i]) %></td>
	<td><%=df.format(fac.Lh[i]) %></td>
	<td><%=df.format(fac.Lh1[i]) %></td>
	</tr>	
	<%					
	}
	%>
	</table>
    <br><br><br>
      请通过特征值百分比表选择因子个数((一般规则：累计百分比>=85%))：
      <select name="factor_number" ">
            		<option value="1" >1</option>
            		<option value="2" selected>2</option>
            		<option value="3" >3</option>
            		<option value="4" >4</option>
            		<option value="5" >5</option>
            		<option value="6" >6</option>
            		<option value="7" >7</option>
            		<option value="8" >8</option>
           		</select>
   <br>Please choose factor number through the Eigenvalue percentage table(general rule: the accumulative percentage >= 85%)
      			
	  <p><input type="submit" value="提交(Submit)" name="B1">
	  <input type="reset" value="重置(Reset)" name="B2">
	  </p>
	  </form>
  <input type="button" value="后退(Back)" onClick="javascript:history.back(-1);">
    </div>
  </body>
</html>
