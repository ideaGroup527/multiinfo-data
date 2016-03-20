package org.plot.parse;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import org.plot.exception.ObjectToObjectException;

import sun.misc.BASE64Decoder;

public class StringToObject implements ObjectToObject {

	public Object transform(Object obj) throws ObjectToObjectException {
		Object resultObj = null;// 存放结果对象

		String objStr = (String) obj;// 强转为字符串
		/** 开始反base64编码** */
		BASE64Decoder decoder = new BASE64Decoder();// 获得反编码对象
		byte[] tempByte = null;// 临时存放的字节数组
		try {
			tempByte = decoder.decodeBuffer(objStr);// 执行转换
		} catch (IOException e) {
			throw new ObjectToObjectException("字符串转换为对象出现IO异常", e);
		}

		/** 把字节数组转换成对象* */
		ByteArrayInputStream in = new ByteArrayInputStream(tempByte);//内层数据管道
		try {
			ObjectInputStream ois = new ObjectInputStream(in);//外层数据管道
			resultObj = ois.readObject();//得到对象
			ois.close() ;//关闭外层管道
			in.close() ;//关闭内层管道
		} catch (IOException e) {
			throw new ObjectToObjectException("字符串转换为对象出现IO异常", e);
		} catch (ClassNotFoundException e) {
			throw new ObjectToObjectException("字符串转换为对象出现找不到类", e);
		}

		return resultObj;
	}

}
