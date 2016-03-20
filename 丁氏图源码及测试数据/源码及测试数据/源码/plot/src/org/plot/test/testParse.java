package org.plot.test;

import java.util.List;

import org.plot.parse.ParseValue;
import org.plot.util.Transfer;

public class testParse {
	public static void main(String[] args) {
		ParseValue pv = new ParseValue();
		Transfer tr = new Transfer();
		List value = tr.getValue();
		double[][] result = pv.parseArray(value);
		for (int i = 0; i < result.length; i++) {
			for (int j = 0; j < result[i].length; j++) {
				System.out.print(result[i][j] + "   ");
			}
			System.out.println();
			System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
		}
		List result1 = pv.parseList(result);
		for (int i = 0; i < result1.size(); i++) {
			List temp1 = (List) result1.get(i);
			for (int j = 0; j < temp1.size(); j++) {
				System.out.print(temp1.get(j) + "   ");
			}
			System.out.println();
			System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		}
	}
}
