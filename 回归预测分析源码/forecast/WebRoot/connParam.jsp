<%@ page language="java" pageEncoding="gb2312"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>

		<title>Parameters for database connection</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">

	</head>

	<body>

		<p align="center">
			<b><font size="6">数据库连接参数<br>
			</font><font size="4">Parameters for database connection</font></b></p>
		<p>
		</p>
		<form method="post" action="getTableName">
			<table width="414" border="1" align="center">
				<tr>
					<td>
						数据库类型
						Database type
					</td>
					<td width="176">
						<select name="databaseKind" size="1">
							<option value="sqlserver" selected>
								SQL Server
							</option>
							<option value="oracle">
								ORACLE
							</option>
							<option value="mysql">
								MySQL
							</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>
						数据库名
						Database name</td>
					<td width="176">
						<input type="text" name="dataBase" size="23">
					</td>
				</tr>
				<tr>
					<td>
						用户名
						User name</td>
					<td width="176">
						<input type="text" name="userName" size="23">
					</td>
				</tr>
				<tr>
					<td>
						密码
						Password</td>
					<td width="176">
						<input type="password" name="password" size="23">
					</td>
				</tr>
				<tr>
					<td>
						IP
					</td>
					<td width="176">
						<input type="text" name="ip" size="23">
					</td>
				</tr>
			</table>
			<br>
			<br>
			<center>
				<input type="submit" value="连接数据库(Connect)">
				<input type="reset" value="重置(Reset)">
							<br><br><hr>
			<input type="button" value="后退(Back)" onClick="javascript:history.back(-1);">
			</center>
		</form>
		
	</body>
</html>
