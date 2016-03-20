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
 * 第二次数据选择使用，如bar和pie图的绘制使用的中转站
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
		int chartKind = 0;// 1为bar、2为pie、3为折线、4为丁氏图 、5为散点图
		// 得到种类
		if (!"".equals(request.getParameter("chartKind"))
				&& null != request.getParameter("chartKind")) {
			if ("bar".equals(request.getParameter("chartKind"))) {
				chartKind = 1;// 选择的绘图方式为bar
			} else if ("pie".equals(request.getParameter("chartKind"))) {
				chartKind = 2;// 选择的绘图方式为pie
			} else if ("line".equals(request.getParameter("chartKind"))) {
				chartKind = 3;// 选择的绘图方式为line
			} else if ("ding".equals(request.getParameter("chartKind"))) {
				chartKind = 4;// 选择的绘图方式为ding
			} else if ("disperse".equals(request.getParameter("chartKind"))) {
				chartKind = 5;// 选择的绘图方式为disperse
			} else {
				// 出错
				request.setAttribute("message", "没有这种绘图方法");
				// 跳转
				request.getRequestDispatcher("message.jsp").forward(request,
						response);
				// 返回空
				return;
			}
		}
		// 得到数据集
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

		/** *反序列化，把string转换为对象* */
		ObjectToObject oto = new StringToObject();// 得到转换对象
		Transfer trans = null;
		try {
			trans = (Transfer) oto.transform(objStr);
		} catch (ObjectToObjectException e) {
			e.printStackTrace();
		}
		ParseInteger pi = new ParseInteger();// 强转对象
		// 得到数据
		String[] title = trans.getLineTitle();
		String chartTitle = trans.getChartTitle();
		List value = trans.getValue();
		String[] xScale = trans.getXscale();
		String picName = trans.getChartTitle();

		// 创建一个用于在页面显示的List
		List showList = null;
		ListRound lr = new ListRound();// 转换横纵List的类
		showList = lr.beginTrans(value);// 执行转换

		// 保存对象信息到request中
		request.setAttribute("tran", objStr);// 对象字符串
		request.setAttribute("showList", showList);// 用于显示List
		request.setAttribute("title", title);// 用于显示title
		request.setAttribute("picName", picName);// 用于显示图名称title
		request.setAttribute("xScale", xScale);// 用于显示xScale
		// 执行跳转
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
