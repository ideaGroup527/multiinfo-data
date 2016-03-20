package org.plot.chart.impl;

import java.awt.Font;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.entity.StandardEntityCollection;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.CombinedDomainCategoryPlot;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.chart.servlet.ServletUtilities;
import org.plot.chart.MulChart;
import org.plot.parse.ParseValue;
import org.plot.pojo.CommonData;
import org.plot.util.CreateDataset;

public class MulBar implements MulChart {

	// 公用数据包装类
	public CommonData comm = null;
	// 文件的名称及后缀
	public String fileName = "";

	String[] BarTitle;

	String[] scale;

	List value;

	int idea;

	int choose = -1;

	boolean lable = false;

	public MulBar(HttpSession session,// 固定格式需要session进行图片生成
			String chartTitle,// 图片的名称
			String[] BarTitle,// 条的名称
			String xTitle,// X轴的名称
			String yTitle,// Y轴的名称
			String scale[],// 刻度的值
			List value,// X、Y轴所对应的值
			int width,// 宽度
			int height, // 高度
			int idea,// 1为竖值平面 2为竖值3D 3为横向平面 4为横向3D
			int choose,// 选择要单独绘制的数组“i”
			boolean lable// 是否有标签
	) {
		comm = new CommonData(session, chartTitle, width, height, xTitle,
				yTitle);

		this.BarTitle = BarTitle;
		this.scale = scale;
		this.value = value;
		this.idea = idea;
		this.choose = choose;
		this.lable = lable;

		/***********************************************************************
		 * 使用parseValue类将List转换成double类型的数组
		 **********************************************************************/
		ParseValue pv = new ParseValue();// 转换类
		double[][] valueArray = pv.parseArray(value);// 临时变量存储转换维数组后的结果

		// 创建第一个子plot对象, 并设置相关绘制属性
		CreateDataset createData = new CreateDataset();

		// 创建父Plot对象的横轴，用于两个子Plot共享
		CategoryAxis domainAxis = new CategoryAxis(xTitle);

		// 创建父Plot对象
		CombinedDomainCategoryPlot plot = new CombinedDomainCategoryPlot(
				domainAxis);
		// 设置间隔
		plot.setGap(10.0);

		for (int i = 0; i < BarTitle.length; i++) {

			if (idea == 1 || idea == 3) {
				if (idea == 3) {
					plot.setOrientation(PlotOrientation.HORIZONTAL);
				}
				BarRenderer renderer = new BarRenderer();
				NumberAxis rangeAxis = new NumberAxis(yTitle);
				rangeAxis.setStandardTickUnits(NumberAxis
						.createIntegerTickUnits());
				CategoryPlot subplot = new CategoryPlot(createData
						.createDataset(i, valueArray, BarTitle, scale), null,
						rangeAxis, renderer);
				subplot.setDomainGridlinesVisible(true);

				plot.add(subplot, 1);
			} else {
				if (idea == 4) {
					plot.setOrientation(PlotOrientation.HORIZONTAL);
				}
				BarRenderer3D renderer = new BarRenderer3D();
				NumberAxis rangeAxis = new NumberAxis(yTitle);
				rangeAxis.setStandardTickUnits(NumberAxis
						.createIntegerTickUnits());
				CategoryPlot subplot = new CategoryPlot(createData
						.createDataset(i, valueArray, BarTitle, scale), null,
						rangeAxis, renderer);
				subplot.setDomainGridlinesVisible(true);

				plot.add(subplot, 1);

			}

		}

		// 创建图表
		JFreeChart chart = createChart(plot);
		// 结束自定义图表绘制的相关属性
		ChartRenderingInfo info = new ChartRenderingInfo(
				new StandardEntityCollection());
		// 设置图片生成格式PNG
		try {
			fileName = ServletUtilities.saveChartAsPNG(chart, width, height,
					info, session);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// 创建数据集

	public JFreeChart createChart(Plot temp) {
		CombinedDomainCategoryPlot plot = (CombinedDomainCategoryPlot) temp;
		// 调用JFreeChart构造器，创建图表对象
		JFreeChart chart = new JFreeChart(comm.getChartTitle(), // 图表标题
				new Font("汉真广标", Font.BOLD, 15), // 图表标题字体
				plot, // 图表绘制对象
				true // 是否包含图例
		);
		return chart;
	}

	public String getFileName() {
		return fileName;
	}
}
