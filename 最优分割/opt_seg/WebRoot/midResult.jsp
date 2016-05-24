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

		<title>��ʾ������</title>

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
						���ŷָ������{Devision result}
					</h2>
					<h3>
						��ѡ�Ĺ�������{your table}��
						<span class="STYLE1">${title }</span>
					</h3>

				</center>

				<center>
					<img src="${graphURL }" border="1" name="ͼƬ" alt="ͼƬ" id="pic">
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
						��ѡ�����շָ����{please choose final division number}:
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
										�� �� ${flagi+2 } �� �� �� �� ��{Result}
									</center>
								</td>
							</tr>
							<tr class="even">
								<td>
									�κ�
									<br>
									{segment number}
								</td>
								<td>
									����
									<br>
									{from}
								</td>
								<td>
									�յ��
									<br>
									{to}
								</td>
								<td>
									�������ƽ����
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
										���ƽ���ܺ�{segment}��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${presult[flagi]
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
						��Ʒ���ηָ��λͼ��һ��
						<br>
						{��Segmentation contrast figure}
					</h2>
				</center>
				<table class="stock" id="tableExc2" width="600" border="1"
					height="74" align="center">
					<thead>
						<tr>
							<th width="97">
								�ָ����{segment}\��ʶ��{sign}
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
								�ָ�${flag+2 }��
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
						��Ʒ���ηָ��λͼ������
						<br>
						{��Segmentation contrast figure}
					</h2>
				</center>
				<table class="stock" id="tableExc3" width="600" border="1"
					height="74" align="center">
					<thead>
						<tr>
							<th width="97">
								��ʶ��{sign}/�ָ����{segment}
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
						��ѡ�����շָ����{please choose final division number}:
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
