package org.plot.util;

import java.util.ArrayList;
import java.util.List;

/**
 * ���ڶ�ҳ����ѡȡ�����ݽ��з�װ,��List�Ĳ��� <br>
 * ���ڣ�2009-03-05 <br>
 * ���ߣ��ֿ���
 */
public class ChoosePageData {

	/**
	 * beforeδת���ļ��ϣ�row��ѡ���У�col��ѡ����
	 */
	public List beginTran(List before, int[] row, int[] col) {
		/***********************************************************************
		 * ˼·��1��ѡ���С� 2������ת���� 3��ѡ���С� 4������ת��
		 **********************************************************************/
		List after = null;// ��Ž���ļ���
		ListRound lr = new ListRound();// ���ת�����ϵĶ���

		// ����ѡ��ȡ�е���ֵ���з�װ��ԭʼ��
		List colList = new ArrayList();// ���ѡ�����еļ���
		if (null == col || 0 == col.length) {// ��û��ѡ
			colList = before;// û��ѡ��Ϊԭֵ
		} else {
			for (int i = 0; i < col.length; i++) {// ִ��ѡȡ
				int tempCol = col[i];// ��ѡ����
				colList.add(before.get(tempCol));// ��ѡ�е��зŵ�colList
			}
		}

		// ����ȡ�к�����ݽ��к���ת��
		List firstTranList = null;// ��ŵ�һ��ת���ļ���
		firstTranList = lr.beginTrans(colList);// ִ�к���ת������ֵ

		// ����ѡ��ȡ�е���ֵ���з�װ��ѡ��һ�Σ�
		List rowList = new ArrayList();// �������ѡ
		if (null == row || 0 == row.length) {// ��ûѡ
			rowList = firstTranList;// û��ѡ��Ϊԭֵ
		} else {
			for (int i = 0; i < row.length; i++) {// ִ��ѡȡ
				int tempRow = row[i];// ��ѡ����
				rowList.add(firstTranList.get(tempRow));// ��ѡ�е��зŵ�rowList
			}
		}

		// ���ڶ��η�װ�������ٺ���ת��һ��
		after = lr.beginTrans(rowList);// �õ������

		return after;
	}

//	public static void main(String[] args) {
//		List outer = new ArrayList();
//		for (int i = 0; i < 6; i++) {
//			List inner = new ArrayList();
//			inner.add("a" + i);
//			inner.add("b" + i);
//			inner.add("c" + i);
//			inner.add("d" + i);
//			inner.add("e" + i);
//			inner.add("f" + i);
//			inner.add("g" + i);
//			inner.add("h" + i);
//			inner.add("i" + i);
//			inner.add("j" + i);
//
//			outer.add(inner);
//		}
//		for (int i = 0; i < outer.size(); i++) {
//			List t = (List) outer.get(i);
//			for (int j = 0; j < t.size(); j++) {
//				System.out.print(t.get(j) + " ");
//			}
//			System.out.println();
//		}
//		System.out.println();
//
//		int[] col = null;
//		int[] row = {};
//		System.out.println(row.length);
//		ChoosePageData cd = new ChoosePageData();
//		List result = cd.beginTran(outer, row, col);
//		for (int i = 0; i < result.size(); i++) {
//			List innerResult = (List) result.get(i);
//			for (int j = 0; j < innerResult.size(); j++) {
//				System.out.print(innerResult.get(j) + " ");
//			}
//			System.out.println();
//		}
//	}

}
