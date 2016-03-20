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

	// �������ݰ�װ��
	public CommonData comm = null;
	// �ļ������Ƽ���׺
	public String fileName = "";

	// �ߵı���
	public String LineTitle[];

	public String[] xScale;

	// ��Ӧ��ֵ
	public List value;

	boolean orient = false;
	OtherKindChartFont other ;

	// �в������캯�� ֻ��������ʹ��
	public XYLine(HttpSession session,// �̶���ʽ��Ҫsession����ͼƬ����
			String chartTitle,// ͼƬ������
			String[] LineTitle,// ����������
			String xTitle,// X�������
			String yTitle,// Y�������
			List value,// X��Y������Ӧ��ֵ
			int width,// ���
			int height, // �߶�
			String[] xScale,// ��-2ά
			boolean orient,
			OtherKindChartFont other 

	) {

		// ���ݴ���
		// session������ʹ�ã���JSP�е�session����������
		comm = new CommonData(session, chartTitle, width, height, xTitle,
				yTitle);

		this.orient = orient;
		this.xScale = xScale;
		this.value = value;
		this.LineTitle = LineTitle;
		this.other = other ;

		// ����һ��ͼ��
		CategoryDataset dataset = (CategoryDataset) createDataset();
		JFreeChart chart = createChart(dataset);

		// ���ͼ���������ã��������ø�����Զ����������
		CategoryPlot plot = (CategoryPlot) chart.getPlot();
		LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot
				.getRenderer();
		// ͼ����ɫ
		BackGround bg = new BackGround(plot, renderer, chart, height,
				LineTitle.length,other);
		if (orient) {
			plot.setOrientation(PlotOrientation.HORIZONTAL);
		} else {
			plot.setOrientation(PlotOrientation.VERTICAL);
		}
		// �����Զ���ͼ����Ƶ��������

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
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		double value1 = 0;
		// �жϲ���ֵ
		for (int i = 0; i < value.size(); i++) {
			// �ڲ㼯��
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
		// ����ͼ�����
		JFreeChart chart = ChartFactory.createLineChart(comm.getChartTitle(), // ͼ�����
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
