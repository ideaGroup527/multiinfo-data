package com.kxm.pojo;

import java.util.List;

public class DownloadPojo {
	private String[][] Dingresult = null;
	private String[] CutnumDing = null;
	private String[][] lineresult = null;
	private String[][][] showresult = null;
	private List presult = null;
	private String title = null;      //表名
	private String[] xScale = null;     //标志列
	private int[] cutnumS = null;
	
	public String[][] getDingresult() {
		return Dingresult;
	}
	public void setDingresult(String[][] dingresult) {
		Dingresult = dingresult;
	}
	public String[] getCutnumDing() {
		return CutnumDing;
	}
	public void setCutnumDing(String[] cutnumDing) {
		CutnumDing = cutnumDing;
	}
	public String[][] getLineresult() {
		return lineresult;
	}
	public void setLineresult(String[][] lineresult) {
		this.lineresult = lineresult;
	}
	public String[][][] getShowresult() {
		return showresult;
	}
	public void setShowresult(String[][][] showresult) {
		this.showresult = showresult;
	}
	public List getPresult() {
		return presult;
	}
	public void setPresult(List presult) {
		this.presult = presult;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String[] getXScale() {
		return xScale;
	}
	public void setXScale(String[] scale) {
		xScale = scale;
	}
	public int[] getCutnumS() {
		return cutnumS;
	}
	public void setCutnumS(int[] cutnumS) {
		this.cutnumS = cutnumS;
	}
	
}
