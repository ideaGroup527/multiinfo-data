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
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.servlet.ServletUtilities;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.Dataset;
import org.plot.chart.Chart;
import org.plot.pojo.CommonData;
import org.plot.pojo.OtherKindChartFont;
import org.plot.util.BackGround;

public class XYBar implements Chart {

	// �ļ������Ƽ���׺
	public String fileName = "";

	// �������ݰ�װ��
	public CommonData comm = null;

	// ÿ����״ͼ�ı���
	String[] BarTitle;

	// X��̶�����
	String[] scale;

	List value;

	int idea;

	int choose = -1;

	boolean lable = false;
	
	OtherKindChartFont other  ;

	public XYBar(HttpSession session,// �̶���ʽ��Ҫsession����ͼƬ����
			String chartTitle,// ͼƬ������
			String[] BarTitle,// ��������
			String xTitle,// X�������
			String yTitle,// Y�������
			String scale[],// �̶ȵ�ֵ
			List value,// X��Y������Ӧ��ֵ
			int width,// ���
			int height, // �߶�
			int idea,// 1Ϊ��ֵƽ�� 2Ϊ��ֵ3D 3Ϊ����ƽ�� 4Ϊ����3D
			int choose,// ѡ��Ҫ�������Ƶ����顰i��
			boolean lable,// �Ƿ��б�ǩ
			OtherKindChartFont other 
	) {
		comm = new CommonData(session, chartTitle, width, height, xTitle,
				yTitle);

		this.BarTitle = BarTitle;
		this.scale = scale;
		this.value = value;
		this.idea = idea;
		this.choose = choose;
		this.lable = lable;
		this.other = other ;

		CategoryDataset dataset = (CategoryDataset) createDataset();
		JFreeChart chart = createChart(dataset);

		// ����ͼ��ı�����ɫ

		// ���ͼ���������ã��������ø�����Զ����������
		CategoryPlot plot = chart.getCategoryPlot();
		BarRenderer renderer = (BarRenderer) plot.getRenderer();
		BackGround bg = new BackGround(plot, renderer, chart, height, lable,other);

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
		if (choose < 0) {
			for (int i = 0; i < value.size(); i++) {
				// ȡ����һ��LIST
				List firstList = (List) value.get(i);
				for (int j = 0; j < firstList.size(); j++) {
					dataset.setValue((Double) firstList.get(j), BarTitle[i],
							scale[j]);
				}
			}
		} else if (choose >= value.size()) {
			int i = value.size() - 1;
			// ȡ����һ��LIST
			List firstList = (List) value.get(i);
			for (int j = 0; j < firstList.size(); j++) {
				// dataset.addValue(value[i][j], BarTitle[i], scale[j]);
				dataset.setValue((Double) firstList.get(j), BarTitle[i],
						scale[j]);
			}
		} else {
			int i = choose;
			List firstList = (List) value.get(i);
			for (int j = 0; j < firstList.size(); j++) {
				// dataset.addValue(value[i][j], BarTitle[i], scale[j]);
				dataset.setValue((Double) firstList.get(j), BarTitle[i],
						scale[j]);
			}
		}
		return dataset;
	}

	public JFreeChart createChart(Dataset temp) {

		CategoryDataset dataset = (CategoryDataset) temp;
		// ����ͼ�����
		JFreeChart chart;
		switch (idea) {
		case 1:
			chart = ChartFactory.createBarChart(comm.getChartTitle(), // ͼ�����
					comm.getXTitle(), // �������
					comm.getYTitle(), // �������
					dataset, // �����������
					PlotOrientation.VERTICAL, // ֱ��ͼ�ķ��� ��ֱ
					true, // ����ͼ���Ƿ����ͼ��
					true, // ����ͼ���Ƿ������ʾ
					false // ����ͼ���Ƿ����URL
					);
			return chart;

		case 2:
			chart = ChartFactory.createBarChart3D(comm.getChartTitle(), // ͼ�����
					comm.getXTitle(), // �������
					comm.getYTitle(), // �������
					dataset, // �����������
					PlotOrientation.VERTICAL, // ֱ��ͼ�ķ��� ��ֱ
					true, // ����ͼ���Ƿ����ͼ��
					true, // ����ͼ���Ƿ������ʾ
					false // ����ͼ���Ƿ����URL
					);
			return chart;

		case 3:
			chart = ChartFactory.createBarChart(comm.getChartTitle(), // ͼ�����
					comm.getXTitle(), // �������
					comm.getYTitle(), // �������
					dataset, // �����������
					PlotOrientation.HORIZONTAL, // ֱ��ͼ�ķ��� ����
					true, // ����ͼ���Ƿ����ͼ��
					true, // ����ͼ���Ƿ������ʾ
					false // ����ͼ���Ƿ����URL
					);
			return chart;

		default:
			chart = ChartFactory.createBarChart3D(comm.getChartTitle(), // ͼ�����
					comm.getXTitle(), // �������
					comm.getYTitle(), // �������
					dataset, // �����������
					PlotOrientation.HORIZONTAL, // ֱ��ͼ�ķ��� ����
					true, // ����ͼ���Ƿ����ͼ��
					true, // ����ͼ���Ƿ������ʾ
					false // ����ͼ���Ƿ����URL
					);
			return chart;

		}
	}

	public String getFileName() {
		return fileName;
	}

}
