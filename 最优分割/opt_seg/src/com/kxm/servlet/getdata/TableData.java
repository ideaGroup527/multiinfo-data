package com.kxm.servlet.getdata;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kxm.db.factory.CommonFactory;
import com.kxm.exception.ObjectToObjectException;
import com.kxm.parse.ListToArray;
import com.kxm.parse.ObjectToObject;
import com.kxm.parse.ObjectToString;
import com.kxm.parse.ParseDouble;
import com.kxm.parse.ParseInteger;
import com.kxm.parse.StringToObject;
import com.kxm.pojo.DataBaseMessage;
import com.kxm.util.ListRound;
import com.kxm.util.Transfer;

public class TableData extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public TableData() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String tableName = "";
		String objStr = "";
		String xScaleName = "";
		String[] chooseCol = null;// 每个数据表名称
		DataBaseMessage dbm = new DataBaseMessage();// 连接条件对象
		// 得到所选X轴
		if (null != request.getParameter("xScaleName")
				&& !"".equals(request.getParameter("xScaleName"))) {
			xScaleName = request.getParameter("xScaleName");
		} else {
			// 出错。。数据不完整
			// 跳转到消息页面
			// 保存消息
			request.setAttribute("message", "请选择标识列！");
			request.getRequestDispatcher("message.jsp").forward(request,
					response);
			return;
		}

		// 得到所选X轴
		if (null != request.getParameterValues("chooseCol")
				&& !"".equals(request.getParameterValues("chooseCol"))) {
			chooseCol = request.getParameterValues("chooseCol");
		} else {
			// 出错。。数据不完整
			// 跳转到消息页面
			// 保存消息
			request.setAttribute("message", "请选择数据列！");
			request.getRequestDispatcher("message.jsp").forward(request,
					response);
			return;
		}

		// 得到所选表名
		if (null != request.getParameter("tableName")
				&& !"".equals(request.getParameter("tableName"))) {
			tableName = request.getParameter("tableName");
		} else {
			// 出错。。数据不完整
			// 跳转到消息页面
			// 保存消息
			request.setAttribute("message", "表名不能为空！");
			request.getRequestDispatcher("message.jsp").forward(request,
					response);
			return;
		}

		// 得到对象字符串
		if (null != request.getParameter("objStr")
				&& !"".equals(request.getParameter("objStr"))) {
			objStr = request.getParameter("objStr");
		} else {
			// 出错。。数据不完整
			// 跳转到消息页面
			// 保存消息
			request.setAttribute("message", "数据丢失！");
			request.getRequestDispatcher("message.jsp").forward(request,
					response);
			return;
		}
		// 得到封装好的数据库连接对象
		ObjectToObject oto = new StringToObject();
		try {
			dbm = (DataBaseMessage) oto.transform(objStr);
		} catch (ObjectToObjectException e) {
			// 跳转到消息页面
			// 保存消息
			e.printStackTrace();
			request.setAttribute("message", "对象转换错误");
			request.getRequestDispatcher("message.jsp").forward(request,
					response);
			return;
		}

		CommonFactory cf = new CommonFactory(dbm, tableName, xScaleName);// 表操作对象
		String[] xScale = null;
		List tableCol = null;
		List tempList = cf.factory();
		List tList = new ArrayList();
		for (int i = 0; i < tempList.size(); i++) {
			List t = (List) tempList.get(i);
			tList.add(t.get(0));
		}
		ListToArray lta = new ListToArray();
		xScale = lta.parseArray(tList);// 得到xScale

		CommonFactory cf1 = new CommonFactory(dbm, tableName, chooseCol);// 表操作对象
		List valueFirst = cf1.factory();
		List value = new ArrayList();
		ParseDouble pd = new ParseDouble();
		for (int i = 0; i < valueFirst.size(); i++) {
			List temp = (List) valueFirst.get(i);
			for (int j = 0; j < temp.size(); j++) {
				double a = pd.parseDouble((String)temp.get(j));
				temp.set(j, Double.valueOf(a));
			}
			value.add(temp);
		}

		/***********************************************************************
		 * 把String转换成Double
		 * 
		 **********************************************************************/

		// 创建一个用于在页面显示的List
		List showList = null;
		ListRound lr = new ListRound();// 转换横纵List的类
		try {
			showList = lr.beginTrans(value);// 执行转换
		} catch (IndexOutOfBoundsException e) {
			// 跳转到消息页面
			// 保存消息
			e.printStackTrace();
			request.setAttribute("message", "请确认该表是否有数据！");
			request.getRequestDispatcher("message.jsp").forward(request,
					response);
			return;
		}
		// 获得的数据封装到Transfer类中
		Transfer tran = new Transfer();
		tran.setValue(showList);// value包装
		tran.setXscale(xScale);// xScale包装
		tran.setlineTitle(chooseCol);// title包装
		tran.setChartTitle(tableName);// 包装图片名称

		// 将对象字符串化出来
		ObjectToObject ots = new ObjectToString();
		String newObjStr = null;// 转换后的结果

		try {
			newObjStr = (String) ots.transform(tran);// 得到对象对应的字符串
		} catch (ObjectToObjectException e) {
			e.printStackTrace();
		}

		request.setAttribute("showList", value);// 用于显示List
		request.setAttribute("title", chooseCol);// 用于显示title
		request.setAttribute("picName", tableName);// 用于显示图名称title
		request.setAttribute("xScale", xScale);// 用于显示xScale
		request.setAttribute("tran", newObjStr);// tran包装到request，数据传递
		request.getRequestDispatcher("showData.jsp").forward(request, response);

	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}

}
