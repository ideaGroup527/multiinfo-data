package my_servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import my_class.Parameters;

public class Get_data extends HttpServlet {

	public Get_data() {
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
		String[] r = request.getParameterValues("row");
		String[] c = request.getParameterValues("col");
		try {
			double[][] x1 = new double[r.length + 1][c.length + 1];
			String[][] x = new String[r.length + 1][c.length + 1];
			for (int i = 0; i < x.length; i++) {
				for (int j = 0; j < x[0].length; j++) {
					x1[i][j] = 0;
					x[i][j] = "";
				}
			}
			int[] row = new int[r.length + 1];
			int[] col = new int[c.length + 1];
			for (int i = 0; i < row.length; i++) {
				if (i == 0)
					row[i] = 0;
				else
					row[i] = (int) Integer.valueOf(r[i - 1]);
			}
			for (int j = 0; j < col.length; j++) {
				if (j == 0)
					col[j] = 0;
				else
					col[j] = (int) Integer.valueOf(c[j - 1]);
			}
			for (int i = 0, a = 0; i < Parameters.x.length && a < row.length; i++) {
				if (i == row[a]) {
					for (int j = 0, b = 0; j < Parameters.x[0].length
							&& b < col.length; j++) {
						if (j == col[b]) {
							x[a][b] = Parameters.x[i][j];
							b++;
						}
					}
					a++;
				}
			}
			for (int i = 0; i < x.length; i++) {
				for (int j = 0; j < x[0].length; j++) {
					if (i > 0 && j > 0) {
						if (x[i][j] == null || x[i][j].trim().equals("")) {
							x[i][j] = "0";
						} else {
							x1[i][j] = Double.valueOf(x[i][j].toString());
						}
					} else {
						if (x[i][j].trim().equals("")) {
							x[i][j] = "";
						}
					}
				}
			}
			Parameters.x_new = x;
			Parameters.x1 = x1;
			response.sendRedirect("../showdata.jsp");
		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().println("对不起,出现错误!");
			response.getWriter().println(e);
		}
	}

	public void init() throws ServletException {
		// Put your code here
	}

}
