<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>

<html>
<script language='javascript'>
function check() {
 if (uploadform.sourcefile.value.length==0) {
  alert("请选择源文件!Please select source file!") ;
  return false ; }
}
</script>
  <head>
    <title>Excel文件并上传(File upload)</title>
    <style type="text/css">
    </style>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312"></head>
  
  <body bgcolor="#DDDDDD">
  <div align="center"> 
    <p>请选择您要进行因子分析的Excel文件并上传</p>
    <p>Please select the Excel file you want to do factor analysis and upload it.</p>
    <br>
    <hr>
    <form method="POST" name="uploadform" enctype="multipart/form-data" action="servlet/Fileupload">
	<p>
	<input type="file" name="sourcefile" size="20">
	<input type="submit" value="提交(Submit)" name="B1" onClick="check()">
	<input type="reset" value="重置(Reset)" name="B2">
	</p>
	<p>*说明：数据来源为*.xls文件，文件中的第一行为变量名，第一列为样本名，其余的为数据区域。在其它数据源中也是类似摆放的。</p>
	
	<p>* Note: The data source for processing is *.xls file, the first  
	row in the file is variable name, the first column is sample name, and the rest is data area. In other data sources, the data table is put in the similar way.</p>
	</form>
  <input type="button" value="后退(Back)" onClick="javascript:history.back(-1);">
  </div>
  </body>
</html>
