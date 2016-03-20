package org.plot.test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.plot.exception.ObjectToObjectException;
import org.plot.parse.ObjectToObject;
import org.plot.parse.ObjectToString;
import org.plot.parse.StringToObject;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class TestMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TestSerializa ts = new TestSerializa();
		ts.setA(1);
		ts.setB("测试");
		ts.setC(false);

			// // 序列化
			// ByteArrayOutputStream out = new ByteArrayOutputStream();
			// ObjectOutputStream oos = new ObjectOutputStream(out);
			// oos.writeObject(ts);
			// byte[] objByte = out.toByteArray();
			// oos.close();
			// out.close() ;
			// //base64编码
			// BASE64Encoder base64 = new BASE64Encoder();
			// String objStr = base64.encode(objByte);
			ObjectToObject ots = new ObjectToString();
			String objStr = null;
			try {
				objStr = (String)ots.transform(ts);
			} catch (ObjectToObjectException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(objStr);

//			// 反base64编码
//			BASE64Decoder decoder = new BASE64Decoder();
//			byte[] ojbByte = decoder.decodeBuffer(objStr);
//			// 反序列化
//			ByteArrayInputStream in = new ByteArrayInputStream(ojbByte);
//			ObjectInputStream ois = new ObjectInputStream(in);
//			TestSerializa t = (TestSerializa) ois.readObject();
//			ois.close();
//			in.close();
			ObjectToObject oto = new StringToObject() ;
			TestSerializa t = null ;
			try {
				t = (TestSerializa)oto.transform(objStr);
			} catch (ObjectToObjectException e) {
				e.printStackTrace();
			}
			System.out.println(t.getB());

	}

}
