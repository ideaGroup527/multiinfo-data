/*
 * ʱ�������ַ�������ʽ����
 * ����trans.getDingTime()  trans.getDingValue()   trans.getDingTitle()
 * ��trans.getDingTime()������a��ֵʱ   trans.getDingValue��Ҫ����a�е�ֵ
 * ��trans.getDingTitle()������b��ֵʱ  trans.getDingValue��Ҫ����b�е�ֵ
 * 
 * */
package org.plot.chart.impl;

import java.awt.*;
import java.io.*;
import java.util.List;
import java.awt.image.*;
import java.awt.geom.*;
import javax.servlet.http.*;
import javax.imageio.*;

import org.plot.parse.ParseColor;
import org.plot.parse.ParseValue;
import org.plot.pojo.ChartFont;

public class Ding1 {
	HttpServletResponse response;

	int width = 800, height = 550;

	private String chartTitle = "��     ��     ͼ";

	private String[] dingTitle;

	String xTitle;

	String yTitle;

	double[][] value;

	String[] xScale;

	double allMax;
	double allMin;
	boolean left_right;// ����������
	boolean up_down;// ����������
	ChartFont cf;
	ParseColor pc;
	
	private Graphics2D g2d = null;

	private BufferedImage image = null;

	public Ding1() {
		// Ĭ�Ϲ�����
	}

	public Ding1(HttpServletResponse response, String chartTitle,// ͼƬ����
			String[] dingTitle,// �����߶ε�����
			String xTitle, // X�������
			String yTitle, // Y�������
			List valueList, // X��Y���Ӧ��ֵ
			String[] xScale,// X��
			double allMax,// �����
			double allMin,// ��С����
			boolean left_right,// ����������false��true��
			boolean up_down,// ����������false��true��
			ChartFont cf // ��ͼϸ�ڡ����塣��ɫ����С

	) {
		this.response = response;
		this.chartTitle = chartTitle;
		this.dingTitle = dingTitle;
		this.xTitle = xTitle;
		this.allMax = allMax;
		this.allMin = allMin;
		this.left_right = left_right;
		this.up_down = up_down;
		this.cf = cf;
		this.pc = new ParseColor();
		/***********************************************************************
		 * ʹ��parseValue�ཫListת����double���͵�����
		 **********************************************************************/
		ParseValue pv = new ParseValue();// ת����
		double[][] valuetemp = pv.parseArray(valueList);// ��ʱ�����洢ת��ά�����Ľ��

		this.value = valuetemp;
		this.value = value;
		this.yTitle = yTitle;
		this.xScale = xScale;
		// �ж�ͼ��Ŀ��
		this.width = 100 + value.length * 46;
		if (chartTitle.length() * 25 >= width) {
			width = chartTitle.length() * 25 + 50;
		}

		int len = 0;
		for (int str_len = 0; str_len < dingTitle.length; str_len++) {
			len = Math.max(dingTitle[str_len].length(), len);

		}
		int xScale_str_len = 0;
		for (int x_len = 0; x_len < xScale.length; x_len++) {
			xScale_str_len = Math.max(xScale[x_len].length(), xScale_str_len);
			// System.out.println(xScale[x_len].length());
		}
		int hangLeng = 15 * xScale_str_len ;
		width = width + hangLeng+10;// ÿ������ռ15
		// �ж�ͼ��ĸ߶�
		int zongLength = 20 * len / 2 ;
		this.height = this.maxLength(value) * 20 + 50 + zongLength;

		// ��ʼ��
		this.initialize();
		// ���Ʊ���
		this.drawBackground();
		// ���Ʊ���
		this.drawTitle();
		// �������岿��
		this.drawChart(hangLeng,zongLength);
		// ���ͼƬ
		this.getChart();

	}

	// ��ʼ��
	public void initialize() {
		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		g2d = image.createGraphics();
		g2d.setColor(Color.white);
		g2d.fillRect(0, 0, width, height);
	}

	// ��������ͼ��ı���
	public void drawBackground() {
		// ��Ӱ��ͼ��֮���ƫ����
		int shadowOffset = 5;

		// �򿪷���ݹ��ܣ�����ƽ��������ر���
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);


		// ���ƽ�����ɫԲ�Ǿ��α���
		GradientPaint blueGP = new GradientPaint(0, 0, this.pc
				.parseColor(this.cf.getUpBackColor()), 0, height, this.pc
				.parseColor(this.cf.getDownBackColor()), false);

		g2d.setPaint(blueGP);
		// ���Ʊ����Ļ���
		g2d.fillRoundRect(0, 0, width - shadowOffset, height - shadowOffset,
				50, 50);

