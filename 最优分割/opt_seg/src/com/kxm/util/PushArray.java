package com.kxm.util;
//��double[][]������Ԫ��ȫ������һλ���������Ա�ƥ��Cut��
public class PushArray {
	public double[][] push(Double[][] before){
		double[][] after = new double[before.length+1][before[0].length+1];
		if(before.length!=0){
			for(int i=0;i<before.length;i++){
				Double[] temp = before[i];
				for(int j = 0;j<before[0].length;j++){
					after[i+1][j+1] = temp[j].doubleValue();
				}
			}
		}
		return after;
	}
}