package com.kxm.cut;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.StaticBucketMap;

import com.kxm.parse.ListToDouble;
import com.kxm.util.PushArray;
/**
 * Cut类用于进行最优分割的核心计算
 * @author kexinmei
 * @author kexinmeip@126.com
 * @version 1.0
 * */
public class Cut {
	static List result = null;  //详细分段结果
	static List printResult = null; //绘图所需结果
	
	static double[][] e1 = new double[100][500];
	static double[][] e2 = new double[100][500];
	static double[][] R = new double[100][100];
	static double[] T = null;
	static double[] S = null;
	static double[] D1 = null;
	
	/**
	 * 初始化变量
	 * @param n 行数
	 */
	public static void init(int n){
		T = new double[n+1];
		S = new double[n];
		D1 = new double[n*(n + 1)/2];
		
		for(int i = 0 ; i < T.length ; i++){
			T[i] = 0.0;
		}
		
		for(int i = 0 ; i < S.length ; i++){
			S[i] = 0.0;
		}
		
		for(int i = 0 ; i < D1.length ; i++){
			D1[i] = 0.0;
		}
	}
	public static List getResult() {
		return result;
	}
	public static void setResult(List result) {
		result = result;
	}
	public static List getPrintResult() {
		return printResult;
	}
	public static void setPrintResult(List printResult) {
		printResult = printResult;
	}
	/**
	 * cut方法用于计算 2 至 cutnum 段分割的结果 结果存放在result集合中
	 * @param 参数为一个保存用户数据库数据的List value，以及int cutnum参数（分割段数）
	 * @return 无返回值
	 * */
	public static void cut(List value,int cutnum){
		int i = 0,j = 0,l = 0;//循环变量
		double a = 0,b = 0;  //a存放分割段的开始下标 b存放结束下标
		double p = 0,q = 0;
		/*
		 * 处理value 变List为Double[][]
		 * */
		ListToDouble ltd = new ListToDouble();
		Double[][] tmpvalue = ltd.parse(value);
		PushArray pa = new PushArray();
		double[][] varray = pa.push(tmpvalue);
		
		System.out.println(ListToDouble.hasNaN(varray));
		/*
		 * 求数据行数n,列数m
		 * */
		int m = varray.length;
		int n = varray[0].length;
		init(n);
		n--;
		m--;
		/*
		 * 数据正规化
		 * */
		double XMA = 0,XMI = 0;
		for(i = 1;i <= m;i++){
			XMA = varray[i][1];
			XMI = varray[i][1];
			for(j = 2;j <= n;j++){
				if(XMA <= varray[i][j]){
					XMA = varray[i][j];
				}
				if(XMI > varray[i][j]){
					XMI = varray[i][j];
				}
			}
			for(j = 1;j <= n;j++){
				if(XMA!=XMI){
					varray[i][j] = (varray[i][j] - XMI)/(XMA - XMI);
				}
			}
		}
		/*
		 * 计算第i个样品至第j个样品子段内的离差平方和
		 * */
		for(j = 1;j <= n;j++){
			T[j+1] = 0;
			S[j] = 0;
			for(i = j;i>=1;i--){
				q = 0;
				for(l = 1;l <= m;l++){
					double tmp = varray[l][i] - varray[l][j];
					System.out.println(tmp);
					if(tmp != 0)
						q += Math.pow(tmp,2);
				}
				if(Double.isNaN(q))
					System.out.println(varray[l-1][i] - varray[l-1][j]);
				T[i] = q + T[i+1];
				a = T[i] + S[i];
				if(Double.isNaN(a))
					a = a + a - a;
				S[i] = a;
				D1[n*(i - 1) + j - (i - 1)*i / 2] = a/(j - i + 1);
			}
		}
		/*
		 * 计算各子段最优2分割的离差和分割点
		 * */
		for(i = 1;i <= n-1;i++){
			for(j = 1;j <= (n - i);j++){
				S[j] = D1[j] + D1[n * j + n - i + 1 - j*(j + 1)/2];
			}
			a = S[1];
			e1[0][i] = 1;
//			e1[i][0] = 1;
			for(j = 1;j <= n - i;j++){
				if(a > S[j]){
					a = S[j];
					e1[0][i] = j;
				}
			}
			T[n - i] = a;
		}
		//计算各子段最优 3 至 K 分割的离差和分割点
		for(l = 2;l <= (cutnum - 1);l++){
			for(i = 1;i <= (n - l);i++){
				for(j = 1;j <= (n - l - i + 1) ; j++){
					S[j] = T[j] + D1[n * (l + j - 1) + n - i + 1 - (l + j)*(l + j - 1)/2];
				}
				a = S[1];
				e1[l - 1][i] = l;
				for(j = 1;j<=( n - l - i + 1);j++){
					if(a > S[j]){
						a = S[j];
						e1[l - 1][i] = j + l - 1;
					}
				}
				T[n - i - l + 1] = a;
			}
		}
		for(i = 0; i <= cutnum;i++){
			for(j = 0;j <= n;j++){
				e2[i][j] = e1[i][j];
			}
		}
		result = new ArrayList();
		printResult = new ArrayList();
		for(l = 2;l <= cutnum;l++){
			q = n; p = 1;
			for(j = 0;j <= cutnum;j++){
				R[j][l-1] = 0;
			}
			for(i = 0;i <= l-2 ;i++){
				q = e1[l - i - 2][(int)(n - q + 1)];//(改) 精度
				R[i + 1][l - 1] = q;
			}
		
			List aCut = new ArrayList(); //一次分割 包括几个段（List）
			//求除最后一个外的分割段
			for(i = l-1;i >= 1;i--){ 
				a = R[i][l - 1];
				b = R[i + 1][l - 1] + 1;
				List seg = new ArrayList();  //一段
				seg.add(Double.valueOf(p));  //第p段
				seg.add(Double.valueOf(b));  //从b开始
				seg.add(Double.valueOf(a));  //到a
				seg.add(Double.valueOf( D1[(int)(n*(b - 1) + a - (b - 1) * b/2)]) ); //离差 
				aCut.add(seg); //把该段加入到这次分割中
				p++;
			}
			//最后一段单独求
			List seg = new ArrayList();
			seg.add(Double.valueOf(p));
			seg.add(Double.valueOf(a+1));
			seg.add(Double.valueOf((double)n));
			seg.add(Double.valueOf( D1[(int)(n * a + n - a * (a + 1) / 2)]));//离差
			aCut.add(seg); //把最后一段添加到这次分割中
			result.add(aCut); //把这次分割结果添加到结果集中
			
			/*
			 * 处理printResult需要的数据 仅需离差平方总和
			 * */
			printResult.add(Double.valueOf(T[n - l + 1]));
		}
	}
	

}
