package org.forecast.sevlet.getdata;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.forecast.exception.ObjectToObjectException;
import org.forecast.parse.ObjectToObject;
import org.forecast.parse.ObjectToString;
import org.forecast.parse.ParseDouble;
import org.forecast.parse.ParseInteger;
import org.forecast.util.ListRound;

public class ReadExcel extends HttpServlet {

	public ReadExcel() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ParseInteger pi = new ParseInteger();// 得到强转对象
		// 得到需要读取的excel路径
		String excelPath = request.getParameter("excelPath");
		int sheetNO = Integer.parseInt(request.getParameter("sheetNO"))-1;// 所选工作表


		try {
			// 获得一个工作簿对象
			Workbook book = Workbook.getWorkbook(new File(excelPath));
			int allSheetNO = book.getNumberOfSheets();// 得到工作表的个数
			if (sheetNO >allSheetNO || sheetNO < 0) {
				// 跳转到消息页面
				// 保存消息
				request.setAttribute("message", "所选的工作表不存在！");
				request.getRequestDispatcher("message.jsp").forward(request,
						response);
				return;
			}else{
				// 不做任何动作
			}

			// 拿到一个工作表对象
			Sheet sheet = book.getSheet(sheetNO);
			int col = sheet.getColumns();// 列的个数
			String picName = sheet.getName() ;// 得到工作表的名称
			// 1. 获得第一列数据
			Cell[] xCell = sheet.getColumn(0);
			if (xCell.length < 2) {// 行数少于二，不符合规范
				// 跳转到消息页面
				// 保存消息
				request.setAttribute("message", "该表数据量不足，行数少于2！");
				request.getRequestDispatcher("message.jsp").forward(request,
						response);
				return;
			}
			
//			String[] xScale = new String[xCell.length - 1];// 分配空间
//			for (int i = 0; i < xCell.length - 1; i++) {// 第一个不是需要的数据
//				try {
//					String tempStr = xCell[i + 1].getContents();// 获得内容
//					if (!"".equals(tempStr) && null != tempStr) {
//						xScale[i] = xCell[i + 1].getContents();// 获得内容
//					} else {
//						xScale[i] = "--";// 为空
//					}
//				} catch (Exception e) {
//					xScale[i] = "--";// 出现错误用-代替
//				}
//			}

			// 2.获取每组数据的名称
			Cell[] titleCell = sheet.getRow(0);
			if (titleCell.length < 2) {// 列数少于二，不符合规范
				// 跳转到消息页面
				// 保存消息
				request.setAttribute("message", "该表数据量不足，列数少于2！");
				request.getRequestDispatcher("message.jsp").forward(request,
						response);
				return;
			}
			// 从第二个开始是对应的数据
			String[] title = new String[titleCell.length ];// 分配空间
			
			for (int i = 0; i < titleCell.length; i++) {// 第一个不是需要的数据
				try {
					String tempStr = titleCell[i ].getContents();
					if (!"".equals(tempStr) && null != tempStr) {
						title[i] = titleCell[i].getContents();// 获得内容
					
					} else {
						title[i] = "--";// 为空
					}
				} catch (Exception e) {
					title[i] = "--";// 出现错误用-代替
				}
			}

			// 3.获取每组的数据
			ParseDouble pd = new ParseDouble();// 强转
			List value = new ArrayList();// 外层集合，用于存放包装好的每一组数据
			for (int i = 0; i < col; i++) {// 第一个数据是X轴名称，不需要
				Cell[] cell = sheet.getColumn(i);// 取得一列的数据
				List valueInner = new ArrayList();// 内层集合，用于包装一组的数据
				int row = cell.length;// 该列的数据个数
				for (int j = 1; j < xCell.length; j++) {// 第一个数据是名称，不需要
					String message = null;// 出现错误用-代替
					try {
						String tempStr = cell[j].getContents();
						if (!"".equals(tempStr) && null != tempStr) {
							message = cell[j].getContents();// 获得String的数据
						} else {
							message = "0.0";// 为空则给默认值
						}
					} catch (Exception e) {
						message = "0.0";// 出错则给默认值
					}
					double messageDouble = pd.parseDouble(message);
					valueInner.add(messageDouble);// 对每一组数据进行数据封装
				}
				value.add(valueInner);// 将一组封装好的数据放入外层集合
			}

			// 创建一个用于在页面显示的List
			List showList = null;
			ListRound lr = new ListRound();// 转换横纵List的类
			showList = lr.beginTrans(value);// 执行转换
			
			ObjectToObject oto = new ObjectToString();
			String objStr = null;// 转换后的结果
			
			try {
				objStr = (String) oto.transform(showList);// 得到对象对应的字符串
			} catch (ObjectToObjectException e) {
				e.printStackTrace();
			}
			
			response.setContentType("text/html "); 
			response.setCharacterEncoding( "gb2312" );
			request.setAttribute("showList", showList);// 用于显示List
			request.setAttribute("objStr",objStr );
			request.setAttribute("title", title);// 用于显示title
			
			request.setAttribute("picName", picName);// 用于显示图名称title
			request.getRequestDispatcher("showData.jsp").forward(request,
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
}
