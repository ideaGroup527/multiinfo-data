package org.plot.sevlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.plot.chart.Chart;
import org.plot.chart.impl.XYBar;
import org.plot.exception.ObjectToObjectException;
import org.plot.parse.Normalization;
import org.plot.parse.ObjectToObject;
import org.plot.parse.ParseBoolean;
import org.plot.parse.ParseDouble;
import org.plot.parse.ParseInteger;
import org.plot.parse.StringToObject;
import org.plot.pojo.OtherKindChartFont;
import org.plot.util.Transfer;

public class XYBarChart extends HttpServlet {

	public XYBarChart() {
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
		
		
		
		// 得到对象反序列化后的字符串
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
		// 获得图片所在位置的路径
		String graphURL = "";
		// 获得图片的相关信息
		String chartTitle = "";
		String fileName = "";
		String xTitle = "";
		String yTitle = "";
		int width = 0;
		int height = 0;
		double maxLimit = 0;// 最大极限
		double minLimit = 0;// 最小极限
		List value = null;
		// 是否需要正规化处理
		boolean isNormalization = true;
		int idea = 1;// 1为竖值平面 2为竖值3D 3为横向平面 4为横向3D
		// 选择绘图的范围
		// 小于0为全体图; 0到value.length-1 为部分; 大于等于value.length为把choose=value.length
		int choose = 1;
		boolean lable = false;// 3维立体的柱状若是总体的 没有标签 其他都有
		String[] barTitle = trans.getLineTitle();
		chartTitle = trans.getChartTitle();
		String[] xScale = trans.getXscale();

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

		// 判断是绘制平面还是3D（idea）
		if (null != request.getParameter("idea")
				&& !"".equals(request.getParameter("idea"))) {
			// 从页面取值并强制转换
			idea = pi.parseInt(request.getParameter("idea"));
			// 判断idea的有效性
			if (idea < 0) {
				idea = 1;
			} else if (idea > 4) {
				idea = 4;
			}
		} else {
			// 默认赋值
			idea = 1;
		}

		// 判断是哪行或者哪列
		if (null != request.getParameter("row")
				&& !"".equals(request.getParameter("row"))) {
			// 按列绘制标识符
			// 所选列
			if ("all".equals(request.getParameter("row"))) {
				// 选择所有数据，所有数据进行统计
				choose = -1;
			} else {
				choose = pi.parseInt(request.getParameter("row"));
			}
		} else {
			// 其他情况绘制全部
			choose = -1;
		}

		// 判断是否需要标签
		if (null != request.getParameter("lable")
				&& !"".equals(request.getParameter("lable"))) {
			// 从页面取值并强制转换
			lable = pb.parseBoolean(request.getParameter("lable"));
		} else {
			// 默认赋值
			lable = false;
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
			value = trans.getValue();
		}

		/***********************************************************************
		 * 从上页面取数据并处理结束
		 **********************************************************************/

		// 判断
		if (value.size() != barTitle.length) {
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
			if (choose >= 0) {
				// 大于0，代表某组数据的图，用改数据的ingc当图片标题
				chart = new XYBar(session,// 固定格式传入session ******必须传入******
						xScale[choose],// 图片标题
						barTitle,// 各个线段的名称
						xTitle, // X轴的名称
						yTitle, // Y轴的名称
						xScale, // x轴刻度
						value, // X、Y轴对应的值
						width, // 宽度
						height,// 高度
						idea,// 1为竖值平面 2为竖值3D 3为横向平面 4为横向3D
						choose,// 选择要单独绘制的数组
						lable,// 是否有标签
						other
				);
			} else {
				// 小于0，按整体绘制
				chart = new XYBar(session,// 固定格式传入session ******必须传入******
						chartTitle,// 图片标题
						barTitle,// 各个线段的名称
						xTitle, // X轴的名称
						yTitle, // Y轴的名称
						xScale, // x轴刻度
						value, // X、Y轴对应的值
						width, // 宽度
						height,// 高度
						idea,// 1为竖值平面 2为竖值3D 3为横向平面 4为横向3D
						choose,// 选择要单独绘制的数组
						lable,// 是否有标签
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
