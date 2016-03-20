package org.plot.parse;

public class ParseDouble {

	public double parseDouble(String temp) {
		double result = 0;
		try {
			System.out.println(temp);
			result = Double.parseDouble(temp);
		} catch (NumberFormatException e) {
//			e.printStackTrace();
			return result;
		} catch (NullPointerException e) {
			return result;
		}
		return result;
	}
}
