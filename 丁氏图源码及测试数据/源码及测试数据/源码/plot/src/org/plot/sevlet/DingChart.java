package org.plot.sevlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.plot.chart.impl.Ding;
import org.plot.chart.impl.Ding1;
import org.plot.chart.impl.Ding2;
import org.plot.exception.ObjectToObjectException;
import org.plot.parse.ObjectToObject;
import org.plot.parse.ParseBoolean;
import org.plot.parse.ParseDouble;
import org.plot.parse.ParseInteger;
import org.plot.parse.StringToObject;
import org.plot.pojo.ChartFont;
import org.plot.util.Transfer;

public class DingChart extends HttpServlet {

	public DingChart() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ParseDouble pd = new ParseDouble();
		ParseBoolean pb = new ParseBoolean();
		double maxLimit = 0;// �����
		double minLimit = 0;// ��С����
		boolean left_right = false;
		boolean up_down = false;
		String upBackColor = "#99CCFF";
		String downBackColor = "#99CCFF";
		String lineColor = "#0000FF";
		String roundColor = "#FF0000";
		String titleFont = "����";
		String titleSize = "25";
		String titleColor = "#3366FF";
		String rowFont = "����";
		String rowSize = "15";
		String rowColor = "#3366FF";
		String colFont = "����";
		String colSize = "15";
		String colColor = "#3366FF";

		//��ɫ������
		ChartFont cf = new ChartFont();
		if (null != request.getParameter("upBackColor")
				&& !"".equals(request.getParameter("upBackColor"))) {
			// ��ҳ��ȡֵ��ǿ��ת��
			upBackColor = request.getParameter("upBackColor");
			upBackColor = upBackColor.substring(1, upBackColor.length()) ;
			cf.setUpBackColor(upBackColor) ;
		}
		if (null != request.getParameter("downBackColor")
				&& !"".equals(request.getParameter("downBackColor"))) {
			// ��ҳ��ȡֵ��ǿ��ת��
			downBackColor = request.getParameter("downBackColor");
			downBackColor = downBackColor.substring(1, downBackColor.length()) ;
			cf.setDownBackColor(downBackColor) ;
		}
		if (null != request.getParameter("lineColor")
				&& !"".equals(request.getParameter("lineColor"))) {
			// ��ҳ��ȡֵ��ǿ��ת��
			lineColor = request.getParameter("lineColor");
			lineColor = lineColor.substring(1, lineColor.length()) ;
			cf.setLineColor(lineColor) ;
		}
		if (null != request.getParameter("roundColor")
				&& !"".equals(request.getParameter("roundColor"))) {
			// ��ҳ��ȡֵ��ǿ��ת��
			roundColor = request.getParameter("roundColor");
			roundColor = roundColor.substring(1, roundColor.length()) ;
			cf.setRoundColor(roundColor) ;
		}
		if (null != request.getParameter("titleFont")
				&& !"".equals(request.getParameter("titleFont"))) {
			// ��ҳ��ȡֵ��ǿ��ת��
			titleFont = request.getParameter("titleFont");
			cf.setTitleFont(titleFont) ;
		}
		if (null != request.getParameter("titleSize")
				&& !"".equals(request.getParameter("titleSize"))) {
			// ��ҳ��ȡֵ��ǿ��ת��
			titleSize = request.getParameter("titleSize");
			try {
				cf.setTitleSize(Integer.parseInt(titleSize)) ;
			} catch (NumberFormatException e) {
				// ��ת����Ϣҳ��
				// ������Ϣ
				request.setAttribute("message", "���������С��ʽ����");
				request.getRequestDispatcher("message.jsp").forward(request,
						response);
				return;
			}
		}
		if (null != request.getParameter("titleColor")
				&& !"".equals(request.getParameter("titleColor"))) {
			// ��ҳ��ȡֵ��ǿ��ת��
			titleColor = request.getParameter("titleColor");
			titleColor = titleColor.substring(1, titleColor.length()) ;
			cf.setTitleColor(titleColor);
		}
		if (null != request.getParameter("rowFont")
				&& !"".equals(request.getParameter("rowFont"))) {
			// ��ҳ��ȡֵ��ǿ��ת��
			rowFont = request.getParameter("rowFont");
			cf.setRowFont(rowFont) ;
		}
		if (null != request.getParameter("rowSize")
				&& !"".equals(request.getParameter("rowSize"))) {
			// ��ҳ��ȡֵ��ǿ��ת��
			rowSize = request.getParameter("rowSize");
			try {
				cf.setRowSize(Integer.parseInt(rowSize)) ;
			} catch (NumberFormatException e) {
				// ��ת����Ϣҳ��
				// ������Ϣ
				request.setAttribute("message", "��˵�������С��ʽ����");
				request.getRequestDispatcher("message.jsp").forward(request,
						response);
				return;
			}
		}
		if (null != request.getParameter("rowColor")
				&& !"".equals(request.getParameter("rowColor"))) {
			// ��ҳ��ȡֵ��ǿ��ת��
			rowColor = request.getParameter("rowColor");
			rowColor = rowColor.substring(1, rowColor.length()) ;
			cf.setRowColor(rowColor) ;
		}
		if (null != request.getParameter("colFont")
				&& !"".equals(request.getParameter("colFont"))) {
			// ��ҳ��ȡֵ��ǿ��ת��
			colFont = request.getParameter("colFont");
			cf.setColFont(colFont) ;
		}
		if (null != request.getParameter("colSize")
				&& !"".equals(request.getParameter("colSize"))) {
			// ��ҳ��ȡֵ��ǿ��ת��
			colSize = request.getParameter("colSize");
			try {
				cf.setColSize(Integer.parseInt(colSize)) ;
			} catch (NumberFormatException e) {
				// ��ת����Ϣҳ��
				// ������Ϣ
				request.setAttribute("message", "��˵�������С��ʽ����");
				request.getRequestDispatcher("message.jsp").forward(request,
						response);
				return;
			}
		}
		if (null != request.getParameter("colColor")
				&& !"".equals(request.getParameter("colColor"))) {
			// ��ҳ��ȡֵ��ǿ��ת��
			colColor = request.getParameter("colColor");
			colColor = colColor.substring(1, colColor.length()) ;
			cf.setColColor(colColor) ;
		}

