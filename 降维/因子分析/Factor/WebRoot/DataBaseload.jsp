<%@ page language="java" pageEncoding="gb2312"%>
<html>
	<head>
		<title>���ݿ����Ӳ��� database connection</title>
	    <style type="text/css">
        </style>
	</head>
	<body bgcolor="#DDDDDD">
<br><br>
<div align="center">
		<p><font size="+1">���ݿ����Ӳ���</font> </p>
		<p><font size="+1">Parameters for database connection</font></p>
		<hr>
		<form method="post" action="getTable.jsp">
		  <center>
			  <table border="1" cellspacing="0">
                <tr>
                  <td><div align="center">���ݿ�����(Database type):</div></td>
                  <td><select name="dataBaseKind" size="1">
                    <option value=1 selected>SQL Server 2000</option>
                    <option value=2>SQL Server 2005</option>
                    <option value=3>MySQL</option>
                  </select></td>
                </tr>
                <tr>
                  <td><div align="center">���ݿ���(Database name):</div></td>
                  <td><input type="text" name="dataBaseName" size="21"></td>
                </tr>
                <tr>
                  <td> <div align="center">�û���(User name):</div></td>
                  <td><input type="text" name="userName" size="21"></td>
                </tr>
                <tr>
                  <td><div align="center">����(Password):</div></td>
                  <td><input type="password" name="password" size="22"></td>
                </tr>
                <tr>
                  <td><div align="center">IP:</div></td>
                  <td><input type="text" name="ip" size="21"></td>
                </tr>
              </table>
		    <p>
		      <input name="submit" type="submit" value="����(Connect)">
		      <input name="reset" type="reset" value="����(Reset)">
		    </p>
	      </center>
		</form>
	    <p align="center"><font color="#ff0000">*˵��:��ʱֻ֧��</font><font color="#ff0000">SQL Server 2000��SQL Server 2005��MySQL</font><font color="#ff0000">���ݿ�������Լ����ݴ������е�������ʾ������ÿһ�б�ʾһ�����������ݵĵ�һ��Ϊ��������</font></p>
	    <p align="center"><font color="#ff0000">*Note: only SQL Server 2000, SQL Server 2005 and MySQL database connection and data analysis are </font><font color="#ff0000">supported at present</font><font color="#ff0000">. Column names are variables, each row represents a sample, and the first column is the name of the sample.</font></p>
	    <p align="center">
	      <input name="button" type="button" onClick="javascript:history.back(-1);" value="����(Back)">
        </p>
	</div>
	</body>
</html>