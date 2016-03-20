package org.plot.chart.impl;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.entity.StandardEntityCollection;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.servlet.ServletUtilities;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.general.Dataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.plot.chart.Chart;
import org.plot.pojo.CommonData;
import org.plot.pojo.OtherKindChartFont;
import org.plot.util.BackGround;

public class Pie implements Chart {

	// ͼ������
	boolean idea;
	// Pie��ÿ�����ֵ�����
	String[] pieTitle;
	// X���Ӧ�Ŀ̶�
	String[] xScale;

	// ͼ�������
	String chartTitle;

	// ÿ����������Ӧ����ֵ
	private List value;
	// �ļ������Ƽ���׺
	public String fileName = "";
	// �������ݰ�װ��
	public CommonData comm = null;

	int choose = -1;
	
	OtherKindChartFont other ;

	public Pie() {
	}

	// ���л���
	public Pie(HttpSession session,// �̶���ʽ��Ҫsession����ͼƬ����
			String chartTitle,// ͼƬ������
			String[] pieTitle,// Pie��ÿ�����ֵ�����
			List value,// X��Y������Ӧ��ֵ
			int width,// ���
			int height, // �߶�
			boolean idea,// ideaΪtrueʱ��Ϊƽ��ͼ����falseʱ��Ϊ3Dͼ
			int choose, // ѡ��ڼ���
			OtherKindChartFont other 
	) {

		comm = new CommonData(session, chartTitle, width, height);
		this.idea = idea;
		this.pieTitle = pieTitle;
		this.value = value;
		this.choose = choose;
		this.other = other ;

		PieDataset dataset = (PieDataset) createDataset();
		JFreeChart chart = createChart(dataset);

		// ��ʼ�Զ���ͼ����Ƶ��������
		// �Զ���ͼ��ı�����������ɫ
		TextTitle title = chart.getTitle();
		title.setFont(new Font("����", Font.BOLD, 25));
		title.setPaint(Color.YELLOW);

		// ���ͼ����������
		PiePlot plot = (PiePlot) chart.getPlot();
		// ��������ͼ��ı�����ɫ
		BackGround bg = new BackGround(plot, chart, height,other);

		// �����Զ���ͼ����Ƶ��������
		ChartRenderingInfo info = new ChartRenderingInfo(
				new StandardEntityCollection());

		// ����ͼƬ���ɸ�ʽ
		try {
			fileName = ServletUtilities.saveChartAsPNG(chart, width, height,
					info, session);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// ���л���
	public Pie(HttpSession session,// �̶���ʽ��Ҫsession����ͼƬ����
			String chartTitle,// ͼƬ������
			String[] pieTitle,// Pie��ÿ�����ֵ�����
			List value,// X��Y������Ӧ��ֵ
			String[] xScale,// X���Ӧ�Ŀ̶�
			int width,// ���
			int height, // �߶�
			boolean idea,// ideaΪtrueʱ��Ϊƽ��ͼ����falseʱ��Ϊ3Dͼ
			int choose, // �ڼ���
			OtherKindChartFont other 
	) {

		comm = new CommonData(session, chartTitle, width, height);
		this.idea = idea;
		this.pieTitle = pieTitle;
		this.value = value;
		this.xScale = xScale;
		this.choose = choose;
		this.other = other ;

		PieDataset dataset = (PieDataset) createDataset();
		JFreeChart chart = createChart(dataset);

		// ��ʼ�Զ���ͼ����Ƶ��������
		// �Զ���ͼ��ı�����������ɫ
		TextTitle title = chart.getTitle();
		title.setFont(new Font("����", Font.BOLD, 25));
		title.setPaint(Color.YELLOW);

		// ���ͼ����������
		PiePlot plot = (PiePlot) chart.getPlot();
		// ��������ͼ��ı�����ɫ
		BackGround bg = new BackGround(plot, chart, height,other);

		// �����Զ���ͼ����Ƶ��������
		ChartRenderingInfo info = new ChartRenderingInfo(
				new StandardEntityCollection());

		// ����ͼƬ���ɸ�ʽ
		try {
			fileName = ServletUtilities.saveChartAsPNG(chart, width, height,
					info, session);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// �������ݼ�
	public Dataset createDataset() {
		DefaultPieDataset dataset = new DefaultPieDataset();
		//��x��̶�xScaleΪ�����ʾ����
		if (null == xScale) {
			// ѡ��ĳ����
			// �˴�choose����ѡ����һ��
			if (choose < 0) {
				// �����ͼ
				for (int i = 0; i < pieTitle.length; i++) {
					Double tempvalue = 0.0;
					List valueInner = (List) value.get(i);
					for (int j = 0; j < valueInner.size(); j++) {
						tempvalue += (Double) valueInner.get(j);
					}
					dataset.setValue(pieTitle[i], tempvalue);
				}
			} else if (choose >= ((List) value.get(0)).size()) {
				// !!!!!!!!!!be carefull!!!!!!!!!!!
				// ȡ���һ��������
				List valueInner = null;
				for (int i = 0; i < pieTitle.length; i++) {
					valueInner = (List) value.get(i);
					double tempValue = (Double) valueInner.get(pieTitle.length);
					dataset.setValue(pieTitle[i], tempValue);
				}
			} else {
				// ���������ƣ�ˮƽ�ó�һ������
				List valueInner = null;
				for (int i = 0; i < pieTitle.length; i++) {
					valueInner = (List) value.get(i);
					double tempValue = (Double) valueInner.get(choose);
					dataset.setValue(pieTitle[i], tempValue);
				}

			}
		} else {
			// ѡ��ĳһ�е�����������
			// �˴�choose�Ǵ���ѡ����һ��
			if (choose < 0) {
				choose = 0;
			} else if (choose > pieTitle.length) {
				choose = pieTitle.length;
			}
			List valueInner = (List) value.get(choose);
			for (int i = 0; i < valueInner.size(); i++) {
				double tempValue = (Double) valueInner.get(i);
				dataset.setValue(xScale[i], tempValue);
			}

		}
		return dataset;
	}

	// ��������ͼ
	public JFreeChart createChart(Dataset temp) {

		PieDataset dataset = (PieDataset) temp;

		if (idea) {
			JFreeChart chart = ChartFactory.createPieChart(
					comm.getChartTitle(), // ͼ�����
					dataset, // ���ݼ�
					true, // ����ͼ��
					true, false);
			return chart;
		} else {
			JFreeChart chart = ChartFactory.createPieChart3D(comm
					.getChartTitle(), // ͼ�����
					dataset, // ���ݼ�
					true, // ����ͼ��
					true, false);
			return chart;
		}

	}

	// ����·��
	public String getFileName() {

		return fileName;
	}

}
