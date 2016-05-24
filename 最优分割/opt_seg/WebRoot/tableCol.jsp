<%@ page language="java" pageEncoding="GBK"
	contentType="text/html;charset=gbk" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>多元有序数据最优分割</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href ="css.css " rel ="stylesheet" type ="text/css" media ="all" />
 <link rel ="stylesheet" href ="iefixes.css " type ="text/css"  />
		<style type="text/css">

	<!--.STYLE1 {
	font-size: x-large;
	font-weight: bold;
}
-->
</style>
	</head>
	<body>

		<div id="container">

			<div id="header">

				<h1>
					<a href="#" title="Return To Homepage"><img id="logo"
							style="width: 760px; height: 112px;"
							alt="Division1.0/k&w"
							src="images/header.png" />
					</a>
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

					<h2 class="bottom-border">Welcome to MulVerOrdCut 1.0</h2>

					
					

					<div id="featured-wrapper">

						<div id="featured-top"></div>

						<div id="featured-content">
							<h2 class="bottom-border"> 
								您操作的表是{Your table}：${tableName } 
							</h2>




							
							<form method="POST" action="tableData">
								<input type="hidden" name="objStr" value="${objStr }">
								<input type="hidden" name="tableName" value="${tableName }">
								<center>
								<table class="stock" width="221" border="0">
									
									<thead>
										<tr class="even">
											<th width="200"> 
												选择标识列<br>{sign column} 
											</th>
											<c:forEach var="i" items="${tableCol}">
												<th width="54" class="a1">
													<input type="radio" name="xScaleName" value="${i }" />
												</th>
											</c:forEach>
										</tr>
									</thead>
										<tr class="odd">
											<td> 
												选择数据列<br>{data column} 
											</td>
											<c:forEach var="i" items="${tableCol}">
												<td width="54">
													<input type="checkbox" name="chooseCol" value="${i }">
												</td>
											</c:forEach>
										</tr>
										<tr class="even">
											<td class="a1"> 
												列名<br>{column name} 
											</td>
											<c:forEach var="i" items="${tableCol}">
												<td width="54">
													${i }
												</td>
											</c:forEach>
										</tr>
									
								</table>
								</center>
								<p>
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

					<h2 class="bottom-border">
						Recent Clients
					</h2>

					<div id="recent-clients">
						<ul>
							<li>
								<a href="#"><img style="width: 64px; height: 64px;"
										alt="Client Name"
										src="images/client_thumb_1.jpg" />
								</a>
							</li>
							<li>
								<a href="#"><img style="width: 64px; height: 64px;"
										alt="Client Name"
										src="images/client_thumb_2.jpg" />
								</a>
							</li>
							<li>
								<a href="#"><img style="width: 64px; height: 64px;"
										alt="Client Name"
										src="images/client_thumb_3.jpg" />
								</a>
							</li>
							<li>
								<a href="#"><img style="width: 64px; height: 64px;"
										alt="Client Name"
										src="images/client_thumb_4.jpg" />
								</a>
							</li>
							<li>
								<a href="#"><img style="width: 64px; height: 64px;"
										alt="Client Name"
										src="images/client_thumb_5.jpg" />
								</a>
							</li>
							<li>
								<a href="#"><img style="width: 64px; height: 64px;"
										alt="Client Name"
										src="images/client_thumb_6.jpg" />
								</a>
							</li>
							<li>
								<a href="#"><img style="width: 64px; height: 64px;"
										alt="Client Name"
										src="images/client_thumb_7.jpg" />
								</a>
							</li>
							<li>
								<a href="#"><img style="width: 64px; height: 64px;"
										alt="Client Name"
										src="images/client_thumb_8.jpg" />
								</a>
							</li>
							<li>
								<a href="#"><img style="width: 64px; height: 64px;"
										alt="Client Name"
										src="images/client_thumb_9.jpg" />
								</a>
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