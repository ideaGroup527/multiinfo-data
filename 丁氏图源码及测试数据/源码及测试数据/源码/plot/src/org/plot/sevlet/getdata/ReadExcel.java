package org.plot.sevlet.getdata;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.plot.exception.ObjectToObjectException;
import org.plot.parse.ObjectToObject;
import org.plot.parse.ObjectToString;
import org.plot.parse.ParseDouble;
import org.plot.parse.ParseInteger;
import org.plot.util.ListRound;
import org.plot.util.Transfer;

public class ReadExcel extends HttpServlet {

	public ReadExcel() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ParseInteger pi = new ParseInteger();// �õ�ǿת����
		// �õ���Ҫ��ȡ��excel·��
		String excelPath = null;
		if (!"".equals(request.getParameter("excelPath"))
				&& null != request.getParameter("excelPath")) {
			excelPath = request.getParameter("excelPath");// �õ�·��
		}
		int sheetNO = 0;// ��ѡ������
		if (!"".equals(request.getParameter("sheetNO"))
				&& null != request.getParameter("sheetNO")) {
			String tempNO = request.getParameter("sheetNO");// �õ��ַ���
			sheetNO = pi.parseInt(tempNO)-1;// ִ��ǿת

		}
		try {
			// ���һ������������
			Workbook book = Workbook.getWorkbook(new File(excelPath));
			int allSheetNO = book.getNumberOfSheets();// �õ�������ĸ���
			if (sheetNO >allSheetNO || sheetNO < 0) {
				// ��ת����Ϣҳ��
				// ������Ϣ
				request.setAttribute("message", "��ѡ�Ĺ��������ڣ�");
				request.getRequestDispatcher("message.jsp").forward(request,
						response);
				return;
			}else{
				//�����κζ���
			}

			// �õ�һ�����������
			Sheet sheet = book.getSheet(sheetNO);
			int col = sheet.getColumns();// �еĸ���
			String picName = sheet.getName() ;//�õ������������
			// 1. ��õ�һ�����ݣ�X�������
			Cell[] xCell = sheet.getColumn(0);
			if (xCell.length < 2) {// �������ڶ��������Ϲ淶
				// ��ת����Ϣҳ��
				// ������Ϣ
				request.setAttribute("message", "�ñ����������㣬��������2��");
				request.getRequestDispatcher("message.jsp").forward(request,
						response);
				return;
			}
			// �ӵڶ�����ʼ�Ƕ�Ӧ��X��̶�
			String[] xScale = new String[xCell.length - 1];// ����ռ�
			for (int i = 0; i < xCell.length - 1; i++) {// ��һ��������Ҫ������
				try {
					String tempStr = xCell[i + 1].getContents();// �������
					if (!"".equals(tempStr) && null != tempStr) {
						xScale[i] = xCell[i + 1].getContents();// �������
					} else {
						xScale[i] = "--";// Ϊ��
					}
				} catch (Exception e) {
					xScale[i] = "--";// ���ִ�����-����
				}
			}

			// 2.��ȡÿ�����ݵ�����
			Cell[] titleCell = sheet.getRow(0);
			if (titleCell.length < 2) {// �������ڶ��������Ϲ淶
				// ��ת����Ϣҳ��
				// ������Ϣ
				request.setAttribute("message", "�ñ����������㣬��������2��");
				request.getRequestDispatcher("message.jsp").forward(request,
						response);
				return;
			}
			// �ӵڶ�����ʼ�Ƕ�Ӧ������
			String[] title = new String[titleCell.length - 1];// ����ռ�
			for (int i = 0; i < titleCell.length - 1; i++) {// ��һ��������Ҫ������
				try {
					String tempStr = titleCell[i + 1].getContents();
					if (!"".equals(tempStr) && null != tempStr) {
						title[i] = titleCell[i + 1].getContents();// �������
					} else {
						title[i] = "--";// Ϊ��
					}
				} catch (Exception e) {
					title[i] = "--";// ���ִ�����-����
				}
			}

			// 3.��ȡÿ�������
			ParseDouble pd = new ParseDouble();// ǿת
			List value = new ArrayList();// ��㼯�ϣ����ڴ�Ű�װ�õ�ÿһ������
			for (int i = 1; i < col; i++) {// ��һ��������X�����ƣ�����Ҫ
				Cell[] cell = sheet.getColumn(i);// ȡ��һ�е�����
				List valueInner = new ArrayList();// �ڲ㼯�ϣ����ڰ�װһ�������
				int row = cell.length;// ���е����ݸ���
				for (int j = 1; j <= xScale.length; j++) {// ��һ�����������ƣ�����Ҫ
					String message = null;// ���ִ�����-����
					try {
						String tempStr = cell[j].getContents();
						if (!"".equals(tempStr) && null != tempStr) {
							message = cell[j].getContents();// ���String������
						} else {
							message = "0.0";// Ϊ�����Ĭ��ֵ
						}
					} catch (Exception e) {
						message = "0.0";// �������Ĭ��ֵ
					}
					double messageDouble = pd.parseDouble(message);
					valueInner.add(messageDouble);// ��ÿһ�����ݽ������ݷ�װ
				}
				value.add(valueInner);// ��һ���װ�õ����ݷ�����㼯��
			}

			// ����һ��������ҳ����ʾ��List
			List showList = null;
			ListRound lr = new ListRound();// ת������List����
			showList = lr.beginTrans(value);// ִ��ת��

			// ��õ����ݷ�װ��Transfer����
			Transfer tran = new Transfer();
			tran.setValue(value);// value��װ
			tran.setXscale(xScale);// xScale��װ
			tran.setlineTitle(title);// title��װ
			tran.setChartTitle(picName);// ��װͼƬ����

			// �������ַ���������
			ObjectToObject oto = new ObjectToString();
			String objStr = null;// ת����Ľ��
			try {
				objStr = (String) oto.transform(tran);// �õ������Ӧ���ַ���
			} catch (ObjectToObjectException e) {
				e.printStackTrace();
			}

			request.setAttribute("showList", showList);// ������ʾList
			request.setAttribute("title", title);// ������ʾtitle
			request.setAttribute("picName", picName);// ������ʾͼ����title
			request.setAttribute("xScale", xScale);// ������ʾxScale
			request.setAttribute("tran", objStr);// tran��װ��request�����ݴ���
			request.getRequestDispatcher("showData.jsp").forward(request,
					response);

		} catch (BiffException e) {
			// ��ת����Ϣҳ��
			// ������Ϣ
			request.setAttribute("message", "������ļ����ͣ�");
			request.getRequestDispatcher("message.jsp").forward(request,
					response);
			e.printStackTrace();
			return;
		} catch (FileNotFoundException e) {
			// ��ת����Ϣҳ��
			// ������Ϣ
			request.setAttribute("message", "ϵͳ�Ҳ���ָ����·��,��ȷ�ļ�·�����������ַ����ڣ�");
			request.getRequestDispatcher("message.jsp").forward(request,
					response);
			e.printStackTrace();
			return;
		} catch (IOException e) {
			// ��ת����Ϣҳ��
			// ������Ϣ
			request.setAttribute("message", "���ֶ�ȡ����");
			request.getRequestDispatcher("message.jsp").forward(request,
					response);
			e.printStackTrace();
			return;
		}

	}
}
