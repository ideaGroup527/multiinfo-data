package com.kxm.util;

import java.util.ArrayList;
import java.util.List;

/*******************************************************************************
 * ������������List��Ƕ�׶���list ����Ƕ��list�����ܶ��� �����ǰ��ж�����ŵ������.
 ******************************************************************************/

public class ListRound {

	public List beginTrans(List before) {
		/***********************************************************************
		 * ˼·�� ��ȡÿ�е���ͬλ�õ����ݣ���ŵ�һ����ʱ��List �ٽ���ʱList��װ��after�� *
		 **********************************************************************/
		List after = new ArrayList();// ��Ž���ļ���
		int firstListSize = ((List) before.get(0)).size();// �Ե�һ��Ϊ��׼���õ�����
		for (int i = 0; i < firstListSize; i++) {
			List tempList = new ArrayList();// ��ʱ����ڲ㼯�ϵļ���
			for (int j = 0; j < before.size(); j++) {
				List innerList = (List) before.get(j);// �õ�һ�еĶ���
				tempList.add(innerList.get(i));// �Ѹ��ж����Ӧ���е�����ȡ����
			}
			after.add(tempList);// ��һ�е����ݷ�װ��after��
		}
		return after;
	}
}
