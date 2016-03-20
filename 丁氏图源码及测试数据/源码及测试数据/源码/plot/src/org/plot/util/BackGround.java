package org.plot.util;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import org.jfree.chart.renderer.category.*;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.Legend;
import org.jfree.chart.StandardLegend;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryLabelGenerator;
import org.jfree.chart.plot.*;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.ui.TextAnchor;
import org.jfree.chart.renderer.xy.*;
import org.jfree.chart.title.TextTitle;
import org.plot.parse.ParseColor;
import org.plot.pojo.OtherKindChartFont;

public class BackGround {

	/*
	 * Disperse.java XYDisperse.java LineChart.java XYLineChart.java �ı���
	 * 
	 */

	public BackGround(XYPlot plot, StandardXYItemRenderer renderer,
			JFreeChart chart, int height, int length,
			OtherKindChartFont other) {
		// ��ɫת����
		ParseColor pc = new ParseColor();
		
		// ����ɫ����
		BackColor bc = new BackColor();
		// ���岿��
		GradientPaint bgGP = bc.totalBack(height, other.getUpBackColor(), other
				.getDownBackColor());
		chart.setBackgroundPaint(bgGP);
		// ͼ�����ݲ���
		GradientPaint bg = bc.chartBack();
		plot.setBackgroundPaint(bg);
		// ������ɫ
		plot.setDomainGridlinePaint(Color.RED);
		plot.setDomainGridlinesVisible(true);
		plot.setRangeGridlinePaint(Color.RED);

		// ����������ɫ��С
		Font xlabelfont = new Font("����", Font.PLAIN, 12);// x���������
		Font xtickfont = new Font(other.getXFont(), Font.PLAIN, other.getXSize());// x��̶�����
		Font ylabelfont = new Font("����", Font.PLAIN, 12);// Y���������
		Font ytickfont = new Font(other.getYFont(), Font.PLAIN, other.getYSize());// Y��̶�����
		Font titleFont = new Font("����", Font.PLAIN, 25); // ͼƬ����
		Color xColor = pc.parseColor(other.getXColor());
		Color yColor = pc.parseColor(other.getYColor());
		Color titleColor = pc.parseColor(other.getTitleColor());
		// X��
		plot.getDomainAxis().setLabelFont(xlabelfont); // x���������
		plot.getDomainAxis().setTickLabelFont(xtickfont); // x��̶�����
		plot.getDomainAxis().setTickLabelPaint(xColor);
		// Y��
		plot.getRangeAxis().setLabelFont(ylabelfont); // y����Χ����
		plot.getRangeAxis().setTickLabelFont(ytickfont); // y���������
		plot.getRangeAxis().setTickLabelPaint(yColor);

		// ����
		chart.getTitle().setFont(titleFont);
		chart.getTitle().setPaint(titleColor);

		// ��ǩ��״
		StandardLegend sl = (StandardLegend) chart.getLegend();
		sl.setDisplaySeriesShapes(true);
		sl.setShapeScaleX(2.5);
		sl.setShapeScaleY(2.5);
		sl.setDisplaySeriesLines(true);

		// ��ɫ����
		renderer.setPlotShapes(true);
		renderer.setSeriesShapesFilled(0, Boolean.TRUE);
		renderer.setSeriesShapesFilled(1, Boolean.FALSE);

		// �Զ����߶εĻ�����ɫ
		Color color[] = new Color[length];
		color[0] = new Color(99, 99, 0);
		color[1] = new Color(55, 19, 6);
		color[2] = new Color(13, 255, 66);
		color[3] = new Color(33, 0, 255);
		color[4] = new Color(25, 0, 66);
		for (int i = 0; i < color.length; i++) {
			renderer.setSeriesPaint(i, color[i]);
		}

		// �Զ����߶εĻ��Ʒ��
		BasicStroke bs;
		for (int i = 0; i < length; i++) {
			float dashes[] = { 10.0f };
			bs = new BasicStroke(1.0f, BasicStroke.CAP_ROUND,
					BasicStroke.JOIN_ROUND, 1.0f, dashes, 1.0f);
			if (i % 2 != 0)
				renderer.setSeriesStroke(i, bs);
			else
				renderer.setSeriesStroke(i, new BasicStroke(1.0f));
		}

	}

