package org.plot.util;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import org.jfree.chart.renderer.category.*;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.Legend;
import org.jfree.chart.StandardLegend;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryLabelGenerator;
import org.jfree.chart.plot.*;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.ui.TextAnchor;
import org.jfree.chart.renderer.xy.*;
import org.jfree.chart.title.TextTitle;
import org.plot.parse.ParseColor;
import org.plot.pojo.OtherKindChartFont;

public class BackGround {

	/*
	 * Disperse.java XYDisperse.java LineChart.java XYLineChart.java 的背景
	 * 
	 */

	public BackGround(XYPlot plot, StandardXYItemRenderer renderer,
			JFreeChart chart, int height, int length,
			OtherKindChartFont other) {
		// 颜色转换器
		ParseColor pc = new ParseColor();
		
		// 背景色设置
		BackColor bc = new BackColor();
		// 整体部分
		GradientPaint bgGP = bc.totalBack(height, other.getUpBackColor(), other
				.getDownBackColor());
		chart.setBackgroundPaint(bgGP);
		// 图表内容部分
		GradientPaint bg = bc.chartBack();
		plot.setBackgroundPaint(bg);
		// 虚线颜色
		plot.setDomainGridlinePaint(Color.RED);
		plot.setDomainGridlinesVisible(true);
		plot.setRangeGridlinePaint(Color.RED);

		// 设置字体颜色大小
		Font xlabelfont = new Font("宋体", Font.PLAIN, 12);// x轴标题字体
		Font xtickfont = new Font(other.getXFont(), Font.PLAIN, other.getXSize());// x轴刻度字体
		Font ylabelfont = new Font("宋体", Font.PLAIN, 12);// Y轴标题字体
		Font ytickfont = new Font(other.getYFont(), Font.PLAIN, other.getYSize());// Y轴刻度字体
		Font titleFont = new Font("宋体", Font.PLAIN, 25); // 图片标题
		Color xColor = pc.parseColor(other.getXColor());
		Color yColor = pc.parseColor(other.getYColor());
		Color titleColor = pc.parseColor(other.getTitleColor());
		// X轴
		plot.getDomainAxis().setLabelFont(xlabelfont); // x轴标题字体
		plot.getDomainAxis().setTickLabelFont(xtickfont); // x轴刻度字体
		plot.getDomainAxis().setTickLabelPaint(xColor);
		// Y轴
		plot.getRangeAxis().setLabelFont(ylabelfont); // y轴外围字体
		plot.getRangeAxis().setTickLabelFont(ytickfont); // y轴标题字体
		plot.getRangeAxis().setTickLabelPaint(yColor);

		// 标题
		chart.getTitle().setFont(titleFont);
		chart.getTitle().setPaint(titleColor);

		// 标签形状
		StandardLegend sl = (StandardLegend) chart.getLegend();
		sl.setDisplaySeriesShapes(true);
		sl.setShapeScaleX(2.5);
		sl.setShapeScaleY(2.5);
		sl.setDisplaySeriesLines(true);

		// 颜色设置
		renderer.setPlotShapes(true);
		renderer.setSeriesShapesFilled(0, Boolean.TRUE);
		renderer.setSeriesShapesFilled(1, Boolean.FALSE);

		// 自定义线段的绘制颜色
		Color color[] = new Color[length];
		color[0] = new Color(99, 99, 0);
		color[1] = new Color(55, 19, 6);
		color[2] = new Color(13, 255, 66);
		color[3] = new Color(33, 0, 255);
		color[4] = new Color(25, 0, 66);
		for (int i = 0; i < color.length; i++) {
			renderer.setSeriesPaint(i, color[i]);
		}

		// 自定义线段的绘制风格
		BasicStroke bs;
		for (int i = 0; i < length; i++) {
			float dashes[] = { 10.0f };
			bs = new BasicStroke(1.0f, BasicStroke.CAP_ROUND,
					BasicStroke.JOIN_ROUND, 1.0f, dashes, 1.0f);
			if (i % 2 != 0)
				renderer.setSeriesStroke(i, bs);
			else
				renderer.setSeriesStroke(i, new BasicStroke(1.0f));
		}

	}

