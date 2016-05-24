<%@ page language="java" import="java.util.*,my_class.*" pageEncoding="gb2312"%>

<html>
  <head>
    <title>绘图参数定制(Parameters configuration for drawing)</title>
    <style type="text/css">
	<!--
	.STYLE2 {
		color: #3300ff;
		font-size: 18px;
	}
	-->
    </style>
     <script type="text/javascript">
		function xSelected(){
			var factors=<%=Parameters.factor_number%>;
			var y=document.getElementsByName("y")[0];
			var x=document.getElementsByName("x")[0];
			var s = y.value;
			for(var i=y.length; i>=0; i--){
				y.remove(i);
			}
			document.myform.y.length = factors-1;
			var j=0;
			for(var i=0; i<factors; i++){
				if(i!=x.value){
					document.myform.y.options[j].value = i;
					document.myform.y.options[j].text = i+1;
					if(s!=x.value && s==i)
						document.myform.y.options[j].selected = true;	
					j++;
				}
			}
		}
		
		function ySelected(){
			var factors=<%=Parameters.factor_number%>;
			var y=document.getElementsByName("y")[0];
			var x=document.getElementsByName("x")[0];
			var s = x.value;
			for(var i=x.length; i>=0; i--){
				x.remove(i);
			}
			document.myform.x.length = factors-1;
			var j=0;
			for(var i=0; i<factors; i++){
				if(i!=y.value){
					document.myform.x.options[j].value = i;
					document.myform.x.options[j].text = i+1;
					if(s!=y.value && s==i)
						document.myform.x.options[j].selected = true;	
					j++;
				}
			}
		}
    </script>
</head>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/jquery.colorPicker.js"></script>
  <body bgcolor="#DDDDDD">
    <div align="center">
    <p class="STYLE2">请选择绘图参数:</p>
		<p class="STYLE2">Please select the parameters for drawing:</p>
		<hr>
		<form method="POST" name="myform" action="draw.jsp">
			<table border="1" cellspacing="0">
				<tr>
					<td>请选择坐标轴对应因子(Please select factor for axis)：</td>
					<td>X轴(Axis X):
					<select name="x" onchange="xSelected()">
							   <% 
						   		for(int i=1; i<Parameters.factor_number+1; i++){
						   %>
						        <option value="<%=i-1 %>" <%if(i==1) out.print("selected");%>><%=i %></option>
						        
						   <% 
						   		}
						   %>
					</select> 
					Y轴(Axis Y):
					<select name="y" onchange="ySelected()">
						   <% 
						   		for(int i=1; i<Parameters.factor_number+1; i++){
						   %>
						        <option value="<%=i-1 %>" <%if(i==2) out.print("selected");%>><%=i %></option>
						        
						   <% 
						   		}
						   %>
					</select>
					</td>
				</tr>
				<tr>
					<td>字体字号(The font size)：</td>
					<td><select size="1" name="size">
							<option value="10">10</option>
							<option value="11">11</option>
							<option value="12">12</option>
							<option value="13">13</option>
							<option selected value="14">14</option>
							<option value="15">15</option>
							<option value="16">16</option>
							<option value="17">17</option>
							<option value="18">18</option>
							<option value="19">19</option>
							<option value="20">20</option>
							<option value="21">21</option>
							<option value="22">22</option>
							<option value="23">23</option>
							<option value="24">24</option>
							<option value="25">25</option>
							<option value="26">26</option>
							<option value="27">27</option>
							<option value="28">28</option>
							<option value="29">29</option>
							<option value="30">30</option>
					</select>
					</td>
				</tr>
				<tr>
					<td>字体颜色(The font color):</td>
					<td><input type="text" name="fontcolor" size="9"
						style="background: #000000" value="#000000"
						onClick="$(this).colorPicker()" readonly />
					</td>
				</tr>
				<tr>
					<td>变量点颜色(The color of variable points):</td>
					<td><input type="text" name="spointcolor" size="9"
						style="background: #FF0000" value="#FF0000"
						onClick="$(this).colorPicker()" readonly />
					</td>
				</tr>
			</table>
			<p>
				<input type="submit" value="绘图(draw)" name="B1"> <input
					type="reset" value="重置(Reset)" name="B2">
			</p>
		</form>
		<input type="button" value="后退(Back)"
			onClick="javascript:history.back(-1);">
	</div>
</body>
</html>
