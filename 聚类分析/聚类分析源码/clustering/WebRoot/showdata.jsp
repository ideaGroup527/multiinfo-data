<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@ page import="my_class.Parameters"%>

<html>
 <head>
    <title>�������WEB���</title>

<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<style type="text/css">
<!--
.STYLE1 {font-size: 16px}
-->
</style>
</head>
  
  <body bgcolor="#DDDDDD">
  <div align="center">  
    <form method="post" action="clustering.jsp" name="myform">
      <p class="STYLE1">��������ѡ�������ݣ�</p>
      <p class="STYLE1">The following are the data you've chosen:</p>
      <hr>
      <table border="1">
	<tbody>
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
	</tbody></table>
	<input type="submit" value="��һ��(Next)" name="button1" >
	<input type="button" value="����(Back)" onClick="javascript:history.back(-1);">
    </form>
</div>
  </body>
</html>