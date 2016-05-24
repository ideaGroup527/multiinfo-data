package com.kxm.servlet.getdata;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.read.biff.BiffException;

public class GetExcelWorkBook extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public GetExcelWorkBook() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		response.setCharacterEncoding("gb2312");
		request.setCharacterEncoding("gb2312");
		
		// 得到需要读取的excel路径
		String excelPath = null;
		if (!"".equals(request.getParameter("excelPath"))
				&& null != request.getParameter("excelPath")) {
			excelPath = request.getParameter("excelPath");// 得到路径
			excelPath = java.net.URLDecoder.decode(excelPath, "utf-8");
		}
		
		try {
			// 获得一个工作簿对象
			Workbook book = Workbook.getWorkbook(new File(excelPath));
			int sheetNO = book.getNumberOfSheets();// 得到工作表的个数
			StringBuffer sb = new StringBuffer();
			sb.append("<br>选择Excel中的工作表：");
			sb.append("<select name=\"sheetNO\">");
			for (int i = 0; i < sheetNO; i++) {
				sb.append("<option value=\"" + (i + 1) + "\">"
						+ book.getSheetNames()[i] + "</option>");
			}
			sb.append("</select>");

			sb.append("<br><br><center><input type=\"submit\" value=\"提交\" name=\"B1\"></center>");
			response.getWriter().write(sb.toString());
			return ;
		} catch (BiffException e) {
			response.getWriter().write("<font color=\"#FF0000\">所选文件格式错误！</font>");
			return ;
		}
	}

}

