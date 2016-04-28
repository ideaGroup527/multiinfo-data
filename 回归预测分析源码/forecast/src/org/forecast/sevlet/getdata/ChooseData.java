package org.forecast.sevlet.getdata;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.forecast.calculate.DataDemo;
import org.forecast.exception.ObjectToObjectException;
import org.forecast.parse.ListDemo;
import org.forecast.parse.ObjectToObject;
import org.forecast.parse.ObjectToString;
import org.forecast.parse.ParseInteger;
import org.forecast.parse.StringToObject;
import org.forecast.util.ChoosePageData;

public class ChooseData extends HttpServlet {

	public ChooseData() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int choose = 0;// 所选择的操作
		boolean isCol = false;// 是否列全选
		boolean isRow = false;// 是否行全选
		boolean isAll = false;// 是否行列全选

		List value = null;// 选择后的结果集合
		String[] tempCol = null;// 临时存放列
		String[] tempRow = null;// 临时存放行
		String list = null;// 对象字符串
		String  titleStr=null;

		String changeresult = null;//存放因变量
		request.setCharacterEncoding("gb2312");
		// 得到对象字符串
		if (!"".equals(request.getParameter("changeresult"))
				&& null != request.getParameter("changeresult")) {
			changeresult = request.getParameter("changeresult");//得到因变量	
			System.out.println("因变量为第"+changeresult);
		}
		if (!"".equals(request.getParameter("objStr"))
				&& null != request.getParameter("objStr")) {
			list = request.getParameter("objStr");// 得到所有数据的对象字符串
		}

		if (!"".equals(request.getParameterValues("changereason"))
				&& null != request.getParameterValues("changereason")) {
			tempCol = request.getParameterValues("changereason");// 得到所选列
		}
		if (!"".equals(request.getParameterValues("row"))
				&& null != request.getParameterValues("row")) {
			tempRow = request.getParameterValues("row");// 得到所选行
		}
		
		
		// 反序列化
		ObjectToObject oto = new StringToObject();// 得到序列化器
		List list2 = null;
		
	
		try {
			list2 = (List) oto.transform(list);// 反序列化得到对象
			
		} catch (ObjectToObjectException e) {
			e.printStackTrace();
		}
		DataDemo dd = new DataDemo();
		
//		
//		changeresult=title2.get(titleindex).toString();
//		System.out.println(changeresult);
		
		// 从页面得到选择的条件
		if (!"".equals(request.getParameter("isAll"))
				&& null != request.getParameter("isAll")) {// 是否全选
			isAll = true;
		}
		if (!"".equals(request.getParameter("isCol"))
				&& null != request.getParameter("isCol")) {// 是否列全选
			isCol = true;
		}
		if (!"".equals(request.getParameter("isRow"))
				&& null != request.getParameter("isRow")) {// 是否行全选
			isRow = true;
		}
		
		/***********************************************************************
		 * 逻辑判断1
		 **********************************************************************/
		if (isCol) {// 列全选
			choose = 1;
		}
		if (isRow) {// 行全选
			choose = 2;
		}
		if (isAll) {// 行列全选
			choose = 3;
		}
		if (isCol && isRow) {// 行列全选
			choose = 3;
		}
		if (choose == 0) {//当没有选择任何选项的时候就默认完全选择
			if (!"".equals(tempCol) && null != tempCol && !"".equals(tempRow)
					&& null != tempRow) {
				//不做任何选择
			} else {
				choose = 3;//什么都没有选等同于行列全选
			}
		}
		/***********************************************************************
		 * 逻辑判断1
		 **********************************************************************/
		if (choose == 3) {
			value = list2;// 行列全选就把原有数据集赋值给value
			
		}

		// 行、列全选行列全选没有使用 或者 行全选 或者 列全选
		if (choose == 0 || choose == 1 || choose == 2) {
			int[] col = null;// 所选的列
			int[] row = null;// 所选的行
			// 把字符串型转换为数字
			ParseInteger pi = new ParseInteger();// 得到强转器对象
			/*******************************************************************
			 * 逻辑判断赋值开始
			 ******************************************************************/
			// 列的操作
			if (choose == 1) {// 列全选
				col = new int[((List)(list2.get(0))).size()];// 所选的列
				
				for (int a = 0; a < col.length; a++) {
					col[a] = a;
				}
			} else if (null == tempCol || 0 == tempCol.length) {// 没选
				// 没有选择数据
			} else {// 部分选择
				col = new int[tempCol.length];// 所选的列
				
				for (int i = 0; i < tempCol.length; i++) {
					int subChoose = pi.parseInt(tempCol[i]);// 列转换为数字
					col[i] = subChoose;// 列转换为数字
//					System.out.println("aaa"+subChoose);
		
				}
				int temp=0;
				for(int i =0 ;i<tempCol.length;i++){
					System.out.println("aaa"+tempCol[i]);
					if(tempCol[i].equals(changeresult)){
						changeresult=new Integer(i).toString();
						System.out.println(changeresult);
						temp=1;
					}
				}
				
				if(temp==0){
					request.setAttribute("message", "计算数据必需引入所要预测的数据");
					request.getRequestDispatcher("message.jsp").forward(request,
							response);
					return;
				}
			}
			// 行的操作
			if (choose == 2) {// 行全选
				row = new int[list2.size()];

				for (int a = 0; a < row.length; a++) {
					row[a] = a;
				}
			} else if (null == tempRow || 0 == tempRow.length) {
				// 没有选择数据
			} else {// 部分选择
				row = new int[tempRow.length];// 所选的行
				for (int i = 0; i < tempRow.length; i++) {
					int subChoose = pi.parseInt(tempRow[i]);// 行转换为数字
					row[i] = subChoose;

				}
			}
			/*******************************************************************
			 * 逻辑判断赋值结束
			 ******************************************************************/

			// 执行数据缩减
			ChoosePageData cpd = new ChoosePageData();// 创建一个对象
			value = cpd.beginTran(list2, col, row);// 得到选中的数据
		}
//		System.out.println("参与预测的数据：");
//		 for (int i = 0; i < value.size(); i++) {
//		 List temp = (List) value.get(i);
//		 for (int j = 0; j < temp.size(); j++) {
//		 System.out.print(temp.get(j) + "-");
//		 }
//		 System.out.println();
//		 }
		
		if(null==changeresult){
			request.setAttribute("message", "请选择因变量");
			request.getRequestDispatcher("message.jsp").forward(request,
					response);
			return;
		}
		int index = new Integer(changeresult);
		DataDemo DD= new DataDemo();
		
		List value1=new ListDemo().DoubletoList2(dd.demo(new ListDemo().ListToDouble(value), index));
		String objStr = null;// 用于存放对象字符串
		// 执行序列化
		try {
			ObjectToObject otoStr = new ObjectToString();// 得到对象转换器
			objStr = (String) otoStr.transform(value1);
		} catch (ObjectToObjectException e) {
			e.printStackTrace();
		}
//		System.out.println("因变量:"+changeresult);
		// 保存数据
		request.setAttribute("objStr", objStr);
	    request.setAttribute("changeresult", changeresult);// 用于显示title
		// request.setAttribute("xScale", xScale);// 用于显示xScale
		// 跳转
		request.getRequestDispatcher("choose.jsp").forward(request, response);
	}
}
