package org.forecast.calculate;



import java.math.BigDecimal;

public class CommonMethod {
	private static final int DEF_DIV_SCALE = 10;
	/**
	 * ����һά�����ƽ��ֵ
	 * 
	 * @param vals
	 *            double[]
	 * @return avg �������õ�ƽ��ֵ,double
	 * @exception IndexOutOfBoundsException
	 *                �����±�Խ��ʱ�׳����쳣
	 * @exception NullPointerException
	 *                �����õ�����Ϊ��ʱ�׳����쳣
	 */
	public static double avgOfArray(double[] vals) throws NullPointerException,
			IndexOutOfBoundsException {
		double sum = 0;
		for (int i = 0; i < vals.length; i++) {
			sum += vals[i];
		}
		return CommonMethod.div(sum, vals.length);
	}

	/**
	 * �����ά�����ƽ��ֵ
	 * 
	 * @param vals
	 * @return
	 * @throws NullPointerException
	 * @throws IndexOutOfBoundsException
	 */
	public static double[] avgOfArray(double[][] vals)
			throws NullPointerException, IndexOutOfBoundsException {
		// ��
		int n = vals.length;
		int m = vals[0].length;

		double[] avg = new double[m];

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				avg[i] += vals[j][i];
			}
			avg[i]=CommonMethod.div(avg[i], n);
		}
		return avg;
	}

	/**
	 * ����̬�ֲ��ķ�λ��
	 * 
	 * @param Q
	 *            �ϲ���ʣ�0 < Q < 1
	 * @return ��λ��
	 */
	private static double pNorm(double Q) throws Exception {
		double p = 0, y = 0, z = 0, x = 0, b0, b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b;
		// ��b-b10��ֵ
		b0 = 1.570796288;
		b1 = 0.03706987906;
		b2 = -0.0008364353589;
		b3 = -0.0002250947176;
		b4 = 0.000006841218299;
		b5 = 0.000005824238515;
		b6 = -0.00000104527497;
		b7 = 8.360937017E-08;
		b8 = -3.231081277E-09;
		b9 = 3.657763036E-11;
		b10 = 6.936233982E-13;
		// ��ʼ�����λ��x
		if (Q != 0.5) {
			if (Q > 0.5) {
				p = CommonMethod.sub(1, Q);
			} else {
				p = Q;
			}
			y = -Math.log(4 * p * (1 - p));
			b = y * (b9 + y * b10);
			b = y * (b8 + b);
			b = y * (b7 + b);
			b = y * (b6 + b);
			b = y * (b5 + b);
			b = y * (b4 + b);
			b = y * (b3 + b);
			b = y * (b2 + b);
			b = y * (b1 + b);
			z = y * (b0 + b);
			x = Math.sqrt(z);
			if (Q > 0.5) {
				x = -x;
			}
		} else {
			x = 0;
		}
//		System.out.println("��̬�ֲ���λ����"+x);
		return x;
	}

	/**
	 * ����GAMMA����
	 * 
	 * @param x
	 *            �Ա���
	 * @return Gamma����ֵ
	 */
	private static double gamma(double x) throws Exception {
		double H, y = 0, y1 = 0, z = 0;
		H = 1;
		y = x;
		// ��yֵ������2 <= y < 3ʱ��ͨ�����¼���ʹ��ֵ���������
		while (y < 2 || y >= 3) {
			if (y < 2) {
				H = div(H,y);
				y = y + 1;
			} else if (y >= 3) {
				y = y - 1;
				H = mul(H,y);
			}
		}
		// yֵ����2 <= y < 3ʱ����z��ֵ
		if (y > 2 && y < 3) {
			y = y - 2;
			y1 = mul(y,add(0.005159,mul(y,0.001606)));
			y1 = mul(y,add(0.004451,y1));
			y1 = mul(y,add(0.07211,y1));
			y1 = mul(y,add(0.082112,y1));
			y1 = mul(y,add(0.41174,y1));
			y1 = mul(y,add(0.422787,y1));
			H = mul(y,add(0.999999,y1));
			z = H;
		} else if (y == 2) {
			z = H;
		}
		return z;
	}

	/**
	 * ��gamma�����Ķ���
	 * 
	 * @param x
	 *            �Ա���
	 * @return gamma�����Ķ���
	 */
	private static double lnGamma(double x) throws Exception {
		double g = 0, y = 0, z = 0, a = 0, b = 0, b1 = 0;
		int n = 0;

		if (x < 8) {
			y = x + 8;
			n = -1;
		} else {
			y = x;
			n = 1;
		}
		z = div(1,mul(y,y));
		a = (y - 0.5) * Math.log(y) - y + 0.9189385;
		b1 = mul(sub(mul(0.0007663452,z),0.0005940956),z);
		b1 = mul(add(b1,0.0007936431),z);
		b1 = mul(sub(b1,0.002777778),z);
		b = div(add(b1,0.0833333),y);
		g = a + b;
		if (n < 0) {
			y = y - 1;
			a = y;
			for (int i = 1; i <= 7; i++) {
				a = a * (y - i);
			}
			g = g - Math.log(a);
		}

		return g;
	}

	/**
	 * ����F�ֲ��ķֲ��������²����
	 * 
	 * @param n1
	 *            ���ɶ�
	 * @param n2
	 *            ���ɶ�
	 * @param f
	 *            Fֵ
	 * @return F�ֲ��ķֲ��������²����
	 */
	private static double f_p_Dist(double n1, double n2, double f) throws Exception {
		double p = 0, d = 0, x = 0, u = 0, lu = 0, nn1 = 0, nn2 = 0;
		int IAI = 0, IBI = 0;
		final double PI = 3.14159265359;
		// ��f=0 �򷵻�p=0
		if (f == 0) {
			p = 0;
			d = 0;// ddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd
			return p;
		}
		x =  div(n1 * f,(n2 + n1 * f));
		/*
		 * ����n1,n2����ż��ִ�в�ͬ�ļ���
		 */
		// ��n1Ϊż��
		if (n1 % 2 == 0) {
			// ��n2Ϊż��
			if (n2 % 2 == 0) {
				u = x * (1 - x);
				p = x;
				IAI = 2;
				IBI = 2;
			}
			// ��n2Ϊ����
			else {
				u = div(x * Math.sqrt(1 - x), 2);
				p = 1 - Math.sqrt(1 - x);
				IAI = 2;
				IBI = 1;
			}
		}
		// ��n1Ϊ����
		else {
			// ��n2Ϊż��
			if (n2 % 2 == 0) {
				p = Math.sqrt(x);
				u = div(p * (1 - x) , 2);
				IAI = 1;
				IBI = 2;
			}
			// ��n2Ϊ����
			else {
				u = div(Math.sqrt(x * (1 - x)) , PI);
				p = 1 - div(2 * Math.atan(Math.sqrt(div((1 - x) , x))) , PI);
				IAI = 1;
				IBI = 1;
			}
		}
		nn1 = n1 - 2;
		nn2 = n2 - 2;
		// u=0ʱ����p��������
		if (u == 0) {
			d = div(u,f); // dddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd
			return p;
		} else {
			lu = Math.log(u);
		}
		// ��IAI != n1ʱִ�����¼���
		if (IAI != n1) {
			for (int i = IAI; i <= nn1; i += 2) {
				p = p - div(2 * u , i);
				lu = lu + Math.log((1 + div(IBI , i)) * x);
				u = Math.exp(lu);
			}
		}
		// ��IBI == n2 ����p��������
		if (IBI == n2) {
			d = div(u , f); // dddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd
			return p;
		}
		for (int i = IBI; i <= nn2; i += 2) {
			p = p + div(2 * u , i);
			lu = lu + Math.log((1 + div(n1 , i)) * (1 - x));
			u = Math.exp(lu);
		}
		d = div(u,f); // ddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd
		return p;
	}

	/**
	 * ����F�ֲ��ķֲ������ĸ����ܶ�
	 * 
	 * @param n1
	 *            ���ɶ�
	 * @param n2
	 *            ���ɶ�
	 * @param f
	 *            Fֵ
	 * @return F�ֲ��ķֲ������ĸ����ܶ�
	 */
	private static double f_d_Dist(double n1, double n2, double f) throws Exception {
		double x = 0, u = 0, Lu = 0, p, d, nn1, nn2;
		int IAI, IBI;
		final double PI = 3.14159265359;

		if (f == 0) {
			p = 0;
			d = 0;
			return d;
		}

		x = div(n1 * f , (n2 + n1 * f));
		if ((n1 / 2) * 2 == n1) {
			if ((n2 / 2) * 2 == n2) {
				u = x * (1 - x);
				p = x;
				IAI = 2;
				IBI = 2;
			} else {
				u = div(x * Math.sqrt(1 - x) , 2);
				p = 1 - Math.sqrt(1 - x);
				IAI = 2;
				IBI = 1;
			}
		} else {
			if ((n2 / 2) * 2 == n2) {
				p = Math.sqrt(x);
				u = div(p * (1 - x) , 2);
				IAI = 1;
				IBI = 2;
			} else {
				u = div(Math.sqrt(x * (1 - x)) , PI);
				p = 1 - div(2 * Math.atan(Math.sqrt(div((1 - x) , x))) , PI);
				IAI = 1;
				IBI = 1;
			}
		}
		nn1 = n1 - 2;
		nn2 = n2 - 2;

		if (u == 0) {
			d = div(u,f);
			return d;
		} else {
			Lu = Math.log(u);
		}

		if (IAI != n1) {
			for (int i = IAI; i <= nn1; i += 2) {
				p = p - div(2 * u , i);
				Lu = Lu + Math.log((1 + div(IBI , i)) * x);
				u = Math.exp(Lu);
			}
		}
		if (IBI == n2) {
			d = div(u,f);
			return d;
		}
		for (int i = IBI; i <= nn2; i += 2) {
			p = p + 2 * div(u,i);
			Lu = Lu + Math.log((1 + div(n1,i)) * (1 - x));
			u = Math.exp(Lu);
		}
		d = div(u,f);
		return d;
	}

	/**
	 * ����F�ֲ��ķ�λ��
	 * 
	 * @param n1
	 *            �ع鷽�����ɶ�
	 * @param n2
	 *            ʣ�෽�����ɶ�
	 * @param Q
	 *            �ϲ����
	 * @return F�ֲ��ķ�λ��
	 */
	public static double pF_Dist(double n1, double n2, double Q) throws Exception {
		double DF12, DF22, A, b, F = 0;
		double A1, b1, p, YQ;
		double E, FO, pp, d;
		double GA1, GA2, GA3;
		DF12 = div(n1,2);
		DF22 = div(n2,2);
		A = div(2, 9*n1);
		A1 = 1 - A;
		b= div(2, 9*n2);
		b1 = 1 - b;
		p = 1 - Q;
		YQ = pNorm(Q);
		E = b1 * b1 - b * YQ * YQ;
//		System.out.println("b1=" + b1);
//		System.out.println("b=" + b);
//		System.out.println("YQ=" + YQ);
//		System.out.println("n1=" + n1);
//		System.out.println("n2=" + n2);
		if (E > 0.8) {
			
			FO =Math.pow(
					div((A1 * b1 + YQ * Math.sqrt(A1 * A1 * b + A * E)) , E), 3);
		} else {
			GA1 = lnGamma(DF12 + DF22);
			GA2 = lnGamma(DF12);
			GA3 = lnGamma(DF22);
			FO = (2 / n2)
					* (GA1 - GA2 - GA3 + 0.69315 + (DF22 - 1) * Math.log(n2)
							- DF22 * Math.log(n1) - Math.log(Q));
			FO = Math.exp(FO);
		}
		for (int k = 1; k <= 30; k++) {
			pp = f_p_Dist(n1, n2, FO);
			d = f_d_Dist(n1, n2, FO);
			if (d == 0) {
				F = FO;
				return F;
			}
			F = FO - div((pp - p) , d);
			if (Math.abs(FO - F) < 0.000001 * Math.abs(F)) {
				return F;
			} else {
				FO = F;
			}
		}

		return F;
	}

	/**
	 * ����t�ֲ��ķֲ��������²����
	 * 
	 * @param n
	 *            ���ɶ�
	 * @param T
	 *            tֵ
	 * @return pp �²����
	 */
	private static double t_p_Dist(double n, double t) throws Exception {
		int Sign, IBI;
		double TT, x, p, u, GA1, GA2, pp = 0, dd = 0, N2;
		final double PI = 3.14159265359;

		if (t == 0) {
			GA1 = gamma(div(n,2));
			GA2 = gamma(div(n,2) + 0.5);
			pp = 0.5;
			dd = div(GA2 , Math.sqrt(n * PI) * GA1);
			
			return pp;
		}
		if (t < 0) {
			Sign = -1;
		} else {
			Sign = 1;
		}
		TT = t * t;
		x = div(TT , (n + TT));
		if (n % 2 == 0) {
			p = Math.sqrt(x);
			
			u = div(p * (1 - x) , 2);
			
			IBI = 2;
		} else {
			u = Math.sqrt(x * (1 - x))/ PI;
			p = 1 - 2 * Math.atan(Math.sqrt((1 - x) / x)) / PI;
			IBI = 1;
			
		}
		if (IBI != n) {
			N2 = n - 2;
			for (int i = IBI; i <= N2; i += 2) {
				p = p + 2 * u / i;
				u = u * (1 + i) / i * (1 - x);
				
			}
		}
		dd = div(u , Math.abs(t));
		pp = 0.5 + div(Sign * p , 2);
		return pp;
	}

	/**
	 * ����t�ֲ��ķֲ������ĸ����ܶ�
	 * 
	 * @param n
	 *            ���ɶ�
	 * @param T
	 *            tֵ
	 * @return dd �����ܶ�
	 */
	private static double t_d_Dist(double n, double t) throws Exception {
		int Sign, IBI;
		double TT, x, p, u, GA1, GA2, pp = 0, dd = 0, N2;
		final double PI = 3.14159265359;

		if (t == 0) {
			GA1 = gamma(n / 2);
			GA2 = gamma(n / 2 + 0.5);
			pp = 0.5;
			dd = div(GA2 , (Math.sqrt(n * PI) * GA1));
			return dd;
		}
		if (t < 0) {
			Sign = -1;
		} else {
			Sign = 1;
		}
		TT = t * t;
		x = div(TT , (n + TT));
		if (n % 2 == 0) {
			p = Math.sqrt(x);
			u = p * (1 - x) / 2;
			IBI = 2;
		} else {
			u = div(Math.sqrt(x * (1 - x)) , PI);
			p = 1 - div(2 * Math.atan(Math.sqrt(div((1 - x) , x))) , PI);
			IBI = 1;
		}
		if (IBI != n) {
			N2 = n - 2;
			for (int i = IBI; i <= N2; i += 2) {
				p = p + div(2 * u , i);
				u = div(u * (1 + i) , i * (1 - x));
			}
		}
		dd = div(u , Math.abs(t));
		pp = 0.5 + div(Sign * p , 2);
		return dd;
	}

	/**
	 * ��t�ֲ��ķ�λ��
	 * 
	 * @param n
	 *            ���ɶ�
	 * @param Q
	 *            �ϲ����(<=0.5)
	 * @return t�ֲ��ķ�λ��
	 */
	public static double pT_Dist(double n, double Q) throws Exception {
		double PIS, DFR2, C;
		double Q2, p, YQ, E;
		double GA1, GA2, GA3;
		double t = 0, T0, pp, d;
		final double PI = 3.14159265359;

		PIS = Math.sqrt(PI);
		DFR2 = n / 2;
		if (n == 1) {
			t = Math.tan(PI * (0.5 - Q));
			return t;
		}
		if (n == 2) {
			if (Q > 0.5) {
				C = -1;
			} else {
				C = 1;
			}
			Q2 = Math.pow((1 - 2 * Q), 2);
			t = Math.sqrt(div(2 * Q2 , (1 - Q2))) * C;
			return t;
		}
		p = 1 - Q;
		YQ = pNorm(Q);
		E = Math.pow((1 - div(1 , (4 * n))), 2) - div(YQ * YQ , (2 * n));
		if (E > 0.5) {
			T0 = div(YQ , Math.sqrt(E));
		} else {
			GA1 = lnGamma(DFR2);
			GA2 = lnGamma(DFR2 + 0.5);
			GA3 = Math.exp(div(GA1 - GA2 , n));
			T0 = div(div(Math.sqrt(n) , Math.pow((PIS * Q * n), div(1 , n))) , GA3);
		}
		for (int k = 1; k <= 30; k++) {
			pp = t_p_Dist(n, T0);
			d = t_d_Dist(n, T0);
			if (d == 0) {
				t = T0;
				return t;
			}
			t = T0 - div((pp - p) , d);
			if (Math.abs(T0 - t) < 0.000001 * Math.abs(t)) {
				return t;
			} else {
				T0 = t;
			}
		}
		return t;
	}

	/**
	 * ���Ԫһ�η���
	 * @param double[][] �Ա����������
	 * @return double[] �Ա���ϵ��
	 */
	public static double[] calxishu(double[] args) throws Exception {
		double[][] param = { { 1,2,1,28 }, { 2,3,4,67 }, { 1,1,1,27.9} };
		double[] result = new double[param.length];
		double a = calc(param);
		for (int i = 0; i < param.length; i++) {
			double[][] param2 = new double[param.length][param.length];
			for (int m = 0; m < param2.length; m++) {
				for (int n = 0; n < param2[m].length; n++) {
					if (n == i) {
						param2[m][n] = param[m][param[m].length - 1];
					} else {
						param2[m][n] = param[m][n];
					}
				}
			}
			result[i] = calc(param2) / a;
		}

		return result;
	}

	private static double calc(double[][] param) {
		double sum = 0;
		int length = param.length;
		for (int i = 0; i < length; i++) {
			int x = i;
			int tmp = 1, tmp2 = 1;
			for (int m = 0; m < length; m++) {
				tmp *= param[(x + m) % length][m];
				tmp2 *= param[(x + m) % length][Math.abs(length - 1 - m)
						% length];
			}
			sum += tmp;
			sum -= tmp2;
		}
		return sum;
	}
	/**
	   * �ṩ��ȷ�ļӷ����㡣
	   * @param v1 ������
	   * @param v2 ����
	   * @return ���������ĺ�
	   */
	  public static double add(double v1,double v2){
	    BigDecimal b1 = new BigDecimal(Double.toString(v1));
	    BigDecimal b2 = new BigDecimal(Double.toString(v2));
	    return b1.add(b2).doubleValue();
	  }

	  /**
	   * �ṩ��ȷ�ļ������㡣
	   * @param v1 ������
	   * @param v2 ����
	   * @return ���������Ĳ�
	   */
	  public static double sub(double v1,double v2){
	    BigDecimal b1 = new BigDecimal(Double.toString(v1));
	    BigDecimal b2 = new BigDecimal(Double.toString(v2));
	    return b1.subtract(b2).doubleValue();
	  }

	  /**
	   * �ṩ��ȷ�ĳ˷����㡣
	   * @param v1 ������
	   * @param v2 ����
	   * @return ���������Ļ�
	   */

	public static double mul(double v1,double v2){
	    BigDecimal b1 = new BigDecimal(Double.toString(v1));
	    BigDecimal b2 = new BigDecimal(Double.toString(v2));
	    return b1.multiply(b2).doubleValue();
	  }

	  /**
	   * �ṩ����ԣ���ȷ�ĳ������㣬�����������������ʱ����ȷ��
	   * С�����Ժ�10λ���Ժ�������������롣
	   * @param v1 ������
	   * @param v2 ����
	   * @return ������������
	   */
	  public static double div(double v1,double v2){
	    return div(v1,v2,DEF_DIV_SCALE);
	  }

	  /**
	   * �ṩ����ԣ���ȷ�ĳ������㡣�����������������ʱ����scale����ָ
	   * �����ȣ��Ժ�������������롣
	   * @param v1 ������
	   * @param v2 ����
	   * @param scale ��ʾ��ʾ��Ҫ��ȷ��С�����Ժ�λ��
	   * @return ������������
	   */
	  public static double div(double v1,double v2,int scale){
	    if(scale<0){
	      throw new IllegalArgumentException(
	      "The scale must be a positive integer or zero");
	    }
	    BigDecimal b1 = new BigDecimal(Double.toString(v1));
	    BigDecimal b2 = new BigDecimal(Double.toString(v2));
	    return b1.divide(b2,scale,BigDecimal.ROUND_HALF_UP).doubleValue();
	  }

	  /**
	   * �ṩ��ȷ��С��λ�������봦��
	   * @param v ��Ҫ�������������
	   * @param scale С���������λ
	   * @return ���������Ľ��
	   */
	  public static double round(double v,int scale){
	    if(scale<0){
	      throw new IllegalArgumentException(
	      "The scale must be a positive integer or zero");
	    }
	    BigDecimal b = new BigDecimal(Double.toString(v));
	    BigDecimal one = new BigDecimal("1");
	    return b.divide(one,scale,BigDecimal.ROUND_HALF_UP).doubleValue();
	  }

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		double[][] aa = { { 274, 2450 }, { 180, 3254 }, { 375, 3802 },
				{ 205, 2838 }, { 86, 2347 }, { 265, 3782 }, { 98, 3008 },
				{ 330, 2450 }, { 195, 2137 }, { 53, 2560 }, { 430, 4020 },
				{ 372, 4427 }, { 236, 2660 }, { 157, 2088 }, { 370, 2605 } };
		double[] bb = { 162, 120, 223, 131, 67, 169, 81, 192, 116, 55, 252,
				232, 144, 103, 212 };
		
	}

}
