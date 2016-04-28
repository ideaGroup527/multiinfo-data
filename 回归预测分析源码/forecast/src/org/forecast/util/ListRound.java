package org.forecast.util;

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

	
	
	
	
	
	
	
	
	
	
	/*
 * ���Դ���
  public static void main(String[] args) {
		List outer = new ArrayList();
		for (int i = 0; i < 6; i++) {
			List inner = new ArrayList();
			inner.add(1);
			inner.add(2);
			inner.add(3);
			inner.add(4);
			inner.add(5);

			outer.add(inner);
		}
		ListRound lr = new ListRound();
		List outer1 = new ArrayList();
		outer1 = lr.beginTrans(outer);
		// ��ӡ���
		for (int i = 0; i < outer1.size(); i++) {
			List t = (List) outer1.get(i);
			for (int j = 0; j < t.size(); j++) {
				System.out.print(t.get(j) + "--");
			}
			System.out.println();
		}

	}
  */
}
