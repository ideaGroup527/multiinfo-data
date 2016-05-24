package com.kxm.util;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class ExcelHandle {
	public static void writeExcel(OutputStream os)
	  {
		/**
		 * 在服务器端 /downloaddir 生成excel文件
		 */

		String filelongName = "";
		String fileShortName = "";
		String fileType = "";
		

		
		WritableWorkbook wwb = null;
		try {
			wwb = Workbook.createWorkbook(os);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		WritableSheet ws = wwb.createSheet("Sheet 1",0); //建表
		
		//**************往工作表中添加数据*****************
		 Label label = new Label(0,0,"this is a label test");
		  try {
			ws.addCell(label);
			 wwb.write();
			 wwb.close();
		} catch (RowsExceededException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	  }
}
