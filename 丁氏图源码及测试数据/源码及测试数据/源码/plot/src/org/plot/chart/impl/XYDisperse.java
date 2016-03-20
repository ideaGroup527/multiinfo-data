package org.plot.chart.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardLegend;
import org.jfree.chart.entity.StandardEntityCollection;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StandardXYItemRenderer;
import org.jfree.chart.servlet.ServletUtilities;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.Dataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.TextAnchor;
import org.plot.chart.Chart;
import org.plot.pojo.CommonData;
import org.plot.pojo.OtherKindChartFont;
import org.plot.util.BackGround;

public class XYDisperse implements Chart {

	// 公用数据包装类
	public CommonData comm = null;

	// 文件的名称及后缀
	public String fileName = "";

	// 线的标题
	public String disperseTitle[];

	public int[] xScale;

	// 对应的值
	public List value;

	boolean orient = false;
	OtherKindChartFont other ;

	// 有参数构造函数 只有年的情况使用
	public XYDisperse(HttpSession session,// 固定格式需要session进行图片生成
			String chartTitle,// 图片的名称
			String[] disperseTitle,// 线条的名称
			String xTitle,// X轴的名称
			String yTitle,// Y轴的名称
			List value,// X、Y轴所对应的值
			int width,// 宽度
			int height, // 高度
			int[] xScale,// 年-2维
			boolean orient,
			OtherKindChartFont other 

	) {

		// 数据传入
		// session的连接使用，将JSP中的session传到类里面
		comm = new CommonData(session, chartTitle, width, height, xTitle,
				yTitle);

		this.xScale = xScale;

		this.value = value;

		this.disperseTitle = disperseTitle;

		this.orient = orient;

		this.other = other ;
		
		// 创建一个图像
		XYDataset dataset = (XYDataset) createDataset();
		JFreeChart chart = createChart(dataset);

		// 背景 获得图表对象的引用，用于设置更多的自定义绘制属性 自定义图例的显示风格
		XYPlot plot = chart.getXYPlot();

		StandardXYItemRenderer renderer = (StandardXYItemRenderer) plot
				.getRenderer();
//		// 标签形状
//		StandardLegend sl = (StandardLegend) chart.getLegend();
//		sl.setDisplaySeriesShapes(true);
//		sl.setShapeScaleX(2.5);
//		sl.setShapeScaleY(2.5);
//		sl.setDisplaySeriesLines(true);
//
//		// plot.setQuadrantPaint(0, Color.red) ;
//		// plot.setQuadrantPaint(1, Color.black) ;
//		// plot.setQuadrantPaint(2, Color.blue) ;
//		// plot.setQuadrantPaint(3, Color.green) ;
//
//		// renderer.setLabelGenerator(new XYLabelGenerator()) ;
//		renderer.setBaseItemLabelsVisible(true);
//
//		ItemLabelPosition p = new ItemLabelPosition(ItemLabelAnchor.INSIDE1,
//				TextAnchor.CENTER_LEFT, TextAnchor.CENTER_LEFT, -Math.PI / 2.0);
//		renderer.setBasePositiveItemLabelPosition(p);
//		// BackGround bg = new BackGround(plot, renderer, chart, height,
//		// disperseTitle.length);
		
		// 图表背景色
		BackGround bg = new BackGround(plot, renderer, chart, height,
				disperseTitle.length,other);

		if (orient) {
			plot.setOrientation(PlotOrientation.HORIZONTAL);
		} else {
			plot.setOrientation(PlotOrientation.VERTICAL);
		}

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
		XYSeriesCollection dataset = new XYSeriesCollection();
		XYSeries ts[] = new XYSeries[disperseTitle.length];

		double xvalue = 0;
		double yvalue = 0;
		// 判断并赋值
		for (int i = 0; i < disperseTitle.length; i++) {
			ts[i] = new XYSeries(disperseTitle[i]);
			List valueTemp = (List) value.get(i);
			for (int j = 0; j < valueTemp.size(); j++) {
				xvalue = xScale[j];
				yvalue = (Double) valueTemp.get(j);
				ts[i].add(xvalue, yvalue);

			}
			dataset.addSeries(ts[i]);
		}
		return dataset;
	}

	public JFreeChart createChart(Dataset temp) {
		XYDataset dataset = (XYDataset) temp;
		// 创建图表对象
		JFreeChart chart = ChartFactory.createScatterPlot(comm.getChartTitle(), // 图表标题
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