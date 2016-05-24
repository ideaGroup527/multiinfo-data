package com.kxm.servlet.download;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

public class DownloadMidResTable extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public DownloadMidResTable() {
		super();
	}
	
	public void destroy() {
		super.destroy(); 
		
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String path = new String(request.getParameter("downloadPath").getBytes(
				"iso-8859-1"));
		path = path +".xls";
		SmartUpload su = new SmartUpload();

		su.initialize(getServletConfig(), request, response);// 初始化
		try {
			su.setContentDisposition(null);// 出现下载提示
			su.downloadFile(path);
		} catch (SmartUploadException e) {
			e.printStackTrace();
		}
	}

	public void init() throws ServletException {
	}

}
