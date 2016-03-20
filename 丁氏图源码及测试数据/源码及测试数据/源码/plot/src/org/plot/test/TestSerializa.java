package org.plot.test;

import java.io.Serializable;



public class TestSerializa implements Serializable {

	private int a ;
	private String b ;
	private boolean c ;
	public int getA() {
		return a;
	}
	public void setA(int a) {
		this.a = a;
	}
	public String getB() {
		return b;
	}
	public void setB(String b) {
		this.b = b;
	}
	public boolean isC() {
		return c;
	}
	public void setC(boolean c) {
		this.c = c;
	}
	
}
