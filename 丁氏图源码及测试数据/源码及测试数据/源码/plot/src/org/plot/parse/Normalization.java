package org.plot.parse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//���ڶ����ݽ��д��� ��ʹ���ݴ�С����0~1֮��
public class Normalization {
	public List min_Switch(List value) {
		List max = new ArrayList();// ÿһ�����ݵ����ֵ
		List min = new ArrayList();// ÿһ�����ݵ���Сֵ
		// Ѱ�������Сֵ
		for (int i = 0; i < value.size(); i++) {
			List firstList = (List) value.get(i);
			// ȡ�����ϵ������Сֵ
			max.add(Collections.max(firstList));
			min.add(Collections.min(firstList));
		}

		// �����������滯

		List mid_Value = new ArrayList();// ���ڴ���м�ֵ
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
	 * List value--��Ҫ���滯�ļ��� int allMax--����allMax����ȫ��ֵΪallMax int
	 * allMin--С��allMix����ȫ��ֵΪallMix
	 */
	public List min_Switch(List value, double allMax, double allMin) {
		List max = new ArrayList();// ÿһ�����ݵ����ֵ
		List min = new ArrayList();// ÿһ�����ݵ���Сֵ
		// Ѱ�������Сֵ
		for (int i = 0; i < value.size(); i++) {
			List firstList = (List) value.get(i);
			min.add(Collections.min(firstList)); 
			max.add(Collections.max(firstList)); 
		}

		// �����������滯

		List mid_Value = new ArrayList();// ���ڴ���м�ֵ
		for (int i = 0; i < value.size(); i++) {
			List firstList = (List) value.get(i);
			List secondList = new ArrayList();
			for (int j = 0; j < firstList.size(); j++) {
				double temp = Double.parseDouble(firstList.get(j).toString());
				double tempMin = Double.parseDouble(min.get(i).toString());
				double tempMax = Double.parseDouble(max.get(i).toString());

				double resultTemp = (temp - tempMin) / (tempMax - tempMin);
				
				if(allMax!=0){
					// ������ִ���tempMax
					if (resultTemp > allMax) {
						resultTemp = allMax;
					}
				}
				if(allMin!=0){
					// �������С��tempMin
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
