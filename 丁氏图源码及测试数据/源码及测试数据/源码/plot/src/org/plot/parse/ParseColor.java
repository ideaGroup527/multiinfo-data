package org.plot.parse;

import java.awt.Color;


/**
 * ��ʮ�����Ƶ��ַ���ת����Color����
 * */
public class ParseColor {

	/**
	 * ִ��ת������
	 * */
	public Color parseColor(String str)throws NumberFormatException{
		
		Color color = new Color(Integer.parseInt(str, 16)) ;
		
		return color ;
	}
	
}
