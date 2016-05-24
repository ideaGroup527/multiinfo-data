<%@ page language="java" pageEncoding="gb2312"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>

		<title>显示计算结果</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="css.css" rel="stylesheet" type="text/css" media="all" />
		<link rel="stylesheet" href="iefixes.css" type="text/css" />
		<style type="text/css">
<!--
.STYLE1 {
	color: #FF0000
}
-->
</style>

		<script language="javascript">
function saveit()
{
	var temp = document.getElementById('pic').src;
	I1.document.location=temp;
	savepic();
}
function savepic()
{
	if(I1.document.readyState=="complete"){
		I1.document.execCommand("saveas");
	}
	else{
		window.setTimeout("savepic()",10);
	}
}
function submidResult(){
	document.getElementById("MyCutnum").value = resultform.cutnum.options[resultform.cutnum.selectedIndex].value;
	document.resultform.submit();
}
</script>
	</head>

	<body>


		<div id="container">

			<div id="header">

				<h1>
					<a href="#" title="Return To Homepage"><img id="logo"
							style="width: 760px; height: 112px;"
							alt="Division1.0/k&w"
							src="images/header.png" /> </a>
				</h1>

			</div>



			<!--Begin main navigation menu.-->

			<div id="menu">

				<!--Begin nothing goes here. Leave empty.-->
				<div id="menu-left"></div>
				<div id="menu-right"></div>
				<!--End nothing goes here.-->

				<ul>
					<li id="first">
						<img align='top' src="images/star-green.png" width="15"
							height="15"></img>
						<span align='middle' class="menu-key">HOME</span>
					</li>
					<li>
						<img align='top' src="images/star-green.png" width="15"
							height="15">
						<span class="menu-key">CONNECT</span>
					</li>
					<li>
						<img align='top' src="images/star-green.png" width="15"
							height="15">
						<span class="menu-key">CHOOSETABLE</span>
					</li>
					<li>
						<img align='top' src="images/star-green.png" width="15"
							height="15">
						<span class="menu-key">CHOOSEDATA</span>
					</li>
					<li>
						<img align='top' src="images/star-green.png" width="15"
							height="15">
						<span class="menu-key">DIVISION</span>
					</li>
					<li>
						<img align='top' src="images/star-green.png" width="15"
							height="15">
						<span class="menu-key">RESULT</span>
					</li>
				</ul>
			</div>

			<!--End main navigation menu.-->

			<div id="content">

				<!--Place your main content within the following division-->



				<h2>
					Welcome to Division 1.0
				</h2>



				<center>
					<h2>
						最优分割计算结果{Devision result}
					</h2>
					<h3>
						所选的工作表是{your table}：
						<span class="STYLE1">${title }</span>
					</h3>

				</center>

				<center>
					<img src="${graphURL }" border="1" name="图片" alt="图片" id="pic">
					<br>
					<br>
					<br>
					<input type="button" class="savepic" value=""
						onmouseover="this.style.backgroundPosition='left -42px'"
						onmouseout="this.style.backgroundPosition='left top'"
						onclick="saveit();" />
					<iframe name="I1" style="display: none"></iframe>
					<br>
					<br>
					<br>

				</center>
				<form action="downloadMidResTable" method="post" name="downloadform1">
					<input type="hidden" name="downloadPath" value="${excMidResTablePath }">
				</form>
				<form action="downloadMidResTable" method="post" name="downloadform2">
					<input type="hidden" name="downloadPath" value="${excDingTablePath }">
				</form>
				
				<form method="post" action="finalChoose" name="resultform">
					<input type="hidden" name="MyCutnum" id="MyCutnum" value="">
					<input type="hidden" name="tran" value="${tran }">
					<center>
						请选择最终分割段数{please choose final division number}:
						<c:set var="num" value="0"></c:set>
						<select name="cutnum">
							<c:forEach var="k" items="${cutnumS }">
								<option value="${cutnumS[num] }">
									&nbsp;&nbsp;${cutnumS[num] }&nbsp;&nbsp;
								</option>
								<c:set var="num" value="${num+1 }"></c:set>
							</c:forEach>
						</select>

						<div class="center">
							<center>
								<input type="button" class="next" value=""
									onmouseover="this.style.backgroundPosition='left -36px'"
									onmouseout="this.style.backgroundPosition='left top'"
									onclick="submidResult()" />
								&nbsp;
								<input type="button" class="back" value=""
									onmouseover="this.style.backgroundPosition='left -36px'"
									onmouseout="this.style.backgroundPosition='left top'"
									onClick="javascript:history.back(-1);" />
							</center>
						</div>
					</center>
				</form>
				<br>
				<br>

					<table class="stock" id="tableExc1" border="1" align="center"
						style="width: 600px; height: 303px;">

						<c:set var="flagi" value="0"></c:set>
						<c:set var="flagj" value="0"></c:set>
						<c:forEach var="i" items="${result }">
							<tr class="even">
								<td colspan="4">
									<center>
										最 优 ${flagi+2 } 段 分 割 结 果{Result}
									</center>
								</td>
							</tr>
							<tr class="even">
								<td>
									段号
									<br>
									{segment number}
								</td>
								<td>
									起点号
									<br>
									{from}
								</td>
								<td>
									终点号
									<br>
									{to}
								</td>
								<td>
									组内离差平方和
									<br>
									{variance of the segment}
								</td>
							</tr>
							<c:forEach var="j" items="${result[flagi] }">
								<tr <c:if test="${flagj%2==0 }">class="odd"</c:if>
									<c:if test="${flagj%2==1 }">class="even"</c:if>>
									<c:forEach var="k" items="${result[flagi][flagj] }">
										<td>
											${k }
										</td>
									</c:forEach>
									<c:set var="flagj" value="${flagj+1 }"></c:set>
								</tr>
							</c:forEach>
							<c:set var="flagj" value="0"></c:set>
							<tr class="odd">
								<td colspan="4">
									<center>
										离差平方总和{segment}：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${presult[flagi]
										}
									</center>
								</td>
							</tr>
							<c:set var="flagi" value="${flagi+1 }"></c:set>
						</c:forEach>

					</table>
					<br>
					<center>
						<input type="button" class="save" value=""
							onmouseover="this.style.backgroundPosition='left -42px'"
							onmouseout="this.style.backgroundPosition='left top'"
							onClick="document.downloadform1.submit();" />
					</center>

				<br>

				<br>
				<br>
				<br>
				<center>
					<h2>
						样品各次分割点位图（一）
						<br>
						{①Segmentation contrast figure}
					</h2>
				</center>
				<table class="stock" id="tableExc2" width="600" border="1"
					height="74" align="center">
					<thead>
						<tr>
							<th width="97">
								分割段数{segment}\标识列{sign}
							</th>
							<c:forEach var="i" items="${xScale }">
								<th width="287">
									${i }
								</th>
							</c:forEach>
						</tr>
					</thead>
					<c:set var="flag" value="0"></c:set>
					<c:forEach var="i" items="${lineresult }">
						<tr <c:if test="${flag%2==0 }">class="odd"</c:if>
							<c:if test="${flag%2==1 }">class="even"</c:if>>
							<td>
								分割${flag+2 }段
							</td>
							<c:forEach var="j" items="${lineresult[flag] }">
								<td bgcolor="${j }">
									&nbsp;

								</td>
							</c:forEach>
						</tr>
						<c:set var="flag" value="${flag+1}"></c:set>
					</c:forEach>

				</table>
				<br>
				<center>
					<br>
					<br>
				</center>
				<center>
					<h2>
						样品各次分割点位图（二）
						<br>
						{②Segmentation contrast figure}
					</h2>
				</center>
				<table class="stock" id="tableExc3" width="600" border="1"
					height="74" align="center">
					<thead>
						<tr>
							<th width="97">
								标识列{sign}/分割段数{segment}
							</th>
							<c:forEach var="i" items="${CutnumDing }">
								<th width="287">
									${i }
								</th>
							</c:forEach>
						</tr>
						<c:set var="flag" value="0"></c:set>
						<c:forEach var="i" items="${Dingresult }">
							<tr>
								<td>
									<c:out value="${xScale[flag]}"></c:out>
								</td>
								<c:forEach var="j" items="${Dingresult[flag] }">
									<td>
										${j }
									</td>
								</c:forEach>
							</tr>
							<c:set var="flag" value="${flag+1}"></c:set>
						</c:forEach>
					</thead>
				</table>
				<br>
				<center>
					<br>
					<input type="button" class="save" value=""
						onmouseover="this.style.backgroundPosition='left -42px'"
						onmouseout="this.style.backgroundPosition='left top'"
						onClick="document.downloadform2.submit();" />
					<br>
					<br>
					<br>
				</center>

				<!--<form method="post" action="finalChoose" name="resultform2">
					<input type="hidden" name="MyCutnum" id="MyCutnum" value="">
					<input type="hidden" name="tran" value="${tran }">
					<center>
						请选择最终分割段数{please choose final division number}:
						<c:set var="num" value="0"></c:set>
						<select name="cutnum">
							<c:forEach var="k" items="${cutnumS }">
								<option value="${cutnumS[num] }">
									&nbsp;&nbsp;${cutnumS[num] }&nbsp;&nbsp;
								</option>
								<c:set var="num" value="${num+1 }"></c:set>
							</c:forEach>
						</select>

						<div class="center">
							<center>
								<input type="button" class="next" value=""
									onmouseover="this.style.backgroundPosition='left -36px'"
									onmouseout="this.style.backgroundPosition='left top'"
									onclick="submidResult()" />
								&nbsp;
								<input type="button" class="back" value=""
									onmouseover="this.style.backgroundPosition='left -36px'"
									onmouseout="this.style.backgroundPosition='left top'"
									onClick="javascript:history.back(-1);" />
							</center>
						</div>
					</center>
				</form>!-->

				<br>
				<br>
				<hr>







			</div>
	</body>
</html>
