package org.forecast.util;

public class zuobu {
	/**
	 * f ΪF����ֵ
	 * LΪ�ȳ�����Ҫ���� �ĸ���
	 * B[] Ϊ�ع�ϵ��
	 * TI[] Ϊ��������T����ֵ
	 */
private double f;
private double l;
private double[] b;
private double[] ti;
public zuobu(){

}
public zuobu(double f,double l,double[]b,double[]ti){
	this.f = f;
	this.l = l;
	this.b = b;
	this.ti = ti;
}
public double getF() {
	return f;
}
public void setF(double f) {
	this.f = f;
}
public double getL() {
	return l;
}
public void setL(double l) {
	this.l = l;
}
public double[] getB() {
	return b;
}
public void setB(double[] b) {
	this.b = b;
}
public double[] getTi() {
	return ti;
}
public void setTi(double[] ti) {
	this.ti = ti;
}
}