		// �ж��Ƿ���Ҫ�Զ������ֵ
		if (null != request.getParameter("left_right")
				&& !"".equals(request.getParameter("left_right"))) {
			// ��ҳ��ȡֵ��ǿ��ת��
			left_right = pb.parseBoolean(request.getParameter("left_right"));
		} else {
			// Ĭ�ϸ�ֵ
			left_right = false;
		}

		// �ж��Ƿ���Ҫ�Զ������ֵ
		if (null != request.getParameter("up_down")
				&& !"".equals(request.getParameter("up_down"))) {
			// ��ҳ��ȡֵ��ǿ��ת��
			up_down = pb.parseBoolean(request.getParameter("up_down"));
		} else {
			// Ĭ�ϸ�ֵ
			up_down = false;
		}

		// �ж��Ƿ���Ҫ�Զ������ֵ
		if (null != request.getParameter("maxLimit")
				&& !"".equals(request.getParameter("maxLimit"))) {
			// ��ҳ��ȡֵ��ǿ��ת��
			maxLimit = pd.parseDouble(request.getParameter("maxLimit"));
		} else {
			// Ĭ�ϸ�ֵ
			maxLimit = 0.0;
		}

		// �ж��Ƿ���Ҫ�Զ�����Сֵ
		if (null != request.getParameter("minLimit")
				&& !"".equals(request.getParameter("minLimit"))) {
			// ��ҳ��ȡֵ��ǿ��ת��
			minLimit = pd.parseDouble(request.getParameter("minLimit"));
		} else {
			// Ĭ�ϸ�ֵ
			minLimit = 0.0;
		}
		// �ж����淶Χ����Ч��
		if (maxLimit > 1 || maxLimit < 0) {
			// value����㼯�ϴ�С����������ͬ
			// �洢��Ϣ��ʾ
			request.setAttribute("message", "���������Ӧ����0~1");
			// ��ת
			request.getRequestDispatcher("message.jsp").forward(request,
					response);
			// ���ؿ�
			return;
		}
		if (minLimit > 1 || minLimit < 0) {
			// value����㼯�ϴ�С����������ͬ
			// �洢��Ϣ��ʾ
			request.setAttribute("message", "��С������Ӧ����0~1");
			// ��ת
			request.getRequestDispatcher("message.jsp").forward(request,
					response);
			// ���ؿ�
			return;
		}
		if (maxLimit > 0) {
			if (minLimit > maxLimit) {
				// value����㼯�ϴ�С����������ͬ
				// �洢��Ϣ��ʾ
				request.setAttribute("message", "��С���������ܴ������������");
				// ��ת
				request.getRequestDispatcher("message.jsp").forward(request,
						response);
				// ���ؿ�
				return;
			}
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
		ParseInteger pi = new ParseInteger();

		String[] dingTitle = trans.getLineTitle();
		String xTitle = trans.getXTitle();
		String yTitle = trans.getYTitle();
		String chartTitle = trans.getChartTitle();
		List value = trans.getValue();
		String[] xScale = trans.getXscale();

		/** *********************************�жϻ�ͼ����*********************************** */
		String flag = null;
		flag = "ZXDB";
		// �ж�Ҫ���ƵĶ���ͼ����
		if (null != request.getParameter("dingType")
				&& !"".equals(request.getParameter("dingType"))) {
			// ��ֵ��ͼ����
			flag = request.getParameter("dingType");
			// �ж��Ƿ���ϻ�ͼ����
			if (!flag.equals("ZXDB") && !flag.equals("HXDB")
					&& !flag.equals("QBDB")) {
				// �������ͣ�ʹ��Ĭ��ֵ
				flag = "ZXDB";
			}
		}
		/** *********************************�жϻ�ͼ�������*********************************** */
		// �ж���Ҫ���ƵĶ���ͼ����
		if ("ZXDB".equals(flag)) {
			// ����Ա�
			Ding dingChart = new Ding(response, chartTitle,// ͼƬ����
					dingTitle,// �����߶ε�����
					xTitle, // X�������
					yTitle, // Y�������
					value, // X��Y���Ӧ��ֵ
					xScale,// X��
					maxLimit, minLimit, left_right, up_down,cf);
		} else if ("HXDB".equals(flag)) {
			// ����Ա�
			Ding1 dingChart = new Ding1(response, chartTitle,// ͼƬ����
					dingTitle,// �����߶ε�����
					xTitle, // X�������
					yTitle, // Y�������
					value, // X��Y���Ӧ��ֵ
					xScale,// X��
					maxLimit, minLimit, left_right, up_down,cf

			);
		} else if ("QBDB".equals(flag)) {
			// ȫ��Ա�
			Ding2 dingChart = new Ding2(response, chartTitle,// ͼƬ����
					dingTitle,// �����߶ε�����
					xTitle, // X�������
					yTitle, // Y�������
					value, // X��Y���Ӧ��ֵ
					xScale,// X��
					maxLimit, minLimit, left_right, up_down,cf);
		} else {
			request.setAttribute("message", "û�����ֻ�ͼ����");
			// ��ת
			request.getRequestDispatcher("message.jsp").forward(request,
					response);
			// ���ؿ�
			return;
		}

		// �������������ͬ����ĳ�ͻ����
		// out.clear();
		// out = pageContext.pushBody();

	}

}
