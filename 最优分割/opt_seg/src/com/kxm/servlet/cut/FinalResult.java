package com.kxm.servlet.cut;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kxm.exception.ObjectToObjectException;
import com.kxm.parse.ObjectToObject;
import com.kxm.parse.StringToObject;
import com.kxm.servlet.download.ExcelAdd;
import com.kxm.util.Transfer;

public class FinalResult extends HttpServlet {

	/**
	 * 上接finalchoose.jsp 下接finalResult.jsp
	 */
	public FinalResult() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String tran = null;
		String[] cutend = null;
		
		// 得到对象字符串
		if (!"".equals(request.getParameter("tran"))
				&& null != request.getParameter("tran")) {
			tran = request.getParameter("tran");// 得到对象字符串
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
		
		cutend = transfer.getCutend();
		int[] finalcut = new int[cutend.length];
		for(int i = 0;i<cutend.length;i++){
			String temp = null;
			String tempname = String.valueOf(i);
			if (!"".equals(request.getParameter(tempname))
					&& null != request.getParameter(tempname)) {
				temp = request.getParameter(tempname);// 得到对象字符串
			}else{
				// 跳转到消息页面
				// 保存消息
				request.setAttribute("message", "请以正确数字填写！");
				request.getRequestDispatcher("message.jsp").forward(request,
						response);
				return;
			}
			finalcut[i] = (Integer.valueOf(temp)).intValue();
			if(i>=1){//验证是否有逆序现象
				if(finalcut[i]<=finalcut[i-1]){
					request.setAttribute("message", "请以正确顺序填写！");
					request.getRequestDispatcher("message.jsp").forward(request,
							response);
					return;
				}
			}
			if(i==cutend.length-1){//最后一段不可更改
				if(!cutend[i].equals(temp)){
					// 跳转到消息页面
					// 保存消息
					request.setAttribute("message", "最后一段的段末必须为数据最后一行！");
					request.getRequestDispatcher("message.jsp").forward(request,
							response);
					return;
				}
			}
		}
		//创建便于输出的String[][]
		String[] xScale = transfer.getXscale();
		String[] linetitle = transfer.getLineTitle();
		List value = transfer.getValue();
		List acol = (List)value.get(0);
		int linenum = acol.size();
		String[][] showresult = new String[linenum][linetitle.length+2];
		for(int i = 0;i<linenum;i++){
			showresult[i][0] = xScale[i]; //第一列 标识列
		}
		int temp = 1;
		for(int i = 0;i<linenum;i++){
			
			showresult[i][1] = String.valueOf(temp);//第二行 所属类
			
			if(i+1 == finalcut[temp-1]){
				temp++;
			}
		}
		for(int i = 0;i<value.size();i++){
			List col = (List)value.get(i);
			for(int j = 0;j<linenum;j++){
				Double tempd = (Double)col.get(j);
				showresult[j][2+i] =  String.valueOf(tempd);//第三行之后 数据
			}
		}
		//设置待化为Excel表的String[][]
		String[][] excShowResult = new String[showresult.length+1][linetitle.length+2];
		
		excShowResult[0][0] = "所选标识{Identification} ";
		excShowResult[0][1] = "最优分类{group}";
		
		for(int i=0; i<linetitle.length; i++){
			excShowResult[0][i+2] = linetitle[i];
		}
		for(int i=0; i<showresult.length; i++){
			excShowResult[i+1] = showresult[i];
		}
		
		String base = request.getSession().getServletContext().getRealPath("/");
		String url = base + "downloaddir\\";
		

		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//获得当前时间的字符串，用作文件名后一部分
		Calendar calendar = Calendar.getInstance();
		String DateString = df.format(calendar.getTime());
		
		String excFinResTablePath = url+"excFinResTable"+DateString;
		try {
			//新建中间解Excel文件
			new ExcelAdd( excFinResTablePath ,excShowResult); 
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		String title = transfer.getChartTitle();
		
		request.setAttribute("title", title);
		request.setAttribute("linetitle", linetitle);
		request.setAttribute("showresult", showresult);  //分割段段末标号
		request.setAttribute("excFinResTablePath", excFinResTablePath); //Excel表文件地址
		request.getRequestDispatcher("finalResult.jsp").forward(request,
				response);
	}

}
