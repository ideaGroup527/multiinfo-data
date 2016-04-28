package org.forecast.calculate;

public class CalRet {
	public double cal(double[] indvars, double [] indvarsdata, double [] t, double t005){
		double result=indvars[0];
		for(int i =0;i<indvarsdata.length;i++){
		//	if(t[i]>=t005){
			result += indvars[i+1]*indvarsdata[i]; 
		//	}
		}
		
		return result;
	}
}
