<%@ page language="java" pageEncoding="GBK"
	contentType="text/html;charset=gbk" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
	<head>

		<title>������ʾ</title>
		<script type="text/javascript" src="js/jquery.js"></script>
		<script type="text/javascript" src="js/jquery.colorPicker.js"></script>
		<link href="css/css.css" rel="stylesheet" type="text/css" media="all" />
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
				����ͼ����ѡ��ڶ���

			</h2>
			<br>
			<h3>
				��ѡ�Ĺ������ǣ�
				<span class="STYLE1">${picName }</span>
			</h3>
		</center>
		<br>
		<br>
		<form method="post" action="Ding" target="_blank">
			<input type="hidden" name="tran" value="${tran }">
			<center>
				<br>
				<input type="button" value="����" onClick="javascript:history.back(-1);">
				<input type="submit" value="��ͼ">
			</center>
			<h2>
				<hr>
			</h2>

			<table width="54%" border="1" align="center" id="table1">
				<tr>
					<td colspan="4">
						<p align="center">
							����ͼ
						</p>
					</td>
				</tr>
				<tr>
					<td width="63%" colspan="3">
						<p align="center">
							ѡ�����滯��ʽ
					</td>
					<td>
						<p align="left">
							<input type="radio" value="ZXDB" name="dingType" checked>
							����
							<input type="radio" value="HXDB" name="dingType">
							����
							<input type="radio" value="QBDB" name="dingType">
							ȫ��
					</td>
				</tr>
				<tr>
					<td width="63%" colspan="3">
						��ʶ˵������λ��
					</td>
					<td width="34%">
						<div align="left">
							<input type="radio" value="false" name="left_right" checked>
							ͼ��
							<input type="radio" value="true" name="left_right">
							ͼ��
						</div>
					</td>
				</tr>
				<tr>
					<td width="63%" colspan="3">
						����˵������λ��
					</td>
					<td width="34%">
						<div align="left">
							<input type="radio" value="false" name="up_down" checked>
							ͼ��
							<input type="radio" value="true" name="up_down">
							ͼ��
						</div>
					</td>
				</tr>
				<tr>
					<td width="63%" colspan="3">
						�����������0~1��
					</td>
					<td width="34%">
						<input type="text" name="maxLimit" size="20">
					</td>
				</tr>
				<tr>
					<td colspan="3">
						��С��������0~1��
					</td>
					<td>
						<input type="text" name="minLimit" size="20">
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
					<td colspan="3">
						������ɫ
						<input type="text" name="lineColor" size="9"
							style="background: #3366FF" value="#3366FF"
							onclick="$(this).colorPicker()" readonly />
					</td>
					<td>
						Բ����ɫ
						<input type="text" name="roundColor" size="9"
							style="background: #FF0000" value="#FF0000"
							onclick="$(this).colorPicker()" readonly />
					</td>
				</tr>
				<tr>
					<td width="14%">
						<font color="#FF0000">����</font>
					</td>
					<td width="25%">
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
					<td width="23%">
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
					<td width="14%">
						<font color="#FF0000">��˵��</font>
					</td>
					<td width="25%">
						����
						<select size="1" name="rowFont">
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
					<td width="23%">
						�ֺ�
						<select name="rowSize">
							<c:forEach var="cs" begin="10" end="20">
								<c:if test="${cs==15}">
									<option value="${cs }" selected>${cs }</option>
								</c:if>
								<c:if test="${cs!=15}">
									<option value="${cs }">${cs }</option>
								</c:if>
							</c:forEach>
						</select>
					</td>
					<td>
						��ɫ
						<input type="text" name="rowColor" size="9"
							style="background: #3366FF" value="#3366FF"
							onclick="$(this).colorPicker()" readonly />
					</td>
				</tr>
				<tr>
					<td width="14%">
						<font color="#FF0000">��˵��</font>
					</td>
					<td width="25%">
						����
						<select size="1" name="colFont">
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
					<td width="23%">
						�ֺ�
						<select name="colSize">
							<c:forEach var="cs" begin="10" end="20">
								<c:if test="${cs==15}">
									<option value="${cs }" selected>${cs }</option>
								</c:if>
								<c:if test="${cs!=15}">
									<option value="${cs }">${cs }</option>
								</c:if>
							</c:forEach>
						</select>
					</td>
					<td>
						��ɫ
						<input type="text" name="colColor" size="9"
							style="background: #3366FF" value="#3366FF"
							onclick="$(this).colorPicker()" readonly />
					</td>
				</tr>
			</table>
			<br>
			<h2>
				<hr>
			</h2>
			<table width="228" border="1" align="center">
				<tr>

					<td width="127">
						X��
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</td>
					<c:forEach var="i" items="${title}">
						<td width="85">
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