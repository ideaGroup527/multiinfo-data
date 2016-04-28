package org.forecast.parse;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import org.forecast.exception.ObjectToObjectException;

import com.sun.faces.io.Base64InputStream;
import com.sun.faces.io.Base64OutputStreamWriter;

import sun.misc.BASE64Encoder;

public class ObjectToString implements ObjectToObject {
	
	public String transform(Object obj) throws ObjectToObjectException{
		String result = null;// ��Ž��
		ByteArrayOutputStream out = new ByteArrayOutputStream();// ��ȡ�����ڲ�ܵ�
		try {
			/*** �Ѷ����ֽڻ������õ�һ���ֽ�����* */
			ObjectOutputStream oos = new ObjectOutputStream(out);// ��ȡ�������ܵ�
			oos.writeObject(obj);// �������
			byte[] tempByte = out.toByteArray();// ����ת��Ϊ�ֽ���
			oos.close();// �ر����ܵ�
			out.close();// �ر��ڲ�ܵ�
			
			/***���ֽ�����ת�������Ϊbase64***/
			BASE64Encoder encoder = new BASE64Encoder() ;//�õ�һ��ת��������
			result = encoder.encode(tempByte) ;//ת�����룬�õ����
		} catch (IOException e) {
			throw new ObjectToObjectException("ת�������ַ��������쳣",e) ;
		}

		return result;
	}
}
