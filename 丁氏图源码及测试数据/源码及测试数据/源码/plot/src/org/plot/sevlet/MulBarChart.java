package org.plot.sevlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.plot.chart.MulChart;
import org.plot.chart.impl.MulBar;
import org.plot.util.Transfer;

public class MulBarChart extends HttpServlet {
	public MulBarChart() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获得图片所在位置的路径
		String graphURL = "";
		// 获得图片的相关信息
		String chartTitle = "";
		String fileName = "";
		Transfer trans = new Transfer();
		String xTitle = trans.getXTitle();
		String yTitle = trans.getYTitle();
		String BarTitle[] = trans.getLineTitle();

		List value = trans.getValue();
		chartTitle = trans.getChartTitle();
		int width = trans.getWidth();
		int height = trans.getHeight();
		String[] scale = trans.getXscale();
		int idea = 1;// 绘图风格

		// 选择绘图的范围
		// 小于0为全体图 0到value.length-1 为部分 大于等于value.length为把choose=value.length
		int choose = 2;
		boolean lable = true;// 3维立体的柱状若是总体的 没有标签 其他都有

		try {
			HttpSession session = request.getSession();
			// 调用线段绘制的类
			MulChart chart = new MulBar(session,// 固定格式传入session******必须传入******
					chartTitle,// 图片标题
					BarTitle,// 各个线段的名称
					xTitle, // X轴的名称
					yTitle, // Y轴的名称
					scale, // X刻度
					value, // X、Y轴对应的值
					width, // 宽度
					1000,// 高度
					idea,// 1为竖值平面 2为竖值3D 3为横向平面 4为横向3D
					choose, lable);

			// 获得图片的名称和后缀
			fileName = chart.getFileName();
			// 获得图片的暂时路径
			graphURL = request.getContextPath()
					+ "/servlet/DisplayChart?filename=" + fileName;
			// 保存图片的访问路径
			request.setAttribute("graphURL", graphURL);
			// 跳转到显示页面
			request.getRequestDispatcher("show.jsp")
					.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
