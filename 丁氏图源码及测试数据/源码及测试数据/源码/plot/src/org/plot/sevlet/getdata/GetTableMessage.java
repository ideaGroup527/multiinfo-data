package org.plot.sevlet.getdata;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.plot.db.factory.DBFactory;
import org.plot.exception.ObjectToObjectException;
import org.plot.parse.ObjectToObject;
import org.plot.parse.ParseDouble;
import org.plot.parse.StringToObject;
import org.plot.pojo.DataBaseMessage;

public class GetTableMessage extends HttpServlet {

	public GetTableMessage() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String tableName = "";
		String objStr = "";
		DataBaseMessage dbm = new DataBaseMessage();// 连接条件对象
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
			request.setAttribute("message", "数据丢失不能为空！");
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

		DBFactory dbf = new DBFactory(dbm,tableName);// 表操作对象
		List tableCol = null;
		tableCol = dbf.factory();//得到结果

		// 封装表名，填写的对象数据
		request.setAttribute("tableCol", tableCol);
		request.setAttribute("tableName", tableName);
		request.setAttribute("objStr", objStr);
		// 跳转
		request.getRequestDispatcher("tableCol.jsp").forward(request,
				response);
		return;
		
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
