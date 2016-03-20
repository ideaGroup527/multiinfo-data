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
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.servlet.ServletUtilities;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.Dataset;
import org.plot.chart.Chart;
import org.plot.pojo.CommonData;
import org.plot.pojo.OtherKindChartFont;
import org.plot.util.BackGround;

public class XYLine implements Chart {

	// 公用数据包装类
	public CommonData comm = null;
	// 文件的名称及后缀
	public String fileName = "";

	// 线的标题
	public String LineTitle[];

	public String[] xScale;

	// 对应的值
	public List value;

	boolean orient = false;
	OtherKindChartFont other ;

	// 有参数构造函数 只有年的情况使用
	public XYLine(HttpSession session,// 固定格式需要session进行图片生成
			String chartTitle,// 图片的名称
			String[] LineTitle,// 线条的名称
			String xTitle,// X轴的名称
			String yTitle,// Y轴的名称
			List value,// X、Y轴所对应的值
			int width,// 宽度
			int height, // 高度
			String[] xScale,// 年-2维
			boolean orient,
			OtherKindChartFont other 

	) {

		// 数据传入
		// session的连接使用，将JSP中的session传到类里面
		comm = new CommonData(session, chartTitle, width, height, xTitle,
				yTitle);

		this.orient = orient;
		this.xScale = xScale;
		this.value = value;
		this.LineTitle = LineTitle;
		this.other = other ;

		// 创建一个图像
		CategoryDataset dataset = (CategoryDataset) createDataset();
		JFreeChart chart = createChart(dataset);

		// 获得图表对象的引用，用于设置更多的自定义绘制属性
		CategoryPlot plot = (CategoryPlot) chart.getPlot();
		LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot
				.getRenderer();
		// 图表背景色
		BackGround bg = new BackGround(plot, renderer, chart, height,
				LineTitle.length,other);
		if (orient) {
			plot.setOrientation(PlotOrientation.HORIZONTAL);
		} else {
			plot.setOrientation(PlotOrientation.VERTICAL);
		}
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

		double value1 = 0;
		// 判断并赋值
		for (int i = 0; i < value.size(); i++) {
			// 内层集合
			List valueSecond = (List) value.get(i);
			for (int j = 0; j < valueSecond.size(); j++) {
				value1 = (Double) valueSecond.get(j);
				dataset.addValue(value1, LineTitle[i], xScale[j]);
			}
		}
		return dataset;
	}

	public JFreeChart createChart(Dataset temp) {
		CategoryDataset dataset = (CategoryDataset) temp;
		// 创建图表对象
		JFreeChart chart = ChartFactory.createLineChart(comm.getChartTitle(), // 图表标题
				comm.getXTitle(), // 坐标标题
				comm.getYTitle(), // 坐标标题
				dataset, // 定义绘制数据
				PlotOrientation.VERTICAL, // 竖直方向
				true, // 定义图表是否包含图例
				true, // 定义图表是否包含提示
				true // 定义图表是否包含URL
				);
		return chart;
	}

	public String getFileName() {
		return fileName;
	}
}
