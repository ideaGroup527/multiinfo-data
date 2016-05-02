package my_servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import my_class.Calculate;
import my_class.Do_data;
import my_class.Parameters;

public class Clustering extends HttpServlet {

	public Clustering() {
		super();
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html; charset=gb2312");
		request.setCharacterEncoding("gb2312");
		int key = Integer.valueOf(request.getParameter("key1"))
				+ Integer.valueOf(request.getParameter("key2"));
		int type = Integer.valueOf(request.getParameter("type"));
		PrintWriter out = response.getWriter();
		try {
			out.println("<HTML>");
			out.println("  <HEAD><TITLE>计算数据</TITLE></HEAD>");
			out.println("  <BODY bgcolor=\"#DDDDDD\">");
			out.println("<div align=\"center\">这是分步聚类计算结果:<br>");
			out.println("These are the calculated results of clustering step by step:");
			out.println("  <hr>");
			double x[][] = Parameters.x1;
			Do_data do_data = new Do_data(x, type);
			do_data.do_data();
			Calculate calculate = new Calculate(do_data, key);
			calculate.calulate_one();
			calculate.calulate_two(response);
			out.println("<form method=\"GET\" action=\"../draw.jsp\">");
			out.println("<p><input type=\"submit\" value=\"下一步(Next)\"></p>");
			out.println("</form>");
			out.println("<br>");
			out.println("<input type=\"button\" value=\"后退(Back)\" onClick=\"javascript:history.back(-1);\">");
			out.println("</div>");
			out.println("  </BODY>");
			out.println("</HTML>");
			Parameters.calculate = calculate;
			Parameters.do_data = do_data;
		} catch (Exception e) {
			e.printStackTrace();
			out.println("error！对不起，您上传的数据有误，读取数据出错！！！" + e + "<br>");
			response.getWriter().println(
					"<form method=\"POST\" action=\"../fileupload.jsp\">");
			response.getWriter()
					.println(
							"<p>请点击返回并重新选择*.xls文件上传(Please click to back and chose *. xls file to upload)<br>");
			response.getWriter().println(
					"<input type=\"submit\" value=\"返回(back)\">");
			response.getWriter().println("</form>");
		}
	}

	public void init() throws ServletException {
		// Put your code here
	}

}
