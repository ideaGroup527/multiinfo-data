package org.plot.sevlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.plot.chart.MulChart;
import org.plot.chart.impl.MulBar;
import org.plot.util.Transfer;

public class MulBarChart extends HttpServlet {
	public MulBarChart() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ���ͼƬ����λ�õ�·��
		String graphURL = "";
		// ���ͼƬ�������Ϣ
		String chartTitle = "";
		String fileName = "";
		Transfer trans = new Transfer();
		String xTitle = trans.getXTitle();
		String yTitle = trans.getYTitle();
		String BarTitle[] = trans.getLineTitle();

		List value = trans.getValue();
		chartTitle = trans.getChartTitle();
		int width = trans.getWidth();
		int height = trans.getHeight();
		String[] scale = trans.getXscale();
		int idea = 1;// ��ͼ���

		// ѡ���ͼ�ķ�Χ
		// С��0Ϊȫ��ͼ 0��value.length-1 Ϊ���� ���ڵ���value.lengthΪ��choose=value.length
		int choose = 2;
		boolean lable = true;// 3ά�������״��������� û�б�ǩ ��������

		try {
			HttpSession session = request.getSession();
			// �����߶λ��Ƶ���
			MulChart chart = new MulBar(session,// �̶���ʽ����session******���봫��******
					chartTitle,// ͼƬ����
					BarTitle,// �����߶ε�����
					xTitle, // X�������
					yTitle, // Y�������
					scale, // X�̶�
					value, // X��Y���Ӧ��ֵ
					width, // ���
					1000,// �߶�
					idea,// 1Ϊ��ֵƽ�� 2Ϊ��ֵ3D 3Ϊ����ƽ�� 4Ϊ����3D
					choose, lable);

			// ���ͼƬ�����ƺͺ�׺
			fileName = chart.getFileName();
			// ���ͼƬ����ʱ·��
			graphURL = request.getContextPath()
					+ "/servlet/DisplayChart?filename=" + fileName;
			// ����ͼƬ�ķ���·��
			request.setAttribute("graphURL", graphURL);
			// ��ת����ʾҳ��
			request.getRequestDispatcher("show.jsp")
					.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
