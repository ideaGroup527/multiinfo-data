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
			<b><font size="6">���ݿ����Ӳ���<br>
			</font><font size="4">Parameters for database connection</font></b></p>
		<p>
		</p>
		<form method="post" action="getTableName">
			<table width="414" border="1" align="center">
				<tr>
					<td>
						���ݿ�����
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
						���ݿ���
						Database name</td>
					<td width="176">
						<input type="text" name="dataBase" size="23">
					</td>
				</tr>
				<tr>
					<td>
						�û���
						User name</td>
					<td width="176">
						<input type="text" name="userName" size="23">
					</td>
				</tr>
				<tr>
					<td>
						����
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
				<input type="submit" value="�������ݿ�(Connect)">
				<input type="reset" value="����(Reset)">
							<br><br><hr>
			<input type="button" value="����(Back)" onClick="javascript:history.back(-1);">
			</center>
		</form>
		
	</body>
</html>
