package org.plot.sevlet.getdata;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.plot.exception.ObjectToObjectException;
import org.plot.parse.ObjectToObject;
import org.plot.parse.ObjectToString;
import org.plot.parse.ParseInteger;
import org.plot.parse.StringToObject;
import org.plot.util.ChoosePageData;
import org.plot.util.Transfer;

public class ChooseData extends HttpServlet {

	public ChooseData() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List value = null;// ѡ���Ľ������
		String[] tempCol = null;// ��ʱ�����
		String[] tempRow = null;// ��ʱ�����
		String tran = null;// �����ַ���
		String[] title = null;// ����
		String[] xScale = null;// ������
		String[] resultTitle = null;// ��Ž������
		String[] resultXScale = null;// ��Ž��������

		// �õ������ַ���
		if (!"".equals(request.getParameter("tran"))
				&& null != request.getParameter("tran")) {
			tran = request.getParameter("tran");// �õ������ַ���
		}

		if (!"".equals(request.getParameterValues("col"))
				&& null != request.getParameterValues("col")) {
			tempCol = request.getParameterValues("col");// �õ���ѡ��
		}
		if (!"".equals(request.getParameterValues("row"))
				&& null != request.getParameterValues("row")) {
			tempRow = request.getParameterValues("row");// �õ���ѡ��
		}
		// ûѡ��
		if (null == tempRow || "".equals(tempRow)) {
			// ��ת����Ϣҳ��
			// ������Ϣ
			request.setAttribute("message", "��ѡ�������ݣ�");
			request.getRequestDispatcher("message.jsp").forward(request,
					response);
			return;
		}
		// ûѡ��
		if (null == tempCol || "".equals(tempCol)) {
			// ��ת����Ϣҳ��
			// ������Ϣ
			request.setAttribute("message", "��ѡ�������ݣ�");
			request.getRequestDispatcher("message.jsp").forward(request,
					response);
			return;
		}

		// �����л�
		ObjectToObject oto = new StringToObject();// �õ����л���
		Transfer transfer = null;
		try {
			transfer = (Transfer) oto.transform(tran);// �����л��õ�����
		} catch (ObjectToObjectException e) {
			// ��ת����Ϣҳ��
			// ������Ϣ
			request.setAttribute("message", "�������л�����");
			request.getRequestDispatcher("message.jsp").forward(request,
					response);
			return;
		}
		List before = (List) transfer.getValue();// �õ�value����
		title = transfer.getLineTitle();// �õ�title
		xScale = transfer.getXscale();// �õ�xScale
		/** **�õ���ѡ������*** */
		int[] col = null;// ��ѡ����
		int[] row = null;// ��ѡ����
		// ���ַ�����ת��Ϊ����
		ParseInteger pi = new ParseInteger();// �õ�ǿת������
		col = new int[tempCol.length];// ��ѡ����
		resultTitle = new String[tempCol.length];// ��ű���
		for (int i = 0; i < tempCol.length; i++) {
			int subChoose = pi.parseInt(tempCol[i]);// ��ת��Ϊ����
			col[i] = subChoose;// ��ת��Ϊ����
			resultTitle[i] = title[subChoose];
		}

		row = new int[tempRow.length];// ��ѡ����
		resultXScale = new String[tempRow.length];// ���X����
		for (int i = 0; i < tempRow.length; i++) {
			int subChoose = pi.parseInt(tempRow[i]);// ��ת��Ϊ����
			row[i] = subChoose;
			resultXScale[i] = xScale[subChoose];// �õ���Ӧ��ѡ��xScale
		}

		// ִ����������
		ChoosePageData cpd = new ChoosePageData();// ����һ������
		value = cpd.beginTran(before, row, col);// �õ�ѡ�е�����
		/** **�õ���ѡ������*** */
		// ���ݷ�װ
		Transfer trans = new Transfer();
		trans.setValue(value);
		trans.setlineTitle(resultTitle);
		trans.setXscale(resultXScale);
		trans.setChartTitle(transfer.getChartTitle());

		String objStr = null;// ���ڴ�Ŷ����ַ���
		// ִ�����л�
		try {
			ObjectToObject otoStr = new ObjectToString();// �õ�����ת����
			objStr = (String) otoStr.transform(trans);
		} catch (ObjectToObjectException e) {
			// ��ת����Ϣҳ��
			// ������Ϣ
			request.setAttribute("message", "�������л�����");
			request.getRequestDispatcher("message.jsp").forward(request,
					response);
			return;
		}
		// ��������
		request.setAttribute("tran", objStr);
		// request.setAttribute("title", title);// ������ʾtitle
		// request.setAttribute("xScale", xScale);// ������ʾxScale
		// ��ת
		request.getRequestDispatcher("choose.jsp").forward(request, response);
	}
}
