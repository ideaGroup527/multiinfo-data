<html>
<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<jsp:useBean id="choosesheet" class="my_bean.Choosesheet" scope="request">
</jsp:useBean>
 <head>
    <title>因子分析Web软件(Web soft for factor analysis)</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312"></head>
  
  <body bgcolor="#DDDDDD">
  <div align="center"> 
    <p>请选择工作表：</p>
    <p>Please choose the sheet: </p> 
    <hr>  
  </div><div align="center">
  <form name="form1" method="post" action="servlet/Get_sheet"> 
      <label> 
      <%
      String[] sheetNames=choosesheet.getSheetNames(); 
      int size=choosesheet.getSize();
      %>
      <select name="sheetN" size="1"> 
        <%for(int i=0;i<size;i++){%>
                    <option value=<%=i%>><%=sheetNames[i]%></option>
                    <%}%>
      </select> 
      </label><input type="submit" name="B1" value="提交(Submit)"> 
   </form>
    </div>
  </body>
</html>
