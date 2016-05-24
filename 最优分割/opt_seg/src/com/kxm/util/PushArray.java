package com.kxm.util;
//把double[][]型数组元素全部后移一位————以便匹配Cut类
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