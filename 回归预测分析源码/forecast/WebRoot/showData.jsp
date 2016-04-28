<%@ page language="java" import="java.util.*" pageEncoding="GB2312"
	contentType="text/html;charset=gb2312" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
	<head>
		<title>数据显示 Data display</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
		<script src="js/jquery.js" type="text/javascript"></script>
		<script type="text/javascript">
			//行全选
			function _rowAllCheck(){
				$("input[name='row']").each(function(){this.checked=true;}); 
			}
			//行全不选
			function _rowAllNotCheck(){
				$("input[name='row']").each(function(){this.checked=false;}); 
			}
			//行反选
			function _rowCheckInverse(){
				$("input[name='row']").each(function(){
					if(this.checked==true){
						this.checked=false;
					}else{
						this.checked=true;
					}
				
				}); 
			}
			
			//列全选
			function _colAllCheck(){
				$("input[name='changereason']").each(function(){this.checked=true;}); 
			}
			//列全不选
			function _colAllNotCheck(){
				$("input[name='changereason']").each(function(){this.checked=false;}); 
			}
			//列反选
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
				请选择需要的数据
			<p><font size="4">Please select the data needed.</font></p>
			</h2>
			<br>
			<h3>
				所选的工作表是(Your selected table is):				<span class="STYLE1">${picName }</span>
			</h3>
		</center>
		<br>
		<br>
		<form method="post" action="chooseData">
			<td width="105">
						<input type="text" name="objStr" value="${objStr }">
				  </td>
			<h2 align="center">
				请选择因变量 Please select dependent:
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
				请选择变量(包括自变量和上面已选因变量)及参与预测的数据 Please select variables( including independents and dependent chosen above) and data for prediction:
			</h2>
			
			<p align="left" style="margin-top: 0pt; margin-bottom: 0pt;"> 
				<font color="#ff0000">请记住:</font>所选变量顺序, 对应着自变量X<sub>1</sub>,X<sub>2</sub>,...X<sub>m</sub>,在本页未选的不算在内, 因变量虽然必选, 
				但不排在自变量顺序内, 
				列标识(样本号)也不排在自变量顺序内。必须有一栏作为样本或记录标识，必选。准备数据时, 第一列放样本或记录标识.</p>
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
						<input type="button" id="rowAllCheck" value="行全选 All Rows" onclick="_rowAllCheck()">
					</td>
					<td>
						<input type="button" id="rowAllNotCheck" value="行全不选 No row selected" onclick="_rowAllNotCheck()">
					</td>
					<td>
						<input type="button" id="rowCheckInverse" value="行反选 Unselect Rows" onclick="_rowCheckInverse()">
					</td>
				</tr>
				
				<tr>
					<td>
						<input type="button" id="colAllCheck" value="列全选All columns" onclick="_colAllCheck()">
					</td>
					<td>
						<input type="button" id="colAllNotCheck" value="列全不选No column selected" onclick="_colAllNotCheck()">
					</td>
					<td>
						<input type="button" id="colCheckInverse" value="列反选Unselect Columns" onclick="_colCheckInverse()">
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
				<input type="submit" value="下一步(Next)">
			</center>
		</form>
	</body>
</html>