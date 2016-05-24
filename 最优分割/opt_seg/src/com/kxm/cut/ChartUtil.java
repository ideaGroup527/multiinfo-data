package com.kxm.cut;

import java.awt.Font;
import java.util.List;
import java.util.Random;

import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
/**
 * 本类用于生成绘图所用的DefaultCategoryDataset
 * @author kexinmei
 * @author kexinmeip@126.com
 * @version 1.0
 *  */
public class ChartUtil {      //字体      
	private static final Font PLOT_FONT = new Font("宋体", Font.BOLD, 15);      
	/** * 创建数据集合* @return CategoryDataset对象  
	 * @param 参数为一个包含各分割段离差平方总和的List
	 * */
	public static CategoryDataset createDataSet(List pResult) {          //图例名称    
		int cutnum = pResult.size()+1;
		String[] line = {"离差平方和"};                //折线名称
		String[] category = new String[cutnum-1];    //横坐标 段数                 
		for(int i = 0;i < category.length;i++){
			category[i] = ""+(i+2);
		}
		//实例化DefaultCategoryDataset对象          
		DefaultCategoryDataset dataSet = new DefaultCategoryDataset();          
		//使用循环向数据集合中添加数据
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

