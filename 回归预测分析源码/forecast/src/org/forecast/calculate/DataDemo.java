package org.forecast.calculate;

public class DataDemo {
	//数据前移一位
	public double[][] qy1(double[][]aa){
		double [][] bb = new double[aa.length-1][aa[0].length];
		for(int i=0;i<aa.length-1;i++){
			aa[i][aa[0].length-1]=aa[i+1][aa[0].length-1];
		}


		for(int i=0;i<aa.length-1;i++){
			for(int j=0;j<aa[0].length;j++){
				bb[i][j]=aa[i][j];
			}
		}
		
		return bb;
	}
	
	//数据前移两位
	public double[][] qy2(double[][] aa){
		double[][] bb = new double [aa.length-2][aa[0].length];

		for(int i=0;i<aa.length-2;i++){
			
			aa[i][aa[0].length-1]=aa[i+2][aa[0].length-1];
			
			
		}
		for(int i =0;i<aa.length-2;i++){
			for(int j = 0; j<aa[0].length;j++){
				
				bb[i][j]=aa[i][j];
			}
			
		}
		
		return bb;
	}
	
	
	//数据前移三位
	public double[][] qy3(double[][] aa){
		double[][] bb = new double [aa.length-3][aa[0].length];
		for(int i=0;i<aa.length-3;i++){
			aa[i][aa[0].length-1]=aa[i+3][aa[0].length-1];
		}
		for(int i =0;i<aa.length-3;i++){
			for(int j = 0; j<aa[0].length;j++){
				bb[i][j]=aa[i][j];
			}
		}
		
		return bb;
	}
	
	public double[][] demo(double[][]aa, int index){
		
		double[] indvars = new double[aa.length];
		for(int i=0;i<aa.length;i++){
			indvars[i]= aa[i][index];
		}
		for(int i = 0 ;i<aa.length;i++){
			for(int k=index;k<aa[0].length-1;k++){
				aa[i][k]=aa[i][k+1];
			}
		}
		for(int i=0;i<aa.length;i++){
			aa[i][aa[0].length-1]=indvars[i];
		}
		return aa;
	}
}
