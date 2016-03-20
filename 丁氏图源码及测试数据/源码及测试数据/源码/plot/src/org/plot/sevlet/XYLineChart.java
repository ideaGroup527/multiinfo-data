package org.plot.sevlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.plot.chart.Chart;
import org.plot.chart.impl.XYLine;
import org.plot.exception.ObjectToObjectException;
import org.plot.parse.Normalization;
import org.plot.parse.ObjectToObject;
import org.plot.parse.ParseBoolean;
import org.plot.parse.ParseDouble;
import org.plot.parse.ParseInteger;
import org.plot.parse.StringToObject;
import org.plot.pojo.OtherKindChartFont;
import org.plot.util.Transfer;

public class XYLineChart extends HttpServlet {

	public XYLineChart() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ����ת��
		ParseInteger pi = new ParseInteger();
		ParseBoolean pb = new ParseBoolean();
		ParseDouble pd = new ParseDouble();
		
		String upBackColor = "#99CCFF";
		String downBackColor = "#99CCFF";
		String titleFont = "����";
		String titleSize = "25";
		String titleColor = "#3366FF";
		String xFont = "����";
		String xSize = "15";
		String xColor = "#3366FF";
		String yFont = "����";
		String ySize = "15";
		String yColor = "#3366FF";
		
		OtherKindChartFont other = new OtherKindChartFont() ;
		
