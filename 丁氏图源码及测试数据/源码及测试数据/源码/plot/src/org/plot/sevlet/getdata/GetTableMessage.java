package org.plot.sevlet.getdata;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.plot.db.factory.DBFactory;
import org.plot.exception.ObjectToObjectException;
import org.plot.parse.ObjectToObject;
import org.plot.parse.ParseDouble;
import org.plot.parse.StringToObject;
import org.plot.pojo.DataBaseMessage;

public class GetTableMessage extends HttpServlet {

	public GetTableMessage() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String tableName = "";
		String objStr = "";
		DataBaseMessage dbm = new DataBaseMessage();// ������������
		// �õ���ѡ����
		if (null != request.getParameter("tableName")
				&& !"".equals(request.getParameter("tableName"))) {
			tableName = request.getParameter("tableName");
		} else {
			// ���������ݲ�����
			// ��ת����Ϣҳ��
			// ������Ϣ
			request.setAttribute("message", "��������Ϊ�գ�");
			request.getRequestDispatcher("message.jsp").forward(request,
					response);
			return;
		}

		// �õ������ַ���
		if (null != request.getParameter("objStr")
				&& !"".equals(request.getParameter("objStr"))) {
			objStr = request.getParameter("objStr");
		} else {
			// ���������ݲ�����
			// ��ת����Ϣҳ��
			// ������Ϣ
			request.setAttribute("message", "���ݶ�ʧ����Ϊ�գ�");
			request.getRequestDispatcher("message.jsp").forward(request,
					response);
			return;
		}
		// �õ���װ�õ����ݿ����Ӷ���
		ObjectToObject oto = new StringToObject();
		try {
			dbm = (DataBaseMessage) oto.transform(objStr);
		} catch (ObjectToObjectException e) {
			// ��ת����Ϣҳ��
			// ������Ϣ
			e.printStackTrace();
			request.setAttribute("message", "����ת������");
			request.getRequestDispatcher("message.jsp").forward(request,
					response);
			return;
		}

		DBFactory dbf = new DBFactory(dbm,tableName);// ���������
		List tableCol = null;
		tableCol = dbf.factory();//�õ����

		// ��װ��������д�Ķ�������
		request.setAttribute("tableCol", tableCol);
		request.setAttribute("tableName", tableName);
		request.setAttribute("objStr", objStr);
		// ��ת
		request.getRequestDispatcher("tableCol.jsp").forward(request,
				response);
		return;
		
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
