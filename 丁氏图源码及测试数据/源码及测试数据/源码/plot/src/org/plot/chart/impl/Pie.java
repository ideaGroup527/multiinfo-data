package org.plot.chart.impl;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.entity.StandardEntityCollection;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.servlet.ServletUtilities;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.general.Dataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.plot.chart.Chart;
import org.plot.pojo.CommonData;
import org.plot.pojo.OtherKindChartFont;
import org.plot.util.BackGround;

public class Pie implements Chart {

	// 图表类型
	boolean idea;
	// Pie的每个部分的名称
	String[] pieTitle;
	// X轴对应的刻度
	String[] xScale;

	// 图表的名称
	String chartTitle;

	// 每个部分所对应的数值
	private List value;
	// 文件的名称及后缀
	public String fileName = "";
	// 公用数据包装类
	public CommonData comm = null;

	int choose = -1;
	
	OtherKindChartFont other ;

	public Pie() {
	}

	// 按行绘制
	public Pie(HttpSession session,// 固定格式需要session进行图片生成
			String chartTitle,// 图片的名称
			String[] pieTitle,// Pie的每个部分的名称
			List value,// X、Y轴所对应的值
			int width,// 宽度
			int height, // 高度
			boolean idea,// idea为true时候为平面图，当false时候为3D图
			int choose, // 选择第几行
			OtherKindChartFont other 
	) {

		comm = new CommonData(session, chartTitle, width, height);
		this.idea = idea;
		this.pieTitle = pieTitle;
		this.value = value;
		this.choose = choose;
		this.other = other ;

		PieDataset dataset = (PieDataset) createDataset();
		JFreeChart chart = createChart(dataset);

		// 开始自定义图表绘制的相关属性
		// 自定义图表的标题的字体和颜色
		TextTitle title = chart.getTitle();
		title.setFont(new Font("仿宋", Font.BOLD, 25));
		title.setPaint(Color.YELLOW);

		// 获得图表对象的引用
		PiePlot plot = (PiePlot) chart.getPlot();
		// 设置整个图表的背景颜色
		BackGround bg = new BackGround(plot, chart, height,other);

		// 结束自定义图表绘制的相关属性
		ChartRenderingInfo info = new ChartRenderingInfo(
				new StandardEntityCollection());

		// 设置图片生成格式
		try {
			fileName = ServletUtilities.saveChartAsPNG(chart, width, height,
					info, session);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// 按列绘制
	public Pie(HttpSession session,// 固定格式需要session进行图片生成
			String chartTitle,// 图片的名称
			String[] pieTitle,// Pie的每个部分的名称
			List value,// X、Y轴所对应的值
			String[] xScale,// X轴对应的刻度
			int width,// 宽度
			int height, // 高度
			boolean idea,// idea为true时候为平面图，当false时候为3D图
			int choose, // 第几列
			OtherKindChartFont other 
	) {

		comm = new CommonData(session, chartTitle, width, height);
		this.idea = idea;
		this.pieTitle = pieTitle;
		this.value = value;
		this.xScale = xScale;
		this.choose = choose;
		this.other = other ;

		PieDataset dataset = (PieDataset) createDataset();
		JFreeChart chart = createChart(dataset);

		// 开始自定义图表绘制的相关属性
		// 自定义图表的标题的字体和颜色
		TextTitle title = chart.getTitle();
		title.setFont(new Font("仿宋", Font.BOLD, 25));
		title.setPaint(Color.YELLOW);

		// 获得图表对象的引用
		PiePlot plot = (PiePlot) chart.getPlot();
		// 设置整个图表的背景颜色
		BackGround bg = new BackGround(plot, chart, height,other);

		// 结束自定义图表绘制的相关属性
		ChartRenderingInfo info = new ChartRenderingInfo(
				new StandardEntityCollection());

		// 设置图片生成格式
		try {
			fileName = ServletUtilities.saveChartAsPNG(chart, width, height,
					info, session);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// 创建数据集
	public Dataset createDataset() {
		DefaultPieDataset dataset = new DefaultPieDataset();
		//若x轴刻度xScale为空则表示按行
		if (null == xScale) {
			// 选择某几行
			// 此处choose代表选择那一行
			if (choose < 0) {
				// 整体绘图
				for (int i = 0; i < pieTitle.length; i++) {
					Double tempvalue = 0.0;
					List valueInner = (List) value.get(i);
					for (int j = 0; j < valueInner.size(); j++) {
						tempvalue += (Double) valueInner.get(j);
					}
					dataset.setValue(pieTitle[i], tempvalue);
				}
			} else if (choose >= ((List) value.get(0)).size()) {
				// !!!!!!!!!!be carefull!!!!!!!!!!!
				// 取最后一组数绘制
				List valueInner = null;
				for (int i = 0; i < pieTitle.length; i++) {
					valueInner = (List) value.get(i);
					double tempValue = (Double) valueInner.get(pieTitle.length);
					dataset.setValue(pieTitle[i], tempValue);
				}
			} else {
				// 按行来绘制，水平拿出一组数据
				List valueInner = null;
				for (int i = 0; i < pieTitle.length; i++) {
					valueInner = (List) value.get(i);
					double tempValue = (Double) valueInner.get(choose);
					dataset.setValue(pieTitle[i], tempValue);
				}

			}
		} else {
			// 选择某一列的数据来绘制
			// 此处choose是代表选择那一列
			if (choose < 0) {
				choose = 0;
			} else if (choose > pieTitle.length) {
				choose = pieTitle.length;
			}
			List valueInner = (List) value.get(choose);
			for (int i = 0; i < valueInner.size(); i++) {
				double tempValue = (Double) valueInner.get(i);
				dataset.setValue(xScale[i], tempValue);
			}

		}
		return dataset;
	}

	// 用数据制图
	public JFreeChart createChart(Dataset temp) {

		PieDataset dataset = (PieDataset) temp;

		if (idea) {
			JFreeChart chart = ChartFactory.createPieChart(
					comm.getChartTitle(), // 图表标题
					dataset, // 数据集
					true, // 定义图例
					true, false);
			return chart;
		} else {
			JFreeChart chart = ChartFactory.createPieChart3D(comm
					.getChartTitle(), // 图表标题
					dataset, // 数据集
					true, // 定义图例
					true, false);
			return chart;
		}

	}

	// 访问路径
	public String getFileName() {

		return fileName;
	}

}