		if (null != request.getParameter("upBackColor")
				&& !"".equals(request.getParameter("upBackColor"))) {
			// ��ҳ��ȡֵ��ǿ��ת��
			upBackColor = request.getParameter("upBackColor");
			upBackColor = upBackColor.substring(1, upBackColor.length()) ;
			other.setUpBackColor(upBackColor) ;
		}
		if (null != request.getParameter("downBackColor")
				&& !"".equals(request.getParameter("downBackColor"))) {
			// ��ҳ��ȡֵ��ǿ��ת��
			downBackColor = request.getParameter("downBackColor");
			downBackColor = downBackColor.substring(1, downBackColor.length()) ;
			other.setDownBackColor(downBackColor) ;
		}
		if (null != request.getParameter("titleFont")
				&& !"".equals(request.getParameter("titleFont"))) {
			// ��ҳ��ȡֵ��ǿ��ת��
			titleFont = request.getParameter("titleFont");
			other.setTitleFont(titleFont) ;
		}
		if (null != request.getParameter("titleSize")
				&& !"".equals(request.getParameter("titleSize"))) {
			// ��ҳ��ȡֵ��ǿ��ת��
			titleSize = request.getParameter("titleSize");
			try {
				other.setTitleSize(Integer.parseInt(titleSize)) ;
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
			other.setTitleColor(titleColor);
		}
		if (null != request.getParameter("yFont")
				&& !"".equals(request.getParameter("yFont"))) {
			// ��ҳ��ȡֵ��ǿ��ת��
			yFont = request.getParameter("yFont");
			other.setYFont(yFont) ;
		}
		if (null != request.getParameter("ySize")
				&& !"".equals(request.getParameter("ySize"))) {
			// ��ҳ��ȡֵ��ǿ��ת��
			ySize = request.getParameter("ySize");
			try {
				other.setYSize(Integer.parseInt(ySize)) ;
			} catch (NumberFormatException e) {
				// ��ת����Ϣҳ��
				// ������Ϣ
				request.setAttribute("message", "��˵�������С��ʽ����");
				request.getRequestDispatcher("message.jsp").forward(request,
						response);
				return;
			}
		}
		if (null != request.getParameter("yColor")
				&& !"".equals(request.getParameter("yColor"))) {
			// ��ҳ��ȡֵ��ǿ��ת��
			yColor = request.getParameter("yColor");
			yColor = yColor.substring(1, yColor.length()) ;
			other.setYColor(yColor) ;
		}
		if (null != request.getParameter("xFont")
				&& !"".equals(request.getParameter("xFont"))) {
			// ��ҳ��ȡֵ��ǿ��ת��
			xFont = request.getParameter("xFont");
			other.setXFont(xFont) ;
		}
		if (null != request.getParameter("xSize")
				&& !"".equals(request.getParameter("xSize"))) {
			// ��ҳ��ȡֵ��ǿ��ת��
			xSize = request.getParameter("xSize");
			try {
				other.setXSize(Integer.parseInt(xSize)) ;
			} catch (NumberFormatException e) {
				// ��ת����Ϣҳ��
				// ������Ϣ
				request.setAttribute("message", "��˵�������С��ʽ����");
				request.getRequestDispatcher("message.jsp").forward(request,
						response);
				return;
			}
		}
		if (null != request.getParameter("xColor")
				&& !"".equals(request.getParameter("xColor"))) {
			// ��ҳ��ȡֵ��ǿ��ת��
			xColor = request.getParameter("xColor");
			xColor = xColor.substring(1, xColor.length()) ;
			other.setXColor(xColor) ;
		}
		
		//�õ��������л�����ַ���
		String objStr = null;
		if (!"".equals(request.getParameter("tran"))
				&& null != request.getParameter("tran")) {
			objStr = request.getParameter("tran") ;
		}
		/** *�����л�����stringת��Ϊ����* */
		ObjectToObject oto = new StringToObject();// �õ�ת������
		Transfer trans = null ;
		try {
			trans = (Transfer) oto.transform(objStr);
		} catch (ObjectToObjectException e) {
			e.printStackTrace();
		}
		
		
		
		// ���ͼƬ����λ�õ�·��
		String graphURL = "";
		// ���ͼƬ�������Ϣ
		String chartTitle = "";
		String fileName = "";
		String xTitle = "";
		String yTitle = "";
		// �Ƿ���Ҫ���滯����
		boolean isNormalization = true;
		int width = 0;
		int height = 0;
		double maxLimit = 0;// �����
		double minLimit = 0;// ��С����
		List value = new ArrayList();
		// ���������
		boolean orient = false;

		String[] xScale = trans.getXscale();
		chartTitle = trans.getChartTitle();
		String[] LineTitle = trans.getLineTitle();
		value = trans.getValue() ;
		/***********************************************************************
		 * ����ҳ��ȡ���ݲ�����ʼ
		 **********************************************************************/
		// ���ȣ�������벻Ϊ����Ϊ���������������ǿ���Ĭ��Ϊ980
		if (null != request.getParameter("width")
				&& !"".equals(request.getParameter("width"))) {
			// ��ҳ��ȡֵ��ǿ��ת��
			width = Integer.parseInt(request.getParameter("width"));
		} else {
			// Ĭ�ϸ�ֵ
			width = 980;
		}
		// �߶ȣ�������벻Ϊ����Ϊ���������������ǿ���Ĭ��Ϊ450
		if (null != request.getParameter("height")
				&& !"".equals(request.getParameter("height"))) {
			// ��ҳ��ȡֵ��ǿ��ת��
			height = pi.parseInt(request.getParameter("height"));
		} else {
			// Ĭ�ϸ�ֵ
			height = 450;
		}

		// X������
		if (null != request.getParameter("xTitle")
				&& !"".equals(request.getParameter("xTitle"))) {
			// ��ҳ��ȡֵ��ǿ��ת��
			xTitle = request.getParameter("xTitle");
		} else {
			// Ĭ�ϸ�ֵ
			xTitle = "X��";
		}
		// Y������
		if (null != request.getParameter("yTitle")
				&& !"".equals(request.getParameter("yTitle"))) {
			// ��ҳ��ȡֵ��ǿ��ת��
			yTitle = request.getParameter("yTitle");
		} else {
			// Ĭ�ϸ�ֵ
			yTitle = "Y��";
		}

		// �ж��Ƿ���Ҫ���滯����
		if (null != request.getParameter("isNormalization")
				&& !"".equals(request.getParameter("isNormalization"))) {
			// ��ҳ��ȡֵ��ǿ��ת��
			isNormalization = pb.parseBoolean(request
					.getParameter("isNormalization"));
			
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
			if(maxLimit>0){
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
		} else {
			// Ĭ�ϸ�ֵ
			isNormalization = true;
		}
		if (isNormalization) {
			// ���滯����
			Normalization sw = new Normalization();
			value = sw.min_Switch(trans.getValue(), maxLimit, minLimit);// ת��Ϊ0~1��ʾ
		} else {
			// �����滯����
			// value = value;
		}
		
		// �ж��Ƿ���ת
		if (null != request.getParameter("orient")
				&& !"".equals(request.getParameter("orient"))) {
			// ��ҳ��ȡֵ��ǿ��ת��
			orient = pb.parseBoolean(request
					.getParameter("orient"));
		} else {
			// Ĭ�ϸ�ֵ
			orient = false;
		}
		/***********************************************************************
		 * ����ҳ��ȡ���ݲ��������
		 **********************************************************************/

		try {
			HttpSession session = request.getSession();
			// �����߶λ��Ƶ���
			Chart chart = new XYLine(session,// �̶���ʽ����session
					// ******���봫��******
					chartTitle,// ͼƬ����
					LineTitle,// �����߶ε�����
					xTitle, // X�������
					yTitle, // Y�������
					value, // X��Y���Ӧ��ֵ
					width, // ���
					height,// �߶�
					xScale,// ��-2ά
					orient,other);

			// ���ͼƬ�����ƺͺ�׺
			fileName = chart.getFileName();
			// ���ͼƬ����ʱ·��
			graphURL = request.getContextPath()
					+ "/servlet/DisplayChart?filename=" + fileName;
			// ����ͼƬ�ķ���·��
			request.setAttribute("graphURL", graphURL);
			// ��ת����ʾҳ��
			request.getRequestDispatcher("show.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
