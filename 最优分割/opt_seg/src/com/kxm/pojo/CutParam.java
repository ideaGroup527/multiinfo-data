package com.kxm.pojo;

import java.util.List;

public class CutParam {
	String[][][] result = null;  //分割结果集
	List presult = null;         //离差平方总和
	String[][] lineresult = null; // 对应样品各次分割点位图的结果集
	public String[][][] getResult() {
		return result;
	}
	public void setResult(String[][][] result) {
		this.result = result;
	}
	public List getPresult() {
		return presult;
	}
	public void setPresult(List presult) {
		this.presult = presult;
	}
	public String[][] getLineresult() {
		return lineresult;
	}
	public void setLineresult(String[][] lineresult) {
		this.lineresult = lineresult;
	}
	
}
