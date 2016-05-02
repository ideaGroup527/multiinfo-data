package my_class;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

public class Calculate extends HttpServlet {
	public int m = 0, n = 0, k = 0, l = 0, li = 0, lj = 0, i = 0, i1 = 0,
			j = 0, key = 0, m1 = 0, m2 = 0, m3 = 0, l1 = 0, l2 = 0, l3 = 0,
			l4 = 0, l5 = 0, n1 = 0, n2 = 0;
	public int[] kk, km, kn, klj, kx1;
	public double ys = 0, ym = 0, ym1 = 0, ym2 = 0, s = 0, s1 = 0, s2 = 0;
	public double[] xa, xc, x1, x2, xlj;
	public double[][] x;

	public Calculate(Do_data data, int key) {
		this.key = key; // Key为统计量类型。=1:Q型距离；=2:Q型夹角；=3:Q型相关；=4:R型相关
		n = data.n;
		m = data.m;
		x = data.x;
		if (key > 3) { // 和vb源代码不一样
			double[][] x1 = new double[m + 1][n + 1];
			for (int i = 0; i <= n; i++) {
				for (int j = 0; j <= m; j++) {
					x1[j][i] = x[i][j];
				}
			}
			x = x1;
			n = x.length - 1;
			m = x[0].length - 1;
		}
		xa = new double[2 * n];
		xc = new double[2 * n];
		x1 = new double[2 * n];
		x2 = new double[2 * n];
		km = new int[2 * n];
		kn = new int[2 * n];
		kk = new int[2 * n];
		klj = new int[2 * n];
		xlj = new double[2 * n];
		kx1 = new int[2 * n];
		m1 = n - 1;
		m2 = n * 2 - 2;
		m3 = n * 2 - 1;
		for (i = 0; i <= 2 * n - 1; i++) {
			xa[i] = 0;
			xc[i] = 0;
			x1[i] = 0;
			x2[i] = 0;
			km[i] = 0;
			kn[i] = 0;
			kk[i] = 0;
			klj[i] = 0;
			xlj[i] = 0;
			kx1[i] = 0;
		}
		for (int i = 1; i <= n; i++) {
			kk[i] = 1;
		}
	}

	public void calulate_one() {
		if (key % 3 == 1) {
			ys = -1000000000;
		}
		if (key % 3 == 2) {
			ys = 1000000000;
			for (int i = 1; i <= n; i++) {
				for (int j = 1; j <= m; j++) {
					xc[i] = xc[i] + x[i][j] * x[i][j];
				}
			}
		}
		if (key % 3 == 0) {
			ys = 1000000000;
			for (int i = 1; i <= n; i++) {
				for (int j = 1; j <= m; j++) {
					xa[i] = xa[i] + x[i][j];
				}
				xa[i] = xa[i] / m;
				for (int j = 1; j <= m; j++) {
					xc[i] = xc[i] + Math.pow(x[i][j] - xa[i], 2);
				}
			}
		}
	}

