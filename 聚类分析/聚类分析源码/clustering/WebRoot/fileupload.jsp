<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>

<html>
<script language='javascript'>
function check() {
 if (uploadform.sourcefile.value.length==0) {
  alert("��ѡ��Դ�ļ�!Please select source file!") ;
  return false ; }
}
</script>
  <head>
    <title>Excel�ļ����ϴ� fileupload</title>
    <style type="text/css">
<!--
.STYLE4 {font-family: "Courier New", Courier, monospace}
.STYLE6 {color: #FF0000}
-->
    </style>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312"></head>
  
  <body bgcolor="#DDDDDD">
  <div align="center">
    <p class="STYLE4">��ѡ����Ҫ���о��������Excel�ļ����ϴ�        </p>
    <p class="STYLE4">Please select the Excel file you want to do cluster analysis and upload it.<br></p>
    <hr>
    <form method="POST" name="uploadform" enctype="multipart/form-data" action="servlet/Fileupload">
	<p><input type="file" name="sourcefile" size="20"><input type="submit" value="�ύ(Submit)" name="B1" onClick="check()">
	<input type="reset" value="����(Reset)" name="B2">
	</p>
	<p class="STYLE6">*˵����������ԴΪ*.xls�ļ����ļ��еĵ�һ��Ϊ����������һ��Ϊ�������������Ϊ��������</p>
	
	<p class="STYLE6">* Note: The data source for processing is *.xls file. The first  
	row in the file is variable name, the first column is sample name,and the rest is data area. </p>
	</form>
  <input type="button" value="����(Back)" onClick="javascript:history.back(-1);">
  </div>
  </body>
</html>
