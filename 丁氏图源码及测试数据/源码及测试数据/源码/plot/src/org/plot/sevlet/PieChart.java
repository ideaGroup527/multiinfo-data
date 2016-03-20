package org.plot.sevlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.plot.chart.Chart;
import org.plot.chart.impl.Pie;
import org.plot.exception.ObjectToObjectException;
import org.plot.parse.ObjectToObject;
import org.plot.parse.ParseBoolean;
import org.plot.parse.ParseInteger;
import org.plot.parse.StringToObject;
import org.plot.pojo.OtherKindChartFont;
import org.plot.util.Transfer;

public class PieChart extends HttpServlet {

	public PieChart() {
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
		
		String upBackColor = "#99CCFF";
		String downBackColor = "#99CCFF";
		String titleFont = "宋体";
		String titleSize = "25";
		String titleColor = "#3366FF";
		String pieLableFont = "宋体";
		String pieLableSize = "15";
		String pieLableColor = "#3366FF";
		
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
		
		if (null != request.getParameter("pieLableFont")
				&& !"".equals(request.getParameter("pieLableFont"))) {
			// 从页面取值并强制转换
			pieLableFont = request.getParameter("pieLableFont");
			pieLableFont = pieLableFont.substring(1, pieLableFont.length()) ;
			other.setPieLableFont(pieLableFont);
		}
		if (null != request.getParameter("pieLableColor")
				&& !"".equals(request.getParameter("pieLableColor"))) {
			// 从页面取值并强制转换
			pieLableColor = request.getParameter("pieLableColor");
			pieLableColor = pieLableColor.substring(1, pieLableColor.length()) ;
			other.setPieLableColor(pieLableColor);
		}
		if (null != request.getParameter("pieLableSize")
				&& !"".equals(request.getParameter("pieLableSize"))) {
			// 从页面取值并强制转换
			pieLableSize = request.getParameter("pieLableSize");
			try {
				other.setPieLableSize(Integer.parseInt(pieLableSize)) ;
			} catch (NumberFormatException e) {
				// 跳转到消息页面
				// 保存消息
				request.setAttribute("message", "标签字体大小格式错误！");
				request.getRequestDispatcher("message.jsp").forward(request,
						response);
				return;
			}
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
		// false为3D ture为平面的
		boolean idea = true;
		// 按行或者按列绘制,true 为按行 false为按列
		boolean lineOrRow = true;
		// 选择列还是行，第几列第几行
		int choose = 0;
		// 图片相对路径
		String fileName = "";
		String[] xScale = null;
		xScale = trans.getXscale();
		String[] pieTitle = trans.getLineTitle();
		chartTitle = trans.getChartTitle();
		List value = trans.getValue();
		int width = 0;
		int height = 0;

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
		// 判断是绘制平面还是3D（idea）
		if (null != request.getParameter("idea")
				&& !"".equals(request.getParameter("idea"))) {
			// 从页面取值并强制转换
			idea = pb.parseBoolean(request.getParameter("idea"));
		} else {
			// 默认赋值
			idea = true;
		}
		// 判断是哪行或者哪列
		if (null != request.getParameter("row")
				&& !"".equals(request.getParameter("row"))
				&& null != request.getParameter("line")
				&& !"".equals(request.getParameter("line"))) {
			// 即选择了行又选择了列，错误的操作
			// 存储信息提示
			request.setAttribute("message", "错误的操作，即选择了行又选择了列");
			// 跳转
			request.getRequestDispatcher("message.jsp").forward(request,
					response);
			// 返回空
			return;
		} else if (null != request.getParameter("chooseAll")
				&& !"".equals(request.getParameter("chooseAll"))) {
			// 按行绘制标识符
			lineOrRow = true;
			// 选择所有数据，所有数据进行统计
			choose = -1;
		} else if (null != request.getParameter("line")
				&& !"".equals(request.getParameter("line"))) {
			// 按行绘制标识符
			lineOrRow = true;
			// 所选行
			choose = pi.parseInt(request.getParameter("line"));
		} else if (null != request.getParameter("row")
				&& !"".equals(request.getParameter("row"))) {
			// 按列绘制标识符
			lineOrRow = false;
			// 所选列
			choose = pi.parseInt(request.getParameter("row"));
		}

		/***********************************************************************
		 * 从上页面取数据并处理结束
		 **********************************************************************/

		// 判断
		if (value.size() != pieTitle.length) {
			// value的外层集合大小与标题个数不同
			// 存储信息提示
			request.setAttribute("message", "标题个数与数值个数不同");
			// 跳转
			request.getRequestDispatcher("message.jsp").forward(request,
					response);
			// 返回空
			return;
		}

		try {
			HttpSession session = request.getSession();
			Chart chart = null;
			// 调用线段绘制的类
			if (!lineOrRow) {
				// 按列取
				chart = new Pie(session,// 固定格式传入session ******必须传入******
						pieTitle[choose],// 图片标题
						pieTitle,// 各个线段的名称
						value, // X、Y轴对应的值
						xScale,// x轴的刻度
						width, // 宽度
						height,// 高度
						idea,// false为3D ture为平面的
						choose,// 选择列还是行，第几列第几行
						other
				);
			} else {
				// 按行取
				chart = new Pie(session,// 固定格式传入session ******必须传入******
						chartTitle,// 图片标题
						pieTitle,// 各个线段的名称
						value, // X、Y轴对应的值
						width, // 宽度
						height,// 高度
						idea,// false为3D ture为平面的
						choose,// 选择列还是行，第几列第几行
						other
				);
			}
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
