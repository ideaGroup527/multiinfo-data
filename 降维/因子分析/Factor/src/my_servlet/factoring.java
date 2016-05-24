package my_servlet;


import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import my_class.Parameters;
import my_class.factoranalysis;
import java.util.ArrayList;
public class factoring extends HttpServlet {

	public factoring() {
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
		response.setContentType("text/html; charset=gb2312");
		request.setCharacterEncoding("gb2312");
		PrintWriter out = response.getWriter();
		Parameters.factor_number = Integer.valueOf(request.getParameter("factor_number"));
		try {
			int i,j;
			out.println("<HTML>");
			out.println("  <HEAD><TITLE>��������(Data calculating)</TITLE></HEAD>");
			out.println("  <BODY bgcolor=\"#DDDDDD\">");
			out.println("<div align=\"center\">�������ӷ����ֲ�������:<br>");
			out.println("These are the calculated results of factor analysis step by step:");
			out.println("  <hr><br>");

			DecimalFormat df = new DecimalFormat( "0.00000 ");  			 
			factoranalysis fac = new factoranalysis();	
			Parameters.names = new ArrayList<String>();
			out.println("ԭʼ����(Original Data)");
			out.println("<br><table border=\"1\" cellspacing=\"0\"><tr><th>Sample\\Variable</th>");
			for(i=1;i<Parameters.x1[0].length;i++){				
				out.println("<th>"+i+"</th>");
				Parameters.names.add(Parameters.x[0][i]);
			}
			out.println("</tr>");
			for(int r=1;r<Parameters.x1.length;r++){
				out.println("<tr><td>"+r+"</td>");
				for(int c=1;c<Parameters.x1[0].length;c++){
					out.println("<td>"+Parameters.x1[r][c]+"</td>");
				}
				out.println("</tr>");
			}
			out.println("</table><br>");
			
			fac.Datastandard();	
			fac.Related_Coefficient_Matrix();
			out.println("���ϵ������(Correlation Coefficient Matrix)<br>");
			out.println("<br><table border=\"1\" cellspacing=\"0\"><tr><th>Variable\\Variable</th>");
			for(i=0;i<fac.r.length;i++){
				out.println("<th>"+(i+1)+"</th>");
			}
			out.println("</tr>");
			for(i=0;i<fac.r.length;i++){
				out.println("<tr><td>"+(i+1)+"</td>");
				for(j=0;j<fac.r[0].length;j++){
					out.println("<td>"+df.format(fac.r[i][j])+"</td>");
				}
				out.println("</tr>");
			}
			out.println("</table><br>");
			
			fac.Characteristic_Matrix(Parameters.Jacobian, Parameters.Variance);
			out.println("���ϵ����������(Correlation coefficient eigen vector)");
			out.println("<br><table border=\"1\" cellspacing=\"0\"><tr><th>Variable\\Variable</th>");
			for(i=0;i<fac.a.length;i++){
				out.println("<th>"+(i+1)+"</th>");
			}
			out.println("</tr>");
			for(i=0;i<fac.a.length;i++){
				out.println("<tr><td>"+(i+1)+"</td>");
				for(j=0;j<fac.a[0].length;j++){
					out.println("<td>"+df.format(fac.a[i][j])+"</td>");
				}
				out.println("</tr>");
			}
			out.println("</table><br>");
			out.println("<br>����ֵ�ٷֱȱ�(Eigenvalue percentage table)<br>");
			out.println("<table border=\"1\" cellspacing=\"0\"><tr><th>NO</th><th>Eigenvalue</th><th>Percentage values</th><th>Cumulative percentage value</th></tr>");
			for(i=0;i<fac.ch1.length;i++){
				out.println("<tr><td>"+(i+1)+"</td>");
				out.println("<td>"+df.format(fac.ch1[i])+"</td>");
				out.println("<td>"+df.format(fac.Lh[i])+"</td>");
				out.println("<td>"+df.format(fac.Lh1[i])+"</td>");
				out.println("</tr>");
			}
			out.println("</table><br>");			
			
			fac.Component_Matrix(Parameters.factor_number);
			out.println("��������(The main factor number)="+Parameters.factor_number+"<br>");
			out.println("�����غɾ���(Component Matrix)<br>");
			out.println("<br><table border=\"1\" cellspacing=\"0\"><tr><th>Variable\\Factor</th>");
			for(i=0;i<Parameters.factor_number;i++){
				out.println("<th>"+(i+1)+"</th>");
			}
			out.println("</tr>");
			for(i=0;i<fac.aa.length;i++){
				out.println("<tr><td>"+(i+1)+"</td>");
				for(j=0;j<fac.aa[0].length;j++){
					out.println("<td>"+df.format(fac.aa[i][j])+"</td>");
				}
				out.println("</tr>");
			}
			out.println("</table><br>");
			
			//��ͼ������ֵ			
			Parameters.factor_params = new ArrayList<ArrayList<Double>>();
			for(i=0; i<Parameters.factor_number; i++){
				ArrayList<Double> temp = new ArrayList<Double>();
				for(j=0; j<fac.aa.length; j++){
					temp.add(fac.aa[j][i]);
				}
				Parameters.factor_params.add(temp);
			}
			
			out.println("���������ӷ���(Common factor variance of Variables)<br>");
			out.println("<br><table border=\"1\" cellspacing=\"0\"><tr><th>Variable</th>");
			for(i=0;i<fac.xx.length;i++){
				out.println("<th>"+(i+1)+"</th>");
			}
			out.println("</tr>");
			out.println("<tr><td>1</td>");
			for(i=0;i<fac.xx.length;i++){
				out.println("<td>"+df.format(fac.xx[i])+"</td>");								
			}
			out.println("</tr>");
			out.println("</table><br>");
			
			fac.Varimax_orthogonal_rotation();
			out.println("�����������ת����(Varimax orthogonal rotation matrix)<br>");
			out.println("<br><table border=\"1\" cellspacing=\"0\"><tr><th>Variable\\Factor</th>");
			for(i=0;i<Parameters.factor_number;i++){
				out.println("<th>"+(i+1)+"</th>");
			}
			out.println("</tr>");
			for(i=0;i<fac.aa.length;i++){
				out.println("<tr><td>"+(i+1)+"</td>");
				for(j=0;j<fac.aa[0].length;j++){
					out.println("<td>"+df.format(fac.aa[i][j])+"</td>");
				}
				out.println("</tr>");
			}
			out.println("</table><br>");
			
			fac.orthogonal_factor();		
			out.println("�������ӵ÷�(Orthogonal factor score)<br>");
			out.println("<br><table border=\"1\" cellspacing=\"0\"><tr><th>Sample\\Factor</th>");
			for(i=0;i<Parameters.factor_number;i++){
				out.println("<th>"+(i+1)+"</th>");
			}
			out.println("</tr>");
			for(i=0;i<fac.xxx.length;i++){
				out.println("<tr><td>"+(i+1)+"</td>");
				for(j=0;j<fac.xxx[0].length;j++){
					out.println("<td>"+df.format(fac.xxx[i][j])+"</td>");
				}
				out.println("</tr>");
			}
			out.println("</table><br>");
						
			fac.oblique_factor();
			out.println("б��������ؾ���(Oblique factor correlation matrix)<br>");
			out.println("<br><table border=\"1\" cellspacing=\"0\"><tr><th>Factor\\Factor</th>");
			for(i=0;i<fac.L[0].length;i++){
				out.println("<th>"+(i+1)+"</th>");
			}
			out.println("</tr>");
			for(i=0;i<fac.L.length;i++){
				out.println("<tr><td>"+(i+1)+"</td>");
				for(j=0;j<fac.L[0].length;j++){
					out.println("<td>"+df.format(fac.L[i][j])+"</td>");
				}
				out.println("</tr>");
			}
			out.println("</table><br>");
			
			out.println("б�����ӽṹ����(Oblique factor structure matrix)<br>");
			out.println("<br><table border=\"1\" cellspacing=\"0\"><tr><th>Sample\\Factor</th>");
			for(i=0;i<Parameters.factor_number;i++){
				out.println("<th>"+(i+1)+"</th>");
			}
			out.println("</tr>");
			for(i=0;i<fac.s.length;i++){
				out.println("<tr><td>"+(i+1)+"</td>");
				for(j=0;j<fac.s[0].length;j++){
					out.println("<td>"+df.format(fac.s[i][j])+"</td>");
				}
				out.println("</tr>");
			}
			out.println("</table><br>");
			
			out.println("б������ģ�;���(Oblique factor model matrix)<br>");
			out.println("<br><table border=\"1\" cellspacing=\"0\"><tr><th>Variable\\Factor</th>");
			for(i=0;i<Parameters.factor_number;i++){
				out.println("<th>"+(i+1)+"</th>");
			}
			out.println("</tr>");
			for(i=0;i<fac.bb.length;i++){
				out.println("<tr><td>"+(i+1)+"</td>");
				for(j=0;j<fac.bb[0].length;j++){
					out.println("<td>"+df.format(fac.bb[i][j])+"</td>");
				}
				out.println("</tr>");
			}
			out.println("</table><br>");
			
			fac.oblique_factor_score();			
			out.println("б�����ӵ÷�(Oblique factor score)<br>");
			out.println("<br><table border=\"1\" cellspacing=\"0\"><tr><th>Sample\\Factor</th>");
			for(i=0;i<Parameters.factor_number;i++){
				out.println("<th>"+(i+1)+"</th>");
			}
			out.println("</tr>");
			for(i=0;i<fac.xxx.length;i++){
				out.println("<tr><td>"+(i+1)+"</td>");
				for(j=0;j<fac.xxx[0].length;j++){
					out.println("<td>"+df.format(fac.xxx[i][j])+"</td>");
				}
				out.println("</tr>");
			}
			out.println("</table><br>");
				
			out.println("<form method=\"GET\" action=\"../ChooseFactor.jsp\">");
			out.println("<p><input type=\"submit\" value=\"��һ��(next)\"></p>");
			out.println("</form>");
			out.println("<br>");
			out.println("<input type=\"button\" value=\"����(Back)\" onClick=\"javascript:history.back(-1);\">");
			out.println("</div>");
			out.println("  </BODY>");
			out.println("</HTML>");
		} catch (Exception e) {
			e.printStackTrace();
			out.println("error���Բ������ϴ����������󣬶�ȡ���ݳ�������" + e + "<br>");
			response.getWriter().println("<form method=\"POST\" action=\"../fileupload.jsp\">");
			response.getWriter().println("<p>�������ز�����ѡ��*.xls�ļ��ϴ�(Please click to back and chose *. xls file to upload)<br>");
			response.getWriter().println("<input type=\"submit\" value=\"����(back)\">");
			response.getWriter().println("</form>");
		}
	}
	public void init() throws ServletException {
		// Put your code here  Junit
	}
}
