package org.plot.chart;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.Plot;

public interface MulChart {

	// �������ݼ�
	public abstract JFreeChart createChart(Plot temp);
	
	public abstract String getFileName() ;

}