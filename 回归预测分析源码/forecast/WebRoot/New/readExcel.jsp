<%@ page language="java" pageEncoding="gb2312"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>��ȡExcel</title>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />

<script src="js/jquery.js" type="text/javascript"></script>
<script src="js/ajax.js" type="text/javascript">    
</script>
	</head>
	<body>
		<center>
			<h2>��ѡȡ��Ҫ��ȡ��Excel(Please Select the Excel file to be read)</h2>
		
			<form method="POST" action="upload" ENCTYPE="multipart/form-data">
				<p align="center">
					ѡ��Excel�ļ�(Selec excel file):<input type="file" name="excelPath" size="20">
				</p>
				<input type="submit" name="�ύ" value="�ύ(Submit)"/>
			<input type="button" value="����(Back)" onClick="javascript:history.back(-1);"></form>

		</center>

	</body>
</html>