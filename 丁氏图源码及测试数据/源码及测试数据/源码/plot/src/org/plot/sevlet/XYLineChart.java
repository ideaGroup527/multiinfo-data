package org.plot.sevlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.plot.chart.Chart;
import org.plot.chart.impl.XYLine;
import org.plot.exception.ObjectToObjectException;
import org.plot.parse.Normalization;
import org.plot.parse.ObjectToObject;
import org.plot.parse.ParseBoolean;
import org.plot.parse.ParseDouble;
import org.plot.parse.ParseInteger;
import org.plot.parse.StringToObject;
import org.plot.pojo.OtherKindChartFont;
import org.plot.util.Transfer;

public class XYLineChart extends HttpServlet {

	public XYLineChart() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 数据转换
		ParseInteger pi = new ParseInteger();
		ParseBoolean pb = new ParseBoolean();
		ParseDouble pd = new ParseDouble();
		
		String upBackColor = "#99CCFF";
		String downBackColor = "#99CCFF";
		String titleFont = "宋体";
		String titleSize = "25";
		String titleColor = "#3366FF";
		String xFont = "宋体";
		String xSize = "15";
		String xColor = "#3366FF";
		String yFont = "宋体";
		String ySize = "15";
		String yColor = "#3366FF";
		
		OtherKindChartFont other = new OtherKindChartFont() ;
		
		if (null != request.getParameter("upBackColor")
				&& !"".equals(request.getParameter("upBackColor"))) {
			// 从页面取值并强制转换
			upBackColor = request.getParameter("upBackColor");
			upBackColor = upBackColor.substring(1, upBackColor.length()) ;
			other.setUpBackColor(upBackColor) ;
		}
		if (null != request.getParameter("downBackColor")
				&& !"".equals(request.getParameter("downBackColor"))) {
			// 从页面取值并强制转换
			downBackColor = request.getParameter("downBackColor");
			downBackColor = downBackColor.substring(1, downBackColor.length()) ;
			other.setDownBackColor(downBackColor) ;
		}
		if (null != request.getParameter("titleFont")
				&& !"".equals(request.getParameter("titleFont"))) {
			// 从页面取值并强制转换
			titleFont = request.getParameter("titleFont");
			other.setTitleFont(titleFont) ;
		}
		if (null != request.getParameter("titleSize")
				&& !"".equals(request.getParameter("titleSize"))) {
			// 从页面取值并强制转换
			titleSize = request.getParameter("titleSize");
			try {
				other.setTitleSize(Integer.parseInt(titleSize)) ;
			} catch (NumberFormatException e) {
				// 跳转到消息页面
				// 保存消息
				request.setAttribute("message", "标题字体大小格式错误！");
				request.getRequestDispatcher("message.jsp").forward(request,
						response);
				return;
			}
		}
		if (null != request.getParameter("titleColor")
				&& !"".equals(request.getParameter("titleColor"))) {
			// 从页面取值并强制转换
			titleColor = request.getParameter("titleColor");
			titleColor = titleColor.substring(1, titleColor.length()) ;
			other.setTitleColor(titleColor);
		}
		if (null != request.getParameter("yFont")
				&& !"".equals(request.getParameter("yFont"))) {
			// 从页面取值并强制转换
			yFont = request.getParameter("yFont");
			other.setYFont(yFont) ;
		}
		if (null != request.getParameter("ySize")
				&& !"".equals(request.getParameter("ySize"))) {
			// 从页面取值并强制转换
			ySize = request.getParameter("ySize");
			try {
				other.setYSize(Integer.parseInt(ySize)) ;
			} catch (NumberFormatException e) {
				// 跳转到消息页面
				// 保存消息
				request.setAttribute("message", "行说明字体大小格式错误！");
				request.getRequestDispatcher("message.jsp").forward(request,
						response);
				return;
			}
		}
		if (null != request.getParameter("yColor")
				&& !"".equals(request.getParameter("yColor"))) {
			// 从页面取值并强制转换
			yColor = request.getParameter("yColor");
			yColor = yColor.substring(1, yColor.length()) ;
			other.setYColor(yColor) ;
		}
		if (null != request.getParameter("xFont")
				&& !"".equals(request.getParameter("xFont"))) {
			// 从页面取值并强制转换
			xFont = request.getParameter("xFont");
			other.setXFont(xFont) ;
		}
		if (null != request.getParameter("xSize")
				&& !"".equals(request.getParameter("xSize"))) {
			// 从页面取值并强制转换
			xSize = request.getParameter("xSize");
			try {
				other.setXSize(Integer.parseInt(xSize)) ;
			} catch (NumberFormatException e) {
				// 跳转到消息页面
				// 保存消息
				request.setAttribute("message", "列说明字体大小格式错误！");
				request.getRequestDispatcher("message.jsp").forward(request,
						response);
				return;
			}
		}
		if (null != request.getParameter("xColor")
				&& !"".equals(request.getParameter("xColor"))) {
			// 从页面取值并强制转换
			xColor = request.getParameter("xColor");
			xColor = xColor.substring(1, xColor.length()) ;
			other.setXColor(xColor) ;
		}
		
		//得到对象反序列化后的字符串
		String objStr = null;
		if (!"".equals(request.getParameter("tran"))
				&& null != request.getParameter("tran")) {
			objStr = request.getParameter("tran") ;
		}
		/** *反序列化，把string转换为对象* */
		ObjectToObject oto = new StringToObject();// 得到转换对象
		Transfer trans = null ;
		try {
			trans = (Transfer) oto.transform(objStr);
		} catch (ObjectToObjectException e) {
			e.printStackTrace();
		}
		
		
		
