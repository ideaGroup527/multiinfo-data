package org.forecast.parse;

import java.util.ArrayList;
import java.util.List;

public class ListDemo {
	public double[][] ListToDouble(List list){
		double [][] xx=new double[list.size()][((List)(list.get(0))).size()];
		
		for(int i=0;i<list.size();i++){
			for(int j=0;j<((List)(list.get(0))).size();j++){
				xx[i][j]=new Double(((List)(list.get(i))).get(j).toString());
//				System.out.println(xx[i][j]);
			}
		}
		return xx;
	}
	public List DoubletoList(double[]aa){
		List list = new ArrayList();
		for(int i=0;i<aa.length;i++){
//			list.add(new Double(aa[i]));
		}
		return list;
	}
	public List DoubletoList2(double[][]aa){
		List list = new ArrayList();
		for(int i=0;i<aa.length;i++){
			List list2 = new ArrayList();
			for(int j=0;j<aa[0].length;j++){
				list2.add(aa[i][j]);
			}
			list.add(list2);
		}
		return list;
	}
}
