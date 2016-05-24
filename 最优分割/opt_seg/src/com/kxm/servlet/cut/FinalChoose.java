package com.kxm.servlet.cut;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kxm.exception.ObjectToObjectException;
import com.kxm.parse.ObjectToObject;
import com.kxm.parse.ObjectToString;
import com.kxm.parse.StringToObject;
import com.kxm.pojo.CutParam;
import com.kxm.util.Transfer;

public class FinalChoose extends HttpServlet {

	/**
	 * 上接minResult 下接finalChoose
	 */
	public FinalChoose() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String tran = null;       // 原始数据表格对象字符串
		String tmpCutnum = null;    // 暂存参数Mycutnum
		int cutnum = 0;             // 整型cutnum
		
		String title = null;      //表名
		String[] xScale = null;     //标志列
	
		// 得到对象字符串
		if (!"".equals(request.getParameter("tran"))
				&& null != request.getParameter("tran")) {
			tran = request.getParameter("tran");// 得到对象字符串
		}
		
		if (!"".equals(request.getParameter("MyCutnum"))
				&& null != request.getParameter("MyCutnum")) {
			tmpCutnum = request.getParameter("MyCutnum");// 得到参数段数
			cutnum = (Integer.valueOf(tmpCutnum)).intValue(); //转换成整型
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
		title = transfer.getChartTitle();
		String[][][] result = transfer.getResult();
		String[][] finalresult = new String[cutnum][4];
		for(int i = 0;i<cutnum;i++){
			for(int j = 0;j<4;j++){
				finalresult[i][j] = result[cutnum-2][i][j]; //取最终选择的分段结果集
			}
		}
		String[] cutend = new String[finalresult.length];//保存每段段末标号
		for(int i = 0;i<finalresult.length;i++){
			cutend[i] = finalresult[i][2];
		}
		transfer.setCutend(cutend);
		
		String objStr = null;// 用于存放对象字符串
		// 执行序列化
		try {
			ObjectToObject otoStr = new ObjectToString();// 得到对象转换器
			objStr = (String) otoStr.transform(transfer);
		} catch (ObjectToObjectException e) {
			// 跳转到消息页面
			// 保存消息
			request.setAttribute("message", "对象序列化出错！");
			request.getRequestDispatcher("message.jsp").forward(request,
					response);
			return;
		}
		
		request.setAttribute("tran", objStr);  //数据集
		request.setAttribute("title", title);  //表名
		request.setAttribute("cutend", cutend);  //分割段段末标号
		request.getRequestDispatcher("finalChoose.jsp").forward(request,
				response);
	}

}
