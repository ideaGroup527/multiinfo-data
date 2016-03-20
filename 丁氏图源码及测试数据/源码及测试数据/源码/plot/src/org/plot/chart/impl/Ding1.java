/*
 * 时间是以字符串的形式进入
 * 调用trans.getDingTime()  trans.getDingValue()   trans.getDingTitle()
 * 当trans.getDingTime()中增加a个值时   trans.getDingValue中要增加a列的值
 * 当trans.getDingTitle()中增加b个值时  trans.getDingValue中要增加b行的值
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

	private String chartTitle = "丁     氏     图";

	private String[] dingTitle;

	String xTitle;

	String yTitle;

	double[][] value;

	String[] xScale;

	double allMax;
	double allMin;
	boolean left_right;// 文字在左右
	boolean up_down;// 文字在上下
	ChartFont cf;
	ParseColor pc;
	
	private Graphics2D g2d = null;

	private BufferedImage image = null;

	public Ding1() {
		// 默认构造器
	}

	public Ding1(HttpServletResponse response, String chartTitle,// 图片标题
			String[] dingTitle,// 各个线段的名称
			String xTitle, // X轴的名称
			String yTitle, // Y轴的名称
			List valueList, // X、Y轴对应的值
			String[] xScale,// X轴
			double allMax,// 最大极限
			double allMin,// 最小极限
			boolean left_right,// 文字在左右false左true右
			boolean up_down,// 文字在上下false上true下
			ChartFont cf // 绘图细节。字体。颜色。大小

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
		 * 使用parseValue类将List转换成double类型的数组
		 **********************************************************************/
		ParseValue pv = new ParseValue();// 转换类
		double[][] valuetemp = pv.parseArray(valueList);// 临时变量存储转换维数组后的结果

		this.value = valuetemp;
		this.value = value;
		this.yTitle = yTitle;
		this.xScale = xScale;
		// 判断图表的宽度
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
		width = width + hangLeng+10;// 每个汉字占15
		// 判断图表的高度
		int zongLength = 20 * len / 2 ;
		this.height = this.maxLength(value) * 20 + 50 + zongLength;

		// 初始化
		this.initialize();
		// 绘制背景
		this.drawBackground();
		// 绘制标题
		this.drawTitle();
		// 绘制主体部分
		this.drawChart(hangLeng,zongLength);
		// 获得图片
		this.getChart();

	}

	// 初始化
	public void initialize() {
		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		g2d = image.createGraphics();
		g2d.setColor(Color.white);
		g2d.fillRect(0, 0, width, height);
	}

	// 绘制整个图表的背景
	public void drawBackground() {
		// 阴影和图表之间的偏移量
		int shadowOffset = 5;

		// 打开反锯齿功能，用于平滑处理相关背景
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);


		// 绘制渐进蓝色圆角矩形背景
		GradientPaint blueGP = new GradientPaint(0, 0, this.pc
				.parseColor(this.cf.getUpBackColor()), 0, height, this.pc
				.parseColor(this.cf.getDownBackColor()), false);

		g2d.setPaint(blueGP);
		// 绘制背景的弧度
		g2d.fillRoundRect(0, 0, width - shadowOffset, height - shadowOffset,
				50, 50);

		// 重新关闭反锯齿功能
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_OFF);
	}

	// 绘制图表标题
	public void drawTitle() {
		g2d.setFont(new Font(this.cf.getTitleFont(), Font.PLAIN, this.cf
				.getTitleSize()));
		int titleLength = g2d.getFontMetrics().stringWidth(chartTitle);

		g2d.setColor(this.pc.parseColor(this.cf.getTitleColor()));
		g2d.drawString(chartTitle, (width - titleLength) / 2, 22);
	}

	// 绘制图表主体部分
	public void drawChart(int hangLeng,int zongLength) {
		// 获得当前的AffineTransform对象
		AffineTransform old = g2d.getTransform();
		g2d.translate(80, 30);

		// 创建虚线笔划
		float[] dashes = { 0.1f };
		BasicStroke bs = new BasicStroke(0.1f, BasicStroke.CAP_ROUND,
				BasicStroke.JOIN_ROUND, 10, dashes, 0);
		g2d.setStroke(bs);
		g2d.setFont(new Font("Courier New", Font.PLAIN, 12));
		int stringLength = 0, stringHeight = 0;

		// 装载横轴方向说明
		String[] str = dingTitle;

		// 横轴上刻度值的长度
		int hSpace = 46;
		// 纵轴上刻度值之间的空白
		int vSpace = 20;
		
		/******* 判断文字的左右方向及上下方向*******/
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
		/******* 判断文字的左右方向及上下方向*******/
		
		stringHeight = g2d.getFontMetrics().getAscent();
		for (int i = 0; i < dingTitle.length; i++) {
			// 绘制垂直方向虚线
			g2d.setColor(this.pc.parseColor(this.cf.getLineColor()));
			g2d.drawLine(i * hSpace+strLength, hightLength, i * hSpace+strLength, vSpace * xScale.length+hightLength);
			if (i == dingTitle.length - 1) {
				g2d.drawLine((i + 1) * hSpace+strLength, hightLength, (i + 1) * hSpace+strLength, vSpace
						* xScale.length+hightLength);
			}
			// 划定需要偏转的范围开始处
			AffineTransform old2 = g2d.getTransform();

			// 绘制横轴上刻度值的说明文字

			// 设置字体
			// 设置字体
			g2d.setFont(new Font(this.cf.getColFont(), Font.HANGING_BASELINE,
					this.cf.getColSize()));
			stringLength = g2d.getFontMetrics().stringWidth(str[i]);

			// 设置字体颜色
			g2d.setColor(this.pc.parseColor(this.cf.getColColor()));
			// 设置说明文字的偏转角度为13
			// g2d.rotate(0.9, i * hSpace + hSpace / 2 - 5,vSpace *
			// xScale.length + 13);

			String a = str[i];
			// 上下间隔
			int updown_size = 13;
			// 一行几个字
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
			
			/** *判断文字的上下** */
			if (hightLength > 0) {
				for (int aa = 0; aa < tempStr.length; aa++) {
					/** **移动*** */
					g2d.drawString(tempStr[aa], i * hSpace + strLength, updown_size
							+ vSpace * aa);
				}
			} else {
				for (int aa = 0; aa < tempStr.length; aa++) {
					/** **移动*** */
					g2d.drawString(tempStr[aa], i * hSpace + strLength, vSpace
							* xScale.length + 18 + vSpace * aa);
				}
			}
			/** *判断文字的上下** */
			
			// 偏转范围结束
			g2d.setTransform(old2);
		}

		// 取得纵向说明文字
		String str1[] = xScale;

		for (int i = 0; i < xScale.length; i++) {
			// 绘制水平方向虚线
			g2d.setColor(this.pc.parseColor(this.cf.getLineColor()));
			g2d.drawLine(strLength, i * vSpace+hightLength, hSpace * dingTitle.length+strLength, i * vSpace+hightLength);

			if (i == xScale.length - 1) {
				g2d.drawLine(strLength, (i + 1) * vSpace+hightLength, hSpace * dingTitle.length+strLength,
						(i + 1) * vSpace+hightLength);
			}

			// 绘制纵轴上刻度值的说明文字
			g2d.setFont(new Font(this.cf.getRowFont(), Font.HANGING_BASELINE,
					this.cf.getRowSize()));
			stringLength = g2d.getFontMetrics().stringWidth(str1[i]);
			g2d.setColor(this.pc.parseColor(this.cf.getRowColor()));
			/** **移动*** */
			/** *判断文字的上下** */
			if (strLength > 0) {
				g2d.drawString(str1[i], 0, i * vSpace + stringHeight / 2
						+ vSpace / 2 + hightLength);
			} else {
				g2d.drawString(str1[i], hSpace * dingTitle.length + 5, i * vSpace
						+ stringHeight / 2 + vSpace / 2 + hightLength);
			}

		}

		// 找出最大值 最小值
		double[][] dingValue1 = value;
		double max[] = new double[dingValue1[0].length];
		double min[] = new double[dingValue1[0].length];
		int second = 0;// 数组的第一维
		while (second < dingValue1[0].length) {
			max[second] = dingValue1[0][second];
			min[second] = dingValue1[0][second];
			for (int k = 0; k < dingValue1.length; k++) {

				max[second] = Math.max(max[second], dingValue1[k][second]);
				min[second] = Math.min(min[second], dingValue1[k][second]);

			}
			// System.out.println(max[second]+" "+min[second]+"***");
			second++;// 下一行数据
		}
		double[][] value1 = new double[dingValue1.length][dingValue1[0].length];// 椭圆的高
		int first1 = 0;// 数组的第一维
		while (first1 < dingValue1[0].length) {

			for (int k = 0; k < dingValue1.length; k++) {

				if (dingValue1[k][first1] == min[first1]) {
					if (allMin == 0) {
						value1[k][first1] = 0.01 * hSpace;// 最小的值
					} else {
						value1[k][first1] = Math.sqrt(allMin) * vSpace;
					}
				} else {
					double tem = (double) ((dingValue1[k][first1] - min[first1]) / (max[first1] - min[first1]));
					if (allMax != 0) {
						// 如果数字大于tempMax
						if (tem > allMax) {
							tem = allMax;
						}
					}
					if (allMin != 0) {
						// 如果数字小于tempMin
						if (tem < allMin) {
							tem = allMin;
						}
					}
					value1[k][first1] = Math.sqrt(tem) * vSpace;
				}
			}
			// System.out.println(max[first]+" "+min[first]+"***");
			first1++;// 下一行数据
		}

		// 转换数值把数值变成椭圆的宽value[][]

		/***********************************************************************
		 * ** ** ** ** ** ** ** ** ** ** ** ** ** 开始绘制椭圆 * 开始绘制椭圆 * 开始绘制椭圆 *
		 * 开始绘制椭圆 * ** ** ** ** ** ** ** ** ** ** ** ** **
		 **********************************************************************/

		// 1：重新设置笔划
		g2d.setFont(new Font("Courier New", Font.PLAIN, 12));
		g2d.setColor(this.pc.parseColor(this.cf.getRoundColor()));// 颜色设置

		// 2：打开反锯齿功能，用于平滑处理相关背景
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		// new一个可以用double类型画椭圆的对象
		Ellipse2D ellipse = new Ellipse2D.Double();

		for (int a = 0; a < value1.length; a++) {
			for (int aa = 0; aa < value1[a].length; aa++) {
				ellipse.setFrame(a * hSpace+strLength, (vSpace - value1[a][aa]) / 2 + aa
						* vSpace+hightLength, hSpace, value1[a][aa]);
				g2d.fill(ellipse);

			}
		}

		// 3：重新关闭反锯齿功能
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_OFF);

		// 恢复以前的AffineTransform对象（用于旋转）
		g2d.setTransform(old);

	}

	// 绘制图例
	// 绘制坐标轴标题

	public void getChart() {
		// 部署图形环境
		g2d.dispose();

		// 输出图像到WEB页面
		try {
			this.response.reset();
			this.response.setContentType("image/png");
			OutputStream sos = response.getOutputStream();
			ImageIO.write(image, "PNG", sos);

			sos.close();

		} catch (Exception e) {
			System.out.println("图表输出错误!");
		}
	}

	// 判断所给2维数组的最大值
	public int maxLength(double[][] count) {

		int length = count[0].length;

		for (int i = 1; i < count.length; i++) {

			length = Math.max(length, count[i].length);

		}

		return length;
	}

}