	// Bar.java 的背景
	public BackGround(XYPlot plot, JFreeChart chart, int height,
			OtherKindChartFont other) {
		// 颜色转换器
		ParseColor pc = new ParseColor();
		// 背景色设置
		BackColor bc = new BackColor();
		// 整体部分
		GradientPaint bgGP = bc.totalBack(height, other.getUpBackColor(), other
				.getDownBackColor());
		chart.setBackgroundPaint(bgGP);
		// 图表内容部分
		GradientPaint bg = bc.chartBack();
		plot.setBackgroundPaint(bg);
		// 虚线颜色
		plot.setDomainGridlinePaint(Color.BLACK);
		plot.setDomainGridlinesVisible(true);
		plot.setRangeGridlinePaint(Color.RED);

		// 设置字体颜色大小
		Font xlabelfont = new Font("宋体", Font.PLAIN, 12);// x轴标题字体
		Font xtickfont = new Font(other.getXFont(), Font.PLAIN, other.getXSize());// x轴刻度字体
		Font ylabelfont = new Font("宋体", Font.PLAIN, 12);// Y轴标题字体
		Font ytickfont = new Font(other.getYFont(), Font.PLAIN, other.getYSize());// Y轴刻度字体
		Font titleFont = new Font("宋体", Font.PLAIN, 25); // 图片标题
		Color xColor = pc.parseColor(other.getXColor());
		Color yColor = pc.parseColor(other.getYColor());
		Color titleColor = pc.parseColor(other.getTitleColor());
		// X轴
		plot.getDomainAxis().setLabelFont(xlabelfont); // x轴标题字体
		plot.getDomainAxis().setTickLabelFont(xtickfont); // x轴刻度字体
		plot.getDomainAxis().setTickLabelPaint(xColor);
		// Y轴
		plot.getRangeAxis().setLabelFont(ylabelfont); // y轴外围字体
		plot.getRangeAxis().setTickLabelFont(ytickfont); // y轴标题字体
		plot.getRangeAxis().setTickLabelPaint(yColor);

		// 标题
		chart.getTitle().setFont(titleFont);
		chart.getTitle().setPaint(titleColor);

		// 标签形状
		StandardLegend sl = (StandardLegend) chart.getLegend();
		sl.setDisplaySeriesShapes(true);
		sl.setShapeScaleX(2.5);
		sl.setShapeScaleY(2.5);
		sl.setDisplaySeriesLines(true);

	}

