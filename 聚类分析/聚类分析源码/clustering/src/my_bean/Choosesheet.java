package my_bean;

import jxl.Sheet;
import jxl.Workbook;
import my_class.Get_exceldata;
import my_class.Parameters;

public class Choosesheet {
	private String[] sheetNames;
	private Workbook workBook;
	private int size;

	public Choosesheet() {
		Get_exceldata get = new Get_exceldata(Parameters.url);
		sheetNames = get.sheetNames;
		workBook = get.workBook;
	}

	public String[] getSheetNames() {
		return sheetNames;
	}

	public Sheet getSheet(int a) {
		return workBook.getSheet(a);
	}

	public int getSize() {
		size = workBook.getNumberOfSheets();
		return size;
	}
}
