<%@ page language="java" pageEncoding="gb2312" isELIgnored="false"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>

<title>ͼƬ��ʾ</title>
<script language="javascript">
function saveit()
{
	var temp = document.getElementById('pic').src;
	I1.document.location=temp;
	savepic();
}
function savepic()
{
	if(I1.document.readyState=="complete"){
		I1.document.execCommand("saveas");
	}
	else{
		window.setTimeout("savepic()",10);
	}
}
</script>

	</head>

	<body>
		<center>
			<img src="${graphURL }" border="1" name="ͼƬ" alt="ͼƬ" id="pic">
			<br><br><br><input type="button" value="����ͼƬ" name="B1" onclick="saveit();">
			<iframe name="I1" style="display:none"></iframe>
			<br><br><hr>
			<input type="button" value="����" onClick="javascript:history.back(-1);">
		</center>
	</body>
</html>
