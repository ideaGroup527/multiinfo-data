<%@ page language="java" pageEncoding="gb2312" isELIgnored="false"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>信息提示页面</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
	</head>

	<body>

		<center>
			<br>
			<br>
			<br>
			<h2>
				<font color="red"><li>
						${message }
				</font>
			</h2>
			<br>
			<br>
			<input type="button" value="后退" onclick="history.back(-1)">
		</center>
	</body>
</html>