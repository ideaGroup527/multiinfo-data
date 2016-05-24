<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@ page import="my_class.Parameters"%>
<script language=javascript>
function checkAll(v){
  for (i=0;i<document.myform[v].length;i++)
    document.myform[v][i].checked = true;
}
function uncheckAll(v){
  for (i=0;i<document.myform[v].length;i++)
    document.myform[v][i].checked = false;
}
function switchAll(v){
  for (i=0;i<document.myform[v].length;i++)
    document.myform[v][i].checked = !document.myform[v][i].checked;
}
</script>
<html>
 <head>
    <title>因子分析Web软件(Web soft for factor analysis)</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312"></head>
  
  <body bgcolor="#DDDDDD">
  <div align="center">  
  	<p>请选择数据：    </p>
  	<p>Please choose the data: </p>
  	<hr>
    <form method="post" action="servlet/Get_data" name="myform">
      <table width="200" border="0" cellspacing="0">
          <tr>
            <td><input name="button2" type=button onClick='checkAll("row")' value="行全选  (All rows) "></td>
            <td><input name="button" type=button onClick='uncheckAll("row")' value="行全不选  (No row selected) "></td>
            <td><input name="button3" type=button onClick='switchAll("row")' value="行反选 (Unselect rows) "></td>
          </tr>
          <tr>
            <td><input name="button4" type=button onClick='checkAll("col")' value="列全选(All columns)"></td>
            <td><input name="button5" type=button onClick='uncheckAll("col")' value="列全不选(No column selected)"></td>
            <td><input name="button6" type=button onClick='switchAll("col")' value="列反选(Unselet columns)"></td>
          </tr>
      </table>
		<br>
		<table border="3">
	<tbody>
	<%
	for(int i=0;i<Parameters.x.length;i++){
	%>
	<tr>
	<%for(int j=0;j<Parameters.x[0].length;j++){ %>
	<td>
	<%if(i==0&&j!=0){ %>
	<input type="checkbox" name="col" value=<%=j%> checked="checked">
	<%} %>
	<%if(i!=0&&j==0){%>
	<input type="checkbox" name="row" value=<%=i%> checked="checked">
	<%} %>
	<%=Parameters.x[i][j]%>
	</td>
	<%} %>
	</tr>
	<%} %>
	</tbody></table>
	<input type="submit" value="下一步(Next)" name="button1" >
    <input type="button" value="后退(Back)" onClick="javascript:history.back(-1);">
    </form>
</div>
  </body>
</html>
