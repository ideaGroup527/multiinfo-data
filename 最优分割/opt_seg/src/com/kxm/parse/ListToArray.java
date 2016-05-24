package com.kxm.parse;

import java.util.List;

public class ListToArray {

	public String[] parseArray(List before) {
		String[] after = null;
		if (before.size() > 0) {
			after = new String[before.size()];
			for (int i = 0; i < before.size(); i++) {
				after[i] = before.get(i).toString() ;
			}
		}

		return after;
	}
	
}

