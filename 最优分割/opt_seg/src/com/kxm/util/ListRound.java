package com.kxm.util;

import java.util.ArrayList;
import java.util.List;

/*******************************************************************************
 * 该类用来处理List中嵌套多组list 将内嵌的list看作很多列 把他们按行读出存放到结果中.
 ******************************************************************************/

public class ListRound {

	public List beginTrans(List before) {
		/***********************************************************************
		 * 思路： 读取每列的相同位置的数据，存放到一个临时的List 再将临时List封装到after中 *
		 **********************************************************************/
		List after = new ArrayList();// 存放结果的集合
		int firstListSize = ((List) before.get(0)).size();// 以第一列为标准，得到长度
		for (int i = 0; i < firstListSize; i++) {
			List tempList = new ArrayList();// 临时存放内层集合的集合
			for (int j = 0; j < before.size(); j++) {
				List innerList = (List) before.get(j);// 得到一列的对象
				tempList.add(innerList.get(i));// 把该列对象对应的行的数据取出来
			}
			after.add(tempList);// 把一列的数据封装到after中
		}
		return after;
	}
}
