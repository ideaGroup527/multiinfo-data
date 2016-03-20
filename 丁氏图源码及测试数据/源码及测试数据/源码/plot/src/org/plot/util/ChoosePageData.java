package org.plot.util;

import java.util.ArrayList;
import java.util.List;

/**
 * 用于对页面所选取的数据进行封装,对List的操作 <br>
 * 日期：2009-03-05 <br>
 * 作者：林俊洪
 */
public class ChoosePageData {

	/**
	 * before未转换的集合，row所选的行，col所选的列
	 */
	public List beginTran(List before, int[] row, int[] col) {
		/***********************************************************************
		 * 思路：1、选择列。 2、横纵转换。 3、选择行。 4、横纵转换
		 **********************************************************************/
		List after = null;// 存放结果的集合
		ListRound lr = new ListRound();// 获得转换集合的对象

		// 按所选提取列的数值进行封装（原始）
		List colList = new ArrayList();// 存放选完后的列的集合
		if (null == col || 0 == col.length) {// 列没有选
			colList = before;// 没有选择，为原值
		} else {
			for (int i = 0; i < col.length; i++) {// 执列选取
				int tempCol = col[i];// 所选的列
				colList.add(before.get(tempCol));// 把选中的列放到colList
			}
		}

		// 将提取列后的数据进行横纵转换
		List firstTranList = null;// 存放第一次转换的集合
		firstTranList = lr.beginTrans(colList);// 执行横纵转换并赋值

		// 按所选提取列的数值进行封装（选过一次）
		List rowList = new ArrayList();// 存放行所选
		if (null == row || 0 == row.length) {// 行没选
			rowList = firstTranList;// 没有选择，为原值
		} else {
			for (int i = 0; i < row.length; i++) {// 执行选取
				int tempRow = row[i];// 所选的行
				rowList.add(firstTranList.get(tempRow));// 把选中的行放到rowList
			}
		}

		// 将第二次封装的数据再横纵转换一次
		after = lr.beginTrans(rowList);// 得到最后结果

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
