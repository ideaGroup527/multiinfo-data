package org.forecast.util;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ListNoSame {
	
	//ȥ��List���ظ�������
	public List listDeal(List before){
		List after = null ;//��Ŵ�����
		Set temp = new HashSet(before);//�����ظ���List�ŵ�hashset�оͻ�ȥ���ظ���
		after.addAll(temp) ;//�ѽ����after
		return after ;
	}
}
