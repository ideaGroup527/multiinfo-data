<%@ page language="java" import="java.util.*" pageEncoding="GB2312"%>

<html>
  <head>
    <title>��ͼ����</title>
    <style type="text/css">
<!--
.STYLE2 {
	color: #3300ff;
	font-size: 18px;
}
-->
    </style>
</head>

		<script type="text/javascript" src="js/jquery.js"></script>
		<script type="text/javascript" src="js/jquery.colorPicker.js"></script>
<body bgcolor="#DDDDDD">
<div align="center">
  <p class="STYLE2">��ѡ���ͼ����:</p>
  <p class="STYLE2">Please select the parameters for drawing:</p>
  <hr>
<form method="GET" name="myform" action="servlet/Draw">
  <table border="1" cellspacing="0">
  <tr>
    <td>ͼƬ������˵��������(Language of the text in the image):</td>
    <td><select name="language">
      <option value="0">����(Chinese)</option>
      <option value="1">Ӣ��(English)</option>
      <option value="2">����+Ӣ��(Chinese+English)</option>
    </select></td>
  </tr>
  <tr>
    <td>��ϵͼ������ɫ(The color of title):</td>
    <td><input type="text" name="titlecolor" size="9"
	style="background: #0000FF" value="#0000FF" onClick="$(this).colorPicker()" readonly /></td>
  </tr>
  <tr>
    <td>�����ֺ�(The word size of the title)��</td>
    <td><select size="1" name="size">
      <option value="10">10</option>
      <option value="11">11</option>
      <option value="12">12</option>
      <option value="13">13</option>
      <option value="14">14</option>
      <option value="15">15</option>
      <option selected value="16">16</option>
      <option value="17">17</option>
      <option value="18">18</option>
      <option value="19">19</option>
      <option value="20">20</option>
      <option value="21">21</option>
      <option value="22">22</option>
      <option value="23">23</option>
      <option value="24">24</option>
      <option value="25">25</option>
      <option value="26">26</option>
      <option value="27">27</option>
      <option value="28">28</option>
      <option value="29">29</option>
      <option value="30">30</option>
    </select></td>
  </tr>
  <tr>
    <td>��ϵͼ������ɫ(The color of connection):</td>
    <td><input type="text" name="linescolor" size="9"
	style="background: #FF0000" value="#FF0000" onClick="$(this).colorPicker()" readonly /></td>
  </tr>
  <tr>
    <td>��ϵͼ�̶���ɫ(The color of calibration):</td>
    <td><input type="text" name="gradcolor" size="9" 
	style="background: #000000" value="#000000" onClick="$(this).colorPicker()" readonly /></td>
  </tr>
  <tr>
    <td>��ϵͼ����/��������ɫ(The color of Sample/Variables):</td>
    <td><input type="text" name="textcolor" size="9"
	style="background: #006600" value="#006600" onClick="$(this).colorPicker()" readonly /></td>
  </tr>
</table>
  <p><input type="submit" value="��ͼ(Begin to Draw)">
				<input type="reset" value="����(Reset)" onClick="load()">
				<br>
				<input type="button" value="����(Back)" onClick="javascript:history.back(-1);">
</form>
</div>
</body>
</html>