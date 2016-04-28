package org.forecast.sevlet.getdata;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.forecast.calculate.DataDemo;
import org.forecast.exception.ObjectToObjectException;
import org.forecast.parse.ListDemo;
import org.forecast.parse.ObjectToObject;
import org.forecast.parse.ObjectToString;
import org.forecast.parse.ParseInteger;
import org.forecast.parse.StringToObject;
import org.forecast.util.ChoosePageData;

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

		int choose = 0;// ��ѡ��Ĳ���
		boolean isCol = false;// �Ƿ���ȫѡ
		boolean isRow = false;// �Ƿ���ȫѡ
		boolean isAll = false;// �Ƿ�����ȫѡ

		List value = null;// ѡ���Ľ������
		String[] tempCol = null;// ��ʱ�����
		String[] tempRow = null;// ��ʱ�����
		String list = null;// �����ַ���
		String  titleStr=null;

		String changeresult = null;//��������
		request.setCharacterEncoding("gb2312");
		// �õ������ַ���
		if (!"".equals(request.getParameter("changeresult"))
				&& null != request.getParameter("changeresult")) {
			changeresult = request.getParameter("changeresult");//�õ������	
			System.out.println("�����Ϊ��"+changeresult);
		}
		if (!"".equals(request.getParameter("objStr"))
				&& null != request.getParameter("objStr")) {
			list = request.getParameter("objStr");// �õ��������ݵĶ����ַ���
		}

		if (!"".equals(request.getParameterValues("changereason"))
				&& null != request.getParameterValues("changereason")) {
			tempCol = request.getParameterValues("changereason");// �õ���ѡ��
		}
		if (!"".equals(request.getParameterValues("row"))
				&& null != request.getParameterValues("row")) {
			tempRow = request.getParameterValues("row");// �õ���ѡ��
		}
		
		
		// �����л�
		ObjectToObject oto = new StringToObject();// �õ����л���
		List list2 = null;
		
	
		try {
			list2 = (List) oto.transform(list);// �����л��õ�����
			
		} catch (ObjectToObjectException e) {
			e.printStackTrace();
		}
		DataDemo dd = new DataDemo();
		
//		
//		changeresult=title2.get(titleindex).toString();
//		System.out.println(changeresult);
		
		// ��ҳ��õ�ѡ�������
		if (!"".equals(request.getParameter("isAll"))
				&& null != request.getParameter("isAll")) {// �Ƿ�ȫѡ
			isAll = true;
		}
		if (!"".equals(request.getParameter("isCol"))
				&& null != request.getParameter("isCol")) {// �Ƿ���ȫѡ
			isCol = true;
		}
		if (!"".equals(request.getParameter("isRow"))
				&& null != request.getParameter("isRow")) {// �Ƿ���ȫѡ
			isRow = true;
		}
		
		/***********************************************************************
		 * �߼��ж�1
		 **********************************************************************/
		if (isCol) {// ��ȫѡ
			choose = 1;
		}
		if (isRow) {// ��ȫѡ
			choose = 2;
		}
		if (isAll) {// ����ȫѡ
			choose = 3;
		}
		if (isCol && isRow) {// ����ȫѡ
			choose = 3;
		}
		if (choose == 0) {//��û��ѡ���κ�ѡ���ʱ���Ĭ����ȫѡ��
			if (!"".equals(tempCol) && null != tempCol && !"".equals(tempRow)
					&& null != tempRow) {
				//�����κ�ѡ��
			} else {
				choose = 3;//ʲô��û��ѡ��ͬ������ȫѡ
			}
		}
		/***********************************************************************
		 * �߼��ж�1
		 **********************************************************************/
		if (choose == 3) {
			value = list2;// ����ȫѡ�Ͱ�ԭ�����ݼ���ֵ��value
			
		}

		// �С���ȫѡ����ȫѡû��ʹ�� ���� ��ȫѡ ���� ��ȫѡ
		if (choose == 0 || choose == 1 || choose == 2) {
			int[] col = null;// ��ѡ����
			int[] row = null;// ��ѡ����
			// ���ַ�����ת��Ϊ����
			ParseInteger pi = new ParseInteger();// �õ�ǿת������
			/*******************************************************************
			 * �߼��жϸ�ֵ��ʼ
			 ******************************************************************/
			// �еĲ���
			if (choose == 1) {// ��ȫѡ
				col = new int[((List)(list2.get(0))).size()];// ��ѡ����
				
				for (int a = 0; a < col.length; a++) {
					col[a] = a;
				}
			} else if (null == tempCol || 0 == tempCol.length) {// ûѡ
				// û��ѡ������
			} else {// ����ѡ��
				col = new int[tempCol.length];// ��ѡ����
				
				for (int i = 0; i < tempCol.length; i++) {
					int subChoose = pi.parseInt(tempCol[i]);// ��ת��Ϊ����
					col[i] = subChoose;// ��ת��Ϊ����
//					System.out.println("aaa"+subChoose);
		
				}
				int temp=0;
				for(int i =0 ;i<tempCol.length;i++){
					System.out.println("aaa"+tempCol[i]);
					if(tempCol[i].equals(changeresult)){
						changeresult=new Integer(i).toString();
						System.out.println(changeresult);
						temp=1;
					}
				}
				
				if(temp==0){
					request.setAttribute("message", "�������ݱ���������ҪԤ�������");
					request.getRequestDispatcher("message.jsp").forward(request,
							response);
					return;
				}
			}
			// �еĲ���
			if (choose == 2) {// ��ȫѡ
				row = new int[list2.size()];

				for (int a = 0; a < row.length; a++) {
					row[a] = a;
				}
			} else if (null == tempRow || 0 == tempRow.length) {
				// û��ѡ������
			} else {// ����ѡ��
				row = new int[tempRow.length];// ��ѡ����
				for (int i = 0; i < tempRow.length; i++) {
					int subChoose = pi.parseInt(tempRow[i]);// ��ת��Ϊ����
					row[i] = subChoose;

				}
			}
			/*******************************************************************
			 * �߼��жϸ�ֵ����
			 ******************************************************************/

			// ִ����������
			ChoosePageData cpd = new ChoosePageData();// ����һ������
			value = cpd.beginTran(list2, col, row);// �õ�ѡ�е�����
		}
//		System.out.println("����Ԥ������ݣ�");
//		 for (int i = 0; i < value.size(); i++) {
//		 List temp = (List) value.get(i);
//		 for (int j = 0; j < temp.size(); j++) {
//		 System.out.print(temp.get(j) + "-");
//		 }
//		 System.out.println();
//		 }
		
		if(null==changeresult){
			request.setAttribute("message", "��ѡ�������");
			request.getRequestDispatcher("message.jsp").forward(request,
					response);
			return;
		}
		int index = new Integer(changeresult);
		DataDemo DD= new DataDemo();
		
		List value1=new ListDemo().DoubletoList2(dd.demo(new ListDemo().ListToDouble(value), index));
		String objStr = null;// ���ڴ�Ŷ����ַ���
		// ִ�����л�
		try {
			ObjectToObject otoStr = new ObjectToString();// �õ�����ת����
			objStr = (String) otoStr.transform(value1);
		} catch (ObjectToObjectException e) {
			e.printStackTrace();
		}
//		System.out.println("�����:"+changeresult);
		// ��������
		request.setAttribute("objStr", objStr);
	    request.setAttribute("changeresult", changeresult);// ������ʾtitle
		// request.setAttribute("xScale", xScale);// ������ʾxScale
		// ��ת
		request.getRequestDispatcher("choose.jsp").forward(request, response);
	}
}
