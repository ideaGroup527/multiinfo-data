package org.plot.parse;

import java.awt.Color;


/**
 * 将十六进制的字符串转换成Color类型
 * */
public class ParseColor {

	/**
	 * 执行转换方法
	 * */
	public Color parseColor(String str)throws NumberFormatException{
		
		Color color = new Color(Integer.parseInt(str, 16)) ;
		
		return color ;
	}
	
}