	// XYBar背景设计
	public BackGround(CategoryPlot plot, BarRenderer renderer,
			JFreeChart chart,// 实例
			int height,// 图表的高度
			boolean lable,// 是否有标签
			OtherKindChartFont other)
	{
		// 颜色转换器
		ParseColor pc = new ParseColor();
		// 背景色设置
		BackColor bc = new BackColor();
		// 整体部分
		GradientPaint bgGP = bc.totalBack(height, other.getUpBackColor(), other
				.getDownBackColor());
		chart.setBackgroundPaint(bgGP);
		// 图表内容部分
		GradientPaint bg = bc.chartBack();
		plot.setBackgroundPaint(bg);
		// 虚线颜色
		plot.setDomainGridlinePaint(Color.BLACK);
		plot.setDomainGridlinesVisible(true);
		plot.setRangeGridlinePaint(Color.RED);

		// 设置字体颜色大小
		Font xlabelfont = new Font("宋体", Font.PLAIN, 12);// x轴标题字体
		Font xtickfont = new Font(other.getXFont(), Font.PLAIN, other.getXSize());// x轴刻度字体
		Font ylabelfont = new Font("宋体", Font.PLAIN, 12);// Y轴标题字体
		Font ytickfont = new Font(other.getYFont(), Font.PLAIN, other.getYSize());// Y轴刻度字体
		Font titleFont = new Font("宋体", Font.PLAIN, 25); // 图片标题
		Color xColor = pc.parseColor(other.getXColor());
		Color yColor = pc.parseColor(other.getYColor());
		Color titleColor = pc.parseColor(other.getTitleColor());
		// X轴
		plot.getDomainAxis().setLabelFont(xlabelfont); // x轴标题字体
		plot.getDomainAxis().setTickLabelFont(xtickfont); // x轴刻度字体
		plot.getDomainAxis().setTickLabelPaint(xColor);
		// Y轴
		plot.getRangeAxis().setLabelFont(ylabelfont); // y轴外围字体
		plot.getRangeAxis().setTickLabelFont(ytickfont); // y轴标题字体
		plot.getRangeAxis().setTickLabelPaint(yColor);

		// 标题
		chart.getTitle().setFont(titleFont);
		chart.getTitle().setPaint(titleColor);

		// 标签形状
		StandardLegend sl = (StandardLegend) chart.getLegend();
		sl.setDisplaySeriesShapes(true);
		sl.setShapeScaleX(2.5);
		sl.setShapeScaleY(2.5);
		sl.setDisplaySeriesLines(true);

		// 定义是否绘制轮廓线
		renderer.setDrawBarOutline(true);

		// 定义标签
		renderer.setLabelGenerator(new StandardCategoryLabelGenerator());
		renderer.setItemLabelsVisible(lable);
		ItemLabelPosition p = new ItemLabelPosition(ItemLabelAnchor.CENTER,
				TextAnchor.CENTER_LEFT, TextAnchor.CENTER_LEFT, -Math.PI / 2.0);

		renderer.setPositiveItemLabelPositionFallback(p);

		// 设置横轴标题文字的旋转方向
		CategoryAxis domainAxis = plot.getDomainAxis();
		domainAxis.setCategoryLabelPositions(CategoryLabelPositions.STANDARD);

		// 设置图例的显示位置
		Legend legend = chart.getLegend();
		legend.setAnchor(Legend.SOUTH);

	}

