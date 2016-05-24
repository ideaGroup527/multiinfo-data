<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@ page import="my_class.Parameters" %>
<jsp:useBean id="DataBaseConnection" class="my_bean.DataBaseConnection" scope="request" >
<jsp:setProperty name="DataBaseConnection" property="*" />
</jsp:useBean>
<html>
  <head>   
    <title>选择数据表 GetTable</title>
  </head> 
  <body bgcolor="#DDDDDD">
<form method="get" name="form" action="servlet/Get_table">
<% 
try{
String[] tableNames=DataBaseConnection.getTableNames();
%>
  <div align="center">
    <p>请选择数据表:</p>
    <p>Please choose the data table:    </p>
    <hr>
    <p>
      <select size="1" name="table_list">
        
<% for(int i=0;i<tableNames.length;i++){ %>
        <option value=<%=i %>><%=tableNames[i] %></option>
        <%} %>
      </select>
      <input type="submit" value="下一步(next)" name="button1">
<%
Parameters.conn=DataBaseConnection.getConn();
Parameters.tableNames=tableNames;
}
catch(Exception e){
	response.getWriter().println("连接数据库错误！");
}
 %>
    </p>
  </div>
</form>
</body>
</html>
