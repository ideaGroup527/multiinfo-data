package org.plot.sevlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.plot.chart.impl.Ding;
import org.plot.chart.impl.Ding1;
import org.plot.chart.impl.Ding2;
import org.plot.exception.ObjectToObjectException;
import org.plot.parse.ObjectToObject;
import org.plot.parse.ParseBoolean;
import org.plot.parse.ParseDouble;
import org.plot.parse.ParseInteger;
import org.plot.parse.StringToObject;
import org.plot.pojo.ChartFont;
import org.plot.util.Transfer;

public class DingChart extends HttpServlet {

	public DingChart() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ParseDouble pd = new ParseDouble();
		ParseBoolean pb = new ParseBoolean();
		double maxLimit = 0;// 最大极限
		double minLimit = 0;// 最小极限
		boolean left_right = false;
		boolean up_down = false;
		String upBackColor = "#99CCFF";
		String downBackColor = "#99CCFF";
		String lineColor = "#0000FF";
		String roundColor = "#FF0000";
		String titleFont = "宋体";
		String titleSize = "25";
		String titleColor = "#3366FF";
		String rowFont = "宋体";
		String rowSize = "15";
		String rowColor = "#3366FF";
		String colFont = "宋体";
		String colSize = "15";
		String colColor = "#3366FF";

