<%@ page language="java" pageEncoding="GBK"
	contentType="text/html;charset=gbk" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>

		<title>������ʾ</title>
		<link href="css/css.css" rel="stylesheet" type="text/css" media="all" />
		<script type="text/javascript" src="js/jquery.js"></script>
		<script type="text/javascript" src="js/jquery.colorPicker.js"></script>
		<style type="text/css">
<!--
.STYLE1 {
	color: #FF0000
}
-->
</style>
	</head>

	<body>
		<script>
		$(document).ready(function() {
			$("Selector").colorPicker({
		   		setBackground: true,
		   		setColor: true,
		   		setValue: true,
		   		setText: true
			}); 
		
		});
</script>
		<br>
		<center>
			<h2>
				��״ͼ����ѡ��ڶ��������廹�ǵ��� ��
			</h2>
			<br>
			<h3>
				��ѡ�Ĺ������ǣ�
				<span class="STYLE1">${picName }</span>
			</h3>
		</center>
		<br>
		<br>
		<form method="post" action="XYBar">
			<input type="hidden" name="title" value="${title }">
			<input type="hidden" name="xScale" value="${xScale }">
			<center>
				<br>
				<input type="button" value="����" onClick="javascript:history.back(-1);">
				<input type="submit" value="��ͼ">
			</center>
			<br>
			<h2>
				<hr>
			</h2>
			<table border="1" align="center" width="636">
				<tr>
					<td width="53%" colspan="3">
						<p align="center">
							��������
					</td>
					<td>
						<p align="center">
							<input type="radio" value="1" name="idea" checked>
							��ֵƽ��
							<input type="radio" value="2" name="idea">
							��ֵ3D
							<input type="radio" value="3" name="idea">
							����ƽ��
							<input type="radio" value="4" name="idea">
							����3D
					</td>
				</tr>
				<tr>
					<td width="53%" colspan="3">
						<p align="center">
							���滯����
					</td>
					<td>
						<p align="center">
							<input type="radio" value="true" name="isNormalization" checked>
							��
							<input type="radio" value="false" name="isNormalization">
							��
						<table width="263" border="1" align="center">
							<tr>
								<td width="107">
									�����������0~1��
								</td>
								<td width="147">
									<input type="text" name="maxLimit" size="20">
								</td>
							</tr>
							<tr>
								<td>
									��С��������0~1��
								</td>
								<td>
									<input type="text" name="minLimit" size="20">
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td width="53%" colspan="3">
						<p align="center">
							��ǩ
					</td>
					<td>
						<p align="center">
							<input type="radio" value="true" name="lable" checked>
							��
							<input type="radio" value="false" name="lable">
							��
					</td>
				</tr>
				<tr>
					<td width="53%" colspan="3">
						<p align="center">
							ͼƬ���
					</td>
					<td>
						<p align="center">
							<input type="text" name="width" size="20" value="980">
							����
					</td>
				</tr>
				<tr>
					<td width="53%" colspan="3">
						<p align="center">
							ͼƬ�߶�
					</td>
					<td>
						<p align="center">
							<input type="text" name="height" size="20" value="450">
							����
					</td>
				</tr>
				<tr>
					<td width="53%" colspan="3">
						<p align="center">
							X������
					</td>
					<td>
						<p align="center">
							<input type="text" name="xTitle" size="20" value="X��">
					</td>
				</tr>
				<tr>
					<td width="53%" colspan="3">
						<p align="center">
							Y������
					</td>
					<td>
						<p align="center">
							<input type="text" name="yTitle" size="20" value="Y��">
					</td>
				</tr>
				<tr>
					<td colspan="3">
						�����ϲ���ɫ
						<input type="text" name="upBackColor" size="9"
							style="background: #99CCFF" value="#99CCFF"
							onclick="$(this).colorPicker()" readonly />
					</td>
					<td>
						�����²���ɫ
						<input type="text" name="downBackColor" size="9"
							style="background: #99CCFF" value="#99CCFF"
							onclick="$(this).colorPicker()" readonly />
					</td>
				</tr>
				<tr>
					<td width="11%">
						<font color="#FF0000">����</font>
					</td>
					<td width="19%">
						����
						<select size="1" name="titleFont">
							<option selected value="����">
								����
							</option>
							<option value="����">
								����
							</option>
							<option value="��������">
								��������
							</option>
							<option value="����Ҧ��">
								����Ҧ��
							</option>
							<option value="����">
								����
							</option>
							<option value="���Ĳ���">
								���Ĳ���
							</option>
							<option value="���ķ���">
								���ķ���
							</option>
							<option value="����ϸ��">
								����ϸ��
							</option>
							<option value="������κ">
								������κ
							</option>
							<option value="�����п�">
								�����п�
							</option>
							<option value="��������">
								��������
							</option>
							<option value="����">
								����
							</option>
							<option value="����">
								����
							</option>
							<option value="��Բ">
								��Բ
							</option>
							<option value="������">
								������
							</option>
						</select>
						&nbsp;
					</td>
					<td width="19%">
						�ֺ�
						<input type="text" name="titleSize" size="9" value="25">
					</td>
					<td>
						��ɫ
						<input type="text" name="titleColor" size="9"
							style="background: #3366FF" value="#3366FF"
							onclick="$(this).colorPicker()" readonly />
					</td>
				</tr>
				<tr>
					<td width="11%">
						<font color="#FF0000">X��̶�</font>
					</td>
					<td width="19%">
						����
						<select size="1" name="xFont">
							<option selected value="����">
								����
							</option>
							<option value="����">
								����
							</option>
							<option value="��������">
								��������
							</option>
							<option value="����Ҧ��">
								����Ҧ��
							</option>
							<option value="����">
								����
							</option>
							<option value="���Ĳ���">
								���Ĳ���
							</option>
							<option value="���ķ���">
								���ķ���
							</option>
							<option value="����ϸ��">
								����ϸ��
							</option>
							<option value="������κ">
								������κ
							</option>
							<option value="�����п�">
								�����п�
							</option>
							<option value="��������">
								��������
							</option>
							<option value="����">
								����
							</option>
							<option value="����">
								����
							</option>
							<option value="��Բ">
								��Բ
							</option>
							<option value="������">
								������
							</option>
						</select>
					</td>
					<td width="19%">
						�ֺ�
						<input type="text" name="xSize" size="9" value="12">
					</td>
					<td>
						��ɫ
						<input type="text" name="xColor" size="9"
							style="background: #3366FF" value="#3366FF"
							onclick="$(this).colorPicker()" readonly />
					</td>
				</tr>
				<tr>
					<td width="11%">
						<font color="#FF0000">Y��̶�</font>
					</td>
					<td width="19%">
						����
						<select size="1" name="yFont">
							<option selected value="����">
								����
							</option>
							<option value="����">
								����
							</option>
							<option value="��������">
								��������
							</option>
							<option value="����Ҧ��">
								����Ҧ��
							</option>
							<option value="����">
								����
							</option>
							<option value="���Ĳ���">
								���Ĳ���
							</option>
							<option value="���ķ���">
								���ķ���
							</option>
							<option value="����ϸ��">
								����ϸ��
							</option>
							<option value="������κ">
								������κ
							</option>
							<option value="�����п�">
								�����п�
							</option>
							<option value="��������">
								��������
							</option>
							<option value="����">
								����
							</option>
							<option value="����">
								����
							</option>
							<option value="��Բ">
								��Բ
							</option>
							<option value="������">
								������
							</option>
						</select>
					</td>
					<td width="19%">
						�ֺ�
						<input type="text" name="ySize" size="9" value="12">
					</td>
					<td>
						��ɫ
						<input type="text" name="yColor" size="9"
							style="background: #3366FF" value="#3366FF"
							onclick="$(this).colorPicker()" readonly />
					</td>
				</tr>

			</table>
			<h2>
				<hr>
			</h2>
			<table width="228" border="1" align="center">
				<tr>

					<td width="127">
						<input type="hidden" name="tran" value="${tran }">
						ȫѡ
						<input type="radio" name="row" value="all" checked>
					</td>
					<c:set var="tFlag" value="0"></c:set>
					<c:forEach var="i" items="${title}">
						<td width="85">
							<input type="radio" name="row" value="${tFlag }">
						</td>
						<c:set var="tFlag" value="${tFlag+1}"></c:set>
					</c:forEach>
				</tr>
				<tr>

					<td>
						X��
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</td>
					<c:forEach var="i" items="${title}">
						<td>
							${i }
						</td>
					</c:forEach>
				</tr>
				<c:set var="flag" value="0"></c:set>
				<c:forEach var="j" items="${xScale}">
					<tr>
						<td>
							${j }
						</td>
						<c:forEach var="jj" items="${showList[flag]}">
							<td>
								${jj }
							</td>
						</c:forEach>
						<c:set var="flag" value="${flag+1}"></c:set>
					</tr>
				</c:forEach>
			</table>
			<center>
				<br>
				<input type="submit" value="��ͼ">
			</center>
		</form>
		<center>
			<br>
			<br>
			<hr>
			<input type="button" value="����"
				onClick="javascript:history.back(-1);">
		</center>
	</body>
</html>