		// 获得图片所在位置的路径
		String graphURL = "";
		// 获得图片的相关信息
		String chartTitle = "";
		String fileName = "";
		String xTitle = "";
		String yTitle = "";
		// 是否需要正规化处理
		boolean isNormalization = true;
		int width = 0;
		int height = 0;
		double maxLimit = 0;// 最大极限
		double minLimit = 0;// 最小极限
		List value = new ArrayList();
		// 横向或纵向
		boolean orient = false;

		String[] xScale = trans.getXscale();
		chartTitle = trans.getChartTitle();
		String[] LineTitle = trans.getLineTitle();
		value = trans.getValue() ;
		/***********************************************************************
		 * 从上页面取数据并处理开始
		 **********************************************************************/
		// 长度，如果输入不为空则为所输入的数，如果是空则默认为980
		if (null != request.getParameter("width")
				&& !"".equals(request.getParameter("width"))) {
			// 从页面取值并强制转换
			width = Integer.parseInt(request.getParameter("width"));
		} else {
			// 默认赋值
			width = 980;
		}
		// 高度，如果输入不为空则为所输入的数，如果是空则默认为450
		if (null != request.getParameter("height")
				&& !"".equals(request.getParameter("height"))) {
			// 从页面取值并强制转换
			height = pi.parseInt(request.getParameter("height"));
		} else {
			// 默认赋值
			height = 450;
		}

		// X轴名称
		if (null != request.getParameter("xTitle")
				&& !"".equals(request.getParameter("xTitle"))) {
			// 从页面取值并强制转换
			xTitle = request.getParameter("xTitle");
		} else {
			// 默认赋值
			xTitle = "X轴";
		}
		// Y轴名称
		if (null != request.getParameter("yTitle")
				&& !"".equals(request.getParameter("yTitle"))) {
			// 从页面取值并强制转换
			yTitle = request.getParameter("yTitle");
		} else {
			// 默认赋值
			yTitle = "Y轴";
		}

		// 判断是否需要正规化处理
		if (null != request.getParameter("isNormalization")
				&& !"".equals(request.getParameter("isNormalization"))) {
			// 从页面取值并强制转换
			isNormalization = pb.parseBoolean(request
					.getParameter("isNormalization"));
			
			// 判断是否需要自定义最大值
			if (null != request.getParameter("maxLimit")
					&& !"".equals(request.getParameter("maxLimit"))) {
				// 从页面取值并强制转换
				maxLimit = pd.parseDouble(request.getParameter("maxLimit"));
			} else {
				// 默认赋值
				maxLimit = 0.0;
			}

			// 判断是否需要自定义最小值
			if (null != request.getParameter("minLimit")
					&& !"".equals(request.getParameter("minLimit"))) {
				// 从页面取值并强制转换
				minLimit = pd.parseDouble(request.getParameter("minLimit"));
			} else {
				// 默认赋值
				minLimit = 0.0;
			}
			// 判断正规范围的有效性
			if (maxLimit > 1 || maxLimit < 0) {
				// value的外层集合大小与标题个数不同
				// 存储信息提示
				request.setAttribute("message", "最大正规数应该在0~1");
				// 跳转
				request.getRequestDispatcher("message.jsp").forward(request,
						response);
				// 返回空
				return;
			}
			if (minLimit > 1 || minLimit < 0) {
				// value的外层集合大小与标题个数不同
				// 存储信息提示
				request.setAttribute("message", "最小正规数应该在0~1");
				// 跳转
				request.getRequestDispatcher("message.jsp").forward(request,
						response);
				// 返回空
				return;
			}
			if(maxLimit>0){
				if (minLimit > maxLimit) {
					// value的外层集合大小与标题个数不同
					// 存储信息提示
					request.setAttribute("message", "最小正规数不能大于最大正规数");
					// 跳转
					request.getRequestDispatcher("message.jsp").forward(request,
							response);
					// 返回空
					return;
				}
			}
		} else {
			// 默认赋值
			isNormalization = true;
		}
		if (isNormalization) {
			// 正规化处理
			Normalization sw = new Normalization();
			value = sw.min_Switch(trans.getValue(), maxLimit, minLimit);// 转化为0~1表示
		} else {
			// 无正规化处理
			// value = value;
		}
		
		// 判断是否旋转
		if (null != request.getParameter("orient")
				&& !"".equals(request.getParameter("orient"))) {
			// 从页面取值并强制转换
			orient = pb.parseBoolean(request
					.getParameter("orient"));
		} else {
			// 默认赋值
			orient = false;
		}
		/***********************************************************************
		 * 从上页面取数据并处理结束
		 **********************************************************************/

		try {
			HttpSession session = request.getSession();
			// 调用线段绘制的类
			Chart chart = new XYLine(session,// 固定格式传入session
					// ******必须传入******
					chartTitle,// 图片标题
					LineTitle,// 各个线段的名称
					xTitle, // X轴的名称
					yTitle, // Y轴的名称
					value, // X、Y轴对应的值
					width, // 宽度
					height,// 高度
					xScale,// 年-2维
					orient,other);

			// 获得图片的名称和后缀
			fileName = chart.getFileName();
			// 获得图片的暂时路径
			graphURL = request.getContextPath()
					+ "/servlet/DisplayChart?filename=" + fileName;
			// 保存图片的访问路径
			request.setAttribute("graphURL", graphURL);
			// 跳转到显示页面
			request.getRequestDispatcher("show.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
