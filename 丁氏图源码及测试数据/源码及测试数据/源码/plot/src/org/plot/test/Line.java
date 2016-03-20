package org.plot.test;

import java.awt.BasicStroke;
import java.awt.Dimension;
import java.text.NumberFormat;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.Month;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class Line extends ApplicationFrame {

	public Line(String s) {
		super(s);
		JFreeChart jfreechart = createChart();
		ChartPanel chartpanel = new ChartPanel(jfreechart);
		chartpanel.setPreferredSize(new Dimension(600, 350));
		setContentPane(chartpanel);
	}

	private static XYDataset createDataset() {
		TimeSeries s1 = new TimeSeries("家具产量增量", Month.class);

		s1.add(new Month(1, 2001), 200);
		s1.add(new Month(2, 2001), 210);
		s1.add(new Month(3, 2001), 220);
		s1.add(new Month(4, 2001), 230);
		s1.add(new Month(5, 2001), 240);
		s1.add(new Month(6, 2001), 250);
		s1.add(new Month(7, 2001), 260);
		s1.add(new Month(8, 2001), 270);
		s1.add(new Month(9, 2001), 280);
		s1.add(new Month(10, 2001), 290);

		TimeSeries s2 = new TimeSeries("家具产量增量预测", Month.class);
		s2.add(new Month(10, 2001), 290);
		s2.add(new Month(11, 2001), 320);
		s2.add(new Month(12, 2001), 340);

		TimeSeriesCollection dataset = new TimeSeriesCollection();
		dataset.addSeries(s1);
		dataset.addSeries(s2);

		return dataset;
	}

	private static JFreeChart createChart() {
		XYDataset defaultcategorydataset1 = createDataset();

		JFreeChart chart = ChartFactory.createTimeSeriesChart("预测图", "", "", // y-axis
																				// label
				defaultcategorydataset1, // data
				true, true, false);
		XYPlot categoryplot = chart.getXYPlot();
		categoryplot.setRangeGridlinesVisible(true);
		categoryplot.setOrientation(PlotOrientation.VERTICAL);
		categoryplot.getRenderer().setSeriesStroke(0,
				new BasicStroke(2.0F, 1, 1, 1.0F, null, 0.0F));
		categoryplot.getRenderer()
				.setSeriesStroke(
						1,
						new BasicStroke(2.0F, 1, 1, 1.0F,
								new float[] { 6F, 6F }, 0.0F));
//		XYLineAndShapeRenderer lineandshaperenderer = (XYLineAndShapeRenderer) categoryplot
//				.getRenderer();
//		lineandshaperenderer.setBaseShapesVisible(true);

		/** 纵轴设置 */
		NumberAxis numberaxis = (NumberAxis) categoryplot.getRangeAxis();
		numberaxis.setNumberFormatOverride(NumberFormat.getPercentInstance());
		numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		numberaxis.setAutoRangeIncludesZero(true);
		numberaxis.setUpperMargin(0.12D);

		return new JFreeChart(categoryplot);
	}

	public static void main(String args[]) {
		Line linechartdemo5 = new Line("Line Chart Demo 5");
		linechartdemo5.pack();
		RefineryUtilities.centerFrameOnScreen(linechartdemo5);
		linechartdemo5.setVisible(true);
	}
}