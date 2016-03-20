package org.plot.parse;

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

	/**
	 * List value--需要正规化的集合 int allMax--大于allMax的数全赋值为allMax int
	 * allMin--小于allMix的数全赋值为allMix
	 */
	public List min_Switch(List value, double allMax, double allMin) {
		List max = new ArrayList();// 每一组数据的最大值
		List min = new ArrayList();// 每一组数据的最小值
		// 寻找最大最小值
		for (int i = 0; i < value.size(); i++) {
			List firstList = (List) value.get(i);
			min.add(Collections.min(firstList)); 
			max.add(Collections.max(firstList)); 
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

				double resultTemp = (temp - tempMin) / (tempMax - tempMin);
				
				if(allMax!=0){
					// 如果数字大于tempMax
					if (resultTemp > allMax) {
						resultTemp = allMax;
					}
				}
				if(allMin!=0){
					// 如果数字小于tempMin
					if (resultTemp < allMin) {
						resultTemp = allMin;
					}
				}
				
				secondList.add(resultTemp);
			}
			mid_Value.add(secondList);
		}
		return mid_Value;
	}

}
