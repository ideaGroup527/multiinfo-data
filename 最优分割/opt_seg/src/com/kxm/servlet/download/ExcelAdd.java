package com.kxm.servlet.download;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;

import java.io.File;

import java.io.FileInputStream;

import java.io.FileNotFoundException;

import java.io.IOException;

import java.text.DecimalFormat;

import java.text.SimpleDateFormat;

import java.util.ArrayList;

import java.util.Arrays;

import java.util.Date;

import java.util.List;

import javax.swing.JOptionPane;

 

import org.apache.poi.hssf.usermodel.HSSFCell;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;

import org.apache.poi.hssf.usermodel.HSSFRow;

import org.apache.poi.hssf.usermodel.HSSFSheet;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import org.apache.poi.poifs.filesystem.POIFSFileSystem;

public class ExcelAdd {

	/**
	 * @param args
	 * @throws Exception 
	 */
	
		public ExcelAdd(String excelName,String[][] data) throws Exception{
//			excelName = ".\\downloaddir\\" + excelName; 
			try{
				this.add(excelName, data);
			}catch (Exception ex)
			{
				ExcelCreate excel = new ExcelCreate(excelName,data);
			}
		}
		public void add(String excelName,String[][] data) throws Exception{	
             //建立输出流
			
			BufferedInputStream in = 
					new BufferedInputStream(new FileInputStream(excelName+".xls"));    
		    POIFSFileSystem fs = new POIFSFileSystem(in);
		    
		    HSSFWorkbook wb = new HSSFWorkbook(fs);

		    HSSFCell cell = null;
		    HSSFRow  row = null;
		    
		    HSSFSheet st = wb.getSheetAt(0);
		    int rowIndex = st.getLastRowNum()+1;
		 //   row= st.createRow(rowIndex);
		    int lastRow = rowIndex + data.length;
		    for(int j=0;rowIndex < lastRow;rowIndex++,j++)
		    {
		    		row = st.createRow(rowIndex);
		    		int columnIndex = data[j].length;
		    		for(short i=0;i < columnIndex;i++){
		    	
		    	
		    			cell = row.createCell(i);
		    			cell.setEncoding((short) 1);
		    			cell.setCellValue(data[j][i]);
		    		}
		    }
		    FileOutputStream fos = new FileOutputStream(excelName + ".xls"); 
		    wb.write(fos); 
	        fos.close();
		}
}
