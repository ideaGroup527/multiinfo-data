<%@ page language="java" pageEncoding="GBK"
	contentType="text/html;charset=gbk" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>

		<title>数据显示</title>
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
				柱状图数据选择第二步（整体还是单列 ）
			</h2>
			<br>
			<h3>
				所选的工作表是：
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
				<input type="button" value="后退" onClick="javascript:history.back(-1);">
				<input type="submit" value="绘图">
			</center>
			<br>
			<h2>
				<hr>
			</h2>
			<table border="1" align="center" width="636">
				<tr>
					<td width="53%" colspan="3">
						<p align="center">
							绘制类型
					</td>
					<td>
						<p align="center">
							<input type="radio" value="1" name="idea" checked>
							竖值平面
							<input type="radio" value="2" name="idea">
							竖值3D
							<input type="radio" value="3" name="idea">
							横向平面
							<input type="radio" value="4" name="idea">
							横向3D
					</td>
				</tr>
				<tr>
					<td width="53%" colspan="3">
						<p align="center">
							正规化处理
					</td>
					<td>
						<p align="center">
							<input type="radio" value="true" name="isNormalization" checked>
							是
							<input type="radio" value="false" name="isNormalization">
							否
						<table width="263" border="1" align="center">
							<tr>
								<td width="107">
									最大正规数（0~1）
								</td>
								<td width="147">
									<input type="text" name="maxLimit" size="20">
								</td>
							</tr>
							<tr>
								<td>
									最小正规数（0~1）
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
							标签
					</td>
					<td>
						<p align="center">
							<input type="radio" value="true" name="lable" checked>
							是
							<input type="radio" value="false" name="lable">
							否
					</td>
				</tr>
				<tr>
					<td width="53%" colspan="3">
						<p align="center">
							图片宽度
					</td>
					<td>
						<p align="center">
							<input type="text" name="width" size="20" value="980">
							像素
					</td>
				</tr>
				<tr>
					<td width="53%" colspan="3">
						<p align="center">
							图片高度
					</td>
					<td>
						<p align="center">
							<input type="text" name="height" size="20" value="450">
							像素
					</td>
				</tr>
				<tr>
					<td width="53%" colspan="3">
						<p align="center">
							X轴名称
					</td>
					<td>
						<p align="center">
							<input type="text" name="xTitle" size="20" value="X轴">
					</td>
				</tr>
				<tr>
					<td width="53%" colspan="3">
						<p align="center">
							Y轴名称
					</td>
					<td>
						<p align="center">
							<input type="text" name="yTitle" size="20" value="Y轴">
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
					<td width="11%">
						<font color="#FF0000">标题</font>
					</td>
					<td width="19%">
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
					<td width="19%">
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
					<td width="11%">
						<font color="#FF0000">X轴刻度</font>
					</td>
					<td width="19%">
						字体
						<select size="1" name="xFont">
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
					<td width="19%">
						字号
						<input type="text" name="xSize" size="9" value="12">
					</td>
					<td>
						颜色
						<input type="text" name="xColor" size="9"
							style="background: #3366FF" value="#3366FF"
							onclick="$(this).colorPicker()" readonly />
					</td>
				</tr>
				<tr>
					<td width="11%">
						<font color="#FF0000">Y轴刻度</font>
					</td>
					<td width="19%">
						字体
						<select size="1" name="yFont">
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
					<td width="19%">
						字号
						<input type="text" name="ySize" size="9" value="12">
					</td>
					<td>
						颜色
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
						全选
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
						X轴
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