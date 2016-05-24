package my_class;

import java.sql.Connection;
import java.util.ArrayList;

import jxl.Sheet;
import jxl.Workbook;

public class Parameters {
	public static String url;//
	public static String[][] x;//
	public static String[][] x_new;//表格数据区
	public static double[][] x1;//
	public static Sheet sheet;//
	public static Workbook workBook;//
	public static Connection conn;//
	public static String[] tableNames;//
	public static int factor_number;//主因子个数
	public static double Jacobian ;//雅可比
	public static double Variance ;//方差最大正交旋转精度
	public static double[][] drawdata;
	public static ArrayList<ArrayList<Double>> factor_params;
	public static ArrayList<String> names;
}
