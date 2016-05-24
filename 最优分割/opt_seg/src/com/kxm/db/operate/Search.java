package com.kxm.db.operate;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
//import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 通用的查询方法
 */
public class Search extends DBAction {

	private String selectStr = "";
	private String fromStr = "";
	private String whereStr = "";

	private String[] colName;

	public Search() {
	}

	/**
	 * 拼装选择字段,包括select单词，多个字段
	 */
	public void chainCol(String[] colName) {
		this.colName = colName;
		StringBuffer sb = new StringBuffer();
		sb.append("select ");// 加入select单词
		// 循环加条件
		String stopStr = ",";
		for (int i = 0; i < colName.length; i++) {
			sb.append(colName[i]);
			sb.append(stopStr);
		}
		int strLength = sb.length() - stopStr.length();// 去掉多余的and之后剩下的字符串长度
		if (strLength > 0) {
			this.selectStr = sb.substring(0, strLength);
		}
	}

	/**
	 * 拼装选择字段,包括select单词，一个字段
	 */
	public void chainCol(String colName) {

		if ("*".equals(colName) || "".equals(colName) || null == colName) {
			throw new RuntimeException("列名无效，请确认");
		}

		this.colName = new String[1];//?
		this.colName[0] = colName;
		StringBuffer sb = new StringBuffer();
		sb.append("select ");// 加入select单词
		sb.append(colName);
		sb.append(" ");
		this.selectStr = sb.toString();
	}

	/**
	 * 拼装表名,多个表
	 */
	public void chainTable(String[] tableName) {
		StringBuffer sb = new StringBuffer();
		sb.append(" from ");// 加入select单词
		// 循环加条件
		String stopStr = ",";
		for (int i = 0; i < tableName.length; i++) {
			sb.append(tableName[i]);
			sb.append(stopStr);
		}
		int strLength = sb.length() - stopStr.length();// 去掉多余的"'"之后剩下的字符串长度
		if (strLength > 0) {
			this.fromStr = sb.substring(0, strLength);
		}
	}

	/**
	 * 拼装表名，一个表
	 */
	public void chainTable(String tableName) {
		StringBuffer sb = new StringBuffer();
		sb.append(" from ");// 加入select单词
		sb.append(tableName);
		sb.append(" ");
		this.fromStr = sb.toString();
	}

	/**
	 * 拼装where条件
	 */
	public void chainWhere(Map whereMap) {
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");// 写入where单词
		Set keys = whereMap.keySet();// 得到key集合
		Iterator it = keys.iterator();// 迭代
		String andStr = " and ";// and字符串
		while (it.hasNext()) {
			Object key = it.next();// 单个的KEY
			Object value = whereMap.get(key);// key对应的值
			sb.append(key + "='" + value+"'");// 拼装条件
			sb.append(andStr);// 加入and字符串
		}
		int strLength = sb.length() - andStr.length();// 去掉多余的and之后剩下的字符串长度
		if (strLength > 0) {
			this.whereStr = sb.substring(0, strLength);// 得到结果
		}
	}

	@Override
	public List work() {
		List dbList = new ArrayList();
		Statement stmt = (Statement) super.work();

		String sql = this.selectStr + this.fromStr + this.whereStr;
		try {
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				List temp = new ArrayList();
				for (int i = 0; i < colName.length; i++) {
					try {
						temp.add(rs.getString(colName[i]));
					} catch (RuntimeException e) {
						throw new RuntimeException("列名无效，请确认", e);
					}
				}
				dbList.add(temp);  //数据的形式是一行放在一个temp容器里 然后再把所有的temp容器放在dblist里
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// 关闭resultset
		this.resultSetClose();

		return dbList;
	}
}
