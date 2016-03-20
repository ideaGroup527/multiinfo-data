package org.plot.chart.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.entity.StandardEntityCollection;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.servlet.ServletUtilities;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.Dataset;
import org.plot.chart.Chart;
import org.plot.pojo.CommonData;
import org.plot.pojo.OtherKindChartFont;
import org.plot.util.BackGround;

public class XYBar implements Chart {

	// 文件的名称及后缀
	public String fileName = "";

	// 公用数据包装类
	public CommonData comm = null;

	// 每个柱状图的标题
	String[] BarTitle;

	// X轴刻度名称
	String[] scale;

	List value;

	int idea;

	int choose = -1;

	boolean lable = false;
	
	OtherKindChartFont other  ;

	public XYBar(HttpSession session,// 固定格式需要session进行图片生成
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
			boolean lable,// 是否有标签
			OtherKindChartFont other 
	) {
		comm = new CommonData(session, chartTitle, width, height, xTitle,
				yTitle);

		this.BarTitle = BarTitle;
		this.scale = scale;
		this.value = value;
		this.idea = idea;
		this.choose = choose;
		this.lable = lable;
		this.other = other ;

		CategoryDataset dataset = (CategoryDataset) createDataset();
		JFreeChart chart = createChart(dataset);

		// 设置图表的背景颜色

		// 获得图表对象的引用，用于设置更多的自定义绘制属性
		CategoryPlot plot = chart.getCategoryPlot();
		BarRenderer renderer = (BarRenderer) plot.getRenderer();
		BackGround bg = new BackGround(plot, renderer, chart, height, lable,other);

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
	public Dataset createDataset() {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		if (choose < 0) {
			for (int i = 0; i < value.size(); i++) {
				// 取出第一个LIST
				List firstList = (List) value.get(i);
				for (int j = 0; j < firstList.size(); j++) {
					dataset.setValue((Double) firstList.get(j), BarTitle[i],
							scale[j]);
				}
			}
		} else if (choose >= value.size()) {
			int i = value.size() - 1;
			// 取出第一个LIST
			List firstList = (List) value.get(i);
			for (int j = 0; j < firstList.size(); j++) {
				// dataset.addValue(value[i][j], BarTitle[i], scale[j]);
				dataset.setValue((Double) firstList.get(j), BarTitle[i],
						scale[j]);
			}
		} else {
			int i = choose;
			List firstList = (List) value.get(i);
			for (int j = 0; j < firstList.size(); j++) {
				// dataset.addValue(value[i][j], BarTitle[i], scale[j]);
				dataset.setValue((Double) firstList.get(j), BarTitle[i],
						scale[j]);
			}
		}
		return dataset;
	}

	public JFreeChart createChart(Dataset temp) {

		CategoryDataset dataset = (CategoryDataset) temp;
		// 创建图表对象
		JFreeChart chart;
		switch (idea) {
		case 1:
			chart = ChartFactory.createBarChart(comm.getChartTitle(), // 图表标题
					comm.getXTitle(), // 坐标标题
					comm.getYTitle(), // 坐标标题
					dataset, // 定义绘制数据
					PlotOrientation.VERTICAL, // 直方图的方向 竖直
					true, // 定义图表是否包含图例
					true, // 定义图表是否包含提示
					false // 定义图表是否包含URL
					);
			return chart;

		case 2:
			chart = ChartFactory.createBarChart3D(comm.getChartTitle(), // 图表标题
					comm.getXTitle(), // 坐标标题
					comm.getYTitle(), // 坐标标题
					dataset, // 定义绘制数据
					PlotOrientation.VERTICAL, // 直方图的方向 竖直
					true, // 定义图表是否包含图例
					true, // 定义图表是否包含提示
					false // 定义图表是否包含URL
					);
			return chart;

		case 3:
			chart = ChartFactory.createBarChart(comm.getChartTitle(), // 图表标题
					comm.getXTitle(), // 坐标标题
					comm.getYTitle(), // 坐标标题
					dataset, // 定义绘制数据
					PlotOrientation.HORIZONTAL, // 直方图的方向 横向
					true, // 定义图表是否包含图例
					true, // 定义图表是否包含提示
					false // 定义图表是否包含URL
					);
			return chart;

		default:
			chart = ChartFactory.createBarChart3D(comm.getChartTitle(), // 图表标题
					comm.getXTitle(), // 坐标标题
					comm.getYTitle(), // 坐标标题
					dataset, // 定义绘制数据
					PlotOrientation.HORIZONTAL, // 直方图的方向 横向
					true, // 定义图表是否包含图例
					true, // 定义图表是否包含提示
					false // 定义图表是否包含URL
					);
			return chart;

		}
	}

	public String getFileName() {
		return fileName;
	}

}
