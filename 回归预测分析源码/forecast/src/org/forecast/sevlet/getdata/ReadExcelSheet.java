package org.forecast.sevlet.getdata;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.forecast.exception.ObjectToObjectException;
import org.forecast.parse.ObjectToObject;
import org.forecast.parse.ObjectToString;
import org.forecast.parse.ParseDouble;
import org.forecast.parse.ParseInteger;
import org.forecast.util.ListRound;

public class ReadExcelSheet extends HttpServlet {

	public ReadExcelSheet() {
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
