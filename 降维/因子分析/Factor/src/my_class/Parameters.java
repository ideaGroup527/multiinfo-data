package my_class;

import java.sql.Connection;
import java.util.ArrayList;

import jxl.Sheet;
import jxl.Workbook;

public class Parameters {
	public static String url;//
	public static String[][] x;//
	public static String[][] x_new;//���������
	public static double[][] x1;//
	public static Sheet sheet;//
	public static Workbook workBook;//
	public static Connection conn;//
	public static String[] tableNames;//
	public static int factor_number;//�����Ӹ���
	public static double Jacobian ;//�ſɱ�
	public static double Variance ;//�������������ת����
	public static double[][] drawdata;
	public static ArrayList<ArrayList<Double>> factor_params;
	public static ArrayList<String> names;
}
