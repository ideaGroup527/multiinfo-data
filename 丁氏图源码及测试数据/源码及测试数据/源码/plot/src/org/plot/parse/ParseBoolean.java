package org.plot.parse;

public class ParseBoolean {

	public boolean parseBoolean(String temp) {
		boolean result = false;
		try {
			result = Boolean.parseBoolean(temp);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return result;
		}
		return result;
	}
}
