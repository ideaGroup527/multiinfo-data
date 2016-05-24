<%@ page language="java" pageEncoding="gb2312"%>
<html>
	<head>
		<title>数据库连接参数 database connection</title>
	    <style type="text/css">
        </style>
	</head>
	<body bgcolor="#DDDDDD">
<br><br>
<div align="center">
		<p><font size="+1">数据库连接参数</font> </p>
		<p><font size="+1">Parameters for database connection</font></p>
		<hr>
		<form method="post" action="getTable.jsp">
		  <center>
			  <table border="1" cellspacing="0">
                <tr>
                  <td><div align="center">数据库类型(Database type):</div></td>
                  <td><select name="dataBaseKind" size="1">
                    <option value=1 selected>SQL Server 2000</option>
                    <option value=2>SQL Server 2005</option>
                    <option value=3>MySQL</option>
                  </select></td>
                </tr>
                <tr>
                  <td><div align="center">数据库名(Database name):</div></td>
                  <td><input type="text" name="dataBaseName" size="21"></td>
                </tr>
                <tr>
                  <td> <div align="center">用户名(User name):</div></td>
                  <td><input type="text" name="userName" size="21"></td>
                </tr>
                <tr>
                  <td><div align="center">密码(Password):</div></td>
                  <td><input type="password" name="password" size="22"></td>
                </tr>
                <tr>
                  <td><div align="center">IP:</div></td>
                  <td><input type="text" name="ip" size="21"></td>
                </tr>
              </table>
		    <p>
		      <input name="submit" type="submit" value="连接(Connect)">
		      <input name="reset" type="reset" value="重置(Reset)">
		    </p>
	      </center>
		</form>
	    <p align="center"><font color="#ff0000">*说明:暂时只支持</font><font color="#ff0000">SQL Server 2000、SQL Server 2005和MySQL</font><font color="#ff0000">数据库的连接以及数据处理。表中的列名表示变量，每一行表示一个样本，数据的第一列为样本名。</font></p>
	    <p align="center"><font color="#ff0000">*Note: only SQL Server 2000, SQL Server 2005 and MySQL database connection and data analysis are </font><font color="#ff0000">supported at present</font><font color="#ff0000">. Column names are variables, each row represents a sample, and the first column is the name of the sample.</font></p>
	    <p align="center">
	      <input name="button" type="button" onClick="javascript:history.back(-1);" value="后退(Back)">
        </p>
	</div>
	</body>
</html>