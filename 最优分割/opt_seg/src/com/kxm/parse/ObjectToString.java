package com.kxm.parse;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import com.kxm.exception.ObjectToObjectException;

import sun.misc.BASE64Encoder;

public class ObjectToString implements ObjectToObject {
	
	public String transform(Object obj) throws ObjectToObjectException{
		String result = null;// 存放结果
		ByteArrayOutputStream out = new ByteArrayOutputStream();// 读取数据内层管道
		try {
			/*** 把对象字节化处理，得到一个字节数组* */
			ObjectOutputStream oos = new ObjectOutputStream(out);// 读取数据外层管道
			oos.writeObject(obj);// 读入对象
			byte[] tempByte = out.toByteArray();// 对象转换为字节流
			oos.close();// 关闭外层管道
			out.close();// 关闭内层管道
			
			/***把字节数组转换编码成为base64***/
			BASE64Encoder encoder = new BASE64Encoder() ;//得到一个转换器对象
			result = encoder.encode(tempByte) ;//转换编码，得到结果
		} catch (IOException e) {
			throw new ObjectToObjectException("转换对象到字符串出现异常",e) ;
		}

		return result;
	}
}
