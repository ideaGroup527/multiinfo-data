package my_servlet;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.write.DateTime;

import my_class.Parameters;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;

public class Fileupload extends HttpServlet {

	static String url;

	public Fileupload() {
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
		String url = request.getSession().getServletContext().getRealPath("uploaddir");
		File saveDir = new File(url);
		if (!saveDir.isDirectory())
			saveDir.mkdir();
		response.setContentType("text/html; charset=gb2312");
		request.setCharacterEncoding("gb2312");
		String fileName = "";
		String fileType = "";
		try {
			DiskFileItemFactory dff = new DiskFileItemFactory();
			dff.setSizeThreshold(1024000);
			ServletFileUpload sfu = new ServletFileUpload(dff);
			sfu.setFileSizeMax(5000000);
			FileItemIterator fii = sfu.getItemIterator(request);
			while (fii.hasNext()) {
				FileItemStream fis = fii.next();
				if (!fis.isFormField() && fis.getName().length() > 0) {				
					fileType = fis.getName().substring(fis.getName().lastIndexOf("."));
					Date now=new Date();
					now.getTime();
					SimpleDateFormat sdf = new SimpleDateFormat(); 
					sdf.applyPattern("yyyy_MM_dd_HH_mm_ss");
					fileName = sdf.format(now)+fis.getName();
					if (fileType.equals(".xls")) {
						url = url +"/"+ fileName;
						BufferedInputStream in = new BufferedInputStream(fis.openStream());
						BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File(saveDir,fileName)));
						Streams.copy(in, out, true);
						Parameters.url = url;
						response.sendRedirect("../choosesheet.jsp");
					} else {
						response.getWriter().println("  <BODY bgcolor=\"#DDDDDD\">");
						response.getWriter().println("<form method=\"POST\" action=\"../fileupload.jsp\">");
						response.getWriter().println("<p>您选择的文件不是*.xls文件，请点击返回并选择*.xls文件上传");
						response.getWriter().println("<p>error");
						response.getWriter().println("<input type=\"submit\" value=\"返回\">");
						response.getWriter().println("</form>");
						response.getWriter().println("</div>");
						response.getWriter().println("  </BODY>");
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().println("<form method=\"POST\" action=\"../fileupload.jsp\">");
			response.getWriter().println("<p>上传有误，请点击返回重新上传！");
			response.getWriter().println("<input type=\"submit\" value=\"返回\">");
			response.getWriter().println("</form>");
			response.getWriter().println(e);
		}
	}

	public void init() throws ServletException {
		super.init();
	}

}
