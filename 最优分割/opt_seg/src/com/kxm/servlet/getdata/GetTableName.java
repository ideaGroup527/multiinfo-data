package com.kxm.servlet.getdata;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kxm.db.factory.DBFactory;
import com.kxm.exception.ObjectToObjectException;
import com.kxm.parse.ObjectToObject;
import com.kxm.parse.ObjectToString;
import com.kxm.pojo.DataBaseMessage;

public class GetTableName extends HttpServlet {

	/**
	 * �õ��û������б������
	 */
	public GetTableName() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// �õ�ҳ�������
		String databaseKind = null;
		String ip = null;
		String dataBase = null;
		String userName = null;
		String password = null;
		DataBaseMessage dbm = new DataBaseMessage();
		if (!"".equals(request.getParameter("databaseKind"))
				&& null != request.getParameter("databaseKind")) {
			dbm.setDatabaseKind(request.getParameter("databaseKind"));// ��������ݿ����Ͳ�Ϊ����ֵ
		} else {
			// ���������ݲ�����
			// ��ת����Ϣҳ��
			// ������Ϣ
			request.setAttribute("message", "���ݿ����Ͳ���Ϊ�գ�");
			request.getRequestDispatcher("message.jsp").forward(request,
					response);
			return;
		}
		if (!"".equals(request.getParameter("ip"))
				&& null != request.getParameter("ip")) {
			dbm.setIp(request.getParameter("ip"));// �����ip��Ϊ����ֵ
		} else {
			// ���������ݲ�����
			// ��ת����Ϣҳ��
			// ������Ϣ
			request.setAttribute("message", "IP����Ϊ�գ�");
			request.getRequestDispatcher("message.jsp").forward(request,
					response);
			return;
		}
		if (!"".equals(request.getParameter("dataBase"))
				&& null != request.getParameter("dataBase")) {
			dbm.setDataBase(request.getParameter("dataBase"));// ��������ݿⲻΪ����ֵ
		} else {
			// ���������ݲ�����
			// ��ת����Ϣҳ��
			// ������Ϣ
			request.setAttribute("message", "���ݿⲻ��Ϊ�գ�");
			request.getRequestDispatcher("message.jsp").forward(request,
					response);
			return;
		}
		if (!"".equals(request.getParameter("userName"))
				&& null != request.getParameter("userName")) {
			dbm.setUserName(request.getParameter("userName"));// ������û�����Ϊ����ֵ
		} else {
			// ���������ݲ�����
			// ��ת����Ϣҳ��
			// ������Ϣ
			request.setAttribute("message", "���ݿ��û�������Ϊ�գ�");
			request.getRequestDispatcher("message.jsp").forward(request,
					response);
			return;
		}
		if (!"".equals(request.getParameter("password"))
				&& null != request.getParameter("password")) {
			dbm.setPassword(request.getParameter("password"));// ��������벻Ϊ����ֵ
		} else {
			// ���������ݲ�����
			// ��ת����Ϣҳ��
			// ������Ϣ
			request.setAttribute("message", "���ݿ����벻��Ϊ�գ�");
			request.getRequestDispatcher("message.jsp").forward(request,
					response);
			return;
		}

		// �����ݿ�õ�����
		List allTableName = null;

		DBFactory dbf = new DBFactory(dbm);
		try {
			allTableName = dbf.factory();
		} catch (RuntimeException e1) {
			// ���������ݲ�����
			// ��ת����Ϣҳ��
			// ������Ϣ
			request.setAttribute("message", "�޷��������ݿ⣬ȷ���������󣬱�֤���ݿ�����ڿ���״̬��");
			request.getRequestDispatcher("message.jsp").forward(request,
					response);
			return;
		}
		// ���л��û���д�Ķ���
		ObjectToObject oto = new ObjectToString();
		String objStr = null;// �����洢�����ַ���
		try {
			objStr = (String) oto.transform(dbm);
		} catch (ObjectToObjectException e) {
			// ��ת����Ϣҳ��
			// ������Ϣ
			e.printStackTrace();
			request.setAttribute("message", "����ת������");
			request.getRequestDispatcher("message.jsp").forward(request,
					response);
			return;
		}
		// ��װ��������д�Ķ�������
		request.setAttribute("allTableName", allTableName);
		request.setAttribute("objStr", objStr);
		// ��ת
		request.getRequestDispatcher("allTables.jsp")
				.forward(request, response);
		return;

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}

}
