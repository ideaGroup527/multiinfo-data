<%@ page language="java" pageEncoding="GBK"
	contentType="text/html;charset=gbk" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
	<head>

		<title>数据显示</title>
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
				丁氏图数据选择第二步

			</h2>
			<br>
			<h3>
				所选的工作表是：
				<span class="STYLE1">${picName }</span>
			</h3>
		</center>
		<br>
		<br>
		<form method="post" action="Ding" target="_blank">
			<input type="hidden" name="tran" value="${tran }">
			<center>
				<br>
				<input type="button" value="后退" onClick="javascript:history.back(-1);">
				<input type="submit" value="绘图">
			</center>
			<h2>
				<hr>
			</h2>

			<table width="54%" border="1" align="center" id="table1">
				<tr>
					<td colspan="4">
						<p align="center">
							丁氏图
						</p>
					</td>
				</tr>
				<tr>
					<td width="63%" colspan="3">
						<p align="center">
							选择正规化方式
					</td>
					<td>
						<p align="left">
							<input type="radio" value="ZXDB" name="dingType" checked>
							纵向
							<input type="radio" value="HXDB" name="dingType">
							横向
							<input type="radio" value="QBDB" name="dingType">
							全表
					</td>
				</tr>
				<tr>
					<td width="63%" colspan="3">
						标识说明文字位置
					</td>
					<td width="34%">
						<div align="left">
							<input type="radio" value="false" name="left_right" checked>
							图左
							<input type="radio" value="true" name="left_right">
							图右
						</div>
					</td>
				</tr>
				<tr>
					<td width="63%" colspan="3">
						数据说明文字位置
					</td>
					<td width="34%">
						<div align="left">
							<input type="radio" value="false" name="up_down" checked>
							图上
							<input type="radio" value="true" name="up_down">
							图下
						</div>
					</td>
				</tr>
				<tr>
					<td width="63%" colspan="3">
						最大正规数（0~1）
					</td>
					<td width="34%">
						<input type="text" name="maxLimit" size="20">
					</td>
				</tr>
				<tr>
					<td colspan="3">
						最小正规数（0~1）
					</td>
					<td>
						<input type="text" name="minLimit" size="20">
					</td>
				</tr>
				<tr>
					<td colspan="3">
						背景上部颜色
						<input type="text" name="upBackColor" size="9"
							style="background: #99CCFF" value="#99CCFF"
							onclick="$(this).colorPicker()" readonly />
					</td>
					<td>
						背景下部颜色
						<input type="text" name="downBackColor" size="9"
							style="background: #99CCFF" value="#99CCFF"
							onclick="$(this).colorPicker()" readonly />
					</td>
				</tr>
				<tr>
					<td colspan="3">
						格线颜色
						<input type="text" name="lineColor" size="9"
							style="background: #3366FF" value="#3366FF"
							onclick="$(this).colorPicker()" readonly />
					</td>
					<td>
						圆形颜色
						<input type="text" name="roundColor" size="9"
							style="background: #FF0000" value="#FF0000"
							onclick="$(this).colorPicker()" readonly />
					</td>
				</tr>
				<tr>
					<td width="14%">
						<font color="#FF0000">标题</font>
					</td>
					<td width="25%">
						字体
						<select size="1" name="titleFont">
							<option selected value="宋体">
								宋体
							</option>
							<option value="黑体">
								黑体
							</option>
							<option value="方正舒体">
								方正舒体
							</option>
							<option value="方正姚体">
								方正姚体
							</option>
							<option value="仿宋">
								仿宋
							</option>
							<option value="华文彩云">
								华文彩云
							</option>
							<option value="华文仿宋">
								华文仿宋
							</option>
							<option value="华文细黑">
								华文细黑
							</option>
							<option value="华文新魏">
								华文新魏
							</option>
							<option value="华文行楷">
								华文行楷
							</option>
							<option value="华文中宋">
								华文中宋
							</option>
							<option value="楷体">
								楷体
							</option>
							<option value="隶书">
								隶书
							</option>
							<option value="幼圆">
								幼圆
							</option>
							<option value="新宋体">
								新宋体
							</option>
						</select>
						&nbsp;
					</td>
					<td width="23%">
						字号
						<input type="text" name="titleSize" size="9" value="25">
					</td>
					<td>
						颜色
						<input type="text" name="titleColor" size="9"
							style="background: #3366FF" value="#3366FF"
							onclick="$(this).colorPicker()" readonly />
					</td>
				</tr>
				<tr>
					<td width="14%">
						<font color="#FF0000">行说明</font>
					</td>
					<td width="25%">
						字体
						<select size="1" name="rowFont">
							<option selected value="宋体">
								宋体
							</option>
							<option value="黑体">
								黑体
							</option>
							<option value="方正舒体">
								方正舒体
							</option>
							<option value="方正姚体">
								方正姚体
							</option>
							<option value="仿宋">
								仿宋
							</option>
							<option value="华文彩云">
								华文彩云
							</option>
							<option value="华文仿宋">
								华文仿宋
							</option>
							<option value="华文细黑">
								华文细黑
							</option>
							<option value="华文新魏">
								华文新魏
							</option>
							<option value="华文行楷">
								华文行楷
							</option>
							<option value="华文中宋">
								华文中宋
							</option>
							<option value="楷体">
								楷体
							</option>
							<option value="隶书">
								隶书
							</option>
							<option value="幼圆">
								幼圆
							</option>
							<option value="新宋体">
								新宋体
							</option>
						</select>
					</td>
					<td width="23%">
						字号
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
						颜色
						<input type="text" name="rowColor" size="9"
							style="background: #3366FF" value="#3366FF"
							onclick="$(this).colorPicker()" readonly />
					</td>
				</tr>
				<tr>
					<td width="14%">
						<font color="#FF0000">列说明</font>
					</td>
					<td width="25%">
						字体
						<select size="1" name="colFont">
							<option selected value="宋体">
								宋体
							</option>
							<option value="黑体">
								黑体
							</option>
							<option value="方正舒体">
								方正舒体
							</option>
							<option value="方正姚体">
								方正姚体
							</option>
							<option value="仿宋">
								仿宋
							</option>
							<option value="华文彩云">
								华文彩云
							</option>
							<option value="华文仿宋">
								华文仿宋
							</option>
							<option value="华文细黑">
								华文细黑
							</option>
							<option value="华文新魏">
								华文新魏
							</option>
							<option value="华文行楷">
								华文行楷
							</option>
							<option value="华文中宋">
								华文中宋
							</option>
							<option value="楷体">
								楷体
							</option>
							<option value="隶书">
								隶书
							</option>
							<option value="幼圆">
								幼圆
							</option>
							<option value="新宋体">
								新宋体
							</option>
						</select>
					</td>
					<td width="23%">
						字号
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
						颜色
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
						X轴
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
				<input type="submit" value="绘图">
			</center>
		</form>

		<center>
			<br>
			<br>
			<hr>
			<input type="button" value="后退"
				onClick="javascript:history.back(-1);">
		</center>
	</body>
</html>