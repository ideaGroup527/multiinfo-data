<%@ page language="java" pageEncoding="gb2312"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>读取Excel</title>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />

<script src="js/jquery.js" type="text/javascript"></script>
<script src="js/ajax.js" type="text/javascript">    
</script>
	</head>
	<body>
		<center>
			<h2>请选取需要读取的Excel(Please Select the Excel file to be read)</h2>
		
			<form method="POST" action="upload" ENCTYPE="multipart/form-data">
				<p align="center">
					选择Excel文件(Selec excel file):<input type="file" name="excelPath" size="20">
				</p>
				<input type="submit" name="提交" value="提交(Submit)"/>
			<input type="button" value="后退(Back)" onClick="javascript:history.back(-1);"></form>

		</center>

	</body>
</html>