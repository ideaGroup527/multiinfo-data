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
    <title>Excel�ļ����ϴ�(File upload)</title>
    <style type="text/css">
    </style>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312"></head>
  
  <body bgcolor="#DDDDDD">
  <div align="center"> 
    <p>��ѡ����Ҫ�������ӷ�����Excel�ļ����ϴ�</p>
    <p>Please select the Excel file you want to do factor analysis and upload it.</p>
    <br>
    <hr>
    <form method="POST" name="uploadform" enctype="multipart/form-data" action="servlet/Fileupload">
	<p>
	<input type="file" name="sourcefile" size="20">
	<input type="submit" value="�ύ(Submit)" name="B1" onClick="check()">
	<input type="reset" value="����(Reset)" name="B2">
	</p>
	<p>*˵����������ԴΪ*.xls�ļ����ļ��еĵ�һ��Ϊ����������һ��Ϊ�������������Ϊ������������������Դ��Ҳ�����ưڷŵġ�</p>
	
	<p>* Note: The data source for processing is *.xls file, the first  
	row in the file is variable name, the first column is sample name, and the rest is data area. In other data sources, the data table is put in the similar way.</p>
	</form>
  <input type="button" value="����(Back)" onClick="javascript:history.back(-1);">
  </div>
  </body>
</html>
