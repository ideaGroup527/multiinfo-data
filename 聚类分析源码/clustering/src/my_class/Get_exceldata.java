package my_class;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class Get_exceldata {
	public String filePath;
	public Sheet sheet;
	public Workbook workBook;
	public String[] sheetNames;

	public Get_exceldata(String filePath) {
		this.filePath = filePath;
		InputStream fs = null;
		workBook = null;
		try {
			fs = new FileInputStream(filePath);
			workBook = Workbook.getWorkbook(fs);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (BiffException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		sheetNames = workBook.getSheetNames();
	}
}