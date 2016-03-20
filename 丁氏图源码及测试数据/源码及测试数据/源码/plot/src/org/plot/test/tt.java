package org.plot.test ;

import java.awt.BasicStroke;
import java.awt.Dimension;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.Month;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class tt extends ApplicationFrame {

    public tt(String s) {
    super(s);
    JFreeChart jfreechart = createChart();
    ChartPanel chartpanel = new ChartPanel(jfreechart);
    chartpanel.setPreferredSize(new Dimension(600, 350));
    setContentPane(chartpanel);
    }

    private static JFreeChart createChart() {
    XYDataset defaultcategorydataset1 = createDataset();

    JFreeChart chart = ChartFactory.createTimeSeriesChart(
        "Legal & General Unit Trust Prices", // title
        "Date", // x-axis label
        "Price Per Unit", // y-axis label
        defaultcategorydataset1, // data
        true, // create legend?
        true, // generate tooltips?
        false // generate URLs?
        );
    XYPlot categoryplot = chart.getXYPlot();

    XYItemRenderer lineandshaperenderer = new XYLineAndShapeRenderer();
    categoryplot.setRenderer(0, lineandshaperenderer);

    categoryplot.setRangeGridlinesVisible(true);
    categoryplot.setDomainAxis(new DateAxis("Category"));
    categoryplot.setRangeAxis(new NumberAxis("Value"));
    categoryplot.setOrientation(PlotOrientation.VERTICAL);

    XYItemRenderer lineandshaperenderer1 = new XYLineAndShapeRenderer();
    lineandshaperenderer1.setBaseItemLabelsVisible(true);

    lineandshaperenderer1.setSeriesStroke(0, new BasicStroke(2.0F, 1, 1,
        1.0F, new float[] { 6F, 6F }, 0.0F));
    lineandshaperenderer1.setBaseItemLabelsVisible(true);

//    lineandshaperenderer1
//        .setBaseItemLabelGenerator(new StandardXYItemLabelGenerator());

    categoryplot.setRenderer(1, lineandshaperenderer1);
    return new JFreeChart(categoryplot);
    }

    private static XYDataset createDataset() {
    TimeSeries s1 = new TimeSeries("L&G European Index Trust", Month.class);

    s1.add(new Month(6, 2001), 158.8);
    s1.add(new Month(7, 2001), 148.3);
    s1.add(new Month(8, 2001), 153.9);

    TimeSeries s2 = new TimeSeries("L&G UK Index Trust", Month.class);
    s2.add(new Month(8, 2001), 153.9);
    s2.add(new Month(9, 2001), 112.7);
    s2.add(new Month(10, 2001), 101.5);
    s2.add(new Month(11, 2001), 106.1);

    TimeSeriesCollection dataset = new TimeSeriesCollection();
    dataset.addSeries(s1);
    dataset.addSeries(s2);
    dataset.setDomainIsPointsInTime(true);
    return dataset;
    }

    public static void main(String args[]) {
    tt linechartdemo5 = new tt(
        "Line Chart Demo 5");
    linechartdemo5.pack();
    RefineryUtilities.centerFrameOnScreen(linechartdemo5);
    linechartdemo5.setVisible(true);
    }
}

