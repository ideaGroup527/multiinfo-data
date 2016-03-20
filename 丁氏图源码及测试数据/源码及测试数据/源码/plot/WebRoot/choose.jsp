<%@ page language="java" pageEncoding="gb2312" isELIgnored="false"%>

<html>
	<head>
		<meta http-equiv="Content-Language" content="zh-cn">
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
		<title>选择绘图方法</title>
		
		<script type="text/javascript">
			function chooseChartKind(kind){
				document.getElementById("chartKind").value= kind;
				document.chartForm.submit();
			}
		</script>
		
	</head>
	<body>
		<br>
		<h2>
			<center>选择绘图方法</center>
		</h2>
		<br>
		<form name="chartForm" method="POST" action="midChooseData" name="ding">
			<input type="hidden" name="tran" value="${tran }">
			<input type="hidden" id="chartKind" name="chartKind">
			<br><br>
			<p align="center">
				<input type="button" value="丁氏图" onclick="chooseChartKind('ding')">
				<input type="button" value="饼状图" onclick="chooseChartKind('pie')">
				<input type="button" value="折线图" onclick="chooseChartKind('line')">
				<input type="button" value="柱状图" onclick="chooseChartKind('bar')">
				<input type="button" value="散点图" onclick="chooseChartKind('disperse')">
			</p>
		</form>
<center>
			<br><br><hr>
			<input type="button" value="后退" onClick="javascript:history.back(-1);">
</center>
	</body>

</html>