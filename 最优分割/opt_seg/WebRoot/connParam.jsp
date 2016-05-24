<%@ page language="java" pageEncoding="gb2312"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>

		<title>多元有序数据最优分割</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="css.css" rel="stylesheet" type="text/css" media="all" />

		<link rel="stylesheet" href="iefixes.css" type="text/css" />

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
						<img align='top' src="images/star-grey.png"  width="15" height="15"><span class="access-key">CHOOSETABLE</span>
					</li>
					<li>
						<img align='top' src="images/star-grey.png"  width="15" height="15"><span class="access-key">CHOOSEDATA</span>
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

					<h2 class="bottom-border">
						Welcome to MulVerOrdCut 1.0
					</h2>
					

					<div id="featured-wrapper">

						<div id="featured-top"></div>

						<div id="featured-content">


							<form method="post" action="getTableName">
								<div class="featured">
									<div class="number">
										01
									</div>

									<h3>
										<strong>数据库类型{database type}</strong>
									</h3>



									<select name="databaseKind" size="1">
										<option value="sqlserver" selected>
											SQLSERVER
										</option>
										<option value="oracle">
											ORACLE
										</option>
										<option value="mysql">
											MySQL
										</option>
									</select>


								</div>

								<div class="featured">

									<div class="number">
										02
									</div>

									<h3>
										<strong>数据库名{database name}</strong>
									</h3>

									<input  type="text" name="dataBase" border="1px" size="21"><font size="5"><b>*</b></font>
								</div>

								<div class="featured">

									<div class="number">
										03
									</div>

									<h3>
										<strong>用户名{user name}</strong>
									</h3>

									<input type="text" name="userName" size="21"><font size="5"><b>*</b></font> 
								</div>

								<div class="featured">

									<div class="number">
										04
									</div>

									<h3>
										<strong>密码{password}</strong>
									</h3>
									<input type="password" name="password" size="21"><font size="5"><b>*</b></font>
								</div>

								<div class="featured">

									<div class="number">
										05
									</div>

									<h3>
										<strong>IP</strong>
									</h3>

									<input type="text" name="ip" size="21"><font size="5"><b>*</b></font>

								</div>

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
						Outside Links
					</h2>

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
										alt="Waiting"
										src="images/client_thumb_4.jpg" />
								</a>
							</li>
							<li>
								<a href="#"><img style="width: 64px; height: 64px;"
										alt="Waiting"
										src="images/client_thumb_5.jpg" />
								</a>
							</li>
							<li>
								<a href="#"><img style="width: 64px; height: 64px;"
										alt="Waiting"
										src="images/client_thumb_6.jpg" />
								</a>
							</li>
							<li>
								<a href="#"><img style="width: 64px; height: 64px;"
										alt="Waiting"
										src="images/client_thumb_7.jpg" />
								</a>
							</li>
							<li>
								<a href="#"><img style="width: 64px; height: 64px;"
										alt="Waiting"
										src="images/client_thumb_8.jpg" />
								</a>
							</li>
							<li>
								<a href="#"><img style="width: 64px; height: 64px;"
										alt="Waiting"
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
