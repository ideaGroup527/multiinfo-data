<%@ page language="java" pageEncoding="gb2312"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>

		<title>数据库连接参数</title>
		<link href="css/css.css" rel="stylesheet" type="text/css" media="all" />
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">

	</head>

	<body>
<br><br>
		<p align="center">
			<b><font size="6">数据库连接参数</font> </b>
		</p>
		<p>
		</p>
		<form method="post" action="getTableName">
			<table width="251" border="1" align="center">
				<tr>
					<td>
						数据库类型
					</td>
					<td>
						<select name="databaseKind" size="1">
							<option value="sqlserver" selected>
								SQLSERVER
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
					</td>
					<td>
						<input type="text" name="dataBase" size="21">
					</td>
				</tr>
				<tr>
					<td>
						用户名
					</td>
					<td>
						<input type="text" name="userName" size="21">
					</td>
				</tr>
				<tr>
					<td>
						密码
					</td>
					<td>
						<input type="password" name="password" size="21">
					</td>
				</tr>
				<tr>
					<td>
						IP
					</td>
					<td>
						<input type="text" name="ip" size="21">
					</td>
				</tr>
			</table>
			<br>
			<br>
			<center>
				<input type="submit" value="连接数据库">
				<input type="reset" value="重置">
							<br><br><hr>
			<input type="button" value="后退" onClick="javascript:history.back(-1);">
			</center>
		</form>
		
	</body>
</html>
