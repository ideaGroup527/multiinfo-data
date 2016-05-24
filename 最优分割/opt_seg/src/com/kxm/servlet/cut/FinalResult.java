package com.kxm.servlet.cut;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kxm.exception.ObjectToObjectException;
import com.kxm.parse.ObjectToObject;
import com.kxm.parse.StringToObject;
import com.kxm.servlet.download.ExcelAdd;
import com.kxm.util.Transfer;

public class FinalResult extends HttpServlet {

	/**
	 * �Ͻ�finalchoose.jsp �½�finalResult.jsp
	 */
	public FinalResult() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String tran = null;
		String[] cutend = null;
		
		// �õ������ַ���
		if (!"".equals(request.getParameter("tran"))
				&& null != request.getParameter("tran")) {
			tran = request.getParameter("tran");// �õ������ַ���
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
		
		cutend = transfer.getCutend();
		int[] finalcut = new int[cutend.length];
		for(int i = 0;i<cutend.length;i++){
			String temp = null;
			String tempname = String.valueOf(i);
			if (!"".equals(request.getParameter(tempname))
					&& null != request.getParameter(tempname)) {
				temp = request.getParameter(tempname);// �õ������ַ���
			}else{
				// ��ת����Ϣҳ��
				// ������Ϣ
				request.setAttribute("message", "������ȷ������д��");
				request.getRequestDispatcher("message.jsp").forward(request,
						response);
				return;
			}
			finalcut[i] = (Integer.valueOf(temp)).intValue();
			if(i>=1){//��֤�Ƿ�����������
				if(finalcut[i]<=finalcut[i-1]){
					request.setAttribute("message", "������ȷ˳����д��");
					request.getRequestDispatcher("message.jsp").forward(request,
							response);
					return;
				}
			}
			if(i==cutend.length-1){//���һ�β��ɸ���
				if(!cutend[i].equals(temp)){
					// ��ת����Ϣҳ��
					// ������Ϣ
					request.setAttribute("message", "���һ�εĶ�ĩ����Ϊ�������һ�У�");
					request.getRequestDispatcher("message.jsp").forward(request,
							response);
					return;
				}
			}
		}
		//�������������String[][]
		String[] xScale = transfer.getXscale();
		String[] linetitle = transfer.getLineTitle();
		List value = transfer.getValue();
		List acol = (List)value.get(0);
		int linenum = acol.size();
		String[][] showresult = new String[linenum][linetitle.length+2];
		for(int i = 0;i<linenum;i++){
			showresult[i][0] = xScale[i]; //��һ�� ��ʶ��
		}
		int temp = 1;
		for(int i = 0;i<linenum;i++){
			
			showresult[i][1] = String.valueOf(temp);//�ڶ��� ������
			
			if(i+1 == finalcut[temp-1]){
				temp++;
			}
		}
		for(int i = 0;i<value.size();i++){
			List col = (List)value.get(i);
			for(int j = 0;j<linenum;j++){
				Double tempd = (Double)col.get(j);
				showresult[j][2+i] =  String.valueOf(tempd);//������֮�� ����
			}
		}
		//���ô���ΪExcel���String[][]
		String[][] excShowResult = new String[showresult.length+1][linetitle.length+2];
		
		excShowResult[0][0] = "��ѡ��ʶ{Identification} ";
		excShowResult[0][1] = "���ŷ���{group}";
		
		for(int i=0; i<linetitle.length; i++){
			excShowResult[0][i+2] = linetitle[i];
		}
		for(int i=0; i<showresult.length; i++){
			excShowResult[i+1] = showresult[i];
		}
		
		String base = request.getSession().getServletContext().getRealPath("/");
		String url = base + "downloaddir\\";
		

		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//��õ�ǰʱ����ַ����������ļ�����һ����
		Calendar calendar = Calendar.getInstance();
		String DateString = df.format(calendar.getTime());
		
		String excFinResTablePath = url+"excFinResTable"+DateString;
		try {
			//�½��м��Excel�ļ�
			new ExcelAdd( excFinResTablePath ,excShowResult); 
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		String title = transfer.getChartTitle();
		
		request.setAttribute("title", title);
		request.setAttribute("linetitle", linetitle);
		request.setAttribute("showresult", showresult);  //�ָ�ζ�ĩ���
		request.setAttribute("excFinResTablePath", excFinResTablePath); //Excel���ļ���ַ
		request.getRequestDispatcher("finalResult.jsp").forward(request,
				response);
	}

}
