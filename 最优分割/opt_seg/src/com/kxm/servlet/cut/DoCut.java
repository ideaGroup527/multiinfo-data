package com.kxm.servlet.cut;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.chart.servlet.ServletUtilities;

import com.kxm.cut.CreatChart;
import com.kxm.cut.Cut;
import com.kxm.cut.ElimOutliers;
import com.kxm.cut.LogTrans;
import com.kxm.exception.ObjectToObjectException;
import com.kxm.parse.ListToDouble;
import com.kxm.parse.ObjectToObject;
import com.kxm.parse.ObjectToString;
import com.kxm.parse.StringToObject;
import com.kxm.servlet.download.ExcelAdd;
import com.kxm.util.Transfer;

public class DoCut extends HttpServlet {

	/**
	 * 得到来自choose的参数，进行最优分割运算
	 */
	static String url;
	
	public DoCut() {
		super();
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		String tran = null;       // 对象字符串
		String tmpElim = null;    // 暂存参数isElim
		String tmpLog = null;     // 暂存参数isLog
		String tmpCutnum = null;  // 暂存参数Mycutnum
		int cutnum = 0;           // 整型cutnum
		
		String title = null;      //表名
		String[] xScale = null;     //标志列
		List tmpBefore = new ArrayList();     //用于运算的before
		// 得到对象字符串
		
		
		
		//
		if (!"".equals(request.getParameter("tran"))
				&& null != request.getParameter("tran")) {
			tran = request.getParameter("tran");// 得到对象字符串
		}
		
		if (!"".equals(request.getParameter("isElim"))
				&& null != request.getParameter("isElim")) {
			tmpElim = request.getParameter("isElim");// 得到参数是否消除异常值
		}
		
		if (!"".equals(request.getParameter("isLog"))
				&& null != request.getParameter("isLog")) {
			tmpLog = request.getParameter("isLog");// 得到参数是否对数变换
		}
		
		if (!"".equals(request.getParameter("myCutnum"))
				&& null != request.getParameter("myCutnum")) {
			tmpCutnum = request.getParameter("myCutnum");// 得到参数段数
			cutnum = Integer.valueOf(tmpCutnum); //转换成整型
		}
		// 反序列化
		ObjectToObject oto = new StringToObject();// 得到序列化器
		Transfer transfer = null;
		try {
			transfer = (Transfer) oto.transform(tran);// 反序列化得到对象
		} catch (ObjectToObjectException e) {
			// 跳转到消息页面
			// 保存消息
			request.setAttribute("message", "对象反序列化出错！");
			request.getRequestDispatcher("message.jsp").forward(request,
					response);
			return;
		}
		ArrayList before = (ArrayList) transfer.getValue();// 得到value集合
		for(int i = 0;i<before.size();i++){ //深入克隆before
			ArrayList tmpSub = new ArrayList(); 
			tmpSub = (ArrayList)before.get(i);
			tmpBefore.add(tmpSub.clone());
		}
		title = transfer.getChartTitle();  //得到表名
		xScale = transfer.getXscale();     // 得到xScale
		/** **前期处理*** */

		List tmpTest = transfer.getValue();
		if("true".equals(tmpElim)){
			ElimOutliers elim = new ElimOutliers(); //得到消除异常值工具ElimOutliers类的实例
			tmpBefore = elim.work(tmpBefore);
			System.out.println(ListToDouble.hasNaN(tmpBefore));
		}else{}
		
		if("true".equals(tmpLog)){
			LogTrans log = new LogTrans();           //得到对数变换工具LogTrans类的实例
			tmpBefore = log.work(tmpBefore); 
			System.out.println(ListToDouble.hasNaN(tmpBefore));
		}else{}
		tmpTest = transfer.getValue();
		/** **进行最优分割运算*** */
		Cut.cut(tmpBefore, cutnum);
		List result = Cut.getResult();
		List presult = Cut.getPrintResult();
		CreatChart chart = new CreatChart();
		String fileName = null;     //生成图片的文件名     
			//获取生成图片的名称      
		fileName = ServletUtilities.saveChartAsJPEG(              
						chart.createChart(presult), 
						500, 300, 
						request.getSession());
		//获取图片的路径
		String graphURL = request.getContextPath() + "/DisplayChart?filename=" + fileName;  
		//将路径放到request对象中  
		request.setAttribute("graphURL", graphURL);
		
		//转换result到适合输出列表的String[][][]格式
		List aCut = (List)result.get(0);
		List aSeg = (List)aCut.get(0);
		String[][][] showresult = new String[result.size()][][];//数组元素长度问题？？
		for(int i = 0; i< result.size();i++){
			aCut = (List)result.get(i);
			String[][] tempString = new String[aCut.size()][4];
			for(int j = 0; j< aCut.size() ; j++){
				aSeg = (List)aCut.get(j);
				for(int k = 0; k < aSeg.size()-1 ; k++){
					Double temp = (Double)aSeg.get(k);
					double tempd = temp.doubleValue();
					String temps = String.valueOf((int)tempd);
					tempString[j][k] = temps;
				}
				Double temp = (Double)aSeg.get(3);
				double tempd = temp.doubleValue();
				String temps = String.valueOf(tempd);
				tempString[j][3] = temps;
			}
			showresult[i] = tempString;
		}
		//这个excTables是用来装String[][]的 因为第一组表是多个表拼成的,
		//行数难以统计,所以把所有二维表都装在这个容器里,你结束的时候想把他们拼在一起也可以,
		//只要统计一下所有现有表的行数,连接一下做一张大表就行了
		
		List<String[][]> excelTables = new  ArrayList<String[][]>();
		for(int i=0; i < result.size();i++){
			aCut = (List)result.get(i);
			String[][] excelTable = new String[aCut.size()+3][4];
			excelTable[0][1] = "最优"+(i+2)+"段";
			excelTable[0][2] = "分割结果{result}";
			excelTable[1][0] = "段号 {segment number}";
			excelTable[1][1] = "起点号 {from}";
			excelTable[1][2] = "终点号 {to}";
			excelTable[1][3] = "组内离差平方和{variance of the segment}";
			int j = 0;
			for(j = 0; j< aCut.size() ; j++){
				aSeg = (List)aCut.get(j);
				excelTable[j+2][0] = aSeg.get(0).toString();
				excelTable[j+2][1] = aSeg.get(1).toString();
				excelTable[j+2][2] = aSeg.get(2).toString();
				excelTable[j+2][3] = aSeg.get(3).toString();
			}
			excelTable[j+2][0] = "离差平方总和";
			excelTable[j+2][1] = "{segment}： ";
			excelTable[j+2][2] = ""+ presult.get(i);
			excelTables.add(excelTable);
		}
		int rows = 0;
		for(String[][] table : excelTables){
			rows += table.length;
		}
		String[][] excMidResTable = new String[rows][4];
		int countRow = 0;
		for(int i = 0 ; i < result.size() ; i++){
			String[][] table = excelTables.get(i);
			for(int j = 0 ; j <table.length ; j++){
				excMidResTable[countRow + j][0] = table[j][0];
				excMidResTable[countRow + j][1] = table[j][1];
				excMidResTable[countRow + j][2] = table[j][2];
				excMidResTable[countRow + j][3] = table[j][3];
			}
			countRow += table.length;
		}
		
		//如无downloaddir目录 建目录
		String base = request.getSession().getServletContext().getRealPath("/");
		url = base + "downloaddir\\";
		File saveDir = new File(url);
		if (!saveDir.isDirectory())
			saveDir.mkdir();
		
		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//获得当前时间的字符串，用作文件名后一部分
		Calendar calendar = Calendar.getInstance();
		String DateString = df.format(calendar.getTime());
		
		String excMidResTablePath = url+"excMidResTable"+DateString;
		try {
			//新建中间解Excel文件
			new ExcelAdd( excMidResTablePath ,excMidResTable); 
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		String[] color = {"#FFFFFF","#F0FBFB","#9DE6E6","#53D2D2","#37CACA","#30B4B4","#299C9C","#207979","#1A6060","#0d3131"//蓝
		          ,"#CBFCDA","#A7FABF","#6FF797","#34F36D","0DDF4B","#0CC243","#0A9232","#077428","#044D1A","#02260D"//绿
		          ,"#FFFFF7","#FFFFB3","#FFFF71","#FFFF46","#FFFF09","#F2F200","#D9D900","#BFBF00","#828200","#5E5E00"//黄
		          ,"#FEEDDE","#FEC5B6","#FEA994","#FE8D72","#FE7856","#FE5429","#FE4010","#E72F01","#CD2901","#A52201"//红
		          ,"#F0F0F0","#D8D8D8","#C3C3C3","#AAAAAA","#939393","#7F7F7F","#676767","#4F4F4F","#404040","#FCFCFC"};//黑
		//转换result到适合输出 样品各次分割点位图 的String[][]格式
		String[][] lineresult = new String[result.size()][xScale.length];
		for(int i = 0; i< result.size();i++){
			aCut = (List)result.get(i);
			int colorflag = -1;
			String thiscolor = null;
			for(int j = 0; j< aCut.size() ; j++){
				aSeg = (List)aCut.get(j);
				colorflag = colorflag+3;
				if(colorflag>=50){
					colorflag = 2;
				}
				Double tempfrom = (Double)aSeg.get(1);
				int from = (int)tempfrom.doubleValue()-1;
				Double tempto = (Double)aSeg.get(2);
				int to = (int)tempto.doubleValue()-1;
				for(int k = from; k <= to;k++){
					lineresult[i][k] = color[colorflag];
				}
			}
		}
		//丁跃潮的显示方法
		String space = "&nbsp;";
		String line = "___";
		String[] CutnumDing = null;
		String[][] Dingresult = null;
		if(result.size()>10){
			Dingresult = new String[xScale.length][10];
			CutnumDing = new String[10];
			for(int i = 0; i < 10 ; i++){
				int num =  i + (result.size()- 9) + 1;
				aCut = (List)result.get(num);
				CutnumDing[i] = "" + num;
				for(int j = 0; j<aCut.size() ;j++){
					aSeg = (List)aCut.get(j);
					Double tempfrom = (Double)aSeg.get(1);//得到每段的开始结束下标
					int from = (int)tempfrom.doubleValue()-1;
					Double tempto = (Double)aSeg.get(2);
					int to = (int)tempto.doubleValue()-1;
					for(int k = from; k <= to;k++){
						if(k == to){
							Dingresult[k][i] = line;
						}else{
							Dingresult[k][i] = space;
						}
					}
				}
			}
		}else{
			Dingresult = new String[xScale.length][result.size()];
			CutnumDing = new String[result.size()];
			for(int i = 0; i < result.size() ; i++){
				aCut = (List)result.get(i);
				int num = i + 2;
				CutnumDing[i] = "" + num;
				for(int j = 0; j<aCut.size() ;j++){
					aSeg = (List)aCut.get(j);
					Double tempfrom = (Double)aSeg.get(1);//得到每段的开始结束下标
					int from = (int)tempfrom.doubleValue()-1;
					Double tempto = (Double)aSeg.get(2);
					int to = (int)tempto.doubleValue()-1;
					for(int k = from; k <= to;k++){
						if(k == to){
							Dingresult[k][i] = line;
						}else{
							Dingresult[k][i] = space;
						}
					}
				}
			}
		}
		//最后一行不用加横线,默认分段到最后一行
		for(int i = 0 ; i < Dingresult[0].length ; i++){
			Dingresult[Dingresult.length - 1][i] = space;
		}
		//excDingTable是丁氏表的String[][]
		String[][] excDingTable = new String[Dingresult.length+1][Dingresult[0].length+1];
		for(int i=0; i<Dingresult.length;i++){
			for(int j=0;j<Dingresult[0].length;j++){
				if(Dingresult[i][j]== space){
					excDingTable[i+1][j+1] = " ";
				}else{
					excDingTable[i+1][j+1] = Dingresult[i][j];
				}
			}
		}
		excDingTable[0][0] = "标识列{sign}/分割段数{segment}";
		for(int i=1;i<excDingTable[0].length;i++){
			excDingTable[0][i] = ""+(i+1);
		}
		for(int i=1;i<excDingTable.length;i++){
			excDingTable[i][0] = xScale[i-1];
		}
		
		String excDingTablePath = url+"excDingTable"+DateString;
		try {
			//新建丁氏表Excel文件	
			new ExcelAdd(excDingTablePath , excDingTable); 
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		int[] cutnumS = new int[result.size()];
		for(int i = 0;i<cutnumS.length;i++){
			cutnumS[i] = i+2;
		}

		//把结果加入数据集
		transfer.setResult(showresult);
		transfer.setPresult(presult);
		transfer.setLineresult(lineresult);
		
		String objStrTran = null;// 用于存放对象字符串
		ObjectToObject otoStr = new ObjectToString();// 得到对象转换器
		// 执行序列化
		try {
			
			objStrTran = (String) otoStr.transform(transfer);
		} catch (ObjectToObjectException e) {
			// 跳转到消息页面
			// 保存消息
			request.setAttribute("message", "对象序列化出错！");
			request.getRequestDispatcher("message.jsp").forward(request,
					response);
			return;
		}

		  
		request.setAttribute("tran", objStrTran);  //数据合集
		request.setAttribute("Dingresult", Dingresult);
		request.setAttribute("CutnumDing", CutnumDing);
		
		request.setAttribute("lineresult", lineresult);
		request.setAttribute("result", showresult);
		request.setAttribute("presult", presult);
		request.setAttribute("title", title);  //表名
		request.setAttribute("xScale",xScale); //标志列
		request.setAttribute("cutnumS",cutnumS); //分割段数数组
		request.setAttribute("excMidResTablePath", excMidResTablePath); //中间结果表的路径
		request.setAttribute("excDingTablePath", excDingTablePath);     //分割表<一>的路径
		
		request.getRequestDispatcher("midResult.jsp").forward(request,
				response);
	}
}


