<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>

<html>
  <head>
    <title>���ݴ���</title>
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
	  <p class="STYLE1">��ѡ�����ķ�ʽ</p>
	  <p class="STYLE1">Please select the cluster way:</p>
	  <hr>
	  <table border="1" cellspacing="0">
        <tr>
          <td>���ݾ��˻�����Method of data haomogenization</td>
          <td><select size="1" name="type">
            <option value="1" selected>��׼���׼��(standard deviation standardization)</option>
            <option value="2">�����׼��(range standardization)</option>
            <option value="3">�������滯(range normalization)</option>
          </select></td>
        </tr>
        <tr>
          <td>��������Type of clustering</td>
          <td><select name="key1" onChange="change()">
            <option value="0" selected>Q��(Q type)</option>
            <option value="3">R��(R type)</option>
          </select></td>
        </tr>
        <tr>
          <td>����ͳ����Statistics for clustering</td>
          <td><label>
            <select name="key2">
			  <option value="1" selected>����ϵ��(distance coefficient)</option>
              <option value="2">�н�����(angle cosine)</option>
              <option value="3">���ϵ��(correlation coefficient)</option>
            </select>
          </label></td>
        </tr>
      </table>
	  <p><input type="submit" value="�ύ(Submit)" name="B1">
	  <input type="reset" value="����(Reset)" name="B2">
	  </p>
	  </form>
	  <p class="STYLE2">*ע���������Q���Ƕ������������о��࣬R���Ƕ����ݵı������о���</p>
	  <p class="STYLE2">* Note: Q-mode cluster analysis is the clustering of data samples, R-mode is the clustering of data variables</p>
  <input type="button" value="����(Back)" onClick="javascript:history.back(-1);">
    </div>
  </body>
</html>
