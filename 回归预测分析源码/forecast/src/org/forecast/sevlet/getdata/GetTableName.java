package org.forecast.sevlet.getdata;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.forecast.db.factory.DBFactory;
import org.forecast.exception.ObjectToObjectException;
import org.forecast.parse.ObjectToObject;
import org.forecast.parse.ObjectToString;
import org.forecast.pojo.DataBaseMessage;

/**
 * 得到用户的所有表的名称
 */
public class GetTableName extends HttpServlet {

	public GetTableName() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 得到页面的数据
		String databaseKind = null;
		String ip = null;
		String dataBase = null;
		String userName = null;
		String password = null;
		DataBaseMessage dbm = new DataBaseMessage();
		if (!"".equals(request.getParameter("databaseKind"))
				&& null != request.getParameter("databaseKind")) {
			dbm.setDatabaseKind(request.getParameter("databaseKind"));// 传入的数据库类型不为空则赋值
		} else {
			// 出错。。数据不完整
			// 跳转到消息页面
			// 保存消息
			request.setAttribute("message", "数据库类型不能为空！");
			request.getRequestDispatcher("message.jsp").forward(request,
					response);
			return;
		}
		if (!"".equals(request.getParameter("ip"))
				&& null != request.getParameter("ip")) {
			dbm.setIp(request.getParameter("ip"));// 传入的ip不为空则赋值
		} else {
			// 出错。。数据不完整
			// 跳转到消息页面
			// 保存消息
			request.setAttribute("message", "IP不能为空！");
			request.getRequestDispatcher("message.jsp").forward(request,
					response);
			return;
		}
		if (!"".equals(request.getParameter("dataBase"))
				&& null != request.getParameter("dataBase")) {
			dbm.setDataBase(request.getParameter("dataBase"));// 传入的数据库不为空则赋值
		} else {
			// 出错。。数据不完整
			// 跳转到消息页面
			// 保存消息
			request.setAttribute("message", "数据库不能为空！");
			request.getRequestDispatcher("message.jsp").forward(request,
					response);
			return;
		}
		if (!"".equals(request.getParameter("userName"))
				&& null != request.getParameter("userName")) {
			dbm.setUserName(request.getParameter("userName"));// 传入的用户名不为空则赋值
		} else {
			// 出错。。数据不完整
			// 跳转到消息页面
			// 保存消息
			request.setAttribute("message", "数据库用户名不能为空！");
			request.getRequestDispatcher("message.jsp").forward(request,
					response);
			return;
		}
		if (!"".equals(request.getParameter("password"))
				&& null != request.getParameter("password")) {
			dbm.setPassword(request.getParameter("password"));// 传入的密码不为空则赋值
		} else {
			// 出错。。数据不完整
			// 跳转到消息页面
			// 保存消息
			request.setAttribute("message", "数据库密码不能为空！");
			request.getRequestDispatcher("message.jsp").forward(request,
					response);
			return;
		}

		// 从数据库得到表名
		List allTableName = null;

		DBFactory dbf = new DBFactory(dbm);
		try {
			allTableName = dbf.factory();
		} catch (RuntimeException e1) {
			// 出错。。数据不完整
			// 跳转到消息页面
			// 保存消息
			request.setAttribute("message", "无法连接数据库，确认输入无误，保证数据库服务处于开启状态！");
			request.getRequestDispatcher("message.jsp").forward(request,
					response);
			return;
		}
		// 序列化用户填写的对象
		ObjectToObject oto = new ObjectToString();
		String objStr = null;// 用来存储对象字符串
		try {
			objStr = (String) oto.transform(dbm);
		} catch (ObjectToObjectException e) {
			// 跳转到消息页面
			// 保存消息
			e.printStackTrace();
			request.setAttribute("message", "对象转换错误");
			request.getRequestDispatcher("message.jsp").forward(request,
					response);
			return;
		}
		// 封装表名，填写的对象数据
		request.setAttribute("allTableName", allTableName);
		request.setAttribute("objStr", objStr);
		// 跳转
		request.getRequestDispatcher("allTables.jsp")
				.forward(request, response);
		return;

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
