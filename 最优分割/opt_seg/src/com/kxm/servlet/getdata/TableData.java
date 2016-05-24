package com.kxm.servlet.getdata;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kxm.db.factory.CommonFactory;
import com.kxm.exception.ObjectToObjectException;
import com.kxm.parse.ListToArray;
import com.kxm.parse.ObjectToObject;
import com.kxm.parse.ObjectToString;
import com.kxm.parse.ParseDouble;
import com.kxm.parse.ParseInteger;
import com.kxm.parse.StringToObject;
import com.kxm.pojo.DataBaseMessage;
import com.kxm.util.ListRound;
import com.kxm.util.Transfer;

public class TableData extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public TableData() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String tableName = "";
		String objStr = "";
		String xScaleName = "";
		String[] chooseCol = null;// ÿ�����ݱ�����
		DataBaseMessage dbm = new DataBaseMessage();// ������������
		// �õ���ѡX��
		if (null != request.getParameter("xScaleName")
				&& !"".equals(request.getParameter("xScaleName"))) {
			xScaleName = request.getParameter("xScaleName");
		} else {
			// ���������ݲ�����
			// ��ת����Ϣҳ��
			// ������Ϣ
			request.setAttribute("message", "��ѡ���ʶ�У�");
			request.getRequestDispatcher("message.jsp").forward(request,
					response);
			return;
		}

		// �õ���ѡX��
		if (null != request.getParameterValues("chooseCol")
				&& !"".equals(request.getParameterValues("chooseCol"))) {
			chooseCol = request.getParameterValues("chooseCol");
		} else {
			// ���������ݲ�����
			// ��ת����Ϣҳ��
			// ������Ϣ
			request.setAttribute("message", "��ѡ�������У�");
			request.getRequestDispatcher("message.jsp").forward(request,
					response);
			return;
		}

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
			request.setAttribute("message", "���ݶ�ʧ��");
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

		CommonFactory cf = new CommonFactory(dbm, tableName, xScaleName);// ���������
		String[] xScale = null;
		List tableCol = null;
		List tempList = cf.factory();
		List tList = new ArrayList();
		for (int i = 0; i < tempList.size(); i++) {
			List t = (List) tempList.get(i);
			tList.add(t.get(0));
		}
		ListToArray lta = new ListToArray();
		xScale = lta.parseArray(tList);// �õ�xScale

		CommonFactory cf1 = new CommonFactory(dbm, tableName, chooseCol);// ���������
		List valueFirst = cf1.factory();
		List value = new ArrayList();
		ParseDouble pd = new ParseDouble();
		for (int i = 0; i < valueFirst.size(); i++) {
			List temp = (List) valueFirst.get(i);
			for (int j = 0; j < temp.size(); j++) {
				double a = pd.parseDouble((String)temp.get(j));
				temp.set(j, Double.valueOf(a));
			}
			value.add(temp);
		}

		/***********************************************************************
		 * ��Stringת����Double
		 * 
		 **********************************************************************/

		// ����һ��������ҳ����ʾ��List
		List showList = null;
		ListRound lr = new ListRound();// ת������List����
		try {
			showList = lr.beginTrans(value);// ִ��ת��
		} catch (IndexOutOfBoundsException e) {
			// ��ת����Ϣҳ��
			// ������Ϣ
			e.printStackTrace();
			request.setAttribute("message", "��ȷ�ϸñ��Ƿ������ݣ�");
			request.getRequestDispatcher("message.jsp").forward(request,
					response);
			return;
		}
		// ��õ����ݷ�װ��Transfer����
		Transfer tran = new Transfer();
		tran.setValue(showList);// value��װ
		tran.setXscale(xScale);// xScale��װ
		tran.setlineTitle(chooseCol);// title��װ
		tran.setChartTitle(tableName);// ��װͼƬ����

		// �������ַ���������
		ObjectToObject ots = new ObjectToString();
		String newObjStr = null;// ת����Ľ��

		try {
			newObjStr = (String) ots.transform(tran);// �õ������Ӧ���ַ���
		} catch (ObjectToObjectException e) {
			e.printStackTrace();
		}

		request.setAttribute("showList", value);// ������ʾList
		request.setAttribute("title", chooseCol);// ������ʾtitle
		request.setAttribute("picName", tableName);// ������ʾͼ����title
		request.setAttribute("xScale", xScale);// ������ʾxScale
		request.setAttribute("tran", newObjStr);// tran��װ��request�����ݴ���
		request.getRequestDispatcher("showData.jsp").forward(request, response);

	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}

}
