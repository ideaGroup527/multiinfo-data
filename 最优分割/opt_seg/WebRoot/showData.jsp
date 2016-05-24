<%@ page language="java" import="java.util.*" pageEncoding="GBK"
	contentType="text/html;charset=gbk" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>

		<title>多元有序数据最优分割</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<script type="text/javaScript" src="js/checkBox.js"></script>
		<link href="css.css" rel="stylesheet" type="text/css" media="all" />

  		<link rel="stylesheet" href="iefixes.css" type="text/css" />
		<style type="text/css">
<!--
.STYLE1 {
	color: #FF0000
}
-->
</style>
	</head>

	<body>

<div id="container">

<div id="header">

<h1><a href="#" title="Return To Homepage"><img id="logo" style="width: 760px; height: 112px;" alt="Division1.0/k&w" src="images/header.png" /></a></h1>

</div>



<!--Begin main navigation menu.-->

<div id="menu">

<!--Begin nothing goes here. Leave empty.--><div id="menu-left"></div><div id="menu-right"></div><!--End nothing goes here.-->

<ul>
					<li id="first">
						<img align='top' src="images/star-green.png" width="15" height="15"></img><span align='middle' class="menu-key">HOME</span>
					</li>
					<li>
						<img align='top' src="images/star-green.png"  width="15" height="15"><span class="menu-key">CONNECT</span>
					</li>
					<li>
						<img align='top' src="images/star-green.png"  width="15" height="15"><span class="menu-key">CHOOSETABLE</span>
					</li>
					<li>
						<img align='top' src="images/star-green.png"  width="15" height="15"><span class="menu-key">CHOOSEDATA</span>
					</li>
					<li>
						<img align='top' src="images/star-grey.png"  width="15" height="15"><span class="access-key">DIVISION</span>
					</li>
					<li>
						<img align='top' src="images/star-grey.png"  width="15" height="15"><span class="access-key">RESULT</span>
					</li>
				</ul>
</div>

<!--End main navigation menu.-->

<div id="content">

<!--Place your main content within the following division-->

<div id="right-column">


<h2 class="bottom-border">Welcome to MulVerOrdCut 1.0</h2><div id="featured-wrapper">

<div id="featured-top"></div>

<div id="featured-content">
<h2 class="bottom-border">请选择需要的数据{Determine data}</h2>

			<h3> 
				所选的工作表是{Your table}： 
				<span class="STYLE1">${picName }</span>
			</h3>



<form method="post" action="chooseData">
			<input type="hidden" name="title" value="${title }">
			<input type="hidden" name="xScale" value="${xScale }">
			
			<center>
				<br>
				
				<div class="center">
								<center>
										<input type="submit" class="next" value=""
											onmouseover="this.style.backgroundPosition='left -36px'"
											onmouseout="this.style.backgroundPosition='left top'" />&nbsp;
										<input type="button" class="back" value=""
											onmouseover="this.style.backgroundPosition='left -36px'"
											onmouseout="this.style.backgroundPosition='left top'"
											onClick="javascript:history.back(-1);" />
								</center>
									</div>
				
				<br>
			</center>
			
			<table width="200" border="0" align="center">
				<tr>
					<td><input type="button" value="行全选"  onmouseover="this.style.borderColor='#75cd02'" onmouseout="this.style.borderColor='#dcdcdc'" class="ok pbtn1" onClick="checkAll('row')"/></td>
					<td><input type="button" value="行全不选" onmouseover="this.style.borderColor='#f76b00'" onmouseout="this.style.borderColor='#dcdcdc'" class="delete pbtn1" onClick="uncheckAll('row')"/></td>
					<td><input type="button" value="行反选" onmouseover="this.style.borderColor='#86c6f7'" onmouseout="this.style.borderColor='#dcdcdc'" class="circle pbtn1" onClick="switchAll('row')"/>
					</td>
				</tr>
				<tr>
				<td><input type="button" value="列全选" onmouseover="this.style.borderColor='#75cd02'" onmouseout="this.style.borderColor='#dcdcdc'" class="ok pbtn1" onClick="checkAll('col')"/></td>
					<td><input type="button" value="列全不选" onmouseover="this.style.borderColor='#f76b00'" onmouseout="this.style.borderColor='#dcdcdc'" class="delete pbtn1" onClick="uncheckAll('col')"/></td>
					<td><input type="button" value="列反选" onmouseover="this.style.borderColor='#86c6f7'" onmouseout="this.style.borderColor='#dcdcdc'" class="circle pbtn1" onClick="switchAll('col')"/></td>
				</tr>
			</table>
			<h2>
				<hr>
			</h2>
			<table class="stock" width="278" border="0" align="center">
				<tr class="even">
					<td width="93">
						<input type="hidden" name="tran" value="${tran }">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				  </td>
					<c:set var="tFlag" value="0"></c:set>
					<c:forEach var="i" items="${title}">
						<td width="169">
							<c:set var="emptyCol" value="0"></c:set>
							<c:forEach var="z" items="${EmptyCols}">
								<c:if test="${tFlag == (z-1)}">
									<c:set var="emptyCol" value="1"></c:set>
								</c:if>
							</c:forEach>
							<c:if test="${emptyCol == 0}">
								<input type="checkbox" name="col" value="${tFlag }" checked="checked">
							</c:if>

					  </td>
						<c:set var="tFlag" value="${tFlag+1}"></c:set>
					</c:forEach>
				</tr>
				
				<thead>
				<tr>

					<th width="93"> 
						X轴{X axis}					</th>
					<c:forEach var="i" items="${title}">
						<th>
							${i }
						</th>
					</c:forEach>
				</tr>
				</thead>
				<c:set var="flag" value="0"></c:set>
				<c:set var="num" value="0"></c:set>
				<c:forEach var="j" items="${xScale}">
					<tr <c:if test="${num%2==0}">class="odd"</c:if><c:if test="${num%2==1}">class="even"</c:if>>
						<td  width="93">
								<input type="checkbox" name="row" value="${flag }" checked="checked">
							<br>
							${j }
					  </td>
						<c:forEach var="jj" items="${showList[flag]}">
							<td>
								${jj }
							</td>
						</c:forEach>
						<c:set var="flag" value="${flag+1}"></c:set>
					</tr>
					<c:set var="num" value="${num+1}"></c:set>
				</c:forEach>
		  </table>
		  <br>
			<div class="center">
								<center>
										<input type="submit" class="next" value=""
											onmouseover="this.style.backgroundPosition='left -36px'"
											onmouseout="this.style.backgroundPosition='left top'" />
										<input type="reset" class="reset" value=""
											onmouseover="this.style.backgroundPosition='left -36px'"
											onmouseout="this.style.backgroundPosition='left top'" />
										<input type="button" class="back" value=""
											onmouseover="this.style.backgroundPosition='left -36px'"
											onmouseout="this.style.backgroundPosition='left top'"
											onClick="javascript:history.back(-1);" />
								</center>
									</div>
		</form>


		
