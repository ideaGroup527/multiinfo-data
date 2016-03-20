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

		List value = null;// 选择后的结果集合
		String[] tempCol = null;// 临时存放列
		String[] tempRow = null;// 临时存放行
		String tran = null;// 对象字符串
		String[] title = null;// 标题
		String[] xScale = null;// 横坐标
		String[] resultTitle = null;// 存放结果标题
		String[] resultXScale = null;// 存放结果横坐标

		// 得到对象字符串
		if (!"".equals(request.getParameter("tran"))
				&& null != request.getParameter("tran")) {
			tran = request.getParameter("tran");// 得到对象字符串
		}

		if (!"".equals(request.getParameterValues("col"))
				&& null != request.getParameterValues("col")) {
			tempCol = request.getParameterValues("col");// 得到所选列
		}
		if (!"".equals(request.getParameterValues("row"))
				&& null != request.getParameterValues("row")) {
			tempRow = request.getParameterValues("row");// 得到所选行
		}
		// 没选行
		if (null == tempRow || "".equals(tempRow)) {
			// 跳转到消息页面
			// 保存消息
			request.setAttribute("message", "请选择行数据！");
			request.getRequestDispatcher("message.jsp").forward(request,
					response);
			return;
		}
		// 没选列
		if (null == tempCol || "".equals(tempCol)) {
			// 跳转到消息页面
			// 保存消息
			request.setAttribute("message", "请选择列数据！");
			request.getRequestDispatcher("message.jsp").forward(request,
					response);
			return;
		}

		// 反序列化
		ObjectToObject oto = new StringToObject();// 得到序列化器
		Transfer transfer = null;
		try {
			transfer = (Transfer) oto.transform(tran);// 反序列化得到对象
		} catch (ObjectToObjectException e) {
			// 跳转到消息页面
			// 保存消息
			request.setAttribute("message", "对象反序列化出错！");
			request.getRequestDispatcher("message.jsp").forward(request,
					response);
			return;
		}
		List before = (List) transfer.getValue();// 得到value集合
		title = transfer.getLineTitle();// 得到title
		xScale = transfer.getXscale();// 得到xScale
		/** **得到所选的数据*** */
		int[] col = null;// 所选的列
		int[] row = null;// 所选的行
		// 把字符串型转换为数字
		ParseInteger pi = new ParseInteger();// 得到强转器对象
		col = new int[tempCol.length];// 所选的列
		resultTitle = new String[tempCol.length];// 存放标题
		for (int i = 0; i < tempCol.length; i++) {
			int subChoose = pi.parseInt(tempCol[i]);// 列转换为数字
			col[i] = subChoose;// 列转换为数字
			resultTitle[i] = title[subChoose];
		}

		row = new int[tempRow.length];// 所选的行
		resultXScale = new String[tempRow.length];// 存放X轴结果
		for (int i = 0; i < tempRow.length; i++) {
			int subChoose = pi.parseInt(tempRow[i]);// 行转换为数字
			row[i] = subChoose;
			resultXScale[i] = xScale[subChoose];// 得到对应的选择xScale
		}

		// 执行数据缩减
		ChoosePageData cpd = new ChoosePageData();// 创建一个对象
		value = cpd.beginTran(before, row, col);// 得到选中的数据
		/** **得到所选的数据*** */
		// 数据封装
		Transfer trans = new Transfer();
		trans.setValue(value);
		trans.setlineTitle(resultTitle);
		trans.setXscale(resultXScale);
		trans.setChartTitle(transfer.getChartTitle());

		String objStr = null;// 用于存放对象字符串
		// 执行序列化
		try {
			ObjectToObject otoStr = new ObjectToString();// 得到对象转换器
			objStr = (String) otoStr.transform(trans);
		} catch (ObjectToObjectException e) {
			// 跳转到消息页面
			// 保存消息
			request.setAttribute("message", "对象序列化出错！");
			request.getRequestDispatcher("message.jsp").forward(request,
					response);
			return;
		}
		// 保存数据
		request.setAttribute("tran", objStr);
		// request.setAttribute("title", title);// 用于显示title
		// request.setAttribute("xScale", xScale);// 用于显示xScale
		// 跳转
		request.getRequestDispatcher("choose.jsp").forward(request, response);
	}
}