	// Bar.java �ı���
	public BackGround(XYPlot plot, JFreeChart chart, int height,
			OtherKindChartFont other) {
		// ��ɫת����
		ParseColor pc = new ParseColor();
		// ����ɫ����
		BackColor bc = new BackColor();
		// ���岿��
		GradientPaint bgGP = bc.totalBack(height, other.getUpBackColor(), other
				.getDownBackColor());
		chart.setBackgroundPaint(bgGP);
		// ͼ�����ݲ���
		GradientPaint bg = bc.chartBack();
		plot.setBackgroundPaint(bg);
		// ������ɫ
		plot.setDomainGridlinePaint(Color.BLACK);
		plot.setDomainGridlinesVisible(true);
		plot.setRangeGridlinePaint(Color.RED);

		// ����������ɫ��С
		Font xlabelfont = new Font("����", Font.PLAIN, 12);// x���������
		Font xtickfont = new Font(other.getXFont(), Font.PLAIN, other.getXSize());// x��̶�����
		Font ylabelfont = new Font("����", Font.PLAIN, 12);// Y���������
		Font ytickfont = new Font(other.getYFont(), Font.PLAIN, other.getYSize());// Y��̶�����
		Font titleFont = new Font("����", Font.PLAIN, 25); // ͼƬ����
		Color xColor = pc.parseColor(other.getXColor());
		Color yColor = pc.parseColor(other.getYColor());
		Color titleColor = pc.parseColor(other.getTitleColor());
		// X��
		plot.getDomainAxis().setLabelFont(xlabelfont); // x���������
		plot.getDomainAxis().setTickLabelFont(xtickfont); // x��̶�����
		plot.getDomainAxis().setTickLabelPaint(xColor);
		// Y��
		plot.getRangeAxis().setLabelFont(ylabelfont); // y����Χ����
		plot.getRangeAxis().setTickLabelFont(ytickfont); // y���������
		plot.getRangeAxis().setTickLabelPaint(yColor);

		// ����
		chart.getTitle().setFont(titleFont);
		chart.getTitle().setPaint(titleColor);

		// ��ǩ��״
		StandardLegend sl = (StandardLegend) chart.getLegend();
		sl.setDisplaySeriesShapes(true);
		sl.setShapeScaleX(2.5);
		sl.setShapeScaleY(2.5);
		sl.setDisplaySeriesLines(true);

	}

