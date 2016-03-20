package org.plot.sevlet.getdata;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.plot.exception.ObjectToObjectException;
import org.plot.parse.ObjectToObject;
import org.plot.parse.ParseInteger;
import org.plot.parse.StringToObject;
import org.plot.util.ListRound;
import org.plot.util.Transfer;

/**
 * �ڶ�������ѡ��ʹ�ã���bar��pieͼ�Ļ���ʹ�õ���תվ
 */
public class MidChooseData extends HttpServlet {

	public MidChooseData() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int chartKind = 0;// 1Ϊbar��2Ϊpie��3Ϊ���ߡ�4Ϊ����ͼ ��5Ϊɢ��ͼ
		// �õ�����
		if (!"".equals(request.getParameter("chartKind"))
				&& null != request.getParameter("chartKind")) {
			if ("bar".equals(request.getParameter("chartKind"))) {
				chartKind = 1;// ѡ��Ļ�ͼ��ʽΪbar
			} else if ("pie".equals(request.getParameter("chartKind"))) {
				chartKind = 2;// ѡ��Ļ�ͼ��ʽΪpie
			} else if ("line".equals(request.getParameter("chartKind"))) {
				chartKind = 3;// ѡ��Ļ�ͼ��ʽΪline
			} else if ("ding".equals(request.getParameter("chartKind"))) {
				chartKind = 4;// ѡ��Ļ�ͼ��ʽΪding
			} else if ("disperse".equals(request.getParameter("chartKind"))) {
				chartKind = 5;// ѡ��Ļ�ͼ��ʽΪdisperse
			} else {
				// ����
				request.setAttribute("message", "û�����ֻ�ͼ����");
				// ��ת
				request.getRequestDispatcher("message.jsp").forward(request,
						response);
				// ���ؿ�
				return;
			}
		}
		// �õ����ݼ�
		String tran = null;
		if (!"".equals(request.getParameter("tran"))
				&& null != request.getParameter("tran")) {
			tran = request.getParameter("tran");
		}

		String objStr = null;
		if (!"".equals(request.getParameter("tran"))
				&& null != request.getParameter("tran")) {
			objStr = request.getParameter("tran");
		}

		/** *�����л�����stringת��Ϊ����* */
		ObjectToObject oto = new StringToObject();// �õ�ת������
		Transfer trans = null;
		try {
			trans = (Transfer) oto.transform(objStr);
		} catch (ObjectToObjectException e) {
			e.printStackTrace();
		}
		ParseInteger pi = new ParseInteger();// ǿת����
		// �õ�����
		String[] title = trans.getLineTitle();
		String chartTitle = trans.getChartTitle();
		List value = trans.getValue();
		String[] xScale = trans.getXscale();
		String picName = trans.getChartTitle();

		// ����һ��������ҳ����ʾ��List
		List showList = null;
		ListRound lr = new ListRound();// ת������List����
		showList = lr.beginTrans(value);// ִ��ת��

		// ���������Ϣ��request��
		request.setAttribute("tran", objStr);// �����ַ���
		request.setAttribute("showList", showList);// ������ʾList
		request.setAttribute("title", title);// ������ʾtitle
		request.setAttribute("picName", picName);// ������ʾͼ����title
		request.setAttribute("xScale", xScale);// ������ʾxScale
		// ִ����ת
		switch (chartKind) {
		case 1:
			request.getRequestDispatcher("showBarData.jsp").forward(request,
					response);
			break;
		case 2:
			request.getRequestDispatcher("showPieData.jsp").forward(request,
					response);
			break;
		case 3:
			request.getRequestDispatcher("showLineData.jsp").forward(request,
					response);
			break;
		case 4:
			request.getRequestDispatcher("showDingData.jsp").forward(request,
					response);
			break;
		case 5:
			request.getRequestDispatcher("showDisperseData.jsp").forward(request,
					response);
			break;
		}

	}

}
