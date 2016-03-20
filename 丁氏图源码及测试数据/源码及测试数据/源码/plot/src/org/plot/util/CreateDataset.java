package org.plot.util;

import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.time.Day;
import org.jfree.data.time.Month;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.Year;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class CreateDataset {

	public XYDataset createDataset(String[] LineTitle, int[][] yea,
			double[][] value, int i

	) {
		TimeSeriesCollection dataset = new TimeSeriesCollection();
		TimeSeries ts[] = new TimeSeries[LineTitle.length];
		double value1 = 0;
		int[][] round = yea;

		ts[i] = new TimeSeries(LineTitle[i], Year.class);

		for (int j = 0; j < round[i].length; j++) {
			value1 = value[i][j];
			// 赋值

			ts[i].addOrUpdate(new Year(yea[i][j]), value1);

		}
		dataset.addSeries(ts[i]);

		return dataset;
	}

	public XYDataset createDataset(String[] LineTitle, int[][] yea,
			int[][] mon, double[][] value, int i

	) {
		TimeSeriesCollection dataset = new TimeSeriesCollection();
		TimeSeries ts[] = new TimeSeries[LineTitle.length];
		double value1 = 0;
		int[][] round = mon;

		ts[i] = new TimeSeries(LineTitle[i], Month.class);

		for (int j = 0; j < round[i].length; j++) {
			value1 = value[i][j];
			// 赋值

			ts[i].addOrUpdate(new Month(mon[i][j], yea[i][j]), value1);

		}
		dataset.addSeries(ts[i]);

		return dataset;
	}

	public XYDataset createDataset(String[] LineTitle, int[][] yea,
			int[][] mon, int[][] day, double[][] value, int i

	) {
		TimeSeriesCollection dataset = new TimeSeriesCollection();
		TimeSeries ts[] = new TimeSeries[LineTitle.length];
		double value1 = 0;
		int[][] round = day;

		ts[i] = new TimeSeries(LineTitle[i], Day.class);

		for (int j = 0; j < round[i].length; j++) {
			value1 = value[i][j];
			// 赋值

			ts[i].addOrUpdate(new Day(day[i][j], mon[i][j], yea[i][j]), value1);
		}
		dataset.addSeries(ts[i]);

		return dataset;
	}

	public XYDataset createDataset(String[] LineTitle, double[][] xx,
			double[][] yy, int i) {
		XYSeriesCollection dataset = new XYSeriesCollection();
		XYSeries ts[] = new XYSeries[LineTitle.length];

		double xvalue = 0;
		double yvalue = 0;
		// 判断并赋值

		ts[i] = new XYSeries(LineTitle[i]);
		for (int j = 0; j < xx[i].length; j++) {
			xvalue = xx[i][j];
			yvalue = yy[i][j];
			ts[i].add(xvalue, yvalue);

		}
		dataset.addSeries(ts[i]);

		return dataset;
	}

	public CategoryDataset createDataset(int choose, double[][] value,
			String[] BarTitle, String[] scale) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		if (choose < 0) {
			for (int i = 0; i < value.length; i++) {
				for (int j = 0; j < value[i].length; j++) {
					dataset.setValue(value[i][j], BarTitle[i], scale[j]);

				}
			}
		} else if (choose >= value.length) {
			int i = value.length - 1;
			for (int j = 0; j < value[i].length; j++) {
				// dataset.addValue(value[i][j], BarTitle[i], scale[j]);
				dataset.setValue(value[i][j], BarTitle[i], scale[j]);

			}

		} else {
			int i = choose;
			for (int j = 0; j < value[i].length; j++) {
				// dataset.addValue(value[i][j], BarTitle[i], scale[j]);
				dataset.setValue(value[i][j], BarTitle[i], scale[j]);

			}

		}
		return dataset;

	}

}
