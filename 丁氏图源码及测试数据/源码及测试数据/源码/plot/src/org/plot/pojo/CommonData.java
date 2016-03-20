package org.plot.pojo;

import java.util.List;

import javax.servlet.http.HttpSession;

public class CommonData {

	// 声明一个session
	private HttpSession session;

	// 图表的名称
	private String chartTitle;

	// 宽默认980
	private int width;

	// 高默认450
	private int height;
	
	//x轴名称
	private String xTitle;

	//y轴名称
	private String yTitle;

	public CommonData(HttpSession session, String chartTitle, int width,
			int height, String xTitle, String yTitle) {
		super();
		this.session = session;
		this.chartTitle = chartTitle;
		this.width = width;
		this.height = height;
		this.xTitle = xTitle;
		this.yTitle = yTitle;
	}

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

	public String getChartTitle() {
		return chartTitle;
	}

	public void setChartTitle(String chartTitle) {
		this.chartTitle = chartTitle;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public CommonData(HttpSession session, String chartTitle, int width, int height) {
		super();
		this.session = session;
		this.chartTitle = chartTitle;
		this.width = width;
		this.height = height;
	}

	public CommonData(){
		//无参构造器
	}
	
	public String getXTitle() {
		return xTitle;
	}

	public void setXTitle(String title) {
		xTitle = title;
	}

	public String getYTitle() {
		return yTitle;
	}

	public void setYTitle(String title) {
		yTitle = title;
	}
}