	// XLineChart的背景
	public BackGround(CategoryPlot plot, LineAndShapeRenderer renderer,
			JFreeChart chart, int height, int linelength,
			OtherKindChartFont other) {
		// 颜色转换器
		ParseColor pc = new ParseColor();
		// 背景色设置
		BackColor bc = new BackColor();
		// 整体部分
		GradientPaint bgGP = bc.totalBack(height, other.getUpBackColor(), other
				.getDownBackColor());
		chart.setBackgroundPaint(bgGP);
		// 图表内容部分
		GradientPaint bg = bc.chartBack();
		plot.setBackgroundPaint(bg);

		// 虚线颜色
		plot.setDomainGridlinePaint(Color.RED);
		plot.setDomainGridlinesVisible(true);
		plot.setRangeGridlinePaint(Color.RED);

		// 设置字体颜色大小
		Font xlabelfont = new Font("宋体", Font.PLAIN, 12);// x轴标题字体
		Font xtickfont = new Font(other.getXFont(), Font.PLAIN, other.getXSize());// x轴刻度字体
		Font ylabelfont = new Font("宋体", Font.PLAIN, 12);// Y轴标题字体
		Font ytickfont = new Font(other.getYFont(), Font.PLAIN, other.getYSize());// Y轴刻度字体
		Font titleFont = new Font("宋体", Font.PLAIN, 25); // 图片标题
		Color xColor = pc.parseColor(other.getXColor());
		Color yColor = pc.parseColor(other.getYColor());
		Color titleColor = pc.parseColor(other.getTitleColor());
		// X轴
		plot.getDomainAxis().setLabelFont(xlabelfont); // x轴标题字体
		plot.getDomainAxis().setTickLabelFont(xtickfont); // x轴刻度字体
		plot.getDomainAxis().setTickLabelPaint(xColor);
		// Y轴
		plot.getRangeAxis().setLabelFont(ylabelfont); // y轴外围字体
		plot.getRangeAxis().setTickLabelFont(ytickfont); // y轴标题字体
		plot.getRangeAxis().setTickLabelPaint(yColor);

		// 标题
		chart.getTitle().setFont(titleFont);
		chart.getTitle().setPaint(titleColor);

		// 标签形状
		StandardLegend sl = (StandardLegend) chart.getLegend();
		sl.setDisplaySeriesShapes(true);
		sl.setShapeScaleX(2.5);
		sl.setShapeScaleY(2.5);
		sl.setDisplaySeriesLines(true);

		// 设置横轴标题文字的旋转方向
		CategoryAxis domainAxis = plot.getDomainAxis();
		domainAxis.setCategoryLabelPositions(CategoryLabelPositions.STANDARD);

		// 设置图例的显示位置
		Legend legend = chart.getLegend();
		legend.setAnchor(Legend.SOUTH);

		// 设置横轴标题的字体
		// CategoryAxis domainAxis = plot.getDomainAxis();
		// domainAxis.setLabelFont(new Font("汉真广标", Font.BOLD, 15));

		// 设置纵轴标题文字的字体及其旋转方向
		// ValueAxis rangeAxis = plot.getRangeAxis();
		// rangeAxis.setLabelFont(new Font("汉真广标", Font.BOLD, 15));
		// rangeAxis.setLabelAngle(Math.PI/2);

		// 设置线条上有小图标************************
		renderer.setDrawShapes(true);

		// 自定义线段的绘制颜色
		Color color[] = new Color[linelength];
		color[0] = new Color(99, 99, 0);
		color[1] = new Color(255, 169, 66);
		color[2] = new Color(33, 255, 66);
		color[3] = new Color(33, 0, 255);
		color[4] = new Color(25, 0, 66);
		for (int i = 0; i < color.length; i++) {
			renderer.setSeriesPaint(i, color[i]);
		}

		// 自定义线段的绘制风格
		BasicStroke bs;
		for (int i = 0; i < linelength; i++) {
			float dashes[] = { 10.0f };
			bs = new BasicStroke(1.0f, BasicStroke.CAP_ROUND,
					BasicStroke.JOIN_ROUND, 1.0f, dashes, 1.0f);
			if (i % 2 != 0)
				renderer.setSeriesStroke(i, bs);
			else
				renderer.setSeriesStroke(i, new BasicStroke(1.0f));
		}

	}

	public BackGround(PiePlot plot, JFreeChart chart, int height,
			OtherKindChartFont other) {
		// 颜色转换器
		ParseColor pc = new ParseColor();

		// 背景色设置
		BackColor bc = new BackColor();
		// 整体部分
		GradientPaint totalBG = bc.totalBack(height, other.getUpBackColor(),
				other.getDownBackColor());
		chart.setBackgroundPaint(totalBG);
		// 图表内容部分
		GradientPaint chartBG = bc.chartBack();
		plot.setBackgroundPaint(chartBG);

		Font titleFont = new Font(other.getTitleFont(), Font.PLAIN, other
				.getTitleSize()); // 图片标题
		chart.getTitle().setFont(titleFont);
		chart.getTitle().setPaint(pc.parseColor(other.getTitleColor()));

		// 设置是否使用反锯齿功能
		chart.setAntiAlias(true);
		// 设置饼图标签的绘制字体
		plot.setLabelFont(new Font(other.getPieLableFont(), Font.PLAIN, other
				.getPieLableSize()));
		plot.setLabelPaint(pc.parseColor(other.getPieLableColor()));
		// 设置饼图的外观为椭圆形还是正圆形
		plot.setCircular(true);

	}

}
