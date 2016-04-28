package org.forecast.sevlet.getdata;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.forecast.calculate.CalRet;
import org.forecast.calculate.CommonMethod;
import org.forecast.calculate.DataDemo;
import org.forecast.calculate.Zuobuhuigui;
import org.forecast.exception.ObjectToObjectException;
import org.forecast.parse.ListDemo;
import org.forecast.parse.ObjectToObject;
import org.forecast.parse.StringToObject;
import org.forecast.util.ShowDataUnit;
import org.forecast.util.zuobu;

public class calculate extends HttpServlet {

	public calculate() {
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
		String objStr = null;
		double f1 = 0.0;
		double f2 = 0.0;
		int method = 0;
		double f005 = 0;
		double f001 = 0;
		double t005 = 0;
		double t001 = 0;
		String equation=new String("");
		String equation1=new String("");
		String equation2=new String("");
		String equation3=new String("");
		if (!"".equals(request.getParameter("f1"))
				&& null != request.getParameter("f1")) {
			f1 = new Double(request.getParameter("f1"));
		}
		if (!"".equals(request.getParameter("f2"))
				&& null != request.getParameter("f2")) {
			f2 = new Double(request.getParameter("f2"));
		}
		if (!"".equals(request.getParameter("objStr"))
				&& null != request.getParameter("objStr")) {
			objStr = request.getParameter("objStr");
		}
		if (!"".equals(request.getParameter("method"))
				&& null != request.getParameter("method")) {
			method = new Integer(request.getParameter("method"));
		}

		if (f1 == 0 || f2 == 0) {
			request.setAttribute("message", "引入F或剔除F不能为0");
			request.getRequestDispatcher("message.jsp").forward(request,
					response);
			return;
		}
		if (f1 < f2) {
			request.setAttribute("message", "引入F不能小于剔除F");
			request.getRequestDispatcher("message.jsp").forward(request,
					response);
			return;
		}
		ObjectToObject oto = new StringToObject();// 得到序列化器
		List list = null;

		try {
			list = (List) oto.transform(objStr);// 反序列化得到对象

		} catch (ObjectToObjectException e) {
			e.printStackTrace();
		}

		// System.out.println("参与预测的数据：");
		// for (int i = 0; i < list.size(); i++) {
		// List temp = (List) list.get(i);
		// for (int j = 0; j < temp.size(); j++) {
		// System.out.print(temp.get(j) + "-");
		// }
		// System.out.println();
		// }
		if (method == 0) {
			request.setAttribute("message", "请选择预测方法！");
			request.getRequestDispatcher("message.jsp").forward(request,
					response);
			return;
		} else if (method == 1) {// method=1时，选择用逐步回归
			ListDemo ld = new ListDemo();
			double[][] list2 = ld.ListToDouble(list);
			// for(int i=0;i<list2.length;i++){
			// for(int j=0;j<list2[0].length;j++){
			// System.out.print(list2[i][j]);
			// }
			// System.out.println();
			// }
			if(list2.length<list2[0].length+1){
				request.setAttribute("message", "数据量不够！");
				request.getRequestDispatcher("message.jsp").forward(request,
						response);
				return;
			}
			DataDemo dd = new DataDemo();
			ShowDataUnit sdu = new ShowDataUnit();
			// double [][]cc =dd.qy1(list2);
			Zuobuhuigui zbhg = new Zuobuhuigui();
			zuobu zb = zbhg.zuobuhuigui(list2, f1, f2);
			double f = zb.getF();
			double UA = zb.getL();
			double b[] = zb.getB();
			double t[] = zb.getTi();
			double UE = list2.length - UA - 1;

			sdu.setF(f);
			sdu.setUa(UA);
			try {
				f005 = CommonMethod.pF_Dist(UA, UE, 0.05);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				f001 = CommonMethod.pF_Dist(UA, UE, 0.01);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				t005 = CommonMethod.pT_Dist(UE, 0.05 / 2) * 2;

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				t001 = CommonMethod.pT_Dist(UE, 0.01 / 2) * 2;

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			sdu.setF001(f001);
			sdu.setF005(f005);
			sdu.setT001(t001);
			sdu.setT005(t005);
			double b2[] = new double[b.length];
			b2[0] = b[b.length - 1];
			for (int i = 0; i < b.length - 1; i++) {
				b2[i + 1] = b[i];
			}
			
	//		int final_b_length = 1;
	//		for (int i = 0; i < b.length - 1; i++) {
	//			b2[i + 1] = b[i];
	//		}
	//		for(int i=0;i<t.length;i++){
	//			if(t[i]>=t005){
	//				final_b_length++;
	//			}
	//		}
//			double[] final_b = new double[final_b_length];
//			final_b[0]= b[b.length-1];
//			int b_index = 1;
//			for(int i = 0 ;i<b.length-1;i++){
//				if(t[i]>=t005){
//					final_b[b_index]=b[i];
//					b_index++;
//				}
//			}
			
			equation=equation+b[b.length-1];
			for(int i = 1 ;i<b.length-1;i++){
				if(b[i]!=0)
					equation=equation+" + "+b[i]+" × "+"X"+i;
			}
			// System.out.println("F检验值为："+f);
			// System.out.println("选出的重要变量个数为："+UA);
			// System.out.println("他们分别为：：");
			// for(int i=0;i<b.length;i++){
			// System.out.println(b[i]+" ");
			// }
			// System.out.println("各变量的T检验值为：");
			// for(int i=0;i<t.length;i++){
			// System.out.println(t[i]+" ");
			// }
			// System.out.println("显著性为5的F临界值："+f005);
			// System.out.println("显著性为1的F临界值："+f001);
			// System.out.println("显著性为5的t临界值："+t005);
			// System.out.println("显著性为1的t临界值："+t001);
			response.setContentType("text/html ");
			response.setCharacterEncoding("gb2312");
			request.setAttribute("sdu", sdu);
			request.setAttribute("equation", equation);
			request.setAttribute("t", t);

			request.getRequestDispatcher("showResult.jsp").forward(request,
					response);
		} else if (method == 2) {

			// method=2时，选择用前移回归
			ListDemo ld = new ListDemo();
			double[][] list2 = ld.ListToDouble(list);
			// for(int i=0;i<list2.length;i++){
			// for(int j=0;j<list2[0].length;j++){
			// System.out.print(list2[i][j]);
			// }
			// System.out.println();
			// }
			if(list2.length<list2[0].length+1){
				request.setAttribute("message", "数据量不够！");
				request.getRequestDispatcher("message.jsp").forward(request,
						response);
				return;
			}
			DataDemo dd = new DataDemo();
			double[][] cc = dd.qy1(list2);
			double[] indvarsdata = new double[list2[0].length - 1];
			for (int i = 0; i < indvarsdata.length; i++) {
				indvarsdata[i] = list2[list2.length - 1][i];
			}
			Zuobuhuigui zbhg = new Zuobuhuigui();
			zuobu zb = zbhg.zuobuhuigui(cc, f1, f2);
			double f = zb.getF();
			double UA = zb.getL();
			double b[] = zb.getB();
			double t[] = zb.getTi();
			double UE = list2.length - UA - 1;
			try {
				f005 = CommonMethod.pF_Dist(UA, UE, 0.05);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				f001 = CommonMethod.pF_Dist(UA, UE, 0.01);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				t005 = CommonMethod.pT_Dist(UE, 0.05 / 2) * 2;

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				t001 = CommonMethod.pT_Dist(UE, 0.01 / 2) * 2;

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			double b2[] = new double[b.length];
			b2[0] = b[b.length - 1];
		//	for (int i = 0; i < b.length - 1; i++) {
		//		b2[i + 1] = b[i];
		//	}
		//	int final_b_length = 1;
			for (int i = 0; i < b.length - 1; i++) {
				b2[i + 1] = b[i];
			}
//			for(int i=0;i<t.length;i++){
//				if(t[i]>=t005){
//					final_b_length++;
//				}
//			}
//			double[] final_b = new double[final_b_length];
//				int b_index = 1;
			equation=equation+b[b.length-1];
			for(int i = 1 ;i<b.length-1;i++){
				if(b[i]!=0)
					equation=equation+" + "+b[i]+" × "+"X"+i;
			}
			double result = 0;
			CalRet cg = new CalRet();
			result = cg.cal(b2, indvarsdata,t,t005);
			// System.out.println("F检验值为："+f);
			// System.out.println("选出的重要变量个数为："+UA);
			// System.out.println("他们分别为：：");
			// for(int i=0;i<b.length;i++){
			// System.out.println(b[i]+" ");
			// }
			// System.out.println("各变量的T检验值为：");
			// for(int i=0;i<t.length;i++){
			// System.out.println(t[i]+" ");
			// }
			// System.out.println("显著性为5的F临界值："+f005);
			// System.out.println("显著性为1的F临界值："+f001);
			// System.out.println("显著性为5的t临界值："+t005);
			// System.out.println("显著性为1的t临界值："+t001);
			ShowDataUnit sdu = new ShowDataUnit();
			sdu.setF(f);
			sdu.setF001(f001);
			sdu.setF005(f005);
			sdu.setResult(result);
			sdu.setT001(t001);
			sdu.setT005(t005);
			sdu.setUa(UA);
			response.setContentType("text/html ");
			response.setCharacterEncoding("gb2312");
//			request.setAttribute("b", final_b);
			request.setAttribute("equation", equation);
			request.setAttribute("t", t);
			request.setAttribute("sdu", sdu);
			request.getRequestDispatcher("showResult1.jsp").forward(request,
					response);
		} else if (method == 3) {

			// method=3时，选择用多因素趋势回归
			double f005_1 = 0;
			double f001_1 = 0;
			double t005_1 = 0;
			double t001_1 = 0;
			double f005_2 = 0;
			double f001_2 = 0;
			double t005_2 = 0;
			double t001_2 = 0;
			double f005_3 = 0;
			double f001_3 = 0;
			double t005_3 = 0;
			double t001_3 = 0;
			ListDemo ld = new ListDemo();
			double[][] list2 = ld.ListToDouble(list);
			if(list2.length<list2[0].length+3){
				request.setAttribute("message", "数据量不够！");
				request.getRequestDispatcher("message.jsp").forward(request,
						response);
				return;
			}
			DataDemo dd = new DataDemo();
			double[][] cc1 = dd.qy1(list2);
			double[][] cc2 = dd.qy2(list2);
			double[][] cc3 = dd.qy3(list2);
			double[] indvarsdata1 = new double[list2[0].length - 1];
			double[] indvarsdata2 = new double[list2[0].length - 2];
			double[] indvarsdata3 = new double[list2[0].length - 3];
			for (int i = 0; i < indvarsdata1.length; i++) {
				indvarsdata1[i] = list2[list2.length - 1][i];
			}
			for (int i = 0; i < indvarsdata2.length; i++) {
				indvarsdata2[i] = list2[list2.length - 2][i];
			}
			for (int i = 0; i < indvarsdata3.length; i++) {
				indvarsdata3[i] = list2[list2.length - 3][i];
			}
			Zuobuhuigui zbhg = new Zuobuhuigui();
			zuobu zb1 = zbhg.zuobuhuigui(cc1, f1, f2);
			zuobu zb2 = zbhg.zuobuhuigui(cc2, f1, f2);
			zuobu zb3 = zbhg.zuobuhuigui(cc3, f1, f2);
			double f_1 = zb1.getF();
			double UA_1 = zb1.getL();
			double b_1[] = zb1.getB();
			double t_1[] = zb1.getTi();
			double UE_1 = list2.length - UA_1 - 1;
			double f_2 = zb2.getF();
			double UA_2 = zb2.getL();
			double b_2[] = zb2.getB();
			double t_2[] = zb2.getTi();
			double UE_2 = list2.length - UA_2 - 1;
			double f_3 = zb3.getF();
			double UA_3 = zb3.getL();
			double b_3[] = zb3.getB();
			double t_3[] = zb3.getTi();
			double UE_3 = list2.length - UA_3 - 1;
//			System.out.println(f_1);
//			System.out.println(f_2);
//			System.out.println(f_3);
			try {
				f005_1 = CommonMethod.pF_Dist(UA_1, UE_1, 0.05);
				f005_2 = CommonMethod.pF_Dist(UA_2, UE_2, 0.05);
				f005_3 = CommonMethod.pF_Dist(UA_3, UE_3, 0.05);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				f001_1 = CommonMethod.pF_Dist(UA_1, UE_1, 0.01);
				f001_2 = CommonMethod.pF_Dist(UA_2, UE_2, 0.01);
				f001_3 = CommonMethod.pF_Dist(UA_3, UE_3, 0.01);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				t005_1 = CommonMethod.pT_Dist(UE_1, 0.05 / 2) * 2;
				t005_2 = CommonMethod.pT_Dist(UE_2, 0.05 / 2) * 2;
				t005_3 = CommonMethod.pT_Dist(UE_3, 0.05 / 2) * 2;

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				t001_1 = CommonMethod.pT_Dist(UE_1, 0.01 / 2) * 2;
				t001_2 = CommonMethod.pT_Dist(UE_2, 0.01 / 2) * 2;

				t001_3 = CommonMethod.pT_Dist(UE_3, 0.01 / 2) * 2;

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			double b2_1[] = new double[b_1.length];
			double b2_2[] = new double[b_2.length];
			double b2_3[] = new double[b_3.length];
			b2_1[0] = b_1[b_1.length - 1];
			b2_2[0] = b_2[b_2.length - 1];
			b2_3[0] = b_3[b_3.length - 1];
			for (int i = 0; i < b_1.length - 1; i++) {
				b2_1[i + 1] = b_1[i];
			}
			for (int i = 0; i < b_2.length - 1; i++) {
				b2_2[i + 1] = b_2[i];
			}
			for (int i = 0; i < b_3.length - 1; i++) {
				b2_3[i + 1] = b_3[i];
			}
			
			
	//		int final_b_length_1 = 1;
	//		for (int i = 0; i < b_1.length - 1; i++) {
	//			b2_1[i + 1] = b_1[i];
	//		}
	//		for(int i=0;i<t_1.length;i++){
	//			if(t_1[i]>=t005_1){
	//				final_b_length_1++;
	//			}
	//		}
			equation1=equation1+b_1[b_1.length-1];
			for(int i = 1 ;i<b_1.length-1;i++){
				if(b_1[i]!=0)
					equation1=equation1+" + "+b_1[i]+" × "+"X"+i;
			}
			
			
	//		int final_b_length_2 = 1;
	//		for (int i = 0; i < b_2.length - 1; i++) {
	//			b2_2[i + 1] = b_2[i];
	//		}
	//		for(int i=0;i<t_2.length;i++){
	//			if(t_2[i]>=t005_2){
	//				final_b_length_2++;
	//			}
	//		}
	//		double[] final_b_2 = new double[final_b_length_2];
	//		final_b_2[0]= b_2[b_2.length-1];
	//		int b_index_2 = 1;
	//		for(int i = 0 ;i<b_2.length-1;i++){
	//			if(t_2[i]>=t005_2){
	//				final_b_2[b_index_2]=b_2[i];
	//				b_index_2++;
	//			}
	//		}
			
			equation2=equation2+b_2[b_2.length-1];
			for(int i = 1 ;i<b_2.length-1;i++){
				if(b_2[i]!=0)
					equation2=equation2+" + "+b_2[i]+" × "+"X"+i;
			}		
			
	//		int final_b_length_3 = 1;
	//		for (int i = 0; i < b_3.length - 1; i++) {
	//			b2_3[i + 1] = b_3[i];
	//		}
	//		for(int i=0;i<t_3.length;i++){
	//			if(t_3[i]>=t005_3){
	//				final_b_length_3++;
	//			}
	//		}
//			double[] final_b_3 = new double[final_b_length_3];
//			final_b_3[0]= b_3[b_3.length-1];
//			int b_index_3 = 1;
//			for(int i = 0 ;i<b_3.length-1;i++){
//				if(t_3[i]>=t005_3){
//					final_b_3[b_index_3]=b_3[i];
//					b_index_3++;
//				}
//			}

			equation3=equation3+b_3[b_3.length-1];
			for(int i = 1 ;i<b_3.length-1;i++){
				if(b_3[i]!=0)
					equation3=equation3+" + "+b_3[i]+" × "+"X"+i;
			}			
			double result1 = 0;
			double result2 = 0;
			double resutl3 = 0;

			CalRet cg = new CalRet();
			result1 = cg.cal(b2_1, indvarsdata1,t_1,t005_1);
			result2 = cg.cal(b2_2, indvarsdata2,t_2,t005_2);
			resutl3 =Math.abs(cg.cal(b2_3, indvarsdata3,t_3,t005_3));
			// System.out.println("F检验值为："+f);
			// System.out.println("选出的重要变量个数为："+UA);
			// System.out.println("他们分别为：：");
			// for(int i=0;i<b.length;i++){
			// System.out.println(b[i]+" ");
			// }
			// System.out.println("各变量的T检验值为：");
			// for(int i=0;i<t.length;i++){
			// System.out.println(t[i]+" ");
			// }
			// System.out.println("显著性为5的F临界值："+f005);
			// System.out.println("显著性为1的F临界值："+f001);
			// System.out.println("显著性为5的t临界值："+t005);
			// System.out.println("显著性为1的t临界值："+t001);
			ShowDataUnit sdu1 = new ShowDataUnit();
			ShowDataUnit sdu2 = new ShowDataUnit();
			ShowDataUnit sdu3 = new ShowDataUnit();
			sdu1.setF(f_1);
			sdu1.setF001(f001_1);
			sdu1.setF005(f005_1);
			sdu1.setResult(result1);
			sdu1.setT001(t001_1);
			sdu1.setT005(t005_1);
			sdu1.setUa(UA_1);
			sdu2.setF(f_2);
			sdu2.setF001(f001_2);
			sdu2.setF005(f005_2);
			sdu2.setResult(result2);
			sdu2.setT001(t001_2);
			sdu2.setT005(t005_2);
			sdu2.setUa(UA_2);
			sdu3.setF(f_3);
			sdu3.setF001(f001_3);
			sdu3.setF005(f005_3);
			sdu3.setResult(resutl3);
			sdu3.setT001(t001_3);
			sdu3.setT005(t005_3);
			sdu3.setUa(UA_3);
			response.setContentType("text/html ");
			response.setCharacterEncoding("gb2312");
			request.setAttribute("equation1", equation1);
			request.setAttribute("t_1", t_1);
			request.setAttribute("sdu1", sdu1);
			request.setAttribute("equation2", equation2);
			request.setAttribute("t_2", t_2);
			request.setAttribute("sdu2", sdu2);
			request.setAttribute("equation3", equation3);
			request.setAttribute("t_3", t_3);
			request.setAttribute("sdu3", sdu3);
			request.getRequestDispatcher("showResult2.jsp").forward(request,
					response);
		}
	}

	public void init() throws ServletException {
		// Put your code here
	}

}
