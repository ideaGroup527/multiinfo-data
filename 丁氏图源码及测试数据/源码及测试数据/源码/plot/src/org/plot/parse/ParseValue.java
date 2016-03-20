package org.plot.parse;

import java.util.ArrayList;
import java.util.List;

public class ParseValue {

	/***************************************************************************
	 *  2������:
	 *  1��������ת���ɼ��� 2���Ѽ���ת�������� 
	 * ������Ϊdouble��List����������List��2��List��
	 **************************************************************************/
	// ��ά����ת���ɼ���
	public List parseList(double[][] value) {

		// ���List
		List temp = new ArrayList();
		for (int i = 0; i < value.length; i++) {
			// �ڲ�List
			List tempInner = new ArrayList();
			for (int j = 0; j < value[i].length; j++) {
				// �ѵڶ�ά���ݸ�ֵ���ڲ�ļ���
				tempInner.add(value[i][j]);
			}
			// ���ڲ㼯���ٷ�װ����㼯����
			temp.add(tempInner);
		}
		// ���ؽ������
		return temp;
	}

	// ����ת���ɶ�ά����
	public double[][] parseArray(List value) {
		// ��������һά�����С����㼯�ϵĴ�С
		double[][] temp = new double[value.size()][];
		for (int i = 0; i < temp.length; i++) {
			// ȡ���ڲ㼯��
			List tempValue = (List) value.get(i);
			// ��ÿһά�����ƶ���С
			temp[i] = new double[tempValue.size()];
			for (int j = 0; j < temp[i].length; j++) {
				// ��Ӧ��ֵ
				temp[i][j] = (Double) tempValue.get(j);
			}
		}
		//���ؽ������
		return temp;
	}
}