		// ���¹رշ���ݹ���
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_OFF);
	}

	// ����ͼ�����
	public void drawTitle() {
		g2d.setFont(new Font(this.cf.getTitleFont(), Font.PLAIN, this.cf
				.getTitleSize()));
		int titleLength = g2d.getFontMetrics().stringWidth(chartTitle);

		g2d.setColor(this.pc.parseColor(this.cf.getTitleColor()));
		g2d.drawString(chartTitle, (width - titleLength) / 2, 22);
	}

	// ����ͼ�����岿��
	public void drawChart(int hangLeng,int zongLength) {
		// ��õ�ǰ��AffineTransform����
		AffineTransform old = g2d.getTransform();
		g2d.translate(80, 30);

		// �������߱ʻ�
		float[] dashes = { 0.1f };
		BasicStroke bs = new BasicStroke(0.1f, BasicStroke.CAP_ROUND,
				BasicStroke.JOIN_ROUND, 10, dashes, 0);
		g2d.setStroke(bs);
		g2d.setFont(new Font("Courier New", Font.PLAIN, 12));
		int stringLength = 0, stringHeight = 0;

		// װ�غ��᷽��˵��
		String[] str = dingTitle;

		// �����Ͽ̶�ֵ�ĳ���
		int hSpace = 46;
		// �����Ͽ̶�ֵ֮��Ŀհ�
		int vSpace = 20;
		
		/******* �ж����ֵ����ҷ������·���*******/
		int strLength = 0;
		if (left_right) {
			strLength = 0;
		} else {
			strLength = hangLeng;
		}
		int hightLength = 0;
		if (up_down) {
			hightLength = 0;
		} else {
			hightLength = zongLength;
		}
		/******* �ж����ֵ����ҷ������·���*******/
		
		stringHeight = g2d.getFontMetrics().getAscent();
		for (int i = 0; i < dingTitle.length; i++) {
			// ���ƴ�ֱ��������
			g2d.setColor(this.pc.parseColor(this.cf.getLineColor()));
			g2d.drawLine(i * hSpace+strLength, hightLength, i * hSpace+strLength, vSpace * xScale.length+hightLength);
			if (i == dingTitle.length - 1) {
				g2d.drawLine((i + 1) * hSpace+strLength, hightLength, (i + 1) * hSpace+strLength, vSpace
						* xScale.length+hightLength);
			}
			// ������Ҫƫת�ķ�Χ��ʼ��
			AffineTransform old2 = g2d.getTransform();

			// ���ƺ����Ͽ̶�ֵ��˵������

			// ��������
			// ��������
			g2d.setFont(new Font(this.cf.getColFont(), Font.HANGING_BASELINE,
					this.cf.getColSize()));
			stringLength = g2d.getFontMetrics().stringWidth(str[i]);

			// ����������ɫ
			g2d.setColor(this.pc.parseColor(this.cf.getColColor()));
			// ����˵�����ֵ�ƫת�Ƕ�Ϊ13
			// g2d.rotate(0.9, i * hSpace + hSpace / 2 - 5,vSpace *
			// xScale.length + 13);

			String a = str[i];
			// ���¼��
			int updown_size = 13;
			// һ�м�����
			int jum = 2;
			if (cf.getColSize() == 10) {
				jum = 4;
				updown_size = 52;
			} else if (cf.getColSize() == 12 || cf.getColSize() == 11) {
				jum = 3;
				updown_size = 28;
			} else {
				jum = 2;
				updown_size = 15;
			}
			String[] tempStr = new String[a.length() / jum + 1];

			for (int cut = 0; cut <= a.length() / jum; cut++) {
				if ((jum * cut + jum) < a.length()) {
					tempStr[cut] = a.substring(jum * cut, jum * cut + jum);
				} else {
					tempStr[cut] = a.substring(jum * (cut - 1) + jum, a
							.length());
				}
			}
			
			/** *�ж����ֵ�����** */
			if (hightLength > 0) {
				for (int aa = 0; aa < tempStr.length; aa++) {
					/** **�ƶ�*** */
					g2d.drawString(tempStr[aa], i * hSpace + strLength, updown_size
							+ vSpace * aa);
				}
			} else {
				for (int aa = 0; aa < tempStr.length; aa++) {
					/** **�ƶ�*** */
					g2d.drawString(tempStr[aa], i * hSpace + strLength, vSpace
							* xScale.length + 18 + vSpace * aa);
				}
			}
			/** *�ж����ֵ�����** */
			
			// ƫת��Χ����
			g2d.setTransform(old2);
		}

		// ȡ������˵������
		String str1[] = xScale;

		for (int i = 0; i < xScale.length; i++) {
			// ����ˮƽ��������
			g2d.setColor(this.pc.parseColor(this.cf.getLineColor()));
			g2d.drawLine(strLength, i * vSpace+hightLength, hSpace * dingTitle.length+strLength, i * vSpace+hightLength);

			if (i == xScale.length - 1) {
				g2d.drawLine(strLength, (i + 1) * vSpace+hightLength, hSpace * dingTitle.length+strLength,
						(i + 1) * vSpace+hightLength);
			}

			// ���������Ͽ̶�ֵ��˵������
			g2d.setFont(new Font(this.cf.getRowFont(), Font.HANGING_BASELINE,
					this.cf.getRowSize()));
			stringLength = g2d.getFontMetrics().stringWidth(str1[i]);
			g2d.setColor(this.pc.parseColor(this.cf.getRowColor()));
			/** **�ƶ�*** */
			/** *�ж����ֵ�����** */
			if (strLength > 0) {
				g2d.drawString(str1[i], 0, i * vSpace + stringHeight / 2
						+ vSpace / 2 + hightLength);
			} else {
				g2d.drawString(str1[i], hSpace * dingTitle.length + 5, i * vSpace
						+ stringHeight / 2 + vSpace / 2 + hightLength);
			}

		}

		// �ҳ����ֵ ��Сֵ
		double[][] dingValue1 = value;
		double max[] = new double[dingValue1[0].length];
		double min[] = new double[dingValue1[0].length];
		int second = 0;// ����ĵ�һά
		while (second < dingValue1[0].length) {
			max[second] = dingValue1[0][second];
			min[second] = dingValue1[0][second];
			for (int k = 0; k < dingValue1.length; k++) {

				max[second] = Math.max(max[second], dingValue1[k][second]);
				min[second] = Math.min(min[second], dingValue1[k][second]);

			}
			// System.out.println(max[second]+" "+min[second]+"***");
			second++;// ��һ������
		}
		double[][] value1 = new double[dingValue1.length][dingValue1[0].length];// ��Բ�ĸ�
		int first1 = 0;// ����ĵ�һά
		while (first1 < dingValue1[0].length) {

			for (int k = 0; k < dingValue1.length; k++) {

				if (dingValue1[k][first1] == min[first1]) {
					if (allMin == 0) {
						value1[k][first1] = 0.01 * hSpace;// ��С��ֵ
					} else {
						value1[k][first1] = Math.sqrt(allMin) * vSpace;
					}
				} else {
					double tem = (double) ((dingValue1[k][first1] - min[first1]) / (max[first1] - min[first1]));
					if (allMax != 0) {
						// ������ִ���tempMax
						if (tem > allMax) {
							tem = allMax;
						}
					}
					if (allMin != 0) {
						// �������С��tempMin
						if (tem < allMin) {
							tem = allMin;
						}
					}
					value1[k][first1] = Math.sqrt(tem) * vSpace;
				}
			}
			// System.out.println(max[first]+" "+min[first]+"***");
			first1++;// ��һ������
		}

		// ת����ֵ����ֵ�����Բ�Ŀ�value[][]

		/***********************************************************************
		 * ** ** ** ** ** ** ** ** ** ** ** ** ** ��ʼ������Բ * ��ʼ������Բ * ��ʼ������Բ *
		 * ��ʼ������Բ * ** ** ** ** ** ** ** ** ** ** ** ** **
		 **********************************************************************/

		// 1���������ñʻ�
		g2d.setFont(new Font("Courier New", Font.PLAIN, 12));
		g2d.setColor(this.pc.parseColor(this.cf.getRoundColor()));// ��ɫ����

		// 2���򿪷���ݹ��ܣ�����ƽ��������ر���
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		// newһ��������double���ͻ���Բ�Ķ���
		Ellipse2D ellipse = new Ellipse2D.Double();

		for (int a = 0; a < value1.length; a++) {
			for (int aa = 0; aa < value1[a].length; aa++) {
				ellipse.setFrame(a * hSpace+strLength, (vSpace - value1[a][aa]) / 2 + aa
						* vSpace+hightLength, hSpace, value1[a][aa]);
				g2d.fill(ellipse);

			}
		}

		// 3�����¹رշ���ݹ���
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_OFF);

		// �ָ���ǰ��AffineTransform����������ת��
		g2d.setTransform(old);

	}

	// ����ͼ��
	// �������������

	public void getChart() {
		// ����ͼ�λ���
		g2d.dispose();

		// ���ͼ��WEBҳ��
		try {
			this.response.reset();
			this.response.setContentType("image/png");
			OutputStream sos = response.getOutputStream();
			ImageIO.write(image, "PNG", sos);

			sos.close();

		} catch (Exception e) {
			System.out.println("ͼ���������!");
		}
	}

	// �ж�����2ά��������ֵ
	public int maxLength(double[][] count) {

		int length = count[0].length;

		for (int i = 1; i < count.length; i++) {

			length = Math.max(length, count[i].length);

		}

		return length;
	}

}
