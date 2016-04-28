package org.forecast.sevlet.getdata;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.forecast.parse.ParseInteger;

public class UploadServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public UploadServlet() {
		super();
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response) ;
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ParseInteger pi = new ParseInteger();// 得到强转对象
		// 得到需要读取的excel路径
		String excelPath = null;
		int sheetNO = 0;// 所选工作表
		String newFileName = new Long(System.currentTimeMillis()).toString() ;

		// 解析 request，判断是否有上传文件
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if (isMultipart) {
			// 创建磁盘工厂，利用构造器实现内存数据储存量和临时储存路径
			DiskFileItemFactory factory = new DiskFileItemFactory(1024 * 4,
					new File(this.getServletContext().getRealPath("/temp")));
			// 设置最多只允许在内存中存储的数据,单位:字节
			// factory.setSizeThreshold(4096);
			// 设置文件临时存储路径
			// factory.setRepository(new File("D:\\Temp"));
			// 产生一新的文件上传处理程式
			ServletFileUpload upload = new ServletFileUpload(factory);
			// 设置路径、文件名的字符集
			upload.setHeaderEncoding("UTF-8");
			// 设置允许用户上传文件大小,单位:字节
			upload.setSizeMax(1024 * 1024 * 100);
			// 得到所有的表单域，它们目前都被当作FileItem
			List fileItems = null;
			try {
				fileItems = upload.parseRequest(request);
			} catch (FileUploadException e) {
				e.printStackTrace();
			}
			// 依次处理请求
			Iterator iter = fileItems.iterator();
			while (iter.hasNext()) {
				FileItem item = (FileItem) iter.next();
				if (item.isFormField()) {
					// 如果item是正常的表单域
					String name = item.getFieldName();
					if("sheetNO".equals(name)){
						String tempNO = item.getString("UTF-8");// 得到字符串
						sheetNO = pi.parseInt(tempNO) - 1;// 执行强转
					}
//					String value = item.getString("UTF-8");
				} else {
					// 如果item是文件上传表单域
					// 获得文件名及路径
					String fileName = item.getName();
					if (fileName != null) {
						// 如果文件存在则上传
						File fullFile = new File(item.getName());
//						if (fullFile.exists()) {
							// 存储路径
							String path = this.getServletContext().getRealPath(
									"/")
									+ "upload/" + newFileName+fullFile.getName();
							excelPath = path ;//当前文件存储路径
							// 文件流
							File fileOnServer = new File(path);
							try {
								// 写操作
								item.write(fileOnServer);
							} catch (Exception e) {
								e.printStackTrace();
								System.out.println("文件"
										+ fileOnServer.getName()
										+ "上传失败！！！！！！！");
								return;
							}
							System.out.println("文件" + fileOnServer.getName()
									+ "上传成功");
//						}
					}
				}
			}
		}
		
		
		
		try {
			// 获得一个工作簿对象
			Workbook book = Workbook.getWorkbook(new File(excelPath));
			int sheetNO_ = book.getNumberOfSheets();// 得到工作表的个数
			StringBuffer sb = new StringBuffer();
			sb.append("<br>选择Excel中的工作表(Select an excel work sheet): ");
			sb.append("<select name=\"sheetNO\">");
			for (int i = 0; i < sheetNO_; i++) {
				sb.append("<option value=\"" + (i + 1) + "\">"
						+ book.getSheetNames()[i] + "</option>");
			}
			sb.append("</select>");

			sb.append("<br><br><center><input type=\"submit\" value=\"提交(Submit)\" name=\"B1\"></center>");
			request.setAttribute("sheetSelect", sb.toString()) ;
			request.setAttribute("excelPath", excelPath);
//			response.getWriter().write(sb.toString());
			request.getRequestDispatcher("sheet.jsp").forward(request,
					response);
			return ;
		} catch (BiffException e) {
			response.getWriter().write("<font color=\"#FF0000\">所选文件格式错误！</font>");
			return ;
		}
		
	}

	public void init() throws ServletException {
		// Put your code here
	}

}
