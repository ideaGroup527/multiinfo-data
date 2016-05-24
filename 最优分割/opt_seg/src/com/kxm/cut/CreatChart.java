package com.kxm.cut;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;

/**
 * ������������ͼ�����JFreeChart
 * @author kexinmei
 * @author kexinmeip@126.com
 * @version 1.0
 *  */
public class CreatChart {
	/**
	 * ����������������ͼ�����JFreeChart
	 * @param ����Ϊһ���������ָ�����ƽ���ܺ͵�List
	 * @return JFreeChart
	 * */
	public JFreeChart createChart(List pResult) {
		JFreeChart chart = null;
		ChartUtil chartutil = new ChartUtil();
		CategoryDataset data = chartutil.createDataSet(pResult);
		chart = ChartFactory.createLineChart("���ƽ���ܺͱ仯����ͼ", // ͼ�����
				"�ָ����", // X�����
				"���ƽ���ܺ�", // Y�����
				data, // ��ͼ���ݼ�
				PlotOrientation.VERTICAL, // ���Ʒ���
				true, // �Ƿ���ʾͼ��
				true, // �Ƿ���ñ�׼������
				false // �Ƿ����ɳ�����
				);
		// ���ñ�������
		chart.getTitle().setFont(new Font("����", Font.BOLD, 23)); // ����ͼ���������
		//chart.getLegend().setItemFont(new Font("����", Font.BOLD, 15));
		chart.setBackgroundPaint(new Color(40, 90, 210)); // ���ñ���ɫ
		// ��ȡ��ͼ������
		CategoryPlot plot = chart.getCategoryPlot();
		plot.getDomainAxis().setLabelFont(new Font("����", Font.BOLD, 15)); // ���ú�������
		plot.getDomainAxis().setTickLabelFont(new Font("����", Font.BOLD, 15));// ������������ֵ����
		plot.getRangeAxis().setLabelFont(new Font("����", Font.BOLD, 15)); // ������������
		plot.setBackgroundPaint(Color.WHITE); // ���û�ͼ������ɫ
		plot.setRangeGridlinePaint(Color.RED); // ����ˮƽ���򱳾�����ɫ
		plot.setRangeGridlinesVisible(true); // �����Ƿ���ʾˮƽ���򱳾���,Ĭ��ֵΪtrue
		plot.setDomainGridlinePaint(Color.RED); // ���ô�ֱ���򱳾�����ɫ
		plot.setDomainGridlinesVisible(true); // �����Ƿ���ʾ��ֱ���򱳾���,Ĭ��ֵΪfalse
		// ��ȡ���߶���
		LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot
				.getRenderer();
		float dashes[] = { 8.0f }; // ������������
		BasicStroke brokenLine = new BasicStroke(1.6f, // ������ϸ
				BasicStroke.CAP_SQUARE, // �˵���
				BasicStroke.JOIN_MITER, // �۵���
				8.f, // �۵㴦��취
				dashes, // ��������
				0.0f); // ����ƫ����
		renderer.setSeriesStroke(1, brokenLine); // �������߻���
		return chart;
	}
}
