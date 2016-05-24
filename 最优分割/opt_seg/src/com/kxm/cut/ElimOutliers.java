package com.kxm.cut;

import java.util.List;
/**
 * ElimOutliers类用于进行排除异常值计算
 * @author kexinmei
 * @author kexinmeip@126.com
 * @version 1.0
 * */
public class ElimOutliers {
	/**
	 * @param List before
	 * @return List after
	 * */
	public List work(List before){
		List after = before;
		double xx,xy,sd,sdi,sdA;
		for(int j=0;j<after.size();j++){
			xx = 0;
			xy = 0;
			List col = (List)after.get(j);  //得到value的一列
			int collen = col.size();   //该列长度
			for(int i=0; i < collen;i++){
				xx += ((Double) col.get(i)).doubleValue(); 
			}
			xx = xx/col.size();
			for(int i=0; i < collen;i++){
				xy = xy + Math.pow( ( ((Double)col.get(i)).doubleValue() - xx), 2);
			}
			sd = Math.sqrt(xy / (collen - 1));
			sdA = xx + sd * 3;
			sdi = xx - sd * 3;
			for(int i=0; i < collen;i++){
				if( ((Double)col.get(i)).doubleValue()>sdA)col.set(i, Double.valueOf(sdA)); //用sdA替换第i位
				if( ((Double)col.get(i)).doubleValue()<sdi)col.set(i, Double.valueOf(sdi)); //用sdi替换第i位
			}
			after.set(j, col);
		}
		System.out.print("来自 ElimOutliers 的消息：消除异常值成功");
		
		return after;
	}
}