</div>

<div id="featured-bottom"></div>

</div>

</div>
		

<!--Place additional secondary content in the next division.-->

<div id="left-column">

<h2 class="bottom-border">Recent Clients</h2>

<div id="recent-clients">
						<ul>
							<li>
								<a href="http://www.jmu.edu.cn/"><img style="width: 64px; height: 64px;"
										title="集美大学/Our School-JMU" alt="Client Name"
										src="images/client_thumb_1.jpg" />
								</a>
							</li>
							<li>
								<a href="http://cec.jmu.edu.cn/dyc"><img style="width: 64px; height: 64px;"
										title="丁跃潮的主页" alt="Client Name"
										src="images/client_thumb_2.jpg" />
								</a>
							</li>
							<li>
								<a href="http://weibo.com/kxmcat"><img style="width: 64px; height: 64px;"
										alt="Client Name" title="柯昕玫的微博/K's microblog"
										src="images/client_thumb_3.jpg" />
								</a>
							</li>
							<li>
								<a href="#"><img style="width: 64px; height: 64px;"
										alt="Waiting" src="images/client_thumb_4.jpg" /> </a>
							</li>
							<li>
								<a href="#"><img style="width: 64px; height: 64px;"
										alt="Waiting" src="images/client_thumb_5.jpg" /> </a>
							</li>
							<li>
								<a href="#"><img style="width: 64px; height: 64px;"
										alt="Waiting" src="images/client_thumb_6.jpg" /> </a>
							</li>
							<li>
								<a href="#"><img style="width: 64px; height: 64px;"
										alt="Waiting" src="images/client_thumb_7.jpg" /> </a>
							</li>
							<li>
								<a href="#"><img style="width: 64px; height: 64px;"
										alt="Waiting" src="images/client_thumb_8.jpg" /> </a>
							</li>
							<li>
								<a href="#"><img style="width: 64px; height: 64px;"
										alt="Waiting" src="images/client_thumb_9.jpg" /> </a>
							</li>
						</ul>
					</div>
<h2 class="bottom-border">
						About Us
					</h2>

					<h3 class="headline">
						<span class="date">08 <abbr title="June">DEC</abbr> 11</span>
					</h3>

					<p class="news"><br>The program is develop by team K&amp;W which is direct by professor YueChao Ding and is primary product of .. . . 
						<br> 
						<span class="right"><a href="aboutUs.jsp" title="Read the Full Story">&raquo; 
								Read More</a> 
						</span> 
					</p>

					<p>
						<a href="#"><img class="left"
								style="width: 51px; height: 41px;" alt="RSS Feed"
								src="images/horizontalfold1.png" />
						</a>We always try to fit your need. 
					</p>
</div>

</div>
			<div id="footer">

				<div id="footer-content">

					<div id="footer-right">
						Design by 
						<a title="Designed by K&W"
							href="">K&W</a>
					</div>

					<p>  
						Copyright &copy; 2011 <br></p>

				</div>

			</div>

		</div>

</body>
</html>