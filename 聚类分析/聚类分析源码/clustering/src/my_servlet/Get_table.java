package my_servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import my_class.Parameters;

public class Get_table extends HttpServlet {

	public Get_table() {
		super();
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int i = (int) Integer.valueOf(request.getParameter("table_list"));
		int j = 0;
		String[][] x;
		String x1;
		String tableName = Parameters.tableNames[i];
		response.setContentType("text/html");
		response.setCharacterEncoding("gb2312");
		PrintWriter out = response.getWriter();
		Connection conn = Parameters.conn;
		Statement stmt = null;
		try {
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = stmt.executeQuery("select * from " + tableName);
			ResultSetMetaData rsmd = rs.getMetaData();
			i = 0;
			j = rsmd.getColumnCount();
			rs.last();
			x = new String[rs.getRow() + 1][j];
			rs.beforeFirst();
			i = 0;
			for (int n = 0; n < j; n++) {
				x[i][n] = rsmd.getColumnName(n + 1);
			}
			i++;
			while (rs.next()) {
				for (int n = 0; n < j; n++) {
					try {
						x1 = rs.getString(n + 1);
					} catch (Exception e) {
						x1 = null;
					}
					if (x1 == null)
						x1 = "";
					if (n == 0) {
						x[i][n] = x1;
					} else {
						if (x1.trim().equals(""))
							x[i][n] = "0";
						else
							x[i][n] = x1;
					}
				}
				i++;
			}
			Parameters.x = x;
			response.sendRedirect("../chooseRowColumn.jsp");
		} catch (Exception e) {
			e.printStackTrace();
			out.println("获取数据出现错误");
		}
		out.flush();
		out.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the POST method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	public void init() throws ServletException {
		// Put your code here
	}

}
