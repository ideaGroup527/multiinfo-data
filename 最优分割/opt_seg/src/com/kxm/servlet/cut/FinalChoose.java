package com.kxm.servlet.cut;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kxm.exception.ObjectToObjectException;
import com.kxm.parse.ObjectToObject;
import com.kxm.parse.ObjectToString;
import com.kxm.parse.StringToObject;
import com.kxm.pojo.CutParam;
import com.kxm.util.Transfer;

public class FinalChoose extends HttpServlet {

	/**
	 * �Ͻ�minResult �½�finalChoose
	 */
	public FinalChoose() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String tran = null;       // ԭʼ���ݱ������ַ���
		String tmpCutnum = null;    // �ݴ����Mycutnum
		int cutnum = 0;             // ����cutnum
		
		String title = null;      //����
		String[] xScale = null;     //��־��
	
		// �õ������ַ���
		if (!"".equals(request.getParameter("tran"))
				&& null != request.getParameter("tran")) {
			tran = request.getParameter("tran");// �õ������ַ���
		}
		
		if (!"".equals(request.getParameter("MyCutnum"))
				&& null != request.getParameter("MyCutnum")) {
			tmpCutnum = request.getParameter("MyCutnum");// �õ���������
			cutnum = (Integer.valueOf(tmpCutnum)).intValue(); //ת��������
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
		title = transfer.getChartTitle();
		String[][][] result = transfer.getResult();
		String[][] finalresult = new String[cutnum][4];
		for(int i = 0;i<cutnum;i++){
			for(int j = 0;j<4;j++){
				finalresult[i][j] = result[cutnum-2][i][j]; //ȡ����ѡ��ķֶν����
			}
		}
		String[] cutend = new String[finalresult.length];//����ÿ�ζ�ĩ���
		for(int i = 0;i<finalresult.length;i++){
			cutend[i] = finalresult[i][2];
		}
		transfer.setCutend(cutend);
		
		String objStr = null;// ���ڴ�Ŷ����ַ���
		// ִ�����л�
		try {
			ObjectToObject otoStr = new ObjectToString();// �õ�����ת����
			objStr = (String) otoStr.transform(transfer);
		} catch (ObjectToObjectException e) {
			// ��ת����Ϣҳ��
			// ������Ϣ
			request.setAttribute("message", "�������л�����");
			request.getRequestDispatcher("message.jsp").forward(request,
					response);
			return;
		}
		
		request.setAttribute("tran", objStr);  //���ݼ�
		request.setAttribute("title", title);  //����
		request.setAttribute("cutend", cutend);  //�ָ�ζ�ĩ���
		request.getRequestDispatcher("finalChoose.jsp").forward(request,
				response);
	}

}