		//颜色、字体
		ChartFont cf = new ChartFont();
		if (null != request.getParameter("upBackColor")
				&& !"".equals(request.getParameter("upBackColor"))) {
			// 从页面取值并强制转换
			upBackColor = request.getParameter("upBackColor");
			upBackColor = upBackColor.substring(1, upBackColor.length()) ;
			cf.setUpBackColor(upBackColor) ;
		}
		if (null != request.getParameter("downBackColor")
				&& !"".equals(request.getParameter("downBackColor"))) {
			// 从页面取值并强制转换
			downBackColor = request.getParameter("downBackColor");
			downBackColor = downBackColor.substring(1, downBackColor.length()) ;
			cf.setDownBackColor(downBackColor) ;
		}
		if (null != request.getParameter("lineColor")
				&& !"".equals(request.getParameter("lineColor"))) {
			// 从页面取值并强制转换
			lineColor = request.getParameter("lineColor");
			lineColor = lineColor.substring(1, lineColor.length()) ;
			cf.setLineColor(lineColor) ;
		}
		if (null != request.getParameter("roundColor")
				&& !"".equals(request.getParameter("roundColor"))) {
			// 从页面取值并强制转换
			roundColor = request.getParameter("roundColor");
			roundColor = roundColor.substring(1, roundColor.length()) ;
			cf.setRoundColor(roundColor) ;
		}
		if (null != request.getParameter("titleFont")
				&& !"".equals(request.getParameter("titleFont"))) {
			// 从页面取值并强制转换
			titleFont = request.getParameter("titleFont");
			cf.setTitleFont(titleFont) ;
		}
		if (null != request.getParameter("titleSize")
				&& !"".equals(request.getParameter("titleSize"))) {
			// 从页面取值并强制转换
			titleSize = request.getParameter("titleSize");
			try {
				cf.setTitleSize(Integer.parseInt(titleSize)) ;
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
			cf.setTitleColor(titleColor);
		}
		if (null != request.getParameter("rowFont")
				&& !"".equals(request.getParameter("rowFont"))) {
			// 从页面取值并强制转换
			rowFont = request.getParameter("rowFont");
			cf.setRowFont(rowFont) ;
		}
		if (null != request.getParameter("rowSize")
				&& !"".equals(request.getParameter("rowSize"))) {
			// 从页面取值并强制转换
			rowSize = request.getParameter("rowSize");
			try {
				cf.setRowSize(Integer.parseInt(rowSize)) ;
			} catch (NumberFormatException e) {
				// 跳转到消息页面
				// 保存消息
				request.setAttribute("message", "行说明字体大小格式错误！");
				request.getRequestDispatcher("message.jsp").forward(request,
						response);
				return;
			}
		}
		if (null != request.getParameter("rowColor")
				&& !"".equals(request.getParameter("rowColor"))) {
			// 从页面取值并强制转换
			rowColor = request.getParameter("rowColor");
			rowColor = rowColor.substring(1, rowColor.length()) ;
			cf.setRowColor(rowColor) ;
		}
		if (null != request.getParameter("colFont")
				&& !"".equals(request.getParameter("colFont"))) {
			// 从页面取值并强制转换
			colFont = request.getParameter("colFont");
			cf.setColFont(colFont) ;
		}
		if (null != request.getParameter("colSize")
				&& !"".equals(request.getParameter("colSize"))) {
			// 从页面取值并强制转换
			colSize = request.getParameter("colSize");
			try {
				cf.setColSize(Integer.parseInt(colSize)) ;
			} catch (NumberFormatException e) {
				// 跳转到消息页面
				// 保存消息
				request.setAttribute("message", "列说明字体大小格式错误！");
				request.getRequestDispatcher("message.jsp").forward(request,
						response);
				return;
			}
		}
		if (null != request.getParameter("colColor")
				&& !"".equals(request.getParameter("colColor"))) {
			// 从页面取值并强制转换
			colColor = request.getParameter("colColor");
			colColor = colColor.substring(1, colColor.length()) ;
			cf.setColColor(colColor) ;
		}

		// 判断是否需要自定义最大值
		if (null != request.getParameter("left_right")
				&& !"".equals(request.getParameter("left_right"))) {
			// 从页面取值并强制转换
			left_right = pb.parseBoolean(request.getParameter("left_right"));
		} else {
			// 默认赋值
			left_right = false;
		}

		// 判断是否需要自定义最大值
		if (null != request.getParameter("up_down")
				&& !"".equals(request.getParameter("up_down"))) {
			// 从页面取值并强制转换
			up_down = pb.parseBoolean(request.getParameter("up_down"));
		} else {
			// 默认赋值
			up_down = false;
		}

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
		if (maxLimit > 0) {
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

		String objStr = null;
		if (!"".equals(request.getParameter("tran"))
				&& null != request.getParameter("tran")) {
			objStr = request.getParameter("tran");
		}

		/** *反序列化，把string转换为对象* */
		ObjectToObject oto = new StringToObject();// 得到转换对象
		Transfer trans = null;
		try {
			trans = (Transfer) oto.transform(objStr);
		} catch (ObjectToObjectException e) {
			e.printStackTrace();
		}
		ParseInteger pi = new ParseInteger();

		String[] dingTitle = trans.getLineTitle();
		String xTitle = trans.getXTitle();
		String yTitle = trans.getYTitle();
		String chartTitle = trans.getChartTitle();
		List value = trans.getValue();
		String[] xScale = trans.getXscale();

		/** *********************************判断绘图种类*********************************** */
		String flag = null;
		flag = "ZXDB";
		// 判断要绘制的丁氏图类型
		if (null != request.getParameter("dingType")
				&& !"".equals(request.getParameter("dingType"))) {
			// 赋值绘图类型
			flag = request.getParameter("dingType");
			// 判断是否符合绘图类型
			if (!flag.equals("ZXDB") && !flag.equals("HXDB")
					&& !flag.equals("QBDB")) {
				// 错误类型，使用默认值
				flag = "ZXDB";
			}
		}
		/** *********************************判断绘图种类结束*********************************** */
		// 判断需要绘制的丁氏图种类
		if ("ZXDB".equals(flag)) {
			// 纵向对比
			Ding dingChart = new Ding(response, chartTitle,// 图片标题
					dingTitle,// 各个线段的名称
					xTitle, // X轴的名称
					yTitle, // Y轴的名称
					value, // X、Y轴对应的值
					xScale,// X轴
					maxLimit, minLimit, left_right, up_down,cf);
		} else if ("HXDB".equals(flag)) {
			// 横向对比
			Ding1 dingChart = new Ding1(response, chartTitle,// 图片标题
					dingTitle,// 各个线段的名称
					xTitle, // X轴的名称
					yTitle, // Y轴的名称
					value, // X、Y轴对应的值
					xScale,// X轴
					maxLimit, minLimit, left_right, up_down,cf

			);
		} else if ("QBDB".equals(flag)) {
			// 全表对比
			Ding2 dingChart = new Ding2(response, chartTitle,// 图片标题
					dingTitle,// 各个线段的名称
					xTitle, // X轴的名称
					yTitle, // Y轴的名称
					value, // X、Y轴对应的值
					xScale,// X轴
					maxLimit, minLimit, left_right, up_down,cf);
		} else {
			request.setAttribute("message", "没有这种绘图方法");
			// 跳转
			request.getRequestDispatcher("message.jsp").forward(request,
					response);
			// 返回空
			return;
		}

		// 下面两语句解决不同输出的冲突问题
		// out.clear();
		// out = pageContext.pushBody();

	}

}
