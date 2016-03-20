package org.plot.parse;

import java.util.ArrayList;
import java.util.List;

public class ParseValue {

	/***************************************************************************
	 *  2个方法:
	 *  1：把数组转换成集合 2：把集合转换成数组 
	 * （数组为double，List里面包含多个List即2层List）
	 **************************************************************************/
	// 二维数组转换成集合
	public List parseList(double[][] value) {

		// 外层List
		List temp = new ArrayList();
		for (int i = 0; i < value.length; i++) {
			// 内层List
			List tempInner = new ArrayList();
			for (int j = 0; j < value[i].length; j++) {
				// 把第二维数据赋值给内层的集合
				tempInner.add(value[i][j]);
			}
			// 把内层集合再封装到外层集合中
			temp.add(tempInner);
		}
		// 返回结果集合
		return temp;
	}

	// 集合转换成二维数组
	public double[][] parseArray(List value) {
		// 结果数组第一维数组大小是外层集合的大小
		double[][] temp = new double[value.size()][];
		for (int i = 0; i < temp.length; i++) {
			// 取出内层集合
			List tempValue = (List) value.get(i);
			// 给每一维数组制定大小
			temp[i] = new double[tempValue.size()];
			for (int j = 0; j < temp[i].length; j++) {
				// 对应赋值
				temp[i][j] = (Double) tempValue.get(j);
			}
		}
		//返回结果数组
		return temp;
	}
}
