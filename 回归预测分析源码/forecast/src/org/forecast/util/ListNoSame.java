package org.forecast.util;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ListNoSame {
	
	//去除List中重复的数据
	public List listDeal(List before){
		List after = null ;//存放处理结果
		Set temp = new HashSet(before);//把有重复的List放到hashset中就会去掉重复的
		after.addAll(temp) ;//把结果给after
		return after ;
	}
}
