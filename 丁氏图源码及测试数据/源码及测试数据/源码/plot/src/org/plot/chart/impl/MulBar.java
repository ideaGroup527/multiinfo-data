package org.plot.chart.impl;

import java.awt.Font;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.entity.StandardEntityCollection;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.CombinedDomainCategoryPlot;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.chart.servlet.ServletUtilities;
import org.plot.chart.MulChart;
import org.plot.parse.ParseValue;
import org.plot.pojo.CommonData;
import org.plot.util.CreateDataset;

public class MulBar implements MulChart {

	// �������ݰ�װ��
	public CommonData comm = null;
	// �ļ������Ƽ���׺
	public String fileName = "";

	String[] BarTitle;

	String[] scale;

	List value;

	int idea;

	int choose = -1;

	boolean lable = false;

	public MulBar(HttpSession session,// �̶���ʽ��Ҫsession����ͼƬ����
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
			boolean lable// �Ƿ��б�ǩ
	) {
		comm = new CommonData(session, chartTitle, width, height, xTitle,
				yTitle);

		this.BarTitle = BarTitle;
		this.scale = scale;
		this.value = value;
		this.idea = idea;
		this.choose = choose;
		this.lable = lable;

		/***********************************************************************
		 * ʹ��parseValue�ཫListת����double���͵�����
		 **********************************************************************/
		ParseValue pv = new ParseValue();// ת����
		double[][] valueArray = pv.parseArray(value);// ��ʱ�����洢ת��ά�����Ľ��

		// ������һ����plot����, ��������ػ�������
		CreateDataset createData = new CreateDataset();

		// ������Plot����ĺ��ᣬ����������Plot����
		CategoryAxis domainAxis = new CategoryAxis(xTitle);

		// ������Plot����
		CombinedDomainCategoryPlot plot = new CombinedDomainCategoryPlot(
				domainAxis);
		// ���ü��
		plot.setGap(10.0);

		for (int i = 0; i < BarTitle.length; i++) {

			if (idea == 1 || idea == 3) {
				if (idea == 3) {
					plot.setOrientation(PlotOrientation.HORIZONTAL);
				}
				BarRenderer renderer = new BarRenderer();
				NumberAxis rangeAxis = new NumberAxis(yTitle);
				rangeAxis.setStandardTickUnits(NumberAxis
						.createIntegerTickUnits());
				CategoryPlot subplot = new CategoryPlot(createData
						.createDataset(i, valueArray, BarTitle, scale), null,
						rangeAxis, renderer);
				subplot.setDomainGridlinesVisible(true);

				plot.add(subplot, 1);
			} else {
				if (idea == 4) {
					plot.setOrientation(PlotOrientation.HORIZONTAL);
				}
				BarRenderer3D renderer = new BarRenderer3D();
				NumberAxis rangeAxis = new NumberAxis(yTitle);
				rangeAxis.setStandardTickUnits(NumberAxis
						.createIntegerTickUnits());
				CategoryPlot subplot = new CategoryPlot(createData
						.createDataset(i, valueArray, BarTitle, scale), null,
						rangeAxis, renderer);
				subplot.setDomainGridlinesVisible(true);

				plot.add(subplot, 1);

			}

		}

		// ����ͼ��
		JFreeChart chart = createChart(plot);
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

	public JFreeChart createChart(Plot temp) {
		CombinedDomainCategoryPlot plot = (CombinedDomainCategoryPlot) temp;
		// ����JFreeChart������������ͼ�����
		JFreeChart chart = new JFreeChart(comm.getChartTitle(), // ͼ�����
				new Font("������", Font.BOLD, 15), // ͼ���������
				plot, // ͼ����ƶ���
				true // �Ƿ����ͼ��
		);
		return chart;
	}

	public String getFileName() {
		return fileName;
	}
}
