package my_class;

public class Do_data {
	public int m = 0, n = 0, i = 0, j = 0, type = 0;
	public double[][] x;

	public Do_data(double[][] x, int type) {
		this.x = x;
		this.type = type;
		n = x.length - 1;
		m = x[0].length - 1;
	}

	public void do_data() {
		if (type == 1) { // 数据的标准差标准化变换
			double ave = 0, s = 0;
			for (j = 1; j <= m; j++) {
				ave = 0;
				for (i = 1; i <= n; i++) {
					ave = ave + x[i][j];
				}
				ave = ave / n;
				s = 0;
				for (i = 1; i <= n; i++) {
					s = s + (x[i][j] - ave) * (x[i][j] - ave);
				}
				s = Math.sqrt(s / n);
				if (s == 0) {
					for (i = 1; i <= n; i++) {
						x[i][j] = 0;
					}
				} else {
					for (i = 1; i <= n; i++) {
						x[i][j] = (x[i][j] - ave) / s;
					}
				}
			}
		}
		if (type == 2) { // 数据的极差标准化变换
			double xMax = 0, xMin = 0, ave = 0, d = 0;
			for (j = 1; j <= m; j++) {
				ave = 0;
				for (i = 1; i <= n; i++) {
					ave = ave + x[i][j];
				}
				ave = ave / n;
				xMax = -100000000;
				xMin = 100000000;
				for (i = 1; i <= n; i++) {
					if (x[i][j] > xMax) {
						xMax = x[i][j];
					}
					if (x[i][j] < xMin) {
						xMin = x[i][j];
					}
				}
				d = xMax - xMin;
				if (d == 0) {
					for (i = 1; i <= n; i++) {
						x[i][j] = 1;
					}
				} else {
					for (i = 1; i <= n; i++) {
						x[i][j] = (x[i][j] - ave) / d;
					}
				}
			}
		}
		if (type == 3) { // 数据的极差正规化变换
			double xMax = 0, xMin = 0, d = 0;
			for (j = 1; j <= m; j++) {
				xMax = -100000000;
				xMin = 100000000;
				for (i = 1; i <= n; i++) {
					if (x[i][j] > xMax) {
						xMax = x[i][j];
					}
					if (x[i][j] < xMin) {
						xMin = x[i][j];
					}
				}
				d = xMax - xMin;
				if (d == 0) {
					for (i = 1; i <= n; i++) {
						x[i][j] = 0;
					}
				} else {
					for (i = 1; i <= n; i++) {
						x[i][j] = (x[i][j] - xMin) / d;
					}
				}
			}
		}

	}
}
