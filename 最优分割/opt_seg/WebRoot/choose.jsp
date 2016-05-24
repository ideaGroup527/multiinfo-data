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
		<base href="<%=basePath%>">

		<title>输入最优分割参数</title>

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
		<script type="text/javascript">
	function next(){
		if(cutform.Elim.checked)document.getElementById("isElim").value = "true";
		else{document.getElementById("isElim").value = "false";}
		
		if(cutform.Log.checked)document.getElementById("isLog").value = "true";
		else{document.getElementById("isLog").value = "false";}
		
		document.getElementById("myCutnum").value = cutform.cutnum.options[cutform.cutnum.selectedIndex].value;
		
		document.cutform.submit();
	}
</script>
	</head>

	<body>

		<div id="container">

			<div id="header">

				<h1>
					<a href="#" title="Return To Homepage"><img id="logo"
							style="width: 760px; height: 112px;"
							alt="Your Website Name / Company Slogan Tagline"
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
						<img align='top' src="images/star-green.png"  width="15" height="15"><span class="menu-key">DIVISION</span>
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

					<h2 class="bottom-border">Welcome to Division 1.0&nbsp;</h2>

					<div id="featured-wrapper">

						<div id="featured-top"></div>

						<div id="featured-content">
							<h2 class="bottom-border"> 
								请选择最优分割参数 {division mode}</h2> 
							<h2>所选的工作表是{your table}:<span class="STYLE1">${tablename }</span></h2>
							<h3>
							</h3>

							<form method="post" name="cutform" action="doCut">
								<input type="hidden" name="tran" value="${tran }">
								<input type="hidden" name="isElim" id="isElim" value="">
								<input type="hidden" name="isLog" id="isLog" value="">
								<input type="hidden" name="myCutnum" id="myCutnum" value="">
								<table class="stock" width="353" border="0" height="74" align="center">
									
									<thead>
										<tr>
											<th>
												<input type="checkbox" value="消除异常值" name="Elim"> 
												&nbsp;消除异常值<br>&nbsp;{Eliminate outliers}&nbsp; &nbsp; 
											</th>
											<th>
												<input type="checkbox" value="对数变换" name="Log">
												&nbsp;对数变换&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br>{Logarithmic transformation}
											</th>
										</tr>
									</thead>
										<tr class="even">
											<td>
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												分割段数：<br>{division number}
											</td>
											<td>
												<select name="cutnum">
													<option value="1">
														1
													</option>
													<option value="2">
														2
													</option>
													<option value="3">
														3
													</option>
													<option value="4">
														4
													</option>
													<option value="5">
														5
													</option>
													<option value="6">
														6
													</option>
													<option value="7">
														7
													</option>
													<option selected value="8">
														8
													</option>
													<option value="9">
														9
													</option>
													<option value="10">
														10
													</option>
													<option value="11">
														11
													</option>
													<option value="12">
														12
													</option>
													<option value="13">
														13
													</option>
													<option value="14">
														14
													</option>
													<option value="15">
														15
													</option>
													<option value="16">
														16
													</option>
													<option value="17">
														17
													</option>
													<option value="18">
														18
													</option>
													<option value="19">
														19
													</option>
													<option value="20">
														20
													</option>
													<option value="21">
														21
													</option>
													<option value="22">
														22
													</option>
													<option value="23">
														23
													</option>
													<option value="24">
														24
													</option>
													<option value="25">
														25
													</option>
													<option value="26">
														26
													</option>
													<option value="27">
														27
													</option>
													<option value="28">
														28
													</option>
													<option value="29">
														29
													</option>
													<option value="30">
														30
													</option>
												</select>
											</td>
										</tr>
									

								</table>
								<br>
								注：分割段数可以多取，留有余地，后续步骤根据分割情况确定最终段数<br>
								P.S : The final division number can be later identified
								<br>

								<br>
								<br>
								<hr>
								<p>
								<div class="center">
								<center>
										<input type="button" class="next" value=""
											onmouseover="this.style.backgroundPosition='left -36px'"
											onmouseout="this.style.backgroundPosition='left top'" onclick="next()"/>
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
