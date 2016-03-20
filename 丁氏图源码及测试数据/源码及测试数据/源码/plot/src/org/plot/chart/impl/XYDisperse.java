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

	// �������ݰ�װ��
	public CommonData comm = null;

	// �ļ������Ƽ���׺
	public String fileName = "";

	// �ߵı���
	public String disperseTitle[];

	public int[] xScale;

	// ��Ӧ��ֵ
	public List value;

	boolean orient = false;
	OtherKindChartFont other ;

	// �в������캯�� ֻ��������ʹ��
	public XYDisperse(HttpSession session,// �̶���ʽ��Ҫsession����ͼƬ����
			String chartTitle,// ͼƬ������
			String[] disperseTitle,// ����������
			String xTitle,// X�������
			String yTitle,// Y�������
			List value,// X��Y������Ӧ��ֵ
			int width,// ���
			int height, // �߶�
			int[] xScale,// ��-2ά
			boolean orient,
			OtherKindChartFont other 

	) {

		// ���ݴ���
		// session������ʹ�ã���JSP�е�session����������
		comm = new CommonData(session, chartTitle, width, height, xTitle,
				yTitle);

		this.xScale = xScale;

		this.value = value;

		this.disperseTitle = disperseTitle;

		this.orient = orient;

		this.other = other ;
		
		// ����һ��ͼ��
		XYDataset dataset = (XYDataset) createDataset();
		JFreeChart chart = createChart(dataset);

		// ���� ���ͼ���������ã��������ø�����Զ���������� �Զ���ͼ������ʾ���
		XYPlot plot = chart.getXYPlot();

		StandardXYItemRenderer renderer = (StandardXYItemRenderer) plot
				.getRenderer();
//		// ��ǩ��״
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
		
		// ͼ����ɫ
		BackGround bg = new BackGround(plot, renderer, chart, height,
				disperseTitle.length,other);

		if (orient) {
			plot.setOrientation(PlotOrientation.HORIZONTAL);
		} else {
			plot.setOrientation(PlotOrientation.VERTICAL);
		}

		ChartRenderingInfo info = new ChartRenderingInfo(
				new StandardEntityCollection());

		// ����ͼƬ���ɸ�ʽPNG
		try {
			fileName = ServletUtilities.saveChartAsPNG(chart, width, height,
					info, session);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// �������ݼ�
	public Dataset createDataset() {
		XYSeriesCollection dataset = new XYSeriesCollection();
		XYSeries ts[] = new XYSeries[disperseTitle.length];

		double xvalue = 0;
		double yvalue = 0;
		// �жϲ���ֵ
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
		// ����ͼ�����
		JFreeChart chart = ChartFactory.createScatterPlot(comm.getChartTitle(), // ͼ�����
				comm.getXTitle(), // �������
				comm.getYTitle(), // �������
				dataset, // �����������
				PlotOrientation.VERTICAL, // ��ֱ����
				true, // ����ͼ���Ƿ����ͼ��
				true, // ����ͼ���Ƿ������ʾ
				true // ����ͼ���Ƿ����URL
				);
		return chart;
	}

	public String getFileName() {
		return fileName;
	}
}