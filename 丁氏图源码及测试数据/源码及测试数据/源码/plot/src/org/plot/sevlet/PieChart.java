package org.plot.sevlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.plot.chart.Chart;
import org.plot.chart.impl.Pie;
import org.plot.exception.ObjectToObjectException;
import org.plot.parse.ObjectToObject;
import org.plot.parse.ParseBoolean;
import org.plot.parse.ParseInteger;
import org.plot.parse.StringToObject;
import org.plot.pojo.OtherKindChartFont;
import org.plot.util.Transfer;

public class PieChart extends HttpServlet {

	public PieChart() {
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
		
		String upBackColor = "#99CCFF";
		String downBackColor = "#99CCFF";
		String titleFont = "����";
		String titleSize = "25";
		String titleColor = "#3366FF";
		String pieLableFont = "����";
		String pieLableSize = "15";
		String pieLableColor = "#3366FF";
		
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
		
		if (null != request.getParameter("pieLableFont")
				&& !"".equals(request.getParameter("pieLableFont"))) {
			// ��ҳ��ȡֵ��ǿ��ת��
			pieLableFont = request.getParameter("pieLableFont");
			pieLableFont = pieLableFont.substring(1, pieLableFont.length()) ;
			other.setPieLableFont(pieLableFont);
		}
		if (null != request.getParameter("pieLableColor")
				&& !"".equals(request.getParameter("pieLableColor"))) {
			// ��ҳ��ȡֵ��ǿ��ת��
			pieLableColor = request.getParameter("pieLableColor");
			pieLableColor = pieLableColor.substring(1, pieLableColor.length()) ;
			other.setPieLableColor(pieLableColor);
		}
		if (null != request.getParameter("pieLableSize")
				&& !"".equals(request.getParameter("pieLableSize"))) {
			// ��ҳ��ȡֵ��ǿ��ת��
			pieLableSize = request.getParameter("pieLableSize");
			try {
				other.setPieLableSize(Integer.parseInt(pieLableSize)) ;
			} catch (NumberFormatException e) {
				// ��ת����Ϣҳ��
				// ������Ϣ
				request.setAttribute("message", "��ǩ�����С��ʽ����");
				request.getRequestDispatcher("message.jsp").forward(request,
						response);
				return;
			}
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
		// falseΪ3D tureΪƽ���
		boolean idea = true;
		// ���л��߰��л���,true Ϊ���� falseΪ����
		boolean lineOrRow = true;
		// ѡ���л����У��ڼ��еڼ���
		int choose = 0;
		// ͼƬ���·��
		String fileName = "";
		String[] xScale = null;
		xScale = trans.getXscale();
		String[] pieTitle = trans.getLineTitle();
		chartTitle = trans.getChartTitle();
		List value = trans.getValue();
		int width = 0;
		int height = 0;

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
		// �ж��ǻ���ƽ�滹��3D��idea��
		if (null != request.getParameter("idea")
				&& !"".equals(request.getParameter("idea"))) {
			// ��ҳ��ȡֵ��ǿ��ת��
			idea = pb.parseBoolean(request.getParameter("idea"));
		} else {
			// Ĭ�ϸ�ֵ
			idea = true;
		}
		// �ж������л�������
		if (null != request.getParameter("row")
				&& !"".equals(request.getParameter("row"))
				&& null != request.getParameter("line")
				&& !"".equals(request.getParameter("line"))) {
			// ��ѡ��������ѡ�����У�����Ĳ���
			// �洢��Ϣ��ʾ
			request.setAttribute("message", "����Ĳ�������ѡ��������ѡ������");
			// ��ת
			request.getRequestDispatcher("message.jsp").forward(request,
					response);
			// ���ؿ�
			return;
		} else if (null != request.getParameter("chooseAll")
				&& !"".equals(request.getParameter("chooseAll"))) {
			// ���л��Ʊ�ʶ��
			lineOrRow = true;
			// ѡ���������ݣ��������ݽ���ͳ��
			choose = -1;
		} else if (null != request.getParameter("line")
				&& !"".equals(request.getParameter("line"))) {
			// ���л��Ʊ�ʶ��
			lineOrRow = true;
			// ��ѡ��
			choose = pi.parseInt(request.getParameter("line"));
		} else if (null != request.getParameter("row")
				&& !"".equals(request.getParameter("row"))) {
			// ���л��Ʊ�ʶ��
			lineOrRow = false;
			// ��ѡ��
			choose = pi.parseInt(request.getParameter("row"));
		}

		/***********************************************************************
		 * ����ҳ��ȡ���ݲ��������
		 **********************************************************************/

		// �ж�
		if (value.size() != pieTitle.length) {
			// value����㼯�ϴ�С����������ͬ
			// �洢��Ϣ��ʾ
			request.setAttribute("message", "�����������ֵ������ͬ");
			// ��ת
			request.getRequestDispatcher("message.jsp").forward(request,
					response);
			// ���ؿ�
			return;
		}

		try {
			HttpSession session = request.getSession();
			Chart chart = null;
			// �����߶λ��Ƶ���
			if (!lineOrRow) {
				// ����ȡ
				chart = new Pie(session,// �̶���ʽ����session ******���봫��******
						pieTitle[choose],// ͼƬ����
						pieTitle,// �����߶ε�����
						value, // X��Y���Ӧ��ֵ
						xScale,// x��Ŀ̶�
						width, // ���
						height,// �߶�
						idea,// falseΪ3D tureΪƽ���
						choose,// ѡ���л����У��ڼ��еڼ���
						other
				);
			} else {
				// ����ȡ
				chart = new Pie(session,// �̶���ʽ����session ******���봫��******
						chartTitle,// ͼƬ����
						pieTitle,// �����߶ε�����
						value, // X��Y���Ӧ��ֵ
						width, // ���
						height,// �߶�
						idea,// falseΪ3D tureΪƽ���
						choose,// ѡ���л����У��ڼ��еڼ���
						other
				);
			}
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
