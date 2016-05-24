package com.kxm.parse;

import java.util.List;
//处理多列
public class ListToDouble {
	public Double[][] parse(List before){
		List temp = (List)before.get(0);
		Double[][] after = new Double[before.size()][temp.size()];
		//Double[] col = new Double[temp.size()];
		for(int i=0; i<before.size(); i++){
		    temp = (List)before.get(i);
		    //col = new Double[temp.size()];
		    for(int j=0;j<temp.size();j++){
		    	after[i][j] = (Double) temp.get(j);
		    }
		}
		return after;
	}
	public static boolean hasNaN(List before){
		List temp = (List)before.get(0);
		for(int i=0; i<before.size(); i++){
		    temp = (List)before.get(i);
		    for(int j=0;j<temp.size();j++){
		    	if(Double.isNaN((Double) temp.get(j)))
		    		return true;
		    }
		}
		return false;
	}
	public static boolean hasNaN(double[][] before){
		for(double[] col : before){
			for(double i : col){
				if(Double.isNaN(i)){
					return true;
				}
			}
		}
		return false;
	}
}
