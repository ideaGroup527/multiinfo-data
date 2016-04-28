<%@ page language="java" import="java.util.*" pageEncoding="GB2312"
	contentType="text/html;charset=gb2312" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
	<head>
		<title>������ʾ Data display</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
		<script src="js/jquery.js" type="text/javascript"></script>
		<script type="text/javascript">
			//��ȫѡ
			function _rowAllCheck(){
				$("input[name='row']").each(function(){this.checked=true;}); 
			}
			//��ȫ��ѡ
			function _rowAllNotCheck(){
				$("input[name='row']").each(function(){this.checked=false;}); 
			}
			//�з�ѡ
			function _rowCheckInverse(){
				$("input[name='row']").each(function(){
					if(this.checked==true){
						this.checked=false;
					}else{
						this.checked=true;
					}
				
				}); 
			}
			
			//��ȫѡ
			function _colAllCheck(){
				$("input[name='changereason']").each(function(){this.checked=true;}); 
			}
			//��ȫ��ѡ
			function _colAllNotCheck(){
				$("input[name='changereason']").each(function(){this.checked=false;}); 
			}
			//�з�ѡ
			function _colCheckInverse(){
				$("input[name='changereason']").each(function(){
					if(this.checked==true){
						this.checked=false;
					}else{
						this.checked=true;
					}
				
				}); 
			}
		</script>
		<style type="text/css">
<!--
.STYLE1 {
	color: #FF0000
}
-->
</style>
	</head>

	<body>
		<br>
		<center>
			<h2>
				��ѡ����Ҫ������
			<p><font size="4">Please select the data needed.</font></p>
			</h2>
			<br>
			<h3>
				��ѡ�Ĺ�������(Your selected table is):				<span class="STYLE1">${picName }</span>
			</h3>
		</center>
		<br>
		<br>
		<form method="post" action="chooseData">
			<td width="105">
						<input type="text" name="objStr" value="${objStr }">
				  </td>
			<h2 align="center">
				��ѡ������� Please select dependent:
			</h2>
			<table border="1" align="center">
				<tr>
					<td>
					</td>
					<c:set var="tFlag" value="0"></c:set>
					<c:forEach var="i" items="${title}">
						<td>
							<input type="radio" name="changeresult" value="${ tFlag}">
							${i }
						</td>
					<c:set var="tFlag" value="${tFlag+1}"></c:set>
					</c:forEach>
				</tr>
			</table>
			<h2>
				<hr>
			</h2>
			<h2 align="center">
				��ѡ�����(�����Ա�����������ѡ�����)������Ԥ������� Please select variables( including independents and dependent chosen above) and data for prediction:
			</h2>
			
			<p align="left" style="margin-top: 0pt; margin-bottom: 0pt;"> 
				<font color="#ff0000">���ס:</font>��ѡ����˳��, ��Ӧ���Ա���X<sub>1</sub>,X<sub>2</sub>,...X<sub>m</sub>,�ڱ�ҳδѡ�Ĳ�������, �������Ȼ��ѡ, 
				���������Ա���˳����, 
				�б�ʶ(������)Ҳ�������Ա���˳���ڡ�������һ����Ϊ�������¼��ʶ����ѡ��׼������ʱ, ��һ�з��������¼��ʶ.</p>
			<p align="left" style="margin-top: 0pt; margin-bottom: 0pt">
			<font color="#ff0000">Please remember: </font>The order of selected variables is corresponding to independents 
			X<sub>1</sub>, X<sub>2</sub>, ...X<sub>m</sub>. The variables not selected in this page are not take in 
				account. The dependent must be selected, 
				but not put into the order of independents. The mark column(sample No) will not put into the 
				independent order too. There must be 1 column 
				selected as sample No or record mark. When 
				prepare data, the first column shoud be sample No or record 
				mark.</p>
			

			<table border="1" align="center">
				<tr>
					<td>
						<input type="button" id="rowAllCheck" value="��ȫѡ All Rows" onclick="_rowAllCheck()">
					</td>
					<td>
						<input type="button" id="rowAllNotCheck" value="��ȫ��ѡ No row selected" onclick="_rowAllNotCheck()">
					</td>
					<td>
						<input type="button" id="rowCheckInverse" value="�з�ѡ Unselect Rows" onclick="_rowCheckInverse()">
					</td>
				</tr>
				
				<tr>
					<td>
						<input type="button" id="colAllCheck" value="��ȫѡAll columns" onclick="_colAllCheck()">
					</td>
					<td>
						<input type="button" id="colAllNotCheck" value="��ȫ��ѡNo column selected" onclick="_colAllNotCheck()">
					</td>
					<td>
						<input type="button" id="colCheckInverse" value="�з�ѡUnselect Columns" onclick="_colCheckInverse()">
					</td>
				</tr>
			</table>
			<table border="1" align="center">
				<tr>
					<td>
					</td>
					<c:set var="tFlag" value="0"></c:set>
					<c:forEach var="i" items="${title}">
						<td>
							<input type="checkbox" name="changereason" value="${tFlag }">
						</td>
						<c:set var="tFlag" value="${tFlag+1}"></c:set>
					</c:forEach>
				</tr>
				<tr>

					<td>

					</td>
					<c:forEach var="i" items="${title}">
						<td>
							${i }
						</td>
					</c:forEach>
				</tr>

				<c:set var="flag" value="0"></c:set>
				<c:forEach var="j" items="${showList}">
					<tr>
						<td>
							<input type="checkbox" name="row" value="${flag }">
							<br>

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
				<input type="submit" value="��һ��(Next)">
			</center>
		</form>
	</body>
</html>