<%@ page language="java" import="java.util.*" pageEncoding="GB2312"
	contentType="text/html;charset=gb2312" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
	<head>
		<title>������ʾ</title>
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
			</h2>
			<br>
			<h3>
				��ѡ�Ĺ������ǣ�
				<span class="STYLE1">${picName }</span>
			</h3>
		</center>
		<br>
		<br>
		<form method="post" action="chooseData">
			<td width="105">
						<input type="text" name="objStr" value="${objStr }">
				  </td>
			<h2 align="center">
				��ѡ���������
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
				��ѡ�����(�����Ա�����������ѡ�����)������Ԥ�������
			</h2>
			
			<p align="center" style="margin-top: 0pt; margin-bottom: 0pt;"> 
				(<font color="#ff0000">���ס</font>��ѡ����˳�򣬶�Ӧ���Ա���X<sub>1</sub>,X<sub>2</sub>,...X<sub>m</sub>,�ڱ�ҳδѡ�Ĳ�������  
				���������Ȼ��ѡ�����������Ա���˳���ڣ��б�ʶ(������)Ҳ�������Ա���˳���ڡ�������һ����Ϊ�������¼��ʶ����ѡ��׼������ʱ����һ�з��������¼��ʶ��)</p>
			

			<table border="1" align="center">
				<tr>
					<td>
						<input type="button" id="rowAllCheck" value="��ȫѡ" onclick="_rowAllCheck()">
					</td>
					<td>
						<input type="button" id="rowAllNotCheck" value="��ȫ��ѡ" onclick="_rowAllNotCheck()">
					</td>
					<td>
						<input type="button" id="rowCheckInverse" value="�з�ѡ" onclick="_rowCheckInverse()">
					</td>
				</tr>
				
				<tr>
					<td>
						<input type="button" id="colAllCheck" value="��ȫѡ" onclick="_colAllCheck()">
					</td>
					<td>
						<input type="button" id="colAllNotCheck" value="��ȫ��ѡ" onclick="_colAllNotCheck()">
					</td>
					<td>
						<input type="button" id="colCheckInverse" value="�з�ѡ" onclick="_colCheckInverse()">
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
				<input type="submit" value="��һ��">
			</center>
		</form>
	</body>
</html>