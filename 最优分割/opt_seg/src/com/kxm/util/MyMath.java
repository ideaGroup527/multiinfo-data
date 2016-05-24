package com.kxm.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyMath {
	public static boolean isDigital(String s){
		Pattern p = Pattern.compile("^[-?|\\d+].?[\\d+]$");
		Matcher m = p.matcher(s);
		boolean isNull = m.find();
		return isNull;
	}
}
