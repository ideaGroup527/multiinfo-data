package com.kxm.servlet.download;

import org.apache.poi.hssf.usermodel.*; 
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelCreate { 

    public ExcelCreate(String excelName,String[][] data) throws Exception{
    	FileOutputStream fos = new FileOutputStream(excelName+".xls"); 
        HSSFWorkbook wb = new HSSFWorkbook(); 
        HSSFSheet s = wb.createSheet(); 
      //  wb.setSheetName(0, excelName,(short)1); 
        wb.setSheetName(0, "sheet",(short)1); 
        HSSFRow row = s.createRow(0); 
        HSSFCell cell = row.createCell((short) 0);
        
        for(short j=0;j<data.length;j++)
        {
        	row = s.createRow(j);
        	for(short i=0;i<data[j].length;i++)
        	{
        	
        		cell = row.createCell((short) i); 
        		cell.setEncoding((short) 1);
        		cell.setCellValue(data[j][i]);          
        	
        	}
        }
        wb.write(fos); 
        fos.close(); 
    }
    
    public static void createExcelFromArray(String excelName,String[][] data) throws Exception{
    	FileOutputStream fos = new FileOutputStream(excelName+".xls"); 
        HSSFWorkbook wb = new HSSFWorkbook(); 
        HSSFSheet s = wb.createSheet(); 
      //  wb.setSheetName(0, excelName,(short)1); 
        wb.setSheetName(0, "sheet",(short)1); 
        HSSFRow row = s.createRow(0); 
        HSSFCell cell = row.createCell((short) 0);
        
        for(short j=0;j<data.length;j++)
        {
        	row = s.createRow(j);
        	for(short i=0;i<data[j].length;i++)
        	{
        	
        		cell = row.createCell((short) i); 
        		cell.setEncoding((short) 1);
        		cell.setCellValue(data[j][i]);          
        	
        	}
        }
        wb.write(fos); 
        fos.close(); 
    }
   
}
