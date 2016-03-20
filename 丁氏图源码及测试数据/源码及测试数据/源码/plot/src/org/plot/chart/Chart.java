package org.plot.chart;

import org.jfree.chart.JFreeChart;
import org.jfree.data.general.Dataset;

public interface Chart {
	// 创建数据集
	public abstract Dataset createDataset();
	public abstract JFreeChart createChart(Dataset temp);
	public abstract String getFileName() ;
}