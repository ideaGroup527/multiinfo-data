package com.kxm.cut;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;

/**
 * 本类用于生成图表对象JFreeChart
 * @author kexinmei
 * @author kexinmeip@126.com
 * @version 1.0
 *  */
public class CreatChart {
	/**
	 * 函数用于生成折线图表对象JFreeChart
	 * @param 参数为一个包含各分割段离差平方总和的List
	 * @return JFreeChart
	 * */
	public JFreeChart createChart(List pResult) {
		JFreeChart chart = null;
		ChartUtil chartutil = new ChartUtil();
		CategoryDataset data = chartutil.createDataSet(pResult);
		chart = ChartFactory.createLineChart("离差平方总和变化折线图", // 图表标题
				"分割段数", // X轴标题
				"离差平方总和", // Y轴标题
				data, // 绘图数据集
				PlotOrientation.VERTICAL, // 绘制方向
				true, // 是否显示图例
				true, // 是否采用标准生成器
				false // 是否生成超链接
				);
		// 设置标题字体
		chart.getTitle().setFont(new Font("隶书", Font.BOLD, 23)); // 设置图例类别字体
		//chart.getLegend().setItemFont(new Font("宋体", Font.BOLD, 15));
		chart.setBackgroundPaint(new Color(40, 90, 210)); // 设置背景色
		// 获取绘图区对象
		CategoryPlot plot = chart.getCategoryPlot();
		plot.getDomainAxis().setLabelFont(new Font("宋体", Font.BOLD, 15)); // 设置横轴字体
		plot.getDomainAxis().setTickLabelFont(new Font("宋体", Font.BOLD, 15));// 设置坐标轴标尺值字体
		plot.getRangeAxis().setLabelFont(new Font("宋体", Font.BOLD, 15)); // 设置纵轴字体
		plot.setBackgroundPaint(Color.WHITE); // 设置绘图区背景色
		plot.setRangeGridlinePaint(Color.RED); // 设置水平方向背景线颜色
		plot.setRangeGridlinesVisible(true); // 设置是否显示水平方向背景线,默认值为true
		plot.setDomainGridlinePaint(Color.RED); // 设置垂直方向背景线颜色
		plot.setDomainGridlinesVisible(true); // 设置是否显示垂直方向背景线,默认值为false
		// 获取折线对象
		LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot
				.getRenderer();
		float dashes[] = { 8.0f }; // 定义虚线数组
		BasicStroke brokenLine = new BasicStroke(1.6f, // 线条粗细
				BasicStroke.CAP_SQUARE, // 端点风格
				BasicStroke.JOIN_MITER, // 折点风格
				8.f, // 折点处理办法
				dashes, // 虚线数组
				0.0f); // 虚线偏移量
		renderer.setSeriesStroke(1, brokenLine); // 利用虚线绘制
		return chart;
	}
}
