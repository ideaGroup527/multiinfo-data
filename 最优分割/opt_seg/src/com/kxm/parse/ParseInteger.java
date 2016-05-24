package com.kxm.parse;

public class ParseInteger {

	public int parseInt(String temp) {
		int result = 0;
		try {
			result = Integer.parseInt(temp);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return result;
		}
		return result;
	}
}
