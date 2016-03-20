package org.plot.util;

import java.awt.Color;
import java.awt.GradientPaint;

import org.plot.parse.ParseColor;

public class BackColor {

	public GradientPaint totalBack(int height,String upColor,String downColor){
		ParseColor pc = new ParseColor();
		// 整体部分背景设置
		GradientPaint totalBG = new GradientPaint(0, 0, pc.parseColor(upColor), 0, height,
				pc.parseColor(downColor), false);
		//返回设置结果
		return totalBG ;
	}
	
	public GradientPaint chartBack(){
		// 制图部分背景设置
		GradientPaint chartBG = new GradientPaint(0, 50, new Color(205, 237, 252),
				0, 250, new Color(205, 237, 252));
		//返回设置结果
		return chartBG ;
	}
	
	
}
