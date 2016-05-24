package my_servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Cell;
import jxl.Sheet;
import my_bean.Choosesheet;
import my_class.Parameters;

public class Get_sheet extends HttpServlet {

	public Get_sheet() {
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
		Choosesheet choosesheet = new Choosesheet();
		Parameters.sheet = choosesheet.getSheet(Integer.parseInt(request
				.getParameter("sheetN")));
		Sheet sheet = Parameters.sheet;
		String[][] x = new String[sheet.getRows()][sheet.getColumns()];
		Cell cell = null;
		for (int i = 0; i < sheet.getRows(); i++) {
			for (int j = 0; j < sheet.getColumns(); j++) {
				cell = sheet.getCell(j, i);
				x[i][j] = cell.getContents().toString().trim();
				if (x[i][j] == null)
					x[i][j] = "";
				if (i > 0 && j > 0) {
					if (x[i][j].trim().equals("")) {
						x[i][j] = "0";
					}
				} else {
					if (x[i][j].trim().equals("")) {
						x[i][j] = "";
					}
				}
			}
		}
		Parameters.x = x;
		response.sendRedirect("../chooseRowColumn.jsp");
	}

}
