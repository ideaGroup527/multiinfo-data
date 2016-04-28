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

		ParseInteger pi = new ParseInteger();// �õ�ǿת����
		// �õ���Ҫ��ȡ��excel·��
		String excelPath = null;
		int sheetNO = 0;// ��ѡ������
		String newFileName = new Long(System.currentTimeMillis()).toString() ;

		// ���� request���ж��Ƿ����ϴ��ļ�
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if (isMultipart) {
			// �������̹��������ù�����ʵ���ڴ����ݴ���������ʱ����·��
			DiskFileItemFactory factory = new DiskFileItemFactory(1024 * 4,
					new File(this.getServletContext().getRealPath("/temp")));
			// �������ֻ�������ڴ��д洢������,��λ:�ֽ�
			// factory.setSizeThreshold(4096);
			// �����ļ���ʱ�洢·��
			// factory.setRepository(new File("D:\\Temp"));
			// ����һ�µ��ļ��ϴ������ʽ
			ServletFileUpload upload = new ServletFileUpload(factory);
			// ����·�����ļ������ַ���
			upload.setHeaderEncoding("UTF-8");
			// ���������û��ϴ��ļ���С,��λ:�ֽ�
			upload.setSizeMax(1024 * 1024 * 100);
			// �õ����еı�������Ŀǰ��������FileItem
			List fileItems = null;
			try {
				fileItems = upload.parseRequest(request);
			} catch (FileUploadException e) {
				e.printStackTrace();
			}
			// ���δ�������
			Iterator iter = fileItems.iterator();
			while (iter.hasNext()) {
				FileItem item = (FileItem) iter.next();
				if (item.isFormField()) {
					// ���item�������ı���
					String name = item.getFieldName();
					if("sheetNO".equals(name)){
						String tempNO = item.getString("UTF-8");// �õ��ַ���
						sheetNO = pi.parseInt(tempNO) - 1;// ִ��ǿת
					}
//					String value = item.getString("UTF-8");
				} else {
					// ���item���ļ��ϴ�����
					// ����ļ�����·��
					String fileName = item.getName();
					if (fileName != null) {
						// ����ļ��������ϴ�
						File fullFile = new File(item.getName());
//						if (fullFile.exists()) {
							// �洢·��
							String path = this.getServletContext().getRealPath(
									"/")
									+ "upload/" + newFileName+fullFile.getName();
							excelPath = path ;//��ǰ�ļ��洢·��
							// �ļ���
							File fileOnServer = new File(path);
							try {
								// д����
								item.write(fileOnServer);
							} catch (Exception e) {
								e.printStackTrace();
								System.out.println("�ļ�"
										+ fileOnServer.getName()
										+ "�ϴ�ʧ�ܣ�������������");
								return;
							}
							System.out.println("�ļ�" + fileOnServer.getName()
									+ "�ϴ��ɹ�");
//						}
					}
				}
			}
		}
		
		
		
		try {
			// ���һ������������
			Workbook book = Workbook.getWorkbook(new File(excelPath));
			int sheetNO_ = book.getNumberOfSheets();// �õ�������ĸ���
			StringBuffer sb = new StringBuffer();
			sb.append("<br>ѡ��Excel�еĹ�����(Select an excel work sheet): ");
			sb.append("<select name=\"sheetNO\">");
			for (int i = 0; i < sheetNO_; i++) {
				sb.append("<option value=\"" + (i + 1) + "\">"
						+ book.getSheetNames()[i] + "</option>");
			}
			sb.append("</select>");

			sb.append("<br><br><center><input type=\"submit\" value=\"�ύ(Submit)\" name=\"B1\"></center>");
			request.setAttribute("sheetSelect", sb.toString()) ;
			request.setAttribute("excelPath", excelPath);
//			response.getWriter().write(sb.toString());
			request.getRequestDispatcher("sheet.jsp").forward(request,
					response);
			return ;
		} catch (BiffException e) {
			response.getWriter().write("<font color=\"#FF0000\">��ѡ�ļ���ʽ����</font>");
			return ;
		}
		
	}

	public void init() throws ServletException {
		// Put your code here
	}

}
