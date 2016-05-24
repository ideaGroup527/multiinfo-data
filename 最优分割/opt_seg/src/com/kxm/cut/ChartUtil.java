package com.kxm.cut;

import java.awt.Font;
import java.util.List;
import java.util.Random;

import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
/**
 * �����������ɻ�ͼ���õ�DefaultCategoryDataset
 * @author kexinmei
 * @author kexinmeip@126.com
 * @version 1.0
 *  */
public class ChartUtil {      //����      
	private static final Font PLOT_FONT = new Font("����", Font.BOLD, 15);      
	/** * �������ݼ���* @return CategoryDataset����  
	 * @param ����Ϊһ���������ָ�����ƽ���ܺ͵�List
	 * */
	public static CategoryDataset createDataSet(List pResult) {          //ͼ������    
		int cutnum = pResult.size()+1;
		String[] line = {"���ƽ����"};                //��������
		String[] category = new String[cutnum-1];    //������ ����                 
		for(int i = 0;i < category.length;i++){
			category[i] = ""+(i+2);
		}
		//ʵ����DefaultCategoryDataset����          
		DefaultCategoryDataset dataSet = new DefaultCategoryDataset();          
		//ʹ��ѭ�������ݼ������������
		for(int i = 0; i < line.length; i++){
			for (int j = 0; j < category.length; j++) { 
				Double temp = (Double)pResult.get(j);
				double tempd = temp.doubleValue();
				dataSet.addValue(tempd,line[i],category[j]);              
			}
		}
		return dataSet;
		}  
	}

