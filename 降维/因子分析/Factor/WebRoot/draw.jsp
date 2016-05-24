<%@ page language="java" pageEncoding="gb2312"%>
<%@ page language="java" import="my_class.*,java.util.*"%>
<html>
 <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>因子平面图(Factor ichnography of factor analysis)</title>
     <link href="layout.css" rel="stylesheet" type="text/css">
    <!--[if lte IE 8]><script language="javascript" type="text/javascript" src="js/excanvas.min.js"></script><![endif]-->
    <script language="javascript" type="text/javascript" src="js/jquery.js"></script>
    <script language="javascript" type="text/javascript" src="js/jquery.flot.js"></script>
    <script language="javascript" type="text/javascript" src="js/jquery.flot.navigate.js"></script>
    <style type="text/css">
    #placeholder .button {
        position: absolute;
        cursor: pointer;
    }
    #placeholder div.button {
        font-size: smaller;
        color: #999;
        background-color: #eee;
        padding: 2px;
    }
    .message {
        padding-left: 50px;
        font-size: smaller;
    }
    .fs {
    	font-size:<%=request.getParameter("size")%>;
    	color:<%=request.getParameter("fontcolor")%>;
    }
    .ax {
    	font-size: 20px;
    	color: red;
    }
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
 <body>
    <h>因子分析平面图(The ichnography of Factor analysis)</h>
	<br><br><br><br><br><br>
    <div id="placeholder" style="width:1200px;height:600px;">   
    </div>
    
    <p><br> 图片可用鼠标左键单击进行拖曳，鼠标中间滚轮进行缩放 <br>
    (Picture can be dragged by clicking the left key of the mouse , the middle roller of the mouse also can scale the picture)
	</p>
	<p id="choices">选择需要显示的数据(Choose the data what to be displayed):<br></p>
    <%  	
    	int a = Integer.parseInt(request.getParameter("x"));
    	int b = Integer.parseInt(request.getParameter("y"));
    	ArrayList<Double> x = Parameters.factor_params.get(a);
    	ArrayList<Double> y = Parameters.factor_params.get(b);
    	ArrayList<String> name = Parameters.names;
    %>
	<p>
	    <form method="POST" name="myform" action="draw.jsp">
      请选择坐标轴对应因子(Please select factor corresponding by axis)：
      <br> <br>
   X轴(Axis X):
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
        <option value="<%=i-1 %>" <%if(i==b+1) out.print("selected");%>><%=i %></option>
        
   <% 
   		}
   %>
   </select>
   <input type="hidden" name="size" value="<%=request.getParameter("size")%>">
   <input type="hidden" name="fontcolor" value="<%=request.getParameter("fontcolor")%>">
   <input type="hidden" name="spointcolor" value="<%=request.getParameter("spointcolor")%>">
	  <p><input type="submit" value="绘图(Draw)" name="B1">
	  <input type="reset" value="重置(Reset)" name="B2">
	  </p>
	  </form>	
	</p>
<script type="text/javascript">
$(function () {
    
	// 添加数据
    var datasets = {
    	<%
    		for(int i=0; i<x.size(); i++){
    			out.println("\"" + i + "\": {");
    			out.println("label: \"" + name.get(i) + "\",");
				out.println("color: \"" + request.getParameter("spointcolor") + "\",");
    			out.println("data: [[" + x.get(i) + "," + y.get(i) + "]]");
    			if(i!=x.size()-1)	out.println("},");
    			else	out.print("}");
    		}
    	%>
    };
    $('<div class="ax">' + "F" + <%=Integer.parseInt(request.getParameter("x"))+1%> + '</div>').css( {
        position: 'absolute',
        display: 'none',
        top: 720 + 5,
        left: 1240 + 5,
        padding: '2px',
        opacity: 0.80
    }).appendTo("body").fadeIn(200);
    
    $('<div class="ax">' + "F" + <%=Integer.parseInt(request.getParameter("y"))+1%> + '</div>').css( {
        position: 'absolute',
        display: 'none',
        top: 100 + 5,
        left: 65 + 5,
        padding: '2px',
        opacity: 0.80
    }).appendTo("body").fadeIn(200);
    
    // 添加显示数据确认box 
    var choiceContainer = $("#choices");
    $.each(datasets, function(key, val) {
        choiceContainer.append('<br/><input type="checkbox" name="' + key +
                               '" checked="checked" id="id' + key + '">' +
                               '<label for="id' + key + '">'
                                + val.label + '</label>');
    });
    choiceContainer.find("input").click(plotAccordingToChoices);
    
    //设置绘图参数
    var options = {
        series: { points: { show: true }, shadowSize: 0 },
        grid: { hoverable: true, clickable: false },
        xaxis: { zoomRange: [0.001, 10], panRange: [-10, 10], show: true },
        yaxis: { zoomRange: [0.001, 10], panRange: [-10, 10], show: true },
        zoom: {
            interactive: true
        },
        pan: {
            interactive: true
        },
        legend: {
        	show: false
        }
    };
	
    var placeholder = $("#placeholder");
    
    // 海图缩放设置
    placeholder.bind('plotpan', function (event, plot) {
        var axes = plot.getAxes();
        $(".message").html("Panning to x: "  + axes.xaxis.min.toFixed(2)
                           + " &ndash; " + axes.xaxis.max.toFixed(2)
                           + " and y: " + axes.yaxis.min.toFixed(2)
                           + " &ndash; " + axes.yaxis.max.toFixed(2));
    });
    placeholder.bind('plotzoom', function (event, plot) {
        var axes = plot.getAxes();
        $(".message").html("Zooming to x: "  + axes.xaxis.min.toFixed(2)
                           + " &ndash; " + axes.xaxis.max.toFixed(2)
                           + " and y: " + axes.yaxis.min.toFixed(2)
                           + " &ndash; " + axes.yaxis.max.toFixed(2));
    });

    //鼠标hover设置
    function showTooltip(x, y, contents) {
        $('<div id="tooltip">' + contents + '</div>').css( {
            position: 'absolute',
            display: 'none',
            top: y + 5,
            left: x + 5,
            border: '1px solid #fdd',
            padding: '2px',
            'background-color': '#fee',
            opacity: 0.80
        }).appendTo("body").fadeIn(200);
    }
    var previousPoint = null;
    $("#placeholder").bind("plothover", function (event, pos, item) {
        $("#x").text(pos.x.toFixed(2));
        $("#y").text(pos.y.toFixed(2));

            if (item) {
                if (previousPoint != item.dataIndex) {
                    previousPoint = item.dataIndex;
                    
                    $("#tooltip").remove();
                    showTooltip(item.pageX, item.pageY,
                                item.series.label );
                }
            }
            else {
                $("#tooltip").remove();
                previousPoint = null;            
            }
    });
	
    // 根据选项框选择数据
    function plotAccordingToChoices() {
        var data = [];
        choiceContainer.find("input:checked").each(function () {
            var key = $(this).attr("name");
            if (key && datasets[key])
                data.push(datasets[key]);
        });

        if (data.length > 0)
            $.plot(placeholder, data, options);
    }
    
    //根据选项框绘图
    plotAccordingToChoices();
});
</script>

 </body>
</html>
