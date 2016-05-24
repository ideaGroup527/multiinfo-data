package com.kxm.servlet.getdata;

import org.apache.commons.*;
import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import com.kxm.exception.ObjectToObjectException;
import com.kxm.parse.ObjectToObject;
import com.kxm.parse.ObjectToString;
import com.kxm.parse.ParseDouble;
import com.kxm.parse.ParseInteger;
import com.kxm.util.ListRound;
import com.kxm.util.Transfer;

public class ReadExcel extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	static String url;
	
	public ReadExcel() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	  
		
		////////将文件保存到本地
		String base = request.getSession().getServletContext().getRealPath("/");
		url = base + "uploaddir";
		File saveDir = new File(url);
		if (!saveDir.isDirectory())
			saveDir.mkdir();
		response.setContentType("text/html; charset=gb2312");
		request.setCharacterEncoding("gb2312");
		String fileName = "";
		String fileShortName = "";
		String fileType = "";
		//String IP = getIpAddr(request); 获得客户端IP 可记录在文件名中
		
		try {
			DiskFileItemFactory dff = new DiskFileItemFactory();
			dff.setSizeThreshold(1024000);
			ServletFileUpload sfu = new ServletFileUpload(dff);
			sfu.setFileSizeMax(5000000);
			FileItemIterator fii = sfu.getItemIterator(request);
			
			
			while (fii.hasNext()) {
				FileItemStream fis = fii.next();
				if (!fis.isFormField() && fis.getName().length() > 0) {
					
			//	fileName = fis.getName();
			//		fileName = fis.getFieldName();
			////由于ie和火狐的不同，所以要判断一下
					
					DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//获得当前时间的字符串，用作文件名后一部分
					Calendar calendar = Calendar.getInstance();
					String DateString = df.format(calendar.getTime());
					//DateString = DateString.substring(10);
					
					fileName = fis.getName();
					if(fileName.contains("\\")){
						fileShortName = fileName.substring(fileName.lastIndexOf("\\")
								,fileName.lastIndexOf("."));
					}else{
						fileShortName = fileName.substring(0
								,fileName.lastIndexOf("."));
					}
					
					String saveFileName = fileShortName + DateString + ".xls";
					
					int index = fileName.indexOf("\\");   //用index来判断浏览器是否取得路径还是文件名
					
					if(index != -1){
						fileName = fileName.substring(
								fileName.lastIndexOf("\\"));
					}
					
					fileType = fis.getName().substring(
							fis.getName().lastIndexOf("."));
					if (fileType.equals(".xls")) {
						url = url + saveFileName;
						BufferedInputStream bufIn = new BufferedInputStream(
								fis.openStream());
						BufferedOutputStream out = new BufferedOutputStream(
								new FileOutputStream(new File(saveDir
										+ saveFileName)));
						Streams.copy(bufIn, out, true);
						
					}
				}
			}

		} catch (Exception e) {
			
			throw new ServletException(e.getMessage());
		}


		
		
		ParseInteger pi = new ParseInteger();// 得到强转对象
		// 得到需要读取的excel路径
		String excelPath = null;
		
		//if (!"".equals(request.getParameter("excelPath"))
		//		&& null != request.getParameter("excelPath")) {
		//	excelPath = request.getParameter("excelPath");// 得到路径
			
		//}
		excelPath = url;           //获取本地文件路径

		try{
			Workbook book = Workbook.getWorkbook(new File(excelPath));
			int allSheetNO = book.getNumberOfSheets();// 得到工作表的个数
			String[] sheetNames = new String[allSheetNO];
			sheetNames = book.getSheetNames();
			
			

			request.setAttribute("fileShortName", fileShortName);// 用于显示title
			request.setAttribute("sheetNames", sheetNames);// 用于显示title
			request.setAttribute("excelPath", excelPath);//用于保存Excel本地路径 待下一个servlet读取
			request.getRequestDispatcher("ChooseExcelTable.jsp").forward(request,
					response);

		} catch (BiffException e) {
			// 跳转到消息页面
			// 保存消息
			request.setAttribute("message", "错误的文件类型！");
			request.getRequestDispatcher("message.jsp").forward(request,
					response);
			e.printStackTrace();
			return;
		} catch (FileNotFoundException e) {
			// 跳转到消息页面
			// 保存消息
			request.setAttribute("message", "系统找不到指定的路径,请确文件路径中无特殊字符存在！");
			request.getRequestDispatcher("message.jsp").forward(request,
					response);
			e.printStackTrace();
			return;
		} catch (IOException e) {
			// 跳转到消息页面
			// 保存消息
			request.setAttribute("message", "出现读取错误！");
			request.getRequestDispatcher("message.jsp").forward(request,
					response);
			e.printStackTrace();
			return;
		}
	}
	
		public String getIpAddr(HttpServletRequest request) {   
			       String ip = request.getHeader("x-forwarded-for");   
			       if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {   
			           ip = request.getHeader("Proxy-Client-IP");   
			       }   
			       if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {   
			           ip = request.getHeader("WL-Proxy-Client-IP");   
			       }   
			       if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {   
			           ip = request.getRemoteAddr();   
			       }   
			       return ip;   
		}   

}
