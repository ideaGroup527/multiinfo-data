<%@ page language="java" pageEncoding="gb2312"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>多元有序数据最优分割系统</title>
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
						<img align='top' src="images/star-grey.png"  width="15" height="15"><span class="access-key">CONNECT</span>
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

					<h2 class="bottom-border">Welcome to Division 1.0</h2>

					<p class="justify"><strong>经济信息</strong>是时间和经济规律的函数，其资料或数据都按一定的时间顺序排列(一般是由老到新)，为了探索事物发展的周期性或旋回性，并合理地划分阶段，就需要进行最优分割。本程序为您的多维数据合理分段。过程清晰简单，仅需要您的一张表格即可。</p><p class="justify"><br><strong>Economic information</strong> is function about time and the economic law,it's information or data in a chronological order (usually from Past to present ). In order to explore the developing period or cycles and reasonably dividing stage, we requires optimal segmentation.&nbsp; This program provide reasonable divison for your multidimensional data.The program is clear and simple, only need you to provide a table(Excel or table from database).<br> 
						<span class="right"><a title="Learn More About Us" href="learnMore.jsp">&raquo; 
								Learn More</a> 
						</span> 
					</p>
					<h2 class="bottom-border"> 
						　</h2>

					<div id="featured-wrapper">
						<div id="featured-top"></div>
						<div id="featured-content">							
								<form name="example_form" action="#" method="POST">
									<h2>请选择您数据表的形式<br>{Choose Your Data Type}:</h2>
									<font size="4">
									<center>

										<span class="access-key"></span><a title="通过Excel文件:" accesskey="2" href="readExcel.jsp">Excel</a><br>
										<span class="access-key"></span><a title="通过连接数据库（需要IP地址）:" accesskey="2" href="connParam.jsp">Database Table</a><br><br>
									</center></font>
									<p>(<font color="red"><b>注：</b></font>用户事先应准备好自己需要分析的数据，数据库可以用Excel、SQL Server、Oracle和MySQL。如果用Excel，数据样例见<a href="http://210.34.136.253:8488/Sample_data_cutting.xls">实例Excel表</a>；采用其他数据库需要在下一步填入数据库的URL、用户名、密码。)<p>
		<p>(<font color="red"><b>Notic: </b></font>User must prepare the data for analysis. The dadabase could be excel, SQL Server,Oracle and MySQL. If excel is selected, the sample data is like &quot;<a href="http://210.34.136.253:8488/Sample_data_cutting.xls">sample 
		excel file</a> If you choose other database, you may fill in the URL, user name and passwrod.)
									<p>
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
								<a href="http://cec.jmu.edu.cn/dyc"><img style="width: 64px; height: 64px;"
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