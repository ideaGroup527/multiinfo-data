package org.forecast.parse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//用于对数据进行处理 ，使数据大小处于0~1之间
public class Normalization {
	public List min_Switch(List value) {
		List max = new ArrayList();// 每一组数据的最大值
		List min = new ArrayList();// 每一组数据的最小值
		// 寻找最大最小值
		for (int i = 0; i < value.size(); i++) {
			List firstList = (List) value.get(i);
			// 取出集合的最大最小值
			max.add(Collections.max(firstList));
			min.add(Collections.min(firstList));
		}

		// 进行数据正规化

		List mid_Value = new ArrayList();// 用于存放中间值
		for (int i = 0; i < value.size(); i++) {
			List firstList = (List) value.get(i);
			List secondList = new ArrayList();
			for (int j = 0; j < firstList.size(); j++) {
				double temp = Double.parseDouble(firstList.get(j).toString());
				double tempMin = Double.parseDouble(min.get(i).toString());
				double tempMax = Double.parseDouble(max.get(i).toString());
				secondList.add((temp - tempMin) / (tempMax - tempMin));
			}
			mid_Value.add(secondList);
		}
		return mid_Value;
	}

}