	// XYBar�������
	public BackGround(CategoryPlot plot, BarRenderer renderer,
			JFreeChart chart,// ʵ��
			int height,// ͼ��ĸ߶�
			boolean lable,// �Ƿ��б�ǩ
			OtherKindChartFont other)
	{
		// ��ɫת����
		ParseColor pc = new ParseColor();
		// ����ɫ����
		BackColor bc = new BackColor();
		// ���岿��
		GradientPaint bgGP = bc.totalBack(height, other.getUpBackColor(), other
				.getDownBackColor());
		chart.setBackgroundPaint(bgGP);
		// ͼ�����ݲ���
		GradientPaint bg = bc.chartBack();
		plot.setBackgroundPaint(bg);
		// ������ɫ
		plot.setDomainGridlinePaint(Color.BLACK);
		plot.setDomainGridlinesVisible(true);
		plot.setRangeGridlinePaint(Color.RED);

		// ����������ɫ��С
		Font xlabelfont = new Font("����", Font.PLAIN, 12);// x���������
		Font xtickfont = new Font(other.getXFont(), Font.PLAIN, other.getXSize());// x��̶�����
		Font ylabelfont = new Font("����", Font.PLAIN, 12);// Y���������
		Font ytickfont = new Font(other.getYFont(), Font.PLAIN, other.getYSize());// Y��̶�����
		Font titleFont = new Font("����", Font.PLAIN, 25); // ͼƬ����
		Color xColor = pc.parseColor(other.getXColor());
		Color yColor = pc.parseColor(other.getYColor());
		Color titleColor = pc.parseColor(other.getTitleColor());
		// X��
		plot.getDomainAxis().setLabelFont(xlabelfont); // x���������
		plot.getDomainAxis().setTickLabelFont(xtickfont); // x��̶�����
		plot.getDomainAxis().setTickLabelPaint(xColor);
		// Y��
		plot.getRangeAxis().setLabelFont(ylabelfont); // y����Χ����
		plot.getRangeAxis().setTickLabelFont(ytickfont); // y���������
		plot.getRangeAxis().setTickLabelPaint(yColor);

		// ����
		chart.getTitle().setFont(titleFont);
		chart.getTitle().setPaint(titleColor);

		// ��ǩ��״
		StandardLegend sl = (StandardLegend) chart.getLegend();
		sl.setDisplaySeriesShapes(true);
		sl.setShapeScaleX(2.5);
		sl.setShapeScaleY(2.5);
		sl.setDisplaySeriesLines(true);

		// �����Ƿ����������
		renderer.setDrawBarOutline(true);

		// �����ǩ
		renderer.setLabelGenerator(new StandardCategoryLabelGenerator());
		renderer.setItemLabelsVisible(lable);
		ItemLabelPosition p = new ItemLabelPosition(ItemLabelAnchor.CENTER,
				TextAnchor.CENTER_LEFT, TextAnchor.CENTER_LEFT, -Math.PI / 2.0);

		renderer.setPositiveItemLabelPositionFallback(p);

		// ���ú���������ֵ���ת����
		CategoryAxis domainAxis = plot.getDomainAxis();
		domainAxis.setCategoryLabelPositions(CategoryLabelPositions.STANDARD);

		// ����ͼ������ʾλ��
		Legend legend = chart.getLegend();
		legend.setAnchor(Legend.SOUTH);

	}

