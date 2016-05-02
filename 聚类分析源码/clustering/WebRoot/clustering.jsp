<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>

<html>
  <head>
    <title>数据处理</title>
    <style type="text/css">
<!--
.STYLE1 {
	color: #3300ff;
	font-size: 18px;
}
.STYLE2 {color: #FF0000}
-->
    </style>
</head>
<script language="JavaScript">
function change(){
	if(document.myform.key1.value==0){
		document.myform.key2.value=1;
	}
	else{
		document.myform.key2.value=3;
	}
}
</script>
  <body bgcolor="#DDDDDD">
    <div align="center">
    <form method="POST" name="myform" action="servlet/Clustering">
	  <p class="STYLE1">请选择聚类的方式</p>
	  <p class="STYLE1">Please select the cluster way:</p>
	  <hr>
	  <table border="1" cellspacing="0">
        <tr>
          <td>数据均运化方法Method of data haomogenization</td>
          <td><select size="1" name="type">
            <option value="1" selected>标准差标准化(standard deviation standardization)</option>
            <option value="2">极差标准化(range standardization)</option>
            <option value="3">极差正规化(range normalization)</option>
          </select></td>
        </tr>
        <tr>
          <td>聚类类型Type of clustering</td>
          <td><select name="key1" onChange="change()">
            <option value="0" selected>Q型(Q type)</option>
            <option value="3">R型(R type)</option>
          </select></td>
        </tr>
        <tr>
          <td>聚类统计量Statistics for clustering</td>
          <td><label>
            <select name="key2">
			  <option value="1" selected>距离系数(distance coefficient)</option>
              <option value="2">夹角余弦(angle cosine)</option>
              <option value="3">相关系数(correlation coefficient)</option>
            </select>
          </label></td>
        </tr>
      </table>
	  <p><input type="submit" value="提交(Submit)" name="B1">
	  <input type="reset" value="重置(Reset)" name="B2">
	  </p>
	  </form>
	  <p class="STYLE2">*注：聚类分析Q型是对数据样本进行聚类，R型是对数据的变量进行聚类</p>
	  <p class="STYLE2">* Note: Q-mode cluster analysis is the clustering of data samples, R-mode is the clustering of data variables</p>
  <input type="button" value="后退(Back)" onClick="javascript:history.back(-1);">
    </div>
  </body>
</html>
