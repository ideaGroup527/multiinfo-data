package org.plot.parse;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import org.plot.exception.ObjectToObjectException;

import sun.misc.BASE64Decoder;

public class StringToObject implements ObjectToObject {

	public Object transform(Object obj) throws ObjectToObjectException {
		Object resultObj = null;// ��Ž������

		String objStr = (String) obj;// ǿתΪ�ַ���
		/** ��ʼ��base64����** */
		BASE64Decoder decoder = new BASE64Decoder();// ��÷��������
		byte[] tempByte = null;// ��ʱ��ŵ��ֽ�����
		try {
			tempByte = decoder.decodeBuffer(objStr);// ִ��ת��
		} catch (IOException e) {
			throw new ObjectToObjectException("�ַ���ת��Ϊ�������IO�쳣", e);
		}

		/** ���ֽ�����ת���ɶ���* */
		ByteArrayInputStream in = new ByteArrayInputStream(tempByte);//�ڲ����ݹܵ�
		try {
			ObjectInputStream ois = new ObjectInputStream(in);//������ݹܵ�
			resultObj = ois.readObject();//�õ�����
			ois.close() ;//�ر����ܵ�
			in.close() ;//�ر��ڲ�ܵ�
		} catch (IOException e) {
			throw new ObjectToObjectException("�ַ���ת��Ϊ�������IO�쳣", e);
		} catch (ClassNotFoundException e) {
			throw new ObjectToObjectException("�ַ���ת��Ϊ��������Ҳ�����", e);
		}

		return resultObj;
	}

}
