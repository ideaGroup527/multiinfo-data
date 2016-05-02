package my_class;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Panel;
import java.awt.image.BufferedImage;

/**
 * 画板类
 */
public class Palette extends Panel {
	private Color color = null;
	private BasicStroke stroke = null;
	private BufferedImage image = null;
	private Calculate calculate = null;
	private String[][] x = null;
	private int xo = Parameters.xo;
	private int yo = Parameters.yo;
	private int language = 0;

	/**
	 * 构造一个画板类
	 * 
	 */
	public Palette(Calculate calculate, String x[][], int language) {
		this.calculate = calculate;
		this.x = x;
		this.language = language;
		color = new Color(0, 0, 0);
		stroke = new BasicStroke(2.0f, BasicStroke.CAP_ROUND,
				BasicStroke.JOIN_ROUND);
		image = new BufferedImage(1280 + xo, (Parameters.calculate.n + 2) * 30,
				BufferedImage.TYPE_INT_RGB);
		image.getGraphics().setColor(Color.white);
		image.getGraphics().fillRect(0, 0, 1280 + xo,
				(Parameters.calculate.n + 2) * 30);
	}

	/**
	 * 重写 paint 绘图方法
	 */
	public void paint(Graphics g, Graphics g1, Graphics g2, Graphics g3,
			Graphics g4, int[] titlecolor, int[] linescolor, int[] gradcolor,
			int[] textcolor, int size) {
		super.paint(g);
		Color titleColor = new Color(titlecolor[0], titlecolor[1],
				titlecolor[2]);
		Color linesColor = new Color(linescolor[0], linescolor[1],
				linescolor[2]);
		Color gradColor = new Color(gradcolor[0], gradcolor[1], gradcolor[2]);
		Color textColor = new Color(textcolor[0], textcolor[1], textcolor[2]);
		Graphics2D g2d = image.createGraphics();
		Graphics2D bg = (Graphics2D) g;
		Graphics2D title = (Graphics2D) g1;
		Graphics2D lines = (Graphics2D) g2;
		Graphics2D grad = (Graphics2D) g3;
		Graphics2D text = (Graphics2D) g4;
		bg.setColor(color);
		bg.setStroke(stroke);
		title.setColor(titleColor);
		title.setStroke(stroke);
		title.setFont(new Font("TimesRoman", Font.BOLD, size));
		lines.setColor(linesColor);
		lines.setStroke(stroke);
		grad.setColor(gradColor);
		grad.setStroke(stroke);
		text.setColor(textColor);
		text.setStroke(stroke);
		// -----------------------------------------------------
		int x1 = xo, y1 = yo, x2 = 0, y2 = yo - 10, ka = 0;
		int xx1 = xo, xx2 = xo + 100, yy1 = yo - 5;
		double yms = 0, ymd = 0, intxs = 0;
		double[] xs = new double[103];
		Line[] line = new Line[calculate.m3 + 1];
		for (int i = 0; i <= 102; i++) {
			xs[i] = 0;
		}
		for (int i = 0; i <= calculate.m3; i++) {
			line[i] = new Line(0, 0, 0, 0, 0);
		}
		// ------------------------------------------------------

		yms = calculate.ym1 - calculate.ym2;
		ymd = yms / 100;
		for (int i = 1; i <= calculate.m3; i++) { // 创建水平线条对象,calculate.kx1[i]即为line[].n为水平线行号。
			line[i].set((int) (1000 * (calculate.x1[i])), yo + 20 + 10
					* calculate.kx1[i], (int) (1000 * (calculate.x2[i])), yo
					+ 20 + 10 * calculate.kx1[i], calculate.kx1[i]);
		}
		for (int i = 1; i <= 102; i++) {
			xs[i] = calculate.ym1 - ymd * (i - 1);
		}
		for (int i = 1; i <= 10; i++) { // 画刻度线
			grad.drawLine(x1, y1, x1, y2);
			grad.drawLine(xx1, yy1, xx2, yy1);
			x1 += 100;
			xx1 += 100;
			xx2 += 100;
		}
		grad.drawLine(x1, y1, x1, y2);
		x1 = xo - 5;
		y1 = yo + 13;
		for (int i = 1; i <= 101; i += 10) { // 写刻度
			intxs = (int) (xs[i] * 1000 + 0.5);
			xs[i] = intxs / 1000;
			grad.drawString(String.valueOf(xs[i]), x1, y1);
			x1 += 100;
		}
		if (calculate.key < 4) {
			if (language == 0) {
				title.drawString("Q型聚类谱系图", 500 - yo, yo - 20);
				if (calculate.key % 3 == 1)
					grad.drawString("距离系数", xx1 - 20, yy1 + 30);
				if (calculate.key % 3 == 2)
					grad.drawString("夹角余弦", xx1 - 20, yy1 + 30);
				if (calculate.key % 3 == 0)
					grad.drawString("相关系数", xx1 - 20, yy1 + 30);
				text.drawString("序号", 0, yo + 10);
				text.drawString("样本号", 50, yo + 10);
			}
			if (language == 1) {
				title.drawString("Q-type Cluster Dendrogram", 450 - yo, yo - 20);
				if (calculate.key % 3 == 1)
					grad.drawString("Distance coefficient", xx1 - 80, yy1 + 30);
				if (calculate.key % 3 == 2)
					grad.drawString("Angle cosine", xx1 - 40, yy1 + 30);
				if (calculate.key % 3 == 0)
					grad.drawString("Correlation coefficient", xx1 - 85,
							yy1 + 30);
				text.drawString("Sample", 10, yo - 5);
				text.drawString("No", 0, yo + 10);
				text.drawString("Name", 50, yo + 10);
			}
			if (language == 2) {
				title.drawString("Q型聚类谱系图(Q-type Cluster Dendrogram)",
						400 - yo, yo - 20);
				if (calculate.key % 3 == 1)
					grad.drawString("距离系数(Distance coefficient)", xx1 - 135,
							yy1 + 30);
				if (calculate.key % 3 == 2)
					grad.drawString("夹角余弦(Angle cosine)", xx1 - 95, yy1 + 30);
				if (calculate.key % 3 == 0)
					grad.drawString("相关系数(Correlation coefficient)", xx1 - 140,
							yy1 + 30);
				text.drawString("序号", 0, yo - 15);
				text.drawString("样本号", 50, yo - 15);
				text.drawString("Sample", 10, yo);
				text.drawString("No", 0, yo + 15);
				text.drawString("Name", 50, yo + 15);
			}
		} else {
			if (language == 0) {
				title.drawString("R型聚类谱系图", 500 - yo, yo - 20);
				if (calculate.key % 3 == 1)
					grad.drawString("距离系数", xx1 - 20, yy1 + 30);
				if (calculate.key % 3 == 2)
					grad.drawString("夹角余弦", xx1 - 20, yy1 + 30);
				if (calculate.key % 3 == 0)
					grad.drawString("相关系数", xx1 - 20, yy1 + 30);
				text.drawString("序号", 0, yo + 10);
				text.drawString("变量名", 50, yo + 10);
			}
			if (language == 1) {
				title.drawString("R-type Cluster Dendrogram", 450 - yo, yo - 20);
				if (calculate.key % 3 == 1)
					grad.drawString("Distance coefficient", xx1 - 80, yy1 + 30);
				if (calculate.key % 3 == 2)
					grad.drawString("Angle cosine", xx1 - 40, yy1 + 30);
				if (calculate.key % 3 == 0)
					grad.drawString("Correlation coefficient", xx1 - 85,
							yy1 + 30);
				text.drawString("Variables", 8, yo - 5);
				text.drawString("No", 0, yo + 10);
				text.drawString("Name", 50, yo + 10);
			}
			if (language == 2) {
				title.drawString("R型聚类谱系图(R-type Cluster Dendrogram)",
						400 - yo, yo - 20);
				if (calculate.key % 3 == 1)
					grad.drawString("距离系数(Distance coefficient)", xx1 - 135,
							yy1 + 30);
				if (calculate.key % 3 == 2)
					grad.drawString("夹角余弦(Angle cosine)", xx1 - 95, yy1 + 30);
				if (calculate.key % 3 == 0)
					grad.drawString("相关系数(Correlation coefficient)", xx1 - 140,
							yy1 + 30);
				text.drawString("序号", 0, yo - 15);
				text.drawString("变量名", 50, yo - 15);
				text.drawString("Variables", 8, yo);
				text.drawString("No", 0, yo + 15);
				text.drawString("Name", 50, yo + 15);
			}
		}
		for (int i = 1; i <= calculate.m3; i++) {
			if (i % 2 == 1) { // 画聚类的序号和元素名称
				ka = ka + 1;
				if (Parameters.calculate.key < 4)
					text.drawString(x[calculate.km[ka]][0], 50, yo + 15 + 20
							* ka);
				else
					text.drawString(x[0][calculate.km[ka]], 50, yo + 15 + 20
							* ka);
				text.drawString(String.valueOf(calculate.km[ka]), 0, yo + 15
						+ 20 * ka);
			}
			if (calculate.ym2 == calculate.ym1) // 计算水平线X坐标
				x2 = x1 = xo;
			else {
				x1 = xo
						+ (int) ((line[i].x1 - calculate.ym1 * 1000) / (calculate.ym2 - calculate.ym1));
				x2 = x1
						+ (int) ((line[i].x2 - line[i].x1) / (calculate.ym2 - calculate.ym1));
			}
			if (calculate.x1[i] == calculate.ym1 && line[i].n % 2 == 1)
				x1 = xo - 3;
			lines.drawLine(x1, line[i].y1, x2, line[i].y2); // 画水平线
			if (i > 1 && calculate.x2[i] == calculate.x2[i - 1]) { // 前后画出两条右坐标相同水平线再这两条线之间画直线连接
				int ys = 0, yx = 0;
				yx = line[i].y1;
				ys = yx - (calculate.kx1[i] - calculate.kx1[i - 1]) * 10;
				lines.drawLine(x2, ys, x2, yx);
			}
		}
		g2d.drawImage(image, 0, 0, this);
	}

	/**
	 * 重写 update 方法
	 */
	public void update(Graphics g) {
		this.paint(g);
	}
}