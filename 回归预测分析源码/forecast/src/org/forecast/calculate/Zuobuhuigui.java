package org.forecast.calculate;


import java.math.BigDecimal;

import org.forecast.util.zuobu;

public class Zuobuhuigui {

	public zuobu zuobuhuigui(double[][] xy, double f1, double f2) {
		zuobu zb = new zuobu();
		// b[m]为最后一个
		int n = xy.length;// n是观测数据的组数，即x数组的行数
		int y = xy[0].length;// y是自变量+因变量的个数，即x数组的列数
		int m = y - 1;// m是自变量的个数
		int imin = 0;
		int imax = 0;
		int i, j, k;
		double d;
		double[] vx = new double[y];
		double[][] r = new double[y][y];
		double Syy = 0.0;
		double Sy = 0;
		double v = 0.0;
		double ry12m = 0;
		double f12;
		double k12;
		double ss = 0.0;
		double vmax = 0;
		double vmin = 0;
		double bi = 0;
		double f = 0;
		// 求平均值，保存于Mx()
		double[] mx = new double[y];
		double[] b = new double[m + 1];
		double[] ri = new double[m];
		double[] vyx = new double[y];
		double[] ti = new double[m];

		for (i = 0; i < y; i++) {
			d = 0;
			for (k = 0; k < n; k++) {
				d = xy[k][i] + d;
			}
			mx[i] = CommonMethod.div(d, n);
		}
		// 计算离差矩阵，放在R的下三角部分
		for (k = 0; k < n; k++) {
			for (i = 0; i < y; i++) {
				d = xy[k][i] - mx[i];
				vx[i] = d;

				for (j = 0; j <= i; j++) {
					r[i][j] = CommonMethod.mul(d, vx[j]) + r[i][j];
				}

			}
		}

		for (i = 0; i < y; i++) {
			Syy = r[i][i];
			if (Syy == 0) {
				System.out.println("某变量为常数，无法计算相关系数！");
				return null;
			} else {
				vx[i] = Math.sqrt(Syy);
			}
		}
		// 计算相关矩阵，放在R的上三角部分
		for (i = 1; i < y; i++) {
			d = vx[i];
			for (j = 0; j < i; j++) {
				r[j][i] = CommonMethod.div(r[i][j], d * vx[j]);
			}
		}

		d = Math.sqrt(CommonMethod.div(1, n - 1));
		for (i = 0; i < y; i++) {
			vx[i] = d * vx[i];
			ss = vx[i];
			vyx[i] = r[i][y - 1];
		}
		for (i = 0; i < y; i++) {
			r[i][i] = 1;
			vyx[i] = vx[y-1] / vx[i];
			for (j = i + 1; j < y; j++) {
				r[j][i] = r[i][j];
			}
		}

		for (i = 0; i < y; i++) {
			for (j = 0; j < y; j++) {
				ss = r[i][j];
			}
		}

		// '法方程已建立，下面进入逐步计算
		// '计算各变量的贡献V，从已入选的变量中找出最小的V，从未选量中找出最大的V
		int l = 0;
		int sp = 0;
		double q = 1;

		for (;;) {
			sp = sp + 1;
			vmax = 0;
			vmin = 10;

			for (i = 0; i < m; i++) {
				ti[i] = 0;
				d = r[i][i];
				if (d > 0.00000001) {
					v = CommonMethod.div(r[y-1][i], d) * r[i][y-1];
					if (v < 0) {
						ti[i] = d;
						if (-v < vmin) {
							vmin = -v;
							imin = i+1;
						}
					} else if (v > vmax) {
						vmax = v;
						imax = i+1;
					}
				}

			}
			if (l != 0) {
				d = 0;
				for (i = 0; i < m; i++) {
					if (ti[i] == 0) {
						b[i] = 0;
						ri[i] = 0;

					} else {
						bi = r[i][y-1];
						b[i] = vyx[i] * bi;
						d = d + b[i] * mx[i];
						ri[i] = CommonMethod.div(bi, Math.sqrt(ti[i] * q + bi
								* bi));
						ti[i] =bi/Math.sqrt(ti[i]*q/(n-l-1));

					}
				}
				b[m] = mx[y-1] - d;
			}
			f12 = CommonMethod.div((n - l - 1) * vmin, q);
			if (f12 < f2) {
				l = l - 1;
				k = imin-1;
				k12 = -k;
			} else {
				f12 = CommonMethod.div((n - l - 1) * vmax, q - vmax);
				if (f12 <= f1 + 0.00000001) {
					break;
				} else {
					l = l + 1;
					k = imax-1;
					k12 = k;
				}
			}
			d = CommonMethod.div(1, r[k][k]);
			r[k][k] = 1;
			for (j = 0; j < y; j++) {
				r[k][j] = r[k][j] * d;
			}
			for (i = 0; i < y; i++) {
				if (i == k) {

				} else {
					d = r[i][k];
					r[i][k] = 0;
					for (j = 0; j < y; j++) {
						r[i][j] = r[i][j] - d * r[k][j];
					}
				}
			}
			q = r[y-1][y-1];
			ry12m = Math.sqrt(1 - q);
			f = CommonMethod.div((n - l - 1) * (1 - q), l * q);
			Sy = Math.sqrt(CommonMethod.div(Syy * q, n - l - 1));
		}
		if (l == 0) {
			System.out.println("在当前的F1、F2下，不能选出重要的变量！");
		}
		d = 0;
		for (i = 0; i < m; i++) {
			if (ti[i] == 0) {
				b[i] = 0;
				ri[i] = 0;
			} else {
				bi = r[i][y-1];
				b[i] = vyx[i] * bi;
				d = d + b[i] * mx[i];
				ri[i] = CommonMethod.div(bi, Math.sqrt(Math.abs(ti[i] * q + bi
						* bi)));
				ti[i] = bi/Math.sqrt(Math.abs(ti[i]*q/(n-l-1)));
				ti[i] = Math.abs(ti[i]);
			}
		}

		b[m] = mx[y-1] - d;
		zb.setB(b);
		zb.setF(f);
		zb.setL(l);
		zb.setTi(ti);
		return zb;
	}

}