	// XLineChart�ı���
	public BackGround(CategoryPlot plot, LineAndShapeRenderer renderer,
			JFreeChart chart, int height, int linelength,
			OtherKindChartFont other) {
		// ��ɫת����
		ParseColor pc = new ParseColor();
		// ����ɫ����
		BackColor bc = new BackColor();
		// ���岿��
		GradientPaint bgGP = bc.totalBack(height, other.getUpBackColor(), other
				.getDownBackColor());
		chart.setBackgroundPaint(bgGP);
		// ͼ�����ݲ���
		GradientPaint bg = bc.chartBack();
		plot.setBackgroundPaint(bg);

		// ������ɫ
		plot.setDomainGridlinePaint(Color.RED);
		plot.setDomainGridlinesVisible(true);
		plot.setRangeGridlinePaint(Color.RED);

		// ����������ɫ��С
		Font xlabelfont = new Font("����", Font.PLAIN, 12);// x���������
		Font xtickfont = new Font(other.getXFont(), Font.PLAIN, other.getXSize());// x��̶�����
		Font ylabelfont = new Font("����", Font.PLAIN, 12);// Y���������
		Font ytickfont = new Font(other.getYFont(), Font.PLAIN, other.getYSize());// Y��̶�����
		Font titleFont = new Font("����", Font.PLAIN, 25); // ͼƬ����
		Color xColor = pc.parseColor(other.getXColor());
		Color yColor = pc.parseColor(other.getYColor());
		Color titleColor = pc.parseColor(other.getTitleColor());
		// X��
		plot.getDomainAxis().setLabelFont(xlabelfont); // x���������
		plot.getDomainAxis().setTickLabelFont(xtickfont); // x��̶�����
		plot.getDomainAxis().setTickLabelPaint(xColor);
		// Y��
		plot.getRangeAxis().setLabelFont(ylabelfont); // y����Χ����
		plot.getRangeAxis().setTickLabelFont(ytickfont); // y���������
		plot.getRangeAxis().setTickLabelPaint(yColor);

		// ����
		chart.getTitle().setFont(titleFont);
		chart.getTitle().setPaint(titleColor);

		// ��ǩ��״
		StandardLegend sl = (StandardLegend) chart.getLegend();
		sl.setDisplaySeriesShapes(true);
		sl.setShapeScaleX(2.5);
		sl.setShapeScaleY(2.5);
		sl.setDisplaySeriesLines(true);

		// ���ú���������ֵ���ת����
		CategoryAxis domainAxis = plot.getDomainAxis();
		domainAxis.setCategoryLabelPositions(CategoryLabelPositions.STANDARD);

		// ����ͼ������ʾλ��
		Legend legend = chart.getLegend();
		legend.setAnchor(Legend.SOUTH);

		// ���ú�����������
		// CategoryAxis domainAxis = plot.getDomainAxis();
		// domainAxis.setLabelFont(new Font("������", Font.BOLD, 15));

		// ��������������ֵ����弰����ת����
		// ValueAxis rangeAxis = plot.getRangeAxis();
		// rangeAxis.setLabelFont(new Font("������", Font.BOLD, 15));
		// rangeAxis.setLabelAngle(Math.PI/2);

		// ������������Сͼ��************************
		renderer.setDrawShapes(true);

		// �Զ����߶εĻ�����ɫ
		Color color[] = new Color[linelength];
		color[0] = new Color(99, 99, 0);
		color[1] = new Color(255, 169, 66);
		color[2] = new Color(33, 255, 66);
		color[3] = new Color(33, 0, 255);
		color[4] = new Color(25, 0, 66);
		for (int i = 0; i < color.length; i++) {
			renderer.setSeriesPaint(i, color[i]);
		}

		// �Զ����߶εĻ��Ʒ��
		BasicStroke bs;
		for (int i = 0; i < linelength; i++) {
			float dashes[] = { 10.0f };
			bs = new BasicStroke(1.0f, BasicStroke.CAP_ROUND,
					BasicStroke.JOIN_ROUND, 1.0f, dashes, 1.0f);
			if (i % 2 != 0)
				renderer.setSeriesStroke(i, bs);
			else
				renderer.setSeriesStroke(i, new BasicStroke(1.0f));
		}

	}

	public BackGround(PiePlot plot, JFreeChart chart, int height,
			OtherKindChartFont other) {
		// ��ɫת����
		ParseColor pc = new ParseColor();

		// ����ɫ����
		BackColor bc = new BackColor();
		// ���岿��
		GradientPaint totalBG = bc.totalBack(height, other.getUpBackColor(),
				other.getDownBackColor());
		chart.setBackgroundPaint(totalBG);
		// ͼ�����ݲ���
		GradientPaint chartBG = bc.chartBack();
		plot.setBackgroundPaint(chartBG);

		Font titleFont = new Font(other.getTitleFont(), Font.PLAIN, other
				.getTitleSize()); // ͼƬ����
		chart.getTitle().setFont(titleFont);
		chart.getTitle().setPaint(pc.parseColor(other.getTitleColor()));

		// �����Ƿ�ʹ�÷���ݹ���
		chart.setAntiAlias(true);
		// ���ñ�ͼ��ǩ�Ļ�������
		plot.setLabelFont(new Font(other.getPieLableFont(), Font.PLAIN, other
				.getPieLableSize()));
		plot.setLabelPaint(pc.parseColor(other.getPieLableColor()));
		// ���ñ�ͼ�����Ϊ��Բ�λ�����Բ��
		plot.setCircular(true);

	}

}
