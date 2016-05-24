package com.kxm.servlet.getdata;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
import com.kxm.util.ListRound;
import com.kxm.util.MyMath;
import com.kxm.util.Transfer;

public class ChooseExcelTable extends HttpServlet {

	public ChooseExcelTable() {
		super();
	}

	public void destroy() {
		super.destroy(); 
		
	}


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			
	}


	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/html; charset=gb2312");
		request.setCharacterEncoding("gb2312");
		
		byte[] sheetNameTemp;
		String sheetName = "";
		String excelPath = "";
		if (null != request.getParameter("sheetName")
				&& !"".equals(request.getParameter("sheetName"))) {
//			sheetNameTemp = request.getParameter("sheetName").getBytes("UTF-8");
//			sheetName = new String(sheetNameTemp);
			sheetName = request.getParameter("sheetName");
		} else {
			// 出错。。数据不完整
			// 跳转到消息页面
			// 保存消息
			request.setAttribute("message", "请选择Excel表！(Please choose the sheet you need!)");
			request.getRequestDispatcher("message.jsp").forward(request,
					response);
			return;
		}
		
 		if (null != request.getParameter("excelPath")
				&& !"".equals(request.getParameter("excelPath"))) {
			excelPath = request.getParameter("excelPath");
		} else {
			// 出错。。数据不完整
			// 跳转到消息页面
			// 保存消息
			request.setAttribute("message", "路径保存出错!请重试(The path lost.Please try again.)");
			request.getRequestDispatcher("message.jsp").forward(request,
					response);
			return;
		}
		try {
			// 获得一个工作簿对象
			Workbook book = Workbook.getWorkbook(new File(excelPath));

			// 拿到一个工作表对象
			int sheetNum = Integer.parseInt(sheetName);
			Sheet sheet = book.getSheet(sheetNum);
			int col = sheet.getColumns();// 列的个数
			if(col == 0){
				// 出错。。数据不完整
				// 跳转到消息页面
				// 保存消息
				request.setAttribute("message", "选择的表无内容(Excel table is empty)");
				request.getRequestDispatcher("message.jsp").forward(request,
						response);
				return;
			}
			String picName = sheet.getName() ;//得到工作表的名称
			// 1. 获得第一列数据，X轴的数据
			Cell[] xCell = sheet.getColumn(0);
			if (xCell.length < 2) {// 行数少于二，不符合规范
				// 跳转到消息页面
				// 保存消息
				request.setAttribute("message", "该表数据量不足，行数少于2！");
				request.getRequestDispatcher("message.jsp").forward(request,
						response);
				return;
			}
			// 从第二个开始是对应的X轴刻度
			String[] xScale = new String[xCell.length - 1];// 分配空间
			for (int i = 0; i < xCell.length - 1; i++) {// 第一个不是需要的数据
				try {
					String tempStr = xCell[i + 1].getContents();// 获得内容
					if (!"".equals(tempStr) && null != tempStr) {
						xScale[i] = xCell[i + 1].getContents();// 获得内容
					} else {
						xScale[i] = "--";// 为空
					}
				} catch (Exception e) {
					xScale[i] = "--";// 出现错误用-代替
				}
			}

			// 2.获取每组数据的名称
			Cell[] titleCell = sheet.getRow(0);
			if (titleCell.length < 2) {// 列数少于二，不符合规范
				// 跳转到消息页面
				// 保存消息0
				request.setAttribute("message", "该表数据量不足，列数少于2！");
				request.getRequestDispatcher("message.jsp").forward(request,
						response);
				return;
			}
			// 从第二个开始是对应的数据
			String[] title = new String[titleCell.length - 1];// 分配空间
			for (int i = 0; i < titleCell.length - 1; i++) {// 第一个不是需要的数据
				try {
					String tempStr = titleCell[i + 1].getContents();
					if (!"".equals(tempStr) && null != tempStr) {
						title[i] = titleCell[i + 1].getContents();// 获得内容
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
			List EmptyCols = new ArrayList();
			for (int i = 1; i < col; i++) {// 第一个数据是X轴名称，不需要
				Cell[] cell = sheet.getColumn(i);// 取得一列的数据
				List valueInner = new ArrayList();// 内层集合，用于包装一组的数据
				int row = cell.length;// 该列的数据个数
				boolean isEmptyCol = true; //判断该列是否全为0.0 如果是 该列无效
				for (int j = 1; j <= xScale.length; j++) {// 第一个数据是名称，不需要
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
					if(messageDouble!= 0.0){
						isEmptyCol = false;
					}
					valueInner.add(Double.valueOf(messageDouble));// 对每一组数据进行数据封装
				}
				if(isEmptyCol == true){
					EmptyCols.add(i); //把改行加入空行列表 在页面上空行是不能选择的
				}
				value.add(valueInner);// 将一组封装好的数据放入外层集合
			}
			
			// 创建一个用于在页面显示的List
			List showList = null;
			ListRound lr = new ListRound();// 转换横纵List的类
			showList = lr.beginTrans(value);// 执行转换

			// 获得的数据封装到Transfer类中
			Transfer tran = new Transfer();
			tran.setValue(value);// value包装
			tran.setXscale(xScale);// xScale包装
			tran.setlineTitle(title);// title包装
			tran.setChartTitle(picName);// 包装图片名称

			// 将对象字符串化出来
			
			ObjectToObject oto = new ObjectToString();
			String objStr = null;// 转换后的结果
			try {
				objStr = (String) oto.transform(tran);// 得到对象对应的字符串
			} catch (ObjectToObjectException e) {
				e.printStackTrace();
			}
			request.setAttribute("EmptyCols", EmptyCols);
			request.setAttribute("showList", showList);// 用于显示List
			request.setAttribute("title", title);// 用于显示title
			request.setAttribute("picName", picName);// 用于显示图名称title
			request.setAttribute("xScale", xScale);// 用于显示xScale
			request.setAttribute("tran", objStr);// tran包装到request，数据传递
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
