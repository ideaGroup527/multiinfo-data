<%@ page language="java" pageEncoding="gb2312" isELIgnored="false"%>

<html>
	<head>
		<meta http-equiv="Content-Language" content="zh-cn">
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
		<title>ѡ���ͼ����</title>
		
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
			<center>ѡ���ͼ����</center>
		</h2>
		<br>
		<form name="chartForm" method="POST" action="midChooseData" name="ding">
			<input type="hidden" name="tran" value="${tran }">
			<input type="hidden" id="chartKind" name="chartKind">
			<br><br>
			<p align="center">
				<input type="button" value="����ͼ" onclick="chooseChartKind('ding')">
				<input type="button" value="��״ͼ" onclick="chooseChartKind('pie')">
				<input type="button" value="����ͼ" onclick="chooseChartKind('line')">
				<input type="button" value="��״ͼ" onclick="chooseChartKind('bar')">
				<input type="button" value="ɢ��ͼ" onclick="chooseChartKind('disperse')">
			</p>
		</form>
<center>
			<br><br><hr>
			<input type="button" value="����" onClick="javascript:history.back(-1);">
</center>
	</body>

</html>