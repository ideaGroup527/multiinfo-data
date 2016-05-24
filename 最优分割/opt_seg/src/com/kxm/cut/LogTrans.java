package com.kxm.cut;

import java.util.List;
/**
 * LogTrans类用于进行对数计算
 * @author kexinmei
 * @author kexinmeip@126.com
 * @version 1.0
 * */
public class LogTrans {
	/**
	 * @param List before
	 * @return List after
	 * */
	public List work(List before){
		List after = before;
		for(int i=0;i<after.size();i++){
			List col = (List)after.get(i);
			int collen = col.size();
			for(int j=0; j < collen;j++){
				if((Double)col.get(j) <= 0){
					Double tmp = Math.log( Math.abs((Double)col.get(j)));
					if(Double.isNaN(Double.valueOf(tmp)))
							System.out.println((Double)col.get(j));
					col.set(j, tmp);
					
				}else
					col.set(j, Math.log(((Double)col.get(j))));

			}
			after.set(i, col);
		}
		return after;
	}
}
