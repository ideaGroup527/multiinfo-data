<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@ page import="my_class.Parameters"%>

<html>
 <head>
    <title>���ӷ���Web���(Web soft for factor analysis)</title>
</head> 
  <body bgcolor="#DDDDDD">
  <div align="center">  
    <form method="post" action="factor.jsp" name="myform">
      <p class="STYLE1">��������ѡ�������ݣ�</p>
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
	<p>��ѡ�����²����������ӷ�����</p>
	<p>Please choose the following parameters for factor analysis:</p>
	<table border="1" cellspacing="0">
        <tr>
          <td>�ſɱȵ�������(Jacobian iterative precision)</td>         
          <td><Input type=text value=0.0001 name="Jacobian"></td>
        </tr>
        <tr>
          <td>�������������ת����(The biggest orthogonal rotating precision of variance)</td>
          <td><Input type=text value=0.0001 name="Variance"></td>
        </tr>
    </table>
    <br><br>
	<input type="submit" value="��һ��(Next)" name="button1" >
	<input type="button" value="����(Back)" onClick="javascript:history.back(-1);">
    </form>
</div>
</body>
</html>