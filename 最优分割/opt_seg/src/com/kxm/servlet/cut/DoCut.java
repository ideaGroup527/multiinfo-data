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
	 * �õ�����choose�Ĳ������������ŷָ�����
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
		String tran = null;       // �����ַ���
		String tmpElim = null;    // �ݴ����isElim
		String tmpLog = null;     // �ݴ����isLog
		String tmpCutnum = null;  // �ݴ����Mycutnum
		int cutnum = 0;           // ����cutnum
		
		String title = null;      //����
		String[] xScale = null;     //��־��
		List tmpBefore = new ArrayList();     //���������before
		// �õ������ַ���
		
		
		
		//
		if (!"".equals(request.getParameter("tran"))
				&& null != request.getParameter("tran")) {
			tran = request.getParameter("tran");// �õ������ַ���
		}
		
		if (!"".equals(request.getParameter("isElim"))
				&& null != request.getParameter("isElim")) {
			tmpElim = request.getParameter("isElim");// �õ������Ƿ������쳣ֵ
		}
		
		if (!"".equals(request.getParameter("isLog"))
				&& null != request.getParameter("isLog")) {
			tmpLog = request.getParameter("isLog");// �õ������Ƿ�����任
		}
		
		if (!"".equals(request.getParameter("myCutnum"))
				&& null != request.getParameter("myCutnum")) {
			tmpCutnum = request.getParameter("myCutnum");// �õ���������
			cutnum = Integer.valueOf(tmpCutnum); //ת��������
		}
		// �����л�
		ObjectToObject oto = new StringToObject();// �õ����л���
		Transfer transfer = null;
		try {
			transfer = (Transfer) oto.transform(tran);// �����л��õ�����
		} catch (ObjectToObjectException e) {
			// ��ת����Ϣҳ��
			// ������Ϣ
			request.setAttribute("message", "�������л�����");
			request.getRequestDispatcher("message.jsp").forward(request,
					response);
			return;
		}
		ArrayList before = (ArrayList) transfer.getValue();// �õ�value����
		for(int i = 0;i<before.size();i++){ //�����¡before
			ArrayList tmpSub = new ArrayList(); 
			tmpSub = (ArrayList)before.get(i);
			tmpBefore.add(tmpSub.clone());
		}
		title = transfer.getChartTitle();  //�õ�����
		xScale = transfer.getXscale();     // �õ�xScale
		/** **ǰ�ڴ���*** */

		List tmpTest = transfer.getValue();
		if("true".equals(tmpElim)){
			ElimOutliers elim = new ElimOutliers(); //�õ������쳣ֵ����ElimOutliers���ʵ��
			tmpBefore = elim.work(tmpBefore);
			System.out.println(ListToDouble.hasNaN(tmpBefore));
		}else{}
		
		if("true".equals(tmpLog)){
			LogTrans log = new LogTrans();           //�õ������任����LogTrans���ʵ��
			tmpBefore = log.work(tmpBefore); 
			System.out.println(ListToDouble.hasNaN(tmpBefore));
		}else{}
		tmpTest = transfer.getValue();
		/** **�������ŷָ�����*** */
		Cut.cut(tmpBefore, cutnum);
		List result = Cut.getResult();
		List presult = Cut.getPrintResult();
		CreatChart chart = new CreatChart();
		String fileName = null;     //����ͼƬ���ļ���     
			//��ȡ����ͼƬ������      
		fileName = ServletUtilities.saveChartAsJPEG(              
						chart.createChart(presult), 
						500, 300, 
						request.getSession());
		//��ȡͼƬ��·��
		String graphURL = request.getContextPath() + "/DisplayChart?filename=" + fileName;  
		//��·���ŵ�request������  
		request.setAttribute("graphURL", graphURL);
		
		//ת��result���ʺ�����б��String[][][]��ʽ
		List aCut = (List)result.get(0);
		List aSeg = (List)aCut.get(0);
		String[][][] showresult = new String[result.size()][][];//����Ԫ�س������⣿��
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
		//���excTables������װString[][]�� ��Ϊ��һ����Ƕ����ƴ�ɵ�,
		//��������ͳ��,���԰����ж�ά��װ�����������,�������ʱ���������ƴ��һ��Ҳ����,
		//ֻҪͳ��һ���������б������,����һ����һ�Ŵ�������
		
		List<String[][]> excelTables = new  ArrayList<String[][]>();
		for(int i=0; i < result.size();i++){
			aCut = (List)result.get(i);
			String[][] excelTable = new String[aCut.size()+3][4];
			excelTable[0][1] = "����"+(i+2)+"��";
			excelTable[0][2] = "�ָ���{result}";
			excelTable[1][0] = "�κ� {segment number}";
			excelTable[1][1] = "���� {from}";
			excelTable[1][2] = "�յ�� {to}";
			excelTable[1][3] = "�������ƽ����{variance of the segment}";
			int j = 0;
			for(j = 0; j< aCut.size() ; j++){
				aSeg = (List)aCut.get(j);
				excelTable[j+2][0] = aSeg.get(0).toString();
				excelTable[j+2][1] = aSeg.get(1).toString();
				excelTable[j+2][2] = aSeg.get(2).toString();
				excelTable[j+2][3] = aSeg.get(3).toString();
			}
			excelTable[j+2][0] = "���ƽ���ܺ�";
			excelTable[j+2][1] = "{segment}�� ";
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
		
		//����downloaddirĿ¼ ��Ŀ¼
		String base = request.getSession().getServletContext().getRealPath("/");
		url = base + "downloaddir\\";
		File saveDir = new File(url);
		if (!saveDir.isDirectory())
			saveDir.mkdir();
		
		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//��õ�ǰʱ����ַ����������ļ�����һ����
		Calendar calendar = Calendar.getInstance();
		String DateString = df.format(calendar.getTime());
		
		String excMidResTablePath = url+"excMidResTable"+DateString;
		try {
			//�½��м��Excel�ļ�
			new ExcelAdd( excMidResTablePath ,excMidResTable); 
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		String[] color = {"#FFFFFF","#F0FBFB","#9DE6E6","#53D2D2","#37CACA","#30B4B4","#299C9C","#207979","#1A6060","#0d3131"//��
		          ,"#CBFCDA","#A7FABF","#6FF797","#34F36D","0DDF4B","#0CC243","#0A9232","#077428","#044D1A","#02260D"//��
		          ,"#FFFFF7","#FFFFB3","#FFFF71","#FFFF46","#FFFF09","#F2F200","#D9D900","#BFBF00","#828200","#5E5E00"//��
		          ,"#FEEDDE","#FEC5B6","#FEA994","#FE8D72","#FE7856","#FE5429","#FE4010","#E72F01","#CD2901","#A52201"//��
		          ,"#F0F0F0","#D8D8D8","#C3C3C3","#AAAAAA","#939393","#7F7F7F","#676767","#4F4F4F","#404040","#FCFCFC"};//��
		//ת��result���ʺ���� ��Ʒ���ηָ��λͼ ��String[][]��ʽ
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
		//��Ծ������ʾ����
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
					Double tempfrom = (Double)aSeg.get(1);//�õ�ÿ�εĿ�ʼ�����±�
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
					Double tempfrom = (Double)aSeg.get(1);//�õ�ÿ�εĿ�ʼ�����±�
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
		//���һ�в��üӺ���,Ĭ�Ϸֶε����һ��
		for(int i = 0 ; i < Dingresult[0].length ; i++){
			Dingresult[Dingresult.length - 1][i] = space;
		}
		//excDingTable�Ƕ��ϱ��String[][]
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
		excDingTable[0][0] = "��ʶ��{sign}/�ָ����{segment}";
		for(int i=1;i<excDingTable[0].length;i++){
			excDingTable[0][i] = ""+(i+1);
		}
		for(int i=1;i<excDingTable.length;i++){
			excDingTable[i][0] = xScale[i-1];
		}
		
		String excDingTablePath = url+"excDingTable"+DateString;
		try {
			//�½����ϱ�Excel�ļ�	
			new ExcelAdd(excDingTablePath , excDingTable); 
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		int[] cutnumS = new int[result.size()];
		for(int i = 0;i<cutnumS.length;i++){
			cutnumS[i] = i+2;
		}

		//�ѽ���������ݼ�
		transfer.setResult(showresult);
		transfer.setPresult(presult);
		transfer.setLineresult(lineresult);
		
		String objStrTran = null;// ���ڴ�Ŷ����ַ���
		ObjectToObject otoStr = new ObjectToString();// �õ�����ת����
		// ִ�����л�
		try {
			
			objStrTran = (String) otoStr.transform(transfer);
		} catch (ObjectToObjectException e) {
			// ��ת����Ϣҳ��
			// ������Ϣ
			request.setAttribute("message", "�������л�����");
			request.getRequestDispatcher("message.jsp").forward(request,
					response);
			return;
		}

		  
		request.setAttribute("tran", objStrTran);  //���ݺϼ�
		request.setAttribute("Dingresult", Dingresult);
		request.setAttribute("CutnumDing", CutnumDing);
		
		request.setAttribute("lineresult", lineresult);
		request.setAttribute("result", showresult);
		request.setAttribute("presult", presult);
		request.setAttribute("title", title);  //����
		request.setAttribute("xScale",xScale); //��־��
		request.setAttribute("cutnumS",cutnumS); //�ָ��������
		request.setAttribute("excMidResTablePath", excMidResTablePath); //�м������·��
		request.setAttribute("excDingTablePath", excDingTablePath);     //�ָ��<һ>��·��
		
		request.getRequestDispatcher("midResult.jsp").forward(request,
				response);
	}
}