	public void calulate_two(HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=gb2312");
		PrintWriter out = response.getWriter();
		for (l = 1; l <= m1; l++) {
			l1 = 0;
			l2 = 0;
			l3 = 0;
			l4 = 0;
			l5 = (l - 1) * 2;
			n1 = 0;
			n2 = 0;
			if (key % 3 == 1) {
				ym = 1000000000;
			} else {
				ym = -1000000000;
			}
			for (i = 2; i <= n; i++) {
				if (kk[i] == 0) {
					continue;
				}
				i1 = i - 1;
				for (j = 1; j <= i1; j++) {
					if (kk[j] == 0) {
						continue;
					}
					s1 = 0;
					if (key % 3 == 1) {
						for (int k = 1; k <= m; k++) {
							s1 = s1 + Math.pow(x[j][k] - x[i][k], 2);
						}
						s = Math.sqrt(s1);
						if (s < ym) {
							ym = s;
							li = i;
							lj = j;
						}
					}
					if (key % 3 == 2) {
						for (int k = 1; k <= m; k++) {
							s1 = s1 + x[j][k] * x[i][k];
						}
						s2 = Math.sqrt(xc[j] * xc[i]);
						s = s1 / s2;
						if (s > ym) {
							ym = s;
							li = i;
							lj = j;
						}
					}
					if (key % 3 == 0) {
						for (int k = 1; k <= m; k++) {
							s1 = s1 + (x[j][k] - xa[j]) * (x[i][k] - xa[i]);
						}
						s2 = Math.sqrt(xc[j] * xc[i]);
						s = s1 / s2;
						if (s > ym) {
							ym = s;
							li = i;
							lj = j;
						}
					}
				}
			}
			if (l == 1) {
				ym1 = ym;
			}
			if (l == m1) {
				ym2 = ym;
			}
			String suffix = "th";
			if (l % 10 == 1)
				suffix = "st";
			if (l % 10 == 2)
				suffix = "nd";
			if (l % 10 == 3)
				suffix = "rd";
			if (key % 3 == 1) {
				out.println("第" + l + "次合并类(The " + l + suffix
						+ " time of merger )：[" + li + "]--[" + lj
						+ "] 距离系数(distance coefficient)：" + ym + "<br>");
			}
			if (key % 3 == 2) {
				out.println("第" + l + "次合并类(The " + l + suffix
						+ " time of merger )：[" + li + "]--[" + lj
						+ "] 夹角余弦(angle cosine)：" + ym + "<br>");
			}
			if (key % 3 == 0) {
				out.println("第" + l + "次合并类(The " + l + suffix
						+ " time of merger )：[" + li + "]--[" + lj
						+ "] 相关系数(correlation coefficient)：" + ym + "<br>");
			}
			for (int j = 1; j <= m; j++) {
				x[lj][j] = (x[lj][j] * kk[lj] + x[li][j] * kk[li])
						/ (kk[li] + kk[lj]);
			}
			if (key % 3 == 2) {
				xc[lj] = 0;
				for (int j = 1; j <= m; j++) {
					xc[lj] = xc[lj] + x[lj][j] * x[lj][j];
				}
			}
			if (key % 3 == 0) {
				xa[lj] = 0;
				for (int j = 1; j <= m; j++) {
					xa[lj] = xa[lj] + x[lj][j];
				}
				xa[lj] = xa[lj] / m;
				xc[lj] = 0;
				for (int j = 1; j <= m; j++) {
					xc[lj] = xc[lj] + Math.pow(x[lj][j] - xa[lj], 2);
				}
			}
			for (k = 1; k <= n; k++) { // 分别处理
				if (km[k] == li) {
					for (j = k; j <= n; j++) { // 41
						if (km[j] == lj) {
							if (j - k != kk[lj]) { // 49
								l1 = k + 1; // 50
								l2 = j - kk[lj];
								l3 = l2 + 1;
								l4 = j;
								for (int j = l1; j <= l4; j++) {
									kn[j] = km[j];
								}
								n1 = kk[lj];
								for (int j = l1; j <= l2; j++) {
									km[j + n1] = kn[j];
								}
								n2 = l4 - k - n1;
								for (int j = l3; j <= l4; j++) {
									km[j - n2] = kn[j];
								}
								for (int j = 1; j <= l5; j++) {
									if ((kx1[j] >= l1 * 2 - 1)
											&& (kx1[j] <= l2 * 2 - 1)) {
										kx1[j] = kx1[j] + n1 * 2;
									} else if ((kx1[j] >= l3 * 2 - 1)
											&& (kx1[j] <= l4 * 2 - 1)) {
										kx1[j] = kx1[j] - n2 * 2;
									}
								} // 56
								for (int j = 1; j <= n; j++) {
									if ((klj[j] >= l1 * 2 - 1)
											&& (klj[j] <= l2 * 2 - 1)) {
										klj[j] = klj[j] + n1 * 2;
									} else if ((klj[j] >= l3 * 2 - 1)
											&& (klj[j] <= l4 * 2 - 1)) {
										klj[j] = klj[j] - n2 * 2;
									}
								} // 59
							}
							kx1[l * 2 - 1] = klj[li];
							x1[l * 2 - 1] = xlj[li];
							kx1[l * 2] = klj[lj];
							x1[l * 2] = xlj[lj];
							break;// 到77
						} else if (km[j] == 0) {
							l1 = k + 1; // 43
							l2 = j - 1;
							if (l2 >= l1) {
								for (int j = l1; j <= l2; j++) {
									kn[j] = km[j];
								}
								for (int j = l1; j <= l2; j++) {
									km[j + 1] = kn[j];
								}
								for (int j = 1; j <= l5; j++) {
									if (kx1[j] >= l1 * 2 - 1) {
										kx1[j] = kx1[j] + 2;
									}
								}
								for (int j = 1; j < n; j++) {
									if (klj[j] >= l1 * 2 - 1) {
										klj[j] = klj[j] + 2;
									}
								}
							}
							km[l1] = lj; // 48
							kx1[l * 2 - 1] = klj[li];
							x1[l * 2 - 1] = xlj[li];
							kx1[l * 2] = l1 * 2 - 1;
							x1[l * 2] = ym1;
							break;// 到77
						}
					}
				} else if (km[k] == lj) {
					for (j = k; j <= n; j++) { // 60
						if (km[j] == li) {
							l1 = k - kk[lj] + 1; // 67
							l2 = j - kk[li];
							l3 = l2 + 1;
							l4 = j;
							for (int j = l1; j <= l4; j++) {
								kn[j] = km[j];
							}
							n1 = kk[li];
							for (int j = l1; j <= l2; j++) {
								km[j + n1] = kn[j];
							}
							n2 = l3 - l1;
							for (int j = l3; j <= l4; j++) {
								km[j - n2] = kn[j];
							}
							for (int j = 1; j <= l5; j++) {
								if ((kx1[j] >= l1 * 2 - 1)
										&& (kx1[j] <= l2 * 2 - 1)) {
									kx1[j] = kx1[j] + n1 * 2;
								} else if ((kx1[j] >= l3 * 2 - 1)
										&& (kx1[j] <= l4 * 2 - 1)) {
									kx1[j] = kx1[j] - n2 * 2;
								}
							}
							for (int j = 1; j <= n; j++) {
								if ((klj[j] >= l1 * 2 - 1)
										&& (klj[j] <= l2 * 2 - 1)) {
									klj[j] = klj[j] + n1 * 2;
								} else if ((klj[j] >= l3 * 2 - 1)
										&& (klj[j] <= l4 * 2 - 1)) {
									klj[j] = klj[j] - n2 * 2;
								}
							} // 76
							kx1[l * 2 - 1] = klj[li];
							x1[l * 2 - 1] = xlj[li];
							kx1[l * 2] = klj[lj];
							x1[l * 2] = xlj[lj];
							break;// 到77
						} else if (km[j] == 0) {
							l1 = k - kk[lj] + 1; // 62
							l2 = j - 1;
							for (int j = l1; j <= l2; j++) {
								kn[j] = km[j];
							}
							for (int j = l1; j <= l2; j++) {
								km[j + 1] = kn[j];
							}
							km[l1] = li;
							for (int j = 1; j <= l5; j++) {
								if (kx1[j] >= l1 * 2 - 1) {
									kx1[j] = kx1[j] + 2;
								}
							}
							for (int j = 1; j <= n; j++) {
								if (klj[j] >= l1 * 2 - 1) {
									klj[j] = klj[j] + 2;
								}
							}
							kx1[l * 2 - 1] = l1 * 2 - 1;
							x1[l * 2 - 1] = ym1;
							kx1[l * 2] = klj[lj];
							x1[l * 2] = xlj[lj];
							break;// 到77
						}
					}
				} else if (km[k] == 0) { // 40
					km[k] = li;
					km[k + 1] = lj;
					kx1[l * 2 - 1] = (k - 1) * 2 + 1;
					x1[l * 2 - 1] = ym1;
					kx1[l * 2] = (k - 1) * 2 + 3;
					x1[l * 2] = ym1;
				} else {
					continue;
				}
				break;// 结束k下一个l
			}
			x2[l * 2 - 1] = ym; // 77
			x2[l * 2] = ym;
			klj[lj] = (kx1[l * 2] + kx1[l * 2 - 1]) / 2;
			xlj[lj] = ym;
			kk[lj] = kk[lj] + kk[li];
			kk[li] = 0;
			ys = ym;
		}
	}
}
